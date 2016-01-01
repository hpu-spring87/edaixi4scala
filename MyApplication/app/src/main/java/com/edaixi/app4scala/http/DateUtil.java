package com.edaixi.app4scala.http;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by weichunsheng on 16/1/1.
 */

public class DateUtil {

    public static String getCouponDate(String dateString) {
        StringBuilder sBuffer = new StringBuilder(dateString);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        if (df.format(cal.getTime()).equals(sBuffer.toString())) {
            System.out.println("Today");
            sBuffer = sBuffer.insert(10, "过期(今天)  ");
        }
        cal.roll(Calendar.DAY_OF_YEAR, 1);
        if (df.format(cal.getTime()).equals(sBuffer.toString())) {
            System.out.println("Tomorrow");
            sBuffer = sBuffer.insert(10, "过期(明天)  ");
        }
        cal.roll(Calendar.DAY_OF_YEAR, 1);
        if (df.format(cal.getTime()).equals(sBuffer.toString())) {
            System.out.println("The day after");
            sBuffer = sBuffer.insert(10, "过期(后天)  ");
        }
        if (!sBuffer.toString().contains("(")) {
            sBuffer = sBuffer.insert(10, "  ");
        }
        String replace = sBuffer.toString().replace("-", ".");
        return replace;
    }

    public static boolean isCloseEnough(long var0, long var2) {
        long var4 = var0 - var2;
        if (var4 < 0L) {
            var4 = -var4;
        }
        return var4 < 300000L;
    }


    /**
     * 获取时间戳
     *
     * @return 毫秒为单位的当前时间
     */
    public static long getTimestamp() {
        return (System.currentTimeMillis() / 1000);
    }
}