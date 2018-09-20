package cn.com.sparknet.branchRecord.test.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import cn.com.sparknet.branchRecord.common.util.Base64Util;

/**
 * 法人库接口开发
 * @author jiangyq
 *
 */
public class ClientServlet {

    public static void main(String[] args) {
    	//getDeviceToken();
    	//getCorpMsg();
    	getFrMsg();
    	
	}
  
    /**
     * 通过用户名密码获取本次传输令牌，令牌半小时后失效
     */
    public static void getDeviceToken(){
    	 //开启访问
        HttpClient client = HttpClients.createDefault();
        //访问地址
        String url = "http://10.66.1.103:65/corporateLibInterface/CorporateLibServlet.do?getDeviceToken=true";
        HttpPost post = new HttpPost( url );
        //json格式 utf-8
        post.setHeader( "Content-Type", "application/json" );
        //伪造IP
        JSONObject json = new JSONObject();
        json.put( "userName","jyq");
        json.put( "password","123456");
        json.put( "orgName","sparksoft");
        StringEntity se = new StringEntity(json.toString(), Charset.forName("UTF-8") );
        se.setContentType("application/json");
        post.setEntity( se );
        HttpResponse response;
        try {
            response = client.execute( post );
            HttpEntity entity = response.getEntity();
            //获取内容
            String result = EntityUtils.toString(entity, "UTF-8" );
            JSONObject object = JSONObject.fromObject( result );
            
            if(object.size() > 0){
            	//返回码
                System.out.println("status："+ object.get( "status" ).toString());
                //错误信息
                System.out.println("errMsg:" + object.get( "errMsg" ).toString());
                //令牌
                System.out.println("deviceToken：" + object.get( "deviceToken" ).toString());
                
                System.out.println("返回数据： " + object.toString());
            }
            
        }
        catch ( ClientProtocolException e ) {  
            e.printStackTrace();
        }
        catch ( IOException e ) {
            e.printStackTrace();
        }
    }
    
    /**
     * 通过用户提供的统一信用代码或注册号在法人库中进行查询
     */
    public static void getCorpMsg(){

   	 //开启访问
       HttpClient client = HttpClients.createDefault();
       //访问地址
       String url = "http://10.66.1.103:65/corporateLibInterface/CorporateLibServlet.do?getCorpMsg=true";
       HttpPost post = new HttpPost( url );
       //json格式 utf-8
       post.setHeader( "Content-Type", "application/json" );
       //伪造IP
       JSONObject json = new JSONObject();
       json.put( "regNo",Base64Util.encode("32000018050891"));
       json.put( "uuit_no",Base64Util.encode("91320000MA1MEL4594,320500000200492,91320100MA1MEHYT5G"));
       json.put( "deviceToken","3501873560e3478da82c9b4b69e2f730");
       
       StringEntity se = new StringEntity(json.toString(), Charset.forName("UTF-8") );
       se.setContentType("application/json");
       post.setEntity( se );
       HttpResponse response;
       try {
           response = client.execute( post );
           HttpEntity entity = response.getEntity();
           //获取内容
           String result = EntityUtils.toString(entity, "UTF-8" );
           JSONObject object = JSONObject.fromObject( result );
           if(object.size() > 0){
           	   //返回码
               System.out.println("status：" + object.get( "status" ).toString());
               //错误信息
               System.out.println("errMsg：" + object.get( "errMsg" ).toString());
               if(object.get( "status" ).equals("1")){
            	 //返回数据
                   System.out.println("datas：" + object.get( "datas" ).toString());
               }
               System.out.println("返回数据： " + object.toString());
           }
           
       }
       catch ( ClientProtocolException e ) {  
           e.printStackTrace();
       }
       catch ( IOException e ) {
           e.printStackTrace();
       }
       
    }
    
    
    /**
     * 通过法人姓名和身份证号码进行查询
     */
    public static void getFrMsg(){

   	 //开启访问
       HttpClient client = HttpClients.createDefault();
       //访问地址
       String url = "http://10.66.1.103:65/corporateLibInterface/CorporateLibServlet.do?getFrMsg=true";
       HttpPost post = new HttpPost( url );
       //json格式 utf-8
       post.setHeader( "Content-Type", "application/json" );

       JSONObject json = new JSONObject();
       json.put( "OPER_MAN_NAME","A");
       json.put( "OPER_MAN_IDENT_NO",Base64Util.encode("320321198911231452"));
       
       StringEntity se = new StringEntity(json.toString(), Charset.forName("UTF-8") );
       se.setContentType("application/json");
       post.setEntity( se );
       HttpResponse response;
       try {
           response = client.execute( post );
           HttpEntity entity = response.getEntity();
           //获取内容
           String result = EntityUtils.toString(entity, "UTF-8" );
           JSONObject object = JSONObject.fromObject( result );
           if(object.size() > 0){
           	   //返回码
               System.out.println("status：" + object.get( "status" ).toString());
               //错误信息
               System.out.println("errMsg：" + object.get( "errMsg" ).toString());
               if (object.get( "errMsg" ).toString().equals("")) {
            	 //返回数据
                   System.out.println(object.get( "datas" ).toString());
                   JSONArray array = JSONArray.fromObject(object.get( "datas" ));
                   for(int i =0;i<array.size();i++){
                	   System.out.println(((JSONObject)array.get(i)).toString()); 
                   }
                   
			}
               
               System.out.println("返回数据： " + object.toString());
           }
           
       }
       catch ( ClientProtocolException e ) {  
           e.printStackTrace();
       }
       catch ( IOException e ) {
           e.printStackTrace();
       }
       
    }
    
    
    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }    
    
    
}
