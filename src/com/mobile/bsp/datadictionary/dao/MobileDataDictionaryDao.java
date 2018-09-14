package com.mobile.bsp.datadictionary.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pc.bsp.common.util.DBUtil;
import com.pc.bsp.datadictionary.po.Dictionary;


@Repository("mobileDataDictionaryDao")
public class MobileDataDictionaryDao {
	@Autowired
	private DBUtil util;
	
	
	/**
	 * 根据父节点逐级获取下级字典值（用于ComboTree）
	 */
	@SuppressWarnings("unchecked")
	public List<Dictionary> getComDictList(String pId) {
		String sql = "";
		sql = "select " +
				"id as \"id\", " +
				"pid as \"pid\", " +
				"is_parent as \"isParent\", " +
				"dict_code as \"dictCode\", " +
				"dict_data as \"dictData\", " +
				"dict_name as \"dictName\" " +
				"from pub_dictionary " +
				"where pid = ?";
		return (List<Dictionary>) util.getObjList(sql, new Object[] { pId }, Dictionary.class);
	}
}
