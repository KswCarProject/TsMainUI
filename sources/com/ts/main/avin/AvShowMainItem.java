package com.ts.main.avin;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.MainUI.TsDisplay;
import com.ts.backcar.AutoFitTextureView;
import com.ts.can.toyota.dj.CanToyotaDJCarDeviceView;
import com.ts.main.common.KeyTouch;
import com.ts.main.common.MainSet;
import com.ts.main.common.MainUI;
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
    static boolean bHave = false;
    static int nCheckNum = 0;
    private AutoFitTextureView AFtex;
    private ParamButton BtnHome;
    private ParamButton BtnReturn;
    private ParamButton BtnSet;
    Context mContext;
    private TsDisplay mDisplay = TsDisplay.GetInstance();
    private KeyTouch mKeyTouch = KeyTouch.GetInstance();
    ViewGroup mlayout;
    public int nCheckTime = 0;
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

    public void InintCommonBtn() {
        this.BtnHome = ParamButton.fsCreateRelative(0, 0, 80, 75);
        this.BtnHome.setStateDrawable(R.drawable.avin_menu_up, R.drawable.avin_menu_dn, R.drawable.avin_menu_dn);
        this.BtnHome.setTag(1);
        if (MainSet.GetScreenType() == 3 || MainSet.GetScreenType() == 6) {
            this.BtnReturn = ParamButton.fsCreateRelative(MainUI.mScrH - 80, 0, 80, 75);
            this.BtnSet = ParamButton.fsCreateRelative(MainUI.mScrH - 160, 0, 80, 75);
        } else if (MainSet.GetScreenType() == 8) {
            ViewGroup.LayoutParams params = this.mlayout.getLayoutParams();
            params.height = CanToyotaDJCarDeviceView.ITEM_PLAY;
            this.mlayout.setLayoutParams(params);
            this.BtnReturn = ParamButton.fsCreateRelative(MainUI.mScrW - 80, 0, 80, 75);
            this.BtnSet = ParamButton.fsCreateRelative(MainUI.mScrW - 160, 0, 80, 75);
        } else {
            this.BtnReturn = ParamButton.fsCreateRelative(MainUI.mScrW - 80, 0, 80, 75);
            this.BtnSet = ParamButton.fsCreateRelative(MainUI.mScrW - 160, 0, 80, 75);
        }
        RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams) this.BtnReturn.getLayoutParams();
        params2.addRule(11);
        params2.leftMargin = 0;
        this.BtnReturn.setLayoutParams(params2);
        this.BtnReturn.setId(View.generateViewId());
        RelativeLayout.LayoutParams params3 = (RelativeLayout.LayoutParams) this.BtnSet.getLayoutParams();
        params3.addRule(0, this.BtnReturn.getId());
        params3.leftMargin = 0;
        this.BtnSet.setLayoutParams(params3);
        this.BtnReturn.setStateDrawable(R.drawable.avin_return_up, R.drawable.avin_return_dn, R.drawable.avin_return_dn);
        this.BtnReturn.setTag(2);
        this.BtnSet.setStateDrawable(R.drawable.avin_set_up, R.drawable.avin_set_dn, R.drawable.avin_set_dn);
        this.BtnSet.setTag(3);
        this.BtnHome.setOnClickListener(this);
        this.BtnReturn.setOnClickListener(this);
        this.BtnSet.setOnClickListener(this);
        this.videostatetext = (TextView) this.mlayout.findViewById(R.id.avinvideoState);
        this.videoname = (TextView) this.mlayout.findViewById(R.id.avinHomeName);
        this.AFtex = (AutoFitTextureView) this.mlayout.findViewById(R.id.textureView);
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
                if (FtSet.GetTconAdj() == 1) {
                    this.BtnSet.setVisibility(0);
                } else {
                    this.BtnSet.setVisibility(8);
                }
                this.videostatetext.setVisibility(8);
                this.AFtex.setVisibility(0);
                this.mDisplay.SetDisp(this.nShowType);
                this.pProgressLoading.setVisibility(4);
                break;
            case 2:
                this.BtnHome.setVisibility(0);
                this.BtnReturn.setVisibility(0);
                this.videoname.setVisibility(0);
                this.BtnSet.setVisibility(8);
                this.videostatetext.setVisibility(0);
                this.videostatetext.setText(R.string.video_state_none);
                this.AFtex.setVisibility(4);
                this.pProgressLoading.setVisibility(4);
                break;
            case 3:
                this.BtnHome.setVisibility(0);
                this.BtnReturn.setVisibility(0);
                this.videoname.setVisibility(0);
                this.BtnSet.setVisibility(8);
                this.videostatetext.setVisibility(0);
                this.videostatetext.setText(R.string.video_state_forbiden);
                this.AFtex.setVisibility(4);
                this.pProgressLoading.setVisibility(4);
                break;
            case 4:
                this.BtnHome.setVisibility(8);
                this.BtnReturn.setVisibility(8);
                this.BtnSet.setVisibility(8);
                this.videoname.setVisibility(8);
                this.videostatetext.setVisibility(0);
                this.videostatetext.setText(" ");
                this.mDisplay.SetDisp(this.nShowType);
                this.AFtex.setVisibility(0);
                this.pProgressLoading.setVisibility(4);
                break;
            case 5:
                this.BtnHome.setVisibility(0);
                this.BtnReturn.setVisibility(0);
                this.videoname.setVisibility(0);
                this.BtnSet.setVisibility(8);
                this.videostatetext.setVisibility(8);
                this.AFtex.setVisibility(4);
                this.pProgressLoading.setVisibility(4);
                break;
            case 6:
                this.BtnHome.setVisibility(0);
                this.BtnReturn.setVisibility(0);
                this.videoname.setVisibility(0);
                this.BtnSet.setVisibility(8);
                this.videostatetext.setVisibility(4);
                this.AFtex.setVisibility(4);
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
        if (nCheckNum % 30 == 0) {
            if (nCheckNum != 0) {
                bHave = MainSet.GetInstance().IsHaveCamSignal();
            } else {
                bHave = true;
            }
        }
        nCheckNum++;
        return bHave;
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
        } else if (IsvideoForbiden()) {
            ShowMode(3, false);
        } else if (this.nShowMode != 4) {
            ShowMode(1, false);
        }
    }

    public TextView GetVideoName() {
        return this.videoname;
    }

    public void onClick(View arg0) {
        switch (((Integer) arg0.getTag()).intValue()) {
            case 1:
                this.mKeyTouch.sendKeyClick(3);
                return;
            case 2:
                this.mKeyTouch.sendKeyClick(3);
                Evc.GetInstance().evol_workmode_set(0);
                return;
            case 3:
                ScreenSet.GetInstance().nAidlShow = this.nShowType;
                return;
            default:
                return;
        }
    }
}
