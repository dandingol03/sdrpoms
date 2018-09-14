/**   
 * @Package: com.mobile.exhibition.map.service 
 * @author: jwl   
 * @date: 2018年5月14日 下午5:48:45 
 */
package com.mobile.exhibition.map.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.exhibition.map.dao.MobileExMapDao;

/**   
 * @Package: com.mobile.exhibition.map.service 
 * @author: jwl   
 * @date: 2018年5月14日 下午5:48:45 
 */
@Service("mobileExMapService")
public class MobileExMapService {
	@Autowired
	private MobileExMapDao mobileExMapDao;

	/**
	 * @param rangeDanger
	 * @return
	 */
	public List<Map<String, Object>> mobileExhibition(String rangeDanger) {
		// TODO Auto-generated method stub
		return mobileExMapDao.mobileExhibition(rangeDanger);
	}
}
