package com.ts.can.hyundai.rzc;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.other.CustomImgView;

public class CanHyunDaiRzcNllctView extends CanRelativeCarInfoView {
    public static final String TAG = "CanHyunDaiRzcNllctView";
    private CustomImgView mImgCar;
    private CustomImgView[] mImgDc;
    private CustomImgView mImgDdj;
    private CustomImgView mImgFdj;
    private CanDataInfo.HyRzcXnySet3 mSetData;

    public CanHyunDaiRzcNllctView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        initCommonScreen();
    }

    public void doOnResume() {
        super.doOnResume();
    }

    private void initCommonScreen() {
        this.mSetData = new CanDataInfo.HyRzcXnySet3();
        this.mImgCar = addImage(Can.CAN_DFFG_S560, 131, 714, 295, R.drawable.can_hyundai_car);
        this.mImgFdj = addImage(Can.CAN_DFFG_S560, 131, 714, 295, R.drawable.can_hyundai_fdj);
        this.mImgDdj = addImage(Can.CAN_DFFG_S560, 131, 714, 295, R.drawable.can_hyundai_ddj);
        initNllct(0, 0);
        this.mImgDc = new CustomImgView[8];
        for (int i = 0; i < this.mImgDc.length; i++) {
            this.mImgDc[i] = addImage(685, (((this.mImgDc.length - 1) - i) * 9) + Can.CAN_TOYOTA_SP_XP, 81, 6, R.drawable.can_hyundai_dc);
            this.mImgDc[i].setVisibility(8);
        }
    }

    public void ResetData(boolean check) {
        CanJni.HyundaiRzcGetXnySet3(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            for (int i = 0; i < this.mImgDc.length; i++) {
                if (i < this.mSetData.Dcdl) {
                    this.mImgDc[i].setVisibility(0);
                } else {
                    this.mImgDc[i].setVisibility(8);
                }
            }
            switch (this.mSetData.Nllct) {
                case 0:
                    initNllct(0, 0);
                    return;
                case 1:
                    initNllct(0, 0);
                    return;
                case 2:
                    initNllct(0, 1);
                    return;
                case 3:
                    initNllct(1, 1);
                    return;
                case 4:
                case 7:
                    initNllct(1, 0);
                    return;
                case 5:
                case 6:
                case 8:
                case 9:
                case 10:
                case 11:
                    initNllct(0, 0);
                    return;
                default:
                    return;
            }
        }
    }

    private void initNllct(int mFdj, int mDdj) {
        int i;
        int i2 = 0;
        CustomImgView customImgView = this.mImgFdj;
        if (i2b(mFdj)) {
            i = 0;
        } else {
            i = 8;
        }
        customImgView.setVisibility(i);
        CustomImgView customImgView2 = this.mImgDdj;
        if (!i2b(mDdj)) {
            i2 = 8;
        }
        customImgView2.setVisibility(i2);
    }

    public void QueryData() {
        CanJni.HyundaiRzcQuery(85, 0);
    }
}
