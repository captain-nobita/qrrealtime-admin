package com.napas.qr.models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BCGiaoDich {
    private String tenDonVi;
    private String tenMerchant;
    private Long soLuongGd;
    private BigDecimal giaTriGd;
}
