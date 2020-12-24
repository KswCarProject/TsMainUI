package com.ts.can.baic.ec180;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemMsgBox;
import com.ts.canview.CanItemTextBtnList;
import com.ts.other.CustomImgView;
import com.ts.other.RelativeLayoutManager;

public class CanBaicEC180PwrInfoActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack, CanItemMsgBox.onMsgBoxClick {
    public static final int ITEM_BATVAL = 2;
    public static final int ITEM_CARWARN = 1;
    private static final int ITEM_MAX = 2;
    private static final int ITEM_MIN = 1;
    public static final String TAG = "CanBaicEC180PwrInfoActivity";
    protected int mCarWarn = 0;
    protected CanDataInfo.BaicEcInfo mEcInfo = new CanDataInfo.BaicEcInfo();
    protected CustomImgView[] mImgBat;
    protected CanItemTextBtnList mItemCarBat;
    protected CanItemMsgBox mItemCarWarn;
    private RelativeLayoutManager mManager;
    private boolean mbLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_relative);
        InitUI();
        this.mCarWarn = 0;
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.BaicEcGetCarInfo(this.mEcInfo);
        if (!i2b(this.mEcInfo.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mEcInfo.Update)) {
            this.mEcInfo.Update = 0;
            if (this.mEcInfo.CarWarn != this.mCarWarn) {
                this.mCarWarn = this.mEcInfo.CarWarn;
                if (this.mEcInfo.CarWarn == 1) {
                    new CanItemMsgBox(1, this, R.string.can_ec_warn1, this);
                } else if (this.mEcInfo.CarWarn == 2) {
                    new CanItemMsgBox(1, this, R.string.can_ec_warn2, this);
                }
            }
            for (int i = 0; i < 10; i++) {
                if (i < this.mEcInfo.BatVal) {
                    this.mImgBat[i].setSelected(true);
                } else {
                    this.mImgBat[i].setSelected(false);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.BaicEcQuery(64);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        QueryData();
        this.mCarWarn = 0;
        Log.d(TAG, "-----onResume-----");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        Log.d(TAG, "-----onPause-----");
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        this.mItemCarBat = new CanItemTextBtnList((Context) this, R.string.can_dfqc_battery_e);
        this.mItemCarBat.ShowArrow(false);
        this.mItemCarBat.SetIdClickListener(this, 2);
        this.mManager.AddView(this.mItemCarBat.GetView(), 0, 10, -2, 85);
        this.mImgBat = new CustomImgView[10];
        for (int i = 0; i < this.mImgBat.length; i++) {
            this.mImgBat[i] = new CustomImgView(this);
            this.mImgBat[i].setStateDrawable(R.drawable.can_dc_up, R.drawable.can_dc_dn);
            AddViewWrapContent((RelativeLayout) this.mItemCarBat.GetView(), this.mImgBat[i], (i * 18) + 800, 20);
        }
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 1; i <= 2; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        return i2b(0);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean IsHaveItem = IsHaveItem(item);
    }

    public void AddViewWrapContent(RelativeLayout layout, View view, int x, int y) {
        RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(-2, -2);
        rl.leftMargin = x;
        rl.topMargin = y;
        view.setLayoutParams(rl);
        layout.addView(view);
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onOK(int param) {
    }
}
