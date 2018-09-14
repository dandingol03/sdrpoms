package com.pc.busniess.videoMeeting.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.busniess.videoMeeting.dao.VideoMeetingDao;
import com.pc.busniess.videoMeeting.po.VideoMeetingInfo;

@Service("videoMeetingService")
public class VideoMeetingService{

	@Autowired
	private VideoMeetingDao videoMeetingDao;
	
	//分页查询
	public Map<String, Object> getVideoInfoPage(DataGridModel dgm, VideoMeetingInfo videoMeetingInfo){
		
		return videoMeetingDao.getVideoInfoPage(dgm, videoMeetingInfo);
	}
	//添加
	public int saveVideoInfo(VideoMeetingInfo videoMeetingInfo){
		return videoMeetingDao.saveVideoInfo(videoMeetingInfo);
	}
	//修改
	public int updateVideoinfo(VideoMeetingInfo videoMeetingInfo){
		return videoMeetingDao.updateVideoinfo(videoMeetingInfo);
	}
	
	//删除
	public int[] delVideoInfo(List<String> idList){
		
		return videoMeetingDao.delVideoInfo(idList);
	}
	
}
