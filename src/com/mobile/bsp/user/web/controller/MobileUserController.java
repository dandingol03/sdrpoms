/**   
 * @Package: com.mobile.bsp.web.controller 
 * @author: jwl   
 * @date: 2018年5月4日 上午11:14:41 
 */
package com.mobile.bsp.user.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobile.bsp.user.po.MobileUserWorkAttendancePo;
import com.mobile.bsp.user.service.MobileUserService;
import com.mobile.bsp.util.Util;
import com.pc.bsp.org.service.OrgService;
import com.pc.bsp.security.po.PubUsers;
import com.pc.bsp.user.po.User;
import com.pc.bsp.user.service.UserService;
import com.pc.busniess.partrolTeamUserRelation.service.PartrolTeamUserRelationService;

/**   
 * @Package: com.mobile.bsp.web.controller 
 * @author: jwl   
 * @date: 2018年5月4日 上午11:14:41 
 */
@Controller
public class MobileUserController {
	
	private static Logger logger = Logger.getLogger(MobileUserController.class);
	
	@Autowired
	private OrgService orgService;
	@Autowired
	private UserService userService;
	@Autowired
	private MobileUserService mobileUserService;
	@Autowired
	private PartrolTeamUserRelationService partrolTeamUserRelationService;
	/**
	 * 手机端APP登陆获取用户信息
	 * @return
	 */
	@RequestMapping(value="/user/getCurrentUser",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getCurrentUser(){
		//获取当前登录用户
		 Map<String,Object> map=new HashMap<String, Object>();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PubUsers user = (PubUsers)principal;
		User mobileUser=userService.getLoginUserObj(user.getUserAccount());
		try {
			map.putAll(Util.convertToMap(user));
			map.putAll(Util.convertToMap(mobileUser));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String userId=user.getUserId();
		Map<String, Object> mapsize=partrolTeamUserRelationService.mobileFindById(userId);
		if(mapsize.get("count").toString().equals("1")){
			map.put("isTeamMember", "1");
		}else{
			map.put("isTeamMember", "0");
		}
		//使用Date
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		map.put("startDate", sdf.format(d));
		System.out.println(map);
		return map;	
	}
	/**
	 * 验证原始密码是否正确
	 * 更新当前登录用户的密码
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/mobile/user/updateUserNewPwd",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> updateUserNewPwd(@RequestParam("oldPwd") String oldPwd,@RequestParam("newPwd") String newPwd,
			@RequestParam(value="userAccount",required=false) String userAccount){
		Map<String,Object> map=new HashMap<String, Object>();
		int i=userService.checkLoginUserPwd(oldPwd);
		if(i>0){
			int j=userService.updateUserPwd(newPwd,userAccount);
			if(j>0){
				map.put("success",true);
			}else{
				map.put("success",false);
				map.put("mes","密码修改失败!");
			}
		}else{
			map.put("success",false);
			map.put("mes","原始密码错误!");
		}
		return map;
	}
	@RequestMapping(value="/user/addUserWorkAttendance",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addUserWorkAttendance(MobileUserWorkAttendancePo mobileUserWorkAttendancePo){
		Map<String,Object> map=new HashMap<String, Object>();
		String orgId=orgService.getOrg(mobileUserWorkAttendancePo.getLng(), mobileUserWorkAttendancePo.getLat());
		if(StringUtils.isEmpty(orgId)){
			map.put("success",false);
			map.put("mes","请填写正确的经纬度坐标,范围:北京");
			return map;
		}
		try{
			map.put("data", mobileUserWorkAttendancePo);
			int j=mobileUserService.addUserWorkAttendance(mobileUserWorkAttendancePo);
			if(j>0){
				map.put("success",true);
			}else{
				map.put("success",false);
				map.put("mes","信息保存失败!");
			}
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("信息保存失败",e);
			}
			map.put("mes", "信息保存失败");
			map.put("success",false);
		}
		return map; 
	}	
}
