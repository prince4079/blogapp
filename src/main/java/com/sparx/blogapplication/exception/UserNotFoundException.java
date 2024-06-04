package com.sparx.blogapplication.exception;

public class UserNotFoundException extends RuntimeException{
	String userId;
	
	public UserNotFoundException() {
		super("user not found");
		// TODO Auto-generated constructor stub
	}
	 public UserNotFoundException(String userId) {
		super("user not found with this userId"+userId);
		// TODO Auto-generated constructor stub
	}

	
	public UserNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	
	public UserNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	

}
