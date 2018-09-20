package cn.com.sparknet.branchRecord.common.exception;

/**
 * 业务异常（必须继承RuntimeException，否则事务无法监控）
 * @author chenxy
 *
 */
public class BusinessException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private String errorCode="";
	private String errorDesc="";
	private String errorHandler="";

	public BusinessException() {
		super();
	}
	
	public BusinessException(String... errorDesc) {
		super(errorDesc[0]);
		setErrorDesc(errorDesc[0]);
	}
	
	public BusinessException(Throwable cause,String errorDesc) {
		super(errorDesc,cause);
		setErrorDesc(errorDesc);
	}
	
	public BusinessException(ExceptionEnum exceptionEnum,String... errorDesc) {
		super(errorDesc.length==0?exceptionEnum.getErrorDesc():errorDesc[0]);
		setErrorInfo(exceptionEnum,errorDesc);
	}

	public BusinessException(Throwable cause,ExceptionEnum exceptionEnum,String... errorDesc) {
		super(errorDesc.length==0?exceptionEnum.getErrorDesc():errorDesc[0],cause);
		setErrorInfo(exceptionEnum,errorDesc);
	}
	
	private void setErrorInfo(ExceptionEnum exceptionEnum,String... errorDesc){
		setErrorCode(exceptionEnum.getErrorCode());
		setErrorDesc(errorDesc.length==0?exceptionEnum.getErrorDesc():errorDesc[0]);
		setErrorHandler(exceptionEnum.getErrorHandler());
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	public String getErrorHandler() {
		return errorHandler;
	}

	public void setErrorHandler(String errorHandler) {
		this.errorHandler = errorHandler;
	}
	
	
}
