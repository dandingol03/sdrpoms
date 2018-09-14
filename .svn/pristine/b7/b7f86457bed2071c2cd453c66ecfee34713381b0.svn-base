package com.pc.socket.service.handler;

import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.pc.bsp.common.util.SpringContextUtil;
import com.pc.socket.dao.IMpsSocketDao;

public class MultiSocketTimeHandler implements Runnable 
{
	private static Logger logger = Logger.getLogger(MultiSocketTimeHandler.class);
	
	protected static List<Socket> pool = new LinkedList<Socket>();
	protected Socket connection;
	protected SimpleDateFormat sdateformat;
	
	@Resource(name="mpsSocketDao")
	private IMpsSocketDao mpsSocketDao;
	
	/**
	 * constructor(手动获取mpsSocketDao)
	 */
	public MultiSocketTimeHandler() 
	{
		sdateformat = new SimpleDateFormat("yyyy-MM-dd;HH:mm:ss");
		mpsSocketDao = (IMpsSocketDao)SpringContextUtil.getBean("mpsSocketDao");
	}
	
	/**
	 * Time
	 * @param strMac 
	 * @return 2013-07-05;02:00:00
	*/
	/*public String CMDTime(String strMAC)
	{
		String strSocket = PubData.getSockData("value").toString();
        String resultString = "";     
          
        char[] charArray = strSocket.toCharArray();  
      
        for (int i=charArray.length-1; i>=1; i=i-2)
        {  
        	resultString += charArray[i-1];
            resultString += charArray[i];
        }  
        
		String strFeedBack = sdateformat.format(new Date());
		strFeedBack+=";"+resultString+"&";
		return strFeedBack;
	}*/
	
	/**
	 * socket处理
	 */
	public void handleConnection() 
	{
		/*BufferedInputStream in = null;
		BufferedOutputStream out = null;
		String strfeedBack = "01";
		
		try {
			in = new BufferedInputStream(connection.getInputStream());
			out = new BufferedOutputStream(connection.getOutputStream());
			
			byte[] input = new byte[1024];
			int nLength = in.read(input,0,input.length);
			Base64 temp64 = new Base64();
			
			String orderStr = new String(input, "UTF-8").trim();
			
			byte[] btempByte = Encrypt.getSockEncodecrypt(input,nLength);
			String strEn = new String(btempByte, "UTF-8").trim();
			
			String str64 = temp64.decode(orderStr);
			
			String[]  strArrayEn = strEn.split(":");
			String[]  strArray64 = null;
			if(str64!=null)
				strArray64 = str64.split(":");
			
			if(strArrayEn.length<3&&strArray64!=null&&strArray64.length<3)
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
			if(strArray64!=null&&strArray64.length>=3)
			{
				String temp2 = strArray64[2];
				long n2 = Long.parseLong(temp2);
				if(n2==2)
				{
					orderStr=str64;
				}
				else
				{
					if(strArrayEn.length>=3)
					{
						String temp20 = strArrayEn[2];
						long n20 = Long.parseLong(temp20);
						if(n20==20)
						{
							orderStr=strEn;
						}
					}
					else
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
				}
			}
			else
			{
				if(strArrayEn.length>=3)
				{
					String temp20 = strArrayEn[2];
					long n20 = Long.parseLong(temp20);
					if(n20==20)
					{
						orderStr=strEn;
					}
				}
				else
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
			}
			
			String[]  strArrayOrder = orderStr.split(":");	
			
			String strMac = strArrayOrder[0];
			strMac = strMac.replaceAll("-", "");
			String strCMD = strArrayOrder[2];
			int nCMD = Integer.parseInt(strCMD);
			
			if(strArrayOrder.length<3)
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
			
			switch(nCMD)
			{
			case 2:
				strfeedBack = CMDTime(strMac);
				Base64 t64 = new Base64();
				strfeedBack = t64.encode(strfeedBack);
				out.write(strfeedBack.getBytes("UTF-8"));
				break;
			case 20:
				strfeedBack = mpsSocketDao.UpgradeVersion(strMac,strArrayOrder[3],strArrayOrder[4]);
				out.write(Encrypt.getSockEncodecrypt(strfeedBack.getBytes(),strfeedBack.length()));
				break;
			default:
				break;
			}
			
		    out.flush();
		        
		    out.close();
		    in.close();

		} catch (Exception e) 
		{
			logger.info("状态报备状态异常！详细信息：[" + e.getMessage() +"]");
			//m_staticStatusLogger.error("状态报备状态异常！详细信息[" + e.getMessage() + "]");
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
			logger.info("Time添加一个请求道池中，Time请求池大小为：[" + pool.size() + "]" );
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
