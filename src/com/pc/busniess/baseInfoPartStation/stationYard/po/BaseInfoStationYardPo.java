package com.pc.busniess.baseInfoPartStation.stationYard.po;

/**
 * 
* @author jwl E-mail:1183011789@qq.com
* @version 创建时间：2017年12月25日 上午10:12:05
* @version 1.0 
* @since 1.0
* 类说明
 */
public class BaseInfoStationYardPo{
	private String id;								//ID
	private String stationId;						//车站ID
	private String name;							//名称
	private String lng;								//经度
	private String lat;								//纬度
	private String range;							//范围
	private String theGeom;						//the_geom
	private String orgId;	   						//所属机构
	private String fileId;							//站场照片
	private String remark;							//备注
	
	public String getTheGeom() {
		return theGeom;
	}
	public void setTheGeom(String theGeom) {
		this.theGeom = theGeom;
	}
	public String getStationId() {
		return stationId;
	}
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	public String getOrgId() {
	    return orgId;
	}
	public void setOrgId(String orgId) {
	    this.orgId = orgId;
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
	public String getRange() {
	    return range;
	}
	public void setRange(String range) {
	    this.range = range;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getRemark() {
	    return remark;
	}
	public void setRemark(String remark) {
	    this.remark = remark;
	}
	
}

