package com.ts.main.avin;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import com.ts.MainUI.Evc;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.TsDisplay;
import com.ts.MainUI.UserCallBack;
import com.ts.backcar.AutoFitTextureView;
import com.ts.backcar.BackcarService;
import com.ts.main.common.MainSet;
import com.ts.main.common.MainUI;
import com.ts.main.common.ScreenSet;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.Mcu;

public class AvinMainActivity extends Activity implements UserCallBack {
    private static final int AVIN_DELAY_NUM = 0;
    private static final String TAG = "AvinMainActivity";
    AvShowMainItem AvinShow = new AvShowMainItem();
    RelativeLayout MyRelativeLayout = null;
    boolean bMul = false;
    boolean bRear = false;
    private Evc mEvc = Evc.GetInstance();
    int nRearShow = 1;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avin_main);
        this.AvinShow.Inint(this, (RelativeLayout) findViewById(R.id.activity_avin_mainid), 1);
        this.AvinShow.SetIsHaveVol(true);
        this.AvinShow.InintCommonBtn();
        this.AvinShow.GetVideoName().setText(R.string.title_activity_avin_main);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        Log.i("scj", "av car onResume ");
        this.AvinShow.nDelayNum = 0;
        Mcu.SetFcamstate((byte) 0);
        this.AvinShow.bCameraReady = BackcarService.getInstance().bIninOK;
        MainTask.GetInstance().SetUserCallBack(this);
        this.AvinShow.ShowMode(1, true);
        this.AvinShow.nShowMode = 0;
        super.onResume();
        EnterAVIN();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        Log.i("scj", "av car onPause ");
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        BackcarService.getInstance().StopCamera();
        ScreenSet.GetInstance().Hide();
        this.AvinShow.ShowMode(5, false);
        MainSet.GetInstance().TwShowTitle(TXZResourceManager.STYLE_DEFAULT);
        TsDisplay.GetInstance().SetDispParat(-1);
        if (MainUI.IsCameraMode() == 0) {
            BackcarService.getInstance().ShowRearDisplay();
        }
        super.onPause();
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == 0) {
            this.AvinShow.DealKeyTouch();
            return true;
        }
        event.getAction();
        return true;
    }

    private void full(boolean enable) {
        if (enable) {
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.flags |= 1024;
            getWindow().setAttributes(lp);
            getWindow().addFlags(512);
            return;
        }
        WindowManager.LayoutParams attr = getWindow().getAttributes();
        attr.flags &= -1025;
        getWindow().setAttributes(attr);
        getWindow().clearFlags(512);
    }

    /* access modifiers changed from: package-private */
    public void EnterAVIN() {
        if (BackcarService.getInstance().bIninOK) {
            this.mEvc.evol_workmode_set(9);
            MainSet.GetInstance().SetVideoChannel(2);
            MainSet.GetInstance().TwShowTitle(getResources().getString(R.string.title_activity_avin_main));
            BackcarService.getInstance().StartCamera((AutoFitTextureView) findViewById(R.id.textureView), false);
            TsDisplay.GetInstance().SetSrcMute(20);
            TsDisplay.GetInstance().SetDispParat(1);
            this.AvinShow.bCameraReady = BackcarService.getInstance().bIninOK;
        }
    }

    public void UserAll() {
        if (this.AvinShow.bCameraReady) {
            this.AvinShow.SignalDetect();
        } else {
            EnterAVIN();
        }
    }
}
