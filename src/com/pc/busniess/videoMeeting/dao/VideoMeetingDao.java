package com.pc.busniess.videoMeeting.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.pc.bsp.common.util.DBUtil;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.org.po.OrgDesc;
import com.pc.bsp.security.po.PubUsers;
import com.pc.busniess.videoMeeting.po.VideoMeetingInfo;

@Repository("videoMeetingDao")
public class VideoMeetingDao {
	@Autowired
	private DBUtil util;

	// 分页查询
	public Map<String, Object> getVideoInfoPage(DataGridModel dgm, VideoMeetingInfo videoMeetingInfo) {
		// 查询结果Map
		Map<String, Object> result = new HashMap<String, Object>(2);
		OrgDesc orgDesc = null;
		// 获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			// 根据登录用户的机构编号获取机构描述对象
			orgDesc = util.getOrgDescByOrgId(((PubUsers) principal).getUserOrg());
		} else {
			// 如果登录用户session过期，则返回空结果集
			return result;
		}
		

		// 获取记录数
		StringBuffer sumSql = new StringBuffer();
		sumSql.append("select count(1) from video_meeting_manage a, pub_org o, pub_org_desc od " +
				"where a.org_id = o.org_id and o.org_id = od.org_id");
		
		// 获取结果集
		StringBuffer sqlSb = new StringBuffer("select ");
		sqlSb.append(" a.id as id,");// 主键
		sqlSb.append(" a.org_id as orgId,");// 组织id
		sqlSb.append(" o.org_name as orgName,");// 组织名称
		sqlSb.append(" a.video_ip as videoIp,");// 摄像头ip地址
		sqlSb.append(" a.video_user as videoUser,");// 摄像头用户名
		sqlSb.append(" a.video_pass as videoPass,");// 摄像头密码
		sqlSb.append(" a.video_port as videoPort,");// 摄像头端口号
		sqlSb.append(" a.video_channel as videoChannel,");// 摄像头通道号
		sqlSb.append(" a.video_stream as videoStream,");// 摄像头码流0：主码流，1：从码流1，2：从码流2，3：从码流3
		
		sqlSb.append(" a.decode_ip as decodeIp,");// 解码器ip地址
		sqlSb.append(" a.decode_user as decodeUser,");// 解码器用户名
		sqlSb.append(" a.decode_pass as decodePass,");// 解码器密码
		sqlSb.append(" a.decode_port as decodePort,");// 解码器端口号
		sqlSb.append(" a.decode_channel as decodeChannel,");// 解码器通道号
		
		sqlSb.append(" a.ismain as ismain,");// 是否主会场。0：否，1：是
		sqlSb.append(" a.remarks as remarks");// 备注

		sqlSb.append(" from video_meeting_manage a,pub_org o, pub_org_desc od "
				+ " where a.org_id = o.org_id and o.org_id = od.org_id ");

		// 组装查询条件
		Map<String, Object> params = new HashMap<String, Object>();

		//按机构查询任务
		if (null != videoMeetingInfo.getOrgId() && !videoMeetingInfo.getOrgId().equals("")) {
				orgDesc = util.getOrgDescByOrgId(videoMeetingInfo.getOrgId());
				sqlSb.append(" and od.id like :orgId");
				params.put("orgId", orgDesc.getId() + "%");
				sumSql.append(" and od.id like '").append(orgDesc.getId()).append("%'");
		}else{// 根据当前登录用户所在的机构进行查询
			if (null != orgDesc) {
				sqlSb.append(" and od.id like :userOrg");
				params.put("userOrg", orgDesc.getId() + "%");
				sumSql.append(" and od.id like '").append(orgDesc.getId()).append("%'");
			} else {
				// 如果机构描述对象为null，返回空结果集
				return result;
			}
		}
		// 组装排序规则
		String orderString = "";
		if (StringUtils.isNotBlank(dgm.getSort())) {
			orderString = " order by " + dgm.getSort() + " " + dgm.getOrder();
		}
		// 组装分页定义
		String pageString = "";
		if (dgm.getRows() != 0) {
			pageString = " limit " + (dgm.getPage() - 1) * dgm.getRows() + ", " + dgm.getRows();
		}
		// 绑定查询结果('total'和'rows'名称不能修改)
		result.put("total", util.getObjCount(sumSql.toString()));
		result.put("rows", util.getMapList(sqlSb.toString() + orderString + pageString, params));
		return result;
	}

	// 添加
	public int saveVideoInfo(VideoMeetingInfo videoMeetingInfo) {

		StringBuffer sqlInsert = new StringBuffer("");
		sqlInsert.append("insert into video_meeting_manage(");
		sqlInsert.append(" id,");// 主键
		sqlInsert.append(" org_id,");// 组织机构id
		sqlInsert.append(" video_ip,");// 摄像头ip地址
		sqlInsert.append(" video_user,");// 摄像头用户名
		sqlInsert.append(" video_pass,");// 摄像头密码
		sqlInsert.append(" video_port,");// 摄像头端口号
		sqlInsert.append(" video_channel,");// 摄像头通道号
		sqlInsert.append(" video_stream,");// 摄像头码流
		sqlInsert.append(" decode_ip,");// 解码器ip地址
		sqlInsert.append(" decode_user,");// 解码器用户名
		sqlInsert.append(" decode_pass,");// 解码器密码
		sqlInsert.append(" decode_port,");// 解码器端口号
		sqlInsert.append(" decode_channel,");// 解码器通道号
		sqlInsert.append(" ismain,");// 是否主会场
		sqlInsert.append(" remarks)");// 备注

		sqlInsert.append(" values(");
		sqlInsert.append(" :id,");// 主键
		sqlInsert.append(" :orgId,");// 组织机构id
		sqlInsert.append(" :videoIp,");// ip地址
		sqlInsert.append(" :videoUser,");// 用户名
		sqlInsert.append(" :videoPass,");// 密码
		sqlInsert.append(" :videoPort,");// 端口号
		sqlInsert.append(" :videoChannel,");// 通道号
		sqlInsert.append(" :videoStream,");// 码流
		sqlInsert.append(" :decodeIp,");// ip地址
		sqlInsert.append(" :decodeUser,");// 用户名
		sqlInsert.append(" :decodePass,");// 密码
		sqlInsert.append(" :decodePort,");// 端口号
		sqlInsert.append(" :decodeChannel,");// 通道号
		sqlInsert.append(" :ismain,");// 码流
		sqlInsert.append(" :remarks)");// 备注

		return util.editObject(sqlInsert.toString(), videoMeetingInfo);

	}

	// 修改
	public int updateVideoinfo(VideoMeetingInfo videoMeetingInfo) {

		StringBuffer sqlSb = new StringBuffer("update video_meeting_manage set org_id = :orgId, ");
		sqlSb.append("video_ip = :videoIp, video_user = :videoUser, video_pass = :videoPass, video_port = :videoPort, video_channel = :videoChannel,video_stream = :videoStream, ");
		sqlSb.append("decode_ip = :decodeIp, decode_user = :decodeUser, decode_pass = :decodePass, decode_port = :decodePort, decode_channel = :decodeChannel, ");
		sqlSb.append("ismain = :ismain, remarks = :remarks");

		
		sqlSb.append(" where id = :id");

		return util.editObject(sqlSb.toString(), videoMeetingInfo);

	}

	// 删除
	public int[] delVideoInfo(List<String> idList) {

		// 删除车站信息,数据库外键级联，自动将明细表删除
		String sql = "delete from video_meeting_manage where id = ?";

		return util.batchDelete(sql, idList);
	}


}
