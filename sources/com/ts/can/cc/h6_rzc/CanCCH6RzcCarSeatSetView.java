package com.ts.can.cc.h6_rzc;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.factoryset.AtcDisplaySettingsUtils;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;

public class CanCCH6RzcCarSeatSetView extends CanRelativeCarInfoView {
    public static final int ITEM_LT_HOT = 0;
    public static final int ITEM_LT_WIND = 2;
    public static final int ITEM_REAR_HOT = 4;
    public static final int ITEM_RT_HOT = 1;
    public static final int ITEM_RT_WIND = 3;
    public static final String TAG = "CanCCH6RzcCarSeatStatusView";
    private ParamButton mBtnLtHot;
    private ParamButton mBtnLtWind;
    private ParamButton mBtnRearHot;
    private ParamButton mBtnRtHot;
    private ParamButton mBtnRtWind;
    private CustomTextView mFrontText;
    private CustomImgView[] mLtHotVal;
    private CustomImgView[] mLtWindVal;
    private CustomImgView[] mRearHotVal;
    private CustomTextView mRearText;
    private CustomImgView[] mRtHotVal;
    private CustomImgView[] mRtWindVal;
    private CanDataInfo.ChairHotInfo m_ChairHotInfo;

    public CanCCH6RzcCarSeatSetView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View v, MotionEvent event) {
        int id = ((Integer) v.getTag()).intValue();
        int action = event.getAction();
        if (action != 0) {
            if (action == 1) {
                switch (id) {
                    case 0:
                        CanJni.CCH2sAcSet(36, 0);
                        break;
                    case 1:
                        CanJni.CCH2sAcSet(37, 0);
                        break;
                    case 2:
                        CanJni.CCH2sAcSet(38, 0);
                        break;
                    case 3:
                        CanJni.CCH2sAcSet(39, 0);
                        break;
                }
            }
        } else {
            switch (id) {
                case 0:
                    CanJni.CCH2sAcSet(36, 1);
                    break;
                case 1:
                    CanJni.CCH2sAcSet(37, 1);
                    break;
                case 2:
                    CanJni.CCH2sAcSet(38, 1);
                    break;
                case 3:
                    CanJni.CCH2sAcSet(39, 1);
                    break;
            }
        }
        return false;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                int value = this.m_ChairHotInfo.nLtChairHot;
                if (value == 3) {
                    value = -1;
                }
                CanJni.CCH2sAcSet(44, value + 1);
                return;
            case 1:
                int value2 = this.m_ChairHotInfo.nRtChairHot;
                if (value2 == 3) {
                    value2 = -1;
                }
                CanJni.CCH2sAcSet(45, value2 + 1);
                return;
            case 2:
                int value3 = this.m_ChairHotInfo.nLtChairWind;
                if (value3 == 3) {
                    value3 = -1;
                }
                CanJni.CCH2sAcSet(46, value3 + 1);
                return;
            case 3:
                int value4 = this.m_ChairHotInfo.nRtChairWind;
                if (value4 == 3) {
                    value4 = -1;
                }
                CanJni.CCH2sAcSet(47, value4 + 1);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        initCommonScreen();
    }

    public void doOnResume() {
        super.doOnResume();
    }

    private void initCommonScreen() {
        this.m_ChairHotInfo = new CanDataInfo.ChairHotInfo();
        this.mLtHotVal = new CustomImgView[3];
        this.mRtHotVal = new CustomImgView[3];
        this.mRearHotVal = new CustomImgView[3];
        this.mLtWindVal = new CustomImgView[3];
        this.mRtWindVal = new CustomImgView[3];
        RelativeLayoutManager mManager = getRelativeManager();
        this.mRearText = mManager.AddCusText(5, 300, 170, 50);
        this.mRearText.setGravity(17);
        this.mRearText.setTextSize(0, 35.0f);
        this.mRearText.setTextColor(-1);
        this.mRearText.setText(R.string.can_gl8_2017_rear_seat);
        this.mFrontText = mManager.AddCusText(5, 10, 170, 50);
        this.mFrontText.setGravity(17);
        this.mFrontText.setTextSize(0, 35.0f);
        this.mFrontText.setTextColor(-1);
        this.mFrontText.setText(R.string.can_gl8_2017_front_seat);
        AddParaButton(this.mBtnLtHot, 0, 222, 70, R.drawable.can_chairhot_lt_up, R.drawable.can_chairhot_lt_dn);
        AddParaButton(this.mBtnRtHot, 1, CanCameraUI.BTN_SENOVA_SUB_BJ40_MODE3, 70, R.drawable.can_chairhot_rt_up, R.drawable.can_chairhot_rt_dn);
        for (int i = 0; i < 3; i++) {
            this.mLtHotVal[i] = mManager.AddImage((i * 40) + Can.CAN_LUXGEN_WC, Can.CAN_DFFG_S560);
            this.mLtHotVal[i].setStateDrawable(R.drawable.conditioning_rect_up, R.drawable.conditioning_rect_dn);
            this.mRtHotVal[i] = mManager.AddImage((i * 40) + 659, Can.CAN_DFFG_S560);
            this.mRtHotVal[i].setStateDrawable(R.drawable.conditioning_rect_up, R.drawable.conditioning_rect_dn);
        }
        AddParaButton(this.mBtnLtWind, 2, 222, 200, R.drawable.can_chairwind_lt_up, R.drawable.can_chairwind_lt_dn);
        AddParaButton(this.mBtnRtWind, 3, CanCameraUI.BTN_SENOVA_SUB_BJ40_MODE3, 200, R.drawable.can_chairwind_rt_up, R.drawable.can_chairwind_rt_dn);
        for (int i2 = 0; i2 < 3; i2++) {
            this.mLtWindVal[i2] = mManager.AddImage((i2 * 40) + Can.CAN_LUXGEN_WC, 285);
            this.mLtWindVal[i2].setStateDrawable(R.drawable.conditioning_rect_up, R.drawable.conditioning_rect_blue);
            this.mRtWindVal[i2] = mManager.AddImage((i2 * 40) + 659, 285);
            this.mRtWindVal[i2].setStateDrawable(R.drawable.conditioning_rect_up, R.drawable.conditioning_rect_blue);
        }
        AddParaButton(this.mBtnRearHot, 4, 222, AtcDisplaySettingsUtils.SPECIFIC_Y_SMALL2, R.drawable.can_chairhot_lt_up, R.drawable.can_chairhot_lt_dn);
        for (int i3 = 0; i3 < 3; i3++) {
            this.mRearHotVal[i3] = mManager.AddImage((i3 * 40) + Can.CAN_LUXGEN_WC, 455);
            this.mRearHotVal[i3].setStateDrawable(R.drawable.conditioning_rect_up, R.drawable.conditioning_rect_dn);
        }
    }

    private void AddParaButton(ParamButton mBtn, int Tag, int x, int y, int up, int dn) {
        ParamButton mBtn2 = getRelativeManager().AddButton(x, y);
        mBtn2.setTag(Integer.valueOf(Tag));
        if (CanJni.GetSubType() == 10) {
            mBtn2.setOnClickListener(this);
        } else if (CanJni.GetSubType() == 8) {
            mBtn2.setOnTouchListener(this);
        }
        mBtn2.setDrawable(up, dn);
    }

    public void ResetData(boolean check) {
        CanJni.CcHfH9GetChairHotInfo(this.m_ChairHotInfo);
        if (!i2b(this.m_ChairHotInfo.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.m_ChairHotInfo.Update)) {
            setValue(this.mLtHotVal, this.m_ChairHotInfo.nLtChairHot);
            setValue(this.mRtHotVal, this.m_ChairHotInfo.nRtChairHot);
            setValue(this.mLtWindVal, this.m_ChairHotInfo.nLtChairWind);
            setValue(this.mRtWindVal, this.m_ChairHotInfo.nRtChairWind);
            setValue(this.mRearHotVal, this.m_ChairHotInfo.nRearChairHot);
        }
    }

    public void QueryData() {
        CanJni.CCCarQueryRzc(53, 0);
    }

    private void setValue(CustomImgView[] views, int value) {
        int i = 0;
        while (i < views.length) {
            views[i].setSelected(i < value);
            i++;
        }
    }
}
