package com.ts.can;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.canview.CanItemIcoList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanScrollList;

public class CanToyotaCarInfoActivity extends CanToyotaBaseActivity implements View.OnClickListener, UserCallBack, CanItemProgressList.onPosChange {
    public static final int ITEM_CARTYPE_SET = 0;
    public static final int ITEM_CONSUMPTION = 2;
    public static final int ITEM_HYBRID = 4;
    public static final int ITEM_PERSON_SET = 1;
    public static final int ITEM_REAR_DISPLAY = 6;
    public static final int ITEM_TPMS = 3;
    public static final int ITEM_VOL = 5;
    public static final String TAG = "CanToyotaCarInfoActivity";
    protected static final int TOTAL_ITEM = 5;
    public static final int[] mOptIco = {R.drawable.can_icon_service, R.drawable.can_icon_setup, R.drawable.can_icon_consumption, R.drawable.can_icon_tpms, R.drawable.can_icon_hybrid};
    public static final int[] mOptId;
    public static final int[] mOptTitle = {R.string.can_car_type_select, R.string.can_car_gxh, R.string.can_consumption, R.string.can_pressure, R.string.can_hybrid_image};
    public static int mVol = 0;
    private CanDataInfo.ToyotaHybrid mHybridData = new CanDataInfo.ToyotaHybrid();
    protected CanItemProgressList mItemVol;
    protected CanItemIcoList[] mOpt;
    private CanDataInfo.ToyotaTpmsInfo mTpmsData = new CanDataInfo.ToyotaTpmsInfo();

    static {
        int[] iArr = new int[5];
        iArr[1] = 1;
        iArr[2] = 2;
        iArr[3] = 3;
        iArr[4] = 4;
        mOptId = iArr;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_list);
        CanScrollList sl = new CanScrollList(this);
        this.mOpt = new CanItemIcoList[5];
        for (int i = 0; i < 5; i++) {
            this.mOpt[i] = new CanItemIcoList(this, mOptIco[i], mOptTitle[i]);
            this.mOpt[i].GetView().setOnClickListener(this);
            this.mOpt[i].GetView().setTag(Integer.valueOf(mOptId[i]));
            sl.AddView(this.mOpt[i].GetView());
        }
        this.mOpt[3].ShowGone(false);
        this.mOpt[4].ShowGone(false);
        this.mItemVol = new CanItemProgressList((Context) this, "音量");
        this.mItemVol.SetMinMax(0, 63);
        this.mItemVol.SetIdCallBack(5, this);
        CanItemIcoList mRearDisplay = new CanItemIcoList(this, R.drawable.can_icon_car, R.string.can_rear_display);
        mRearDisplay.GetView().setOnClickListener(this);
        mRearDisplay.GetView().setTag(6);
        sl.AddView(mRearDisplay.GetView());
        mRearDisplay.ShowGone(false);
        if (CanJni.GetCanFsTp() == 128) {
            mRearDisplay.ShowGone(true);
        }
        if (CanJni.GetCanFsTp() == 252) {
            this.mOpt[0].ShowGone(false);
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                enterSubWin(CanToyotaCarTypeActivity.class);
                return;
            case 1:
                enterSubWin(CanToyotaSetMainActivity.class);
                return;
            case 2:
                enterSubWin(CanToyotaTripPerMinActivity.class);
                return;
            case 3:
                enterSubWin(CanToyotaTpmsActivity.class);
                return;
            case 4:
                enterSubWin(CanToyotaHybridActivity.class);
                return;
            case 6:
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, -1);
                return;
            default:
                return;
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

    private void ResetData(boolean check) {
        CanJni.ToyotaGetTpms(this.mTpmsData);
        if (this.mTpmsData.UpdateOnce != 0) {
            if (!check || this.mTpmsData.Update != 0) {
                this.mTpmsData.Update = 0;
                this.mOpt[3].ShowGone(this.mTpmsData.fgAvalid);
            }
        } else if (!check) {
            CanJni.ToyotaQuery(37, 0);
        }
        CanJni.ToyotaGetHybrid(this.mHybridData);
        if (this.mHybridData.UpdateOnce != 0) {
            if (!check || this.mHybridData.Update != 0) {
                this.mHybridData.Update = 0;
                this.mOpt[4].ShowGone(this.mHybridData.fgHybrid);
            }
        } else if (!check) {
            CanJni.ToyotaQuery(31, 0);
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onChanged(int id, int pos) {
        this.mItemVol.SetCurVal(pos);
        CanJni.ToyotaAmpSet(7, pos);
        mVol = pos;
    }
}
