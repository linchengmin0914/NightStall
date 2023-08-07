package com.lcm.utils.file;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	/** 
    * 获取过去第几天的日期 
    * 
    * @param past 
    * @return 
    */  
   public static String getPastDate(int past) {  
       Calendar calendar = Calendar.getInstance();  
       calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);  
       Date today = calendar.getTime();  
       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
       String result = format.format(today);  
       return result;  
   }  
  
   /** 
    * 获取未来 第 past 天的日期 
    * @param past 
    * @return 
    */  
   public static String getFetureDate(int past) {  
       Calendar calendar = Calendar.getInstance();  
       calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);  
       Date today = calendar.getTime();  
       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
       String result = format.format(today);  
       return result;  
   }  
   
   
   public static Date getFetureToDate(int past) {  
       Calendar calendar = Calendar.getInstance();  
       calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);  
       Date today = calendar.getTime();  
       return today;  
   }  
   
   /** 
    * 获取过去第几天的日期 
    * @param past 
    * @return 
    */  
   public static String getPastDate(Date date, int past) {  
       Calendar calendar = Calendar.getInstance(); 
       calendar.setTime(date);
       calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);  
       Date today = calendar.getTime();  
       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
       String result = format.format(today);  
       return result;  
   }  
   
   public static String getPastDate2(Date date, int past) {  
       Calendar calendar = Calendar.getInstance(); 
       calendar.setTime(date);
       calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);  
       Date today = calendar.getTime();  
       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
       String result = format.format(today);  
       return result;  
   } 
   
   /** 
    * 获取未来 第 past 天的日期 
    * @param past 
    * @return 
    */  
   public static String getFetureDate(Date date, int past) {  
       Calendar calendar = Calendar.getInstance(); 
       calendar.setTime(date);
       calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);  
       Date today = calendar.getTime();  
       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
       String result = format.format(today);  
       return result;  
   }  
   
   /** 
    * 获取未来 第 past 天的日期 
    * @param past 
    * @return 
    */  
   public static Date getFetureDDate(Date date, int past) {  
       Calendar calendar = Calendar.getInstance(); 
       calendar.setTime(date);
       calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);  
       Date today = calendar.getTime();  
       return today;  
   }  
   
   public static void main(String[] args) {
	   System.out.println("=============" + getFetureDate(new Date(), 3));
   }
}
