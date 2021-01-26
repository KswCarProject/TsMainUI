package com.ts.can.gac.trumpchi;

import android.os.Bundle;
import android.support.v4.internal.view.SupportMenu;
import android.util.Log;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanCameraUI;
import com.ts.can.CanFunc;
import com.ts.canview.CanItemProgressList;
import com.ts.other.CustomTextView;
import com.ts.other.RelativeLayoutManager;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.KeyDef;

public class CanGqcqLinkSosActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener, CanItemProgressList.onPosChange {
    public static final int ITEM_VOL = 0;
    public static final String TAG = "CanGqcqLinkSosActivity";
    public static CanDataInfo.GCLinkSos mLinkSos = new CanDataInfo.GCLinkSos();
    public static boolean mbCurWin = false;
    protected RelativeLayoutManager mManager;
    protected CustomTextView mTxtCenter;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        this.mTxtCenter = this.mManager.AddCusText(KeyDef.RKEY_MEDIA_OSD, Can.CAN_VOLKS_XP, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 80);
        this.mTxtCenter.setGravity(17);
        this.mTxtCenter.setPadding(0, 0, 0, 0);
        this.mTxtCenter.SetPixelSize(75);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        Log.d("CanGqcqLinkSosActivity", "-----onResume-----");
        mbCurWin = true;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        mbCurWin = false;
    }

    private void ResetData(boolean check) {
        if (mLinkSos.UpdateOnce != 0 && mLinkSos.Update != 0) {
            mLinkSos.Update = 0;
            switch (mLinkSos.Sta) {
                case 1:
                    this.mTxtCenter.setText("Link");
                    this.mTxtCenter.setTextColor(-16711936);
                    break;
                case 2:
                    this.mTxtCenter.setText("SoS");
                    this.mTxtCenter.setTextColor(SupportMenu.CATEGORY_MASK);
                    break;
                default:
                    this.mTxtCenter.setText(TXZResourceManager.STYLE_DEFAULT);
                    break;
            }
            if (mLinkSos.Sta <= 0) {
                finish();
            }
        }
    }

    public static void DealStatus(boolean isCamMode) {
        CanJni.GqcqGetLinkSos(mLinkSos);
        if (mLinkSos.Sta > 0) {
            if (!isCamMode) {
                CanFunc.showCanActivity(CanGqcqLinkSosActivity.class);
            }
            Evc.GetInstance().evol_aux_hold();
            return;
        }
        Evc.GetInstance().evol_aux_release();
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onChanged(int id, int pos) {
    }
}
