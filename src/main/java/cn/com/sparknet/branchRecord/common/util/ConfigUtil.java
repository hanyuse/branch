package cn.com.sparknet.branchRecord.common.util;

import java.io.InputStream;

import cn.com.sparknet.branchRecord.common.propety.PropertiesUtil;

/**
 * 获取参数公用 类  
 * @author chenyin
 *
 */
public class ConfigUtil {
    
    private static ConfigUtil instance;
    
    private PropertiesUtil properties;
    
    private static ConfigUtil getInstance(){
        if (instance == null) {
            synchronized(ConfigUtil.class){
                if (instance == null) { 
                    instance = new ConfigUtil();
                }
            }
        }
        return instance;
    }

    private ConfigUtil(){
        InputStream is = null;
        try {
            is = ConfigUtil.class.getClassLoader().getResourceAsStream("config.properties");
            PropertiesUtil properties=new PropertiesUtil(is);
            this.properties=properties;
        }catch ( Exception e ) {
            throw new RuntimeException(e.getMessage(),e);
        }finally{
            try{
                if(null!=is){
                    is.close();
                    is=null;
                }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage(),e);
            }
        }
    }
    
    /**
     * 获取配置文件属性值
     */
    public static String getPropertyValue(String key) throws Exception{
        ConfigUtil config=ConfigUtil.getInstance();
        return config.properties.getProperty( key );
    }
    
    
}
