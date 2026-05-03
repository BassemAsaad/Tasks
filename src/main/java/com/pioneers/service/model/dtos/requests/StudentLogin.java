package com.pioneers.service.model.dtos.requests;

import com.pioneers.service.error.exceptions.ValidationException;
import com.pioneers.service.utils.validations.ValidationRuleTypes;
import com.pioneers.service.utils.validations.ValidationRulesHelper;
import lombok.Builder;

import java.util.LinkedHashMap;
import java.util.Map;
@Builder
public record StudentLogin(
//        @Email(message = "Email must be valid")
        String email,
//        @NotBlank(message = "Password cannot be blank or null")
        String password
) {
    /**
     * Validate the elements of the StudentLogin object.
     */
    public void validate() {
        final Map<String, String> errorMessages = new LinkedHashMap<>();

        if (!ValidationRulesHelper.validate(ValidationRuleTypes.EMAIL, email)) {
            errorMessages.put("email", "Email must be valid");
        }

        if (!errorMessages.isEmpty()) {
            throw new ValidationException(errorMessages);
        }
    }
}
