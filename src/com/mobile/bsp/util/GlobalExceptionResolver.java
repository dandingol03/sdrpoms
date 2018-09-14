/**   
 * @Package: com.mobile.bsp.util 
 * @author: jwl   
 * @date: 2018年8月7日 下午2:11:47 
 */
package com.mobile.bsp.util;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.pc.bsp.common.util.HttpException;


@Controller
public class GlobalExceptionResolver implements HandlerExceptionResolver {


    /**
     * 进行全局异常过滤并处理
     *
     * @param request  action 请求
     * @param response 服务器响应
     * @param handler  发生错误的事件句柄
     * @param ex       异常情况
     * @return 视图&对象
     */

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object handler, Exception ex) {
        //使用StringBuilder提搞GC回收效率，降低垃圾回收开销
//        StringBuilder content = new StringBuilder("<++>AllInOne-busWeb抓捕到异常具体内容信息:<++> \t\n");
//        content.append("爆出异常的类型为：  " + ex.getClass() + "   \t\n");
//        content.append("异常发生的位置为：     " + handler + "   \t\n");
//        content.append("造成的原因:      " + ex.getMessage() + "     \t\n");
//        content.append("造成的详细原因为：\t\n\n");
        StackTraceElement[] trace = ex.getStackTrace();
//        for (StackTraceElement traceElement : trace){
//        	 content.append("at\t  " + traceElement + "\t\n");
//        }
		Map<String, Object> map = new HashMap<>();
		map.put("status", 500);
		map.put("mes","服务端异常");
		JSONObject json=new JSONObject();
			response.setCharacterEncoding("utf-8");
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(HttpException.CODE_500);
			try {
				response.getWriter().write(json.fromObject(map).toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//        System.err.println(content.toString());
		return null;
    }
}
