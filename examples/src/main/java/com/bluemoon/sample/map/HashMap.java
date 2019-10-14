package com.bluemoon.sample.map;

import java.util.Map;

public class HashMap {

	public static void main(String[] args) {
		final Map<Integer, String> PaymentStatusMap;
		PaymentStatusMap = new java.util.HashMap<>();
		PaymentStatusMap.put(100, "PENDING");

		System.out.println(PaymentStatusMap.get(100));
		System.out.println(PaymentStatusMap.get(50));
	}

}
