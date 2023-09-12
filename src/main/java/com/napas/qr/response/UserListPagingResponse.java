package com.napas.qr.response;

import com.napas.qr.models.UserShortInfo;
import lombok.Data;

import java.util.List;

@Data
public class UserListPagingResponse {
    private long totalRows;
    private int totalPages;
    private List<UserShortInfo> listUsers;
}
