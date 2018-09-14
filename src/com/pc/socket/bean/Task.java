package com.pc.socket.bean;

public class Task {
	private String columnId;
	private byte[] content;
	private byte[] command; 
	private int taskId;

	//任务id生成器
	static class IdGenerator{
		static int id;
		public static int generateId() {
			if(id<Integer.MAX_VALUE)
				return ++id;
			else
			{
				id=0;
				return ++id;
			}
		}
	}
	
	public Task() {
		taskId=IdGenerator.generateId();
	}
	
	public String getColumnId() {
		return columnId;
	}
	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	public byte[] getCommand() {
		return command;
	}
	public void setCommand(byte[] command) {
		this.command = command;
	}
	
}
