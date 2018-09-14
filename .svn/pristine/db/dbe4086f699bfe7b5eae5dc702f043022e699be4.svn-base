package com.pc.busniess.baseInfoRail.service;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.NextID;
import com.pc.bsp.security.po.PubUsers;
import com.pc.busniess.baseInfoRail.dao.BaseInfoRailDao;
import com.pc.busniess.baseInfoRail.po.BaseInfoRailPo;
/**
 * 铁路
 * @author jiawenlong
 * Service
 */
@Service("baseInfoRailService")
public class BaseInfoRailService {

	@Autowired
	private BaseInfoRailDao baseInfoRailDao;
	
	//
	public List<Map<String, Object>> findRegionalRail(String point,String r) {
		// TODO Auto-generated method stub
		return baseInfoRailDao.findRegionalRail(point,r);
	}
	
	/**
	 * 获取全部铁路
	 * @param railId
	 * @return
	 */
	public List<Map<String, Object>> findAllRails() {
		// TODO Auto-generated method stub
		//获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PubUsers user = (PubUsers)principal;
		String orgId = user.getUserOrg();
		return baseInfoRailDao.findAllRails(orgId);
	}
	/**
	 * @param railId
	 * @param stream 
	 * @return
	 */
	public Map<String, Object> findById(String railId) {
		// TODO Auto-generated method stub
		return baseInfoRailDao.findById(railId);
	}
	public List<Map<String, Object>> find(String railId) {
		// TODO Auto-generated method stub
		return baseInfoRailDao.find(railId);
	}
	/**
	 * 查询铁路信息
	 * @param dgm
	 * @param baseInfoRailPo
	 * @return
	 */
	public Map<String, Object> baseInfoRailQueryList(DataGridModel dgm, BaseInfoRailPo baseInfoRailPo) {
		// TODO 查询铁路信息
	    	
	    	dgm.setOrder("asc");
	    	dgm.setSort("classify");
		return baseInfoRailDao.baseInfoRailQueryList(dgm,baseInfoRailPo);
	}
	/**
	 * 添加铁路信息
	 * @param baseInfoRailPo
	 * @return
	 */
	public int addBaseInfoRail(BaseInfoRailPo baseInfoRailPo) {
		// TODO 添加铁路信息
		String id = NextID.getNextID("rail");
		baseInfoRailPo.setId(id);
		return baseInfoRailDao.addBaseInfoRail(baseInfoRailPo);
	}
	/**
	 * 修改铁路信息
	 * @param baseInfoRailPo
	 * @return
	 */
	public int updateBaseInfoRail(BaseInfoRailPo baseInfoRailPo) {
		// TODO 修改铁路信息
		return baseInfoRailDao.updateBaseInfoRail(baseInfoRailPo);
	}
	/**
	 *  删除铁路信息
	 * @param idList
	 * @return
	 */
	public int[] deleteBaseInfoRail(List<String> idList) {
		// TODO 删除铁路信息
		return baseInfoRailDao.deleteBaseInfoRail(idList);
	}
}
