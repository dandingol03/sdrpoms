package com.pc.bsp.role.service;

import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.role.dao.RoleDao;
import com.pc.bsp.role.po.Role;
import com.pc.bsp.role.po.RoleAuthority;

/**
 * @author simple
 *
 */
@Service("roleService")
public class RoleService{
	
	@Autowired
	private RoleDao roleDao;
	
	/**
	 * 分页查询角色
	 * @param dgm
	 * @param org
	 * @return
	 */
	public Map<String, Object> getPageList(DataGridModel dgm, Role role){
		return roleDao.getPageList(dgm, role);
	}
	
	/**
	 * 保存角色
	 * @param role
	 * @return
	 */
	public int saveRole(Role role){
		return roleDao.saveRole(role);
	}
	
	/**
	 * 根据角色名称获取角色对象
	 * @param roleName
	 * @return
	 */
	public Role getRoleByName(String roleName){
		return roleDao.getRoleByName(roleName);
	}
	
	/**
	 * 更新角色
	 * @param role
	 * @return
	 */
	public int updateRole(Role role){
		return roleDao.updateRole(role);
	}
	
	/**
	 * 批量删除角色及关联数据
	 * @param idList
	 * @return
	 */
	public int[] delRoleBatch(List<String> idList){
		//删除[角色-权限]对应关系
		roleDao.delRoleAuth(idList);
		//删除[用户-角色]对应关系
		roleDao.delUserRole(idList);
		return roleDao.delRoleBatch(idList);
	}
	
	/**
	 * 获取选中角色没有的所有权限
	 * @param roleId
	 * @return
	 */
	public List<Map<String, Object>> getAllAuthList(String roleId){
		String sql = "select authority_id as \"id\", " +
				"authority_name as \"name\" " +
				"from pub_authorities where authority_id not in " +
				"(select authority_id from pub_roles_authorities where role_id = ?) " +
				"order by authority_name";
		return roleDao.getAllAuths(sql, new Object[]{roleId});
	}
	
	/**
	 * 获取选中角色的所有权限
	 * @param roleId
	 * @return
	 */
	public List<Map<String, Object>> getRoleAuthList(String roleId){
		String sql = "select ra.authority_id as \"id\", " +
				"a.authority_name as \"name\" " +
				"from pub_roles_authorities ra, pub_authorities a where " +
				"ra.authority_id = a.authority_id and ra.role_id = ? " +
				"order by authority_name";
		return roleDao.getAllAuths(sql, new Object[]{roleId});
	}
	
	/**
	 * 删除旧的，添加新的角色权限对应关系
	 */
	public int saveRoleAuths(RoleAuthority roleAuth, List<Object[]> saveList){
		
		int result = roleDao.delRoleAuths(roleAuth);
		
		if(saveList.size() > 0){
			int[] saveCount = roleDao.saveRoleAuths(saveList);
			for(int i = 0; i < saveCount.length; i ++){
				result += saveCount[i] == Statement.SUCCESS_NO_INFO ? 0 : saveCount[i];
			}
		}
		return result;
	}

}
