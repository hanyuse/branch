package cn.com.sparknet.branchRecord.common.basedao;

import org.springframework.context.ApplicationContext;

import cn.com.sparknet.branchRecord.common.util.SpringUtil;



public class ApplicationContextManager
{
  private static ApplicationContextManager instance;
  private ApplicationContext applicationContext;

  public static ApplicationContextManager getInstance()
  {
    if (instance == null) {
      synchronized (ApplicationContextManager.class) {
        if (instance == null) {
          instance = new ApplicationContextManager();
        }
      }
    }
    return instance;
  }

  private ApplicationContextManager() {
    SpringUtil.loadSpringXML("applicationContext.xml");
    this.applicationContext = SpringUtil.applicationContext;
  }

  public ApplicationContext getApplicationContext() {
    return this.applicationContext;
  }
}
