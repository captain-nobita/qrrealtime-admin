package com.napas.qr.response;

import com.napas.qr.utils.ResponseCodeAndMsg;
import org.apache.poi.ss.formula.functions.T;

public class BaseResponse {

    private int code = 1;
    private String message = "SUCCESS";
    private T data;

    public BaseResponse(T data) {
        super();
        this.data = data;
    }

    public BaseResponse() {
        super();
    }

    public void setCodeAndMsg(ResponseCodeAndMsg msg){
        this.code=  msg.getCode();
        this.message = msg.getMessage();
    }
}
