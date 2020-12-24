package com.ts.main.common;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Process;
import android.util.Log;
import com.ts.MainUI.R;
import com.yyw.ts70xhw.Iop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String ACTION_ERROR = "com.forfan.system.error";
    static final String Crash_PATH = "/mnt/sdcard/TsCrash/";
    private static CrashHandler INSTANCE;
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
    private Context mContext;
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CrashHandler();
        }
        return INSTANCE;
    }

    public void init(Context context) {
        Log.i("CrashHandler", "init==" + context);
        this.mContext = context;
        this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public void uncaughtException(Thread thread, Throwable ex) {
        Log.i("CrashHandler", "uncaughtException");
        Iop.PopMuteFast(Iop.GetWorkMode());
        if (handleException(ex) || this.mDefaultHandler == null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Process.killProcess(Process.myPid());
            return;
        }
        this.mDefaultHandler.uncaughtException(thread, ex);
    }

    private void sendAppCrashReport(Context context, String crashReport) {
        if (crashReport != null) {
            String fileName = "crash-" + this.formatter.format(new Date()) + "-" + System.currentTimeMillis() + ".log";
            File dir = new File(Crash_PATH);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            try {
                FileOutputStream fos = new FileOutputStream(Crash_PATH + fileName);
                fos.write(crashReport.toString().getBytes());
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.i("CrashHandler", crashReport);
        }
    }

    private String getErrorStr(Throwable ex) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(this.mContext.getPackageName());
        buffer.append(ShellUtils.COMMAND_LINE_END);
        buffer.append("HMI Version:" + this.mContext.getResources().getString(R.string.custom_num) + MainSet.GetHMIVersion());
        buffer.append(ShellUtils.COMMAND_LINE_END);
        if (ex == null) {
            buffer.append("Throwable == null");
        } else {
            buffer.append(ex.getMessage());
            buffer.append(ShellUtils.COMMAND_LINE_END);
            Writer writer = new StringWriter();
            PrintWriter printWriter = new PrintWriter(writer);
            ex.printStackTrace(printWriter);
            for (Throwable cause = ex.getCause(); cause != null; cause = cause.getCause()) {
                cause.printStackTrace(printWriter);
            }
            buffer.append(writer.toString());
        }
        return buffer.toString();
    }

    private void sendErrCrash(Throwable ex) {
        String errorStr = getErrorStr(ex);
        Intent intent = new Intent();
        intent.setAction(ACTION_ERROR);
        intent.setPackage("com.forfan.xfapp");
        intent.putExtra("error", errorStr);
        this.mContext.sendBroadcast(intent);
        sendAppCrashReport(this.mContext, errorStr);
    }

    public boolean handleException(Throwable ex) {
        if (ex == null || this.mContext == null) {
            return false;
        }
        try {
            sendErrCrash(ex);
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    private String getCrashReport(Context context, Throwable ex) {
        PackageInfo pinfo = getPackageInfo(context);
        StringBuffer exceptionStr = new StringBuffer();
        exceptionStr.append("HMI Version:" + MainVerSion.HMIVER + "\r\n");
        exceptionStr.append("packageName:" + pinfo.packageName + "\r\n");
        exceptionStr.append(" Exception:" + ex.getMessage() + "\r\n");
        StackTraceElement[] elements = ex.getStackTrace();
        for (int i = 0; i < elements.length; i++) {
            exceptionStr.append(String.valueOf(elements[i].toString()) + "\r\n");
        }
        return exceptionStr.toString();
    }

    private PackageInfo getPackageInfo(Context context) {
        PackageInfo info = null;
        try {
            info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace(System.err);
        }
        if (info == null) {
            return new PackageInfo();
        }
        return info;
    }
}
