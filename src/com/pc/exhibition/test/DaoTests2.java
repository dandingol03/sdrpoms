/**   
 * @Package: com.pc.busniess.baseInfoRail.service 
 * @author: jwl   
 * @date: 2018年9月1日 上午9:23:10 
 */
package com.pc.exhibition.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pc.bsp.common.util.Point;
import com.pc.busniess.baseInfoDefenceBroadcast.dao.BaseInfoDefenceBroadcastDao;
import com.pc.busniess.baseInfoRail.baseInfoRailData.service.BaseInfoRailDataService;
/**   
 * 测试类
 * @Package: com.pc.busniess.baseInfoRail.service 
 * @author: jwl   
 * @date: 2018年9月1日 上午9:23:10 
 */
@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = {"classpath*:/META-INF/jdbc-context.xml","classpath*:/META-INF/spring-security.xml","classpath*:/META-INF/servlet-context.xml"}) 
//@Transactional 
public class DaoTests2 {
	@Autowired
	private JUnitDao jUnitDao;
	@Autowired
	private BaseInfoRailDataService baseInfoRailDataService;
	@Autowired
	private BaseInfoDefenceBroadcastDao baseInfoDefenceBroadcastDao;
	@Test
	public void testSelect() {
		//stre_1531876718909 stre_1531876719422  stre_1531876719091
		String arr1[]={"stre_1531876719422"};
		for (int i = 0; i < arr1.length; i++) {
			String railid=arr1[i];
			List<Point> list = jUnitDao.test1(railid);
		}
//		System.out.println(list);
//		List<Object[]> objList = new ArrayList<Object[]>();
//		for (int i = 0; i < list.size(); i++) {
//			Point point = list.get(i);
//			Object[] obj = new Object[2];
//			obj[0] = Double.toString(point.getKm());
//			obj[1] = point.getId();
//			objList.add(obj);
//		}
//		int []a=jUnitDao.updateRailKm(objList);
//		System.out.println(Arrays.toString(a));
		
	}
}
