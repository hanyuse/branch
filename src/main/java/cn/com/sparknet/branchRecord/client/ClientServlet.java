package cn.com.sparknet.branchRecord.client;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class ClientServlet {

	/**
     * @param args
     */
    public static void main( String[] args ) {
        getForMvcController();
    }
    
	/**
     * 模拟访问普通后台
     */
    public static void getForMvcController(){
        //访问地址
        final String server = "http://10.66.1.96:7000/onlineGovQuery/TestServlet.do?findUserPage=true";
        //调用另一个类实现
        final org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient(); 
        //post方式
        final PostMethod post = new PostMethod( server );
        //访问带参数
        post.setRequestBody( new NameValuePair[] {
                new NameValuePair( "page", "1" ),
                new NameValuePair( "limit", "15" ) } );
        //伪造IP
//        post.setRequestHeader("Referer","11.11.11");
        post.setRequestHeader("X-Forwarded-For","11.11.11");
        post.setRequestHeader("HTTP_X_FORWARDED_FOR","11.11.11");
        post.setRequestHeader("HTTP_CLIENT_IP","11.11.11");
        post.setRequestHeader("REMOTE_ADDR","11.11.11");
        //访问
        try {
            client.executeMethod( post );
            final String response = post.getResponseBodyAsString();
            //响应状态
            System.out.println( post.getStatusCode());
            //响应内容
            System.out.println( response);
            JSONObject dataMap = JSONObject.fromObject( response );
            System.out.println(dataMap.toString()); 
        }
        catch ( HttpException e ) {
            e.printStackTrace();
        }
        catch ( IOException e ) {
            e.printStackTrace();
        }
    }
}
