/**
 * 
 */
package com.pc.bsp.user.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.pc.bsp.common.util.DBUtil;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.PubData;
import com.pc.bsp.common.util.SqlUtil;
import com.pc.bsp.org.po.OrgDesc;
import com.pc.bsp.security.po.PubUsers;
import com.pc.bsp.user.po.User;
import com.pc.bsp.user.po.UserRole;

/**
 * @author simple
 *
 */
@SuppressWarnings("deprecation")
@Repository("userDao")
public class UserDao {

	@Autowired
	private DBUtil util;

	// 对用户输入的密码进行MD5编码
	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * 根据id用户详细信息
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> findById(String id) {
		String sql = "select * from pub_users where user_id = :id";
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		return (Map<String, Object>) util.getMap(sql, paramMap);
	}
	/**
	 * 添加用户
	 * 
	 * @param user
	 * @return
	 */
	public int saveUser(User user) {
		String sql = "insert into pub_users " +
				"(user_id, " +
				"user_account, " +
				"user_name, " +
				"user_password, " +
				"user_gender, " +
				"user_birthday, " +
				"user_org, " +
				"user_duty, " +
				"user_telephone, " +
				"user_department, " +
				"mail, " +
				"qq_weixin, " +
				"user_desc, " +
				"enable, " +
				"is_sys, " +
				"lng, " +
				"lat) " +
				"values (" +
				":userId, " +
				":userAccount, " +
				":userName, " +
				":userPassword, " +
				":userGender, " +
				":userBirthday, " +
				":userOrg, " +
				":userDuty, " +
				":userTelephone, " +
				":userDepartment, " +
				":mail, " +
				":qqWeixin, " +
				":userDesc, " +
				":enable, " +
				":isSys, " +
				":lng, " +
				":lat)";
		// 盐值加密
		String password = passwordEncoder.encodePassword
				(user.getUserPassword().trim(), user.getUserAccount().trim());
		user.setUserPassword(password);
		return util.editObject(sql, user);
	}

	/**
	 * 根据userId删除用户
	 * 
	 * @param userId
	 * @return
	 */
	public int delUserById(String userId) {
		String sql = "delete from pub_users where user_id = :userId";
		User user = new User();
		user.setUserId(userId);
		return util.editObject(sql, user);
	}

	/**
	 * 批量删除用户
	 * 
	 * @param idList
	 * @return
	 */
	public int[] delUserBatch(List<String> idList) {
		String sql = "delete from pub_users where user_id = ?";
		return util.batchDelete(sql, idList);
	}

	/**
	 * 批量删除[用户-角色]关联数据
	 * 
	 * @param ids
	 * @return
	 */
	public int[] delUserRef(List<String> idList) {
		String sql = "delete from pub_users_roles where user_id = ?";
		return util.batchDelete(sql, idList);
	}

	/**
	 * 更新用户对象
	 * 
	 * @param user
	 * @return
	 */
	public int updateUser(User user) {
		StringBuffer sqlSb = new StringBuffer(
				"update pub_users set " +
				"user_account = :userAccount, " +
				"user_name = :userName, "+
				"user_gender = :userGender, " +
				"user_birthday = :userBirthday, " +
				"user_org = :userOrg, " +
				"user_duty = :userDuty, "+
				"user_telephone = :userTelephone, " +
				"user_department = :userDepartment, " +
				"mail = :mail, " +
				"qq_weixin = :qqWeixin, " +
				"user_desc = :userDesc, "+
				"enable = :enable, " +
				"is_sys = :isSys, " +
				"lng = :lng, " +
				"lat = :lat");

		// 用户解锁时将登录错误次数也清零
		if (user.getEnable().equals("1")) {
			sqlSb.append(", err_times = '0'");
		}

		// 如果密码改变，重新加密保存
		if (null != user.getUserPassword()) {
			sqlSb.append(", user_password = :userPassword");
			String pwdStr = "";
			// jQuery返回了修改前和修改后的密码字符串[原密码,新密码明文]
			String[] pwdArr = user.getUserPassword().split(",");
			if (pwdArr.length > 1) {
				pwdStr = pwdArr[1].trim();
				// 盐值加密
				String newPwd = passwordEncoder.encodePassword(pwdStr, user.getUserAccount().trim());
				user.setUserPassword(newPwd);
			}
		}

		sqlSb.append(" where user_id = :userId");

		return util.editObject(sqlSb.toString(), user);
	}

	/**
	 * 修改登录用户个人信息
	 * 
	 * @param user
	 * @return
	 */
	public int updateUserMsg(User user) {
		String sql = "update pub_users set " +
				"user_name = :userName, " +
				"user_gender = :userGender, " +
				"user_birthday = :userBirthday, " +
				"user_duty = :userDuty, " +
				"user_telephone = :userTelephone, " +
				"mail = :mail, " +
				"qq_weixin = :qqWeixin, " +
				"user_desc = :userDesc " +
				"where user_account = :userAccount";
		return util.editObject(sql, user);
	}

	/**
	 * 获取登录失败次数
	 * 
	 * @param userAccount
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getErrTimes(String userAccount) {
		Map<String, Object> map = null;
		String sql = "select err_times from pub_users where user_account = ?";
		map = (Map<String, Object>) util.getMap(sql, new Object[] { userAccount });
		return map;
	}

	/**
	 * 每次登录失败时更新登录次数（累加）和用户状态（正常or禁用）
	 * 
	 * @param userAccount
	 * @param errTimes
	 * @return
	 */
	public int updateEnable(String userAccount, String errTimes, String isEnable) {
		String sql = "update pub_users set enable = '" + isEnable + "', err_times = '" + errTimes
				+ "' where user_account = '" + userAccount + "'";
		return util.updateObject(sql);
	}

	/**
	 * 获取用户数量(查询条件自己写sql)
	 * 
	 * @param sql
	 * @return
	 */
	public int getUserCount(String sql) {
		return util.getObjCount(sql);
	}

	/**
	 * 根据用户Id获取用户对象
	 * 
	 * @param userId
	 * @return
	 */
	public User getUserById(String userId) {
		String sql = "select * from pub_users where user_id = :userId";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		return (User) util.getObject(sql, paramMap, User.class);
	}

	/**
	 * 验证原始密码是否正确
	 * 
	 * @param password
	 * @return
	 */
	public int checkLoginUserPwd(String password) {
		int ret = 0;
		// 获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			String userAccount = ((PubUsers) principal).getUserAccount();
			// 盐值加密
			String encryptPwd = passwordEncoder.encodePassword(password, userAccount);
			String sql = "select count(1) from pub_users where user_account = '" + userAccount
					+ "' and user_password = '" + encryptPwd + "'";
			ret = util.getObjCount(sql);
		}
		return ret;
	}

	/**
	 * 更新当前登录用户的密码
	 * 
	 * @param password
	 * @return
	 */
	public int updatePwd(String password) {
		int ret = 0;
		// 获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			String userAccount = ((PubUsers) principal).getUserAccount();
			// 盐值加密
			String encryptPwd = passwordEncoder.encodePassword(password, userAccount);
			String sql = "update pub_users set user_password = '" + encryptPwd + "' where user_account = '"
					+ userAccount + "'";
			ret = util.updateObject(sql);
		}
		return ret;
	}
	
	
	/**
	 * 更新用户的密码
	 * 
	 * @param password
	 * @return
	 */
	public int updateUserPwd(String password,String userAccount) {
		int ret = 0;
		// 盐值加密
		String encryptPwd = passwordEncoder.encodePassword(password, userAccount);
		String sql = "update pub_users set user_password = '" + encryptPwd + "' where user_account = '"
				+ userAccount + "'";
		ret = util.updateObject(sql);
		return ret;
	}
	/**
	 * 分页查询
	 */
	public Map<String, Object> getPageList(DataGridModel dgm, User user) {

		// 查询结果Map
		Map<String, Object> result = new HashMap<String, Object>(2);

		// 获取记录数
		StringBuffer sumSql = new StringBuffer();
		sumSql.append(
				"select count(1) " +
				"from pub_users u, pub_org o, pub_org_desc od " +
				"where u.user_org = o.org_id and o.org_id = od.org_id");

		// 获取结果集
		String quSql = "select u.user_id as \"uid\", " +
				"u.user_id as \"userId\", " +
				"u.user_account as \"userAccount\", " +
				"u.user_name as \"userName\", " +
				"u.user_password as \"userPassword\", " +
				"u.user_gender as \"userGender\", " +
				"u.user_birthday as \"userBirthday\", " +
				"u.user_org as \"userOrg\", " +
				"o.org_id as \"orgId\", " +
				"o.org_name as \"orgName\", " +
				"u.user_duty as \"userDuty\", " +
				"u.user_telephone as \"userTelephone\", " +
				"u.user_department as \"userDepartment\", " +
				"u.mail as \"mail\", " +
				"u.qq_weixin as \"qqWeixin\", " +
				"u.user_desc as \"userDesc\", " +
				"u.enable as \"enable\", " +
				"u.is_sys as \"isSys\", " +
				"u.lng as \"lng\", " +
				"u.lat as \"lat\" " +
				"from pub_users u, pub_org o, pub_org_desc od " +
				"where u.user_org = o.org_id and o.org_id = od.org_id";

		// 组装查询条件
		StringBuffer sqlSb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		OrgDesc orgDesc = null;
		// 获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			// 根据登录用户的机构编号(userOrg)获取机构描述对象
			orgDesc = util.getOrgDescByOrgId(((PubUsers) principal).getUserOrg());
		} else {
			// 如果登录用户session过期，则返回空结果集
			return result;
		}

		// 点击查询按钮时组装查询语句
		if (null != user.getUserOrg()) {
			if (null != user.getUserAccount() && !user.getUserAccount().equals("")) {
				sqlSb.append(" and u.user_account = :userAccount");
				params.put("userAccount", user.getUserAccount());
				sumSql.append(" and u.user_account = '").append(user.getUserAccount()).append("'");
			}
			if (null != user.getUserName() && !user.getUserName().equals("")) {
				sqlSb.append(" and u.user_name like :userName");
				params.put("userName", "%" + user.getUserName() + "%");
				sumSql.append(" and u.user_name like '%").append(user.getUserName()).append("%'");
			}
			if (null != user.getUserGender() && !user.getUserGender().equals("")) {
				sqlSb.append(" and u.user_gender = :userGender");
				params.put("userGender", user.getUserGender());
				sumSql.append(" and u.user_gender = '").append(user.getUserGender()).append("'");
			}
			if (!user.getUserOrg().equals("")) {
				orgDesc = util.getOrgDescByOrgId(user.getUserOrg());
				sqlSb.append(" and od.id like :userOrg");
				params.put("userOrg", orgDesc.getId() + "%");
				sumSql.append(" and od.id like '").append(orgDesc.getId()).append("%'");
			} else { // 如果清空查询条件或不选择机构，也根据登录用户的当前机构进行过滤
				if (null != orgDesc) {
					sqlSb.append(" and od.id like :userOrg");
					params.put("userOrg", orgDesc.getId() + "%");
					sumSql.append(" and od.id like '").append(orgDesc.getId()).append("%'");
				} else { // 如果机构描述对象获取失败
					return result;
				}
			}
			if (null != user.getEnable() && !user.getEnable().equals("")) {
				sqlSb.append(" and u.enable = :enable");
				params.put("enable", user.getEnable());
				sumSql.append(" and u.enable = ").append(user.getEnable());
			}
			if (null != user.getUserBirthday() && !user.getUserBirthday().equals("")) {
				sqlSb.append(" and u.user_birthday = :userBirthday");
				params.put("userBirthday", user.getUserBirthday());
				sumSql.append(" and u.user_birthday = '").append(user.getUserBirthday()).append("'");
			}
			if (null != user.getUserDepartment() && !user.getUserDepartment().equals("")) {
				sqlSb.append(" and u.user_department = :userDepartment");
				params.put("userBirthday", user.getUserDepartment());
				sumSql.append(" and u.user_department = '").append(user.getUserDepartment()).append("'");
			}
		} else { // 点击菜单进入页面时，根据当前登录用户所在的机构进行查询
			if (null != orgDesc) {
				sqlSb.append(" and od.id like :userOrg");
				params.put("userOrg", orgDesc.getId() + "%");
				sumSql.append(" and od.id like '").append(orgDesc.getId()).append("%'");
			} else {
				// 如果机构描述对象为null，返回空结果集
				return result;
			}
		}

		// 组装排序规则
		String orderString = "";
		if (StringUtils.isNotBlank(dgm.getSort())) {
			if(dgm.getSort().equals("orgName")){
				dgm.setSort("orgId");
			}
			orderString = SqlUtil.getOrderbySql(dgm);
		}

		// 组装分页定义
		String sql = quSql + sqlSb.toString() + orderString;
		String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(), dgm.getRows());

		// 绑定查询结果('total'和'rows'名称不能修改)
		List<Map<String,Object>> rowList = util.getMapList(pageQuerySql, params);
		// 添加部门名称
		for(int i=0;i<rowList.size();i++){
			Object tempDepartment=rowList.get(i).get("userDepartment");
			if(tempDepartment!=null&&!"".equals(tempDepartment.toString().trim())){
				rowList.get(i).put("userDepartmentName", 
						PubData.getDictName("DEPARTMENT", rowList.get(i).get("userDepartment").toString()));
			}
		}
		result.put("total", util.getObjCount(sumSql.toString()));
		result.put("rows", rowList);

		return result;
	}

	/**
	 * 根据用户账号获取用户对象（修改登录用户个人信息）
	 * 
	 * @param userAccount
	 * @return
	 */
	public User getLoginUserObj(String userAccount) {
		String sql = "select " +
				"user_name as \"userName\", " +
				"user_gender as \"userGender\", " +
				"user_birthday as \"userBirthday\", " +
				"user_duty as \"userDuty\", " +
				"user_telephone as \"userTelephone\", " +
				"mail as \"mail\", " +
				"qq_weixin as \"qqWeixin\", " +
				"user_desc as \"userDesc\" " +
				"from pub_users where user_account = ?";
		return (User) util.getObject(sql, new Object[] { userAccount }, User.class);
	}

	/**
	 * 获取选中用户没有的所有角色
	 * 
	 * @param sql
	 * @param obj
	 * @return
	 */
	public List<Map<String, Object>> getAllRoles(String sql, Object[] obj) {
		return (List<Map<String, Object>>) util.getMapList(sql, obj);
	}

	/**
	 * 根据UserRole对象删除用户角色对应关系
	 */
	public int delUserRoles(UserRole userRole) {
		String sql = "delete from pub_users_roles where user_id = :userId";
		return util.editObject(sql, userRole);
	}

	/**
	 * 批量保存用户角色对应关系
	 * 
	 * @param objList
	 * @return
	 */
	public int[] saveUserRoles(List<Object[]> objList) {
		String sql = "insert into pub_users_roles (id, user_id, role_id) values (?, ?, ?)";
		return util.batchOperate(sql, objList);
	}

	/**
	 * 保存用户角色对象
	 * 
	 * @param userRole
	 * @return
	 */
	public int saveUserRole(UserRole userRole) {
		String sql = "insert into pub_users_roles (id, user_id, role_id) values (:id, :userId, :roleId)";
		return util.editObject(sql, userRole);
	}
	public List<Map<String, Object>> getOnlineUsers(String orgId) {
		String quSql = "select u.user_id as \"uid\", " +
				"u.user_id as \"userId\", " +
				"u.user_account as \"userAccount\", " +
				"u.user_name as \"userName\", " +
				"u.user_password as \"userPassword\", " +
				"u.user_gender as \"userGender\", " +
				"u.user_birthday as \"userBirthday\", " +
				"u.user_org as \"userOrg\", " +
				"o.org_id as \"orgId\", " +
				"o.org_name as \"orgName\", " +
				"u.user_duty as \"userDuty\", " +
				"u.user_telephone as \"userTelephone\", " +
				"u.user_department as \"userDepartment\", " +
				"u.mail as \"mail\", " +
				"u.qq_weixin as \"qqWeixin\", " +
				"u.user_desc as \"userDesc\", " +
				"u.enable as \"enable\", " +
				"u.is_sys as \"isSys\", " +
				"u.lng as \"lng\", " +
				"u.lat as \"lat\" " +
				"from pub_users u, pub_org o, pub_org_desc od " +
				"where u.user_org = o.org_id and o.org_id = od.org_id " +
				"AND u.online = \"1\" AND u.lat is not null AND u.lng is not null ";
			
		Map<String, Object> paramMap = new HashMap<>();
		if(!StringUtils.equals(orgId, "org0000")) {
			quSql += "AND u.user_org = :orgId";
			paramMap.put("orgId", orgId);
		}
		return util.getMapList(quSql, paramMap);
	}

}
