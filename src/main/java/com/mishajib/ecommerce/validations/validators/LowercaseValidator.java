package com.mishajib.ecommerce.validations.validators;

import com.mishajib.ecommerce.validations.Lowercase;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LowercaseValidator implements ConstraintValidator<Lowercase, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null || value.isEmpty()) return true;

        return value.equals(value.toLowerCase());
    }
}
