package com.myswap.exceptions;

public class DealNotFoundException extends Exception {
	
	public DealNotFoundException(String description){
		super(description);
	}
}