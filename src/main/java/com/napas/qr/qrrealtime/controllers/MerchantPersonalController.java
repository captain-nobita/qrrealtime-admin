package com.napas.qr.qrrealtime.controllers;

import com.napas.qr.qrrealtime.define.MerchantStatus;
import com.napas.qr.qrrealtime.models.CreateMerchantPersonalDTO;
import com.napas.qr.qrrealtime.models.TblMerchantPersonalDTO;
import com.napas.qr.qrrealtime.service.MerchantPersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.SignatureException;

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

    @PutMapping("/{id}")
    public ResponseEntity<?> put(HttpServletRequest request,@PathVariable Long id, @RequestBody CreateMerchantPersonalDTO input){
        return merchantPersonalService.put(id, input);
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
}
