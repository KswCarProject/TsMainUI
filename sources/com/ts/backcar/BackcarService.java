package com.ts.backcar;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.TextureView;
import com.autochips.backcar.BackCar;
import com.autochips.camera.CameraServiceImpl;
import com.ts.factoryset.AtcDisplaySettingsUtils;
import com.ts.factoryset.FsBaseActivity;
import com.ts.main.common.MainSet;
import com.ts.main.common.MainUI;
import com.yyw.ts70xhw.FtSet;
import java.util.ArrayList;

public class BackcarService {
    private static final String ACTION_PREQB_OFF = "autochips.intent.action.PREQB_POWEROFF";
    private static final String ACTION_PREQB_ON = "autochips.intent.action.PREQB_POWERON";
    private static final String ACTION_QB_OFF = "autochips.intent.action.QB_POWEROFF";
    private static final String ACTION_QB_ON = "autochips.intent.action.QB_POWERON";
    public static final String AVM_PACKAGE_NAME = "com.autochips.avmplayer";
    public static final int AVM_PORT_FRONT = 0;
    public static final int AVM_PORT_LEFT = 2;
    public static final int AVM_PORT_REAR = 1;
    public static final int AVM_PORT_RIGHT = 3;
    public static final int AVM_VIEW_AVM_CALIB = 7;
    public static final int AVM_VIEW_CAM_CALIB = 6;
    public static final int AVM_VIEW_DOUBLE_2D_3D = 3;
    public static final int AVM_VIEW_DOUBLE_2D_CAM = 2;
    public static final int AVM_VIEW_SINGLE_AVM = 1;
    public static final int AVM_VIEW_SINGLE_CAM = 0;
    public static final int AVM_VIEW_TRIPLE_CLIFF = 5;
    public static final int AVM_VIEW_TRIPLE_LIMIT = 4;
    public static final int BC_AHD_1080P_25 = 2;
    public static final int BC_AHD_1080P_30 = 7;
    public static final int BC_AHD_720P_25 = 1;
    public static final int BC_AHD_720P_30 = 6;
    public static final int BC_AVM_720P = 3;
    public static final int BC_AVM_720P_FRONT_AND_REAR = 10;
    public static final int BC_AVM_720P_REAR = 4;
    public static final int BC_CVBS = 0;
    public static final int BC_MIPI_USER = 5;
    public static final int BC_SOURCE_AHD = 1;
    public static final int BC_SOURCE_AVM = 2;
    public static final int BC_SOURCE_CVBS = 0;
    public static final int BC_TVI_720P_25 = 8;
    public static final int BC_TVI_720P_30 = 9;
    private static final String BROAD_CAST_ACTION_EXIT_AVM = "com.autochips.action.exit_avm";
    private static final String BROAD_CAST_ACTION_START_3DROUND = "com.autochips.action.3D.ROUND";
    private static final String NOTIFICATION_CHANNEL_ID_SERVICE = "com.autochips.camera.CameraService";
    private static final int NOTIFICATION_ID = 105;
    private static final String TAG = "BackCarService";
    static int Updatetime = 0;
    public static boolean bMirror = false;
    public static boolean bSigShow = false;
    private static BackcarService gInst = null;
    public static int nSourceType = 255;
    public boolean bForceExit = false;
    public boolean bIninOK = false;
    public boolean bLock = false;
    boolean bMir = false;
    public boolean bRearShow = false;
    private boolean bSignOK = false;
    public BackCar backcar;
    private Activity mActivity = null;
    private TextureView mBcView = null;
    /* access modifiers changed from: private */
    public CameraServiceImpl mCameraServiceImpl;
    public Context mContext = null;
    private BackCar.Listener mEventListener = new BackCar.Listener() {
        public void onEvent(int evt, int param1, int param2) {
            Log.i(BackcarService.TAG, "onEvent evt:" + evt);
            if (evt == 0) {
                Log.i(BackcarService.TAG, "onSignal():EVENT_TYPE_ARM2EXIT, ARM2 exit");
                BackcarService.this.bIninOK = true;
                return;
            }
            Log.i(BackcarService.TAG, "receive msg, but not for ahd of cvbs backcar");
        }
    };
    BroadcastReceiver mPowerOnOffReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.i(BackcarService.TAG, "mPowerOnOffReceiver, action:" + action);
            if (BackcarService.ACTION_PREQB_ON.equals(action)) {
                BackcarService.this.mCameraServiceImpl.sendQBMessage(true);
            } else if ("autochips.intent.action.QB_POWEROFF".equals(action)) {
                BackcarService.this.mCameraServiceImpl.sendQBMessage(false);
            } else if ("autochips.intent.action.PREQB_POWEROFF".equals(action)) {
                if (!BackcarService.this.bIninOK) {
                    BackcarService.this.backcar.sendForceExitToArm2();
                }
                BackcarService.this.mCameraServiceImpl.updateStatus("0", 103);
            } else {
                "autochips.intent.action.QB_POWERON".equals(action);
            }
        }
    };
    public int nTime = 0;

    public void StartAvm(int nPort) {
        if (this.mContext != null) {
            MainSet.GetInstance().SetVideoChannel(0);
            Intent intent = MainSet.GetInstance().GetAppStartIntent(this.mContext, "com.autochips.avmplayer");
            if (intent != null) {
                if (nPort >= 0 && nPort <= 3) {
                    intent.putExtra("port", nPort);
                }
                this.mContext.startActivity(intent);
            }
        }
    }

    public void StartAvmBack(int nPort) {
        if (this.mContext != null) {
            MainSet.GetInstance().SetVideoChannel(0);
            Intent intent = MainSet.GetInstance().GetAppStartIntent(this.mContext, "com.autochips.avmplayer");
            if (intent != null) {
                intent.putExtra("port", nPort);
                intent.putExtra("operate", 1);
                this.mContext.startActivity(intent);
            }
        }
    }

    public void Avm3dMode(int nPort) {
        Intent intent;
        if (this.mContext != null && (intent = MainSet.GetInstance().GetAppStartIntent(this.mContext, "com.autochips.avmplayer")) != null) {
            intent.putExtra("port", 1);
            intent.putExtra("mode", 1);
            this.mContext.startActivity(intent);
        }
    }

    public void Avm2dMode(int nPort) {
        Intent intent;
        if (this.mContext != null && (intent = MainSet.GetInstance().GetAppStartIntent(this.mContext, "com.autochips.avmplayer")) != null) {
            intent.putExtra("port", nPort);
            intent.putExtra("mode", 3);
            this.mContext.startActivity(intent);
        }
    }

    public void AvmLIMITMode() {
        Intent intent;
        if (this.mContext != null && (intent = MainSet.GetInstance().GetAppStartIntent(this.mContext, "com.autochips.avmplayer")) != null) {
            intent.putExtra("port", 1);
            intent.putExtra("mode", 4);
            this.mContext.startActivity(intent);
        }
    }

    public void AvmCLIFFdMode() {
        Intent intent;
        if (this.mContext != null && (intent = MainSet.GetInstance().GetAppStartIntent(this.mContext, "com.autochips.avmplayer")) != null) {
            intent.putExtra("port", 1);
            intent.putExtra("mode", 5);
            this.mContext.startActivity(intent);
        }
    }

    public void OpenSignalAvm(int nID) {
        Intent intent;
        Log.i(TAG, "OpenSignalAvm==" + nID);
        if (this.mContext != null && (intent = MainSet.GetInstance().GetAppStartIntent(this.mContext, "com.autochips.avmplayer")) != null) {
            intent.putExtra("port", nID);
            intent.putExtra("singleCamera", true);
            intent.putExtra("mode", 0);
            this.mContext.startActivity(intent);
        }
    }

    public void Start3DRound() {
        Log.i(TAG, "Start3DRound");
        Intent intent = new Intent(BROAD_CAST_ACTION_START_3DROUND);
        intent.setPackage("com.autochips.avmplayer");
        this.mContext.sendBroadcast(intent);
    }

    public void StopAvmforce() {
        Log.i(TAG, "StopAvmforce");
        Intent intent = new Intent(BROAD_CAST_ACTION_EXIT_AVM);
        intent.putExtra("force", true);
        intent.setPackage("com.autochips.avmplayer");
        this.mContext.sendBroadcast(intent);
    }

    public void StopAvm() {
        Log.i(TAG, "StopAvm");
        Intent intent = new Intent(BROAD_CAST_ACTION_EXIT_AVM);
        intent.setPackage("com.autochips.avmplayer");
        this.mContext.sendBroadcast(intent);
    }

    public static void SetSignal(boolean bShow) {
        Updatetime = 30;
        bSigShow = bShow;
    }

    public void InintCamera(Context MyContext) {
        if (this.mContext == null) {
            this.mContext = MyContext;
            Inint();
        }
    }

    public void StopActivity() {
        if (this.mActivity != null) {
            Log.i(TAG, "mActivity finish" + this.mActivity);
            this.mActivity.finish();
            this.mActivity = null;
        }
    }

    public void Task() {
        if (Updatetime > 0) {
            Updatetime--;
            if (Updatetime == 0) {
                if (GetSouceType() == 2) {
                    bSigShow = true;
                }
                this.bSignOK = bSigShow;
            }
        }
        CheckBackCarMirSet();
    }

    public boolean bIsAvmFandR() {
        if (FtSet.GetCamType() == 10) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public int GetSouceType() {
        if (MainUI.IsCameraMode() == 1) {
            return GetBackCarSouseType() - 1;
        }
        return 0;
    }

    public void ReStartFCamera() {
    }

    public void StartAvmChanel(AutoFitTextureView tex) {
        Log.i(TAG, "StartAvmChanel in");
        if (tex == null) {
            Log.e(TAG, "StartCamera tex=" + tex);
        } else if (!this.bIninOK) {
            Log.e(TAG, "StartCamera error arm2 have not exit=" + tex);
        } else {
            nSourceType = 2;
        }
    }

    public void StartCamera(AutoFitTextureView tex, boolean b) {
        Log.i(TAG, "StartCamera in");
        if (tex == null) {
            Log.e(TAG, "StartCamera tex=" + tex);
        } else if (!this.bIninOK) {
            Log.e(TAG, "StartCamera error arm2 have not exit=" + tex);
        } else {
            if (nSourceType == 255) {
                nSourceType = GetSouceType();
            }
            Log.i(TAG, "StartCamera tex nSourceType=" + nSourceType);
            this.mBcView = tex;
            this.bSignOK = true;
            bSigShow = true;
            Updatetime = 0;
            switch (nSourceType) {
                case 0:
                    this.mCameraServiceImpl.startBackcar(1, this.mBcView);
                    break;
                case 1:
                    this.mCameraServiceImpl.startBackcar(2, this.mBcView);
                    break;
                case 2:
                    this.mCameraServiceImpl.startBackcar(3, this.mBcView);
                    break;
            }
            Log.i(TAG, "StartCamera out");
        }
    }

    public void ExitCamera() {
        this.bForceExit = true;
    }

    public void ResetCamera() {
        this.bForceExit = false;
    }

    public void StopCamera() {
        if (!this.bIninOK) {
            Log.e(TAG, "StopCamera error arm2 have not exit=");
            return;
        }
        if (nSourceType >= 0) {
            Log.i(TAG, "StopCamera nSourceType=" + nSourceType);
        }
        Log.i(TAG, "StopCamera in nSourceType==" + nSourceType);
        if (this.mBcView != null) {
            switch (nSourceType) {
                case 0:
                    this.mCameraServiceImpl.stopBackcar(1, this.mBcView);
                    break;
                case 1:
                    this.mCameraServiceImpl.stopBackcar(2, this.mBcView);
                    break;
                case 2:
                    this.mCameraServiceImpl.stopBackcar(3, this.mBcView);
                    break;
            }
        }
        nSourceType = 255;
        this.mBcView = null;
        try {
            Thread.sleep(80);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "StopCamera out");
    }

    public static BackcarService getInstance() {
        if (gInst == null) {
            gInst = new BackcarService();
        }
        return gInst;
    }

    /* access modifiers changed from: package-private */
    public boolean bIsCamMirror() {
        return FtSet.GetCamMir() == 1;
    }

    /* access modifiers changed from: package-private */
    public void CheckBackCarMirSet() {
        if (bIsCamMirror() && !this.bMir) {
            AtcDisplaySettingsUtils.mLRMirrorStatusFlag = 1;
            AtcDisplaySettingsUtils.getInstance().setMirror(GetBackCarSouseType());
            this.bMir = true;
            Log.i(TAG, "SET TO 1");
        } else if (!bIsCamMirror() && this.bMir) {
            AtcDisplaySettingsUtils.mLRMirrorStatusFlag = 0;
            AtcDisplaySettingsUtils.getInstance().setMirror(GetBackCarSouseType());
            this.bMir = false;
            Log.i(TAG, "SET TO 0");
        }
    }

    public static void SetforceMir(boolean bMir2) {
        bMirror = bMir2;
    }

    public static boolean GetForceMir() {
        return bMirror;
    }

    public boolean bFastAvm() {
        return AtcDisplaySettingsUtils.getInstance().getFastAVM() == 1;
    }

    public void InintParat() {
        if (1 == AtcDisplaySettingsUtils.getInstance().getMirror(GetBackCarSouseType())) {
            this.bMir = true;
        } else {
            this.bMir = false;
        }
        CheckBackCarMirSet();
    }

    /* access modifiers changed from: package-private */
    public void Inint() {
        this.backcar = new BackCar();
        Log.d(TAG, "Set On signal listener!");
        this.mCameraServiceImpl = new CameraServiceImpl(this.mContext);
        ArrayList<Integer> evts = new ArrayList<>(3);
        evts.add(0);
        this.backcar.setListener(this.mEventListener, evts);
        this.backcar.sendReadyToArm2();
        regiestPowerOnOffReceiver();
    }

    public CameraServiceImpl GetService() {
        return this.mCameraServiceImpl;
    }

    /* access modifiers changed from: package-private */
    public int GetBackCarSouseType() {
        Log.i(TAG, "CheckBackCarSouseType nSigType==" + FtSet.GetCamType());
        switch (FtSet.GetCamType()) {
            case 1:
            case 2:
            case 5:
            case 6:
            case 7:
                return 2;
            case 3:
            case 4:
            case 8:
            case 9:
            case 10:
                return 3;
            default:
                return 1;
        }
    }

    public boolean bIsAvm360() {
        return FtSet.GetCamType() == 3;
    }

    /* access modifiers changed from: package-private */
    public void UnInint() {
        this.backcar.setListener((BackCar.Listener) null, (ArrayList) null);
        unregiestPowerOnOffReceiver();
    }

    public boolean bSingnalOK() {
        return this.bSignOK || bIsAvmFandR();
    }

    private void regiestPowerOnOffReceiver() {
        Log.i(TAG, "regiestPowerOnOffReceiver");
        IntentFilter filter = new IntentFilter();
        filter.addAction("autochips.intent.action.QB_POWEROFF");
        filter.addAction("autochips.intent.action.QB_POWERON");
        filter.addAction("autochips.intent.action.PREQB_POWEROFF");
        filter.addAction(ACTION_PREQB_ON);
        this.mContext.registerReceiver(this.mPowerOnOffReceiver, filter);
    }

    private void unregiestPowerOnOffReceiver() {
        Log.i(TAG, "unregiestPowerOnOffReceiver");
        this.mContext.unregisterReceiver(this.mPowerOnOffReceiver);
    }

    public void ShowRearDisplay() {
    }

    public void HideRearView(boolean bShow) {
    }

    public void ReSumeRearDisplay() {
    }

    public void SetSource(int src) {
        if (nSourceType != 255) {
            Log.e(TAG, "SetSourceType but have not stop nSourceType=" + nSourceType);
        }
        nSourceType = src;
    }

    public void SetCamType(int nType, int x, int y) {
        FsBaseActivity.CanSetBackcartype(nType, x, y);
    }
}
