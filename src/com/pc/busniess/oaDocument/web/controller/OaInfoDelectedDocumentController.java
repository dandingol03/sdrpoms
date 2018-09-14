package com.pc.busniess.oaDocument.web.controller;


/**
 * 回收站
 * @author jwl
 *Controller
 */

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.HttpException;
import com.pc.busniess.oaDocument.po.OaInfoDocumentPo;
import com.pc.busniess.oaDocument.service.OaInfoDelectedDocumentService;

@Controller
public class OaInfoDelectedDocumentController {

	private static Logger logger = Logger.getLogger(OaInfoDelectedDocumentController.class);
	
	@Autowired
	private OaInfoDelectedDocumentService oaInfoDelectedDocumentService;
	@Autowired
	HttpSession session;//注入session 获取userId
	/**
	 * 已删除的邮件 打开页面
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/oaInfo/deletedDocumentInit",method=RequestMethod.GET)
	public String baseInfoRailInit(HttpServletRequest req){
		return "pc/bussiness/oaInfoDetelectedDocument/oaInfoDelectedDocumentInit";
	}
	
	/**
	 * 已删除的邮件查询
	 * @param dgm
	 * @param oaInfoDocumentPo
	 * @return
	 */
	@RequestMapping(value = "/oaInfo/deletedDocumentQueryList",method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> delectedDocumentQueryList(DataGridModel dgm, OaInfoDocumentPo oaInfoDocumentPo) {
		//session 域获取userId
			String userId=(String) session.getAttribute("userId");
			oaInfoDocumentPo.setSendUserId(userId);//收件人
			oaInfoDocumentPo.setReceiveUserId(userId);//发件人
		return oaInfoDelectedDocumentService.deletedocumentQueryList(dgm,oaInfoDocumentPo); 
	}
}
