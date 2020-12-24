package com.ts.can.audi.xhd;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.backcar.AutoFitTextureView;
import com.ts.backcar.BackcarService;
import com.ts.bt.BtExe;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanFunc;
import com.ts.can.CanIF;
import com.ts.can.MyApplication;
import com.ts.main.common.MainSet;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.Mcu;

public class CanAudiWithCDExdActivity extends CanBaseActivity implements UserCallBack, View.OnTouchListener, View.OnClickListener {
    public static final String TAG = "CanAudiWithCDExdActivity";
    public static int mOldBtSta = 0;
    public static int mOldMode = 0;
    public static boolean mfgAutoEnt = false;
    public static boolean mfgFinish = false;
    private static boolean mfgSendFunc = false;
    public static boolean mfgShow = false;
    private AutoFitTextureView mCameraView;
    private RelativeLayoutManager mManager;
    private WindowManager wManager;
    private WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        setContentView(R.layout.activity_can_audi_withcd_base);
        this.mManager = new RelativeLayoutManager(this, R.id.can_withcd_base_layout);
        this.mManager.GetLayout().setOnTouchListener(this);
        this.mManager.GetLayout().setClickable(true);
    }

    public ParamButton addButton(int x, int y, int normal, int selected, int id) {
        ParamButton button = this.mManager.AddButton(x, y);
        button.setStateDrawable(normal, selected, selected);
        button.setOnTouchListener(this);
        button.setTag(Integer.valueOf(id));
        return button;
    }

    public ParamButton addButton(int x, int y, int w, int h, int normal, int selected, int id) {
        ParamButton button = this.mManager.AddButton(x, y, w, h);
        button.setStateDrawable(normal, selected, selected);
        button.setOnTouchListener(this);
        button.setTag(Integer.valueOf(id));
        return button;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        onCreate((Bundle) null);
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        if (!mfgAutoEnt) {
            Evc.GetInstance().evol_workmode_set(12);
            Log.d(TAG, "WORKMODE_EXD");
        }
        mfgShow = true;
        mfgFinish = false;
        Log.d(TAG, "onResume");
        MainSet.GetInstance().SetVideoChannel(2);
        this.mCameraView = (AutoFitTextureView) findViewById(R.id.DevtextureView);
        BackcarService.getInstance().StartCamera(this.mCameraView, false);
        CanJni.AudiZmytSrcSwitch(1);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        mfgShow = false;
        mfgAutoEnt = false;
        Log.d(TAG, "onPause");
        BackcarService.getInstance().StopCamera();
        CanJni.AudiZmytSrcSwitch(2);
    }

    public static void AudiWithCDDModeChange(int sta) {
        if (sta == 0) {
            Log.d(TAG, "AudiWithCDDModeChange Exd Exit");
            int temp = 0;
            if (CanAudiWithCDCarInitView.IsAuxConfig() > 0) {
                temp = 0 | 128;
            }
            CanJni.AudiZmytAudioSet(1, temp | ((CanAudiWithCDCarInitView.IsAuxLin2() + 1) << 4) | ((CanAudiWithCDCarInitView.IsAuxLin1() + 1) & 15));
            return;
        }
        Log.d(TAG, "AudiWithCDDModeChange Exd Enter");
    }

    public static void showAudiWithCDWin() {
        if (!mfgShow) {
            mfgAutoEnt = true;
            CanFunc.showCanActivity(CanAudiWithCDExdActivity.class);
        }
    }

    public static void entAudiWithCDMode() {
        if (!mfgShow) {
            CanFunc.showCanActivity(CanAudiWithCDExdActivity.class);
        }
    }

    public static void finishAudiWithCDWin() {
        if (mfgShow) {
            mfgFinish = true;
        }
    }

    public static boolean IsAudiWithCDWin() {
        return mfgShow;
    }

    private void ResetData(boolean check) {
    }

    public void UserAll() {
        ResetData(true);
        if (mfgShow && mfgFinish) {
            mfgFinish = false;
            mfgShow = false;
            finish();
        }
    }

    public boolean onTouch(View arg0, MotionEvent event) {
        if (event.getAction() == 0 || event.getAction() == 2) {
            Log.d(TAG, "onTouch ACTION_DOWN event.getX() = " + 0);
            Log.d(TAG, "onTouch ACTION_DOWN event.getY() = " + 0);
            return false;
        } else if (event.getAction() != 1) {
            return false;
        } else {
            Log.d(TAG, "onTouch ACTION_UP event.getX() = " + 0);
            Log.d(TAG, "onTouch ACTION_UP event.getY() = " + 0);
            Mcu.SetCkey(8);
            return false;
        }
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public static void Init() {
        if ((FtSet.Getlgb1() & 1) != 0) {
            Mcu.SendXKey(33);
        } else {
            Mcu.SendXKey(34);
        }
        if (FtSet.Getlgb2() == 1) {
            Mcu.SendXKey(41);
        } else {
            Mcu.SendXKey(40);
        }
        Mcu.SendXKey(CanAudiWithCDCarFuncView.RvsDelay() + 42);
        Mcu.SendXKey(((FtSet.Getlgb3() & 61440) >> 12) + 50);
        Intent intent = new Intent("com.ts.can.BROADCAST_CAN_INFO");
        intent.putExtra("CanType", CanJni.GetCanType());
        intent.putExtra("CanSubType", CanAudiWithCDCarInitView.DoorUI());
        MyApplication.mContext.sendBroadcast(intent);
        byte[] fileMsg = new byte[8];
        fileMsg[0] = (byte) FtSet.GetCanTp();
        fileMsg[1] = (byte) CanAudiWithCDCarInitView.DoorUI();
        CanFunc.sendFileCarInfo(fileMsg, CanFunc.Can_Factory_Set_fileName);
        if (FtSet.IsIconExist(1) == 0) {
            Iop.RstPort(0);
        }
    }

    public static void DealDevEvent() {
        BtExe bt = BtExe.getBtInstance();
        if (mOldBtSta != bt.getSta() && FtSet.IsIconExist(1) == 0) {
            mOldBtSta = bt.getSta();
            if ((mOldBtSta == 3 || mOldBtSta == 2 || mOldBtSta == 4) && CanIF.IsExdMode()) {
                Log.d(TAG, "Bt call on ");
                Iop.RstPort(1);
            } else {
                Log.d(TAG, "Bt call of ");
                Iop.RstPort(0);
            }
        }
        if (CanIF.mGpsVoiceDelay > 0) {
            CanIF.mGpsVoiceDelay--;
            if (CanIF.mGpsVoiceDelay == 0) {
                Log.d(TAG, "GpsVoice on ");
                Iop.RstPort(1);
            }
        }
    }
}
