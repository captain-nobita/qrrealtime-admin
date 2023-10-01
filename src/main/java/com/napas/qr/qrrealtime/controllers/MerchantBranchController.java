package com.napas.qr.qrrealtime.controllers;

import com.napas.qr.qrrealtime.define.MerchantStatus;
import com.napas.qr.qrrealtime.models.CreatedMerchantBranchDTO;
import com.napas.qr.qrrealtime.models.CreatedMerchantCorporateDTO;
import com.napas.qr.qrrealtime.models.UpdateAccountBankDTO;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/mngweb/api/merchantBranch", produces = "application/json")
public class MerchantBranchController {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private MerchantBranchService merchantBranchService;

    @GetMapping("/search")
    public ResponseEntity<?> search(@PageableDefault(page = 0, size = 5) @SortDefault.SortDefaults({
            @SortDefault(sort = "id", direction = Sort.Direction.DESC)}) Pageable paging,
                                    @RequestParam(name = "branchName", required = false) String branchName,
                                    @RequestParam(name = "status", required = false) MerchantStatus status,
                                    @RequestParam(name = "branchCode", required = false) String branchCode
    ) {
        return merchantBranchService.search(paging, branchName, status, branchCode);
    }
    @PostMapping()
    public ResponseEntity<?>post(@RequestBody CreatedMerchantBranchDTO input){
        return merchantBranchService.post(input);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>put(@PathVariable Long id,@RequestBody CreatedMerchantBranchDTO input){
        return merchantBranchService.put(id,input);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return merchantBranchService.delete(id);
    }
    @GetMapping()
    public ResponseEntity<?>list(){
        return merchantBranchService.list();
    }
    @PostMapping("/checkCode")
    public ResponseEntity<?>checkCode(@RequestBody CreatedMerchantBranchDTO input){
        return merchantBranchService.checkCode(input);
    }

    @PutMapping("/updateAccountBank/{id}")
    public ResponseEntity<?>updateAccountBank(@PathVariable Long id, @RequestBody UpdateAccountBankDTO input){
        return merchantBranchService.updateAccount(id, input);
    }
}
