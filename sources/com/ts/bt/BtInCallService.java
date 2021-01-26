package com.ts.bt;

import android.annotation.SuppressLint;
import android.telecom.Call;
import android.telecom.CallAudioState;
import android.telecom.InCallService;
import android.util.Log;

@SuppressLint({"NewApi"})
public class BtInCallService extends InCallService {
    public static final String TAG = "Bt";
    BtExe bt = BtExe.getBtInstance();
    private Call.Callback callback = new Call.Callback() {
        public void onStateChanged(Call call, int state) {
            super.onStateChanged(call, state);
            Log.d(BtInCallService.TAG, "onStateChanged:" + state);
            switch (state) {
            }
        }
    };

    public void onCallAdded(Call call) {
        super.onCallAdded(call);
        Log.d(TAG, "onCallAdded");
        this.bt.addCall(call);
    }

    public void onCallAudioStateChanged(CallAudioState state) {
        super.onCallAudioStateChanged(state);
        Log.d(TAG, "onCallAudioStateChanged");
    }

    public void onCallRemoved(Call call) {
        super.onCallRemoved(call);
        Log.d(TAG, "onCallRemoved");
    }
}
