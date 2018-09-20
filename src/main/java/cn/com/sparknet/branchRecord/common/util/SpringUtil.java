package cn.com.sparknet.branchRecord.common.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring工具类
 * @author chenxy
 *
 */
public final class SpringUtil {

	public static ApplicationContext applicationContext;
	
	private SpringUtil(){
	}
	
	/**
	 * 加载Spring核心文件
	 * @param xml
	 */
	public static synchronized void loadSpringXML(String xml){
		applicationContext = new ClassPathXmlApplicationContext(xml);
	}

	/**
	 * 获取Spring Bean
	 * @param beanName
	 * @return
	 */
	public static Object getBean(String beanName){
		return applicationContext.getBean(beanName);
	}
}
