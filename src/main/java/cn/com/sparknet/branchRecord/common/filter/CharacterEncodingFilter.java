package cn.com.sparknet.branchRecord.common.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 编码过滤器
 * @author chenxy
 */
public class CharacterEncodingFilter implements Filter {
	
	public static ArrayList filList = new ArrayList();
	static {
		String strBegin = "(&|%26)(#|%23)60(;|%3b)"; //<
		String strEnd   = "(&|%26)(#|%23)62(;|%3b)"; //>
		ArrayList dangerLst = new ArrayList();
		dangerLst.add("javascript");
		dangerLst.add("(<script)|(script>)");
		dangerLst.add("(" + strBegin + "script)|(script" + strEnd + ")");
		
		/**
		 以下是javascript的多种变体

   		("&#x6a;&#x61;&#x76;&#x61;&#x73;&#x63;&#x72;&#x69;&#x70;&#x74;"); //javascript
		("%26%23x6a;%26%23x61;%26%23x76;%26%23x61;%26%23x73;%26%23x63;%26%23x72;%26%23x69;%26%23x70;%26%23x74;"); //javascript
		("%26%23x6a%3b%26%23x61%3b%26%23x76%3b%26%23x61%3b%26%23x73%3b%26%23x63%3b%26%23x72%3b%26%23x69%3b%26%23x70%3b%26%23x74%3b");//javascript
		 */
		//以下两行是javascript中十个字母("j","a","v","a"....."t")的拦截，认为用以下格式书写其中任何一个字母，都是有不正当企图
		dangerLst.add("(&|%26)(#|%23)x\\d[\\d|a](;|%3b)");		
		for (int i=0; i<dangerLst.size(); i++) {
			Pattern p = Pattern.compile((String)dangerLst.get(i));
			filList.add(p);
		}
   }
	
	protected String encoding = null;

	protected FilterConfig filterConfig = null;

	protected boolean ignore = true;

	public void destroy() {
		this.encoding = null;
		this.filterConfig = null;

	}

	public void SetCharacterEncodingFilter() {
		encoding = null;
		filterConfig = null;
		ignore = true;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		if (ignore || (request.getCharacterEncoding() == null)) {
			String encoding = selectEncoding(request);
			if (encoding != null)
				request.setCharacterEncoding(encoding);
			response.setContentType("text/html;charset=" + encoding);
		}
		
        //只接受来自本域的请求而忽略外部域的请求
        String referer = req.getHeader("Referer");
        String serverName = request.getServerName();
        if(null != referer&&referer.indexOf(serverName) < 0){ 
            req.getRequestDispatcher(req.getRequestURI()).forward(req, response);
            return;
        }

		 //防止20%和.的攻击,该类攻击会导致源代码泄露
		String strUrl =  req.getRequestURL().toString();
		if (strUrl !=null && strUrl.indexOf(".jsp")>-1 && !".jsp".equals(strUrl.substring(strUrl.indexOf(".jsp")))) {
			System.out.println("拦截成功！=" + strUrl.substring(strUrl.indexOf(".jsp")));
			throw new RuntimeException("错误地址！");
		}

		if (validate(request, response)) {
			String retUrl = getWebUrl(req.getRequestURL().toString());
			String msg = "页面含有非法字符 <a href='" + retUrl + "'>返回</a>";
			resp.getWriter().println(msg);
			return;
		}
				
		chain.doFilter(request, response);

	}
	
	private boolean validateIP(String url) {
		//System.out.println("validateIP:"+url);
		if (url == null)
			return true;
		List list = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			if (url.indexOf((String) list.get(i)) != -1) {
				return false;
			}
		}
		return true;
	}

	protected String selectEncoding(ServletRequest request) {
		return (this.encoding);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		this.encoding = filterConfig.getInitParameter("encoding");
		String value = filterConfig.getInitParameter("ignore");
		if (value == null)
			this.ignore = true;
		else if (value.equalsIgnoreCase("true"))
			this.ignore = true;
		else if (value.equalsIgnoreCase("yes"))
			this.ignore = true;
		else
			this.ignore = false;
	}
	
	
	/**
	 * 过滤非法字符
	 * 
	 * @param sreq
	 * @param sresp
	 * @param chain
	 */
	public boolean validate(ServletRequest sreq, ServletResponse sresp) {
		HttpServletRequest req = (HttpServletRequest) sreq;
		
		Map map = req.getParameterMap();
		
		//表单值检查		
		Collection cs = map.values();
		Iterator it = cs.iterator();
		while (it.hasNext()) {
			Object obj = it.next();
			String[] ss = (String[]) obj;
			for (int i = 0; i < ss.length; i++) {
				// 得到输入页面的文本

				String temp = ss[i];
				if (hasDangerous(temp)) {
					return true;
				}
			}

		}
		
        //表单名称检查

		cs = map.keySet();
		it = cs.iterator();
		while (it.hasNext()) {
			Object obj = it.next();
			String tname = (String) obj;
			if (hasDangerous(tname)) {
				return true;
			}

		}
		return false;
	}
	
	/**
	 * 检查字符串中是否有危害到系统的内容
	 * 
	 * @param vStr
	 * @return
	 */
	private static boolean hasDangerous(String vStr) {
		//检查是否有危险字符
		for (int i=0; i<filList.size(); i++) {
			Pattern px = (Pattern)filList.get(i);
			Matcher mx = px.matcher(vStr.toLowerCase()); //转为小写
			if (mx.find()) {				
				return true;
			}
		}
		return  false;
		
	}
	
	/**
	 * 根据URL取得网站地址
	 * @param href
	 * @return
	 */
	private static String getWebUrl(String href) {		
		
		href = href==null?"":href;		
		Pattern p = Pattern.compile("(http://.*?)/.*");
		Matcher m = p.matcher(href);		
		if (m.matches()) {
		   return (String)m.group(1)+"/zjql";	
		} else {
		   return "/login.jsp";
		}

	}
}
