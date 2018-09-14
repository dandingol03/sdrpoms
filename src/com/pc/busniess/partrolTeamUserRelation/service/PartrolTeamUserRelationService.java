package com.pc.busniess.partrolTeamUserRelation.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pc.bsp.common.util.DaoException;
import com.pc.bsp.common.util.DataGridModel;
import com.pc.bsp.common.util.NextID;
import com.pc.bsp.excelfile.util.ExcelExport;
import com.pc.bsp.security.po.PubUsers;
import com.pc.busniess.partrolTeamUserRelation.dao.PartrolTeamUserRelationDao;
import com.pc.busniess.partrolTeamUserRelation.po.PartrolTeamUserRelationPo;
import com.pc.busniess.partrolTeamUserRelation.po.ResponsibilityLinePo;

/**
 * 巡防人
 * 这个Service主要是对巡防管理信息的业务处理
 * @author jiawenlong
 * @version 1.0
 * @since 1.0
 */
@Service("PartrolTeamUserRelationService")
public class PartrolTeamUserRelationService {

	@Autowired
	private PartrolTeamUserRelationDao partrolTeamUserRelationDao;
	
	public Map<String, Object> findById(String id) {
		return partrolTeamUserRelationDao.findById(id);
	}
	public Map<String, Object> findByCardeId(String id) {
		return partrolTeamUserRelationDao.findByCardeId(id);
	}
	public Map<String, Object> mobileFindById(String id) {
		return partrolTeamUserRelationDao.mobileFindById(id);
	}
	/**
	 * 该方法是查询巡防人信息
	 * @param dgm
	 * @param partrolTeamUserRelationPo
	 * @return Dao层查询巡防人信息
	 */
	public Map<String, Object> PartrolTeamUserRelationQueryList(DataGridModel dgm,
			PartrolTeamUserRelationPo partrolTeamUserRelationPo) {
		// TODO 查询巡防人信息
		//获取当前登录用户
				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				PubUsers user = (PubUsers)principal;
				String orgId = user.getUserOrg();
		return partrolTeamUserRelationDao.PartrolTeamUserRelationQueryList(dgm,
				partrolTeamUserRelationPo,orgId);
	}
	/**
	 * 该方法是添加巡防人信息
	 * @param partrolTeamUserRelationPo
	 * @return Dao层添加巡防人信息
	 */
	public int addPartrolTeamUserRelation(
			PartrolTeamUserRelationPo partrolTeamUserRelationPo) {
		// TODO 添加巡防人信息
		String id = NextID.getNextID("TmUr");
		partrolTeamUserRelationPo.setUserId(NextID.getNextID("user"));
		partrolTeamUserRelationPo.setId(id);
		return partrolTeamUserRelationDao
				.addPartrolTeamUserRelation(partrolTeamUserRelationPo);
	}
	/**
	 * 该方法是修改巡防人信息
	 * @param PartrolTeamUserRelationPo
	 * @return Dao层修改巡防人信息
	 */
	public int updatePartrolTeamUserRelation(
			PartrolTeamUserRelationPo PartrolTeamUserRelationPo) {
		// TODO 修改巡防人信息
		return partrolTeamUserRelationDao
				.updatePartrolTeamUserRelation(PartrolTeamUserRelationPo);
	}
	/**
	 * 该方法是删除巡防人信息
	 * @param idList
	 * @param userList 
	 * @return Dao层删除巡防人信息
	 */
	public int[] deletePartrolTeamUserRelation(List<String> idList, List<String> userList) {
		// TODO 删除巡防人信息
		return partrolTeamUserRelationDao.deletePartrolTeamUserRelation(idList,userList);
	}
	/**
	 * 该方法是参考巡防人线路
	 * @return list
	 */
	public List<Map<String, Object>> queryPers() {
		// TODO 参考巡防人线
		List<Map<String, Object>> list = partrolTeamUserRelationDao.queryPers();
		return list;
	}
	public String exportSafeCheckExcel(PartrolTeamUserRelationPo PartrolTeamUserRelationPo,String fileName,String teamInfoId) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = partrolTeamUserRelationDao.getNewsListByExport(PartrolTeamUserRelationPo,teamInfoId);
		Map<String, String> titleMap = new LinkedHashMap<String, String>();
		titleMap.put("name", "姓名");
		titleMap.put("genderName", "性别");
		titleMap.put("telephone", "联系电话");
		titleMap.put("idNumber", "身份证号");
		titleMap.put("educationName", "文化程度");
		titleMap.put("birth", "出生日期");
		titleMap.put("domicile", "户籍地");
		titleMap.put("registrationName", "户口性质");
		titleMap.put("teamInfoName", "隶属队伍名称");
		titleMap.put("managementUnit", "管理单位");
		try {
			return ExcelExport.exportAndReturnFileId(list,fileName, titleMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new DaoException("生成"+fileName+"excel出错", e);
		}
	}
	/**
	 * 责任区段查询
	 * @param dgm
	 * @param responsibilityLinePo
	 * @return
	 */
	public Map<String, Object> responsibilityLineQueryList(DataGridModel dgm,
			ResponsibilityLinePo responsibilityLinePo) {
		// TODO Auto-generated method stub
		return partrolTeamUserRelationDao.responsibilityLineQueryList(dgm,responsibilityLinePo);
	}
	/**
	 * @param responsibilityLinePo
	 * @return
	 */
	public int addResponsibilityLine(ResponsibilityLinePo responsibilityLinePo) {
		// TODO Auto-generated method stub
		String id = NextID.getNextID("resL");
		responsibilityLinePo.setId(id);
		return partrolTeamUserRelationDao.addResponsibilityLine(responsibilityLinePo);
	}
	/**
	 * @param responsibilityLinePo
	 * @return
	 */
	public int updateResponsibilityLine(
			ResponsibilityLinePo responsibilityLinePo) {
		// TODO Auto-generated method stub
		return partrolTeamUserRelationDao.updateResponsibilityLine(responsibilityLinePo);
	}
	/**
	 * @param idList
	 * @return
	 */
	public int[] deleteResponsibilityLine(List<String> idList) {
		// TODO Auto-generated method stub
		return partrolTeamUserRelationDao.deleteResponsibilityLine(idList);
	}
	/**
	 * @param partrolTeamUserRelationPo
	 * @return
	 */
	public List<Map<String, Object>> partrolTeamUserRelationQueryList(
			PartrolTeamUserRelationPo partrolTeamUserRelationPo) {
		// TODO Auto-generated method stub
		return partrolTeamUserRelationDao.partrolTeamUserRelationQueryList( partrolTeamUserRelationPo);
	}
	public int checkTeamData(String id, String userId) {
		// TODO Auto-generated method stub
		return partrolTeamUserRelationDao.checkTeamData(id,userId);
	}
	public int updatePatrolUserBlongTeam(
			PartrolTeamUserRelationPo partrolTeamUserRelationPo,String teamInfoId) {
		// TODO Auto-generated method stub
		return partrolTeamUserRelationDao
				.updatePatrolUserBlongTeam(partrolTeamUserRelationPo,teamInfoId);
	}
	public List<Double[]> findlnglat(Object railId,Object middle,Object endMiddle) {
	// TODO Auto-generated method stub
		return partrolTeamUserRelationDao.findlnglat(railId, middle, endMiddle);
	}
}
