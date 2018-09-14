package com.pc.busniess.baseInfoPartCulvert.service;


import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.security.po.PubUsers;
import com.pc.busniess.baseInfoPartCulvert.dao.BaseInfoPartCulvertDao;
import com.pc.busniess.baseInfoPartCulvert.po.BaseInfoPartCulvertPo;
/**
 * 该Service为涵洞基本信息相关的业务逻辑
 * @author SSY
 * @version 1.0
 */
@Service("baseInfoPartCulvertService")
public class BaseInfoPartCulvertService {

	@Autowired
	private BaseInfoPartCulvertDao baseInfoPartCulvertDao;
	
	/**
	 * 根据id获取<strong>涵洞</strong>的详细信息
	 * @param id 涵洞ID
	 * @return 返回对应的涵洞的详细信息
	 * @author lxb
	 */
	public Map<String, Object> findById(String id) {
		return baseInfoPartCulvertDao.findById(id);
	}
	
	/**
	 * 查询所有的涵洞基本信息（参考涵洞基本线时需要）
	 * @return 返回查询到的铁路结果集
	 */
	public List<Map<String, Object>> queryRails() {
		// TODO 查询所有的涵洞基本信息（参考涵洞基本线时需要）
		List<Map<String, Object>> list = baseInfoPartCulvertDao.queryRails();
		return list;
	}

	/**
	 * 查询涵洞基本信息
	 * @param dgm DataGird表单查询结构体 @see DataGridModel
	 * @param baseInfoPartCulvertPo 涵洞PO @see BaseInfoPartCulvertPo
	 * @return 结果map 分为total 总数 rows 类型为ArrayList<Map<String,Object>的队列， 其中map结构为PO的索引
	 */
	public Map<String, Object> baseInfoPartCulvertQueryList(DataGridModel dgm, BaseInfoPartCulvertPo baseInfoPartCulvertPo) {
		// TODO 查询涵洞基本信息
		//获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PubUsers user = (PubUsers)principal;
		String orgId = user.getUserOrg();
		
		return baseInfoPartCulvertDao.baseInfoPartCulvertQueryList(dgm,baseInfoPartCulvertPo,orgId);
	}

	/**
	 * 添加涵洞基本信息
	 * @param baseInfoPartCulvertPo 涵洞PO @see BaseInfoPartCulvertPo
	 * @param objList 
	 * @return 
	 */
	public int addBaseInfoPartCulvert(BaseInfoPartCulvertPo baseInfoPartCulvertPo, List<Object[]> objList) {
		// TODO 添加涵洞基本信息 
		int result = baseInfoPartCulvertDao.addBaseInfoPartCulvert(baseInfoPartCulvertPo);
		
		if(objList.size() > 0){
			int[] saveCount = baseInfoPartCulvertDao.saveBaseInfoPartCulvertAuths(objList);
			for(int i = 0; i < saveCount.length; i ++){
				result += saveCount[i] == Statement.SUCCESS_NO_INFO ? 0 : saveCount[i];
			}
		}
		return result;
	}
	
	/**
	 * 更新涵洞基本信息
	 * @param baseInfoPartCulvertPo 涵洞PO @see baseInfoPartCulvertPo
	 * @param objList 
	 * @return
	 */
	public int updateBaseInfoPartCulvert(BaseInfoPartCulvertPo baseInfoPartCulvertPo, List<Object[]> objList) {
		// TODO 更新涵洞基本信息 
		int result = baseInfoPartCulvertDao.updateBaseInfoPartCulvert(baseInfoPartCulvertPo);
		String id=baseInfoPartCulvertPo.getId();
		baseInfoPartCulvertDao.deleteBaseInfoPartCulvertAuths(id);
		if(objList.size() > 0){
			int[] saveCount = baseInfoPartCulvertDao.saveBaseInfoPartCulvertAuths(objList);
			for(int i = 0; i < saveCount.length; i ++){
				result += saveCount[i] == Statement.SUCCESS_NO_INFO ? 0 : saveCount[i];
			}
		}
		return result;
	}
	
	/**
	 * 删除涵洞基本信息
	 * @param idList 批量删除集合id
	 * @return
	 */
	public int[] deleteBaseInfoPartCulvert(List<String> idList) {
		// TODO 删除涵洞基本信息
		return baseInfoPartCulvertDao.deleteBaseInfoPartCulvert(idList);
	}
}
