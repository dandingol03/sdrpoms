package com.pc.bsp.authority.po;


import java.io.Serializable;

/**
 * @author simple
 *
 */
public class Authority implements Serializable{
	
	private static final long serialVersionUID = 3192200672313573234L;
	
	private String authorityId;
	private String authorityName;
	private String authorityDesc;
	private String enable;
	private String isSys;
	
	public String getAuthorityId() {
		return authorityId;
	}
	public void setAuthorityId(String authorityId) {
		this.authorityId = authorityId;
	}
	public String getAuthorityName() {
		return authorityName;
	}
	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}
	public String getAuthorityDesc() {
		return authorityDesc;
	}
	public void setAuthorityDesc(String authorityDesc) {
		this.authorityDesc = authorityDesc;
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

}
