package com.Ty.T.Ty;

import android.util.Log;
import com.Ty.T.Tr.Tn;

/* compiled from: Proguard */
public final class Ty {

    /* renamed from: T  reason: collision with root package name */
    private static volatile boolean f351T = false;
    private static volatile boolean Tr = true;

    public static void T(boolean writeDebugLogs) {
        f351T = writeDebugLogs;
    }

    public static void T(String message, Object... args) {
        if (f351T) {
            T(3, (Throwable) null, message, args);
        }
    }

    public static void Tr(String message, Object... args) {
        T(4, (Throwable) null, message, args);
    }

    public static void Ty(String message, Object... args) {
        T(5, (Throwable) null, message, args);
    }

    public static void T(Throwable ex) {
        T(6, ex, (String) null, new Object[0]);
    }

    public static void Tn(String message, Object... args) {
        T(6, (Throwable) null, message, args);
    }

    private static void T(int priority, Throwable ex, String message, Object... args) {
        String logMessage;
        String log;
        if (Tr) {
            if (args.length > 0) {
                message = String.format(message, args);
            }
            if (ex == null) {
                log = message;
            } else {
                if (message == null) {
                    logMessage = ex.getMessage();
                } else {
                    logMessage = message;
                }
                log = String.format("%1$s\n%2$s", new Object[]{logMessage, Log.getStackTraceString(ex)});
            }
            Log.println(priority, Tn.f338T, log);
        }
    }
}
