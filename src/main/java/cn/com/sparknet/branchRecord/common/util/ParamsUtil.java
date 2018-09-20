package cn.com.sparknet.branchRecord.common.util;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


/**
 *  获取客户端通过http请求传递的参数，将其存放于集合中
 *  
 *  @author zhangxd 
 * */
public class ParamsUtil { 
	
	@SuppressWarnings ( "rawtypes" )
    public static Map<String, Object> requestParamMap(HttpServletRequest request) {
		java.util.Iterator iter = request.getParameterMap().entrySet().iterator();
		Map<String, Object> map = new LinkedHashMap<String, Object>();
        while (iter.hasNext()) {
            java.util.Map.Entry entry = (java.util.Map.Entry)iter.next();
            String key = StringUtil.strNewFiltrate(entry.getKey().toString());
            String[] checkboxValues = request.getParameterValues(key);
            String value = StringUtil.strNewFiltrate(request.getParameter(key)); // 这里能成功输出VAL值

            if(checkboxValues != null && checkboxValues.length>1){
            	String checkboxV = "";
            	for(int i = 0; i < checkboxValues.length ; i++){
            		checkboxV += checkboxValues[i]+",";
            	}
            	if(!"".equals(checkboxV)){
            		value = checkboxV.substring(0, checkboxV.length()-1);
            	}
            }
            
            map.put(key, StringUtil.strNewFiltrate(value)); //添加安全过滤，替换非法字符
        }
        return map;
	}
}
