package com.ts.can.psa.hc;

import android.os.Bundle;
import android.support.v4.internal.view.SupportMenu;
import android.util.Log;
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
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;

public class CanBZMemTabActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener, CanItemProgressList.onPosChange, CanNumInuptDlg.onInputOK {
    public static final int ITEM_OK = 11;
    public static final int ITEM_RESET = 10;
    public static final int ITEM_SUBCHK_BASE = 16;
    public static final int ITEM_TOP_CHK = 12;
    public static final int ITEM_VAL_BASE = 1;
    public static final String TAG = "CanBZMemTabActivity";
    private ParamButton[] mBtnSpeed;
    private ParamButton[] mBtnSubChk;
    private ParamButton mBtnTopChk;
    protected RelativeLayoutManager mManager;
    protected CanDataInfo.C4LMemTab mSpeedData = new CanDataInfo.C4LMemTab();
    protected boolean mbModify;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        this.mManager.AddImage(180, 105, R.drawable.can_psa_c4l_bg);
        this.mBtnSpeed = new ParamButton[6];
        this.mBtnSubChk = new ParamButton[6];
        for (int i = 0; i < 5; i++) {
            this.mBtnSpeed[i] = AddValBtn(((i % 2) * 300) + 383, ((i / 2) * 67) + 127 + 50, i + 1);
            this.mBtnSubChk[i] = AddChkBtn(((i % 2) * 300) + Can.CAN_SITECHDEV_CW, ((i / 2) * 67) + 133 + 50, i + 16);
            CustomTextView tv = this.mManager.AddCusText(((i % 2) * 300) + 291, ((i / 2) * 67) + 127 + 50, 90, 56);
            tv.SetPxSize(40);
            tv.setText(String.format("M%d:", new Object[]{Integer.valueOf(i + 1)}));
            tv.setGravity(19);
        }
        CustomTextView tv2 = this.mManager.AddCusText(Can.CAN_BYD_M6_DJ, 112, 200, 56);
        tv2.SetPxSize(40);
        tv2.setText(R.string.can_car_active);
        tv2.setGravity(19);
        this.mBtnTopChk = AddChkBtn(194, 118, 12);
        AddCtrlBtn(Can.CAN_MZD_TXB, 438, 10, R.string.can_setup_reset);
        AddCtrlBtn(639, 438, 11, R.string.can_confirm);
    }

    /* access modifiers changed from: protected */
    public ParamButton AddValBtn(int x, int y, int id) {
        ParamButton btn = this.mManager.AddButton(x, y, 110, 56);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setStateUpDn(R.drawable.can_psa_c4l_box, R.drawable.can_psa_c4l_box);
        btn.setColorUpDn(-1, SupportMenu.CATEGORY_MASK);
        btn.setTextSize(0, 40.0f);
        btn.setGravity(17);
        btn.setPadding(0, 0, 0, 0);
        return btn;
    }

    /* access modifiers changed from: protected */
    public ParamButton AddChkBtn(int x, int y, int id) {
        ParamButton btn = this.mManager.AddButton(x, y, 46, 45);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setStateUpDn(R.drawable.can_psa_c4l_hook_up, R.drawable.can_psa_c4l_hook_up);
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
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    private void ResetData(boolean check) {
        if (!this.mbModify) {
            CanJni.BZGetMemTab(this.mSpeedData);
            if (!i2b(this.mSpeedData.UpdateOnce)) {
                return;
            }
            if (!check || i2b(this.mSpeedData.Update)) {
                this.mSpeedData.Update = 0;
                for (int i = 0; i < 5; i++) {
                    this.mBtnSpeed[i].setText(new StringBuilder(String.valueOf(this.mSpeedData.ubData[i])).toString());
                    SetChk(i, this.mSpeedData.fgDatAvalid[i]);
                }
                SetChk(5, this.mSpeedData.fgTabAvalid);
            }
        }
    }

    private void SetChk(int index, int chk) {
        ParamButton btn;
        Log.d(TAG, "SetChk index = " + index + ", chk = " + chk);
        if (5 == index) {
            btn = this.mBtnTopChk;
        } else {
            btn = this.mBtnSubChk[index];
        }
        if (chk == 0) {
            btn.setStateUpDn(R.drawable.can_psa_c4l_hook_up, R.drawable.can_psa_c4l_hook_up);
        } else {
            btn.setStateUpDn(R.drawable.can_psa_c4l_hook_dn, R.drawable.can_psa_c4l_hook_dn);
        }
        btn.invalidate();
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        switch (id) {
            case 10:
                this.mbModify = false;
                CanJni.BZMemTabSet(this.mSpeedData, 1);
                return;
            case 11:
                this.mbModify = false;
                CanJni.BZMemTabSet(this.mSpeedData, 0);
                return;
            case 12:
                this.mSpeedData.fgTabAvalid = Neg(this.mSpeedData.fgTabAvalid);
                SetChk(5, this.mSpeedData.fgTabAvalid);
                this.mbModify = true;
                return;
            default:
                if (id >= 1 && id <= 6) {
                    new CanNumInuptDlg(this, this, 3, id);
                    this.mbModify = true;
                    return;
                } else if (id >= 16 && id <= 21) {
                    int index = id - 16;
                    this.mSpeedData.fgDatAvalid[index] = Neg(this.mSpeedData.fgDatAvalid[index]);
                    SetChk(index, this.mSpeedData.fgDatAvalid[index]);
                    this.mbModify = true;
                    return;
                } else {
                    return;
                }
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
        this.mSpeedData.ubData[id - 1] = num;
        this.mBtnSpeed[id - 1].setText(new StringBuilder().append(num).toString());
    }
}
