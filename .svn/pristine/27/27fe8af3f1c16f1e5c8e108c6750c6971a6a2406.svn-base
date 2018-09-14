package com.pc.socket.service;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.pc.socket.service.handler.MultiSocketStatusConnection;
import com.pc.socket.service.handler.MultiSocketStatusHandler;

//import org.apache.log4j.Logger;

/**
 * @author D.Steven
 * @see
 * @since 2013-07-25
 */

public class MultiSocketStatusServer extends Thread 
{
	private static Logger logger = Logger.getLogger(MultiSocketStatusServer.class);
	
	private int status = 0;
	private int maxConnections;
	private int maxMapConnections;
	private int listenPort;
	private static ServerSocket serverSocket;
	
	/**
	 *	constructor
	 */
    public MultiSocketStatusServer()
    {
    	
    	super();
    	logger.info("状态报备Socket服务开始初始化……");
    	//m_staticStatuslogger.info("状态报备Socket服务开始初始化...");
        this.start();
        
    }
    
    /**
     * 获取线程状态
     * @return Returns the status.
     */
    public int getStatus() 
    {
        return status;
    }
    
    /**
     * 设置线程状态
     * @param status
     */
    public synchronized void setStatus(int status)
    {
    	this.status = status;
        if(status==1)
        {
            this.notify();
        }
    }
    
    /**
     * 初始化
     */
    public void init(int maxCons,int port,int MaxMapCons)
    {
    	
    	this.listenPort = port;
        this.maxConnections = maxCons;
        this.maxMapConnections = MaxMapCons;
        SetUpConnection();
        setUphandles();
        
        try{
        	serverSocket = new ServerSocket(this.listenPort);
        	logger.info("状态报备Socket服务启动完成,等待接收请求...");
        	//m_staticStatuslogger.info("状态报备Socket服务启动完成,等待接收请求...");
        	
        }catch (BindException e){
    		e.printStackTrace();
    	}catch (IOException e1){
    		e1.printStackTrace();
    	} 
    }
    
    /**
     * run方法
     */
    public synchronized void run()
    {
    	
    	while(true){
    		if (status == 0) 
    		{
                try {
                    this.wait();
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
    		try{
    			
    			Socket inComingSocket = serverSocket.accept();
    			//Socket关闭之后300秒强制释放资源(D.Steven2013-07-27 增加)
    			inComingSocket.setSoLinger(true, 1);   	
    			
    			inComingSocket.setKeepAlive(true);
    			handleConnection(inComingSocket);
    		}catch (IOException e1)
    		{
        		e1.printStackTrace();
    			status = 0;
        	}
    	}
    }
    
    /**
     * 处理客户端请求
     * @param connectionToHandle
     */
    protected void handleConnection(Socket connectionToHandle)
    {
    	MultiSocketStatusHandler.processRequest(connectionToHandle);
    }
    
    /**
     * 设置并初始化线程池
     */
    public void setUphandles()
    {
    	for(int i = 0; i < this.maxConnections; i ++)
    	{
    		MultiSocketStatusHandler currentHandler = new MultiSocketStatusHandler();
    		new Thread(currentHandler,"StatusSocketStatusHandler"+i).start();
    		logger.info("StatusSocketStatusHandler"+i);
    	}
    }
    
    /**
     * 设置并初始化Connection线程池
     */
    public void SetUpConnection()
    {
    	for(int i=0;i<this.maxMapConnections;i++)
    	{
    		MultiSocketStatusConnection currentConnectioner = new MultiSocketStatusConnection();
    		new Thread(currentConnectioner,"StatusSocketStatusConnectioner"+i).start();
    		logger.info("StatusSocketStatusConnectioner"+i);
    	}
    }
    /**
     * 关闭socket连接
     */
    public void closeSocketServer()
    {

    	try{
    		serverSocket.close();
    	}catch (IOException e)
    	{
    		e.printStackTrace();
    	}
    }
}
