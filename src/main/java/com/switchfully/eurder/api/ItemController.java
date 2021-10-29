package com.switchfully.eurder.api;

import com.switchfully.eurder.api.dto.items.ItemDto;
import com.switchfully.eurder.api.dto.items.UpdateItemDto;
import com.switchfully.eurder.services.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService service;
    private final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    public ItemController(ItemService service) {
        this.service = service;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerItem(@RequestBody UpdateItemDto updateItemDto, @RequestHeader String authorizedUserId) {
        logger.info("Creating item with name: " + updateItemDto.getName() + " by authorizedUserId: " + authorizedUserId);
        boolean created = service.createNewItem(updateItemDto, authorizedUserId);
        if (created) {
            logger.info("Item creation successful");
        } else {
            logger.error("Item creation failed.");
        }
    }

    @PutMapping(path = "/{itemId}", consumes = "application/json")
    public void updateItem(@PathVariable String itemId, @RequestBody UpdateItemDto updateItemDto,
                           @RequestHeader String authorizedUserId) {
        logger.info("Updating item with name: " + updateItemDto.getName() + " by authorizedUserId: " + authorizedUserId);
        boolean updated = service.updateItem(itemId, updateItemDto, authorizedUserId);
        if (updated) {
            logger.info("Item update successful");
        } else {
            logger.error("Item update failed.");
        }
    }

    @GetMapping(produces = "application/json")
    public List<ItemDto> getItems() {
        logger.info("Showing all items.");
        return service.getItems();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public void handleIllegalArgumentException(IllegalArgumentException exception,
                                               HttpServletResponse response) throws IOException {
        logger.error("Illegal Argument for Item: " + exception.getMessage());
        response.sendError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public void handleNullPointerException(NullPointerException exception,
                                           HttpServletResponse response) throws IOException {
        logger.error("Null Pointer for Item: " + exception.getMessage());
        response.sendError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }
}
