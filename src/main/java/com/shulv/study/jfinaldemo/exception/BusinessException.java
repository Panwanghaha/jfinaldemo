package com.shulv.study.jfinaldemo.exception;

import com.shulv.study.jfinaldemo.constant.ResponseConstant;

/**
 * 通用业务异常，继承RuntimeException
 */
public class BusinessException extends RuntimeException {
    private final Integer code;
    private final String message;

    /**
     * 传入message作为错误信息
     *
     * @param code    错误编码
     * @param message 错误信息
     */
    private BusinessException(Integer code, String message) {
        super(code + "：" + message);
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public static BusinessException DEFAULT_ERROR() {
        return new BusinessException(
                ResponseConstant.CodeEnum.FAIL.getValue(),
                ResponseConstant.CodeEnum.FAIL.getMessage()
        );
    }

    public static BusinessException CUSTOM_ERROR(String message) {
        return new BusinessException(
                ResponseConstant.CodeEnum.FAIL.getValue(),
                message
        );
    }
}