package com.ts.bt;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

@SuppressLint({"NewApi"})
public class BtReceiver extends BroadcastReceiver {
    private static final String TAG = "BluetoothReceiver";
    private static BtUITimer timeCallBack = new BtUITimer() {
        public void onBtTimer() {
            BtBaseActivity.updateBtInfo();
            BtCallMsgbox.GetInstance().OnTimer(BtExe.getContext());
        }
    };

    public void onReceive(Context context, Intent intent) {
    }
}
