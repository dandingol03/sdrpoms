/**   
 * @Package: com.pc.exhibition.test 
 * @author: jwl   
 * @date: 2018年9月6日 下午4:39:26 
 */
package com.pc.exhibition.test;

import java.util.List;
import java.util.Map;

import com.pc.bsp.common.util.SpringContextUtil;


/**
 * @Package: com.pc.exhibition.test
 * @author: jwl
 * @date: 2018年9月6日 下午4:39:26
 */
public class PrintStringThread implements Runnable {
	private  JUnitDao jUnitDao = (JUnitDao)SpringContextUtil.getBean("jUnitDao");
	private int num;

	private String str;


	public PrintStringThread(int num, String str) {
		this.num = num;
		this.str = str;
	}

	public void run() {
	//+baseInfoDefenceBroadcastService.findById("broa_101")
		System.out.println("线程编号：" + num + "，字符串：" + str+"--broa_101---"+jUnitDao);
		List<Map<String, Object>> findStation = jUnitDao.findStation();
		System.err.println(findStation);
	}
}