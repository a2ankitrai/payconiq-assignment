package com.ank.payconiq.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.validator.routines.BigDecimalValidator;

import com.ank.payconiq.validator.annotation.Price;
 

public class PriceValidator implements ConstraintValidator<Price, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
 	
	if(BigDecimalValidator.getInstance().isValid(value))
		return true;

	return false;
    }

}

