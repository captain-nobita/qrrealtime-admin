package com.napas.qr.qrrealtime.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.napas.qr.qrrealtime.define.MerchantStatus;
import lombok.Data;

import java.util.Date;

@Data
public class MerchantCashierDTO {

    private Long id;

    private String cashierCode;

    @JsonIgnore
    private MerchantStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private Date dateCreated;

    private Long createdByUser;

    private Long modifiedByUser;

    private Long  branchId;
}
