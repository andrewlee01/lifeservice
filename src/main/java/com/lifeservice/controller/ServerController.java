package com.lifeservice.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lifeservice.model.Server;
import com.lifeservice.model.UserInfo;
import com.lifeservice.service.ServerService;
import com.lifeservice.service.UserService;

@Controller
@RequestMapping("/server")
public class ServerController {
	
	@Autowired
	private ServerService serverService;
	
	@Autowired
	private UserService userService;
	
	Map<String,Object> result = new HashMap<String,Object>(); //返回集合
	
	@RequestMapping(value = "/findServerByUserId", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Map<String,Object> findServerByUserId(int userId) throws Exception{
		List<Server> serverList = serverService.findServerListByUserId(userId);
		result.put("serverList", serverList);
		return result;
		
	}
	
	@RequestMapping(value = "/addServer", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Map<String, Object> addServer(Server server) throws Exception{
		try {
			serverService.addServer(server);
			result.put("result", "success");
		} catch (Exception e) {
			result.put("result", "fail");
			e.printStackTrace();
		}
		return result;
		
	}
	
	@RequestMapping(value = "/getDetail", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Map<String, Object> getDetail(int serverId) throws Exception{
		Server server= serverService.selectServerById(serverId);
		result.put("server", server);
		return result;
		
	}
	
	@RequestMapping(value = "/updateServer", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Map<String, Object> updateServer(Server server) throws Exception{
		try {
			serverService.updateServer(server);
			result.put("result", "success");
		} catch (Exception e) {
			result.put("result", "fail");
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping(value = "/userAndServerList", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Map<String,Object> userAndServerList(int userId){
		List<Server> serverList=serverService.findServerListByUserId(userId);
		UserInfo user=userService.findUserByUserId(userId);
		result.put("user", user);
		result.put("serverList", serverList);
		return result;
		
	}
	
	@RequestMapping(value = "/updateServerStatus", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Map<String,Object> updateServerStatus(int serverId,int memo){
		try{
			serverService.updateServerStatus(serverId, memo);
			result.put("result", "success");
		} catch (Exception e){
			result.put("result", "fail");
			e.printStackTrace();
		}
		return result;
		
	}
	
	@RequestMapping(value = "/deleteServer", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Map<String, Object> deleteServer(int serverId){
		try {
			serverService.deleteServer(serverId);
			result.put("result", "success");
		} catch (Exception e) {
			result.put("result", "fail");
			e.printStackTrace();
		}
		return result;
	}
	

	@RequestMapping(value = "/searchServer", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Map<String, Object> searchServer(String keyWord){
		
		try {
			List<Server> serverList = serverService.searchServer(keyWord);
			result.put("result", "success");
			result.put("serverList", serverList);
		} catch (Exception e) {
			result.put("result", "fail");
			e.printStackTrace();
		}
		return result;
	}
	
	
	
}
