package com.myswap.exceptions;

public class CommentNotFoundException extends Exception {
	
	public CommentNotFoundException(String description){
		super(description);
	}
}