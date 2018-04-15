package com.bytx.admin.util;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期和时间处理方法
 * Created by ibm on 2017/7/28.
 */
@SuppressWarnings("deprecation")
public class DateTimeUtil
{


    /**
     * 获得当前日期
     *
     * @return
     */
    public static String getCurrentDate()
    {
        return DateFormatUtils.ISO_DATE_FORMAT.format(System.currentTimeMillis());
    }

    public static String getCurrentDate(Long time)
    {
        return DateFormatUtils.ISO_DATE_FORMAT.format(time);
    }

    /**
     * 获得当前日期和时间
     *
     * @return
     */
    public static String getCurrentDateTime()
    {
        return DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss");
    }


    /**
     * 获得当前日期和时间 年月日
     *
     * @return
     */
    public static String getCurrentDateTimeYMD()
    {
        return DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd");
    }


    //根据unix获取年月日
    public static String getDateTimeYMD(String unix)
    {
        Long millions = (long) Integer.valueOf(unix) * 1000;
        return DateFormatUtils.format(millions, "yyyy-MM-dd");
    }

    //根据unix获取年月日
    public static String getDateTimeYMD2(String unix)
    {
        Long timestamp = Long.parseLong(unix) * 1000;
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(timestamp));
        return format;
    }

    //根据unix获取年月日时分秒
    public static String getDateTimeYMDHMS(String unix)
    {
        Long timestamp = Long.parseLong(unix) * 1000;
        return DateFormatUtils.format(timestamp, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获得当前日期的前一天
     *
     * @return
     */
    public static String getDateBeforeCurrentDate()
    {
        Date now = new Date(System.currentTimeMillis());
        Date beforeDay = DateUtils.addDays(now, -1);

        return DateFormatUtils.ISO_DATE_FORMAT.format(beforeDay);
    }

    /**
     * 获得当前日期的后一天
     *
     * @return
     */
    public static String getDateAfterCurrentDate()
    {
        Date now = new Date(System.currentTimeMillis());
        Date afterDay = DateUtils.addDays(now, 1);

        return DateFormatUtils.ISO_DATE_FORMAT.format(afterDay);
    }

    /**
     * 根据时间差，获得分钟数
     *
     * @param diff 时间差：毫秒
     * @return
     */
    public static String getDatePoor(long diff)
    {
        StringBuilder sb = new StringBuilder();
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;

        // 计算差多少小时
        long hour = diff % nd / nh;

        if (0 != hour)
        {
            ;
            sb.append(StringUtils.leftPad("" + hour, 2, '0') + ":");
        }

        // 计算差多少分钟
        long min = diff % nd % nh / nm;

        sb.append(StringUtils.leftPad("" + min, 2, '0') + ":");

        long ss = diff % nd % nh % nm / ns;

        sb.append(StringUtils.leftPad("" + ss, 2, '0'));

        // 计算差多少秒//输出结果
        return sb.toString();
    }

    /**
     * 计算两个日期之间的差
     *
     * @param endDate   结束日期
     * @param startDate 开始日期
     * @return
     */
    public static String getDatePoor(Date endDate, Date startDate)
    {

        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - startDate.getTime();

        return getDatePoor(diff);
    }

    /**
     * 两个日期字符串之间的时间差
     *
     * @param endDate   结束日期
     * @param startDate 开始日期
     * @return
     */
    public static String getDatePoor(String endDate, String startDate)
    {
        String parsePattern = "yyyy-MM-dd HH:mm:ss";
        Date dEndDate = new Date();
        Date dStartDate = dEndDate;

        try
        {
            dEndDate = DateUtils.parseDate(endDate, parsePattern);
            dStartDate = DateUtils.parseDate(startDate, parsePattern);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return getDatePoor(dEndDate, dStartDate);
    }

    /**
     * Method description
     *
     * @param endDate
     * @param startDate
     * @return
     */
    public static Integer getDatePoorDiff(String endDate, String startDate)
    {
        String parsePattern = "yyyy-MM-dd HH:mm:ss";
        Date dEndDate = new Date();
        Date dStartDate = dEndDate;

        try
        {
            dEndDate = DateUtils.parseDate(endDate, parsePattern);
            dStartDate = DateUtils.parseDate(startDate, parsePattern);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        long diff = dEndDate.getTime() - dStartDate.getTime();

        return Long.valueOf(diff).intValue();
    }

    /**
     * 将毫秒转换为日期时间格式
     *
     * @param mills 毫秒
     * @return 日期格式
     */
    public static String getDateTimeByMillis(Long mills)
    {
        return DateFormatUtils.format(mills, "yyyy-MM-dd HH:mm:ss");
    }


    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) throws ParseException
    {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s)
    {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 获取自2017-07-01后的所有月份
     *
     * @return
     */
    public static List<HashMap<String, String>> getYearMoth()
    {
        ArrayList<HashMap<String, String>> listMap = new ArrayList<>();
        try
        {
            Date startDate = DateUtils.parseDate("2017-07-01", "yyyy-MM-dd");
            while (startDate.getTime() < System.currentTimeMillis())
            {
                HashMap<String, String> mapYearMoth = new HashMap<>();
                String yearDate = DateFormatUtils.format(startDate, "yyyyMM");
                mapYearMoth.put("index", yearDate + "01");
                mapYearMoth.put("value", yearDate);
                startDate = DateUtils.addMonths(startDate, 1);
                listMap.add(mapYearMoth);
            }
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return listMap;

    }

    /**
     * 将当前日期yyyy-MM-dd转换成秒 Integer
     * 一般用于开始时间
     */
    public static Integer getIStartDate(String startDate) throws ParseException
    {
        if (startDate == null)
        {
            return null;
        }
        Date tempDate = null;
        tempDate = DateUtils.parseDate(startDate, "yyyy-MM-dd");
        return Integer.valueOf(String.valueOf(tempDate.getTime() / 1000));
    }

    /**
     * 将当前日期yyyy-MM-dd 加一天转换成秒 Integer
     * 一般用于结束时间
     */
    public static Integer getIEndDate(String endDate) throws ParseException
    {
        if (endDate == null)
        {
            return null;
        }
        Date tempDate = null;
        tempDate = DateUtils.parseDate(endDate, "yyyy-MM-dd");
        tempDate = DateUtils.addDays(tempDate, 1);
        return Integer.valueOf(String.valueOf(tempDate.getTime() / 1000));
    }


    /**
     * 获取unix十位时间戳
     *
     * @return
     */
    public static Integer getUnixTime()
    {
        Long l = System.currentTimeMillis() / 1000;
        return l.intValue();
    }

    /**
     * 获取unix十位时间戳
     *
     * @return
     */
    public static Integer getYMDString()
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String format = df.format(new Date());
        String ymd = getUnixAddDaysByYmd(format);
        Integer time1 = Integer.parseInt(ymd);
        Long time2 = time1.longValue() * 1000;
        Date parse = new Date(time2);
        Long time = (parse.getTime()) / 1000 - 1;
        return time.intValue();
    }

    /**
     * 获取今年加以后三年
     *
     * @return
     */
    public static List<String> getNextThreeYear()
    {
        List<String> list = Lists.newArrayList();

        Calendar date = Calendar.getInstance();

        for (int i = 0; i < 4; i++)
        {
            String year = String.valueOf(date.get(Calendar.YEAR) + i);
            list.add(year);
        }
        return list;
    }

    /**
     * 获取今年加以前以后三年共6年
     *
     * @return
     */
    public static List<String> getAroundThreeYear()
    {
        List<String> list = Lists.newArrayList();

        Calendar date = Calendar.getInstance();

        for (int i = -3; i < 4; i++)
        {
            String year = String.valueOf(date.get(Calendar.YEAR) + i);
            list.add(year);
        }
        return list;
    }

    /*
     * 获取当前时间对应的数据库时间戳
     * */
    public static Integer getTimeAsDb()
    {
        Date date = new Date();
        long ts = date.getTime();
        String inputStr = String.valueOf(ts);
        String inputStr2 = inputStr.substring(0, inputStr.length() - 3);
        return Integer.valueOf(inputStr2);
    }

    //比较两个时间的时间差 如果data1晚于date2返回 -1 早于返回1 相等返回0
    public static int compare_date(String DATE1, String DATE2)
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try
        {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime())
            {
                System.out.println("dt1 在dt2前");
                return 1;
            }
            else if (dt1.getTime() < dt2.getTime())
            {
                System.out.println("dt1在dt2后");
                return -1;
            }
            else
            {
                return 0;
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        return 0;
    }

    /*获取时间差 格式 yyyy-MM-dd HH:mm ~ HH:mm*/
    public static String getTimeDifferent(String date1Str, String date2Str) throws ParseException
    {
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        DateFormat df3 = new SimpleDateFormat("HH:mm");
        Date date1 = df1.parse(date1Str);
        Date date2 = df1.parse(date2Str);
        return df2.format(date1) + "~" + df3.format(date2);
    }

    public static Long getEndTimeUnixTime(String date1Str) throws ParseException
    {
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm-HH:mm");
        Date date1 = df1.parse(date1Str);
        return date1.getTime() / 1000;
    }

    public static String getStartTimeUnixTime(String date1Str) throws ParseException
    {
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm-");
        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date1 = df1.parse(date1Str);
        return df2.format(date1);
    }

    public static Long getStartUnixTime(String date1Str) throws ParseException
    {
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm-");
        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date1 = df1.parse(date1Str);
        return date1.getTime() / 1000;
    }

    public static Integer getUnixTimeByHM(String date1Str) throws ParseException
    {
        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date1 = df2.parse(date1Str);
        Long time = date1.getTime() / 1000;
        return time.intValue();
    }

    public static String getChannelTimeByUnixAndStr(String time1, Integer time2)
    {
        DateFormat df2 = new SimpleDateFormat("HH:mm");
        Date date = new Date(time2.longValue() * 1000);
        return time1 + "~" + df2.format(date);
    }

    /**
     * 获取两个时间戳之间相差的天数
     *
     * @param startTime 开始时间戳
     * @param endTime   结束时间戳
     */
    public static Integer getTimeMillisPoor(Long startTime, Long endTime)
    {
        Long day = ((startTime - endTime) / 1000 / 60 / 60 / 24);
        return day.intValue();
    }

    /**
     * 获得当前日期的前N天
     *
     * @return
     */
    public static String getDateBeforeDaysCurrentDate(int days)
    {
        Date now = new Date(System.currentTimeMillis());
        Date beforeDay = DateUtils.addDays(now, -days);
        return DateFormatUtils.ISO_DATE_FORMAT.format(beforeDay);
    }

    /**
     * 获得当前日期的前N天 unix时间戳
     *
     * @return
     */
    public static Integer getDateBeforeDaysCurrentDateUnix(int days)
    {
        Date now = new Date(System.currentTimeMillis());
        Date beforeDay = DateUtils.addDays(now, -days);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String format = df.format(beforeDay);
        try
        {
            beforeDay = df.parse(format);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        Long unix = beforeDay.getTime() / 1000;
        return unix.intValue();
    }

    /**
     * 获得当前日期的前N天 unix时间戳
     *
     * @return
     */
    public static Integer getDateBeforeDayCurrentDateUnix(int days)
    {
        String ymd = DateTimeUtil.getCurrentDateTimeYMD();
        String unixStamp = DateTimeUtil.getUnixStrByYmd(ymd);
        Date now = new Date(Long.valueOf(unixStamp + "000"));
        Date beforeDay = DateUtils.addDays(now, -days);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String format = df.format(beforeDay);
        try
        {
            beforeDay = df.parse(format);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        Long unix = beforeDay.getTime() / 1000;
        return unix.intValue();
    }

    /**
     * 获得增加分钟后的时间信息，格式为2017-10-30 19:00
     *
     * @param addMinutes
     * @return
     */
    public static String getDateOfAddCurrentTimeMins(int addMinutes)
    {
        Date currentDate = new Date(System.currentTimeMillis());
        Date addMinutesDate = DateUtils.addMinutes(currentDate, addMinutes);
        return DateFormatUtils.format(addMinutesDate, "yyyy-MM-dd HH:mm");
    }

    /**
     * Unix时间戳转 yyyy-MM-dd HH:mm
     */
    public static String unixDateFoment(String unixDate)
    {
        Long time = Long.valueOf(unixDate + "000");
        return DateFormatUtils.format(time, "yyyy-MM-dd HH:mm");
    }

    //根据yyyy-MM-dd HH:mm获取 时间戳
    public static String getUnixStrByYMD(String str)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try
        {
            date = df.parse(str);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        Long unixTime = date.getTime() / 1000;
        return unixTime.toString();
    }

    public static String getUnixStrByYmd(String str)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try
        {
            date = df.parse(str);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        Long unixTime = date.getTime() / 1000;
        return unixTime.toString();
    }

    public static String getUnixStrByYmd2(String str)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try
        {
            date = df.parse(str);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        Long unixTime = date.getTime() / 1000;
        return unixTime.toString();
    }


    /**
     * 获得增加/减少 分钟后的时间戳
     *
     * @param addMinutes
     * @return
     */
    public static Integer getUnixOfAddCurrentTimeMins(int addMinutes)
    {
        return getUnixTime() + addMinutes * 60;
    }

    /**
     * 根据当前日期加上n天之后的时间
     *
     * @return : java.lang.Integer
     * @author:hushihai
     * @date:11:17 2017/11/18
     * @params:[addTime, courseMonthly]
     */
    public static Integer getUnixAddDays(Date addTime, Short courseMonthly)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(addTime);
        calendar.add(Calendar.DATE, courseMonthly);
        Long timeInMillis = calendar.getTimeInMillis();
        Long unixTime = timeInMillis / 1000;
        return unixTime.intValue();
    }

    //某个日期的加一天
    public static String getUnixAddDaysByYmd(String endTime)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try
        {
            date = df.parse(endTime);
            date = DateUtils.addDays(date, 1);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        Long time = date.getTime() / 1000;
        return time.intValue() + "";
    }


    //日期yyyy-MM-dd减一天
    public static String getUnixMinusDaysByYmd(String endTime)
    {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try
        {
            date = df.parse(endTime);
            date = DateUtils.addDays(date, -1);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        Long time = date.getTime() / 1000;
        return time.intValue() + "";
    }

    //获取年份列表
    public static List<Integer> getYears()
    {
        List<Integer> years = new ArrayList<>(0);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Integer curYear = calendar.get(Calendar.YEAR);
        for (int i = curYear + 2; i >= 2015; i--)
        {
            years.add(i);
        }
        return years;
    }

    /**
     * @param
     * @return
     * @description 获取过去n个月份 yyyy-MM
     * @author lishibin
     * @date 2018/1/24 11:03
     */
    public static List<String> getPastMonth(int n)
    {
        List<String> months = new ArrayList<>();
        Date currentDate = new Date(System.currentTimeMillis());
        int i = 1;
        while (i <= n)
        {
            String month = DateFormatUtils.format(currentDate, "yyyy-MM");
            months.add(month);
            currentDate = DateUtils.addMonths(currentDate, -1);
            i++;
        }
        return months;
    }

    /**
     * @param
     * @return java.util.List<java.lang.String>
     * @description 获取某月yyyy-MM的日期集合 yyyy-MM-dd
     * @author lishibin
     * @date 2018/1/24 13:47
     */
    public static List<String> getDaysInMonth(String month) throws ParseException
    {
        String startDay = month + "-01";
        Date date = DateUtils.parseDate(startDay, "yyyy-MM-dd");
        List<String> dayList = getAllDaysMonthByDate(date);
        return dayList;
    }

    /**
     * @Author: lishibin
     * @Description: 根据传入的日期获取所在月份所有日期（当前日期之前的）
     * @Date: Created in 2018/1/24 14:22
     */
    public static List<String> getAllDaysMonthByDate(Date d)//根据传入的日期获取所在月份所有日期
    {
        List<String> lst = new ArrayList();
        Date date = getMonthStart(d);
        Date monthEnd = getMonthEnd(d);
        Date currentDate = new Date(System.currentTimeMillis());
        while ((!date.after(monthEnd)) && (!date.after(currentDate)))
        {
            //System.out.println(sdf.format(date));
            lst.add(new SimpleDateFormat("yyyy-MM-dd").format(date));
            date = getNext(date);
        }
        return lst;
    }

    /**
     * @Author: lishibin
     * @Description: 获取日期所在月份的月初
     * @Date: Created in 2018/1/24 14:22
     */
    private static Date getMonthStart(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int index = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE, (1 - index));
        return calendar.getTime();
    }

    /**
     * @Author: lishibin
     * @Description: 获取日期所在月份的月末
     * @Date: Created in 2018/1/24 14:24
     */
    private static Date getMonthEnd(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        int index = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE, (-index));
        return calendar.getTime();
    }

    /**
     * @Author: lishibin
     * @Description: 获取下一天
     * @Date: Created in 2018/1/24 14:25
     */
    private static Date getNext(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    //unix时间戳转DAte
    public static Date unixToDate(Integer unix)
    {
        String time = unix + "000";
        return new Date(Long.valueOf(time));
    }

    /**
     * 获取两个日期间的所有日期
     * 参数格式:startDate yyyy-MM-dd
     * endDate yyyy-MM-dd
     *
     * @return : java.util.List<java.lang.String>
     * @author:hushihai
     * @date:9:54 2018/3/7
     * @params:[]
     */
    public static List<String> getDates(String startDate, String endDate) throws ParseException
    {
        Date d1 = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);//定义起始日期
        Date d2 = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);//定义结束日期
        Calendar dd = Calendar.getInstance();//定义日期实例
        dd.setTime(d1);//设置日期起始时间
        ArrayList<String> dates = new ArrayList<String>();
        while (dd.getTime().compareTo(d2) <= 0)
        {//判断是否到结束日期
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String str = sdf.format(dd.getTime());
            dates.add(str);
            dd.add(Calendar.DAY_OF_MONTH, 1);//进行当前日期月份加1
        }
        return dates;
    }

    /**
     * 获得当前年份
     *
     * @return
     */
    public static String getSysYear()
    {
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        return year;
    }

    /**
     * 根据传入日期获取年份 参数格式 : 'yyyy-MM-dd'
     *
     * @return : java.lang.Integer
     * @author:hushihai
     * @date:15:48 2018/3/13
     * @params:[date]
     */
    public static Integer getYearByYMD(String date) throws ParseException
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(date));
        Integer year = calendar.get(Calendar.YEAR);
        return year;
    }

    /**
     * @param
     * @return
     * @description 根据年周获取一周中的所有日期
     * @author huangjiqing
     * @date 2018/3/13 19:18
     */
    public static List<String> getDatesByYearAndWeek(Integer year, Integer week)
    {
        ArrayList<String> dates = new ArrayList<String>();
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.WEEK_OF_YEAR, week);
        calendar.set(Calendar.YEAR, year);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 1; i < 7; i++)
        {
            calendar.add(Calendar.DATE, -1);
            int cweek = calendar.get(Calendar.WEEK_OF_YEAR);
            if (cweek != week)
            {
                break;
            }

        }

        for (int i = 1; i <= 7; i++)
        {
            calendar.add(Calendar.DATE, 1);
            Date date = calendar.getTime();
            dates.add(sdf.format(date));
        }

        return dates;
    }

    /**
     * @param
     * @return
     * @description 根据年月获取当月中的所有日期
     * @author huangjiqing
     * @date 2018/3/13 19:18
     */
    public static List<String> getDatesByYearAndMonth(Integer year, Integer month)
    {
        ArrayList<String> dates = new ArrayList<String>();
        Calendar calendar = Calendar.getInstance();

        calendar.set(year, month - 1, 1);
        calendar.add(Calendar.DATE, -1);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 1; i < 32; i++)
        {
            calendar.add(Calendar.DATE, 1);
            int cmonth = calendar.get(Calendar.MONTH) + 1;
            if (cmonth != month)
            {
                break;
            }
            Date date = calendar.getTime();
            dates.add(sdf.format(date));
        }
        return dates;
    }

    public static String getDateBeforeNowYmd(Integer dayCount)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, dayCount);
        date = calendar.getTime();
        return sdf.format(date);
    }

    public static String getYmdByYmdHs(String date) throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = sdf.parse(date);
        return sdf2.format(parse);
    }

    public static String dateToString(Date date)
    {
        return DateFormatUtils.format(date,"yyyy-MM-dd HH:mm:ss");
    }

}