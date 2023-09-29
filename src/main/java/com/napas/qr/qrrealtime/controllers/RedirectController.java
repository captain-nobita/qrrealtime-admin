package com.napas.qr.qrrealtime.controllers;

import com.napas.qr.qrrealtime.define.ETargetType;
import com.napas.qr.qrrealtime.service.RedirectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mngweb/no-auth/api/redirect")
public class RedirectController {

    @Autowired
    private RedirectService redirectService;

    @GetMapping()
    public ResponseEntity<?>get(@RequestParam("targetType")ETargetType targetType,
                                @RequestParam("targetId") Long targetId,
                                @RequestParam("account") String account){
        return redirectService.get(targetType,targetId, account);
    }
}
