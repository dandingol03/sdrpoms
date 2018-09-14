/**
 * 
 */
package com.pc.bsp.role.po;

import java.io.Serializable;

/**
 * @author simple
 *
 */
public class Role implements Serializable{
	
	private static final long serialVersionUID = 1294233722161646735L;
	
	private String roleId;
	private String roleName;
	private String roleDesc;
	private String roleLevel;
	private String enable;
	private String isSys;
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public String getRoleLevel() {
		return roleLevel;
	}
	public void setRoleLevel(String roleLevel) {
		this.roleLevel = roleLevel;
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
