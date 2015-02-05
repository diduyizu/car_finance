package com.carfinance.core.dao;

import java.text.MessageFormat;

public class PaginationHelper {
	public static String retriveCountField(String sql) {
		int index = sql.toUpperCase().indexOf(" DISTINCT ");
		if (index == -1) {
			return "*";
		} else {
			int commaIndex = sql.indexOf(",");
			int fromIndex = sql.toUpperCase().indexOf(" FROM ");
			if (commaIndex != -1 && commaIndex < fromIndex) {
				return "DISTINCT " + sql.substring(index + " DISTINCT ".length(), commaIndex).trim();
			} else {
				return "DISTINCT " + sql.substring(index + " DISTINCT ".length(), fromIndex).trim();
			}
		}
	}
	public static String decorateCountSql(String sql) {
		return new StringBuffer("SELECT count(*) FROM (").append(sql).append(")").toString();
	}
	private final static String	FIRST_PAGE_SQL	= "SELECT * FROM ( {0} ) WHERE rownum <= {1}";
	private final static String	PAGING_SQL		= "SELECT * FROM ( SELECT row_.*, rownum r FROM ( {0} ) row_ WHERE rownum <= {1} ) WHERE r > {2}";
	public static String decorateToPaginationSql(String sql, int startIndex, int count) {
		if (startIndex < 0 || count < 0)
			throw new IllegalArgumentException("startIndex || which value < 0, is a illegal argument");
		if (startIndex == 0) {
			return MessageFormat.format(FIRST_PAGE_SQL, new Object[] { sql, String.valueOf(count) });
		} else {
			return MessageFormat.format(PAGING_SQL, new Object[] { sql, String.valueOf(startIndex + count), String.valueOf(startIndex) });
		}
	}
}