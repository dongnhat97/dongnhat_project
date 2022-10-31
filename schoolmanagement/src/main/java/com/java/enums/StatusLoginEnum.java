package com.java.enums;

public class StatusLoginEnum {

    public enum Status {
        SUCCESS("SUCCESS"),
        FAILED("FAILED");

        private String code;

        Status(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }
}
