package com.pc.bsp.sysparam.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pc.bsp.common.util.DBUtil;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.SqlUtil;
import com.pc.bsp.sysparam.po.SysParam;

/**
 * @author simple
 *
 */
@Repository("sysParamDao")
public class SysParamDao {

	@Autowired
	private DBUtil util;

	/**
	 * 分页查询系统参数
	 */
	public Map<String, Object> getPageList(DataGridModel dgm, SysParam param) {

		// 查询结果Map
		Map<String, Object> result = new HashMap<String, Object>(2);

		// 获取记录数
		StringBuffer sumSql = new StringBuffer();
		sumSql.append("select count(1) from pub_sys_param where 1=1");

		// 获取结果集
		String quSql = "select " +
				"param_id as \"uid\", " +
				"param_id as \"paramId\", " +
				"param_code as \"paramCode\", " +
				"param_name as \"paramName\", "+
				"param_value as \"paramValue\", " +
				"param_status as \"paramStatus\" " +
				"from pub_sys_param where 1=1";

		// 组装查询条件
		StringBuffer sqlSb = new StringBuffer();
		Map<String, Object> params = new HashMap<String, Object>();

		// 点击查询按钮时组装查询语句
		if (null != param.getParamCode() && !param.getParamCode().equals("")) {
			sqlSb.append(" and param_code like :paramCode");
			params.put("paramCode", "%" + param.getParamCode() + "%");
			sumSql.append(" and param_code like '%").append(param.getParamCode()).append("%'");
		}
		if (null != param.getParamName() && !param.getParamName().equals("")) {
			sqlSb.append(" and param_name like :paramName");
			params.put("paramName", "%" + param.getParamName() + "%");
			sumSql.append(" and param_name like '%").append(param.getParamName()).append("%'");
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
	 * 保存系统参数
	 */
	public int saveSysParam(SysParam param) {
		String sql = "insert into pub_sys_param " +
				"(param_id, " +
				"param_code, " +
				"param_name, " +
				"param_value, " +
				"param_status) "
				+ "values " +
				"(:paramId, " +
				":paramCode, " +
				":paramName, " +
				":paramValue, " +
				":paramStatus)";
		return util.editObject(sql, param);
	}

	/**
	 * 修改系统参数
	 */
	public int updateSysParam(SysParam param) {
		String sql = "update pub_sys_param set " +
				"param_code = :paramCode, " +
				"param_name = :paramName, " +
				"param_value = :paramValue, "+
				"param_status = :paramStatus " +
				"where param_id = :paramId";
		return util.editObject(sql, param);
	}

	/**
	 * 批量删除系统参数
	 */
	public int[] delSysParamBatch(List<String> idList) {
		String sql = "delete from pub_sys_param where param_id = ?";
		return util.batchDelete(sql, idList);
	}

}
