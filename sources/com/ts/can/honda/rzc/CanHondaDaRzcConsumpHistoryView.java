package com.ts.can.honda.rzc;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.canview.MyProgressBar;
import com.ts.main.common.MainSet;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.KeyDef;

public class CanHondaDaRzcConsumpHistoryView extends CanRelativeCarInfoView {
    public static final int BTN_CURRENT = 1;
    private ParamButton mBtnCurrent;
    private CustomTextView[] mConsump;
    private int[] mConsumpData;
    private CanDataInfo.HondaYLLCData_1 mCurData;
    private CanDataInfo.HondaYLLCData_2 mHistoryData;
    private RelativeLayoutManager mManager;
    private CustomTextView[] mMark;
    private MyProgressBar[] mProg;
    private CustomTextView mRange;
    private String mStrRange;
    private CustomTextView[] mTitle;
    private CustomTextView[] mTripA;
    private int[] mTripAData;

    public CanHondaDaRzcConsumpHistoryView(Activity activity) {
        super(activity);
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mTripAData = new int[4];
        this.mConsumpData = new int[4];
        this.mCurData = new CanDataInfo.HondaYLLCData_1();
        this.mHistoryData = new CanDataInfo.HondaYLLCData_2();
        if (MainSet.GetScreenType() == 5) {
            initScreen_1280x480();
        } else {
            initCommonScreen();
        }
    }

    private void initScreen_1280x480() {
        this.mManager = getRelativeManager();
        setBackgroundResource(R.drawable.can_btyh_bg);
        CustomTextView tv = this.mManager.AddCusText(528, 0, 300, 40);
        tv.setText(R.string.can_jszkll);
        tv.SetPxSize(40);
        this.mTitle = new CustomTextView[4];
        this.mTripA = new CustomTextView[4];
        this.mProg = new MyProgressBar[4];
        this.mConsump = new CustomTextView[4];
        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                this.mTitle[i] = this.mManager.AddCusText(195, 75, 133, 47);
                this.mTripA[i] = this.mManager.AddCusText(356, 101, 175, 47);
                this.mConsump[i] = this.mManager.AddCusText(914, 101, Can.CAN_CC_HF_DJ, 47);
                this.mProg[i] = new MyProgressBar(getActivity(), R.drawable.can_btyh_pro_up, R.drawable.can_btyh_pro_dn);
                this.mManager.AddViewWrapContent(this.mProg[i], CanCameraUI.BTN_TRUMPCHI_GS7_MODE5, 104);
            } else {
                this.mTitle[i] = this.mManager.AddCusText(195, ((i - 1) * 63) + 164, 133, 47);
                this.mTripA[i] = this.mManager.AddCusText(356, ((i - 1) * 63) + 164, 175, 47);
                this.mConsump[i] = this.mManager.AddCusText(914, ((i - 1) * 63) + 164, Can.CAN_CC_HF_DJ, 47);
                this.mProg[i] = new MyProgressBar(getActivity(), R.drawable.can_btyh_pro_up, R.drawable.can_btyh_pro_dn);
                this.mManager.AddViewWrapContent(this.mProg[i], CanCameraUI.BTN_TRUMPCHI_GS7_MODE5, ((i - 1) * 61) + 173);
            }
            this.mTitle[i].setGravity(21);
            this.mTitle[i].SetPxSize(40);
            this.mTripA[i].setGravity(17);
            this.mTripA[i].SetPxSize(40);
            this.mConsump[i].setGravity(19);
            this.mConsump[i].SetPxSize(40);
        }
        this.mTitle[0].setText(R.string.can_bczk);
        this.mTitle[1].setText(R.string.can_1st);
        this.mTitle[2].setText(R.string.can_2st);
        this.mTitle[3].setText(R.string.can_3st);
        this.mMark = new CustomTextView[3];
        this.mMark[0] = this.mManager.AddCusText(CanCameraUI.BTN_TRUMPCHI_GS7_MODE5, 59, 45, 34);
        this.mMark[0].setGravity(19);
        this.mMark[0].SetPxSize(30);
        this.mMark[0].setText("0");
        this.mMark[1] = this.mManager.AddCusText(694, 59, 45, 34);
        this.mMark[1].setGravity(17);
        this.mMark[1].SetPxSize(30);
        this.mMark[1].setText("10");
        this.mMark[2] = this.mManager.AddCusText(KeyDef.SKEY_PP_1, 59, 45, 34);
        this.mMark[2].setGravity(21);
        this.mMark[2].SetPxSize(30);
        this.mMark[2].setText("20");
        CustomTextView tv2 = this.mManager.AddCusText(408, 59, 86, 34);
        tv2.SetPxSize(30);
        tv2.setText("TRIP A");
        CustomTextView tv3 = this.mManager.AddCusText(914, 59, Can.CAN_CC_HF_DJ, 34);
        tv3.SetPxSize(36);
        tv3.setText(R.string.can_pjyh);
        this.mStrRange = getActivity().getResources().getString(R.string.can_range_xhlc);
        this.mRange = this.mManager.AddCusText(756, 365, KeyDef.RKEY_RDS_TA, 50);
        this.mRange.setGravity(21);
        this.mRange.SetPxSize(40);
        this.mBtnCurrent = this.mManager.AddButton(195, 357, 167, 63);
        SetCommBtn(this.mBtnCurrent, R.string.can_jsxx);
        this.mBtnCurrent.setTag(1);
        this.mBtnCurrent.setOnClickListener(this);
    }

    private void initCommonScreen() {
        this.mManager = getRelativeManager();
        this.mManager.AddImage(62, 69, R.drawable.can_btyh_line);
        this.mManager.AddImage(62, 182, R.drawable.can_btyh_line);
        this.mManager.AddImage(62, 382, R.drawable.can_btyh_line);
        CustomTextView tv = this.mManager.AddCusText(372, 16, 300, 46);
        tv.setText(R.string.can_jszkll);
        tv.SetPxSize(40);
        this.mTitle = new CustomTextView[4];
        this.mTripA = new CustomTextView[4];
        this.mProg = new MyProgressBar[4];
        this.mConsump = new CustomTextView[4];
        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                this.mTitle[i] = this.mManager.AddCusText(68, 119, 133, 47);
                this.mTripA[i] = this.mManager.AddCusText(Can.CAN_VOLVO_XFY, 119, 175, 47);
                this.mConsump[i] = this.mManager.AddCusText(KeyDef.SKEY_VOLUP_2, 119, Can.CAN_CC_HF_DJ, 47);
                this.mProg[i] = new MyProgressBar(getActivity(), R.drawable.can_btyh_pro_up, R.drawable.can_btyh_pro_dn);
                this.mManager.AddViewWrapContent(this.mProg[i], 437, 124);
            } else {
                this.mTitle[i] = this.mManager.AddCusText(68, ((i - 1) * 63) + 197, 133, 47);
                this.mTripA[i] = this.mManager.AddCusText(Can.CAN_VOLVO_XFY, ((i - 1) * 63) + 197, 175, 47);
                this.mConsump[i] = this.mManager.AddCusText(KeyDef.SKEY_VOLUP_2, ((i - 1) * 63) + 197, Can.CAN_CC_HF_DJ, 47);
                this.mProg[i] = new MyProgressBar(getActivity(), R.drawable.can_btyh_pro_up, R.drawable.can_btyh_pro_dn);
                this.mManager.AddViewWrapContent(this.mProg[i], 437, ((i - 1) * 61) + Can.CAN_LEXUS_IZ);
            }
            this.mTitle[i].setGravity(21);
            this.mTitle[i].SetPxSize(40);
            this.mTripA[i].setGravity(17);
            this.mTripA[i].SetPxSize(40);
            this.mConsump[i].setGravity(19);
            this.mConsump[i].SetPxSize(40);
        }
        this.mTitle[0].setText(R.string.can_bczk);
        this.mTitle[1].setText(R.string.can_1st);
        this.mTitle[2].setText(R.string.can_2st);
        this.mTitle[3].setText(R.string.can_3st);
        this.mMark = new CustomTextView[3];
        this.mMark[0] = this.mManager.AddCusText(437, 79, 45, 34);
        this.mMark[0].setGravity(19);
        this.mMark[0].SetPxSize(30);
        this.mMark[0].setText("0");
        this.mMark[1] = this.mManager.AddCusText(CanCameraUI.BTN_TRUMPCHI_GS7_MODE8, 79, 45, 34);
        this.mMark[1].setGravity(17);
        this.mMark[1].SetPxSize(30);
        this.mMark[1].setText("10");
        this.mMark[2] = this.mManager.AddCusText(697, 79, 45, 34);
        this.mMark[2].setGravity(21);
        this.mMark[2].SetPxSize(30);
        this.mMark[2].setText("20");
        CustomTextView tv2 = this.mManager.AddCusText(281, 79, 86, 34);
        tv2.SetPxSize(30);
        tv2.setText("TRIP A");
        CustomTextView tv3 = this.mManager.AddCusText(KeyDef.SKEY_VOLUP_2, 79, Can.CAN_CC_HF_DJ, 34);
        tv3.SetPxSize(36);
        tv3.setText(R.string.can_pjyh);
        this.mStrRange = getActivity().getResources().getString(R.string.can_range_xhlc);
        this.mRange = this.mManager.AddCusText(629, 390, KeyDef.RKEY_RDS_TA, 50);
        this.mRange.setGravity(21);
        this.mRange.SetPxSize(40);
        this.mBtnCurrent = this.mManager.AddButton(410, 444);
        SetCommBtn(this.mBtnCurrent, R.string.can_jsxx);
        this.mBtnCurrent.setTag(1);
        this.mBtnCurrent.setOnClickListener(this);
    }

    public void doOnResume() {
        super.doOnResume();
        ResetData(false);
        QueryData();
    }

    public void doOnPause() {
        super.doOnPause();
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                enterSubWin(CanCarInfoSub1Activity.class, 2);
                return;
            default:
                return;
        }
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void ResetData(boolean check) {
        CanJni.HondaDAGetConsump(this.mCurData, this.mHistoryData);
        if (i2b(this.mCurData.UpdateOnce) && (!check || i2b(this.mCurData.Update))) {
            this.mCurData.Update = 0;
            this.mTripA[0].setText(GetTripAVal(this.mCurData.TripA, this.mCurData.DwTripA));
            this.mConsump[0].setText(GetYhVal(this.mCurData.Pjyh, this.mCurData.DwPjyh));
            int maxpos = this.mCurData.Yhlc * 10;
            this.mProg[0].SetMinMax(0, maxpos);
            if (this.mCurData.Pjyh < maxpos) {
                this.mProg[0].SetCurPos(this.mCurData.Pjyh);
            } else if (65535 == this.mCurData.Pjyh) {
                this.mProg[0].SetCurPos(0);
            } else {
                this.mProg[0].SetCurPos(maxpos);
            }
            this.mRange.setText(String.format("%s %s", new Object[]{this.mStrRange, GetRangeVal(this.mCurData.Range, this.mCurData.DwRange)}));
        }
        if (!i2b(this.mHistoryData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mHistoryData.Update)) {
            this.mHistoryData.Update = 0;
            this.mMark[1].setText(new StringBuilder(String.valueOf(this.mHistoryData.Yhlc / 2)).toString());
            this.mMark[2].setText(new StringBuilder(String.valueOf(this.mHistoryData.Yhlc)).toString());
            this.mTripAData[1] = this.mHistoryData.Xc1;
            this.mTripAData[2] = this.mHistoryData.Xc2;
            this.mTripAData[3] = this.mHistoryData.Xc3;
            this.mConsumpData[1] = this.mHistoryData.Yh1;
            this.mConsumpData[2] = this.mHistoryData.Yh2;
            this.mConsumpData[3] = this.mHistoryData.Yh3;
            int maxpos2 = this.mHistoryData.Yhlc * 10;
            for (int i = 1; i < 4; i++) {
                this.mTripA[i].setText(GetTripAVal(this.mTripAData[i], this.mHistoryData.DwTripA));
                this.mConsump[i].setText(GetYhVal(this.mConsumpData[i], this.mHistoryData.DwPjyh));
                this.mProg[i].SetMinMax(0, maxpos2);
                if (65535 == this.mConsumpData[i]) {
                    this.mProg[i].SetCurPos(0);
                } else if (this.mConsumpData[i] >= maxpos2) {
                    this.mProg[i].SetCurPos(maxpos2);
                } else {
                    this.mProg[i].SetCurPos(this.mConsumpData[i]);
                }
            }
        }
    }

    public void QueryData() {
        CanJni.HondaDAQuery(51, 2);
    }

    /* access modifiers changed from: protected */
    public void SetCommBtn(ParamButton btn, int text) {
        btn.setStateUpDn(R.drawable.fuel_consumption_rect_up, R.drawable.fuel_consumption_rect_dn);
        if (text != 0) {
            btn.setText(text);
        }
        btn.setPadding(0, 3, 0, 0);
        btn.setTextColor(-1);
        btn.setGravity(17);
        btn.setTextSize(0, 32.0f);
    }

    private String GetYhDW(int dw) {
        switch (dw) {
            case 0:
                return "mpg";
            case 1:
                return "km/l";
            case 2:
                return "l/100km";
            default:
                return TXZResourceManager.STYLE_DEFAULT;
        }
    }

    private String GetYhVal(int yh, int dw) {
        String ret;
        if (yh >= 65535) {
            ret = "--.- ";
        } else {
            ret = String.format("%.1f ", new Object[]{Float.valueOf(((float) yh) * 0.1f)});
        }
        return String.valueOf(ret) + GetYhDW(dw);
    }

    private String GetRangeVal(int range, int dw) {
        String strRange;
        if (range >= 65535) {
            strRange = "---- ";
        } else {
            strRange = String.valueOf(range) + " ";
        }
        if (dw == 0) {
            return String.valueOf(strRange) + "km";
        }
        return String.valueOf(strRange) + "mi";
    }

    private String GetTripAVal(int tripA, int dw) {
        String strRange;
        if (tripA >= 16777215) {
            strRange = "---- ";
        } else {
            strRange = String.format("%.1f ", new Object[]{Float.valueOf(((float) tripA) * 0.1f)});
        }
        if (dw == 0) {
            return String.valueOf(strRange) + "km";
        }
        return String.valueOf(strRange) + "mi";
    }
}
