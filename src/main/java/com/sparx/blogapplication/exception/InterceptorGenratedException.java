package com.sparx.blogapplication.exception;

public class InterceptorGenratedException  extends Exception{
	public String Message;

	public InterceptorGenratedException(String message) {
		super();
		Message = message;
	}
	public InterceptorGenratedException() {
		super("Access has been blocked by interceptor ");
		
	}

}
