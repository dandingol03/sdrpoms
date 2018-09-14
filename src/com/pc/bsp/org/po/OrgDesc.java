/**
 * 
 */
package com.pc.bsp.org.po;

import java.io.Serializable;

/**
 * @author simple
 *
 */
public class OrgDesc implements Serializable{

	private static final long serialVersionUID = 6630318375727008198L;

	private String id;			//机构关系ID
	private String name;		//机构名称(pub_org_desc表中没有，是从pub_org表中关联过来生成机构树的)
	private String orgId;		//机构
	private String orgLevel;	//机构级别
	private String pId;			//父机构关系ID
	private String isParent;	//是否父节点
	private String open;		//是否展开
	
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
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgLevel() {
		return orgLevel;
	}
	public void setOrgLevel(String orgLevel) {
		this.orgLevel = orgLevel;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getIsParent() {
		return isParent;
	}
	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	
}
