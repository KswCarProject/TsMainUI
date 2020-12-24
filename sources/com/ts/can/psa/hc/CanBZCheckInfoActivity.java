package com.ts.can.psa.hc;

import android.os.Bundle;

public class CanBZCheckInfoActivity extends CanBZLogInfoActivity {
    public static final String TAG = "CanBZCheckInfoActivity";

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        SetLogType(135);
    }
}
