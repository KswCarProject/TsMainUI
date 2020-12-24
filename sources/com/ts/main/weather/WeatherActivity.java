package com.ts.main.weather;

import android.app.Activity;
import android.os.Bundle;
import com.ts.main.common.WinShow;

public class WeatherActivity extends Activity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WinShow.show("com.ts.MainUI", "com.ts.bt.WrcActivity");
        finish();
    }
}
