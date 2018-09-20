package cn.com.sparknet.branchRecord.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import net.sf.json.JSONObject;

@Service
public class BranchRecordService {
	private static final Logger logger = LogManager.getLogger(BranchRecordService.class);
	
	public JSONObject queryptent(String path,JSONObject object){
		JSONObject result = new JSONObject();
		return result;
	}
	
	/**
	 * 发送http请求公共方法
	 * @param path
	 * @param object
	 * @return
	 */
	public JSONObject sendHttp(String path,JSONObject object){
		JSONObject result = new JSONObject();
		
		
		
		
		
		
		
		
		
		return result;
	}
	
	/**
	 * 读取资源配置文件
	 * @return
	 */
	public  Map<String,Object> getProperties(){
		InputStream in = null;
		Properties properties = new Properties();
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			in = BranchRecordService.class.getClassLoader().getResourceAsStream("config.properties");
			properties.load(in);
			map.put("userName", properties.getProperty("userName"));
			map.put("password", properties.getProperty("password"));
			map.put("host", properties.getProperty("host"));
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return map;
	}
}
