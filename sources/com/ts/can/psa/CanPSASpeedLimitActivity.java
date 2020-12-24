package com.ts.can.psa;

import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanNumInuptDlg;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;

public class CanPSASpeedLimitActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener, CanItemProgressList.onPosChange, CanNumInuptDlg.onInputOK {
    public static final int ITEM_OK = 11;
    public static final int ITEM_RESET = 10;
    public static final int ITEM_VAL_BASE = 1;
    public static final String TAG = "CanPSASpeedLimitActivity";
    private ParamButton[] mBtnSpeed;
    protected RelativeLayoutManager mManager;
    protected CanDataInfo.PeugMemTab mSpeedData = new CanDataInfo.PeugMemTab();
    protected boolean mbModify;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        this.mManager.AddImage(180, 105, R.drawable.can_psa_c4l_bg);
        this.mBtnSpeed = new ParamButton[6];
        for (int i = 0; i < 6; i++) {
            this.mBtnSpeed[i] = AddValBtn(((i % 3) * 195) + 270, ((i / 3) * 121) + 88 + 50, i + 1);
        }
        AddCtrlBtn(Can.CAN_MZD_TXB, 438, 10, R.string.can_setup_reset);
        AddCtrlBtn(639, 438, 11, R.string.can_confirm);
    }

    /* access modifiers changed from: protected */
    public ParamButton AddValBtn(int x, int y, int id) {
        ParamButton btn = this.mManager.AddButton(x, y);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setStateUpDn(R.drawable.can_psa_yuan_up, R.drawable.can_psa_yuan_dn);
        btn.setColorUpDn(-16777216, -1);
        btn.setTextSize(0, 45.0f);
        return btn;
    }

    /* access modifiers changed from: protected */
    public void AddCtrlBtn(int x, int y, int id, int text) {
        ParamButton btn = this.mManager.AddButton(x, y, 143, 74);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setStateUpDn(R.drawable.can_psa_c4l_rect_up, R.drawable.can_psa_c4l_rect_dn);
        btn.setTextColor(-1);
        btn.setTextSize(0, 34.615387f);
        btn.setPadding(0, 5, 0, 0);
        btn.setText(text);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        CanJni.PSAQuery(61, 0);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    private void ResetData(boolean check) {
        if (!this.mbModify) {
            CanJni.PSAGetSpeedLimit(this.mSpeedData);
            if (!i2b(this.mSpeedData.UpdateOnce)) {
                return;
            }
            if (!check || i2b(this.mSpeedData.Update)) {
                this.mSpeedData.Update = 0;
                for (int i = 0; i < 6; i++) {
                    this.mBtnSpeed[i].setText(new StringBuilder(String.valueOf(this.mSpeedData.Data[i])).toString());
                }
            }
        }
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        switch (id) {
            case 10:
                this.mbModify = false;
                CanJni.PSASpeedLimitSet(this.mSpeedData, 1);
                return;
            case 11:
                this.mbModify = false;
                CanJni.PSASpeedLimitSet(this.mSpeedData, 0);
                return;
            default:
                if (id >= 1 && id <= 6) {
                    new CanNumInuptDlg(this, this, 3, id);
                    this.mbModify = true;
                    return;
                }
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onChanged(int id, int pos) {
    }

    public void onOK(String val, int num, int id) {
        if (num > 255) {
            num = 255;
        }
        this.mSpeedData.Data[id - 1] = num;
        this.mBtnSpeed[id - 1].setText(new StringBuilder().append(num).toString());
    }
}
