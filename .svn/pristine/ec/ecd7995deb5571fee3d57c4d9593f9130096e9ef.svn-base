package com.mobile.busniess.mobileOaDocument.web.controller;

/**
 * 发送箱
 * @author jwl
 *Controller
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobile.busniess.mobileOaDocument.service.MobileOaInfoOutBoxDocumentService;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.HttpException;
import com.pc.busniess.oaDocument.po.OaInfoDocumentPo;
import com.pc.busniess.partrolTeamUserRelation.service.PartrolTeamUserRelationService;

@Controller
public class MobileOaInfoOutBoxDocumentController {
	private static Logger logger = Logger.getLogger(MobileOaInfoOutBoxDocumentController.class);
	@Autowired
	private MobileOaInfoOutBoxDocumentService oaInfoOutBoxDocumentService;
	@Autowired
	private PartrolTeamUserRelationService partrolTeamUserRelationService;
	@Autowired
	HttpSession session;//注入session 获取userId
	/**
	 * 查询查询发件箱
	 * @param dgm
	 * @param oaInfoDocumentPo
	 * @return
	 * @throws HttpException 
	 */
	@RequestMapping(value = "/mobile/oaInfo/outBoxDocumentQueryList",method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> OutBoxDocumentQueryList(DataGridModel dgm,  OaInfoDocumentPo oaInfoDocumentPo) throws HttpException {
		//session 域获取userId
			String userId=(String) session.getAttribute("userId");
			if(StringUtils.isBlank(userId)){
				throw new HttpException(HttpException.CODE_400, "userId获取失败");
			}
			oaInfoDocumentPo.setReceiveUserId(userId);//发件人
		return oaInfoOutBoxDocumentService.OutBoxDocumentQueryList(dgm,oaInfoDocumentPo); 
	}
	/**
	 * 保存发件箱
	 * @param oaInfoDocumentPo
	 * @return
	 */
	@RequestMapping(value = "/mobile/oaInfo/addOutBoxDocument",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addOaInfoDocument( OaInfoDocumentPo oaInfoDocumentPo) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if("0".equals(oaInfoDocumentPo.getIsDrafts())){
				oaInfoDocumentPo.setIsApproved("1");
				oaInfoDocumentPo.setIsDelete("0");
				oaInfoDocumentPo.setIsDeleteOut("0");
				oaInfoDocumentPo.setSendState("1");
				if(oaInfoOutBoxDocumentService.addOaInfoOutBoxDocumentD(oaInfoDocumentPo) > 0){
					map.put("mes", "保存草稿箱成功");
					map.put("success",true);
//					map.put("error", false);
				}else{
					map.put("mes", "保存草稿失败");
					map.put("success",false);
//					map.put("error", true);
				}
			}else{
				oaInfoDocumentPo.setIsApproved("1");
				oaInfoDocumentPo.setIsDelete("0");
				oaInfoDocumentPo.setIsDeleteOut("0");
				oaInfoDocumentPo.setSendState("0");
				if(oaInfoOutBoxDocumentService.addOaInfoOutBoxDocument(oaInfoDocumentPo) > 0){
					map.put("mes", "发送成功");
					map.put("success",true);
//					map.put("error", true);
				}else{
					map.put("mes", "发送失败");
					map.put("success",false);
//					map.put("error", true);
				}
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("失败",e);
				map.put("success",false);
//				map.put("error", true);
			}
			map.put("mes", "失败");
			map.put("success",false);
//			map.put("error", true);
		}
		return map; 
	}
	/**
	 * 删除发件箱
	 * @param idList
	 * @return
	 */
	@RequestMapping(value="/mobile/oaInfo/deleteOutBoxDocument",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteOaInfoOutBoxDocument(@RequestParam("idList") List<String> idList){  
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			int[] result = oaInfoOutBoxDocumentService.deleteOaInfoOutBoxDocument(idList);
			int sum = 0;
			for(int j = 0; j < result.length; j ++){
				sum += result[j];
			}
			if(sum == idList.size()){
				map.put("mes", "删除成功["+sum+"]条邮件");
				map.put("error", false);
			}else{
				map.put("mes", "删除邮件失败");
				map.put("error", true);
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("删除邮件失败",e);
				map.put("error", true);
			}
			map.put("mes", "删除邮件失败");
			map.put("error", true);
		}
		return map;
	}
	
}
