package cn.com.sparknet.branchRecord.common.json;
/**
 * 给ExtJs的表单加载数据直接使用的对象，在form表单直接使用load方法加载数据时使用
 * @author zhangshuai
 *
 */
public class ExtEditJson extends AbstractJsonBean {
	private Object data;
	private boolean success = false;

	public ExtEditJson() {
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
