package com.ts.main.dvr;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import com.ts.MainUI.Dvr;
import com.ts.MainUI.Evc;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.TsDisplay;
import com.ts.MainUI.TsFile;
import com.ts.MainUI.UserCallBack;
import com.ts.backcar.AutoFitTextureView;
import com.ts.backcar.BackcarService;
import com.ts.can.CanIF;
import com.ts.main.avin.AvShowMainItem;
import com.ts.main.common.MainSet;
import com.ts.main.common.ScreenSet;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;
import java.io.IOException;

public class DvrMainActivity extends Activity implements UserCallBack {
    private static final int DVR_DELAY_NUM = 50;
    static final String TS_CAM_FILE = "cam.ini";
    static final String TS_CAM_PATH = "/mnt/sdcard/cam/";
    ParamButton[] BtnDvr = new ParamButton[8];
    RelativeLayoutManager DvrManage;
    AvShowMainItem DvrShow = new AvShowMainItem();
    private int[] Ysj_btn1_Icondn = {R.drawable.driving_gnxd_dn, R.drawable.driving_lykg_dn, R.drawable.driving_msqh_dn, R.drawable.driving_play_dn1, R.drawable.driving_prv_dn, R.drawable.driving_next_dn};
    private int[] Ysj_btn1_Iconup = {R.drawable.driving_gnxd_up, R.drawable.driving_lykg_up, R.drawable.driving_msqh_up, R.drawable.driving_play_up1, R.drawable.driving_prv_up, R.drawable.driving_next_up};
    private int[] Ysj_btn_Icondn = {R.drawable.driving_menu_dn, R.drawable.driving_return_dn, R.drawable.driving_lock_dn, R.drawable.driving_hf_dn, R.drawable.driving_seekup_dn, R.drawable.driving_play_dn, R.drawable.driving_seekdn_dn};
    private int[] Ysj_btn_Iconup = {R.drawable.driving_menu_up, R.drawable.driving_return_up, R.drawable.driving_lock_up, R.drawable.driving_hf_up, R.drawable.driving_seekup_up, R.drawable.driving_play_up, R.drawable.driving_seekdn_up};
    private Evc mEvc = Evc.GetInstance();
    private int nDelayNum = 0;

    /* access modifiers changed from: package-private */
    public boolean bIsWr() {
        if (getResources().getString(R.string.custom_spesail_install).equals(MainSet.SP_LM_WR)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void SetClick(int nBtn) {
        for (int i = 0; i < 4; i++) {
            this.BtnDvr[i].setSelected(false);
        }
        this.BtnDvr[nBtn].setSelected(true);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        if (CanIF.BmwWithCDCmd(2, 1).booleanValue()) {
            super.onCreate(savedInstanceState);
            finish();
            return;
        }
        setContentView(R.layout.activity_avin_main);
        this.DvrShow.Inint(this, (RelativeLayout) findViewById(R.id.activity_avin_mainid), 3);
        this.DvrShow.InintCommonBtn();
        this.DvrShow.GetVideoName().setText(R.string.title_activity_dvr_main);
        this.DvrManage = new RelativeLayoutManager(this, R.id.activity_avin_mainid);
        switch (FtSet.GetDvrType()) {
            case 0:
                if (bIsWr()) {
                    for (int i = 0; i < 4; i++) {
                        this.BtnDvr[i] = this.DvrManage.AddButton((i * 151) + 300, 20, 130, 76);
                        this.BtnDvr[i].setDrawable(R.drawable.cstudy_public_up, R.drawable.cstudy_public_dn);
                        this.BtnDvr[i].setTag(Integer.valueOf(i));
                        this.BtnDvr[i].setText("Cam" + (i + 1));
                        this.BtnDvr[i].setOnClickListener(new View.OnClickListener() {
                            public void onClick(View arg0) {
                                int nTag = ((Integer) arg0.getTag()).intValue();
                                try {
                                    TsFile.writeFileSdcardFile("/mnt/sdcard/cam/cam.ini", new StringBuilder().append(nTag).toString());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                DvrMainActivity.this.SetClick(nTag);
                                switch (nTag) {
                                    case 0:
                                        TsDisplay.GetInstance().SetDispParat(0);
                                        TsDisplay.GetInstance().SetSrcMute(20);
                                        MainSet.GetInstance().SetVideoChannel(0);
                                        return;
                                    case 1:
                                        TsDisplay.GetInstance().SetDispParat(2);
                                        TsDisplay.GetInstance().SetSrcMute(20);
                                        MainSet.GetInstance().SetVideoChannel(1);
                                        return;
                                    case 2:
                                        Mcu.SetFcamstate((byte) 1);
                                        TsDisplay.GetInstance().SetSrcMute(20);
                                        TsDisplay.GetInstance().SetDispParat(1);
                                        MainSet.GetInstance().SetVideoChannel(2);
                                        return;
                                    case 3:
                                        Mcu.SetFcamstate((byte) 0);
                                        TsDisplay.GetInstance().SetSrcMute(20);
                                        TsDisplay.GetInstance().SetDispParat(1);
                                        MainSet.GetInstance().SetVideoChannel(2);
                                        return;
                                    default:
                                        return;
                                }
                            }
                        });
                    }
                    break;
                }
                break;
            case 1:
                for (int i2 = 0; i2 < 6; i2++) {
                    this.BtnDvr[i2] = this.DvrManage.AddButton((i2 * 151) + 63, 500, 135, 82);
                    this.BtnDvr[i2].setDrawable(this.Ysj_btn1_Iconup[i2], this.Ysj_btn1_Icondn[i2]);
                    this.BtnDvr[i2].setTag(Integer.valueOf(i2));
                    this.BtnDvr[i2].setOnClickListener(new View.OnClickListener() {
                        public void onClick(View arg0) {
                            int nTag = ((Integer) arg0.getTag()).intValue();
                            DvrMainActivity.this.DvrShow.SetBtnDelay();
                            Dvr.GetInstance().BtnFun(nTag);
                        }
                    });
                }
                break;
            case 10:
                for (int i3 = 0; i3 < 7; i3++) {
                    if (MainSet.GetScreenType() == 5) {
                        this.BtnDvr[i3] = this.DvrManage.AddButton((i3 * 137) + 172, 390, 118, 78);
                    } else {
                        this.BtnDvr[i3] = this.DvrManage.AddButton((i3 * 137) + 44, 500, 118, 78);
                    }
                    this.BtnDvr[i3].setDrawable(this.Ysj_btn_Iconup[i3], this.Ysj_btn_Icondn[i3]);
                    this.BtnDvr[i3].setTag(Integer.valueOf(i3));
                    this.BtnDvr[i3].setOnClickListener(new View.OnClickListener() {
                        public void onClick(View arg0) {
                            int nTag = ((Integer) arg0.getTag()).intValue();
                            DvrMainActivity.this.DvrShow.SetBtnDelay();
                            Dvr.GetInstance().BtnFun(nTag);
                        }
                    });
                }
                break;
        }
        super.onCreate(savedInstanceState);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        if (bIsWr()) {
            this.DvrShow.nDelayNum = 0;
        } else {
            this.DvrShow.nDelayNum = 50;
        }
        MainTask.GetInstance().SetUserCallBack(this);
        super.onResume();
        this.DvrShow.ShowMode(1, true);
        this.DvrShow.nShowMode = 0;
        MainSet.GetInstance().TwShowTitle(getResources().getString(R.string.title_activity_dvr_main));
        if (bIsWr()) {
            String string = "";
            if (TsFile.fileIsExists("/mnt/sdcard/cam/cam.ini")) {
                try {
                    string = TsFile.readFileSdcardFile("/mnt/sdcard/cam/cam.ini");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                TsFile.NewDir(TS_CAM_PATH);
            }
            if (string.equals("0")) {
                TsDisplay.GetInstance().SetDispParat(0);
                MainSet.GetInstance().SetVideoChannel(0);
                SetClick(0);
            } else if (string.equals(MainSet.SP_XPH5)) {
                SetClick(1);
                TsDisplay.GetInstance().SetDispParat(2);
                MainSet.GetInstance().SetVideoChannel(1);
            } else if (string.equals(MainSet.SP_RLF_KORON)) {
                SetClick(2);
                Mcu.SetFcamstate((byte) 1);
                TsDisplay.GetInstance().SetDispParat(1);
                MainSet.GetInstance().SetVideoChannel(2);
            } else {
                SetClick(3);
                Mcu.SetFcamstate((byte) 0);
                TsDisplay.GetInstance().SetDispParat(1);
                MainSet.GetInstance().SetVideoChannel(2);
            }
        } else {
            TsDisplay.GetInstance().SetDispParat(3);
            MainSet.GetInstance().SetVideoChannel(1);
        }
        BackcarService.getInstance().StartCamera((AutoFitTextureView) findViewById(R.id.textureView), true);
        if (FtSet.GetDvrType() == 30) {
            this.mEvc.evol_workmode_set(6);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        ScreenSet.GetInstance().Hide();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        this.DvrShow.ShowMode(2, false);
        this.DvrShow.nShowMode = 0;
        MainSet.GetInstance().TwShowTitle("");
        BackcarService.getInstance().StopCamera();
        TsDisplay.GetInstance().SetDispParat(-1);
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
        this.DvrShow.SignalDetect();
    }
}
