package com.ts.bt;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.SparseIntArray;
import android.view.View;
import com.ts.main.common.MainUI;
import com.yyw.ts70xhw.KeyDef;

public class BtFunc {
    /* access modifiers changed from: private */
    public static Activity mBtDialActivity = null;
    /* access modifiers changed from: private */
    public static DealBtCallKey mDealBtCall = new DealBtCallKey() {
        SparseIntArray key2Id = new SparseIntArray();

        public int dealBtCallKey(int key) {
            View v;
            int viewId = 0;
            switch (key) {
                case 29:
                case 273:
                    viewId = findViewId(key, "bt_btn_dial_call");
                    break;
                case 30:
                case 274:
                    viewId = findViewId(key, "bt_btn_dial_end");
                    break;
                case 41:
                case 285:
                    viewId = findViewId(key, "bt_btn_dial_numj");
                    break;
                case 42:
                case 286:
                    viewId = findViewId(key, "bt_btn_dial_numx");
                    break;
                case KeyDef.RKEY_DEL /*335*/:
                    viewId = findViewId(key, "bt_btn_dial_bkspace");
                    break;
                default:
                    if (key < 275 || key > 284) {
                        if (key >= 31 && key <= 40) {
                            viewId = findNumKeyId(key, 31);
                            break;
                        }
                    } else {
                        viewId = findNumKeyId(key, 275);
                        break;
                    }
                    break;
            }
            if (viewId <= 0 || viewId == 0 || BtFunc.mBtDialActivity == null || (v = BtFunc.mBtDialActivity.findViewById(viewId)) == null) {
                return 0;
            }
            v.performClick();
            return 1;
        }

        private int findNumKeyId(int key, int startKey) {
            if (key < startKey || key > startKey + 9) {
                return 0;
            }
            int id = this.key2Id.get(key);
            if (id == 0) {
                return findViewId(key, "bt_btn_dial_num" + (key - startKey));
            }
            return id;
        }

        private int findViewId(int key, String name) {
            try {
                int viewId = Class.forName("com.ts.MainUI.R$id").getDeclaredField(name).getInt((Object) null);
                if (viewId <= 0) {
                    return viewId;
                }
                this.key2Id.put(key, viewId);
                return viewId;
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
    };
    public static Application.ActivityLifecycleCallbacks mLifecyleCallbacks = new Application.ActivityLifecycleCallbacks() {
        public void onActivityCreated(Activity arg0, Bundle arg1) {
        }

        public void onActivityDestroyed(Activity arg0) {
        }

        public void onActivityPaused(Activity activity) {
            if (activity.getClass().getName().equals("com.ts.bt.BtDialActivity")) {
                MainUI.unregisterBtCallKey(BtFunc.mDealBtCall);
            }
        }

        public void onActivityResumed(Activity activity) {
            if (activity.getClass().getName().equals("com.ts.bt.BtDialActivity")) {
                MainUI.registerBtCallKey(BtFunc.mDealBtCall);
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

    public interface DealBtCallKey {
        int dealBtCallKey(int i);
    }

    public static int DealKey(int nkey) {
        BtExe bt = BtExe.getBtInstance();
        if (bt == null) {
            return 0;
        }
        switch (nkey) {
            case 29:
            case 273:
            case KeyDef.SKEY_CALLUP_1 /*814*/:
                bt.answer();
                return 1;
            case 30:
            case 274:
            case KeyDef.SKEY_CALLDN_1 /*819*/:
                bt.hangup();
                return 1;
            case 44:
            case 291:
                bt.musicNext();
                return 1;
            case 45:
            case 292:
                bt.musicPrev();
                return 1;
            case 60:
            case 299:
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
}
