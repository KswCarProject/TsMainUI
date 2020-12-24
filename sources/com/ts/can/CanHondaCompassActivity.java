package com.ts.can;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemTitleValList;
import com.ts.canview.CanScrollList;

public class CanHondaCompassActivity extends CanBaseActivity implements CanItemProgressList.onPosChange, View.OnClickListener, UserCallBack {
    public static final int ID_STATUS = 1;
    public static final int ID_ZONE = 3;
    public static final String TAG = "CanHondaCompassActivity";
    private CanDataInfo.HondaCompass mCompassData = new CanDataInfo.HondaCompass();
    private CanItemTitleValList mItemAngle;
    private CanItemTitleValList mItemStatus;
    private CanItemProgressList mItemZone;
    protected CanScrollList mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        this.mManager = new CanScrollList(this);
        this.mItemStatus = new CanItemTitleValList(this, R.string.can_status);
        this.mItemAngle = new CanItemTitleValList(this, R.string.can_angle);
        this.mItemZone = new CanItemProgressList((Context) this, R.string.can_zone);
        this.mItemZone.SetMinMax(1, 15);
        this.mItemZone.SetIdCallBack(3, this);
        this.mManager.AddView(this.mItemStatus.GetView());
        this.mManager.AddView(this.mItemAngle.GetView());
        this.mManager.AddView(this.mItemZone.GetView());
        this.mItemStatus.GetView().setOnClickListener(this);
        this.mItemStatus.GetView().setTag(1);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.HondaGetCompass(this.mCompassData);
        if (!i2b(this.mCompassData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCompassData.Update)) {
            this.mCompassData.Update = 0;
            this.mItemZone.SetCurVal(this.mCompassData.Zone);
            this.mItemAngle.SetVal(String.valueOf(this.mCompassData.Angle) + "°");
            if (this.mCompassData.Status == 0) {
                this.mItemStatus.SetVal(R.string.can_fjz);
            } else {
                this.mItemStatus.SetVal(R.string.can_zzjz);
            }
        }
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
    }

    public void onChanged(int id, int pos) {
        if (id == 3) {
            switch (CanJni.GetCanType()) {
                case 4:
                    CanJni.HondaOldCompassCtrl(2, pos);
                    CanJni.HondaOldCompassCtrl(1, 1);
                    return;
                case 5:
                    CanJni.HondaDACarSet(193, pos);
                    CanJni.HondaDACarSet(192, 1);
                    return;
                default:
                    return;
            }
        }
    }

    public void onClick(View v) {
        if (((Integer) v.getTag()).intValue() == 1 && this.mCompassData.Status == 0) {
            switch (CanJni.GetCanType()) {
                case 4:
                    CanJni.HondaOldCompassCtrl(1, 1);
                    return;
                case 5:
                    CanJni.HondaDACarSet(192, 1);
                    return;
                default:
                    return;
            }
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
