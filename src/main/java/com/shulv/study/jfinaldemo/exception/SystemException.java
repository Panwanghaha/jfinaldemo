package com.shulv.study.jfinaldemo.exception;

public class SystemException extends RuntimeException {

    public SystemException(String message) {
        super(message);
    }

    public static SystemException create(String message) {
        return new SystemException(message);
    }
}