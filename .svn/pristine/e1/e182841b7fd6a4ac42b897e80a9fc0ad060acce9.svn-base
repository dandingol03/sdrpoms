/**   
 * @Package: com.mobile.exhibition.map.service 
 * @author: jwl   
 * @date: 2018年5月14日 下午5:48:45 
 */
package com.mobile.exhibition.map.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pc.bsp.common.util.CommonUtil;
import com.pc.bsp.common.util.DBUtil;
import com.pc.bsp.common.util.PubData;

/**   
 * @Package: com.mobile.exhibition.map.service 
 * @author: jwl   
 * @date: 2018年5月14日 下午5:48:45 
 */
@Repository("mobileExMapDao")
public class MobileExMapDao {

	@Autowired
	private DBUtil util;

	/**
	 * @param rangeDanger
	 * @return
	 */
	public List<Map<String, Object>> mobileExhibition(String rangeDanger) {
		// TODO Auto-generated method stub
		StringBuffer appendSql=new StringBuffer();
		String sql=
			"SELECT "+
					"t.id as \"id\"," +
					"t.name as \"name\"," +
					"t.description as \"description\"," +
					"t.photos as \"photos\"," +
					"t.rail_id as \"railId\"," +
					"t.middle as \"middle\"," +
					"t.org_id as \"orgId\"," +
					"t.lng as \"lng\"," +
					"t.lat as \"lat\"," +
					"t.charger as \"charger\"," +
					"t.telephone as \"telephone\"," +
					"t.report_user_id as \"reportUserId\"," +
					"t.treat_user_id as \"treatUserId\"," +
					"t.report_time as \"reportTime\"," +
					"t.handle_status as \"handleStatus\"," +
					"t.handle_way as \"handleWay\"," +
					"t.result_dis as \"resultDis\"," +
					"t.photoss as \"photoss\"," +
					"t.danger_type as \"dangerType\"," +
					"c.name as \"railName\", " +
					"b.ORG_NAME as \"orgName\"," +
					"report.USER_NAME as \"reportUserName\","+
					"treat.USER_NAME as \"treatUserName\","+
					"o.ORG_NAME as \"descOrgName\", " +
					"t.remark as \"remark\" " +
					"from patrol_danger_info t " +
					"LEFT OUTER JOIN base_info_rail AS c ON t.rail_id = c.id " +
					"LEFT OUTER JOIN pub_org AS b ON t.org_id = b.ORG_ID " +
					"LEFT OUTER JOIN pub_users AS report ON t.report_user_id = report.USER_ID " +
					"LEFT OUTER JOIN pub_users AS treat ON t.treat_user_id = treat.USER_ID " +
					"LEFT OUTER JOIN pub_org_desc AS dd ON dd.ORG_ID = SUBSTR(t.org_id,1,6) " +
					"LEFT OUTER JOIN pub_org AS o ON dd.ORG_ID = o.ORG_ID ";
		if(StringUtils.isNotBlank(rangeDanger)){
			appendSql.append(" WHERE ");
			appendSql.append(" mbrcontains ( ");
			appendSql.append(" ST_GeomFromText( '"+rangeDanger+"' ) , ");
			appendSql.append(" `t`.`the_geom` ");
			appendSql.append(" ) = 1 ");
		}
		List<Map<String,Object>> rowsList=util.getMapList(sql+appendSql, new HashMap<String, Object>());
		if(rowsList!=null&&rowsList.size()>0){
			for(int i=0;i<rowsList.size();i++){
				Map<String,Object> row=rowsList.get(i);
				//中心里程
				Object middle=row.get("middle");
				if(null!=middle&&!"".equals(middle.toString().trim())){
					row.put("middleStr", CommonUtil.KM2MStrbyDec(middle.toString()));
					String [] middleArr=CommonUtil.M2KMStrbyDec(middle.toString());
					row.put("middleKM", middleArr[0]);
					row.put("middleM", middleArr[1]);
				}
				Object handleStatus=row.get("handleStatus");
				if(handleStatus!=null&&!"".equals(handleStatus.toString().trim())){
					row.put("handleStatusName", PubData.getDictName("PATROL_HANDLE_STATUS", handleStatus.toString()));		
				}
				//隐患类别
				Object dangerType=row.get("dangerType");
				if(dangerType!=null&&!"".equals(dangerType.toString().trim())){
					row.put("dangeTypeName", PubData.getDictName("DANGERS_TYPE", dangerType.toString()));		
				}
				//上报照片
				Object tempProfile=row.get("photos");
				List<String> tempProfileList =new ArrayList<String>();
				if(tempProfile!=null&&!"".equals(tempProfile.toString().trim())){
					String []tempFileArray = tempProfile.toString().split(",");
					for(int j=0;j<tempFileArray.length;j++){
						tempProfileList.add("/file/showPicFile?fileId="+tempFileArray[j]);
					}
					//上报照片
					row.put("photoUrls",tempProfileList);
				}
				//处置照片
				Object tempProfiles=row.get("photoss");
				List<String> tempProfilesList =new ArrayList<String>();
				if(tempProfiles!=null&&!"".equals(tempProfiles.toString().trim())){
					String []tempFileArray = tempProfiles.toString().split(",");
					for(int j=0;j<tempFileArray.length;j++){
						tempProfilesList.add("/file/showPicFile?fileId="+tempFileArray[j]);
					}
					//处置照片
					row.put("resultPhotoUrls",tempProfilesList);
				}
				
			}
		}
		return rowsList;
	}
}
