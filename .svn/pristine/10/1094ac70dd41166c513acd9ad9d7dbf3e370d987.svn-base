package com.pc.bsp.role.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.pc.bsp.common.util.DBUtil;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.SqlUtil;
import com.pc.bsp.org.po.OrgDesc;
import com.pc.bsp.role.po.Role;
import com.pc.bsp.role.po.RoleAuthority;
import com.pc.bsp.security.po.PubUsers;

/**
 * @author simple
 *
 */
@Repository("roleDao")
public class RoleDao {

	@Autowired
	private DBUtil util;

	/**
	 * 分页查询角色
	 */
	public Map<String, Object> getPageList(DataGridModel dgm, Role role) {

		// 查询结果Map
		Map<String, Object> result = new HashMap<String, Object>(2);

		OrgDesc orgDesc = null;
		// 获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			// 根据登录用户的机构编号(userOrg)获取机构描述对象
			orgDesc = util.getOrgDescByOrgId(((PubUsers) principal).getUserOrg());
		}
		int roleLevel = 8; // 默认为最低的角色级别
		if (orgDesc != null) {
			// 根据登录用户的所在机构计算角色级别
			roleLevel = (orgDesc.getId().length()) / 4;
		}

		// 获取记录数
		StringBuffer sumSql = new StringBuffer();
		sumSql.append("select count(1) from pub_roles where role_level >= " + roleLevel);

		// 获取结果集
		String quSql = "select " +
				"role_id as \"uid\", " +
				"role_id as \"roleId\", " +
				"role_name as \"roleName\", " +
				"role_desc as \"roleDesc\", " +
				"role_level as \"roleLevel\", " +
				"enable as \"enable\", " +
				"is_sys as \"isSys\" " +
				"from pub_roles where role_level >= "+ roleLevel;

		// 组装查询条件
		StringBuffer sqlSb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		// 点击查询按钮时组装查询语句
		if (null != role.getRoleName() && !role.getRoleName().equals("")) {
			sqlSb.append(" and role_name like :roleName");
			params.put("roleName", "%" + role.getRoleName() + "%");
			sumSql.append(" and role_name like '%").append(role.getRoleName()).append("%'");
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
	 * 保存角色
	 */
	public int saveRole(Role role) {
		String sql = "insert into pub_roles " +
				"(role_id, " +
				"role_name, " +
				"role_desc, " +
				"role_level, " +
				"enable, " +
				"is_sys) "
				+ "values (" +
				":roleId, " +
				":roleName, " +
				":roleDesc, " +
				":roleLevel, " +
				":enable, " +
				":isSys)";
		return util.editObject(sql, role);
	}

	/**
	 * 根据角色名称获取角色对象
	 * 
	 * @param roleName
	 * @return
	 */
	public Role getRoleByName(String roleName) {
		String sql = "select * from pub_roles where role_name='" + roleName + "'";
		return (Role) util.getObject(sql, new Object[] {}, Role.class);
	}

	/**
	 * 修改角色
	 */
	public int updateRole(Role role) {
		String sql = "update pub_roles set " +
				"role_name = :roleName, " +
				"role_desc = :roleDesc, " +
				"role_level = :roleLevel, " +
				"enable = :enable, " +
				"is_sys = :isSys " +
				"where role_id = :roleId";
		return util.editObject(sql, role);
	}

	/**
	 * 批量删除角色
	 */
	public int[] delRoleBatch(List<String> idList) {
		String sql = "delete from pub_roles where role_id = ?";
		return util.batchDelete(sql, idList);
	}

	/**
	 * 批量删除[角色-用户]对应关系
	 * 
	 * @param idList
	 * @return
	 */
	public int[] delUserRole(List<String> idList) {
		String sql = "delete from pub_users_roles where role_id = ?";
		return util.batchDelete(sql, idList);
	}

	/**
	 * 批量删除[角色-权限]对应关系
	 * 
	 * @param idList
	 * @return
	 */
	public int[] delRoleAuth(List<String> idList) {
		String sql = "delete from pub_roles_authorities where role_id = ?";
		return util.batchDelete(sql, idList);
	}

	/**
	 * 获取选中角色没有的所有权限
	 * 
	 * @param sql
	 * @param obj
	 * @return
	 */
	public List<Map<String, Object>> getAllAuths(String sql, Object[] obj) {
		return (List<Map<String, Object>>) util.getMapList(sql, obj);
	}

	/**
	 * 根据RoleAuthority对象删除角色权限对应关系
	 */
	public int delRoleAuths(RoleAuthority roleAuth) {
		String sql = "delete from pub_roles_authorities where role_id = :roleId";
		return util.editObject(sql, roleAuth);
	}

	/**
	 * 批量保存角色权限对应关系
	 * 
	 * @param objList
	 * @return
	 */
	public int[] saveRoleAuths(List<Object[]> objList) {
		String sql = "insert into pub_roles_authorities (id, role_id, authority_id) values (?, ?, ?)";
		return util.batchOperate(sql, objList);
	}

}
