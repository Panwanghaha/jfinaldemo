package com.shulv.study.jfinaldemo.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.shulv.study.jfinaldemo.constant.ResponseConstant;

import java.io.Serializable;

/**
 * 返回结果统一处理类
 */
@SuppressWarnings("all")
public class ResponsePojo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 请求是否成功状态标识，success：成功，error：失败
     */
    @JSONField(ordinal = 0)
    private String status;

    /**
     * 响应编码
     */
    @JSONField(ordinal = 1)
    private Integer code;

    /**
     * 响应描述
     */
    @JSONField(ordinal = 2)
    private String message;

    /**
     * 响应数据
     */
    @JSONField(ordinal = 3, serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private Object data;

    private ResponsePojo(String status, Integer code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    private ResponsePojo(String status, Integer code, String message, Object data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    /**
     * 默认成功返回描述，不带返回数据
     */
    public static ResponsePojo success() {
        return new ResponsePojo(
                ResponseConstant.Status.SUCESS,
                ResponseConstant.CodeEnum.SUCCESS.getValue(),
                ResponseConstant.CodeEnum.SUCCESS.getMessage()
        );
    }

    /**
     * 默认成功返回描述，带返回数据
     *
     * @param data 返回数据
     */
    public static ResponsePojo success(Object data) {
        return new ResponsePojo(
                ResponseConstant.Status.SUCESS,
                ResponseConstant.CodeEnum.SUCCESS.getValue(),
                ResponseConstant.CodeEnum.SUCCESS.getMessage(),
                data
        );
    }

    /**
     * 错误信息返回描述
     *
     * @param code    错误编码
     * @param message 错误信息
     */
    public static ResponsePojo error(Integer code, String message) {
        return new ResponsePojo(
                ResponseConstant.Status.ERROR,
                code,
                message
        );
    }
}