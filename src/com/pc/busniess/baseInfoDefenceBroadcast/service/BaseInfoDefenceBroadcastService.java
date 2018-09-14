package com.pc.busniess.baseInfoDefenceBroadcast.service;

import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pc.bsp.common.util.ConverterUtils;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.security.po.PubUsers;
import com.pc.busniess.baseInfoDefenceBroadcast.dao.BaseInfoDefenceBroadcastDao;
import com.pc.busniess.baseInfoDefenceBroadcast.po.BaseInfoDefenceBroadcastPo;
import com.pc.busniess.baseInfoDefenceBroadcast.po.BroadcastPo;


@Service("baseInfoDefenceBroadcastService")
public class BaseInfoDefenceBroadcastService {
	@Autowired
	private BaseInfoDefenceBroadcastDao baseInfoDefenceBroadcastDao;
	/**
	 * 根据id获取广播警示柱的详细信息
	 * @param id 广播警示柱id
	 * @return
	 */
	public Map<String, Object> findByIpPort(BroadcastPo broadcastPo) {
		return baseInfoDefenceBroadcastDao.findByIpPort(broadcastPo);
	}
	public Map<String, Object> findById(String id) {
		return baseInfoDefenceBroadcastDao.findById(id);
	}
	public Map<String, Object> findById(byte[] tmpId) {
		return baseInfoDefenceBroadcastDao.findById(Arrays.toString(tmpId));
	}
	public List<Map<String, Object>> baseInfoDefenceBroadcastQueryList() {
		return baseInfoDefenceBroadcastDao.baseInfoDefenceBroadcastQueryList();
	}
	public Map<String, Object> baseInfoDefenceBroadcastQueryList(DataGridModel dgm, BaseInfoDefenceBroadcastPo baseInfoDefenceBroadcastPo) {
		/**TODO 
		 * 查询广播警示柱信息
		 */
		//获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PubUsers user = (PubUsers)principal;
		String orgId = user.getUserOrg();
		return baseInfoDefenceBroadcastDao.baseInfoDefenceBroadcastQueryList(dgm,baseInfoDefenceBroadcastPo,orgId);
	}

	public int addBaseInfoDefenceBroadcast(BaseInfoDefenceBroadcastPo baseInfoDefenceBroadcastPo, List<Object[]> objList) {
		/**
		 * TODO
		 *  添加广告警示柱信息
		 */
		int result = baseInfoDefenceBroadcastDao.addBaseInfoDefenceBroadcast(baseInfoDefenceBroadcastPo);
		
		if(objList.size() > 0){
			int[] saveCount = baseInfoDefenceBroadcastDao.saveBaseInfoDefenceBroadcastAuths(objList);
			for(int i = 0; i < saveCount.length; i ++){
				result += saveCount[i] == Statement.SUCCESS_NO_INFO ? 0 : saveCount[i];
			}
		}
		return result;
	}
	
	public int updateBaseInfoDefenceBroadcast(BaseInfoDefenceBroadcastPo baseInfoDefenceBroadcastPo, List<Object[]> objList) {
		/**
		 * TODO
		 * 修改广告警示柱信息
		 */
		int result = baseInfoDefenceBroadcastDao.updateBaseInfoDefenceBroadcast(baseInfoDefenceBroadcastPo);
		String id=baseInfoDefenceBroadcastPo.getId();
		baseInfoDefenceBroadcastDao.deleteBaseInfoDefenceBroadcastAuths(id);
		if(objList.size() > 0){
			int[] saveCount = baseInfoDefenceBroadcastDao.saveBaseInfoDefenceBroadcastAuths(objList);
			for(int i = 0; i < saveCount.length; i ++){
				result += saveCount[i] == Statement.SUCCESS_NO_INFO ? 0 : saveCount[i];
			}
		}
		return result;
	}
	//修改广播柱时间socket
	public int updateBroadcast(BroadcastPo broadcastPo) {
		return baseInfoDefenceBroadcastDao.updateBroadcast(broadcastPo);
	}
	//触发次数
	public int updateBroadcastInfoTrigger(BroadcastPo broadcastPo) {
		return baseInfoDefenceBroadcastDao.updateBroadcastInfoTrigger(broadcastPo);
	}
	//播放状态
	public int updateBroadcastInfoPlayStatus(BroadcastPo broadcastPo) {
		return baseInfoDefenceBroadcastDao.updateBroadcastInfoPlayStatus(broadcastPo);
	}
	//触发次数
	public int updateBroadcastInfoSoundSourceChannel(BroadcastPo broadcastPo) {
		return baseInfoDefenceBroadcastDao.updateBroadcastInfoSoundSourceChannel(broadcastPo);
	}
	public int[] deleteBaseInfoDefenceBroadcast(List<String> idList) {
		/**
		 * TODO
		 * 删除广告警示柱信息
		 */
		return baseInfoDefenceBroadcastDao.deleteBaseInfoDefenceBroadcast(idList);
	}
	
}
