package com.pioneers.service.model.dtos.requests;

import com.pioneers.service.error.exceptions.ValidationException;
import com.pioneers.service.utils.validations.ValidationRuleTypes;
import com.pioneers.service.utils.validations.ValidationRulesHelper;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.LinkedHashMap;
import java.util.Map;

public record StudentUpdate(
//        @Size(min = 2, max = 15, message = "First Name must be between 2 and 15 characters")
        String firstName,
//        @Size(min = 2, max = 15, message = "Second Name must be between 2 and 15 characters")
        String secondName,
//        @Email(message = "Email must be valid")
        String email,
//        @Pattern(regexp = "^\\+20(10|11|12|15)\\d{8}$", message = "The phone number format is incorrect")
        String phone
) {
    /**
     * Validate the elements of the StudentUpdate object.
     */
    public void validate() {
        final Map<String, String> errorMessages = new LinkedHashMap<>();

        if (!ValidationRulesHelper.validate(ValidationRuleTypes.NAME, firstName)) {
            errorMessages.put("firstName", "First Name must be between 2 and 15 characters");
        }

        if (!ValidationRulesHelper.validate(ValidationRuleTypes.NAME, secondName)) {
            errorMessages.put("lastName", "Second Name must be between 2 and 15 characters");
        }

        if (!ValidationRulesHelper.validate(ValidationRuleTypes.EMAIL, email)) {
            errorMessages.put("email", "Email must be valid");
        }

        if (!errorMessages.isEmpty()) {
            throw new ValidationException(errorMessages);
        }
    }

}
