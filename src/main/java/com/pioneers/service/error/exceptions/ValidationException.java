package com.pioneers.service.error.exceptions;

import com.pioneers.service.utils.time.TimeHelper;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.Map;

@Getter
public class ValidationException extends RuntimeException {

    public static final String VALIDATION_EXCEPTION_MESSAGE = "validationException";
    public static final int VALIDATION_EXCEPTION_CODE = 1004;

    private final Map<String, String> errorMessages;
    private final Timestamp timestamp = TimeHelper.currentTimestamp();

    public ValidationException(Map<String, String> errorMessages) {
        this.errorMessages = errorMessages;
    }
}
