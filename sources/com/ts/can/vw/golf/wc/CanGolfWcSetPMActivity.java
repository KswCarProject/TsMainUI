package com.ts.can.vw.golf.wc;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemCheckList;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanScrollList;

public class CanGolfWcSetPMActivity extends CanBaseActivity implements View.OnClickListener, CanItemProgressList.onPosChange, UserCallBack, CanItemPopupList.onPopItemClick {
    public static final int ITEM_AUTO_ACTIVATE = 1;
    public static final int ITEM_BCFS = 5;
    public static final int ITEM_BCZDGN = 3;
    public static final int ITEM_DCLDSY = 4;
    public static final int ITEM_FRONT_TONE = 7;
    public static final int ITEM_FRONT_VOL = 6;
    private static final int ITEM_MAX = 10;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_OFF_ROAD = 10;
    public static final int ITEM_REAR_TONE = 9;
    public static final int ITEM_REAR_VOL = 8;
    public static final int ITEM_SCCWFZ = 2;
    private String[] mArrays;
    private CanDataInfo.GolfWcInfo1 mInfo1 = new CanDataInfo.GolfWcInfo1();
    private CanItemCheckList mItemAutoActivate;
    private CanItemPopupList mItemBcfs;
    private CanItemCheckList mItemBczdgn;
    private CanItemProgressList mItemFrontTone;
    private CanItemProgressList mItemFrontVol;
    private CanItemCheckList mItemLdsy;
    private CanItemCheckList mItemOffRoad;
    private CanItemProgressList mItemRearTone;
    private CanItemProgressList mItemRearVol;
    private CanItemCheckList mItemSccwfz;
    private CanScrollList mManager;
    private CanDataInfo.GolfWcParkingManoeuvring mParkingAdt = new CanDataInfo.GolfWcParkingManoeuvring();
    private CanDataInfo.GolfWcParkingManoeuvring mParkingInfo = new CanDataInfo.GolfWcParkingManoeuvring();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        this.mArrays = getResources().getStringArray(R.array.can_bcfs_array);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.GolfWcGetParkingManoeuvring(1, this.mParkingAdt);
        CanJni.GolfWcGetParkingManoeuvring(0, this.mParkingInfo);
        CanJni.GolfWcGetInfo1(this.mInfo1);
        if (i2b(this.mParkingAdt.UpdateOnce) && (!check || i2b(this.mParkingAdt.Update))) {
            this.mParkingAdt.Update = 0;
            this.mItemAutoActivate.ShowGone(this.mParkingAdt.ActivateAuto);
            this.mItemFrontVol.ShowGone(this.mParkingAdt.FrontVol);
            this.mItemFrontTone.ShowGone(this.mParkingAdt.FrontTone);
            this.mItemRearVol.ShowGone(this.mParkingAdt.RearVol);
            this.mItemRearTone.ShowGone(this.mParkingAdt.RearTone);
            this.mItemSccwfz.ShowGone(this.mParkingAdt.Sccwfz);
            this.mItemBcfs.ShowGone(this.mParkingAdt.Bcfs);
            this.mItemBczdgn.ShowGone(this.mParkingAdt.Bczd);
            this.mItemLdsy.ShowGone(this.mParkingAdt.RadarVol);
        }
        if (i2b(this.mParkingInfo.UpdateOnce) && (!check || i2b(this.mParkingInfo.Update))) {
            this.mParkingInfo.Update = 0;
            this.mItemAutoActivate.SetCheck(this.mParkingInfo.ActivateAuto);
            this.mItemFrontVol.SetCurVal(this.mParkingInfo.FrontVol);
            this.mItemFrontTone.SetCurVal(this.mParkingInfo.FrontTone);
            this.mItemRearVol.SetCurVal(this.mParkingInfo.RearVol);
            this.mItemRearTone.SetCurVal(this.mParkingInfo.RearTone);
            this.mItemSccwfz.SetCheck(this.mParkingInfo.Sccwfz);
            this.mItemBczdgn.SetCheck(this.mParkingInfo.Bczd);
            this.mItemLdsy.SetCheck(this.mParkingInfo.RadarVol);
            this.mItemBcfs.SetSel(this.mParkingInfo.Bcfs - 1);
        }
        if (!i2b(this.mInfo1.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mInfo1.Update)) {
            this.mInfo1.Update = 0;
            this.mItemOffRoad.ShowGone(this.mInfo1.OffRoadEn);
            this.mItemOffRoad.SetCheck(this.mInfo1.OffRoadIcon);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.GolfWcQueryData(1, 69);
        Sleep(10);
        CanJni.GolfWcQueryData(1, 18);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        QueryData();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        for (int i = 1; i <= 10; i++) {
            InitItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public void InitItem(int item) {
        switch (item) {
            case 1:
                this.mItemAutoActivate = AddCheck(R.string.can_active_auto, item);
                return;
            case 2:
                this.mItemSccwfz = AddCheck(R.string.can_sccwfz, item);
                return;
            case 3:
                this.mItemBczdgn = AddCheck(R.string.can_bczdgn, item);
                return;
            case 4:
                this.mItemLdsy = AddCheck(R.string.can_lddcsy, item);
                return;
            case 5:
                this.mItemBcfs = this.mManager.addItemPopupList(R.string.can_bcfs, this.mArrays, item, (CanItemPopupList.onPopItemClick) this);
                this.mItemBcfs.ShowGone(false);
                return;
            case 6:
                this.mItemFrontVol = AddProgress(R.string.can_front_vol, item);
                return;
            case 7:
                this.mItemFrontTone = AddProgress(R.string.can_front_tone, item);
                return;
            case 8:
                this.mItemRearVol = AddProgress(R.string.can_rear_vol, item);
                return;
            case 9:
                this.mItemRearTone = AddProgress(R.string.can_rear_tone, item);
                return;
            case 10:
                this.mItemOffRoad = AddCheck(R.string.can_teramont_cross, item);
                return;
            default:
                return;
        }
    }

    private CanItemCheckList AddCheck(int strId, int id) {
        CanItemCheckList item = new CanItemCheckList(this, strId);
        item.SetIdClickListener(this, id);
        item.ShowGone(false);
        this.mManager.AddView(item.GetView());
        return item;
    }

    private CanItemProgressList AddProgress(int strId, int id) {
        CanItemProgressList item = new CanItemProgressList((Context) this, strId);
        item.SetIdCallBack(id, this);
        item.SetMinMax(1, 9);
        item.ShowGone(false);
        this.mManager.AddView(item.GetView());
        return item;
    }

    public void onItem(int id, int item) {
        if (id == 5) {
            CanJni.GolfWcParkAssistSet(7, Neg(item));
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.GolfWcParkAssistSet(1, Neg(this.mParkingInfo.ActivateAuto));
                return;
            case 2:
                CanJni.GolfWcParkAssistSet(9, Neg(this.mParkingInfo.Sccwfz));
                return;
            case 3:
                CanJni.GolfWcParkAssistSet(11, Neg(this.mParkingInfo.Bczd));
                return;
            case 4:
                CanJni.GolfWcParkAssistSet(8, Neg(this.mParkingInfo.RadarVol));
                return;
            case 10:
                CanJni.GolfWcParkAssistSet(10, Neg(this.mInfo1.OffRoadIcon));
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 6:
                CanJni.GolfWcParkAssistSet(2, pos);
                return;
            case 7:
                CanJni.GolfWcParkAssistSet(3, pos);
                return;
            case 8:
                CanJni.GolfWcParkAssistSet(4, pos);
                return;
            case 9:
                CanJni.GolfWcParkAssistSet(5, pos);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
