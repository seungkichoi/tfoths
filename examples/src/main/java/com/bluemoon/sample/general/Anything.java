package com.bluemoon.sample.general;

import com.bluemoon.sample.model.CreditCardData;
import com.bluemoon.sample.model.CreditCardDetail;
import com.bluemoon.sample.model.TransDetail;
import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Anything {

	public static void main (String[] args) throws UnsupportedEncodingException {
		List<String> transIds = new ArrayList<>();
		transIds.add("US1");
		transIds.add("US2");
		transIds.add("US3");
		transIds.add("US4");
		transIds.add("US5");
		System.out.println(transIds);

		List<List<String>> subLists = Lists.partition(new ArrayList<>(transIds), 10);
		List<TransDetail> transIdsResult = new ArrayList<>();

		subLists.forEach(list -> {
			//String transIdsStr = StringUtils.join(list, ',');
			String transIdsStr = "";
			TransDetail transDetail = new TransDetail();
			transDetail.setTransid(transIdsStr);
			transIdsResult.add(transDetail);
			System.out.println(transIdsStr);
		});

		System.out.println(transIdsResult.size());
		transIdsResult.forEach(list -> {
			System.out.println(list.getTransid());
		});

		// print transaction detail
		CreditCardData creditCardData = new CreditCardData();
		TransDetail transDetail = new TransDetail("1234", creditCardData);
		System.out.println(transDetail.toString());


		// compare boolean
		final boolean a = true;
		final boolean b = false;
		if (a && b) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}

		final BigDecimal amt1 = new BigDecimal("110.0");
		final BigDecimal amt2 = new BigDecimal(11.00);
		final BigDecimal amt3 = null;
		System.out.println(amt1.compareTo(amt2));

		final String paginationToken = "2";
		final int pageToken = Integer.valueOf(paginationToken.trim());
		System.out.println(pageToken);

		//convert to milliseconds to date
		long currentDateTime = System.currentTimeMillis();
		System.out.println("currentDateTime:" + currentDateTime);

		final long tokenExpireTime = Calendar.getInstance().getTimeInMillis() + (new Long("86399") * 1000);
		final long refreshTime = Calendar.getInstance().getTimeInMillis() + (60 * 1000);
		System.out.println("refreshTime: " + refreshTime);
		System.out.println("tokenExpireTime: " + tokenExpireTime);

		//creating Date from millisecond
		Date currentDate = new Date(currentDateTime);
		Date specificDate = new Date(new Long("1570063282603"));
		Date refreshDate = new Date(refreshTime);
		Date expireTimeInSeconds = new Date(currentDateTime + (7 * 24 * 60 * 60 * 1000));

		System.out.println("current date: " + currentDate);
		System.out.println("specific date: " + specificDate);
		System.out.println("refresh date: " + refreshDate);
		System.out.println("expire date: " + currentDate);
		System.out.println("expireTimeInSeconds: " + expireTimeInSeconds);

		System.out.println(amt1.toPlainString());
		System.out.println(amt1.toString());

		System.out.println("Optional.of:" + Optional.of(""));

		final DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyyMMdd");
		final DateTime now = DateTime.now();
		final StringBuilder regex = new StringBuilder();
		for (int i = 1; i <= 5; i++) {
			regex.append(formatter.print(now.minusDays(i)));
			if (i < 5) {
				regex.append("|");
			}
		}
		System.out.println("regex: " + regex);
		final DateTimeFormatter formattermis = DateTimeFormat.forPattern("yyyyMMddHHmmss");
		System.out.println(formattermis.print(DateTime.now()));

		OffsetDateTime utc = OffsetDateTime.now(ZoneOffset.UTC);
		System.out.println("utcTime:" + utc);
		final LocalDateTime convertUtctoLocal = utc.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();;
		System.out.println("convertUtctoLocal:" + convertUtctoLocal);

		final LocalDateTime localDateTime =
				new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		System.out.println("localDateTime:"+localDateTime);
		System.out.println("Timestamp: " + new Timestamp(System.currentTimeMillis()));



		// java stream()
		List<String> list = Arrays.asList("a", "b", "c");
		Optional<String> result = list.stream()
				.filter(x -> x.equals("d"))
				.findFirst();
		if (result.isPresent()) {
			System.out.println("result:" + result);
		}

		String basicAuthUser = "";
		String basicAuthPasswd = "";
		String token = basicAuthUser + ":" + basicAuthPasswd;
		byte[] base64Token = Base64.getEncoder().encode(token.getBytes("UTF-8"));
		System.out.println("Basic " + new String(base64Token, "UTF-8"));

		final CreditCardData cardData = new CreditCardData();
		final CreditCardDetail cardDetail = new CreditCardDetail();
		cardData.setCreditCardDetail(cardDetail);
		String firstName = Optional.ofNullable(cardData.getCreditCardDetail()).orElse(new CreditCardDetail()).getFirstName();
		System.out.println("Optional. firstName: " + firstName);
		System.out.println("firstName: " + cardData.getCreditCardDetail().getFirstName());

		System.out.println(new BigDecimal("10.00"));
		System.out.println(new BigDecimal("10.00").toString());
		System.out.println(new BigDecimal("10.99").intValue());

		final List<String> sampleList = new ArrayList<>();
		System.out.println(Optional.ofNullable(sampleList).orElseGet(Collections::emptyList));

		final List<Integer> sampleList2 = null;
		final List<String> resultList = new ArrayList<>();
		Optional.ofNullable(sampleList2).orElseGet(Collections::emptyList).forEach(i -> {
			try {
				resultList.add("");
			} catch (final IllegalArgumentException e) {
			}
		});
		//System.out.println(resultList);
		//final String testNull = null;
		//System.out.println(Optional.ofNullable("testNull:"+testNull).orElse("else testNull:"+null));
		//System.out.println(StringUtils.hasText(testNull) ? "testNull:"+testNull : "else testNull:"+null);

		//System.out.println("Mop: " + Mop.valueOf("AX"));

		try{
			UUID uuid = UUID.fromString("e083efc7-047f-4910-aba6-31ce706646e0");
			System.out.println("uuid:" + uuid);
			//do something
		} catch (IllegalArgumentException exception){
			//handle the case where string is not valid UUID
		}

		System.out.println("UUID: " + UUID.randomUUID().toString());

	}
}
