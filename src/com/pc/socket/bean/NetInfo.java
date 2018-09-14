package com.pc.socket.bean;

import java.net.InetAddress;

public class NetInfo {
	
	private InetAddress address;
	private int port;
	
	
	public InetAddress getAddress() {
		return address;
	}
	public void setAddress(InetAddress address) {
		this.address = address;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}

	
	
}
