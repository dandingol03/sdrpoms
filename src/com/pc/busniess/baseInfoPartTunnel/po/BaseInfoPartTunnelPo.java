package com.pc.busniess.baseInfoPartTunnel.po;

/**
 * 隧道基本信息表Pojo
 * @author CaoLu
 * @version 1.0
 * @since
 */
public class BaseInfoPartTunnelPo {
	private String id;				//ID
	private String mId;                 //ID移动端区分
	private String number;			//隧道编号
	private String name;			//隧道名称
	private String railId;			//参考铁路线
	private String middle;			//中心里程
	private String middleKM;		//中心里程公里 32
	private String middleM;			//中心里程米 32
	private String length;			//长度
	private String stream;			//行别
	private String telephone;		//联系电话
	private String orgId;			//所属机构
	private String lng;				//经度
	private String lat;				//纬度
	private String fileId;			//隧道入口照片
	private String photos;			//隧道出口照片
	private String remark;			//备注
	private String theGeom;			//theGeom
	/**
	 * 数据字典  
	 * 行别streamName
	 	01	单线
		02	双线上行
		03	双线下行
		04	双
		05	下
		06	上
		07	三
		08	四
	 * 守护情况guardStatusName
		02	铁路看守
		03	无人守护
		01	地方监护
	 */
	
	public String getId() {
		return id;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getTheGeom() {
		return theGeom;
	}
	public void setTheGeom(String theGeom) {
		this.theGeom = theGeom;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRailId() {
		return railId;
	}
	public void setRailId(String railId) {
		this.railId = railId;
	}
	public String getMiddle() {
		return middle;
	}
	public void setMiddle(String middle) {
		this.middle = middle;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
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
	public String getMiddleKM() {
		return middleKM;
	}
	public void setMiddleKM(String middleKM) {
		this.middleKM = middleKM;
	}
	public String getMiddleM() {
		return middleM;
	}
	public void setMiddleM(String middleM) {
		this.middleM = middleM;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getPhotos() {
		return photos;
	}
	public void setPhotos(String photos) {
		this.photos = photos;
	}
	
	
}
