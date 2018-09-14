/**
 * 
 */
package com.pc.bsp.user.service;

import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.user.dao.UserDao;
import com.pc.bsp.user.po.User;
import com.pc.bsp.user.po.UserRole;

/**
 * @author simple
 *
 */
@Service("userService")
public class UserService{
	
	//private static Logger logger = Logger.getLogger(UserService.class);
	
	@Autowired
	private UserDao userDao;
	
	/**
	 * 根据id查询用户
	 * @param id
	 * @return
	 */
	public Map<String, Object> findById(String id){
		return userDao.findById(id);
	}
	
	/**
	 * 分页查询用户
	 */
	public Map<String, Object> getPageList(DataGridModel dgm,User user){
		return userDao.getPageList(dgm,user);
	}
	
	/**
	 * 通过userId获取User对象
	 */
	public User getUserById(String userId){
		return userDao.getUserById(userId);
	}
	
	/**
	 * 验证原始密码是否正确
	 * @param password
	 * @return
	 */
	public int checkLoginUserPwd(String password){
		return userDao.checkLoginUserPwd(password);
	}
	
	/**
	 * 更新当前登录用户的密码
	 * @param password
	 * @return
	 */
	public int updatePwd(String password){
		return userDao.updatePwd(password);
	}
	
	/**
	 * 更新用户的密码
	 * @param password
	 * @return
	 */
	public int updateUserPwd(String password,String userAccount){
		return userDao.updateUserPwd(password,userAccount);
	}
	
	/**
	 * 修改登录用户个人信息
	 * @param user
	 * @return
	 */
	public int updateUserMsg(User user){
		return userDao.updateUserMsg(user);
	}
	
	/**
	 * 根据用户账号获取用户对象（修改登录用户个人信息）
	 * @param userAccount
	 * @return
	 */
	public User getLoginUserObj(String userAccount){
		return userDao.getLoginUserObj(userAccount);
	}
	
	/**
	 * 保存用户
	 */
	public int saveUser(User user){
		return userDao.saveUser(user);
	}
	
	/**
	 * 更新用户
	 */
	public int updateUser(User user){
		return userDao.updateUser(user);
	}
	
	/**
	 * 获取登录失败次数
	 * @param userAccount
	 * @return
	 */
	public Map<String, Object> getErrTimes(String userAccount){
		return userDao.getErrTimes(userAccount);
	}
	
	/**
	 * 每次登录失败时更新登录次数（累加）和用户状态（正常or禁用）
	 * @param userAccount
	 * @param errTimes
	 * @return
	 */
	public int updateEnable(String userAccount, String errTimes, String isEnable){
		return userDao.updateEnable(userAccount, errTimes, isEnable);
	}
	
	/**
	 * 批量删除用户及关联数据
	 */
	public int[] delUserBatch(List<String> idList){
		//先删除[用户-角色]对应关系
		userDao.delUserRef(idList);
		return userDao.delUserBatch(idList);
	}
	
	/**
	 * 获取用户账号数量
	 */
	public int getUserCountByAcc(String userAccount){
		String sql = "select count(1) from pub_users where user_account = '"+userAccount+"'";
		return userDao.getUserCount(sql);
	}
	
	/**
	 * 获取用户ID数量
	 */
	public int getUserCountById(String userId){
		String sql = "select count(1) from pub_users where user_id = '"+userId+"'";
		return userDao.getUserCount(sql);
	}
	
	/**
	 * 获取指定机构下用户的数量（删除机构时使用）
	 * @param orgId
	 * @return
	 */
	public int getUserCountByOrgId(String orgId){
		String sql = "select count(1) from pub_users where user_org = '"+orgId+"'";
		return userDao.getUserCount(sql);
	}
	
	/**
	 * 获取选中用户没有的所有角色
	 * @param userId
	 * @return
	 */
	public List<Map<String, Object>> getAllRoleList(String userId, int roleLevel){
		String sql = "select " +
				"role_id as \"id\", " +
				"role_name as \"name\" " +
				"from pub_roles " +
				"where role_level >= "+roleLevel+" " +
				"and role_id not in " +
				"(select role_id from pub_users_roles where user_id = ?) " +
				"order by role_name";
		return userDao.getAllRoles(sql, new Object[]{userId});
	}
	
	/**
	 * 获取选中用户的所有角色
	 * @param userId
	 * @return
	 */
	public List<Map<String, Object>> getUserRoleList(String userId){
		String sql = "select " +
				"ur.role_id as \"id\", " +
				"r.role_name as \"name\" " +
				"from pub_users_roles ur, pub_roles r where " +
				"ur.role_id = r.role_id and ur.user_id = ? " +
				"order by role_name";
		return userDao.getAllRoles(sql, new Object[]{userId});
	}
	
	/**
	 * 删除旧的，添加新的用户角色对应关系
	 */
	public int saveUserRoles(UserRole userRole, List<Object[]> saveList){
		
		int result = userDao.delUserRoles(userRole);
		
		if(saveList.size() > 0){
			int[] saveCount = userDao.saveUserRoles(saveList);
			for(int i = 0; i < saveCount.length; i ++){
				result += saveCount[i] == Statement.SUCCESS_NO_INFO ? 0 : saveCount[i];
			}
		}
		return result;
	}

	public List<Map<String, Object>> getAppOnlineUserInfo(String orgId, Set<String> railIds) {
		return userDao.getOnlineUsers(orgId);
	}

}
