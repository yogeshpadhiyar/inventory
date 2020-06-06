package com.neosoft.validator.validationClass;

import com.neosoft.validator.validationAnnotation.RangeCustom;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RangeConstraintValidator implements ConstraintValidator<RangeCustom, Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value >= 1 && value <= 3000;
    }
}
