package com.ts.main.Media;

import android.app.Activity;
import android.os.Bundle;
import com.ts.main.common.MainSet;
import com.ts.main.common.WinShow;

public class DvdMainActivity extends Activity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainSet.GetInstance().FtSetInint();
        WinShow.show("com.android.sdvdplayer", "com.android.sdvdplayer.SDVDPlayer");
        overridePendingTransition(0, 0);
        finish();
        overridePendingTransition(0, 0);
    }
}
