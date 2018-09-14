package com.mobile.busniess.mobileOaAddressBook.dao;

/**
 * pc端通讯录基本库操作
 * @author  c.xk
 * @version 1.0
 * @since   1.0
 */

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pc.bsp.common.util.DBUtil;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.SqlUtil;
import com.pc.busniess.oaAddressBook.po.OaInfoAddressBookPo;

@Repository("mobileOaInfoAddressBookDao")
public class MobileOaInfoAddressBookDao{
	
	@Autowired
	private DBUtil util;
	/**
	 * 查询通讯录列表
	 * @param dgm 分页信息以及排序信息 @see DataGridModel
	 * @param userId 
	 * @param oaInfoAddressBookQueryList @see OaInfoAddressBookPo
	 * @return 返回查询的通讯录相关信息
	 */

	public Map<String, Object> oaInfoAddressBookQueryList(DataGridModel dgm, OaInfoAddressBookPo oaInfoAddressBookPo, String userId) {
		// TODO 查询公文标题信息
		//结果集 分为2个 第一个为总数 total  第二为详细的rows 
		Map<String, Object> result = new HashMap<String, Object>(2);
		
		//统计数据总数语句
		StringBuffer sumSql = new StringBuffer();
		sumSql.append("select count(1) from oa_addressbook t  "
				+ "LEFT OUTER JOIN pub_users AS u ON u.USER_ID = t.user_id where 1=1");
		
		//统计数据详情语句 
		String quSql = "select " +
				"t.id as \"id\", " +                      	//ID
				"t.user_id as \"userId\", " +               //用户id
				"u.USER_ORG as \"orgId\", " +			  	//所属机构org_id
				"o.ORG_NAME as \"orgName\", " +           	//组织名称
				"u.USER_NAME as \"name\", " +              	//姓名
				"t.unit_name as \"unitName\", " +           //单位名
				"u.USER_DUTY as \"duty\", " +               //职务
				"u.USER_TELEPHONE as \"telephone\", " +     //联系电话
				"u.MAIL as \"email\", " +              		//邮箱
				"u.QQ_WEIXIN as \"qq\", " +            		//qq或者微信
				"t.adress as \"adress\", " +          		//地址
				"t.postalcode as \"postalcode\", " +  		//邮编
				"od.ORG_NAME as \"descOrgName\", " +
				"t.remark as \"remark\" " +            		//备注
				"from oa_addressbook t  " +            		//所查表  
				"LEFT OUTER JOIN pub_users AS u ON u.USER_ID = t.user_id " +
				"LEFT OUTER JOIN pub_org AS o ON u.USER_ORG = o.ORG_ID " +
				"LEFT OUTER JOIN pub_org_desc AS d ON d.ORG_ID = SUBSTR(u.USER_ORG,1,6) " +
				"LEFT OUTER JOIN pub_org AS od ON d.ORG_ID = od.ORG_ID " +
				"where 1=1 ";

		// 组装查询条件
		StringBuffer sqlSb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();
		//去掉登陆者
		if (null != userId&& !userId.equals("")) {
			sqlSb.append(" and t.user_id != :userId");
			params.put("userId",userId);
			sumSql.append(" and t.user_id != '").append(userId).append("'");
		}
		//按照标题查询公文信息
		if (null != oaInfoAddressBookPo.getName()&& !oaInfoAddressBookPo.getName().equals("")) {
			sqlSb.append(" and u.USER_NAME like :name");
			params.put("name", "%" + oaInfoAddressBookPo.getName()+ "%");
			sumSql.append(" and u.USER_NAME like '%").append(oaInfoAddressBookPo.getName()).append("%'");
		}
		//机构筛选
		if (null != oaInfoAddressBookPo.getUserOrg()&& !oaInfoAddressBookPo.getUserOrg().equals("")) {
			sqlSb.append(" and u.USER_ORG like :userOrg");
			params.put("userOrg", "%" + oaInfoAddressBookPo.getUserOrg()+ "%");
			sumSql.append(" and u.USER_ORG like '%").append(oaInfoAddressBookPo.getUserOrg()).append("%'");
		}
		// 组装排序规则
		String orderString = "";
		if (StringUtils.isNotBlank(dgm.getSort())) {
			orderString = SqlUtil.getOrderbySql(dgm);
		}
		String pageQuerySql = "";
		// 组装分页定义
		String sql = quSql + sqlSb.toString() + orderString;
		if(dgm.getPage()<=0&dgm.getPage()<=0){
			result.put("rows", util.getMapList(sql, params));
		}else{
			pageQuerySql=SqlUtil.getPageQuerySql(sql, dgm.getPage(), dgm.getRows());	
			result.put("rows", util.getMapList(pageQuerySql, params));
		}
		
		
		// 绑定查询结果('total'和'rows'名称不能修改)
		result.put("total", util.getObjCount(sumSql.toString()));

		return result;
	}
}
