package com.pc.busniess.dataRetrieval.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pc.bsp.common.util.DataGridModel;
import com.pc.busniess.dataRetrieval.service.DataRetrievalService;

@Controller
public class DataRetrievalController {

	@Autowired
	private DataRetrievalService dataRetrievalService;
	
	/**
	 * 数据检索查询
	 * @param dgm
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/baseInfo/dataRetrievalQueryList", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> dataRetrievalQueryList(DataGridModel dgm,
			@RequestParam("name") String name,
			@RequestParam("orgId")String orgId) {
		return dataRetrievalService.dataRetrievalQueryList(dgm, name, orgId);
	}
}
