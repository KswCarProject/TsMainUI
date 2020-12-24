package com.ts.main.view360;

import android.app.Activity;
import android.os.Bundle;
import com.lgb.canmodule.Can;
import com.yyw.ts70xhw.Mcu;

public class View360MainActivity extends Activity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mcu.SendxyTouch(Can.CAN_MG_ZS_WC, 4, 1, 1);
        finish();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }
}
