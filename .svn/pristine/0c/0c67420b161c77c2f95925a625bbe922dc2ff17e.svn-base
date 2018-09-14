package com.pc.busniess.videoMeeting.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.NextID;
import com.pc.busniess.videoMeeting.po.VideoMeetingInfo;
import com.pc.busniess.videoMeeting.service.VideoMeetingService;

@Controller
public class VideoMeetingController {
	@Autowired
	private VideoMeetingService videoMeetingService;
	
	@RequestMapping(value = "/videoMeeting/videoMeetingInit", method = RequestMethod.GET)
	public String videoMeetingInit(Model model,HttpServletRequest req) {

		return "pc/bussiness/videoMeeting/videoMeetingInfo";
	}
	
	@RequestMapping(value = "/videoMeeting/videoMeetingQueryList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> videoMeetingQueryList(DataGridModel dgm, VideoMeetingInfo videoMeetingInfo) {

		return videoMeetingService.getVideoInfoPage(dgm, videoMeetingInfo);
	}
	
	/**
	 * 进入添加页面
	 * @return
	 */
	@RequestMapping(value="/videoMeeting/videoMeetingAddPopWin",method=RequestMethod.GET)
	public String videoMeetingAddPopWin(HttpServletRequest req){

		return "pc/bussiness/videoMeeting/addVideoMeetingPopWin";
	}
	
	/**
	 * 保存添加
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/videoMeeting/addVideoMeetingInfo",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addVideoMeetingInfo(VideoMeetingInfo videoMeetingInfo){

		videoMeetingInfo.setId("videoMeeting-"+NextID.getUUID());
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(videoMeetingService.saveVideoInfo(videoMeetingInfo)> 0){
				map.put("mes", "添加视频会议设备信息成功");
			}else{
				map.put("mes", "添加视频会议设备信息失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "添加视频会议设备信息失败");
		}
		return map; 
	}
	
	/**
	 * 批量删除
	 * @param \
	 * @return
	 * @throws \
	 */
	@RequestMapping(value="/videoMeeting/delVideoMeetingInfo",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delVideoMeetingInfo(@RequestParam("idList") List<String> idList){
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			int[] result = videoMeetingService.delVideoInfo(idList);
			int sum = 0;
			for(int j = 0; j < result.length; j ++){
				sum += result[j];
			}
			if(sum == idList.size()){
				map.put("mes", "删除成功["+sum+"]条记录");
			}else{
				map.put("mes", "删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "删除失败");
		}
		return map;//重定向
	}
	
	/**
	 * 进入修改页面
	 * @return
	 */
	@RequestMapping(value="/videoMeeting/videoMeetingUpdatePopWin",method=RequestMethod.GET)
	public String videoMeetingUpdatePopWin(HttpServletRequest req){

		return "pc/bussiness/videoMeeting/updateVideoMeetingPopWin";
	}
	
	/**
	 * 保存修改后信息
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/videoMeeting/updateVideoMeetingInfo",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateVideoMeetingInfo(VideoMeetingInfo videoMeetingInfo){
		
		Map<String, String> map = new HashMap<String, String>();
		try {

			if(videoMeetingService.updateVideoinfo(videoMeetingInfo) > 0){
				map.put("mes", "更新视频会议设备信息成功");
			}else{
				map.put("mes", "更新视频会议设备信息失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "更新视频会议设备信息失败");
		}
		return map; 
	}
}
