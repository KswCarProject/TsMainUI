package com.ts.can.landrover.zmyt;

import android.app.Activity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.MainUI.TsDisplay;
import com.ts.backcar.AutoFitTextureView;
import com.ts.backcar.BackcarService;
import com.ts.bt.BtExe;
import com.ts.can.CanCameraUI;
import com.ts.can.CanCarDeviceActivity;
import com.ts.can.CanFunc;
import com.ts.can.CanIF;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.main.common.MainSet;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.Mcu;

public class CanLandRoverZmytCarDevView extends CanRelativeCarInfoView {
    private static final int ITEM_KEY_AUTOPARK = 1;
    private static final int ITEM_KEY_BT = 7;
    private static final int ITEM_KEY_CAMERA = 3;
    private static final int ITEM_KEY_HOME = 0;
    private static final int ITEM_KEY_MEDIA = 10;
    private static final int ITEM_KEY_MENU = 5;
    private static final int ITEM_KEY_MODE = 4;
    private static final int ITEM_KEY_MUSIC = 11;
    private static final int ITEM_KEY_NAVI = 6;
    private static final int ITEM_KEY_P = 2;
    private static final int ITEM_KEY_POWER = 9;
    private static final int ITEM_KEY_SET = 8;
    private static final int ITEM_TOUCH = 60;
    protected static final String TAG = "CanLandRoverZmytCarDevView";
    public static int mBtCnt = 0;
    public static int mOldBtSta = 0;
    public static int mOldMode = 0;
    public static boolean mfgAutoEnt = false;
    public static boolean mfgFinish = false;
    public static boolean mfgShow = false;
    private ParamButton[] mBtn;
    private AutoFitTextureView mCameraView;
    private CanDataInfo.CanLandRoverConfig mConfigData;
    private RelativeLayoutManager mManager;
    private CanDataInfo.CanLandRoverRadar mRadarData;

    public CanLandRoverZmytCarDevView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View v, MotionEvent event) {
        int x;
        int y;
        int Id = ((Integer) v.getTag()).intValue();
        Log.d(TAG, "Id=" + Id);
        if (Id != 60) {
            if (event.getAction() != 0) {
                if (event.getAction() == 1) {
                    switch (Id) {
                        case 0:
                            CanJni.LandRoverZmytKeyCmd(2, 0);
                            break;
                        case 1:
                            CanJni.LandRoverZmytKeyCmd(12, 0);
                            break;
                        case 2:
                            CanJni.LandRoverZmytKeyCmd(8, 0);
                            break;
                        case 3:
                            CanJni.LandRoverZmytKeyCmd(6, 0);
                            break;
                        case 4:
                            CanJni.LandRoverZmytKeyCmd(1, 0);
                            break;
                        case 6:
                            CanJni.LandRoverZmytKeyCmd(5, 0);
                            break;
                        case 7:
                            CanJni.LandRoverZmytKeyCmd(7, 0);
                            break;
                        case 8:
                            CanJni.LandRoverZmytKeyCmd(3, 0);
                            break;
                        case 9:
                            CanJni.LandRoverZmytKeyCmd(9, 0);
                            break;
                        case 10:
                            CanJni.LandRoverZmytKeyCmd(4, 0);
                            break;
                        case 11:
                            CanJni.LandRoverZmytKeyCmd(10, 0);
                            break;
                    }
                }
            } else {
                switch (Id) {
                    case 0:
                        CanJni.LandRoverZmytKeyCmd(2, 1);
                        break;
                    case 1:
                        CanJni.LandRoverZmytKeyCmd(12, 1);
                        break;
                    case 2:
                        CanJni.LandRoverZmytKeyCmd(8, 1);
                        break;
                    case 3:
                        CanJni.LandRoverZmytKeyCmd(6, 1);
                        break;
                    case 4:
                        CanJni.LandRoverZmytKeyCmd(1, 1);
                        break;
                    case 5:
                        Mcu.SetCkey(8);
                        break;
                    case 6:
                        CanJni.LandRoverZmytKeyCmd(5, 1);
                        break;
                    case 7:
                        CanJni.LandRoverZmytKeyCmd(7, 1);
                        break;
                    case 8:
                        CanJni.LandRoverZmytKeyCmd(3, 1);
                        break;
                    case 9:
                        CanJni.LandRoverZmytKeyCmd(9, 1);
                        break;
                    case 10:
                        CanJni.LandRoverZmytKeyCmd(4, 1);
                        break;
                    case 11:
                        CanJni.LandRoverZmytKeyCmd(10, 1);
                        break;
                }
            }
            return false;
        }
        if (this.mCameraView != null) {
            x = (int) ((event.getX() * 255.0f) / ((float) this.mCameraView.getWidth()));
            y = (int) ((event.getY() * 255.0f) / ((float) this.mCameraView.getHeight()));
        } else {
            x = (int) ((event.getX() * 255.0f) / 1200.0f);
            y = (int) ((event.getY() * 255.0f) / 720.0f);
        }
        Log.d(TAG, "x=" + x);
        Log.d(TAG, "y=" + y);
        if (event.getAction() == 0 || event.getAction() == 2) {
            CanJni.LandRoverZmytTouchCmd(1, x, y);
        } else if (event.getAction() == 1) {
            CanJni.LandRoverZmytTouchCmd(0, x, y);
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        getActivity().setContentView(R.layout.activity_can_landrover_withcd_base);
        this.mManager = new RelativeLayoutManager(getActivity(), R.id.can_landrover_withcd_base_layout);
        this.mBtn = new ParamButton[12];
        this.mBtn[0] = AddBtn(0, 0, 0, R.drawable.can_landrover_iconkey_home_up, R.drawable.can_landrover_iconkey_home_dn);
        this.mBtn[1] = AddBtn(1, 0, 180, R.drawable.can_landrover_iconkey_windin_up, R.drawable.can_landrover_iconkey_windin_dn);
        this.mBtn[2] = AddBtn(2, 0, 360, R.drawable.can_landrover_iconkey_windwaitup_up, R.drawable.can_landrover_iconkey_windwaitup_dn);
        this.mBtn[3] = AddBtn(3, 0, 540, R.drawable.can_landrover_iconkey_driving_up, R.drawable.can_landrover_iconkey_driving_dn);
        this.mBtn[4] = AddBtn(4, CanCameraUI.BTN_LANDWINDOD_2D3D, 0, R.drawable.can_landrover_iconkey_mode_up, R.drawable.can_landrover_iconkey_mode_dn);
        this.mBtn[5] = AddBtn(5, CanCameraUI.BTN_LANDWINDOD_2D3D, 180, R.drawable.can_landrover_iconkey_programs_up, R.drawable.can_landrover_iconkey_programs_dn);
        this.mBtn[6] = AddBtn(6, CanCameraUI.BTN_LANDWINDOD_2D3D, 360, R.drawable.can_landrover_iconkey_navi_up, R.drawable.can_landrover_iconkey_navi_dn);
        this.mBtn[7] = AddBtn(7, CanCameraUI.BTN_LANDWINDOD_2D3D, 540, R.drawable.can_landrover_iconkey_phone_up, R.drawable.can_landrover_iconkey_phone_dn);
        this.mBtn[8] = AddBtn(8, 0, 180, R.drawable.can_landrover_iconkey_setting_up, R.drawable.can_landrover_iconkey_setting_dn);
        this.mBtn[9] = AddBtn(9, 0, 540, R.drawable.can_landrover_iconkey_onoff_up, R.drawable.can_landrover_iconkey_onoff_dn);
        this.mBtn[10] = AddBtn(10, CanCameraUI.BTN_LANDWINDOD_2D3D, 180, R.drawable.can_landrover_iconkey_media_up, R.drawable.can_landrover_iconkey_media_dn);
        this.mBtn[11] = AddBtn(11, CanCameraUI.BTN_LANDWINDOD_2D3D, 540, R.drawable.can_landrover_iconkey_music_up, R.drawable.can_landrover_iconkey_music_dn);
        if (CanLandRoverZmytCarInitView.KeyType() == 1) {
            this.mBtn[1].Show(false);
            this.mBtn[3].Show(false);
            this.mBtn[10].Show(false);
            this.mBtn[11].Show(false);
        } else if (CanLandRoverZmytCarInitView.KeyType() == 2) {
            this.mBtn[1].Show(false);
            this.mBtn[9].Show(false);
            this.mBtn[5].Show(false);
            this.mBtn[11].Show(false);
        } else if (CanLandRoverZmytCarInitView.KeyType() == 3) {
            this.mBtn[1].Show(false);
            this.mBtn[3].Show(false);
            this.mBtn[4].Show(false);
            this.mBtn[11].Show(false);
            this.mBtn[5] = AddBtn(5, CanCameraUI.BTN_LANDWINDOD_2D3D, 0, R.drawable.can_landrover_iconkey_programs_up, R.drawable.can_landrover_iconkey_programs_dn);
        } else if (CanLandRoverZmytCarInitView.KeyType() == 4) {
            this.mBtn[1].Show(false);
            this.mBtn[4].Show(false);
            this.mBtn[9].Show(false);
            this.mBtn[11].Show(false);
            this.mBtn[5] = AddBtn(5, CanCameraUI.BTN_LANDWINDOD_2D3D, 0, R.drawable.can_landrover_iconkey_programs_up, R.drawable.can_landrover_iconkey_programs_dn);
        } else if (CanLandRoverZmytCarInitView.KeyType() == 5) {
            this.mBtn[8].Show(false);
            this.mBtn[3].Show(false);
            this.mBtn[4].Show(false);
            this.mBtn[11].Show(false);
            this.mBtn[5] = AddBtn(5, CanCameraUI.BTN_LANDWINDOD_2D3D, 0, R.drawable.can_landrover_iconkey_programs_up, R.drawable.can_landrover_iconkey_programs_dn);
        } else if (CanLandRoverZmytCarInitView.KeyType() == 6) {
            this.mBtn[1].Show(false);
            this.mBtn[9].Show(false);
            this.mBtn[4].Show(false);
            this.mBtn[11].Show(false);
            this.mBtn[5] = AddBtn(5, CanCameraUI.BTN_LANDWINDOD_2D3D, 0, R.drawable.can_landrover_iconkey_programs_up, R.drawable.can_landrover_iconkey_programs_dn);
        } else if (CanLandRoverZmytCarInitView.KeyType() == 7) {
            this.mBtn[1].Show(false);
            this.mBtn[5].Show(false);
            this.mBtn[3].Show(false);
            this.mBtn[11].Show(false);
        } else if (CanLandRoverZmytCarInitView.KeyType() == 8) {
            this.mBtn[8].Show(false);
            this.mBtn[3].Show(false);
            this.mBtn[5].Show(false);
            this.mBtn[11].Show(false);
        } else if (CanLandRoverZmytCarInitView.KeyType() == 9) {
            this.mBtn[1].Show(false);
            this.mBtn[3].Show(false);
            this.mBtn[5].Show(false);
            this.mBtn[11].Show(false);
        } else if (CanLandRoverZmytCarInitView.KeyType() == 10) {
            this.mBtn[1].Show(false);
            this.mBtn[3].Show(false);
            this.mBtn[5].Show(false);
            this.mBtn[11].Show(false);
        } else if (CanLandRoverZmytCarInitView.KeyType() == 11) {
            this.mBtn[8].Show(false);
            this.mBtn[3].Show(false);
            this.mBtn[5].Show(false);
            this.mBtn[6].Show(false);
            this.mBtn[7] = AddBtn(7, CanCameraUI.BTN_LANDWINDOD_2D3D, 360, R.drawable.can_landrover_iconkey_phone_up, R.drawable.can_landrover_iconkey_phone_dn);
        } else if (CanLandRoverZmytCarInitView.KeyType() == 12) {
            this.mBtn[8].Show(false);
            this.mBtn[9].Show(false);
            this.mBtn[5].Show(false);
            this.mBtn[6].Show(false);
            this.mBtn[7] = AddBtn(7, CanCameraUI.BTN_LANDWINDOD_2D3D, 360, R.drawable.can_landrover_iconkey_phone_up, R.drawable.can_landrover_iconkey_phone_dn);
        } else if (CanLandRoverZmytCarInitView.KeyType() == 13) {
            this.mBtn[8].Show(false);
            this.mBtn[9].Show(false);
            this.mBtn[5].Show(false);
            this.mBtn[11].Show(false);
        } else if (CanLandRoverZmytCarInitView.KeyType() == 14) {
            this.mBtn[1].Show(false);
            this.mBtn[9].Show(false);
            this.mBtn[5].Show(false);
            this.mBtn[11].Show(false);
        } else {
            this.mBtn[8].Show(false);
            this.mBtn[9].Show(false);
            this.mBtn[10].Show(false);
            this.mBtn[11].Show(false);
        }
        this.mConfigData = new CanDataInfo.CanLandRoverConfig();
        this.mRadarData = new CanDataInfo.CanLandRoverRadar();
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public void doOnPause() {
        super.doOnPause();
        mfgShow = false;
        mfgAutoEnt = false;
        BackcarService.getInstance().StopCamera();
        CanJni.LandRoverZmytReqControl(17, 1);
    }

    public void doOnResume() {
        super.doOnResume();
        CanJni.LandRoverZmytReqControl(17, 0);
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
        this.mCameraView.setTag(60);
        this.mCameraView.setOnTouchListener(this);
        BackcarService.getInstance().StartCamera(this.mCameraView, false);
    }

    public void ResetData(boolean check) {
        CanJni.LandRoverZmytGetConfigData(this.mConfigData);
        if (i2b(this.mConfigData.UpdateOnce) && (!check || i2b(this.mConfigData.Update))) {
            this.mConfigData.Update = 0;
            if (i2b(this.mConfigData.Yczdbczsd)) {
                this.mBtn[1] = AddBtn(1, 0, 180, R.drawable.can_landrover_iconkey_windinup_up, R.drawable.can_landrover_iconkey_windinup_dn);
            } else {
                this.mBtn[1] = AddBtn(1, 0, 180, R.drawable.can_landrover_iconkey_windin_up, R.drawable.can_landrover_iconkey_windin_dn);
            }
        }
        CanJni.LandRoverZmytGetRadarInfo(this.mRadarData);
        if (i2b(this.mRadarData.UpdateOnce) && (!check || i2b(this.mRadarData.Update))) {
            this.mRadarData.Update = 0;
            if (i2b(this.mRadarData.P)) {
                this.mBtn[2] = AddBtn(2, 0, 360, R.drawable.can_landrover_iconkey_windwait_up, R.drawable.can_landrover_iconkey_windwait_dn);
            } else {
                this.mBtn[2] = AddBtn(2, 0, 360, R.drawable.can_landrover_iconkey_windwaitup_up, R.drawable.can_landrover_iconkey_windwaitup_dn);
            }
        }
        if (mfgShow && mfgFinish) {
            mfgFinish = false;
            mfgShow = false;
            getActivity().finish();
        }
    }

    public void QueryData() {
    }

    /* access modifiers changed from: protected */
    public ParamButton AddBtn(int id, int x, int y, int up, int dn) {
        ParamButton btn = this.mManager.AddButton(x, y);
        btn.setTag(Integer.valueOf(id));
        btn.setOnTouchListener(this);
        btn.setStateDrawable(up, dn, dn);
        return btn;
    }

    public static void Init() {
        if (CanLandRoverZmytCarFuncView.RvsMode() == 1) {
            Mcu.SendXKey(33);
        } else if (CanLandRoverZmytCarFuncView.RvsMode() == 2) {
            Mcu.SendXKey(35);
        } else if (CanLandRoverZmytCarFuncView.RvsMode() == 3) {
            Mcu.SendXKey(36);
        } else {
            Mcu.SendXKey(34);
        }
        if (CanFunc.getInstance().IsCore() == 1) {
            if (CanLandRoverZmytCarFuncView.RCamera() <= 0 || CanLandRoverZmytCarFuncView.RvsMode() == 2) {
                Mcu.SendXKey(40);
            } else {
                Mcu.SendXKey(41);
            }
        } else if (CanLandRoverZmytCarFuncView.RCamera() == 1) {
            Mcu.SendXKey(41);
        } else {
            Mcu.SendXKey(40);
        }
        Mcu.SendXKey(CanLandRoverZmytCarInitView.HostRes() + 50);
        CanLandRoverZmytCarInitView.SetCamType(0, 0, 0);
        if (CanFunc.getInstance().IsCore() == 1) {
            if (CanLandRoverZmytCarInitView.IsLvdsType() == 0) {
                Mcu.ReqOrgTiming(32);
            } else if (CanLandRoverZmytCarInitView.IsLvdsType() == 1) {
                Mcu.ReqOrgTiming(33);
            }
            Mcu.SendXKey(CanLandRoverZmytCarInitView.DispType() + 75);
        }
        Mcu.SendXKey(CanLandRoverZmytCarFuncView.RvsDelay() + 42);
        if (FtSet.IsIconExist(1) == 0) {
            Iop.RstPort(0);
        }
        byte[] fileMsg = new byte[8];
        fileMsg[0] = (byte) FtSet.GetCanTp();
        fileMsg[1] = (byte) ((FtSet.GetCanTp() >> 8) & 255);
        fileMsg[2] = (byte) FtSet.GetCanSubT();
        CanFunc.getInstance();
        CanFunc.sendFileCarInfo(fileMsg, CanFunc.Can_Factory_Set_fileName);
    }

    public static void DealDevEvent() {
        BtExe btInstance = BtExe.getBtInstance();
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
        CanDataInfo.CanLandRoverWork mStaData = new CanDataInfo.CanLandRoverWork();
        CanJni.LandRoverZmytGetWorkInfo(mStaData);
        if (mStaData.UpdateOnce > 0 && mOldMode != mStaData.Mode) {
            mOldMode = mStaData.Mode;
            if (mStaData.Mode == 3 || mStaData.Mode == 4 || mStaData.Mode == 6) {
                entMode();
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
