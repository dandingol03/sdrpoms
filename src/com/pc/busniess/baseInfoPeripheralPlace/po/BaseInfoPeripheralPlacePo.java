package com.pc.busniess.baseInfoPeripheralPlace.po;

/**
 * 
 * @Package: com.pc.busniess.baseInfoPeripheralPlace.po 
 * @author: jwl   
 * @date: 2018年4月3日 上午9:45:12
 */
public class BaseInfoPeripheralPlacePo {
	private String id; 					
	private String mId;                 //ID移动端区分
	private String name; 				// 名称
	private String category;			// 类别(字典) 住宅 01 单位 02 市场 03 学校 04 厂矿 05 工地 06 休闲 07 商厦 08 吃住 09 养殖 10 种植（大棚）11 加油站 12 河湖 13
	private String address; 			// 地址
	private String charger; 			// 负责人
	private String telephone; 			// 联系电话
	private String orgId; 				// 所属机构
	private String photos;				// 照片
	private String lng; 				// 经度
	private String lat;					// 纬度
	private String remark; 				// 备注
	private String type; 				// 重点1 不重点0
	private String theGeom; 			// 
	private String description;         //基本情况描述
	private String railId;
	/**
	 * 数据字典  
	 * 周边场所类别category
		01	住宅
		02	单位
		03	市场
		04	学校
		05	厂矿
		06	工地
		07	休闲
		08	商厦
		09	吃住
		10	养殖
		11	种植（大棚）
		12	加油站
		13	河湖（鱼塘）
	 */
	
	public String getDescription() {
		return description;
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
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTheGeom() {
		return theGeom;
	}
	public void setTheGeom(String theGeom) {
		this.theGeom = theGeom;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPhotos() {
		return photos;
	}
	public void setPhotos(String photos) {
		this.photos = photos;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCharger() {
		return charger;
	}
	public void setCharger(String charger) {
		this.charger = charger;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
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
