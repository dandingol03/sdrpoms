/**   
 * @Package: com.pc.busniess.oaOaInfoNews.dao 
 * @author: jwl   
 * @date: 2018年5月5日 上午11:43:26 
 */
package com.mobile.busniess.mobileOaBaseInfoNews.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pc.bsp.common.util.DBUtil;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.JurisdictionAppendSql;
import com.pc.bsp.common.util.SqlUtil;
import com.pc.busniess.oaBaseInfoNews.po.OaInfoNewsPo;

/**   
 * @Package: com.pc.busniess.oaOaInfoNews.dao 
 * @author: jwl   
 * @date: 2018年5月5日 上午11:43:26 
 */
@Repository("mobileoaInfoNewsDao")
public class MobileOaInfoNewsDao {
	@Autowired
	private DBUtil util;

	/**
	 * @param dgm
	 * @param oaInfoNewsPo
	 * @param orgId
	 * @return
	 */
	public Map<String, Object> oaInfoNewsQueryList(DataGridModel dgm,
			OaInfoNewsPo oaInfoNewsPo,String orgId) {
		// TODO Auto-generated method stub
		// TODO 查询巡防人信息
		//结果集 分为2个 第一个为总数 total  第二为详细的rows 
		Map<String, Object> result = new HashMap<String, Object>(2);
		//统计数据总数语句
		StringBuffer sumSql = new StringBuffer();
		
		 String quSql="";
		     sumSql.append("select count(1) from base_info_news t "
		    		+ "LEFT OUTER JOIN pub_users AS c ON t.editor = c.USER_ID " 
		     		+ "where 1=1 "
		     		 );
		//统计数据详情语句
			  quSql = "select " +
					"t.id as \"id\", " +
					"t.title as \"title\", " +
					"t.abstracts as \"abstracts\", " +
					"t.editor as \"editor\", " +
					"t.publish_time as \"publishTime\", " +
					"t.content as \"content\", " +
					"t.photos as \"photos\", " +
					"t.attachment as \"attachment\", " +
					"t.remark as \"remark\"," +
					"c.USER_NAME as \"userName\" " +
					"from base_info_news t " +
					"LEFT OUTER JOIN pub_users AS c ON t.editor = c.USER_ID " +
					"where 1=1 ";
		// 组装查询条件
		StringBuffer sqlSb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();
		//按照标题查询通知公告信息
		if (null !=oaInfoNewsPo.getTitle()&& !oaInfoNewsPo.getTitle().equals("")) {
			sqlSb.append(" and t.title like :title");
			params.put("title", "%" +oaInfoNewsPo.getTitle()+ "%");
			sumSql.append(" and t.title like '%").append(oaInfoNewsPo.getTitle()).append("%'");
		}
		String andquSql="";
		if(!StringUtils.equals("110", orgId)) {  
			 sumSql.append(JurisdictionAppendSql.appendSql(orgId));
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
		
		//查询字典
		List<Map<String,Object>> rowsList=util.getMapList(pageQuerySql, params);
		if(rowsList!=null&&rowsList.size()>0){
			for(int i=0;i<rowsList.size();i++){
				Map<String,Object> row=rowsList.get(i);
				//正文照片
				Object tempProfile=row.get("photos");
				List<String> tempProfileList =new ArrayList<String>();
				if(tempProfile!=null&&!"".equals(tempProfile.toString().trim())){
					String []tempFileArray = tempProfile.toString().split(",");
					for(int j=0;j<tempFileArray.length;j++){
						tempProfileList.add("/file/showPicFile?fileId="+tempFileArray[j]);
					}
					row.put("photoUrls",tempProfileList);
				}
				// 文件Id
				Object tempProfiles=row.get("attachment");
				List<String> tempProfilesList =new ArrayList<String>();
				if(tempProfiles!=null&&!"".equals(tempProfiles.toString().trim())){
					String []tempFileArray = tempProfiles.toString().split(",");
					for(int j=0;j<tempFileArray.length;j++){
						tempProfilesList.add("/file/showFile?fileId="+tempFileArray[j]);
					}
					//文件下载
					row.put("fileDownload",tempProfilesList);
				}
			}
		}
		// 绑定查询结果('total'和'rows'名称不能修改)
		result.put("total", util.getObjCount(sumSql.toString()));
		result.put("rows", rowsList);
		return result;
	}
}
