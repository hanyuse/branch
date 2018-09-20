package cn.com.sparknet.branchRecord.common.util;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

    /**
     * 说明：设置Cookie
     * @param response
     * @param key		健
     * @param value		值
     * @param maxAge	存活周期 单位：秒 如果设置为负值，则在内存中保存，关闭浏览器就失效
     */
    public static final void setCookie(HttpServletResponse response,String key,String value,int maxAge){
    	String encodeValue="";
		try {
			encodeValue = java.net.URLEncoder.encode(value,"utf-8");
			Cookie cookie = new Cookie(key,encodeValue);
	    	cookie.setMaxAge(maxAge);
	    	cookie.setValue(encodeValue);
	    	response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage());
		}
    }
    /**
     * 说明：获取Cookie
     * @param request
     * @param key		健
     * @return
     */
    public static final String getCookie(HttpServletRequest request,String key){
    	String value="";
        try {
	    	Cookie c = null;
	    	Cookie[] cookies = request.getCookies();
	        for (int i = 0; i < cookies.length; i++) {
	            c = cookies[i];
	            if(c.getName().equals(key)){
	            	value=java.net.URLDecoder.decode(c.getValue(), "UTF-8");
	            }
	        }
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage());
		}
		return value;
    }
    
    /**
     * 说明：修改Cookie
     * @param request
     * @param response
     * @param key
     * @param newValue
     */
    public static final void updCookie(HttpServletRequest request,HttpServletResponse response,String key,String newValue){
    	try {
			String encodeValue = java.net.URLEncoder.encode(newValue,"utf-8");
			Cookie[] cookies = request.getCookies();
	    	if(cookies.length>1){
	    		for(int i = 0;i<cookies.length;i++){
	    			if(cookies[i].getName().equals(key)){
	    				cookies[i].setValue(encodeValue);
	    				response.addCookie(cookies[i]);
	    				break;
	    			}
	    		}
	    	}
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage());
		}
    }
    /**
     * 说明：删除Cookie
     * @param response
     * @param key
     */
    public static final void delCookie(HttpServletResponse response,String key){
    	Cookie cookie = new Cookie(key,null);
    	cookie.setMaxAge(0);
    	response.addCookie(cookie);
    }
	
}
