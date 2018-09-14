/**
 * 
 */
package com.pc.bsp.security.po;

import java.io.Serializable;

/**
 * @author simple
 *
 */
public class PubResources implements Serializable{
	
	private static final long serialVersionUID = 6045588485709054484L;
	
	private String resourceId;		//资源ID
	private String resourceName;	//资源名称
	private String resourceType;	//资源类型(url/method)
	private int priority;			//资源优先级
	private String resourceString;	//资源字符串
	private String resourceDesc;	//资源描述
	private int enable;				//是否禁用
	private int isSys;				//是否超级权限
	
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getResourceString() {
		return resourceString;
	}
	public void setResourceString(String resourceString) {
		this.resourceString = resourceString;
	}
	public String getResourceDesc() {
		return resourceDesc;
	}
	public void setResourceDesc(String resourceDesc) {
		this.resourceDesc = resourceDesc;
	}
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
	public int getIsSys() {
		return isSys;
	}
	public void setIsSys(int isSys) {
		this.isSys = isSys;
	}

}
