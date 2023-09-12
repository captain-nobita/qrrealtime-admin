package com.napas.qr.models;

import com.napas.qr.define.MerchantStatus;
import lombok.Data;
import java.util.Date;

@Data
public class MerchantDetail {

    private Long id;
    private String deptName;
    private String  merchantId;
    private String merchantCode;
    private String merchantName;
    private Long provinceId;
    private Long districtId;
    private String address;
    private String telephone;
    private String managerName;
    private String taxCode;
    private String emailAddress;
    private String businessRegistrationNumber;
    private String contractNumber;
    private String bankAccountNumber;
    private MerchantStatus merchantStatus;

    public MerchantDetail(Long id, String deptName, String merchantId, String merchantCode, String merchantName, Long provinceId, Long districtId, String address, String telephone, String managerName, String taxCode, String emailAddress, String businessRegistrationNumber, String contractNumber, String bankAccountNumber, MerchantStatus merchantStatus) {
        this.id = id;
        this.deptName = deptName;
        this.merchantId = merchantId;
        this.merchantCode = merchantCode;
        this.merchantName = merchantName;
        this.provinceId = provinceId;
        this.districtId = districtId;
        this.address = address;
        this.telephone = telephone;
        this.managerName = managerName;
        this.taxCode = taxCode;
        this.emailAddress = emailAddress;
        this.businessRegistrationNumber = businessRegistrationNumber;
        this.contractNumber = contractNumber;
        this.bankAccountNumber = bankAccountNumber;
        this.merchantStatus = merchantStatus;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getBusinessRegistrationNumber() {
        return businessRegistrationNumber;
    }

    public void setBusinessRegistrationNumber(String businessRegistrationNumber) {
        this.businessRegistrationNumber = businessRegistrationNumber;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public MerchantStatus getMerchantStatus() {
        return merchantStatus;
    }

    public void setMerchantStatus(MerchantStatus merchantStatus) {
        this.merchantStatus = merchantStatus;
    }
}
