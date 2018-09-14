package com.pc.busniess.baseInfoPartTunnel.service;

import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.security.po.PubUsers;
import com.pc.busniess.baseInfoPartTunnel.dao.BaseInfoPartTunnelDao;
import com.pc.busniess.baseInfoPartTunnel.po.BaseInfoPartTunnelPo;
/**
 * 该Service为隧道基本信息相关的业务逻辑
 * @author CaoLu
 * @version
 * @since
 *
 */
@Service("baseInfoPartTunnelService")
public class BaseInfoPartTunnelService {
	@Autowired
	private BaseInfoPartTunnelDao baseInfoPartTunnelDao;
	
	/**
	 * 根据id获取隧道的详细信息
	 * @param id 隧道ID
	 * @return 返回隧道的详细信息
	 * @author Caolu
	 */
	public Map<String, Object> findById(String id) {
		return baseInfoPartTunnelDao.findById(id);
	}
	
	/**
	 * 查询所有的铁路信息（参考铁路线时需要）
	 * @return
	 */
	public List<Map<String, Object>> queryRails() {
		//TODO 查询所有的铁路信息（参考铁路线时需要）
		List<Map<String, Object>> list = baseInfoPartTunnelDao.queryRails();
		return list;
	}
	
	/**
	 * 查询隧道信息
	 * @param dgm
	 * @param baseInfoPartTunnelPo
	 * @return
	 */
	public Map<String, Object> baseInfoPartTunnelQueryList(DataGridModel dgm, BaseInfoPartTunnelPo baseInfoPartTunnelPo) {
		//TODO 查询隧道信息
		//获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PubUsers user = (PubUsers)principal;
		String orgId = user.getUserOrg();
		return baseInfoPartTunnelDao.baseInfoPartTunnelQueryList(dgm,baseInfoPartTunnelPo,orgId);
	}

	/**
	 * 添加隧道信息
	 * @param baseInfoPartTunnelPo
	 * @param objList 
	 * @return
	 */
	public int addBaseInfoPartTunnel(BaseInfoPartTunnelPo baseInfoPartTunnelPo, List<Object[]> objList) {
		//TODO 添加隧道信息
		int result = baseInfoPartTunnelDao.addBaseInfoPartTunnel(baseInfoPartTunnelPo);
		if(objList.size() > 0){
			int[] saveCount = baseInfoPartTunnelDao.saveBaseInfoPartTunnelAuths(objList);
			for(int i = 0; i < saveCount.length; i ++){
				result += saveCount[i] == Statement.SUCCESS_NO_INFO ? 0 : saveCount[i];
			}
		}
		return result;
	}
	
	/**
	 * 修改隧道信息
	 * @param baseInfoPartTunnelPo
	 * @param objList 
	 * @return
	 */
	public int updateBaseInfoPartTunnel(BaseInfoPartTunnelPo baseInfoPartTunnelPo, List<Object[]> objList) {
		//TODO 修改隧道信息
		int result = baseInfoPartTunnelDao.updateBaseInfoPartTunnel(baseInfoPartTunnelPo);
		String id=baseInfoPartTunnelPo.getId();
		baseInfoPartTunnelDao.deleteBaseInfoPartTunnelAuths(id);
		if(objList.size() > 0){
			int[] saveCount = baseInfoPartTunnelDao.saveBaseInfoPartTunnelAuths(objList);
			for(int i = 0; i < saveCount.length; i ++){
				result += saveCount[i] == Statement.SUCCESS_NO_INFO ? 0 : saveCount[i];
			}
		}
		return result;
	}
	
	/**
	 * 删除隧道信息
	 * @param idList
	 * @return
	 */
	public int[] deleteBaseInfoPartTunnel(List<String> idList) {
		//TODO 删除隧道信息
		return baseInfoPartTunnelDao.deleteBaseInfoPartTunnel(idList);
	}
}
