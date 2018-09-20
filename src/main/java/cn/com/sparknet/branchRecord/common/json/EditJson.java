package cn.com.sparknet.branchRecord.common.json;

/**
 * 生成编辑操作时的Json串
 * @author chenxy
 *
 */
public class EditJson extends JsonBean {

	private boolean success;

	public EditJson() {
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
