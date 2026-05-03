package com.pioneers.fp.register.service.strategy;

import com.pioneers.fp.register.dto.RegisterRequest;
import org.springframework.stereotype.Component;

@Component
public class NameValidator implements RegistrationValidator {

    @Override
    public boolean isRuleValid(RegisterRequest request) {
        return request.name() != null
                && !request.name().isBlank()
                && request.name().length() >= 3
                && request.name().length() <= 30;
    }

    @Override
    public String errorMessage() {
        return "Name must be between 3 and 30 characters";
    }
}
