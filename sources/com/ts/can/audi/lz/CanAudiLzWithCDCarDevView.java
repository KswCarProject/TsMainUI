package com.ts.can.audi.lz;

import android.app.Activity;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.MainUI.TsDisplay;
import com.ts.backcar.AutoFitTextureView;
import com.ts.backcar.BackcarService;
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

public class CanAudiLzWithCDCarDevView extends CanRelativeCarInfoView {
    public static int mBtCnt = 0;
    public static int mOldBtSta = 0;
    public static int mOldMode = 0;
    public static boolean mfgAutoEnt = false;
    public static boolean mfgFinish = false;
    public static boolean mfgShow = false;
    private AutoFitTextureView mCameraView;
    private RelativeLayoutManager mManager;

    public CanAudiLzWithCDCarDevView(Activity activity) {
        super(activity);
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        getActivity().setContentView(R.layout.activity_can_audi_withcd_base);
    }

    public void doOnResume() {
        super.doOnResume();
        if (!mfgAutoEnt) {
            Evc.GetInstance().evol_workmode_set(12);
        }
        mfgShow = true;
        mfgFinish = false;
        TsDisplay.GetInstance().SetDispParat(-1);
        MainSet.GetInstance().SetVideoChannel(2);
        if (CanFunc.getInstance().IsCore() == 1) {
            BackcarService.getInstance().SetSource(1);
        }
        this.mCameraView = (AutoFitTextureView) getActivity().findViewById(R.id.DevtextureView);
        this.mCameraView.setOnTouchListener(this);
        BackcarService.getInstance().StartCamera(this.mCameraView, false);
        if (CanFunc.getInstance().IsCore() == 1 && CanFunc.IsRviewDis() > 0) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mCameraView.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -1;
            layoutParams.leftMargin = 0;
            this.mCameraView.setLayoutParams(layoutParams);
        }
        this.mManager = new RelativeLayoutManager(getActivity(), R.id.can_withcd_base_layout);
        this.mManager.GetLayout().setOnTouchListener(this);
        this.mManager.GetLayout().setClickable(true);
        CanJni.AudiLzSrcSwitch(1);
    }

    public void doOnPause() {
        super.doOnPause();
        mfgShow = false;
        mfgAutoEnt = false;
        BackcarService.getInstance().StopCamera();
        CanJni.AudiLzSrcSwitch(2);
    }

    public static void AudiWithCDDModeChange(int sta) {
        if (sta == 0) {
            CanJni.AudiLzAudioSet(1, 0 | ((CanAudiLzWithCDCarInitView.IsAuxLin1() + 1) & 15));
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

    public void ResetData(boolean check) {
        if (mfgShow && mfgFinish) {
            mfgFinish = false;
            mfgShow = false;
            getActivity().finish();
        }
    }

    public void QueryData() {
    }

    public boolean onTouch(View arg0, MotionEvent event) {
        if (event.getAction() == 0 || event.getAction() == 2 || event.getAction() != 1) {
            return false;
        }
        Mcu.SetCkey(8);
        return false;
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public static void Init() {
        if (CanAudiLzWithCDCarFuncView.RvsMode() == 1) {
            Mcu.SendXKey(33);
        } else if (CanAudiLzWithCDCarFuncView.RvsMode() == 2) {
            Mcu.SendXKey(35);
        } else if (CanAudiLzWithCDCarFuncView.RvsMode() == 3) {
            Mcu.SendXKey(36);
        } else {
            Mcu.SendXKey(34);
        }
        if (CanFunc.getInstance().IsCore() == 1) {
            if (CanAudiLzWithCDCarFuncView.RCamera() <= 0 || CanAudiLzWithCDCarFuncView.RvsMode() == 2) {
                Mcu.SendXKey(40);
            } else {
                Mcu.SendXKey(41);
            }
        } else if (CanAudiLzWithCDCarFuncView.RCamera() > 0) {
            Mcu.SendXKey(41);
        } else {
            Mcu.SendXKey(40);
        }
        Mcu.SendXKey(CanAudiLzWithCDCarFuncView.RvsDelay() + 42);
        Mcu.SendXKey(((FtSet.Getlgb3() & 61440) >> 12) + 50);
        CanAudiLzWithCDCarInitView.SetCamType(0, 0, 0);
        if (CanAudiLzWithCDCarInitView.IsLvdsType() == 0 && CanFunc.getInstance().IsCore() == 1) {
            Mcu.ReqOrgTiming(32);
        } else if (CanAudiLzWithCDCarInitView.IsLvdsType() == 1 && CanFunc.getInstance().IsCore() == 1) {
            Mcu.ReqOrgTiming(33);
        }
        Intent intent = new Intent("com.ts.can.BROADCAST_CAN_INFO");
        intent.putExtra("CanType", CanJni.GetCanType());
        intent.putExtra("CanSubType", CanAudiLzWithCDCarInitView.DoorUI());
        MyApplication.mContext.sendBroadcast(intent);
        byte[] fileMsg = new byte[8];
        fileMsg[0] = (byte) FtSet.GetCanTp();
        fileMsg[1] = (byte) CanAudiLzWithCDCarInitView.DoorUI();
        CanFunc.sendFileCarInfo(fileMsg, CanFunc.Can_Factory_Set_fileName);
        if (FtSet.IsIconExist(1) == 0) {
            Iop.RstPort(0);
        }
    }

    public static void DealDevEvent() {
        if (FtSet.IsIconExist(1) == 0) {
            switch (mOldBtSta) {
                case 0:
                    if (Iop.GetMediaOrBlue() > 0) {
                        mOldBtSta = 1;
                        mBtCnt = 10;
                        break;
                    }
                    break;
                case 1:
                    if (mBtCnt <= 0) {
                        mOldBtSta = 2;
                        Iop.RstPort(1);
                        break;
                    } else {
                        mBtCnt--;
                        break;
                    }
                case 2:
                    if (Iop.GetMediaOrBlue() == 0) {
                        Iop.RstPort(0);
                        mOldBtSta = 0;
                        break;
                    }
                    break;
            }
        }
        if (CanIF.mGpsVoiceDelay > 0) {
            CanIF.mGpsVoiceDelay--;
            if (CanIF.mGpsVoiceDelay == 0) {
                Iop.RstPort(1);
            }
        }
    }
}
