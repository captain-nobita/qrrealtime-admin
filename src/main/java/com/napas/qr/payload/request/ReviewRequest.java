package com.napas.qr.payload.request;

import com.napas.qr.define.UserStatusType;

public class ReviewRequest {

    private UserStatusType status;

    public UserStatusType getStatus() {
        return status;
    }

    public void setStatus(UserStatusType status) {
        this.status = status;
    }
}
