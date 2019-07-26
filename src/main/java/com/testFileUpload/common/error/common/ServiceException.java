package com.testFileUpload.common.error.common;

/**
 * 统一服务异常
 * @author CAIFUCHENG3
 */
public class ServiceException extends RuntimeException {

    private ErrorCode code;

    public ErrorCode getCode() {
        return code;
    }

    private Object[] args;

    ServiceException(Throwable cause, ErrorCode code, Object... args) {
        super(cause);
        this.code = code;
        this.args = args;
    }

    @Override
    public String getMessage() {
        String s = code.message();
        if (args != null) {
            s = String.format(s, args);
        }
        return s;
    }

}
