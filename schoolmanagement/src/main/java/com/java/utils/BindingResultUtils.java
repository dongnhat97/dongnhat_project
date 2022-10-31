package com.java.utils;

import com.java.common.constant.CommonConstant;
import com.java.common.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;


@Component
public class BindingResultUtils {

    @Autowired
    MessageService messageService;
    public Map<String,String> errorMapBindingResultUtils(BindingResult bindingResult) {
        Map<String, String> mapErrors = new HashMap<>();
        String errorCode = "";
        String message;
        String result;
        String code = bindingResult.getAllErrors().get(0).getCode();
        String field = bindingResult.getFieldError().getField();
        mapErrors.put("code", code);
        if (code.equals(CommonConstant.ERROR_NAMES.PASSWORD_CONSTRAINT)) {
            errorCode = CommonConstant.ERROR_CODES.E_002;
            message = messageService.buildMessages(errorCode);
            result = MessageFormat.format(message, field, 8, 1, 1, 1, 1);
        } else if (code.equals(CommonConstant.ERROR_NAMES.SIZE)) {
            errorCode = CommonConstant.ERROR_CODES.E_009;
            message = messageService.buildMessages(errorCode);
            result = MessageFormat.format(message, field, 8, 100);
        } else {
            switch (code) {
                case CommonConstant.ERROR_NAMES.NOT_NULL:
                case CommonConstant.ERROR_NAMES.NOT_EMPTY:
                    errorCode = CommonConstant.ERROR_CODES.E_001;
                    break;
                case CommonConstant.ERROR_NAMES.EMAIL_CONSTRAINT:
                    errorCode = CommonConstant.ERROR_CODES.E_007;
                    break;
                case CommonConstant.ERROR_NAMES.DATE_CONSTRAINT:
                    errorCode = CommonConstant.ERROR_CODES.E_008;
                    break;
                case CommonConstant.ERROR_NAMES.NUMERIC_CONSTRAINT:
                    errorCode = CommonConstant.ERROR_CODES.E_003;
                    break;
                case CommonConstant.ERROR_NAMES.IS_HASH_VALUE_CONSTRAINT:
                    errorCode = CommonConstant.ERROR_CODES.E_004;
                    break;
                case CommonConstant.ERROR_NAMES.HALF_SIZE_CONSTRAINT:
                    errorCode = CommonConstant.ERROR_CODES.E_005;
                    break;
                case CommonConstant.ERROR_NAMES.FULL_SIZE_CONSTRAINT:
                    errorCode = CommonConstant.ERROR_CODES.E_006;
                    break;
            }
            message = messageService.buildMessages(errorCode);
            result = MessageFormat.format(message, field);
        }
        mapErrors.put("message", result);
        mapErrors.put("code", errorCode);
        return mapErrors;
    }
}
