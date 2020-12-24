package com.ts.main.Media;

import android.app.Activity;
import android.os.Bundle;
import com.ts.main.common.WinShow;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Iop;

public class ADASMainActivity extends Activity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (FtSet.IsIconExist(127) == 1 && Iop.DspSupport() == 1) {
            overridePendingTransition(0, 0);
            WinShow.TurnToEq();
            overridePendingTransition(0, 0);
        }
        finish();
    }
}
