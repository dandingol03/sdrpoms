package com.mobile.bsp.datadictionary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.bsp.datadictionary.dao.MobileDataDictionaryDao;
import com.pc.bsp.datadictionary.po.Dictionary;
import com.pc.bsp.org.dao.OrgDao;

@Service("mobileDataDictionaryService")
public class MobileDataDictionaryService {

	@Autowired
	private MobileDataDictionaryDao mobiledictionaryDao;
	@Autowired
	private OrgDao orgDao;
	
	/**
	 * 根据父节点逐级获取下级数据字典（用于ComboTree）
	 * @param pId
	 * @return
	 */
	public List<Dictionary> getComDictList(String pId){
		return mobiledictionaryDao.getComDictList(pId);
	}
	
}
