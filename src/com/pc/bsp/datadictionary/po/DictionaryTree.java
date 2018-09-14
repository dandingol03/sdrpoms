package com.pc.bsp.datadictionary.po;

public class DictionaryTree {
	public String id;
	public String pId;
	public String name;
	public String dictData;
	public String dictName;
	public String dictLevel;
	public String isParent;
	public String remark;
	public String open;
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = "0";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDictData() {
		return dictData;
	}
	public void setDictData(String dictData) {
		this.dictData = dictData;
	}
	public String getDictName() {
		return dictName;
	}
	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
	public String getDictLevel() {
		return dictLevel;
	}
	public void setDictLevel(String dictLevel) {
		this.dictLevel = dictLevel;
	}
	public String getIsParent() {
		return isParent;
	}
	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
