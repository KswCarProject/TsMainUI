package com.ts.main.Media;

import android.app.Activity;
import android.os.Bundle;
import com.ts.main.common.MainSet;
import com.ts.main.common.WinShow;

public class DvdBoxMainActivity extends Activity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainSet.GetInstance().FtSetInint();
        overridePendingTransition(0, 0);
        WinShow.show("com.ts.dvdplayer", "com.ts.dvdplayer.DvdBoxActivity");
        finish();
        overridePendingTransition(0, 0);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        overridePendingTransition(0, 0);
        super.onPause();
        overridePendingTransition(0, 0);
    }
}
