package com.ank.payconiq.validator;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.validator.routines.BigDecimalValidator;

import com.ank.payconiq.validator.annotation.Price;

public class PriceValidator implements ConstraintValidator<Price, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		if (BigDecimalValidator.getInstance().isValid(value)) {

			BigDecimal bigDecimal = new BigDecimal(value);

			if (bigDecimal.signum() >= 0) {
				return true;
			}

		}

		return false;
	}

}
