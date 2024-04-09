/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.napas.qr.qrrealtime.controllers;

import com.napas.qr.qrrealtime.define.SettlementStatusDef;
import com.napas.qr.qrrealtime.service.HisPaymentService;
import io.swagger.v3.oas.annotations.Operation;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author huynx
 */
@RestController
@RequestMapping(value = "/api/payment", produces = "application/json")
public class HisPaymentController {
    @Autowired
    private HisPaymentService hisPaymentService;

    @Operation(summary = "Search", description = "Lấy danh sách các giao dịch", tags = {"Payment"})
    @GetMapping("/search")
    public ResponseEntity<?> search(
    @PageableDefault(page = 0, size = 5) @SortDefault.SortDefaults({
                                            @SortDefault(sort = "id", direction = Sort.Direction.DESC)}) Pageable paging, 
            @RequestParam(name = "branchId", required = false) Long branchId, 
            @RequestParam(name = "cashierId", required = false) Long cashierId,
            @RequestParam(name = "status", required = false) SettlementStatusDef status,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam(name = "dateTimeBegin", required = false) LocalDateTime dateTimeBegin,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @RequestParam(name = "dateTimeEnd", required = false) LocalDateTime dateTimeEnd
    ) {
        return hisPaymentService.search(paging, dateTimeBegin, dateTimeEnd, branchId, cashierId, status);
    }
}
