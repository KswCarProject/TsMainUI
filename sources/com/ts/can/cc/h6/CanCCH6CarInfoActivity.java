package com.ts.can.cc.h6;

import android.os.Bundle;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemCarType;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanScrollList;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class CanCCH6CarInfoActivity extends CanBaseActivity implements UserCallBack, CanItemPopupList.onPopItemClick {
    private static final int ITEM_CAR_TYPE = 0;
    private static final int ITEM_DD_DELAY = 1;
    private static final int ITEM_HJ_DELAY = 2;
    private static final int ITEM_HRLTS = 5;
    private static final int ITEM_JD_SETUP = 3;
    private static final int ITEM_LANGUAGE = 8;
    private static final int ITEM_MAX = 8;
    private static final int ITEM_MIN = 0;
    private static final int ITEM_TGZT = 7;
    private static final int ITEM_YLGXCGQ = 4;
    private static final int ITEM_YLSS = 6;
    private static final int[] mHrltsArrays = {R.string.can_h6_coupe_hr, R.string.can_h6_coupe_lt};
    private static final int[] mLanguageArrays = {R.string.can_lang_cn, R.string.can_lang_en};
    private static final int[] mTgztArrays = {R.string.can_off, R.string.can_on};
    private static final int[] mYlgxcgqArrays = {R.string.can_h6_coupe_yz_mode, R.string.can_h6_coupe_oz_mode};
    private static final int[] mYlssArrays = {R.string.can_off, R.string.can_cdjd, R.string.can_cdzj, R.string.can_cdjg};
    private CanDataInfo.CcH6CarInfo mCarInfo = new CanDataInfo.CcH6CarInfo();
    private String[] mCarTypeArrays;
    private CanItemCarType mCarTypeItem;
    private String[] mDDDelayArrays;
    private CanItemPopupList mDDDelayItem;
    private String[] mHJDelayArrays;
    private CanItemPopupList mHJDelayItem;
    private CanItemPopupList mHrltsItem;
    private String[] mJDSetupArrays;
    private CanItemPopupList mJDSetupItem;
    private CanItemPopupList mLanguageItem;
    private CanScrollList mManager;
    private CanItemPopupList mTgztItem;
    private CanItemPopupList mYlgxcgqItem;
    private CanItemPopupList mYlssItem;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        System.out.println("onCreate");
        this.mDDDelayArrays = getResources().getStringArray(R.array.can_cch6_dd_delay_array);
        this.mHJDelayArrays = getResources().getStringArray(R.array.can_cch6_hj_delay_array);
        this.mJDSetupArrays = getResources().getStringArray(R.array.can_cch6_jd_setup_array);
        this.mCarTypeArrays = getResources().getStringArray(R.array.can_h6_car_type_array);
        InitUI();
    }

    private void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mCarTypeItem = this.mManager.addItemCarType(R.string.can_car_type_select, this.mCarTypeArrays, 0, (CanItemPopupList.onPopItemClick) this);
        this.mDDDelayItem = this.mManager.addItemPopupList(R.string.can_cch6_dingdeng_delaytime, this.mDDDelayArrays, 1, (CanItemPopupList.onPopItemClick) this);
        this.mHJDelayItem = this.mManager.addItemPopupList(R.string.can_cch6_gensuihuijia_delaytime, this.mHJDelayArrays, 2, (CanItemPopupList.onPopItemClick) this);
        this.mJDSetupItem = this.mManager.addItemPopupList(R.string.can_cch6_jiedian_setup, this.mJDSetupArrays, 3, (CanItemPopupList.onPopItemClick) this);
        this.mYlgxcgqItem = this.mManager.addItemPopupList(R.string.can_h6_coupe_ylgxcgq, mYlgxcgqArrays, 4, (CanItemPopupList.onPopItemClick) this);
        this.mHrltsItem = this.mManager.addItemPopupList(R.string.can_h6_coupe_hrlts, mHrltsArrays, 5, (CanItemPopupList.onPopItemClick) this);
        this.mYlssItem = this.mManager.addItemPopupList(R.string.can_h6_coupe_ylss, mYlssArrays, 6, (CanItemPopupList.onPopItemClick) this);
        this.mTgztItem = this.mManager.addItemPopupList(R.string.can_h6_coupe_tgzt, mTgztArrays, 7, (CanItemPopupList.onPopItemClick) this);
        this.mLanguageItem = this.mManager.addItemPopupList(R.string.can_lang_set, mLanguageArrays, 8, (CanItemPopupList.onPopItemClick) this);
        this.mCarTypeItem.GetPopItem().SetSel(CanJni.GetSubType());
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        CanJni.CCH6Query(3, 0);
        CanJni.CCH6Query(4, 0);
        LayoutUI();
    }

    private void LayoutUI() {
        for (int i = 0; i <= 8; i++) {
            ShowItem(i);
        }
    }

    private void ShowItem(int item) {
        boolean show = true;
        if (1 != CanJni.GetSubType()) {
            show = false;
        }
        switch (item) {
            case 4:
                this.mYlgxcgqItem.ShowGone(show);
                return;
            case 5:
                this.mHrltsItem.ShowGone(show);
                return;
            case 6:
                this.mYlssItem.ShowGone(show);
                return;
            case 7:
                this.mTgztItem.ShowGone(show);
                return;
            case 8:
                this.mLanguageItem.ShowGone(show);
                return;
            default:
                return;
        }
    }

    private void ResetData(boolean check) {
        CanJni.CCH6GetCarSet(this.mCarInfo);
        if (!i2b(this.mCarInfo.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCarInfo.Update)) {
            this.mCarInfo.Update = 0;
            this.mDDDelayItem.SetSel(this.mCarInfo.Ddyssjsd);
            this.mHJDelayItem.SetSel(this.mCarInfo.Gshjyssj);
            this.mJDSetupItem.SetSel(this.mCarInfo.Jdsz);
            this.mYlgxcgqItem.SetSel(this.mCarInfo.Ylgxcgqsz);
            this.mHrltsItem.SetSel(this.mCarInfo.Hrlts);
            this.mYlssItem.SetSel(this.mCarInfo.Ylss);
            this.mTgztItem.SetSel(this.mCarInfo.Tcsz);
            this.mLanguageItem.SetSel(this.mCarInfo.Lang);
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
                FtSet.SetCanSubT(item);
                Mcu.SendXKey(20);
                return;
            case 1:
                CanJni.CCH6CarSet(0, item);
                return;
            case 2:
                CanJni.CCH6CarSet(1, item);
                return;
            case 3:
                CanJni.CCH6CarSet(2, item);
                return;
            case 4:
                CanJni.CCH6CarSet(3, item);
                return;
            case 5:
                CanJni.CCH6CarSet(21, item);
                return;
            case 6:
                CanJni.CCH6CarSet(22, item);
                return;
            case 7:
                CanJni.CCH6CarSet(48, item);
                return;
            case 8:
                CanJni.CCH6CarSet(64, item);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
