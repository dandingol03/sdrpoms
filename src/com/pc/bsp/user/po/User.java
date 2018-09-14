/**
 * 
 */
package com.pc.bsp.user.po;

import java.io.Serializable;

/**
 * @author simple
 *
 */
public class User implements Serializable{

	private static final long serialVersionUID = -2292648391798868483L;
	
	private String userId;			//用户编号(主键)
	private String userAccount;		//用户登录账号
	private String userName;		//用户姓名
	private String userPassword;	//用户密码
	private String userGender;		//用户性别
	private String userBirthday;	//用户生日
	private String userOrg;			//用户所属机构
	private String userDuty;		//用户职务
	private String userTelephone;	//用户电话
	private String mail;			//邮箱
	private String qqWeixin;		//QQ或微信
	private String userDesc;		//用户描述
	private String enable;			//是否可用(0:正常/1:禁用)
	private String isSys;			//是否超级用户
	private String userDepartment;	//用户所属部门
	private String lng;             //经度
	private String lat;             //纬度
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public String getUserBirthday() {
		return userBirthday;
	}
	public void setUserBirthday(String userBirthday) {
		this.userBirthday = userBirthday;
	}
	public String getUserOrg() {
		return userOrg;
	}
	public void setUserOrg(String userOrg) {
		this.userOrg = userOrg;
	}
	public String getUserDuty() {
		return userDuty;
	}
	public void setUserDuty(String userDuty) {
		this.userDuty = userDuty;
	}
	public String getUserTelephone() {
		return userTelephone;
	}
	public void setUserTelephone(String userTelephone) {
		this.userTelephone = userTelephone;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getQqWeixin() {
		return qqWeixin;
	}
	public void setQqWeixin(String qqWeixin) {
		this.qqWeixin = qqWeixin;
	}
	public String getUserDesc() {
		return userDesc;
	}
	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	public String getIsSys() {
		return isSys;
	}
	public void setIsSys(String isSys) {
		this.isSys = isSys;
	}
	public String getUserDepartment() {
		return userDepartment;
	}
	public void setUserDepartment(String userDepartment) {
		this.userDepartment = userDepartment;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	
	
}
