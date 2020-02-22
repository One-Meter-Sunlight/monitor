package com.imooc.monitor.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理
 *
 * @author kai.chen
 */
public class DateUtil {

    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN_YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN_YYYY_MM = "yyyy-MM";

    /**
     * 时间格式(MM)
     */
    public final static String DATE_PATTERN_MM = "MM";

    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_PATTERN_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 时间格式(yyyy/MM/dd HH:mm:ss)
     */
    public final static String DATE_PATTERN_SLASH_YYYY_MM_DD_HH_MM_SS = "yyyy/MM/dd HH:mm:ss";

    /**
     * 时间格式(yyyy-MM-dd HH)
     */
    public final static String DATE_PATTERN_YYYY_MM_DD_HH = "yyyy-MM-dd HH";

    public static String format(Date date) {
        return format(date, DATE_PATTERN_YYYY_MM_DD_HH_MM_SS);
    }

    public static String formatSlash(Date date) {
        return format(date, DATE_PATTERN_SLASH_YYYY_MM_DD_HH_MM_SS);
    }

    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    public static Date addDays(int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, n);
        return calendar.getTime();
    }

    public static Date addMonth(int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, n);
        return calendar.getTime();
    }

    public static Date addMonth(Date date, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, n);
        return calendar.getTime();
    }

    public static Date addHours(Date date, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, n);
        return calendar.getTime();
    }

    /**
     * 两个日期相减得到的小时数
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static int getDiffHours(Date beginDate, Date endDate) {
        if (beginDate == null || endDate == null) {
            throw new IllegalArgumentException("getDiffDays param is null!");
        }
        long diff = (endDate.getTime() - beginDate.getTime()) / (1000 * 60 * 60);
        int hours = new Long(diff).intValue();
        return hours;
    }

    /**
     * 两个日期相减得到的秒数
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static int getDiffSeconds(Date beginDate, Date endDate) {
        if (beginDate == null || endDate == null) {
            throw new IllegalArgumentException("getDiffSeconds param is null!");
        }
        long diff = endDate.getTime() - beginDate.getTime();
        int seconds = new Long(diff).intValue();
        return seconds;
    }

    public static Date passDate(String date, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(date);
    }

}
