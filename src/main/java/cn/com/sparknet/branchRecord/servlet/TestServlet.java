package cn.com.sparknet.branchRecord.servlet;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.sparknet.branchRecord.common.json.EditJson;
import cn.com.sparknet.branchRecord.common.json.ListJson;
import cn.com.sparknet.branchRecord.common.util.Base64Util;
import cn.com.sparknet.branchRecord.common.util.UUIDUtil;
import cn.com.sparknet.branchRecord.service.TestService;

@Controller
@RequestMapping(value = "/TestServlet.do")
public class TestServlet {

    @Resource
    private TestService testService;
    
    @ResponseBody
    @RequestMapping(params = "findUser")
    public ListJson findUser()throws Exception {
//        System.out.println(request.getParameter( "deviceToken" ));
//      
//        JSONObject object = JSONObject.fromObject( json );
//        System.out.println(object.get( "content" ).toString());

        HttpClient client = HttpClients.createDefault();
        String url = "http://10.66.1.96:7000/onlineGovQuery/TestServlet.do?getFrMsg=true";
        HttpPost post = new HttpPost( url );
        post.setHeader( "Content-Type", "application/json" );
//        post.setHeader( "Referer", "121212" );
        JSONObject json = new JSONObject();
        json.put( "regNo", Base64Util.encode( "123456" ) );
        StringEntity se = new StringEntity(json.toString(), Charset.forName("UTF-8") );
        post.setEntity( se );
        HttpResponse response;
        try {
            response = client.execute( post );
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8" );
            JSONObject object = JSONObject.fromObject( result );
            String datas = object.get( "datas" ).toString();
            JSONObject dataMap = JSONObject.fromObject( datas );
        }
        catch ( ClientProtocolException e ) {
            e.printStackTrace();
        }
        catch ( IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    
        return testService.findUser();
    }
    
    @ResponseBody
    @RequestMapping(params = "insertUser")
    public EditJson insertUser(HttpServletRequest request, HttpServletResponse response)throws Exception {
        return testService.insertUser();
    }
    
    @ResponseBody
    @RequestMapping(params = "deleteUser")
    public EditJson deleteUser(HttpServletRequest request, HttpServletResponse response)throws Exception {
        return testService.deleteUser();
    }
    
    
    
    @ResponseBody
    @RequestMapping(params = "getDeviceToken",method = RequestMethod.POST)
    public String getDeviceToken(@RequestBody  String json, HttpServletResponse response)throws Exception {
        JSONObject object = JSONObject.fromObject( json );
        System.out.println(object.get( "content" ).toString());
        return UUIDUtil.getNextValue();
    }
    
    
    @ResponseBody
    @RequestMapping(params = "getFrMsg",method = RequestMethod.POST)
    public JSONObject getFrMsg(@RequestBody  String json,HttpServletRequest request, HttpServletResponse response)throws Exception {
//        String referer = request.getHeader("Referer");
        System.out.println("refere1111111111r="+getIpAddress(request));
        JSONObject object = JSONObject.fromObject( json );
        JSONObject result = new JSONObject();
        if(object.get( "regNo" )!=null){
            System.out.println(Base64Util.decode( object.get( "regNo" ).toString() ));
            result.put( "status", 0 );
            result.put( "errMsg", "失败原因" );
            Map<String,Object> map = new HashMap<String,Object> ();
            map.put( "ORGNAME", "江苏星网" );
            map.put( "ADDR", "南京市建邺区新城科技园3栋5楼南" );
            result.put( "datas",  map);
            System.out.println(result.toString()); 
        }
        response.setStatus( 404 );
        return result;
    }
    
    public static String getIpAddress(HttpServletRequest request) {  
        String ip = request.getHeader("x-forwarded-for");  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_CLIENT_IP");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
        }  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getRemoteAddr();  
        }  
        return ip;  
    }  
}
