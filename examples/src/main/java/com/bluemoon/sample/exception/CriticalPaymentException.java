package com.bluemoon.sample.exception;

public class CriticalPaymentException extends Throwable {

	public CriticalPaymentException(Exception e) {
		super(e);
	}
}
