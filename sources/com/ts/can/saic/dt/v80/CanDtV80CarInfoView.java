package com.ts.can.saic.dt.v80;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;

public class CanDtV80CarInfoView extends CanRelativeCarInfoView {
    public static final int ITEM_BATTERY = 1;
    public static final int ITEM_BATTERY_GROUP = 0;
    public static final int ITEM_BMS = 3;
    public static final int ITEM_DETAILS_INFOS = 2;
    protected ParamButton mBtnBattery;
    protected ParamButton mBtnBatteryGroup;
    protected ParamButton mBtnBms;
    protected ParamButton mBtnDetailsInfos;
    protected CustomTextView mTvBattery;
    protected CustomTextView mTvBatteryGroup;
    protected CustomTextView mTvBms;
    protected CustomTextView mTvDetailInfos;

    public CanDtV80CarInfoView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View v) {
        enterSubWin(CanCarInfoSub1Activity.class, ((Integer) v.getTag()).intValue());
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        boolean z = true;
        int x = 0;
        if (CanJni.GetSubType() == 0) {
            x = 110;
        } else if (CanJni.GetSubType() == 1) {
            x = Can.CAN_NISSAN_XFY;
        } else if (CanJni.GetSubType() == 2) {
            x = Can.CAN_NISSAN_XFY;
        }
        this.mBtnBatteryGroup = addButtonState(x, 132, R.drawable.can_jl_mc_up, R.drawable.can_jl_mc_dn);
        this.mBtnBattery = addButtonState(x + 210, 132, R.drawable.can_jl_kc_up, R.drawable.can_jl_kc_dn);
        this.mBtnDetailsInfos = addButtonState(x + 420, 132, R.drawable.can_jl_dcxx_up, R.drawable.can_jl_dcxx_dn);
        this.mBtnBms = addButtonState(750, 132, R.drawable.can_jl_jdxx_up, R.drawable.can_jl_jdxx_dn);
        setIdClickListener(this.mBtnBatteryGroup, 0);
        setIdClickListener(this.mBtnBattery, 1);
        setIdClickListener(this.mBtnDetailsInfos, 2);
        setIdClickListener(this.mBtnBms, 3);
        this.mTvBatteryGroup = AddText(x - 20, 287, 188, 40);
        this.mTvBattery = AddText((x + 210) - 20, 287, 188, 40);
        this.mTvDetailInfos = AddText((x + 420) - 20, 287, 188, 40);
        this.mTvBms = AddText(730, 287, 188, 40);
        this.mTvBatteryGroup.setText("电池组信息");
        this.mTvDetailInfos.setText("电池详细信息");
        this.mTvBms.setText("BMS诊断信息");
        if (CanJni.GetSubType() == 0) {
            this.mTvBattery.setText("电池充电状态");
            this.mTvDetailInfos.setText("电池详细信息");
        } else if (CanJni.GetSubType() == 1) {
            this.mTvBattery.setText("电机详细信息");
            this.mTvDetailInfos.setText("电池详细信息");
        } else if (CanJni.GetSubType() == 2) {
            this.mTvBattery.setText("电机详细信息");
            this.mTvDetailInfos.setText("BMS状态");
        }
        setShowGone((View) this.mBtnBms, CanJni.GetSubType() == 0);
        CustomTextView customTextView = this.mTvBms;
        if (CanJni.GetSubType() != 0) {
            z = false;
        }
        setShowGone((View) customTextView, z);
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }

    /* access modifiers changed from: protected */
    public CustomTextView AddText(int x, int y, int w, int h) {
        CustomTextView temp = getRelativeManager().AddCusText(x, y, w, h);
        temp.SetPixelSize(30);
        temp.setGravity(17);
        return temp;
    }
}
