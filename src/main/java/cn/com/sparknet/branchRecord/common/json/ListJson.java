package cn.com.sparknet.branchRecord.common.json;

/**
 * 生成列表查询时的Json串
 * @author chenxy
 *
 */
public class ListJson extends ListJsonBean {

	private String total;

	public ListJson() {
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(Object total) {
		this.total = total.toString();
	}
}
