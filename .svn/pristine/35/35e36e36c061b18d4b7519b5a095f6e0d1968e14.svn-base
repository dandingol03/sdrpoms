package com.pc.bsp.security.web.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pc.bsp.common.util.PhoneUtils;
import com.pc.bsp.common.web.controller.BaseController;
import com.pc.bsp.security.po.PubUsers;
import com.pc.bsp.user.service.UserService;

/**
 * 登录校验由Spring Security完成，此处仅作页面转向
 * @author simple
 *
 */
@Controller
public class LoginController extends BaseController{
	
	private static Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired
	private UserService userService;
	/**
	 * 登录成功后进入系统欢迎页面
	 * @param userName
	 * @param passWord
	 * @return
	 */
	@RequestMapping(value="/welcome",method=RequestMethod.GET)
	public String login(HttpSession session, HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "menuName", required = false) String menuName,
			@RequestParam(value = "menuId", required = false) String menuId){
		
		
		try {
			//获取当前登录用户
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof PubUsers) {
				PubUsers user = (PubUsers)principal;
				if(user.getEnable().equals("0")){
					//用户锁定
					return "pc/bsp/security/lock";
				} else {
					//用户登录成功后将登录错误次数清零
					userService.updateEnable(user.getUserAccount(), "0", "1");
				}
				session.setAttribute("userId", user.getUserId());
				session.setAttribute("userAccount", user.getUserAccount());
				session.setAttribute("userName", user.getUserName());
				session.setAttribute("userOrg", user.getUserOrg());
				if(PhoneUtils.isFromMobile(request)) {
					String sessionId=session.getId();
					response.setHeader("Cookie", "JSESSIONID=" + sessionId);
					return "pc/bsp/layout/layout";
				}
				if(null == menuName|| "" == menuName) {
					request.setAttribute("menuName", menuName);
				} else {
					request.setAttribute("menuName", menuName);
				}
				request.setAttribute("selectedMenuId", menuId);
				return "pc/bsp/layout/layout";
			}else{
				//session过期或无session
				return "pc/bsp/security/logout";
			}
		}catch(Exception e)
		{
			e.printStackTrace();
			return "pc/bsp/security/logout";
		}
	}
	/**
	 * 进入登录失败页面
	 * @return
	 */
	@RequestMapping(value="/logfail")
	public String logFail(HttpSession session, HttpServletRequest request){
//		if(PhoneUtils.isFromMobile(request)) {
//			return "mobile/bsp/security/logfail";
//		}
		return "pc/bsp/security/logfail";
	}
	
	/**
	 * 进入拒绝访问页面
	 * @return
	 */
	@RequestMapping(value="/accessDenied")
	public String accessDenied(HttpServletRequest request){
//		if(PhoneUtils.isFromMobile(request)) {
//			return "mobile/bsp/security/403";
//		}
		return "pc/bsp/security/403";
	}
	
	/**
	 * 进入会话超时页面
	 * @return
	 */
	@RequestMapping(value="/timeout")
	public String sessionTimeOut(HttpServletRequest request){
//		if(PhoneUtils.isFromMobile(request)) {
//			return "mobile/bsp/security/timeout";
//		}
		return "pc/bsp/security/timeout";
	}
	
	/**
	 * 退出系统
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/logout")
	public void logout(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//清除session
        Enumeration<String> em = request.getSession().getAttributeNames();
        while(em.hasMoreElements()){
            request.getSession().removeAttribute(em.nextElement().toString());
        }
        request.getSession().invalidate();
        response.sendRedirect("index.jsp");
	}
	
	/**
	 * 退出系统
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/index")
	public String index(HttpServletRequest request,HttpServletResponse response){
		return "mobile/homephone/home_index";
	}
}
