package com.ts.can.ht.od;

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

public class CanHtOdTpmsActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener, CanItemProgressList.onPosChange {
    public static final int ITEM_VOL = 0;
    public static final String TAG = "CanHtOdTpmsActivity";
    private static final String[] mWarnArrays = {"胎压正常", "传感器失效", "低压报警", "高压报警"};
    protected CanDataInfo.HanT_Tpms_Data mData = new CanDataInfo.HanT_Tpms_Data();
    protected CustomImgView[] mIvTyres = new CustomImgView[4];
    protected RelativeLayoutManager mManager;
    protected CustomTextView[] mTvPress = new CustomTextView[4];
    protected CustomTextView mTvStatus;
    protected CustomTextView[] mTvTemp = new CustomTextView[4];
    protected CustomTextView[] mTvWarn = new CustomTextView[4];
    protected CanDataInfo.HanT_Tpms_Warn mWarn = new CanDataInfo.HanT_Tpms_Warn();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        if (MainSet.GetScreenType() == 5) {
            initUI_1280x480();
        } else {
            initUI();
        }
    }

    private void initUI() {
        ((RelativeLayout) findViewById(R.id.can_comm_layout)).setBackgroundResource(R.drawable.can_rf_tpms_bg);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        for (int i = 0; i < 4; i++) {
            this.mIvTyres[i] = this.mManager.AddImage(((i % 2) * 132) + 431, ((i / 2) * 186) + 116);
            this.mIvTyres[i].setStateDrawable(R.drawable.can_rf_tpms_up, R.drawable.can_rf_tpms_dn);
            this.mTvPress[i] = this.mManager.AddCusText(((i % 2) * CanCameraUI.BTN_TRUMPCHI_GS7_MODE4) + 91, ((i / 2) * Can.CAN_CC_HF_DJ) + 49, 281, 50);
            this.mTvPress[i].SetPixelSize(35);
            this.mTvPress[i].setGravity(17);
            this.mTvTemp[i] = this.mManager.AddCusText(((i % 2) * CanCameraUI.BTN_TRUMPCHI_GS7_MODE4) + 91, ((i / 2) * Can.CAN_CC_HF_DJ) + 95, 281, 50);
            this.mTvTemp[i].SetPixelSize(35);
            this.mTvTemp[i].setGravity(17);
            this.mTvWarn[i] = this.mManager.AddCusText(((i % 2) * CanCameraUI.BTN_TRUMPCHI_GS7_MODE4) + 91, ((i / 2) * Can.CAN_CC_HF_DJ) + 144, 281, 50);
            this.mTvWarn[i].SetPixelSize(35);
            this.mTvWarn[i].setGravity(17);
        }
        this.mTvStatus = this.mManager.AddCusText(375, 45, 281, 50);
        this.mTvStatus.SetPixelSize(35);
        this.mTvStatus.setGravity(17);
    }

    private void initUI_1280x480() {
        ((RelativeLayout) findViewById(R.id.can_comm_layout)).setBackgroundResource(R.drawable.can_rfhp_tpms_bg);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        for (int i = 0; i < 4; i++) {
            this.mIvTyres[i] = this.mManager.AddImage(((i % 2) * 128) + 557, ((i / 2) * 124) + 92);
            this.mIvTyres[i].setStateDrawable(R.drawable.can_rf_tpms_up, R.drawable.can_rf_tpms_dn);
            this.mTvPress[i] = this.mManager.AddCusText(((i % 2) * KeyDef.SKEY_POWEWR_1) + 123, ((i / 2) * 171) + 30, 281, 50);
            this.mTvPress[i].SetPixelSize(35);
            this.mTvPress[i].setGravity(17);
            this.mTvTemp[i] = this.mManager.AddCusText(((i % 2) * KeyDef.SKEY_POWEWR_1) + 123, ((i / 2) * 171) + 74, 281, 50);
            this.mTvTemp[i].SetPixelSize(35);
            this.mTvTemp[i].setGravity(17);
            this.mTvWarn[i] = this.mManager.AddCusText(((i % 2) * KeyDef.SKEY_POWEWR_1) + 123, ((i / 2) * 171) + 118, 281, 50);
            this.mTvWarn[i].SetPixelSize(35);
            this.mTvWarn[i].setGravity(17);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        Log.d("CanHtOdTpmsActivity", "-----onResume-----");
        CanJni.HanTOdQuery(56);
        Sleep(10);
        CanJni.HanTOdQuery(57);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        Log.d("CanHtOdTpmsActivity", "-----onPause-----");
    }

    private void ResetData(boolean check) {
        CanJni.HanTOdGetTpmsData(this.mData);
        if (i2b(this.mData.UpdateOnce) && (!check || i2b(this.mData.Update))) {
            this.mData.Update = 0;
            SetVal(0, this.mData.FLPress, this.mData.FLTemp);
            SetVal(1, this.mData.FRPress, this.mData.FRTemp);
            SetVal(2, this.mData.RLPress, this.mData.RLTemp);
            SetVal(3, this.mData.RRPress, this.mData.RRTemp);
        }
        CanJni.HanTOdGetTpmsWarn(this.mWarn);
        if (!i2b(this.mWarn.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mWarn.Update)) {
            this.mWarn.Update = 0;
            setTyresInfos(0, this.mWarn.FLWarn[0], this.mWarn.FLWarn[1], this.mWarn.FLWarn[2]);
            setTyresInfos(1, this.mWarn.FRWarn[0], this.mWarn.FRWarn[1], this.mWarn.FRWarn[2]);
            setTyresInfos(2, this.mWarn.RLWarn[0], this.mWarn.RLWarn[1], this.mWarn.RLWarn[2]);
            setTyresInfos(3, this.mWarn.RRWarn[0], this.mWarn.RRWarn[1], this.mWarn.RRWarn[2]);
            if (i2b(this.mWarn.SystemVaild)) {
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
            this.mIvTyres[tpms].setSelected(true);
            if (warn1 > 0) {
                this.mTvWarn[tpms].setText(mWarnArrays[1]);
            } else if (warn2 > 0) {
                this.mTvWarn[tpms].setText(mWarnArrays[2]);
            } else if (warn3 > 0) {
                this.mTvWarn[tpms].setText(mWarnArrays[3]);
            }
            this.mTvWarn[tpms].setTextColor(SupportMenu.CATEGORY_MASK);
            return;
        }
        this.mIvTyres[tpms].setSelected(false);
        this.mTvWarn[tpms].setText(mWarnArrays[0]);
        this.mTvWarn[tpms].setTextColor(-1);
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
        if (press == 255) {
            return "--";
        }
        return String.format("%.2f kpa", new Object[]{Double.valueOf(((double) press) * 1.373d)});
    }

    public String GetTempStr(int temp) {
        return String.format("%d ℃", new Object[]{Integer.valueOf(temp - 40)});
    }

    public void SetVal(int id, int press, int temp) {
        this.mTvPress[id].setText(GetPressStr(press));
        this.mTvTemp[id].setText(GetTempStr(temp));
    }
}
