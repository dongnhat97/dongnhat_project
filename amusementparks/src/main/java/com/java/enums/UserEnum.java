package bap.training.enums;

public class UserEnum {

    public enum Status {
        ACTIVED("ACTIVED"),
        PENDING("PENDING"),
        DELETED("DELETED"),
        BLOCKED("BLOCKED");

        private String code;

        Status(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

}

