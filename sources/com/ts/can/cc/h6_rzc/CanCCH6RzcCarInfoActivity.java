package com.ts.can.cc.h6_rzc;

import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;
import com.yyw.ts70xhw.FtSet;

public class CanCCH6RzcCarInfoActivity extends CanBaseActivity implements UserCallBack, CanItemPopupList.onPopItemClick, View.OnClickListener {
    private static final int ITEM_ALLTERRAIN = 13;
    private static final int ITEM_ALS = 4;
    private static final int ITEM_ANTITHEFT_PREVENTION = 9;
    private static final int ITEM_DD_DELAY = 0;
    private static final int ITEM_ELECTRIC_SIDE_PEDAL_SYSTEM = 11;
    private static final int ITEM_GATING_SETTINGS = 10;
    private static final int ITEM_HEADLIGHT_MODE = 6;
    private static final int ITEM_HJ_DELAY = 1;
    private static final int ITEM_HSJZDZD = 5;
    private static final int ITEM_JD_SETUP = 2;
    private static final int ITEM_LANG = 18;
    private static final int ITEM_MAX = 19;
    private static final int ITEM_MIN = 0;
    private static final int ITEM_PARGING_SETTING = 8;
    private static final int ITEM_PKEY_NAVI = 19;
    private static final int ITEM_QPZYJXT = 14;
    private static final int ITEM_ROOF_MODE = 12;
    private static final int ITEM_SEAT_MEMOTY = 7;
    private static final int ITEM_TCSZ = 17;
    private static final int ITEM_YLGXCGQ = 3;
    private static final int ITEM_ZDJJZDXT = 15;
    private static final int ITEM_ZNQT = 16;
    private static final int[] mAlsArrays = {R.string.can_left, R.string.can_right};
    private static final int[] mGatingsettingsArrays = {R.string.can_cch9_gatingsettings_key1, R.string.can_cch9_gatingsettings_key2};
    private static final int[] mHeadlightModeArrays = {R.string.can_cch9_headlight_mode_key1, R.string.can_cch9_headlight_mode_key2};
    private static final int[] mHsjzdzdArrays = {R.string.can_mzd_cx4_drive_owner, R.string.can_mzd_cx4_drive_auto};
    private static final int[] mLangArrays = {R.string.lang_cn, R.string.lang_en_uk};
    private static final int[] mPkeyNaviArrays = {R.string.can_swkey_navi, R.string.can_swkey_rear, R.string.can_swkey_rcamera};
    private static final int[] mSeatmemoryArrays = {R.string.can_cch9_seatmemory_key1, R.string.can_cch9_seatmemory_key2};
    private static final int[] mYlgxcgqArrays = {R.string.can_h6_coupe_yz_mode, R.string.can_h6_coupe_oz_mode};
    private CanItemPopupList mAllTerrainItem;
    private CanItemPopupList mAlsItem;
    private String[] mAntiTheftPreventionArrays;
    private CanItemPopupList mAntiTheftPreventionItem;
    private CanDataInfo.H6CarSet mCarInfo = new CanDataInfo.H6CarSet();
    private String[] mDDDelayArrays;
    private CanItemPopupList mDDDelayItem;
    private CanItemPopupList mElectricSidePedalSystemItem;
    private CanItemPopupList mGatingSettingsItem;
    private String[] mHJDelayArrays;
    private CanItemPopupList mHJDelayItem;
    private CanItemPopupList mHeadLightModeItem;
    private CanItemPopupList mHsjzdzdItem;
    private String[] mJDSetupArrays;
    private CanItemPopupList mJDSetupItem;
    private CanItemPopupList mLangItem;
    private CanScrollList mManager;
    private CanItemPopupList mParkingSettingsItem;
    private CanItemPopupList mPkeyNaivItem;
    private CanItemSwitchList mQpzyjxt;
    private CanItemPopupList mRoofModeItem;
    private CanItemPopupList mSeatMemoryItem;
    private CanItemSwitchList mTcsz;
    private CanItemPopupList mYlgxcgqItem;
    private CanItemSwitchList mZdjjzzxt;
    private CanItemSwitchList mZnqt;
    private int nPkeySetb = 255;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        System.out.println("onCreate");
        this.mDDDelayArrays = getResources().getStringArray(R.array.can_cch6_dd_delay_2017_array);
        this.mHJDelayArrays = getResources().getStringArray(R.array.can_cch6_hj_delay_array);
        this.mJDSetupArrays = getResources().getStringArray(R.array.can_cch6_jd_setup_array);
        this.mAntiTheftPreventionArrays = getResources().getStringArray(R.array.can_cch9_anti_theft_prevention);
        InitUI();
    }

    private void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mPkeyNaivItem = this.mManager.addItemPopupList(R.string.can_mbgnjxz, mPkeyNaviArrays, 19, (CanItemPopupList.onPopItemClick) this);
        this.mDDDelayItem = this.mManager.addItemPopupList(R.string.can_cch6_dingdeng_delaytime, this.mDDDelayArrays, 0, (CanItemPopupList.onPopItemClick) this);
        this.mHJDelayItem = this.mManager.addItemPopupList(R.string.can_cch6_gensuihuijia_delaytime, this.mHJDelayArrays, 1, (CanItemPopupList.onPopItemClick) this);
        this.mJDSetupItem = this.mManager.addItemPopupList(R.string.can_cch6_jiedian_setup, this.mJDSetupArrays, 2, (CanItemPopupList.onPopItemClick) this);
        this.mYlgxcgqItem = this.mManager.addItemPopupList(R.string.can_h6_coupe_ylgxcgq, mYlgxcgqArrays, 3, (CanItemPopupList.onPopItemClick) this);
        this.mAlsItem = this.mManager.addItemPopupList(R.string.can_cc_als, mAlsArrays, 4, (CanItemPopupList.onPopItemClick) this);
        this.mHsjzdzdItem = this.mManager.addItemPopupList(R.string.can_zdhsjzd, mHsjzdzdArrays, 5, (CanItemPopupList.onPopItemClick) this);
        this.mHeadLightModeItem = this.mManager.addItemPopupList(R.string.can_cch9_headlightmode, mHeadlightModeArrays, 6, (CanItemPopupList.onPopItemClick) this);
        this.mSeatMemoryItem = this.mManager.addItemPopupList(R.string.can_cch9_seatmemory, mSeatmemoryArrays, 7, (CanItemPopupList.onPopItemClick) this);
        this.mParkingSettingsItem = this.mManager.addItemPopupList(R.string.can_cch9_parkingsettings, mHsjzdzdArrays, 8, (CanItemPopupList.onPopItemClick) this);
        this.mAntiTheftPreventionItem = this.mManager.addItemPopupList(R.string.can_cch9_antitheftprevention, this.mAntiTheftPreventionArrays, 9, (CanItemPopupList.onPopItemClick) this);
        this.mGatingSettingsItem = this.mManager.addItemPopupList(R.string.can_cch9_gatingsettings, mGatingsettingsArrays, 10, (CanItemPopupList.onPopItemClick) this);
        this.mElectricSidePedalSystemItem = this.mManager.addItemPopupList(R.string.can_cch9_electricsidepedalsystem, mSeatmemoryArrays, 11, (CanItemPopupList.onPopItemClick) this);
        this.mRoofModeItem = this.mManager.addItemPopupList(R.string.can_cch9_roofmode, mSeatmemoryArrays, 12, (CanItemPopupList.onPopItemClick) this);
        this.mAllTerrainItem = this.mManager.addItemPopupList(R.string.can_cch9_allterrain, mSeatmemoryArrays, 13, (CanItemPopupList.onPopItemClick) this);
        this.mLangItem = this.mManager.addItemPopupList(R.string.can_car_lang, mLangArrays, 18, (CanItemPopupList.onPopItemClick) this);
        this.mQpzyjxt = this.mManager.addItemCheckBox(R.string.can_jp_qfpzjg, 14, this);
        this.mZdjjzzxt = this.mManager.addItemCheckBox(R.string.can_zdjjzdxt, 15, this);
        this.mZnqt = this.mManager.addItemCheckBox(R.string.can_smart_qt, 16, this);
        this.mTcsz = this.mManager.addItemCheckBox(R.string.can_cch9_trailer, 17, this);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        LayoutUI();
        CanJni.CCCarQueryRzc(49, 0);
    }

    private void LayoutUI() {
        for (int i = 0; i <= 19; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 0:
                ret = 1;
                break;
            case 1:
                ret = 1;
                break;
            case 2:
                ret = 1;
                break;
            case 3:
                ret = 1;
                break;
            case 4:
                ret = 1;
                break;
            case 5:
                ret = 1;
                break;
            case 6:
                if (CanJni.GetSubType() == 8) {
                    ret = 1;
                    break;
                }
                break;
            case 7:
                if (CanJni.GetSubType() == 8) {
                    ret = 1;
                    break;
                }
                break;
            case 8:
                if (CanJni.GetSubType() == 8) {
                    ret = 1;
                    break;
                }
                break;
            case 9:
                if (CanJni.GetSubType() == 8) {
                    ret = 1;
                    break;
                }
                break;
            case 10:
                if (CanJni.GetSubType() == 8) {
                    ret = 1;
                    break;
                }
                break;
            case 11:
                if (CanJni.GetSubType() == 8) {
                    ret = 1;
                    break;
                }
                break;
            case 12:
                if (CanJni.GetSubType() == 8) {
                    ret = 1;
                    break;
                }
                break;
            case 13:
                if (CanJni.GetSubType() == 8) {
                    ret = 1;
                    break;
                }
                break;
            case 14:
                if (CanJni.GetSubType() != 10) {
                    ret = 1;
                    break;
                }
                break;
            case 15:
                if (CanJni.GetSubType() != 10) {
                    ret = 1;
                    break;
                }
                break;
            case 16:
                if (CanJni.GetSubType() != 10) {
                    ret = 1;
                    break;
                }
                break;
            case 17:
                if (CanJni.GetSubType() == 9) {
                    ret = 1;
                    break;
                }
                break;
            case 18:
                if (CanJni.GetSubType() == 8) {
                    ret = 1;
                    break;
                }
                break;
            case 19:
                ret = 1;
                break;
        }
        return i2b(ret);
    }

    private void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 0:
                this.mDDDelayItem.ShowGone(show);
                return;
            case 1:
                this.mHJDelayItem.ShowGone(show);
                return;
            case 2:
                this.mJDSetupItem.ShowGone(show);
                return;
            case 3:
                this.mYlgxcgqItem.ShowGone(show);
                return;
            case 4:
                this.mAlsItem.ShowGone(show);
                return;
            case 5:
                this.mHsjzdzdItem.ShowGone(show);
                return;
            case 6:
                this.mHeadLightModeItem.ShowGone(show);
                return;
            case 7:
                this.mSeatMemoryItem.ShowGone(show);
                return;
            case 8:
                this.mParkingSettingsItem.ShowGone(show);
                return;
            case 9:
                this.mAntiTheftPreventionItem.ShowGone(show);
                return;
            case 10:
                this.mGatingSettingsItem.ShowGone(show);
                return;
            case 11:
                this.mElectricSidePedalSystemItem.ShowGone(show);
                return;
            case 12:
                this.mRoofModeItem.ShowGone(show);
                return;
            case 13:
                this.mAllTerrainItem.ShowGone(show);
                return;
            case 14:
                this.mQpzyjxt.ShowGone(show);
                return;
            case 15:
                this.mZdjjzzxt.ShowGone(show);
                return;
            case 16:
                this.mZnqt.ShowGone(show);
                return;
            case 17:
                this.mTcsz.ShowGone(show);
                return;
            case 18:
                this.mLangItem.ShowGone(show);
                return;
            case 19:
                this.mPkeyNaivItem.ShowGone(show);
                return;
            default:
                return;
        }
    }

    private void ResetData(boolean check) {
        CanJni.CCH6GetCarSetRzc(this.mCarInfo);
        if (i2b(this.mCarInfo.UpdateOnce) && (!check || i2b(this.mCarInfo.Update))) {
            this.mCarInfo.Update = 0;
            if (this.mCarInfo.Ddys > 0) {
                this.mDDDelayItem.SetSel(this.mCarInfo.Ddys - 1);
            }
            if (this.mCarInfo.Gshjyssj > 0) {
                this.mHJDelayItem.SetSel(this.mCarInfo.Gshjyssj - 1);
            }
            if (this.mCarInfo.Jdsz > 0) {
                this.mJDSetupItem.SetSel(this.mCarInfo.Jdsz - 1);
            }
            this.mYlgxcgqItem.SetSel(this.mCarInfo.Ylgxcgq);
            this.mAlsItem.SetSel(this.mCarInfo.Als);
            this.mHsjzdzdItem.SetSel(this.mCarInfo.Hsjzdzd);
            this.mHeadLightModeItem.SetSel(this.mCarInfo.FrontLed);
            this.mSeatMemoryItem.SetSel(this.mCarInfo.ChairRemb);
            this.mAntiTheftPreventionItem.SetSel(this.mCarInfo.Fdyf);
            this.mParkingSettingsItem.SetSel(this.mCarInfo.ParkSet);
            this.mGatingSettingsItem.SetSel(this.mCarInfo.DoorContrl);
            this.mElectricSidePedalSystemItem.SetSel(this.mCarInfo.Ddtjxt);
            this.mRoofModeItem.SetSel(this.mCarInfo.CarUpMode);
            this.mAllTerrainItem.SetSel(this.mCarInfo.AllMap);
            this.mQpzyjxt.SetCheck(this.mCarInfo.Qpzyjxt);
            this.mZdjjzzxt.SetCheck(this.mCarInfo.Zdjjzdxt);
            this.mZnqt.SetCheck(this.mCarInfo.Znqt);
            this.mTcsz.SetCheck(this.mCarInfo.Tcsz);
            this.mLangItem.SetSel(this.mCarInfo.Lang);
        }
        if (this.nPkeySetb != FtSet.Getyw6() || !check) {
            this.mPkeyNaivItem.SetSel(FtSet.Getyw6());
            this.nPkeySetb = FtSet.Getyw6();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.CCH6CarSetRzc(4, item + 1, 0);
                return;
            case 1:
                CanJni.CCH6CarSetRzc(5, item + 1, 0);
                return;
            case 2:
                CanJni.CCH6CarSetRzc(6, item + 1, 0);
                return;
            case 3:
                CanJni.CCH6CarSetRzc(7, item + 1, 0);
                return;
            case 4:
                CanJni.CCH6CarSetRzc(8, item + 1, 0);
                return;
            case 5:
                CanJni.CCH6CarSetRzc(9, item, 0);
                return;
            case 6:
                CanJni.CCH6CarSetRzc(12, item, 0);
                return;
            case 7:
                CanJni.CCH6CarSetRzc(13, item, 0);
                return;
            case 8:
                CanJni.CCH6CarSetRzc(15, item, 0);
                return;
            case 9:
                CanJni.CCH6CarSetRzc(14, item, 0);
                return;
            case 10:
                CanJni.CCH6CarSetRzc(16, item, 0);
                return;
            case 11:
                CanJni.CCH6CarSetRzc(17, item, 0);
                return;
            case 12:
                CanJni.CCH6CarSetRzc(18, item, 0);
                return;
            case 13:
                CanJni.CCH6CarSetRzc(19, item, 0);
                return;
            case 18:
                CanJni.CCH6CarSetRzc(27, item, 0);
                return;
            case 19:
                FtSet.Setyw6(item);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 14:
                CanJni.CCH6CarSetRzc(23, Neg(this.mCarInfo.Qpzyjxt), 0);
                return;
            case 15:
                CanJni.CCH6CarSetRzc(24, Neg(this.mCarInfo.Zdjjzdxt), 0);
                return;
            case 16:
                CanJni.CCH6CarSetRzc(11, Neg(this.mCarInfo.Znqt), 0);
                return;
            case 17:
                CanJni.CCH6CarSetRzc(25, Neg(this.mCarInfo.Tcsz), 0);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
