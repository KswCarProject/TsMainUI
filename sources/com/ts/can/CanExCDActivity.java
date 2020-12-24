package com.ts.can;

import android.os.Bundle;
import android.util.Log;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.TsDisplay;
import com.ts.main.common.MainSet;
import com.yyw.ts70xhw.FtSet;

public class CanExCDActivity extends CanBaseActivity {
    public static final String TAG = "CanExCDActivity";
    private static boolean fgAvmVaild = false;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        fgAvmVaild = false;
        switch (CanJni.GetCanType()) {
            case 6:
                if (FtSet.GetCanSubT() == 1 || FtSet.GetCanSubT() == 2 || FtSet.GetCanSubT() == 5 || FtSet.GetCanSubT() == 6 || FtSet.GetCanSubT() == 9 || FtSet.GetCanSubT() == 8) {
                    CanJni.NissanCamera360Key(1);
                    fgAvmVaild = true;
                    finish();
                    break;
                }
            case 46:
                if (CanJni.GetSubType() == 1) {
                    CanJni.HmS5CameraSet(128);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    CanJni.HmS5CameraSet(0);
                    fgAvmVaild = true;
                    finish();
                    break;
                }
                break;
            case 47:
                CanJni.QCCamSwitch();
                fgAvmVaild = true;
                finish();
                break;
            case 49:
                if (CanJni.GetSubType() == 5) {
                    CanJni.SenovaRzcAvmCmd(2);
                    fgAvmVaild = true;
                    finish();
                    break;
                }
                break;
            case 145:
                if (CanJni.GetSubType() == 3) {
                    CanJni.RzcSciCameraSet(0, 1);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                    CanJni.RzcSciCameraSet(0, 0);
                    fgAvmVaild = true;
                    finish();
                    break;
                }
                break;
            case 149:
                CanJni.NissanWcCameryKey(1, 0);
                fgAvmVaild = true;
                finish();
                break;
            case Can.CAN_DF_WC:
                if (CanJni.GetSubType() == 3) {
                    CanJni.DfWcCarAvmKey(1, 0);
                    fgAvmVaild = true;
                    finish();
                    break;
                }
                break;
            case 199:
                if (CanJni.GetSubType() == 3) {
                    CanJni.NissanCamera360Key(1);
                    fgAvmVaild = true;
                    finish();
                    break;
                }
                break;
            case 221:
                CanJni.PorscheLzAvmSet(14);
                fgAvmVaild = true;
                finish();
                break;
            case 265:
                CanJni.MitSubishiRzcAvmCmd(1);
                fgAvmVaild = true;
                finish();
                break;
            case 267:
                CanJni.DffengsOdAvmKey(1);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e3) {
                    e3.printStackTrace();
                }
                CanJni.DffengsOdAvmKey(0);
                fgAvmVaild = true;
                finish();
                break;
        }
        super.onCreate(savedInstanceState);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (!fgAvmVaild) {
            CanIF.mfgAvm = 1;
            TsDisplay.GetInstance().SetDispParat(0);
            MainSet.GetInstance().SetVideoChannel(0);
            CanCameraUI.GetInstance().EnterCamera(1);
        }
        Log.d(TAG, "-----onResume-----");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (!fgAvmVaild) {
            CanIF.mfgAvm = 0;
            TsDisplay.GetInstance().SetDispParat(-1);
            CanCameraUI.GetInstance().EnterCamera(0);
        }
    }
}
