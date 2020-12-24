package com.ts.can.honda.wc.accord9;

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
import com.ts.main.common.MainSet;
import com.ts.other.ParamButton;
import com.yyw.ts70xhw.KeyDef;

public class CanAccord9WcConsumpCurView extends CanRelativeCarInfoView {
    public static final int BTN_HISTORY = 1;
    public static boolean mThis = false;
    private ParamButton mBtnHistory;
    private TextView[] mCenTitle;
    private TextView[] mConsump;
    private CanDataInfo.HondaWcCurYh mCurData;
    private TextView[] mMark;
    private MyProgressBar[] mProg;
    private TextView mRange;
    private String mStrRange;
    private TextView[] mTitle;

    public CanAccord9WcConsumpCurView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
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

    /* access modifiers changed from: protected */
    public void InitUI() {
        initCommonScreen();
        this.mCurData = new CanDataInfo.HondaWcCurYh();
    }

    private void initCommonScreen() {
        addImage(62, 69, R.drawable.can_btyh_line);
        addImage(62, 182, R.drawable.can_btyh_line);
        addImage(62, 382, R.drawable.can_btyh_line);
        TextView tv = addText(372, 16, 300, 46);
        tv.setText(R.string.can_bcjszk);
        setTextStyle(tv, -1, 20);
        this.mTitle = new TextView[4];
        this.mCenTitle = new TextView[4];
        this.mProg = new MyProgressBar[4];
        this.mConsump = new TextView[4];
        for (int i = 0; i < 3; i++) {
            if (i == 0) {
                this.mTitle[i] = addText(68, 119, 133, 47);
                this.mCenTitle[i] = addText(Can.CAN_VOLVO_XFY, 119, 175, 47);
                this.mConsump[i] = addText(KeyDef.SKEY_VOLUP_2, 119, Can.CAN_CC_HF_DJ, 47);
                this.mProg[i] = new MyProgressBar(getActivity(), R.drawable.can_btyh_pro_dn, R.drawable.can_btyh_pro_up);
                getRelativeManager().AddViewWrapContent(this.mProg[i], 437, 124);
            } else {
                this.mTitle[i] = addText(68, ((i - 1) * 78) + 220, 133, 47);
                this.mCenTitle[i] = addText(Can.CAN_VOLVO_XFY, ((i - 1) * 78) + 220, 175, 47);
                this.mConsump[i] = addText(KeyDef.SKEY_VOLUP_2, ((i - 1) * 78) + 220, Can.CAN_CC_HF_DJ, 47);
                setShowGone((View) this.mTitle[i], false);
                this.mProg[i] = new MyProgressBar(getActivity(), R.drawable.can_btyh_pro_dn, R.drawable.can_btyh_pro_up);
                getRelativeManager().AddViewWrapContent(this.mProg[i], 437, ((i - 1) * 78) + Can.CAN_BENZ_SMART_OD);
            }
            this.mTitle[i].setGravity(21);
            setTextStyle(this.mTitle[i], -1, 20);
            this.mCenTitle[i].setGravity(17);
            setTextStyle(this.mCenTitle[i], -1, 20);
            this.mConsump[i].setGravity(19);
            setTextStyle(this.mConsump[i], -1, 20);
        }
        TextView tv2 = addText(68, 259, 133, 47);
        tv2.setGravity(21);
        setTextStyle(tv2, -1, 20);
        tv2.setText(R.string.can_pjyh);
        this.mMark = new TextView[3];
        this.mMark[0] = addText(437, 79, 45, 34);
        this.mMark[0].setGravity(19);
        setTextStyle(this.mMark[0], -1, 20);
        this.mMark[0].setText("0");
        this.mMark[1] = addText(CanCameraUI.BTN_TRUMPCHI_GS7_MODE8, 79, 45, 34);
        this.mMark[1].setGravity(17);
        setTextStyle(this.mMark[1], -1, 20);
        this.mMark[1].setText(MainSet.SP_XH_FORD);
        this.mMark[2] = addText(687, 79, 60, 34);
        this.mMark[2].setGravity(21);
        setTextStyle(this.mMark[2], -1, 20);
        this.mMark[2].setText("20");
        this.mTitle[0].setText(R.string.can_ssyh);
        setShowGone((View) this.mCenTitle[0], false);
        this.mCenTitle[1].setText(R.string.can_bczk);
        this.mCenTitle[2].setText(R.string.can_yslc);
        this.mStrRange = getActivity().getResources().getString(R.string.can_range_xhlc);
        this.mRange = addText(629, 390, KeyDef.RKEY_RDS_TA, 50);
        this.mRange.setText(this.mStrRange);
        this.mRange.setTextColor(-1);
        this.mRange.setGravity(21);
        this.mRange.setTextSize(0, 30.0f);
        this.mBtnHistory = addButton(410, 444);
        SetCommBtn(this.mBtnHistory, R.string.can_lsxx);
        this.mBtnHistory.setTag(1);
        this.mBtnHistory.setOnClickListener(this);
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
        if (!i2b(this.mCurData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCurData.Update)) {
            this.mCurData.Update = 0;
            int Yhlc = getCurDataYhlc(this.mCurData.Yhlc);
            this.mMark[1].setText(new StringBuilder(String.valueOf(Yhlc / 2)).toString());
            this.mMark[2].setText(new StringBuilder(String.valueOf(Yhlc)).toString());
            this.mConsump[0].setText(GetYhDW(this.mCurData.SsyhDw));
            this.mProg[0].SetMinMax(0, Yhlc);
            this.mProg[0].SetCurPos(this.mCurData.Ssyh);
            int maxpos = Yhlc * 10;
            this.mProg[1].SetMinMax(0, maxpos);
            this.mProg[2].SetMinMax(0, maxpos);
            if (this.mCurData.CurPjyh < maxpos) {
                this.mProg[1].SetCurPos(this.mCurData.CurPjyh);
            } else if (65535 == this.mCurData.CurPjyh) {
                this.mProg[1].SetCurPos(0);
            } else {
                this.mProg[1].SetCurPos(maxpos);
            }
            if (this.mCurData.HisPjyh < maxpos) {
                this.mProg[2].SetCurPos(this.mCurData.HisPjyh);
            } else if (65535 == this.mCurData.HisPjyh) {
                this.mProg[2].SetCurPos(0);
            } else {
                this.mProg[2].SetCurPos(maxpos);
            }
            this.mConsump[1].setText(GetYhVal(this.mCurData.CurPjyh, this.mCurData.CurHisYhDw));
            this.mConsump[2].setText(GetYhVal(this.mCurData.HisPjyh, this.mCurData.CurHisYhDw));
            this.mRange.setText(String.format("%s %s", new Object[]{this.mStrRange, GetRangeVal(this.mCurData.Xhlc, this.mCurData.XhlcDw)}));
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

    public void doOnPause() {
        super.doOnPause();
        mThis = false;
    }

    public void doOnResume() {
        super.doOnResume();
        mThis = true;
    }

    public void QueryData() {
        CanJni.HondaWcQuery(5, 1, 22);
    }

    private String GetYhDW(int dw) {
        switch (dw) {
            case 0:
                return "mpg";
            case 1:
                return "km/L";
            case 2:
                return "L/100km";
            default:
                return "";
        }
    }

    private String GetYhVal(int yh, int dw) {
        String ret;
        if (yh >= 65535) {
            ret = "-.- ";
        } else {
            ret = String.format("%.1f ", new Object[]{Float.valueOf(((float) yh) / 10.0f)});
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

    public static boolean DealDisp() {
        if (mThis) {
            return true;
        }
        CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 1);
        return false;
    }
}
