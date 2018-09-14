package com.pc.socket.dao.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pc.socket.dao.IMpsSocketDao;

@Repository("mpsSocketDao")
public class MpsSocketDaoImpl implements IMpsSocketDao
{
	private static Logger logger = Logger.getLogger(MpsSocketDaoImpl.class);
	protected SimpleDateFormat sMoformat;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public MpsSocketDaoImpl() 
	{
		sMoformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 绑定数据源
	 * @param dataSource
	 */
	@Resource(name="dataSource")
	public void setDataSource(DataSource dataSource) 
	{
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
}
