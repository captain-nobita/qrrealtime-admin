package com.napas.qr.qrrealtime.controllers;

import com.napas.qr.qrrealtime.define.MerchantStatus;
import com.napas.qr.qrrealtime.service.MerchantBranchService;
import com.napas.qr.qrrealtime.service.MerchantCorporateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/merchantBranch")
public class MerchantBranchController {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private MerchantBranchService merchantBranchService;

    @GetMapping("/search")
    public ResponseEntity<?> search(HttpServletRequest request,
                                    @PageableDefault( page = 0, size = 5) @SortDefault.SortDefaults({
                                            @SortDefault(sort = "id", direction = Sort.Direction.DESC)}) Pageable paging,
                                    @RequestParam(name = "branchName", required = false) String branchName,
                                    @RequestParam(name = "status", required = false) MerchantStatus status,
                                    @RequestParam(name = "branchCode", required = false) String branchCode
    ) {
        return merchantBranchService.search(paging,branchName, status, branchCode);
    }
}
