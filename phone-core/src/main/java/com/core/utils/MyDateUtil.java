package com.core.utils;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Gray on 2018/9/15
 */
public class MyDateUtil {
    private static Logger logs = LogManager.getLogger(MyDateUtil.class);

    public static String simpleDateString(Date date, String simple) {
        SimpleDateFormat format = new SimpleDateFormat(simple);
        String dateStr = format.format(date);
        return dateStr;
    }

    public static Date getPatternToDate(Date date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = format.format(date);
        return format.parse(dateStr);
    }

    /**
     * 获取当天凌晨 2020-11-20 00:00:00
     * @return
     */
    public static long getDayZeroTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }
    public static Date getTodayDate() throws ParseException {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = format1.format(new Date());
        return format2.parse(dateStr + " 00:00:00");
    }
    public static Date getTodayDateEnd() throws ParseException {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = format1.format(new Date());
        return format2.parse(dateStr + " 23:59:59");
    }
    public static Date getYesterdayDate() throws ParseException {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = format1.format(DateUtils.addDays(new Date(),-1));
        return format2.parse(dateStr + " 00:00:00");
    }

    public static Date getYesterdayDateEnd() throws ParseException {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = format1.format(DateUtils.addDays(new Date(),-1));
        return format2.parse(dateStr + " 23:59:59");
    }

    public static Date getDayDateEnd(int day) throws ParseException {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = format1.format(DateUtils.addDays(new Date(),day));
        return format2.parse(dateStr + " 23:59:59");
    }
    public static Date getPatternToDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.parse(date);
    }

    public static Date getToMonthDateStart() throws ParseException {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format2.parse(format1.format(new Date()) + "-01 00:00:00");
    }

    public static Date getToUpMonthDateStart() throws ParseException {
        return DateUtils.addMonths(getToMonthDateStart(), -1);
    }

    public static Date getToLowMonthDateStart() throws ParseException {
        return DateUtils.addMonths(getToMonthDateStart(), 1);
    }

    public static Date getToWeekDateStart() throws ParseException {
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar curStartCal = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = (Calendar) curStartCal.clone();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        String firstDay = df.format(cal.getTime()) + " 00:00:00";
        return format2.parse(firstDay);
    }

    public static Date getToUpWeekDateStart() throws ParseException {
        return DateUtils.addDays(getToWeekDateStart(), -7);
    }

    public static Date getToLowWeekDateStart() throws ParseException {
        return DateUtils.addDays(getToWeekDateStart(), 7);
    }

    public static String getPatternToString(Date date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static String getPattern(Date date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static Date getPatternDate(Date date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(format.format(date));
    }

    public static Date getCurrentDate() throws ParseException {
        return getPatternToDate(new Date());
    }

    public static Date getToUpDayDateStart(int day) throws ParseException {
        return DateUtils.addDays(getCurrentDate(), day);
    }

    /**
     * 取oldDate的时分秒，替换给newDate
     *
     * @param oldDate
     * @param newDate
     * @return
     */
    public static Date replaceDate(Date oldDate, Date newDate) {
        Calendar oldCal = Calendar.getInstance();
        Calendar newCal = Calendar.getInstance();
        oldCal.setTime(oldDate);
        newCal.setTime(newDate);
        oldCal.set(newCal.get(Calendar.YEAR), newCal.get(Calendar.MONTH), newCal.get(Calendar.DATE));
        //newCal.set(oldCal.get(Calendar.HOUR), oldCal.get(Calendar.MINUTE), oldCal.get(Calendar.SECOND));
        return oldCal.getTime();
    }

    public static int computeDay(Date oldDate, Date newDate) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(oldDate);   //设置为另一个时间
        c2.setTime(newDate);   //设置为另一个时间

        long oldDay = c1.getTimeInMillis();
        long newDay = c2.getTimeInMillis();
        return (int) ((newDay - oldDay) / (1000L*3600L*24L));
    }

    /**
     * 根据开始时间和结束时间返回集合，间隔为1天
     */
    public static List<String> getDateList(Date startTiem, Date endTime) throws ParseException {
        List<String> list = new ArrayList<>();
        while (startTiem.getTime() < endTime.getTime()) {
            list.add(getPattern(startTiem));
            startTiem = DateUtils.addDays(startTiem, 1);
        }
        return list;
    }

    public static Date getMonthFrist(Date date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        String dateStr = format.format(c.getTime());
        System.out.println(dateStr+" 00:00:00");
        return format2.parse(dateStr+" 00:00:00");
    }

    public static Date getMonthLast(Date date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        String dateStr = format.format(c.getTime());
        System.out.println(dateStr+" 00:00:00");
        return format2.parse(dateStr+" 00:00:00");
    }

    public static void main(String[] args) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(getMonthFrist(format.parse("2021-02-01")));
        System.out.println(getMonthLast(format.parse("2021-02-01")));
        System.out.println(getTodayDate());
    }
}
