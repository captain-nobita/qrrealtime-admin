package com.napas.qr.qrrealtime.controllers;

import com.napas.qr.qrrealtime.models.DistrictDTO;
import com.napas.qr.qrrealtime.service.DistrictService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/api/district", produces = "application/json")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @Operation(summary = "Search", description = "Lấy danh sách các quận huyện theo ID tỉnh/Thành phố", tags = {"district"})
    @GetMapping("/search")
    public List<DistrictDTO>search(HttpServletRequest request,
                                   @RequestParam(name = "provId", required = false) Long provId){
        return districtService.get(provId);
    }
}
