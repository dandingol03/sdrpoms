/**   
 * @Package: com.pc.busniess.baseInfoRail.service 
 * @author: jwl   
 * @date: 2018年9月1日 上午9:23:10 
 */
package com.pc.exhibition.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pc.bsp.common.util.ConverterUtils;
import com.pc.busniess.baseInfoDefenceBroadcast.dao.BaseInfoDefenceBroadcastDao;
import com.pc.busniess.baseInfoRail.baseInfoRailData.po.BaseInfoRailDataPo;
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
public class DaoTests {
	@Autowired
	private JUnitDao jUnitDao;
	@Autowired
	private BaseInfoRailDataService baseInfoRailDataService;
	@Autowired
	private BaseInfoDefenceBroadcastDao baseInfoDefenceBroadcastDao;
	@Test
	public void testSelect() {
		
//		List<Point> list = jUnitDao.test1(railId);
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
		
		
		
		List<Map<String, Object>> findStations = jUnitDao.findStation();
		if(findStations!=null&&findStations.size()>0){
			for(int i=0;i<findStations.size();i++){
				Map<String,Object> row=findStations.get(i);
						//车站 位置查找 '京津城际上行','京津城际下行'
						if(StringUtils.equals("京广高铁京西联上行11",row.get("rail_subname").toString())||StringUtils.equals("京广高铁京西联下行",row.get("rail_subname").toString())){
						String railId=row.get("railId").toString();
						String lng =row.get("lng").toString();
						String lat =row.get("lat").toString();
						System.err.println(railId);
						List<Map<String, String>> mapList = jUnitDao.findner(lng,lat,railId);
						System.out.println(mapList);
						//距离最近的点
						int orderNum1=ConverterUtils.toInt(mapList.get(0).get("order_number"));
						Map<String, String> map1=new HashMap<String, String>();//最近前一个
						Map<String, String> map2 = mapList.get(0);//最近
						//
						if(mapList!=null&&mapList.size()>0){
							for(int i1=0;i1<mapList.size();i1++){
								Map<String, String> row11=mapList.get(i1);
								String orderNum2= ConverterUtils.toString(orderNum1-1);
								if(orderNum2.equals(row11.get("order_number"))){
									map1.putAll(row11);
								}
							}
						}
						//1
						double xx1=ConverterUtils.toDouble(map1.get("lng").toString());
						double yy1=	ConverterUtils.toDouble(map1.get("lat").toString());
						//2
						double xx2=ConverterUtils.toDouble(map2.get("lng").toString());
						double yy2=ConverterUtils.toDouble(map2.get("lat").toString());
						//3
						double xx3=ConverterUtils.toDouble(lng);
						double yy3=ConverterUtils.toDouble(lat);
						
						BaseInfoRailDataPo pubMapRailDataPo=new BaseInfoRailDataPo();
						pubMapRailDataPo.setRailId(railId);
						pubMapRailDataPo.setLng(lng);
						pubMapRailDataPo.setLat(lat);
						pubMapRailDataPo.setKilometerMark(row.get("middle").toString());
						String theGeom="POINT("+lng+" "+lat+")";
						pubMapRailDataPo.setTheGeom(theGeom);
						
						if(((xx1-xx2)*(xx3-xx2)+(yy1-yy2)*(yy3-yy2))>0) {
							System.out.println("rui");
							orderNum1-=1;
							pubMapRailDataPo.setOrderNumber(ConverterUtils.toString(orderNum1));
							System.err.println(pubMapRailDataPo.toString());
							baseInfoRailDataService.addPubMapRailData(pubMapRailDataPo);
						}else{
							System.out.println("dun");
							orderNum1+=1;
							pubMapRailDataPo.setOrderNumber(ConverterUtils.toString(orderNum1));
							System.err.println(pubMapRailDataPo.toString());
							baseInfoRailDataService.addPubMapRailData(pubMapRailDataPo);
						}
						}
				//
			}
		}
	}
}
