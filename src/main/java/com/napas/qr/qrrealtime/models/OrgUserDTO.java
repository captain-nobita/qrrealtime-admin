package com.napas.qr.qrrealtime.models;

import com.napas.qr.qrrealtime.define.ERole;
import com.napas.qr.qrrealtime.define.ETargetType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrgUserDTO {

    private Long id;

    private String fullname;


    private String username;

    private String status;

    private LocalDateTime dateCreated;

    private LocalDateTime dateModified;

    private long createdByUser;

    private Long modifiedByUser;

    private String password;

    private ERole role;

    private ETargetType targetType;

    private long targetId;

}
