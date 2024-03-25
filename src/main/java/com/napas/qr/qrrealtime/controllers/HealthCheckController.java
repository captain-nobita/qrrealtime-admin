package com.napas.qr.qrrealtime.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/mngweb/api/healthcheck")
public class HealthCheckController {

    @GetMapping()
    public ResponseEntity<String> check() {
        return ResponseEntity.ok("Ngon choet");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error message")
    public void handleError() {
    }
}
