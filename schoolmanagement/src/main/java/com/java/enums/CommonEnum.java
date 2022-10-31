package com.java.enums;

public class CommonEnum {
    public enum StatusEnum{
        ACTIVE("ACTIVE"),
        DELETED("DELETED");

        private String code;

        StatusEnum(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }
}
