package com.trujillodev.api.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

	private static SimpleDateFormat  formatterShortDate = new SimpleDateFormat("dd/mm/yyyy");
	private static SimpleDateFormat  formatterLongDate = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
	
	public static Date ConvertFullDateStringToDate(String value) throws ParseException {
		return formatterLongDate.parse(value);
	}

	public static String ConvertFullDateToString(Date value) {
		return formatterLongDate.format(value);
	}
	
	public static Date ConvertShortDateStringToDate(String value) throws ParseException {
		return formatterShortDate.parse(value);
	}

	public static String ConvertShortDateToString(Date value) {
		return formatterShortDate.format(value);
	}

	public static <T> T getValueOrDefault(T value, T defaultValue) {
	    return value == null ? defaultValue : value;
	}
}
