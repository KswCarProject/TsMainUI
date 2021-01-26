package com.ts.can.mzd.rzc;

import android.app.Activity;
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
import com.ts.other.CustomImgView;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanMzdRzcCarTpmsView extends CanRelativeCarInfoView {
    private CanDataInfo.CAN_Msg mCanMsg;
    protected CustomImgView[] mIvTyres;
    RelativeLayoutManager mManager;
    private CanDataInfo.Mzd_Rzc_TpmsTime mTimeData;
    protected TextView[] mTvPress;
    protected TextView mTvStatus;
    protected TextView[] mTvTemp;
    protected TextView mTvTime;

    public CanMzdRzcCarTpmsView(Activity activity) {
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
        this.mIvTyres = new CustomImgView[4];
        this.mTvPress = new TextView[4];
        this.mTvTemp = new TextView[4];
        this.mCanMsg = new CanDataInfo.CAN_Msg();
        setBackgroundResource(R.drawable.can_rf_tpms_bg);
        if (MainSet.GetScreenType() == 5) {
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) this.mManager.GetLayout().getLayoutParams();
            lp.width = 1024;
            lp.height = CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST6;
            lp.gravity = 17;
            this.mManager.GetLayout().setLayoutParams(lp);
            initCommon();
            this.mManager.GetLayout().setScaleX(1.25f);
            this.mManager.GetLayout().setScaleY(0.78f);
        } else {
            initCommon();
        }
        this.mTimeData = new CanDataInfo.Mzd_Rzc_TpmsTime();
    }

    private void initCommon() {
        this.mTvTime = addText(352, 445, KeyDef.RKEY_MEDIA_SLOW, 50);
        this.mTvTime.setTextSize(24.0f);
        for (int i = 0; i < 4; i++) {
            this.mIvTyres[i] = addImage(((i % 2) * 132) + 431, ((i / 2) * 186) + 116);
            this.mIvTyres[i].setStateDrawable(R.drawable.can_rf_tpms_up, R.drawable.can_rf_tpms_dn);
            this.mTvPress[i] = addText(((i % 2) * CanCameraUI.BTN_TRUMPCHI_GS7_MODE4) + 91, ((i / 2) * Can.CAN_CC_HF_DJ) + 94, 281, 50);
            this.mTvPress[i].setTextSize(0, 35.0f);
            this.mTvPress[i].setTextColor(-1);
            this.mTvPress[i].setGravity(17);
            this.mTvTemp[i] = addText(((i % 2) * CanCameraUI.BTN_TRUMPCHI_GS7_MODE4) + 91, ((i / 2) * Can.CAN_CC_HF_DJ) + 95, 281, 0);
            this.mTvTemp[i].setTextSize(0, 35.0f);
            this.mTvTemp[i].setTextColor(-1);
            this.mTvTemp[i].setGravity(17);
        }
    }

    public void ResetData(boolean check) {
        CanJni.GetCarInfo(this.mCanMsg);
        if (i2b(this.mCanMsg.UpdateOnce) && (!check || i2b(this.mCanMsg.Update))) {
            this.mCanMsg.Update = 0;
            SetVal(0, this.mCanMsg.Tpms[0], this.mCanMsg.Tpms[0]);
            SetVal(1, this.mCanMsg.Tpms[1], this.mCanMsg.Tpms[1]);
            SetVal(2, this.mCanMsg.Tpms[2], this.mCanMsg.Tpms[2]);
            SetVal(3, this.mCanMsg.Tpms[3], this.mCanMsg.Tpms[3]);
        }
        CanJni.MzdRzcGetCarTpmsTimeSet(this.mTimeData);
        if (!i2b(this.mTimeData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mTimeData.Update)) {
            this.mTimeData.Update = 0;
            this.mTvTime.setText(String.format("%04d.%02d.%02d  %02d:%02d", new Object[]{Integer.valueOf(this.mTimeData.Year + 2018), Integer.valueOf(this.mTimeData.Month), Integer.valueOf(this.mTimeData.Day), Integer.valueOf(this.mTimeData.Hour), Integer.valueOf(this.mTimeData.Min)}));
        }
    }

    public void QueryData() {
        CanJni.MzdCx4Query(38, 0);
        CanJni.MzdCx4Query(39, 0);
    }

    public String GetPressStr(int press) {
        if (press == 255) {
            return "-.- bar";
        }
        return String.format("%.1f bar", new Object[]{Double.valueOf(((double) press) * 2.0d)});
    }

    public String GetTempStr(int temp) {
        if (temp == 255) {
            return "-- ℃";
        }
        return String.format("%d ℃", new Object[]{Integer.valueOf(temp - 40)});
    }

    public void SetVal(int id, int press, int temp) {
        this.mTvPress[id].setText(GetPressStr(press));
    }
}
