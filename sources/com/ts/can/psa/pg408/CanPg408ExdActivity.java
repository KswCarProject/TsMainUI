package com.ts.can.psa.pg408;

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
import com.ts.can.CanCameraUI;
import com.ts.can.CanFunc;
import com.ts.canview.CanItemProgressList;
import com.ts.other.CustomImgView;
import com.ts.other.CustomTextView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.KeyDef;

public class CanPg408ExdActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener, CanItemProgressList.onPosChange {
    public static final int ITEM_FILE_DN = 5;
    public static final int ITEM_FILE_UP = 4;
    public static final int ITEM_NEXT = 3;
    public static final int ITEM_PLAY = 1;
    public static final int ITEM_PREV = 0;
    public static final int ITEM_STOP = 2;
    public static final String TAG = "CanPg408ExdActivity";
    protected static CanDataInfo.PgBTInfo mBtInfo = new CanDataInfo.PgBTInfo();
    protected static CanDataInfo.PgBTInfo mOldBtInfo = new CanDataInfo.PgBTInfo();
    protected static boolean mfgExit = false;
    protected static boolean mfgThis = false;
    protected ParamButton mBtnFileDn;
    protected ParamButton mBtnFileUp;
    protected ParamButton mBtnNext;
    protected ParamButton mBtnPlay;
    protected ParamButton mBtnPrev;
    protected ParamButton mBtnStop;
    protected CustomImgView mImgDevMode;
    protected RelativeLayoutManager mManager;
    protected String[] mStaArr = {"USB没有连接", "正在读取USB", "播放", "暂停", "快进", "快退"};
    protected CustomTextView mTxtTime;
    protected CustomTextView mTxtTrack;
    protected CustomTextView mTxtUsbSta;
    protected CanDataInfo.PgUSBInfo mUsbInfo = new CanDataInfo.PgUSBInfo();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        this.mBtnPrev = AddBtn(0, 257, 427, R.drawable.original_car_seekup_up, R.drawable.original_car_seekup_dn);
        this.mBtnPlay = AddBtn(1, 396, 427, R.drawable.original_car_play_up, R.drawable.original_car_play_dn);
        this.mBtnStop = AddBtn(2, 536, 427, R.drawable.original_car_stop_up, R.drawable.original_car_stop_dn);
        this.mBtnNext = AddBtn(3, 675, 427, R.drawable.original_car_seekdn_up, R.drawable.original_car_seekdn_dn);
        this.mBtnFileUp = AddBtn(4, 118, 427, R.drawable.original_car_foldup_up, R.drawable.original_car_foldup_dn);
        this.mBtnFileDn = AddBtn(5, KeyDef.SKEY_CALLUP_1, 427, R.drawable.original_car_folddn_up, R.drawable.original_car_folddn_dn);
        this.mImgDevMode = this.mManager.AddImage(466, 79, 100, Can.CAN_TOYOTA_SP_XP);
        this.mImgDevMode.setImageResource(R.drawable.original_car_usb);
        this.mTxtTime = AddTxtLt(CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 23, 200, 38);
        this.mTxtTrack = AddTxtLt(800, 23, 200, 38);
        this.mTxtUsbSta = AddTxtLt(100, 23, 200, 38);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        Log.d(TAG, "-----onResume-----");
        if (mBtInfo.ubSta == 0) {
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
        this.mBtnFileUp.Show(show);
        this.mBtnFileDn.Show(show);
        this.mTxtTrack.ShowHide(show);
        this.mTxtUsbSta.ShowHide(show);
    }

    private void ResetData(boolean check) {
        boolean z;
        if (mfgExit) {
            finish();
            mfgExit = false;
            return;
        }
        if (i2b(mBtInfo.UpdateOnce) && (!check || i2b(mBtInfo.Update))) {
            mBtInfo.Update = 0;
            if (mBtInfo.ubSta != 0) {
                this.mImgDevMode.setImageResource(R.drawable.original_car_call);
            } else {
                this.mImgDevMode.setImageResource(R.drawable.original_car_usb);
            }
            if (mBtInfo.ubSta == 0) {
                z = true;
            } else {
                z = false;
            }
            ShowUsb(z);
        }
        CanJni.Pg408GetUsbInfo(this.mUsbInfo);
        if (!i2b(this.mUsbInfo.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mUsbInfo.Update)) {
            this.mUsbInfo.Update = 0;
            this.mTxtTime.setText(String.format("%02d:%02d", new Object[]{Integer.valueOf(this.mUsbInfo.ubMin), Integer.valueOf(this.mUsbInfo.ubSec)}));
            this.mTxtTrack.setText(String.format("%02d:%02d", new Object[]{Integer.valueOf(this.mUsbInfo.uwCur), Integer.valueOf(this.mUsbInfo.uwTotal)}));
            this.mTxtUsbSta.setText(String.format("%s", new Object[]{this.mStaArr[this.mUsbInfo.ubSta]}));
        }
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 0:
                CanJni.Pg408SetUsbCtrl(3);
                return;
            case 1:
                CanJni.Pg408SetUsbCtrl(1);
                return;
            case 2:
                CanJni.Pg408SetUsbCtrl(0);
                return;
            case 3:
                CanJni.Pg408SetUsbCtrl(2);
                return;
            case 4:
                CanJni.Pg408SetUsbCtrl(7);
                return;
            case 5:
                CanJni.Pg408SetUsbCtrl(6);
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
        CanJni.Pg408GetBtInfo(mBtInfo);
        if (mOldBtInfo.ubSta != mBtInfo.ubSta) {
            if (mBtInfo.ubSta != 0) {
                fgEnter = true;
                Evc.GetInstance().evol_aux_hold();
            } else {
                fgExit = true;
                Evc.GetInstance().evol_aux_release();
            }
        }
        Log.d(TAG, "DealDevEvent enter = " + fgEnter + ", exit = " + fgExit);
        if (fgEnter) {
            if (!mfgThis && CanFunc.IsCamMode() == 0) {
                CanFunc.showCanActivity(CanPg408ExdActivity.class);
            }
        } else if (fgExit && mfgThis && 12 != Iop.GetWorkMode()) {
            mfgExit = true;
        }
        mOldBtInfo.ubSta = mBtInfo.ubSta;
    }
}
