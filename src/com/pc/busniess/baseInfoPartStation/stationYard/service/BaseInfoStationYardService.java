package com.pc.busniess.baseInfoPartStation.stationYard.service;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.NextID;
import com.pc.bsp.security.po.PubUsers;
import com.pc.busniess.baseInfoPartStation.stationYard.dao.BaseInfoStationYardDao;
import com.pc.busniess.baseInfoPartStation.stationYard.po.BaseInfoStationYardPo;
/**
 * 
* @author jwl E-mail:1183011789@qq.com
* @version 创建时间：2017年12月25日 上午10:24:07
* @version 1.0 
* @since 1.0
* 类说明
 */
@Service("baseInfoStationYardService")
public class BaseInfoStationYardService {

	@Autowired
	private BaseInfoStationYardDao baseInfoStationYardDao;
	
	/**
	 * 根据id获取<strong>站场</strong>的详细信息
	 * @param id 车站ID
	 * @return 返回车站的详细信息
	 * @author lxb
	 */
	public Map<String, Object> findById(String id) {
		return baseInfoStationYardDao.findById(id);
	}
	/**
	 * 该方法是对站场信息的查询
	 * @param dgm
	 * @param baseInfoStationYardPo
	 * @see baseInfoStationYardPo DataGridModel
	 * @return 站场dao层站场信息查询
	 */
	public Map<String, Object> baseInfoStationYardQueryList(DataGridModel dgm, BaseInfoStationYardPo baseInfoStationYardPo) {
		// TODO 查询站场信息
	    		//获取当前登录用户
	  		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	  		PubUsers user = (PubUsers)principal;
	  		String orgId = user.getUserOrg();
		return baseInfoStationYardDao.baseInfoStationYardQueryList(dgm,baseInfoStationYardPo,orgId);
	}
	/**
	 * 该方法是增加站场信息
	 * @param baseInfoStationYardPo
	 * @see baseInfoStationYardPo
	 * @return 站场dao层添加站场信息
	 */
	public int addBaseInfoStationYard(BaseInfoStationYardPo baseInfoStationYardPo) {
		// TODO 添加站场信息
		String id = NextID.getNextID("staY");
		baseInfoStationYardPo.setId(id);
		return baseInfoStationYardDao.addBaseInfoStationYard(baseInfoStationYardPo);
	}
	/**
	 * 该方法是修改站场信息
	 * @param baseInfoStationYardPo
	 * @see baseInfoStationYardPo 
	 * @return 站场dao层修改站场信息
	 */
	public int updateBaseInfoStationYard(BaseInfoStationYardPo baseInfoStationYardPo) {
		// TODO 修改站场信息
		return baseInfoStationYardDao.updateBaseInfoStationYard(baseInfoStationYardPo);
	}
	/**
	 * 该方法是删除站场信息
	 * @param idList
	 * @see List<String>
	 * @return 站场dao层删除站场信息
	 */
	public int[] deleteBaseInfoStationYard(List<String> idList) {
		// TODO 删除站场信息
		return baseInfoStationYardDao.deleteBaseInfoStationYard(idList);
	}
}