package com.ts.can.renault.kadjar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import com.yyw.ts70xhw.KeyDef;

public class CanKadjarSosActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener, CanItemProgressList.onPosChange {
    public static final int ITEM_VOL = 0;
    public static final String TAG = "CanGqcqLinkSosActivity";
    protected static int mOldSos = 0;
    public static CanDataInfo.KadjarSos mSos = new CanDataInfo.KadjarSos();
    public static boolean mbCurWin = false;
    protected RelativeLayoutManager mManager;
    protected CustomTextView mTxtCenter;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        this.mTxtCenter = this.mManager.AddCusText(KeyDef.RKEY_MEDIA_OSD, 240, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 90);
        this.mTxtCenter.setGravity(17);
        this.mTxtCenter.setPadding(0, 0, 0, 0);
        this.mTxtCenter.SetPixelSize(75);
        this.mTxtCenter.setText("SoS");
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
        if (mSos.UpdateOnce != 0 && mSos.Update != 0) {
            mSos.Update = 0;
            if (mSos.Sos <= 0) {
                finish();
            }
        }
    }

    public static void DealStatus(boolean isCamMode) {
        CanJni.KadjarGetSos(mSos);
        if (mOldSos != mSos.Sos) {
            mOldSos = mSos.Sos;
            if (mSos.Sos > 0) {
                if (!isCamMode) {
                    CanFunc.showCanActivity(CanKadjarSosActivity.class);
                }
                Evc.GetInstance().evol_aux_hold();
                return;
            }
            Evc.GetInstance().evol_aux_release();
        }
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
