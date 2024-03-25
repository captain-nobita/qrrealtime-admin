package com.napas.qr.qrrealtime.controllers;

import com.napas.qr.qrrealtime.define.MerchantStatus;
import com.napas.qr.qrrealtime.models.CreatedMerchantBranchDTO;
import com.napas.qr.qrrealtime.models.CreatedMerchantCorporateDTO;
import com.napas.qr.qrrealtime.models.UpdateAccountBankDTO;
import com.napas.qr.qrrealtime.service.MerchantBranchService;
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
@RequestMapping(value = "/api/merchantBranch", produces = "application/json")
public class MerchantBranchController {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private MerchantBranchService merchantBranchService;

    @Operation(summary = "Search", description = "Lấy danh sách các Merchant Branch", tags = {"MerchantBranch"})
    @GetMapping("/search")
    public ResponseEntity<?> search(@PageableDefault(page = 0, size = 5) @SortDefault.SortDefaults({
            @SortDefault(sort = "id", direction = Sort.Direction.DESC)}) Pageable paging,
                                    @RequestParam(name = "branchName", required = false) String branchName,
                                    @RequestParam(name = "status", required = false) MerchantStatus status,
                                    @RequestParam(name = "branchCode", required = false) String branchCode
    ) {
        return merchantBranchService.search(paging, branchName, status, branchCode);
    }
    @Operation(summary = "POST", description = "Thêm mới Merchant Branch", tags = {"MerchantBranch"})
    @PostMapping()
    public ResponseEntity<?>post(@RequestBody CreatedMerchantBranchDTO input){
        return merchantBranchService.post(input);
    }

    @Operation(summary = "PUT", description = "Sửa thông tin các Merchant Branch", tags = {"MerchantBranch"})
    @PutMapping("/{id}")
    public ResponseEntity<?>put(@PathVariable Long id,@RequestBody CreatedMerchantBranchDTO input){
        return merchantBranchService.put(id,input);
    }
    @Operation(summary = "DELETE", description = "Xóa các Merchant Branch", tags = {"MerchantBranch"})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return merchantBranchService.delete(id);
    }

    @Operation(summary = "Search", description = "Lấy danh sách các Merchant Branch không phân trang", tags = {"MerchantBranch"})
    @GetMapping()
    public ResponseEntity<?>list(){
        return merchantBranchService.list();
    }
    @PostMapping("/checkCode")
    public ResponseEntity<?>checkCode(@RequestBody CreatedMerchantBranchDTO input){
        return merchantBranchService.checkCode(input);
    }

    @Operation(summary = "Update", description = "Cập nhật số tài khoản các Merchant Branch", tags = {"MerchantBranch"})
    @PutMapping("/updateAccountBank/{id}")
    public ResponseEntity<?>updateAccountBank(@PathVariable Long id, @RequestBody UpdateAccountBankDTO input){
        return merchantBranchService.updateAccount(id, input);
    }
}
