package com.pc.busniess.videoMonitorInfo.dao;

/**
 * jwl
 * 监控Dao
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pc.bsp.common.util.DBUtil;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.JurisdictionAppendSql;
import com.pc.bsp.common.util.PubData;
import com.pc.bsp.common.util.SqlUtil;
import com.pc.busniess.videoMonitorInfo.po.VideoMonitorInfoPo;

@Repository("videoMonitorInfoDao")
public class VideoMonitorInfoDao{
	
	@Autowired
	private DBUtil util;
	
	
	public List<Map<String, Object>> getVideoInfoCameraList(String orgId, String videoType) {
		// 组装查询条件
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuffer sqlSb = new StringBuffer("select ");
		sqlSb.append(" v.id as id,");// 主键
		sqlSb.append(" v.org as org,");// 组织id
		sqlSb.append(" v.ip as ip,");// ip地址
		sqlSb.append(" v.user as user,");// 用户名
		sqlSb.append(" v.pass as pass,");// 密码
		sqlSb.append(" v.port as port,");// 端口号
		sqlSb.append(" v.class as classStr,");// 调用包
		sqlSb.append(" v.space as space,");// 设备地点
		sqlSb.append(" v.channel as channel,");// 通道号
		sqlSb.append(" v.longitude as longitude,");// 经度
		sqlSb.append(" v.latitude as latitude,");// 纬度
		sqlSb.append(" v.stream as stream,");// 码流
		sqlSb.append(" v.videoType as videoType,");// 视频设备类型
		sqlSb.append(" v.remarks as remarks");// 备注
		sqlSb.append(" from pub_org_desc, video_monitor_info v where pub_org_desc.org_id=v.org");
		sqlSb.append(" and pub_org_desc.id like (select concat(id, '%') from pub_org_desc as aa where aa.org_id ='");
		sqlSb.append(orgId);
		sqlSb.append("')");
		sqlSb.append(" and v.videoType='");
		sqlSb.append(videoType);
		sqlSb.append("'");
		return (List<Map<String, Object>>) util.getMapList(sqlSb.toString(), params);
	}
	
	/**
	 * 根据id获取<strong>监控</strong>的详细信息
	 * @param id 监控ID
	 * @return 返回对应的监控的详细信息
	 * @author lxb
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> findById(String id) {
		String sql = "select " +
				"t.id as \"id\", " +
				"t.number as \"number\", " +
				"t.name as \"name\", " +
				"t.monitoring_position as \"monitoringPosition\", " +
				"t.access_department as \"accessDepartment\", " +
				"t.adress as \"adress\", " +
				"t.administration as \"administration\", " +
				"t.charger as \"charger\", " +
				"t.telephone as \"telephone\", " +
				"t.username as \"username\", " +
				"t.pwd as \"pwd\", " +
				"t.port as \"port\", " +
				"t.channel as \"channel\", " +
				"t.ip as \"ip\", " +
				"t.org_id as \"orgId\", " +
				"t.lng as \"lng\", " +
				"t.lat as \"lat\", " +
				"t.videoType as \"videoType\", " +
				"o.ORG_NAME as \"descOrgName\", " +
				"t.remark as \"remark\", " +
				"b.ORG_NAME as \"orgName\" " +
				"from video_monitor_info t " +
				"LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
				"LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(t.org_id,1,6) " +
				"LEFT OUTER JOIN pub_org AS o ON d.ORG_ID = o.ORG_ID " +
				"where 1=1 AND t.id=:id ";
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		Map<String,Object> row= util.getMapObject(sql, paramMap);
		// 监控类型
		Object videoType=row.get("videoType");
		if(videoType!=null&&!"".equals(videoType.toString().trim())){
			row.put("videoTypeName", PubData.getDictName("VIDEO_TYPE", videoType.toString()));		
		}
		return row;
	}
	
	/**
	 * 查询监控信息
	 * @param dgm
	 * @param videoMonitorInfoPo
	 * @return
	 */
	public Map<String, Object> videoMonitorInfoQueryList(DataGridModel dgm,VideoMonitorInfoPo videoMonitorInfoPo,String orgId) {
		// TODO 查询监控信息
		//结果集 分为2个 第一个为总数 total  第二为详细的rows 
		Map<String, Object> result = new HashMap<String, Object>(2);
		
		//统计数据总数语句
		StringBuffer sumSql = new StringBuffer();
		
		
		//统计数据详情语句
		 String quSql="";
			    sumSql.append("select count(1) from video_monitor_info t where 1=1 ");
		 quSql = "select " +
				"t.id as \"id\", " +
				"t.number as \"number\", " +
				"t.name as \"name\", " +
				"t.monitoring_position as \"monitoringPosition\", " +
				"t.access_department as \"accessDepartment\", " +
				"t.adress as \"adress\", " +
				"t.administration as \"administration\", " +
				"t.charger as \"charger\", " +
				"t.telephone as \"telephone\", " +
				"t.username as \"username\", " +
				"t.pwd as \"pwd\", " +
				"t.port as \"port\", " +
				"t.channel as \"channel\", " +
				"t.ip as \"ip\", " +
				"t.org_id as \"orgId\", " +
				"t.lng as \"lng\", " +
				"t.lat as \"lat\", " +
				"t.videoType as \"videoType\", " +
				"o.ORG_NAME as \"descOrgName\", " +
				"t.remark as \"remark\", " +
				"b.ORG_NAME as \"orgName\" " +
				"from video_monitor_info t " +
				"LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
				"LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(t.org_id,1,6) " +
				"LEFT OUTER JOIN pub_org AS o ON d.ORG_ID = o.ORG_ID " +
				"where 1=1 ";

		// 组装查询条件
		StringBuffer sqlSb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		//按照名称查询监控信息
		if (null != videoMonitorInfoPo.getName()&& !videoMonitorInfoPo.getName().equals("")) {
			sqlSb.append(" and t.name like :name");
			params.put("name", "%" + videoMonitorInfoPo.getName()+ "%");
			sumSql.append(" and t.name like '%").append(videoMonitorInfoPo.getName()).append("%'");
		}
		
		//分类
		if (null != videoMonitorInfoPo.getNumber() && !videoMonitorInfoPo.getNumber().equals("")) {
			sqlSb.append(" and t.number like :number");
			params.put("number", "%" + videoMonitorInfoPo.getNumber()+ "%");
			sumSql.append(" and t.number like '%").append(videoMonitorInfoPo.getNumber()).append("%'");
		}
		String andquSql="";
		if(!StringUtils.equals("110", orgId)) {  
			 sumSql.append(JurisdictionAppendSql.appendSql());
			 andquSql=JurisdictionAppendSql.appendSql();
			 params.put("orgId", orgId);
		}
		// 组装排序规则
		String orderString = "";
		if (StringUtils.isNotBlank(dgm.getSort())) {
			orderString = SqlUtil.getOrderbySql(dgm);
		}
		
		// 组装分页定义
		String sql = quSql + sqlSb.toString() +andquSql+ orderString;
		String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(), dgm.getRows());
		
		List<Map<String,Object>> rowsList=util.getMapList(pageQuerySql, params);
		if(rowsList!=null&&rowsList.size()>0){
			for(int i=0;i<rowsList.size();i++){
				Map<String,Object> row=rowsList.get(i);
				// 监控类型
				Object videoType=row.get("videoType");
				if(videoType!=null&&!"".equals(videoType.toString().trim())){
					row.put("videoTypeName", PubData.getDictName("VIDEO_TYPE", videoType.toString()));		
				}
			}
		}
		
		// 绑定查询结果('total'和'rows'名称不能修改)
		result.put("total", util.getObjCount(sumSql.toString()));
		result.put("rows", rowsList);

		return result;
	}
	/**
	 * 添加监控信息
	 * @param videoMonitorInfoPo
	 * @return
	 */
	public int addvideoMonitorInfo(VideoMonitorInfoPo videoMonitorInfoPo) {
		// TODO 添加服务忌语
		String sql = "insert into video_monitor_info " +
				"(id, " +
				"number," +
				"name," +
				"monitoring_position," +
				"access_department," +
				"adress," +
				"administration," +
				"charger," +
				"telephone	," +
				"username," +
				"pwd," +
				"port," +
				"channel," +
				"ip," +
				"org_id," +
				"videoType," +
				"lng," +
				"lat," +
				"the_geom," +
				"remark" +
				")values( " +
				":id," +
				":number," +
				":name," +
				":monitoringPosition," +
				":accessDepartment," +
				":adress," +
				":administration," +
				":charger," +
				":telephone," +
				":username," +
				":pwd," +
				":port," +
				":channel," +
				":ip," +
				":orgId," +
				":videoType," +
				":lng," +
				":lat," +
				"st_geomfromtext(:theGeom),"+
				":remark)";
		return util.editObject(sql, videoMonitorInfoPo);
	}
	/**
	 * 更新监控信息
	 * @param videoMonitorInfoPo
	 * @return
	 */
	public int updatevideoMonitorInfo(VideoMonitorInfoPo videoMonitorInfoPo) {
		// TODO 更新监控信息
		String sql = "update video_monitor_info set " +
				"number=:number," +
				"name=:name," +
				"monitoring_position=:monitoringPosition," +
				"access_department=:accessDepartment," +
				"adress=:adress," +
				"administration=:administration," +
				"charger=:charger," +
				"telephone=:telephone," +
				"username=:username," +
				"pwd=:pwd," +
				"port=:port," +
				"channel=:channel," +
				"ip=:ip," +
				"org_id=:orgId," +
				"videoType=:videoType," +
				"lng=:lng," +
				"lat=:lat," +
				"the_geom=st_geomfromtext(:theGeom)," +
				"remark=:remark "+
				"where id=:id";
		return util.editObject(sql, videoMonitorInfoPo);
	}
	/**
	 * 删除监控信息
	 * @param idList
	 * @return
	 */
	public int[] deletevideoMonitorInfo(List<String> idList) {
		// TODO 删除监控信息
		String delSql = "delete from video_monitor_info where id=?";
		String delViewSql = "delete from video_monitor_info_view where id=?";
		util.batchDelete(delViewSql, idList);
		return util.batchDelete(delSql, idList);
	}
	//删除旧的关联关系
	public Integer deleteVideoMonitorInfoAuths(String id) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		String delSql = "delete from video_monitor_info_view where id=:id ";
		params.put("id", id);
		return util.editObject(delSql, params);
	}
	//添加新的关联关系
	public int[] saveVideoMonitorInfoAuths(List<Object[]> objList) {
		// TODO Auto-generated method stub
		String viewSql="insert into video_monitor_info_view (id,name,lng,lat,the_geom,org_id,rail_id,rail_stream_id) values (?,?,?,?,st_geomfromtext(?),?,?,?)";
		return util.batchOperate(viewSql, objList);
	}
}
