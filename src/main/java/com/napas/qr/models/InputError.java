package com.napas.qr.models;

import lombok.Getter;

import java.util.Collections;
import java.util.Map;

public class InputError extends RuntimeException {
    @Getter
    private final Map<String,String> errors;

    public InputError(Map<String,String> errors) {
        super(errors.toString());
        this.errors = errors;
    }

    public InputError(String error, String errorMsg) {
        super("[" + error + ": " + errorMsg + "]");
        this.errors = Collections.singletonMap(error, errorMsg);

    }
}