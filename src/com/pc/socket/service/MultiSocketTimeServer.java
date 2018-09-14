package com.pc.socket.service;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.pc.socket.service.handler.MultiSocketTimeHandler;

/**
 * @author D.Steven
 * @see
 * @since 2011-10-27
 */

public class MultiSocketTimeServer extends Thread
{
	private static Logger logger = Logger.getLogger(MultiSocketTimeServer.class);
	
	private int status = 0;
	private int maxConnections;
	private int listenPort;
	private static ServerSocket serverSocket;
	
	/**
	 *	constructor
	 */
    public MultiSocketTimeServer() {
    	
    	super();
    	logger.info("时间升级控制Socket服务开始初始化……");
        this.start();
        
    }
    
    /**
     * 获取线程状态
     * @return Returns the status.
     */
    public int getStatus() {
        return status;
    }

    /**
     * 设置线程状态
     * @param status
     */
    public synchronized void setStatus(int status) {
    	this.status = status;
        if(status==1){
            this.notify();
        }
    }
    
    /**
     * 初始化
     */
    public void init(int maxCons,int port){
    	
    	this.listenPort = port;
        this.maxConnections = maxCons;
        
        setUphandles();
        
        try{
        	
        	serverSocket = new ServerSocket(this.listenPort);
        	logger.info("时间升级控制Socket服务启动完成,等待接收请求");
        	
        }catch (BindException e){
    		e.printStackTrace();
    	}catch (IOException e1){
    		e1.printStackTrace();
    	}
        
        
    }
    
    /**
     * run方法
     */
    public synchronized void run(){
    	
    	while(true){
    		if (status == 0) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

    		try{
    			
    			Socket inComingSocket = serverSocket.accept();
    			
    			//Socket关闭之后300秒强制释放资源(D.Steven2013-07-27 增加)
    			inComingSocket.setSoLinger(true, 300000);  
    			
    			handleConnection(inComingSocket);
    			
    			
    		}catch (IOException e1){
        		e1.printStackTrace();
    			status = 0;
        	}
    	}
    	
    }
    
    /**
     * 处理客户端请求
     * @param connectionToHandle
     */
    protected void handleConnection(Socket connectionToHandle){
    	MultiSocketTimeHandler.processRequest(connectionToHandle);
    }
    
    /**
     * 设置并初始化线程池
     */
    public void setUphandles(){
    	for(int i = 0; i < this.maxConnections; i ++){
    		MultiSocketTimeHandler currentHandler = new MultiSocketTimeHandler();
    		new Thread(currentHandler,"TimeHandler"+i).start();
    		logger.info("时间升级Socket服务线程TimeHandler["+i+"]启动!");
    	}
    }
    
    /**
     * 关闭socket连接
     */
    public void closeSocketServer(){

    	try{

    		serverSocket.close();
    		
    	}catch (IOException e){
    		e.printStackTrace();
    	}
    }
}
