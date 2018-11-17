package com.example;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Util {

    public static String formatNow() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(System.currentTimeMillis());
    }

    public static Date after(int seconds) {
        Date date = new Date();
        date.setTime(date.getTime() + seconds * 1000);
        return date;
    }

    public static String formatDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date.getTime());
    }
}
