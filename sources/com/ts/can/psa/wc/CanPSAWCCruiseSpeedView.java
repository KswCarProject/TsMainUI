package com.ts.can.psa.wc;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.canview.CanNumInuptDlg;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;

public class CanPSAWCCruiseSpeedView extends CanRelativeCarInfoView implements CanNumInuptDlg.onInputOK {
    public static final int ITEM_LEFT_CHK = 12;
    public static final int ITEM_OK = 11;
    public static final int ITEM_RESET = 10;
    public static final int ITEM_RIGHT_CHK = 13;
    public static final int ITEM_VAL_BASE = 1;
    private ParamButton mBtnLeftChk;
    private ParamButton[] mBtnSpeed;
    protected RelativeLayoutManager mManager;
    protected CanDataInfo.PeugWcMemTab mSpeedData = new CanDataInfo.PeugWcMemTab();
    protected boolean mbModify = false;
    CustomTextView tv;

    public CanPSAWCCruiseSpeedView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    /* access modifiers changed from: package-private */
    public void resetData() {
        this.mSpeedData.fgTabAvalid = 0;
        for (int i = 0; i < this.mSpeedData.fgDatAvalid.length; i++) {
            this.mSpeedData.fgDatAvalid[i] = 0;
            this.mSpeedData.Data[i] = 0;
        }
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        switch (id) {
            case 10:
                this.mbModify = false;
                resetData();
                CanJni.PsaWcCruiseSpeedSet(this.mSpeedData, 0);
                return;
            case 11:
                this.mbModify = false;
                for (int i = 0; i < 6; i++) {
                    if (this.mSpeedData.Data[i] == 0) {
                        this.mSpeedData.fgDatAvalid[i] = 0;
                    } else {
                        this.mSpeedData.fgDatAvalid[i] = 1;
                    }
                }
                CanJni.PsaWcCruiseSpeedSet(this.mSpeedData, 0);
                return;
            case 12:
                this.mSpeedData.fgTabAvalid = Neg(this.mSpeedData.fgTabAvalid);
                SetChk(this.mSpeedData.fgTabAvalid);
                this.mbModify = true;
                return;
            default:
                if (id >= 1 && id <= 6) {
                    new CanNumInuptDlg(getActivity(), this, 3, id);
                    return;
                }
                return;
        }
    }

    public void doOnResume() {
        super.doOnResume();
        this.mbModify = false;
        ResetData(false);
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = getRelativeManager();
        this.mManager.AddImage(180, 105, R.drawable.can_psa_c4l_bg);
        this.mBtnSpeed = new ParamButton[6];
        for (int i = 0; i < 6; i++) {
            this.mBtnSpeed[i] = AddValBtn(((i % 3) * 195) + 270, ((i / 3) * 121) + 88 + 50, i + 1);
        }
        AddCtrlBtn(Can.CAN_MZD_TXB, 438, 10, R.string.can_setup_reset);
        AddCtrlBtn(639, 438, 11, R.string.can_confirm);
        this.tv = this.mManager.AddCusText(Can.CAN_BYD_M6_DJ, 42, Can.CAN_NISSAN_XFY, 56);
        this.tv.SetPxSize(40);
        this.tv.setText(R.string.can_psa_wc_xhsdzqy);
        this.tv.setGravity(19);
        this.mBtnLeftChk = AddChkBtn(194, 48, 12);
    }

    private void SetChk(int chk) {
        Log.d("__lh__", "SetChk chk = " + chk);
        ParamButton btn = this.mBtnLeftChk;
        if (chk == 0) {
            btn.setStateUpDn(R.drawable.can_psa_c4l_hook_up, R.drawable.can_psa_c4l_hook_up);
            btn.setClickable(true);
        } else if (2 == chk) {
            btn.setStateUpDn(R.drawable.can_psa_c4l_check_00, R.drawable.can_psa_c4l_check_00);
            btn.setClickable(false);
        } else {
            btn.setStateUpDn(R.drawable.can_psa_c4l_hook_dn, R.drawable.can_psa_c4l_hook_dn);
            btn.setClickable(true);
        }
        btn.invalidate();
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
    public void updateBtn() {
        if (this.mSpeedData.Adt[0] == 0) {
            this.mBtnLeftChk.setClickable(false);
            this.tv.setTextColor(Color.parseColor("#545454"));
            SetChk(2);
            for (int i = 0; i < this.mBtnSpeed.length; i++) {
                this.mBtnSpeed[i].setStateUpDn(R.drawable.can_psa_yuan_gray, R.drawable.can_psa_yuan_dn);
                this.mBtnSpeed[i].setClickable(false);
            }
            return;
        }
        this.mBtnLeftChk.setClickable(true);
        this.tv.setTextColor(Color.parseColor("#ffffff"));
        SetChk(this.mSpeedData.fgTabAvalid);
        for (int i2 = 0; i2 < this.mBtnSpeed.length; i2++) {
            if (this.mSpeedData.Adt[i2 + 1] == 0) {
                this.mBtnSpeed[i2].setStateUpDn(R.drawable.can_psa_yuan_gray, R.drawable.can_psa_yuan_dn);
                this.mBtnSpeed[i2].setClickable(false);
            } else if (this.mSpeedData.fgDatAvalid[i2] == 0) {
                this.mBtnSpeed[i2].setStateUpDn(R.drawable.can_psa_yuan_green, R.drawable.can_psa_yuan_dn);
                this.mBtnSpeed[i2].setClickable(true);
            } else {
                this.mBtnSpeed[i2].setStateUpDn(R.drawable.can_psa_yuan_up, R.drawable.can_psa_yuan_dn);
                this.mBtnSpeed[i2].setClickable(true);
            }
        }
    }

    public void ResetData(boolean check) {
        if (!this.mbModify) {
            CanJni.PsaWcGetCruiseSpeed(this.mSpeedData);
            if (!i2b(this.mSpeedData.UpdateOnce)) {
                return;
            }
            if (!check || i2b(this.mSpeedData.Update)) {
                this.mSpeedData.Update = 0;
                SetChk(this.mSpeedData.fgTabAvalid);
                updateBtn();
                for (int i = 0; i < 6; i++) {
                    this.mBtnSpeed[i].setText(new StringBuilder(String.valueOf(this.mSpeedData.Data[i])).toString());
                }
            }
        }
    }

    public void QueryData() {
    }

    public void onOK(String val, int num, int id) {
        if (num > 255) {
            num = 255;
        }
        this.mSpeedData.Data[id - 1] = num;
        this.mBtnSpeed[id - 1].setText(new StringBuilder().append(num).toString());
        this.mbModify = true;
    }
}
