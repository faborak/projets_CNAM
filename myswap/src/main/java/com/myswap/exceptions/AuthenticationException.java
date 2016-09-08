package com.myswap.exceptions;

public class AuthenticationException extends Exception {
	
	public AuthenticationException(String description){
		super(description);
	}
}