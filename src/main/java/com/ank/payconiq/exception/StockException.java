package com.ank.payconiq.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class StockException extends RuntimeException {

	private static final long serialVersionUID = 5861310537366287163L;
	private HttpStatus httpStatus;

	public StockException() {
		super();
	}

	public StockException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public StockException(final String message, final HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public StockException(final String message) {
		super(message);
	}

	public StockException(final Throwable cause) {
		super(cause);
	}
}
