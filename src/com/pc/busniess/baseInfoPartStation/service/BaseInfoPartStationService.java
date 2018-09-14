package com.pc.busniess.baseInfoPartStation.service;


import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.security.po.PubUsers;
import com.pc.busniess.baseInfoPartStation.dao.BaseInfoPartStationDao;
import com.pc.busniess.baseInfoPartStation.po.BaseInfoPartStationPo;
/**
 * 这个service主要用来处理车站页面的业务逻辑
 * @author lyc
 * @version 1.0
 * @since 1.0 从1.0版本开始添加的
 */
@Service("baseInfoPartStationService")
public class BaseInfoPartStationService {

	@Autowired
	private BaseInfoPartStationDao baseInfoPartStationDao;
	/**
	 * 根据id获取<strong>车站</strong>的详细信息
	 * @param id 车站ID
	 * @return 返回车站的详细信息
	 * @author lxb
	 */
	public Map<String, Object> findById(String id) {
		return baseInfoPartStationDao.findById(id);
	}
	
	/**
	 * 该方法是对铁路线的查询
	 * @return 集合list
	 */
	public List<Map<String, Object>> queryRails() {
		// TODO 参考铁路线
		List<Map<String, Object>> list = baseInfoPartStationDao.queryRails();
			return list;
	}
	/**
	 * 该方法是对车站信息的查询
	 * @param dgm
	 * @param baseInfoPartStationPo
	 * @see BaseInfoPartStationPo DataGridModel
	 * @return 车站dao层车站信息查询
	 */
	public Map<String, Object> baseInfoPartStationQueryList(DataGridModel dgm, BaseInfoPartStationPo baseInfoPartStationPo) {
		// TODO 查询车站信息
		//获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PubUsers user = (PubUsers)principal;
		String orgId = user.getUserOrg();

		return baseInfoPartStationDao.baseInfoPartStationQueryList(dgm,baseInfoPartStationPo,orgId);
	}
	/**
	 * 该方法是增加车站信息
	 * @param baseInfoPartStationPo
	 * @param objList 
	 * @see BaseInfoPartStationPo
	 * @return 车站dao层添加车站信息
	 */
	public int addBaseInfoPartStation(BaseInfoPartStationPo baseInfoPartStationPo, List<Object[]> objList) {
		// TODO 添加车站信息
		int result = baseInfoPartStationDao.addBaseInfoPartStation(baseInfoPartStationPo);
		if(objList.size() > 0){
			int[] saveCount = baseInfoPartStationDao.saveBaseInfoPartStationAuths(objList);
			for(int i = 0; i < saveCount.length; i ++){
				result += saveCount[i] == Statement.SUCCESS_NO_INFO ? 0 : saveCount[i];
			}
		}
		return result;
	}
	/**
	 * 该方法是修改车站信息
	 * @param baseInfoPartStationPo
	 * @param objList 
	 * @see BaseInfoPartStationPo 
	 * @return 车站dao层修改车站信息
	 */
	public int updateBaseInfoPartStation(BaseInfoPartStationPo baseInfoPartStationPo, List<Object[]> objList) {
		// TODO 修改车站信息
		int result = baseInfoPartStationDao.updateBaseInfoPartStation(baseInfoPartStationPo);
		String id=baseInfoPartStationPo.getId();
		baseInfoPartStationDao.deleteBaseInfoPartStationAuths(id);
		if(objList.size() > 0){
			int[] saveCount = baseInfoPartStationDao.saveBaseInfoPartStationAuths(objList);
			for(int i = 0; i < saveCount.length; i ++){
				result += saveCount[i] == Statement.SUCCESS_NO_INFO ? 0 : saveCount[i];
			}
		}
		return result;
	}
	/**
	 * 该方法是删除车站信息
	 * @param idList
	 * @see List<String>
	 * @return 车站dao层删除车站信息
	 */
	public int[] deleteBaseInfoPartStation(List<String> idList) {
		// TODO 删除车站信息
		return baseInfoPartStationDao.deleteBaseInfoPartStation(idList);
	}

}