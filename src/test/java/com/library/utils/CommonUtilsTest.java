package com.library.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CommonUtilsTest {

	@Test
	public void transformArrayToStringTest() {
		String expectedValue = "one,two,three";

		String[] testArray = { "one", "two", "three" };
		String result = CommonUtils.transformArrayToString(testArray);

		assertThat(result).isEqualTo(expectedValue);
	}

	@Test
	public void getShortenedStringTest() {
		String expectedValue = "Lorem ipsum dolor";
		
		String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit";
		String result = CommonUtils.getShortenedString(text, 17);
		
		assertThat(result).isEqualTo(expectedValue);
	}

	@Test
	public void averageOfTest() {
		double expectedValue = 4.5;

		List<Double> testDoubles = Arrays.asList(2.0, 7.0);
		double result = CommonUtils.averageOf(testDoubles);

		assertThat(result).isEqualTo(expectedValue);
	}

}
