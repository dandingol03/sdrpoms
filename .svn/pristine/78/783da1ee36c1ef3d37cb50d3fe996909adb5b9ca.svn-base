package com.pc.bsp.common.util;

import javax.servlet.ServletException;

import org.springframework.http.HttpStatus;

/**
 * 自定义异常类(继承运行时异常)
 * @author AlanLee
 * @version 2016/11/26
 */
public class HttpException extends ServletException {
	public static final int CODE_400 = 400;
	public static final int CODE_404 = 404;
	public static final int CODE_500 = 500;
	public static final int CODE_435 = 435;
    private static final long serialVersionUID = 1L;

    /**
     * 错误编码
     */
    private int statusCode;

    /**
     * 消息是否为属性文件中的Key
     */
    private boolean propertiesKey = true;

    /**
     * 构造一个基本异常.
     *
     * @param message
     *            信息描述
     */
    public HttpException(String message)
    {
        super(message);
        
    }

    /**
     * 构造一个基本异常.
     *
     * @param stakeCode
     *            错误编码
     * @param message
     *            信息描述
     */
    public HttpException(int stakeCode, String message)
    {
        this(stakeCode, message, true);
    }

    /**
     * 构造一个基本异常.
     *
     * @param stakeCode
     *            错误编码
     * @param message
     *            信息描述
     */
    public HttpException(int stakeCode, String message, Throwable cause)
    {
        this(stakeCode, message, cause, true);
    }

    /**
     * 构造一个基本异常.
     *
     * @param stakeCode
     *            错误编码
     * @param message
     *            信息描述
     * @param propertiesKey
     *            消息是否为属性文件中的Key
     */
    public HttpException(int statusCode, String message, boolean propertiesKey)
    {
        super(message);
        this.statusCode = statusCode;
        this.setPropertiesKey(propertiesKey);
    }

    /**
     * 构造一个基本异常.
     *
     * @param stakeCode
     *            错误编码
     * @param message
     *            信息描述
     */
    public HttpException(int stakeCode, String message, Throwable cause, boolean propertiesKey)
    {
        super(message, cause);
        this.setPropertiesKey(propertiesKey);
    }

    /**
     * 构造一个基本异常.
     *
     * @param message
     *            信息描述
     * @param cause
     *            根异常类（可以存入任何异常）
     */
    public HttpException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public boolean isPropertiesKey()
    {
        return propertiesKey;
    }

   
	public void setPropertiesKey(boolean propertiesKey)
    {
        this.propertiesKey = propertiesKey;
    }

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	
}