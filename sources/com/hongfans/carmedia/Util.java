package com.hongfans.carmedia;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Toast;

public class Util {
    private static final String TAG = "carmediadebug";
    private static Toast sToast;

    public static String GetAppID(Context context) {
        return getInfo(context, "appkey");
    }

    public static String GetSecret(Context context) {
        return getInfo(context, "appsecret");
    }

    private static String getInfo(Context context, String info) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString(info);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            print("发生异常 info " + info);
            return "";
        }
    }

    public static void show(Context ctx, String msg) {
        if (sToast == null) {
            sToast = Toast.makeText(ctx, "", 0);
        }
        sToast.setText(msg);
        sToast.show();
    }

    public static void print(String msg) {
        if (MediaAPI.getIsDebug()) {
            try {
                StackTraceElement[] list = Thread.currentThread().getStackTrace();
                StringBuilder sb = new StringBuilder();
                sb.append("carmedia_debug:" + list[3].getFileName() + "__" + list[3].getMethodName() + "__" + list[3].getLineNumber() + "__");
                sb.append(list[4].getFileName() + "__" + list[4].getMethodName() + "__" + list[4].getLineNumber() + "__");
                sb.append(list[5].getFileName() + "__" + list[5].getMethodName() + "__" + list[5].getLineNumber());
                sb.append(" " + msg);
                Log.w(TAG, sb.toString());
            } catch (Exception e) {
                Log.w(TAG, e.toString());
            }
        }
    }

    public static void printE(String msg) {
        if (MediaAPI.getIsDebug()) {
            try {
                StackTraceElement[] list = Thread.currentThread().getStackTrace();
                StringBuilder sb = new StringBuilder();
                sb.append("carmedia_debug:" + list[3].getFileName() + "__" + list[3].getMethodName() + "__" + list[3].getLineNumber() + "__");
                sb.append(list[4].getFileName() + "__" + list[4].getMethodName() + "__" + list[4].getLineNumber() + "__");
                sb.append(list[5].getFileName() + "__" + list[5].getMethodName() + "__" + list[5].getLineNumber());
                sb.append(msg);
                Log.e(TAG, sb.toString());
            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
        }
    }

    public static void print(String msg, boolean isprintln) {
        if (isprintln) {
            try {
                StackTraceElement[] list = Thread.currentThread().getStackTrace();
                StringBuilder sb = new StringBuilder();
                sb.append("carmedia_debug:" + list[3].getFileName() + "__" + list[3].getMethodName() + "__" + list[3].getLineNumber() + "__");
                sb.append(list[4].getFileName() + "__" + list[4].getMethodName() + "__" + list[4].getLineNumber() + "__");
                sb.append(list[5].getFileName() + "__" + list[5].getMethodName() + "__" + list[5].getLineNumber());
                sb.append(msg);
                Log.w(TAG, sb.toString());
            } catch (Exception e) {
                Log.w(TAG, e.toString());
            }
        }
    }
}
