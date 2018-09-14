package com.pc.busniess.baseInfoRail.po;

import com.pc.bsp.common.util.PubData;


/**
 * @author jiawenlong
 * 铁路po
 */
public class BaseInfoRailPo{
	public static final String R=PubData.getDictList("CIRCULAR_R").get(0).get("dictData").toString();
	private String id;				//ID
	private String number;			//编号
	private String name;			//铁路名称	
	private String classify;		//铁路分类
	private String start;			//起点
	private String startKM;			
	private String startM;
	private String end;				//终点
	private String endKM;		
	private String endM;
	private String length;			//长度
	private String railLevel;		//线别
	private String state;			//运营状态
	private String startId;			//起点站
	private String endId;			//终点站
	private String remark;			//备注
	private String lineGeom;			
	private String rangeGeom;			
	/**
	 * 数据字典  
	 *铁路分类 classifyName
		01	高铁干线
		03	普通干线
		02	重载干线
		04	其他线路
	 *运营状态stateName
		01	运营
		02	地方
		03	合资
	 *线别railLevelName
		01	正线
		02	代维修
		03	其他站线
		04	段管线
	 */
	
	public String getId() {
		return id;
	}
	public String getLineGeom() {
		return lineGeom;
	}
	public void setLineGeom(String lineGeom) {
		this.lineGeom = lineGeom;
	}
	public String getRangeGeom() {
		return rangeGeom;
	}
	public void setRangeGeom(String rangeGeom) {
		this.rangeGeom = rangeGeom;
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
	public String getClassify() {
		return classify;
	}
	public void setClassify(String classify) {
		this.classify = classify;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getStartKM() {
		return startKM;
	}
	public void setStartKM(String startKM) {
		this.startKM = startKM;
	}
	public String getStartM() {
		return startM;
	}
	public void setStartM(String startM) {
		this.startM = startM;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getEndKM() {
		return endKM;
	}
	public void setEndKM(String endKM) {
		this.endKM = endKM;
	}
	public String getEndM() {
		return endM;
	}
	public void setEndM(String endM) {
		this.endM = endM;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getRailLevel() {
		return railLevel;
	}
	public void setRailLevel(String railLevel) {
		this.railLevel = railLevel;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStartId() {
		return startId;
	}
	public void setStartId(String startId) {
		this.startId = startId;
	}
	public String getEndId() {
		return endId;
	}
	public void setEndId(String endId) {
		this.endId = endId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
