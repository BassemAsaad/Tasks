package com.pioneers.fp.register.service.strategy;

import com.pioneers.fp.register.dto.RegisterRequest;
import org.springframework.stereotype.Component;

@Component
public class AgeValidator implements RegistrationValidator {

    @Override
    public boolean isRuleValid(RegisterRequest request) {
        return request.age() >= 18;
    }

    @Override
    public String errorMessage() {
        return "Age must be greater than 18";
    }
}
