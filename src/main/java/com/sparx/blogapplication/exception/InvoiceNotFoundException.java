package com.sparx.blogapplication.exception;

public class InvoiceNotFoundException extends RuntimeException {

	public InvoiceNotFoundException(String message) {
		super(message);
	}

	public InvoiceNotFoundException() {
		super("Invoice not found");
	}

}
