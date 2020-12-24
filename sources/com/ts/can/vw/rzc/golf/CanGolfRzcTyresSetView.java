package com.ts.can.vw.rzc.golf;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemIcoList;
import com.ts.canview.CanItemMsgBox;
import com.ts.canview.CanItemProgressList;

public class CanGolfRzcTyresSetView extends CanScrollCarInfoView implements CanItemMsgBox.onMsgBoxClick {
    public static final int ITEM_DJLT = 2;
    public static final int ITEM_LTYLJKXS = 0;
    public static final int ITEM_SET_BTN = 1;
    public static final int ITEM_SPEED_WARN = 3;
    public static final int ITEM_WARN_AT = 4;
    public static final String TAG = "CanGolfRzcTyresSetView";
    private CanDataInfo.GolfAdtTyres mAdtTyres;
    private CanDataInfo.GolfTyres mTyresData;

    public CanGolfRzcTyresSetView(Activity activity) {
        super(activity, 5);
    }

    public void onItem(int id, int item) {
        if (id == 0) {
            CanJni.GolfSendCmd(16, item);
        }
    }

    public void onChanged(int id, int pos) {
        if (id == 4) {
            CanJni.GolfSendCmd(33, pos);
        }
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        switch (id) {
            case 1:
                new CanItemMsgBox(id, getActivity(), R.string.can_tpms_box, this);
                return;
            case 3:
                CanJni.GolfSendCmd(32, 1 - (this.mTyresData.fgSpeedWarning & 1));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        int[] iArr = new int[5];
        iArr[0] = R.string.can_tyres_tpms;
        iArr[2] = R.string.can_winter_tyres;
        iArr[3] = R.string.can_speed_warn;
        iArr[4] = R.string.can_warn_at;
        this.mItemTitleIds = iArr;
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.CHECK, CanScrollCarInfoView.Item.PROGRESS};
        int[] iArr2 = new int[5];
        iArr2[1] = R.drawable.can_golf_icon10;
        this.mItemIcons = iArr2;
        this.mProgressAttrs[4] = new int[]{20, Can.CAN_JAC_REFINE_OD, 5, 1};
        this.mTyresData = new CanDataInfo.GolfTyres();
        this.mAdtTyres = new CanDataInfo.GolfAdtTyres();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
        ((CanItemIcoList) this.mItemObjects[1]).GetBtn().setText("SET");
        this.mItemVisibles[4] = 0;
    }

    public void ResetData(boolean check) {
        CanJni.GolfGetTyres(this.mTyresData);
        if (!i2b(this.mTyresData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mTyresData.Update)) {
            this.mTyresData.Update = 0;
            updateItem(3, this.mTyresData.fgSpeedWarning);
            if (i2b(this.mAdtTyres.WarnningAt)) {
                showItem(4, this.mTyresData.fgSpeedWarning);
            }
            if (this.mTyresData.SpeedDW == 0) {
                ((CanItemProgressList) this.mItemObjects[4]).SetMinMax(30, 240);
                ((CanItemProgressList) this.mItemObjects[4]).SetStep(10);
                updateItem(4, this.mTyresData.Data, this.mTyresData.Data + "km/h");
                return;
            }
            ((CanItemProgressList) this.mItemObjects[4]).SetMinMax(20, Can.CAN_JAC_REFINE_OD);
            ((CanItemProgressList) this.mItemObjects[4]).SetStep(5);
            updateItem(4, this.mTyresData.Data, this.mTyresData.Data + "mph");
        }
    }

    public void QueryData() {
        CanJni.GolfQuery(65, 32);
        Sleep(5);
        CanJni.GolfQuery(64, 32);
    }

    public void doOnResume() {
    }

    public void onOK(int param) {
        if (1 == param) {
            CanJni.GolfSendCmd(34, 1);
        }
    }
}
