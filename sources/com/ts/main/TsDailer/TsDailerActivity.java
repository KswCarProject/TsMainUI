package com.ts.main.TsDailer;

import android.app.Activity;
import android.os.Bundle;
import com.ts.main.common.MainSet;

public class TsDailerActivity extends Activity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (MainSet.GetInstance().IsHaveApk("com.android.dialer")) {
            MainSet.GetInstance().openApplication(this, "com.android.dialer");
        }
        finish();
    }
}
