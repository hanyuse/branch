package cn.com.sparknet.branchRecord.servlet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.sparknet.branchRecord.service.BranchService;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/api")
public class BranchRecord {

	@Resource
	private BranchService branchService;

	@ResponseBody
	@RequestMapping(params = "/branch/queryptent", method = RequestMethod.POST)
	public JSONObject queryptent(@RequestBody String json, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject object = JSONObject.fromObject(json);
		return branchService.queryptent("searchPath",object);
	}
	
	@ResponseBody
	@RequestMapping(params = "/branch/pushbrinfo", method = RequestMethod.POST)
	public JSONObject pushbrinfo(@RequestBody String json, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject object = JSONObject.fromObject(json);
		return branchService.pushbrinfo("searchPath",object);
	}
	
	@ResponseBody
	@RequestMapping(params = "/branch/pushbrinfo", method = RequestMethod.POST)
	public JSONObject savePushbrinfo(@RequestBody String json, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject object = JSONObject.fromObject(json);
		return branchService.savePushbrinfo(request,object);
		 
	}
	
}
