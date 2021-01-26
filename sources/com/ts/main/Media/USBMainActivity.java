package com.ts.main.Media;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import com.ts.MainUI.R;
import com.ts.backcar.BackcarService;
import com.ts.main.common.MainSet;
import com.ts.main.common.WinShow;

public class USBMainActivity extends Activity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainSet.GetInstance().FtSetInint();
        if (getResources().getString(R.string.custom_num_show).equals("MCX")) {
            WinShow.ShowVideoWin();
        } else if (MainSet.GetInstance().IsHaveApk("com.mxtech.videoplayer.pro")) {
            MainSet.GetInstance().openApplication(this, "com.mxtech.videoplayer.pro");
        } else if (MainSet.GetInstance().IsHaveApk("com.mxtech.videoplayer.ad")) {
            MainSet.GetInstance().openApplication(this, "com.mxtech.videoplayer.ad");
        } else if (BackcarService.getInstance().bIninOK) {
            WinShow.ShowVideoWin();
        } else {
            Toast.makeText(this, R.string.video_state_init, 0).show();
        }
        overridePendingTransition(0, 0);
        finish();
        overridePendingTransition(0, 0);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        overridePendingTransition(0, 0);
        super.onPause();
    }
}
