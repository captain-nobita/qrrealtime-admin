package com.napas.qr.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/healthcheck",produces = "application/json")
public class HealthCheckController {

    @GetMapping
    public ResponseEntity<?> get(){
        return ResponseEntity.ok("app vẫn khỏe");
    }
}
