package org.crudboy.review.service.exception;

public class BussinessException extends RuntimeException{
    private String code;
    private String msg;

    public BussinessException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
