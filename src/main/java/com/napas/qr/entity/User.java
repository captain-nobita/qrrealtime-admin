package com.napas.qr.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.napas.qr.define.UserGroupType;
import com.napas.qr.define.UserStatusType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Data
@Table(name = "TBL_AUTH_USER")
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_AUTH_USER")
    @SequenceGenerator(sequenceName = "SEQ_AUTH_USER", allocationSize = 1, name = "SEQ_AUTH_USER")
    @Column(name = "ID")
    private Long id;

    @Column(name = "CREATED_AT")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private Date createdAt;

    @Column(name = "EMAIL")
    @NotNull
    @Email
    private String email;

    @Column(name = "FAILED_LOGIN_COUNT")
    @NotNull
    private Long failedLoginCount;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "IS_ENABLED")
    private Integer isEnabled;

    @Column(name = "LAST_CREDENTIAL_UPDATE_AT")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private Date lastCredentialUpdateAt;

    @Column(name = "LAST_FAILED_LOGIN")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private Date lastFailedLogin;

    @Column(name = "LAST_LOGGED_IN")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private Date lastLoggedIn;

    @Column(name = "LOCKED_UNTIL")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private Date lockedUntil;

    @Column(name = "PASSWORD_HASHES")
    @NotNull
    private String passwordHashes;

    @Column(name = "USERNAME")
    @NotNull
    @Size(min = 1, max = 50)
    private String username;

    @Column(name = "LAST_DETAIL_UPDATED_AT")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private Date lastDetailUpdateAt;

    @Column(name = "EMAIL_RECOVERY_TOKEN")
    @Email
    private String emailRecoveryToken;

    @Column(name = "EMAIL_RECOVERY_TOKEN_EXPIRE_AT")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private Date emailRecoveryTokenExpireAt;

    @Column(name = "USER_GROUP_TYPE")
    @Enumerated(EnumType.STRING)
    private UserGroupType userGroupType;

    @Column(name = "DEPT_ID")
    private Long deptId;

    @Column(name = "MERCHANT_ID")
    private String merchantId;

    @Column(name = "CREATED_BY")
    private Long createdBy;

    @Column(name = "MODIFIED_BY")
    private Long modifiedBy;

    @Column(name = "APPROVED_BY")
    private Long approvedBy;

    @Column(name = "DATE_APPROVED")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private Date dateApproved;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private UserStatusType status;

    @Column(name = "USER_POSITION")
    private String userPosition;

    @Column(name = "USER_UNIT")
    private String userUnit;

    @Column(name = "USER_ADDRESS")
    private String userAddress;

    @Column(name = "DISTRICT_ID")
    private Long districtId;

//    @OneToOne
//    @JoinColumn(name = "ROLE_ID")
//    private Role role;

    @Column(name = "TELEPHONE_NUMBER")
    private String telephone;

    @Column(name = "PROVINCE_ID")
    private Long provinceId;
}
