package com.ts.can.cc.h6_rzc;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCameraUI;
import com.ts.can.CanFunc;
import com.ts.other.CustomImgView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.KeyDef;

public class CanCCH9RzcACActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener, View.OnTouchListener {
    private static final int AC_REAR = 9;
    private static final int CLOSED = 16;
    private static final int LT_TEMP_INCREASE = 0;
    private static final int LT_TEMP__DECREASE = 1;
    private static final int REAR_CLOSED = 32;
    private static final int REAR_WIND_DECREASE = 23;
    private static final int REAR_WIND_INCREASE = 22;
    private static final int RR_TEMP_DECREASE = 7;
    private static final int RR_TEMP_INCREASE = 6;
    private static final int RT_TEMP_INCREASE = 2;
    private static final int RT_TEMP__DECREASE = 3;
    private static final int STATUS_AC = 15;
    private static final int STATUS_AUTO = 13;
    private static final int STATUS_DUAL = 11;
    private static final int STATUS_FOOTWINWIND = 35;
    private static final int STATUS_HAVWIND = 26;
    private static final int STATUS_HWIND = 24;
    private static final int STATUS_LHOT = 20;
    private static final int STATUS_MODE = 14;
    private static final int STATUS_OUT_LOOP = 12;
    private static final int STATUS_REARHOT = 33;
    private static final int STATUS_REAR_AUTO = 31;
    private static final int STATUS_REAR_HAVWIND = 29;
    private static final int STATUS_REAR_HWIND = 27;
    private static final int STATUS_REAR_OFF = 30;
    private static final int STATUS_REAR_VWIND = 28;
    private static final int STATUS_RHOT = 21;
    private static final int STATUS_VWIND = 25;
    private static final int STATUS_WINDOW1 = 17;
    private static final int STATUS_WINDOW2 = 18;
    private static final int STATUS_WINOW = 10;
    private static final int STATUS_WINWIND = 34;
    private static final int STATUS_ZONE = 19;
    private static final int WIND_DECREASE = 5;
    private static final int WIND_INCREASE = 4;
    private static boolean mfgJump;
    private boolean isAutoFinish = false;
    private ParamButton[] mACMode = new ParamButton[5];
    private int[] mIcons = {R.drawable.can_rh7_signal01_dn, R.drawable.can_rh7_signal02_dn, R.drawable.can_rh7_signal03_dn, R.drawable.can_rh7_signal04_dn, R.drawable.can_rh7_signal05_dn, R.drawable.can_rh7_signal06_dn, R.drawable.can_rh7_signal07_dn};
    private CustomImgView[] mLHotIcons = new CustomImgView[4];
    private TextView mLeftTemp;
    private RelativeLayoutManager mManager;
    private CustomImgView[] mRHotIcons = new CustomImgView[4];
    private TextView mRRightTemp;
    private ParamButton[] mRearACMode = new ParamButton[3];
    private TextView mRearAir;
    private CustomImgView[] mRearHotIcons = new CustomImgView[4];
    private int[] mRearIcons = {R.drawable.can_rh7_8signal01_dn, R.drawable.can_rh7_8signal02_dn, R.drawable.can_rh7_8signal03_dn, R.drawable.can_rh7_8signal04_dn, R.drawable.can_rh7_8signal05_dn, R.drawable.can_rh7_8signal06_dn, R.drawable.can_rh7_8signal07_dn, R.drawable.can_rh7_8signal08_dn};
    private CustomImgView[] mRearWindIcons = new CustomImgView[8];
    private TextView mRightTemp;
    private ParamButton mStatusAc;
    private ParamButton mStatusAuto;
    private ParamButton mStatusLhot;
    private ParamButton mStatusOutLoop;
    private ParamButton mStatusRearAuto;
    private ParamButton mStatusRearClose;
    private ParamButton mStatusRearHot;
    private ParamButton mStatusRhot;
    private ParamButton mStatusZone;
    private TextView mTextView1;
    private CustomImgView[] mWindIcons = new CustomImgView[7];
    private CanDataInfo.ChairHotInfo m_ChairHotInfo = new CanDataInfo.ChairHotInfo();
    private CanDataInfo.RearAirInfo m_RearAcInfo = new CanDataInfo.RearAirInfo();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_relative);
        mfgJump = CanFunc.IsCanActivityJumped(this);
        InitView();
    }

    private void InitView() {
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        addButton(50, 37, R.drawable.can_rh7_jia_up, R.drawable.can_rh7_jia_dn, 0);
        addButton(50, Can.CAN_LIEBAO_WC, R.drawable.can_rh7_jian_up, R.drawable.can_rh7_jian_dn, 1);
        this.mLeftTemp = addText(53, 148, 92, 61);
        addButton(880, 37, R.drawable.can_rh7_jia_up, R.drawable.can_rh7_jia_dn, 2);
        addButton(880, Can.CAN_LIEBAO_WC, R.drawable.can_rh7_jian_up, R.drawable.can_rh7_jian_dn, 3);
        this.mRightTemp = addText(883, 148, 92, 61);
        addButton(681, 435, R.drawable.can_rh7_jia_up, R.drawable.can_rh7_jia_dn, 6);
        addButton(CanCameraUI.BTN_GEELY_YJX6_FXP, 435, R.drawable.can_rh7_jian_up, R.drawable.can_rh7_jian_dn, 7);
        this.mRRightTemp = addText(CanCameraUI.BTN_CCH9_MODE3, 448, 92, 61);
        addButton(188, 37, R.drawable.can_rh7_fsb_up, R.drawable.can_rh7_fsb_dn, 4);
        addButton(188, Can.CAN_LIEBAO_WC, R.drawable.can_rh7_fss_up, R.drawable.can_rh7_fss_dn, 5);
        addImage(191, 148, R.drawable.can_rh7_signal_up);
        for (int i = 0; i < this.mWindIcons.length; i++) {
            this.mWindIcons[i] = addImage(191, 148, this.mIcons[i]);
        }
        this.mACMode[0] = addButton(KeyDef.RKEY_MEDIA_ANGLE, 68, R.drawable.can_rh7_icon01_up, R.drawable.can_rh7_icon01_dn, 24);
        this.mACMode[1] = addButton(KeyDef.RKEY_MEDIA_ANGLE, 145, R.drawable.can_rh7_icon03_up, R.drawable.can_rh7_icon03_dn, 25);
        this.mACMode[2] = addButton(KeyDef.RKEY_MEDIA_ANGLE, 222, R.drawable.can_rh7_icon02_up, R.drawable.can_rh7_icon02_dn, 26);
        this.mACMode[3] = addButton(385, 105, R.drawable.can_rh7_icon05_up, R.drawable.can_rh7_icon05_dn, 34);
        this.mACMode[4] = addButton(385, 175, R.drawable.can_rh7_icon04_up, R.drawable.can_rh7_icon04_dn, 35);
        this.mRearACMode[0] = addButton(365, 345, R.drawable.can_rh7_icon01_up, R.drawable.can_rh7_icon01_dn, 27);
        this.mRearACMode[1] = addButton(465, 345, R.drawable.can_rh7_icon03_up, R.drawable.can_rh7_icon03_dn, 28);
        this.mRearACMode[2] = addButton(CanCameraUI.BTN_TRUMPCHI_GS7_MODE6, 345, R.drawable.can_rh7_icon02_up, R.drawable.can_rh7_icon02_dn, 29);
        this.mStatusOutLoop = addButton(757, 162, R.drawable.can_rh7_nxh_up, R.drawable.can_rh7_wxh_up, 12);
        this.mStatusAc = addButton(CanCameraUI.BTN_LANDWIND_3D_RIGHT_UP, 50, R.drawable.can_rh7_ac_up, R.drawable.can_rh7_ac_dn, 15);
        this.mStatusZone = addButton(CanCameraUI.BTN_LANDWIND_3D_RIGHT_UP, 162, R.drawable.can_rh7_3zone_up, R.drawable.can_rh7_3zone_dn, 19);
        this.mStatusAuto = addButton(757, 50, R.drawable.can_rh7_auto_up, R.drawable.can_rh7_auto_dn, 13);
        this.mStatusLhot = addButton(CanCameraUI.BTN_CHANA_ALSVINV7_MODE1, 50, R.drawable.can_rh7_ljr0_up, R.drawable.can_rh7_ljr0_dn, 20);
        this.mStatusRhot = addButton(CanCameraUI.BTN_CHANA_ALSVINV7_MODE1, 162, R.drawable.can_rh7_rjr0_up, R.drawable.can_rh7_rjr0_dn, 21);
        addButton(392, 435, R.drawable.can_rh7_fsb_up, R.drawable.can_rh7_fsb_dn, 22);
        addButton(Can.CAN_TOYOTA_SP_XP, 435, R.drawable.can_rh7_fss_up, R.drawable.can_rh7_fss_dn, 23);
        addImage(KeyDef.RKEY_MEDIA_10, 450, R.drawable.can_rh7_8signal00_up);
        for (int i2 = 0; i2 < this.mRearWindIcons.length; i2++) {
            this.mRearWindIcons[i2] = addImage(KeyDef.RKEY_MEDIA_10, 450, this.mRearIcons[i2]);
        }
        this.mStatusRearClose = addButton(KeyDef.SKEY_CALLDN_2, 430, R.drawable.can_rh7_del_dn, R.drawable.can_rh7_del_up, 32);
        this.mStatusRearAuto = addButton(112, 435, R.drawable.can_rh7_auto_up, R.drawable.can_rh7_auto_dn, 31);
        this.mStatusRearHot = addButton(CanCameraUI.BTN_CHANA_ALSVINV7_MODE1, 435, R.drawable.can_rh7_ljr0_up, R.drawable.can_rh7_ljr0_up, 33);
        addImage(47, 20, R.drawable.can_rh7_yuan_bg);
        addImage(187, 20, R.drawable.can_rh7_yuan_bg);
        addImage(877, 20, R.drawable.can_rh7_yuan_bg);
        addImage(Can.CAN_TOYOTA_SP_XP, 430, R.drawable.can_rh7_yuan_bg1);
        addImage(537, 430, R.drawable.can_rh7_yuan_bg1);
        for (int i3 = 0; i3 < this.mLHotIcons.length; i3++) {
            this.mLHotIcons[i3] = addImage((i3 * 8) + 558, 100, R.drawable.can_rh7_jt);
            this.mLHotIcons[i3].Show(false);
        }
        for (int i4 = 0; i4 < this.mRHotIcons.length; i4++) {
            this.mRHotIcons[i4] = addImage((i4 * 8) + CanCameraUI.BTN_TRUMPCHI_GS7_MODE3, 212, R.drawable.can_rh7_jt);
            this.mRHotIcons[i4].Show(false);
        }
        for (int i5 = 0; i5 < this.mRearHotIcons.length; i5++) {
            this.mRearHotIcons[i5] = addImage((i5 * 8) + 558, 354, R.drawable.can_rh7_jt);
            this.mRearHotIcons[i5].Show(false);
        }
        this.mTextView1 = addText(10, KeyDef.RKEY_RDS_TA, 100, 100);
        this.mTextView1.setText(R.string.can_gl8_2017_rear_seat);
        this.mTextView1.setTextSize(0, 30.0f);
        this.mStatusLhot.Show(false);
        this.mStatusRhot.Show(false);
        this.mStatusRearHot.Show(false);
    }

    public ParamButton addButton(int x, int y, int normal, int selected, int id) {
        ParamButton button = this.mManager.AddButton(x, y);
        button.setStateDrawable(normal, selected, selected);
        button.setOnTouchListener(this);
        button.setTag(Integer.valueOf(id));
        return button;
    }

    private CustomImgView addImage(int x, int y, int resId) {
        return this.mManager.AddImage(x, y, resId);
    }

    private TextView addText(int x, int y, int w, int h) {
        TextView text = this.mManager.AddText(x, y, w, h);
        text.setTextColor(Color.parseColor("#ffffff"));
        text.setTextSize(0, 27.0f);
        text.setGravity(17);
        return text;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        UpdateACUI();
        CanFunc.mfgShowAC = false;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        Can.updateAC();
        if (!CanFunc.mfgShowAC) {
            if (!this.isAutoFinish) {
                finish();
            }
            mfgJump = false;
            this.isAutoFinish = false;
        }
    }

    private String changeTemp(int temp) {
        double cTemp = (((double) (temp - 116)) / 2.0d) + 18.0d;
        Log.d("_lh_", "temp = " + cTemp);
        if (temp == 0) {
            return String.format("LO", new Object[0]);
        }
        if (temp == 255) {
            return String.format("HI", new Object[0]);
        }
        return String.format("%.1f℃", new Object[]{Double.valueOf(cTemp)});
    }

    private void UpdateACUI() {
        boolean z;
        CanDataInfo.CAN_ACInfo mAcInfo = Can.mACInfo;
        Can.mACInfo.Update = 0;
        this.m_RearAcInfo.Update = 0;
        this.m_ChairHotInfo.Update = 0;
        this.mLeftTemp.setText(mAcInfo.szLtTemp);
        this.mRightTemp.setText(mAcInfo.szRtTemp);
        this.mRRightTemp.setText(changeTemp(this.m_RearAcInfo.Temp));
        if (this.m_RearAcInfo.Mode == 1) {
            this.mRearACMode[0].setSelected(true);
            this.mRearACMode[1].setSelected(false);
            this.mRearACMode[2].setSelected(false);
        } else if (this.m_RearAcInfo.Mode == 2) {
            this.mRearACMode[0].setSelected(false);
            this.mRearACMode[1].setSelected(false);
            this.mRearACMode[2].setSelected(true);
        } else if (this.m_RearAcInfo.Mode == 3) {
            this.mRearACMode[0].setSelected(false);
            this.mRearACMode[1].setSelected(true);
            this.mRearACMode[2].setSelected(false);
        }
        this.mStatusAc.SetSel(mAcInfo.fgAC);
        this.mStatusZone.SetSel(mAcInfo.fgDual);
        ParamButton paramButton = this.mStatusOutLoop;
        if (mAcInfo.fgInnerLoop == 0) {
            z = true;
        } else {
            z = false;
        }
        paramButton.setSelected(z);
        setRearWindValue(this.m_RearAcInfo.Wind);
        setWindValue(mAcInfo.nWindValue);
        int footWind = mAcInfo.fgDownWind;
        int headWind = mAcInfo.fgParallelWind;
        int winWind = mAcInfo.fgForeWindMode;
        if ((i2b(footWind) && i2b(headWind) && i2b(winWind)) || ((i2b(headWind) && i2b(winWind)) || (i2b(footWind) && i2b(winWind)))) {
            setAcMode(4);
        } else if (i2b(footWind) && i2b(headWind)) {
            setAcMode(2);
        } else if (i2b(headWind)) {
            setAcMode(0);
        } else if (i2b(footWind)) {
            setAcMode(1);
        } else if (i2b(winWind)) {
            setAcMode(3);
        } else {
            setAcMode(-1);
        }
        this.mStatusAuto.SetSel(mAcInfo.nAutoLight);
        this.mStatusRearAuto.SetSel(this.m_RearAcInfo.Auto);
        this.mStatusRearClose.SetSel(this.m_RearAcInfo.Power);
    }

    private void setAcMode(int index) {
        int i = 0;
        while (i < this.mACMode.length) {
            this.mACMode[i].setSelected(i == index);
            i++;
        }
    }

    private void setWindValue(int wind) {
        for (int i = 0; i < this.mWindIcons.length; i++) {
            this.mWindIcons[i].Show(i + 1 <= wind);
        }
    }

    private void setRearWindValue(int wind) {
        for (int i = 0; i < this.mRearWindIcons.length; i++) {
            this.mRearWindIcons[i].Show(i + 1 <= wind);
        }
    }

    private void setLHotValue(int value) {
        int i = 0;
        while (i < this.mLHotIcons.length) {
            this.mLHotIcons[i].Show(i < value);
            i++;
        }
    }

    private void setRHotValue(int value) {
        int i = 0;
        while (i < this.mRHotIcons.length) {
            this.mRHotIcons[i].Show(i < value);
            i++;
        }
    }

    private void setRearHotValue(int value) {
        int i = 0;
        while (i < this.mRearHotIcons.length) {
            this.mRearHotIcons[i].Show(i < value);
            i++;
        }
    }

    public boolean onTouch(View v, MotionEvent event) {
        int param;
        int action = event.getAction();
        int id = ((Integer) v.getTag()).intValue();
        if (action == 0) {
            param = 1;
        } else {
            if (action == 1) {
                param = 0;
            }
            return false;
        }
        switch (id) {
            case 0:
                CanJni.CCH2sAcSet(30, param);
                break;
            case 1:
                CanJni.CCH2sAcSet(31, param);
                break;
            case 2:
                CanJni.CCH2sAcSet(32, param);
                break;
            case 3:
                CanJni.CCH2sAcSet(33, param);
                break;
            case 4:
                CanJni.CCH2sAcSet(28, param);
                break;
            case 5:
                CanJni.CCH2sAcSet(29, param);
                break;
            case 6:
                CanJni.CCH2sAcSet(133, param);
                break;
            case 7:
                CanJni.CCH2sAcSet(134, param);
                break;
            case 12:
                CanJni.CCH2sAcSet(19, param);
                break;
            case 13:
                CanJni.CCH2sAcSet(34, param);
                break;
            case 15:
                CanJni.CCH2sAcSet(17, param);
                break;
            case 16:
                CanJni.CCH2sAcSet(16, param);
                break;
            case 19:
                CanJni.CCH2sAcSet(35, param);
                break;
            case 20:
                CanJni.CCH2sAcSet(36, param);
                break;
            case 21:
                CanJni.CCH2sAcSet(37, param);
                break;
            case 22:
                CanJni.CCH2sAcSet(131, param);
                break;
            case 23:
                CanJni.CCH2sAcSet(132, param);
                break;
            case 24:
                CanJni.CCH2sAcSet(24, param);
                break;
            case 25:
                CanJni.CCH2sAcSet(26, param);
                break;
            case 26:
                CanJni.CCH2sAcSet(25, param);
                break;
            case 27:
                CanJni.CCH2sAcSet(135, param);
                break;
            case 28:
                CanJni.CCH2sAcSet(137, param);
                break;
            case 29:
                CanJni.CCH2sAcSet(136, param);
                break;
            case 31:
                CanJni.CCH2sAcSet(130, param);
                break;
            case 32:
                CanJni.CCH2sAcSet(129, param);
                break;
            case 34:
                CanJni.CCH2sAcSet(21, param);
                break;
            case 35:
                CanJni.CCH2sAcSet(27, param);
                break;
        }
        CanFunc.mLastACTick = GetTickCount();
        return false;
    }

    public void UserAll() {
        Can.updateAC();
        CanJni.CcHfH9GetRearAir(this.m_RearAcInfo);
        CanJni.CcHfH9GetChairHotInfo(this.m_ChairHotInfo);
        if (!(Can.mACInfo.Update == 0 && this.m_RearAcInfo.Update == 0 && this.m_ChairHotInfo.Update == 0)) {
            UpdateACUI();
        }
        if (mfgJump && GetTickCount() > CanFunc.mLastACTick + 6000) {
            finish();
        }
    }

    public void onClick(View arg0) {
    }
}
