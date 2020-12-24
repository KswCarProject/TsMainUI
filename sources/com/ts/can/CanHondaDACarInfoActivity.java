package com.ts.can;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.canview.CanItemCarType;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class CanHondaDACarInfoActivity extends CanBaseActivity implements View.OnClickListener, CanItemPopupList.onPopItemClick {
    public static final int ITEM_360PANORAMA_SET = 16;
    public static final int ITEM_AMP_SET = 12;
    public static final int ITEM_CAMERA_SET = 13;
    public static final int ITEM_CAR_SET = 8;
    public static final int ITEM_COMPASS = 1;
    public static final int ITEM_CONSUMPTION = 2;
    public static final int ITEM_DISTANCE = 4;
    public static final int ITEM_DISTANCE_ILL = 5;
    public static final int ITEM_JSPZXTSZ = 9;
    public static final int ITEM_LOCK = 6;
    public static final int ITEM_MOTO_R_DOOR = 11;
    public static final int ITEM_QJXYSYSTEM_SET = 15;
    public static final int ITEM_REMOTE_LOCK = 7;
    public static final int ITEM_SYSTEM_SET = 10;
    public static final int ITEM_TYPE = 3;
    public static final int ITEM_YIBIAO_SET = 14;
    public static final String TAG = "CanHondaDACarInfoActivity";
    private static String[] mTypeArr = new String[0];
    protected CanItemTextBtnList mItem360PanoramaSet;
    protected CanItemTextBtnList mItemAmpSet;
    protected CanItemTextBtnList mItemCameraSet;
    protected CanItemTextBtnList mItemCarSet;
    protected CanItemCarType mItemCarType;
    protected CanItemTextBtnList mItemCompass;
    protected CanItemTextBtnList mItemConsumption;
    protected CanItemTextBtnList mItemDisatnce;
    protected CanItemTextBtnList mItemDistanceIll;
    protected CanItemTextBtnList mItemJspzXtsz;
    protected CanItemTextBtnList mItemLock;
    protected CanItemTextBtnList mItemMotoRearDoor;
    protected CanItemTextBtnList mItemQJYXSystemSet;
    protected CanItemTextBtnList mItemRemoteLock;
    protected CanItemTextBtnList mItemSystemSet;
    protected CanItemTextBtnList mItemYibiaoSet;
    protected CanScrollList mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        mTypeArr = getResources().getStringArray(R.array.can_fs_declare_5);
        this.mManager = new CanScrollList(this);
        this.mItemCarType = new CanItemCarType((Context) this, R.string.can_car_type_select, mTypeArr, 3, (CanItemPopupList.onPopItemClick) this);
        this.mManager.AddView(this.mItemCarType.GetView());
        this.mItemCompass = new CanItemTextBtnList((Context) this, R.string.can_compass);
        this.mItemCompass.SetIdClickListener(this, 1);
        this.mItemConsumption = new CanItemTextBtnList((Context) this, R.string.can_consumption);
        this.mItemConsumption.SetIdClickListener(this, 2);
        this.mItemDisatnce = new CanItemTextBtnList((Context) this, R.string.can_distance_sz);
        this.mItemDisatnce.SetIdClickListener(this, 4);
        this.mItemDistanceIll = new CanItemTextBtnList((Context) this, R.string.can_distanceill_sz);
        this.mItemDistanceIll.SetIdClickListener(this, 5);
        this.mItemLock = new CanItemTextBtnList((Context) this, R.string.can_cssz);
        this.mItemLock.SetIdClickListener(this, 6);
        this.mItemRemoteLock = new CanItemTextBtnList((Context) this, R.string.can_remote_lock);
        this.mItemRemoteLock.SetIdClickListener(this, 7);
        this.mItemCarSet = new CanItemTextBtnList((Context) this, R.string.can_csshez);
        this.mItemCarSet.SetIdClickListener(this, 8);
        this.mItemJspzXtsz = new CanItemTextBtnList((Context) this, R.string.can_jsfzxysz);
        this.mItemJspzXtsz.SetIdClickListener(this, 9);
        this.mItemSystemSet = new CanItemTextBtnList((Context) this, R.string.can_system_set);
        this.mItemSystemSet.SetIdClickListener(this, 10);
        this.mItemMotoRearDoor = new CanItemTextBtnList((Context) this, R.string.can_moto_rear_door);
        this.mItemMotoRearDoor.SetIdClickListener(this, 11);
        this.mItemAmpSet = new CanItemTextBtnList((Context) this, R.string.can_amp_set);
        this.mItemAmpSet.SetIdClickListener(this, 12);
        this.mItemCameraSet = new CanItemTextBtnList((Context) this, R.string.can_camera_status);
        this.mItemCameraSet.SetIdClickListener(this, 13);
        this.mItemYibiaoSet = new CanItemTextBtnList((Context) this, R.string.can_psa_2017_4008_gxhybsz);
        this.mItemYibiaoSet.SetIdClickListener(this, 14);
        this.mItemQJYXSystemSet = new CanItemTextBtnList((Context) this, R.string.can_honda_qjyxxtsz);
        this.mItemQJYXSystemSet.SetIdClickListener(this, 15);
        this.mItem360PanoramaSet = new CanItemTextBtnList((Context) this, R.string.can_360qjsz);
        this.mItem360PanoramaSet.SetIdClickListener(this, 16);
        this.mManager.AddView(this.mItemCompass.GetView());
        this.mManager.AddView(this.mItemConsumption.GetView());
        this.mManager.AddView(this.mItemDisatnce.GetView());
        this.mManager.AddView(this.mItemDistanceIll.GetView());
        this.mManager.AddView(this.mItemLock.GetView());
        this.mManager.AddView(this.mItemRemoteLock.GetView());
        this.mManager.AddView(this.mItemCarSet.GetView());
        this.mManager.AddView(this.mItemJspzXtsz.GetView());
        this.mManager.AddView(this.mItemSystemSet.GetView());
        this.mManager.AddView(this.mItemMotoRearDoor.GetView());
        this.mManager.AddView(this.mItemAmpSet.GetView());
        this.mManager.AddView(this.mItemCameraSet.GetView());
        this.mManager.AddView(this.mItemYibiaoSet.GetView());
        this.mManager.AddView(this.mItemQJYXSystemSet.GetView());
        this.mManager.AddView(this.mItem360PanoramaSet.GetView());
        if (CanJni.GetSubType() == 3 || CanJni.GetSubType() == 4 || CanJni.GetSubType() == 6 || CanJni.GetSubType() == 7 || CanJni.GetSubType() == 12 || CanJni.GetSubType() == 11 || CanJni.GetSubType() == 13) {
            this.mItemCompass.ShowGone(false);
            this.mItemAmpSet.ShowGone(false);
        } else if (CanJni.GetSubType() == 8 || CanJni.GetSubType() == 9) {
            this.mItemCompass.ShowGone(false);
        } else {
            if (CanJni.GetSubType() == 5) {
                this.mItemCompass.ShowGone(false);
                this.mItemConsumption.ShowGone(false);
            }
            this.mItemDisatnce.ShowGone(false);
            this.mItemDistanceIll.ShowGone(false);
            this.mItemLock.ShowGone(false);
            this.mItemRemoteLock.ShowGone(false);
            this.mItemCarSet.ShowGone(false);
            this.mItemJspzXtsz.ShowGone(false);
            this.mItemSystemSet.ShowGone(false);
            this.mItemMotoRearDoor.ShowGone(false);
            this.mItemAmpSet.ShowGone(false);
        }
        if (!(CanJni.GetSubType() == 10 || CanJni.GetSubType() == 2 || CanJni.GetSubType() == 4 || CanJni.GetSubType() == 9 || CanJni.GetSubType() == 12 || CanJni.GetSubType() == 11 || CanJni.GetSubType() == 7)) {
            this.mItemCameraSet.ShowGone(false);
        }
        if (!(CanJni.GetSubType() == 6 || CanJni.GetSubType() == 7 || CanJni.GetSubType() == 12 || CanJni.GetSubType() == 11)) {
            this.mItemYibiaoSet.ShowGone(false);
            this.mItemQJYXSystemSet.ShowGone(false);
        }
        if (CanJni.GetSubType() != 13) {
            this.mItem360PanoramaSet.ShowGone(false);
        }
        if (CanFunc.getInstance().IsCustom("SZKS")) {
            this.mItemCarType.GetView().setVisibility(8);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        Log.d(TAG, "subtype = " + CanJni.GetSubType());
        this.mItemCarType.GetPopItem().SetSel(CanJni.GetSubType());
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.HondaDAQuery(210, 0);
                enterSubWin(CanHondaCompassActivity.class);
                return;
            case 2:
                enterSubWin(CanHondaDAConsumpCurrentActivity.class);
                return;
            case 4:
                enterSubWin(CanHondDACarDistanceActivity.class);
                return;
            case 5:
                enterSubWin(CanHondaADACarDistanceIllActivity.class);
                return;
            case 6:
                enterSubWin(CanHondaDACarCsSetActivity.class);
                return;
            case 7:
                enterSubWin(CanHondDACarRemoteLockSetActivity.class);
                return;
            case 8:
                enterSubWin(CanHondaDACarSetActivity.class);
                return;
            case 9:
                enterSubWin(CanHondDACarDrivAssistSetActivity.class);
                return;
            case 10:
                enterSubWin(CanHondDACarSystemSetActivity.class);
                return;
            case 11:
                enterSubWin(CanHondDACarMotoRearDoorActivity.class);
                return;
            case 12:
                enterSubWin(CanHondDACarAMPSetActivity.class);
                return;
            case 13:
                enterSubWin(CanHondDACarCameraSetActivity.class);
                return;
            case 14:
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 1);
                return;
            case 15:
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 2);
                return;
            case 16:
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, 3);
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        if (id == 3) {
            Log.d(TAG, "Select = " + item);
            FtSet.SetCanSubT(item);
            Mcu.SendXKey(20);
        }
    }
}
