package com.pc.socket.service.handler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.aspectj.weaver.ast.Var;

import com.pc.bsp.common.util.SpringContextUtil;
import com.pc.busniess.baseInfoDefenceBroadcast.po.BaseInfoDefenceBroadcastPo;
import com.pc.busniess.baseInfoDefenceBroadcast.po.BroadcastPo;
import com.pc.busniess.baseInfoDefenceBroadcast.service.BaseInfoDefenceBroadcastService;
import com.pc.socket.bean.Commands;
import com.pc.socket.bean.NetInfo;
import com.pc.socket.bean.Task;
import com.pc.socket.dao.IMpsSocketDao;
import com.sun.glass.ui.Timer;


public class MultiSocketCMDHandler implements Runnable
{
	private static Logger logger = Logger.getLogger(MultiSocketCMDHandler.class);
	
	protected static List<DatagramPacket> pool = new LinkedList<DatagramPacket>();
	public static HashMap<String,NetInfo> broadcastColumns=new HashMap<>();
	public static HashMap<String, List<Task>> broadcastTasks=new HashMap<>();
	
	protected DatagramPacket connection;
	protected DatagramSocket socket;


	private BaseInfoDefenceBroadcastService baseInfoDefenceBroadcastService;
	
	public BaseInfoDefenceBroadcastService getBaseInfoDefenceBroadcastService() {
		return baseInfoDefenceBroadcastService;
	}

	public void setBaseInfoDefenceBroadcastService(BaseInfoDefenceBroadcastService baseInfoDefenceBroadcastService) {
		this.baseInfoDefenceBroadcastService = baseInfoDefenceBroadcastService;
	}


	public MultiSocketCMDHandler() 
	{
		baseInfoDefenceBroadcastService = (BaseInfoDefenceBroadcastService)SpringContextUtil.getBean("baseInfoDefenceBroadcastService");
	}
		
	
	public MultiSocketCMDHandler(DatagramSocket socket) 
	{
		baseInfoDefenceBroadcastService = (BaseInfoDefenceBroadcastService)SpringContextUtil.getBean("baseInfoDefenceBroadcastService");
		this.socket = socket;
	
	}
	
	

	/**
	 * 处理client端发送过来的数据报
	 */
	public void handleConnection() 
	{	
		//Read-Write Stream
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		String strfeedBack = "01";
		
		try {

			String orderStr=null;
			byte[] input= connection.getData();
			//初始化返回的数据包
			DatagramPacket packetCallback = null;
			final InetAddress address = connection.getAddress();;
			final int port = connection.getPort();
			logger.info("port -> "+port);
			 byte[] ipAddress=address.getAddress();
	         byte[] response = null;
	         
			//区别命令
     		byte initial=input[0];
     		byte second=input[1];
     		
			byte[] columnId=new byte[4];
			boolean result=((initial&0xf0)==0xD0);
			byte[] matched=new byte[] {(byte)0xD8,(byte)0x00,(byte)0x48,(byte)0x01};
			logger.info("got -> "+Arrays.toString(input));
			if((initial&0xf0)==0xd0)//注册包
			{
				//记录广播柱的id
				NetInfo info=new NetInfo();
				info.setAddress(address);
				info.setPort(port);
		        BroadcastPo po=new BroadcastPo();
		        for(int j=0;j<4;j++)
		        	columnId[j]=input[j];
		        logger.info("columnId  -> "+ Arrays.toString(columnId));
		        broadcastColumns.put(Arrays.toString(columnId),info);
				logger.info(broadcastColumns.toString());
		        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        Date day=new Date();
		        String date=df.format(day);
		        po.setNumber(columnId);
		        po.setVisitedTime(date);
		        po.setIpAddress(ipAddress);
		        po.setPort(port);
		        if(baseInfoDefenceBroadcastService!=null)
		        	baseInfoDefenceBroadcastService.updateBroadcast(po);
		        else
		        	logger.info("service 获取不到");
		        
		        //返回注册包
		        response=new byte[4];
		        for(int k=0;k<4;k++)
		        	response[k]=input[k];
				logger.info("return register data -> "+Arrays.toString(response));
				packetCallback = new DatagramPacket(response, response.length, address, port);
		        socket.send(packetCallback);
		        
		        //查询播放状态
		        logger.info("查询播放状态");
		        packetCallback = new DatagramPacket(Commands.PLAY_STATUS_INQUIRE, Commands.PLAY_STATUS_INQUIRE.length, address, port);
		        socket.send(packetCallback);
			}else if((initial&0xf0)==0xC0)//心跳包
			{
				//返回心跳包
				response=input;
				logger.info("heart -> "+Arrays.toString(input));
				packetCallback = new DatagramPacket(response, response.length, address, port);
		        socket.send(packetCallback);
		        //更新广播柱的status和visitedTime
		        BroadcastPo po=new BroadcastPo();
		        columnId=input;
		        columnId[0]=(byte)(0xd0|(columnId[0]&0x0f));//合成广播柱id
		        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        Date day=new Date();
		        String date=df.format(day);
		        logger.info("date ->"+date);
		        logger.info("origin id -> "+Arrays.toString(columnId));
		        po.setNumber(columnId);
		        po.setVisitedTime(date);
		        po.setIpAddress(ipAddress);
		        po.setPort(port);
		        
		        if(baseInfoDefenceBroadcastService!=null)
		        	baseInfoDefenceBroadcastService.updateBroadcast(po);
		        else
		        	logger.info("service 获取不到");
		        
		        //查询播放状态
		        logger.info("查询播放状态");
		        packetCallback = new DatagramPacket(Commands.PLAY_STATUS_INQUIRE, Commands.PLAY_STATUS_INQUIRE.length, address, port);
		        socket.send(packetCallback);
		        
			}else if(((initial&0xff)==0xaa)&&((second&0xff)==0x85))//查询触发次数返回
			{
				logger.info("收到查询触发次数");
				//转换触发次数保存进数据库
				byte[] trigger=new byte[2];
				BroadcastPo po=new BroadcastPo();
				for(int i=2;i<4;i++)
					trigger[i-2]=input[i];
				logger.info("trigger count ->"+Arrays.toString(trigger));
				po.setIpAddress(ipAddress);
				po.setPort(port);
				po.setTrigger(trigger);
				if(baseInfoDefenceBroadcastService!=null)
		        	baseInfoDefenceBroadcastService.updateBroadcastInfoTrigger(po);
		        else
		        	logger.info("service 获取不到");
			}else if(((initial&0xff)==0xaa)&&((second&0xff)==0x86))//查询播放状态返回
			{
				logger.info("收到查询状态返回");
				byte[] status=new byte[1];
				BroadcastPo po=new BroadcastPo();
				status[0]=input[2];
				po.setIpAddress(ipAddress);
				po.setPort(port);
				if((status[0]&0x0f)==0x01)
					po.setPlayStatus("1");
				else {
					po.setPlayStatus("0");
				}
				if(baseInfoDefenceBroadcastService!=null)
		        	baseInfoDefenceBroadcastService.updateBroadcastInfoPlayStatus(po);
		        else
		        	logger.info("service 获取不到");
			}else if(((initial&0xff)==0xaa)&&((second&0xff)==0x90))//设置tts音源成功
			{
				logger.info("设置tts音源成功");
				BroadcastPo po=new BroadcastPo();
				po.setIpAddress(ipAddress);
				po.setPort(port);
				Map<String, Object> poInfo= baseInfoDefenceBroadcastService.findByIpPort(po);
				logger.info("poInfo--3-3"+poInfo);
				if(poInfo.get("number")!=null)
				{
					logger.info("po find ->");
					String number=poInfo.get("number").toString();
					String regex = "(.{2})";
					number = number.replaceAll (regex, "$1 ");
					String[] num=number.split(" ");
					byte[] tmpId=new byte[] {(byte)Integer.parseInt(num[0],16),(byte)Integer.parseInt(num[1],16),(byte)Integer.parseInt(num[2],16),(byte)Integer.parseInt(num[3],16)};
					List<Task> tasks=MultiSocketCMDHandler.broadcastTasks.get(Arrays.toString(tmpId));
					java.util.Timer timer=new java.util.Timer();
					timer.schedule(new TimerTask() {
						
						@Override
						public void run() {
							
							try {
								// TODO Auto-generated method stub
								DatagramPacket	packetCallback = new DatagramPacket(Commands.TRIGGER_COUNT_INQUIRE, Commands.TRIGGER_COUNT_INQUIRE.length, address, port);
								logger.info("发送登记任务");
								DatagramSocket sendSocket=socket;
								sendSocket.send(packetCallback);
							}catch (Exception e) {
								logger.error(e);
							}
						}
					}, 500);
					
				
					
//					if(tasks!=null&&tasks.size()>0)
//					{
//						for(int i=0;i<tasks.size();i++)
//						{
//							Task task=tasks.get(i);
//							byte[] command= task.getCommand();
//							logger.info("发送登记任务");
//							logger.info("发送的命令 -> "+Arrays.toString(command));
//						     packetCallback = new DatagramPacket(Commands.TRIGGER_COUNT_INQUIRE, Commands.TRIGGER_COUNT_INQUIRE.length, address, port);
//						     socket.send(packetCallback);
//						}
//						//清除任务
//						tasks.clear();
//					}
				}
			}
			else if(((initial&0xff)==0xaa)&&((second&0xff)==0xa6))//查询音源通道返回
			{
				logger.info("收到查询音源通道返回");
				byte[] source=new byte[1];
				BroadcastPo po=new BroadcastPo();
				source[0]=input[2];
				po.setIpAddress(ipAddress);
				po.setPort(port);
				po.setSoundSourceChannel(source);
				//保存音源通道信息
				if(baseInfoDefenceBroadcastService!=null)
		        	baseInfoDefenceBroadcastService.updateBroadcastInfoSoundSourceChannel(po);
		        else
		        	logger.info("service 获取不到");
				
			}else if (((initial&0xff)==0xaa)&&((second&0xff)==0x92))//语音合成成功
			{
				logger.info("语音合成成功");
				
			}
			else if (((initial&0xff)==0xaa)&&((second&0xff)==0x82))//语音文件位置返回
			{
				logger.info("语音文件位置返回");
				byte[] position=new byte[1];
				position[0]=input[2];
				BroadcastPo po=new BroadcastPo();
				po.setVoiceFileAddress(position);
				po.setIpAddress(ipAddress);
				po.setPort(port);

				if(baseInfoDefenceBroadcastService!=null)
		        	{}
		        else
		        	logger.info("service 获取不到");
			}else if (((initial&0xff)==0xaa)&&((second&0xff)==0x83))//电压值返回
			{
				logger.info("电压值返回");
				byte[] voltage=new byte[2];
				for(int i=2;i<4;i++)
					voltage[i-2]=input[i];
				BroadcastPo po=new BroadcastPo();
				po.setVoltage(voltage);
				po.setIpAddress(ipAddress);
				po.setPort(port);

				if(baseInfoDefenceBroadcastService!=null)
		        	{}
		        else
		        	logger.info("service 获取不到");
			}else if (((initial&0xff)==0xaa)&&((second&0xff)==0x89))//系统版本返回
			{
				byte[] versionNumber=new byte[2];
				for(int i=2;i<4;i++)
					versionNumber[i-2]=input[i];
				BroadcastPo po=new BroadcastPo();
				po.setVersionNumber(versionNumber);

				if(baseInfoDefenceBroadcastService!=null)
		        	{}
		        else
		        	logger.info("service 获取不到");
			}else if (((initial&0xff)==0xaa)&&((second&0xff)==0x8a))//TF卡文件总数
			{
				byte[] tfCardFileTotal=new byte[2];
				for(int i=2;i<4;i++)
					tfCardFileTotal[i-2]=input[i];
				BroadcastPo po=new BroadcastPo();
				po.setTfCardFileTotal(tfCardFileTotal);

				if(baseInfoDefenceBroadcastService!=null)
		        	{}
		        else
		        	logger.info("service 获取不到");
			}else if (((initial&0xff)==0xaa)&&((second&0xff)==0x8b))//Flash文件总数
			{
				byte[] flashFileTotal=new byte[2];
				for(int i=2;i<4;i++)
					flashFileTotal[i-2]=input[i];
				BroadcastPo po=new BroadcastPo();
				po.setFlashFileTotal(flashFileTotal);

				if(baseInfoDefenceBroadcastService!=null)
		        	{}
		        else
		        	logger.info("service 获取不到");
			}else if (((initial&0xff)==0xaa)&&((second&0xff)==0x8c))//tf当前曲目
			{
				byte[] tfCurrentTrcak=new byte[2];
				for(int i=2;i<4;i++)
					tfCurrentTrcak[i-2]=input[i];
				BroadcastPo po=new BroadcastPo();
				po.setTfCurrentTrcak(tfCurrentTrcak);
				

				if(baseInfoDefenceBroadcastService!=null)
		        	{}
		        else
		        	logger.info("service 获取不到");
			}else if (((initial&0xff)==0xaa)&&((second&0xff)==0x8d))//flash当前曲目
			{
				byte[] flashCurrentTrcak=new byte[2];
				for(int i=2;i<4;i++)
					flashCurrentTrcak[i-2]=input[i];
				BroadcastPo po=new BroadcastPo();
				po.setFlashCurrentTrcak(flashCurrentTrcak);

				if(baseInfoDefenceBroadcastService!=null)
		        	{}
		        else
		        	logger.info("service 获取不到");
			}else if (((initial&0xff)==0xaa)&&((second&0xff)==0x98))//返回TTS音量
			{
				byte[] ttsVolume=new byte[2];
				for(int i=2;i<4;i++)
					ttsVolume[i-2]=input[i];
				BroadcastPo po=new BroadcastPo();
				po.setTtsVolume(ttsVolume);

				if(baseInfoDefenceBroadcastService!=null)
		        	{}
		        else
		        	logger.info("service 获取不到");
			}else if (((initial&0xff)==0xaa)&&((second&0xff)==0xa1))//返回主动上报时间间隔
			{
				byte[] activeReportInterval=new byte[2];
				for(int i=2;i<4;i++)
					activeReportInterval[i-2]=input[i];
				BroadcastPo po=new BroadcastPo();
				po.setActiveReportInterval(activeReportInterval);

				if(baseInfoDefenceBroadcastService!=null)
		        	{}
		        else
		        	logger.info("service 获取不到");
			}else if (((initial&0xff)==0xaa)&&((second&0xff)==0xa4))//返回LED当前显示文字
			{
				byte[] ledText=new byte[input.length-2];
				for(int i=2;i<input.length;i++)
					ledText[i-2]=input[i];
				BroadcastPo po=new BroadcastPo();
				po.setLedText(ledText);

				if(baseInfoDefenceBroadcastService!=null)
		        	{}
		        else
		        	logger.info("service 获取不到");
			}else if (((initial&0xff)==0xaa)&&((second&0xff)==0xa7))//返回预置语音音量
			{
				byte[] presetVoicVolume=new byte[1];
				presetVoicVolume[0]=input[2];
				BroadcastPo po=new BroadcastPo();
				po.setPresetVoicVolume(presetVoicVolume);

				if(baseInfoDefenceBroadcastService!=null)
		        	{}
		        else
		        	logger.info("service 获取不到");
			}
			
		
			
			
			//orderStr = new String(connection.getData(), 0, connection.getLength(),"UTF-8").trim();

			
	         
	     
			
			//处理命令
//			String strMac = strArrayOrder[0];
//			strMac = strMac.replaceAll("-", "");
//			String strCMD = strArrayOrder[2];
//			int nCMD = Integer.parseInt(strCMD);
			
			
//			if(strArrayOrder.length<3)
//			{
//				//CMD Error
//				logger.info("Socket状态报备接受错误命令,错误命令为：[" + orderStr +"]");
//				try {
//						out.close();
//						in.close();
//								
//				} catch (IOException e) {
//					e.printStackTrace();
//				}finally
//				{
//					try 
//					{
//						if (null != out)
//						{
//							out.close();
//						}
//						if (null != in) 
//						{
//							in.close();
//						}
//						if (null != connection) 
//						{
//						
//						}
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
//				return;
//			}
			
			
			
//			switch(nCMD)
//			{
//			case 1:
//				strfeedBack = mpsSocketDao.CMDOnline(strMac,true);
//				break;
//			case 3:
//				strfeedBack = mpsSocketDao.CMDFtpConf(strMac,true);
//				break;
//			case 4:
//				strfeedBack = mpsSocketDao.CMDFtpConf(strMac,false);
//				break;
//			case 5:
//				strfeedBack = mpsSocketDao.CMDSocketServerIP(strMac,true);
//				break;
//			case 6:
//				strfeedBack = mpsSocketDao.CMDSocketServerIP(strMac,false);
//				break;
//			case 7:
//				strfeedBack = mpsSocketDao.CMDSocketServerCMDPort(strMac);
//				break;
//			case 8:
//				strfeedBack = mpsSocketDao.CMDSocketServerTimePort(strMac);
//				break;	
//			case 9:
//				strfeedBack = mpsSocketDao.CMDSocketServerStatusPort(strMac);
//				break;
//			case 10:
//				strfeedBack = mpsSocketDao.CMDSocketServerDownloadPort(strMac);
//				break;
//			case 11:
//				strfeedBack = mpsSocketDao.CMDDeviceDownloadTime(strMac);
//				break;
//			case 12:
//				strfeedBack = mpsSocketDao.CMDDeviceOpenScreenTime(strMac);
//				break;
//			case 13:
//				strfeedBack = mpsSocketDao.CMDDeviceCloseScreenTime(strMac);
//				break;
//			case 14:
//				strfeedBack = mpsSocketDao.CMDDeviceOpenDeviceTime(strMac);
//				break;
//			case 15:
//				strfeedBack = mpsSocketDao.CMDDeviceCloseDeviceTime(strMac);
//				break;
//			case 16:
//				strfeedBack = mpsSocketDao.CMDDeviceFtpUpdateAddress(strMac);
//				break;
//			case 17:
//				strfeedBack = mpsSocketDao.CMDDeviceFtpPlaylistAddress(strMac);
//				break;
//			case 18:
//				strfeedBack = mpsSocketDao.CMDDeviceFtpMediaAddress(strMac);
//				break;
//			case 19:
//				strfeedBack = mpsSocketDao.CMDDeviceFtpLogAddress(strMac);
//				break;
//			case 23:
//				strfeedBack = mpsSocketDao.CMDOnline(strMac,false);
//				break;
//			case 24:
//				strfeedBack = mpsSocketDao.CMDResultSet(strMac);
//				break;
//			default:
//				break;
//			}
			//out.write(Encrypt.getSockEncodecrypt(strfeedBack.getBytes(),strfeedBack.length()));

		} catch (Exception e) 
		{
			logger.error("error -> ", e);
			
			logger.info(e.toString());
		}finally
		{
//			try 
//			{
//				if (null != out)
//				{
//					out.close();
//				}
//				if (null != in) 
//				{
//					in.close();
//				}
//				if (null != connection) 
//				{
//				 
//				}
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
		}
	}
	
	/**
	 * 基于类层级 -> 接收到客户端的套接字 -> 唤醒线程处理socket请求
	 * 
	 * @param requestToHandle
	 */
	public static void processRequest(DatagramSocket socket,DatagramPacket requestToHandle) 
	{
		synchronized (pool) 
		{
			pool.add(pool.size(), requestToHandle);
			
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
				connection = (DatagramPacket) pool.remove(0);
			}
			//logger.info("[" + Thread.currentThread().getName() + "]取走一个请求，请求池大小为:[" + pool.size() + "],开始处理……" );
			handleConnection();
		}
	}
}
