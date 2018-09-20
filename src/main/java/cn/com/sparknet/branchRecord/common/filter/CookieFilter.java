package cn.com.sparknet.branchRecord.common.filter;



import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description: 用于安全修改,为会话Cookie添加HttpOnly属性
 * @author chenyin
 *
 */

public class CookieFilter implements Filter {

    public void destroy() {

    }


    //Filter接口的实现    ps:用于WebLogic服务器
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //Request、Response对象类型转换
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;
        //获取session的ID
        String ssid = req.getSession().getId();
        //为值添加httpOnly属性;
        Cookie cookie = new Cookie("JSESSIONID_CORPORATELIBINTERFACE", ssid+"; HttpOnly; ");
        resp.setHeader( "x-frame-options", "SAMEORIGIN" );
        //设置路径（由于webLogic会自动生成一个cookie，如果一个新的cookie与一个已存在的cookie的NAME、Domain和Path属性值均相同，则旧的cookie会被丢弃）
        cookie.setPath("/");
        //把WebLogic服务器自动创建的Cookie给替换掉。
        resp.addCookie(cookie);
        chain.doFilter(req, resp);
    }


    public void init(FilterConfig arg0) throws ServletException {

    }

}