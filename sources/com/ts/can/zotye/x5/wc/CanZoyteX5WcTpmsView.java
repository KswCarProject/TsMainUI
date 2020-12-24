package com.ts.can.zotye.x5.wc;

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

public class CanZoyteX5WcTpmsView extends CanRelativeCarInfoView {
    public static final String TAG = "CanZoyteX5WcTpmsView";
    private static final String[] mWarnStatus = {"正常", "系统自检", "快速漏气", "慢速漏气", "气压高", "气压低", "超高温", "高温", "电池电量低"};
    protected CustomImgView[] mIvTyres;
    protected CustomTextView[] mTvPress;
    protected CustomTextView mTvStatus;
    protected CustomTextView[] mTvTemp;
    protected CustomTextView[] mTvWarn;
    private CanDataInfo.ZtDmX5_TpmsData tpmsData;

    public CanZoyteX5WcTpmsView(Activity activity) {
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
        setBackgroundResource(R.drawable.can_rf_tpms_bg);
        initCommonScreen();
        this.tpmsData = new CanDataInfo.ZtDmX5_TpmsData();
    }

    private void initCommonScreen() {
        this.mTvWarn = new CustomTextView[4];
        this.mTvTemp = new CustomTextView[4];
        this.mTvPress = new CustomTextView[4];
        this.mIvTyres = new CustomImgView[4];
        for (int i = 0; i < 4; i++) {
            this.mIvTyres[i] = getRelativeManager().AddImage(((i % 2) * 132) + 431, ((i / 2) * 186) + 116);
            this.mIvTyres[i].setStateDrawable(R.drawable.can_rf_tpms_up, R.drawable.can_rf_tpms_dn);
            this.mTvPress[i] = getRelativeManager().AddCusText(((i % 2) * CanCameraUI.BTN_TRUMPCHI_GS7_MODE4) + 91, ((i / 2) * Can.CAN_CC_HF_DJ) + 49, 281, 50);
            this.mTvPress[i].SetPixelSize(35);
            this.mTvPress[i].setGravity(17);
            this.mTvTemp[i] = getRelativeManager().AddCusText(((i % 2) * CanCameraUI.BTN_TRUMPCHI_GS7_MODE4) + 91, ((i / 2) * Can.CAN_CC_HF_DJ) + 95, 281, 50);
            this.mTvTemp[i].SetPixelSize(35);
            this.mTvTemp[i].setGravity(17);
            this.mTvWarn[i] = getRelativeManager().AddCusText(((i % 2) * CanCameraUI.BTN_TRUMPCHI_GS7_MODE4) + 91, ((i / 2) * Can.CAN_CC_HF_DJ) + 144, 281, 50);
            this.mTvWarn[i].SetPixelSize(35);
            this.mTvWarn[i].setGravity(17);
        }
        this.mTvStatus = getRelativeManager().AddCusText(375, 45, 281, 50);
        this.mTvStatus.SetPixelSize(35);
        this.mTvStatus.setGravity(17);
        getRelativeManager().AddImage(375, 55, R.drawable.can_rf_tpms_line);
    }

    public void ResetData(boolean check) {
        CanJni.ZtDmX5GetTpmsData(this.tpmsData);
        if (!i2b(this.tpmsData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.tpmsData.Update)) {
            for (int i = 0; i < 4; i++) {
                SetVal(i, this.tpmsData.Pre[i], this.tpmsData.Temp[i]);
            }
            for (int i2 = 0; i2 < 4; i2++) {
                setWarn(i2, this.tpmsData.Sta[i2]);
            }
        }
    }

    public void QueryData() {
    }

    public void setWarn(int id, int warn) {
        if (warn >= 0 && warn < mWarnStatus.length) {
            if (warn == 0) {
                this.mTvWarn[id].setTextColor(-1);
                this.mIvTyres[id].setSelected(false);
            } else {
                this.mTvWarn[id].setTextColor(SupportMenu.CATEGORY_MASK);
                this.mIvTyres[id].setSelected(true);
            }
            this.mTvWarn[id].setText(mWarnStatus[warn]);
        }
    }

    public void SetVal(int id, int press, int temp) {
        this.mTvPress[id].setText(GetPressStr(press));
        this.mTvTemp[id].setText(GetTempStr(temp));
    }

    public String GetPressStr(int press) {
        if (press == 65535) {
            return "-.- Kpa";
        }
        return String.format("%.1f Kpa", new Object[]{Float.valueOf(((float) press) * 1.0f)});
    }

    public String GetTempStr(int temp) {
        if (temp == 65535) {
            return "-- ℃";
        }
        return String.format("%d ℃", new Object[]{Integer.valueOf(temp - 40)});
    }
}
