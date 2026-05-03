package com.pioneers.fp.exceptions;

import com.pioneers.fp.exceptions.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RegisterException.class)
    public ResponseEntity<ErrorResponse> handleRegistration(RegisterException ex){
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(ex.getMessage(), 400));
    }
}
