package com.lifeservice.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lifeservice.model.UserInfo;
import com.lifeservice.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}


//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public @ResponseBody
//	User showOneUser(@PathVariable("id") int id) {
//
//		return userService.getUserById(id);
//	}
	
//	@RequestMapping(value = "/test", method = RequestMethod.GET)
//	public @ResponseBody
//	String test(){
//		return "test";
//		
//	}
	
//	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
//	public @ResponseBody User getUserById(HttpServletRequest request){
//		String id = request.getParameter("id");
//		System.out.println(id);
//		return userService.getUserById(Integer.parseInt(id));
//		
//	}
	
//	@RequestMapping(value = "/findtest", method = RequestMethod.GET)
//	public @ResponseBody
//	String findtest() {
//
//		return "findtest";
//	}
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> addUser(UserInfo userinfo){
		Map<String, Object> result = new HashMap<String, Object>();
		if (userinfo.getUserName() == "" || userinfo.getUserPassword() == ""
				|| userinfo.getPhone() == "" && userinfo.getUserName() == null
				|| userinfo.getUserPassword() == null
				|| userinfo.getPhone() == null) {
			result.put("result", "fail");
			result.put("reason", "0");
			return result;
		} else {
			UserInfo userInfo = userService.findUserByUsername(userinfo.getUserName());
			if (userInfo != null) {
				result.put("result", "fail");
				result.put("reason", "1");
				return result;
			} else {
				userService.addUser(userinfo);
				result.put("result", "success");
				return result;
			}
		}
	}
	
	@RequestMapping(value = "/getDetail", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Map<String, Object> getDetail(int userId){
		Map<String, Object> result = new HashMap<String, Object>();
		UserInfo userInfo = userService.findUserByUserId(userId);
		result.put("userDetail", userInfo);
		result.put("test", "测试中文");
		return result;
		
	}
}
