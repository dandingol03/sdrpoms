package com.pc.socket.service.handler;


import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
//import org.apache.log4j.Logger;

import com.pc.bsp.common.util.SpringContextUtil;
import com.pc.socket.dao.IMpsSocketDao;

/**
 * @author D.Steven
 * @see
 * @since 2013-07-25
 */
public class MultiSocketStatusHandler implements Runnable
{
	private static Logger logger = Logger.getLogger(MultiSocketStatusHandler.class);
	
	protected static List<Socket> pool = new LinkedList<Socket>();
	protected Socket connection;
	
	@Resource(name="mpsSocketDao")
	private IMpsSocketDao mpsSocketDao;
	
	/**
	 * constructor(手动获取mpsSocketDao)
	 */
	public MultiSocketStatusHandler() 
	{
		mpsSocketDao = (IMpsSocketDao)SpringContextUtil.getBean("mpsSocketDao");
	}
	
	/**
	 * socket处理
	 */
	public void handleConnection() 
	{
		/*BufferedInputStream in = null;
		BufferedOutputStream out = null;

		try {
			in = new BufferedInputStream(connection.getInputStream());
			out = new BufferedOutputStream(connection.getOutputStream());
			
			byte[] input = new byte[1024];
			int nLenght = in.read(input,0,input.length);

			
			byte[] btempByte = Encrypt.getSockEncodecrypt(input,nLenght);
			String orderStr = new String(btempByte, "UTF-8").trim();
			String[]  strArrayOrder = orderStr.split(":");	
			
			String strMac = strArrayOrder[0];
			String strCMD = strArrayOrder[2];
			strMac = strMac.replaceAll("-", "");
			
			if(strArrayOrder.length<4||!orderStr.endsWith("&")||!strCMD.equals("0021"))
			{
				logger.info("Socket状态报备接受错误命令,错误命令为：[" + orderStr +"]");
				try {
						out.close();
						in.close();
						connection.close();			
				} catch (IOException e) {
					e.printStackTrace();
				}finally
				{
					try 
					{
						if (null != out)
						{
							out.close();
						}
						if (null != in) 
						{
							in.close();
						}
						if (null != connection) 
						{
						 connection.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				return;
			}			
			String strStatus = strArrayOrder[3];
			if(strStatus.equals("00&"))
			{
				
				String strFeedBack = mpsSocketDao.StatusCMD(connection, strMac);
				out.write(Encrypt.getSockEncodecrypt(strFeedBack.getBytes(),strFeedBack.length()));
				out.flush();
			}
			else
			{
				String strFeedBack = "00";
				strFeedBack=new String(Encrypt.getSockEncodecrypt(strFeedBack.getBytes(),strFeedBack.length()),"UTF-8");
				out.write(strFeedBack.getBytes("UTF-8"));
				out.flush();
				try {
					out.close();
					in.close();
					connection.close();			
					} catch (IOException e1) {
						e1.printStackTrace();
					}finally
					{
						try 
						{
							if (null != out)
							{
								out.close();
							}
							if (null != in) 
							{
								in.close();
							}
							if (null != connection) 
							{
							 connection.close();
							}
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
			}
			String strSta = strStatus.substring(0, strStatus.length()-1);
			mpsSocketDao.StatusAlarm(strMac,strSta);

		} catch (Exception e) 
		{
			try {
				out.close();
				in.close();
				connection.close();			
				} catch (IOException e1) {
					e1.printStackTrace();
				}finally
				{
					try 
					{
						if (null != out)
						{
							out.close();
						}
						if (null != in) 
						{
							in.close();
						}
						if (null != connection) 
						{
						 connection.close();
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			logger.info("状态报备状态异常！详细信息：[" + e.getMessage() +"]");
			e.printStackTrace();
		}*/
	}
	
	/**
	 * 唤醒线程处理socket请求
	 * 
	 * @param requestToHandle
	 */
	public static void processRequest(Socket requestToHandle) 
	{
		synchronized (pool) 
		{
			pool.add(pool.size(), requestToHandle);
			logger.info("Status添加一个请求道池中，Status请求池大小为：[" + pool.size() + "]" );
			pool.notifyAll();
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
				connection = (Socket) pool.remove(0);
			}
			logger.info("[" + Thread.currentThread().getName() + 
					"]取走一个请求，请求池大小为:[" + pool.size() + "],开始处理……" );
			handleConnection();
		}
	}

}
