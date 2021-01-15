package com.loteriaorange.loteriaorange.exceptions;



public class ApostadorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ApostadorException(String msg) {
		super(msg);
	}
	
	public ApostadorException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
