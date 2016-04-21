package com.lifeservice.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.apache.log4j.Logger;



public class UtilMethods {
	private static Logger log = Logger.getLogger(UtilMethods.class);
	public final static double EARTH_RADIUS_KM = 6378.137;
	static String[] dictionary = { "的", "地", "是" ,"要",".",","};
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
	
	/**
	 * 关闭文件流
	 * @param fos
	 * @param fis
	 */
	public static void close(FileOutputStream fos, FileInputStream fis) {
		if (fis != null) {
			try {
				fis.close();
				fis = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (fos != null) {
			try {
				fos.close();
				fis = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 汉字分词
	 * @param str
	 * @return
	 */
	public static ArrayList<String> fenci(String str){
		LinkedList<Term> list = (LinkedList) ToAnalysis.parse(str);
		ArrayList<String> arrayList = new ArrayList<String>();
		for (Term m : list) {
			System.out.println(m.getName());
			arrayList.add(m.getName());
		}
		return arrayList;
		
	}
	
	/**
	 * 去除语气词
	 * @param list
	 * @return
	 */
	public static ArrayList<String> doSomething(ArrayList<String> list){
		Set<String> hashSet = new HashSet<String>();
		hashSet.addAll(list);
		ArrayList<String> keyWords = new ArrayList<String>();
		for (Object a : hashSet) {
			keyWords.add(a.toString());
		}
		for (String aString : dictionary) {
			if (keyWords.contains(aString)) {
				keyWords.remove(aString);
			}
		}
		return keyWords;
	}
	
	public static String creatSql(ArrayList<String> list){
		StringBuilder str = new StringBuilder();
		str.append("(SELECT * FROM server where KeyWord LIKE '%"+list.get(list.size() - 1)+"%' AND memo = 1) as emp0 ") ;
		list.remove(0);
		for(int i = list.size() - 1;i>= 0;i--) {
			str.insert(0,"(SELECT * FROM ");
			str.insert(str.length(),"WHERE emp"+i+".KeyWord LIKE '%"+list.get(i)+"%' AND memo = 1) as emp"+(i+1)+" ");
		}
		String string = str.toString();
		return string.substring(1,string.length()-10);
	}
	
	 public static long fromDateStringToLong(String inVal) { //此方法计算时间毫秒
		  Date date = null;   //定义时间类型       
		  SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-mm-dd hh:ss"); 
		  try { 
		  date = inputFormat.parse(inVal); //将字符型转换成日期型
		  } catch (Exception e) { 
		  e.printStackTrace(); 
		  } 
		  return date.getTime();   //返回毫秒数
		  } 
	 
	 public static String dqsj() {  //此方法用于获得当前系统时间（格式类型2007-11-6 15:10:58）
		   Date date = new Date();  //实例化日期类型
		   String today = date.toLocaleString(); //获取当前时间
		   System.out.println("获得当前系统时间 "+today);  //显示
		   return today;  //返回当前时间
		  }
	 
	 public static String getDateStr(Date date, String formate) {
			SimpleDateFormat sdf = new SimpleDateFormat(formate);
			return sdf.format(date);
		}
}
