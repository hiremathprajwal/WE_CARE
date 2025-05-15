package com.health.WE_CARE.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.health.WE_CARE.dto.ErrorMessage;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @Autowired
    private Environment environment;

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(WecareException.class)
    public ResponseEntity<ErrorMessage> exceptionHandler(WecareException e) {
        String errorMsg = environment.getProperty(e.getMessage());
        ErrorMessage error = new ErrorMessage(errorMsg != null ? errorMsg : "Unknown error occurred.");
        return ResponseEntity.ok(error);
    }
}

