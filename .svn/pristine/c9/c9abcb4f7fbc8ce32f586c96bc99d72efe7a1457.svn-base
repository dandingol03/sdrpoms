package com.pc.socket.service.handler;

import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.pc.bsp.common.util.SpringContextUtil;
import com.pc.socket.dao.IMpsSocketDao;

public class MultiSocketDownloadHandler implements Runnable 
{
	private static Logger logger = Logger.getLogger(MultiSocketDownloadHandler.class);
	
	@Resource(name="mpsSocketDao")
	private IMpsSocketDao mpsSocketDao;
	
	protected static List<Socket> pool = new LinkedList<Socket>();
	protected Socket connection;

	/**
	 * constructor(手动获取mpsSocketDao)
	 */
	public MultiSocketDownloadHandler() 
	{
		mpsSocketDao = (IMpsSocketDao)SpringContextUtil.getBean("mpsSocketDao");
	}
	
	/**
	 * socket处理
	 */
	public void handleConnection() 
	{
		//Read-Write Stream
		/*BufferedInputStream in = null;
		BufferedOutputStream out = null;
		String strfeedBack = "01";
		
		try {
			in = new BufferedInputStream(connection.getInputStream());
			out = new BufferedOutputStream(connection.getOutputStream());
			
			byte[] input = new byte[1024];
			int nLength = in.read(input,0,input.length);

			String orderStr = new String(Encrypt.getSockEncodecrypt(input,nLength),"UTF-8");
			orderStr = orderStr.substring(0,orderStr.indexOf("&"));
			String[]  strArrayOrder = orderStr.split(":");	
			
			if(strArrayOrder.length<4)
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
			String strMac = strArrayOrder[0];
			strMac = strMac.replaceAll("-", "");
			String strCMD = strArrayOrder[2];
			int nCMD = Integer.parseInt(strCMD);
			switch(nCMD)
			{
			case 22:
				String[] strTempO = strArrayOrder[3].split("%");
				if(strTempO.length<2)
					break;
				String strPercent = strTempO[0]+"%";
				String strListName = strTempO[1];
				strfeedBack = mpsSocketDao.DownloadPercent(strMac,strPercent,strListName);
				break;
			default:
				break;
			}
			out.write(Encrypt.getSockEncodecrypt(strfeedBack.getBytes(),strfeedBack.length()));
		    out.flush();
		        
		    out.close();
		    in.close();
		    
		} catch (Exception e) 
		{
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
			logger.info("Download添加一个请求道池中，Download请求池大小为：[" + pool.size() + "]" );
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
