package com.pc.bsp.common.util;

import com.pc.bsp.common.Config;

public class SqlUtil {

	public static String getPageQuerySql(String sql, int pagenum, int rows) {
		String pageQuerySql = "";

		if ("oracle".equals(Config.DATABASE)) {
			pageQuerySql = "select * from (select A.*,ROWNUM RN from (" + sql + ")A where ROWNUM<=" + pagenum * rows
					+ ") where RN>" + (pagenum - 1) * rows + "";
		} else if ("mysql".equals(Config.DATABASE)) {
			pageQuerySql = sql + " limit " + (pagenum - 1) * rows + ", " + rows;
		}else{
			throw new DaoException("com.simple.bsp.common.Config.DATABASE配置错误");
		}

		return pageQuerySql;
	}
	
	public static String getOrderbySql(DataGridModel dgm) {
		String pageQuerySql = "";

		if ("oracle".equals(Config.DATABASE)) {
			pageQuerySql = " order by \"" + dgm.getSort() + "\" "+ dgm.getOrder();
		} else if ("mysql".equals(Config.DATABASE)) {
			pageQuerySql = " order by " + dgm.getSort() + " "+ dgm.getOrder();
		}else{
			throw new DaoException("com.simple.bsp.common.Config.DATABASE配置错误");
		}

		return pageQuerySql;
	}

	private static String getOraclePageQuerySql(String sql, int pagenum, int rows) {

		String pageQuerySql = "select * from (select A.*,ROWNUM RN from (" + sql + ")A where ROWNUM<=" + pagenum * rows
				+ ") where RN>" + (pagenum - 1) * rows + "";

		return pageQuerySql;
	}

	private static String getMysqlPageQuerySql(String sql, int pagenum, int rows) {

		String pageQuerySql = sql + " limit " + (pagenum - 1) * rows + ", " + rows;
		return pageQuerySql;
	}
}
