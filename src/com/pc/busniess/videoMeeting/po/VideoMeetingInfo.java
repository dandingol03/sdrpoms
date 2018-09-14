package com.pc.busniess.videoMeeting.po;

public class VideoMeetingInfo {

	private String id;//主键id
	private String orgId;//组织机构id
	private String orgName;//组织名称
	private String videoIp;//摄像头ip地址
	private String videoUser;//摄像头用户名
	private String videoPass;//摄像头密码
	private String videoPort;//摄像头端口号
	private String videoChannel;//摄像头通道号
	private String videoStream;//0：主码流，1：从码流1，2：从码流2，3：从码流3
	
	private String decodeIp;//解码器ip地址
	private String decodeUser;//解码器用户名
	private String decodePass;//解码器密码
	private String decodePort;//解码器端口号
	private String decodeChannel;//解码器通道号
	
	private String ismain;//释放主会场，0：否，1：是
	private String remarks;//备注
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getVideoIp() {
		return videoIp;
	}
	public void setVideoIp(String videoIp) {
		this.videoIp = videoIp;
	}
	public String getVideoUser() {
		return videoUser;
	}
	public void setVideoUser(String videoUser) {
		this.videoUser = videoUser;
	}
	public String getVideoPass() {
		return videoPass;
	}
	public void setVideoPass(String videoPass) {
		this.videoPass = videoPass;
	}
	public String getVideoPort() {
		return videoPort;
	}
	public void setVideoPort(String videoPort) {
		this.videoPort = videoPort;
	}
	public String getVideoChannel() {
		return videoChannel;
	}
	public void setVideoChannel(String videoChannel) {
		this.videoChannel = videoChannel;
	}
	public String getVideoStream() {
		return videoStream;
	}
	public void setVideoStream(String videoStream) {
		this.videoStream = videoStream;
	}
	public String getDecodeIp() {
		return decodeIp;
	}
	public void setDecodeIp(String decodeIp) {
		this.decodeIp = decodeIp;
	}
	public String getDecodeUser() {
		return decodeUser;
	}
	public void setDecodeUser(String decodeUser) {
		this.decodeUser = decodeUser;
	}
	public String getDecodePass() {
		return decodePass;
	}
	public void setDecodePass(String decodePass) {
		this.decodePass = decodePass;
	}
	public String getDecodePort() {
		return decodePort;
	}
	public void setDecodePort(String decodePort) {
		this.decodePort = decodePort;
	}
	public String getDecodeChannel() {
		return decodeChannel;
	}
	public void setDecodeChannel(String decodeChannel) {
		this.decodeChannel = decodeChannel;
	}
	public String getIsmain() {
		return ismain;
	}
	public void setIsmain(String ismain) {
		this.ismain = ismain;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
}
