package com.ts.can.ford;

import android.os.Bundle;
import android.util.Log;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanJni;
import com.ts.can.CanBaseActivity;
import com.ts.can.ford.wc.CanFordWcSyncUIActivity;

public class CanFordSyncActivity extends CanBaseActivity {
    public static final String TAG = "CanFordSyncActivity";
    public static boolean mfgAcIconClick = false;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        mfgAcIconClick = true;
        Log.d(TAG, "-----onCreate-----");
        super.onCreate(savedInstanceState);
        switch (CanJni.GetCanFsTp()) {
            case 13:
            case 146:
                CanFordSyncUIActivity.ShowSyncClick();
                break;
            case Can.CAN_FORD_WC:
                CanFordWcSyncUIActivity.ShowSyncClick();
                break;
            case Can.CAN_FORD_SYNC3:
                CanFordSync3UIActivity.ShowSyncClick();
                break;
        }
        finish();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        Log.d(TAG, "-----onResume-----");
    }
}
