/**   
 * @Package: com.mobile.bsp.authority.dao 
 * @author: jwl   
 * @date: 2018年6月25日 下午2:06:36 
 */
package com.mobile.bsp.authority.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pc.bsp.common.util.DBUtil;

/**   
 * @Package: com.mobile.bsp.authority.dao 
 * @author: jwl   
 * @date: 2018年6月25日 下午2:06:36 
 */
@Repository("mobileAuthorityDao")
public class MobileAuthorityDao {

	@Autowired
	private DBUtil util;
	/**
	 * 查询某权限已经注册的资源
	 * 
	 * @param temp
	 * @return
	 */
	public List<Map<String, Object>> queryHasRegResList(String temp) {
		// TODO Auto-generated method stub
		// 获取结果集
		String sql = "select " +
				"a.resource_name as \"resourceName\", " +
				"a.resource_string as \"resourceString\" " +
				"from pub_resources a , pub_authorities_resources b " +
				"where a.resource_id=b.resource_id " +
				"and b.authority_id in("+ temp + ")";
		return util.getMapList(sql, new HashMap<String, Object>());
	}

}
