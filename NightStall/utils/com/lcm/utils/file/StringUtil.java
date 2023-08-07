package com.lcm.utils.file;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class StringUtil {
	/**
	 * 字符串list转字符串,以,隔开
	 * */
	public static String listToString(List<String> stringList){
        return listToString(stringList, ",");
    }
	
	/**
	 * 字符串list转字符串，并且以指定的字符分割
	 * */
	public static String listToString(List<String> stringList, String separator){
        if (stringList==null) {
            return null;
        }
        StringBuilder result=new StringBuilder();
        boolean flag=false;
        for (String string : stringList) {
            if (flag) {
                result.append(separator);
            }else {
                flag=true;
            }
            result.append(string);
        }
        return result.toString();
    }
	
	
	/** 
     * 获取最近12个月，经常用于统计图表的X轴 
     */  
    public static String[] getLast12Months(){  
          
        String[] last12Months = new String[12];  
          
        Calendar cal = Calendar.getInstance();  
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)+1); //要先+1,才能把本月的算进去</span>  
        for(int i=0; i<12; i++){  
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)-1); //逐次往前推1个月  
            int month = cal.get(Calendar.MONTH);
            if(month >= 9) {
            	last12Months[11-i] = cal.get(Calendar.YEAR)+ "-" + (cal.get(Calendar.MONTH)+1);  
            } else {
            	last12Months[11-i] = cal.get(Calendar.YEAR)+ "-0" + (cal.get(Calendar.MONTH)+1);  
            }
            
        }  
          
        return last12Months;  
    }  
    

	/** 
     * 计算日期间相差天数、月份(相差的天数用1年2个月零3天格式展示) 
     */ 
    public static int calDiffMonth(String startDate,String endDate){
        int result=0;
        try {
            SimpleDateFormat sfd=new SimpleDateFormat("yyyy-MM-dd");
            Date start = sfd.parse(startDate);
            Date end = sfd.parse(endDate);
            int startYear=getYear(start);
            int startMonth=getMonth(start);
            int startDay=getDay(start);
            int endYear=getYear(end);
            int endMonth=getMonth(end);
            int endDay=getDay(end);
            if (startDay>endDay){ //1月17  大于 2月28
                if (endDay==getDaysOfMonth(getYear(new Date()),2)){   //也满足一月
                    result=(endYear-startYear)*12+endMonth-startMonth;
                }else{
                    result=(endYear-startYear)*12+endMonth-startMonth-1;
                }
            }else{
                result=(endYear-startYear)*12+endMonth-startMonth;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 返回日期的月份，1-12,即yyyy-MM-dd中的MM
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 返回日期的年,即yyyy-MM-dd中的yyyy
     *
     * @param date
     *            Date
     * @return int
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static int getDaysOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    
    public static String[] getMonthBetween(String minDate, String maxDate) {
		ArrayList<String> result = null;
		try {
			result = new ArrayList<String>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月

			Calendar min = Calendar.getInstance();
			Calendar max = Calendar.getInstance();

			min.setTime(sdf.parse(minDate));
			min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

			max.setTime(sdf.parse(maxDate));
			max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

			Calendar curr = min;
			while (curr.before(max)) {
			 result.add(sdf.format(curr.getTime()));
			 curr.add(Calendar.MONTH, 1);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String[] arr = new String[result.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = result.get(i);
		}
		
		return arr;
	}
    
    public static void main(String[] args) {
    	String[] arr = getMonthBetween("2016-04-09", "2017-03-09");
        for (int i = 0; i < arr.length; i++) {
			System.out.println("---------" + arr[i]);
		}
    }
}
