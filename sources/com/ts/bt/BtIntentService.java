package com.ts.bt;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class BtIntentService extends IntentService {
    private static final String TAG = "BtExe";
    private static BtUITimer timeCallBack = new BtUITimer() {
        public void onBtTimer() {
            BtBaseActivity.updateBtInfo();
            BtCallMsgbox.GetInstance().OnTimer(BtExe.getContext());
        }
    };

    public BtIntentService() {
        super("BtIntentService");
    }

    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "BtIntent onCreate");
        BtExe.getBtInstance().SetTimerCallBack(timeCallBack);
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        Log.d(TAG, "BtIntent onDestroy");
    }

    public void onDestroy() {
        Log.d(TAG, "onHandleIntent");
        super.onDestroy();
    }
}
