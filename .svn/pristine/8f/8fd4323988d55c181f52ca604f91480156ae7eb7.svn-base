package com.pc.socket.service.handler;

import java.io.IOException;
import java.net.Socket;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.pc.bsp.common.util.SpringContextUtil;
import com.pc.socket.dao.IMpsSocketDao;


/**
 * @author D.Steven
 * @see
 * @since 2013-07-25
 */
public class MultiSocketStatusConnection implements Runnable
{
	private static Logger logger = Logger.getLogger(MultiSocketStatusConnection.class);
	
	protected static HashMap<String,Socket> ConnectionMap = new HashMap<String,Socket>();
	protected static List<String> pool = new LinkedList<String>();
	protected String strMAC;
	
	@Resource(name="mpsSocketDao")
	private IMpsSocketDao mpsSocketDao;
	
	/**
	 * constructor(手动获取mpsSocketDao)
	 */
	public MultiSocketStatusConnection() 
	{
		mpsSocketDao = (IMpsSocketDao)SpringContextUtil.getBean("mpsSocketDao");
	}
	 
	/**
	 * User处理
	 */
	public void handleUser() 
	{
		/*Socket Connection = ConnectionMap.get(strMAC);
		if(null == Connection)
		{
			ConnectionMap.remove(strMAC);
			return;
		}
	
		String strFeedBack = mpsSocketDao.StatusCMD(Connection, strMAC);
		if("03"==strFeedBack)
		{
			synchronized(ConnectionMap)
			{
				ConnectionMap.remove(strMAC);
			}
			try
			{
				Connection.close();
			}
			catch(IOException e1)
			{
				e1.printStackTrace();
			}
			finally
			{
				try
				{
					Connection.close();
				}catch(IOException e1)
				{
					e1.printStackTrace();
				}
			}
		}*/
	}
	
	/**
	 * 唤醒线程处理User请求
	 * 
	 * @param UserToHandle
	 */
	public static void processRequest(Socket UserToHandle,String strMAC) 
	{
		synchronized (ConnectionMap) 
		{
			Socket tempConnection = ConnectionMap.get(strMAC);
			if(null != tempConnection)
			{
				try{
					tempConnection.close();
				}catch(IOException e)
				{
					e.printStackTrace();
				}finally
				{
					if(null !=tempConnection)
					{
						try{
							tempConnection.close();
						}catch(IOException e)
						{
							e.printStackTrace();
						}
					}
				}
			}
			ConnectionMap.put(strMAC, UserToHandle);
			logger.info("添加一个设备Socket链接，链接池里的个数为：[" + ConnectionMap.size() + "]");
		}	
		synchronized (pool) 
		{
			if(!pool.contains(strMAC))
			{
				pool.add(pool.size(), strMAC);
				logger.info("添加一个请求到链接处理池中，链接处理池大小为：[" + pool.size() + "]" );
				pool.notifyAll();
			}
		}
	}
	
	/**
	 * 唤醒线程处理User请求
	 * 
	 * @param UserToHandle
	 */
	public static void processRequest(String strMAC) 
	{	
		synchronized (pool) 
		{
			if(!pool.contains(strMAC))
			{
				pool.add(pool.size(), strMAC);
				logger.info("添加一个请求到链接处理池中，链接处理池大小为：[" + pool.size() + "]" );
				pool.notifyAll();
			}
		}
	}
	
	/**
	 * run运行方法
	 */
	public void run()
	{
		while (true) 
		{	
			synchronized (pool)
			{
				while (pool.isEmpty()) 
				{
					try {
						pool.wait();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				strMAC = (String) pool.remove(0);
			}
			logger.info("[" + Thread.currentThread().getName() + 
					"]取走一个链接请求，链接请求池大小为:[" + pool.size() + "],开始处理……" );
			handleUser();
		}
	}
}
