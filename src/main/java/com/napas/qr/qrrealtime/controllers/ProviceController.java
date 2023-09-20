package com.napas.qr.qrrealtime.controllers;

import com.napas.qr.qrrealtime.models.ProviceDTO;
import com.napas.qr.qrrealtime.service.ProviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/mngweb/api/provice", produces = "application/json")
public class ProviceController {

    @Autowired
    private ProviceService proviceService;

    @GetMapping("/search")
    public List<ProviceDTO> search(HttpServletRequest request,
                                   @RequestParam(name = "provName", required = false) String provName){
        return proviceService.get(provName);
    }
}
