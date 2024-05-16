package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(NoUserFoundException.class)
    public ResponseEntity<Object> handleException(NoUserFoundException ex) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
