package test.demo.enums;

/**
 * @author a58
 */

public enum ReturnStatus {
    /**
     * 请求返回状态
     */
    SUCCESS(1, "成功"),
    FAILED(0, "失败"),
    CONDITION_ERROR(10000, "查询条件无效");

    private int code;
    private String message;

    ReturnStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

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
}