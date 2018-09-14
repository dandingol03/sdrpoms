package com.pc.socket.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import com.pc.bsp.common.util.GetJDBCConnection;

/**
 * @author simple
 *
 */
public class InitSocketServer extends HttpServlet{
	
	private static Logger logger = Logger.getLogger(InitSocketServer.class);

	private static final long serialVersionUID = -5616730244803392339L;

	/**
	 * 启动
	 */
	public  void init() throws ServletException  {
		
		Map<String, Object> mapserverConf = getParamConf();
		
		if(!mapserverConf.isEmpty())
		{
			String strCount = (String)mapserverConf.get("cmd_thread_count");
			int nCMDCount = Integer.parseInt(strCount);
			String strPort = (String)mapserverConf.get("cmd_port");
			int nCMDPort = Integer.parseInt(strPort);
			
			MultiSocketCMDServer CMDServer = new MultiSocketCMDServer();
			CMDServer.init(nCMDCount, nCMDPort);
			CMDServer.setStatus(1);
			logger.info("命令参数Socket服务端口为:["+strPort+"],线程数为:["+strCount+"]");
			
			//以下线程均不启动
//			strCount = (String)mapserverConf.get("time_thread_count");
//			int nTimeCount = Integer.parseInt(strCount);
//			strPort = (String)mapserverConf.get("upgrade_time_port");
//			int nTimePort = Integer.parseInt(strPort);
//			MultiSocketTimeServer TimeServer = new MultiSocketTimeServer();
//			TimeServer.init(nTimeCount, nTimePort);
//			TimeServer.setStatus(1);
//			System.out.print("时间升级参数Socket服务端口为:["+strPort+"],线程数为:["+strCount+"]");
//			
//			strCount = (String)mapserverConf.get("status_thread_count");
//			int nStatusCount = Integer.parseInt(strCount);
//			String strConncetion = (String)mapserverConf.get("status_connection_thread_count");
//			int nConnectionCount = Integer.parseInt(strConncetion);
//			strPort = (String)mapserverConf.get("status_port");
//			int nStatusPort = Integer.parseInt(strPort);
//			MultiSocketStatusServer StatusServer = new MultiSocketStatusServer();
//			StatusServer.init(nStatusCount, nStatusPort,nConnectionCount);
//			StatusServer.setStatus(1);
//			System.out.print("状态监控参数Socket服务端口为:["+strPort+"],线程数为:["+strCount+"]"+",链接线程数为:["+strConncetion+"]");
//			
//			strCount = (String)mapserverConf.get("download_thread_count");
//			int ndownloadCount = Integer.parseInt(strCount);
//			strPort = (String)mapserverConf.get("download_port");
//			int ndownloadPort = Integer.parseInt(strPort);
//			MultiSocketDownloadServer DownloadServer = new MultiSocketDownloadServer();
//			DownloadServer.init(ndownloadCount, ndownloadPort);
//			DownloadServer.setStatus(1);
//			System.out.print("下载参数Socket服务端口为:["+strPort+"],线程数为:["+strCount+"]");
		}
		else
		{
			logger.info("系统Socket配置参数查找失败！");
		}
	}
	
	/**
	 * 获取SocketServer参数
	 * @return
	 */
	private Map<String, Object> getParamConf(){
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Connection conn = null;
		
		paramMap.put("cmd_thread_count", "10");
		paramMap.put("cmd_port","5000");
		paramMap.put("time_thread_count","10");
		if(true)
		return paramMap;
		
		try{
			conn = GetJDBCConnection.getJDBCConnection();
			Statement stmt = conn.createStatement();
			String sql = "select cmd_thread_count, cmd_port, time_thread_count, upgrade_time_port, status_thread_count, " +
					"status_connection_thread_count, status_port, download_thread_count, download_port from mps_devices_conf_server";
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()){
				
				//paramMap.put("cmd_thread_count", rs.getString("cmd_thread_count"));
				//paramMap.put("cmd_port", rs.getString("cmd_port"));
				//paramMap.put("time_thread_count", rs.getString("time_thread_count"));
				paramMap.put("upgrade_time_port", rs.getString("upgrade_time_port"));
				paramMap.put("status_thread_count", rs.getString("status_thread_count"));
				paramMap.put("status_connection_thread_count", rs.getString("status_connection_thread_count"));
				paramMap.put("status_port", rs.getString("status_port"));
				paramMap.put("download_thread_count", rs.getString("download_thread_count"));
				paramMap.put("download_port", rs.getString("download_port"));
			}
			
		}catch(Exception e){
			e.printStackTrace();
			logger.info("InitSocketServer读取数据库参数错误，错误信息["+e.getMessage()+"]");
			GetJDBCConnection.closeConnection(conn);
		}finally{
			GetJDBCConnection.closeConnection(conn);
		}
		return paramMap;
	}

}
