package com.ts.main.ipod;

import android.app.Activity;
import android.os.Bundle;
import com.ts.MainUI.Evc;
import com.ts.main.common.MainSet;
import com.ts.main.common.WinShow;

public class IpodMainActivity extends Activity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainSet.GetInstance().FtSetInint();
        WinShow.show("com.ts.ipodplayer", "com.ts.ipodplayer.activity.IpodMainActivity");
        Evc.GetInstance().evol_workmode_set(10);
        finish();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }
}
