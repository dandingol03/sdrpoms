package com.pc.busniess.baseInfoPartJunction.service;

import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.security.po.PubUsers;
import com.pc.busniess.baseInfoPartJunction.dao.BaseInfoPartJunctionDao;
import com.pc.busniess.baseInfoPartJunction.po.BaseInfoPartJunctionPo;
/**
 * 该Service为道口基本信息相关的业务逻辑
 * @author CaoLu
 * @version 1.0
 * @since
 * 
 */
@Service("baseInfoPartJunctionService")
public class BaseInfoPartJunctionService {
	@Autowired
	private BaseInfoPartJunctionDao baseInfoPartJunctionDao;
	
	/**
	 * 根据id获取道口的详细信息
	 * @param id 道口ID
	 * @return 返回道口的详细信息
	 * @author Caolu
	 */
	public Map<String, Object> findById(String id) {
		return baseInfoPartJunctionDao.findById(id);
	}
	
	/**
	 * 查询所有的铁路信息（参考铁路线时需要）
	 * @return
	 */
	public List<Map<String, Object>> queryRails() {
		//TODO 查询所有的铁路信息（参考铁路线时需要）
		List<Map<String, Object>> list = baseInfoPartJunctionDao.queryRails();
		return list;
	}
	
	/**
	 * 查询道口信息
	 * @param dgm
	 * @param baseInfoPartJunctionPo
	 * @return
	 */
	public Map<String, Object> baseInfoPartJunctionQueryList(DataGridModel dgm, BaseInfoPartJunctionPo baseInfoPartJunctionPo) {
		//TODO 查询道口信息
		//获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PubUsers user = (PubUsers)principal;
		String orgId = user.getUserOrg();
		return baseInfoPartJunctionDao.baseInfoPartJunctionQueryList(dgm,baseInfoPartJunctionPo,orgId);
	}

	/**
	 * 添加道口信息
	 * @param baseInfoPartJunctionPo
	 * @param objList 
	 * @return
	 */
	public int addBaseInfoPartJunction(BaseInfoPartJunctionPo baseInfoPartJunctionPo, List<Object[]> objList) {
		//TODO 添加道口信息
		int result = baseInfoPartJunctionDao.addBaseInfoPartJunction(baseInfoPartJunctionPo);
		
		if(objList.size() > 0){
			int[] saveCount = baseInfoPartJunctionDao.saveBaseInfoPartJunctionAuths(objList);
			for(int i = 0; i < saveCount.length; i ++){
				result += saveCount[i] == Statement.SUCCESS_NO_INFO ? 0 : saveCount[i];
			}
		}
		return result;
	}
	
	/**
	 * 修改道口信息
	 * @param baseInfoPartJunctionPo
	 * @param objList 
	 * @return
	 */
	public int updateBaseInfoPartJunction(BaseInfoPartJunctionPo baseInfoPartJunctionPo, List<Object[]> objList) {
		//TODO 修改道口信息
		int result = baseInfoPartJunctionDao.updateBaseInfoPartJunction(baseInfoPartJunctionPo);
		String id=baseInfoPartJunctionPo.getId();
		baseInfoPartJunctionDao.deleteBaseInfoPartJunctionAuths(id);
		if(objList.size() > 0){
			int[] saveCount = baseInfoPartJunctionDao.saveBaseInfoPartJunctionAuths(objList);
			for(int i = 0; i < saveCount.length; i ++){
				result += saveCount[i] == Statement.SUCCESS_NO_INFO ? 0 : saveCount[i];
			}
		}
		return result;
	}
	
	/**
	 * 删除道口信息
	 * @param idList
	 * @return
	 */
	public int[] deleteBaseInfoPartJunction(List<String> idList) {
		//TODO 删除道口信息
		return baseInfoPartJunctionDao.deleteBaseInfoPartJunction(idList);
	}
}
