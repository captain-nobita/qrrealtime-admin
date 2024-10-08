package com.napas.qr.qrrealtime.controllers;

import com.napas.qr.qrrealtime.define.MerchantStatus;
import com.napas.qr.qrrealtime.models.CreateMerchantPersonalDTO;
import com.napas.qr.qrrealtime.models.UpdateAccountBankDTO;
import com.napas.qr.qrrealtime.service.MerchantPersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/mngweb/api/merchantPersonal", produces = "application/json")
public class MerchantPersonalController {

    @Autowired
    private MerchantPersonalService merchantPersonalService;

    @GetMapping("/search")
    public ResponseEntity<?> search(HttpServletRequest request,
                                    @PageableDefault( page = 0, size = 5) @SortDefault.SortDefaults({
                                            @SortDefault(sort = "id", direction = Sort.Direction.DESC)}) Pageable paging,
                                    @RequestParam(name = "name", required = false) String name,
                                    @RequestParam(name = "status", required = false) MerchantStatus status,
                                    @RequestParam(name = "merchantCode", required = false) String merchantCode
    ) {
        return merchantPersonalService.search(paging,name, status, merchantCode);
    }

    @PostMapping()
    public ResponseEntity<?>post(@RequestBody CreateMerchantPersonalDTO input){
        return merchantPersonalService.post(input);
    }

    @PutMapping()
    public ResponseEntity<?> put(@RequestBody CreateMerchantPersonalDTO input){
        return merchantPersonalService.put(input);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(HttpServletRequest request,@PathVariable Long id) {
        return merchantPersonalService.delete(id);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?>merchantDetail(@PathVariable Long id){
        return merchantPersonalService.merchantDetail(id);
    }
    @GetMapping("/path")
    public ResponseEntity<?>getPatchResiterAccountBank(){
        return merchantPersonalService.getPatch();
    }
    @GetMapping()
    public ResponseEntity<?> get(){
        return merchantPersonalService.list();
    }
    @PostMapping("/checkCode")
    public ResponseEntity<?>checkCode(@RequestBody CreateMerchantPersonalDTO input){
        return merchantPersonalService.checkCode(input);
    }

    @PutMapping("/updateAccountBank")
    public ResponseEntity<?>updateAccountBank(@RequestBody UpdateAccountBankDTO input){
        return merchantPersonalService.updateAccount(input);
    }
}
