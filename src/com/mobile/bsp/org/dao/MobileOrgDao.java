/**
 * 
 */
package com.mobile.bsp.org.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.pc.bsp.common.util.DBUtil;
import com.pc.bsp.org.po.OrgDesc;
import com.pc.bsp.security.po.PubUsers;

/**
 * @author simple
 *
 */
@Repository("mobileOrgDao")
public class MobileOrgDao {

	@Autowired
	private DBUtil util;


	/**
	 * 根据父节点逐级获取下级机构（用于ComboTree）
	 */
	@SuppressWarnings("unchecked")
	public List<OrgDesc> getComOrgList(String orgId) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String sql = "";
		OrgDesc orgDesc = null;
		if ("0".equals(orgId)) {
			// 根据当前登录用户机构过滤
			if (principal instanceof PubUsers) {
				String userOrg = ((PubUsers) principal).getUserOrg();
				orgDesc = util.getOrgDescByOrgId(userOrg);
				orgId = orgDesc.getOrgId();
			} else {
				// 如果用户尚未登录，默认取根节点
				orgId = "110";
			}
			sql = "select " +
					"od.org_id as \"id\", " +
					"od.parent_id as \"pId\", " +
					"o.org_name as \"name\", " +
					"od.is_parent as \"isParent\", " +
					"od.ORG_LEVEL as \"orgLevel\", " +
					"od.open as \"open\" " +
					"from pub_org o, pub_org_desc od " +
					"where od.org_id = o.org_id and od.org_id = ?";
		} else {
			sql = "select " +
					"od.org_id as \"id\", " +
					"o.org_name as \"name\", " +
					"od.is_parent as \"isParent\", " +
					"od.ORG_LEVEL as \"orgLevel\", " +
					"od.open as \"open\" " +
					"from pub_org o, pub_org_desc od " +
					"where od.org_id = o.org_id " +
					"and od.parent_id = (select id from pub_org_desc t where t.org_id = ?)";
		}
		return (List<OrgDesc>) util.getObjList(sql, new Object[] { orgId }, OrgDesc.class);
	}
	/**
	 * 根据orgName获取机构树
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<OrgDesc> findByOrgName(String orgName) {
		// TODO Auto-generated method stub
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		OrgDesc orgDesc = null;
		String orgId=null;
		// 根据当前登录用户机构过滤
		if (principal instanceof PubUsers) {
			String userOrg = ((PubUsers) principal).getUserOrg();
			orgDesc = util.getOrgDescByOrgId(userOrg);
			orgId = orgDesc.getOrgId();
		} 
		String sql = "";
		if(orgId!=null){
			sql = "select * from ("+
					"select " +
					"od.org_id as \"id\", " +
					"od.parent_id as \"pId\", " +
					"o.org_name as \"name\", " +
					"od.is_parent as \"isParent\", " +
					"od.ORG_LEVEL as \"orgLevel\", " +
					"od.open as \"open\" " +
					"from pub_org o, pub_org_desc od " +
					"where od.org_id = o.org_id and od.org_id like ?) as p "+ 
					"where p.name like ?";
			return (List<OrgDesc>) util.getObjList(sql, new Object[] { orgId+"%","%"+orgName+"%" }, OrgDesc.class);
		}else{
			sql = "select " +
					"od.org_id as \"id\", " +
					"o.org_name as \"name\", " +
					"od.is_parent as \"isParent\", " +
					"od.ORG_LEVEL as \"orgLevel\", " +
					"od.open as \"open\" " +
					"from pub_org o, pub_org_desc od " +
					"where od.org_id = o.org_id " +
					"and o.org_name like ?";
			return (List<OrgDesc>) util.getObjList(sql, new Object[] { "%"+orgName+"%" }, OrgDesc.class);
		}
	}
}
