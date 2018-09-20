package cn.com.sparknet.branchRecord.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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

import cn.com.sparknet.branchRecord.common.util.StringUtil;
import cn.com.sparknet.branchRecord.dao.BranchDao;
import cn.com.spraknet.branchRecord.bean.ErrorCode;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class BranchService {
	private static final Logger logger = LogManager.getLogger(BranchService.class);

	@Resource BranchDao branchDao;
	
	public static void main(String[] args) {

		JSONObject result = JSONObject.fromObject("{\"a\":1}");
		System.out.println(result);
	}
	
	/**
	 * 查询母公司信息
	 * @param path
	 * @param object
	 * @return
	 */
	public JSONObject queryptent(String path, JSONObject object) {
		JSONObject result = new JSONObject();
		result = sendHttp(path, object);
		return result;
	}
	
	/**
	 * 分公司数据接收接口
	 * @param path
	 * @param object
	 * @return
	 */
	public JSONObject savePushbrinfo(HttpServletRequest request,JSONObject object) {
		JSONObject result = new JSONObject();
		Map<String,Object> map = null;
		try {
			//验证请求头信息
			if(!checkHeaderAuth(request)) {
				result.put("CODE", "S40000");
				result.put("MSG", "省局分支机构备案信息接收失败，接口状态异常！");
				return result;
			}
			//母公司信息
			map = new HashMap<String,Object>();
			JSONObject subentInfo = object.getJSONObject("SUBENTINFO");
			map.put("ENTNAME", subentInfo.getString("ENTNAME"));
			map.put("UNISCID", subentInfo.getString("UNISCID"));
			map.put("REGORG", subentInfo.getString("REGORG"));
			map.put("NODENUM", subentInfo.getString("NODENUM"));
			branchDao.saveSubentInfo(map);
			
			//分支机构信息
			JSONObject brentInfo = object.getJSONObject("BRENTINFO");
			map.put("BRNAME", brentInfo.getString("BRNAME"));
			map.put("BRUNISCID", brentInfo.getString("BRUNISCID"));
			map.put("BRREGORG", brentInfo.getString("BRREGORG"));
			map.put("BRAPPRDATE", brentInfo.getString("BRAPPRDATE"));
			map.put("BRESTDATE", brentInfo.getString("BRESTDATE"));
			map.put("BRREGSTATE", brentInfo.getString("BRREGSTATE"));
			branchDao.SavebrentInfo(map);
			//分公司注销信息（是否必输？是否和母公司信息存在一张表？）
			JSONObject brcanInfo = object.getJSONObject("BRCANINFO");
			map.put("BRCANREA", brcanInfo.getString("BRCANREA"));
			map.put("BRCANDATE", brcanInfo.getString("BRCANDATE"));
			
			//变更信息
			JSONArray  braltInfo = object.getJSONArray("BRALTINFO");
			for(int i=0;i<braltInfo.size();i++) {
				map = new HashMap<String,Object>();
				JSONObject json = braltInfo.getJSONObject(0);
				map.put("BRALTITEM", json.getString("BRALTITEM"));
				map.put("BRALTBE", json.getString("BRALTBE"));
				map.put("BRALTAF", json.getString("BRALTAF"));
				map.put("BRALTDATE", json.getString("BRALTDATE"));
				//调用存库方法
			}
			
			result.put("CODE", "S50000");
			result.put("MSG", "省局备案信息接收成功。");
		}catch(Exception e) {
			result.put("CODE", "S40000");
			result.put("MSG", "省局分支机构备案信息接收失败，接口状态异常！");
			throw new RuntimeException();
		}
		return result;
	}
	
	
	/**
	 * 上报分公司信息
	 * @param path
	 * @param object
	 * @return
	 */
	public JSONObject pushbrinfo(String path, JSONObject object) {
		JSONObject result = new JSONObject();
		result = sendHttp(path, object);
		return result;
	}

	public JSONObject sendHttp(String path, JSONObject object) {
		JSONObject result = new JSONObject();
		Map<String, Object> map = new HashMap<String, Object>();
		// 获取配置文件
		try {
			map = getProperties();
		} catch (IOException e1) {
			result.put("CODE", "J60001");
			result.put("MSG", ErrorCode.J60001);
			logger.error(object.toString());
			e1.printStackTrace();
		}
		String APP_KEY = StringUtil.nullToEmpty(map.get("userName"));
		String SECRET_KEY = StringUtil.nullToEmpty(map.get("password"));
		String host = StringUtil.nullToEmpty(map.get("host"));
		// 设置Basic请求头认证
		String AUTH_HEADER_KEY = "Authorization";
		String auth = APP_KEY + ":" + SECRET_KEY;
		Base64 base64 = new Base64();
		byte[] encodedAuth = base64.encode(auth.getBytes());
		String AUTH_HEADER_VAL = "Basic " + new String(encodedAuth);
		HttpClient client = HttpClients.createDefault();
		String url = host + path;
		// 创建http方法
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader(AUTH_HEADER_KEY, AUTH_HEADER_VAL);
		// 设置参数
		StringEntity entity = new StringEntity(object.toString(), "UTF-8");
		httpPost.setEntity(entity);
		HttpResponse response;
		try {
			response = client.execute(httpPost);
			String body = EntityUtils.toString(response.getEntity());
			JSONObject json = JSONObject.fromObject(body);
			JSONObject resInfo = (JSONObject) json.get("RESINFO");
			String code = resInfo.getString("CODE");
			if(!code.equals("Z50000")) {
				logger.error("查询母公司接口失败"+object.toString());
			}
			result = json;
		} catch (ClientProtocolException e) {
			result.put("CODE", "J60002");
			result.put("MSG", ErrorCode.J60002);
			logger.error("远程客户端调用协议出错");
			logger.error(object.toString());
			e.printStackTrace();
		} catch (IOException e) {
			result.put("CODE", "J500");
			result.put("MSG", "数据返回出错");
			logger.error("数据返回出错");
			logger.error(object.toString());
			e.printStackTrace();
		} catch(Exception e){
			result.put("CODE", "J60000");
			result.put("MSG", ErrorCode.J60000);
			logger.error("数据返回出错");
		}
		return result;
	}

	/**
	 * 读取资源配置文件
	 * 
	 * @return
	 * @throws IOException 
	 */
	public Map<String, Object> getProperties() throws IOException {
		InputStream in = null;
		Properties properties = new Properties();
		Map<String, Object> map = new HashMap<String, Object>();
		in = BranchRecordService.class.getClassLoader().getResourceAsStream("config.properties");
		properties.load(in);
		map.put("userName", properties.getProperty("userName"));
		map.put("password", properties.getProperty("password"));
		map.put("host", properties.getProperty("host"));
		if (in != null) {
			in.close();
		}
		return map;
	}
	
	private boolean checkHeaderAuth(HttpServletRequest request) throws IOException { 
		Map<String, Object> map = new HashMap<String, Object>();
		boolean result = true;
		// 获取配置文件
		map = getProperties();
		String auth = request.getHeader("Authorization");
		if ((auth != null) && (auth.length() > 6)) {
			auth = auth.substring(6, auth.length());
			Base64 base64 = new Base64();
			byte[] decodeAuth = base64.decode(auth);
			String basic = new String(decodeAuth);
			String APP_KEY = StringUtil.nullToEmpty(map.get("userName"));
			String SECRET_KEY = StringUtil.nullToEmpty(map.get("password"));
			String basicAuth = APP_KEY + ":" + SECRET_KEY;
			if(basic.equals(basicAuth)) {
				return true;
			}else {
				return false;
			}
		}
		return result;
	}
}
