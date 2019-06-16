package com.ank.payconiq.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.FieldError;

import lombok.Data;

@Data

public class ValidationErrorVo {

	private Map<String, List<String>> validationErrors;
	
	public void addFieldError(String objectName, String field, String message) {
		
		FieldError fieldError = new FieldError(objectName, field, message);
		

		if (validationErrors == null) {
		    validationErrors = new HashMap<>();
		}
		
		List<String> errorList = validationErrors.getOrDefault(fieldError.getField(), new ArrayList<String>());
		errorList.add(fieldError.getDefaultMessage());
		validationErrors.put(fieldError.getField(), errorList);
		
	}
}
