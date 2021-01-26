package com.ts.can.cc.h6_rzc;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanItemTitleValList;
import com.ts.canview.CanScrollList;

public class CanCCH9RzcAmpSetActivity extends CanBaseActivity implements UserCallBack, CanItemPopupList.onPopItemClick, CanItemProgressList.onPosChange, View.OnClickListener {
    private static final int ITEM_BALANCEBEFOREANDAFTER = 5;
    private static final int ITEM_BALANCELEFTANDRIGHT = 4;
    private static final int ITEM_EQUALIZERALTO = 2;
    private static final int ITEM_EQUALIZERBASS = 3;
    private static final int ITEM_EQUALIZERTREBLE = 1;
    private static final int ITEM_MAX = 9;
    private static final int ITEM_MIN = 0;
    private static final int ITEM_SURROUNDSOUND = 6;
    private static final int ITEM_VOLUMEINFORMATION = 0;
    private static final int ITEM_VOLUMEWITHSPEED = 7;
    private static final int ITEM_YSYYXF = 9;
    private static final int ITEM_YXSZ = 8;
    private static final int[] mSurroundSoundItemArrays = {R.string.can_cch9_surround_sound_key1, R.string.can_cch9_surround_sound_key2};
    private static String[] mWithTheVolumeArrays;
    private static final int[] mYxszArrays = {R.string.can_zc, R.string.can_zjs, R.string.can_lbw, R.string.can_xhsm, R.string.can_qp, R.string.can_hp};
    private CanItemProgressList mBalanceBeforeAndAfterItem;
    private CanItemProgressList mBalanceLeftAndRightItem;
    private CanItemProgressList mEqualizerAltoItem;
    private CanItemProgressList mEqualizerBassItem;
    private CanItemProgressList mEqualizerTrebleItem;
    private CanDataInfo.H9AmpSet mHAmpSet = new CanDataInfo.H9AmpSet();
    private CanScrollList mManager;
    Resources mResources;
    private CanItemPopupList mSurroundSoundItem;
    private CanItemProgressList mVolumeInformationItem;
    private CanItemPopupList mVolumeWithSpeedItem;
    private CanItemSwitchList mYsyyxfItem;
    private CanItemPopupList mYxszItem;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        System.out.println("onCreate");
        this.mResources = getResources();
        mWithTheVolumeArrays = this.mResources.getStringArray(R.array.can_cch9_with_the_volume_array);
        InitUI();
    }

    private void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mVolumeInformationItem = new CanItemProgressList((Context) this, R.string.can_cch9_volume_information);
        this.mVolumeInformationItem.SetMinMax(0, 40);
        this.mVolumeInformationItem.SetIdCallBack(0, this);
        this.mManager.AddView(this.mVolumeInformationItem.GetView());
        this.mEqualizerTrebleItem = new CanItemProgressList((Context) this, R.string.can_cch9_equalizer_treble);
        this.mEqualizerTrebleItem.SetMinMax(0, 20);
        this.mEqualizerTrebleItem.SetIdCallBack(1, this);
        this.mManager.AddView(this.mEqualizerTrebleItem.GetView());
        this.mEqualizerAltoItem = new CanItemProgressList((Context) this, R.string.can_cch9_equalizer_alto);
        this.mEqualizerAltoItem.SetMinMax(0, 20);
        this.mEqualizerAltoItem.SetIdCallBack(2, this);
        this.mManager.AddView(this.mEqualizerAltoItem.GetView());
        this.mEqualizerBassItem = new CanItemProgressList((Context) this, R.string.can_cch9_equalizer_bass);
        this.mEqualizerBassItem.SetMinMax(0, 20);
        this.mEqualizerBassItem.SetIdCallBack(3, this);
        this.mManager.AddView(this.mEqualizerBassItem.GetView());
        this.mBalanceLeftAndRightItem = new CanItemProgressList((Context) this, R.string.can_cch9_balance_left_and_right);
        this.mBalanceLeftAndRightItem.SetMinMax(0, 20);
        this.mBalanceLeftAndRightItem.SetIdCallBack(4, this);
        this.mManager.AddView(this.mBalanceLeftAndRightItem.GetView());
        this.mBalanceBeforeAndAfterItem = new CanItemProgressList((Context) this, R.string.can_cch9_balance_before_and_after);
        this.mBalanceBeforeAndAfterItem.SetMinMax(0, 20);
        this.mBalanceBeforeAndAfterItem.SetIdCallBack(5, this);
        this.mManager.AddView(this.mBalanceBeforeAndAfterItem.GetView());
        this.mSurroundSoundItem = this.mManager.addItemPopupList(R.string.can_cch9_surround_sound, mSurroundSoundItemArrays, 6, (CanItemPopupList.onPopItemClick) this);
        this.mVolumeWithSpeedItem = this.mManager.addItemPopupList(R.string.can_cch9_with_the_volume, mWithTheVolumeArrays, 7, (CanItemPopupList.onPopItemClick) this);
        this.mYxszItem = this.mManager.addItemPopupList(R.string.can_yxsd, mYxszArrays, 8, (CanItemPopupList.onPopItemClick) this);
        this.mYsyyxfItem = this.mManager.addItemCheckBox(R.string.can_ysyyxf, 9, this);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        CanJni.CCCarQueryRzc(55, 0);
        ResetData(false);
        LayoutUI();
    }

    private void LayoutUI() {
        for (int i = 0; i <= 9; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public CanItemTitleValList AddItemTitleVal(int text, int id) {
        CanItemTitleValList item = new CanItemTitleValList(this, text);
        item.SetIconVisibility(0);
        this.mManager.AddView(item.GetView());
        item.ShowGone(false);
        item.SetIconVisibility(8);
        return item;
    }

    private void ShowItem(int item) {
        switch (item) {
            case 0:
                this.mVolumeInformationItem.ShowGone(true);
                return;
            case 1:
                this.mEqualizerTrebleItem.ShowGone(true);
                return;
            case 2:
                this.mEqualizerAltoItem.ShowGone(true);
                return;
            case 3:
                this.mEqualizerBassItem.ShowGone(true);
                return;
            case 4:
                this.mBalanceLeftAndRightItem.ShowGone(true);
                return;
            case 5:
                this.mBalanceBeforeAndAfterItem.ShowGone(true);
                return;
            case 6:
                this.mSurroundSoundItem.ShowGone(true);
                return;
            case 7:
                this.mVolumeWithSpeedItem.ShowGone(true);
                return;
            case 8:
                if (CanJni.GetSubType() == 8) {
                    this.mYxszItem.ShowGone(true);
                    return;
                }
                return;
            case 9:
                if (CanJni.GetSubType() == 8) {
                    this.mYsyyxfItem.ShowGone(true);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void ResetData(boolean check) {
        CanJni.CcHfH9GetAmpInfo(this.mHAmpSet);
        if (!i2b(this.mHAmpSet.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mHAmpSet.Update)) {
            this.mHAmpSet.Update = 0;
            this.mVolumeInformationItem.SetCurVal(this.mHAmpSet.Vol);
            this.mVolumeInformationItem.SetValText(new StringBuilder().append(this.mHAmpSet.Vol).toString());
            this.mEqualizerTrebleItem.SetCurVal(this.mHAmpSet.Treble);
            this.mEqualizerTrebleItem.SetValText(new StringBuilder().append(this.mHAmpSet.Treble - 10).toString());
            this.mEqualizerAltoItem.SetCurVal(this.mHAmpSet.Middle);
            this.mEqualizerAltoItem.SetValText(new StringBuilder().append(this.mHAmpSet.Middle - 10).toString());
            this.mEqualizerBassItem.SetCurVal(this.mHAmpSet.Bass);
            this.mEqualizerBassItem.SetValText(new StringBuilder().append(this.mHAmpSet.Bass - 10).toString());
            this.mBalanceLeftAndRightItem.SetCurVal(this.mHAmpSet.Lr);
            if (this.mHAmpSet.Lr >= 0 && this.mHAmpSet.Lr < 10) {
                this.mBalanceLeftAndRightItem.SetValText("L" + (this.mHAmpSet.Lr + 1));
            } else if (this.mHAmpSet.Lr <= 10 || this.mHAmpSet.Lr > 20) {
                this.mBalanceLeftAndRightItem.SetValText(new StringBuilder().append(this.mHAmpSet.Lr - 10).toString());
            } else {
                this.mBalanceLeftAndRightItem.SetValText("R" + (this.mHAmpSet.Lr - 10));
            }
            this.mBalanceBeforeAndAfterItem.SetCurVal(this.mHAmpSet.Fr);
            if (this.mHAmpSet.Fr >= 0 && this.mHAmpSet.Fr < 10) {
                this.mBalanceBeforeAndAfterItem.SetValText("L" + (this.mHAmpSet.Fr + 1));
            } else if (this.mHAmpSet.Fr <= 10 || this.mHAmpSet.Fr > 20) {
                this.mBalanceBeforeAndAfterItem.SetValText(new StringBuilder().append(this.mHAmpSet.Fr - 10).toString());
            } else {
                this.mBalanceBeforeAndAfterItem.SetValText("R" + (this.mHAmpSet.Fr - 10));
            }
            this.mSurroundSoundItem.SetSel(this.mHAmpSet.Stereo - 1);
            this.mVolumeWithSpeedItem.SetSel(this.mHAmpSet.VolWithSpeed);
            this.mYxszItem.SetSel(this.mHAmpSet.EqSet);
            this.mYsyyxfItem.SetCheck(this.mHAmpSet.Ysyyxf);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 6:
                CanJni.CcHfH9AmpSet(7, item + 1);
                return;
            case 7:
                CanJni.CcHfH9AmpSet(8, item);
                return;
            case 8:
                if (item == 0) {
                    CanJni.CcHfH9AmpSet(9, 5);
                    return;
                } else {
                    CanJni.CcHfH9AmpSet(9, item - 1);
                    return;
                }
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 0:
                CanJni.CcHfH9AmpSet(1, pos);
                return;
            case 1:
                CanJni.CcHfH9AmpSet(2, pos);
                return;
            case 2:
                CanJni.CcHfH9AmpSet(3, pos);
                return;
            case 3:
                CanJni.CcHfH9AmpSet(4, pos);
                return;
            case 4:
                CanJni.CcHfH9AmpSet(5, pos);
                return;
            case 5:
                CanJni.CcHfH9AmpSet(6, pos);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 9:
                CanJni.CcHfH9AmpSet(10, Neg(this.mHAmpSet.Ysyyxf));
                return;
            default:
                return;
        }
    }
}
