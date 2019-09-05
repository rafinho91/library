package com.library.utils;

import java.util.List;

public class CommonUtils {

	public static String transformArrayToString(String[] arrayOfStrings) {
		return arrayOfStrings != null ?
				String.join(",", arrayOfStrings) : null;
	}
	
	public static String getShortenedString(String text, int length) {
		return text != null ? 
				text.substring(0, Math.min(text.length(), length)) : null;
	}
	
	public static double averageOf(List<Double> values) {
		return values
				.stream()
				.mapToDouble(d -> d)
				.average()
				.getAsDouble();
	}
}
