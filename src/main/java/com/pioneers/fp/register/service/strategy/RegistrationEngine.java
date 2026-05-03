package com.pioneers.fp.register.service.strategy;

import com.pioneers.fp.register.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RegistrationEngine {
    private final List<RegistrationValidator> validatorRules;

    public void register(final RegisterRequest request, final OnSuccess onSuccess, final OnFailure onFailure) {
        validatorRules.stream()
                .filter(strategy -> !strategy.isRuleValid(request))
                .findFirst()
                .ifPresentOrElse(failedStrategy ->
                                onFailure.fail(failedStrategy.errorMessage()),
                        onSuccess::success);
    }
}
