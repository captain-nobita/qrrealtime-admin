package com.napas.qr.qrrealtime.controllers;

import com.napas.qr.qrrealtime.define.MerchantStatus;
import com.napas.qr.qrrealtime.service.MerchantCorporateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/merchantCorporate")
public class MerchantCorporateController {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private MerchantCorporateService merchantCorporateService;

    @GetMapping("/search")
    public ResponseEntity<?> search(HttpServletRequest request,
                                    @PageableDefault( page = 0, size = 5) @SortDefault.SortDefaults({
                                            @SortDefault(sort = "id", direction = Sort.Direction.DESC)}) Pageable paging,
                                    @RequestParam(name = "name", required = false) String name,
                                    @RequestParam(name = "status", required = false) MerchantStatus status,
                                    @RequestParam(name = "merchantCode", required = false) String merchantCode
    ) {
        return merchantCorporateService.search(paging,name, status, merchantCode);
    }

}
