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
import com.ts.can.CanFunc;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.canview.MyProgressBar;
import com.ts.main.common.MainSet;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanHondaDaRzcConsumpCurrentView extends CanRelativeCarInfoView {
    public static final int BTN_HISTORY = 1;
    public static boolean mThis = false;
    private ParamButton mBtnHistory;
    private CustomTextView[] mCenTitle;
    private CustomTextView[] mConsump;
    private CanDataInfo.HondaYLLCData_1 mCurData;
    private RelativeLayoutManager mManager;
    private CustomTextView[] mMark;
    private MyProgressBar[] mProg;
    private CustomTextView mRange;
    private String mStrRange;
    private CustomTextView[] mTitle;

    public CanHondaDaRzcConsumpCurrentView(Activity activity) {
        super(activity);
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mCurData = new CanDataInfo.HondaYLLCData_1();
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
        tv.setText(R.string.can_bcjszk);
        tv.SetPxSize(40);
        this.mTitle = new CustomTextView[4];
        this.mCenTitle = new CustomTextView[4];
        this.mProg = new MyProgressBar[4];
        this.mConsump = new CustomTextView[4];
        for (int i = 0; i < 3; i++) {
            if (i == 0) {
                this.mTitle[i] = this.mManager.AddCusText(195, 75, 133, 47);
                this.mCenTitle[i] = this.mManager.AddCusText(Can.CAN_VOLVO_XFY, 119, 175, 47);
                this.mConsump[i] = this.mManager.AddCusText(914, 94, Can.CAN_CC_HF_DJ, 47);
                this.mProg[i] = new MyProgressBar(getActivity(), R.drawable.can_btyh_pro_up, R.drawable.can_btyh_pro_dn);
                this.mManager.AddViewWrapContent(this.mProg[i], CanCameraUI.BTN_TRUMPCHI_GS7_MODE5, 104);
            } else {
                this.mTitle[i] = this.mManager.AddCusText(68, ((i - 1) * 78) + 190, 133, 47);
                this.mCenTitle[i] = this.mManager.AddCusText(KeyDef.RKEY_res3, ((i - 1) * 78) + 184, 175, 47);
                this.mConsump[i] = this.mManager.AddCusText(914, ((i - 1) * 78) + 186, Can.CAN_CC_HF_DJ, 47);
                this.mTitle[i].ShowGone(false);
                this.mProg[i] = new MyProgressBar(getActivity(), R.drawable.can_btyh_pro_up, R.drawable.can_btyh_pro_dn);
                this.mManager.AddViewWrapContent(this.mProg[i], CanCameraUI.BTN_TRUMPCHI_GS7_MODE5, ((i - 1) * 78) + 190);
            }
            this.mTitle[i].setGravity(21);
            this.mTitle[i].SetPxSize(40);
            this.mCenTitle[i].setGravity(17);
            this.mCenTitle[i].SetPxSize(40);
            this.mConsump[i].setGravity(19);
            this.mConsump[i].SetPxSize(40);
        }
        CustomTextView tv2 = this.mManager.AddCusText(195, 220, 133, 47);
        tv2.setGravity(21);
        tv2.SetPxSize(40);
        tv2.setText(R.string.can_pjyh);
        this.mMark = new CustomTextView[3];
        this.mMark[0] = this.mManager.AddCusText(CanCameraUI.BTN_TRUMPCHI_GS7_MODE5, 59, 45, 34);
        this.mMark[0].setGravity(19);
        this.mMark[0].SetPxSize(30);
        this.mMark[0].setText("0");
        this.mMark[1] = this.mManager.AddCusText(694, 59, 45, 34);
        this.mMark[1].setGravity(17);
        this.mMark[1].SetPxSize(30);
        this.mMark[1].setText(MainSet.SP_XH_FORD);
        this.mMark[2] = this.mManager.AddCusText(KeyDef.SKEY_PP_1, 59, 45, 34);
        this.mMark[2].setGravity(21);
        this.mMark[2].SetPxSize(30);
        this.mMark[2].setText("20");
        this.mTitle[0].setText(R.string.can_ssyh);
        this.mCenTitle[0].ShowGone(false);
        this.mCenTitle[1].setText(R.string.can_bczk);
        this.mCenTitle[2].setText(R.string.can_yslc);
        this.mStrRange = getActivity().getResources().getString(R.string.can_range_xhlc);
        this.mRange = this.mManager.AddCusText(756, 365, KeyDef.RKEY_RDS_TA, 50);
        this.mRange.setText(this.mStrRange);
        this.mRange.setGravity(21);
        this.mRange.SetPxSize(40);
        this.mBtnHistory = this.mManager.AddButton(195, 357, 167, 63);
        SetCommBtn(this.mBtnHistory, R.string.can_lsxx);
        this.mBtnHistory.setTag(1);
        this.mBtnHistory.setOnClickListener(this);
    }

    private void initCommonScreen() {
        this.mManager = getRelativeManager();
        this.mManager.AddImage(62, 69, R.drawable.can_btyh_line);
        this.mManager.AddImage(62, 182, R.drawable.can_btyh_line);
        this.mManager.AddImage(62, 382, R.drawable.can_btyh_line);
        CustomTextView tv = this.mManager.AddCusText(372, 16, 300, 46);
        tv.setText(R.string.can_bcjszk);
        tv.SetPxSize(40);
        this.mTitle = new CustomTextView[4];
        this.mCenTitle = new CustomTextView[4];
        this.mProg = new MyProgressBar[4];
        this.mConsump = new CustomTextView[4];
        for (int i = 0; i < 3; i++) {
            if (i == 0) {
                this.mTitle[i] = this.mManager.AddCusText(68, 119, 133, 47);
                this.mCenTitle[i] = this.mManager.AddCusText(Can.CAN_VOLVO_XFY, 119, 175, 47);
                this.mConsump[i] = this.mManager.AddCusText(KeyDef.SKEY_VOLUP_2, 119, Can.CAN_CC_HF_DJ, 47);
                this.mProg[i] = new MyProgressBar(getActivity(), R.drawable.can_btyh_pro_up, R.drawable.can_btyh_pro_dn);
                this.mManager.AddViewWrapContent(this.mProg[i], 437, 124);
            } else {
                this.mTitle[i] = this.mManager.AddCusText(68, ((i - 1) * 78) + 220, 133, 47);
                this.mCenTitle[i] = this.mManager.AddCusText(Can.CAN_VOLVO_XFY, ((i - 1) * 78) + 220, 175, 47);
                this.mConsump[i] = this.mManager.AddCusText(KeyDef.SKEY_VOLUP_2, ((i - 1) * 78) + 220, Can.CAN_CC_HF_DJ, 47);
                this.mTitle[i].ShowGone(false);
                this.mProg[i] = new MyProgressBar(getActivity(), R.drawable.can_btyh_pro_up, R.drawable.can_btyh_pro_dn);
                this.mManager.AddViewWrapContent(this.mProg[i], 437, ((i - 1) * 78) + Can.CAN_BENZ_SMART_OD);
            }
            this.mTitle[i].setGravity(21);
            this.mTitle[i].SetPxSize(40);
            this.mCenTitle[i].setGravity(17);
            this.mCenTitle[i].SetPxSize(40);
            this.mConsump[i].setGravity(19);
            this.mConsump[i].SetPxSize(40);
        }
        CustomTextView tv2 = this.mManager.AddCusText(68, 259, 133, 47);
        tv2.setGravity(21);
        tv2.SetPxSize(40);
        tv2.setText(R.string.can_pjyh);
        this.mMark = new CustomTextView[3];
        this.mMark[0] = this.mManager.AddCusText(437, 79, 45, 34);
        this.mMark[0].setGravity(19);
        this.mMark[0].SetPxSize(30);
        this.mMark[0].setText("0");
        this.mMark[1] = this.mManager.AddCusText(CanCameraUI.BTN_TRUMPCHI_GS7_MODE8, 79, 45, 34);
        this.mMark[1].setGravity(17);
        this.mMark[1].SetPxSize(30);
        this.mMark[1].setText(MainSet.SP_XH_FORD);
        this.mMark[2] = this.mManager.AddCusText(697, 79, 45, 34);
        this.mMark[2].setGravity(21);
        this.mMark[2].SetPxSize(30);
        this.mMark[2].setText("20");
        this.mTitle[0].setText(R.string.can_ssyh);
        this.mCenTitle[0].ShowGone(false);
        this.mCenTitle[1].setText(R.string.can_bczk);
        this.mCenTitle[2].setText(R.string.can_yslc);
        this.mStrRange = getActivity().getResources().getString(R.string.can_range_xhlc);
        this.mRange = this.mManager.AddCusText(629, 390, KeyDef.RKEY_RDS_TA, 50);
        this.mRange.setText(this.mStrRange);
        this.mRange.setGravity(21);
        this.mRange.SetPxSize(40);
        this.mBtnHistory = this.mManager.AddButton(410, 444);
        SetCommBtn(this.mBtnHistory, R.string.can_lsxx);
        this.mBtnHistory.setTag(1);
        this.mBtnHistory.setOnClickListener(this);
    }

    public void doOnResume() {
        super.doOnResume();
        ResetData(false);
        QueryData();
        mThis = true;
    }

    public void doOnPause() {
        super.doOnPause();
        mThis = false;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                enterSubWin(CanCarInfoSub1Activity.class, -1);
                return;
            default:
                return;
        }
    }

    public static boolean DealDisp() {
        if (mThis) {
            return true;
        }
        CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 2);
        return false;
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void ResetData(boolean check) {
        CanJni.HondaDAGetConsumpCurrnt(this.mCurData);
        if (!i2b(this.mCurData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCurData.Update)) {
            this.mCurData.Update = 0;
            this.mMark[1].setText(new StringBuilder(String.valueOf(this.mCurData.Yhlc / 2)).toString());
            this.mMark[2].setText(new StringBuilder(String.valueOf(this.mCurData.Yhlc)).toString());
            this.mConsump[0].setText(GetYhDW(this.mCurData.DwJsyh));
            this.mProg[0].SetMinMax(0, 21);
            this.mProg[0].SetCurPos(this.mCurData.Jsyh);
            int maxpos = this.mCurData.Yhlc * 10;
            this.mProg[1].SetMinMax(0, maxpos);
            this.mProg[2].SetMinMax(0, maxpos);
            if (this.mCurData.Dqpjyh < maxpos) {
                this.mProg[1].SetCurPos(this.mCurData.Dqpjyh);
            } else if (65535 == this.mCurData.Dqpjyh) {
                this.mProg[1].SetCurPos(0);
            } else {
                this.mProg[1].SetCurPos(maxpos);
            }
            if (this.mCurData.Lspjyh < maxpos) {
                this.mProg[2].SetCurPos(this.mCurData.Lspjyh);
            } else if (65535 == this.mCurData.Lspjyh) {
                this.mProg[2].SetCurPos(0);
            } else {
                this.mProg[2].SetCurPos(maxpos);
            }
            this.mConsump[1].setText(GetYhVal(this.mCurData.Dqpjyh, this.mCurData.DwDqlspjyh));
            this.mConsump[2].setText(GetYhVal(this.mCurData.Lspjyh, this.mCurData.DwDqlspjyh));
            this.mRange.setText(String.format("%s %s", new Object[]{this.mStrRange, GetRangeVal(this.mCurData.Range, this.mCurData.DwRange)}));
        }
    }

    public void QueryData() {
        CanJni.HondaDAQuery(51, 1);
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
                return "";
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
}
