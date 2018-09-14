/**
 * 
 */
package com.pc.bsp.file.web.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.pc.bsp.file.po.FileDownload;
import com.pc.bsp.file.service.FileService;



/**
 * @author simple
 *
 */
@Controller
public class FileController {

	private static Logger logger = Logger.getLogger(FileController.class);
	
	@Autowired
	private FileService fileService;
	
	@RequestMapping(value = "/file/popFileUpload", method = RequestMethod.GET)
	public String popWin4Upload(ModelMap model) {
		// model.addAttribute("fileResult", "文件上传");
		return "pc/bsp/file/fileUpload";
	}
	
	@RequestMapping(value = "/file/popMutiFileUpload", method = RequestMethod.GET)
	public String popWin4MutiUpload(ModelMap model) {
		// model.addAttribute("fileResult", "文件上传");
		return "pc/bsp/file/mutiFileUpload";
	}
	// @RequestMapping(value = "/file/fileUpLoad")
	// @ResponseBody
	// public Map<String, String> fileUpLoad(
	// @RequestParam(value = "uploadFile", required = false) MultipartFile[]
	// files,
	// HttpServletRequest request,HttpServletResponse response) {
	//
	// // 获取当前登录用户
	// Object principal = SecurityContextHolder.getContext()
	// .getAuthentication().getPrincipal();
	// Map<String, String> map = new HashMap<String, String>();
	// for (MultipartFile file : files) {
	// if (file.isEmpty()) {
	// System.out.println("文件未上传");
	// } else {
	// System.out.println("文件长度: " + file.getSize());
	// System.out.println("文件类型: " + file.getContentType());
	// System.out.println("文件上传表单name: " + file.getName());
	// System.out.println("文件原名: " + file.getOriginalFilename());
	// System.out.println("========================================");
	//
	// }
	// String fileName = file.getOriginalFilename();
	// File targetFile = new File("d:/testfileupload", fileName);
	// if (!targetFile.exists()) {
	// targetFile.mkdirs();
	// }
	//
	// // 保存
	// try {
	// file.transferTo(targetFile);
	// map.put("mes", "文件上传成功");
	// } catch (Exception e) {
	// e.printStackTrace();
	// map.put("mes", "文件上传失败");
	// return map;
	// }
	//
	// }
	// return map;
	// }

	// @RequestMapping(value = "/file/fileUpLoad")
	// @ResponseBody
	// public void fileUpLoad(
	// @RequestParam(value = "uploadFile", required = false) MultipartFile[]
	// files,
	// HttpServletRequest request, HttpServletResponse response) {
	//
	// response.setCharacterEncoding("utf-8");
	// response.setContentType("text/html");
	//
	// for (MultipartFile file : files) {
	// if (file.isEmpty()) {
	// System.out.println("文件未上传");
	// continue;
	// }
	// System.out.println("文件长度: " + file.getSize());
	// System.out.println("文件类型: " + file.getContentType());
	// System.out.println("文件上传表单name: " + file.getName());
	// System.out.println("文件原名: " + file.getOriginalFilename());
	// System.out.println("========================================");
	//
	// String fileName = file.getOriginalFilename();
	// File targetFile = new File(defaultFileFolder, fileName);
	// if (!targetFile.exists()) {
	// targetFile.mkdirs();
	// }
	// // 保存
	// try {
	// file.transferTo(targetFile);
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// try {
	// response.getWriter().write("0");
	// return;
	// } catch (IOException e1) {
	// // TODO Auto-generated catch block
	// e1.printStackTrace();
	// return;
	// }
	// }
	//
	// }
	// // model.addAttribute("fileResult", "文件上传成功");
	// try {
	// response.getWriter().write("1");// "文件上传成功"
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
    /**
     * ckeditor图片上传
     * 
     * @Title imageUpload
     * @param request
     * @param response
     */
    @RequestMapping("/imageUpload")
    public void imageUpload(@RequestParam(value = "upload", required = false) MultipartFile[] files,
			@RequestParam(value = "fileUploadService", required = false) String fileUploadService,
			HttpServletRequest request, HttpServletResponse response) {
    	response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");

		try {

//			WebApplicationContext context = WebApplicationContextUtils
//					.getWebApplicationContext(request.getSession().getServletContext());
//
//			IFileService fileService = (IFileService) context.getBean(fileUploadService);
			String resultFileId = fileService.saveFileUpLoad(files);
			if(resultFileId != null && !"".equals(resultFileId)){
				try {
					response.getWriter().write(resultFileId);// "文件上传成功"/file/showFile?fileId=
					  String imageContextPath = request.getContextPath() + "/file/showPicFile?fileId=" + resultFileId;
				        response.setContentType("text/html;charset=UTF-8");
				        String callback = request.getParameter("CKEditorFuncNum");
				        PrintWriter out = response.getWriter();
				        out.println("<script type=\"text/javascript\">");
				        out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + imageContextPath + "',''" + ")");
				        out.println("</script>");
				        out.flush();
				        out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					if(logger.isDebugEnabled()){
						logger.debug("上传出错",e);
					}
				}
			}else{
				try {
					response.getWriter().write("-1");

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("上传出错",e);
			}
			try {
				response.getWriter().write("-1");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				if(logger.isDebugEnabled()){
					logger.debug("上传出错",e);
				}
			}
		}
		// model.addAttribute("fileResult", "文件上传成功");
    }
	@RequestMapping(value = "/mobile/file/fileUpLoad")
	@ResponseBody
	public void mobileFileUpLoad(@RequestParam(value = "uploadFile", required = false) MultipartFile[] files,
			@RequestParam(value = "fileUploadService", required = false) String fileUploadService,
			HttpServletRequest request, HttpServletResponse response) {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");

		try {

//			WebApplicationContext context = WebApplicationContextUtils
//					.getWebApplicationContext(request.getSession().getServletContext());
//
//			IFileService fileService = (IFileService) context.getBean(fileUploadService);
			String resultFileId = fileService.saveFileUpLoad(files);
			if(resultFileId != null && !"".equals(resultFileId)){
				try {
					response.getWriter().write(resultFileId);// "文件上传成功"
				} catch (IOException e) {
					// TODO Auto-generated catch block
					if(logger.isDebugEnabled()){
						logger.debug("上传出错",e);
					}
				}
			}else{
				try {
					response.getWriter().write("-1");

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("上传出错",e);
			}
			try {
				response.getWriter().write("-1");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				if(logger.isDebugEnabled()){
					logger.debug("上传出错",e);
				}
			}
		}
		// model.addAttribute("fileResult", "文件上传成功");
		
	}
	@RequestMapping(value = "/file/fileUpLoad")
	@ResponseBody
	public void fileUpLoad(@RequestParam(value = "uploadFile", required = false) MultipartFile[] files,
			@RequestParam(value = "fileUploadService", required = false) String fileUploadService,
			HttpServletRequest request, HttpServletResponse response) {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");

		try {

//			WebApplicationContext context = WebApplicationContextUtils
//					.getWebApplicationContext(request.getSession().getServletContext());
//
//			IFileService fileService = (IFileService) context.getBean(fileUploadService);
			String resultFileId = fileService.saveFileUpLoad(files);
			if(resultFileId != null && !"".equals(resultFileId)){
				try {
					response.getWriter().write(resultFileId);// "文件上传成功"
				} catch (IOException e) {
					// TODO Auto-generated catch block
					if(logger.isDebugEnabled()){
						logger.debug("上传出错",e);
					}
				}
			}else{
				try {
					response.getWriter().write("-1");

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("上传出错",e);
			}
			try {
				response.getWriter().write("-1");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				if(logger.isDebugEnabled()){
					logger.debug("上传出错",e);
				}
			}
		}
		// model.addAttribute("fileResult", "文件上传成功");
		
	}
	@RequestMapping(value = "/file/fileUpLoadTwo")
	@ResponseBody
	public void fileUpLoadTwo(@RequestParam(value = "uploadFileTwo", required = false) MultipartFile[] files,
			@RequestParam(value = "fileUploadService", required = false) String fileUploadService,
			HttpServletRequest request, HttpServletResponse response) {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");

		try {

//			WebApplicationContext context = WebApplicationContextUtils
//					.getWebApplicationContext(request.getSession().getServletContext());
//
//			IFileService fileService = (IFileService) context.getBean(fileUploadService);
			String resultFileId = fileService.saveFileUpLoad(files);
			if(resultFileId != null && !"".equals(resultFileId)){
				try {
					response.getWriter().write(resultFileId);// "文件上传成功"
				} catch (IOException e) {
					// TODO Auto-generated catch block
					if(logger.isDebugEnabled()){
						logger.debug("上传出错",e);
					}
				}
			}else{
				try {
					response.getWriter().write("-1");

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("上传出错",e);
			}
			try {
				response.getWriter().write("-1");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				if(logger.isDebugEnabled()){
					logger.debug("上传出错",e);
				}
			}
		}
		// model.addAttribute("fileResult", "文件上传成功");
		
	}
	@RequestMapping(value = "/file/fileUpLoadThree")
	@ResponseBody
	public void fileUpLoadThree(@RequestParam(value = "uploadFileThree", required = false) MultipartFile[] files,
			@RequestParam(value = "fileUploadService", required = false) String fileUploadService,
			HttpServletRequest request, HttpServletResponse response) {

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");

		try {

//			WebApplicationContext context = WebApplicationContextUtils
//					.getWebApplicationContext(request.getSession().getServletContext());
//
//			IFileService fileService = (IFileService) context.getBean(fileUploadService);
			String resultFileId = fileService.saveFileUpLoad(files);
			if(resultFileId != null && !"".equals(resultFileId)){
				try {
					response.getWriter().write(resultFileId);// "文件上传成功"
				} catch (IOException e) {
					// TODO Auto-generated catch block
					if(logger.isDebugEnabled()){
						logger.debug("上传出错",e);
					}
				}
			}else{
				try {
					response.getWriter().write("-1");

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("上传出错",e);
			}
			try {
				response.getWriter().write("-1");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				if(logger.isDebugEnabled()){
					logger.debug("上传出错",e);
				}
			}
		}
		// model.addAttribute("fileResult", "文件上传成功");
		
	}
	@RequestMapping(value = "/file/fileDownload")
	public void downloadFile(HttpServletRequest request,HttpServletResponse response,FileDownload fileDownloadPO) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");
		//response.setHeader("Content-Disposition", "attachment;fileName=" + "a.pdf");

//		WebApplicationContext context = WebApplicationContextUtils
//				.getWebApplicationContext(request.getSession().getServletContext());
//
//		IFileService fileService = (IFileService) context.getBean(fileDownloadPO.getFileDownloadService());
		
		try {			
		    fileService.fileDownLoad(fileDownloadPO,response);
		
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
	
	@RequestMapping(value = "/file/fileDelete")
	@ResponseBody
	public Map<String, String> deleteFile(HttpServletRequest request,FileDownload fileDownloadPO){
		Map<String, String> map = new HashMap<String, String>();
		try{
//			WebApplicationContext context = WebApplicationContextUtils
//					.getWebApplicationContext(request.getSession().getServletContext());
//			IFileService fileService = (IFileService) context.getBean(fileDownloadPO.getFileDownloadService());
			fileService.deleteFile(fileDownloadPO.getFileId());
		}catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("删除文件失败",e);
			}
			map.put("mes", "删除文件失败");
		}
		return map;//重定向
	}
	
	@RequestMapping(value = "/file/showPicFile")
	public void showPicFile(HttpServletResponse response,@RequestParam("fileId") String fileId) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("image/jpeg");
		response.setHeader("Cache-control", "no-cache");

		
		try {			
		    fileService.showFileByStream(fileId,response);
		
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			response.setContentType("text/html");
			try {
				response.getWriter().write("图片展示失败,"+e1.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				if(logger.isDebugEnabled()){
					logger.debug("图片展示失败",e);
				}
			}
		}
	}
	
	@RequestMapping(value = "/file/showVideoFile")
	public void showVideoFile(HttpServletResponse response,@RequestParam("fileId") String fileId) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("video/mpeg4");
		response.setHeader("Cache-control", "no-cache");
		
		try {			
		    fileService.showFileByStream(fileId,response);
		
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			response.setContentType("text/html");
			try {
				response.getWriter().write("视频下载失败,"+e1.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				if(logger.isDebugEnabled()){
					logger.debug("视频下载失败",e);
				}
			}
		}
	}
	
	@RequestMapping(value = "/file/showPicFilePopWin")
	public String showPicFilePopWin(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("fileId") String fileId) {
		request.setAttribute("fileId", fileId);
		return "pc/bsp/file/showPicture";
	}
	
	@RequestMapping(value = "/file/showVideoFilePopWin")
	public String showVideoFilePopWin(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("fileId") String fileId) {
		request.setAttribute("fileId", fileId);
		return "pc/bsp/file/showVideo";
	}
	@RequestMapping(value = "/file/showFile")
	public void showFile(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("fileId") String fileId) throws IOException {
		// TODO 根据文件类型进行判断
		String strContextPath = request.getContextPath();
		String fileType = fileService.getFileType(fileId);
		fileType = fileType.substring(0, 5);
		switch(fileType){
		case "image":
			// TODO 查看图片
			response.sendRedirect(strContextPath+"/file/showPicFilePopWin?fileId="+fileId);
			return;
		case "video":
			// TODO 查看视频
			response.sendRedirect(strContextPath+"/file/showVideoFilePopWin?fileId="+fileId);
			return;
		default:
			// TODO 查看文件
			response.setCharacterEncoding("utf-8");
			response.setContentType("multipart/form-data");
			try {			
			    fileService.fileDownLoad(fileId,response);
			} catch (Exception e1) {
				e1.printStackTrace();
				response.setContentType("text/html");
				try {
					response.getWriter().write("下载失败,"+e1.getMessage());
				} catch (IOException e) {
					if(logger.isDebugEnabled()){
						logger.debug("下载失败",e);
					}
				}
			}
			return;
		}
	}
	@RequestMapping(value = "/file/showFile2")
	public void showFile2(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("fileId") String fileId) throws IOException {
			// TODO 查看文件
			response.setCharacterEncoding("utf-8");
			response.setContentType("multipart/form-data");
			try {			
			    fileService.fileDownLoad2(fileId,response);
			} catch (Exception e1) {
				e1.printStackTrace();
				response.setContentType("text/html");
				try {
					response.getWriter().write("下载失败,"+e1.getMessage());
				} catch (IOException e) {
					if(logger.isDebugEnabled()){
						logger.debug("下载失败",e);
					}
				}
			}
			return;
	}
}