package com.txznet.comm.Tr.Tr;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Process;
import android.util.Log;
import com.txznet.comm.Tr.Tn;
import com.txznet.comm.Ty.Tr;
import com.txznet.txz.T.Tn;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.Arrays;

/* compiled from: Proguard */
public class Tn {

    /* renamed from: T  reason: collision with root package name */
    static StackTraceElement f402T = null;
    private static Class<?> T9 = null;
    private static int TE = 0;
    private static T TZ = new T();
    private static Method Tk = null;
    static int Tn = 3;
    static boolean Tr = Tn.class.getName().equals("com.txznet.comm.remote.util.LogUtil");
    static int Ty = 3;

    private Tn() {
    }

    static {
        if (!com.txznet.T.T.TZ()) {
            com.txznet.comm.Tr.T.Tr().registerReceiver(new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    Tn.Ty = intent.getIntExtra("console", Tn.Ty);
                    Tn.Tn = intent.getIntExtra("file", Tn.Tn);
                }
            }, new IntentFilter(com.txznet.comm.Tr.T.Tr().getPackageName() + ".LogUtil.level.notify"));
            com.txznet.comm.Tr.T.Tr().sendBroadcast(new Intent(com.txznet.comm.Tr.T.Tr().getPackageName() + ".LogUtil.level.query"));
        } else {
            com.txznet.comm.Tr.T.Tr().registerReceiver(new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    Tn.T();
                }
            }, new IntentFilter(com.txznet.comm.Tr.T.Tr().getPackageName() + ".LogUtil.level.query"));
        }
    }

    static int T(int level, String content) {
        String tag = "TXZ";
        try {
            if (Tr) {
                if (f402T == null) {
                    Ty(2);
                }
                tag = f402T.getClassName();
                content = ("[" + tag + "::" + f402T.getMethodName() + "]") + content + ("[" + f402T.getFileName() + ":" + f402T.getLineNumber() + "]");
            }
            T(level, tag, content);
            return 0;
        } catch (Exception e) {
            return 0;
        } finally {
            f402T = null;
        }
    }

    static void T(int level, String tag, String content) {
        if (!com.txznet.comm.Tr.T.T() || !com.txznet.T.T.TZ()) {
            if (level >= Ty) {
                Log.println(level, tag, content);
            }
            if (level >= Tn) {
                try {
                    com.txznet.comm.Tr.T.Tr();
                    if (content.length() > 900) {
                        content = content.substring(0, 300) + "\n............too many logs...........\n" + content.substring(content.length() - 300);
                    }
                    if (com.txznet.comm.Tr.T.T.T().Ty()) {
                        String data = new Tr().T("pid", (Object) Integer.valueOf(Process.myPid())).T("tid", (Object) Integer.valueOf(Process.myTid())).T("level", (Object) Integer.valueOf(level)).T("tag", (Object) tag).T("content", (Object) content).T("seq", (Object) Integer.valueOf(TE)).T("package", (Object) com.txznet.comm.Tr.T.Tr().getPackageName()).toString();
                        TE = Ty();
                        com.txznet.comm.Tr.T.T.T().T(10, data.getBytes());
                        return;
                    }
                    com.txznet.comm.Tr.Tn.Tr().T("com.txznet.txz", "comm.log", new Tr().T("pid", (Object) Integer.valueOf(Process.myPid())).T("tid", (Object) Integer.valueOf(Process.myTid())).T("level", (Object) Integer.valueOf(level)).T("tag", (Object) tag).T("content", (Object) content).toString().getBytes(), (Tn.Tr) null, 3000);
                } catch (IllegalStateException e) {
                }
            }
        } else {
            try {
                if (Tk == null || T9 == null) {
                    T9 = Class.forName("com.txznet.txz.jni.JNIHelper");
                    Tk = T9.getMethod("_logRaw", new Class[]{String.class, Integer.TYPE, String.class, String.class});
                }
                Tk.invoke(T9, new Object[]{"", Integer.valueOf(level), tag, content});
            } catch (Exception e2) {
            }
        }
    }

    public static void T() {
        if (com.txznet.T.T.TZ()) {
            Intent intent = new Intent(com.txznet.comm.Tr.T.Tr().getPackageName() + ".LogUtil.level.notify");
            intent.putExtra("console", Ty);
            intent.putExtra("file", Tn);
            com.txznet.comm.Tr.T.Tr().sendBroadcast(intent);
        }
    }

    public static void T(int level) {
        Ty = level;
        if (com.txznet.comm.Tr.T.T()) {
            try {
                Class<?> cls = Class.forName("com.txznet.txz.jni.JNIHelper");
                cls.getMethod("setConsoleLogLevel", new Class[]{Integer.TYPE}).invoke(cls, new Object[]{Integer.valueOf(level)});
            } catch (Exception e) {
            }
        }
        T();
    }

    public static void Tr(int level) {
        Tn = level;
        if (com.txznet.comm.Tr.T.T()) {
            try {
                Class<?> cls = Class.forName("com.txznet.txz.jni.JNIHelper");
                cls.getMethod("setFileLogLevel", new Class[]{Integer.TYPE}).invoke(cls, new Object[]{Integer.valueOf(level)});
            } catch (Exception e) {
            }
        }
        T();
    }

    public static int T(String content) {
        return T(3, content);
    }

    public static int Tr(String content) {
        return T(4, content);
    }

    public static int Ty(String content) {
        return T(5, content);
    }

    public static int Tn(String content) {
        return T(6, content);
    }

    public static int T(String content, Throwable tr) {
        if (tr != null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            tr.printStackTrace(pw);
            pw.flush();
            content = content + "\r\n" + sw.toString();
        }
        return T(6, content);
    }

    public static void Ty(int depth) {
        if (Tr) {
            f402T = new Throwable().getStackTrace()[depth + 1];
        }
    }

    public static void Tr() {
        com.txznet.txz.T.Tn.T("comm.log.", TZ);
    }

    /* compiled from: Proguard */
    private static class T implements Tn.T {
        private T() {
        }
    }

    public static void T(Object log) {
        T(3, "TXZ", log);
    }

    public static void Tr(Object log) {
        T(6, "TXZ", log);
    }

    private static int Ty() {
        if (TE < Integer.MAX_VALUE) {
            return TE + 1;
        }
        return 0;
    }

    private static void T(int level, String tag, Object log) {
        String message;
        String headInfo = Tn();
        if (log.getClass().isArray()) {
            message = Arrays.deepToString((Object[]) log);
        } else {
            message = log.toString();
        }
        T(level, tag, headInfo + " " + message);
    }

    private static String Tn() {
        if (!Tr) {
            return "";
        }
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        int index = T(stackTrace);
        if (index == -1) {
            return "[Get Info Error]";
        }
        StackTraceElement element = stackTrace[index];
        String className = element.getClassName();
        if (className.contains(".")) {
            String[] names = className.split("\\.");
            className = names[names.length - 1] + ".java";
        }
        if (className.contains("$")) {
            className = className.split("\\$")[0] + ".java";
        }
        return "[(" + className + ":" + element.getLineNumber() + ")#" + element.getMethodName() + "]";
    }

    private static int T(StackTraceElement[] stackTrace) {
        if (stackTrace != null) {
            for (int i = 2; i < stackTrace.length; i++) {
                if (!stackTrace[i].getClassName().equals(Tn.class.getName())) {
                    return i;
                }
            }
        }
        return -1;
    }
}
