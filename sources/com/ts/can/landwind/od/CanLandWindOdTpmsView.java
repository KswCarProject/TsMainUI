package com.ts.can.landwind.od;

import android.app.Activity;
import android.support.v4.internal.view.SupportMenu;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;

public class CanLandWindOdTpmsView extends CanRelativeCarInfoView {
    public static final int ITEM_VOL = 0;
    public static final String TAG = "CanHtOdTpmsActivity";
    protected static CanDataInfo.LandWindOd_TpmsData mData;
    protected static CustomImgView[] mIvTyres = new CustomImgView[4];
    protected static CustomTextView[] mTvPress = new CustomTextView[4];
    protected static CustomTextView[] mTvTemp = new CustomTextView[4];
    protected static CustomTextView[] mTvWarn = new CustomTextView[4];
    protected static CanDataInfo.LandWindOd_TpmsWarn mWarn;
    private static final String[] mWarnArrays = {"胎压正常", "传感器失效", "低压报警", "高压报警"};
    protected CustomTextView mTvStatus;

    public CanLandWindOdTpmsView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View view) {
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        setBackgroundResource(R.drawable.can_rf_tpms_bg);
        for (int i = 0; i < 4; i++) {
            mIvTyres[i] = getRelativeManager().AddImage(((i % 2) * 132) + 431, ((i / 2) * 186) + 116);
            mIvTyres[i].setStateDrawable(R.drawable.can_rf_tpms_up, R.drawable.can_rf_tpms_dn);
            mTvPress[i] = getRelativeManager().AddCusText(((i % 2) * CanCameraUI.BTN_TRUMPCHI_GS7_MODE4) + 91, ((i / 2) * Can.CAN_CC_HF_DJ) + 49, 281, 50);
            mTvPress[i].SetPixelSize(35);
            mTvPress[i].setGravity(17);
            mTvTemp[i] = getRelativeManager().AddCusText(((i % 2) * CanCameraUI.BTN_TRUMPCHI_GS7_MODE4) + 91, ((i / 2) * Can.CAN_CC_HF_DJ) + 95, 281, 50);
            mTvTemp[i].SetPixelSize(35);
            mTvTemp[i].setGravity(17);
            mTvWarn[i] = getRelativeManager().AddCusText(((i % 2) * CanCameraUI.BTN_TRUMPCHI_GS7_MODE4) + 91, ((i / 2) * Can.CAN_CC_HF_DJ) + 144, 281, 50);
            mTvWarn[i].SetPixelSize(35);
            mTvWarn[i].setGravity(17);
        }
        this.mTvStatus = getRelativeManager().AddCusText(375, 45, 281, 50);
        this.mTvStatus.SetPixelSize(35);
        this.mTvStatus.setGravity(17);
        mData = new CanDataInfo.LandWindOd_TpmsData();
        mWarn = new CanDataInfo.LandWindOd_TpmsWarn();
    }

    public void ResetData(boolean check) {
        CanJni.LandWindOdGetTpmsData(mData);
        if (i2b(mData.UpdateOnce) && (!check || i2b(mData.Update))) {
            mData.Update = 0;
            SetVal(0, mData.FLPress, mData.FLTemp);
            SetVal(1, mData.FRPress, mData.FRTemp);
            SetVal(2, mData.RLPress, mData.RLTemp);
            SetVal(3, mData.RRPress, mData.RRTemp);
        }
        CanJni.LandWindOdGetTpmsWarn(mWarn);
        if (!i2b(mWarn.UpdateOnce)) {
            return;
        }
        if (!check || i2b(mWarn.Update)) {
            mWarn.Update = 0;
            setTyresInfos(0, mWarn.FLWarn[0], mWarn.FLWarn[1], mWarn.FLWarn[2]);
            setTyresInfos(1, mWarn.FRWarn[0], mWarn.FRWarn[1], mWarn.FRWarn[2]);
            setTyresInfos(2, mWarn.RLWarn[0], mWarn.RLWarn[1], mWarn.RLWarn[2]);
            setTyresInfos(3, mWarn.RRWarn[0], mWarn.RRWarn[1], mWarn.RRWarn[2]);
            if (i2b(mWarn.SystemVaild)) {
                this.mTvStatus.setText("系统失效");
                this.mTvStatus.setTextColor(SupportMenu.CATEGORY_MASK);
                return;
            }
            this.mTvStatus.setText("系统正常");
            this.mTvStatus.setTextColor(-1);
        }
    }

    private void setTyresInfos(int tpms, int warn1, int warn2, int warn3) {
        if (warn1 > 0 || warn2 > 0 || warn3 > 0) {
            mIvTyres[tpms].setSelected(true);
            if (warn1 > 0) {
                mTvWarn[tpms].setText(mWarnArrays[1]);
            } else if (warn2 > 0) {
                mTvWarn[tpms].setText(mWarnArrays[2]);
            } else if (warn3 > 0) {
                mTvWarn[tpms].setText(mWarnArrays[3]);
            }
            mTvWarn[tpms].setTextColor(SupportMenu.CATEGORY_MASK);
            return;
        }
        mIvTyres[tpms].setSelected(false);
        mTvWarn[tpms].setText(mWarnArrays[0]);
        mTvWarn[tpms].setTextColor(-1);
    }

    public String GetPressStr(int press) {
        if (press == 255) {
            return "--";
        }
        return String.format("%.2f kpa", new Object[]{Double.valueOf(((double) press) * 1.373d)});
    }

    public String GetTempStr(int temp) {
        return String.format("%d ℃", new Object[]{Integer.valueOf(temp - 40)});
    }

    public void SetVal(int id, int press, int temp) {
        mTvPress[id].setText(GetPressStr(press));
        mTvTemp[id].setText(GetTempStr(temp));
    }

    public void QueryData() {
        CanJni.LandWindOdQuery(56);
        CanJni.LandWindOdQuery(57);
    }
}
