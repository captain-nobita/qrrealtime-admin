/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.napas.qr.controllers;

import com.napas.qr.models.HisApiAccessDTO;
import com.napas.qr.service.HisApiAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 *
 * @author HuyNX
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/hisapiaccess",produces = "application/json")
public class HisApiAccessController {

    @Autowired
    private HisApiAccessService hisAccessService;

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Page<HisApiAccessDTO> searchPaging(
            @RequestParam(name = "begindate", required = false) String beginDate,
            @RequestParam(name = "enddate", required = false) String endDate,
            @RequestParam(name = "username", required = false) String username,
            @RequestParam(name = "method", required = false) String method,
            @RequestParam(name = "page", required = false) String pageIndex,
            @RequestParam(name = "pagesize", required = false) String pageSize) throws ParseException {
        return hisAccessService.searchPaging(username, method, beginDate, endDate, Integer.parseInt(pageIndex) - 1, Integer.parseInt(pageSize));
    }

    @PreAuthorize("hasAnyAuthority('ROOT', 'ADMIN', 'SENIOR_OPERATOR')")
    @GetMapping("subjects")
    public List<String> listExistingSubjects() {
        return null;
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error message")
    public void handleError() {
    }
}
