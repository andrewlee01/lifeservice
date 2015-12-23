package com.lifeservice.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lifeservice.model.User;
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

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	String showUser() {

		return "12";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	User showOneUser(@PathVariable("id") int id) {

		return userService.getUserById(id);
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public @ResponseBody
	String test(){
		return "test";
		
	}
	
	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	public @ResponseBody User getUserById(HttpServletRequest request){
		String id = request.getParameter("id");
		System.out.println(id);
		return userService.getUserById(Integer.parseInt(id));
		
	}
	
	@RequestMapping(value = "/findtest", method = RequestMethod.GET)
	public @ResponseBody
	String findtest() {

		return "findtest";
	}
}
