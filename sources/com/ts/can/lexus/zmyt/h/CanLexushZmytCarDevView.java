package com.ts.can.lexus.zmyt.h;

import android.app.Activity;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.MainUI.TsDisplay;
import com.ts.backcar.AutoFitTextureView;
import com.ts.backcar.BackcarService;
import com.ts.bt.BtExe;
import com.ts.can.CanCarDeviceActivity;
import com.ts.can.CanFunc;
import com.ts.can.CanIF;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.can.MyApplication;
import com.ts.main.common.MainSet;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.Mcu;

public class CanLexushZmytCarDevView extends CanRelativeCarInfoView {
    public static int mOldBtSta = 0;
    public static boolean mfgAutoEnt = false;
    public static boolean mfgFinish = false;
    public static boolean mfgShow = false;
    private AutoFitTextureView mCameraView;
    private RelativeLayoutManager mManager;

    public CanLexushZmytCarDevView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View v, MotionEvent event) {
        int x;
        int y;
        int action = event.getAction();
        if (CanJni.GetSubType() == 1) {
            if (this.mCameraView != null) {
                x = (int) ((event.getX() * 1279.0f) / ((float) this.mCameraView.getWidth()));
                y = (int) ((event.getY() * 479.0f) / ((float) this.mCameraView.getHeight()));
            } else {
                x = (int) ((event.getX() * 1279.0f) / 1200.0f);
                y = (int) ((event.getY() * 479.0f) / 720.0f);
            }
            if (event.getAction() == 0 || event.getAction() == 2) {
                CanJni.LexusHZmytTouchCmd(1, x, y);
            } else if (event.getAction() == 1) {
                CanJni.LexusHZmytTouchCmd(0, x, y);
            }
        } else if (action != 0 && action == 1) {
            Log.d("lq", "KeyDef.PKEY_MENU");
            Mcu.SetCkey(8);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        getActivity().setContentView(R.layout.activity_can_lexus_withcd_base);
    }

    public void onClick(View arg0) {
    }

    public void doOnPause() {
        super.doOnPause();
        mfgShow = false;
        mfgAutoEnt = false;
        BackcarService.getInstance().StopCamera();
        CanJni.LexusHZmytConfigSet(17, 1);
    }

    public void doOnResume() {
        super.doOnResume();
        CanJni.LexusHZmytConfigSet(17, 0);
        if (!mfgAutoEnt) {
            Evc.GetInstance().evol_workmode_set(12);
        }
        mfgShow = true;
        mfgFinish = false;
        TsDisplay.GetInstance().SetDispParat(-1);
        MainSet.GetInstance().SetVideoChannel(2);
        this.mCameraView = (AutoFitTextureView) getActivity().findViewById(R.id.DevtextureView);
        this.mCameraView.setOnTouchListener(this);
        BackcarService.getInstance().StartCamera(this.mCameraView, false);
        this.mManager = new RelativeLayoutManager(getActivity(), R.id.can_lexus_withcd_base_layout);
        this.mManager.GetLayout().setOnTouchListener(this);
        this.mManager.GetLayout().setClickable(true);
    }

    public void ResetData(boolean check) {
        if (mfgShow && mfgFinish) {
            mfgFinish = false;
            mfgShow = false;
            getActivity().finish();
        }
    }

    public void QueryData() {
    }

    private long getTickCount() {
        return SystemClock.uptimeMillis();
    }

    public static void Init() {
        if (FtSet.Getlgb1() != 0) {
            Mcu.SendXKey(33);
        } else {
            Mcu.SendXKey(34);
        }
        if (FtSet.Getlgb2() == 1) {
            Mcu.SendXKey(41);
        } else {
            Mcu.SendXKey(40);
        }
        Mcu.SendXKey(CanLexushZmytCarInitView.HostRes() + 50);
        Mcu.SendXKey(CanLexushZmytCarFuncView.RvsDelay() + 42);
        if (FtSet.IsIconExist(1) == 0) {
            Iop.RstPort(0);
        }
        byte[] fileMsg = new byte[8];
        fileMsg[0] = (byte) FtSet.GetCanTp();
        fileMsg[1] = (byte) ((FtSet.GetCanTp() >> 8) & 255);
        fileMsg[2] = (byte) FtSet.GetCanSubT();
        fileMsg[3] = (byte) CanLexushZmytCarInitView.IsHaveAir();
        CanFunc.getInstance();
        CanFunc.sendFileCarInfo(fileMsg, CanFunc.Can_Factory_Set_fileName);
        MyApplication.mContext.sendBroadcast(new Intent("com.ts.can.BROADCAST_CAN_INFO_LEXUS_AIR"));
    }

    public static void DealDevEvent() {
        BtExe bt = BtExe.getBtInstance();
        if (mOldBtSta != bt.getSta() && FtSet.IsIconExist(1) == 0) {
            mOldBtSta = bt.getSta();
            if ((mOldBtSta == 3 || mOldBtSta == 2 || mOldBtSta == 4) && CanIF.IsExdMode()) {
                Iop.RstPort(1);
            } else {
                Iop.RstPort(0);
            }
        }
        if (CanIF.mGpsVoiceDelay > 0) {
            CanIF.mGpsVoiceDelay--;
            if (CanIF.mGpsVoiceDelay == 0) {
                Iop.RstPort(1);
            }
        }
    }

    public static void showWin() {
        if (!mfgShow) {
            mfgAutoEnt = true;
            CanFunc.showCanActivity(CanCarDeviceActivity.class);
        }
    }

    public static void entMode() {
        if (!mfgShow) {
            CanFunc.showCanActivity(CanCarDeviceActivity.class);
        }
    }

    public static void finishWin() {
        if (mfgShow) {
            mfgFinish = true;
        }
    }

    public static boolean IsWin() {
        return mfgShow;
    }
}
