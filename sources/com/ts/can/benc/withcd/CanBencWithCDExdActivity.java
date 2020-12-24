package com.ts.can.benc.withcd;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.TsDisplay;
import com.ts.MainUI.UserCallBack;
import com.ts.backcar.AutoFitTextureView;
import com.ts.backcar.BackcarService;
import com.ts.bt.BtExe;
import com.ts.can.CanBaseActivity;
import com.ts.can.CanFunc;
import com.ts.can.CanIF;
import com.ts.main.common.MainSet;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.Mcu;

public class CanBencWithCDExdActivity extends CanBaseActivity implements UserCallBack, View.OnTouchListener, View.OnClickListener {
    public static final String TAG = "CanBencWithCDExdActivity";
    private static int mBencStartTick = 0;
    private static Context mContext;
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
        Log.d(TAG, "onCreate");
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        setContentView(R.layout.activity_can_withcd_base);
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
        if (CanJni.GetSubType() == 1) {
            CanBencWithCDTouchDeal.Set(16, 0);
        } else {
            TsDisplay.GetInstance().SetDispParat(-1);
            MainSet.GetInstance().SetVideoChannel(2);
            if (CanFunc.getInstance().IsCore() == 1) {
                BackcarService.getInstance().SetSource(1);
            }
            this.mCameraView = (AutoFitTextureView) findViewById(R.id.DevtextureView);
            BackcarService.getInstance().StartCamera(this.mCameraView, false);
            if (CanBencWithCDCarInitActivity.IsRviewDis() > 0) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mCameraView.getLayoutParams();
                layoutParams.width = -1;
                layoutParams.height = -1;
                layoutParams.leftMargin = 0;
                this.mCameraView.setLayoutParams(layoutParams);
            }
        }
        CanJni.BencZmytWithCDSrcSwitch(2, 1);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        mfgShow = false;
        mfgAutoEnt = false;
        Log.d(TAG, "onPause");
        if (CanJni.GetSubType() == 1) {
            CanBencWithCDTouchDeal.Set(16, 1);
        } else {
            BackcarService.getInstance().StopCamera();
        }
        CanJni.BencZmytWithCDSrcSwitch(1, 7);
    }

    public static void BencWithCDDModeChange(int sta) {
        int temp;
        if (sta == 0) {
            Log.d(TAG, "BencWithCDDModeChange Exd Exit");
            if (CanBencWithCDCarInitActivity.RadioUi() == 0) {
                int temp2 = 0 | 128;
                if (CanBencWithCDCarInitActivity.AudioLoad() == 1) {
                    temp = temp2 | 7;
                } else if (CanBencWithCDCarInitActivity.AudioLoad() == 0) {
                    if (CanBencWithCDCarInitActivity.IsWithNavi() == 0) {
                        temp = temp2 | 1;
                    } else {
                        temp = temp2 | 2;
                    }
                } else if (CanBencWithCDCarInitActivity.IsWithAux() > 0) {
                    if (CanBencWithCDCarInitActivity.IsWithNavi() == 0) {
                        temp = temp2 | 5;
                    } else {
                        temp = temp2 | 6;
                    }
                } else if (CanBencWithCDCarInitActivity.IsWithNavi() == 0) {
                    temp = temp2 | 3;
                } else {
                    temp = temp2 | 4;
                }
            } else {
                int temp3 = 0 | 64;
                if (CanBencWithCDCarInitActivity.AudioMethod() != 2) {
                    temp3 |= 4;
                }
                if (CanBencWithCDCarInitActivity.UsbNum() == 1) {
                    temp = temp3 | 2;
                } else {
                    temp = temp3 | 1;
                }
            }
            CanJni.BencZmytWithCDAudioSet(1, temp);
            if (CanBencWithCDCarInitActivity.AudioH() <= 0 || CanBencWithCDCarInitActivity.AudioL() <= 0) {
                CanJni.BencZmytWithCDExAudioSet(0, 0, 0, 0);
            } else {
                CanJni.BencZmytWithCDExAudioSet(1, CanBencWithCDCarInitActivity.AudioL(), CanBencWithCDCarInitActivity.AudioH(), 0);
            }
        } else {
            Log.d(TAG, "BencWithCDDModeChange Exd Enter");
        }
    }

    public static void showBencWithCDWin() {
        if (!mfgShow) {
            mfgAutoEnt = true;
            CanFunc.showCanActivity(CanBencWithCDExdActivity.class);
        }
    }

    public static void entBencWithCDMode() {
        if (!mfgShow) {
            CanFunc.showCanActivity(CanBencWithCDExdActivity.class);
        }
    }

    public static void finishBencWithCDWin() {
        if (mfgShow) {
            mfgFinish = true;
        }
    }

    public static boolean IsBencWithCDWin() {
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
        if (CanBencWithCDCarFuncActivity.RvsMode() == 1) {
            Mcu.SendXKey(33);
        } else if (CanBencWithCDCarFuncActivity.RvsMode() == 2) {
            Mcu.SendXKey(35);
        } else {
            Mcu.SendXKey(34);
        }
        if ((FtSet.Getlgb2() & 15) == 1) {
            Mcu.SendXKey(41);
        } else {
            Mcu.SendXKey(40);
        }
        Mcu.SendXKey(CanBencWithCDCarFuncActivity.RvsDelay() + 42);
        Mcu.SendXKey(CanBencWithCDCarInitActivity.HostRes() + 50);
        CanBencWithCDCarInitActivity.SetCamType(0, 0, 0);
        if (CanBencWithCDCarInitActivity.IsLvdsType() == 0 && CanFunc.getInstance().IsCore() == 1) {
            Mcu.ReqOrgTiming(32);
        } else if (CanBencWithCDCarInitActivity.IsLvdsType() == 1 && CanFunc.getInstance().IsCore() == 1) {
            Mcu.ReqOrgTiming(33);
        }
        if (FtSet.IsIconExist(1) == 0) {
            Iop.RstPort(0);
        }
        byte[] fileMsg = new byte[8];
        fileMsg[0] = (byte) FtSet.GetCanTp();
        fileMsg[1] = (byte) FtSet.GetCanSubT();
        fileMsg[2] = (byte) CanBencWithCDCarInitActivity.IsDoorUI();
        CanFunc.getInstance();
        CanFunc.sendFileCarInfo(fileMsg, CanFunc.Can_Factory_Set_fileName);
        CanBencWithCDCarFuncActivity.AmbientLightSet(255, 255, 255, 255);
    }

    public static void DealDevEvent() {
        BtExe bt = BtExe.getBtInstance();
        if (mOldBtSta != bt.getSta() && FtSet.IsIconExist(1) == 0) {
            mOldBtSta = bt.getSta();
            if (CanBencWithCDCarInitActivity.RadioUi() == 0 || CanBencWithCDCarInitActivity.AudioMethod() != 1) {
                if (mOldBtSta == 3 || mOldBtSta == 2 || mOldBtSta == 4) {
                    Log.d(TAG, "Bt call on ");
                    Iop.RstPort(1);
                } else {
                    Log.d(TAG, "Bt call of ");
                    Iop.RstPort(0);
                }
            } else if (!CanIF.IsExdMode()) {
                Log.d(TAG, "Bt call of ");
                Iop.RstPort(0);
            } else if (mOldBtSta == 3 || mOldBtSta == 2 || mOldBtSta == 4) {
                Log.d(TAG, "Bt call on ");
                Iop.RstPort(0);
            } else {
                Log.d(TAG, "Bt call out ");
                Iop.RstPort(1);
            }
        }
        if (CanIF.mGpsVoiceDelay > 0) {
            CanIF.mGpsVoiceDelay--;
            if (CanIF.mGpsVoiceDelay == 0) {
                Log.d(TAG, "GpsVoice on ");
                Iop.RstPort(1);
            }
        }
        CanDataInfo.AuidoReq mStaData = new CanDataInfo.AuidoReq();
        CanJni.BencZmytWithCDGetAudioReq(mStaData);
        if (mStaData.UpdateOnce > 0 && mOldMode != mStaData.CurMode) {
            mOldMode = mStaData.CurMode;
            if (mStaData.CurMode == 1 || mStaData.CurMode == 2 || mStaData.CurMode == 4 || mStaData.CurMode == 8 || mStaData.CurMode == 9) {
                entBencWithCDMode();
            }
            if (mStaData.CurMode == 7) {
                CanFunc.mBencMsgDlg.Show(0);
            }
        }
        if (CanJni.GetSubType() == 1) {
            mBencStartTick++;
            if (mBencStartTick > 333) {
                mBencStartTick = 0;
                CanBencWithCDTouchDeal.StartCmd(1);
            }
        }
    }
}
