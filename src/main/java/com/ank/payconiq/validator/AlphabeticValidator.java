package com.ank.payconiq.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.ank.payconiq.validator.annotation.Alphabetic;

public class AlphabeticValidator implements ConstraintValidator<Alphabetic, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		if (StringUtils.isAlpha(value))
			return true;

		return false;
	}

}
