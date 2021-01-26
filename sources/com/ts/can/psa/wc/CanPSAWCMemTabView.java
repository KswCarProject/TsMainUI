package com.ts.can.psa.wc;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.internal.view.SupportMenu;
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

public class CanPSAWCMemTabView extends CanRelativeCarInfoView implements CanNumInuptDlg.onInputOK {
    public static final int ITEM_OK = 11;
    public static final int ITEM_RESET = 10;
    public static final int ITEM_SUBCHK_BASE = 16;
    public static final int ITEM_TOP_CHK = 12;
    public static final int ITEM_VAL_BASE = 1;
    private ParamButton[] mBtnSpeed;
    private ParamButton[] mBtnSubChk;
    private ParamButton mBtnTopChk;
    protected RelativeLayoutManager mManager;
    protected CanDataInfo.PeugWcMemTab mSpeedData = new CanDataInfo.PeugWcMemTab();
    private CustomTextView[] mTv;
    private CustomTextView mTvActive;
    protected boolean mbModify = false;

    public CanPSAWCMemTabView(Activity activity) {
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
        }
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        switch (id) {
            case 10:
                this.mbModify = false;
                resetData();
                CanJni.PsaWcMemTabSet(this.mSpeedData, 0);
                return;
            case 11:
                this.mbModify = false;
                CanJni.PsaWcMemTabSet(this.mSpeedData, 0);
                return;
            case 12:
                this.mSpeedData.fgTabAvalid = Neg(this.mSpeedData.fgTabAvalid);
                SetChk(6, this.mSpeedData.fgTabAvalid);
                this.mbModify = true;
                return;
            default:
                if (id >= 1 && id <= 6) {
                    new CanNumInuptDlg(getActivity(), this, 3, id);
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
        this.mBtnSubChk = new ParamButton[6];
        this.mTv = new CustomTextView[6];
        for (int i = 0; i < 6; i++) {
            this.mBtnSpeed[i] = AddValBtn(((i % 2) * 300) + 383, ((i / 2) * 67) + 127 + 50, i + 1);
            this.mBtnSubChk[i] = AddChkBtn(((i % 2) * 300) + Can.CAN_SITECHDEV_CW, ((i / 2) * 67) + 133 + 50, i + 16);
            this.mTv[i] = this.mManager.AddCusText(((i % 2) * 300) + 291, ((i / 2) * 67) + 127 + 50, 90, 56);
            this.mTv[i].SetPxSize(40);
            this.mTv[i].setText(String.format("M%d:", new Object[]{Integer.valueOf(i + 1)}));
            this.mTv[i].setGravity(19);
        }
        this.mTvActive = this.mManager.AddCusText(Can.CAN_BYD_M6_DJ, 112, 200, 56);
        this.mTvActive.SetPxSize(40);
        this.mTvActive.setText(R.string.can_car_active);
        this.mTvActive.setGravity(19);
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

    public void ResetData(boolean check) {
        if (!this.mbModify) {
            CanJni.PsaWcGetMemTab(this.mSpeedData);
            if (!i2b(this.mSpeedData.UpdateOnce)) {
                return;
            }
            if (!check || i2b(this.mSpeedData.Update)) {
                this.mSpeedData.Update = 0;
                for (int i = 0; i < 6; i++) {
                    this.mBtnSpeed[i].setText(new StringBuilder(String.valueOf(this.mSpeedData.Data[i])).toString());
                    SetChk(i, this.mSpeedData.fgDatAvalid[i]);
                }
                SetChk(6, this.mSpeedData.fgTabAvalid);
                updateBtn();
            }
        }
    }

    private void updateBtn() {
        if (this.mSpeedData.Adt[0] == 0) {
            this.mBtnTopChk.setClickable(false);
            this.mTvActive.setTextColor(Color.parseColor("#545454"));
            SetChk(6, 2);
            for (int i = 0; i < this.mBtnSpeed.length; i++) {
                this.mBtnSpeed[i].setTextColor(Color.parseColor("#545454"));
                this.mBtnSpeed[i].setClickable(false);
                this.mBtnSpeed[i].setStateUpDn(R.drawable.can_psa_c4l_box_00, R.drawable.can_psa_c4l_box_00);
                this.mTv[i].setTextColor(Color.parseColor("#545454"));
                SetChk(i, 2);
            }
            return;
        }
        this.mBtnTopChk.setClickable(true);
        this.mTvActive.setTextColor(Color.parseColor("#ffffff"));
        SetChk(6, this.mSpeedData.fgTabAvalid);
        for (int i2 = 0; i2 < this.mBtnSpeed.length; i2++) {
            if (this.mSpeedData.Adt[i2 + 1] == 0) {
                this.mBtnSpeed[i2].setTextColor(Color.parseColor("#545454"));
                this.mBtnSpeed[i2].setClickable(false);
                this.mBtnSpeed[i2].setStateUpDn(R.drawable.can_psa_c4l_box_00, R.drawable.can_psa_c4l_box_00);
                this.mTv[i2].setTextColor(Color.parseColor("#545454"));
                SetChk(i2, 2);
            } else {
                this.mBtnSpeed[i2].setTextColor(Color.parseColor("#ffffff"));
                this.mBtnSpeed[i2].setClickable(true);
                this.mBtnSpeed[i2].setStateUpDn(R.drawable.can_psa_c4l_box, R.drawable.can_psa_c4l_box);
                this.mTv[i2].setTextColor(Color.parseColor("#ffffff"));
                SetChk(i2, this.mSpeedData.fgDatAvalid[i2]);
            }
        }
    }

    private void SetChk(int index, int chk) {
        ParamButton btn;
        if (6 == index) {
            btn = this.mBtnTopChk;
        } else {
            btn = this.mBtnSubChk[index];
        }
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
