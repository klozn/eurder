package com.switchfully.eurder.api;

import com.switchfully.eurder.api.dto.items.ItemDto;
import com.switchfully.eurder.services.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    public void registerItem(@RequestBody ItemDto itemDto, @RequestHeader String authorizedUserId) {
        logger.info("Creating item with name: " + itemDto.getName() + " by authorizedUserId: " + authorizedUserId);
        boolean created = service.createNewItem(itemDto, authorizedUserId);
        if (created) {
            logger.info("Item creation successful");
        } else {
            logger.error("Item creation failed.");
        }
    }

    @PutMapping(path = "/{itemId}", consumes = "application/json")
    public void updateItem(@PathVariable String itemId, @RequestBody ItemDto itemDto, @RequestHeader String authorizedUserId) {
        logger.info("Updating item with name: " + itemDto.getName() + " by authorizedUserId: " + authorizedUserId);
        boolean updated = service.updateItem(itemId, itemDto, authorizedUserId);
        if (updated) {
            logger.info("Item update successful");
        } else {
            logger.error("Item update failed.");
        }
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public void handleIllegalArgumentException(IllegalArgumentException exception, HttpServletResponse response) throws IOException {
        logger.error("Illegal Argument for Item: " + exception.getMessage());
        response.sendError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public void handleNullPointerException(NullPointerException exception, HttpServletResponse response) throws IOException {
        logger.error("Null Pointer for Item: " + exception.getMessage());
        response.sendError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }
}
