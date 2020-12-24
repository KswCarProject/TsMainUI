package com.ts.can.renault.kadjar;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.other.CustomTextView;
import com.yyw.ts70xhw.KeyDef;

public class CanKadjarExdView extends CanRelativeCarInfoView {
    public static final String TAG = "CanKadjarExdView";
    protected CustomTextView mTxtCenter;

    public CanKadjarExdView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mTxtCenter = getRelativeManager().AddCusText(KeyDef.RKEY_MEDIA_OSD, 240, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 90);
        this.mTxtCenter.setGravity(17);
        this.mTxtCenter.setPadding(0, 0, 0, 0);
        this.mTxtCenter.SetPixelSize(75);
        this.mTxtCenter.setText(R.string.can_car_device);
    }

    public void doOnResume() {
        super.doOnResume();
        Evc.GetInstance().evol_workmode_set(12);
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }
}
