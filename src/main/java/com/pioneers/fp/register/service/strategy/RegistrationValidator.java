package com.pioneers.fp.register.service.strategy;

import com.pioneers.fp.register.dto.RegisterRequest;

public interface RegistrationValidator {
    boolean isRuleValid(RegisterRequest request);

    String errorMessage();
}
