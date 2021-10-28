package com.switchfully.eurder.domain.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ControllerAdvisor {

    private final Logger logger = LoggerFactory.getLogger(ControllerAdvisor.class);

    @ExceptionHandler(UnauthorizedUserException.class)
    public void handleUnauthorizedUserException(UnauthorizedUserException exception, HttpServletResponse response) throws IOException {
        logger.error("Unauthorized User: " + exception.getMessage());
        response.sendError(HttpStatus.FORBIDDEN.value(), exception.getMessage());
    }
}
