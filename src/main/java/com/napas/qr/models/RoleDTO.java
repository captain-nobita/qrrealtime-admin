package com.napas.qr.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    @Column(name = "ROLE")
    private String Role;

    @Column(name = "GROUP_TYPE")
    private String groupType;

    @Column(name = "ROLE_DESCRIPTION")
    private String roleDescription;
}
