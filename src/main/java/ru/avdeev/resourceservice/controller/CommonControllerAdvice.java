package ru.avdeev.resourceservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.avdeev.resourceservice.dto.Error;
import ru.avdeev.resourceservice.exception.ResourceNotFoundException;

@ControllerAdvice
public class CommonControllerAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<Error> resourceNotFond(ResourceNotFoundException ex) {
        return ResponseEntity
                .status(ex.getHttpStatus().code())
                .body(new Error(ex.getHttpStatus().code(), ex.getMessage()));
    }
}
