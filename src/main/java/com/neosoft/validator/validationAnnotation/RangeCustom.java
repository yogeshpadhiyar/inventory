package com.neosoft.validator.validationAnnotation;

import com.neosoft.validator.validationClass.RangeConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = RangeConstraintValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RangeCustom {

    public Class<?>[] groups() default {} ;
    public Class<? extends Payload>[] payload() default {};

    public String message() default "Enter valid Price (Between 1 to 3000)";

}
