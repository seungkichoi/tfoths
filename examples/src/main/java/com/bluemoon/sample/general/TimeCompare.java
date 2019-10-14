package com.bluemoon.sample.general;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

public class TimeCompare {
	public static void main (String[] args) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		System.out.println(simpleDateFormat.format(date));
		System.out.println(String.valueOf(simpleDateFormat.format(date)));

		// Sample 1
		// compare time with standard way
		final Instant instantNow = Instant.now();
		// Create from a String
		final Instant instantPast = Instant.parse("2019-09-01T10:12:35Z");

		if(instantNow.isBefore(instantPast)){
			System.out.println("instantNow: " + instantNow + " is before transactionInstant: " + instantPast);
		} else {
			System.out.println("instantNow: " + instantNow + " is after transactionInstant: " + instantPast);
		}

		// Adding 5 hours and 4 minutes to an Instant
		final Instant instantFuture = instantNow.plus(Duration.ofHours(5).plusMinutes(4));
		if(instantNow.isBefore(instantFuture)){
			System.out.println("instantNow: " + instantNow + " is before transactionInstant: " + instantFuture);
		} else {
			System.out.println("instantNow: " + instantNow + " is after transactionInstant: " + instantFuture);
		}

		// Substract 5 days of an instant
		instantNow.minus(5, ChronoUnit.DAYS); // Option 1
		instantNow.minus(Duration.ofDays(5)); // Option 2
		// How many minutes are between to Instants?
		long diffAsMinutes = ChronoUnit.MINUTES.between(instantNow, instantNow);

		// Sample 2
		LocalDateTime localDateTime = LocalDateTime.now();
		// Jump to 25 hours and 3 minutes into the future
		LocalDateTime inTheFuture = localDateTime.plusHours(25).plusMinutes(3);
		// We could do the same on localTime or localDate
		System.out.println(localDateTime.toLocalTime().plusHours(25).plusMinutes(3));
		System.out.println(localDateTime.toLocalDate().plusMonths(2));
		// We could also use TemportalAmount (in this case a Duration and Period)
		System.out.println(localDateTime.toLocalTime().plus(Duration.ofHours(25).plusMinutes(3)));
		System.out.println(localDateTime.toLocalDate().plus(Period.ofMonths(2)));

		System.out.println(Optional.ofNullable(null).orElse(String.valueOf("1")));

		System.out.println("Instant: " + Instant.ofEpochMilli(System.currentTimeMillis()).toString());
	}

}
