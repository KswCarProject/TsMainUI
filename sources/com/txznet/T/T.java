package com.txznet.T;

import android.app.ActivityManager;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.os.SystemClock;
import com.android.SdkConstants;
import com.txznet.comm.Tr.Tr.Tn;
import com.txznet.sdk.TXZResourceManager;
import com.txznet.txz.T.Ty;
import com.txznet.txz.util.T5;
import java.util.HashSet;
import java.util.Set;

/* compiled from: Proguard */
public class T {

    /* renamed from: T  reason: collision with root package name */
    protected static T f353T;
    private static HashSet<BroadcastReceiver> T5 = new HashSet<>();
    protected static T5 T9;
    public static long TE = SystemClock.elapsedRealtime();
    static Boolean TZ = null;
    protected static Handler Tk = new Handler(Looper.getMainLooper());
    protected static T5 Tn;
    protected static Application Tr;
    public static Long Ty = null;

    public static Application T() {
        return Tr;
    }

    public static T Tr() {
        return f353T;
    }

    public Object T(String path, String className, byte[] data) {
        return Ty.T(path, className, data);
    }

    public static String Ty() {
        if (f353T == null) {
            return Tn();
        }
        return f353T.T9();
    }

    public static String Tn() {
        return "20180731201905_USER-PC-FENGLJ_53518";
    }

    public String T9() {
        return Tn();
    }

    public static void T(Runnable r) {
        T(r, 0);
    }

    public static void Tr(Runnable r) {
        Ty(r, 0);
    }

    public static void T(Runnable r, long delay) {
        if (delay > 0) {
            Tn.T(r, delay);
        } else {
            Tn.T(r);
        }
    }

    public static void Ty(Runnable r) {
        Tn.Tr(r);
    }

    public static void Tr(Runnable r, long delay) {
        if (delay > 0) {
            T9.T(r, delay);
        } else {
            T9.T(r);
        }
    }

    public static void Ty(Runnable r, long delay) {
        if (delay > 0) {
            Tk.postDelayed(r, delay);
        } else {
            Tk.post(r);
        }
    }

    public static void Tn(Runnable r) {
        Tk.removeCallbacks(r);
    }

    public static String Tk() {
        int pid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo processInfo : ((ActivityManager) com.txznet.comm.Tr.T.Tr().getSystemService(SdkConstants.TAG_ACTIVITY)).getRunningAppProcesses()) {
            if (processInfo.pid == pid) {
                return processInfo.processName;
            }
        }
        return TXZResourceManager.STYLE_DEFAULT;
    }

    public static boolean TZ() {
        if (TZ == null) {
            TZ = Boolean.valueOf(Tk().equals(com.txznet.comm.Tr.T.Tr().getApplicationInfo().packageName));
        }
        return TZ.booleanValue();
    }

    public static void TE() {
        Tn.T("app restart");
        T5();
        T6();
        Process.killProcess(Process.myPid());
    }

    public static void T5() {
        if (TZ()) {
            try {
                Tn.T("clear start time record");
                SharedPreferences mSharedPreferences = com.txznet.comm.Tr.T.Tr().getSharedPreferences(com.txznet.comm.Tr.T.Tr().getApplicationInfo().packageName + ".ApkLoader", 0);
                if (mSharedPreferences.getString("launchTimes", (String) null) != null) {
                    SharedPreferences.Editor editor = mSharedPreferences.edit();
                    editor.remove("launchTimes");
                    editor.commit();
                }
            } catch (Exception e) {
            }
        }
    }

    public static void Tv() {
        Tn.T("app exit");
        T5();
        T6();
        System.exit(0);
    }

    public static void T(int type, Set<String> files) {
        synchronized (T.class) {
            try {
                SharedPreferences mSharedPreferences = com.txznet.comm.Tr.T.Tr().getSharedPreferences(com.txznet.comm.Tr.T.Tr().getApplicationInfo().packageName + ".ApkLoader", 0);
                int fileSize = mSharedPreferences.getInt("fileSize", 0);
                SharedPreferences.Editor editor = mSharedPreferences.edit();
                editor.putInt("fileSize", fileSize + 1);
                editor.putInt("fileType" + (fileSize + 1), type);
                editor.putStringSet("fileList" + (fileSize + 1), files);
                Tn.T("Reset:input size=" + (fileSize + 1) + ",files=" + files.size());
                editor.commit();
            } catch (Exception e) {
            }
        }
    }

    public static boolean T(BroadcastReceiver receiver) {
        boolean add;
        synchronized (T5) {
            add = T5.add(receiver);
        }
        return add;
    }

    public static boolean Tr(BroadcastReceiver receiver) {
        boolean remove;
        synchronized (T5) {
            remove = T5.remove(receiver);
        }
        return remove;
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x001d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void T6() {
        /*
            java.lang.String r3 = "Receiver:clear receivers"
            com.txznet.comm.Tr.Tr.Tn.T((java.lang.String) r3)
            android.app.Application r3 = Tr
            if (r3 != 0) goto L_0x0011
            java.lang.String r3 = "Receiver:Application == null"
            com.txznet.comm.Tr.Tr.Tn.T((java.lang.String) r3)
        L_0x0010:
            return
        L_0x0011:
            java.util.HashSet<android.content.BroadcastReceiver> r4 = T5
            monitor-enter(r4)
            r2 = 0
        L_0x0015:
            java.util.HashSet<android.content.BroadcastReceiver> r3 = T5     // Catch:{ all -> 0x003f }
            int r3 = r3.size()     // Catch:{ all -> 0x003f }
            if (r3 <= 0) goto L_0x003d
            java.util.HashSet<android.content.BroadcastReceiver> r3 = T5     // Catch:{ all -> 0x003f }
            java.util.HashSet<android.content.BroadcastReceiver> r5 = T5     // Catch:{ all -> 0x003f }
            int r5 = r5.size()     // Catch:{ all -> 0x003f }
            android.content.BroadcastReceiver[] r5 = new android.content.BroadcastReceiver[r5]     // Catch:{ all -> 0x003f }
            java.lang.Object[] r3 = r3.toArray(r5)     // Catch:{ all -> 0x003f }
            r0 = r3
            android.content.BroadcastReceiver[] r0 = (android.content.BroadcastReceiver[]) r0     // Catch:{ all -> 0x003f }
            r2 = r0
            int r5 = r2.length     // Catch:{ all -> 0x003f }
            r3 = 0
        L_0x0031:
            if (r3 >= r5) goto L_0x0015
            r1 = r2[r3]     // Catch:{ all -> 0x003f }
            android.app.Application r6 = Tr     // Catch:{ all -> 0x003f }
            r6.unregisterReceiver(r1)     // Catch:{ all -> 0x003f }
            int r3 = r3 + 1
            goto L_0x0031
        L_0x003d:
            monitor-exit(r4)     // Catch:{ all -> 0x003f }
            goto L_0x0010
        L_0x003f:
            r3 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x003f }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.txznet.T.T.T6():void");
    }

    public void Th() {
    }
}
