package com.napas.qr.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.napas.qr.define.MerchantStatus;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class MerchantDTO {

    private Long id;
    @NotEmpty(message = "MerchantId không được để trống")
    private String merchantId;
    @NotEmpty(message = "Tên Merchant không được để trống")
    private String merchantName;
    @NotNull
    private Long deptId;

    private String deptName;
    private String address;
    private String telephone;

    private String managerName;

    @JsonIgnore
    private MerchantStatus merchantStatus;

    private Date dateCreated;
    private String createBy;
    private Date dateModified;
    private String nguoiCapNhat;
    private Date dateApproved;
    private String nguoiDuyet;

    @NotEmpty(message = "Mã merchant không được để trống")
    private String merchantCode;

    private Long districtId;
    private Long provinceId;
    private String taxCode;
    private String emailAddress;
    private String businessRegistrationNumber;
    private String contractNumber;
    private Long createdBy;
    private Long modifiedBy;
    private Long approvedBy;
    private String bankAccountNumber;
}
