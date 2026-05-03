package com.pioneers.service.utils.validations;

import lombok.extern.slf4j.Slf4j;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Predicate;

import static com.pioneers.service.utils.StringUtils.isNullOrBlank;

@Slf4j
public final class ValidationRulesHelper {

    private static final Map<ValidationRuleTypes, Predicate<String>> VALIDATION_RULES =
            new EnumMap<>(ValidationRuleTypes.class);

    static {
        VALIDATION_RULES.put(ValidationRuleTypes.NAME, ValidationRulesHelper::isNameValid);
        VALIDATION_RULES.put(ValidationRuleTypes.EMAIL, ValidationRulesHelper::isEmailValid);
        VALIDATION_RULES.put(ValidationRuleTypes.PHONE, ValidationRulesHelper::isPhoneValid);
        VALIDATION_RULES.put(ValidationRuleTypes.PASSWORD, ValidationRulesHelper::isPasswordValid);
        log.trace("added the validation rules to the map!!");
    }

    private ValidationRulesHelper() {
        throw new AssertionError("Utility class");
    }

    public static boolean validate(final ValidationRuleTypes type, final String input) {
        if (isNullOrBlank(input)) {
            return false;
        }

        return VALIDATION_RULES.get(type)
                .test(input);
    }

    public static boolean isAgeValid(final int age) {
        return age >= 18 && age <= 25;
    }

    private static boolean isNameValid(final String name) {
        return name.length() >= 2 && name.length() <= 15;
    }

    private static boolean isEmailValid(final String email) {
        return email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    private static boolean isPhoneValid(final String phone) {
        return phone.matches("^\\+20(10|11|12|15)\\d{8}$");
    }

    private static boolean isPasswordValid(final String password) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z\\d]).{8,}$");
    }
}
