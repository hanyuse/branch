package cn.com.sparknet.branchRecord.common.json;

import java.util.ArrayList;
import java.util.List;

public class ListJsonBean extends AbstractJsonBean {

	private List items;
	private String total;

	public ListJsonBean() {
		items = new ArrayList();
	}

	public void addItem(Object item) {
		items.add(item);
	}

	public List getItems() {
		return items;
	}

	public void setItems(Object items) {
		this.items = (List)items;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

}
