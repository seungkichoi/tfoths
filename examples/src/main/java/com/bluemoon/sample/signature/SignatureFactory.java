package com.bluemoon.sample.signature;

import com.bluemoon.sample.exception.CriticalPaymentException;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


public class SignatureFactory {

	protected static final String HMAC_SHA_256 = "HmacSHA256";

	public static String createSignatureHmacSha(final String signatureString, final String key) throws CriticalPaymentException {
		try {
			final Mac sha256Hmac = Mac.getInstance(HMAC_SHA_256);
			final SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), HMAC_SHA_256);
			sha256Hmac.init(secretKey);
			return Hex.encodeHexString(sha256Hmac.doFinal(signatureString.getBytes("UTF-8")));
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}


}
