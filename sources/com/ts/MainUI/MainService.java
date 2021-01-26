package com.ts.MainUI;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class MainService extends Service {
    private static final String TAG = "MainService";
    /* access modifiers changed from: private */
    public static int delaytime = 30;
    /* access modifiers changed from: private */
    public MainTask mMainTask = MainTask.GetInstance();
    /* access modifiers changed from: private */
    public Handler mainHandler = new Handler();

    private class MainRunnable implements Runnable {
        private MainRunnable() {
        }

        /* synthetic */ MainRunnable(MainService mainService, MainRunnable mainRunnable) {
            this();
        }

        public void run() {
            if (MainService.this.mMainTask.mTaskCallBack != null) {
                MainService.this.mMainTask.mTaskCallBack.DealTask();
                MainService.this.mMainTask.mTaskCallBack.DealKey();
            }
            if (MainService.this.mMainTask.mUserCallBack != null) {
                MainService.this.mMainTask.mUserCallBack.UserAll();
            }
            if (MainService.this.mMainTask.mUserRadioCalllBack != null) {
                MainService.this.mMainTask.mUserRadioCalllBack.UserAll();
            }
            MainService.this.mainHandler.postDelayed(this, (long) MainService.delaytime);
        }
    }

    public static void SetDelayTaskTime(int nTime) {
        delaytime = nTime;
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        Log.i(TAG, "start service!");
        Intent startIntent = new Intent("android.intent.action.MAIN_UI");
        startIntent.setPackage("com.ts.MainUI");
        startService(startIntent);
        this.mainHandler.post(new MainRunnable(this, (MainRunnable) null));
        Log.i(TAG, "start service! end ");
        IntentFilter Myfile = new IntentFilter("android.media.GIS_AUDIO_STATUS_ACTION");
        Myfile.addCategory("android.intent.category.DEFAULT");
        registerReceiver(new AutoBootUp(), Myfile);
        Log.i(TAG, "start service! end 22");
        super.onCreate();
    }

    public void onDestroy() {
        Log.w(TAG, "######## Service Destroy !");
        super.onDestroy();
    }
}
