package com.pioneers.service.model.dtos.requests;

import com.pioneers.service.error.exceptions.ValidationException;
import com.pioneers.service.utils.validations.ValidationRuleTypes;
import com.pioneers.service.utils.validations.ValidationRulesHelper;

import lombok.Builder;

import java.util.*;

import static com.pioneers.service.utils.validations.ValidationRulesHelper.isAgeValid;

@Builder
public record StudentSignup(
//        @Size(min = 2, max = 15, message = "First Name must be between 2 and 15 characters")
        String firstName,
//        @Size(min = 2, max = 15, message = "Last Name must be between 2 and 15 characters")
        String lastName,
//        @Email(message = "Email must be valid")
        String email,
//        @Min(value = 18, message = "Age must be at least 18 years old")
//        @Max(value = 25, message = "Age must be at most 25 years old")
        int age,
//        @Pattern(regexp = "^\\+20(10|11|12|15)\\d{8}$", message = "The phone number format is incorrect")
        String phoneNumber,
//        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z\\d]).{8,}$", message = "Password doesn't meet our criteria")
        String password
) {
    /**
     * Validate the elements of the StudentSignup object.
     */
    public void validate() {
        final Map<String, String> errorMessages = new LinkedHashMap<>();

        if (!ValidationRulesHelper.validate(ValidationRuleTypes.NAME, firstName)) {
            errorMessages.put("firstName", "First Name must be between 2 and 15 characters");
        }

        if (!ValidationRulesHelper.validate(ValidationRuleTypes.NAME, lastName)) {
            errorMessages.put("lastName", "Last Name must be between 2 and 15 characters");
        }

        if (!ValidationRulesHelper.validate(ValidationRuleTypes.EMAIL, email)) {
            errorMessages.put("email", "Email must be valid");
        }

        if (!isAgeValid(age)) {
            errorMessages.put("age", "Age must be between 18 & 25 years old");
        }

        if (!ValidationRulesHelper.validate(ValidationRuleTypes.PHONE, phoneNumber)) {
            errorMessages.put("phoneNumber", "The phone number format is incorrect");
        }

        if (!ValidationRulesHelper.validate(ValidationRuleTypes.PASSWORD, password)) {
            errorMessages.putIfAbsent("password", "Password doesn't meet our criteria");
        }

        if (!errorMessages.isEmpty()) {
            throw new ValidationException(errorMessages);
        }
    }
}
