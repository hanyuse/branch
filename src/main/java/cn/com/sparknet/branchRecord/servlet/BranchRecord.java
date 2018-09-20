package cn.com.sparknet.branchRecord.servlet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.sparknet.branchRecord.service.BranchRecordService;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/api")
public class BranchRecord {

	@Resource
	private BranchRecordService branchReacrdService;

	@ResponseBody
	@RequestMapping(params = "/branch/queryptent", method = RequestMethod.POST)
	public JSONObject queryptent(@RequestBody String json, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject object = JSONObject.fromObject(json);
		JSONObject result = new JSONObject();
		String entName = object.getString("ENTNAME");
		String uniScid = object.getString("UNISCID");
		if(entName==null || uniScid== null || entName.trim().equals("") || uniScid.trim().equals("")){
			result.put("success",false);
			result.put("data","母公司企业名称和母公司统一社会信用代码参数不能为空");
			response.setStatus(406);
			return result;
		}
		result = branchReacrdService.queryptent("searchPath",object);
		return result;
	}
}
