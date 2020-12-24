package com.ts.can.df;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemCarType;
import com.ts.canview.CanItemFsSetList;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollBaseActivity;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class CanT70CarInfoActivity extends CanScrollBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick, CanItemProgressList.onPosChange, CanItemFsSetList.onFsSetClick {
    protected static final int ITEM_A_V_M_FLASH = 3;
    protected static final int ITEM_CAR_TYPE = 5;
    protected static final int ITEM_FIRST_BOOT = 2;
    protected static final int ITEM_FXPCF = 8;
    protected static final int ITEM_LT_LIGHT = 6;
    protected static final int ITEM_MAX = 9;
    protected static final int ITEM_MIN = 1;
    protected static final int ITEM_QJDCF = 9;
    protected static final int ITEM_RESET = 4;
    protected static final int ITEM_RT_LIGHT = 7;
    protected static final int ITEM_SMART_IN = 1;
    public static final String TAG = "CanT70CarInfoActivity";
    private static String[] mTypeArray;
    protected CanItemSwitchList mItemAVMFlash;
    protected CanItemCarType mItemCarType;
    protected CanItemSwitchList mItemFirstBoot;
    protected CanItemSwitchList mItemFxpCf;
    protected CanItemSwitchList mItemLtLight;
    protected CanItemSwitchList mItemQjdCf;
    protected CanItemFsSetList mItemReset;
    protected CanItemSwitchList mItemRtLight;
    protected CanItemSwitchList mItemSmartIn;
    protected CanDataInfo.VenuciaCam mSetData = new CanDataInfo.VenuciaCam();
    protected boolean mbLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        mTypeArray = getResources().getStringArray(R.array.can_qc_t70_car_type_array);
        this.mItemCarType = this.mManager.addItemCarType(R.string.can_car_type_select, mTypeArray, 5, (CanItemPopupList.onPopItemClick) this);
        this.mItemSmartIn = AddSwitch((View.OnClickListener) this, "智能进入", 1);
        this.mItemFirstBoot = AddSwitch((View.OnClickListener) this, "首次前进档自动启动", 2);
        this.mItemAVMFlash = AddSwitch((View.OnClickListener) this, "AVM开机动画", 3);
        this.mItemLtLight = AddSwitch((View.OnClickListener) this, "左转向灯触发", 6);
        this.mItemRtLight = AddSwitch((View.OnClickListener) this, "右转向灯触发", 7);
        this.mItemFxpCf = AddSwitch((View.OnClickListener) this, "方向盘触发", 8);
        this.mItemQjdCf = AddSwitch((View.OnClickListener) this, "前进档触发", 9);
        this.mItemReset = new CanItemFsSetList(this);
        this.mItemReset.SetIdClickListener(4, this);
        this.mManager.AddView(this.mItemReset.GetView());
        this.mItemCarType.GetPopItem().SetSel(CanJni.GetSubType());
        LayoutUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.QCGetCamSetup(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemSmartIn.SetCheck(this.mSetData.fgSmartIn);
            this.mItemFirstBoot.SetCheck(this.mSetData.fgFirstBoot);
            this.mItemAVMFlash.SetCheck(this.mSetData.fgAVMFlash);
            this.mItemLtLight.SetCheck(this.mSetData.fgZzxdcf);
            this.mItemRtLight.SetCheck(this.mSetData.fgYzxdcf);
            this.mItemFxpCf.SetCheck(this.mSetData.fgFxpcf);
            this.mItemQjdCf.SetCheck(this.mSetData.fgQjdcf);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.QCQuery(97, 0);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        QueryData();
        Log.d(TAG, "-----onResume-----");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        Log.d(TAG, "-----onPause-----");
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 9; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int subType = CanJni.GetSubType();
        int ret = 0;
        switch (item) {
            case 1:
                if (subType != 2) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
            case 2:
                if (subType != 2) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
            case 3:
                if (subType != 2) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
            case 4:
                if (subType != 2) {
                    ret = 1;
                    break;
                } else {
                    ret = 0;
                    break;
                }
            case 6:
                if (subType != 1) {
                    ret = 0;
                    break;
                } else {
                    ret = 1;
                    break;
                }
            case 7:
                if (subType != 1) {
                    ret = 0;
                    break;
                } else {
                    ret = 1;
                    break;
                }
            case 8:
                if (subType != 1) {
                    ret = 0;
                    break;
                } else {
                    ret = 1;
                    break;
                }
            case 9:
                if (subType != 1) {
                    ret = 0;
                    break;
                } else {
                    ret = 1;
                    break;
                }
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemSmartIn.ShowGone(show);
                return;
            case 2:
                this.mItemFirstBoot.ShowGone(show);
                return;
            case 3:
                this.mItemAVMFlash.ShowGone(show);
                return;
            case 4:
                this.mItemReset.ShowGone(show);
                return;
            case 6:
                this.mItemLtLight.ShowGone(show);
                return;
            case 7:
                this.mItemRtLight.ShowGone(show);
                return;
            case 8:
                this.mItemFxpCf.ShowGone(show);
                return;
            case 9:
                this.mItemQjdCf.ShowGone(show);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CarSet(1, Neg(this.mSetData.fgSmartIn));
                return;
            case 2:
                CarSet(2, Neg(this.mSetData.fgFirstBoot));
                return;
            case 3:
                CarSet(3, Neg(this.mSetData.fgAVMFlash));
                return;
            case 6:
                CarSet(5, Neg(this.mSetData.fgZzxdcf));
                return;
            case 7:
                CarSet(6, Neg(this.mSetData.fgYzxdcf));
                return;
            case 8:
                CarSet(7, Neg(this.mSetData.fgFxpcf));
                return;
            case 9:
                CarSet(8, Neg(this.mSetData.fgQjdcf));
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 5:
                FtSet.SetCanSubT(item);
                Mcu.SendXKey(20);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    /* access modifiers changed from: protected */
    public void CarSet(int cmd, int para) {
        CanJni.QCCamSet(cmd, para);
    }

    public void onFsItem(int id, boolean sure) {
        if (sure) {
            CanJni.QCCamSet(4, 1);
        } else {
            CanJni.QCCamSet(4, 0);
        }
    }
}
