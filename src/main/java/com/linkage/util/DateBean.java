package com.linkage.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * ClassName : DateBean
 * Description : 获取日期时间的类
 */

public  class DateBean
{private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(DateBean.class);
  private static String vssHeader = "$Header: /webapp/BasicPlan/src/com/xwtec/util/DateBean.java 14    10/05/20 9:21 Wb $";
  /**
   * 类公有变量定义
   */

  /**
   * 类私有变量定义
   */
  private Calendar calendar = null;

  /**
   * 类保护变量定义
   */

  /**
   * 构造函数
   */
  public DateBean()
  {
    calendar = Calendar.getInstance();
    calendar.setTime(new Date());
  }

  public int getYear() //获得年
  {
    return calendar.get(Calendar.YEAR);
  }

  public int getMonth() //获得月
  {
    return 1+calendar.get(Calendar.MONTH);
  }
  
  public int getLastMonth() //获得上一个月
  {
    return calendar.get(Calendar.MONTH);
  }
  
  public int getWeek(){ //获得周
  	return calendar.get(Calendar.WEEK_OF_YEAR);
  }

  public int getDay() //获得日
  {
    return calendar.get(Calendar.DAY_OF_MONTH);
  }

  public int getHour() //获得时
  {
    return calendar.get(Calendar.HOUR_OF_DAY);
  }

  public int getMinute() //获得分
  {
    return calendar.get(Calendar.MINUTE);
  }

  public int getSecond() //获得秒
  {
    return calendar.get(Calendar.SECOND);
  }
  
  public int getMilliSecond() //获得毫秒
  {
    return calendar.get(Calendar.MILLISECOND);
  }

  public String getDate() //获得简写日期：12/30/2001
  {
    return getYear()+"-"+getMonth()+"-"+getDay();
  }

  public String getTime() //获得简写时间：23：59：59
  {
    return getHour()+":"+getMinute()+":"+getSecond();
  }

  public String getYearMonthDayNull() //获得年月日 20010101
  {
    String yyyy = "0000",mm = "00",dd = "00";
    yyyy = yyyy+getYear();
    mm = mm+getMonth();
    dd = dd+getDay();
    yyyy = yyyy.substring(yyyy.length()-4);
    mm = mm.substring(mm.length()-2);
    dd = dd.substring(dd.length()-2);
    return yyyy+""+mm+""+dd;
  }

  public String getYearMonthNull() //获得年月日 20010101
  {
    String yyyy = "0000",mm = "00";
    yyyy = yyyy+getYear();
    mm = mm+getMonth();
    yyyy = yyyy.substring(yyyy.length()-4);
    mm = mm.substring(mm.length()-2);
    return yyyy+""+mm;
  }

  public String getYearMonthDay() //获得年月日 2001-01-01
  {
    String yyyy = "0000",mm = "00",dd = "00";
    yyyy = yyyy+getYear();
    mm = mm+getMonth();
    dd = dd+getDay();
    yyyy = yyyy.substring(yyyy.length()-4);
    mm = mm.substring(mm.length()-2);
    dd = dd.substring(dd.length()-2);
    return yyyy+"-"+mm+"-"+dd;
  }
  
  public String getYearMonth() //获得年月 200101
  {
    String yyyy = "0000",mm = "00";
    yyyy = yyyy+getYear();
    mm = mm+getMonth();
    yyyy = yyyy.substring(yyyy.length()-4);
    mm = mm.substring(mm.length()-2);
    return yyyy+mm;
  }
  
  public String getYearMonthBYmode(String mode) //获得年月 2001-01
  {
    String yyyy = "0000",mm = "00";
    yyyy = yyyy+getYear();
    mm = mm+getMonth();
    yyyy = yyyy.substring(yyyy.length()-4);
    mm = mm.substring(mm.length()-2);
    return yyyy+mode+mm;
  }

  public String getHourMinuteSecond() //获得时分秒 01：01：01
  {
    String hh = "00",mm = "00",ss = "00";
    hh = hh+getHour();
    mm = mm+getMinute();
    ss = ss+getSecond();
    hh = hh.substring(hh.length()-2,hh.length());
    mm = mm.substring(mm.length()-2,mm.length());
    ss = ss.substring(ss.length()-2,ss.length());
    return hh+":"+mm+":"+ss;
  }

  public String getHourMinuteSecondNull() //获得时分秒 010101
  {
    String hh = "00",mm = "00",ss = "00";
    hh = hh+getHour();
    mm = mm+getMinute();
    ss = ss+getSecond();
    hh = hh.substring(hh.length()-2,hh.length());
    mm = mm.substring(mm.length()-2,mm.length());
    ss = ss.substring(ss.length()-2,ss.length());
    return hh+mm+ss;
  }

  /**
   * 年份选择框
   * @param controlname
   * @param defaultYear
   * @param fromYear
   * @param toYear
   * @param hasAll
   * @return
   */
  public String getSelectYear(String controlname,int defaultYear,int fromYear,int toYear,boolean hasAll)
  {
    StringBuffer sb = new StringBuffer();
    sb.append("<select name="+controlname+">");
    if(hasAll)
      sb.append("<option value='-1'>-----");
    for(int i = fromYear;i<=toYear;i++)
    {
      if(i==defaultYear)
        sb.append("<option value="+i+" selected>"+i);
      else
        sb.append("<option value="+i+">"+i);
    }
    sb.append("</select>");
    return sb.toString();
  }
  
  /**
   * 年份选择框
   * @param controlname
   * @param defaultYear
   * @param fromYear
   * @param toYear
   * @param hasAll
   * @param eventname
   * @param functionname
   * @return
   */
  public String getSelectYear2(String controlname,int defaultYear,int fromYear,int toYear,boolean hasAll,String eventname,String functionname)
  {
    StringBuffer sb = new StringBuffer();
    sb.append("<select name="+controlname);
    
    if(null!=eventname && null!=functionname)
    {
    	sb.append(" "+eventname+"=\""+functionname+";\" ");
    }
    sb.append(">");
    
    if(hasAll)
      sb.append("<option value='-1'>-----");
    for(int i = fromYear;i<=toYear;i++)
    {
      if(i==defaultYear)
        sb.append("<option value="+i+" selected>"+i);
      else
        sb.append("<option value="+i+">"+i);
    }
    sb.append("</select>");
    return sb.toString();
  }

  /**
   * 月份选择框
   * @param controlname
   * @param defaultMonth
   * @param hasAll
   * @return
   */
  public String getSelectMonth(String controlname,int defaultMonth,boolean hasAll)
  {
    StringBuffer sb = new StringBuffer();
    sb.append("<select name="+controlname+">");
    if(hasAll)
      sb.append("<option value='-1'>-----");
    for(int i = 1;i<=12;i++)
    {
      if(i==defaultMonth)
        sb.append("<option value="+i+" selected>"+i);
      else
        sb.append("<option value="+i+">"+i);
    }
    sb.append("</select>");
    return sb.toString();
  }
  /**
   * 月份选择框
   * @param controlname
   * @param defaultMonth
   * @param hasAll
   * @param eventname
   * @param functionname
   * @return
   */
  public String getSelectMonth2(String controlname,int defaultMonth,boolean hasAll,String eventname,String functionname)
  {
    StringBuffer sb = new StringBuffer();
    
    sb.append("<select name="+controlname);
    if(null!=eventname && null!=functionname)
    {
    	sb.append(" "+eventname+"=\""+functionname+";\" ");
    }
    sb.append(">");
    if(hasAll)
      sb.append("<option value='-1'>-----");
    for(int i = 1;i<=12;i++)
    {
      if(i==defaultMonth)
        sb.append("<option value="+i+" selected>"+i);
      else
        sb.append("<option value="+i+">"+i);
    }
    sb.append("</select>");
    return sb.toString();
  }
  /**
   * 小时选择框
   * @param controlname
   * @param defaultMonth
   * @param hasAll
   * @return
   */
  public String getSelectHour(String controlname,int defaultHour,boolean hasAll)
  {
    StringBuffer sb = new StringBuffer();
    sb.append("<select name="+controlname+">");
    for(int i = 0;i<24;i++)
    {
      String number = "";
      if(i>9)
      {
        number = String.valueOf(i);
      }
      else
      {
        number = "0"+String.valueOf(i);
      }
      if(Integer.parseInt(number)==defaultHour)
        sb.append("<option value="+number+" selected>"+number);
      else
        sb.append("<option value="+number+">"+number);
    }
    sb.append("</select>");
    return sb.toString();
  }

  /**
   * 分钟选择框
   * @param controlname
   * @param defaultMonth
   * @param hasAll
   * @return
   */
  public String getSelectMinute(String controlname,int defaultMinute,boolean hasAll)
  {
    StringBuffer sb = new StringBuffer();
    sb.append("<select name="+controlname+">");
    for(int i = 0;i<60;i++)
    {
      String number = "";
      if(i>9)
      {
        number = String.valueOf(i);
      }
      else
      {
        number = "0"+String.valueOf(i);
      }
      if(Integer.parseInt(number)==defaultMinute)
        sb.append("<option value="+number+" selected>"+number);
      else
        sb.append("<option value="+number+">"+number);
    }
    sb.append("</select>");
    return sb.toString();
  }

  /**
   * 获取两个时间的差值
   * @param date1 yyyymmdd 较小的日期
   * @param date2 yyyymmdd 较大的日期
   * @return
   */
  public static int getDiffDay(Date date1,Date date2) throws Exception
  {
    int days = 0;
   // Date d1 = new GregorianCalendar(Integer.parseInt(date1.substring(0,4)),Integer.parseInt(date1.substring(4,6)),Integer.parseInt(date1.substring(6,8))).getTime();
   // Date d2 = new GregorianCalendar(Integer.parseInt(date2.substring(0,4)),Integer.parseInt(date2.substring(4,6)),Integer.parseInt(date2.substring(6,8))).getTime();
    long diff = date2.getTime()-date1.getTime();
    days = (int)(diff/(1000*60*60*24));
    return days;
  }
  
  public static int getDiffHour(Date date1,Date date2) throws Exception
  {
    int hour = 0;
    //Date d1 = new GregorianCalendar(Integer.parseInt(date1.substring(0,4)),Integer.parseInt(date1.substring(4,6)),Integer.parseInt(date1.substring(6,8))).getTime();
    //Date d2 = new GregorianCalendar(Integer.parseInt(date2.substring(0,4)),Integer.parseInt(date2.substring(4,6)),Integer.parseInt(date2.substring(6,8))).getTime();
    long diff = date2.getTime()-date1.getTime();
    hour = (int)(diff/(1000*60*60));
    return hour;
  }
  
  public static int getDiffMin(Date date1,Date date2) throws Exception
  {
  	 int min = 0;
     //Date d1 = new GregorianCalendar(Integer.parseInt(date1.substring(0,4)),Integer.parseInt(date1.substring(4,6)),Integer.parseInt(date1.substring(6,8))).getTime();
     //Date d2 = new GregorianCalendar(Integer.parseInt(date2.substring(0,4)),Integer.parseInt(date2.substring(4,6)),Integer.parseInt(date2.substring(6,8))).getTime();
     long diff = date2.getTime()-date1.getTime();
     min = (int)(diff/(1000*60));
     return min;
  }
  

  public static String dateAdd(String date,String to) throws Exception
  {
    //日期处理模块 (将日期加上某些天或减去天数)返回字符串
    int strTo;
    try
    {
      strTo = Integer.parseInt(to);
    }
    catch(Exception e)
    {
      log.info("日期标识转换出错! : \n:::"+to+"不能转为数字型");
     log.error("",e);
      strTo = 0;
    }
    Date d = new GregorianCalendar(Integer.parseInt(date.substring(0,4)),Integer.parseInt(date.substring(4,6)),Integer.parseInt(date.substring(6,8))).getTime();
    Calendar strDate = Calendar.getInstance(); //java.util包,设置当前时间
    strDate.setTime(d);
    strDate.add(strDate.DATE,strTo); //日期减 如果不够减会将月变动 //生成 (年-月-日)
    String month = String.valueOf(strDate.get(strDate.MONTH));
    String day = String.valueOf(strDate.get(strDate.DATE));
    if(String.valueOf(strDate.get(strDate.MONTH)).length()==1)
    {
      month = "0"+String.valueOf(strDate.get(strDate.MONTH));
    }
    if(String.valueOf(strDate.get(strDate.DATE)).length()==1)
    {
      day = "0"+String.valueOf(strDate.get(strDate.DATE));
    }

    String meStrDate = strDate.get(strDate.YEAR)+month+day;
    return meStrDate;
  }

  /**
   * 测试过程
   */
  public static void main(String[] args)
  {

//    log.info("class header:"+vssHeader);
    try
    {
      DateBean d = new DateBean();
      log.info("昨天："+d.getYesterdayDate(""));
      log.info(DateBean.dateAdd("20061003","-5"));
//      log.info(getDiff("20060910","20060810"));
//      log.info(d.getQuarter());
    }
    catch(Exception e)
    {
     log.error("",e);
    }
    System.out.println("***"+new DateBean().getCurrentMonthFirstDate(""));
  }

  /**
   * 返回根据传人的字符串时间分割日前
   * @param datetime
   * @param c
   * @return
   */
  public String getYear_Month_Day(String datetime) //获得年月日 2001-01-01
  {
    if(!"".equals(Utility.chgNull(datetime)))
    {
      String yyyy = "0000",mm = "00",dd = "00";
      yyyy = datetime.substring(0,4);
      mm = datetime.substring(4,6);
      dd = datetime.substring(6,8);
      return yyyy+"-"+mm+"-"+dd;
    }
    else
    {
      return "";
    }
  }

  public int getQuarter() //获得季度
  {
    int month=this.getMonth();

    if (month==1 || month==2 || month==3)
      return 1;
    else if (month==4 || month==5 || month==6)
      return 2;
    else if (month==7 || month==8 || month==9)
      return 3;
    else
      return 4;
  }
  public int getYearbyMonth() //获得年度
  {
    int month=this.getMonth();
    if (month<=6)
      return 1;
    else
      return 2;
  }
  
  
  
  /**
   * 返回根据传人的字符串时间分割日前
   * @param datetime
   * @param c
   * @return
   */
  public String getYear_Month_DayOther(String datetime) //获得年月日 2001-01-01
  {
    if(!"".equals(Utility.chgNull(datetime))&&datetime!=null&&!"NULL".equals(datetime))
    {
      String yyyy = "0000",mm = "00",dd = "00";
      yyyy = datetime.substring(0,4);
      mm = datetime.substring(4,6);
      dd = datetime.substring(6,8);
      return yyyy+"-"+mm+"-"+dd;
    }
    else
    {
      return "NULL";
    }
  }
  
  
  public String getAllDatetime(String datetime) //获得 2013-08-30 14:30:30
  {
  	if(!"".equals(Utility.chgNull(datetime))&&datetime!=null&&!"NULL".equals(datetime))
    {
      String yyyy = "0000",mm = "00",dd = "00",hh="00",MM="00",ss="00";
      yyyy = datetime.substring(0,4);
      mm = datetime.substring(4,6);
      dd = datetime.substring(6,8);
      hh = datetime.substring(8, 10);
      MM = datetime.substring(10, 12);
      ss = datetime.substring(12, 14);
      return yyyy+"-"+mm+"-"+dd+" "+hh+":"+MM+":"+ss;
    }
    else
    {
      return "NULL";
    }
  }
  
  /**
   * 返回根据传人的字符串时间分割日前
   * @param datetime
   * @param c
   * @return
   */
  public String getYearMonthDayOtherCN(String datetime) //获得年月日 2001年1月1
  {
    if(!"".equals(Utility.chgNull(datetime))&&datetime!=null&&!"NULL".equals(datetime))
    {
    	String yyyy = "0000",mm = "00",dd = "00";
        yyyy = datetime.substring(0,4);
        
        mm = datetime.substring(4,6);
        if(mm.startsWith("0"))
      	  mm = mm.substring(1);
        
        dd = datetime.substring(6,8);
        if(dd.startsWith("0"))
      	  dd = dd.substring(1);
        
        return yyyy+"年"+mm+"月"+dd+"日";
    }
    else
    {
      return "NULL";
    }
  }  
  
  public String getYear_Month_DayOther_null(String datetime) //获得年月日 2001-01-01
  {
    if(!"".equals(Utility.chgNull(datetime))&&datetime!=null&&!"NULL".equals(datetime))
    {
      String yyyy = "0000",mm = "00",dd = "00";
      yyyy = datetime.substring(0,4);
      mm = datetime.substring(4,6);
      dd = datetime.substring(6,8);
      return yyyy+"-"+mm+"-"+dd;
    }
    else
    {
      return null;
    }
  }
  
  public String getYear_Month_DayNew(String datetime) //获得年月日 2001-01-01
  {
    if(!"".equals(Utility.chgNull(datetime))&&datetime!=null&&!"NULL".equals(datetime))
    {
      String yyyy = "0000",mm = "00",dd = "00";
      yyyy = datetime.substring(0,4);
      mm = datetime.substring(5,7);
      dd = datetime.substring(8,10);
      return yyyy+mm+dd;
    }
    else
    {
      return "NULL";
    }
  }
  /**
   * 获取整个时间值，精确到毫秒
   * @return
   */
  public String getAllToMilliSecond()
  {
	return this.getYearMonthDay()+" "+this.getHourMinuteSecond()+"."+this.getMilliSecond();
  }
  
  /**
   * 获取14位的时间值
   * @return
   */
  public String getChar14Time() //获得年月日 20010101
  {
    return this.getYearMonthDayNull()+this.getHourMinuteSecondNull();
  }
  
  public String gethourMinuteSecond(String datetime) //获得时间12：01：12
  {
    if(!"".equals(Utility.chgNull(datetime))&&datetime!=null&&!"NULL".equals(datetime))
    {
      String hh = "00",mm = "00",ss = "00";
      hh = datetime.substring(0,2);
      mm = datetime.substring(2,4);
      ss = datetime.substring(4,6);
      return hh+":"+mm+":"+ss;
    }
    else
    {
      return "NULL";
    }
  }
  
  public String getDayTime(String datetime){
  	if(!"".equals(Utility.chgNull(datetime))&&datetime!=null&&!"NULL".equals(datetime))
    {
  		if(datetime.trim().length()==8){
  			return  this.getYear_Month_DayOther_null(datetime) ;
  		}else{
		  	String day = datetime.substring(0, 8);
		  	String time = datetime.substring(8, 14);
		  	return  this.getYear_Month_DayOther_null(day) +" "+this.gethourMinuteSecond(time);
	  	}
    }
    else
    {
      return "";
    }
  	
  }
  
  /**
   * 得到上个月的第一天日期
   * @param flag
   * @return
   */
  public String getLastMonthFirstDate(String flag) 
  {
	  String dateFormart = "yyyy"+flag+"MM"+flag+"dd";/*yyyy-MM-dd*/
	  SimpleDateFormat df = new SimpleDateFormat(dateFormart);

	  Calendar cal = Calendar.getInstance();
	  GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
	  Calendar calendar = Calendar.getInstance();
	  calendar.setTime(new Date());

	  calendar.add(Calendar.MONTH, -1);
	  Date theDate = calendar.getTime();
	  gcLast.setTime(theDate);
	  gcLast.set(Calendar.DAY_OF_MONTH, 1);
	  String day_first_prevM = df.format(gcLast.getTime());

	  return day_first_prevM;
  } 
  
  /**
   * 得到当前月的最后一天日期
   * @param flag
   * @return
   */
  public String getLastMonthEndDate(String flag) 
  {
	  String dateFormart = "yyyy"+flag+"MM"+flag+"dd";/*yyyy-MM-dd,yyyyMMdd*/
	  SimpleDateFormat df = new SimpleDateFormat(dateFormart);

	  Calendar cal = Calendar.getInstance();
	  GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
	  Calendar calendar = Calendar.getInstance();
	  calendar.setTime(new Date());
	  calendar.add(Calendar.MONTH, -1);

	  calendar.add(cal.MONTH, 1);
	  calendar.set(cal.DATE, 1);
	  calendar.add(cal.DATE, -1);
	  String day_end_prevM = df.format(calendar.getTime());

	  return day_end_prevM;
  }
  
  /**
   * 得到当前月的第一天日期
   * @param flag
   * @return
   */
  public String getCurrentMonthFirstDate(String flag) 
  {
	  String dateFormart = "yyyy"+flag+"MM"+flag+"dd";/*yyyy-MM-dd*/
	  SimpleDateFormat df = new SimpleDateFormat(dateFormart);

	  Calendar cal = Calendar.getInstance();
	  GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
	  Calendar calendar = Calendar.getInstance();
	  calendar.setTime(new Date());

//	  calendar.add(Calendar.MONTH, -1);
	  calendar.add(Calendar.MONTH,0);
	  Date theDate = calendar.getTime();
	  gcLast.setTime(theDate);
	  gcLast.set(Calendar.DAY_OF_MONTH, 1);
	  String day_first_prevM = df.format(gcLast.getTime());

	  return day_first_prevM;
  } 
  
  /**
   * 得到上个月的最后一天日期
   * @param flag
   * @return
   */
  public String getCurrentMonthEndDate(String flag) 
  {
	  String dateFormart = "yyyy"+flag+"MM"+flag+"dd";/*yyyy-MM-dd,yyyyMMdd*/
	  SimpleDateFormat df = new SimpleDateFormat(dateFormart);

	  Calendar cal = Calendar.getInstance();
	  GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
	  Calendar calendar = Calendar.getInstance();
	  calendar.setTime(new Date());
//	  calendar.add(Calendar.MONTH, -1);
	  calendar.add(Calendar.MONTH, 0);

	  calendar.add(cal.MONTH, 1);
	  calendar.set(cal.DATE, 1);
	  calendar.add(cal.DATE, -1);
	  String day_end_prevM = df.format(calendar.getTime());

	  return day_end_prevM;
  }
  
  /**
   * 得到昨天日期
   * @return
   */
  public String getYesterdayDate(String flag)
  {
	  String dateFormart = "yyyy"+flag+"MM"+flag+"dd";/*yyyy-MM-dd,yyyyMMdd*/
	  SimpleDateFormat df = new SimpleDateFormat(dateFormart);
	  
	  Calendar cal  =  Calendar.getInstance();
	  cal.add(Calendar.DATE,   -1);
	  String yesterday = df.format(cal.getTime());	  
	  return yesterday;
  }
  
  public String getYearFirstDay(String flag)
  {
  	String dateFormart = "yyyy"+flag+"MM"+flag+"dd";
  	SimpleDateFormat df = new SimpleDateFormat(dateFormart);
  	Calendar cal  =  Calendar.getInstance();
  	cal.set(Calendar.MONTH, 0);
  	cal.set(Calendar.DATE, 1);
  	return df.format(cal.getTime());
  }
  
  /**
   * 获取半年前的日期
   * @param flag
   * @return
   */
  public String getHalfYearBeforeDate(String flag)
  {
  	String dateFormart = "yyyy"+flag+"MM"+flag+"dd";
  	SimpleDateFormat df = new SimpleDateFormat(dateFormart);
  	Calendar cal  =  Calendar.getInstance();
  	cal.add(Calendar.MONTH, 0-5);
  	cal.set(Calendar.DATE, 1);
  	return df.format(cal.getTime());
  }
  
  public String getYearMonthByAdd(String flag,int count)
  {
  	String dateFormart = "yyyy"+flag+"MM";
  	SimpleDateFormat df = new SimpleDateFormat(dateFormart);
  	Calendar cal  =  Calendar.getInstance();
  	cal.add(Calendar.MONTH, count);
  	return df.format(cal.getTime());
  }
  
  public String getYear_Month(String datetime)
  {
  	if(null!=datetime && !"".equals(Utility.chgNull(datetime)))
  	{
  		 String yyyy = datetime.substring(0,4);
       String mm = datetime.substring(5,7);
       return yyyy+"/"+mm;
  	}
  	else
  	{
  		return "";
  	}
  }
  
  /**
   * 获取最近半年的日期
   * @return
   */
  public String[] getHalfYearMonth()
  {
  	String[] months = new String[6];
  	int count = 1;
  	for(int i=0;i<months.length;i++)
  	{
  		months[i] = this.getYearMonthByAdd("-",--count);
  	}
  	return months;
  }
  
  
  public String getYearMonthNullOther() //获得年月日 20010101
  {
    String yyyy = "0000",mm = "00";
    yyyy = yyyy+getYear();
    mm = mm+getMonth();
    yyyy = yyyy.substring(yyyy.length()-4);
    mm = mm.substring(mm.length()-2);
    return yyyy+"-"+mm;
  }
  
  public String getYearMonthDay(String datetime) //日期2001-01-01转换20010101
  {
    if(!"".equals(Utility.chgNull(datetime))&&datetime!=null&&!"NULL".equals(datetime))
    {
      String yyyy = "0000",mm = "00",dd = "00";
      yyyy = datetime.substring(0,4);
      mm = datetime.substring(5,7);
      dd = datetime.substring(8,10);
      return yyyy+mm+dd;
    }
    else
    {
      return "NULL";
    }
  }
  
  public String getAllDatetimeNull(String datetime) //日期2013-08-30 14:30:31转换成20130830143030
  {
  	 if(!"".equals(Utility.chgNull(datetime))&&datetime!=null&&!"NULL".equals(datetime))
     {
       String yyyy = "0000",mm = "00",dd = "00",hh="00",MM="00",ss="00";
       yyyy = datetime.substring(0,4);
       mm = datetime.substring(5,7);
       dd = datetime.substring(8,10);
       hh = datetime.substring(11,13);
       MM = datetime.substring(14,16);
       ss = datetime.substring(17,19);
       return yyyy+mm+dd+hh+MM+ss;
     }
     else
     {
       return "NULL";
     }
  }
  
  /**
   * 计算时间增加多少秒之后的值
   * 返回时间格式yyyy-MM-dd HH:mm:ss
   * 
   * @param date 格式yyyy-MM-dd HH:mm:ss
   * @param addSecond
   * @return
   * @throws Exception
   */
  public static String addSecond(String date,int addSecond)
  {
  	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try
    {
			Calendar strDate = Calendar.getInstance(); //java.util包,设置当前时间
	    strDate.setTime(sdf.parse(date));
	    strDate.add(Calendar.SECOND,addSecond); //日期减 如果不够减会将月变动 //生成 (年-月-日)
	    return sdf.format(strDate.getTime());
    }
    catch (ParseException e)
    {
      throw new RuntimeException("时间增加出现异常！",e);
    }
  }
  

	public String getAllToSecond()
  {
		return this.getYearMonthDay()+" "+this.getHourMinuteSecond();
  }
	
	  public String getYear(String datetime) //日期2001-01-01转换20010101
	  {
	    if(!"".equals(Utility.chgNull(datetime))&&datetime!=null&&!"NULL".equals(datetime))
	    {
	      String yyyy = "0000";
	      yyyy = datetime.substring(0,4);
	      return yyyy;
	    }
	    else
	    {
	      return "NULL";
	    }
	  }
	  
	  public String getIOS8601DataTime()
	  {
	  	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	  	Calendar strDate = Calendar.getInstance();
	  	strDate.setTime(new Date());
	  	return sdf.format(strDate.getTime());
	  }
}
