package com.pc.busniess.baseInfoRail.baseInfoRailData.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pc.bsp.common.util.CommonUtil;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.NextID;
import com.pc.bsp.common.util.Point;
import com.pc.bsp.common.util.ProportionLngLat;
import com.pc.bsp.security.po.PubUsers;
import com.pc.busniess.baseInfoRail.baseInfoRailData.dao.BaseInfoRailDataDao;
import com.pc.busniess.baseInfoRail.baseInfoRailData.po.BaseInfoRailDataPo;
import com.pc.busniess.baseInfoRail.dao.BaseInfoRailDao;

/**
 * 铁路坐标
 * 
 * @author xb
 */
@Service("baseInfoRailDataService")
public class BaseInfoRailDataService {

    @Autowired
    private BaseInfoRailDataDao pubMapRailDataDao;

	@Autowired
	private BaseInfoRailDao baseInfoRailDao;
    /**
     * 查询铁路
     * 
     * @return
     */
    public List<Map<String, Object>> queryRails() {
	List<Map<String, Object>> list = pubMapRailDataDao.queryRails();
	return list;
    }

    /**
     * 线路查询
     * 
     * @param dgm
     * @param pubMapRailDataPo
     * @return
     */
    public Map<String, Object> pubMapRailDataQueryList(DataGridModel dgm,
	    BaseInfoRailDataPo pubMapRailDataPo) {
	// TODO Auto-generated method stub
	 //获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PubUsers user = (PubUsers)principal;
		String orgId = user.getUserOrg();
	return pubMapRailDataDao.pubMapRailDataQueryList(dgm, pubMapRailDataPo,orgId);
    }

    /**
     * 线路添加
     * 
     * @param pubMapRailDataPo
     * @return
     */
    public int addPubMapRailData(BaseInfoRailDataPo pubMapRailDataPo) {
    	int a=pubMapRailDataDao.updatePubMapRailDataOrderNumAsc(pubMapRailDataPo);
    	if(a>0){
    		String id = NextID.getNextID("mapr");
        	pubMapRailDataPo.setId(id);
        	System.err.println(id);
        	return pubMapRailDataDao.addPubMapRailData(pubMapRailDataPo);
    	}else{
    		return 0;
    	}
    }

    /**
     * 线路修改
     * 
     * @param pubMapRailDataPo
     * @return
     */
    public int updatePubMapRailData(BaseInfoRailDataPo pubMapRailDataPo) {
	// TODO Auto-generated method stub
    		List<String> idlist=new ArrayList<String>();
    		idlist.add(pubMapRailDataPo.getId());
    		int b=pubMapRailDataDao.updatePubMapRailDataOrderNumDesc(pubMapRailDataPo.getId());
    		if(b>0){
    			int[] result=pubMapRailDataDao.deletePubMapRailData(idlist);
        		if(result.length>0){
        			int a=pubMapRailDataDao.updatePubMapRailDataOrderNumAsc(pubMapRailDataPo);
        	    	if(a>0){
        	    		String id = NextID.getNextID("mapr");
        	        	pubMapRailDataPo.setId(id);
        	        	return pubMapRailDataDao.addPubMapRailData(pubMapRailDataPo);
        	    	}else{
        	    		return 0;
        	    	}
        		}else{
        			return 0;
        		}
    		}else{
    			return 0;
    		}
    }

    /**
     * 线路删除
     * 
     * @param idList
     * @return
     */
    public int[] deletePubMapRailData(List<String> idList) {
	// TODO Auto-generated method stub
    	String id=(String)idList.get(0);
    	int a=pubMapRailDataDao.updatePubMapRailDataOrderNumDesc(id);
    	if(a>0){
    		return pubMapRailDataDao.deletePubMapRailData(idList);
    	}else{
    		int[] result={};
    		return result;
    	}
    }
    /**
     *  铁路定位  km -> 经纬度
     * @param kilometerMark
     * @param baseInfoRailId
     * @return
     */
    public Map<String, Object> findLngLatKm(String baseInfoRailId,
	    String kilometerMark,String stream) {
		Map<String, Object> rail = baseInfoRailDao.findById(baseInfoRailId);
			if(rail.size()>0){
				List<Point> points = new ArrayList<>();
				List<Map<String, Object>> rowsList = pubMapRailDataDao.findLngLatKm(
					baseInfoRailId, kilometerMark,stream);
				if (rowsList != null && rowsList.size() > 0) {
				    for (int i = 0; i < rowsList.size(); i++) {
					Map<String, Object> row = rowsList.get(i);
					points.add(new Point(Double.parseDouble(row.get("lng")
						.toString()), Double.parseDouble(row.get("lat")
						.toString()), Double.parseDouble(row.get(
						"kilometerMark").toString())));
				    }
				    if(points.size()>1){
					if(points.get(0).getKm()<points.get(1).getKm()){
					    Point point = ProportionLngLat
						    .EqualProportionCalculation(points.get(0), points.get(1),
							    Double.valueOf(kilometerMark)); 
					    if (point != null) {
						rail.put("lng", point.getLng());
						rail.put("lat", point.getLat());
						rail.put("km", CommonUtil.KM2MStrbyDec(kilometerMark));
					    } 
					    return rail;
					}else if(points.get(0).getKm()>points.get(1).getKm()){
					    Point point = ProportionLngLat
						    .EqualProportionCalculation(points.get(1), points.get(0),
							    Double.valueOf(kilometerMark)); 
					    if (point != null) {
						rail.put("lng", point.getLng());
						rail.put("lat", point.getLat());
						rail.put("km", CommonUtil.KM2MStrbyDec(kilometerMark));
					    } 
					    return rail;
					}
				    }
				}
			}
			return new HashMap<String, Object>();
    }
}
