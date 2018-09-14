/**   
 * @Package: com.mobile.mobileBsp.datadictionary.web.controller 
 * @author: jwl   
 * @date: 2018年5月5日 下午2:57:31 
 */
package com.mobile.bsp.datadictionary.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobile.bsp.datadictionary.service.MobileDataDictionaryService;
import com.pc.bsp.datadictionary.po.Dictionary;
import com.pc.bsp.datadictionary.web.controller.DataDictionaryController;

/**   
 * @Package: com.mobile.mobileBsp.datadictionary.web.controller 
 * @author: jwl   
 * @date: 2018年5月5日 下午2:57:31 
 */
@Controller
public class MobileDatadictionaryController {
	
	private static Logger logger = Logger.getLogger(DataDictionaryController.class);
	
	@Autowired
	private MobileDataDictionaryService mobileDataDictionaryService;
	/**
	 * 获取字典
	 * @return 
	 * @return
	 */
	@RequestMapping(value = "/mobile/datadictionary/comTree")
	@ResponseBody
	public List<Dictionary> comDictTree(HttpServletResponse response, @RequestParam String pid) {
		List<Dictionary> comOrg = mobileDataDictionaryService.getComDictList(pid);
		String json = JSONArray.fromObject(comOrg).toString();// 转化为JSON
		return comOrg;
	}
}
