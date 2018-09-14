package com.pc.busniess.baseInfoDefencePoliceHouse.service;

import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.security.po.PubUsers;
import com.pc.busniess.baseInfoDefencePoliceHouse.dao.BaseInfoDefencePoliceHouseDao;
import com.pc.busniess.baseInfoDefencePoliceHouse.po.BaseInfoDefencePoliceHousePo;
@Service("baseInfoDefencePoliceHouseService")
public class BaseInfoDefencePoliceHouseService {

	@Autowired
	private BaseInfoDefencePoliceHouseDao baseInfoDefencePoliceHouseDao;
	
	/**
	 * 根据id获取<strong>派出所</strong>的详细信息
	 * @param id 派出所ID
	 * @return 返回对应的派出所的详细信息
	 * @author lxb
	 */
	public Map<String, Object> findById(String id) {
		return baseInfoDefencePoliceHouseDao.findById(id);
	}
	/**
	 * 查询派出所信息
	 * @param dgm
	 * @param baseInfoDefencePoliceHousePo
	 * @return
	 */
	public Map<String, Object> baseInfoDefencePoliceHouseQueryList(DataGridModel dgm, BaseInfoDefencePoliceHousePo baseInfoDefencePoliceHousePo) {
		// TODO 查询派出所信息
		//获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PubUsers user = (PubUsers)principal;
		String orgId = user.getUserOrg();
		
		return baseInfoDefencePoliceHouseDao.baseInfoDefencePoliceHouseQueryList(dgm,baseInfoDefencePoliceHousePo,orgId);
	}
	/**
	 * 添加派出所信息
	 * @param baseInfoDefencePoliceHousePo
	 * @param objList 
	 * @return
	 */
	public int addBaseInfoDefencePoliceHouse(BaseInfoDefencePoliceHousePo baseInfoDefencePoliceHousePo, List<Object[]> objList) {
		// TODO 添加派出所信息
		int result = baseInfoDefencePoliceHouseDao.addBaseInfoDefencePoliceHouse(baseInfoDefencePoliceHousePo);

		if(objList.size() > 0){
			System.err.println(objList);
			int[] saveCount = baseInfoDefencePoliceHouseDao.saveBaseInfoDefencePoliceHouseAuths(objList);
			for(int i = 0; i < saveCount.length; i ++){
				result += saveCount[i] == Statement.SUCCESS_NO_INFO ? 0 : saveCount[i];
			}
		}
		return result;
	}
	/**
	 *  修改派出所信息
	 * @param baseInfoDefencePoliceHousePo
	 * @param objList 
	 * @return
	 */
	public int updateBaseInfoDefencePoliceHouse(BaseInfoDefencePoliceHousePo baseInfoDefencePoliceHousePo, List<Object[]> objList) {
		// TODO 修改派出所信息
		int result = baseInfoDefencePoliceHouseDao.updateBaseInfoDefencePoliceHouse(baseInfoDefencePoliceHousePo);
		String id=baseInfoDefencePoliceHousePo.getId();
		baseInfoDefencePoliceHouseDao.deleteBaseInfoDefencePoliceHouseAuths(id);
		if(objList.size() > 0){
			int[] saveCount = baseInfoDefencePoliceHouseDao.saveBaseInfoDefencePoliceHouseAuths(objList);
			for(int i = 0; i < saveCount.length; i ++){
				result += saveCount[i] == Statement.SUCCESS_NO_INFO ? 0 : saveCount[i];
			}
		}
		return result;
	}
	/**
	 * 删除派出所信息
	 * @param idList
	 * @return
	 */
	public int[] deleteBaseInfoDefencePoliceHouse(List<String> idList) {
		// TODO 删除派出所信息
		return baseInfoDefencePoliceHouseDao.deleteBaseInfoDefencePoliceHouse(idList);
	}
}
