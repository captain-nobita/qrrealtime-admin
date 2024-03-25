package com.napas.qr.qrrealtime.controllers;

import com.napas.qr.qrrealtime.define.MerchantStatus;
import com.napas.qr.qrrealtime.models.CreatedMerchantBranchDTO;
import com.napas.qr.qrrealtime.models.MerchantCashierDTO;
import com.napas.qr.qrrealtime.service.MerchantCashierService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/merchantCashier", produces = "application/json")
public class MerchantCashierController {

    @Autowired
    private MerchantCashierService merchantCashierService;

    @Operation(summary = "Search", description = "Lấy danh sách các Merchant Cashier", tags = {"Cashier"})
    @GetMapping("/search")
    public ResponseEntity<?> search(HttpServletRequest request,
                                    @PageableDefault( page = 0, size = 5) @SortDefault.SortDefaults({
                                            @SortDefault(sort = "id", direction = Sort.Direction.DESC)}) Pageable paging,
                                    @RequestParam(name = "cashierCode", required = false) String cashierCode,
                                    @RequestParam(name = "status", required = false) MerchantStatus status
    ) {
        return merchantCashierService.search(paging,cashierCode, status);
    }
    @Operation(summary = "POST", description = "Thêm mới Merchant Cashier", tags = {"Cashier"})
    @PostMapping()
    public ResponseEntity<?>post(@RequestBody MerchantCashierDTO input){
        return merchantCashierService.post(input);
    }

    @Operation(summary = "PUT", description = "Cập nhật thông tin các Merchant Cashier", tags = {"Cashier"})
    @PutMapping("/{id}")
    public ResponseEntity<?>put(@PathVariable Long id,@RequestBody MerchantCashierDTO input){
        return merchantCashierService.put(id,input);
    }
    @Operation(summary = "DELETE", description = "Xóa thông tin các Merchant Cashier", tags = {"Cashier"})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return merchantCashierService.delete(id);
    }

    @Operation(summary = "LIST", description = "Liệt kê các Merchant Cashier", tags = {"Cashier"})
    @GetMapping()
    public ResponseEntity<?>list(){
        return merchantCashierService.list();
    }
    @PostMapping("/checkCode")
    public ResponseEntity<?>checkCode(@RequestBody MerchantCashierDTO input){
        return merchantCashierService.checkCode(input);
    }
}
