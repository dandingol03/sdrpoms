package com.mobile.busniess.mobileOaDocument.web.controller;


/**
 * 草稿箱
 * @author jwl
 * Controller
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

import com.mobile.busniess.mobileOaDocument.service.MobileOaInfoDraftsDocumentService;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.HttpException;
import com.pc.busniess.oaDocument.po.OaInfoDocumentPo;
import com.pc.busniess.partrolTeamUserRelation.service.PartrolTeamUserRelationService;

@Controller
public class MobileOaInfoDraftsDocumentController {

	private static Logger logger = Logger.getLogger(MobileOaInfoDraftsDocumentController.class);
	@Autowired
	HttpSession session;//注入session 获取userId
	@Autowired
	private MobileOaInfoDraftsDocumentService oaInfoDraftsDocumentService;//注入 队伍业务层调用查询user信息
	@Autowired
	private PartrolTeamUserRelationService partrolTeamUserRelationService;
	/**
	 * 查询查询草稿箱
	 * @param dgm
	 * @param oaInfoDocumentPo
	 * @return
	 * @throws HttpException 
	 */
	@RequestMapping(value = "/mobile/oaInfo/draftsDocumentQueryList",method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> draftsDocumentQueryList(DataGridModel dgm, OaInfoDocumentPo oaInfoDocumentPo) throws HttpException {
		//session 域获取userId
			String userId=(String) session.getAttribute("userId");
			if(StringUtils.isBlank(userId)){
				throw new HttpException(HttpException.CODE_400, "userId获取失败");
			}
			oaInfoDocumentPo.setReceiveUserId(userId);//发件人
		//队伍业务层调用查询user信息
		return oaInfoDraftsDocumentService.draftsDocumentQueryList(dgm,oaInfoDocumentPo); 
	}
	/**
	 * 保存修改草稿箱
	 * @param oaInfoDocumentPo
	 * @return
	 */
	@RequestMapping(value = "/mobile/oaInfo/updateDraftsDocument",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateOaInfoDraftsDocument(OaInfoDocumentPo oaInfoDocumentPo) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(oaInfoDraftsDocumentService.updateOaInfoDraftsDocument(oaInfoDocumentPo) > 0){
				map.put("mes", "修改草稿箱邮件成功");
				map.put("success",true);
//				map.put("error", false);
			}else{
				map.put("mes", "修改草稿箱邮件失败");
//				map.put("error", true);
				map.put("success",false);
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("修改草稿箱邮件失败",e);
//				map.put("error", true);
				map.put("success",false);
			}
			map.put("mes", "修改草稿箱邮件失败");
//			map.put("error", true);
			map.put("success",false);
		}
		return map; 
	}
	/**
	 * 删除草稿箱
	 * @param idList
	 * @return
	 */
	@RequestMapping(value="/mobile/oaInfo/deleteDraftsDocument",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteOaInfoDraftsDocument(@RequestParam("idList") List<String> idList){  
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			int[] result = oaInfoDraftsDocumentService.deleteOaInfoDraftsDocument(idList);
			int sum = 0;
			for(int j = 0; j < result.length; j ++){
				sum += result[j];
			}
			if(sum == idList.size()){
				map.put("mes", "删除成功["+sum+"]条草稿箱邮件");
//				map.put("error", false);
				map.put("success",true);
			}else{
				map.put("mes", "删除草稿箱邮件失败");
//				map.put("error", true);
				map.put("success",false);
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("删除草稿箱邮件失败",e);
				map.put("success",false);
//				map.put("error", true);
			}
			map.put("mes", "删除草稿箱邮件失败");
			map.put("success",false);
//			map.put("error", true);
		}
		return map;
	}
	/**
	 * 删除草稿箱
	 * @param oaInfoDocumentPo
	 * @return
	 */
	@RequestMapping(value = "/mobile/oaInfo/deleteDrafts",method = RequestMethod.POST)
	@ResponseBody
	public int deleteDrafts(OaInfoDocumentPo oaInfoDocumentPo) {
		return oaInfoDraftsDocumentService.deleteDrafts(oaInfoDocumentPo); 
	}
}
