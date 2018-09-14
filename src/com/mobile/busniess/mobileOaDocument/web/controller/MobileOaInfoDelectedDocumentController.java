package com.mobile.busniess.mobileOaDocument.web.controller;


/**
 * 回收站
 * @author jwl
 *Controller
 */

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobile.busniess.mobileOaDocument.service.MobileOaInfoDelectedDocumentService;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.HttpException;
import com.pc.busniess.oaDocument.po.OaInfoDocumentPo;

@Controller
public class MobileOaInfoDelectedDocumentController {

	private static Logger logger = Logger.getLogger(MobileOaInfoDelectedDocumentController.class);
	
	@Autowired
	private MobileOaInfoDelectedDocumentService oaInfoDelectedDocumentService;
	@Autowired
	HttpSession session;//注入session 获取userId
	/**
	 * 已删除的邮件查询
	 * @param dgm
	 * @param oaInfoDocumentPo
	 * @return
	 * @throws HttpException 
	 */
	@RequestMapping(value = "/mobile/oaInfo/deletedDocumentQueryList",method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> delectedDocumentQueryList(DataGridModel dgm, OaInfoDocumentPo oaInfoDocumentPo) throws HttpException {
		//session 域获取userId
			String userId=(String) session.getAttribute("userId");
			if(StringUtils.isBlank(userId)){
				throw new HttpException(HttpException.CODE_400, "userId获取失败");
			}
			oaInfoDocumentPo.setSendUserId(userId);//收件人
			oaInfoDocumentPo.setReceiveUserId(userId);//发件人
		return oaInfoDelectedDocumentService.deletedocumentQueryList(dgm,oaInfoDocumentPo); 
	}
}
