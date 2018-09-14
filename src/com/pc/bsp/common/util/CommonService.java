package com.pc.bsp.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pc.bsp.common.dao.CommonDao;


/**
 * @author simple
 *
 */
@Service("commonService")
public class CommonService{
	
	@Autowired
	private CommonDao commonDao;
	
	public boolean isExistTable(String tableName){
		return commonDao.isExistTable(tableName);
	}
	
}
