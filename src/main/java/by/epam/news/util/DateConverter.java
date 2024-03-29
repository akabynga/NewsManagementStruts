package by.epam.news.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import by.epam.news.constant.NewsConstant;

public final class DateConverter {

	private DateConverter() {
		super();
	}

	public static String convertDateToString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(
				NewsConstant.PARAM_DATE_FORMAT_OUT);
		String string = null;
		if (date != null) {
			string = format.format(date);
		}
		return string;
	}

	public static java.sql.Date convertStringToDate(String dateAsString)
			throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(
				NewsConstant.PARAM_DATE_FORMAT_OUT);
		return new java.sql.Date(format.parse(dateAsString).getTime());
	}
}
