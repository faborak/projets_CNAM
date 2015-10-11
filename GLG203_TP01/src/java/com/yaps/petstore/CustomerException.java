package com.yaps.petstore;

public abstract class CustomerException extends Exception {

	public CustomerException() {
		
	}
	
	public CustomerException(String Message) {
		super(Message);
	}

}
