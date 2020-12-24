package com.hongfans.carmedia.processes;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Process;
import android.util.Log;
import com.hongfans.carmedia.Constant;
import com.hongfans.carmedia.processes.models.AndroidAppProcess;
import com.hongfans.carmedia.processes.models.AndroidProcess;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AndroidProcesses {
    public static final String TAG = "AndroidProcesses";
    private static boolean loggingEnabled;

    AndroidProcesses() {
        throw new AssertionError("no instances");
    }

    public static boolean isLoggingEnabled() {
        return loggingEnabled;
    }

    public static void setLoggingEnabled(boolean enabled) {
        loggingEnabled = enabled;
    }

    public static void log(String message, Object... args) {
        if (loggingEnabled) {
            if (args.length != 0) {
                message = String.format(message, args);
            }
            Log.d(TAG, message);
        }
    }

    public static void log(Throwable error, String message, Object... args) {
        if (loggingEnabled) {
            if (args.length != 0) {
                message = String.format(message, args);
            }
            Log.d(TAG, message, error);
        }
    }

    public static List<AndroidProcess> getRunningProcesses() {
        List<AndroidProcess> processes = new ArrayList<>();
        for (File file : new File("/proc").listFiles()) {
            if (file.isDirectory()) {
                try {
                    int pid = Integer.parseInt(file.getName());
                    try {
                        processes.add(new AndroidProcess(pid));
                    } catch (IOException e) {
                        log(e, "Error reading from /proc/%d.", Integer.valueOf(pid));
                    }
                } catch (NumberFormatException e2) {
                }
            }
        }
        return processes;
    }

    public static List<AndroidAppProcess> getRunningAppProcesses() {
        List<AndroidAppProcess> processes = new ArrayList<>();
        File[] files = new File("/proc").listFiles();
        int length = files.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            File file = files[i];
            if (file.isDirectory()) {
                try {
                    int pid = Integer.parseInt(file.getName());
                    try {
                        String name = new AndroidAppProcess(pid).name;
                        System.out.println("==##getRunningAppProcesses:" + name);
                        if (name.equals(Constant.PACKAGE_NAME)) {
                            processes.add(new AndroidAppProcess(pid));
                            break;
                        }
                    } catch (AndroidAppProcess.NotAndroidAppProcessException e) {
                    } catch (IOException e2) {
                        log(e2, "Error reading from /proc/%d.", Integer.valueOf(pid));
                    }
                } catch (NumberFormatException e3) {
                }
            }
            i++;
        }
        return processes;
    }

    public static List<AndroidAppProcess> getRunningForegroundApps(Context context) {
        List<AndroidAppProcess> processes = new ArrayList<>();
        File[] files = new File("/proc").listFiles();
        PackageManager pm = context.getPackageManager();
        for (File file : files) {
            if (file.isDirectory()) {
                try {
                    int pid = Integer.parseInt(file.getName());
                    try {
                        AndroidAppProcess process = new AndroidAppProcess(pid);
                        if (process.foreground && ((process.uid < 1000 || process.uid > 9999) && !process.name.contains(":") && pm.getLaunchIntentForPackage(process.getPackageName()) != null)) {
                            processes.add(process);
                        }
                    } catch (AndroidAppProcess.NotAndroidAppProcessException e) {
                    } catch (IOException e2) {
                        log(e2, "Error reading from /proc/%d.", Integer.valueOf(pid));
                    }
                } catch (NumberFormatException e3) {
                }
            }
        }
        return processes;
    }

    public static boolean isMyProcessInTheForeground() {
        try {
            return new AndroidAppProcess(Process.myPid()).foreground;
        } catch (Exception e) {
            log(e, "Error finding our own process", new Object[0]);
            return false;
        }
    }

    public static List<ActivityManager.RunningAppProcessInfo> getRunningAppProcessInfo(Context context) {
        if (Build.VERSION.SDK_INT < 21) {
            return ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        }
        List<AndroidAppProcess> runningAppProcesses = getRunningAppProcesses();
        List<ActivityManager.RunningAppProcessInfo> appProcessInfos = new ArrayList<>();
        for (AndroidAppProcess process : runningAppProcesses) {
            ActivityManager.RunningAppProcessInfo info = new ActivityManager.RunningAppProcessInfo(process.name, process.pid, (String[]) null);
            info.uid = process.uid;
            appProcessInfos.add(info);
        }
        return appProcessInfos;
    }

    public static final class ProcessComparator implements Comparator<AndroidProcess> {
        public int compare(AndroidProcess p1, AndroidProcess p2) {
            return p1.name.compareToIgnoreCase(p2.name);
        }
    }
}
