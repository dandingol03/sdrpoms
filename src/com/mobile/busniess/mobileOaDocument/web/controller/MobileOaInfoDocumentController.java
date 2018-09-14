package com.mobile.busniess.mobileOaDocument.web.controller;

/**
 * 收件箱
 * @author jwl
 * Controller
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobile.busniess.mobileOaDocument.service.MobileOaInfoDocumentService;
import com.mobile.busniess.mobileOaDocument.service.MobileOaInfoDraftsDocumentService;
import com.mobile.busniess.mobileOaDocument.service.MobileOaInfoOutBoxDocumentService;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.HttpException;
import com.pc.bsp.common.util.PubData;
import com.pc.busniess.oaDocument.po.OaInfoDocumentPo;


@Controller
public class MobileOaInfoDocumentController {

	private static Logger logger = Logger.getLogger(MobileOaInfoDocumentController.class);
	@Autowired
	private MobileOaInfoDraftsDocumentService oaInfoDraftsDocumentService;
	@Autowired
	private MobileOaInfoOutBoxDocumentService oaInfoOutBoxDocumentService;
	@Autowired
	private MobileOaInfoDocumentService oaInfoDocumentService;
	@Autowired
	HttpSession session;//注入session 获取userId
	/**
	 * 查询未读邮件count
	 * @throws HttpException 
	 */
	@RequestMapping(value = "/mobile/oaInfo/anUnreadMailCount",method = RequestMethod.POST)
	@ResponseBody
	public  Integer anUnreadMailCount(OaInfoDocumentPo oaInfoDocumentPo) throws HttpException {
			String userId=(String) session.getAttribute("userId");
			if(StringUtils.isBlank(userId)){
				throw new HttpException(HttpException.CODE_400, "userId获取失败");
			}
			oaInfoDocumentPo.setSendUserId(userId);//收件人
		return oaInfoDocumentService.anUnreadMailCount(oaInfoDocumentPo); 
	}
	/**
	 * 查询收件箱
	 * @param req
	 * @param dgm
	 * @param oaInfoDocumentPo
	 * @return
	 * @throws HttpException 
	 */
	@RequestMapping(value = "/mobile/oaInfo/documentQueryList",method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> oaInfoDocumentQueryList(HttpServletRequest req,DataGridModel dgm, OaInfoDocumentPo oaInfoDocumentPo) throws HttpException {
		//session 域获取userId
			String userId=(String) session.getAttribute("userId");
			if(StringUtils.isBlank(userId)){
				throw new HttpException(HttpException.CODE_400, "userId获取失败");
			}
			oaInfoDocumentPo.setSendUserId(userId);//收件人
		List<Map<String,Object>> wjflList = PubData.getDictList("WJFL");//文件类型
		req.setAttribute("wjflList",wjflList);
		return oaInfoDocumentService.oaInfoDocumentQueryList(dgm,oaInfoDocumentPo); 
	}
	/**
	 * 删除收件箱
	 * @param idList
	 * @return
	 */
	@RequestMapping(value="/mobile/oaInfo/deleteDocument",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteOaInfoDocument(@RequestParam("idList") List<String> idList,@RequestParam("docType") int i){  
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			int[] result =new int[100];
			if(i==1){
				//收件箱
				result=oaInfoDocumentService.deleteOaInfoDocument(idList);
			}else if(i==2){
				//草稿箱
				result=oaInfoDraftsDocumentService.deleteOaInfoDraftsDocument(idList);
			}else if(i==3){
				//发件箱
				result=oaInfoOutBoxDocumentService.deleteOaInfoOutBoxDocument(idList);
			}
			
			int sum = 0;
			for(int j = 0; j < result.length; j ++){
				sum += result[j];
			}
			if(sum == idList.size()){
				map.put("mes", "删除成功["+sum+"]条收件");
//				map.put("error", false);
				map.put("success",true);
			}else{
				map.put("mes", "删除收件失败");
				map.put("success",false);
//				map.put("error", true);
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("删除收件失败",e);
				map.put("success",false);
//				map.put("error", true);
			}
			map.put("mes", "删除收件失败");
			map.put("success",false);
//			map.put("error", true);
		}
		return map;
	}
	
}
