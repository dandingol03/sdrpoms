package com.pc.busniess.oaDocument.dao;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pc.bsp.common.util.ConverterUtils;
import com.pc.bsp.common.util.DBUtil;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.PubData;
import com.pc.bsp.common.util.SqlUtil;
import com.pc.busniess.oaDocument.po.OaInfoDocumentPo;
/**
 * 收件箱
 * @author jwl
 * Dao
 */
@Repository("oaInfoDocumentDao")
public class OaInfoDocumentDao{
	
	@Autowired
	private DBUtil util;

	/**
	 * 发送人的查询
	 * @return
	 */
	public List<Map<String, Object>> queryPers() {
		//统计数据详情语句
		String quSql = "select " +
				"t.USER_ID as \"id\", " +
				"t.USER_NAME as \"name\"  " +
				"from pub_users t " +
				"where 1=1 ";
		return util.getMapList(quSql, new HashMap<String, Object>());
	}
	/**
	 * 收件箱查询 查询登录者 收件
	 * @param dgm
	 * @param oaInfoDocumentPo
	 * @return
	 */
	public Map<String, Object> oaInfoDocumentQueryList(DataGridModel dgm, OaInfoDocumentPo oaInfoDocumentPo) {
		// TODO 查询公文标题信息
		//结果集 分为2个 第一个为总数 total  第二为详细的rows 
		Map<String, Object> result = new HashMap<String, Object>(2);
		
		//统计数据总数语句
		StringBuffer sumSql = new StringBuffer();
		sumSql.append("select count(1) from oa_document t where 1=1  and is_delete= 0 and is_drafts =1  ");
		
		//统计数据详情语句 
		String quSql = "select " +
				"t.id as \"id\", " +                            //id
				"t.title as \"title\", " +						//标题
				"t.send_time as \"sendTime\", " +               //发件日期
				"t.is_approved as \"isApproved\", " + 			//阅读状态
				"t.state as \"state\", " + 			            //类型
				"t.hair_unit as \"hairUnit\", " + 			    //发件单位
				"t.send_user_id as \"sendUserId\",  " +  		//收件人id
				"t.receive_user_id as \"receiveUserId\",  " +   //发件人id
				"t.send_state as \"sendState\",  " +  		    //发送状态
				"t.file as \"file\",  " +  		                //附件
				"t.is_drafts as \"isDrafts\",  " +  		    //是否草稿
				"c.USER_NAME as \"sendUserIdName\","+           //收件人名
				"a.USER_NAME as \"receiveUserIdName\","+        //收件人名
				"t.editor as \"editor\" "+                      //内容
				"from oa_document t " +
				"LEFT OUTER JOIN pub_users AS c ON t.send_user_id = c.USER_ID " +
				"LEFT OUTER JOIN pub_users AS a ON t.receive_user_id = a.USER_ID " +
				"where 1=1 and is_delete= 0 and is_drafts =1 and send_user_id=:sendUserId";

		// 组装查询条件
		StringBuffer sqlSb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();
		if (null !=oaInfoDocumentPo.getSendUserId()&& !oaInfoDocumentPo.getSendUserId().equals("")) {
			params.put("sendUserId", oaInfoDocumentPo.getSendUserId());
			sumSql.append(" and t.send_user_id = '"+oaInfoDocumentPo.getSendUserId()+"'");
		}
		//按照名称标题信息
		if (null != oaInfoDocumentPo.getTitle()&& !oaInfoDocumentPo.getTitle().equals("")) {
			sqlSb.append(" and t.title like :title");
			params.put("title", "%" + oaInfoDocumentPo.getTitle()+ "%");
			sumSql.append(" and t.title like '%").append(oaInfoDocumentPo.getTitle()).append("%'");
		}
		// 组装排序规则
		String orderString = "";
		if (StringUtils.isNotBlank(dgm.getSort())) {
			orderString = SqlUtil.getOrderbySql(dgm);
		}
		
		// 组装分页定义
		String sql = quSql + sqlSb.toString() + orderString;
		String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(), dgm.getRows());
		List<Map<String,Object>> rowsList=util.getMapList(pageQuerySql, params);
		if(rowsList!=null&&rowsList.size()>0){
			for(int i=0;i<rowsList.size();i++){
				Map<String,Object> row=rowsList.get(i);
				Object state=row.get("state");
				if(state!=null&&!"".equals(state.toString().trim())){
					row.put("stateName", PubData.getDictName("WJFL", state.toString()));		
				}
				//记录文件列表
				Object tempProfile=row.get("file");
				String designProfileName="";
				String fileName="";//文件名获取
				String fileDownload="";
				if(tempProfile!=null&&!"".equals(tempProfile.toString().trim())){
					String []tempFileArray = tempProfile.toString().split(",");
					String tempFileName="'1'";
					for(int j=0;j<tempFileArray.length;j++){
						tempFileName+=","+"'"+tempFileArray[j]+"'";
					}
					String fileQuerysql="select FILE_ID,FILE_NAME from PUB_FILE_UPLOAD where FILE_ID in ("+tempFileName+")";
					List<Map<String,Object>> tempFileList=util.getMapList(fileQuerysql, new Object[]{});
					for(int j=0;j<tempFileList.size();j++){
//						designProfileName+="<a onclick=showPicVedioWindow('"+tempFileList.get(j).get("FILE_ID")+"') style='color:#0000ff;cursor:pointer;'>  " +
//								tempFileList.get(j).get("FILE_NAME")+"</a>"+
//								"</br>";
						fileDownload+="<a href='file/showFile2?fileId="+tempFileList.get(j).get("FILE_ID")+"'>"+tempFileList.get(j).get("FILE_NAME")+"</a>";
						fileName+=tempFileList.get(j).get("FILE_NAME");
					}
					row.put("profileName",designProfileName.toString());
					row.put("fileName",fileName.toString());
					row.put("fileDownload",fileDownload.toString());
				}
			}
		}
		
		// 绑定查询结果('total'和'rows'名称不能修改)
		result.put("total", util.getObjCount(sumSql.toString()));
		result.put("rows", rowsList);

		return result;
	}
	/**
	 *假删除修改状态   收件箱的删除
	 * @param idList
	 * @return
	 */
	public int[] deleteOaInfoDocument(List<String> idList) {
		// TODO 删除公文信息
		String delSql = "update oa_document set is_delete ='1',is_approved_oainfo='2' where id = ?";
		return util.batchDelete(delSql, idList);
	}
	public Integer anUnreadMailCount(
		OaInfoDocumentPo oaInfoDocumentPo) {
	    // TODO Auto-generated method stub
	    String sql = "select count(1) as mailCount  from oa_document t where 1=1"
	    	+ "  and is_delete= 0 "
	    	+ "  and is_approved= 1 "
	    	+ "and is_drafts =1 "
	    	+ "and send_user_id=:userId ";
	    Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", oaInfoDocumentPo.getSendUserId());
		Map<String,Object> mailCount = util.getMapObject(sql, paramMap);
		return ConverterUtils.toInteger(mailCount.get("mailCount"));
	}
	
}