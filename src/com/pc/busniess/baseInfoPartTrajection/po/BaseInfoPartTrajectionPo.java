package com.pc.busniess.baseInfoPartTrajection.po;

/**
 * @author mdf
 * 铁路基本信息
 */
public class BaseInfoPartTrajectionPo{
	private String id;							//ID
	private String mId;                 //ID移动端区分
	private String name;						//行人易穿行部位名称	
	private String status;						//行人易穿行部位状态
	private String address;						//地址
	private String telephone;					//联系电话
	private String photos;						//照片
	private String orgId;						//所属机构
	private String region;						//区域
	private String conditions;					//护栏情况
	private String lng;							//经度
	private String lat;							//纬度
	private String remark;						//备注
	private String theGeom;						//the_geom
	private String railId;
	/**
	 * 	数据字典  
	 *  易穿行状态statusName
	 	01	良好
		02	损坏
	 *	护栏情况 conditionName
		01	围墙
		02	水泥护栏
		03	铁艺护网
		04	无护栏
	 */
	
	public String getTheGeom() {
		return theGeom;
	}
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
	public void setTheGeom(String theGeom) {
		this.theGeom = theGeom;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getPhotos() {
		return photos;
	}
	public void setPhotos(String photos) {
		this.photos = photos;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getConditions() {
		return conditions;
	}
	public void setConditions(String conditions) {
		this.conditions = conditions;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
