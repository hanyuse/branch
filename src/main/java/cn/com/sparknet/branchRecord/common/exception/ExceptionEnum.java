package cn.com.sparknet.branchRecord.common.exception;

/**
 * 异常枚举
 * @author chenxy
 *
 */
public enum ExceptionEnum {
	
	NULL(){
		public String getErrorCode(){
			return "32000";
		};
		public String getErrorDesc(){
			return "主键ID不能为空";
		};
		public String getErrorHandler(){
			return "";
		};
	},
	FORMAT{
		public String getErrorCode(){
			return "32001";
		};
		public String getErrorDesc(){
			return "格式转换错误";
		};
		public String getErrorHandler(){
			return "";
		};
	},
	CLASSCAST{
		public String getErrorCode(){
			return "32002";
		};
		public String getErrorDesc(){
			return "类转换错误";
		};
		public String getErrorHandler(){
			return "";
		};
	};
	
	public abstract String getErrorCode();
	public abstract String getErrorDesc();
	public abstract String getErrorHandler();
	
}
