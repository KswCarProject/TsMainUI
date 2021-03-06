package com.ts.can.bmw.zmyt;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
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
import com.ts.canview.CanItemMsgBox;
import com.ts.main.common.MainSet;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.Mcu;

public class CanBmwZmytWithCDExdActivity extends CanBaseActivity implements UserCallBack, View.OnTouchListener, View.OnClickListener {
    public static final String TAG = "CanBmwZmytWithCDExdActivity";
    public static int mBtCnt = 0;
    public static int mOldBtSta = 0;
    public static int mOldCanKeyCnt = 1;
    public static int mOldMode = 0;
    public static boolean mfgAutoEnt = false;
    public static boolean mfgFinish = false;
    public static boolean mfgRadraEnt = false;
    private static boolean mfgSendFunc = false;
    public static boolean mfgShow = false;
    private AutoFitTextureView mCameraView;
    private RelativeLayoutManager mManager;
    private CanItemMsgBox mMsgBox;
    private WindowManager wManager;
    private WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        if (CanJni.GetSubType() != 1) {
            InitUI();
        } else if (this.mMsgBox == null) {
            this.mMsgBox = new CanItemMsgBox(0, this, R.string.can_not_support_msg, new CanItemMsgBox.onMsgBoxClick() {
                public void onOK(int param) {
                    CanBmwZmytWithCDExdActivity.this.finish();
                }
            });
            this.mMsgBox.SetCancelCallBack(new CanItemMsgBox.onMsgBoxClick2() {
                public void onCancel(int param) {
                    CanBmwZmytWithCDExdActivity.this.finish();
                }
            });
            this.mMsgBox.getDlg().setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface arg0) {
                    CanBmwZmytWithCDExdActivity.this.finish();
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        setContentView(R.layout.activity_can_bmw_withcd_base);
        this.mManager = new RelativeLayoutManager(this, R.id.can_bmw_withcd_base_layout);
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
        if (CanJni.GetSubType() != 1) {
            onCreate((Bundle) null);
            MainTask.GetInstance().SetUserCallBack(this);
            ResetData(false);
            if (!mfgAutoEnt) {
                Evc.GetInstance().evol_workmode_set(12);
                mfgRadraEnt = false;
                Log.d(TAG, "WORKMODE_EXD");
            }
            mfgShow = true;
            mfgFinish = false;
            Log.d(TAG, "onResume");
            TsDisplay.GetInstance().SetDispParat(-1);
            MainSet.GetInstance().SetVideoChannel(2);
            if (CanFunc.getInstance().IsCore() == 1) {
                BackcarService.getInstance().SetSource(1);
            }
            this.mCameraView = (AutoFitTextureView) findViewById(R.id.DevtextureView);
            BackcarService.getInstance().StartCamera(this.mCameraView, false);
            if (CanFunc.getInstance().IsCore() == 1 && CanFunc.IsRviewDis() > 0) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mCameraView.getLayoutParams();
                layoutParams.width = -1;
                layoutParams.height = -1;
                layoutParams.leftMargin = 0;
                this.mCameraView.setLayoutParams(layoutParams);
            }
            CanJni.BmwZmytHostSet(153, 0, 0, 0);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        mfgShow = false;
        mfgAutoEnt = false;
        Log.d(TAG, "onPause");
        BackcarService.getInstance().StopCamera();
        CanJni.BmwZmytHostSet(153, 1, 0, 0);
    }

    public static void BmwZmytWithCDDModeChange(int sta) {
        int temp;
        if (sta == 0) {
            Log.d(TAG, "BmwZmytWithCDDModeChange Exd Exit");
            if (CanBmwZmytWithCDCarInitView.IsAuxConfig() == 2) {
                temp = (CanBmwZmytWithCDCarInitView.IsAuxPara2() << 4) | CanBmwZmytWithCDCarInitView.IsAuxPara3();
            } else if (CanBmwZmytWithCDCarInitView.IsAuxConfig() == 3) {
                temp = CanBmwZmytWithCDCarInitView.IsAuxPara4() << 4;
            } else {
                temp = CanBmwZmytWithCDCarInitView.IsAuxPara1();
            }
            CanJni.BmwZmytHostSet(144, CanBmwZmytWithCDCarInitView.IsAuxConfig(), temp, 0);
            return;
        }
        Log.d(TAG, "AudiWithCDDModeChange Exd Enter");
    }

    public static void showBmwZmytWithCDWin() {
        if (!mfgShow) {
            mfgAutoEnt = true;
            CanFunc.showCanActivity(CanBmwZmytWithCDExdActivity.class);
        }
    }

    public static void entBmwZmytWithCDMode() {
        if (!mfgShow) {
            CanFunc.showCanActivity(CanBmwZmytWithCDExdActivity.class);
        }
    }

    public static void showBmwZmytWithCDRadarWin() {
        if (!mfgShow) {
            mfgAutoEnt = true;
            mfgRadraEnt = true;
            CanFunc.showCanActivity(CanBmwZmytWithCDExdActivity.class);
        }
    }

    public static void finishBmwZmytWithCDRadraWin() {
        if (mfgShow && mfgRadraEnt) {
            mfgFinish = true;
        }
    }

    public static void finishBmwZmytWithCDWin() {
        if (mfgShow) {
            mfgFinish = true;
        }
    }

    public static boolean IsBmwZmytWithCDWin() {
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
        if (CanBmwZmytWithCDCarFuncView.RvsMode() == 1) {
            Mcu.SendXKey(33);
        } else if (CanBmwZmytWithCDCarFuncView.RvsMode() == 2) {
            Mcu.SendXKey(35);
        } else if (CanBmwZmytWithCDCarFuncView.RvsMode() == 3) {
            Mcu.SendXKey(36);
        } else {
            Mcu.SendXKey(34);
        }
        if (CanFunc.getInstance().IsCore() == 1) {
            if (CanBmwZmytWithCDCarFuncView.RCamera() <= 0 || CanBmwZmytWithCDCarFuncView.RvsMode() == 2) {
                Mcu.SendXKey(40);
            } else {
                Mcu.SendXKey(41);
            }
        } else if (CanBmwZmytWithCDCarFuncView.RCamera() != 0) {
            Mcu.SendXKey(41);
        } else {
            Mcu.SendXKey(40);
        }
        Mcu.SendXKey(((FtSet.Getlgb4() & 3840) >> 8) + 50);
        CanBmwZmytWithCDCarInitView.SetCamType(0, 0, 0);
        if (CanBmwZmytWithCDCarInitView.IsLvdsType() == 0 && CanFunc.getInstance().IsCore() == 1) {
            Mcu.ReqOrgTiming(32);
        } else if (CanBmwZmytWithCDCarInitView.IsLvdsType() == 1 && CanFunc.getInstance().IsCore() == 1) {
            Mcu.ReqOrgTiming(33);
        }
        Mcu.SendXKey(CanBmwZmytWithCDCarFuncView.RvsDelay() + 42);
        if (FtSet.IsIconExist(1) == 0) {
            Iop.RstPort(0);
        }
        byte[] fileMsg = new byte[8];
        fileMsg[0] = (byte) FtSet.GetCanTp();
        fileMsg[1] = (byte) FtSet.GetCanSubT();
        fileMsg[2] = (byte) CanFunc.GetSpeedDwSet();
        CanFunc.sendFileCarInfo(fileMsg, CanFunc.Can_Factory_Set_fileName);
    }

    public static void DealDevEvent() {
        int temp;
        BtExe bt = BtExe.getBtInstance();
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
                        Log.d(TAG, "Bt call on ");
                        Iop.RstPort(1);
                        break;
                    } else {
                        mBtCnt--;
                        break;
                    }
                case 2:
                    if (Iop.GetMediaOrBlue() == 0) {
                        Log.d(TAG, "Bt call of ");
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
                Log.d(TAG, "GpsVoice on ");
                Iop.RstPort(1);
            }
        }
        if (mOldCanKeyCnt > 0) {
            mOldCanKeyCnt--;
            if (mOldCanKeyCnt == 0) {
                mOldCanKeyCnt = 50;
                if (Iop.GetWorkMode() == 12) {
                    temp = 129;
                } else if (CanBmwZmytWithCDCarFuncView.IsSwPass() <= 0) {
                    temp = 0;
                } else if (bt.getSta() >= 1) {
                    temp = 128;
                } else {
                    temp = 129;
                }
                CanJni.BmwZmytHostSet(161, temp, 0, 0);
            }
        }
    }
}
