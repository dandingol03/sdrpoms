package com.pc.bsp.common.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.pc.bsp.common.Config;

public class InitSysServlet extends HttpServlet{
	
	private static Logger logger = Logger.getLogger(InitSysServlet.class);

	private static final long serialVersionUID = -6832856544655485237L;
	
	/**
	 * 读取系统参数
	 */
	public  void init() throws ServletException  {
		
		Connection conn = null;
		
		try{
			conn = GetJDBCConnection.getJDBCConnection();
			Statement stmt = conn.createStatement();
			logger.info("加载系统参数开始...");
			String sql = "select param_code, param_value, param_status from pub_sys_param";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				PubData.setData(rs.getString("param_code"),rs.getString("param_value"));
			}
			logger.info("加载系统参数结束!");
			logger.info("=================");
			logger.info("加载数据字典开始...");
			
			sql = "select id, dict_code from pub_dictionary where is_parent='1'";
			Statement stmtDict = conn.createStatement();
			ResultSet rsDict = stmtDict.executeQuery(sql);
			while(rsDict.next()){
				String dictCode=rsDict.getString("dict_code");
				List<Map<String,Object>> tempNodeList =new ArrayList<Map<String,Object>>(); 
				
				Map<String,Object> tempDictNode = new HashMap<String,Object>();
				sql = "select dict_name, dict_data from pub_dictionary where pid='"+rsDict.getString("id")+"' order by dict_data";
				Statement stmtDictNode = conn.createStatement();
				ResultSet rsDictCode = stmtDictNode.executeQuery(sql);
				while(rsDictCode.next()){
					tempDictNode.put(rsDictCode.getString("dict_data"), 
							rsDictCode.getString("dict_name"));
					Map<String,Object> tempDictListNode = new HashMap<String,Object>();
					tempDictListNode.put("dictName", rsDictCode.getString("dict_name"));
					tempDictListNode.put("dictData", rsDictCode.getString("dict_data"));
					tempNodeList.add(tempDictListNode);
				}
				tempDictNode.put(dictCode+"List", tempNodeList);
				PubData.setDictData(dictCode, tempDictNode);
			}
			logger.info("加载数据字典结束!");
			logger.info("=================");
			logger.info("加载系统配置开始...");
			//下面加载config.properties文件
			Resource resource = new ClassPathResource("/config.properties");
			Properties props = PropertiesLoaderUtils.loadProperties(resource);
			Config.AUTH_MODE = props.getProperty("authMode");
			logger.info("加载系统配置结束!");
			
		}catch(Exception e){
			logger.error("InitSysServlet读取数据库参数或config.properties错误，错误信息["+e.getMessage()+"]",e);
		}finally{
			if(null != conn){
				GetJDBCConnection.closeConnection(conn);
			}
		}
	}

}
