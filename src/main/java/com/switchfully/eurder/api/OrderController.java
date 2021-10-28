package com.switchfully.eurder.api;

import com.switchfully.eurder.api.dto.orders.CreateOrderDto;
import com.switchfully.eurder.services.OrderService;
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
@RequestMapping("/orders")
public class OrderController {
    private final OrderService service;
    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerOrder(@RequestBody CreateOrderDto orderDto, @RequestHeader String authorizedUserId) {
        logger.info("Creating order for user with id: " + authorizedUserId);
        boolean created = service.createNewOrder(orderDto, authorizedUserId);
        if (created) {
            logger.info("Order creation successful");
        } else {
            logger.error("Order creation failed.");
        }
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public void handleIllegalArgumentException(IllegalArgumentException exception, HttpServletResponse response) throws IOException {
        logger.error("Illegal Argument for Order: " + exception.getMessage());
        response.sendError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public void handleNullPointerException(NullPointerException exception, HttpServletResponse response) throws IOException {
        logger.error("Null Pointer for Order: " + exception.getMessage());
        response.sendError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }
}
