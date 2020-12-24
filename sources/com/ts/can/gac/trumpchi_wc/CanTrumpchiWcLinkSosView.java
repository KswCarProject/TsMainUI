package com.ts.can.gac.trumpchi_wc;

import android.app.Activity;
import android.support.v4.internal.view.SupportMenu;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.can.CanCameraUI;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanFunc;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.other.CustomTextView;
import com.yyw.ts70xhw.KeyDef;

public class CanTrumpchiWcLinkSosView extends CanRelativeCarInfoView {
    private static CanDataInfo.GCWcLinkSos mLinkSos = new CanDataInfo.GCWcLinkSos();
    private CustomTextView mTxtCenter;

    public CanTrumpchiWcLinkSosView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View arg0) {
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mTxtCenter = getRelativeManager().AddCusText(KeyDef.RKEY_MEDIA_OSD, 240, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 80);
        this.mTxtCenter.setGravity(17);
        this.mTxtCenter.setPadding(0, 0, 0, 0);
        this.mTxtCenter.SetPixelSize(75);
    }

    public void ResetData(boolean check) {
        if (mLinkSos.UpdateOnce == 0) {
            return;
        }
        if (mLinkSos.Update != 0 || !check) {
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
                    this.mTxtCenter.setText("");
                    break;
            }
            if (mLinkSos.Sta <= 0) {
                getActivity().finish();
            }
        }
    }

    public static void DealStatus(boolean isCamMode) {
        CanJni.TrumpchiWcGetLinkSos(mLinkSos);
        if (mLinkSos.Sta > 0) {
            if (!isCamMode) {
                CanFunc.showCanActivity(CanCarInfoSub1Activity.class, -1);
            }
            Evc.GetInstance().evol_aux_hold();
            return;
        }
        Evc.GetInstance().evol_aux_release();
    }

    public void QueryData() {
        ResetData(false);
    }
}
