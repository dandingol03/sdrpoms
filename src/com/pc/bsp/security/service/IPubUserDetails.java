/**
 * 
 */
package com.pc.bsp.security.service;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author simple
 *
 */
public interface IPubUserDetails extends UserDetails{
	
	//用户id
	public String getUserId();

	//用户账户
	public String getUserAccount();

	//用户名
	public String getUserName();

	//用户密码
	public String getUserPassword();

	//用户描述或简介
	public String getUserDesc();

	//用户是否可用
	public String getEnable();

	//是否超级用户
	public String getIsSys();
	
	//所属机构
	public String getUserOrg();

	//用户职位
	public String getUserDuty();
	
	//用户相对应的角色集
	public Set getPubUsersRoles();

}
