package com.server.model;

import lombok.Data;

@Data
public class ReturnResult {
    int    code;
    String message;
    Object data;

    public ReturnResult(int code, String message, Object data) {
        this.setCode(code);
        this.setMessage(message);
        this.setData(data);
    }

    public ReturnResult() {
    }
}
