package com.zhangyw.oktask.exception;

public class TaskTimeException extends Exception {

	public TaskTimeException() {
		super();
	}

	public TaskTimeException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TaskTimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public TaskTimeException(String message) {
		super(message);
	}

	public TaskTimeException(Throwable cause) {
		super(cause);
	}
	
}
