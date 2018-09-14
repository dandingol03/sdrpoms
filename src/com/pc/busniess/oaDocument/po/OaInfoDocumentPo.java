package com.pc.busniess.oaDocument.po;

/**
 * 整个邮箱字段
 * @author jwl
 *	po
 */
public class OaInfoDocumentPo{
	private String id;			     //ID
	private String sendUserId;       //收件人
	private String receiveUserId;  	 //发件人
	private String hairUnit;  	     //发件单位
	private String title;		     //标题	
	private String sendTime;         //发送时间
	private String isApproved;       //状态
	private String isApprovedOainfo; //状态 
	private String isDelete;         //是否删除   收件箱删除
	private String isDeleteOut;      //是否删除 1是删除   发件箱删除
	private String state;            //文件类别
	private String sendState;        //发送状态0是发送1是存草稿
	private String sendUserIdName;   //收件人名
	private String file;             //附件
	private String editor;           //内容
	private String isDrafts;         //是否草稿箱 0是1否
	private String remark;           //备注
	
	public String getIsApprovedOainfo() {
		return isApprovedOainfo;
	}
	public void setIsApprovedOainfo(String isApprovedOainfo) {
		this.isApprovedOainfo = isApprovedOainfo;
	}
	public String getIsDeleteOut() {
		return isDeleteOut;
	}
	public void setIsDeleteOut(String isDeleteOut) {
		this.isDeleteOut = isDeleteOut;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	public String getIsDrafts() {
		return isDrafts;
	}
	public void setIsDrafts(String isDrafts) {
		this.isDrafts = isDrafts;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getSendState() {
		return sendState;
	}
	public void setSendState(String sendState) {
		this.sendState = sendState;
	}
	public String getSendUserIdName() {
		return sendUserIdName;
	}
	public void setSendUserIdName(String sendUserIdName) {
		this.sendUserIdName = sendUserIdName;
	}
	public String getHairUnit() {
		return hairUnit;
	}
	public void setHairUnit(String hairUnit) {
		this.hairUnit = hairUnit;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSendUserId() {
		return sendUserId;
	}
	public void setSendUserId(String sendUserId) {
		this.sendUserId = sendUserId;
	}
	public String getReceiveUserId() {
		return receiveUserId;
	}
	public void setReceiveUserId(String receiveUserId) {
		this.receiveUserId = receiveUserId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
