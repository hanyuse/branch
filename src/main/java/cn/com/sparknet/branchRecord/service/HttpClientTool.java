package cn.com.sparknet.branchRecord.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import cn.com.spraknet.branchRecord.tools.StringUtil;
import net.sf.json.JSONObject;

public class HttpClientTool {
	private static final Logger logger = LogManager.getLogger(HttpClientTool.class);
	
	public static void main(String[] args) throws URISyntaxException {
//		CloseableHttpClient httpclient = HttpClients.createDefault();
//		HttpGet httpget = new HttpGet("http://www.baidu.com/");
			//CloseableHttpResponse response = httpclient.execute(httpget);
			/*URI uri = new URIBuilder()
			        .setScheme("http")
			        .setHost("www.google.com:8080")
			        .setPath("/search")
			        .setParameter("q", "httpclient")
			        .setParameter("btnG", "Google Search")
			        .setParameter("aq", "f")
			        .setParameter("oq", "")
			        .build();
			HttpGet httpget = new HttpGet(uri);
			System.out.println(httpget.getURI());
			HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, 
					HttpStatus.SC_OK, "OK");
			System.out.println(response.getProtocolVersion());
			System.out.println(response.getStatusLine().getStatusCode());
			System.out.println(response.getStatusLine().getReasonPhrase());
			System.out.println(response.getStatusLine().toString());*/
		Map<String,Object> pramasMap =new HashMap<String,Object>();
		pramasMap.put("ENTNAME", "泰康人寿集团股份有限公司");
		pramasMap.put("UNISCID", "900000000000000000");
		HttpClientTool tool = new HttpClientTool();
		tool.sendHttpRequest("/api/branch/queryptent", pramasMap);
	}
	
	/**
	 * 返回添加basic认证的httpclient
	 * @param map userName，password，host
	 * @return
	 */
	public  CloseableHttpClient getHttpClient(Map<String,Object> map) throws Exception{
		String userName;
		String password;
		String host;
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		userName = StringUtil.nullToEmpty(map.get("userName"));
		password = StringUtil.nullToEmpty(map.get("password"));
		host = StringUtil.nullToEmpty(map.get("host"));
		credsProvider.setCredentials(
			    new AuthScope(host, AuthScope.ANY_PORT), 
			    new UsernamePasswordCredentials(userName,password));
		CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider)
                .build();
		return httpclient;
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
			in = HttpClientTool.class.getClassLoader().getResourceAsStream("init.properties");
			properties.load(in);
			map.put("userName", properties.getProperty("userName"));
			map.put("password", properties.getProperty("password"));
			map.put("host", properties.getProperty("host"));
			map.put("port", properties.getProperty("port"));
		}catch(Exception e){
			logger.error("获取资源文件参数出错");
			e.printStackTrace();
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					logger.error("获取资源文件结束后，关闭文件流出错");
					e.printStackTrace();
				}
			}
		}
		return map;
	}
	
	/**
	 * 发送http请求获取资源
	 * @param pramasMap 包含请求参数  pathname请求路径
	 * @return
	 */
	public String sendHttpRequest(String search,Map<String,Object> pramasMap){
		Map<String,Object> map = new HashMap<String,Object>();
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		HttpEntity entity = null;
		String body = null;
		JSONObject jsonObject = null;
		Map<String,String> result =  new HashMap<String,String>();
		try{
			//1.获取资源配置信息
			map = getProperties();
			//2.获取httpclient客户端
			httpClient = getHttpClient(map);
			//3.实例化http方法
			URI uri = new URIBuilder()
			        .setScheme("http")
			        .setHost(StringUtil.nullToEmpty(map.get("host")))
			        .setPath(search)
			        .build();
			HttpPost request = new HttpPost(uri);
			//3.设置参数
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			for (Iterator iter = pramasMap.keySet().iterator(); iter.hasNext();) {
	    		String name = (String) iter.next();
	    		String value = String.valueOf(pramasMap.get(name));
	    		nvps.add(new BasicNameValuePair(name, value));
			}			
			request.setEntity(new UrlEncodedFormEntity(nvps,"UTF-8")); 
			request.setHeader("Content-type", "application/x-www-form-urlencoded"); 
			//获取资源
			response = httpClient.execute(request); 
			entity = response.getEntity();
			int code = response.getStatusLine().getStatusCode();
			if(code==200){
				body = EntityUtils.toString(entity, "UTF-8");
			}
			EntityUtils.consume(entity);
			response.close();
			result.put("CODE","200");
			result.put("DATA", body);
		}catch(URISyntaxException e){
			result.put("CODE","500");
			result.put("DATA", "uri创建出错");
			logger.error("uri创建出错");
			e.printStackTrace();
		}catch(Exception e){
			result.put("CODE","500");
			result.put("DATA", "请求失败");
			logger.error("请求出错");
			e.printStackTrace();
		}finally{
			jsonObject = JSONObject.fromObject(result);
		}
		return jsonObject.toString();
	}
}
