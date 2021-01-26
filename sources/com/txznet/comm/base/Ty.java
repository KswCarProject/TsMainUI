package com.txznet.comm.base;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Process;
import android.util.Log;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* compiled from: Proguard */
public class Ty implements Thread.UncaughtExceptionHandler {

    /* renamed from: T  reason: collision with root package name */
    private static Thread.UncaughtExceptionHandler f423T = Thread.getDefaultUncaughtExceptionHandler();
    /* access modifiers changed from: private */
    public static Tr T9;
    private static Ty Tk = new Ty();
    private static T Tn;
    private static Context Tr;
    /* access modifiers changed from: private */
    public static String Ty;

    /* compiled from: Proguard */
    public interface Tr {
        void T();
    }

    /* compiled from: Proguard */
    public static class T {
        public T(String reportDirectory) {
            String unused = Ty.Ty = reportDirectory;
            if (Ty.Ty != null && !Ty.Ty.endsWith("/")) {
                String unused2 = Ty.Ty = Ty.Ty + "/";
            }
        }

        public void T(Thread thread, Throwable ex, PrintWriter pw) {
            if (Ty.T9 != null) {
                Ty.T9.T();
            }
        }
    }

    private Ty() {
    }

    public static void T(Context context, T lisener) {
        Tr = context;
        if (Ty == null) {
            Ty = null;
        }
        Tn = lisener;
        Thread.setDefaultUncaughtExceptionHandler(Tk);
    }

    public static Ty T() {
        return Tk;
    }

    public void uncaughtException(Thread thread, Throwable ex) {
        try {
            StackTraceElement[] statcks = ex.getStackTrace();
            int i = 0;
            while (i < statcks.length) {
                String classname = statcks[i].getClassName();
                if (!"dalvik.system.VMDebug".equals(classname) && !"android.os.Debug".equals(classname)) {
                    i++;
                } else {
                    return;
                }
            }
        } catch (Exception e) {
        }
        T(thread, ex);
        ex.printStackTrace();
        Writer writer = new StringWriter();
        ex.printStackTrace(new PrintWriter(writer));
        Log.e("CrashCommonHandler", writer.toString());
        if (f423T != null) {
            Process.killProcess(Process.myPid());
            System.exit(0);
            return;
        }
        Process.killProcess(Process.myPid());
        System.exit(0);
    }

    public static void T(Thread thread, Throwable ex) {
        T(Tr, Ty, thread, ex);
    }

    public static void T(Context context, String strDir, Thread thread, Throwable ex) {
        if (strDir != null) {
            File dir = new File(strDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            long current = System.currentTimeMillis();
            String time = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.CHINESE).format(new Date(current));
            String timestr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE).format(new Date(current));
            try {
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File(strDir + "crash" + "_" + context.getApplicationInfo().packageName + "_" + time + ".trace"))));
                pw.println(timestr);
                T(pw);
                if (!(thread == null || Tn == null)) {
                    Tn.T(thread, ex, pw);
                }
                pw.println();
                ex.printStackTrace(pw);
                pw.close();
            } catch (Exception e) {
                Log.e("CrashCommonHandler", "dump crash info failed, cause: " + e.getMessage());
            }
        }
    }

    private static void T(PrintWriter pw) throws PackageManager.NameNotFoundException {
        pw.print("App Name: ");
        pw.println(com.txznet.comm.Tr.T.Tr().getPackageName());
        pw.print("App Version: ");
        pw.print(com.txznet.comm.Tn.T.Tr);
        pw.print('_');
        pw.println(com.txznet.comm.Tn.T.f357T);
        pw.println("Compile Version: " + com.txznet.T.T.Ty());
        pw.print("OS Version: ");
        pw.print(Build.VERSION.RELEASE);
        pw.print("_");
        pw.println(Build.VERSION.SDK_INT);
        pw.print("Vendor: ");
        pw.println(Build.MANUFACTURER);
        pw.print("Model: ");
        pw.println(Build.MODEL);
        pw.print("CPU ABI: ");
        pw.println(Build.CPU_ABI);
        pw.print("ProcessName: ");
        pw.println(com.txznet.T.T.Tk());
    }

    public void Tr() {
        Thread.setDefaultUncaughtExceptionHandler(Tk);
    }
}
