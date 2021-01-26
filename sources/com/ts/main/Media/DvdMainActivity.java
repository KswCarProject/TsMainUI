package com.ts.main.Media;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import com.ts.MainUI.R;
import com.ts.backcar.BackcarService;
import com.ts.main.common.MainSet;
import com.ts.main.common.WinShow;

public class DvdMainActivity extends Activity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (BackcarService.getInstance().bIninOK) {
            MainSet.GetInstance().FtSetInint();
            WinShow.show("com.android.sdvdplayer", "com.android.sdvdplayer.SDVDPlayer");
        } else {
            Toast.makeText(this, R.string.video_state_init, 0).show();
        }
        overridePendingTransition(0, 0);
        finish();
        overridePendingTransition(0, 0);
    }
}
