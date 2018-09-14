package com.pc.socket.service.thread;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.pc.bsp.common.util.SpringContextUtil;
import com.pc.busniess.baseInfoDefenceBroadcast.po.BroadcastPo;
import com.pc.busniess.baseInfoDefenceBroadcast.service.BaseInfoDefenceBroadcastService;
import com.pc.socket.bean.Commands;
import com.pc.socket.bean.NetInfo;
import com.pc.socket.service.MultiSocketCMDServer;
import com.pc.socket.service.handler.MultiSocketCMDHandler;


public class ScanBroadcast extends Thread {

	//logger创建
	private static Logger logger = Logger.getLogger(Commands.class);
	private static BaseInfoDefenceBroadcastService baseInfoDefenceBroadcastService;
	static {
		baseInfoDefenceBroadcastService = (BaseInfoDefenceBroadcastService)SpringContextUtil.getBean("baseInfoDefenceBroadcastService");
	}
	
	@Override
	public void run() {
		try {
			while(true)
			{
				//如果已连接的广播柱不为控，并且服务端socket没有关闭
				if( MultiSocketCMDHandler.broadcastColumns!=null&&MultiSocketCMDServer.serverSocket!=null&&MultiSocketCMDServer.serverSocket.isClosed()==false)
				{
					List<Map<String,Object>> list= baseInfoDefenceBroadcastService.baseInfoDefenceBroadcastQueryList();
					
					for(int i=0;i<list.size();i++)
					{
						Map info=list.get(i);
						DatagramPacket packetCallback = null;
						NetInfo netInfo =new NetInfo();
						InetAddress address=InetAddress.getByName(info.get("ip").toString());
						int port = Integer.valueOf(info.get("port").toString());
						//发送查询播放的包
						packetCallback = new DatagramPacket(Commands.PLAY_STATUS_INQUIRE, Commands.PLAY_STATUS_INQUIRE.length, address, port);
						MultiSocketCMDServer.serverSocket.send(packetCallback);
					}
					
				}
				Thread.sleep(2000);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
