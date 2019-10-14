package com.bluemoon.sample.model;

public enum Mop {
	VI(1, "Visa"),
	MC(1, "Master Card");

	private final long code;

	private final String mopName;

	Mop(
			final long code,
			final String mopName) {
		this.code = code;
		this.mopName = mopName;
	}

	public static Mop valueOf(final long code) {
		for (final Mop mop : Mop.values()) {
			if (mop.code == code) {
				return mop;
			}
		}
		return null;
	}

	public long getCode() {
		return code;
	}

	public String getMopName() {
		return mopName;
	}
}
