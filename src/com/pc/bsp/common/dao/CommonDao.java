package com.pc.bsp.common.dao;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pc.bsp.common.util.DBUtil;

@Repository("commonDao")
public class CommonDao {

	@Autowired
	private DBUtil util;

	public boolean isExistTable(String tableName){
		//表名必须大写
		tableName = tableName.toUpperCase();
		String checkTableSql="select count(*) as \"tableNum\" from user_tables where table_name = '"+tableName+"'";
		List<Map<String,Object>> rowsListcheck=util.getMapList(checkTableSql, new Object[]{});
		if(null==rowsListcheck||rowsListcheck.size()<1){
 			return false;
 		}
 		Object tableNum=rowsListcheck.get(0).get("tableNum");
 		if(null==tableNum){
 			return false;
 		}
 		if(tableNum.toString().equals("0")){
 			return false;
 		}
		return true;
	}
}
