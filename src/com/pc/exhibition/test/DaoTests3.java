/**   
 * @Package: com.pc.busniess.baseInfoRail.service 
 * @author: jwl   
 * @date: 2018年9月1日 上午9:23:10 
 */
package com.pc.exhibition.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.pc.bsp.common.util.SpringContextUtil;

/**   
 * 测试类
 * @Package: com.pc.busniess.baseInfoRail.service 
 * @author: jwl   
 * @date: 2018年9月1日 上午9:23:10 
 */
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = {"classpath*:/META-INF/jdbc-context.xml","classpath*:/META-INF/spring-security.xml","classpath*:/META-INF/servlet-context.xml"}) 
//@Transactional 
public class DaoTests3{
	@Autowired
	private JUnitDao jUnitDao;
	@Test
	public void testSelect() {
//		System.out.println("---------"+Thread.currentThread());
//		System.out.println("jUnitDao.findStation()---"+jUnitDao.findStation());
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				List<Map<String, Object>> findStation = jUnitDao.findStation();
//					System.out.println("++++++++++++++++++++++"+findStation);	
//				
//			}
//		}).start();
		
		List<Map<String, String>> mapList = jUnitDao.findner("116.3274385","39.82930854","stre_1531876721745");
		System.out.println(mapList);
		
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5, 300,
//                TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(3),
//                new ThreadPoolExecutor.CallerRunsPolicy());
//        executor.execute(new PrintStringThread(1,"1"));
//        executor.execute(new PrintStringThread(2,"2"));
//        executor.execute(new PrintStringThread(3,"3"));
//        executor.shutdown();
	}
}
