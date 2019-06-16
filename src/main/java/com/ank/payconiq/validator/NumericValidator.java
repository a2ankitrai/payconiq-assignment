package com.ank.payconiq.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.ank.payconiq.validator.annotation.Numeric;

public class NumericValidator implements ConstraintValidator<Numeric, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

	if (StringUtils.isNumeric(value))
	    return true;

	return false;
    }

}
