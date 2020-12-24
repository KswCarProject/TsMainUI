package com.ts.main.fcamera;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.TsDisplay;
import com.ts.MainUI.UserCallBack;
import com.ts.backcar.AutoFitTextureView;
import com.ts.backcar.BackcarService;
import com.ts.can.CanIF;
import com.ts.main.common.MainSet;
import com.yyw.ts70xhw.Mcu;

public class FcameraMainActivity extends Activity implements UserCallBack {
    private static final String TAG = "FcameraMainActivity";
    ViewGroup mlayout;
    private ProgressBar pProgressLoading;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        if (CanIF.BmwWithCDCmd(3, 1).booleanValue()) {
            super.onCreate(savedInstanceState);
            finish();
            return;
        }
        setContentView(R.layout.activity_avin_main);
        this.mlayout = (RelativeLayout) findViewById(R.id.activity_avin_mainid);
        this.pProgressLoading = (ProgressBar) this.mlayout.findViewById(R.id.loading_progress);
        this.pProgressLoading.setVisibility(4);
        super.onCreate(savedInstanceState);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        BackcarService.getInstance().StopCamera();
        TsDisplay.GetInstance().SetDispParat(-1);
        Mcu.SetFcamstate((byte) 0);
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        Mcu.SetFcamstate((byte) 1);
        TsDisplay.GetInstance().SetSrcMute(12);
        TsDisplay.GetInstance().SetDispParat(3);
        MainSet.GetInstance().SetVideoChannel(2);
        BackcarService.getInstance().StartCamera((AutoFitTextureView) findViewById(R.id.textureView), true);
        MainTask.GetInstance().SetUserCallBack(this);
        super.onResume();
    }

    public void UserAll() {
        if (MainSet.nFcamTime > 0) {
            MainSet.nFcamTime--;
            if (MainSet.nFcamTime == 0) {
                finish();
            }
        }
    }
}
