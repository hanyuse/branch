package cn.com.sparknet.branchRecord.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
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
	
	 public static void main(String[] args) {
	        String perfix = "http://172.16.13.10:9999";
	        String uri = "/api/EnterQuery/EntIndexInfoByCon";
	        JSONObject json = new JSONObject();
	        json.put( "entname", "" );//涓讳綋鍚嶇О
	        json.put( "uniscid", "" );//缁熶竴绀句細淇＄敤浠ｇ爜
	        //String json_param = "浣犵殑鍙傛暟json";
	        String APP_KEY = "neimenggu_api";
	        String SECRET_KEY = "neimeng$3131411";
	        String AUTH_HEADER_KEY = "authorization";
	        String auth = APP_KEY + ":" + SECRET_KEY;
	        Base64 base64 = new Base64();
	        byte[] encodedAuth = base64.encode(auth.getBytes());
	        String AUTH_HEADER_VAL = "Basic " + new String(encodedAuth);
	        System.out.println(AUTH_HEADER_VAL);
	        HttpClient client = HttpClients.createDefault();
	        String url = perfix + uri;
	        HttpPost httpPost = new HttpPost(url);
	        httpPost.addHeader(AUTH_HEADER_KEY, AUTH_HEADER_VAL);
	        StringEntity entity = new StringEntity(json.toString(),"UTF-8");
	        httpPost.setEntity(entity);
	        HttpResponse response;
	        try {
	            response = client.execute(httpPost);
	            System.out.println(EntityUtils.toString(response.getEntity()));
	            
	        } catch (ClientProtocolException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}
