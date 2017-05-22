package com.icebrian.buses.error;

@SuppressWarnings("serial")
public class ApplicationInitializationException extends RuntimeException {

	public ApplicationInitializationException() {
	}

	public ApplicationInitializationException(String msg) {
		super(msg);
	}

	public ApplicationInitializationException(Throwable cause) {
		super(cause);
	}

	public ApplicationInitializationException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
