package com.payconiq.stockmanagement.util;

import java.util.Random;

import org.springframework.beans.factory.annotation.Value;

public class StockUtil {
	
	/**
	 * This utility is used to generate random number between a min a max values.
	 * Min and max values are loaded from the properties file.
	 */

	@Value("${id.gen.min}")
	private int randomNumGeneratorMin;
	
	@Value("${id.gen.max}")
	private int randomNumGeneratorMax;
	
	public Integer getRandomNumberInts() {
	    Random random = new Random();
	    return random.ints(randomNumGeneratorMin,(randomNumGeneratorMax+1)).findFirst().getAsInt();
	}
}
