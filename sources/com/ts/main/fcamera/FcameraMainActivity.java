package com.ts.main.fcamera;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.android.SdkConstants;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.TsDisplay;
import com.ts.MainUI.UserCallBack;
import com.ts.backcar.AutoFitTextureView;
import com.ts.backcar.BackcarService;
import com.ts.backcar.TsDvrService;
import com.ts.main.avin.AvShowMainItem;
import com.ts.main.common.MainSet;
import com.ts.main.common.MainUI;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class FcameraMainActivity extends Activity implements UserCallBack {
    private static final String TAG = "FcameraMainActivity";
    AvShowMainItem FcameraShow = new AvShowMainItem();
    ViewGroup mlayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_avin_main);
        this.FcameraShow.Inint(this, (RelativeLayout) findViewById(R.id.activity_avin_mainid), 1);
        this.FcameraShow.InintCommonBtn();
        if (MainSet.GetInstance().IsFCamSupportMir()) {
            this.FcameraShow.SetMirrBtn(true);
        }
        if (MainSet.GetInstance().IsFCamSupportChange()) {
            this.FcameraShow.SetChangeBtn(true);
        }
        this.FcameraShow.SetTransWorkmode(false);
        this.FcameraShow.GetVideoName().setText(R.string.title_activity_fcamera_main);
        super.onCreate(savedInstanceState);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == 0) {
            this.FcameraShow.DealKeyTouch();
            return true;
        }
        event.getAction();
        return true;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (BackcarService.getInstance().bIsAvmFandR()) {
            TsDvrService.getInstance().StopCam("9");
        } else {
            BackcarService.getInstance().StopCamera();
        }
        this.FcameraShow.ShowMode(4, false);
        TsDisplay.GetInstance().SetDispParat(-1);
        Mcu.SetFcamstate((byte) 0);
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        if (MainSet.nFcamTime > 0) {
            finish();
            MainSet.bIsFrontCam = false;
        }
        if (MainUI.IsCameraMode() == 0) {
            MainSet.bIsFrontCam = false;
            BackcarService.getInstance().ReSumeRearDisplay();
        }
        if (FtSet.GetFcamMir() == 1) {
            BackcarService.SetforceMir(false);
        }
        if (BackcarService.getInstance().bIsAvm360()) {
            MainSet.GetInstance().SetVideoChannel(0);
        }
    }

    /* access modifiers changed from: package-private */
    public void EnterFcam() {
        if (BackcarService.getInstance().bIninOK) {
            TsDisplay.GetInstance().SetSrcMute(12);
            TsDisplay.GetInstance().SetDispParat(3);
            if (getResources().getIdentifier("fcamera_to_back_camera", SdkConstants.TAG_STRING, getPackageName()) <= 0 || MainSet.nFcamTime > 0) {
                MainSet.GetInstance().SetVideoChannel(2);
            } else {
                MainSet.GetInstance().SetVideoChannel(0);
            }
            if (BackcarService.getInstance().bIsAvmFandR()) {
                TsDvrService.getInstance().StartCam("9", (AutoFitTextureView) findViewById(R.id.textureView));
            } else {
                BackcarService.getInstance().StartCamera((AutoFitTextureView) findViewById(R.id.textureView), false);
            }
            this.FcameraShow.bCameraReady = BackcarService.getInstance().bIninOK;
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        Mcu.SetFcamstate((byte) 1);
        this.FcameraShow.nDelayNum = 0;
        this.FcameraShow.ChangerToFR(true);
        MainSet.bIsFrontCam = true;
        this.FcameraShow.ShowMode(1, true);
        this.FcameraShow.nShowMode = 0;
        this.FcameraShow.bCameraReady = BackcarService.getInstance().bIninOK;
        if (FtSet.GetFcamMir() == 1) {
            BackcarService.SetforceMir(true);
        }
        EnterFcam();
        MainTask.GetInstance().SetUserCallBack(this);
        super.onResume();
    }

    public void UserAll() {
        if (this.FcameraShow.bCameraReady) {
            if (MainSet.nFcamTime > 0) {
                MainSet.nFcamTime--;
                if (MainSet.nFcamTime == 0) {
                    finish();
                }
            }
            this.FcameraShow.SignalDetect();
            return;
        }
        EnterFcam();
    }
}
