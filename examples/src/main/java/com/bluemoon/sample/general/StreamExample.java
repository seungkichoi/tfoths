package com.bluemoon.sample.general;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExample {
	public static void main(String[] args) {
		List<String> myList =
			Arrays.asList("a1", "a2", "b1", "c2", "c1");

		// example 1
		myList.stream()
			.filter(s -> s.startsWith("c"))
			.map(String::toUpperCase)
			.sorted()
			.forEach(System.out::println);

		System.out.println("parallel stream");
		myList.stream()
			.findFirst()
			.ifPresent(System.out::println);

		System.out.println("Steam of");
		Stream.of("a1", "a2", "a3")
			.findFirst()
			.ifPresent(System.out::println);

		System.out.println("IntStream.range");
		IntStream.range(1, 4)
			.forEach(System.out::println);

		System.out.println("mapToInt");
		Stream.of("a1", "a2", "a3")
			.map(s -> s.substring(1))
			.mapToInt(Integer::parseInt)
			.max()
			.ifPresent(System.out::println);

		System.out.println("mapToObj");
		IntStream.range(1, 4)
			.mapToObj(i -> "a" + i)
			.findFirst()
			.ifPresent(System.out::println);
	}
}
