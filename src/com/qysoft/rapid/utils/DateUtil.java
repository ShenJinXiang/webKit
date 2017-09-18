

/****************************************************************************
Module: 日期转换处理
Version: 1.0
Author:  chenkb  
Company: excel
Description:
*****************************************************************************/

package com.qysoft.rapid.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
* 日期转换工具
*/
public class DateUtil
{
   public static final String C_DATE_DIVISION = "-";
   public static final String C_TIME_PATTON_OPTION = "yyyyMMddHHmmssSSS";
   public static final String C_TIME_PATTON_DEFAULT = "yyyy-MM-dd HH:mm:ss";
   public static final String C_DATE_PATTON_DEFAULT = "yyyy-MM-dd";
   public static final String C_DATE_PATTON_YEAR_MONTH = "yyyy-MM";
   public static final String C_DATA_PATTON_YYYYMMDD = "yyyyMMdd";
   public static final String C_TIME_PATTON_HHMMSS = "HH:mm:ss";
   
   public static final int  C_ONE_SECOND = 1000;
   public static final int  C_ONE_MINUTE = 60 * C_ONE_SECOND;
   public static final int  C_ONE_HOUR   = 60 * C_ONE_MINUTE;
   public static final long C_ONE_DAY    = 24 * C_ONE_HOUR;
   
   /**
    * Return the current date
    * 
    * @return － DATE<br>
    */
   public static Date getCurrentDate()
   {
    Calendar cal = Calendar.getInstance();
    Date currDate = cal.getTime();
    
    return currDate;
   }

   public static int compare_date(String DATE1, String DATE2) {
       
       
	   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
       try {
           Date dt1 = df.parse(DATE1);
           Date dt2 = df.parse(DATE2);
           if (dt1.getTime() > dt2.getTime()) {
               return 1;
           } else if (dt1.getTime() < dt2.getTime()) {
               return -1;
           } else {
               return 0;
           }
       } catch (Exception exception) {
           exception.printStackTrace();
       }
       return 0;
   }
   
   public static int compare(String DATE1, String DATE2) {
       
       
	   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
       try {
           Date dt1 = df.parse(DATE1);
           Date dt2 = df.parse(DATE2);
           if (dt1.getTime() > dt2.getTime()) {
               return -1;
           } else if (dt1.getTime() < dt2.getTime()) {
               return 1;
           } else {
               return 0;
           }
       } catch (Exception exception) {
           exception.printStackTrace();
       }
       return 0;
   }
   /**
    * Return the current date string
    * 
    * @return － 产生的日期字符串<br>
    */
   public static String getCurrentDateStr()
   {
    Calendar cal = Calendar.getInstance();
    Date currDate = cal.getTime();
    
    return format(currDate);
   }

   public static String getCurrentDateTimeStr()
   {
    Calendar cal = Calendar.getInstance();
    Date currDate = cal.getTime();

    return format(currDate, C_TIME_PATTON_DEFAULT);
   }
   
   /**
 * Return the current date in the specified format
 * 
 * @param strFormat
 * @return
 */
public static String getCurrentDateStr(String strFormat)
{
    Calendar cal = Calendar.getInstance();
    Date currDate = cal.getTime();
    
    return format(currDate, strFormat);
}
   /**
    * Parse a string and return a date value
    * 
    * @param dateValue
    * @return
    * @throws Exception
    */
   public static Date parseDate(String dateValue)
   {
    return parseDate(C_DATE_PATTON_DEFAULT, dateValue);
   }
   
   /**
    * Parse a strign and return a datetime value
    * 
    * @param dateValue
    * @return
    */
   public static Date parseDateTime(String dateValue)
   {
    return parseDate(C_TIME_PATTON_DEFAULT, dateValue);
   }
   /**
    * 获取当前月的最后一天
    */
   public static String getLastDate(String year,String month){
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
	   Calendar ca = Calendar.getInstance();   
	   ca.set(Calendar.DAY_OF_MONTH, 1);  //设置日期  
	   if(!"".equals(year)){
		   ca.set(Calendar.YEAR, Integer.parseInt(year));
	   }
	   if(!"".equals(month)){
		   ca.set(Calendar.MONTH, Integer.parseInt(month)-1);
	   }
       ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
       String last = format.format(ca.getTime());
       return last;
   }
/**
 * Parse a string and return the date value in the specified format
 * 
 * @param strFormat
 * @param dateValue
 * @return
 * @throws ParseException 
 * @throws Exception
 */
public static Date parseDate(String strFormat, String dateValue)
{
       if (dateValue == null)
           return null;
       
       if (strFormat == null)
           strFormat = C_TIME_PATTON_DEFAULT;
       
 SimpleDateFormat dateFormat = new SimpleDateFormat(strFormat);
 Date newDate = null;
 
 try
 {
  newDate = dateFormat.parse(dateValue);
 }
 catch (ParseException pe)
 {
  newDate = null;
 }

 return newDate;
}


   /**
    * 将Timestamp类型的日期转换为系统参数定义的格式的字符串。
    * @param aTs_Datetime 需要转换的日期。
    * @return 转换后符合给定格式的日期字符串
    */
   public static String format(Date aTs_Datetime)
   {
     return format(aTs_Datetime, C_DATE_PATTON_DEFAULT);
   }
   
   /**
    * 将Timestamp类型的日期转换为系统参数定义的格式的字符串。
    * @param aTs_Datetime 需要转换的日期。
    * @return 转换后符合给定格式的日期字符串
    */
   public static String formatTime(Date aTs_Datetime)
   {
     return format(aTs_Datetime, C_TIME_PATTON_DEFAULT);
   }

   /**
    * 将Date类型的日期转换为系统参数定义的格式的字符串。
    * @param aTs_Datetime
    * @param as_Pattern
    * @return
    */
   public static String format(Date aTs_Datetime, String as_Pattern)
   {
     if (aTs_Datetime == null || as_Pattern == null)
       return null;

     SimpleDateFormat dateFromat = new SimpleDateFormat();
     dateFromat.applyPattern(as_Pattern);
     
     return dateFromat.format(aTs_Datetime);
   }
   
   /**
    * @param aTs_Datetime
    * @param as_Format
    * @return
    */
   public static String formatTime(Date aTs_Datetime, String as_Format)
   {
       if (aTs_Datetime == null || as_Format == null)
           return null;

         SimpleDateFormat dateFromat = new SimpleDateFormat();
         dateFromat.applyPattern(as_Format);
         
         return dateFromat.format(aTs_Datetime);
   }
   
   public static String getFormatTime(Date dateTime)
   {
       return formatTime(dateTime, C_TIME_PATTON_HHMMSS);
   }
   /**
    * @param aTs_Datetime
    * @param as_Pattern
    * @return
    */
   public static String format(Timestamp aTs_Datetime, String as_Pattern)
   {
     if (aTs_Datetime == null || as_Pattern == null)
       return null;

     SimpleDateFormat dateFromat = new SimpleDateFormat();
     dateFromat.applyPattern(as_Pattern);
     
     return dateFromat.format(aTs_Datetime);
   }
/**
 * 取得指定日期N天后的日期
 * @param date
 * @param days
 * @return
 */
public static Date addDays(Date date, int days)
{
 Calendar cal = Calendar.getInstance();
 cal.setTime(date);
 
 cal.add(Calendar.DAY_OF_MONTH, days);
 
 return cal.getTime();
}

/**
 * 计算两个日期之间相差的天数
 * @param date1
 * @param date2
 * @return
 */
public static int daysBetween(Date date1,Date date2)
{
 Calendar cal = Calendar.getInstance();
 cal.setTime(date1);
 long time1 = cal.getTimeInMillis();    
 cal.setTime(date2);
 long time2 = cal.getTimeInMillis();  
 long between_days=(time2-time1)/(1000*3600*24);
 
       return Integer.parseInt(String.valueOf(between_days));  
}

/**
 * 计算当前日期相对于"1977-12-01"的天数
 * @param date
 * @return
 */
public static long getRelativeDays(Date date)
{
 Date relativeDate = DateUtil.parseDate("yyyy-MM-dd","1977-12-01");
 
 return DateUtil.daysBetween(relativeDate,date);
}

   public static Date getDateBeforTwelveMonth()
   {
       String date = "";
       Calendar cla = Calendar.getInstance();
       cla.setTime(getCurrentDate());
       int year = cla.get(Calendar.YEAR) - 1;
       int month = cla.get(Calendar.MONTH) + 1 ;
       if (month >9 )
       {
           date = String.valueOf(year) + C_DATE_DIVISION + String.valueOf(month)
                   + C_DATE_DIVISION + "01";
       }
       else 
       {
           date = String.valueOf(year) + C_DATE_DIVISION + "0" + String.valueOf(month)
                   + C_DATE_DIVISION + "01" ;
       }
       
       Date dateBefore = parseDate(date);
       return dateBefore;
   }
   
   /**
    * 传入时间字符串,加一天后返回Date
    * @param date 时间 格式 YYYY-MM-DD 
    * @return
    */
   public static Date addDate(String date)
   {
       if (date == null)
       {
           return null;
       }

       Date tempDate = parseDate(C_DATE_PATTON_DEFAULT, date);
       String year = format(tempDate, "yyyy");
       String month = format(tempDate, "MM");
       String day = format(tempDate, "dd");
       
       
       GregorianCalendar calendar = 
           new GregorianCalendar(Integer.parseInt(year), Integer.parseInt(month)-1, Integer.parseInt(day));
       
       calendar.add(GregorianCalendar.DATE, 1);
       return calendar.getTime();
   }

    /**
     *
     * @param dateStr1 yyyy-MM-dd
     * @param dateStr2 yyyy-MM-dd
     * @return
     */
   public static int dayDiff(String dateStr1, String dateStr2) {
       String str1 = dateStr1 + "00:00:00 000";
       String str2 = dateStr2 + "00:00:00 000";
       Date date1 = DateUtil.parseDate(dateStr1);
       Date date2 = DateUtil.parseDate(dateStr2);
       int diff = daysBetween(date1, date2);
       System.out.println(diff);
       return diff;
   }

}