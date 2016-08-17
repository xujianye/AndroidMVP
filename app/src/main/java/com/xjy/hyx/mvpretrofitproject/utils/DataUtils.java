package com.xjy.hyx.mvpretrofitproject.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

/**
 * description:
 * author：xujianye
 * date: 2016/8/17 0017 17:30
 * email：jianyexu@hyx.com
 */
public class DataUtils {

    /**
     * 复制文本到剪切板
     * @param text 文本
     * @param context 上下文
     */
    public static void copyData(String text, Context context) {
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(context.CLIPBOARD_SERVICE);
        ClipData myClip;
        myClip = ClipData.newPlainText("text", text);
        clipboardManager.setPrimaryClip(myClip);
    }

    /**
     * 粘贴数据，即将数据从剪切板中读取出来
     * @param context
     * @return
     */
    public static String pasteData(Context context) {
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(context.CLIPBOARD_SERVICE);
        ClipData clipData = clipboardManager.getPrimaryClip();
        return clipData.getItemAt(0).getText().toString();
    }
}
