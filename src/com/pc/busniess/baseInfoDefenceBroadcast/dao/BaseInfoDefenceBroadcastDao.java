package com.pc.busniess.baseInfoDefenceBroadcast.dao;

/**
 *  @author lyf
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pc.bsp.common.util.ConverterUtils;
import com.pc.bsp.common.util.DBUtil;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.PubData;
import com.pc.bsp.common.util.SqlUtil;
import com.pc.busniess.baseInfoDefenceBroadcast.po.BaseInfoDefenceBroadcastPo;
import com.pc.busniess.baseInfoDefenceBroadcast.po.BroadcastPo;

@Repository("BaseInfoDefenceBroadcastDao")
public class BaseInfoDefenceBroadcastDao{
	
	private static Logger logger = Logger.getLogger(BaseInfoDefenceBroadcastDao.class);
	@Autowired
	private DBUtil util;
	/**
	 * @return
	 */
	public List<Map<String, Object>> baseInfoDefenceBroadcastQueryList() {
		// TODO Auto-generated method stub
		String sql ="select " +
				   "t.id as \"id\", " +
				   "t.number as \"number\", " +
				   "t.name as \"name\", " +
				   "t.adress as \"adress\", " +
				   "t.status as \"status\", " +
				   "t.voicebroadcast as \"voicebroadcast\", " +
				   "t.broadcasting as \"broadcasting\", " +
				   "t.monitor_id as \"monitorId\", " +
				   "t.charger as \"charger\", " +
				   "t.telephone as \"telephone\", " +
				   "t.org_id as \"orgId\", " +
				   "t.lng as \"lng\", " +
				   "t.lat as \"lat\", " +
				   "t.ip as \"ip\", " +
				   "t.port as \"port\", " +
				   "o.ORG_NAME as \"descOrgName\", " +
				   "t.bro_type as \"broType\", " +
				   "t.remark as \"remark\" ," +
				   "t.photos as \"photos\" ," +
				   "t.visited_time as \"visitedTime\" ," +
				    "t.voice_file_address as \"voiceFileAddress\" ," +
					"t.voltage as \"voltage\" ," +
					"t.play_status as \"playStatus\" ," +
					"t.version_number as \"versionNumber\" ," +
					"t.tf_card_file_total as \"tfCardFileTotal\" ," +
					"t.flash_file_total as \"flashFileTotal\" ," +
					"t.tf_current_trcak as \"tfCurrentTrcak\" ," +
					"t.flash_current_trcak as \"flashCurrentTrcak\" ," +
					"t.tts_volume as \"ttsVolume\" ," +
					"t.active_report_interval as \"activeReportInterval\" ," +
					"t.led_text as \"ledText\" ," +
					"t.sound_source_channel as \"soundSourceChannel\" ," +
					"t.preset_voice_volume as \"presetVoiceVolume\" ," +
				   "b.ORG_NAME as \"orgName\" " +
				   "from base_info_defence_broadcast t " +
				   "LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
				   "LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(t.org_id,1,6) " +
				   "LEFT OUTER JOIN pub_org AS o ON d.ORG_ID = o.ORG_ID  " ;
		//where t.status !='03'
		
		return util.getMapList(sql, new Object[]{});
	}
	
	/**
	 * 根据id获取广播警示柱的详细信息
	 * @param id
	 * @return
	 */
	public Map<String, Object> findById(String id) {
		String sql ="select " +
					   "t.id as \"id\", " +
					   "t.number as \"number\", " +
					   "t.name as \"name\", " +
					   "t.adress as \"adress\", " +
					   "t.status as \"status\", " +
					   "t.voicebroadcast as \"voicebroadcast\", " +
					   "t.broadcasting as \"broadcasting\", " +
					   "t.monitor_id as \"monitorId\", " +
					   "t.charger as \"charger\", " +
					   "t.telephone as \"telephone\", " +
					   "t.org_id as \"orgId\", " +
					   "t.lng as \"lng\", " +
					   "t.lat as \"lat\", " +
					   "t.ip as \"ip\", " +
					   "t.port as \"port\", " +
					   "o.ORG_NAME as \"descOrgName\", " +
					   "t.bro_type as \"broType\", " +
					   "t.remark as \"remark\" ," +
					   "t.photos as \"photos\" ," +
					   "t.visited_time as \"visitedTime\" ," +
					   "t.voice_file_address as \"voiceFileAddress\" ," +
						"t.voltage as \"voltage\" ," +
						"t.play_status as \"playStatus\" ," +
						"t.version_number as \"versionNumber\" ," +
						"t.tf_card_file_total as \"tfCardFileTotal\" ," +
						"t.flash_file_total as \"flashFileTotal\" ," +
						"t.tf_current_trcak as \"tfCurrentTrcak\" ," +
						"t.flash_current_trcak as \"flashCurrentTrcak\" ," +
						"t.tts_volume as \"ttsVolume\" ," +
						"t.active_report_interval as \"activeReportInterval\" ," +
						"t.led_text as \"ledText\" ," +
						"t.sound_source_channel as \"soundSourceChannel\" ," +
						"t.preset_voice_volume as \"presetVoiceVolume\" ," +
					   "b.ORG_NAME as \"orgName\" " +
					   "from base_info_defence_broadcast t " +
					   "LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
					   "LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(t.org_id,1,6) " +
					   "LEFT OUTER JOIN pub_org AS o ON d.ORG_ID = o.ORG_ID " +
					   "where 1=1 AND t.id=:id";
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		Map<String, Object> row=util.getMapObject(sql, paramMap);
		Object status=row.get("status");
		if(status!=null&&!"".equals(status.toString().trim())){
			row.put("statusName", PubData.getDictName("BROADCAST_STATUS", status.toString()));		
		}
		Object broType=row.get("broType");
		if(broType!=null&&!"".equals(broType.toString().trim())){
			row.put("broTypeName", PubData.getDictName("BROADCASTTYPE", broType.toString()));		
		}
		//记录文件列表
		Object tempProfile=row.get("photos");
		List<String> designProfiles=new ArrayList<String>();
		if(tempProfile!=null&&!"".equals(tempProfile.toString().trim())){
			String []tempFileArray = tempProfile.toString().split(",");
			String tempFileName="'1'";
			for(int j=0;j<tempFileArray.length;j++){
				tempFileName+=","+"'"+tempFileArray[j]+"'";
			}
			String fileQuerysql="select FILE_ID,FILE_NAME from PUB_FILE_UPLOAD where FILE_ID in ("+tempFileName+")";
			List<Map<String,Object>> tempFileList=util.getMapList(fileQuerysql, new Object[]{});
			for(int j=0;j<tempFileList.size();j++){
				designProfiles.add(tempFileList.get(j).get("FILE_ID").toString());
			}
			row.put("designProfiles",designProfiles);
			row.put("photosNum",tempFileList.size());
		}
		return row;
	}

	public Map<String, Object> baseInfoDefenceBroadcastQueryList(DataGridModel dgm, BaseInfoDefenceBroadcastPo baseInfoDefenceBroadcastpo,String orgId) {
		
		
		/**
		 * TODO 
		 * 查询广播警示柱信息
		 */
		/**
		 * 结果集 分为2个 第一个为总数 total  第二为详细的rows 
		 */
		Map<String, Object> result = new HashMap<String, Object>(2);
		/**
		 * 统计数据总数语句
		 */
		StringBuffer sumSql = new StringBuffer();
		sumSql.append("select count(1) from base_info_defence_broadcast t where 1=1 ");
		/**
		 * 统计数据详情语句
		 */
		String quSql = "select " +
				   "t.id as \"id\", " +
				   "t.number as \"number\", " +
				   "t.name as \"name\", " +
				   "t.adress as \"adress\", " +
				   "t.ip as \"ip\", " +
				   "t.port as \"port\", " +
				   "t.status as \"status\", " +
				   "t.voicebroadcast as \"voicebroadcast\", " +
				   "t.broadcasting as \"broadcasting\", " +
				   "t.monitor_id as \"monitorId\", " +
				   "t.charger as \"charger\", " +
				   "t.telephone as \"telephone\", " +
				   "t.org_id as \"orgId\", " +
				   "t.lng as \"lng\", " +
				   "t.lat as \"lat\", " +
				   "o.ORG_NAME as \"descOrgName\", " +
				   "t.bro_type as \"broType\", " +
				   "t.remark as \"remark\" ," +
				   "t.photos as \"photos\" ," +
				   "t.visited_time as \"visitedTime\" ," +
				   "b.ORG_NAME as \"orgName\" " +
				   "from base_info_defence_broadcast t " +
				   "LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
				   "LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(t.org_id,1,6) " +
				   "LEFT OUTER JOIN pub_org AS o ON d.ORG_ID = o.ORG_ID " +
				   "where 1=1 ";
		
		/**
		 * 组装查询条件
		 */
		StringBuffer sqlSb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();
		
		/**
		 * 按照名称查询广播警示柱信息
		 */
		if (null != baseInfoDefenceBroadcastpo.getName()&& !baseInfoDefenceBroadcastpo.getName().equals("")) {
			sqlSb.append(" and t.name like :name");
			params.put("name", "%" + baseInfoDefenceBroadcastpo.getName()+ "%");
			sumSql.append(" and t.name like '%").append(baseInfoDefenceBroadcastpo.getName()).append("%'");
		}
		
		if (null != baseInfoDefenceBroadcastpo.getAdress() && !baseInfoDefenceBroadcastpo.getAdress().equals("")) {
			sqlSb.append(" and t.adress like :adress");
			params.put("adress", "%" + baseInfoDefenceBroadcastpo.getAdress()+ "%");
			sumSql.append(" and t.adress like '%").append(baseInfoDefenceBroadcastpo.getAdress()).append("%'");
		}
		
		//机构筛选权限
		if (null != orgId && !orgId.equals("")) {
			sqlSb.append(" and t.org_id like :orgId");
			params.put("orgId", orgId+ "%");
			sumSql.append(" and t.org_id like '").append(orgId).append("%'");
		}
		//机构
		if (null != baseInfoDefenceBroadcastpo.getOrgId() && !baseInfoDefenceBroadcastpo.getOrgId().equals("")) {
			sqlSb.append(" and t.org_id like :orgId");
			params.put("orgId", baseInfoDefenceBroadcastpo.getOrgId()+ "%");
			sumSql.append(" and t.org_id like '").append(baseInfoDefenceBroadcastpo.getOrgId()).append("%'");
		}
		//三公里范围内广播警示柱
		if (null != baseInfoDefenceBroadcastpo.getRailId()&& !baseInfoDefenceBroadcastpo.getRailId().equals("")) {
			String sql=" and t.id in(SELECT id from base_info_defence_broadcast_view where rail_stream_id=:railId) ";
			params.put("railId",baseInfoDefenceBroadcastpo.getRailId());
			sqlSb.append(sql);
			sumSql.append(" and t.id in(SELECT id from base_info_defence_broadcast_view where rail_stream_id= '").
			append(baseInfoDefenceBroadcastpo.getRailId()).append("') ");
		}
		/**
		 * 组装排序规则
		 */
		String orderString = "";
		if (StringUtils.isNotBlank(dgm.getSort())) {
			orderString = SqlUtil.getOrderbySql(dgm);
		}
		
		/**
		 * 组装分页定义
		 */
		String sql = quSql + sqlSb.toString()+orderString;
		String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(), dgm.getRows());
		List<Map<String,Object>> rowsList=util.getMapList(pageQuerySql, params);
		if(rowsList!=null&&rowsList.size()>0){
			for(int i=0;i<rowsList.size();i++){
				Map<String,Object> row=rowsList.get(i);
				Object status=row.get("status");
				if(status!=null&&!"".equals(status.toString().trim())){
					row.put("statusName", PubData.getDictName("BROADCAST_STATUS", status.toString()));		
				}
				Object broType=row.get("broType");
				if(broType!=null&&!"".equals(broType.toString().trim())){
					row.put("broTypeName", PubData.getDictName("BROADCASTTYPE", broType.toString()));		
				}
				//记录文件列表
				Object tempProfile=row.get("photos");
				String designProfileName="";
				String photosName="";
				if(tempProfile!=null&&!"".equals(tempProfile.toString().trim())){
					String []tempFileArray = tempProfile.toString().split(",");
					String tempFileName="'1'";
					for(int j=0;j<tempFileArray.length;j++){
						tempFileName+=","+"'"+tempFileArray[j]+"'";
					}
					String fileQuerysql="select FILE_ID,FILE_NAME from PUB_FILE_UPLOAD where FILE_ID in ("+tempFileName+")";
					List<Map<String,Object>> tempFileList=util.getMapList(fileQuerysql, new Object[]{});
					for(int j=0;j<tempFileList.size();j++){
						designProfileName+="<a onclick=showPicVedioWindow('"+tempFileList.get(j).get("FILE_ID")+"') style='color:#0000ff;cursor:pointer;'>  " +
								tempFileList.get(j).get("FILE_NAME")+"</a>"+
								"</br>";
						photosName+=tempFileList.get(j).get("FILE_NAME");
					}
					row.put("profileName",designProfileName.toString());
					row.put("photosName",photosName.toString());
				}
			}
		}
		/**
		 * 绑定查询结果('total'和'rows'名称不能修改)
		 */
		result.put("total", util.getObjCount(sumSql.toString()));
		result.put("rows", rowsList);
		return result;
	}
	public int addBaseInfoDefenceBroadcast(BaseInfoDefenceBroadcastPo baseInfoDefenceBroadcastPo) {
		/**
		 * TODO 
		 * 添加服务忌语
		 */
		String sql = "insert into base_info_defence_broadcast " +
					 "(id, " +
					 "number,"+
					 "name," +
					 "photos," +
					 "adress," +
					 "status," +
					 "voicebroadcast," +
					 "broadcasting," +
					 "monitor_id," +
					 "charger,"+
					 "telephone,"+
					 "org_id,"+
					 "bro_type,"+
					 "the_geom,"+
					 "ip,"+
					 "port,"+
					 "lng,"+
					 "lat,"+
					 "remark"+
					 ")values( " +
					 ":id," +
					 ":number," +
					 ":name," +
					 ":photos," +
					 ":adress," +
					 ":status," +
					 ":voicebroadcast," +
					 ":broadcasting," +
					 ":monitorId," +
					 ":charger," +
					 ":telephone," +
					 ":orgId," +
					 ":broType,"+
					 "st_geomfromtext(:theGeom),"+
					 ":ip,"+
					 ":port,"+
					 ":lng," +
					 ":lat," +
					 ":remark)";
		return util.editObject(sql, baseInfoDefenceBroadcastPo);
	}
	public int updateBaseInfoDefenceBroadcast(BaseInfoDefenceBroadcastPo baseInfoDefenceBroadcastpo) {
		/**
		 * TODO 
		 * 更新广播警示柱信息
		 */
		String sql = "update base_info_defence_broadcast set " +
					 "number=:number," +
					 "name=:name," +
					 "photos=:photos," +
					 "adress=:adress," +
					 "status=:status," +
					 "voicebroadcast=:voicebroadcast," +
					 "broadcasting=:broadcasting," +
				     "monitor_id=:monitorId," +
					 "charger=:charger," +
					 "telephone=:telephone," +
					 "org_id=:orgId," +
					 "bro_type=:broType," +
					 "the_geom=st_geomfromtext(:theGeom),"+
					 "ip=:ip," +
					 "port=:port," +
					 "lng=:lng," +
					 "lat=:lat," +
					 "remark=:remark "+
					 "where id=:id";
		return util.editObject(sql, baseInfoDefenceBroadcastpo);
	}
	
	public int updateBroadcast(BroadcastPo broadcastPo) {
		/**
		 * TODO 
		 * 更新广播警示柱信息
		 */
		StringBuffer sql=new StringBuffer();
		sql.append("update base_info_defence_broadcast set " );
		sql.append( "visited_time=:visitedTime," );
		if(StringUtils.isNotEmpty(broadcastPo.getIpAddress())&&StringUtils.isNotEmpty(broadcastPo.getIpAddress())){
			sql.append( "ip=:ipAddress," );
			sql.append( "port=:port " );
			sql.append( "where number=:number " );
			logger.info("更新广播警示柱sql1 "+sql.toString());
			util.editObject(sql.toString(), broadcastPo);
		}else{
			String sql1=sql.toString();
			sql1 = sql1.substring(0,sql1.length() - 1);
			sql1+=" where number=:number ";
			logger.info("更新广播警示柱sql1 "+sql1);
			util.editObject(sql1, broadcastPo);
		}
		//view
		StringBuffer sbView=new StringBuffer();
		sbView.append("update base_info_defence_broadcast_view set " );
		sbView.append( "visited_time=:visitedTime," );
		if(StringUtils.isNotEmpty(broadcastPo.getIpAddress())&&StringUtils.isNotEmpty(broadcastPo.getIpAddress())){
			sbView.append( "ip=:ipAddress," );
			sbView.append( "port=:port " );
			sbView.append( "where number=:number " );
			logger.info("更新广播警示柱sbView "+sbView.toString());
			return util.editObject(sbView.toString(), broadcastPo);
		}else{
			String sql2=sbView.toString();
			sql2 = sql2.substring(0,sql2.length() - 1);	
			sql2+=" where number=:number ";
			logger.info("更新广播警示柱sql2 "+sql2);
			return util.editObject(sql2, broadcastPo);
		}
	}
	
	public int updateBroadcastInfoPlayStatus(BroadcastPo broadcastPo) {
		String sql="update base_info_defence_broadcast set play_status=:playStatus where ip=:ipAddress and port=:port ";
		return util.editObject(sql, broadcastPo);
	}
	public int updateBroadcastInfoTrigger(BroadcastPo broadcastPo) {
		String sql="update base_info_defence_broadcast set triggers=:trigger where ip=:ipAddress and port=:port ";
		return util.editObject(sql, broadcastPo);
	}
	public int updateBroadcastInfoSoundSourceChannel(BroadcastPo broadcastPo) {
		String sql="update base_info_defence_broadcast set sound_source_channel=:soundSourceChannel where ip=:ipAddress and port=:port ";
		return util.editObject(sql, broadcastPo);
	}
	//根据ip   port
	public Map<String, Object> findByIpPort(BroadcastPo broadcastPo) {
		// TODO Auto-generated method stub
		String sql ="select " +
				   "t.id as \"id\", " +
				   "t.number as \"number\", " +
				   "t.name as \"name\", " +
				   "t.adress as \"adress\", " +
				   "t.status as \"status\", " +
				   "t.voicebroadcast as \"voicebroadcast\", " +
				   "t.broadcasting as \"broadcasting\", " +
				   "t.monitor_id as \"monitorId\", " +
				   "t.charger as \"charger\", " +
				   "t.telephone as \"telephone\", " +
				   "t.org_id as \"orgId\", " +
				   "t.lng as \"lng\", " +
				   "t.lat as \"lat\", " +
				   "t.ip as \"ip\", " +
				   "t.port as \"port\", " +
				   "o.ORG_NAME as \"descOrgName\", " +
				   "t.bro_type as \"broType\", " +
				   "t.remark as \"remark\" ," +
				   "t.photos as \"photos\" ," +
				   "t.visited_time as \"visitedTime\" ," +
					"t.voice_file_address as \"voiceFileAddress\" ," +
					"t.voltage as \"voltage\" ," +
					"t.play_status as \"playStatus\" ," +
					"t.version_number as \"versionNumber\" ," +
					"t.tf_card_file_total as \"tfCardFileTotal\" ," +
					"t.flash_file_total as \"flashFileTotal\" ," +
					"t.tf_current_trcak as \"tfCurrentTrcak\" ," +
					"t.flash_current_trcak as \"flashCurrentTrcak\" ," +
					"t.tts_volume as \"ttsVolume\" ," +
					"t.active_report_interval as \"activeReportInterval\" ," +
					"t.led_text as \"ledText\" ," +
					"t.sound_source_channel as \"soundSourceChannel\" ," +
					"t.preset_voice_volume as \"presetVoiceVolume\" ," +
				   "b.ORG_NAME as \"orgName\" " +
				   "from base_info_defence_broadcast t " +
				   "LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
				   "LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(t.org_id,1,6) " +
				   "LEFT OUTER JOIN pub_org AS o ON d.ORG_ID = o.ORG_ID " +
				   "where 1=1 AND t.ip=:ip and t.port=:port";
			Map<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("ip", broadcastPo.getIpAddress());
			paramMap.put("port", broadcastPo.getPort());
		return util.getMapObject(sql, paramMap);
	}
	public int[] deleteBaseInfoDefenceBroadcast(List<String> idList) {
		/**
		 * TODO
		 * 删除广播警示柱信息
		 */
		String delSql = "delete from base_info_defence_broadcast where id=?";
		String delViewSql = "delete from base_info_defence_broadcast_view where id=?";
		util.batchDelete(delViewSql, idList);
		return util.batchDelete(delSql, idList);
	}
	//删除旧的关联关系
	public Integer deleteBaseInfoDefenceBroadcastAuths(String id) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		String delSql = "delete from base_info_defence_broadcast_view where id=:id ";
		params.put("id", id);
		return util.editObject(delSql, params);
	}
	//添加新的关联关系
	public int[] saveBaseInfoDefenceBroadcastAuths(List<Object[]> objList) {
		// TODO Auto-generated method stub
		String viewSql="insert into base_info_defence_broadcast_view (id,name,lng,lat,the_geom,org_id,rail_id,rail_stream_id,number,port,ip) values (?,?,?,?,st_geomfromtext(?),?,?,?,?,?,?)";
		return util.batchOperate(viewSql, objList);
	}

}
