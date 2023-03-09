package com.hhm.doorgod.core.entity;

/**
 * @Author huanghm
 * @Date 2023/3/6
 */
public class ResponseWrapper {
    private int code;
    private String message;

    public ResponseWrapper(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResponseWrapper limitResponse() {
        return new ResponseWrapper(428, "too many request");
    }

    public static ResponseWrapper fail() {
        return new ResponseWrapper(-1, "service reject");
    }
    public static ResponseWrapper success() {
        return new ResponseWrapper(0, "success");
    }
    public static ResponseWrapper fail(String message) {
        return new ResponseWrapper(-1, message);
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
