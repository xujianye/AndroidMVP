package com.xjy.hyx.mvpretrofitproject.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * description:
 * author：xujianye
 * date: 2016/8/17 0017 17:30
 * email：jianyexu@hyx.com
 */
public class DataUtils {

    /**
     * 复制文本到剪切板
     *
     * @param text    文本
     * @param context 上下文
     */
    public static void copyData(String text, Context context) {
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("text", text);
        clipboardManager.setPrimaryClip(clipData);
    }

    /**
     * 粘贴数据，即将数据从剪切板中读取出来
     *
     * @param context
     * @return
     */
    public static String pasteData(Context context) {
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(context.CLIPBOARD_SERVICE);
        ClipData clipData = clipboardManager.getPrimaryClip();
        return clipData.getItemAt(0).getText().toString();
    }

    /**
     * 获取时间串
     *
     * @param time yyyy-MM-dd HH:mm
     * @return 1月前 1周前 1天前 1小时前 1分钟前
     */
    public static String getTimeStrByLong(String time) {

        long currentTimeMillis = System.currentTimeMillis();
        long oldTimeMills = getTime(time);

        // 给定日期是多少毫秒以前
        long offTime = currentTimeMillis - oldTimeMills;
        long dayLong = 86400000l; // 一天
        long hourLong = 3600000l; // 一小时
        long minLong = 60000l; // 一分钟

        if (offTime >= dayLong * 30) {// 月
            return time;
        }

        if (offTime >= dayLong * 7) {// 周
            int mul = (int) (offTime / (dayLong * 7));
            return mul + "周" + "前";
        }

        if (offTime >= dayLong) {// 天
            int mul = (int) (offTime / dayLong);
            return mul + "天" + "前";
        }

        if (offTime >= hourLong) {// 时
            int mul = (int) (offTime / hourLong);
            return mul + "小时" + "前";
        }

        if (offTime >= minLong) {// 分
            int mul = (int) (offTime / minLong);
            return mul + "分钟" + "前";
        }

        return time;
    }

    /**
     * 日期格式：yyyy-MM-dd HH:mm 转化为毫秒
     *
     * @param time
     * @return
     */
    public static long getTime(String time) {
        if (TextUtils.isEmpty(time)) {
            return 0l;
        }
        long timeMillis = 0l;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = sdf.parse(time);
            timeMillis = date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return timeMillis;
    }
}
