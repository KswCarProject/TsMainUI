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
    public static final int ITEM_AMP_DOLBY_VOLUME = 6;
    public static final int ITEM_AMP_LISTENING_POSITION = 3;
    public static final int ITEM_AMP_PREMIDIA_HD = 4;
    public static final int ITEM_AMP_PUNCH = 1;
    public static final int ITEM_AMP_SCV = 5;
    public static final int ITEM_AMP_SURROUND_TYPE = 2;
    public static final String TAG = "CanMitSubiShiOutLanderAMPSetActivity";
    private static final int[] mAmpDolbyVolumeArr = {R.string.can_amp_off, R.string.can_amp_low, R.string.can_amp_mid, R.string.can_amp_high};
    private static final int[] mAmpPremidiaHdArr = {R.string.can_amp_off, R.string.can_amp_low, R.string.can_amp_high};
    private static final int[] mAmpScvArr = {R.string.can_amp_off, R.string.can_amp_low, R.string.can_amp_mid, R.string.can_amp_high};
    private static final int[] mAmpSurroundTypeArr = {R.string.can_amp_off, R.string.can_amp_surround_type2, R.string.can_amp_surround_type3};
    private static final int[] mListeningPositionArr = {R.string.can_amp_listening_position_1, R.string.can_amp_listening_position_2, R.string.can_amp_listening_position_3};
    protected CanDataInfo.SLOutLanderAMP mAmpData = new CanDataInfo.SLOutLanderAMP();
    protected CanItemPopupList mItemAmpDolbyVolume;
    protected CanItemPopupList mItemAmpListeningPosition;
    protected CanItemPopupList mItemAmpPremidiaHd;
    protected CanItemProgressList mItemAmpPunch;
    protected CanItemPopupList mItemAmpScv;
    protected CanItemPopupList mItemAmpSurroundType;
    protected CanScrollList mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        this.mManager = new CanScrollList(this);
        this.mItemAmpPunch = new CanItemProgressList((Context) this, R.string.can_amp_punch);
        this.mItemAmpPunch.SetMinMax(2, 8);
        this.mItemAmpPunch.SetIdCallBack(1, this);
        this.mManager.AddView(this.mItemAmpPunch.GetView());
        this.mItemAmpSurroundType = AddPopupItem(R.string.can_amp_surround_type, mAmpSurroundTypeArr, 2);
        this.mItemAmpListeningPosition = AddPopupItem(R.string.can_amp_listening_position, mListeningPositionArr, 3);
        this.mItemAmpPremidiaHd = AddPopupItem(R.string.can_amp_premidia_hd, mAmpPremidiaHdArr, 4);
        this.mItemAmpScv = AddPopupItem(R.string.can_amp_scv, mAmpScvArr, 5);
        this.mItemAmpDolbyVolume = AddPopupItem(R.string.can_amp_dolby_volume, mAmpDolbyVolumeArr, 6);
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
    }

    public void onChanged(int id, int pos) {
        if (id == 1) {
            CanJni.OutLanderAMPSet(7, pos);
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 2:
                CanJni.OutLanderAMPSet(10, item);
                return;
            case 3:
                CanJni.OutLanderAMPSet(11, item);
                return;
            case 4:
                CanJni.OutLanderAMPSet(12, item);
                return;
            case 5:
                CanJni.OutLanderAMPSet(13, item);
                return;
            case 6:
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
            this.mItemAmpSurroundType.SetSel(this.mAmpData.SurroundType);
            this.mItemAmpListeningPosition.SetSel(this.mAmpData.ListeningPos);
            this.mItemAmpPremidiaHd.SetSel(this.mAmpData.PremidiaHD);
            this.mItemAmpScv.SetSel(this.mAmpData.SCV);
            this.mItemAmpDolbyVolume.SetSel(this.mAmpData.DolbyVolume);
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
