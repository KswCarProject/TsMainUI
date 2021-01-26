package com.ts.can.psa.rzc.scr;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.other.ParamButton;
import com.yyw.ts70xhw.KeyDef;

public class CanPSAScrRzcCarDeviceView extends CanRelativeCarInfoView {
    public static final int ITEM_FOLDER_DN = 6;
    public static final int ITEM_FOLDER_UP = 7;
    public static final int ITEM_NEXT = 2;
    public static final int ITEM_PAUSE = 0;
    public static final int ITEM_PLAY = 1;
    public static final int ITEM_PREV = 3;
    public static final int ITEM_SEEK_DN = 4;
    public static final int ITEM_SEEK_UP = 5;
    public static final int ITEM_USB_STATE = 8;
    protected ParamButton mBtnFolderDn;
    protected ParamButton mBtnFolderUp;
    protected ParamButton mBtnNext;
    protected ParamButton mBtnPause;
    protected ParamButton mBtnPlay;
    protected ParamButton mBtnPrev;
    protected ParamButton mBtnSeekDn;
    protected ParamButton mBtnSeekUp;
    protected ParamButton mBtnUsbState;

    public CanPSAScrRzcCarDeviceView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View v) {
        CanJni.PsaSrcRzcUsbCtrl(((Integer) v.getTag()).intValue());
    }

    public void doOnResume() {
        super.doOnResume();
        Evc.GetInstance().evol_workmode_set(12);
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mBtnPrev = AddBtn(3, 279, 427, R.drawable.original_car_prev_up, R.drawable.original_car_prev_dn);
        this.mBtnPlay = AddBtn(1, CanCameraUI.BTN_TRUMPCHI_GS4_MODE4, 427, R.drawable.original_car_play_up, R.drawable.original_car_play_dn);
        this.mBtnPause = AddBtn(0, CanCameraUI.BTN_GEELY_YJX6_FXP, 427, R.drawable.original_car_pause_up, R.drawable.original_car_pause_dn);
        this.mBtnNext = AddBtn(2, 651, 427, R.drawable.original_car_next_up, R.drawable.original_car_next_dn);
        this.mBtnSeekUp = AddBtn(5, Can.CAN_DFFG_S560, 427, R.drawable.original_car_seekup_up, R.drawable.original_car_seekup_dn);
        this.mBtnSeekDn = AddBtn(4, KeyDef.SKEY_VOLUP_2, 427, R.drawable.original_car_seekdn_up, R.drawable.original_car_seekdn_dn);
        this.mBtnFolderUp = AddBtn(7, 31, 427, R.drawable.original_car_foldup_up, R.drawable.original_car_foldup_dn);
        this.mBtnFolderDn = AddBtn(6, 899, 427, R.drawable.original_car_folddn_up, R.drawable.original_car_folddn_dn);
        AddBtn(8, 460, 150, R.drawable.original_car_usb, R.drawable.original_car_usb);
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }

    private ParamButton AddBtn(int id, int x, int y, int up, int dn) {
        ParamButton btn = addButton(x, y);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setDrawable(up, dn);
        return btn;
    }
}
