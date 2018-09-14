package com.pc.busniess.videoMonitorInfo.service;

/**
 * jwl
 * 监控videoMonitorInfoService 
 */
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.security.po.PubUsers;
import com.pc.busniess.videoMonitorInfo.dao.VideoMonitorInfoDao;
import com.pc.busniess.videoMonitorInfo.po.VideoMonitorInfoPo;
@Service("videoMonitorInfoService")
public class VideoMonitorInfoService {

	@Autowired
	private VideoMonitorInfoDao videoMonitorInfoDao;
	
	/**
	 * 根据id获取<strong>监控信息</strong>的详细信息
	 * @param id 监控信息ID
	 * @return 返回对应监控信息的详细信息
	 * @author lxb
	 */
	public Map<String, Object> findById(String id) {
		return videoMonitorInfoDao.findById(id);
	}
	/**
	 * 查询监控信息
	 * @param dgm
	 * @param videoMonitorInfoPo
	 * @return
	 */
	public Map<String, Object> videoMonitorInfoQueryList(DataGridModel dgm, VideoMonitorInfoPo videoMonitorInfoPo) {
		// TODO 查询监控信息
		//获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PubUsers user = (PubUsers)principal;
		String orgId = user.getUserOrg();
		
		return videoMonitorInfoDao.videoMonitorInfoQueryList(dgm,videoMonitorInfoPo,orgId);
	}
	/**
	 * 添加监控信息
	 * @param videoMonitorInfoPo
	 * @param objList 
	 * @return
	 */
	public int addvideoMonitorInfo(VideoMonitorInfoPo videoMonitorInfoPo, List<Object[]> objList) {
		// TODO 添加监控信息
		int result = videoMonitorInfoDao.addvideoMonitorInfo(videoMonitorInfoPo);
		if(objList.size() > 0){
			int[] saveCount = videoMonitorInfoDao.saveVideoMonitorInfoAuths(objList);
			for(int i = 0; i < saveCount.length; i ++){
				result += saveCount[i] == Statement.SUCCESS_NO_INFO ? 0 : saveCount[i];
			}
		}
		return result;
	}
	/**
	 *  修改监控信息
	 * @param videoMonitorInfoPo
	 * @param objList 
	 * @return
	 */
	public int updatevideoMonitorInfo(VideoMonitorInfoPo videoMonitorInfoPo, List<Object[]> objList) {
		// TODO 修改监控信息
		int result = videoMonitorInfoDao.updatevideoMonitorInfo(videoMonitorInfoPo);
		String id=videoMonitorInfoPo.getId();
		videoMonitorInfoDao.deleteVideoMonitorInfoAuths(id);
		if(objList.size() > 0){
			int[] saveCount = videoMonitorInfoDao.saveVideoMonitorInfoAuths(objList);
			for(int i = 0; i < saveCount.length; i ++){
				result += saveCount[i] == Statement.SUCCESS_NO_INFO ? 0 : saveCount[i];
			}
		}
		return result;
	}
	/**
	 * 删除监控信息
	 * @param idList
	 * @return
	 */
	public int[] deletevideoMonitorInfo(List<String> idList) {
		// TODO 删除监控信息
		return videoMonitorInfoDao.deletevideoMonitorInfo(idList);
	}
	/**
	 * 根据信息获取监控信息
	 * @param orgId
	 * @param videoType
	 * @return
	 */
	public List<Map<String, Object>> getVideoInfoCameraList(String orgId, String videoType) {
		return videoMonitorInfoDao.getVideoInfoCameraList(orgId, videoType);
	}
}
