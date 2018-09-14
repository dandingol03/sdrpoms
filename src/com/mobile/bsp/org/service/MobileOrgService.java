/**
 * 
 */
package com.mobile.bsp.org.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.bsp.org.dao.MobileOrgDao;
import com.pc.bsp.org.po.OrgDesc;

/**
 * @author simple
 *
 */
@Service("mobileOrgService")
public class MobileOrgService{
	
	@Autowired
	private MobileOrgDao mobileOrgDao;
	
	/**
	 * 根据父节点逐级获取下级机构（用于ComboTree）
	 * @param orgId
	 * @return
	 */
	public List<OrgDesc> getComOrgList(String orgId){
		return mobileOrgDao.getComOrgList(orgId);
	}
	/**
	 * 根据orgName获取机构树
	 * 
	 * @return
	 */
	public List<OrgDesc> findByOrgName(String orgName){
		return mobileOrgDao.findByOrgName(orgName);
	}
}
