package com.ts.can;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.Log;
import com.ts.bt.BtExe;
import com.ts.bt.BtFunc;
import com.ts.main.common.CrashHandler;
import com.ts.main.common.MainSet;
import com.ts.main.common.MainUI;
import com.ts.main.common.WinShow;
import java.util.List;

public class MyApplication extends Application {
    public static Context mContext;

    public void onCreate() {
        printContext(this);
        String strProcess = getProcessName(this, Process.myPid());
        if (strProcess == null) {
            Log.e("MyApplication", "getProcessName = null");
        } else {
            Log.e("MyApplication", "getProcessName = " + strProcess);
            if (!strProcess.contains(":remote")) {
                Intent intent = new Intent("android.intent.action.MAIN_SERVICE");
                intent.setPackage("com.ts.MainUI");
                startService(intent);
                startNaviBarServiceIfExist();
                mContext = this;
                CrashHandler.getInstance().init(this);
                MainSet.mContext = this;
                WinShow.mContext = this;
                BtExe.setContext(this);
                MainUI.SetCanCallBack(CanFunc.getInstance());
                CanIF.SetCanTypeCb(CanFunc.getInstance());
            }
        }
        registerActivityLifecycleCallbacks(BtFunc.mLifecyleCallbacks);
        super.onCreate();
    }

    private void startNaviBarServiceIfExist() {
        try {
            Class<?> cls = Class.forName("com.ts.main.navigationbar.NaviBarService");
            Intent intent = new Intent("com.ts.main.navigationbar.NaviBarService");
            intent.setPackage("com.ts.MainUI");
            startService(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String getProcessName(Context cxt, int pid) {
        List<ActivityManager.RunningAppProcessInfo> runningApps = ((ActivityManager) cxt.getSystemService("activity")).getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }

    static Context getContext() {
        return mContext;
    }

    private void printContext(Context context) {
        Log.e("MyApplication", "printContext = " + context.toString());
    }
}
