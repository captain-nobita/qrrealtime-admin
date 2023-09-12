package com.napas.qr.utils;

public enum ResponseCodeAndMsg {

    SUCCESS(1, "SUCCESS"), BAD_REQUEST(400, "bad request"), TOKEN_EXPIRED(-1, "token het han");
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ResponseCodeAndMsg(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
