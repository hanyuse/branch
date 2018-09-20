package cn.com.sparknet.branchRecord.common.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 上下文监听器
 * @author chenxy
 *
 */
public class ContextListener implements ServletContextListener {
	
	private static WebApplicationContext context;

	/**
	 * 初始化
	 */
	public void contextInitialized(ServletContextEvent event) {
		this.context=WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
	}

	/**
	 * 销毁
	 */
	public void contextDestroyed(ServletContextEvent event) {
		this.context=null;
	}
	
	/**
	 * 获得对象
	 * */
	public static WebApplicationContext getInitContext(){
		return context;
	}
	
}
