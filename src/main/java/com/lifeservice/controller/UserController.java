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

import redis.clients.jedis.Jedis;

import com.lifeservice.model.UserImage;
import com.lifeservice.model.UserInfo;
import com.lifeservice.service.UserService;
import com.lifeservice.utils.RandomUtils;
import com.lifeservice.utils.RedisUtil;
import com.lifeservice.utils.SMSUtils;
import com.lifeservice.utils.UtilMethods;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	public RedisUtil redisUtil;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/addUser", method = { RequestMethod.POST, RequestMethod.GET } )
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
	
	@RequestMapping(value = "/userLogin", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Map<String, Object> userLogin(String userName, String userPassword){
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		if (userName != null && userPassword != null) {
			
			UserInfo userInfo = userService.findUserByUsername(userName);
			
			if (userInfo != null) {
				
				if (userInfo.getUserPassword().equals(userPassword)) {
					result.put("result", "success");
					result.put("userId", userInfo.getUserId());
				} 
				else {
					result.put("result", "fail");
					result.put("reason", "2");
				}
				
			} 
			else {
				result.put("result", "fail");
				result.put("reason", "1");
			}
			
		} 
		else {
			result.put("result", "fail");
			result.put("reason", "0");
		}
		
		return result;
	}
	
	@RequestMapping(value = "/sendIdentify", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Map<String, Object> sendIdentify(String phoneNum){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String identifyCode = RandomUtils.generateNumberString(6);   //随机生成6位数字验证码
			Jedis jedis = redisUtil.createRedis();
			jedis.set(phoneNum, identifyCode);
			// 设置 key的过期时间
	        System.out.println("设置手机号:"+phoneNum+"的验证码过期时间为15分钟:"+jedis.expire(phoneNum, 900));
	        boolean isSuccess;
	        do{
	        	isSuccess = SMSUtils.sendSms(phoneNum, identifyCode);
	        }while(!isSuccess);
	        result.put("result", "success");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", "fail");
		}
		return result;
	}
	
	
	@RequestMapping(value = "/verify", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Map<String, Object> verify(String phoneNum, String identifyCode){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Jedis jedis = redisUtil.createRedis();
			String code = jedis.get("phoneNum");
			if(code == null || code == ""){
				result.put("result", "fail");
			}
			if(code.equals(identifyCode)){
				result.put("result", "success");
				System.out.println("系统中删除电话号码为:"+phoneNum+"的KEY "+jedis.del(phoneNum));
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", "fail");
		}
		return result;
		
	}
}
