package com.pc.bsp.datadictionary.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pc.bsp.common.util.DBUtil;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.SqlUtil;
import com.pc.bsp.datadictionary.po.Dictionary;


@Repository("dataDictionaryDao")
public class DataDictionaryDao {
	@Autowired
	private DBUtil util;
	
	/**
	 * 分页查询数据字典
	 */
	public Map<String, Object> getPageList(DataGridModel dgm, Dictionary dictionary) {
		// 查询结果Map
		Map<String, Object> result = new HashMap<String, Object>(2);

		// 获取记录数
		StringBuffer sumSql = new StringBuffer();
		sumSql.append("select count(1) from pub_dictionary where 1=1");

		// 获取结果集
		String quSql = "select " +
				"id as \"id\", " +
				"pid as \"pid\", " +
				"dict_code as \"dictCode\", " +
				"dict_data as \"dictData\", " +
				"dict_name as \"dictName\", " +
				"dict_level as \"dictLevel\", " +
				"is_parent as \"isParent\", " +
				"remark as \"remark\" " +
				"from pub_dictionary where 1=1";

		// 组装查询条件
		StringBuffer sqlSb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		// 点击查询按钮时组装查询语句
		if (null != dictionary.getPid()) {
			if (!dictionary.getPid().equals("")) {
				sqlSb.append(" and pid = :pid");
				params.put("pid", dictionary.getPid());
				sumSql.append(" and pid = '").append(dictionary.getPid()).append("'");
			} 
			
		} 
		if (null != dictionary.getDictName()&& !dictionary.getDictName().equals("")) {
			sqlSb.append(" and dict_name like :dictName");
			params.put("dictName", "%" + dictionary.getDictName() + "%");
			sumSql.append(" and dict_name like '%").append(dictionary.getDictName()).append("%'");
		}
		if (null != dictionary.getDictData()&& !dictionary.getDictData().equals("")) {
			sqlSb.append(" and dict_data like :dictData");
			params.put("dictData", "%" + dictionary.getDictName() + "%");
			sumSql.append(" and dict_data like '%").append(dictionary.getDictName()).append("%'");
		}

		// 组装排序规则
		String orderString = "";
		if (StringUtils.isNotBlank(dgm.getSort())) {
			orderString = SqlUtil.getOrderbySql(dgm);
		}

		// 组装分页定义
		String sql = quSql + sqlSb.toString() + orderString;
		String pageQuerySql = SqlUtil.getPageQuerySql(sql, dgm.getPage(), dgm.getRows());

		// 绑定查询结果('total'和'rows'名称不能修改)
		result.put("total", util.getObjCount(sumSql.toString()));
		result.put("rows", util.getMapList(pageQuerySql, params));

		return result;

	}
	
	/**
	 * 保存数据字典信息
	 */
	public int addDictionary(Dictionary dictionary) {
		String sql = "insert into pub_dictionary " +
				"(id, " +
				"pid, " +
				"dict_code, " +
				"dict_name, " +
				"dict_data, " +
				"remark, " +
				"is_parent, " +
				"dict_level) " +
				"values " +
				"(:id, " +
				":pid, " +
				":dictCode, " +
				":dictName, " +
				":dictData, " +
				":remark, " +
				":isParent, " +
				":dictLevel)";
		return util.editObject(sql, dictionary);
	}
	
	/**
	 * 更新数据字典信息
	 */
	public int updateDictionary(Dictionary dictionary) {
		String sql = "update pub_dictionary set " +
				"dict_code = :dictCode, " +
				"dict_name = :dictName, " +
				"dict_data = :dictData, " +
				"remark = :remark " +
				"where id = :id";
		return util.editObject(sql, dictionary);
	}
	
	/**
	 * 根据id批量删除数据字典信息
	 * 
	 * @param ids
	 * @return
	 */
	public int[] delDictBatch(List<String> ids) {
		String sql = "delete from pub_dictionary where id = ?";
		return util.batchDelete(sql, ids);
	}
	
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
				"dict_name as \"dictName\" " +
				"from pub_dictionary " +
				"where pid = ?";
		return (List<Dictionary>) util.getObjList(sql, new Object[] { pId }, Dictionary.class);
	}
	
	/**
	 * 更新是否父节点状态
	 * 
	 * @param pid
	 * @param isParent
	 * @return
	 */
	public int updateIsParent(String pid, String isParent) {
		String upSql = "update pub_dictionary set is_parent = '" + isParent + "' where id = '" + pid + "'";
		return util.editObject(upSql, null);
	}
	
	/**
	 * 判断dictCode是否有重复 
	 * @param dictCode
	 * @return
	 */
	public int getSameCode(String dictCode){
		String sql = "select count(1) from pub_dictionary " +
				"where dict_code='"+dictCode+"'";
		return util.getObjCount(sql);
	}
	
	/**
	 * 判断同一个PID下名称和数据是否有重复
	 * @param pid
	 * @param dictName
	 * @param dictData
	 * @return
	 */
	public int getSameDict(String pid,String dictName,String dictData){
		//判断上级dictCodeList与dictName是否相同
		String  sql = "select dict_code from pub_dictionary where id=?";
		String dictCode = util.getMap(sql, new Object[]{pid}).get("dict_code").toString();
		if(dictName.equals(dictCode+"List"))
			return 1;
		
		sql = "select count(1) from pub_dictionary " +
				"where pid='"+pid+"' " +
				"and dict_name='"+dictName+"' " +
				"and dict_data='"+dictData+"' "; 
		return util.getObjCount(sql);
	}
	
	/**
	 * 获得数据字典树
	 */
	public List<Map<String,Object>> getDictList(String pid) {
		String sql = "select " +
				"id as \"id\", " +
				"pid as \"pId\", " +
				"dict_code as \"name\", " +
				"is_parent as \"isParent\", " +
				"dict_name as \"dictName\" " +
				"from pub_dictionary " +
				"where pid like ?";
		List<Map<String,Object>> tempList = util.getMapList(sql, new Object[] {"%"+pid+"%"});
		List<Map<String,Object>> dictTree = new ArrayList<Map<String,Object>>();
		for(int i=0;i<tempList.size();i++){
			Map<String,Object> tempMap= tempList.get(i);
			Map<String,Object> tempNodeMap = new HashMap<String,Object>();
			tempNodeMap.put("id", tempMap.get("id"));
			tempNodeMap.put("pId", tempMap.get("pId"));
			tempNodeMap.put("name", tempMap.get("name")+"["+tempMap.get("dictName")+"]");
			tempNodeMap.put("isParent", tempMap.get("isParent"));
			tempNodeMap.put("open", "0");
			dictTree.add(tempNodeMap);
		}
		return dictTree;
	}
	
	
	
	public List<Map<String,Object>> getAllDictByCode(String code){
		String sql = "select id from pub_dictionary where code=?";
		List<Map<String,Object>> temp = util.getMapList(sql,  new Object[] {code});
		if(temp!=null&&temp.size()>0){
			sql = "select " +
					"dict_name as \"dictName\", " +
					"dict_data as \"dictData\" "+
					"from pub_dictionary " +
					"where pid = ?";
			return util.getMapList(sql,  new Object[] {code});
		}
		return null;
	}
}
