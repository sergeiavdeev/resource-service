package ru.avdeev.resourceservice.controller;

import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.avdeev.resourceservice.dto.Error;
import ru.avdeev.resourceservice.exception.ApiException;
import ru.avdeev.resourceservice.exception.ResourceNotFoundException;

@ControllerAdvice
@Log
public class CommonControllerAdvice {

    @ExceptionHandler(ApiException.class)
    ResponseEntity<Error> resourceNotFond(ApiException ex) {

        return ResponseEntity
                .status(ex.getHttpStatus().code())
                .body(new Error(ex.getHttpStatus().code(), ex.getMessage()));
    }
}
