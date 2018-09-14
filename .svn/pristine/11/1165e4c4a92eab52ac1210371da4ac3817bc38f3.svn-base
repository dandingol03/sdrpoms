package com.pc.busniess.baseInfoPartBridge.service;


import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.security.po.PubUsers;
import com.pc.busniess.baseInfoPartBridge.dao.BaseInfoPartBridgeDao;
import com.pc.busniess.baseInfoPartBridge.po.BaseInfoPartBridgePo;

/**
 * 这个service主要用来处理桥梁页面的业务逻辑
 * @author lyc
 * @version 1.0
 * @since 1.0 从1.0版本开始添加的
 */

@Service("baseInfoPartBridgeService")
public class BaseInfoPartBridgeService {
	
	@Autowired
	private BaseInfoPartBridgeDao baseInfoPartBridgeDao;
	

	/**
	 * 根据id获取<strong>桥梁</strong>的详细信息
	 * @param id 桥梁ID
	 * @return 返回对应的桥梁的详细信息
	 * @author lxb
	 */
	public Map<String, Object> findById(String id) {
		return baseInfoPartBridgeDao.findById(id);
	}
	/**
	 * 该方法是对铁路线的查询
	 * @return 集合list
	 */
	public List<Map<String, Object>> queryRails() {
		// TODO 参考铁路线
		List<Map<String, Object>> list = baseInfoPartBridgeDao.queryRails();
		return list;
	}
	/**
	 * 该方法是对桥梁信息的查询
	 * @param dgm
	 * @param baseInfoPartBridgePo
	 * @see BaseInfoPartBridgePo DataGridModel
	 * @return 桥梁dao层桥梁信息查询
	 */
	public Map<String, Object> baseInfoPartBridgeQueryList(DataGridModel dgm, BaseInfoPartBridgePo baseInfoPartBridgePo) {
		// TODO 查询桥梁信息
		//获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PubUsers user = (PubUsers)principal;
		String orgId = user.getUserOrg();
		
		return baseInfoPartBridgeDao.baseInfoPartBridgeQueryList(dgm,baseInfoPartBridgePo,orgId);
	}
	/**
	 * 该方法是增加桥梁信息
	 * @param baseInfoPartBridgePo
	 * @param objList 
	 * @see BaseInfoPartBridgePo
	 * @return 桥梁dao层添加桥梁信息
	 */
	public int addBaseInfoPartBridge(BaseInfoPartBridgePo baseInfoPartBridgePo, List<Object[]> objList) {
		// TODO 添加桥梁信息
		baseInfoPartBridgePo.setIsOversize("0");
		int result = baseInfoPartBridgeDao.addBaseInfoPartBridge(baseInfoPartBridgePo);
		
		if(objList.size() > 0){
			int[] saveCount = baseInfoPartBridgeDao.saveBaseInfoPartBridgeAuths(objList);
			for(int i = 0; i < saveCount.length; i ++){
				result += saveCount[i] == Statement.SUCCESS_NO_INFO ? 0 : saveCount[i];
			}
		}
		return result;
	}
	/**
	 * 该方法是修改桥梁信息
	 * @param baseInfoPartBridgePo
	 * @param objList 
	 * @see BaseInfoPartBridgePo
	 * @return 桥梁dao层修改桥梁信息
	 */
	public int updateBaseInfoPartBridge(BaseInfoPartBridgePo baseInfoPartBridgePo, List<Object[]> objList) {
		// TODO 修改桥梁信息
		baseInfoPartBridgePo.setIsOversize("0");
		int result = baseInfoPartBridgeDao.updateBaseInfoPartBridge(baseInfoPartBridgePo);
		String id=baseInfoPartBridgePo.getId();
		baseInfoPartBridgeDao.deleteBaseInfoPartBridgeAuths(id);
		if(objList.size() > 0){
			int[] saveCount = baseInfoPartBridgeDao.saveBaseInfoPartBridgeAuths(objList);
			for(int i = 0; i < saveCount.length; i ++){
				result += saveCount[i] == Statement.SUCCESS_NO_INFO ? 0 : saveCount[i];
			}
		}
		return result;
	}
	/**
	 * 该方法是删除桥梁信息
	 * @param idList
	 * @see List<String>
	 * @return 桥梁dao层删除桥梁信息
	 */
	public int[] deleteBaseInfoPartBridge(List<String> idList) {
		// TODO 删除桥梁信息
		return baseInfoPartBridgeDao.deleteBaseInfoPartBridge(idList);
	}

	
}
