package cn.com.sparknet.branchRecord.common.exception;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

/**
 * 异常解析器
 * @author chenxy
 *
 */
@Component
public class ExceptionResolver implements HandlerExceptionResolver {
	
	private static Logger log = Logger.getLogger(ExceptionResolver.class);

	public ModelAndView resolveException(HttpServletRequest request,HttpServletResponse response,Object handler,Exception e) {
		//记录日志
		log.error(e.getMessage(),e);
		ModelAndView mav = new ModelAndView();
		try{
			//返回数据
	        MappingJacksonJsonView view = new MappingJacksonJsonView();
	        Map<String,String> map = new HashMap<String,String>();
	        if(e instanceof BusinessException){
	        	//通过反射获取相关错误信息
	        	Class<? extends Exception> clazz=e.getClass();
				map.put("errorCode", clazz.getMethod("getErrorCode").invoke(e).toString());
				map.put("errorDesc", e.getMessage());
				map.put("errorHandler", clazz.getMethod("getErrorHandler").invoke(e).toString());
	        }else{
	        	map.put("errorCode", "");
				map.put("errorDesc", e.getMessage());
				map.put("errorHandler", "");
	        }
			view.setAttributesMap(map);
	        mav.setView(view);
		}catch(Exception ex){
			log.error(ex.getMessage(),ex);
		}
        return mav;
	}

}
