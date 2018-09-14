package com.pc.busniess.baseInfoPartTrajection.service;


import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.security.po.PubUsers;
import com.pc.busniess.baseInfoPartTrajection.dao.BaseInfoPartTrajectionDao;
import com.pc.busniess.baseInfoPartTrajection.po.BaseInfoPartTrajectionPo;
/**
 * 行人易穿行部位基本信息相关的业务逻辑
 * @author mdf
 * @version
 * @since
 *
 */
@Service("baseInfoPartTrajectionService")
public class BaseInfoPartTrajectionService {

	@Autowired
	private BaseInfoPartTrajectionDao baseInfoHiddenTrajectionDao;
	/**
	 * 根据id获取行人易穿行部位的详细信息
	 * @param id 行人易穿行部位ID
	 * @return 返回对应的行人易穿行部位的详细信息
	 * @author lxb
	 */
	public Map<String, Object> findById(String id) {
		return baseInfoHiddenTrajectionDao.findById(id);
	}
	/**
	 * 查询行人易穿行部位信息
	 * @param dgm
	 * @param baseInfoHiddenTrajectionPo
	 * @return
	 */
	public Map<String, Object> baseInfoHiddenTrajectionQueryList(DataGridModel dgm, BaseInfoPartTrajectionPo baseInfoHiddenTrajectionPo) {
		// TODO 查询行人易穿行部位信息
		//获取当前登录用户
				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				PubUsers user = (PubUsers)principal;
				String orgId = user.getUserOrg();
				
		return baseInfoHiddenTrajectionDao.baseInfoHiddenTrajectionQueryList(dgm,baseInfoHiddenTrajectionPo,orgId);
	}
	/**
	 * 添加行人易穿行部位信息
	 * @param baseInfoHiddenTrajectionPo
	 * @param objList 
	 * @return
	 */
	public int addBaseInfoHiddenTrajection(BaseInfoPartTrajectionPo baseInfoHiddenTrajectionPo, List<Object[]> objList) {
		// TODO 添加行人易穿行部位信息
		int result = baseInfoHiddenTrajectionDao.addBaseInfoHiddenTrajection(baseInfoHiddenTrajectionPo);
		
		if(objList.size() > 0){
			int[] saveCount = baseInfoHiddenTrajectionDao.saveBaseInfoHiddenTrajectionAuths(objList);
			for(int i = 0; i < saveCount.length; i ++){
				result += saveCount[i] == Statement.SUCCESS_NO_INFO ? 0 : saveCount[i];
			}
		}
		return result;
	}
	/**
	 * 修改行人易穿行部位信息
	 * @param baseInfoHiddenTrajectionPo
	 * @param objList 
	 * @return
	 */
	public int updateBaseInfoHiddenTrajection(BaseInfoPartTrajectionPo baseInfoHiddenTrajectionPo, List<Object[]> objList) {
		// TODO 修改行人易穿行部位信息
		int result = baseInfoHiddenTrajectionDao.updateBaseInfoHiddenTrajection(baseInfoHiddenTrajectionPo);
		String id=baseInfoHiddenTrajectionPo.getId();
		baseInfoHiddenTrajectionDao.deleteBaseInfoHiddenTrajectionAuths(id);
		if(objList.size() > 0){
			int[] saveCount = baseInfoHiddenTrajectionDao.saveBaseInfoHiddenTrajectionAuths(objList);
			for(int i = 0; i < saveCount.length; i ++){
				result += saveCount[i] == Statement.SUCCESS_NO_INFO ? 0 : saveCount[i];
			}
		}
		return result;
	}
	/**
	 * 删除行人易穿行部位信息
	 * @param idList
	 * @return
	 */
	public int[] deleteBaseInfoHiddenTrajection(List<String> idList) {
		// TODO 删除行人易穿行部位信息
		return baseInfoHiddenTrajectionDao.deleteBaseInfoHiddenTrajection(idList);
	}
	
}
