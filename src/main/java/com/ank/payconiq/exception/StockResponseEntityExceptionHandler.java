package com.ank.payconiq.exception;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ank.payconiq.vo.GenericResponseVo;
import com.ank.payconiq.vo.ValidationErrorVo;

@ControllerAdvice
public class StockResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex,
			WebRequest request) {

		ValidationErrorVo validationErrorVo = new ValidationErrorVo();

		String[] exceptionMessages = ex.getMessage().split(",");

		for (String message : exceptionMessages) {
			validationErrorVo.addFieldError(message.split(":")[0], message.split(":")[0], message.split(":")[1]);
		}
		ResponseEntity<Object> response = new ResponseEntity<Object>(validationErrorVo, HttpStatus.BAD_REQUEST);
		return response;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	@ExceptionHandler(StockException.class)
	protected ResponseEntity<Object> handleStockException(StockException ex, WebRequest request) {

		GenericResponseVo genericResponseVo = new GenericResponseVo(ex.getMessage(), LocalDateTime.now().toString());

		ResponseEntity<Object> response = new ResponseEntity<Object>(genericResponseVo, HttpStatus.BAD_REQUEST);
		return response;
	}

	@Override
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		ValidationErrorVo validationErrorVo = new ValidationErrorVo();

		for (FieldError fieldError : fieldErrors) {
			validationErrorVo.addFieldError(fieldError.getObjectName(), fieldError.getField(),
					fieldError.getDefaultMessage());
		}

		ResponseEntity<Object> response = new ResponseEntity<Object>(validationErrorVo, HttpStatus.BAD_REQUEST);
		return response;
	}
}
