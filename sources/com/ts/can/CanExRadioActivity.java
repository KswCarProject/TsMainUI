package com.ts.can;

import android.os.Bundle;
import android.util.Log;
import com.lgb.canmodule.CanJni;
import com.ts.can.honda.accord.CanAccordRadioActivity;
import com.ts.can.honda.accord_xbs.CanAccordXbsRadioActivity;
import com.ts.can.honda.crosstour.CanCrstourRadioActivity;

public class CanExRadioActivity extends CanBaseActivity {
    public static final String TAG = "CanExRadioActivity";

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        switch (CanJni.GetCanType()) {
            case 9:
                if (CanJni.GetSubType() == 2) {
                    enterSubWin(CanAccordRadioActivity.class);
                    break;
                }
                break;
            case 20:
                enterSubWin(CanCrstourRadioActivity.class);
                break;
            case 96:
                enterSubWin(CanAccordXbsRadioActivity.class);
                break;
            case 270:
                if (CanJni.GetSubType() == 2) {
                    enterSubWin(CanAccordRadioActivity.class);
                    break;
                }
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
