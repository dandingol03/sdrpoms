/**   
 * @Package: com.pc.busniess.oaOaInfoNews.po 
 * @author: jwl   
 * @date: 2018年5月5日 上午11:43:17 
 */
package com.pc.busniess.oaBaseInfoNews.po;

/**   
 * 新闻
 * @Package: com.pc.busniess.oaOaInfoNews.po 
 * @author: jwl   
 * @date: 2018年5月5日 上午11:43:17 
 */
public class OaInfoNewsPo {
	private String id;        		//id
	private String title;     		//标题
	private String abstracts; 		//摘要
	private String editor;   	 	//编辑者  user_id
	private String publishTime;		//发布时间
	private String content;			//内容
	private String photos;			//正文照片
	private String attachment;		//附件
	private String remark;			//备注
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAbstracts() {
		return abstracts;
	}
	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	public String getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPhotos() {
		return photos;
	}
	public void setPhotos(String photos) {
		this.photos = photos;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
