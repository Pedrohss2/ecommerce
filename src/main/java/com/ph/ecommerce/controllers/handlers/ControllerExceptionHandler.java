package com.ph.ecommerce.controllers.handlers;

import com.ph.ecommerce.dto.error.CustomError;
import com.ph.ecommerce.services.exceptions.DataBaseException;
import com.ph.ecommerce.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    private final Instant moment = Instant.now();

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException error, HttpServletRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError err = new CustomError(moment, status.value(), error.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<CustomError> dataBase(DataBaseException error, HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        CustomError err = new CustomError(moment, status.value(), error.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

}

