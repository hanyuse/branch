package cn.com.sparknet.branchRecord.common.filter;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Session过滤器
 * @author chenxy
 * 
 */
public class SessionFilter implements Filter {

	public boolean isContains(String container, String[] regx) {
		boolean result = false;
		for(int i = 0; i < regx.length; i++) {
			if(container.indexOf(regx[i].trim()) != -1) {
				return true;
			}
		}
		return result;
	}

	public FilterConfig config;

	public void setFilterConfig(FilterConfig config) {
		this.config = config;
	}

	public FilterConfig getFilterConfig() {
		return config;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
        HttpServletRequest hreq = (HttpServletRequest) request;
        String noNeedFilterUrl = config.getInitParameter("noNeedFilterUrl");
        String needFilterSuffix = config.getInitParameter("needFilterSuffix");
        String isDisabledFilter = config.getInitParameter("isDisabledFilter");
        if (isDisabledFilter.toUpperCase().equals("TRUE")) {
            chain.doFilter(request, response);
            return;
        }
        String[] noNeedFilterUrlList = noNeedFilterUrl.split(";");
        String[] needFilterSuffixList = needFilterSuffix.split(";");
        Object user = hreq.getSession().getAttribute("userInfo");
        if (user == null) {
            if("/branchRecord/".equals( hreq.getRequestURI() )){
                chain.doFilter(request, response);
                return;
            }
            if (!this.isContains(hreq.getRequestURI(), needFilterSuffixList)) {
                chain.doFilter(request, response);
                return;
            }
            if (this.isContains(hreq.getRequestURI(), noNeedFilterUrlList)) {
                chain.doFilter(request, response);
                return;
            }
            ( (HttpServletResponse) response ).setStatus(404);//手动抛出404
            PrintWriter out = response.getWriter();
            StringBuffer str = new StringBuffer();
            str.append("<script language='javascript'>");
            str.append("top.window.location='"+hreq.getContextPath()+"';");
            str.append("</script>");
            out.println(str.toString());
            out.flush();
            out.close();
            out = null;
        } else {
            chain.doFilter(request, response);
        }
    }

	public void destroy(){
		this.config = null;
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
	}
}
