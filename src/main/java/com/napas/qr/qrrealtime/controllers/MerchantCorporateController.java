package com.napas.qr.qrrealtime.controllers;

import com.napas.qr.qrrealtime.define.MerchantStatus;
import com.napas.qr.qrrealtime.models.CreatedMerchantBranchDTO;
import com.napas.qr.qrrealtime.models.CreatedMerchantCorporateDTO;
import com.napas.qr.qrrealtime.service.MerchantCorporateService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping(value = "/api/merchantCorporate", produces = "application/json")
public class MerchantCorporateController {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private MerchantCorporateService merchantCorporateService;

    @Operation(summary = "Search", description = "Lấy danh sách các Merchant Corporate", tags = {"Corporate"})
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
    @Operation(summary = "POST", description = "Thêm mới các Merchant Corporate", tags = {"Corporate"})
    @PostMapping()
    public ResponseEntity<?>post(@RequestBody CreatedMerchantCorporateDTO input){
        return merchantCorporateService.post(input);
    }

    @Operation(summary = "PUT", description = "Cập nhật các Merchant Corporate", tags = {"Corporate"})
    @PutMapping("/{id}")
    public ResponseEntity<?> put(HttpServletRequest request,@PathVariable Long id, @RequestBody CreatedMerchantCorporateDTO input){
        return merchantCorporateService.put(id, input);
    }
    @Operation(summary = "DELETE", description = "Xóa các Merchant Corporate", tags = {"Corporate"})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(HttpServletRequest request,@PathVariable Long id) {
        return merchantCorporateService.delete(id);
    }

    @Operation(summary = "DETAIL", description = "lấy thông tin chi tiết các Merchant Corporate", tags = {"Corporate"})
    @GetMapping("/detail/{id}")
    public ResponseEntity<?>merchantDetail(HttpServletRequest request,@PathVariable Long id){
        return merchantCorporateService.merchantDetail(id);
    }
    @GetMapping("/path")
    public ResponseEntity<?>getPatchResiterAccountBank(){
        return merchantCorporateService.getPatch();
    }
    @GetMapping()
    public ResponseEntity<?>list(){
        return merchantCorporateService.list();
    }
    @PostMapping("/checkCode")
    public ResponseEntity<?>checkCode(@RequestBody CreatedMerchantCorporateDTO input){
        return merchantCorporateService.checkCode(input);
    }
}
