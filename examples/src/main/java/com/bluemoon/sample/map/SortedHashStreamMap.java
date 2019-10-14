package com.bluemoon.sample.map;

import com.bluemoon.sample.exception.CriticalPaymentException;
import com.bluemoon.sample.signature.SignatureFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class SortedHashStreamMap {

	public static void main (String[] args) throws CriticalPaymentException, UnsupportedEncodingException {
		String HMAC_KEY = "";
		String reference_id = "SCHOITEST-10027";
		String channel_id = "8";

		Map<String, String> pairs = new LinkedHashMap<>();
		pairs.put("applicationCode", "");
		pairs.put("referenceId", reference_id);
		pairs.put("version", "v1");
		pairs.put("channelId", channel_id);
		pairs.put("description", "Product A");
		pairs.put("returnUrl", "http://yoursite.com/result?referenceId=" + reference_id);
		if (channel_id.equals("1")) {
			pairs.put("amount", "1000"); // empty for carrier billing or prepaid
			pairs.put("currencyCode", "SGD"); // empty for carrier billing or prepaid
		} else {
			pairs.put("maxAcceptedCurrencyCode", "USD");
			pairs.put("maxAcceptedAmount", "100");
		}
		pairs.put("customerId", "12321144221");
		pairs.put("hashType", "hmac-sha256");
		pairs.put("callbackUrl", "http://yoursite.com/callback");

		SortedMap<String, String> sortedPairs = new TreeMap<>(pairs);
		LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(encodeValues(pairs));

		String parameterHashed = buildRequestString(linkedHashMap);
		String signatureString = sortedPairs.values().stream().collect(Collectors.joining(""));
		//String signature = SignatureFactory.createSignatureHmacSha(signatureString, HMAC_KEY);

		//System.out.println("signature string: " + signatureString);
		//System.out.println(parameterHashed + "&signature="+signature);
		//System.out.println("signature created by given signature string: " + signature);

	}


	public static Map encodeValues(Map<String, String> fields) {
		Map<String, String> fieldPairs =
				fields.entrySet().stream()
						.collect(Collectors.toMap(
								e -> e.getKey(),
								e -> (e.getValue() == null) ? "" : encode(e.getValue()),
								(k, v) -> k,
								LinkedHashMap::new
						));
		return fieldPairs;
	}

	private static String buildRequestString(Map<String, String> stringFields) {
		final LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(stringFields);
		final List<String> parameterList = linkedHashMap.entrySet()
				.stream().map(entry -> entry.getKey() + "=" + entry.getValue())
				.collect(Collectors.toList());

		return String.join("&", parameterList);
	}

	public static String encode(String value) {
		try {
			return escapeString(URLEncoder.encode(value, StandardCharsets.UTF_8.toString()));
		} catch (Exception e) {
			throw new IllegalArgumentException("Unable to URL encode character", e);
		}
	}

	public static String escapeString(final String stringToEscape) {
		if (stringToEscape == null) {
			return null;
		}

		return stringToEscape.replaceAll("\\+", "%20");
	}

	private static byte[] encode(byte[] hexkey, byte[] hexvalue) {
		try {
			Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
			SecretKeySpec secret_key = new SecretKeySpec(hexkey, "HmacSHA256");
			sha256_HMAC.init(secret_key);

			return sha256_HMAC.doFinal(hexvalue);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String stringToHex(String base) throws UnsupportedEncodingException
	{
		return String.format("%040x", new BigInteger(1, base.getBytes(StandardCharsets.US_ASCII)));
	}
}
