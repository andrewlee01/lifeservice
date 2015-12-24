package com.lifeservice.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.lifeservice.model.UserImage;
import com.lifeservice.model.UserInfo;
import com.lifeservice.service.UserService;
import com.lifeservice.utils.UtilMethods;

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
	
	@RequestMapping(value = "/updateUser", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Map<String, Object> updateUser(int userId,String key,String value){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", userService.updateUser(userId, key, value));
		return result;
		
	}
	
	@RequestMapping(value = "/uploadPic", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Map<String, Object> uploadPic(HttpServletRequest request,UserImage userImage,int userId){
		Map<String, Object> result = new HashMap<String, Object>();
		ServletRequestAttributes attr = (ServletRequestAttributes) 
                RequestContextHolder.currentRequestAttributes(); 
        request = attr.getRequest();
		FileOutputStream fos = null;
		FileInputStream fis = null;
		try {
			if (!(new java.io.File(userImage.getSavePath()).isDirectory())) {
				new java.io.File(userImage.getSavePath()).mkdir();
			}
			fos = new FileOutputStream(userImage.getSavePath() + "/" + userImage.getImageFileName());
			fis = new FileInputStream(userImage.getImage());
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) != -1) {
				fos.write(buffer, 0, len);
			}
			userService.updateUser(userId, "memo", userImage.getImageFileName());
			result.put("result", "success");
		} catch (Exception e) {
			result.put("result", "fail");
			e.printStackTrace();
		} finally {
			UtilMethods.close(fos, fis);
		}
		return result;
		
	}
	
}
