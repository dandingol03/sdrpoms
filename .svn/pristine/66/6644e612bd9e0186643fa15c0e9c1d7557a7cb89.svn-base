package com.pc.bsp.file.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pc.bsp.common.util.DaoException;
import com.pc.bsp.common.util.NextID;
import com.pc.bsp.common.util.PubData;
import com.pc.bsp.file.dao.FileDao;
import com.pc.bsp.file.po.FileDownload;
import com.pc.bsp.file.po.FileUpload;
import com.pc.bsp.security.po.PubUsers;


@Service("fileService")
public class FileService {

	@Autowired
	private FileDao fileDao;

	
	public String saveFileUpLoad(MultipartFile[] files) throws Exception {
		// TODO 编写上传文件的方法
		String returnStr = "";
		// 获取当前登录用户
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		String uploadUser = null;
		if (principal instanceof PubUsers) {
			// 根据登录用户的机构编号(userOrg)获取机构描述对象
			uploadUser = ((PubUsers) principal).getUserAccount();
		}

		for (MultipartFile file : files) {

			if (file.isEmpty()) {
				//System.out.println("文件未上传");
				continue;
			}

			String fileName = file.getOriginalFilename();
			String suffix = "";// 扩展名
			int index = fileName.lastIndexOf(".");
			if (index != -1) {
				suffix = fileName.substring(fileName.lastIndexOf("."));
			}

			String fileId = NextID.getUUID();
			returnStr += fileId + ",";

			String defaultFileFolder = (String) PubData.getData("DEFAULT_FILE_FOLDER");
			File fileDir = new File(defaultFileFolder);
			if(!fileDir.exists()){
				fileDir.mkdir();
			}
			
			File targetFile = new File(defaultFileFolder, fileId + suffix);
			file.transferTo(targetFile);

			try {
				FileUpload upPO = new FileUpload();
				upPO.setFileId(fileId);
				upPO.setFileName(fileName);
				upPO.setFileType(file.getContentType());
				upPO.setFilePath(defaultFileFolder + "/" + fileId + suffix);
				upPO.setUploadUser(uploadUser);
				upPO.setUploadTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(new Date()));
				fileDao.saveFile(upPO);
			} catch (Exception e) {
				if (targetFile.exists()) {
					targetFile.delete();
				}
				// 抛出DaoException使得数据库rollback
				throw new DaoException("上传文件出错", e);
			}

		}

		// 返回上传文件id的js数组格式
		if (returnStr.length() > 0) {
			//去掉最后的逗号
			returnStr = returnStr.substring(0, returnStr.length() - 1);
		}
		return returnStr;
	}
	public String saveMobileFileUpLoad(MultipartFile[] files) throws Exception {
		// TODO 编写上传文件的方法
		String returnStr = "";
		// 获取当前登录用户
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		String uploadUser = null;
		if (principal instanceof PubUsers) {
			// 根据登录用户的机构编号(userOrg)获取机构描述对象
			uploadUser = ((PubUsers) principal).getUserAccount();
		}

		for (MultipartFile file : files) {

			if (file.isEmpty()) {
				//System.out.println("文件未上传");
				continue;
			}

			String fileName = file.getOriginalFilename();
			String suffix = "";// 扩展名
			int index = fileName.lastIndexOf(".");
			if (index != -1) {
				suffix = fileName.substring(fileName.lastIndexOf("."));
			}

			String fileId = NextID.getUUID();
			returnStr += fileId + ",";

			String defaultFileFolder = (String) PubData.getData("DEFAULT_FILE_FOLDER");
			File fileDir = new File(defaultFileFolder);
			if(!fileDir.exists()){
				fileDir.mkdir();
			}
			
			File targetFile = new File(defaultFileFolder, fileId + suffix);
			file.transferTo(targetFile);

			try {
				FileUpload upPO = new FileUpload();
				upPO.setFileId(fileId);
				upPO.setFileName(fileName);
				upPO.setFileType(file.getContentType());
				upPO.setFilePath(defaultFileFolder + "/" + fileId + suffix);
				upPO.setUploadUser(uploadUser);
				upPO.setUploadTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(new Date()));
				fileDao.saveMobileFile(upPO);
			} catch (Exception e) {
				if (targetFile.exists()) {
					targetFile.delete();
				}
				// 抛出DaoException使得数据库rollback
				throw new DaoException("上传文件出错", e);
			}

		}

		// 返回上传文件id的js数组格式
		if (returnStr.length() > 0) {
			//去掉最后的逗号
			returnStr = returnStr.substring(0, returnStr.length() - 1);
		}
		return returnStr;
	}
	
	public void fileDownLoad(FileDownload fileDownloadPO,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Map<String, ?> fileMap = fileDao.getFileInfo(fileDownloadPO
				.getFileId());

		File targetFile = new File((String) fileMap.get("filePath"));
		response.setHeader("Content-Disposition", "attachment;fileName="
				+ URLEncoder.encode((String)fileMap.get("fileName"),"UTF-8"));

		OutputStream os = response.getOutputStream();
		FileInputStream inputStream = new FileInputStream(targetFile);
		byte[] b = new byte[8192];
		int length = -1;
		while ((length = inputStream.read(b)) > 0) {
			os.write(b, 0, length);
		}
		inputStream.close();
		os.close();
	}

	public void fileDownLoad(String fileId,HttpServletResponse response) throws Exception {
		Map<String, ?> fileMap = fileDao.getFileInfo(fileId);
		File targetFile = new File((String) fileMap.get("filePath"));
		response.setHeader("Content-Disposition", "attachment;fileName="
				+ URLEncoder.encode((String)fileMap.get("fileName"),"UTF-8"));
		OutputStream os = response.getOutputStream();
		FileInputStream inputStream = new FileInputStream(targetFile);
		byte[] b = new byte[8192];
		int length = -1;
		while ((length = inputStream.read(b)) > 0) {
			os.write(b, 0, length);
		}
		inputStream.close();
		os.close();
	}
	public void fileDownLoad2(String fileId,HttpServletResponse response) throws Exception {
		Map<String, ?> fileMap = fileDao.getFileInfo(fileId);
		response.reset();
		//设置为流下载
        response.setContentType("application/octet-sream");
		response.setHeader("Content-Disposition", 
				"attachment;fileName="+URLEncoder.encode((String)fileMap.get("fileName"), "UTF-8"));
			File file = new File((String) fileMap.get("filePath"));
			//2、 读取文件--输入流
			InputStream input=new FileInputStream(file);
			response.setContentLength(input.available());
			//3、 写出文件--输出流
			OutputStream out = response.getOutputStream();
			byte[] buff =new byte[input.available()];
			int index=0;
			//4、执行 写出操作
			while((index= input.read(buff))!= -1){
				out.write(buff, 0, index);
				out.flush();
			}
			out.close();
			input.close();

	}
	public void deleteFile(String fileId) throws Exception {
		// TODO Auto-generated method stub
		Map<String, ?> fileMap = fileDao.getFileInfo(fileId);
		try{
			fileDao.deleteFile(fileId);
			File targetFile = new File((String) fileMap.get("filePath"));
			if (targetFile.exists()) {
				targetFile.delete();
			}
		}catch (Exception e) {
			throw new DaoException("删除文件出错", e);
		}
		
	}

	public void showFileByStream(String fileId, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		Map<String, ?> fileMap = fileDao.getFileInfo(fileId);
		File targetFile = new File((String) fileMap.get("filePath"));
		OutputStream os = response.getOutputStream();
		FileInputStream inputStream = new FileInputStream(targetFile);
		byte[] b = new byte[8192];
		int length = -1;
		while ((length = inputStream.read(b)) > 0) {
			os.write(b, 0, length);
		}
		inputStream.close();
		os.close();
	}
	
	public String getFileType(String fileId){
		return fileDao.getFileType(fileId);
	}
}
