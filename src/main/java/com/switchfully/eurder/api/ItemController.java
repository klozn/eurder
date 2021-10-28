package com.switchfully.eurder.api;

import com.switchfully.eurder.api.dto.items.CreateItemDto;
import com.switchfully.eurder.services.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    public void registerItem(@RequestBody CreateItemDto itemDto, @RequestHeader String authorizedUserId) {
        logger.info("Creating item with name: " + itemDto.getName() + " by authorizedUserId: " + authorizedUserId);
        logger.info("Created = " + service.createNewItem(itemDto, authorizedUserId));
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
