package com.pc.bsp.security.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
//import org.springframework.stereotype.Service;

import com.pc.bsp.common.Config;
import com.pc.bsp.common.util.GetJDBCConnection;
import com.pc.bsp.security.utils.UrlMatcher;
import com.pc.bsp.security.utils.impl.AntUrlPathMatcher;

/**
 * 
 * 最核心的地方，就是提供某个资源对应的权限定义，即getAttributes方法返回的结果。 此类在初始化时，应该取到所有资源及其对应角色的定义。
 * 由于spring-security.xml中已经对该bean进行了定义，因此不再增加service注解
 * @author simple
 */


public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

	private static Logger logger = Logger.getLogger(MyInvocationSecurityMetadataSourceService.class);

	private UrlMatcher urlMatcher = new AntUrlPathMatcher();

	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	/**
	 * 构造方法
	 */
	public MyInvocationSecurityMetadataSourceService() {
		loadResourceDefine();
	}

	/**
	 * 当修改权限时，系统重新加载资源定义
	 */
	public void reloadResourceDefine() {
		if (resourceMap != null) {
			// 获取所有权限对象
			Connection conn = GetJDBCConnection.getJDBCConnection();
			// System.out.println("初始化权限集合开始...");
			logger.info("重新加载权限集合开始...");

			// 获取所有权限(权限名称AUTH_*)
			List<String> pubAuthorities = getAuthorities(conn);
			
			//清空资源Map
			resourceMap.clear();

			// 权限名称
			for (String authorityId : pubAuthorities) {
				// System.out.println("权限ID:["+authorityId+"]，对应的资源有：");
				logger.info("权限ID:[" + authorityId + "]，对应的资源有：");
				ConfigAttribute ca = new SecurityConfig(authorityId);
				// 根据权限名称获取所有资源对象
				List<String> resList = getAuthResources(conn, authorityId);

				for (String resString : resList) {
					// System.out.println("["+resString+"]");
					logger.info("[" + resString + "]");
					// 判断资源文件和权限的对应关系，如果已经存在相关的资源url，则要通过该url为key提取出权限集合，将权限增加到权限集合中
					if (resourceMap.containsKey(resString)) {
						Collection<ConfigAttribute> value = resourceMap.get(resString);
						value.add(ca);
						resourceMap.put(resString, value);
						// System.out.println("["+resString+"]已存在，添加完成！");
						logger.info("[" + resString + "]已存在，添加完成！");
					} else {
						Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
						atts.add(ca);
						resourceMap.put(resString, atts);
						// System.out.println("["+resString+"]不存在，添加完成！");
						logger.info("[" + resString + "]不存在，添加完成！");
					}
				}
			}
			GetJDBCConnection.closeConnection(conn);

			// System.out.println("初始化权限集合完成！");
			logger.info("重新加载权限集合完成！");
		}
	}

	private void loadResourceDefine() {

		// 系统启动时加载spring security和spring配置，会实例化两遍当前bean对象，加入判断后第二次不再重复执行。
		// 此处已经修改，去掉了对象注解，只会实例化一遍该bean对象
		if (null != resourceMap) {
			return;
		}

		// 获取所有权限对象
		Connection conn = GetJDBCConnection.getJDBCConnection();
		// System.out.println("初始化权限集合开始...");
		logger.info("初始化权限集合开始...");

		// 获取所有权限(权限名称AUTH_*)
		List<String> pubAuthorities = getAuthorities(conn);

		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();

		// 权限名称
		for (String authorityId : pubAuthorities) {
			// System.out.println("权限ID:["+authorityId+"]，对应的资源有：");
			logger.info("权限ID:[" + authorityId + "]，对应的资源有：");
			ConfigAttribute ca = new SecurityConfig(authorityId);
			// 根据权限名称获取所有资源对象
			List<String> resList = getAuthResources(conn, authorityId);

			for (String resString : resList) {
				// System.out.println("["+resString+"]");
				logger.info("[" + resString + "]");
				// 判断资源文件和权限的对应关系，如果已经存在相关的资源url，则要通过该url为key提取出权限集合，将权限增加到权限集合中
				if (resourceMap.containsKey(resString)) {
					Collection<ConfigAttribute> value = resourceMap.get(resString);
					value.add(ca);
					resourceMap.put(resString, value);
					// System.out.println("["+resString+"]已存在，添加完成！");
					logger.info("[" + resString + "]已存在，添加完成！");
				} else {
					Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
					atts.add(ca);
					resourceMap.put(resString, atts);
					// System.out.println("["+resString+"]不存在，添加完成！");
					logger.info("[" + resString + "]不存在，添加完成！");
				}
			}
		}
		GetJDBCConnection.closeConnection(conn);

		// System.out.println("初始化权限集合完成！");
		logger.info("初始化权限集合完成！");
	}

	/**
	 * 根据URL，找到相关的权限配置。
	 */
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		// object为用户请求的URL
		String url = ((FilterInvocation) object).getRequestUrl();
		// 过滤带有参数的请求请求
		int sign = url.indexOf("?");
		if (sign != -1) {
			url = url.substring(0, sign);
		}
		Iterator<String> ite = resourceMap.keySet().iterator();
		while (ite.hasNext()) {
			String resURL = ite.next();
			if (urlMatcher.pathMatchesUrl(resURL, url)) {
				return resourceMap.get(resURL);
			}
		}
		if ("strict".equals(Config.AUTH_MODE)) {
			Collection<ConfigAttribute> returnCollection = new ArrayList<ConfigAttribute>();
			returnCollection.add(new SecurityConfig("AUTH_NO_USER"));
			return returnCollection;// 严格模式，如果该资源未注册，则都不可访问
		}
		return null;// 宽松模式，如果该资源未注册，则都可以访问
		// Collection<ConfigAttribute> returnCollection = new
		// ArrayList<ConfigAttribute>();
		// returnCollection.add(new SecurityConfig("AUTH_NO_USER"));
		// return returnCollection;//严格模式，如果该资源未注册，则都不可访问

	}

	/**
	 * 获取权限集合
	 * 
	 * @param conn
	 * @return
	 */
	private List<String> getAuthorities(Connection conn) {
		List<String> authList = new ArrayList<String>();

		if (null == conn) {
			return authList;
		}

		try {
			String sql = "select authority_id from pub_authorities";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				authList.add(rs.getString("authority_id"));
			}

		} catch (SQLException e) {
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			logger.error("获取权限集合失败!错误信息:[" + e.getMessage() + "]",e);
			
		}
		return authList;
	}

	/**
	 * 根据权限获取资源
	 * 
	 * @param conn
	 * @param authorityName
	 * @return
	 */
	private List<String> getAuthResources(Connection conn, String authorityId) {
		List<String> resList = new ArrayList<String>();

		if (null == conn) {
			return resList;
		}

		try {
			String sql = "select r.resource_string from pub_resources r, pub_authorities_resources ar, pub_authorities a "
					+ "where r.resource_id = ar.resource_id and ar.authority_id = a.authority_id and a.authority_id = '"
					+ authorityId + "'";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				resList.add(rs.getString("resource_string"));
			}
			if (null != rs) {
				rs.close();
			}
		} catch (SQLException e) {
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			logger.error("获取权限获取资源失败!错误信息:[" + e.getMessage() + "]",e);
			
		}
		return resList;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

}
