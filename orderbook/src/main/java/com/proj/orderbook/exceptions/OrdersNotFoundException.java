package com.proj.orderbook.exceptions;

import java.time.LocalDateTime;

public class OrdersNotFoundException extends RuntimeException {

	public OrdersNotFoundException(String errorMessage, LocalDateTime errorOccuredTime) {
		super();
		this.errorMessage = errorMessage;
		this.errorOccuredTime = errorOccuredTime;
	}

	private String errorMessage;
	private LocalDateTime errorOccuredTime;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public LocalDateTime getErrorOccuredTime() {
		return errorOccuredTime;
	}

	public void setErrorOccuredTime(LocalDateTime errorOccuredTime) {
		this.errorOccuredTime = errorOccuredTime;
	}

}
