package com.autochips.camera.util;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import com.ts.main.common.ShellUtils;

public class LogUtil {
    private static final boolean FORCE_DEBUG = true;
    private static final String SEPARATOR = "- ";
    private static final String TAG = "[APP_AtcCamera]";

    private LogUtil() {
    }

    public static void v(@NonNull String tag, @NonNull String msg, @NonNull Object... args) {
        println(2, TAG, tag, msg, args);
    }

    public static void d(@NonNull String tag, @NonNull String msg, @NonNull Object... args) {
        println(3, TAG, tag, msg, args);
    }

    public static void i(@NonNull String tag, @NonNull String msg, @NonNull Object... args) {
        println(4, TAG, tag, msg, args);
    }

    public static void w(@NonNull String tag, @NonNull String msg, @NonNull Object... args) {
        println(5, TAG, tag, msg, args);
    }

    public static void e(@NonNull String tag, @NonNull String msg, @NonNull Object... args) {
        println(6, TAG, tag, msg, args);
    }

    public static void e(@NonNull String tag, @NonNull String msg, @NonNull Throwable throwable) {
        if (!TextUtils.isEmpty(msg)) {
            println(6, TAG, tag, String.valueOf(msg) + ShellUtils.COMMAND_LINE_END + Log.getStackTraceString(throwable), new Object[0]);
        }
    }

    private static void println(int level, @NonNull String tag, @NonNull String localTag, @NonNull String msg, @NonNull Object... args) {
        boolean hasArgs = args == null || args.length > 0;
        if (level < 4) {
            Log.isLoggable(localTag, level);
        }
        String formattedMsg = SEPARATOR + localTag;
        if (!TextUtils.isEmpty(msg)) {
            StringBuilder append = new StringBuilder(String.valueOf(formattedMsg)).append(SEPARATOR);
            if (hasArgs) {
                msg = String.format(msg, args);
            }
            formattedMsg = append.append(msg).toString();
        }
        Log.println(level, tag, formattedMsg);
    }
}
