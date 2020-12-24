package com.ts.can;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import com.lgb.canmodule.Can;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;
import com.ts.main.common.MainSet;
import com.ts.other.ParamButton;

public class CanToyotaSetOtherActivity extends CanToyotaBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick {
    private static final int ID_TRACK_0 = 2000;
    private static final int ID_TRACK_1 = 2001;
    private static final int ID_TRACK_2 = 2002;
    private static final int ITEM_EDOOR = 2;
    private static final int ITEM_MAX = 2;
    private static final int ITEM_MIN = 1;
    private static final int ITEM_TRACK = 1;
    public static final String TAG = "CanGolfSetMFDActivity";
    public static final String[] mStrHbxArray = {MainSet.SP_XPH5, MainSet.SP_RLF_KORON, MainSet.SP_XH_DMAX, MainSet.SP_KS_QOROS, MainSet.SP_LM_WR};
    private ParamButton[] mBtnTrackType = new ParamButton[3];
    private CanItemPopupList mItemEDoor;
    private CanItemTextBtnList mItemTrack;
    private RelativeLayout mLayout;
    private CanScrollList mManager;
    private boolean mbLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        GetSetData();
        if (!this.mbLayout) {
            GetAdaptData();
            if (i2b(this.mAdtData.UpdateOnce)) {
                LayoutUI();
                check = false;
                this.mbLayout = true;
            } else {
                return;
            }
        }
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            for (ParamButton SetSel : this.mBtnTrackType) {
                SetSel.SetSel(0);
            }
            if (this.mSetData.TrackMode < 3) {
                this.mBtnTrackType[this.mSetData.TrackMode].SetSel(1);
            }
            this.mItemEDoor.SetSel(this.mSetData.ERearDoorGear);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
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
    public void setViewPos(View view, int x, int y) {
        RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(-2, -2);
        rl.leftMargin = x;
        rl.topMargin = y;
        view.setLayoutParams(rl);
        this.mLayout.addView(view);
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemTrack = new CanItemTextBtnList((Context) this, R.string.can_rc_track_set);
        this.mItemTrack.ShowArrow(false);
        this.mLayout = (RelativeLayout) this.mItemTrack.GetView();
        this.mItemTrack.GetView().setClickable(false);
        for (int i = 0; i < this.mBtnTrackType.length; i++) {
            this.mBtnTrackType[i] = new ParamButton(this);
            this.mBtnTrackType[i].setTag(Integer.valueOf(i + 2000));
            this.mBtnTrackType[i].setOnClickListener(this);
        }
        this.mBtnTrackType[0].setDrawable(R.drawable.can_camera_tarack_line_up, R.drawable.can_camera_tarack_line_dn);
        this.mBtnTrackType[1].setDrawable(R.drawable.can_camera_tarack_cha_up, R.drawable.can_camera_tarack_cha_dn);
        this.mBtnTrackType[2].setDrawable(R.drawable.can_camera_tarack_wan_up, R.drawable.can_camera_tarack_wan_dn);
        for (int i2 = 0; i2 < this.mBtnTrackType.length; i2++) {
            setViewPos(this.mBtnTrackType[i2], (i2 * Can.CAN_CHANA_CS75_WC) + 500, 8);
        }
        this.mItemEDoor = new CanItemPopupList((Context) this, R.string.can_hbx_kd, mStrHbxArray, (CanItemPopupList.onPopItemClick) this);
        this.mItemEDoor.SetId(2);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        this.mManager.RemoveAllViews();
        for (int i = 1; i <= 2; i++) {
            AddItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                ret = this.mAdtData.TrackMode;
                break;
            case 2:
                ret = this.mAdtData.EDoor;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void AddItem(int item) {
        if (IsHaveItem(item)) {
            switch (item) {
                case 1:
                    this.mManager.AddView(this.mItemTrack.GetView());
                    return;
                case 2:
                    this.mManager.AddView(this.mItemEDoor.GetView());
                    return;
                default:
                    return;
            }
        }
    }

    public void onClick(View v) {
        int item = ((Integer) v.getTag()).intValue();
        switch (item) {
            case 2000:
            case ID_TRACK_1 /*2001*/:
            case ID_TRACK_2 /*2002*/:
                CarSet(34, item - 2000);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onItem(int Id, int item) {
        if (Id == 2) {
            CarSet(35, item);
        }
    }
}
