/**   
 * @Package: com.pc.busniess.oaOaInfoNews.web.controller 
 * @author: jwl   
 * @date: 2018年5月5日 上午11:42:35 
 */
package com.pc.busniess.oaBaseInfoNews.web.controller;

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
import com.pc.busniess.oaBaseInfoNews.po.OaInfoNewsPo;
import com.pc.busniess.oaBaseInfoNews.service.OaInfoNewsService;

/**   
 * 新闻
 * @Package: com.pc.busniess.oaOaInfoNews.web.controller 
 * @author: jwl   
 * @date: 2018年5月5日 上午11:42:35 
 */
@Controller
public class OaInfoNewsController {

	private static Logger logger = Logger.getLogger(OaInfoAddressBookController.class);
	
	@Autowired
	private OaInfoNewsService oaInfoNewsService;
	
	//init页面打开  ---权限
	@RequestMapping(value="/oaInfo/oaInfoNewsInit",method=RequestMethod.GET)
	public String oaInfoNewsPopWin(HttpServletRequest req){
		return "/pc/bussiness/oaBaseInfoNews/oaInfoNewsInit";		
	}
	
	//结果集查询  ---权限
	@RequestMapping(value = "/oaInfo/oaInfoNewsQueryList",method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> oaInfoNewsQueryList(DataGridModel dgm,
			OaInfoNewsPo oaInfoNewsPo) {
		return oaInfoNewsService.oaInfoNewsQueryList(dgm,oaInfoNewsPo); 
	}
	
	//添加页面打开  ---权限
	@RequestMapping(value="/oaInfo/addOaInfoNewsPopWin",method=RequestMethod.GET)
	public String addOaInfoNewsPopWin(HttpServletRequest req){
		return "/pc/bussiness/oaBaseInfoNews/addOaInfoNewsPopWin";		
	}
	
	//添加
	@RequestMapping(value = "/oaInfo/addOaInfoNews",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addOaInfoNews(OaInfoNewsPo oaInfoNewsPo) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(oaInfoNewsService.addOaInfoNews(oaInfoNewsPo) > 0){
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
	@RequestMapping(value="/oaInfo/updateOaInfoNewsPopWin",method=RequestMethod.GET)
	public String updateOaInfoNewsPopWin(HttpServletRequest req) throws UnsupportedEncodingException{
		String photosName = new String(req.getParameter("photosNames").getBytes("iso8859-1"), "utf-8");
		if(!(photosName.equals("undefined"))){
		    req.setAttribute("photosNames", photosName); 
		}else{
		    req.setAttribute("photosNames", "请选择照片"); 
		}
		String filePhotosName = new String(req.getParameter("attachmentName").getBytes("iso8859-1"), "utf-8");
		if(!(filePhotosName.equals("undefined"))){
		    req.setAttribute("attachmentName", filePhotosName); 
		}else{
		    req.setAttribute("attachmentName", "请选择附件"); 
		}
		return "/pc/bussiness/oaBaseInfoNews/updateOaInfoNewsPopWin";		
	}
	
	//跟新
	@RequestMapping(value = "/oaInfo/updateOaInfoNews",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateOaInfoNews(OaInfoNewsPo oaInfoNewsPo) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(oaInfoNewsService.updateOaInfoNews(oaInfoNewsPo) > 0){
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
	@RequestMapping(value="/oaInfo/deleteOaInfoNews",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deleteOaInfoNews(@RequestParam("idList") List<String> idList){  
		Map<String, String> map = new HashMap<String, String>();
		try {
			int[] result = oaInfoNewsService.deleteOaInfoNews(idList);
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
