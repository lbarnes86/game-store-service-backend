package com.company.gamestoreservice.controller;

import com.company.gamestoreservice.exception.InvalidRequestException;
import com.company.gamestoreservice.exception.NoConsoleFoundException;
import com.company.gamestoreservice.exception.NoGameFoundException;
import com.company.gamestoreservice.exception.NoTshirtFoundException;
import com.company.gamestoreservice.models.SearchErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = InvalidRequestException.class)
    public ResponseEntity<SearchErrorResponse> handleInvalidRequest(InvalidRequestException e) {
        SearchErrorResponse error = new SearchErrorResponse(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        ResponseEntity<SearchErrorResponse> responseEntity = new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
        return responseEntity;
    }

    @ExceptionHandler(value= NoConsoleFoundException.class)
    public ResponseEntity<SearchErrorResponse> handleNoConsoleFound(NoConsoleFoundException e) {
        SearchErrorResponse error = new SearchErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
        ResponseEntity<SearchErrorResponse> responseEntity = new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        return responseEntity;
    }

    @ExceptionHandler(value= NoGameFoundException.class)
    public ResponseEntity<SearchErrorResponse> handleNoGameFound(NoGameFoundException e) {
        SearchErrorResponse error = new SearchErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
        ResponseEntity<SearchErrorResponse> responseEntity = new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        return responseEntity;
    }

    @ExceptionHandler(value= NoTshirtFoundException.class)
    public ResponseEntity<SearchErrorResponse> handleNoTshirtFound(NoTshirtFoundException e) {
        SearchErrorResponse error = new SearchErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
        ResponseEntity<SearchErrorResponse> responseEntity = new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        return responseEntity;
    }
}

