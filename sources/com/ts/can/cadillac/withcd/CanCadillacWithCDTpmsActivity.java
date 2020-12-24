package com.ts.can.cadillac.withcd;

import android.os.Bundle;
import android.support.v4.internal.view.SupportMenu;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCameraUI;
import com.ts.canview.CanItemProgressList;
import com.ts.main.common.MainSet;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanCadillacWithCDTpmsActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener, CanItemProgressList.onPosChange {
    public static final int ITEM_VOL = 0;
    public static final String TAG = "CanCadillacWithCDTpmsActivity";
    protected CanDataInfo.CadillacTpms mData = new CanDataInfo.CadillacTpms();
    protected CustomImgView[] mIvTyres = new CustomImgView[4];
    protected RelativeLayoutManager mManager;
    protected CustomTextView[] mTvPress = new CustomTextView[4];
    protected CustomTextView mTvStatus;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        ((RelativeLayout) findViewById(R.id.can_comm_layout)).setBackgroundResource(R.drawable.can_rf_tpms_bg);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        if (MainSet.GetScreenType() == 3) {
            InitUI_768x432();
        } else {
            InitUI();
        }
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mTvStatus = this.mManager.AddCusText(375, 45, 281, 50);
        this.mTvStatus.SetPixelSize(35);
        this.mTvStatus.setGravity(17);
        this.mManager.AddImage(375, 55, R.drawable.can_rf_tpms_line);
        for (int i = 0; i < 4; i++) {
            this.mIvTyres[i] = this.mManager.AddImage(((i % 2) * 132) + 431, ((i / 2) * 186) + 116);
            this.mIvTyres[i].setStateDrawable(R.drawable.can_rf_tpms_up, R.drawable.can_rf_tpms_dn);
            this.mTvPress[i] = this.mManager.AddCusText(((i % 2) * CanCameraUI.BTN_TRUMPCHI_GS7_MODE4) + 91, ((i / 2) * Can.CAN_CC_HF_DJ) + 79, 281, 70);
            this.mTvPress[i].SetPixelSize(47);
            this.mTvPress[i].setGravity(17);
        }
    }

    /* access modifiers changed from: protected */
    public void InitUI_768x432() {
        this.mTvStatus = this.mManager.AddCusText(Can.CAN_FORD_EDGE_XFY, 20, 281, 50);
        this.mTvStatus.SetPixelSize(35);
        this.mTvStatus.setGravity(17);
        this.mManager.AddImage(Can.CAN_FORD_EDGE_XFY, 30, R.drawable.can_rf_tpms_line);
        for (int i = 0; i < 4; i++) {
            this.mIvTyres[i] = this.mManager.AddImage(((i % 2) * 106) + KeyDef.RKEY_EJECT, ((i / 2) * 136) + 90);
            this.mIvTyres[i].setStateDrawable(R.drawable.can_rf_tpms_up, R.drawable.can_rf_tpms_dn);
            this.mTvPress[i] = this.mManager.AddCusText(((i % 2) * 420) + 40, ((i / 2) * 170) + 60, 281, 70);
            this.mTvPress[i].SetPixelSize(47);
            this.mTvPress[i].setGravity(17);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        Log.d(TAG, "-----onResume-----");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        Log.d(TAG, "-----onPause-----");
    }

    private void ResetData(boolean check) {
        CanJni.CadillacWithCDGetTpms(this.mData);
        if (!i2b(this.mData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mData.Update)) {
            this.mData.Update = 0;
            SetVal(0, this.mData.Val[0]);
            SetVal(1, this.mData.Val[1]);
            SetVal(2, this.mData.Val[2]);
            SetVal(3, this.mData.Val[3]);
            if (this.mData.Sta > 0) {
                this.mTvStatus.setText("系统异常");
                this.mTvStatus.setTextColor(SupportMenu.CATEGORY_MASK);
                return;
            }
            this.mTvStatus.setText("系统正常");
            this.mTvStatus.setTextColor(-1);
        }
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onChanged(int id, int pos) {
    }

    public String GetPressStr(int press) {
        if (press == 65535) {
            return String.format("--", new Object[0]);
        }
        if (this.mData.Dw == 0) {
            return String.format("%.1f bar", new Object[]{Double.valueOf(((double) press) * 0.1d)});
        } else if (this.mData.Dw == 1) {
            return String.format("%d psi", new Object[]{Integer.valueOf(press)});
        } else if (this.mData.Dw == 2) {
            return String.format("%.1f kpa", new Object[]{Double.valueOf(((double) press) * 2.5d)});
        } else if (this.mData.Dw != 3) {
            return "";
        } else {
            return String.format("%d kpa", new Object[]{Integer.valueOf(press)});
        }
    }

    public String GetTempStr(int temp) {
        if (temp >= 166) {
            return "-- ℃";
        }
        return String.format("%d ℃", new Object[]{Integer.valueOf(temp - 40)});
    }

    public void SetVal(int id, int press) {
        this.mTvPress[id].setText(GetPressStr(press));
    }
}
