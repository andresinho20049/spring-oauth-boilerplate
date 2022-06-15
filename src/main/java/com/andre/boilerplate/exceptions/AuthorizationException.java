package com.andre.boilerplate.exceptions;

public class AuthorizationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AuthorizationException(String message, Exception e) {
		super(message, e);
	}

	public AuthorizationException(String message) {
		super(message);
	}

	public AuthorizationException(Exception e) {
		super(e);
	}

}
