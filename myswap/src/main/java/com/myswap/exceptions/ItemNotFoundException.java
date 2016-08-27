package com.myswap.exceptions;

public class ItemNotFoundException extends Exception {
	
	public ItemNotFoundException(String description){
		super(description);
	}
}