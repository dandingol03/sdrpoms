/**   
 * @Package: com.mobile.bsp.authority.web.controller 
 * @author: jwl   
 * @date: 2018年6月25日 上午9:32:14 
 */
package com.mobile.bsp.authority.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.mobile.bsp.authority.service.MobileAuthorityService;
import com.pc.bsp.common.util.HttpException;
import com.pc.bsp.common.util.PhoneUtils;

/**   
 * @Package: com.mobile.bsp.authority.web.controller 
 * @author: jwl   
 * @date: 2018年6月25日 上午9:32:14 
 */
@Controller
public class MobileAuthorityController {
	@Autowired
	private MobileAuthorityService mobileauthorityService;
	
	@RequestMapping(value = "/mobile/findUrl", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> queryHasRegResList(@RequestParam("mobileUrl") String url) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> result=mobileauthorityService.queryHasRegResList();
		for (int i = 0; i < result.size(); i++) {
			Map<String, Object> map=result.get(i);
			String urlMap=map.get("resourceString").toString();
			if(StringUtils.equals(urlMap,url)){
				resultMap.put("success",true);
				return resultMap;
			}
		}
		resultMap.put("success",false);
		return resultMap;
	}
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/KickingOutSystem")
	public void KickingOutSystem(HttpServletRequest request, HttpServletResponse response) throws HttpException {
		
		if(PhoneUtils.isFromMobile(request)) {
			Map<String, Object> map = new HashMap<>();
			map.put("status", 400);
			map.put("mes", "用户已在其他设备登陆");
			JSONObject json=new JSONObject();
				response.setCharacterEncoding("utf-8");
                response.setContentType("application/json;charset=utf-8");
                response.setStatus(HttpException.CODE_435);
				try {
					response.getWriter().write(json.fromObject(map).toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}else{
			try {
				response.sendRedirect("index.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}
	@RequestMapping(value = "/logfailUrl")
	public void logfailUrl(HttpServletRequest request, HttpServletResponse response)  {
		
		if(PhoneUtils.isFromMobile(request)) {
			Map<String, Object> map = new HashMap<>();
			map.put("status", 400);
			map.put("mes", "用户名或密码错误");
			JSONObject json=new JSONObject();
				response.setCharacterEncoding("utf-8");
                response.setContentType("application/json;charset=utf-8");
                response.setStatus(HttpException.CODE_400);
				try {
					response.getWriter().write(json.fromObject(map).toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}else{
			try {
				request.getRequestDispatcher("/logfail").forward(request,response);
//				response.sendRedirect("/logfail");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}
}