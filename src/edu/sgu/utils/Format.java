package edu.sgu.utils;

import java.sql.Date;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Format {
	private static Locale lcVN = new Locale("vi", "VN");

	public static String chuyenTienVietNam(double money) {
		NumberFormat nfTienVN = NumberFormat.getCurrencyInstance(lcVN);
		return nfTienVN.format(money);
	}

	public static String chuyenNgayVietNam(Date date) {
		DateFormat dfVN = DateFormat.getDateInstance(DateFormat.SHORT, lcVN);
		return dfVN.format(date);
	}

}
