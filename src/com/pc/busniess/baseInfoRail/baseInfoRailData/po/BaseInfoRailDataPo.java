package com.pc.busniess.baseInfoRail.baseInfoRailData.po;
/**
 * 铁路线路坐标
 * @author xb
 */
public class BaseInfoRailDataPo {
	private String id;           //id
	private String railId;       //铁路id
	private String railName;     //铁路名称
	private String orderNumber;  //编号
	private String lng;          //经度
	private String lat;          //纬度
	private String kilometerMark;//公里标
	private String kilometerMarkK;//公里标
	private String kilometerMarkM;//公里标
	private String stream; 	     //行别
	private String orgId;        //机构id
	private String remark;       //备注
	private String theGeom;       //
	
	public String getTheGeom() {
		return theGeom;
	}
	public void setTheGeom(String theGeom) {
		this.theGeom = theGeom;
	}
	public String getKilometerMarkK() {
	    return kilometerMarkK;
	}
	public void setKilometerMarkK(String kilometerMarkK) {
	    this.kilometerMarkK = kilometerMarkK;
	}
	public String getKilometerMarkM() {
	    return kilometerMarkM;
	}
	public void setKilometerMarkM(String kilometerMarkM) {
	    this.kilometerMarkM = kilometerMarkM;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRailId() {
		return railId;
	}
	public void setRailId(String railId) {
		this.railId = railId;
	}
	public String getRailName() {
		return railName;
	}
	public void setRailName(String railName) {
		this.railName = railName;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
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
	public String getKilometerMark() {
		return kilometerMark;
	}
	public void setKilometerMark(String kilometerMark) {
		this.kilometerMark = kilometerMark;
	}
	public String getStream() {
		return stream;
	}
	public void setStream(String stream) {
		this.stream = stream;
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
	@Override
	public String toString() {
		return "BaseInfoRailDataPo [id=" + id + ", railId=" + railId
				+ ", railName=" + railName + ", orderNumber=" + orderNumber
				+ ", lng=" + lng + ", lat=" + lat + ", kilometerMark="
				+ kilometerMark + ", kilometerMarkK=" + kilometerMarkK
				+ ", kilometerMarkM=" + kilometerMarkM + ", stream=" + stream
				+ ", orgId=" + orgId + ", remark=" + remark + ", theGeom="
				+ theGeom + "]";
	}
	
}
