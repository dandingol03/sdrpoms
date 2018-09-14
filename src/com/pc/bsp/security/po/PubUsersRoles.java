/**
 * 
 */
package com.pc.bsp.security.po;

import java.io.Serializable;

/**
 * @author simple
 *
 */
public class PubUsersRoles implements Serializable{
	
	private static final long serialVersionUID = -5936266546127773114L;
	
	private String id;		//ID(PK)
	private String userId;	//用户ID
	private String roleId;	//角色ID
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}
