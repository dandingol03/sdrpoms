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

import com.pc.bsp.security.po.PubAuthorities;

/**
 * @author simple
 *
 */
@Repository("pubAuthoritiesDao")
public class PubAuthoritiesDao{

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
	 * 获取所有权限对象
	 */
	public List<PubAuthorities> getAuthorities(){
		String sql = "select * from pub_authorities";
		return (List<PubAuthorities>)jdbcTemplate.query(sql, new Object[]{}, BeanPropertyRowMapper.newInstance(PubAuthorities.class));
	}
	
}
