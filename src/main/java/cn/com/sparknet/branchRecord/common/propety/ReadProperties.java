package cn.com.sparknet.branchRecord.common.propety;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

    /**
     * 读取properties文件
     * 
     * @author zhangxd
     * */
    public class ReadProperties extends PropertyPlaceholderConfigurer {

        private static Map<String, Object> ctxPropertiesMap;

        
        public ReadProperties(){
            super();
        }
        
        @Override
        protected void processProperties(
                ConfigurableListableBeanFactory beanFactoryToProcess,
                Properties props) throws BeansException {
            super.processProperties(beanFactoryToProcess, props);
            ctxPropertiesMap = new HashMap<String, Object>();
            for (Object key : props.keySet()) {
                String keyStr = key.toString();
                String value = props.getProperty(keyStr);
                ctxPropertiesMap.put(keyStr, value);
            }
        }

        public static Object getContextProperty(String name) {
            return ctxPropertiesMap.get(name);
        }
}
