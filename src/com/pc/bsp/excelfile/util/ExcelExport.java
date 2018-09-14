package com.pc.bsp.excelfile.util;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.pc.bsp.common.util.PubData;

import jxl.CellView;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelExport {

	private static Logger logger = Logger.getLogger(ExcelExport.class);

	/**
	 * 生成excel导出文件
	 * @param list 数据库查询结果
	 * @param dir excel文件的存放目录
	 * @param strtitle excel的sheet页的名称
	 * @param titleMap 头标题 titleMap.put("month", "月份");
	 * @return 生成excel文件的绝对路径
	 * @throws Exception
	 */
	private static String exportAndReturnFilePath(List<Map<String, Object>> list, String dir, String strtitle, Map<String, String> titleMap) throws Exception {
		String filePath = dir + "/"+ UUID.randomUUID()+".xls";
		WritableCellFormat normalFormat = new WritableCellFormat(NumberFormats.TEXT);
		WritableCellFormat normalFormat1 = new WritableCellFormat(NumberFormats.TEXT);
		WritableCellFormat normalFormat2 = new WritableCellFormat();
		WritableCellFormat normalFormat3 = new WritableCellFormat(new WritableFont(WritableFont.TIMES, 12,
				WritableFont.NO_BOLD));
		try {

			WritableFont bold1 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);// 设置字体种类和黑体显示,字体为Arial,字号大小为10,采用黑体
			normalFormat.setAlignment(Alignment.CENTRE);
			normalFormat.setFont(bold1);
			normalFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			normalFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			WritableFont bold2 = new WritableFont(WritableFont.TIMES, 10,
					WritableFont.NO_BOLD);// 设置字体种类和黑体显示,字体为Arial,字号大小为10,采用黑体
			normalFormat1.setFont(bold2);
			normalFormat1.setAlignment(Alignment.CENTRE);
			normalFormat1.setVerticalAlignment(VerticalAlignment.CENTRE);
			normalFormat1.setBorder(Border.ALL, BorderLineStyle.THIN);
			normalFormat1.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			normalFormat2.setAlignment(Alignment.RIGHT);
			normalFormat2.setVerticalAlignment(VerticalAlignment.CENTRE);
			normalFormat2.setBorder(Border.ALL, BorderLineStyle.THIN);
			normalFormat2.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			normalFormat3.setAlignment(Alignment.CENTRE);
			normalFormat3.setVerticalAlignment(VerticalAlignment.CENTRE);
			normalFormat3.setBorder(Border.ALL, BorderLineStyle.THIN);
			normalFormat3.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			WritableWorkbook book = Workbook.createWorkbook(new File(filePath));
		    WritableSheet sheet1 = book.createSheet(strtitle, 0);
			// 设置列宽
			int line = 0;
			Set<String> titleSet = titleMap.keySet();
			// 表头
			sheet1.mergeCells(0, line, titleSet.size() - 1, line + 3);

			Label title = new Label(0, line, strtitle, normalFormat3);
			sheet1.addCell(title);
			line = line + 4;

			Iterator<String>titleIterator = titleSet.iterator();
			int n = 0;
			while(titleIterator.hasNext()){
				sheet1.addCell(new Label(n, line, titleMap.get(titleIterator.next()), normalFormat));
				sheet1.setColumnView(n, 15);
				n++;
			}
			line = line + 1;

			// 数据
			if (list != null) {

				for (Map<String, Object> map : list) {

					titleIterator = titleSet.iterator();
					n = 0;
					while(titleIterator.hasNext()){
						String key = titleIterator.next();
						if(map.get(key) == null || "null".equalsIgnoreCase(map.get(key)+"") ){
							sheet1.addCell(new Label(n, line, "", normalFormat1));
						}else{
							sheet1.addCell(new Label(n, line, map.get(key) + "", normalFormat1));
						}

						n++;
					}
					line = line + 1;
				}
			}
			// 写入数据并关闭文件
			book.write();
			book.close();
			return filePath;
		} catch (Exception e) {
			if(logger.isInfoEnabled()){
				logger.info("生成导出excle出错", e);
			}
			throw e;
		}
	}

	/**
	 * 生成excel导出文件
	 * @param list 数据库查询结果
	 * @param strtitle excel的sheet页的名称
	 * @param titleMap 头标题 titleMap.put("month", "月份");
	 * @return 生成excel文件的UUID
	 * @throws Exception
	 */
	public static String exportAndReturnFileId(List<Map<String, Object>> list, String strtitle, Map<String, String> titleMap) throws Exception {
		String fileId = UUID.randomUUID().toString();

		WritableCellFormat normalFormat = new WritableCellFormat(NumberFormats.TEXT);
		WritableCellFormat normalFormat1 = new WritableCellFormat(NumberFormats.TEXT);
		WritableCellFormat normalFormat2 = new WritableCellFormat();
		WritableCellFormat normalFormat3 = new WritableCellFormat(new WritableFont(WritableFont.TIMES, 12,
				WritableFont.NO_BOLD));
		try {

			WritableFont bold1 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);// 设置字体种类和黑体显示,字体为Arial,字号大小为10,采用黑体
			normalFormat.setAlignment(Alignment.CENTRE);
			normalFormat.setFont(bold1);
			normalFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			normalFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			WritableFont bold2 = new WritableFont(WritableFont.TIMES, 10,
					WritableFont.NO_BOLD);// 设置字体种类和黑体显示,字体为Arial,字号大小为10,采用黑体
			normalFormat1.setFont(bold2);
			normalFormat1.setAlignment(Alignment.CENTRE);
			normalFormat1.setVerticalAlignment(VerticalAlignment.CENTRE);
			normalFormat1.setBorder(Border.ALL, BorderLineStyle.THIN);
			normalFormat1.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			normalFormat2.setAlignment(Alignment.RIGHT);
			normalFormat2.setVerticalAlignment(VerticalAlignment.CENTRE);
			normalFormat2.setBorder(Border.ALL, BorderLineStyle.THIN);
			normalFormat2.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			normalFormat3.setAlignment(Alignment.CENTRE);
			normalFormat3.setVerticalAlignment(VerticalAlignment.CENTRE);
			normalFormat3.setBorder(Border.ALL, BorderLineStyle.THIN);
			normalFormat3.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			String exportFolder = (String) PubData.getData("EXCEL_EXPORT_TMP_FOLDER");
			File fileDir = new File(exportFolder);
			if (!fileDir.exists()) {
				fileDir.mkdir();
			}

			WritableWorkbook book = Workbook.createWorkbook(new File(exportFolder + "/"+ fileId +".xls"));
		    WritableSheet sheet1 = book.createSheet(strtitle, 0);

			// 设置列宽
			int line = 0;
			Set<String> titleSet = titleMap.keySet();
			// 表头
			sheet1.mergeCells(0, line, titleSet.size() - 1, line + 3);

			Label title = new Label(0, line, strtitle, normalFormat3);
			sheet1.addCell(title);
			line = line + 4;

			Iterator<String>titleIterator = titleSet.iterator();
			int n = 0;
			while(titleIterator.hasNext()){
				//设置列宽 2018-04-11
				if(n==3){
					sheet1.addCell(new Label(n, line, titleMap.get(titleIterator.next()), normalFormat));
					sheet1.setColumnView(n, 20);
				}else if(n==1){
					sheet1.addCell(new Label(n, line, titleMap.get(titleIterator.next()), normalFormat));
					sheet1.setColumnView(n, 10);
				}else if(n==8){
					sheet1.addCell(new Label(n, line, titleMap.get(titleIterator.next()), normalFormat));
					sheet1.setColumnView(n, 25);
				}else if(n==10){
					sheet1.addCell(new Label(n, line, titleMap.get(titleIterator.next()), normalFormat));
					sheet1.setColumnView(n, 40);
				}else if(n==11){
					sheet1.addCell(new Label(n, line, titleMap.get(titleIterator.next()), normalFormat));
					sheet1.setColumnView(n, 20);
				}else{
					sheet1.addCell(new Label(n, line, titleMap.get(titleIterator.next()), normalFormat));
					sheet1.setColumnView(n, 15);
				}

				n++;
			}
			line = line + 1;

			// 数据
			if (list != null) {

				for (Map<String, Object> map : list) {

					titleIterator = titleSet.iterator();
					n = 0;
					while(titleIterator.hasNext()){
						String key = titleIterator.next();
						if(map.get(key) == null || "null".equalsIgnoreCase(map.get(key)+"") ){
							sheet1.addCell(new Label(n, line, "", normalFormat1));
						}else{
							sheet1.addCell(new Label(n, line, map.get(key) + "", normalFormat1));
						}

						n++;
					}
					line = line + 1;
				}
			}
			// 写入数据并关闭文件
			book.write();
			book.close();
			return fileId;
		} catch (Exception e) {
			if(logger.isInfoEnabled()){
				logger.info("生成导出excle出错", e);
			}
			throw e;
		}
	}

	/**
	 * 生成excel导出文件(自行传入fileId)
	 * @param list  数据库查询结果
	 * @param dir   excel文件的存放目录
	 * @param fileId  文件名uuid
	 * @param strtitle  excel的sheet页的名称
	 * @param titleMap 头标题 titleMap.put("month", "月份");
	 * @throws Exception
	 */
	private static void export(List<Map<String, Object>> list, String dir,String fileId, String strtitle, Map<String, String> titleMap) throws Exception {
		String filePath = dir + "/"+ fileId+".xls";
		WritableCellFormat normalFormat = new WritableCellFormat(NumberFormats.TEXT);
		WritableCellFormat normalFormat1 = new WritableCellFormat(NumberFormats.TEXT);
		WritableCellFormat normalFormat2 = new WritableCellFormat();
		WritableCellFormat normalFormat3 = new WritableCellFormat(new WritableFont(WritableFont.TIMES, 12,
				WritableFont.NO_BOLD));
		try {

			WritableFont bold1 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);// 设置字体种类和黑体显示,字体为Arial,字号大小为10,采用黑体
			normalFormat.setAlignment(Alignment.CENTRE);
			normalFormat.setFont(bold1);
			normalFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			normalFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			WritableFont bold2 = new WritableFont(WritableFont.TIMES, 10,
					WritableFont.NO_BOLD);// 设置字体种类和黑体显示,字体为Arial,字号大小为10,采用黑体
			normalFormat1.setFont(bold2);
			normalFormat1.setAlignment(Alignment.CENTRE);
			normalFormat1.setVerticalAlignment(VerticalAlignment.CENTRE);
			normalFormat1.setBorder(Border.ALL, BorderLineStyle.THIN);
			normalFormat1.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			normalFormat2.setAlignment(Alignment.RIGHT);
			normalFormat2.setVerticalAlignment(VerticalAlignment.CENTRE);
			normalFormat2.setBorder(Border.ALL, BorderLineStyle.THIN);
			normalFormat2.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			normalFormat3.setAlignment(Alignment.CENTRE);
			normalFormat3.setVerticalAlignment(VerticalAlignment.CENTRE);
			normalFormat3.setBorder(Border.ALL, BorderLineStyle.THIN);
			normalFormat3.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			WritableWorkbook book = Workbook.createWorkbook(new File(filePath));
		    WritableSheet sheet1 = book.createSheet(strtitle, 0);
			// 设置列宽
			int line = 0;
			Set<String> titleSet = titleMap.keySet();
			// 表头
			sheet1.mergeCells(0, line, titleSet.size() - 1, line + 3);

			Label title = new Label(0, line, strtitle, normalFormat3);
			sheet1.addCell(title);
			line = line + 4;

			Iterator<String>titleIterator = titleSet.iterator();
			int n = 0;
			while(titleIterator.hasNext()){
				sheet1.addCell(new Label(n, line, titleMap.get(titleIterator.next()), normalFormat));
				sheet1.setColumnView(n, 15);
				n++;
			}
			line = line + 1;

			// 数据
			if (list != null) {

				for (Map<String, Object> map : list) {

					titleIterator = titleSet.iterator();
					n = 0;
					while(titleIterator.hasNext()){
						String key = titleIterator.next();
						if(map.get(key) == null || "null".equalsIgnoreCase(map.get(key)+"") ){
							sheet1.addCell(new Label(n, line, "", normalFormat1));
						}else{
							sheet1.addCell(new Label(n, line, map.get(key) + "", normalFormat1));
						}

						n++;
					}
					line = line + 1;
				}
			}
			// 写入数据并关闭文件
			book.write();
			book.close();
		} catch (Exception e) {
			if(logger.isInfoEnabled()){
				logger.info("生成导出excle出错", e);
			}
			throw e;
		}
	}

	/**
	 * 生成excel导出文件(多表头)
	 * @param list 数据库查询结果
	 * @param dir  excel文件的存放目录
	 * @param strtitle excel的sheet页的名称
	 * @param titleMap 头标题 titleMap.put("month", "月份");
	 * @param titleMap2 第二头标题 key为单元格的起始位置和终止位置（起始列，起始行，终止列，终止行）例如：titleMap2.put("1,4,1,5", "年度");
	 * @return  生成excel文件的绝对路径
	 * @throws Exception
	 */
	private static String exportAndReturnFilePath(List<Map<String, Object>> list, String dir, String strtitle, Map<String, String> titleMap, Map<String, String> titleMap2) throws Exception {

		String filePath = dir + "/"+ UUID.randomUUID()+".xls";
		WritableCellFormat normalFormat = new WritableCellFormat(NumberFormats.TEXT);
		WritableCellFormat normalFormat1 = new WritableCellFormat(NumberFormats.TEXT);
		WritableCellFormat normalFormat2 = new WritableCellFormat();
		WritableCellFormat normalFormat3 = new WritableCellFormat(new WritableFont(WritableFont.TIMES, 12,
				WritableFont.NO_BOLD));
		try {

			WritableFont bold1 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);// 设置字体种类和黑体显示,字体为Arial,字号大小为10,采用黑体
			normalFormat.setAlignment(Alignment.CENTRE);
			normalFormat.setFont(bold1);
			normalFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			normalFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			WritableFont bold2 = new WritableFont(WritableFont.TIMES, 10,
					WritableFont.NO_BOLD);// 设置字体种类和黑体显示,字体为Arial,字号大小为10,采用黑体
			normalFormat1.setFont(bold2);
			normalFormat1.setAlignment(Alignment.CENTRE);
			normalFormat1.setVerticalAlignment(VerticalAlignment.CENTRE);
			normalFormat1.setBorder(Border.ALL, BorderLineStyle.THIN);
			normalFormat1.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			normalFormat2.setAlignment(Alignment.RIGHT);
			normalFormat2.setVerticalAlignment(VerticalAlignment.CENTRE);
			normalFormat2.setBorder(Border.ALL, BorderLineStyle.THIN);
			normalFormat2.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			normalFormat3.setAlignment(Alignment.CENTRE);
			normalFormat3.setVerticalAlignment(VerticalAlignment.CENTRE);
			normalFormat3.setBorder(Border.ALL, BorderLineStyle.THIN);
			normalFormat3.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			WritableWorkbook book = Workbook.createWorkbook(new File(filePath));
		    WritableSheet sheet1 = book.createSheet(strtitle, 0);
			// 设置列宽
			int line = 0;
			Set<String> titleSet = titleMap.keySet();
			// 表头
			sheet1.mergeCells(0, line, titleSet.size() - 1, line + 3);

			Label title = new Label(0, line, strtitle, normalFormat3);
			sheet1.addCell(title);
			line = line + 4;

			Iterator<String>titleIterator1 = titleMap2.keySet().iterator();
			Iterator<String>titleIterator = titleSet.iterator();
			while(titleIterator1.hasNext()){
				String titleKey = titleIterator1.next();
				String[] index = titleKey.split(",");
				sheet1.mergeCells(Integer.valueOf(index[0]), Integer.valueOf(index[1]), Integer.valueOf(index[2]), Integer.valueOf(index[3]));
				sheet1.addCell(new Label(Integer.valueOf(index[0]), Integer.valueOf(index[1]), titleMap2.get(titleKey), normalFormat));
			}

			line = line + 2;
			// 数据
			int n = 0;
			if (list != null) {

				for (Map<String, Object> map : list) {

					titleIterator = titleSet.iterator();
					n = 0;
					while(titleIterator.hasNext()){
						String key = titleIterator.next();
						if(map.get(key) == null || "null".equalsIgnoreCase(map.get(key)+"") ){
							sheet1.addCell(new Label(n, line, "", normalFormat1));
						}else{
							sheet1.addCell(new Label(n, line, map.get(key) + "", normalFormat1));
						}
						n++;
					}
					line = line + 1;
				}
			}
			// 写入数据并关闭文件
			book.write();
			book.close();
			return filePath;
		} catch (Exception e) {
			if(logger.isInfoEnabled()){
				logger.info("生成导出excle出错", e);
			}
			throw e;
		}
	}

	/**
	 * 生成excel导出文件(多表头)
	 * @param list 数据库查询结果
	 * @param strtitle excel的sheet页的名称
	 * @param titleMap 头标题 titleMap.put("month", "月份");
	 * @param titleMap2 第二头标题 key为单元格的起始位置和终止位置（起始列，起始行，终止列，终止行）例如：titleMap2.put("1,4,1,5", "年度");
	 * @return  生成excel文件的UUID
	 * @throws Exception
	 */
	public static String exportAndReturnFileId(List<Map<String, Object>> list, String strtitle, Map<String, String> titleMap, Map<String, String> titleMap2) throws Exception {
		String fileId = UUID.randomUUID().toString();

		WritableCellFormat normalFormat = new WritableCellFormat(NumberFormats.TEXT);
		WritableCellFormat normalFormat1 = new WritableCellFormat(NumberFormats.TEXT);
		WritableCellFormat normalFormat2 = new WritableCellFormat();
		WritableCellFormat normalFormat3 = new WritableCellFormat(new WritableFont(WritableFont.TIMES, 12,
				WritableFont.NO_BOLD));
		try {

			WritableFont bold1 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);// 设置字体种类和黑体显示,字体为Arial,字号大小为10,采用黑体
			normalFormat.setAlignment(Alignment.CENTRE);
			normalFormat.setFont(bold1);
			normalFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			normalFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			WritableFont bold2 = new WritableFont(WritableFont.TIMES, 10,
					WritableFont.NO_BOLD);// 设置字体种类和黑体显示,字体为Arial,字号大小为10,采用黑体
			normalFormat1.setFont(bold2);
			normalFormat1.setAlignment(Alignment.CENTRE);
			normalFormat1.setVerticalAlignment(VerticalAlignment.CENTRE);
			normalFormat1.setBorder(Border.ALL, BorderLineStyle.THIN);
			normalFormat1.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			normalFormat2.setAlignment(Alignment.RIGHT);
			normalFormat2.setVerticalAlignment(VerticalAlignment.CENTRE);
			normalFormat2.setBorder(Border.ALL, BorderLineStyle.THIN);
			normalFormat2.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			normalFormat3.setAlignment(Alignment.CENTRE);
			normalFormat3.setVerticalAlignment(VerticalAlignment.CENTRE);
			normalFormat3.setBorder(Border.ALL, BorderLineStyle.THIN);
			normalFormat3.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			String exportFolder = (String) PubData.getData("EXCEL_EXPORT_TMP_FOLDER");
			File fileDir = new File(exportFolder);
			if (!fileDir.exists()) {
				fileDir.mkdir();
			}

			WritableWorkbook book = Workbook.createWorkbook(new File(exportFolder + "/"+ fileId +".xls"));
		    WritableSheet sheet1 = book.createSheet(strtitle, 0);
			// 设置列宽
			int line = 0;
			Set<String> titleSet = titleMap.keySet();
			// 表头
			sheet1.mergeCells(0, line, titleSet.size() - 1, line + 3);

			Label title = new Label(0, line, strtitle, normalFormat3);
			sheet1.addCell(title);
			line = line + 4;

			Iterator<String>titleIterator1 = titleMap2.keySet().iterator();
			Iterator<String>titleIterator = titleSet.iterator();
			while(titleIterator1.hasNext()){
				String titleKey = titleIterator1.next();
				String[] index = titleKey.split(",");
				sheet1.mergeCells(Integer.valueOf(index[0]), Integer.valueOf(index[1]), Integer.valueOf(index[2]), Integer.valueOf(index[3]));
				sheet1.addCell(new Label(Integer.valueOf(index[0]), Integer.valueOf(index[1]), titleMap2.get(titleKey), normalFormat));
			}

			line = line + 2;
			// 数据
			int n = 0;
			if (list != null) {

				for (Map<String, Object> map : list) {

					titleIterator = titleSet.iterator();
					n = 0;
					while(titleIterator.hasNext()){
						String key = titleIterator.next();
						if(map.get(key) == null || "null".equalsIgnoreCase(map.get(key)+"") ){
							sheet1.addCell(new Label(n, line, "", normalFormat1));
						}else{
							sheet1.addCell(new Label(n, line, map.get(key) + "", normalFormat1));
						}
						n++;
					}
					line = line + 1;
				}
			}
			// 写入数据并关闭文件
			book.write();
			book.close();
			return fileId;
		} catch (Exception e) {
			if(logger.isInfoEnabled()){
				logger.info("生成导出excle出错", e);
			}
			throw e;
		}
	}

	/**
	 * 生成excel导出文件(多表头,自行传入fileId)
	 * @param list 数据库查询结果
	 * @param dir  excel文件的存放目录
	 * @param fileId  文件名uuid
	 * @param strtitle excel的sheet页的名称
	 * @param titleMap 头标题 titleMap.put("month", "月份");
	 * @param titleMap2 第二头标题 key为单元格的起始位置和终止位置（起始列，起始行，终止列，终止行）例如：titleMap2.put("1,4,1,5", "年度");
	 * @return  生成excel文件的绝对路径
	 * @throws Exception
	 */
	private static void export(List<Map<String, Object>> list, String dir,String fileId, String strtitle, Map<String, String> titleMap, Map<String, String> titleMap2) throws Exception {

		String filePath = dir + "/"+ fileId +".xls";
		WritableCellFormat normalFormat = new WritableCellFormat(NumberFormats.TEXT);
		WritableCellFormat normalFormat1 = new WritableCellFormat(NumberFormats.TEXT);
		WritableCellFormat normalFormat2 = new WritableCellFormat();
		WritableCellFormat normalFormat3 = new WritableCellFormat(new WritableFont(WritableFont.TIMES, 12,
				WritableFont.NO_BOLD));
		try {

			WritableFont bold1 = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);// 设置字体种类和黑体显示,字体为Arial,字号大小为10,采用黑体
			normalFormat.setAlignment(Alignment.CENTRE);
			normalFormat.setFont(bold1);
			normalFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			normalFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			WritableFont bold2 = new WritableFont(WritableFont.TIMES, 10,
					WritableFont.NO_BOLD);// 设置字体种类和黑体显示,字体为Arial,字号大小为10,采用黑体
			normalFormat1.setFont(bold2);
			normalFormat1.setAlignment(Alignment.CENTRE);
			normalFormat1.setVerticalAlignment(VerticalAlignment.CENTRE);
			normalFormat1.setBorder(Border.ALL, BorderLineStyle.THIN);
			normalFormat1.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			normalFormat2.setAlignment(Alignment.RIGHT);
			normalFormat2.setVerticalAlignment(VerticalAlignment.CENTRE);
			normalFormat2.setBorder(Border.ALL, BorderLineStyle.THIN);
			normalFormat2.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			normalFormat3.setAlignment(Alignment.CENTRE);
			normalFormat3.setVerticalAlignment(VerticalAlignment.CENTRE);
			normalFormat3.setBorder(Border.ALL, BorderLineStyle.THIN);
			normalFormat3.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);

			WritableWorkbook book = Workbook.createWorkbook(new File(filePath));
		    WritableSheet sheet1 = book.createSheet(strtitle, 0);
			// 设置列宽
			int line = 0;
			Set<String> titleSet = titleMap.keySet();
			// 表头
			sheet1.mergeCells(0, line, titleSet.size() - 1, line + 3);

			Label title = new Label(0, line, strtitle, normalFormat3);
			sheet1.addCell(title);
			line = line + 4;

			Iterator<String>titleIterator1 = titleMap2.keySet().iterator();
			Iterator<String>titleIterator = titleSet.iterator();
			while(titleIterator1.hasNext()){
				String titleKey = titleIterator1.next();
				String[] index = titleKey.split(",");
				sheet1.mergeCells(Integer.valueOf(index[0]), Integer.valueOf(index[1]), Integer.valueOf(index[2]), Integer.valueOf(index[3]));
				sheet1.addCell(new Label(Integer.valueOf(index[0]), Integer.valueOf(index[1]), titleMap2.get(titleKey), normalFormat));
			}

			line = line + 2;
			// 数据
			int n = 0;
			if (list != null) {

				for (Map<String, Object> map : list) {

					titleIterator = titleSet.iterator();
					n = 0;
					while(titleIterator.hasNext()){
						String key = titleIterator.next();
						if(map.get(key) == null || "null".equalsIgnoreCase(map.get(key)+"") ){
							sheet1.addCell(new Label(n, line, "", normalFormat1));
						}else{
							sheet1.addCell(new Label(n, line, map.get(key) + "", normalFormat1));
						}
						n++;
					}
					line = line + 1;
				}
			}
			// 写入数据并关闭文件
			book.write();
			book.close();

		} catch (Exception e) {
			if(logger.isInfoEnabled()){
				logger.info("生成导出excle出错", e);
			}
			throw e;
		}
	}

}
