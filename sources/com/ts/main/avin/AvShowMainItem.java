package com.ts.main.avin;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.MainUI.TsDisplay;
import com.ts.backcar.BackcarService;
import com.ts.main.common.KeyTouch;
import com.ts.main.common.MainSet;
import com.ts.main.common.MainUI;
import com.ts.main.common.MainVolume;
import com.ts.main.common.ScreenSet;
import com.ts.other.ParamButton;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;
import com.yyw.ts70xhw.StSet;

public class AvShowMainItem implements View.OnClickListener {
    private static final int AVIN_DELAY_TIME = 60;
    public static final int AV_SHOW_COM = 1;
    public static final int AV_SHOW_FROBIDEN = 3;
    public static final int AV_SHOW_FULL = 4;
    public static final int AV_SHOW_HIDEN = 5;
    public static final int AV_SHOW_LOADING = 6;
    public static final int AV_SHOW_NONE = 2;
    private static final String TAG = "AvShowMainItem";
    static boolean bHave = false;
    static int nCheckNum = 0;
    private ParamButton BtnChange;
    private ParamButton BtnHome;
    private ParamButton BtnMirror;
    private ParamButton BtnReturn;
    private ParamButton BtnSet;
    private ParamButton BtnVolume;
    private ImageView Imgforbiden;
    public boolean bCameraReady = false;
    boolean bFr = true;
    boolean bHaveChange = false;
    boolean bHaveMir = false;
    boolean bHaveVolBtn = false;
    boolean bTransWorkmode = true;
    Context mContext;
    private TsDisplay mDisplay = TsDisplay.GetInstance();
    private KeyTouch mKeyTouch = KeyTouch.GetInstance();
    ViewGroup mlayout;
    public int nDelayNum = 0;
    private int nDelayTimeShow = 0;
    public int nShowMode = 0;
    private int nShowType = 0;
    private ProgressBar pProgressLoading;
    private TextView videoname;
    private TextView videostatetext;

    public void Inint(Context context, ViewGroup layout, int nType) {
        ParamButton.initFactory(context, layout, 0);
        this.mContext = context;
        this.mlayout = layout;
        this.nShowType = nType;
    }

    public void SetMirrBtn(boolean bHave2) {
        this.bHaveMir = bHave2;
    }

    public void SetChangeBtn(boolean bHave2) {
        this.bHaveChange = bHave2;
    }

    public void SetIsHaveVol(boolean bHave2) {
        this.bHaveVolBtn = bHave2;
    }

    public void SetTransWorkmode(boolean bState) {
        this.bTransWorkmode = bState;
    }

    public void InintCommonBtn() {
        this.BtnHome = ParamButton.fsCreateRelative(0, 0, 80, 75);
        this.BtnHome.setStateDrawable(R.drawable.avin_menu_up, R.drawable.avin_menu_dn, R.drawable.avin_menu_dn);
        this.BtnHome.setTag(1);
        if (MainSet.GetScreenType() == 3 || MainSet.GetScreenType() == 6) {
            this.BtnReturn = ParamButton.fsCreateRelative(MainUI.mScrH - 80, 0, 80, 75);
            this.BtnVolume = ParamButton.fsCreateRelative(MainUI.mScrH - 160, 0, 80, 75);
            this.BtnSet = ParamButton.fsCreateRelative(MainUI.mScrH - 160, 0, 80, 75);
            this.BtnMirror = ParamButton.fsCreateRelative(MainUI.mScrH - 80, 120, 80, 75);
            this.BtnChange = ParamButton.fsCreateRelative(10, Can.CAN_CHRYSLER_ONE_HC, 82, 72);
        } else if (MainSet.GetScreenType() == 8) {
            ViewGroup.LayoutParams params = this.mlayout.getLayoutParams();
            params.height = 600;
            this.mlayout.setLayoutParams(params);
            this.BtnReturn = ParamButton.fsCreateRelative(MainUI.mScrW - 80, 0, 80, 75);
            this.BtnVolume = ParamButton.fsCreateRelative(MainUI.mScrW - 160, 0, 80, 75);
            this.BtnSet = ParamButton.fsCreateRelative(MainUI.mScrW - 160, 0, 80, 75);
            this.BtnMirror = ParamButton.fsCreateRelative(MainUI.mScrW - 80, 120, 80, 75);
            this.BtnChange = ParamButton.fsCreateRelative(10, Can.CAN_CHRYSLER_ONE_HC, 82, 72);
        } else {
            this.BtnReturn = ParamButton.fsCreateRelative(MainUI.mScrW - 80, 0, 80, 75);
            this.BtnVolume = ParamButton.fsCreateRelative(MainUI.mScrW - 160, 0, 80, 75);
            this.BtnSet = ParamButton.fsCreateRelative(MainUI.mScrW - 240, 0, 80, 75);
            this.BtnMirror = ParamButton.fsCreateRelative(MainUI.mScrW - 80, 120, 80, 75);
            this.BtnChange = ParamButton.fsCreateRelative(10, Can.CAN_CHRYSLER_ONE_HC, 82, 72);
        }
        RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams) this.BtnReturn.getLayoutParams();
        params2.addRule(11);
        params2.leftMargin = 0;
        this.BtnReturn.setLayoutParams(params2);
        this.BtnReturn.setId(View.generateViewId());
        RelativeLayout.LayoutParams params3 = (RelativeLayout.LayoutParams) this.BtnVolume.getLayoutParams();
        params3.addRule(0, this.BtnReturn.getId());
        params3.leftMargin = 0;
        this.BtnVolume.setLayoutParams(params3);
        this.BtnVolume.setId(View.generateViewId());
        RelativeLayout.LayoutParams params4 = (RelativeLayout.LayoutParams) this.BtnSet.getLayoutParams();
        params4.addRule(0, this.BtnVolume.getId());
        params4.leftMargin = 0;
        this.BtnSet.setLayoutParams(params4);
        this.BtnReturn.setStateDrawable(R.drawable.avin_return_up, R.drawable.avin_return_dn, R.drawable.avin_return_dn);
        this.BtnReturn.setTag(2);
        this.BtnSet.setStateDrawable(R.drawable.avin_set_up, R.drawable.avin_set_dn, R.drawable.avin_set_dn);
        this.BtnSet.setTag(3);
        this.BtnVolume.setStateDrawable(R.drawable.avin_sound_up, R.drawable.avin_sound_dn, R.drawable.avin_sound_dn);
        this.BtnVolume.setTag(4);
        this.BtnMirror.setStateDrawable(R.drawable.avin_mirror_up, R.drawable.avin_mirror_dn, R.drawable.avin_mirror_dn);
        this.BtnMirror.setTag(5);
        this.BtnChange.setStateDrawable(R.drawable.fcam_bot_up, R.drawable.fcam_bot_dn, R.drawable.fcam_bot_dn);
        this.BtnChange.setTag(6);
        this.BtnHome.setOnClickListener(this);
        this.BtnReturn.setOnClickListener(this);
        this.BtnSet.setOnClickListener(this);
        this.BtnVolume.setOnClickListener(this);
        this.BtnMirror.setOnClickListener(this);
        this.BtnChange.setOnClickListener(this);
        this.videostatetext = (TextView) this.mlayout.findViewById(R.id.avinvideoState);
        this.videoname = (TextView) this.mlayout.findViewById(R.id.avinHomeName);
        this.Imgforbiden = (ImageView) this.mlayout.findViewById(R.id.image_forbiden);
        this.pProgressLoading = (ProgressBar) this.mlayout.findViewById(R.id.loading_progress);
        this.pProgressLoading.setVisibility(4);
    }

    public ParamButton GetBtnSet() {
        return this.BtnSet;
    }

    public void SetBtnDelay() {
        this.nDelayTimeShow = 60;
    }

    public int ShowMode(int nMode, boolean bForce) {
        if (this.nShowMode == nMode && !bForce) {
            return 0;
        }
        ScreenSet.GetInstance().nAidlHide = 1;
        this.nDelayTimeShow = 0;
        if (bForce) {
            nCheckNum = 0;
        }
        switch (nMode) {
            case 1:
                this.nDelayTimeShow = 60;
                this.BtnHome.setVisibility(0);
                this.videoname.setVisibility(0);
                this.BtnReturn.setVisibility(0);
                if (this.bHaveVolBtn) {
                    this.BtnVolume.setVisibility(0);
                } else {
                    this.BtnVolume.setVisibility(8);
                }
                if (this.bHaveMir) {
                    this.BtnMirror.setVisibility(0);
                } else {
                    this.BtnMirror.setVisibility(8);
                }
                if (this.bHaveChange) {
                    this.BtnChange.setVisibility(0);
                } else {
                    this.BtnChange.setVisibility(8);
                }
                if (FtSet.GetTconAdj() == 1) {
                    this.BtnSet.setVisibility(0);
                } else {
                    this.BtnSet.setVisibility(8);
                }
                this.videostatetext.setVisibility(8);
                this.Imgforbiden.setVisibility(4);
                this.mDisplay.SetDisp(this.nShowType);
                this.pProgressLoading.setVisibility(4);
                break;
            case 2:
                this.BtnHome.setVisibility(0);
                this.BtnReturn.setVisibility(0);
                this.videoname.setVisibility(0);
                if (this.bHaveVolBtn) {
                    this.BtnVolume.setVisibility(0);
                } else {
                    this.BtnVolume.setVisibility(8);
                }
                this.BtnMirror.setVisibility(8);
                if (this.bHaveChange) {
                    this.BtnChange.setVisibility(0);
                } else {
                    this.BtnChange.setVisibility(8);
                }
                this.BtnSet.setVisibility(8);
                this.videostatetext.setVisibility(0);
                this.videostatetext.setText(R.string.video_state_none);
                this.Imgforbiden.setVisibility(4);
                this.pProgressLoading.setVisibility(4);
                break;
            case 3:
                this.BtnHome.setVisibility(0);
                this.BtnReturn.setVisibility(0);
                this.videoname.setVisibility(0);
                this.BtnMirror.setVisibility(8);
                this.BtnChange.setVisibility(8);
                if (this.bHaveVolBtn) {
                    this.BtnVolume.setVisibility(0);
                } else {
                    this.BtnVolume.setVisibility(8);
                }
                this.BtnSet.setVisibility(8);
                this.videostatetext.setVisibility(0);
                this.videostatetext.setText(R.string.video_state_forbiden);
                this.Imgforbiden.setVisibility(0);
                this.pProgressLoading.setVisibility(4);
                break;
            case 4:
                this.BtnMirror.setVisibility(8);
                this.BtnChange.setVisibility(8);
                this.BtnHome.setVisibility(8);
                this.BtnReturn.setVisibility(8);
                this.BtnSet.setVisibility(8);
                this.BtnVolume.setVisibility(8);
                this.videoname.setVisibility(8);
                this.videostatetext.setVisibility(0);
                this.videostatetext.setText(" ");
                this.mDisplay.SetDisp(this.nShowType);
                this.Imgforbiden.setVisibility(4);
                this.pProgressLoading.setVisibility(4);
                break;
            case 5:
                this.BtnHome.setVisibility(0);
                this.BtnReturn.setVisibility(0);
                this.videoname.setVisibility(0);
                if (this.bHaveVolBtn) {
                    this.BtnVolume.setVisibility(0);
                } else {
                    this.BtnVolume.setVisibility(8);
                }
                this.BtnMirror.setVisibility(8);
                this.BtnChange.setVisibility(8);
                this.BtnSet.setVisibility(8);
                this.videostatetext.setVisibility(8);
                this.pProgressLoading.setVisibility(4);
                break;
            case 6:
                this.BtnHome.setVisibility(0);
                this.BtnReturn.setVisibility(0);
                this.videoname.setVisibility(0);
                if (this.bHaveVolBtn) {
                    this.BtnVolume.setVisibility(0);
                } else {
                    this.BtnVolume.setVisibility(8);
                }
                this.BtnMirror.setVisibility(8);
                this.BtnChange.setVisibility(8);
                this.BtnSet.setVisibility(8);
                this.videostatetext.setVisibility(4);
                this.Imgforbiden.setVisibility(0);
                this.pProgressLoading.setVisibility(0);
                break;
        }
        this.nShowMode = nMode;
        return 1;
    }

    public void DealKeyTouch() {
        if (this.nShowMode == 1) {
            ShowMode(4, false);
        } else if (this.nShowMode == 4) {
            ShowMode(1, false);
        }
    }

    public boolean IsHaveSignal() {
        return MainSet.GetInstance().IsHaveCamSignal();
    }

    public boolean IsvideoForbiden() {
        return StSet.GetDriveVideo() == 1 && Mcu.GetBrake() == 0;
    }

    public void SignalDetect() {
        if (this.nDelayNum > 0) {
            ShowMode(6, false);
            this.nDelayNum--;
            return;
        }
        if (this.nShowMode == 1 && this.nDelayTimeShow > 0) {
            this.nDelayTimeShow--;
            if (this.nDelayTimeShow == 0) {
                if (ScreenSet.GetInstance().bShow) {
                    SetBtnDelay();
                } else {
                    ShowMode(4, false);
                }
            }
        }
        if (!IsHaveSignal()) {
            ShowMode(2, false);
        } else if (IsvideoForbiden() && !MainSet.bIsFrontCam) {
            ShowMode(3, false);
        } else if (this.nShowMode != 4) {
            ShowMode(1, false);
        }
    }

    public TextView GetVideoName() {
        return this.videoname;
    }

    public void ChangerToFR(boolean bFcam) {
        if (bFcam) {
            this.BtnChange.setStateDrawable(R.drawable.fcam_bot_up, R.drawable.fcam_bot_dn, R.drawable.fcam_bot_dn);
        } else {
            this.BtnChange.setStateDrawable(R.drawable.rcam_bot_up, R.drawable.rcam_bot_dn, R.drawable.rcam_bot_dn);
        }
        this.bFr = bFcam;
    }

    public void onClick(View arg0) {
        switch (((Integer) arg0.getTag()).intValue()) {
            case 1:
                this.mKeyTouch.sendKeyClick(3);
                return;
            case 2:
                this.mKeyTouch.sendKeyClick(3);
                if (this.bTransWorkmode) {
                    Evc.GetInstance().evol_workmode_set(0);
                    return;
                }
                return;
            case 3:
                ScreenSet.GetInstance().nAidlShow = this.nShowType;
                return;
            case 4:
                MainVolume.GetInstance().VolWinShow();
                return;
            case 5:
                if (FtSet.GetFcamMir() == 1) {
                    FtSet.SetFcamMir(0);
                } else {
                    FtSet.SetFcamMir(1);
                }
                BackcarService.getInstance().ReStartFCamera();
                return;
            case 6:
                if (this.bFr) {
                    ChangerToFR(false);
                    TsDisplay.GetInstance().SetSrcMute(16);
                    MainSet.GetInstance().SetVideoChannel(0);
                    GetVideoName().setText(R.string.title_activity_camera_main);
                    return;
                }
                ChangerToFR(true);
                TsDisplay.GetInstance().SetSrcMute(16);
                MainSet.GetInstance().SetVideoChannel(2);
                GetVideoName().setText(R.string.title_activity_fcamera_main);
                return;
            default:
                return;
        }
    }
}
