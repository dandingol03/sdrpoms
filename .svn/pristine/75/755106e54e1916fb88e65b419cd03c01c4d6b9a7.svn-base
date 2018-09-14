/**
 * 
 */
package com.pc.bsp.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pc.bsp.common.Config;
import com.pc.bsp.org.po.OrgDesc;


/**
 * @author simple
 * @date 2013-07-10
 */
@Repository(value="util")
public class DBUtil {

	private Log log = LogFactory.getLog(DBUtil.class);
	
	private NamedParameterJdbcTemplate npJdbcTemplate;
	
	/**
	 * 绑定数据源
	 * @param dataSource
	 */
	@Resource(name="dataSource")
	public void setDataSource(DataSource dataSource) {
		
	    this.npJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	    
	}
	
	/**
	 * 增、删、改(参数为po对象)
	 * @param sql
	 * @param obj
	 * @return
	 */
	public Integer editObject(String sql,Object obj){
		Integer id = 0;
		id = npJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(obj));
		return id;
	}
	
	/**
	 * 增、删、改(参数为Map<Key, Value>)
	 * 注意：增加、删除、修改时必须保证Map中的Key与po对象的属性名匹配，且不能缺少，
	 * 对于非必填项，Value可以为Null或""，删除时不建议使用本方法。
	 * @param sql
	 * @param paramMap
	 * @return
	 */
	public Integer editObject(String sql, Map<String, Object> paramMap){
		Integer id = 0;
		try {
			
			id = npJdbcTemplate.update(sql, paramMap);
			
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			throw new DaoException("[editObject<map>]添加或更新数据库操作失败！",e);
		}
		return id;
	}
	
	/**
	 * 执行更新sql
	 * @param sql
	 * @return
	 */
	public Integer updateObject(String sql){
		Integer id = 0;
		try {
			
			id = npJdbcTemplate.getJdbcOperations().update(sql);
			
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			throw new DaoException("[editObject<map>]添加或更新数据库操作失败！",e);
		}
		return id;
	}
	
	/**
	 * 获取Object(参数为Object[])
	 * 注意：查询条件必须确定唯一的结果，否则建议直接使用getObjectList
	 * @param sql(匹配符为?)
	 * @param className
	 * @param obj
	 * @return
	 */
	public Object getObject(String sql, Object[] obj, Class<?> className){
		Object object = null;
		try {
			
			object = npJdbcTemplate.getJdbcOperations().queryForObject(sql, obj, BeanPropertyRowMapper.newInstance(className));
			
		} catch (RuntimeException e) {
			log.error(e.getMessage(),e);
			if("Incorrect result size: expected 1, actual 0".equals(e.getMessage())){
				return null;
			}
			throw new DaoException("[getObject]获取对象数据库操作失败！",e);
		}
		return object;
	}
	
	/**
	 * 获取Object(参数为Map<String, object>)
	 * 注意：查询条件必须确定唯一的结果，否则建议直接使用getObjectList
	 * @param sql(匹配符为:xxx)
	 * @param paramMap
	 * @param className
	 * @return
	 */
	public Object getObject(String sql, Map<String,?> paramMap, Class<?> className){
		Object object = null;
		try {
			
			object = npJdbcTemplate.queryForObject(sql, paramMap, BeanPropertyRowMapper.newInstance(className));
			
		} catch (RuntimeException e) {
			log.error(e.getMessage(),e);
			if("Incorrect result size: expected 1, actual 0".equals(e.getMessage())){
				return null;
			}
			throw new DaoException("[getObject]获取对象数据库操作失败！",e);
		}
		return object;
	}
	
	/**
	 * 获取List集合(参数为Map<String, object>)
	 * @param sql(匹配符为:xxx)
	 * @param paramMap
	 * @param className
	 * @return
	 */
	public List<?> getObjList(String sql, Map<String,?> paramMap, Class<?> className){
		List<?> array = new ArrayList<>();
		try {
			
			array = npJdbcTemplate.query(sql, paramMap, BeanPropertyRowMapper.newInstance(className));
			
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			throw new DaoException("[getObjList]获取对象列表数据库操作失败！",e);
		}
		return array;
	}
	
	/**
	 * 获取List集合(参数为Object[])
	 * 注意：查询条件必须确定唯一的结果，否则建议直接使用getMapList
	 * @param sql(匹配符为?)
	 * @param className
	 * @param obj
	 * @return
	 */
	public List<?> getObjList(String sql, Object[] obj, Class<?> className){
		List<?> array = new ArrayList<>();
		try {
			
			array = npJdbcTemplate.getJdbcOperations().query(sql, obj, BeanPropertyRowMapper.newInstance(className));
			
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			throw new DaoException("[getObjList]获取对象列表数据库操作失败！",e);
		}
		return array;
	}
	
	/**
	 * 获取Map集合List(参数为Map<String, object>)
	 * 注意：查询条件必须确定唯一的结果，否则建议直接使用getMapList
	 * @param sql(匹配符为:xxx)
	 * @param paramMap
	 * @return
	 */
	public List<Map<String, Object>> getMapList(String sql,Map<String,?> paramMap){
		List<Map<String, Object>> array = new ArrayList<Map<String,Object>>();
		try {
			
			array = npJdbcTemplate.queryForList(sql, paramMap);
			
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			throw new DaoException("[getMap]获取Map对象数据库操作失败！",e);
		}
		return array;
	}
	
	/**
	 * 获取Map集合List(参数为Object[])
	 * @param sql(匹配符为?)
	 * @param obj
	 * @return
	 */
	public List<Map<String, Object>> getMapList(String sql,Object[] obj){
		List<Map<String, Object>> array = new ArrayList<Map<String,Object>>();
		try {
			
			array = npJdbcTemplate.getJdbcOperations().queryForList(sql, obj);
			
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			throw new DaoException("[getMap]获取Map对象数据库操作失败！",e);
		}
		return array;
	}
	
	/**
	 * 获取Map集合(参数为Map<String, object>)
	 * 注意：查询条件必须确定唯一的结果，否则建议直接使用getMapList
	 * @param sql(匹配符为:xxx)
	 * @param paramMap
	 * @return
	 */
	public Map<String,?> getMap(String sql,Map<String,?> paramMap){
		Map<String, ?> map = new HashMap<>();
		try {
			
			map = npJdbcTemplate.queryForMap(sql, paramMap);
			
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			throw new DaoException("[getMap]获取Map对象数据库操作失败！",e);
		}
		return map;
	}
	
	/**
	 * 获取Map集合(参数为Object[])
	 * @param sql(匹配符为?)
	 * @param obj
	 * @return
	 */
	public Map<String,?> getMap(String sql,Object[] obj){
		Map<String, ?> map = new HashMap<>();
		try {
			
			map = npJdbcTemplate.getJdbcOperations().queryForMap(sql, obj);
			
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			throw new DaoException("[getMap]获取Map对象数据库操作失败！",e);
		}
		return map;
	}
	
	/**
	 * 获取Map集合(参数为Map<String, object>)
	 * 注意：查询条件必须确定唯一的结果，否则建议直接使用getMapList
	 * @param sql(匹配符为:xxx)
	 * @param paramMap
	 * @return
	 */
	public Map<String,Object> getMapObject(String sql,Map<String,?> paramMap){
		Map<String, Object> map = new HashMap<>();
		try {
			
			map = npJdbcTemplate.queryForMap(sql, paramMap);
			
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			throw new DaoException("[getMap]获取Map对象数据库操作失败！",e);
		}
		return map;
	}
	
	/**
	 * 获取Map集合(参数为Object[])
	 * @param sql(匹配符为?)
	 * @param obj
	 * @return
	 */
	public Map<String,Object> getMapObject(String sql,Object[] obj){
		Map<String, Object> map = new HashMap<>();
		try {
			
			map = npJdbcTemplate.getJdbcOperations().queryForMap(sql, obj);
			
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			throw new DaoException("[getMap]获取Map对象数据库操作失败！",e);
		}
		return map;
	}
	
	/**
	 * 获取记录数量
	 * @param sql
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public Integer getObjCount(String sql){
		Integer id = 0;
		try{
			
			id = npJdbcTemplate.getJdbcOperations().queryForInt(sql);
			
		}catch (RuntimeException e){
			log.error(e.getMessage());
			throw new DaoException("[getCount]查询数据库操作失败！");
		}
		return id;
	}
	
	/**
	 * 批量操作(增、删、改，参数使用Map数组，组装繁琐，不建议使用)
	 * @param sql(匹配符为:xxx)
	 * @param obj
	 * @return
	 */
	public int[] batchOperate(String sql,Map<String, Object>[] obj){
		int[] a = null;
		try {
			
			a = npJdbcTemplate.batchUpdate(sql, obj);
			
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			throw new DaoException("[batchOperate]批量数据库操作失败！",e);
		}
		return a;
	}
	
	/**
	 * 批量操作(增、删、改，参数使用Object[]列表，组装相对方便，推荐使用)
	 * @param sql(匹配符为?)
	 * @param obj
	 * @return
	 */
	public int[] batchOperate(String sql,List<Object[]> obj){
		int[] a = null;
		try {
			
			a = npJdbcTemplate.getJdbcOperations().batchUpdate(sql, obj);
			
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			throw new DaoException("[batchOperate]批量数据库操作失败！",e);
		}
		return a;
	}
	
	/**
	 * 批量删除(简化List<Object[]>组装)
	 * @param sql(匹配符为?)
	 * @param idList
	 * @return
	 */
	public int[] batchDelete(String sql,List<String> idList){
		int[] a = null;
		try {
			
			List<Object[]> myList = new ArrayList<Object[]>();
			for(int i = 0; i < idList.size(); i ++){
				Object[] obj = new Object[1];
				obj[0] = idList.get(i);
				myList.add(obj);
			}
			
			a = npJdbcTemplate.getJdbcOperations().batchUpdate(sql, myList);
			
		} catch (RuntimeException e) {
			log.error(e.getMessage());
			throw new DaoException("[batchOperate]批量数据库操作失败！",e);
		}
		if ("oracle".equals(Config.DATABASE)) {
			int[] b = new int[a.length];
			for (int i = 0; i < a.length; i++) {

				if (a[i] == -2) {
					b[i] = 1;
				} else {
					b[i] = 0;
				}
			}
			return b;
		} else {
			return a;
		}
	}
	
	/**
	 * 根据org_id获取机构关系表对象
	 * @param userOrg
	 * @return
	 */
	public OrgDesc getOrgDescByOrgId(String orgId){
		OrgDesc orgDesc = null;
		String sumSql = "select count(1) from pub_org_desc where org_id = '"+orgId+"'";
		if(getObjCount(sumSql) > 0){
			String sql = "select * from pub_org_desc where org_id = ?";
			orgDesc = (OrgDesc)getObject(sql, new Object[]{orgId}, OrgDesc.class);
		}
		return orgDesc;
	}
	
	/**
	 * 根据id获取机构关系表对象
	 * @param id
	 * @return
	 */
	public OrgDesc getOrgDescById(String id){
		OrgDesc orgDesc = null;
		String sumSql = "select count(1) from pub_org_desc where id = '"+id+"'";
		if(getObjCount(sumSql) > 0){
			String sql = "select * from pub_org_desc where id = ?";
			orgDesc = (OrgDesc)getObject(sql, new Object[]{id}, OrgDesc.class);
		}
		return orgDesc;
	}
}
