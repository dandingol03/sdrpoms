package com.pc.busniess.oaDocument.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pc.bsp.common.util.DBUtil;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.PubData;
import com.pc.bsp.common.util.SqlUtil;
import com.pc.busniess.oaDocument.po.OaInfoDocumentPo;
/**
 * 回收站
 * @author jwl
 * Dao
 */

@Repository("oaInfoDelectedDocumentDao")
public class OaInfoDelectedDocumentDao{
	
	@Autowired
	private DBUtil util;
	/**
	 * 连接结果集   找出删除类别  
	 * @param dgm
	 * @param oaInfoDocumentPo
	 * @return
	 */
	public Map<String, Object> deletedocumentQueryList(DataGridModel dgm,OaInfoDocumentPo oaInfoDocumentPo) {
		// TODO 查询公文标题信息
		//结果集 分为2个 第一个为总数 total  第二为详细的rows 
				Map<String, Object> result = new HashMap<String, Object>(2);
				
				//统计数据总数语句
				StringBuffer sumSql = new StringBuffer();
				sumSql.append("select count(1) from oa_document t where 1=1 AND is_delete='1'  ");
				//统计数据总数语句
				StringBuffer sumSql1 = new StringBuffer();
				sumSql1.append("select count(1) from oa_document t where 1=1  AND is_delete_out='1'  ");
//				
				
				//统计数据详情语句 
				String quSql= "select * from ("
						+ "select " +
						"t.id as \"id\", " +                           	 	 //id
						"t.title as \"title\", " +							 //标题
						"t.send_time as \"sendTime\", " +               	 //发件日期
						"t.is_approved_oainfo as \"isApprovedOainfo\", " +   //删除箱状态
						"t.state as \"state\", " + 			            	 //类型
						"t.hair_unit as \"hairUnit\", " + 			   		 //发件单位
						"t.receive_user_id as \"receiveUserId\",  " +   	 //发件人id
						"c.USER_NAME as \"receiveUserIdName\" "+
						"from oa_document t " +
						"LEFT OUTER JOIN pub_users AS c ON t.receive_user_id = c.USER_ID " +
						"where 1=1 AND is_delete='1' AND send_user_id=:sendUserId "+
						"  UNION ALL "+
						"select " +
						"t.id as \"id\", " +                               //id
						"t.title as \"title\", " +						   //标题
						"t.send_time as \"sendTime\", " +                  //发件日期
						"t.is_approved as \"isApprovedOainfo\", " + 	   //删除箱状态
						"t.state as \"state\", " + 			               //类型
						"t.hair_unit as \"hairUnit\", " + 			       //发件单位
						"t.receive_user_id as \"receiveUserId\",  " +      //发件人id
						"c.USER_NAME as \"receiveUserIdName\" "+
						"from oa_document t " +
						"LEFT OUTER JOIN pub_users AS c ON t.receive_user_id = c.USER_ID " +
						"where 1=1 AND is_delete_out='1' AND receive_user_id=:receiveUserId " + 
						" ) as oa_document_derive where 1=1";

				// 组装查询条件
				StringBuffer sqlSb = new StringBuffer();
				Map<String, Object> params = new HashMap<String, Object>();
				if (null !=oaInfoDocumentPo.getSendUserId()&& !oaInfoDocumentPo.getSendUserId().equals("")&&
						null !=oaInfoDocumentPo.getReceiveUserId()&& !oaInfoDocumentPo.getReceiveUserId().equals("")) {
					params.put("sendUserId", oaInfoDocumentPo.getSendUserId());
					params.put("receiveUserId", oaInfoDocumentPo.getReceiveUserId());
					sumSql.append(" and t.send_user_id = '"+oaInfoDocumentPo.getSendUserId()+"'");
					sumSql1.append(" and t.receive_user_id = '"+oaInfoDocumentPo.getReceiveUserId()+"'");
				}
				if (null != oaInfoDocumentPo.getTitle()&& !oaInfoDocumentPo.getTitle().equals("")) {
					sqlSb.append(" and title like :title");
					params.put("title", "%" + oaInfoDocumentPo.getTitle()+ "%");
					sumSql.append(" and t.title like '%").append(oaInfoDocumentPo.getTitle()).append("%'");
					sumSql1.append(" and t.title like '%").append(oaInfoDocumentPo.getTitle()).append("%'");
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
						//文件类别
						Object state=row.get("state");
						if(state!=null&&!"".equals(state.toString().trim())){
							row.put("stateName", PubData.getDictName("WJFL", state.toString()));		
						}
						}
					}
				
				// 绑定查询结果('total'和'rows'名称不能修改)
				result.put("total", util.getObjCount(sumSql.toString())+util.getObjCount(sumSql1.toString()));
				result.put("rows", rowsList);

				return result;
	}
}
