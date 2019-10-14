package com.bluemoon.sample.xml.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "element")
public class Order {
	private String tradeNo;
	private String tradeSuccess;
	private String price;

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getTradeSuccess() {
		return tradeSuccess;
	}

	public void setTradeSuccess(String tradeSuccess) {
		this.tradeSuccess = tradeSuccess;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
}
