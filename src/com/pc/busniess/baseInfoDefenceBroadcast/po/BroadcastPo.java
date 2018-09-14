/**   
 * @Package: com.pc.busniess.baseInfoDefenceBroadcast.po 
 * @author: jwl   
 * @date: 2018年8月8日 下午3:52:24 
 */
package com.pc.busniess.baseInfoDefenceBroadcast.po;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import org.apache.commons.lang.StringUtils;

import com.pc.bsp.common.util.ConverterUtils;

/**   
 * @Package: com.pc.busniess.baseInfoDefenceBroadcast.po 
 * @author: jwl   
 * @date: 2018年8月8日 下午3:52:24 
 */
public class BroadcastPo {
	private byte[] number; 				// 广播警示柱编号
	private String status; 				// 广播警示柱状态 01 在线   02 离线 03 故障
	private String visitedTime;			//时间
	private byte[] voiceFileAddress;
	private byte[] voltage;
	private byte[] trigger;
	private String playStatus;
	private byte[] versionNumber;
	private byte[] tfCardFileTotal;
	private byte[] flashFileTotal;
	private byte[] tfCurrentTrcak;
	private byte[] flashCurrentTrcak;
	private byte[] ttsVolume;
	private byte[] activeReportInterval;
	private byte[] ledText;
	private byte[] soundSourceChannel;
	private byte[] presetVoicVolume;
	private byte[] ipAddress;
	private int port;
	/***
	 * voice_file_address 语音 文件 位置
	 * voltage 电压值
	 * trigger 触发 次数
	 * play_status 播放状态 0空闲1播放
	 * version_number 系统版本号
	 * tf_card_file_total TF卡 文件 总数
	 * flash_file_total  Flash文件 总数
	 * tf_current_trcak TF的 当前 曲目
	 * flash_current_trcak FLASH的当前曲目
	 * tts_volume TTS音量
	 * active_report_interval 主动上报时间间隔
	 * led_text LED显示文字
	 * sound_source_channel 音源 通道
	 * preset_voice_volume 预置语音音量
	 *  Arrays.toString(number);
	 */
	
	public void setNumber(byte[] number) {
		this.number = number;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public void setIpAddress(byte[] ipAddress) {
		this.ipAddress = ipAddress;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setVisitedTime(String visitedTime) {
		this.visitedTime = visitedTime;
	}
	public void setVoiceFileAddress(byte[] voiceFileAddress) {
		this.voiceFileAddress = voiceFileAddress;
	}
	public void setVoltage(byte[] voltage) {
		this.voltage = voltage;
	}
	public void setTrigger(byte[] trigger) {
		this.trigger = trigger;
	}
	public void setPlayStatus(String playStatus) {
		this.playStatus = playStatus;
	}
	public void setVersionNumber(byte[] versionNumber) {
		this.versionNumber = versionNumber;
	}
	public void setTfCardFileTotal(byte[] tfCardFileTotal) {
		this.tfCardFileTotal = tfCardFileTotal;
	}
	public void setFlashFileTotal(byte[] flashFileTotal) {
		this.flashFileTotal = flashFileTotal;
	}
	public void setTfCurrentTrcak(byte[] tfCurrentTrcak) {
		this.tfCurrentTrcak = tfCurrentTrcak;
	}
	public void setFlashCurrentTrcak(byte[] flashCurrentTrcak) {
		this.flashCurrentTrcak = flashCurrentTrcak;
	}
	public void setTtsVolume(byte[] ttsVolume) {
		this.ttsVolume = ttsVolume;
	}
	public void setActiveReportInterval(byte[] activeReportInterval) {
		this.activeReportInterval = activeReportInterval;
	}
	public void setLedText(byte[] ledText) {
		this.ledText = ledText;
	}
	public void setSoundSourceChannel(byte[] soundSourceChannel) {
		this.soundSourceChannel = soundSourceChannel;
	}
	public void setPresetVoicVolume(byte[] presetVoicVolume) {
		this.presetVoicVolume = presetVoicVolume;
	}
	public String getNumber() {
		if(number.length==0){
			return null;
		}
		return ConverterUtils.bytesToHexFun1(number);
	}
	public String getStatus() {
		return status;
	}
	public String getVisitedTime() {
		return visitedTime;
	}
	public String getVoiceFileAddress() {
		if(voiceFileAddress.length==0){
			return null;
		}
		return Arrays.toString(voiceFileAddress);
	}
	public String getVoltage() {
		if(voltage.length==0){
			return null;
		}
		return Arrays.toString(voltage);
	}
	public String getTrigger() {
		if(trigger.length==0){
			return null;
		}
		return Arrays.toString(trigger);
	}
	public String getPlayStatus() {
		return playStatus;
	}
	public String getVersionNumber() {
		if(versionNumber.length==0){
			return null;
		}
		return Arrays.toString(versionNumber);
	}
	public String getTfCardFileTotal() {
		if(tfCardFileTotal.length==0){
			return null;
		}
		return Arrays.toString(tfCardFileTotal);
	}
	public String getFlashFileTotal() {
		if(flashFileTotal.length==0){
			return null;
		}
		return Arrays.toString(flashFileTotal);
	}
	public String getTfCurrentTrcak() {
		if(tfCurrentTrcak.length==0){
			return null;
		}
		return Arrays.toString(tfCurrentTrcak);
	}
	public String getFlashCurrentTrcak() {
		if(flashCurrentTrcak.length==0){
			return null;
		}
		return Arrays.toString(flashCurrentTrcak);
	}
	public String getTtsVolume() {
		if(ttsVolume.length==0){
			return null;
		}
		return Arrays.toString(ttsVolume);
	}
	public String getActiveReportInterval() {
		if(activeReportInterval.length==0){
			return null;
		}
		return Arrays.toString(activeReportInterval);
	}
	public String getLedText() {
		if(ledText.length==0){
			return null;
		}
		try {
			return new String(ledText, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String getSoundSourceChannel() {
		if(soundSourceChannel.length==0){
			return null;
		}
		return Arrays.toString(soundSourceChannel);
	}
	public String getPresetVoicVolume() {
		if(presetVoicVolume.length==0){
			return null;
		}
		return Arrays.toString(presetVoicVolume);
	}
	public String getIpAddress() {
		if(ipAddress.length==0){
			return null;
		}
		return Arrays.toString(ipAddress);
	}
	public String getPort() {
		return ConverterUtils.toString(port);
	}
}
