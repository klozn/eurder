package com.switchfully.eurder.api;

import com.switchfully.eurder.api.dto.customer.CreateCustomerDto;
import com.switchfully.eurder.api.dto.customer.CustomerDto;
import com.switchfully.eurder.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService service;
    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerCustomer(@RequestBody CreateCustomerDto customerDto) {
        logger.info("Creating new customer for email: " + customerDto.getEmail());
        boolean created = service.createNewCustomer(customerDto);
        if (created) {
            logger.info("Customer creation successful");
        } else {
            logger.error("Customer creation failed.");
        }
    }

    @GetMapping(produces = "application/json")
    public List<CustomerDto> getAllCustomers(@RequestHeader String authorizedUserId) {
        logger.info("Getting all customers for user with id: " + authorizedUserId);
        return service.getAllCustomers(authorizedUserId);
    }



    @ExceptionHandler(IllegalArgumentException.class)
    public void handleIllegalArgumentException(IllegalArgumentException exception, HttpServletResponse response) throws IOException {
        logger.error("Illegal Argument for Customer: " + exception.getMessage());
        response.sendError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    public void handleIllegalStateException(IllegalStateException exception, HttpServletResponse response) throws IOException {
        logger.error("Illegal State for Customers: " + exception.getMessage());
        response.sendError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public void handleNullPointerException(NullPointerException exception, HttpServletResponse response) throws IOException {
        logger.error("Null Pointer for Customer: " + exception.getMessage());
        response.sendError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }
}
