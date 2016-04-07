package com.lifeservice.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lifeservice.model.Nearby;
import com.lifeservice.model.PositionInfo;
import com.lifeservice.model.Server;
import com.lifeservice.model.UserInfo;
import com.lifeservice.service.PositionService;
import com.lifeservice.service.ServerService;
import com.lifeservice.service.UserService;
import com.lifeservice.utils.UtilMethods;

@Controller
@RequestMapping("/position")
public class PositionalController {
	@Autowired
	private PositionService positionService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ServerService serverService;
	
	@RequestMapping(value = "/savePosition", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public void savePosition(PositionInfo positionInfo) throws ParseException{
		
		//Map<String, Object> result = new HashMap<String, Object>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		positionInfo.setUpdateTime(UtilMethods.stringToDate(df.format(new Date())));
		try {
			PositionInfo hasPositionInfo = positionService.getPositionInfoByUserId(positionInfo.getUserId());
			if(hasPositionInfo != null){
				positionInfo.setPositionId(hasPositionInfo.getPositionId());
				positionService.updatePosition(positionInfo);
			}else{
				positionService.savePosition(positionInfo);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(value = "/getNearBy", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Map<String, Object> getNearBy(String city,float jingDu,float weiDu){
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<PositionInfo> positionalList = positionService.getPositionByCity(city);
		List<PositionInfo> nearbyPositionList = new ArrayList<PositionInfo>();
		List<Nearby> nearByList = new ArrayList<Nearby>();
		
		
		List<Double> disList = new ArrayList<Double>();
		
		if(positionalList == null || positionalList.size() == 0){
			result.put("nearby", "fail");
		}else{
			for(int i = 0,j = 0; i < positionalList.size();i++){
				float a = positionalList.get(i).getJingDu();
				float b = positionalList.get(i).getWeiDu();
				System.out.println(a);
				System.out.println(b);
				double distance = UtilMethods.getDistance(jingDu, weiDu, 
						positionalList.get(i).getJingDu(), 
						positionalList.get(i).getWeiDu());
				if(distance < 10 ){
					nearbyPositionList.add(j, positionalList.get(i));
					disList.add(j, distance);
					j ++;
				}
				
			}
			for(int i = 0;i < nearbyPositionList.size(); i++){
				Nearby nearby = new Nearby();
				int userId = nearbyPositionList.get(i).getUserId();
				
				UserInfo userInfo = userService.findUserByUserId(userId);
				//List<Userinfo>  userList  = positionalService.getNearByUserByUserId(userId);
				List<Server> serverList =  serverService.findServerListByUserId(userId);
				if(serverList == null || serverList.size() == 0){
					continue;
				}
				nearby.setUserId(userId);
				nearby.setProvide(serverList.get(0).getServerName());
				nearby.setTouXiangPath(userInfo.getMemo());
				nearby.setDistance((Double) disList.get(i));
				nearby.setPhoneNum(userInfo.getPhone());
				nearby.setServerContent(serverList.get(0).getServerContent());
				nearby.setUserName(userInfo.getUserName());
				nearby.setJingDu(serverList.get(0).getJingDu());
				nearby.setWeiDu(serverList.get(0).getWeiDu());
				//nearby.setUpdateTime(serverList.get(0).getUpdateTime());
				nearByList.add(nearby);
				
				
			}
			result.put("nearby", nearByList);
		}
		return result;
		
	}
}
