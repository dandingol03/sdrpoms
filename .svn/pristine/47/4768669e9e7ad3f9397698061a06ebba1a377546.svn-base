package com.pc.busniess.oaAddressBook.dao;

/**
 * pc端通讯录基本库操作
 * @author  c.xk
 * @version 1.0
 * @since   1.0
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pc.bsp.common.util.DBUtil;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.SqlUtil;
import com.pc.busniess.oaAddressBook.po.OaInfoAddressBookPo;

@Repository("oaInfoAddressBookDao")
public class OaInfoAddressBookDao{
	
	@Autowired
	private DBUtil util;
	/**
	 * 查询通讯录列表
	 * @param dgm 分页信息以及排序信息 @see DataGridModel
	 * @param oaInfoAddressBookQueryList @see OaInfoAddressBookPo
	 * @return 返回查询的通讯录相关信息
	 */

	public Map<String, Object> oaInfoAddressBookQueryList(DataGridModel dgm, OaInfoAddressBookPo oaInfoAddressBookPo) {
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
		
		// 组装分页定义
		String sql = quSql + sqlSb.toString() + orderString;
		String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(), dgm.getRows());
		
		// 绑定查询结果('total'和'rows'名称不能修改)
		result.put("total", util.getObjCount(sumSql.toString()));
		result.put("rows", util.getMapList(pageQuerySql, params));

		return result;
	}
	/**
	 * 添加通讯录信息
	 * @param dgm 分页信息以及排序信息 @see DataGridModel
	 * @param addOaInfoAddressBook @see OaInfoAddressBookPo
	 * @return 返回添加通讯录
	 */    
	public int addOaInfoAddressBook(OaInfoAddressBookPo oaInfoAddressBookPo) {
	
		// TODO 添加通讯录
		String sql = "insert into oa_addressbook " +
				"(id, " +                 
				"user_id," +
				"unit_name," +
				"adress," +
				"postalcode," +
				"remark" +                
				")values( " +
				":id," +
				":userId," +
				":unitName," +
				":adress," +
				":postalcode," +
				":remark)";
		return util.editObject(sql, oaInfoAddressBookPo);
	}
	
	/**
	 * 修改通讯录信息
	 * @param dgm 分页信息以及排序信息 @see DataGridModel
	 * @param updateOaInfoAddressBook @see OaInfoAddressBookPo
	 * @return 返回添加通讯录
	 */   
	public int updateOaInfoAddressBook(OaInfoAddressBookPo oaInfoAddressBookPo) {
		// TODO 更新通讯录信息  
		String sql = "update oa_addressbook set " +     
				"user_id=:userId," +   
				"unit_name=:unitName," + 
				"adress=:adress," + 
				"postalcode=:postalcode," + 
				"remark=:remark "+                      
				"where id=:id";
		return util.editObject(sql, oaInfoAddressBookPo);
	}
	/**
	 * 删除通讯录信息
	 * @param dgm 分页信息以及排序信息 @see DataGridModel
	 * @param deleteOaInfoAddressBook @see OaInfoAddressBookPo
	 * @return 删除通讯录
	 */  
	public int[] deleteOaInfoAddressBook(List<String> idList) {
		// TODO 删除通讯录信息
		String delSql = "delete from oa_addressbook where id=?";
		return util.batchDelete(delSql, idList);
	}
	/**
	 * @param userId 
	 * @return
	 */
	public List<Map<String, Object>> queryPers(String userId) {
		//统计数据详情语句
			String quSql;
			if(StringUtils.isNotBlank(userId)){
				quSql = "select " +
						"t.USER_ID as \"id\", " +
						"t.USER_NAME as \"name\"  " +
						"from pub_users t " +
						"where 1=1 and t.USER_ID not in( "+
						"SELECT DISTINCT " + 
						"(a.user_id) " + 
						"FROM " + 
						"oa_addressbook a  where a.USER_ID != ? )";
				return util.getMapList(quSql, new Object[]{userId});
			}else{
				quSql= "select " +
						"t.USER_ID as \"id\", " +
						"t.USER_NAME as \"name\"  " +
						"from pub_users t " +
						"where 1=1 and t.USER_ID not in( "+
						"SELECT DISTINCT " + 
						"(a.user_id) " + 
						"FROM " + 
						"oa_addressbook a) ";
				return util.getMapList(quSql, new Object[]{});
			}
	}
	//查询用户信息
	public Map<String, Object> findbyUserId(String userId) {
		// TODO Auto-generated method stub
		String sql = "select " +
				"t.USER_ID as \"id\", " +
				"t.USER_ORG as \"orgId\", " +
				"t.MAIL as \"mail\", " +
				"t.USER_TELEPHONE as \"telephone\", " +
				"t.QQ_WEIXIN as \"QW\", " +
				"t.USER_DUTY as \"duty\", " +
				"od.ORG_NAME as \"orgName\", " +
				"t.USER_NAME as \"name\"  " +
				"from pub_users t " +
				"LEFT OUTER JOIN pub_org AS od ON t.USER_ORG  = od.ORG_ID " +
				"where 1=1 "+
				"and t.USER_ID=? ";
		
		return util.getMapObject(sql, new Object[]{userId});
	}
}
