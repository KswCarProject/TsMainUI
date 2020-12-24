package com.ts.bt;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.SparseIntArray;
import android.view.View;
import com.yyw.ts70xhw.KeyDef;

public class BtFunc {
    private static SparseIntArray key2Id = new SparseIntArray();
    /* access modifiers changed from: private */
    public static Activity mBtDialActivity = null;
    public static Application.ActivityLifecycleCallbacks mLifecyleCallbacks = new Application.ActivityLifecycleCallbacks() {
        public void onActivityCreated(Activity arg0, Bundle arg1) {
        }

        public void onActivityDestroyed(Activity arg0) {
        }

        public void onActivityPaused(Activity activity) {
        }

        public void onActivityResumed(Activity activity) {
            if (activity.getClass().getName().equals("com.ts.bt.BtDialActivity")) {
                BtFunc.mBtDialActivity = activity;
            }
        }

        public void onActivitySaveInstanceState(Activity arg0, Bundle arg1) {
        }

        public void onActivityStarted(Activity arg0) {
        }

        public void onActivityStopped(Activity activity) {
            if (activity.getClass().getName().equals("com.ts.bt.BtDialActivity")) {
                BtFunc.mBtDialActivity = null;
            }
        }
    };

    public static int DealKey(int nkey) {
        BtExe bt = BtExe.getBtInstance();
        if (bt == null) {
            return 0;
        }
        switch (nkey) {
            case 29:
            case 273:
            case KeyDef.SKEY_CALLUP_1 /*814*/:
                dealBtCallKey(nkey);
                return 1;
            case 30:
            case 274:
            case KeyDef.SKEY_CALLDN_1 /*819*/:
                bt.hangup();
                return 1;
            case 44:
            case KeyDef.RKEY_NEXT /*291*/:
                bt.musicNext();
                return 1;
            case 45:
            case KeyDef.RKEY_PRE /*292*/:
                bt.musicPrev();
                return 1;
            case 60:
            case KeyDef.RKEY_MEDIA_PP /*299*/:
            case KeyDef.SKEY_PP_1 /*824*/:
                bt.musicPP();
                return 1;
            case 68:
                bt.musicPrev();
                return 1;
            case 69:
                bt.musicNext();
                return 1;
            case 90:
                bt.musicPlay();
                return 1;
            case 91:
                bt.musicPause();
                return 1;
            case 515:
                bt.musicPrev();
                return 1;
            case 516:
                bt.musicNext();
                return 1;
            case KeyDef.SKEY_SEEKUP_1 /*784*/:
            case KeyDef.SKEY_CHUP_1 /*794*/:
                bt.musicNext();
                return 1;
            case KeyDef.SKEY_SEEKDN_1 /*789*/:
            case KeyDef.SKEY_CHDN_1 /*799*/:
                bt.musicPrev();
                return 1;
            default:
                return 1;
        }
    }

    private static int findViewId(int key, String name) {
        try {
            int viewId = Class.forName("com.ts.MainUI.R$id").getDeclaredField(name).getInt((Object) null);
            if (viewId <= 0) {
                return viewId;
            }
            key2Id.put(key, viewId);
            return viewId;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int dealBtCallKey(int key) {
        View v;
        int viewId = 0;
        switch (key) {
            case 29:
            case 273:
            case KeyDef.SKEY_CALLUP_1 /*814*/:
                viewId = findViewId(key, "bt_btn_dial_call");
                break;
        }
        if (viewId <= 0 || viewId == 0 || mBtDialActivity == null || (v = mBtDialActivity.findViewById(viewId)) == null) {
            return 0;
        }
        v.performClick();
        return 1;
    }
}
