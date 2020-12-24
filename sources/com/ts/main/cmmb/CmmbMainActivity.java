package com.ts.main.cmmb;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import com.ts.MainUI.Cmmb;
import com.ts.MainUI.Evc;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.TsDisplay;
import com.ts.MainUI.UserCallBack;
import com.ts.backcar.AutoFitTextureView;
import com.ts.backcar.BackcarService;
import com.ts.main.avin.AvShowMainItem;
import com.ts.main.common.MainSet;
import com.ts.main.common.MainUI;
import com.ts.main.common.ScreenSet;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class CmmbMainActivity extends Activity implements UserCallBack {
    private static final int CMMB_DELAY_NUM = 90;
    static int nOldMode = 255;
    ParamButton[] BtnCmmb = new ParamButton[8];
    AvShowMainItem CMMBShow = new AvShowMainItem();
    RelativeLayoutManager CmmbManage;
    int Iphone = 0;
    private int[] Ysj_btn_Icondn = {R.drawable.dtv_list_dn, R.drawable.dtv_up_dn, R.drawable.dtv_down_dn, R.drawable.dtv_left_dn, R.drawable.dtv_right_dn, R.drawable.dtv_ok_dn, R.drawable.dtv_return_dn};
    private int[] Ysj_btn_Iconup = {R.drawable.dtv_list_up, R.drawable.dtv_up_up, R.drawable.dtv_down_up, R.drawable.dtv_left_up, R.drawable.dtv_right_up, R.drawable.dtv_ok_up, R.drawable.dtv_return_up};
    /* access modifiers changed from: private */
    public Evc mEvc = Evc.GetInstance();
    long nTime = 0;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avin_main);
        this.CMMBShow.Inint(this, (RelativeLayout) findViewById(R.id.activity_avin_mainid), 2);
        this.CMMBShow.InintCommonBtn();
        if (MainSet.GetScreenType() == 7) {
            this.CMMBShow.GetVideoName().setText("同屏传输");
            new AlertDialog.Builder(this).setTitle("系统提示").setMessage("请根据您插入的手机选择相应的模式").setPositiveButton("苹果手机", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    CmmbMainActivity.this.Iphone = 1;
                    CmmbMainActivity.this.mEvc.evol_workmode_set(6);
                    dialog.dismiss();
                }
            }).setNegativeButton("安卓手机", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    CmmbMainActivity.this.Iphone = 0;
                    CmmbMainActivity.this.mEvc.evol_workmode_set(5);
                    dialog.dismiss();
                }
            }).show();
        } else {
            this.CMMBShow.GetVideoName().setText(R.string.title_activity_cmmb_main);
        }
        this.CmmbManage = new RelativeLayoutManager(this, R.id.activity_avin_mainid);
        if (FtSet.GetDtvType() == 1) {
            for (int i = 0; i < 7; i++) {
                this.BtnCmmb[i] = this.CmmbManage.AddButton((i * 137) + 44, 500, 118, 78);
                this.BtnCmmb[i].setDrawable(this.Ysj_btn_Iconup[i], this.Ysj_btn_Icondn[i]);
                this.BtnCmmb[i].setTag(Integer.valueOf(i));
                this.BtnCmmb[i].setOnClickListener(new View.OnClickListener() {
                    public void onClick(View arg0) {
                        int nTag = ((Integer) arg0.getTag()).intValue();
                        Log.i("Cmmb", "nTag==" + nTag);
                        CmmbMainActivity.this.CMMBShow.SetBtnDelay();
                        switch (nTag) {
                            case 0:
                                Cmmb.GetInstance().CmmbList();
                                return;
                            case 1:
                                Cmmb.GetInstance().CmmbUp();
                                return;
                            case 2:
                                Cmmb.GetInstance().CmmbDn();
                                return;
                            case 3:
                                Cmmb.GetInstance().CmmbLeft();
                                return;
                            case 4:
                                Cmmb.GetInstance().CmmbRight();
                                return;
                            case 5:
                                Cmmb.GetInstance().CmmbEnter();
                                return;
                            case 6:
                                Cmmb.GetInstance().CmmbReturn();
                                return;
                            default:
                                return;
                        }
                    }
                });
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == 0) {
            Log.i("onTouchEvent", "x = " + event.getX() + "y=" + event.getY());
            if (MainSet.GetScreenType() == 3) {
                Cmmb.GetInstance().SendTouch((int) ((event.getX() * 255.0f) / ((float) getResources().getDisplayMetrics().widthPixels)), (int) ((event.getY() * 255.0f) / ((float) getResources().getDisplayMetrics().heightPixels)));
            } else {
                Cmmb.GetInstance().SendTouch(((int) event.getX()) / 4, (((int) event.getY()) * 32) / 75);
            }
            if (FtSet.GetDtvType() != 0) {
                this.CMMBShow.DealKeyTouch();
            } else {
                this.nTime = SystemClock.uptimeMillis();
            }
        } else if (event.getAction() != 2 && event.getAction() == 1 && FtSet.GetDtvType() == 0 && SystemClock.uptimeMillis() - this.nTime > 1000) {
            this.CMMBShow.DealKeyTouch();
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        if (MainSet.GetScreenType() == 7) {
            this.CMMBShow.nDelayNum = 0;
        } else {
            this.CMMBShow.nDelayNum = 90;
        }
        MainTask.GetInstance().SetUserCallBack(this);
        if (MainSet.GetScreenType() != 7) {
            this.mEvc.evol_workmode_set(6);
        } else if (this.Iphone == 1) {
            this.mEvc.evol_workmode_set(6);
        } else {
            this.mEvc.evol_workmode_set(5);
        }
        Mcu.SetCmmbstate((byte) 1);
        this.CMMBShow.ShowMode(1, true);
        this.CMMBShow.nShowMode = 0;
        TsDisplay.GetInstance().SetDispParat(2);
        if (MainSet.GetInstance().IsTwcjw() || FtSet.GetExUart() == 1) {
            MainSet.GetInstance().SetVideoChannel(2);
        } else {
            MainSet.GetInstance().SetVideoChannel(1);
        }
        BackcarService.getInstance().StartCamera((AutoFitTextureView) findViewById(R.id.textureView), true);
        MainSet.GetInstance().TwShowTitle(getResources().getString(R.string.title_activity_cmmb_main));
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        ScreenSet.GetInstance().Hide();
        if (MainUI.IsCameraMode() == 1) {
            TsDisplay.GetInstance().SetSrcMute(5);
        }
        this.CMMBShow.nShowMode = 0;
        this.CMMBShow.ShowMode(2, true);
        this.CMMBShow.nShowMode = 0;
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        MainSet.GetInstance().TwShowTitle("");
        BackcarService.getInstance().StopCamera();
        TsDisplay.GetInstance().SetDispParat(-1);
        super.onPause();
    }

    /* access modifiers changed from: package-private */
    public void ShowCmmbBtn(boolean bShow) {
        if (FtSet.GetDtvType() == 1) {
            for (int i = 0; i < 7; i++) {
                if (bShow) {
                    this.BtnCmmb[i].setVisibility(0);
                } else {
                    this.BtnCmmb[i].setVisibility(4);
                }
            }
        }
    }

    public void UserAll() {
        this.CMMBShow.SignalDetect();
        if (nOldMode != this.CMMBShow.nShowMode) {
            nOldMode = this.CMMBShow.nShowMode;
            switch (this.CMMBShow.nShowMode) {
                case 1:
                    ShowCmmbBtn(true);
                    return;
                case 2:
                    ShowCmmbBtn(true);
                    return;
                case 3:
                    ShowCmmbBtn(true);
                    return;
                case 4:
                    ShowCmmbBtn(false);
                    return;
                default:
                    return;
            }
        }
    }
}
