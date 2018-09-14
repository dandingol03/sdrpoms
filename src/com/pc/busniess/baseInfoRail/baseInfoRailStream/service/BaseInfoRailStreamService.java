package com.pc.busniess.baseInfoRail.baseInfoRailStream.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.busniess.baseInfoRail.baseInfoRailData.po.BaseInfoRailDataPo;
import com.pc.busniess.baseInfoRail.baseInfoRailStream.dao.BaseInfoRailStreamDao;

/**
 * 铁路坐标
 * 
 * @author xb
 */
@Service("baseInfoRailStreamService")
public class BaseInfoRailStreamService {

    @Autowired
    private BaseInfoRailStreamDao baseInfoRailStreamDao;
    
	public List<Map<String, Object>>  findById(String railId) {
		// TODO Auto-generated method stub
		return baseInfoRailStreamDao.findById(railId);
	}
	/**
	 * @param dgm
	 * @param pubMapRailDataPo
	 * @return
	 */
	public Map<String, Object> baseInfoRailStreamQueryList(DataGridModel dgm,
			BaseInfoRailDataPo pubMapRailDataPo) {
		// TODO Auto-generated method stub
		return baseInfoRailStreamDao.baseInfoRailStreamQueryList(dgm,pubMapRailDataPo);
	}
}
