package com.pc.bsp.authority.service;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pc.bsp.authority.dao.AuthorityDao;
import com.pc.bsp.authority.po.Authority;
import com.pc.bsp.authority.po.AuthorityResource;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.NextID;

/**
 * @author simple
 *
 */
@Service("authorityService")
public class AuthorityService {

	@Autowired
	private AuthorityDao authorityDao;

	/**
	 * 分页查询权限
	 * 
	 * @param dgm
	 * @param org
	 * @return
	 */
	public Map<String, Object> getPageList(DataGridModel dgm,
			Authority authority) {
		return authorityDao.getPageList(dgm, authority);
	}

	/**
	 * 保存权限
	 * 
	 * @param authority
	 * @return
	 */
	public int saveAuthority(Authority authority) {
		return authorityDao.saveAuthority(authority);
	}

	/**
	 * 更新权限
	 * 
	 * @param authority
	 * @return
	 */
	public int updateAuthority(Authority authority) {
		return authorityDao.updateAuthority(authority);
	}

	/**
	 * 批量删除权限及关联数据
	 * 
	 * @param idList
	 * @return
	 */
	public int[] delAuthorityBatch(List<String> idList) {
		// 删除[权限-资源]对应关系
		authorityDao.delAuthRes(idList);
		// 删除[角色-权限]对应关系
		authorityDao.delRoleAuth(idList);
		return authorityDao.delAuthorityBatch(idList);
	}

	/**
	 * 获取选中权限的所有资源
	 * 
	 * @param authorityId
	 * @return
	 */
	public List<Map<String, Object>> getAuthResList(String authorityId) {
		String sql = "select ar.resource_id as \"id\", r.resource_name as \"name\" from pub_authorities_resources ar, pub_resources r "
				+ "where ar.resource_id = r.resource_id and ar.authority_id = ?";
		return authorityDao.getAllRes(sql, new Object[] { authorityId });
	}

	/**
	 * 删除旧的，添加新的权限资源对应关系
	 */
	public int saveAuthRes(AuthorityResource authRes, List<Object[]> saveList) {

		int result = authorityDao.delAuthRes(authRes);

		if (saveList.size() > 0) {
			int[] saveCount = authorityDao.saveAuthRes(saveList);
			for (int i = 0; i < saveCount.length; i++) {
				result += saveCount[i] == Statement.SUCCESS_NO_INFO ? 0
						: saveCount[i];
			}
		}
		return result;
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
		return authorityDao.queryHasRegResList(dgm, authorityId);
	}

	/**
	 * 查询某权限未注册的资源
	 * 
	 * @param dgm
	 * @param authorityId
	 * @return
	 */
	public Map<String, Object> queryNotRegResList(DataGridModel dgm,
			String authorityId) {
		// TODO Auto-generated method stub
		return authorityDao.queryNotRegResList(dgm, authorityId);
	}

	/**
	 * 删除某权限的资源对应关系
	 * 
	 * @param resourceIdList
	 * @param authorityId
	 */
	public void deleteAuthResource(List<String> resourceIdList,
			String authorityId) {
		// TODO Auto-generated method stub
		authorityDao.deleteAuthResource(resourceIdList, authorityId);
	}

	/**
	 * 增加某权限的资源对应关系
	 * 
	 * @param resourceIdList
	 * @param authorityId
	 */
	public void addAuthResource(List<String> resourceIdList, String authorityId) {
		// TODO Auto-generated method stub
		List<Object[]> objList = new ArrayList<Object[]>();
		// 组装插入数据
		for (String resourceId : resourceIdList) {
			Object[] obj = new Object[3];
			obj[0] = NextID.getUUID();
			obj[1] = authorityId;
			obj[2] = resourceId;

			objList.add(obj);
		}
		authorityDao.saveAuthRes(objList);
	}

}
