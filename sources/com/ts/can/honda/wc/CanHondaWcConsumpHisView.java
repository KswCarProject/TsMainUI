package com.ts.can.honda.wc;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanFunc;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.canview.MyProgressBar;
import com.ts.other.ParamButton;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.KeyDef;

public class CanHondaWcConsumpHisView extends CanRelativeCarInfoView {
    public static final int BTN_CLEAR = 2;
    public static final int BTN_CURRENT = 1;
    public static boolean mThis = false;
    private ParamButton mBtnClear;
    private ParamButton mBtnCurrent;
    private TextView[] mConsump;
    private int[] mConsumpData = new int[4];
    private CanDataInfo.HondaWcCurYh mCurData;
    private CanDataInfo.HondaWcHisYh mHistoryData;
    private TextView[] mMark;
    private MyProgressBar[] mProg;
    private TextView mRange;
    private String mStrRange;
    private TextView[] mTitle;
    private TextView[] mTripA;
    private int[] mTripAData = new int[4];

    public CanHondaWcConsumpHisView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                enterSubWin(CanCarInfoSub1Activity.class, 1);
                return;
            case 2:
                CanJni.HondaWcCameraSet(6, 255);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        initCommonScreen();
        this.mCurData = new CanDataInfo.HondaWcCurYh();
        this.mHistoryData = new CanDataInfo.HondaWcHisYh();
    }

    private void initCommonScreen() {
        addImage(62, 69, R.drawable.can_btyh_line);
        addImage(62, 182, R.drawable.can_btyh_line);
        addImage(62, 382, R.drawable.can_btyh_line);
        TextView tv = addText(372, 16, 300, 46);
        tv.setText(R.string.can_jszkll);
        setTextStyle(tv, -1, 20);
        this.mTitle = new TextView[4];
        this.mTripA = new TextView[4];
        this.mProg = new MyProgressBar[4];
        this.mConsump = new TextView[4];
        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                this.mTitle[i] = addText(68, 119, 133, 47);
                this.mTripA[i] = addText(Can.CAN_VOLVO_XFY, 119, 175, 47);
                this.mConsump[i] = addText(KeyDef.SKEY_VOLUP_2, 119, Can.CAN_CC_HF_DJ, 47);
                this.mProg[i] = new MyProgressBar(getActivity(), R.drawable.can_btyh_pro_dn, R.drawable.can_btyh_pro_up);
                getRelativeManager().AddViewWrapContent(this.mProg[i], 437, 124);
            } else {
                this.mTitle[i] = addText(68, ((i - 1) * 63) + 197, 133, 47);
                this.mTripA[i] = addText(Can.CAN_VOLVO_XFY, ((i - 1) * 63) + 197, 175, 47);
                this.mConsump[i] = addText(KeyDef.SKEY_VOLUP_2, ((i - 1) * 63) + 197, Can.CAN_CC_HF_DJ, 47);
                this.mProg[i] = new MyProgressBar(getActivity(), R.drawable.can_btyh_pro_dn, R.drawable.can_btyh_pro_up);
                getRelativeManager().AddViewWrapContent(this.mProg[i], 437, ((i - 1) * 61) + Can.CAN_LEXUS_IZ);
            }
            this.mTitle[i].setGravity(21);
            setTextStyle(this.mTitle[i], -1, 20);
            this.mTripA[i].setGravity(17);
            setTextStyle(this.mTripA[i], -1, 20);
            this.mConsump[i].setGravity(19);
            setTextStyle(this.mConsump[i], -1, 20);
        }
        this.mTitle[0].setText(R.string.can_bczk);
        this.mTitle[1].setText(R.string.can_1st);
        this.mTitle[2].setText(R.string.can_2st);
        this.mTitle[3].setText(R.string.can_3st);
        this.mMark = new TextView[3];
        this.mMark[0] = addText(437, 79, 45, 34);
        this.mMark[0].setGravity(19);
        setTextStyle(this.mMark[0], -1, 20);
        this.mMark[0].setText("0");
        this.mMark[1] = addText(CanCameraUI.BTN_TRUMPCHI_GS7_MODE8, 79, 45, 34);
        this.mMark[1].setGravity(17);
        setTextStyle(this.mMark[1], -1, 20);
        this.mMark[1].setText("10");
        this.mMark[2] = addText(687, 79, 60, 34);
        this.mMark[2].setGravity(21);
        setTextStyle(this.mMark[2], -1, 20);
        this.mMark[2].setText("20");
        TextView tv2 = addText(281, 79, 100, 34);
        setTextStyle(tv2, -1, 20);
        tv2.setText("TRIP A");
        TextView tv3 = addText(KeyDef.SKEY_VOLUP_2, 79, Can.CAN_CC_HF_DJ, 34);
        setTextStyle(tv3, -1, 20);
        tv3.setText(R.string.can_pjyh);
        this.mStrRange = getActivity().getResources().getString(R.string.can_range_xhlc);
        this.mRange = addText(629, 390, KeyDef.RKEY_RDS_TA, 50);
        this.mRange.setGravity(21);
        setTextStyle(this.mRange, -1, 20);
        this.mBtnCurrent = addButton(410, 444);
        SetCommBtn(this.mBtnCurrent, R.string.can_jsxx);
        this.mBtnCurrent.setTag(1);
        this.mBtnCurrent.setOnClickListener(this);
        this.mBtnClear = addButton(110, 444);
        SetCommBtn(this.mBtnClear, R.string.can_clear);
        this.mBtnClear.setTag(2);
        this.mBtnClear.setOnClickListener(this);
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
        btn.setTextSize(0, 30.0f);
    }

    public void ResetData(boolean check) {
        CanJni.HondaWcGetCurYh(this.mCurData);
        CanJni.HondaWcGetHisYh(this.mHistoryData);
        if (i2b(this.mCurData.UpdateOnce) && (!check || i2b(this.mCurData.Update))) {
            this.mCurData.Update = 0;
            this.mTripA[0].setText(GetTripAVal(this.mCurData.TripA, this.mCurData.TripaDw));
            this.mConsump[0].setText(GetYhVal(this.mCurData.Pjyh, this.mCurData.PjyhDw));
            int Yhlc = getCurDataYhlc(this.mCurData.Yhlc);
            this.mMark[1].setText(new StringBuilder(String.valueOf(Yhlc / 2)).toString());
            this.mMark[2].setText(new StringBuilder(String.valueOf(Yhlc)).toString());
            int maxpos = Yhlc * 10;
            this.mProg[0].SetMinMax(0, maxpos);
            if (this.mCurData.Pjyh < maxpos) {
                this.mProg[0].SetCurPos(this.mCurData.Pjyh);
            } else if (65535 == this.mCurData.Pjyh) {
                this.mProg[0].SetCurPos(0);
            } else {
                this.mProg[0].SetCurPos(maxpos);
            }
            this.mRange.setText(String.format("%s %s", new Object[]{this.mStrRange, GetRangeVal(this.mCurData.Xhlc, this.mCurData.XhlcDw)}));
        }
        if (!i2b(this.mHistoryData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mHistoryData.Update)) {
            this.mHistoryData.Update = 0;
            this.mTripAData[1] = this.mHistoryData.TripA1[0];
            this.mTripAData[2] = this.mHistoryData.TripA2[0];
            this.mTripAData[3] = this.mHistoryData.TripA3[0];
            this.mConsumpData[1] = this.mHistoryData.TripA1[1];
            this.mConsumpData[2] = this.mHistoryData.TripA2[1];
            this.mConsumpData[3] = this.mHistoryData.TripA3[1];
            int maxpos2 = getCurDataYhlc(this.mCurData.Yhlc) * 10;
            for (int i = 1; i < 4; i++) {
                this.mTripA[i].setText(GetTripAVal(this.mTripAData[i], 0));
                this.mConsump[i].setText(GetYhVal(this.mConsumpData[i], 2));
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

    private int getCurDataYhlc(int yhlc) {
        switch (yhlc) {
            case 0:
                return 60;
            case 1:
                return 10;
            case 2:
                return 12;
            case 3:
                return 20;
            case 4:
                return 30;
            case 5:
                return 40;
            case 6:
                return 50;
            case 7:
                return 70;
            case 8:
                return 80;
            case 9:
                return 90;
            case 10:
                return 100;
            default:
                return yhlc;
        }
    }

    public void doOnResume() {
        super.doOnResume();
        mThis = true;
    }

    public void QueryData() {
        CanJni.HondaWcQuery(5, 1, 23);
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

    public static boolean DealDisp() {
        if (mThis) {
            return true;
        }
        CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 1);
        return false;
    }
}
