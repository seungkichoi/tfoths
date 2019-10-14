package com.bluemoon.sample.model;

public class TransDetail {
	String transid;
	CreditCardData creditCardData;

	public TransDetail() {
	}

	public TransDetail(String transid, CreditCardData creditCardData) {
		this.transid = transid;
		this.creditCardData = creditCardData;
	}

	public String getTransid() {
		return transid;
	}

	public void setTransid(String transid) {
		this.transid = transid;
	}

	public CreditCardData getCreditCardData() {
		return creditCardData;
	}

	@Override
	public String toString() {
		return new StringBuilder().append(this.getClass().getSimpleName())
				.append("{")
				.append("transid=")
				.append(transid)
				.append(", ccNum=")
				.append(creditCardData.ccNum)
				.append("}")
				.toString();
	}
}
