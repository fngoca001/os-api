package com.example.OS.services.exception;

public class DataIntegratyViolaionException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public DataIntegratyViolaionException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataIntegratyViolaionException(String message) {
		super(message);
	}


	
}
