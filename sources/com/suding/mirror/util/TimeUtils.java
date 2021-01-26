package com.suding.mirror.util;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

@SuppressLint({"SimpleDateFormat"})
public class TimeUtils {
    private static final ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
        /* access modifiers changed from: protected */
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        }
    };

    public static boolean isToday(long sdate) {
        try {
            Date time = new Date(sdate);
            Date today = new Date();
            if (time == null || !dateFormater.get().format(today).equals(dateFormater.get().format(time))) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isToday(String time) {
        if (time != null) {
            if (dateFormater.get().format(new Date()).equals(time.substring(0, 10).trim())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isYesterday(String time) {
        if (time != null) {
            String date = time.substring(0, 10).trim();
            GregorianCalendar currentDate = new GregorianCalendar();
            currentDate.add(5, -1);
            if (dateFormater.get().format(currentDate.getTime()).equals(date)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isThisYear(String time) {
        if (time != null) {
            if (dateFormater.get().format(new Date()).substring(0, 4).equals(time.substring(0, 4).trim())) {
                return true;
            }
        }
        return false;
    }

    public static String getTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
    }

    public static String timeStamp2DateString(long timeStamp) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timeStamp));
    }
}
