/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.napas.qr.qrrealtime.controllers;

import com.napas.qr.qrrealtime.define.MerchantStatus;
import com.napas.qr.qrrealtime.models.CreateMerchantPersonalDTO;
import com.napas.qr.qrrealtime.models.CreatedUserDTO;
import com.napas.qr.qrrealtime.models.UserDetail;
import com.napas.qr.qrrealtime.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author huynx
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/mngweb/api/user", produces = "application/json")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('ROOT', 'ADMIN')")
    @GetMapping("/{id}")
    public UserDetail get(@PathVariable String id) {
        return userService.get(Long.parseLong(id));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error message")
    public void handleError() {
    }
    @GetMapping("/search")
    public ResponseEntity<?> search(HttpServletRequest request,
                                    @PageableDefault( page = 0, size = 5) @SortDefault.SortDefaults({
                                            @SortDefault(sort = "id", direction = Sort.Direction.DESC)}) Pageable paging,
                                    @RequestParam(name = "fullname", required = false) String fullname,
                                    @RequestParam(name = "status", required = false) MerchantStatus status,
                                    @RequestParam(name = "email", required = false) String email
    ) {
        return userService.search(paging,fullname, status, email);
    }

    @PostMapping()
    public ResponseEntity<?>post(@RequestBody CreatedUserDTO input){
        return userService.post(input);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return userService.delete(id);
    }


}
