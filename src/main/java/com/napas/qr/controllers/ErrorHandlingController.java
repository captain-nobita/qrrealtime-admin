package com.napas.qr.controllers;

import com.napas.qr.models.ExpiredCredentialException;
import com.napas.qr.models.InputError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ErrorHandlingController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InputError.class)
    public Map<String,String> inputError(InputError e) {
        return e.getErrors();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public Map<String, String> handleValidationExceptions(BindException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ExceptionHandler(ExpiredCredentialException.class)
    public ResponseEntity<ExpiredCredentialException.UserInfo> handleExpiredCredential(ExpiredCredentialException e) {
        return ResponseEntity.status(480)
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(e.getUser());
    }
}

