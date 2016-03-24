package com.lifeservice.utils;

import java.util.Random;

/**
 * 随机数工具类
 * @Project:JUtils
 * @file:RandomUtils.java
 */
public class RandomUtils {
	private static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
	private static final String letterChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
	private static final String numberChar = "0123456789";
    
    
    /**
     * 获取定长的随机数，只包含数字
     * @param length
     * 				随机数长度
     * @return
     */
    public static String generateNumberString(int length){
    	StringBuffer sb = new StringBuffer(); 
        Random random = new Random(); 
        for (int i = 0; i < length; i++) { 
                sb.append(numberChar.charAt(random.nextInt(numberChar.length()))); 
        } 
        return sb.toString(); 
    }
    
}
