package cn.com.sparknet.branchRecord.common.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

/**
 * IP工具类
 * @author zhangxd
 * 
 */
public class IpUtil {

	/**
	 * @return 本机IP
	 * @throws SocketException
	 */
	public static String getRealIp() throws SocketException {
		String localip = null;// 本地IP，如果没有配置外网IP则返回它
		String netip = null;// 外网IP

		Enumeration<NetworkInterface> netInterfaces = 
			NetworkInterface.getNetworkInterfaces();
		InetAddress ip = null;
		boolean finded = false;// 是否找到外网IP
		while (netInterfaces.hasMoreElements() && !finded) {
			NetworkInterface ni = netInterfaces.nextElement();
			Enumeration<InetAddress> address = ni.getInetAddresses();
			while (address.hasMoreElements()) {
				ip = address.nextElement();
				if (!ip.isSiteLocalAddress() 
						&& !ip.isLoopbackAddress() 
						&& ip.getHostAddress().indexOf(":") == -1) {// 外网IP
					netip = ip.getHostAddress();
					finded = true;
					break;
				} else if (ip.isSiteLocalAddress() 
						&& !ip.isLoopbackAddress() 
						&& ip.getHostAddress().indexOf(":") == -1) {// 内网IP
					localip = ip.getHostAddress();
				}
			}
		}
	
		if (netip != null && !"".equals(netip)) {
			return netip;
		} else {
			return localip;
		}
	}
	
    /**
     * 获取IP地址
     * @param request
     * @return
     */
	public static final String getIpAddress(HttpServletRequest request) {         
        String ip = request.getHeader("x-forwarded-for");         
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {         
            ip = request.getHeader("Proxy-Client-IP");         
        }         
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {         
            ip = request.getHeader("WL-Proxy-Client-IP");         
        }         
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {         
            ip = request.getRemoteAddr();         
        }  
        return ip;        
   }

    
    /**
     * 获取本机Mac地址
     * @return
     */
    public static final String getMacAddress() {
    	String line="";
    	String MacAddress="";
    	InputStreamReader is=null;
    	LineNumberReader ln=null;
    	try {
    	    Process process = Runtime.getRuntime().exec("ipconfig /all");
    	    is = new InputStreamReader(process.getInputStream());
    	    ln = new LineNumberReader(is);
    	    while ((line = ln.readLine()) != null){
    	        if (line.indexOf("Physical Address") > 0) {
    	        	MacAddress = line.substring(line.indexOf("-") - 2);
    	        }
    	    }
    	} catch (IOException e) {
    		throw new RuntimeException(e.getMessage());
    	} finally{
    		try{
	    		if(is!=null){
	    			is.close();
	    			is=null;
	    		}
	    		if(ln!=null){
	    			ln.close();
	    			ln=null;
	    		}
    		}catch(Exception e){
    			throw new RuntimeException(e.getMessage());
    		}
    	}
    	return MacAddress;
    }

    
    /**
     * 根据IP获取Mac地址
     * @param ip
     * @return
     * @throws IOException
     */
    public static final String getMacAddress(String ip){
    	if(ip.equals("localhost")||ip.equals("127.0.0.1")){
    		return getMacAddress();
    	}
    	String line="";
        String macAddress = "";
        InputStreamReader is = null;
        LineNumberReader ln = null;
        try{
	        Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
	        is = new InputStreamReader(p.getInputStream());
	        ln = new LineNumberReader(is);
	        for (int i = 1; i < 100; i++) {
	        	line = ln.readLine();
	            if (line != null) {
	                if (line.indexOf("MAC Address") > 1) {
	                    macAddress = line.substring(line.indexOf("MAC Address") + 14, line.length());
	                    break;
	                }
	            }
	        }
        }catch(IOException e){
        	throw new RuntimeException(e.getMessage());
        }finally{
    		try{
	    		if(is!=null){
	    			is.close();
	    			is=null;
	    		}
	    		if(ln!=null){
	    			ln.close();
	    			ln=null;
	    		}
    		}catch(Exception e){
    			throw new RuntimeException(e.getMessage());
    		}
    	}
        return macAddress;
    }

	
}
