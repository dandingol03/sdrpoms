/**   
 * @Package: com.pc.busniess.partrolTeamUserRelation.po 
 * @author: jwl   
 * @date: 2018年4月19日 下午2:01:26 
 */
package com.pc.busniess.partrolTeamUserRelation.po;

/**   
 * @Package: com.pc.busniess.partrolTeamUserRelation.po 
 * @author: jwl   
 * @date: 2018年4月19日 下午2:01:26 
 */
public class ResponsibilityLinePo {
	private String id;
	private String userId;
	private String start;			//起点
	private String startKM;			
	private String startM;
	private String end;				//终点
	private String endKM;		
	private String endM;
	private String railId;
	private String railName;
	private String range;
	private String fileId;						//区段照片
	
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getRailName() {
		return railName;
	}
	public void setRailName(String railName) {
		this.railName = railName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getRailId() {
		return railId;
	}
	public void setRailId(String railId) {
		this.railId = railId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	@Override
	public String toString() {
		return "ResponsibilityLinePo [id=" + id + ", userId=" + userId
				+ ", start=" + start + ", startKM=" + startKM + ", startM="
				+ startM + ", end=" + end + ", endKM=" + endKM + ", endM="
				+ endM + ", railId=" + railId + ", railName=" + railName
				+ ", range=" + range + "]";
	}
	
}
