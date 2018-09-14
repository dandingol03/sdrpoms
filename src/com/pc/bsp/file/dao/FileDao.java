package com.pc.bsp.file.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pc.bsp.common.util.DBUtil;
import com.pc.bsp.file.po.FileUpload;

/**
 * @author simple
 *
 */
@Repository("fileDao")
public class FileDao {

	@Autowired
	private DBUtil util;
	/**
	 * mobile保存文件至数据库
	 * @param fileUpload
	 * @return
	 */
	public int saveMobileFile(FileUpload fileUpload) {
		String sql = "insert into PUB_MOBILE_FILE_UPLOAD (FILE_ID, FILE_NAME, FILE_TYPE, FILE_PATH, UPLOAD_USER,UPLOAD_TIME,FILE_REG) "
				+ "values (:fileId, :fileName, :fileType, :filePath, :uploadUser,:uploadTime,:fileReg)";
		return util.editObject(sql, fileUpload);
	}
	/**
	 * 保存文件至数据库
	 * @param fileUpload
	 * @return
	 */
	public int saveFile(FileUpload fileUpload) {
		String sql = "insert into PUB_FILE_UPLOAD (FILE_ID, FILE_NAME, FILE_TYPE, FILE_PATH, UPLOAD_USER,UPLOAD_TIME,FILE_REG) "
				+ "values (:fileId, :fileName, :fileType, :filePath, :uploadUser,:uploadTime,:fileReg)";
		return util.editObject(sql, fileUpload);
	}
	
	/**
	 * 根据文件ID获取文件下载信息
	 * @param fileId
	 * @return
	 */
	public Map<String, ?> getFileInfo(String fileId){
		String sql = "select FILE_ID as \"fileId\",FILE_NAME as \"fileName\",FILE_PATH as \"filePath\" from pub_file_upload where FILE_ID = ?";
		Map<String, ?> map = util.getMap(sql, new Object[] { fileId });
		return map;
	}

	/**
	 * 获取默认文件存放目录
	 * @return
	 */
//	public String getDefaultFileFolder() {
//		// TODO Auto-generated method stub
//		String sql = "select PARAM_VALUE as \"paraValue\"  from PUB_SYS_PARAM where PARAM_CODE = 'DEFAULT_FILE_FOLDER' and PARAM_STATUS='1'";
//		Map<String, Object> map = (Map<String, Object>) util.getMap(sql, new Object[] {});
//		return (String)map.get("paraValue");
//	}

	/**
	 * 根据文件ID删除后该文件在数据库中的记录
	 * @param fileId
	 * @return
	 */
	public int deleteFile(String fileId){
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("fileId", fileId);
		String sql = "delete from PUB_FILE_UPLOAD where FILE_ID = :fileId";
		return util.editObject(sql,params);
	}
	
	public String getFileType(String fileId){
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("fileId", fileId);
		String sql="select FILE_TYPE from PUB_FILE_UPLOAD where FILE_ID=:fileId";
		List<Map<String,Object>> re = util.getMapList(sql,params);
		if(null!=re&&re.size()>0)
		{
			return re.get(0).get("FILE_TYPE").toString();
		}
		return "";
	}
	
}
