package com.napas.qr.models;

import com.napas.qr.define.UserGroupType;
import com.napas.qr.define.UserStatusType;
import com.napas.qr.validation.PciCompliantPassword;
import com.napas.qr.validation.ValidUsername;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String email;

    private String fullName;

    @PciCompliantPassword
    private String passwordHashes;

    @ValidUsername
    private String username;

    private UserGroupType userGroupType;

    private String deptName;

    private String merchantName;

    private UserStatusType status;

    private String userPosition;

    private String userUnit;

    private String userAddress;

    private Long districtId;

    private Long roleId;

    private String telephone;

    private Long provinceId;

    private Long deptId;

    private Long merchantId;
}
