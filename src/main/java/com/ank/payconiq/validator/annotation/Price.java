package com.ank.payconiq.validator.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.ank.payconiq.validator.PriceValidator;

@Documented
@Retention(RUNTIME)
@Target({ FIELD, METHOD, PARAMETER })
@Constraint(validatedBy = { PriceValidator.class })
public @interface Price {

	String message() default "Entered Price Value is incorrect.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
