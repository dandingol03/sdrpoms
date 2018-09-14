package com.pc.busniess.oaDocument.web.controller;

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

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.PubData;
import com.pc.busniess.oaDocument.po.OaInfoDocumentPo;
import com.pc.busniess.oaDocument.service.OaInfoDocumentService;


@Controller
public class OaInfoDocumentController {

	private static Logger logger = Logger.getLogger(OaInfoDocumentController.class);
	
	@Autowired
	private OaInfoDocumentService oaInfoDocumentService;
	@Autowired
	HttpSession session;//注入session 获取userId
	/**
	 * 查询未读邮件count
	 */
	@RequestMapping(value = "/oaInfo/anUnreadMailCount",method = RequestMethod.POST)
	@ResponseBody
	public  Integer anUnreadMailCount(OaInfoDocumentPo oaInfoDocumentPo) {
		String userId=(String) session.getAttribute("userId");
		oaInfoDocumentPo.setSendUserId(userId);//收件人
		return oaInfoDocumentService.anUnreadMailCount(oaInfoDocumentPo); 
	}
	/**
	 * 打开收件箱
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/oaInfo/documentInit",method=RequestMethod.GET)
	public String documentInit(HttpServletRequest req){
		return "pc/bussiness/oaInfoDocument/oaInfoDocumentInit";
	}
	
	/**
	 * 打开详细信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value="oaInfoDocument/detailedInformation",method=RequestMethod.GET)
	public String detailedInformation(HttpServletRequest req){
		return "/pc/bussiness/oaInfoDocument/oaInfoDetailedInformation";
	}
	/**
	 * 查询收件箱
	 * @param req
	 * @param dgm
	 * @param oaInfoDocumentPo
	 * @return
	 */
	@RequestMapping(value = "/oaInfo/documentQueryList",method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> oaInfoDocumentQueryList(HttpServletRequest req,DataGridModel dgm, OaInfoDocumentPo oaInfoDocumentPo) {
		//session 域获取userId
			String userId=(String) session.getAttribute("userId");
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
	@RequestMapping(value="/oaInfo/deleteDocument",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> deleteOaInfoDocument(@RequestParam("idList") List<String> idList){  
		Map<String, String> map = new HashMap<String, String>();
		try {
			int[] result = oaInfoDocumentService.deleteOaInfoDocument(idList);
			int sum = 0;
			for(int j = 0; j < result.length; j ++){
				sum += result[j];
			}
			if(sum == idList.size()){
				map.put("mes", "删除成功["+sum+"]条收件");
			}else{
				map.put("mes", "删除收件");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("删除收件",e);
			}
			map.put("mes", "删除收件");
		}
		return map;
	}
	
}
