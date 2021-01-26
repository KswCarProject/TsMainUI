package com.ts.main.navi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.ts.main.common.MainSet;
import com.ts.main.common.WinShow;

public class NaviMainActivity extends Activity {
    boolean bCheck = true;
    byte[] byteNavipath = new byte[128];

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String NaviPath = MainSet.GetInstance().GetNaviPath();
        Intent it = getPackageManager().getLaunchIntentForPackage(NaviPath);
        Log.i("NaviMainActivity  PATH =", NaviPath);
        if (it != null) {
            it.addFlags(268435456);
            startActivity(it);
            overridePendingTransition(0, 0);
        } else {
            WinShow.GotoWin(11, 99);
        }
        finish();
        overridePendingTransition(0, 0);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }
}
