package com.ewallet.exception;

import com.ewallet.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFound(ResourceNotFoundException e) {
        String message = e.getMessage();
        ApiResponse apiResponse=ApiResponse.builder().message(message).status(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ApiResponse> insufficientBalance(InsufficientBalanceException e) {
        String message = e.getMessage();
        ApiResponse apiResponse=ApiResponse.builder().message(message).status(HttpStatus.UNAUTHORIZED).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.UNAUTHORIZED);
    }

}
