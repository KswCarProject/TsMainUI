package com.ts.can.saic.t60_rzc;

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
import com.txznet.sdk.TXZResourceManager;

public class CanDtT60RzcTpmsView extends CanRelativeCarInfoView {
    private CanDataInfo.CAN_Msg mCanMsg;
    protected CustomImgView[] mIvTyres;
    RelativeLayoutManager mManager;
    protected TextView[] mTvPress;
    protected TextView mTvStatus;
    protected TextView[] mTvTemp;

    public CanDtT60RzcTpmsView(Activity activity) {
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
            return;
        }
        initCommon();
    }

    private void initCommon() {
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
        if (!i2b(this.mCanMsg.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCanMsg.Update)) {
            this.mCanMsg.Update = 0;
            SetVal(0, this.mCanMsg.Tpms[0], 0);
            SetVal(1, this.mCanMsg.Tpms[1], 0);
            SetVal(2, this.mCanMsg.Tpms[2], 0);
            SetVal(3, this.mCanMsg.Tpms[3], 0);
        }
    }

    public void QueryData() {
        CanJni.DtT60RzcQuery(37, 0);
    }

    public String GetPressStr(int press) {
        if (press == 255) {
            return "-.- bar";
        }
        return String.format("%.2f bar", new Object[]{Double.valueOf(((double) press) * 2.75d)});
    }

    public String GetTempStr(int temp) {
        if (temp == 255) {
            return "-- ℃";
        }
        return String.format("%d ℃", new Object[]{Integer.valueOf(temp - 40)});
    }

    public void SetVal(int id, int press, int temp) {
        this.mTvPress[id].setText(GetPressStr(press));
        this.mTvTemp[id].setText(TXZResourceManager.STYLE_DEFAULT);
    }
}
