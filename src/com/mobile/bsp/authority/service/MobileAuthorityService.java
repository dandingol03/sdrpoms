/**   
 * @Package: com.mobile.bsp.authority.service 
 * @author: jwl   
 * @date: 2018年6月25日 上午9:36:14 
 */
package com.mobile.bsp.authority.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mobile.bsp.authority.dao.MobileAuthorityDao;
import com.pc.bsp.security.po.PubUsers;

/**   
 * @Package: com.mobile.bsp.authority.service 
 * @author: jwl   
 * @date: 2018年6月25日 上午9:36:14 
 */
@Service("mobileAuthorityService")
public class MobileAuthorityService {

	@Autowired
	private MobileAuthorityDao mobileauthorityDao;
	/**
	 * 查询某权限已经注册的资源
	 * 
	 * @param authorityId
	 * @return
	 */
	public List<Map<String, Object>> queryHasRegResList() {
		// TODO Auto-generated method stub
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			String temp="'1'";
			 String aut=((PubUsers) principal).getAuthorities().toString();
			 String []tempFileArray = aut.split(",");
			 for(int j=0;j<tempFileArray.length;j++){
				 temp+=","+"'"+tempFileArray[j].replace("[","").replace("]","").trim()+"'";
			 }
			return mobileauthorityDao.queryHasRegResList(temp);
		}
		return null; 
	}
}
