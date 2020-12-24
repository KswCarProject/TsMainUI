package com.ts.main.Media;

import android.app.Activity;
import android.os.Bundle;
import com.ts.main.common.MainSet;
import com.ts.main.common.WinShow;

public class USBMainActivity extends Activity {
    /* access modifiers changed from: protected */
    public void onPause() {
        overridePendingTransition(0, 0);
        super.onPause();
        overridePendingTransition(0, 0);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainSet.GetInstance().FtSetInint();
        if (MainSet.GetInstance().IsHaveApk("com.mxtech.videoplayer.pro")) {
            MainSet.GetInstance().openApplication(this, "com.mxtech.videoplayer.pro");
        } else if (MainSet.GetInstance().IsHaveApk("com.mxtech.videoplayer.ad")) {
            MainSet.GetInstance().openApplication(this, "com.mxtech.videoplayer.ad");
        } else {
            WinShow.ShowVideoWin();
        }
        overridePendingTransition(0, 0);
        finish();
        overridePendingTransition(0, 0);
    }
}
