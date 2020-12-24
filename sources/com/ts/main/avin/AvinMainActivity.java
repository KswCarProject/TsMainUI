package com.ts.main.avin;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import com.ts.MainUI.Evc;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.TsDisplay;
import com.ts.MainUI.UserCallBack;
import com.ts.backcar.AutoFitTextureView;
import com.ts.backcar.BackcarService;
import com.ts.main.common.MainSet;
import com.ts.main.common.ScreenSet;

public class AvinMainActivity extends Activity implements UserCallBack {
    private static final int AVIN_DELAY_NUM = 0;
    private static final String TAG = "AvinMainActivity";
    AvShowMainItem AvinShow = new AvShowMainItem();
    RelativeLayout MyRelativeLayout = null;
    private Evc mEvc = Evc.GetInstance();
    int nRearShow = 1;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avin_main);
        this.AvinShow.Inint(this, (RelativeLayout) findViewById(R.id.activity_avin_mainid), 1);
        this.AvinShow.InintCommonBtn();
        this.AvinShow.GetVideoName().setText(R.string.title_activity_avin_main);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        Log.i("scj", "av car onResume ");
        this.AvinShow.nDelayNum = 0;
        MainTask.GetInstance().SetUserCallBack(this);
        this.mEvc.evol_workmode_set(9);
        super.onResume();
        this.AvinShow.ShowMode(1, true);
        this.AvinShow.nShowMode = 0;
        TsDisplay.GetInstance().SetDispParat(1);
        MainSet.GetInstance().SetVideoChannel(2);
        MainSet.GetInstance().TwShowTitle(getResources().getString(R.string.title_activity_avin_main));
        BackcarService.getInstance().StartCamera((AutoFitTextureView) findViewById(R.id.textureView), true);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        Log.i("scj", "av car onPause ");
        ScreenSet.GetInstance().Hide();
        this.AvinShow.ShowMode(5, false);
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        MainSet.GetInstance().TwShowTitle("");
        BackcarService.getInstance().StopCamera();
        TsDisplay.GetInstance().SetDispParat(-1);
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

    public void UserAll() {
        this.AvinShow.SignalDetect();
    }
}
