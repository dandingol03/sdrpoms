package com.pc.busniess.baseInfoKeyperson.service;
/**
 * @author lyf
 *
 */
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.security.po.PubUsers;
import com.pc.busniess.baseInfoKeyperson.dao.BaseInfoKeypersonDao;
import com.pc.busniess.baseInfoKeyperson.po.BaseInfoKeypersonPo;

@Service("baseInfoKeypersonService")
public class BaseInfoKeypersonService {
	@Autowired
	private BaseInfoKeypersonDao baseInfoKeypersonDao;
	/**
	 * 根据id获取<strong>关键人</strong>的详细信息
	 * @param id 关键人ID
	 * @return 返回对应的关键人的详细信息
	 * @author lyf
	 */
	public Map<String, Object> findById(String id) {
		return baseInfoKeypersonDao.findById(id);
	}
	public Map<String, Object> baseInfoKeypersonQueryList(DataGridModel dgm, BaseInfoKeypersonPo baseInfoKeypersonPo) {
		/**
		 *  TODO
		 *  查询重点人信息
		 */
		//获取当前登录用户
				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				PubUsers user = (PubUsers)principal;
				String orgId = user.getUserOrg();
				
		return baseInfoKeypersonDao.baseInfoKeypersonQueryList(dgm,baseInfoKeypersonPo,orgId);
	}

	public int addBaseInfoKeyperson(BaseInfoKeypersonPo baseInfoKeypersonPo, List<Object[]> objList) {
		//TODO 添加重点人信息
		int result = baseInfoKeypersonDao.addBaseInfoKeyperson(baseInfoKeypersonPo);
		
		if(objList.size() > 0){
			int[] saveCount = baseInfoKeypersonDao.saveBaseInfoKeypersonAuths(objList);
			for(int i = 0; i < saveCount.length; i ++){
				result += saveCount[i] == Statement.SUCCESS_NO_INFO ? 0 : saveCount[i];
			}
		}
		return result;
	}
	
	public int updateBaseInfoKeyperson(BaseInfoKeypersonPo baseInfoKeypersonPo, List<Object[]> objList) {
		//TODO 修改重点人信息
		int result = baseInfoKeypersonDao.updateBaseInfoKeyperson(baseInfoKeypersonPo);
		String id=baseInfoKeypersonPo.getId();
		baseInfoKeypersonDao.deleteBaseInfoKeypersonAuths(id);
		if(objList.size() > 0){
			int[] saveCount = baseInfoKeypersonDao.saveBaseInfoKeypersonAuths(objList);
			for(int i = 0; i < saveCount.length; i ++){
				result += saveCount[i] == Statement.SUCCESS_NO_INFO ? 0 : saveCount[i];
			}
		}
		return result;
	}
	
	public int[] deleteBaseInfoKeyperson(List<String> idList) {
		//TODO 删除重点人信息
		return baseInfoKeypersonDao.deleteBaseInfoKeyperson(idList);
	}
	

}
