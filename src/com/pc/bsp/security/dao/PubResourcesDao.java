/**
 * 
 */
package com.pc.bsp.security.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pc.bsp.security.po.PubResources;

/**
 * @author simple
 *
 */
@Repository("pubResourcesDao")
public class PubResourcesDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 绑定数据源
	 * @param dataSource
	 */
	@Resource(name="dataSource")
	public void setDataSource(DataSource dataSource) {
		
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	    
	}

	/**
	 * 根据权限名称获取资源URL
	 */
	public List<PubResources> getAuthResources(String authorityName){
		String sql = "select r.* from pub_resources r, pub_authorities_resources ar, pub_authorities a " +
		 			 "where r.resource_id = ar.resource_id and ar.authority_id = a.authority_id and a.authority_name = ?";
		
		return (List<PubResources>)jdbcTemplate.query(sql, new Object[]{authorityName}, BeanPropertyRowMapper.newInstance(PubResources.class));
	}
	
}
