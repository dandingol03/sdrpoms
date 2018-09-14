/**   
 * @Package: com.pc.exhibition.map.web.controller 
 * @author: jwl   
 * @date: 2018年4月2日 下午2:34:35 
 */
package com.mobile.exhibition.map.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobile.exhibition.map.po.DangerRangeGPS;
import com.mobile.exhibition.map.service.MobileExMapService;

/**   
 * @Package: com.pc.exhibition.map.web.controller 
 * @author: jwl   
 * @date: 2018年4月2日 下午2:34:35 
 */
@Controller
public class MobileExMapController {
	@Autowired
	private MobileExMapService mobileExMapService;
	
	/**
	 * 手机端隐患
	 * @param req
	 * @param dangerRangeGPS
	 * @return
	 */
	@RequestMapping(value = "/mobile/mobileDangerRange")
	@ResponseBody
	public List<Map<String, Object>> mobileExhibition(HttpServletRequest req,DangerRangeGPS dangerRangeGPS) {
		String rangeDanger=dangerRangeGPS.toString();
		return mobileExMapService.mobileExhibition(rangeDanger);
	}
}