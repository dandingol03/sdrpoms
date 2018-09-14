/**   
 * @Package: com.pc.busniess.oaOaInfoAnnouncement.web.controller 
 * @author: jwl   
 * @date: 2018年5月5日 上午11:42:35 
 */
package com.pc.busniess.oaBaseInfoAnnouncement.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.busniess.oaAddressBook.web.controller.OaInfoAddressBookController;
import com.pc.busniess.oaBaseInfoAnnouncement.po.OaInfoAnnouncementPo;
import com.pc.busniess.oaBaseInfoAnnouncement.service.OaInfoAnnouncementService;

/**   
 * 通知 公告
 * @Package: com.pc.busniess.oaOaInfoAnnouncement.web.controller 
 * @author: jwl   
 * @date: 2018年5月5日 上午11:42:35 
 */
@Controller
public class OaInfoAnnouncementController {

	private static Logger logger = Logger.getLogger(OaInfoAddressBookController.class);
	
	@Autowired
	private OaInfoAnnouncementService oaInfoAnnouncementService;
	
	//init页面打开  ---权限
	@RequestMapping(value="/oaInfo/oaInfoAnnouncementInit",method=RequestMethod.GET)
	public String oaInfoAnnouncementPopWin(HttpServletRequest req){
		return "/pc/bussiness/oaBaseInfoAnnouncement/oaAnnouncementInit";		
	}
	
	//结果集查询  ---权限
	@RequestMapping(value = "/oaInfo/oaInfoAnnouncementQueryList",method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> oaInfoAnnouncementQueryList(DataGridModel dgm,
			OaInfoAnnouncementPo oaInfoAnnouncementPo) {
		return oaInfoAnnouncementService.oaInfoAnnouncementQueryList(dgm,oaInfoAnnouncementPo); 
	}
	
	//添加页面打开  ---权限
	@RequestMapping(value="/oaInfo/addOaInfoAnnouncementPopWin",method=RequestMethod.GET)
	public String addOaInfoAnnouncementPopWin(HttpServletRequest req){
		return "/pc/bussiness/oaBaseInfoAnnouncement/addOaAnnouncementPopWin";		
	}
	
	//添加
	@RequestMapping(value = "/oaInfo/addOaInfoAnnouncement",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addOaInfoAnnouncement(OaInfoAnnouncementPo oaInfoAnnouncementPo) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(oaInfoAnnouncementService.addOaInfoAnnouncement(oaInfoAnnouncementPo) > 0){
				map.put("mes", "新增信息成功");
			}else{
				map.put("mes", "新增信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("新增信息失败",e);
			}
			map.put("mes", "新增信息失败");
		}
		return map; 
	}
	
	//修改页面打开  ---权限
	@RequestMapping(value="/oaInfo/updateOaInfoAnnouncementPopWin",method=RequestMethod.GET)
	public String updateOaInfoAnnouncementPopWin(HttpServletRequest req) throws UnsupportedEncodingException{
		String photosName = new String(req.getParameter("photosNames").getBytes("iso8859-1"), "utf-8");
		if(!(photosName.equals("undefined"))){
		    req.setAttribute("photosNames", photosName); 
		}else{
		    req.setAttribute("photosNames", "请选择照片"); 
		}
		String bcPhotoNames = new String(req.getParameter("bcPhotoNames").getBytes("iso8859-1"), "utf-8");
		if(!(bcPhotoNames.equals("undefined"))){
		    req.setAttribute("bcPhotoNames", bcPhotoNames); 
		}else{
		    req.setAttribute("bcPhotoNames", "请选择轮播照片"); 
		}
		String filePhotosName = new String(req.getParameter("attachmentName").getBytes("iso8859-1"), "utf-8");
		if(!(filePhotosName.equals("undefined"))){
		    req.setAttribute("attachmentName", filePhotosName); 
		}else{
		    req.setAttribute("attachmentName", "请选择附件"); 
		}
		return "/pc/bussiness/oaBaseInfoAnnouncement/updateOaAnnouncementPopWin";		
	}
	
	//跟新
	@RequestMapping(value = "/oaInfo/updateOaInfoAnnouncement",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateOaInfoAnnouncement(OaInfoAnnouncementPo oaInfoAnnouncementPo) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(oaInfoAnnouncementService.updateOaInfoAnnouncement(oaInfoAnnouncementPo) > 0){
				map.put("mes", "更新信息成功");
			}else{
				map.put("mes", "更新信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("修改信息失败",e);
			}
			map.put("mes", "修改信息失败");
		}
		return map; 
	}
	
	//删除  ---权限
	@RequestMapping(value="/oaInfo/deleteOaInfoAnnouncement",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deleteOaInfoAnnouncement(@RequestParam("idList") List<String> idList){  
		Map<String, String> map = new HashMap<String, String>();
		try {
			int[] result = oaInfoAnnouncementService.deleteOaInfoAnnouncement(idList);
			int sum = 0;
			for(int j = 0; j < result.length; j ++){
				sum += result[j];
			}
			if(sum == idList.size()){
				map.put("mes", "删除成功["+sum+"]条信息");
			}else{
				map.put("mes", "删除信息失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("删除信息失败",e);
			}
			map.put("mes", "删除信息失败");
		}
		return map;
	}
}
