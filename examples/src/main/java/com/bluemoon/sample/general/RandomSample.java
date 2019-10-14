package com.bluemoon.sample.general;

import java.util.Random;
import java.util.stream.IntStream;

public class RandomSample {

	public static void main(String[] args) {
		IntStream.range(0, 10000).forEach(i -> getRandomPercent(10));
		//getRandomPercent(10);
	}

	public static void getRandomPercent(int percent) {
		Random rand = new Random();
		final int nextBound = rand.nextInt(100);
		if(nextBound <= percent) {
			System.out.println("enable, value:" + nextBound);
		} else {
			System.out.println("disable, value:" + nextBound);
		}
	}

}
