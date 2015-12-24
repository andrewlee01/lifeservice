package com.lifeservice.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;



public class UtilMethods {
	private static Logger log = Logger.getLogger(UtilMethods.class);
	public final static double EARTH_RADIUS_KM = 6378.137;
	/**
	 * 字符串MD5加密
	 */
	public static String encryptMD5(String strInput) {
		StringBuffer buf = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(strInput.getBytes());
			byte b[] = md.digest();
			buf = new StringBuffer(b.length * 2);
			for (int i = 0; i < b.length; i++) {
				if (((int) b[i] & 0xff) < 0x10) { // & 0xff转换无符号整型
					buf.append("0");
				}
				// buf.append(Long.toString((int) b[i] & 0xff,
				// 16));//转换16进制,下方法同
				buf.append(Long.toHexString((int) b[i] & 0xff));
			}
		} catch (NoSuchAlgorithmException ex) {
			log.error("加密md5错误", ex);
		}
		String result = buf.toString();
		log.info("加密md5(" + strInput + ")=" + result);
		return result;
	}
	
	/**
	 * 通过两点经纬度得到两点直线距离
	 * @param lng1 A点经度
	 * @param lat1 A点维度
	 * @param lng2 B点经度
	 * @param lat2 B点纬度
	 * @return
	 */
	public static double getDistance(double lng1, double lat1, double lng2,

			double lat2){
		double radLat1 = Math.toRadians(lat1);

		double radLat2 = Math.toRadians(lat2);

		double radLng1 = Math.toRadians(lng1);

		double radLng2 = Math.toRadians(lng2);

		double deltaLat = radLat1 - radLat2;

		double deltaLng = radLng1 - radLng2;

		double distance = 2 * Math.asin(Math.sqrt(Math.pow(

		Math.sin(deltaLat / 2), 2)

		+ Math.cos(radLat1)

		* Math.cos(radLat2)

		* Math.pow(Math.sin(deltaLng / 2), 2)));

		distance = distance * EARTH_RADIUS_KM;

		//distance = Math.round(distance * 10000) / 10000;
		System.out.println(distance);
		log.info("两点间距离为：------->>>>>>>>"+ distance);
		return distance;

		}
	
	/**
	 * 字符串转日期
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	public static Date stringToDate(String str) throws ParseException{
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sFormat.parse(str);
	}
}
