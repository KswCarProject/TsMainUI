package com.ts.can.fiat;

import android.os.Bundle;
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
import com.ts.can.CanFunc;
import com.ts.can.CanIF;
import com.ts.canview.CanItemProgressList;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.Iop;

public class CanFiatAllExdActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener, CanItemProgressList.onPosChange {
    public static final int ITEM_NEXT = 3;
    public static final int ITEM_PLAY = 1;
    public static final int ITEM_PREV = 0;
    public static final int ITEM_STOP = 2;
    public static final String TAG = "CanFiatAllExdActivity";
    protected static CanDataInfo.FiatCarDevInfo mDevData = new CanDataInfo.FiatCarDevInfo();
    protected static CanDataInfo.FiatCarDevInfo mOldDevData = new CanDataInfo.FiatCarDevInfo();
    protected static boolean mfgExit = false;
    protected static boolean mfgThis = false;
    protected ParamButton mBtnNext;
    protected ParamButton mBtnPlay;
    protected ParamButton mBtnPrev;
    protected ParamButton mBtnStop;
    protected CustomImgView mImgDevMode;
    protected RelativeLayoutManager mManager;
    protected CanDataInfo.FiatUsbTime mTimeData = new CanDataInfo.FiatUsbTime();
    protected CustomTextView mTxtTime;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        this.mBtnPrev = AddBtn(0, 257, 427, R.drawable.original_car_seekup_up, R.drawable.original_car_seekup_dn);
        this.mBtnPlay = AddBtn(1, 396, 427, R.drawable.original_car_play_up, R.drawable.original_car_play_dn);
        this.mBtnStop = AddBtn(2, 536, 427, R.drawable.original_car_stop_up, R.drawable.original_car_stop_dn);
        this.mBtnNext = AddBtn(3, 675, 427, R.drawable.original_car_seekdn_up, R.drawable.original_car_seekdn_dn);
        this.mImgDevMode = this.mManager.AddImage(466, 79, 100, Can.CAN_TOYOTA_SP_XP);
        this.mImgDevMode.setImageResource(R.drawable.original_car_usb);
        this.mTxtTime = AddTxtLt(800, 23, 200, 38);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        Log.d(TAG, "-----onResume-----");
        if (mDevData.Voice == 0 && mDevData.Phone == 0) {
            Evc.GetInstance().evol_workmode_set(12);
        }
        mfgThis = true;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        mfgThis = false;
        Log.d(TAG, "-----onPause-----");
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int up, int dn) {
        ParamButton btn = this.mManager.AddButton(x, y);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setDrawable(up, dn);
        return btn;
    }

    /* access modifiers changed from: protected */
    public CustomTextView AddTxtLt(int x, int y, int w, int h) {
        CustomTextView temp = this.mManager.AddCusText(x, y, w, h);
        temp.SetPixelSize(30);
        temp.setGravity(19);
        return temp;
    }

    private void ShowUsb(boolean show) {
        this.mBtnPrev.Show(show);
        this.mBtnPlay.Show(show);
        this.mBtnStop.Show(show);
        this.mBtnNext.Show(show);
        this.mTxtTime.ShowHide(show);
    }

    private void ResetData(boolean check) {
        boolean z;
        if (mfgExit) {
            finish();
            mfgExit = false;
            return;
        }
        if (i2b(mDevData.UpdateOnce) && (!check || i2b(mDevData.Update))) {
            mDevData.Update = 0;
            if (mDevData.Voice != 0) {
                this.mImgDevMode.setImageResource(R.drawable.original_car_mike);
            } else if (mDevData.Phone != 0) {
                this.mImgDevMode.setImageResource(R.drawable.original_car_call);
            } else {
                this.mImgDevMode.setImageResource(R.drawable.original_car_usb);
            }
            if (mDevData.Voice == 0 && mDevData.Phone == 0) {
                z = false;
            } else {
                z = true;
            }
            ShowUsb(z);
        }
        CanJni.FiatAllGetUSBTime(this.mTimeData);
        if (!i2b(this.mTimeData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mTimeData.Update)) {
            this.mTimeData.Update = 0;
            this.mTxtTime.setText(String.format("%02d:%02d", new Object[]{Integer.valueOf(this.mTimeData.Min), Integer.valueOf(this.mTimeData.Sec)}));
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanIF.FiatAllPrev();
                return;
            case 1:
                CanIF.FiatAllPlay();
                return;
            case 2:
                CanIF.FiatAllStop();
                return;
            case 3:
                CanIF.FiatAllNext();
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onChanged(int id, int pos) {
    }

    public static void DealDevEvent() {
        boolean fgExit = false;
        boolean fgEnter = false;
        CanJni.FiatAllGetDevInfo(mDevData);
        if (mOldDevData.Voice != mDevData.Voice) {
            if (mDevData.Voice != 0) {
                fgEnter = true;
                Evc.GetInstance().evol_aux_hold();
            } else {
                fgExit = true;
                Evc.GetInstance().evol_aux_release();
            }
        }
        if (mOldDevData.Phone == mDevData.Phone) {
            int i = mOldDevData.UsbSta;
            int i2 = mDevData.UsbSta;
        } else if (mDevData.Phone != 0) {
            fgEnter = true;
            Evc.GetInstance().evol_aux_hold();
        } else {
            fgExit = true;
            Evc.GetInstance().evol_aux_release();
        }
        Log.d(TAG, "DealDevEvent enter = " + fgEnter + ", exit = " + fgExit);
        if (fgEnter) {
            if (!mfgThis && CanFunc.IsCamMode() == 0) {
                CanFunc.showCanActivity(CanFiatAllExdActivity.class);
            }
        } else if (fgExit && mfgThis && 12 != Iop.GetWorkMode()) {
            mfgExit = true;
        }
        mOldDevData.Voice = mDevData.Voice;
        mOldDevData.Phone = mDevData.Phone;
        mOldDevData.UsbSta = mDevData.UsbSta;
    }
}
