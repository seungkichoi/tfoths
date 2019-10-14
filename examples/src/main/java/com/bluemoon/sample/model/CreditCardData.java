package com.bluemoon.sample.model;

public class CreditCardData {
	String ccNum;
	CreditCardDetail creditCardDetail;

	public CreditCardData() {
	}

	public CreditCardData(String ccNum) {
		this.ccNum = ccNum;
	}

	public String getCcNum() {
		return ccNum;
	}

	public CreditCardDetail getCreditCardDetail() {
		return creditCardDetail;
	}

	public void setCreditCardDetail(CreditCardDetail creditCardDetail) {
		this.creditCardDetail = creditCardDetail;
	}
}
