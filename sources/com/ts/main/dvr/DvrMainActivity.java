package com.ts.main.dvr;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import com.ts.MainUI.Dvr;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.TsDisplay;
import com.ts.MainUI.UserCallBack;
import com.ts.backcar.AutoFitTextureView;
import com.ts.backcar.BackcarService;
import com.ts.main.avin.AvShowMainItem;
import com.ts.main.common.MainSet;
import com.ts.main.common.ScreenSet;
import com.ts.main.common.WinShow;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.FtSet;

public class DvrMainActivity extends Activity implements UserCallBack {
    private static final int DVR_DELAY_NUM = 50;
    ParamButton[] BtnDvr = new ParamButton[8];
    RelativeLayoutManager DvrManage;
    AvShowMainItem DvrShow = new AvShowMainItem();
    private int[] Ysj_btn1_Icondn = {R.drawable.driving_gnxd_dn, R.drawable.driving_lykg_dn, R.drawable.driving_msqh_dn, R.drawable.driving_play_dn1, R.drawable.driving_prv_dn, R.drawable.driving_next_dn};
    private int[] Ysj_btn1_Iconup = {R.drawable.driving_gnxd_up, R.drawable.driving_lykg_up, R.drawable.driving_msqh_up, R.drawable.driving_play_up1, R.drawable.driving_prv_up, R.drawable.driving_next_up};
    private int[] Ysj_btn_Icondn = {R.drawable.driving_menu_dn, R.drawable.driving_return_dn, R.drawable.driving_lock_dn, R.drawable.driving_hf_dn, R.drawable.driving_seekup_dn, R.drawable.driving_play_dn, R.drawable.driving_seekdn_dn};
    private int[] Ysj_btn_Iconup = {R.drawable.driving_menu_up, R.drawable.driving_return_up, R.drawable.driving_lock_up, R.drawable.driving_hf_up, R.drawable.driving_seekup_up, R.drawable.driving_play_up, R.drawable.driving_seekdn_up};
    private int nDelayNum = 0;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (FtSet.GetDvrType() == 4) {
            WinShow.show("com.ts.MainUI", "com.autochips.camera.MIPIDVRActivity");
            overridePendingTransition(0, 0);
            finish();
            overridePendingTransition(0, 0);
            return;
        }
        setContentView(R.layout.activity_avin_main);
        this.DvrShow.Inint(this, (RelativeLayout) findViewById(R.id.activity_avin_mainid), 3);
        this.DvrShow.InintCommonBtn();
        this.DvrShow.GetVideoName().setText(R.string.title_activity_dvr_main);
        this.DvrManage = new RelativeLayoutManager(this, R.id.activity_avin_mainid);
        switch (FtSet.GetDvrType()) {
            case 1:
                for (int i = 0; i < 6; i++) {
                    this.BtnDvr[i] = this.DvrManage.AddButton((i * 151) + 63, 500, 135, 82);
                    this.BtnDvr[i].setDrawable(this.Ysj_btn1_Iconup[i], this.Ysj_btn1_Icondn[i]);
                    this.BtnDvr[i].setTag(Integer.valueOf(i));
                    this.BtnDvr[i].setOnClickListener(new View.OnClickListener() {
                        public void onClick(View arg0) {
                            int nTag = ((Integer) arg0.getTag()).intValue();
                            DvrMainActivity.this.DvrShow.SetBtnDelay();
                            Dvr.GetInstance().BtnFun(nTag);
                        }
                    });
                }
                return;
            case 10:
                for (int i2 = 0; i2 < 7; i2++) {
                    this.BtnDvr[i2] = this.DvrManage.AddButton((i2 * 137) + 44, 500, 118, 78);
                    this.BtnDvr[i2].setDrawable(this.Ysj_btn_Iconup[i2], this.Ysj_btn_Icondn[i2]);
                    this.BtnDvr[i2].setTag(Integer.valueOf(i2));
                    this.BtnDvr[i2].setOnClickListener(new View.OnClickListener() {
                        public void onClick(View arg0) {
                            int nTag = ((Integer) arg0.getTag()).intValue();
                            DvrMainActivity.this.DvrShow.SetBtnDelay();
                            Dvr.GetInstance().BtnFun(nTag);
                        }
                    });
                }
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: package-private */
    public void EnterDvr() {
        if (BackcarService.getInstance().bIninOK) {
            MainSet.GetInstance().SetVideoChannel(1);
            BackcarService.getInstance().StartCamera((AutoFitTextureView) findViewById(R.id.textureView), false);
            TsDisplay.GetInstance().SetDispParat(3);
            this.DvrShow.bCameraReady = BackcarService.getInstance().bIninOK;
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        this.DvrShow.nDelayNum = 50;
        this.DvrShow.bCameraReady = BackcarService.getInstance().bIninOK;
        MainTask.GetInstance().SetUserCallBack(this);
        super.onResume();
        this.DvrShow.ShowMode(1, true);
        this.DvrShow.nShowMode = 0;
        EnterDvr();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        ScreenSet.GetInstance().Hide();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        this.DvrShow.ShowMode(2, false);
        this.DvrShow.nShowMode = 0;
        MainSet.GetInstance().TwShowTitle(TXZResourceManager.STYLE_DEFAULT);
        BackcarService.getInstance().StopCamera();
        TsDisplay.GetInstance().SetDispParat(-1);
        if (BackcarService.getInstance().bIsAvm360()) {
            MainSet.GetInstance().SetVideoChannel(0);
        }
        super.onPause();
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == 0) {
            Dvr.GetInstance().SendTouchXY(((int) event.getX()) / 4, (((int) event.getY()) * 32) / 75);
            this.DvrShow.DealKeyTouch();
            return true;
        }
        event.getAction();
        return true;
    }

    public void UserAll() {
        if (this.DvrShow.bCameraReady) {
            this.DvrShow.SignalDetect();
        } else {
            EnterDvr();
        }
    }
}
