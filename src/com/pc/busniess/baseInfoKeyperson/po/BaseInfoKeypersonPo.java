package com.pc.busniess.baseInfoKeyperson.po;


/**
 * @author lyf
 * 重点人基本信息
 */
public class BaseInfoKeypersonPo{
	private String id;							//ID
	private String mId;                 //ID移动端区分
	private String idNumber;						//身份证号
	private String name;							//姓名
	private String gender;						//性别
	private String age;							//年龄
	private String height;						//身高
	private String weight;						//体重
	private String job;							//职位
	private String telephone;					//联系电话
	private String presentAddress;				//现居地址
	private String familyNum;					//家庭人数
	private String member1;						//家庭成员1
	private String member2;						//家庭成员2
	private String familyAdress;					//家庭住址
	private String familyTelephone;				//家庭的联系电话
	private String lng; 							//经度
	private String lat; 							//维度
	private String orgId; 						//所属机构
	private String photos;						//重点人照片
	private String remark;						//备注
	private String isSigned;					//是否签订安全协议 01是 02否
	private String theGeom;
	private String railId;
	
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getRailId() {
		return railId;
	}
	public void setRailId(String railId) {
		this.railId = railId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getPresentAddress() {
		return presentAddress;
	}
	public void setPresentAddress(String presentAddress) {
		this.presentAddress = presentAddress;
	}
	public String getFamilyNum() {
		return familyNum;
	}
	public void setFamilyNum(String familyNum) {
		this.familyNum = familyNum;
	}
	public String getMember1() {
		return member1;
	}
	public void setMember1(String member1) {
		this.member1 = member1;
	}
	public String getMember2() {
		return member2;
	}
	public void setMember2(String member2) {
		this.member2 = member2;
	}
	public String getFamilyAdress() {
		return familyAdress;
	}
	public void setFamilyAdress(String familyAdress) {
		this.familyAdress = familyAdress;
	}
	public String getFamilyTelephone() {
		return familyTelephone;
	}
	public void setFamilyTelephone(String familyTelephone) {
		this.familyTelephone = familyTelephone;
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
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIsSigned() {
		return isSigned;
	}
	public void setIsSigned(String isSigned) {
		this.isSigned = isSigned;
	}
	public String getTheGeom() {
		return theGeom;
	}
	public void setTheGeom(String theGeom) {
		this.theGeom = theGeom;
	}
	public String getPhotos() {
		return photos;
	}
	public void setPhotos(String photos) {
		this.photos = photos;
	}
}
