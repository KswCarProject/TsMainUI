package com.ts.can.mzd.rzc;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.main.common.MainSet;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;

public class CanMzdRzcTxtView extends CanRelativeCarInfoView {
    CanDataInfo.Mzd_Rzc_AudioInfo mAudioData;
    private ParamButton mBtnMode;
    protected RelativeLayoutManager mManager;
    private TextView mTxt;

    public CanMzdRzcTxtView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View arg0) {
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = getRelativeManager();
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        if (MainSet.GetScreenType() == 5) {
            initLayoutParams();
        } else if (mDisplayMetrics.heightPixels == 400 && mDisplayMetrics.widthPixels == 1024) {
            initLayoutParams();
        }
        initBaseUI();
        if (MainSet.GetScreenType() == 5) {
            this.mManager.GetLayout().setScaleX(1.25f);
            this.mManager.GetLayout().setScaleY(0.78f);
        } else if (mDisplayMetrics.heightPixels == 400 && mDisplayMetrics.widthPixels == 1024) {
            this.mManager.GetLayout().setScaleY(0.63f);
        }
    }

    private void initLayoutParams() {
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) this.mManager.GetLayout().getLayoutParams();
        lp.width = 1024;
        lp.height = CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST6;
        lp.gravity = 17;
        this.mManager.GetLayout().setLayoutParams(lp);
    }

    private void initBaseUI() {
        setBackgroundResource(R.drawable.can_jeep_ycsb_bg02);
        addImage(138, Can.CAN_MZD_TXB, R.drawable.can_yg_radio_bg);
        this.mAudioData = new CanDataInfo.Mzd_Rzc_AudioInfo();
        this.mTxt = addText(0, 172, 1024, 100);
        this.mTxt.setGravity(17);
        this.mTxt.setTextSize(0, 50.0f);
        this.mTxt.setTextColor(-1);
        this.mBtnMode = addButton(431, 353);
        this.mBtnMode.setStateUpDn(R.drawable.can_yg_radio_rect01_up, R.drawable.can_yg_radio_rect01_dn);
        this.mBtnMode.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (CanMzdRzcTxtView.this.mAudioData.Src == 3) {
                    CanJni.MzdRzcCarAudioSet(4, 4);
                } else if (CanMzdRzcTxtView.this.mAudioData.Src == 5) {
                    CanJni.MzdRzcCarAudioSet(4, 1);
                }
            }
        });
        this.mBtnMode.setTextSize(0, 36.0f);
        this.mBtnMode.setTextColor(-1);
        this.mBtnMode.setText("MODE");
        this.mBtnMode.setPadding(0, 3, 0, 0);
    }

    public void ResetData(boolean check) {
        CanJni.MzdRzcGetCarAudioInfo(this.mAudioData);
        if (!i2b(this.mAudioData.UpdateOnce)) {
            return;
        }
        if (check && !i2b(this.mAudioData.UpdateOnce)) {
            return;
        }
        if (this.mAudioData.Src == 3) {
            this.mTxt.setText("Android");
        } else if (this.mAudioData.Src == 5) {
            this.mTxt.setText("AUX");
        }
    }

    public void QueryData() {
    }
}
