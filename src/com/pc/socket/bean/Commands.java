package com.pc.socket.bean;

import java.io.RandomAccessFile;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.pc.bsp.common.util.SpringContextUtil;
import com.pc.busniess.baseInfoDefenceBroadcast.po.BroadcastPo;
import com.pc.busniess.baseInfoDefenceBroadcast.service.BaseInfoDefenceBroadcastService;
import com.pc.socket.dao.IMpsSocketDao;
import com.pc.socket.service.MultiSocketCMDServer;
import com.pc.socket.service.handler.MultiSocketCMDHandler;

/**
 * 
 * 处理udp的命令发送和接收
 *
 */

public class Commands {
	
	
	//logger创建
	private static Logger logger = Logger.getLogger(Commands.class);
	private static BaseInfoDefenceBroadcastService baseInfoDefenceBroadcastService;
	static {
		baseInfoDefenceBroadcastService = (BaseInfoDefenceBroadcastService)SpringContextUtil.getBean("baseInfoDefenceBroadcastService");
	}
	
	public static byte[] Register = new byte[]{(byte)0xD8,(byte)0x00,(byte)0x48,(byte)0x01};//注册包
	public static byte[] Heart = new byte[]{(byte)0xC0,(byte)0x00,(byte)0x00,(byte)0x00};//心跳包
	public static byte[] AUDIO_SWITCH=new byte[]{(byte)0xff,(byte)0x01,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};//切换语音文件位置
	public static byte[] AUDIO_INQUIRE=new byte[]{(byte)0xff,(byte)0x02,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};//查询语音文件位置
	public static byte[] VOLTAGE_INQUIRE=new byte[]{(byte)0xff,(byte)0x03,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};//查询设备电压值
	public static byte[] TRIGGER_BROADCAST=new byte[]{(byte)0xff,(byte)0x04,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};//触发播报
	public static byte[] TRIGGER_COUNT_INQUIRE=new byte[]{(byte)0xff,(byte)0x05,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};//查询触发次数
	public static byte[] PLAY_STATUS_INQUIRE=new byte[]{(byte)0xff,(byte)0x06,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};//查询播放状态
	public static byte[] RESET=new byte[]{(byte)0xff,(byte)0x07,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};//系统复位
	public static byte[] RECOVER_CONFIG=new byte[]{(byte)0xff,(byte)0x08,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};//恢复出厂设置
	public static byte[] VERSION_INQUIRE=new byte[]{(byte)0xff,(byte)0x09,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};//查询系统版本
	public static byte[] TF_FILES_COUNT_INQUIRE=new byte[]{(byte)0xff,(byte)0x0a,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};//查询TF卡文件总数
	public static byte[] FLASH_FILES_COUNT_INQUIRE=new byte[]{(byte)0xff,(byte)0x0b,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};//查询Flash文件总数
	public static byte[] TF_CURRENT_SONG_INQUIRE=new byte[]{(byte)0xff,(byte)0x0c,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};//查询TF的当前曲目
	public static byte[] FLASH_CURRENT_SONG_INQUIRE=new byte[]{(byte)0xff,(byte)0x0d,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};//查询FLASH的当前曲目
	public static byte[] RESET_VOICE=new byte[]{(byte)0xff,(byte)0x0f,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};//音源选择预置语音
	public static byte[] SELECT_VOICE_TTS=new byte[]{(byte)0xff,(byte)0x10,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};//音源选择TTS
	public static byte[] SELECT_VOICE_OUTER=new byte[]{(byte)0xff,(byte)0x11,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};//音源选择外部音源输入
	public static byte[] ADD_TTS_VOLUMN=new byte[]{(byte)0xff,(byte)0x13,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};//TTS音量加
	public static byte[] MINUS_TTS_VOLUMN=new byte[]{(byte)0xff,(byte)0x14,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};//TTS音量减
	public static byte[] TTS_VOLUMN_INQUIRE=new byte[]{(byte)0xff,(byte)0x18,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};//查询TTS音量
	public static byte[] ACTIVE_REPORT_INTERNAL_INQUIRE=new byte[]{(byte)0xff,(byte)0x21,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};//查询主动上报时间间隔
	public static byte[] SET_TRIIGER_COUNT_ZERO=new byte[]{(byte)0xff,(byte)0x23,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};//将触发次数清零
	public static byte[] LED_TEXT_INQUIRE=new byte[]{(byte)0xff,(byte)0x24,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};//查询LED当前显示的文字
	public static byte[] VOICE_COMPOSE_TEXT_INQUIRE=new byte[]{(byte)0xff,(byte)0x25,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};//查询语音合成当前设置的文字
	public static byte[] VOICE_CHANNEL_INQUIRE=new byte[]{(byte)0xff,(byte)0x26,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};//查询音源通道
	public static byte[] VOICE_PRESET_VOLUMN_INQUIRE=new byte[]{(byte)0xff,(byte)0x27,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};//查询预置语音音量
	
	
	
	public static byte[] NEXT_SONG=new byte[]{(byte)0xff,(byte)0x7e,(byte)0x02,(byte)0x01,(byte)0xef};//下一曲
	public static byte[] PREV_SONG=new byte[]{(byte)0xff,(byte)0x7e,(byte)0x02,(byte)0x02,(byte)0xef};//上一曲
	public static byte[] ADD_VOLUMN=new byte[]{(byte)0xff,(byte)0x7e,(byte)0x02,(byte)0x04,(byte)0xef};//音量加
	public static byte[] MINUS_VOLUMN=new byte[]{(byte)0xff,(byte)0x7e,(byte)0x02,(byte)0x05,(byte)0xef};//音量减
	public static byte[] PLAY=new byte[]{(byte)0xff,(byte)0x7e,(byte)0x02,(byte)0x0d,(byte)0xef};//播放
	
	
	public static void sendDatagramPacket(final byte[] columnId,final byte[] command) {
		//开启线程
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					DatagramPacket packetCallback = null;
					if( MultiSocketCMDHandler.broadcastColumns!=null&&MultiSocketCMDServer.serverSocket!=null&&MultiSocketCMDServer.serverSocket.isClosed()==false)
					{
						logger.info("发送触发命令");
						//发送命令
						byte[] tmpId=new byte[] {(byte)0xd8,(byte)0x00,(byte)0x48,(byte)0x01};
						String result="";
						String matchedId= Arrays.toString(columnId);
						
						logger.info("=======summary=====");
						logger.info("command -> "+Arrays.toString(command));
						//logger.info(result);
						logger.info("the id to be matched -> "+matchedId);
						String sampleId=Arrays.toString(new byte[] {(byte)0xd8,(byte)0x00,(byte)0x48,(byte)0x01});
						logger.info("sample id -> "+sampleId);
						
						
						//查找设备
						NetInfo info = MultiSocketCMDHandler.broadcastColumns.get(matchedId);
						InetAddress address=info.getAddress();
						int port = info.getPort();
						
						//发送命令
//						packetCallback = new DatagramPacket(command, command.length, address, port);
//						MultiSocketCMDServer.serverSocket.send(packetCallback);
						if(Arrays.equals(command, Commands.TRIGGER_BROADCAST))//触发播报
						{
//							response=Commands.TRIGGER_BROADCAST;
							packetCallback = new DatagramPacket(command, command.length, address, port);
							MultiSocketCMDServer.serverSocket.send(packetCallback);
						}else if(Arrays.equals(command, Commands.TRIGGER_COUNT_INQUIRE))//查询触发次数
						{
							logger.info("发送  查询触发次数 ");
							packetCallback = new DatagramPacket(command, command.length, address, port);
							MultiSocketCMDServer.serverSocket.send(packetCallback);		
							
							
//							logger.info("test for 语音文字合成");
//							//设置音源通道为tts
//							logger.info("选择音源为tts");
//							packetCallback = new DatagramPacket(Commands.SELECT_VOICE_TTS, Commands.SELECT_VOICE_TTS.length, address, port);
//							MultiSocketCMDServer.serverSocket.send(packetCallback);	
//							logger.info("选择音源为tts结束");
							
							
							
						}else if(((command[0]&0xff)==0xff)&&((command[1]&0xff)==0x12))//发送语音合成文字
						{
							//todo:获取广播柱，是否为tts
							Map<String, Object> columnInfo= baseInfoDefenceBroadcastService.findById(columnId);
							if( columnInfo.get("soundSourceChannel")==null||
									((columnInfo.get("soundSourceChannel").toString().equals(Arrays.toString(new byte[] {0x00})))||
								(columnInfo.get("soundSourceChannel").toString().equals(Arrays.toString(new byte[] {0x02}))))
							)
							{
								//登记发送合成文本任务
								logger.info("登记   发送合成文字任务");
								Task task=new Task();
								task.setColumnId(Arrays.toString(columnId));
								task.setCommand(command);
								if(MultiSocketCMDHandler.broadcastTasks.get(Arrays.toString(columnId))!=null)
								{
									MultiSocketCMDHandler.broadcastTasks.get(Arrays.toString(columnId)).add(task);
								}else {
									List<Task> tasks=new ArrayList<>();
									tasks.add(task);
									MultiSocketCMDHandler.broadcastTasks.put(Arrays.toString(columnId), tasks);
								}
								//设置音源通道为tts
								logger.info("选择音源为tts");
								packetCallback = new DatagramPacket(Commands.SELECT_VOICE_TTS, Commands.SELECT_VOICE_TTS.length, address, port);
								MultiSocketCMDServer.serverSocket.send(packetCallback);	
							}else {
								logger.info("登记   发送合成文字任务");
								packetCallback = new DatagramPacket(command, command.length, address, port);
								MultiSocketCMDServer.serverSocket.send(packetCallback);	
							}
						}else if(Arrays.equals(command, Commands.SELECT_VOICE_TTS))//选择音源为tts
						{
							//test
							//登记发送合成文本任务
							byte[] converted="你好".getBytes("GBK");
							byte[] merge=new byte[converted.length+2];
							merge[0]=(byte)(0xff);
							merge[1]=(byte)(0x12);
							for(int i=0;i<converted.length;i++)
								merge[i+2]=converted[i];
							
							logger.info("登记   发送合成文字任务");
							Task task=new Task();
							task.setColumnId(Arrays.toString(columnId));
							task.setCommand(merge);
							if(MultiSocketCMDHandler.broadcastTasks.get(Arrays.toString(columnId))!=null)
							{
								MultiSocketCMDHandler.broadcastTasks.get(Arrays.toString(columnId)).add(task);
							}else {
								List<Task> tasks=new ArrayList<>();
								tasks.add(task);
								MultiSocketCMDHandler.broadcastTasks.put(Arrays.toString(columnId), tasks);
							}
							logger.info("选择音源为tts");
							packetCallback = new DatagramPacket(Commands.SELECT_VOICE_TTS, Commands.SELECT_VOICE_TTS.length, address, port);
							MultiSocketCMDServer.serverSocket.send(packetCallback);	
						}
								
						
						
						
						
					}else {
						return;
					}
				}catch (Exception e) {
					logger.error("error -> ", e);
				}
			}
		}).start();
	}
}
