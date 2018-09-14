package com.pc.busniess.baseInfoDefencePoliceStation.service;

import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.security.po.PubUsers;
import com.pc.busniess.baseInfoDefencePoliceStation.dao.BaseInfoPoliceStationDao;
import com.pc.busniess.baseInfoDefencePoliceStation.po.BaseInfoPoliceStationPo;
@Service("baseInfoPoliceStationService")
public class BaseInfoPoliceStationService {

	@Autowired
	private BaseInfoPoliceStationDao baseInfoPoliceStationDao;
	
	/**
	 * 根据id获取<strong>警务站</strong>的详细信息
	 * @param id 警务站ID
	 * @return 返回对应的警务站的详细信息
	 * @author lxb
	 */
	public Map<String, Object> findById(String id) {
		return baseInfoPoliceStationDao.findById(id);
	}
	/**
	 * 查询警务站信息
	 * @param dgm
	 * @param baseInfoPoliceStationPo
	 * @return
	 */
	public Map<String, Object> baseInfoPoliceStationQueryList(DataGridModel dgm, BaseInfoPoliceStationPo baseInfoPoliceStationPo) {
		// TODO 查询警务站信息
		//获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PubUsers user = (PubUsers)principal;
		String orgId = user.getUserOrg();
		
		return baseInfoPoliceStationDao.baseInfoPoliceStationQueryList(dgm,baseInfoPoliceStationPo,orgId);
	}
	/**
	 * 添加警务站信息
	 * @param baseInfoPoliceStationPo
	 * @param objList 
	 * @return
	 */
	public int addBaseInfoPoliceStation(BaseInfoPoliceStationPo baseInfoPoliceStationPo, List<Object[]> objList) {
		// TODO 添加警务站信息
		int result = baseInfoPoliceStationDao.addBaseInfoPoliceStation(baseInfoPoliceStationPo);
		if(objList.size() > 0){
			int[] saveCount = baseInfoPoliceStationDao.saveBaseInfoPoliceStationAuths(objList);
			for(int i = 0; i < saveCount.length; i ++){
				result += saveCount[i] == Statement.SUCCESS_NO_INFO ? 0 : saveCount[i];
			}
		}
		return result;
	}
	/**
	 *  修改警务站信息
	 * @param baseInfoPoliceStationPo
	 * @param objList 
	 * @return
	 */
	public int updateBaseInfoPoliceStation(BaseInfoPoliceStationPo baseInfoPoliceStationPo, List<Object[]> objList) {
		// TODO 修改警务站信息
		int result = baseInfoPoliceStationDao.updateBaseInfoPoliceStation(baseInfoPoliceStationPo);
		String id=baseInfoPoliceStationPo.getId();
		baseInfoPoliceStationDao.deleteBaseInfoPoliceStationAuths(id);
		if(objList.size() > 0){
			int[] saveCount = baseInfoPoliceStationDao.saveBaseInfoPoliceStationAuths(objList);
			for(int i = 0; i < saveCount.length; i ++){
				result += saveCount[i] == Statement.SUCCESS_NO_INFO ? 0 : saveCount[i];
			}
		}
		return result;
	}
	/**
	 * 删除警务站信息
	 * @param idList
	 * @return
	 */
	public int[] deleteBaseInfoPoliceStation(List<String> idList) {
		// TODO 删除警务站信息
		return baseInfoPoliceStationDao.deleteBaseInfoPoliceStation(idList);
	}
}
