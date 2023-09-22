package com.napas.qr.qrrealtime.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
    private LocalDateTime dateCreated;

    private LocalDateTime dateModified;

    private long createdByUser;

    private Long modifiedByUser;

    @JsonIgnore
    private String password;

    private ERole role;

    private ETargetType targetType;

    private long targetId;

}
