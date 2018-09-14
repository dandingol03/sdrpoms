package com.pc.busniess.partrolTeamUserRelation.po;

/**
 * 巡防人
 * 这个Po主要是巡防管理信息所要用的字段
 * @author jiawenlong
 * @version 1.0
 * @since 1.0
 */

public class PartrolTeamUserRelationPo{
	private String id;						//ID
	private String mId;                 //ID移动端区分
	private String usersRolesId;			//ID
	private String education;				//文化程度
	private String registration;			//户口性质
	private String domicile;				//户籍地
	private String residence;				//居住地
	private String idNumber;				//身份证号
	private String managementUnit;			//管理单位
	private String remark;					//
	private String userId;					//
	private String teamInfoId;				//
	private String userName;
	private String userGender;				//男 1女2
	private String userAccount;
	private String userPassword;
	private String userBirthday;
	private String userOrg;
	private String userDuty;
	private String userTelephone;
	private String mail;			//邮箱
	private String qqWeixin;		//QQ或微信
	private String userDesc;		//用户描述
	private String enable;			//是否可用(0:正常/1:禁用)
	private String isSys;			//是否超级用户
	private String userDepartment;	//用户所属部门
	private String lng;             //经度
	private String lat;             //纬度
	private String theGeom;
	/**
	 * 数据字典
	 * 学历educationName
	  	01	初中
		02	大专
		03	高中
		04	中专
		05	职高
		06	本科
		07	中技
		08	小学
		10	技校
		11	大学
		12	职专
		13	中职
	 *户口registrationName
		01	农业
		02	非农业
	 */
	
	public String getUsersRolesId() {
		return usersRolesId;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public void setUsersRolesId(String usersRolesId) {
		this.usersRolesId = usersRolesId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getRegistration() {
		return registration;
	}
	public void setRegistration(String registration) {
		this.registration = registration;
	}
	public String getDomicile() {
		return domicile;
	}
	public void setDomicile(String domicile) {
		this.domicile = domicile;
	}
	public String getResidence() {
		return residence;
	}
	public void setResidence(String residence) {
		this.residence = residence;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getManagementUnit() {
		return managementUnit;
	}
	public void setManagementUnit(String managementUnit) {
		this.managementUnit = managementUnit;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTeamInfoId() {
		return teamInfoId;
	}
	public void setTeamInfoId(String teamInfoId) {
		this.teamInfoId = teamInfoId;
	}
	@Override
	public String toString() {
		return "PartrolTeamUserRelationPo [id=" + id
				+ ", education=" + education + ", registration=" + registration
				+ ", domicile=" + domicile + ", residence=" + residence
				+ ", idNumber=" + idNumber
				+ ", managementUnit=" + managementUnit + ", remark=" + remark
				+ ", userId=" + userId + ",  teamInfoId="
				+ teamInfoId + "]";
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
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
	public String getTheGeom() {
		return theGeom;
	}
	public void setTheGeom(String theGeom) {
		this.theGeom = theGeom;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

}
