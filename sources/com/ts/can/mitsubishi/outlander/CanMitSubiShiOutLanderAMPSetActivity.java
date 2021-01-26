package com.ts.can.mitsubishi.outlander;

import android.content.Context;
import android.os.Bundle;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanScrollList;

public class CanMitSubiShiOutLanderAMPSetActivity extends CanBaseActivity implements CanItemProgressList.onPosChange, CanItemPopupList.onPopItemClick, UserCallBack {
    public static final int ITEM_AMP_BAL = 2;
    public static final int ITEM_AMP_BASS = 4;
    public static final int ITEM_AMP_DOLBY_VOLUME = 12;
    public static final int ITEM_AMP_EQ = 3;
    public static final int ITEM_AMP_FAD = 1;
    public static final int ITEM_AMP_LISTENING_POSITION = 9;
    public static final int ITEM_AMP_MID = 5;
    public static final int ITEM_AMP_PREMIDIA_HD = 10;
    public static final int ITEM_AMP_PUNCH = 7;
    public static final int ITEM_AMP_SCV = 11;
    public static final int ITEM_AMP_SURROUND_TYPE = 8;
    public static final int ITEM_AMP_TRE = 6;
    public static final String TAG = "CanMitSubiShiOutLanderAMPSetActivity";
    private static final int[] mAmpDolbyVolumeArr = {R.string.can_amp_off, R.string.can_amp_low, R.string.can_amp_mid, R.string.can_amp_high};
    private static final int[] mAmpEqArr = {R.string.can_eq_rock, R.string.can_eq_pop, R.string.can_eq_hiphop, R.string.can_psa_eq_Jazz, R.string.can_mode_normal};
    private static final int[] mAmpPremidiaHdArr = {R.string.can_amp_off, R.string.can_amp_low, R.string.can_amp_high};
    private static final int[] mAmpScvArr = {R.string.can_amp_off, R.string.can_amp_low, R.string.can_amp_mid, R.string.can_amp_high};
    private static final int[] mAmpSurroundTypeArr = {R.string.can_amp_off, R.string.can_amp_surround_type2, R.string.can_amp_surround_type3};
    private static final int[] mListeningPositionArr = {R.string.can_amp_listening_position_1, R.string.can_amp_listening_position_2, R.string.can_amp_listening_position_3};
    protected CanDataInfo.SLOutLanderAMP mAmpData = new CanDataInfo.SLOutLanderAMP();
    protected CanItemProgressList mItemAmpBal;
    protected CanItemProgressList mItemAmpBass;
    protected CanItemPopupList mItemAmpDolbyVolume;
    protected CanItemPopupList mItemAmpEq;
    protected CanItemProgressList mItemAmpFad;
    protected CanItemPopupList mItemAmpListeningPosition;
    protected CanItemProgressList mItemAmpMid;
    protected CanItemPopupList mItemAmpPremidiaHd;
    protected CanItemProgressList mItemAmpPunch;
    protected CanItemPopupList mItemAmpScv;
    protected CanItemPopupList mItemAmpSurroundType;
    protected CanItemProgressList mItemAmpTre;
    protected CanScrollList mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        this.mManager = new CanScrollList(this);
        this.mItemAmpFad = new CanItemProgressList((Context) this, R.string.can_balance_front_rear);
        this.mItemAmpFad.SetMinMax(0, 22);
        this.mItemAmpFad.SetIdCallBack(1, this);
        this.mItemAmpFad.SetCurVal(11);
        this.mManager.AddView(this.mItemAmpFad.GetView());
        this.mItemAmpFad.ShowGone(false);
        this.mItemAmpBal = new CanItemProgressList((Context) this, R.string.can_balance_left_right);
        this.mItemAmpBal.SetMinMax(0, 22);
        this.mItemAmpBal.SetIdCallBack(2, this);
        this.mItemAmpBal.SetCurVal(11);
        this.mManager.AddView(this.mItemAmpBal.GetView());
        this.mItemAmpBal.ShowGone(false);
        this.mItemAmpEq = AddPopupItem(R.string.can_eq, mAmpEqArr, 3);
        this.mItemAmpEq.ShowGone(false);
        this.mItemAmpBass = new CanItemProgressList((Context) this, R.string.can_vol_low);
        this.mItemAmpBass.SetMinMax(2, 12);
        this.mItemAmpBass.SetIdCallBack(4, this);
        this.mItemAmpBass.SetCurVal(7);
        this.mManager.AddView(this.mItemAmpBass.GetView());
        this.mItemAmpMid = new CanItemProgressList((Context) this, R.string.can_vol_middle);
        this.mItemAmpMid.SetMinMax(2, 12);
        this.mItemAmpMid.SetIdCallBack(5, this);
        this.mItemAmpMid.SetCurVal(7);
        this.mManager.AddView(this.mItemAmpMid.GetView());
        this.mItemAmpTre = new CanItemProgressList((Context) this, R.string.can_vol_high);
        this.mItemAmpTre.SetMinMax(2, 12);
        this.mItemAmpTre.SetIdCallBack(6, this);
        this.mItemAmpTre.SetCurVal(7);
        this.mManager.AddView(this.mItemAmpTre.GetView());
        this.mItemAmpPunch = new CanItemProgressList((Context) this, R.string.can_amp_punch);
        this.mItemAmpPunch.SetMinMax(2, 8);
        this.mItemAmpPunch.SetIdCallBack(7, this);
        this.mManager.AddView(this.mItemAmpPunch.GetView());
        this.mItemAmpSurroundType = AddPopupItem(R.string.can_amp_surround_type, mAmpSurroundTypeArr, 8);
        this.mItemAmpListeningPosition = AddPopupItem(R.string.can_amp_listening_position, mListeningPositionArr, 9);
        this.mItemAmpPremidiaHd = AddPopupItem(R.string.can_amp_premidia_hd, mAmpPremidiaHdArr, 10);
        this.mItemAmpScv = AddPopupItem(R.string.can_amp_scv, mAmpScvArr, 11);
        this.mItemAmpDolbyVolume = AddPopupItem(R.string.can_amp_dolby_volume, mAmpDolbyVolumeArr, 12);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        QueryData();
    }

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopupItem(int text, int[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, val, (CanItemPopupList.onPopItemClick) this);
        item.SetId(id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(true);
        return item;
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.OutLanderQuery(23, 0);
    }

    public void onChanged(int id, int pos) {
        if (id == 7) {
            CanJni.OutLanderAMPSet(7, pos);
        } else if (id == 1) {
            CanJni.OutLanderAMPSet(1, pos);
        } else if (id == 2) {
            CanJni.OutLanderAMPSet(2, pos);
        } else if (id == 4) {
            CanJni.OutLanderAMPSet(4, pos);
        } else if (id == 5) {
            CanJni.OutLanderAMPSet(6, pos);
        } else if (id == 6) {
            CanJni.OutLanderAMPSet(5, pos);
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 3:
                CanJni.OutLanderAMPSet(3, item);
                return;
            case 8:
                CanJni.OutLanderAMPSet(10, item);
                return;
            case 9:
                CanJni.OutLanderAMPSet(11, item);
                return;
            case 10:
                CanJni.OutLanderAMPSet(12, item);
                return;
            case 11:
                CanJni.OutLanderAMPSet(13, item);
                return;
            case 12:
                CanJni.OutLanderAMPSet(14, item);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.OutLanderGetAMPSet(this.mAmpData);
        if (!i2b(this.mAmpData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAmpData.Update)) {
            this.mAmpData.Update = 0;
            this.mItemAmpPunch.SetCurVal(this.mAmpData.Punch);
            this.mItemAmpPunch.SetValText(new StringBuilder(String.valueOf(this.mAmpData.Punch - 5)).toString());
            this.mItemAmpFad.SetCurVal(this.mAmpData.Fad);
            this.mItemAmpFad.SetValText(setValText(this.mAmpData.Fad, 1));
            this.mItemAmpBal.SetCurVal(this.mAmpData.Bal);
            this.mItemAmpBal.SetValText(setValText(this.mAmpData.Bal, 2));
            this.mItemAmpBass.SetCurVal(this.mAmpData.Bass);
            this.mItemAmpBass.SetValText(new StringBuilder(String.valueOf(this.mAmpData.Bass - 7)).toString());
            this.mItemAmpMid.SetCurVal(this.mAmpData.Mid);
            this.mItemAmpMid.SetValText(new StringBuilder(String.valueOf(this.mAmpData.Mid - 7)).toString());
            this.mItemAmpTre.SetCurVal(this.mAmpData.Tre);
            this.mItemAmpTre.SetValText(new StringBuilder(String.valueOf(this.mAmpData.Tre - 7)).toString());
            this.mItemAmpSurroundType.SetSel(this.mAmpData.SurroundType);
            this.mItemAmpListeningPosition.SetSel(this.mAmpData.ListeningPos);
            this.mItemAmpPremidiaHd.SetSel(this.mAmpData.PremidiaHD);
            this.mItemAmpScv.SetSel(this.mAmpData.SCV);
            this.mItemAmpDolbyVolume.SetSel(this.mAmpData.DolbyVolume);
            this.mItemAmpEq.SetSel(this.mAmpData.EQ);
        }
    }

    private String setValText(int val, int sta) {
        switch (sta) {
            case 1:
                if (val == 11) {
                    return "0";
                }
                if (val < 11) {
                    return "F" + String.valueOf(11 - val);
                }
                if (val > 11) {
                    return "R" + String.valueOf(val - 11);
                }
                break;
            case 2:
                if (val == 11) {
                    return "0";
                }
                if (val < 11) {
                    return "L" + String.valueOf(11 - val);
                }
                if (val > 11) {
                    return "R" + String.valueOf(val - 11);
                }
                break;
        }
        return "0";
    }

    public void UserAll() {
        ResetData(true);
    }
}
