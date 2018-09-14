/**   
 * @Package: com.pc.busniess.oaOaInfoNews.dao 
 * @author: jwl   
 * @date: 2018年5月5日 上午11:43:26 
 */
package com.pc.busniess.oaBaseInfoNews.dao;

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
import com.pc.bsp.file.dao.FileDao;
import com.pc.busniess.oaBaseInfoNews.po.OaInfoNewsPo;

/**   
 * @Package: com.pc.busniess.oaOaInfoNews.dao 
 * @author: jwl   
 * @date: 2018年5月5日 上午11:43:26 
 */
@Repository("oaInfoNewsDao")
public class OaInfoNewsDao {
	@Autowired
	private DBUtil util;
	@Autowired
	private FileDao fileDao;
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
				Object file=row.get("photos");
				String fileName="";
				String filePhotosName="";
				if(file!=null&&!"".equals(file.toString().trim())){
					String []tempFileArray = file.toString().split(",");
					String tempFileName="'1'";
					for(int j=0;j<tempFileArray.length;j++){
						tempFileName+=","+"'"+tempFileArray[j]+"'";
					}
					String fileQuerysql="select FILE_ID,FILE_NAME from PUB_FILE_UPLOAD where FILE_ID in ("+tempFileName+")";
					List<Map<String,Object>> tempFileList=util.getMapList(fileQuerysql, new Object[]{});
					for(int j=0;j<tempFileList.size();j++){
						fileName+="<a onclick=showPicVedioWindow('"+tempFileList.get(j).get("FILE_ID")+"') style='color:#0000ff;cursor:pointer;'>  " +
								tempFileList.get(j).get("FILE_NAME")+"</a>"+
								"</br>";
						filePhotosName+=tempFileList.get(j).get("FILE_NAME");
					}
					row.put("showPhotos",fileName.toString());
					row.put("photosNames",filePhotosName.toString());
				}
				// 文件Id
				Object fileId=row.get("attachment");
				if(fileId!=null&&!"".equals(fileId.toString().trim())){
					Map<String, ?> fileMap=fileDao.getFileInfo(fileId.toString());
					row.put("attachmentName",fileMap.get("fileName"));								
				}
			}
		}
		// 绑定查询结果('total'和'rows'名称不能修改)
		result.put("total", util.getObjCount(sumSql.toString()));
		result.put("rows", rowsList);
		return result;
	}

	/**
	 * @param oaInfoNewsPo
	 * @return
	 */
	public int addOaInfoNews(
			OaInfoNewsPo oaInfoNewsPo) {
		// TODO Auto-generated method stub
		String sql = "insert into base_info_news " +
				"(id, " +
				"title," +
				"abstracts," +
				"editor," +
				"publish_time," +
				"content," +
				"photos," +
				"attachment," +
				"remark" +
				")values( " +
				":id," +
				":title," +
				":abstracts," +
				":editor," +
				":publishTime," +
				":content," +
				":photos," +
				":attachment," +
				":remark)";
		return util.editObject(sql, oaInfoNewsPo);
	}

	/**
	 * @param oaInfoNewsPo
	 * @return
	 */
	public int updateOaInfoNews(
			OaInfoNewsPo oaInfoNewsPo) {
		// TODO Auto-generated method stub
		String sql = "update base_info_news set " +
				"title=:title," +
				"abstracts=:abstracts," +
				"publish_time=:publishTime," +
				"content=:content, " +
				"photos=:photos, " +
				"attachment=:attachment, " +
				"remark=:remark " +
				"where id=:id";
		return util.editObject(sql, oaInfoNewsPo);
	}

	/**
	 * @param idList
	 * @return
	 */
	public int[] deleteOaInfoNews(List<String> idList) {
		// TODO Auto-generated method stub
		String delSql = "delete from base_info_news where id=?";
		return util.batchDelete(delSql, idList);
	}
}
