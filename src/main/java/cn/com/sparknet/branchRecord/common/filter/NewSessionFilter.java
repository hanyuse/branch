package cn.com.sparknet.branchRecord.common.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//import org.apache.shiro.SecurityUtils;  
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewSessionFilter implements Filter {

    private String              url;

    private static final Logger logger                = LoggerFactory.getLogger( NewSessionFilter.class );

    public static final String  NEW_SESSION_INDICATOR = "cn.com.sparknet.common.filter.NewSessionFilter";

    public static void newSession() {
        //        HttpSession session = (HttpSession) SecurityUtils.getSubject().getSession(true);  
        //        session.setAttribute(NEW_SESSION_INDICATOR, true);  
    }

    public void destroy() {
    }

    public void doFilter( ServletRequest request, ServletResponse response,
            FilterChain chain ) throws IOException, ServletException {

        if ( request instanceof HttpServletRequest ) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;

            //取的url相对地址  
            String url = httpRequest.getRequestURI();
            if ( httpRequest.getSession() != null ) {
                System.out.println( "NewSessionFilter doFilter httpRequest.getSession().getId()" + httpRequest.getSession().getId() );
                //--------复制 session到临时变量  
                HttpSession session = httpRequest.getSession();
                session.setAttribute( NEW_SESSION_INDICATOR, true );
                HashMap old = new HashMap();
                Enumeration keys = (Enumeration) session.getAttributeNames();
                while ( keys.hasMoreElements() ) {
                    String key = (String) keys.nextElement();
                    System.out.println( "key=" + key );
                    if ( !NEW_SESSION_INDICATOR.equals( key ) ) {
                        old.put( key, session.getAttribute( key ) );
                        session.removeAttribute( key );
                    }
                }

                if ( httpRequest.getMethod().equals( "POST" ) && httpRequest.getSession() != null
                        && !httpRequest.getSession().isNew() && httpRequest.getRequestURI().endsWith( url ) ) {
                    session.invalidate();
                    session = httpRequest.getSession( true );
                    logger.debug( "new Session:" + session.getId() );
                }

                //-----------------复制session  
                for ( Iterator it = old.entrySet().iterator(); it.hasNext(); ) {
                    Map.Entry entry = (Entry) it.next();
                    session.setAttribute( (String) entry.getKey(), entry.getValue() );
                }
            }
        }

        chain.doFilter( request, response );
        System.out.println( "NewSessionFilter doFilter end" );
    }

    public void init( FilterConfig filterConfig ) throws ServletException {
        System.out.println( "NewSessionFilter init" );
        System.out.println( "NewSessionFilter init end" );
    }
}