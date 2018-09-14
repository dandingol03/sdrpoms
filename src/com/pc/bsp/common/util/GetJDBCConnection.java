/**
 * 
 */
package com.pc.bsp.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.pc.bsp.common.Config;

/**
 * @author simple
 *
 */
public class GetJDBCConnection {

	private static Logger logger = Logger.getLogger(GetJDBCConnection.class);

	private static Connection conn = null;

	private static String className = null;
	private static String url = null;
	private static String userName = null;
	private static String password = null;

	/**
	 * 获取JDBC数据库连接
	 * 
	 * @return
	 */
	public static synchronized Connection getJDBCConnection() {

		try {
			if (className == null) {
				// 只初始化一次，加载一次配置文件
				Resource resource = new ClassPathResource(
						"/database.properties");
				Properties props = PropertiesLoaderUtils
						.loadProperties(resource);

				Config.DATABASE = props.getProperty("database");

				className = props.getProperty("db.driverClassName");
				url = props.getProperty("db.url");
				userName = props.getProperty("db.username");
				password = props.getProperty("db.password");

			}
			Class.forName(className);
			conn = DriverManager.getConnection(url, userName, password);

		} catch (Exception e) {
			logger.error("获取数据库连接失败!错误信息:[" + e.getMessage() + "]", e);

		}
		return conn;
	}

	/**
	 * 关闭数据库连接
	 * 
	 * @param conn
	 */
	public static void closeConnection(Connection conn) {
		if (null != conn) {
			try {

				conn.close();

			} catch (SQLException e) {
				logger.error("关闭数据库连接失败!错误信息:[" + e.getMessage() + "]", e);

			}
		}
	}

}
