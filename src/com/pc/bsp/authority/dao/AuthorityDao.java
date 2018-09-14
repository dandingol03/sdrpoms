package com.pc.bsp.authority.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pc.bsp.authority.po.Authority;
import com.pc.bsp.authority.po.AuthorityResource;
import com.pc.bsp.common.util.DBUtil;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.SqlUtil;

/**
 * @author D.Steven 我只想做个测试
 *
 */
@Repository("authorityDao")
public class AuthorityDao {

	@Autowired
	private DBUtil util;

	/**
	 * 分页查询
	 */
	public Map<String, Object> getPageList(DataGridModel dgm,
			Authority authority) {

		Map<String, Object> result = new HashMap<String, Object>(2);

		StringBuffer sumSql = new StringBuffer();
		sumSql.append("select count(1) from pub_authorities where 1=1");

		String quSql = "select " +
				"authority_id as \"uid\", " +
				"authority_id as \"authorityId\", " +
				"authority_name as \"authorityName\", " +
				"authority_desc as \"authorityDesc\", " +
				"enable as \"enable\", " +
				"is_sys as \"isSys\" " +
				"from pub_authorities where 1=1";

		StringBuffer sqlSb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		if (null != authority.getAuthorityName()
				&& !authority.getAuthorityName().equals("")) {
			sqlSb.append(" and authority_name like :authorityName");
			params.put("authorityName", "%" + authority.getAuthorityName()
					+ "%");
			sumSql.append(" and authority_name like '%")
					.append(authority.getAuthorityName()).append("%'");
		}

		String orderString = "";
		if (StringUtils.isNotBlank(dgm.getSort())) {
			orderString = SqlUtil.getOrderbySql(dgm);
		}

		// 组装分页定义
		String sql = quSql + sqlSb.toString() + orderString;
		String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(),
				dgm.getRows());

		result.put("total", util.getObjCount(sumSql.toString()));
		result.put("rows", util.getMapList(pageQuerySql, params));

		return result;
	}

	/**
	 * 保存权限
	 */
	public int saveAuthority(Authority authority) {
		String sql = "insert into pub_authorities " +
				"(authority_id, " +
				"authority_name, " +
				"authority_desc, " +
				"enable, " +
				"is_sys) "
				+ "values " +
				"(:authorityId, " +
				":authorityName, " +
				":authorityDesc, " +
				":enable, " +
				":isSys)";
		return util.editObject(sql, authority);
	}

	/**
	 * 更新权限
	 */
	public int updateAuthority(Authority authority) {
		String sql = "update pub_authorities set " +
				"authority_name = :authorityName, " +
				"authority_desc = :authorityDesc, " +
				"enable = :enable, " +
				"is_sys = :isSys " +
				"where authority_id = :authorityId";
		return util.editObject(sql, authority);
	}

	/**
	 * 批量删除权限
	 */
	public int[] delAuthorityBatch(List<String> idList) {
		String sql = "delete from pub_authorities where authority_id = ?";
		return util.batchDelete(sql, idList);
	}

	/**
	 * 批量删除角色权限对应关系
	 * 
	 * @param idList
	 * @return
	 */
	public int[] delRoleAuth(List<String> idList) {
		String sql = "delete from pub_roles_authorities where authority_id = ?";
		return util.batchDelete(sql, idList);
	}

	/**
	 * 批量删除权限资源对应关系
	 * 
	 * @param idList
	 * @return
	 */
	public int[] delAuthRes(List<String> idList) {
		String sql = "delete from pub_authorities_resources where authority_id = ?";
		return util.batchDelete(sql, idList);
	}

	/**
	 * 获取�?��资源
	 * 
	 * @param sql
	 * @param obj
	 * @return
	 */
	public List<Map<String, Object>> getAllRes(String sql, Object[] obj) {
		return (List<Map<String, Object>>) util.getMapList(sql, obj);
	}

	/**
	 * 删除指定权限的资源对应关�?
	 */
	public int delAuthRes(AuthorityResource authRes) {
		String sql = "delete from pub_authorities_resources where authority_id = :authorityId";
		return util.editObject(sql, authRes);
	}

	/**
	 * 批量保存权限资源对应关系
	 * 
	 * @param objList
	 * @return
	 */
	public int[] saveAuthRes(List<Object[]> objList) {
		String sql = "insert into pub_authorities_resources (id, authority_id, resource_id) values (?, ?, ?)";
		return util.batchOperate(sql, objList);
	}

	/**
	 * 查询某权限已经注册的资源
	 * 
	 * @param dgm
	 * @param authorityId
	 * @return
	 */
	public Map<String, Object> queryHasRegResList(DataGridModel dgm,
			String authorityId) {
		// TODO Auto-generated method stub
		// 查询结果Map
		Map<String, Object> result = new HashMap<String, Object>(2);

		// 获取记录数
		StringBuffer sumSql = new StringBuffer();
		sumSql.append("select count(1) " +
				"from pub_resources a , pub_authorities_resources b " +
				"where a.resource_id=b.resource_id and b.authority_id='"+ authorityId + "'");

		// 获取结果集
		String quSql = "select " +
				"a.resource_id as \"uid\", " +
				"a.resource_id as \"resourceId\", " +
				"a.resource_name as \"resourceName\", " +
				"a.resource_type as \"resourceType\", " +
				"a.priority as \"priority\", " +
				"a.resource_string as \"resourceString\", " +
				"a.resource_desc as \"resourceDesc\", " +
				"a.enable as \"enable\", " +
				"a.is_sys as \"isSys\" " +
				"from pub_resources a , pub_authorities_resources b " +
				"where a.resource_id=b.resource_id " +
				"and b.authority_id='"+ authorityId + "'";

		Map<String, Object> params = new HashMap<String, Object>();

		// 组装排序规则
		String orderString = "";
		if (StringUtils.isNotBlank(dgm.getSort())) {
			orderString = SqlUtil.getOrderbySql(dgm);
		}

		// 组装分页定义
		String sql = quSql + orderString;
		String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(),
				dgm.getRows());

		// 绑定查询结果('total'和'rows'名称不能修改)
		result.put("total", util.getObjCount(sumSql.toString()));
		result.put("rows", util.getMapList(pageQuerySql, params));

		return result;
	}

	/**
	 * 查询某权限未注册的资源
	 * @param dgm
	 * @param authorityId
	 * @return
	 */
	public Map<String, Object> queryNotRegResList(DataGridModel dgm,
			String authorityId) {
		// TODO Auto-generated method stub
		// 查询结果Map
		Map<String, Object> result = new HashMap<String, Object>(2);

		// 获取记录数
		StringBuffer sumSql = new StringBuffer();
		sumSql.append("select count(1) from pub_resources a where not exists " +
				"(select 1 from  pub_authorities_resources b " +
				"where a.resource_id=b.resource_id " +
				"and b.authority_id='"+ authorityId + "')");

		// 获取结果集
		String quSql = "select " +
				"a.resource_id as \"uid\", " +
				"a.resource_id as \"resourceId\", " +
				"a.resource_name as \"resourceName\", " +
				"a.resource_type as \"resourceType\", " +
				"a.priority as \"priority\", " +
				"a.resource_string as \"resourceString\", " +
				"a.resource_desc as \"resourceDesc\", " +
				"a.enable as \"enable\", " +
				"a.is_sys as \"isSys\" " +
				"from pub_resources a where not exists " +
				"(select 1 from pub_authorities_resources b " +
				"where a.resource_id=b.resource_id " +
				"and b.authority_id='"+ authorityId + "')";

		Map<String, Object> params = new HashMap<String, Object>();

		// 组装排序规则
		String orderString = "";
		if (StringUtils.isNotBlank(dgm.getSort())) {
			orderString = SqlUtil.getOrderbySql(dgm);
		}

		// 组装分页定义
		String sql = quSql + orderString;
		String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(),
				dgm.getRows());

		// 绑定查询结果('total'和'rows'名称不能修改)
		result.put("total", util.getObjCount(sumSql.toString()));
		result.put("rows", util.getMapList(pageQuerySql, params));

		return result;
	}

	/**
	 * 删除某权限的资源对应关系
	 * @param resourceIdList
	 * @param authorityId
	 */
	public void deleteAuthResource(List<String> resourceIdList,
			String authorityId) {
		// TODO Auto-generated method stub
		util.batchDelete("delete from pub_authorities_resources " +
				"where resource_id = ? and authority_id='"+authorityId+"'", resourceIdList);
	}



}
