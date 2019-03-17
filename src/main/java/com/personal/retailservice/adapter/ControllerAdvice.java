package com.personal.retailservice.adapter;/*
Created By samathashetty on 17/03/19
*/

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.status;

@ControllerAdvice
final class ControllerExceptionAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<String> acceptIllegalArgumentException(final IllegalArgumentException ex) {
        return status(BAD_REQUEST).body("Illegal argument exception: " + ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    ResponseEntity<String> acceptHttpMessageNotReadableException(final HttpMessageNotReadableException ex) {
        return status(BAD_REQUEST).body("HTTP message not readable exception: " + ex.getMessage());
    }



    @ExceptionHandler(NoSuchElementException.class)
    ResponseEntity<String> acceptNoSuchElementException(final NoSuchElementException ex) {
        return status(NOT_FOUND).body("No such element exception: " + ex.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    ResponseEntity<String> acceptThrowable(final Throwable ex) {
        return status(INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

}