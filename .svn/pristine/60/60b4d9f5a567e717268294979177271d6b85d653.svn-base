package com.pc.busniess.oaDocument.web.controller;


/**
 * 草稿箱
 * @author jwl
 * Controller
 */
import java.io.UnsupportedEncodingException;
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

import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.HttpException;
import com.pc.bsp.common.util.PubData;
import com.pc.busniess.oaDocument.po.OaInfoDocumentPo;
import com.pc.busniess.oaDocument.service.OaInfoDraftsDocumentService;
import com.pc.busniess.partrolTeamUserRelation.service.PartrolTeamUserRelationService;

@Controller
public class OaInfoDraftsDocumentController {

	private static Logger logger = Logger.getLogger(OaInfoDraftsDocumentController.class);
	@Autowired
	HttpSession session;//注入session 获取userId
	@Autowired
	private OaInfoDraftsDocumentService oaInfoDraftsDocumentService;//注入 队伍业务层调用查询user信息
	@Autowired
	private PartrolTeamUserRelationService partrolTeamUserRelationService;
	/**
	 * 打开草稿箱页面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/oaInfo/draftsDocumentInit",method=RequestMethod.GET)
	public String baseInfoRailInit(HttpServletRequest req){
		return "pc/bussiness/oaInfoDraftsDocument/oaInfoDraftsDocumentInit";
	}
	/**
	 * 打开详细信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value="oaInfoDrafts/detailedInformation",method=RequestMethod.GET)
	public String detailedInformation(HttpServletRequest req){
		return "/pc/bussiness/oaInfoDraftsDocument/draftsDetailedInformation";
	}
	/**
	 * 查询查询草稿箱
	 * @param dgm
	 * @param oaInfoDocumentPo
	 * @return
	 */
	@RequestMapping(value = "/oaInfo/draftsDocumentQueryList",method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> draftsDocumentQueryList(DataGridModel dgm, OaInfoDocumentPo oaInfoDocumentPo) {
		//session 域获取userId
			String userId=(String) session.getAttribute("userId");
			oaInfoDocumentPo.setReceiveUserId(userId);//发件人
		//队伍业务层调用查询user信息
		return oaInfoDraftsDocumentService.draftsDocumentQueryList(dgm,oaInfoDocumentPo); 
	}
	/**
	 * 弹出修改信息添加页面
	 * @param req
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/oaInfo/updateDraftsDocumentPopWin",method=RequestMethod.GET)
	public String updateOaInfoDocumentPopWin(HttpServletRequest req) throws UnsupportedEncodingException{
		//收件人
		req.setAttribute("queryPers", partrolTeamUserRelationService.queryPers());
		List<Map<String,Object>> wjflList = PubData.getDictList("WJFL");
		req.setAttribute("wjflList",wjflList);
		String photosName = new String(req.getParameter("fileName").getBytes("iso8859-1"), "utf-8");
		if(!(photosName.equals("undefined"))){
		    req.setAttribute("photosName", photosName); 
		}else{
		    req.setAttribute("photosName", "请选择照片"); 
		}
		return "pc/bussiness/oaInfoDraftsDocument/updateOaInfoDraftsDocumentPopWin";
	}
	/**
	 * 保存修改草稿箱
	 * @param oaInfoDocumentPo
	 * @return
	 */
	@RequestMapping(value = "/oaInfo/updateDraftsDocument",method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateOaInfoDraftsDocument(OaInfoDocumentPo oaInfoDocumentPo) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(oaInfoDraftsDocumentService.updateOaInfoDraftsDocument(oaInfoDocumentPo) > 0){
				map.put("mes", "修改草稿箱邮件成功");
			}else{
				map.put("mes", "修改草稿箱邮件失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("修改草稿箱邮件失败",e);
			}
			map.put("mes", "修改草稿箱邮件失败");
		}
		return map; 
	}
	/**
	 * 删除草稿箱
	 * @param idList
	 * @return
	 */
	@RequestMapping(value="/oaInfo/deleteDraftsDocument",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deleteOaInfoDraftsDocument(@RequestParam("idList") List<String> idList){  
		Map<String, String> map = new HashMap<String, String>();
		try {
			int[] result = oaInfoDraftsDocumentService.deleteOaInfoDraftsDocument(idList);
			int sum = 0;
			for(int j = 0; j < result.length; j ++){
				sum += result[j];
			}
			if(sum == idList.size()){
				map.put("mes", "删除成功["+sum+"]条草稿箱邮件");
			}else{
				map.put("mes", "删除草稿箱邮件失败");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("删除草稿箱邮件失败",e);
			}
			map.put("mes", "删除草稿箱邮件失败");
		}
		return map;
	}
	/**
	 * 删除草稿箱
	 * @param oaInfoDocumentPo
	 * @return
	 */
	@RequestMapping(value = "oaInfo/deleteDrafts",method = RequestMethod.POST)
	@ResponseBody
	public int deleteDrafts(OaInfoDocumentPo oaInfoDocumentPo) {
		return oaInfoDraftsDocumentService.deleteDrafts(oaInfoDocumentPo); 
	}
}
