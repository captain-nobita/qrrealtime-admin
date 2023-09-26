package com.napas.qr.qrrealtime.controllers;

import com.napas.qr.qrrealtime.define.MerchantStatus;
import com.napas.qr.qrrealtime.models.MerchantCashierDTO;
import com.napas.qr.qrrealtime.service.MerchantCashierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/mngweb/api/merchantCashier", produces = "application/json")
public class MerchantCashierController {

    @Autowired
    private MerchantCashierService merchantCashierService;

    @GetMapping("/search")
    public ResponseEntity<?> search(HttpServletRequest request,
                                    @PageableDefault( page = 0, size = 5) @SortDefault.SortDefaults({
                                            @SortDefault(sort = "id", direction = Sort.Direction.DESC)}) Pageable paging,
                                    @RequestParam(name = "cashierCode", required = false) String cashierCode,
                                    @RequestParam(name = "status", required = false) MerchantStatus status
    ) {
        return merchantCashierService.search(paging,cashierCode, status);
    }
    @PostMapping()
    public ResponseEntity<?>post(@RequestBody MerchantCashierDTO input){
        return merchantCashierService.post(input);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>put(@PathVariable Long id,@RequestBody MerchantCashierDTO input){
        return merchantCashierService.put(id,input);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return merchantCashierService.delete(id);
    }
    @GetMapping()
    public ResponseEntity<?>list(){
        return merchantCashierService.list();
    }
}
