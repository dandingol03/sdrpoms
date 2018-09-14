package com.pc.busniess.dataRetrieval.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.busniess.dataRetrieval.dao.DataRetrievalDao;

@Service("DataRetrievalDaoService")
public class DataRetrievalService {

	@Autowired
	private DataRetrievalDao dataRetrievalDao;
	/**
	 * 数据检索查询
	 * @param dgm
	 * @param patrolTeamInfoPo
	 * @return
	 */
	public Map<String, Object> dataRetrievalQueryList(DataGridModel dgm, String name, String orgId) {
		// TODO 数据检索查
			return dataRetrievalDao.dataRetrievalQueryList(dgm, name, orgId);
	}
}
