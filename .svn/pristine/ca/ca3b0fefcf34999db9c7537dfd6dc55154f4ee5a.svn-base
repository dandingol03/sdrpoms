/**
 * 
 */
package com.pc.bsp.excelfile.web.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pc.bsp.common.util.PubData;

/**
 * @author simple
 *
 */
@Controller
public class ExcelFileController {

	private static Logger logger = Logger.getLogger(ExcelFileController.class);

	@RequestMapping(value = "/excelfile/excelFileDownload")
	public void downloadFile(HttpServletRequest request,HttpServletResponse response,String fileId,String fileName) throws Exception {
		response.setContentType("multipart/form-data");
		fileName = java.net.URLDecoder.decode(fileName, "UTF-8");
		try {		
			String exportFolder = (String) PubData.getData("EXCEL_EXPORT_TMP_FOLDER");
			File targetFile = new File(exportFolder + "/" + fileId + ".xls");
			
			response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName + ".xls", "UTF-8"));
		
			OutputStream os = response.getOutputStream();
			FileInputStream inputStream = new FileInputStream(targetFile);
			byte[] b = new byte[1024 * 5];
			int length = -1;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			inputStream.close();
			os.close();
			
			//删除临时文件
			targetFile.delete();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			response.setContentType("text/html");
			try {
				response.getWriter().write("下载失败,"+e1.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				if(logger.isDebugEnabled()){
					logger.debug("下载失败",e);
				}
			}
		}
	}
}
