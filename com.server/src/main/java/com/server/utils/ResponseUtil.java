package com.server.utils;

import com.server.enums.ReturnStatus;
import com.server.model.ReturnResult;

public class ResponseUtil {
    public static ReturnResult success(Object data) {
        return new ReturnResult(ReturnStatus.SUCCESS.getCode(), ReturnStatus.SUCCESS.getMessage(), data);
    }

    public static ReturnResult failed(Object data) {
        return new ReturnResult(ReturnStatus.FAILED.getCode(), ReturnStatus.FAILED.getMessage(), data);
    }

    public static ReturnResult withMessage(int code, String message, Object data) {
        return new ReturnResult(code, message, data);
    }
}
