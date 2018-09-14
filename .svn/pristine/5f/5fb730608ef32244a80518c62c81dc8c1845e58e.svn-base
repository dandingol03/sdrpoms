/**   
 * @Package: com.mobile.bsp.util 
 * @author: jwl   
 * @date: 2018年5月17日 下午12:04:39 
 */
package com.mobile.bsp.util;
import java.lang.reflect.Field;
import java.util.HashMap;
/**   
 * @Package: com.mobile.bsp.util 
 * @author: jwl   
 * @date: 2018年5月17日 下午12:04:39 
 */

public class Util {

    public static HashMap<String, Object> convertToMap(Object obj)
            throws Exception {

        HashMap<String, Object> map = new HashMap<String, Object>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0, len = fields.length; i < len; i++) {
            String varName = fields[i].getName();
            boolean accessFlag = fields[i].isAccessible();
            fields[i].setAccessible(true);

            Object o = fields[i].get(obj);
            if (o != null)
                map.put(varName, o.toString());

            fields[i].setAccessible(accessFlag);
        }

        return map;
    }
}
