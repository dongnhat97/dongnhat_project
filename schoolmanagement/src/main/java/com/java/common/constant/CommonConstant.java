package com.java.common.constant;

public class CommonConstant {
    /**
     * Common error code
     */
    public static class ERROR_CODES {
        /**
         * Not null code
         */
        public static final String E_001 = "E_001";
        /**
         * Password code
         */
        public static final String E_002 = "E_002";
        /**
         * Numeric code
         */
        public static final String E_003 = "E_003";
        /**
         * Hash value code
         */
        public static final String E_004 = "E_004";
        /**
         * Half size code
         */
        public static final String E_005 = "E_005";
        /**
         * Full size code
         */
        public static final String E_006 = "E_006";
        /**
         * Email code
         */
        public static final String E_007 = "E_007";
        /**
         * Date code
         */
        public static final String E_008 = "E_008";
        /**
         * Size code
         */
        public static final String E_009 = "E_009";
        /**
         * Not blank code
         */
        public static final String E_010 = "E_010";
        /**
         * In used email code
         */
        public static final String E_011 = "E_011";
        /**
         * In used name code
         */
        public static final String E_012 = "E_012";
        /**
         * Not found code
         */
        public static final String E_013 = "E_013";
    }
    /**
     * Common error name
     */
    public static class ERROR_NAMES {
        /**
         * Not null
         */
        public static final String NOT_NULL = "NotNull";
        /**
         * Not empty
         */
        public static final String NOT_EMPTY = "NotEmpty";
        /**
         * Date validation
         */
        public static final String DATE_CONSTRAINT = "DateConstraint";
        /**
         * Password validation
         */
        public static final String PASSWORD_CONSTRAINT = "PasswordConstraint";
        /**
         * Email validation
         */
        public static final String EMAIL_CONSTRAINT = "EmailConstraint";
        /**
         * Numeric validation
         */
        public static final String NUMERIC_CONSTRAINT = "NumericConstraint";
        /**
         * Half size validation
         */
        public static final String HALF_SIZE_CONSTRAINT = "HalfsizeConstraint";
        /**
         * Full size validation
         */
        public static final String FULL_SIZE_CONSTRAINT = "FullsizeConstraint";
        /**
         * Hash value validation
         */
        public static final String IS_HASH_VALUE_CONSTRAINT = "IsHashValueConstraint";
        /**
         * Size validation
         */
        public static final String SIZE = "Size";
        /**
         * NG status
         */
        public static final String NG = "NG";
        /**
         * OK status
         */
        public static final String OK = "OK";
    }
    public static class REGEX_PATTERN {

        public static final String REX_DATE_YY_MM_DD = "^(\\d{2}((0[1-9]|1[012])(0[1-9]|1\\d|2[0-8])|(0[13456789]|1[012])(29|30)|(0[13578]|1[02])31)|([02468][048]|[13579][26])0229)$";
        /**
         * Local date yyyyREX_DATE_TIME_YYYY_MM_DD_HH_MM_SPLASH/MM/dd
         */
        public static final String REX_DATE_YYYY_MM_DD_SPLASH = "\\d{4}\\/(0?[1-9]|1[012])\\/(0?[1-9]|[12][0-9]|3[01])$";
        /**
         * Local date yyyy/MM/ddT
         */
        public static final String REX_DATE_YYYY_MM_DD_SPLASH_T = "^\\d{4}\\/(0?[1-9]|1[012])\\/(0?[1-9]|[12][0-9]|3[01])T$";
        /**
         * Local date MM/dd
         */
        public static final String REX_DATE_MM_DD_SPLASH = "^(0?[1-9]|1[012])\\/(0?[1-9]|[12][0-9]|3[01])$";
        /**
         * Local date yyyy-MM-dd
         */
        public static final String REX_DATE_YYYY_MM_DD_HYPHEN = "\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";
        /**
         * Local date yyyy-MM-ddT
         */
        public static final String REX_DATE_YYYY_MM_DD_HYPHEN_T = "\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])T$";
        /**
         * Local date time yyyy-MM-dd HH:mm:ss
         */
        public static final String REX_DATE_TIME_YYYY_MM_DD_HH_MM_SS_HYPHEN = "^([0-9]{4})-([0-1][0-9])-([0-3][0-9])\\s([0-1][0-9]|[2][0-3]):([0-5][0-9]):([0-5][0-9])$";
        /**
         * Local date time yyyy-MM-ddTHH:mm:ss
         */
        public static final String REX_DATE_TIME_YYYY_MM_DD_HH_MM_SS_HYPHEN_T = "^([0-9]{4})-([0-1][0-9])-([0-3][0-9])T([0-1][0-9]|[2][0-3]):([0-5][0-9]):([0-5][0-9])$";
        /**
         * Local date time yyyy-MM-dd HH:mm
         */
        public static final String REX_DATE_TIME_YYYY_MM_DD_HH_MM_HYPHEN = "^([0-9]{4})-([0-1][0-9])-([0-3][0-9])\\s([0-1][0-9]|[2][0-3]):([0-5][0-9])$";
        /**
         * Local date time yyyy-MM-ddTHH:mm
         */
        public static final String REX_DATE_TIME_YYYY_MM_DD_HH_MM_HYPHEN_T = "^([0-9]{4})-([0-1][0-9])-([0-3][0-9])T([0-1][0-9]|[2][0-3]):([0-5][0-9])$";
        /**
         * Local date time yyyy/MM/dd HH:mm:ss
         */
        public static final String REX_DATE_TIME_YYYY_MM_DD_HH_MM_SS_SPLASH = "^([0-9]{4})\\/([0-1][0-9])\\/([0-3][0-9])\\s([0-1][0-9]|[2][0-3]):([0-5][0-9]):([0-5][0-9])$";
        /**
         * Local date time yyyy/MM/ddTHH:mm:ss
         */
        public static final String REX_DATE_TIME_YYYY_MM_DD_HH_MM_SS_SPLASH_T = "^([0-9]{4})\\/([0-1][0-9])\\/([0-3][0-9])T([0-1][0-9]|[2][0-3]):([0-5][0-9]):([0-5][0-9])$";
        /**
         * Local date time yyyy/MM/dd HH:mm
         */
        public static final String REX_DATE_TIME_YYYY_MM_DD_HH_MM_SPLASH = "^([0-9]{4})\\/([0-1][0-9])\\/([0-3][0-9])\\s([0-1][0-9]|[2][0-3]):([0-5][0-9])$";
        /**
         * Local date time yyyy/MM/ddTHH:mm
         */
        public static final String REX_DATE_TIME_YYYY_MM_DD_HH_MM_SPLASH_T = "^([0-9]{4})\\/([0-1][0-9])\\/([0-3][0-9])T([0-1][0-9]|[2][0-3]):([0-5][0-9])$";
        /**
         * Local time HH:mm:ss
         */
        public static final String REX_TIME_HH_MM_SS = "^(00|0?[1-9]|1[0-9]|2[0-3])\\:([0-5][0-9])\\:([0-5][0-9])$";
        /**
         * Local time HH:mm
         */
        public static final String REX_TIME_HH_MM = "^([0-5][0-9])\\:([0-5][0-9])$";
        /**
         * Local date time yyyy/MM/dd HH:mm
         */
        public static final String YYYY_MM_DD_HH_MM_SPLASH = "yyyy/MM/dd HH:mm";
    }
    /**
     * Entity status
     */
    public static class STATUS {
        public static final String ACTIVATED = "ACTIVATED";
        public static final String DELETED = "DELETED";

        /**
         * Status get list notifications in time input
         */
        public static final String POSTING = "posting";

        /**
         * Status get list notifications in time input and get future time
         */
        public static final String BEFORE_POSTING = "before_posting";

        /**
         * Status get list notifications in time input and get part time
         */
        public static final String AFTER_POSTING = "after_posting";
    }
}
