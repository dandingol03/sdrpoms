/**   
 * @Package: com.pc.busniess.oaOaInfoNews.web.controller 
 * @author: jwl   
 * @date: 2018年5月5日 上午11:42:35 
 */
package com.mobile.busniess.mobileOaBaseInfoNews.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobile.busniess.mobileOaBaseInfoNews.service.MobileOaInfoNewsService;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.PhoneUtils;
import com.pc.busniess.oaBaseInfoNews.po.OaInfoNewsPo;

/**  
 * 新闻 
 * @Package: com.pc.busniess.oaOaInfoNews.web.controller 
 * @author: jwl   
 * @date: 2018年5月5日 上午11:42:35 
 */
@Controller
public class MobileOaInfoNewsController {

	
	@Autowired
	private MobileOaInfoNewsService oaInfoNewsService;
	
	//结果集查询  ---权限
	@RequestMapping(value = "/mobile/oaInfo/oaInfoNewsQueryList",method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> oaInfoNewsQueryList(DataGridModel dgm,
			OaInfoNewsPo oaInfoNewsPo,HttpServletRequest request,@RequestBody String mobileJSON) {
		try {
			if(PhoneUtils.isFromMobile(request)) {
				if(StringUtils.isNotEmpty(mobileJSON)){
					JSONObject json = JSONObject.fromObject(mobileJSON);
					DataGridModel parsed=(DataGridModel)JSONObject.toBean(json,DataGridModel.class);
					return oaInfoNewsService.oaInfoNewsQueryList(parsed,oaInfoNewsPo); 
				}else{
					return oaInfoNewsService.oaInfoNewsQueryList(dgm,oaInfoNewsPo); 		
				}
			}else {
				return oaInfoNewsService.oaInfoNewsQueryList(dgm,oaInfoNewsPo); 
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
