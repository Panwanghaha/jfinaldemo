package com.shulv.study.jfinaldemo.constant;

public class ResponseConstant {
    public static class Status {
        public static final String SUCESS = "sucess";
        public static final String ERROR = "error";
    }

    public enum CodeEnum {
        SUCCESS(1, "请求成功"),
        FAIL(-1, "未知错误"),
        ;
        private Integer value;
        private String message;

        CodeEnum(Integer value, String message) {
            this.value = value;
            this.message = message;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
