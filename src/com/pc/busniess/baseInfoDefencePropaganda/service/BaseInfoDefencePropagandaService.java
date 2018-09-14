package com.pc.busniess.baseInfoDefencePropaganda.service;

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
import com.pc.busniess.baseInfoDefencePropaganda.dao.BaseInfoDefencePropagandaDao;
import com.pc.busniess.baseInfoDefencePropaganda.po.BaseInfoDefencePropagandaPo;

@Service("baseInfoDefencePropagandaService")
public class BaseInfoDefencePropagandaService {

	@Autowired
	private BaseInfoDefencePropagandaDao baseInfoDefencePropagandaDao;
	
	/**
	 * 根据id获取<strong>护路宣传点</strong>的详细信息
	 * @param id 护路宣传点ID
	 * @return 返回对应护路宣传点的详细信息
	 * 
	 */
	public Map<String, Object> findById(String id) {
		return baseInfoDefencePropagandaDao.findById(id);
	}
	
	public Map<String, Object> baseInfoDefencePropagandaQueryList(DataGridModel dgm, BaseInfoDefencePropagandaPo baseInfoDefencePropagandaPo) {
		/**
		 * TODO
		 * 查询护路宣传点信息
		 */
		 //获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PubUsers user = (PubUsers)principal;
		String orgId = user.getUserOrg();
		return baseInfoDefencePropagandaDao.baseInfoDefencePropagandaQueryList(dgm,baseInfoDefencePropagandaPo,orgId);
	}

	public int addBaseInfoDefencePropaganda(BaseInfoDefencePropagandaPo baseInfoDefencePropagandaPo, List<Object[]> objList) {
		/**
		 * TODO
		 * 添加护路宣传点信息
		 */
		int result = baseInfoDefencePropagandaDao.addBaseInfoDefencePropaganda(baseInfoDefencePropagandaPo);

		if(objList.size() > 0){
			int[] saveCount = baseInfoDefencePropagandaDao.saveBaseInfoDefencePropagandaAuths(objList);
			for(int i = 0; i < saveCount.length; i ++){
				result += saveCount[i] == Statement.SUCCESS_NO_INFO ? 0 : saveCount[i];
			}
		}
		return result;
	}
	
	public int updateBaseInfoDefencePropaganda(BaseInfoDefencePropagandaPo baseInfoDefencePropagandaPo, List<Object[]> objList) {
		/**
		 * TODO
		 * 修改护路宣传点信息
		 */
		int result = baseInfoDefencePropagandaDao.updateBaseInfoDefencePropaganda(baseInfoDefencePropagandaPo);
		String id=baseInfoDefencePropagandaPo.getId();
		baseInfoDefencePropagandaDao.deleteBaseInfoDefencePropagandaAuths(id);
		if(objList.size() > 0){
			int[] saveCount = baseInfoDefencePropagandaDao.saveBaseInfoDefencePropagandaAuths(objList);
			for(int i = 0; i < saveCount.length; i ++){
				result += saveCount[i] == Statement.SUCCESS_NO_INFO ? 0 : saveCount[i];
			}
		}
		return result;
	}
	
	public int[] deleteBaseInfoDefencePropaganda(List<String> idList) {
		/**
		 * TODO
		 * 删除护路宣传点信息
		 */
		return baseInfoDefencePropagandaDao.deleteBaseInfoDefencePropaganda(idList);
	}

}
