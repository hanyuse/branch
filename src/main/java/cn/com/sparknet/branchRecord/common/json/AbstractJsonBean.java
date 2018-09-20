package cn.com.sparknet.branchRecord.common.json;

import java.util.*;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class AbstractJsonBean{

	private String dateFormat;

	public AbstractJsonBean() {
		dateFormat = "yyyy-MM-dd";
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public String toJsonString() {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
		return toJsonString(jsonConfig);
	}

	public String toJsonString(JsonConfig jsonConfig) {
		return JSONObject.fromObject(this, jsonConfig).toString();
	}


}
