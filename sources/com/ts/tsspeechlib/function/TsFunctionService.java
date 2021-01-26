package com.ts.tsspeechlib.function;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.ts.MainUI.AuthServer;
import com.ts.MainUI.Evc;
import com.ts.can.CanIF;
import com.ts.main.common.KeyTouch;
import com.ts.main.common.MainSet;
import com.ts.main.common.MainUI;
import com.ts.main.common.WinShow;
import com.ts.main.common.tool;
import com.ts.tsspeechlib.function.ITsSpeechFunction;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.Mcu;
import com.yyw.ts70xhw.StSet;

public class TsFunctionService extends Service {
    public static final String BROADCAST_LANCHER_FUNC_CLOSEMEDIA = "forfan.intent.action.CLOSEMEDIA";
    /* access modifiers changed from: private */
    public ITsFunctionCallback functionCallback;
    private mTsSpeechFunction mBinder;

    public IBinder onBind(Intent arg0) {
        if (this.mBinder == null) {
            this.mBinder = new mTsSpeechFunction();
        }
        return this.mBinder;
    }

    public class mTsSpeechFunction extends ITsSpeechFunction.Stub {
        public mTsSpeechFunction() {
        }

        public boolean isScreenOpen() throws RemoteException {
            if (Mcu.BklisOn() == 1) {
                return true;
            }
            return false;
        }

        public void onOpenScreen() throws RemoteException {
            if (Mcu.BklisOn() == 0) {
                Mcu.BklTurn();
            }
        }

        public void onCloseScreen() throws RemoteException {
            if (Mcu.BklisOn() == 1) {
                Mcu.BklTurn();
            }
        }

        public int getCurrentBrightness() throws RemoteException {
            return StSet.GetBLDay();
        }

        public int getMinBrightnes() throws RemoteException {
            return 0;
        }

        public int getMaxBrightnes() throws RemoteException {
            return 6;
        }

        public void onBrightenScreen() throws RemoteException {
            if (Mcu.GetIll() == 1 && StSet.GetBLIll() == 1) {
                if (StSet.GetBLNight() < 6) {
                    StSet.SetBLNight(StSet.GetBLNight() + 1);
                }
            } else if (StSet.GetBLDay() < 6) {
                StSet.SetBLDay(StSet.GetBLDay() + 1);
            }
        }

        public void onDimmingScreen() throws RemoteException {
            if (Mcu.GetIll() == 1 && StSet.GetBLIll() == 1) {
                if (StSet.GetBLNight() > 0) {
                    StSet.SetBLNight(StSet.GetBLNight() - 1);
                }
            } else if (StSet.GetBLDay() > 0) {
                StSet.SetBLDay(StSet.GetBLDay() - 1);
            }
        }

        public void onScreenBrightest() throws RemoteException {
            StSet.SetBLDay(6);
        }

        public void onScreenDarkest() throws RemoteException {
            StSet.SetBLDay(0);
        }

        public int getCurrentVolume() throws RemoteException {
            return Iop.GetVolume(0);
        }

        public int getMinVolume() throws RemoteException {
            return 0;
        }

        public int getMaxVolume() throws RemoteException {
            return Evc.GetInstance().volume_max;
        }

        public void onVolumeUp() throws RemoteException {
            Evc.GetInstance().Evol_vol_tune(1);
        }

        public void onVolumeDown() throws RemoteException {
            Evc.GetInstance().Evol_vol_tune(0);
        }

        public void onVolumeMax() throws RemoteException {
            Evc.GetInstance().evol_mediavol_set(Evc.GetInstance().volume_max);
        }

        public void onVolumeMin() throws RemoteException {
            Evc.GetInstance().evol_mediavol_set(0);
        }

        public boolean isVolumeMute() throws RemoteException {
            return Iop.GetMute() == 1;
        }

        public void onVolumeMute() throws RemoteException {
            Evc.GetInstance().evol_mut_set(1);
        }

        public void onVolumeUnmute() throws RemoteException {
            Evc.GetInstance().evol_mut_set(0);
        }

        public void showLauncher() throws RemoteException {
            KeyTouch.GetInstance().sendKeyClick(3);
        }

        public int getWorkMode() throws RemoteException {
            return Iop.GetWorkMode();
        }

        public void openSetting() throws RemoteException {
            WinShow.show("com.ts.MainUI", "com.ts.set.SetMainActivity");
        }

        public void openAvin() throws RemoteException {
            WinShow.show("com.ts.MainUI", "com.ts.main.avin.AvinMainActivity");
        }

        public void openCarInfo() throws RemoteException {
            WinShow.show("com.ts.MainUI", "com.ts.can.CanMainActivity");
        }

        public void speechStartTTS() throws RemoteException {
        }

        public void speechStopTTS() throws RemoteException {
        }

        public void speechStartRecognition() throws RemoteException {
            Evc.GetInstance().evol_popmute_set(Iop.GetWorkMode());
        }

        public void speechStopRecognition() throws RemoteException {
            Evc.GetInstance().evol_popmute_clr(Iop.GetWorkMode());
        }

        public void setVolume(int number) throws RemoteException {
            if (number >= 0 && number <= Evc.GetInstance().volume_max) {
                Evc.GetInstance().evol_mediavol_set(number);
            }
        }

        public void setScreenBrightness(int number) throws RemoteException {
            if (number >= 0 && number <= 6) {
                StSet.SetBLDay(number);
            }
        }

        public void supportFastCharging(int state) throws RemoteException {
            Log.d("TsFunctionService", "supportFastCharging : " + state);
            if (TsFunctionService.this.functionCallback == null) {
                return;
            }
            if (state == 1) {
                TsFunctionService.this.functionCallback.isSupportFastCharging(true);
            } else if (state == 0) {
                TsFunctionService.this.functionCallback.isSupportFastCharging(false);
            }
        }

        public void setFunctionCallback(ITsFunctionCallback callback) throws RemoteException {
            Log.d("TsFunctionService", "setFunctionCallback");
            TsFunctionService.this.functionCallback = callback;
        }

        public int isIllOn() throws RemoteException {
            if (Mcu.GetIll() == 1) {
                return 1;
            }
            return 0;
        }

        public int isBackCar() throws RemoteException {
            if (MainUI.IsCameraMode() == 1) {
                return 1;
            }
            return 0;
        }

        public int withBrakes() throws RemoteException {
            if (Mcu.GetBrake() == 1) {
                return 1;
            }
            return 0;
        }

        public String getDeviceType() throws RemoteException {
            return "8259";
        }

        public String getHMI() throws RemoteException {
            return MainSet.GetHMIVersionToClient();
        }

        public String getBranch() throws RemoteException {
            return tool.GetInstance().GetBranchVerSion();
        }

        public int GetRadioIC() throws RemoteException {
            return FtSet.GetRadioIc();
        }

        public int GetCanType() throws RemoteException {
            return FtSet.GetCanTp();
        }

        public int GetCameraType() throws RemoteException {
            return FtSet.GetCamType();
        }

        public void SendKey(int KeyCode) throws RemoteException {
            Mcu.SetCkey(KeyCode);
        }

        public void KillProcess(String pname) throws RemoteException {
            tool.GetInstance().killProcess(pname);
        }

        public void OpenMainUIApp(int nWin, int parat) throws RemoteException {
            if (nWin > 0 && nWin <= 11) {
                WinShow.GotoWin(nWin, parat);
            }
        }

        public void CloseMainUIApp(int nWin) throws RemoteException {
            if (nWin <= 0 || nWin >= 11) {
                MainUI.GetInstance().BackToLauncher();
                return;
            }
            TsFunctionService.this.sendBroadcast(new Intent("forfan.intent.action.CLOSEMEDIA"));
        }

        public String GetDeviceID() throws RemoteException {
            if (AuthServer.GetInstance().GetIDType() <= 0) {
                return null;
            }
            byte[] mcuid = new byte[14];
            Mcu.GetSerialId(mcuid);
            return CanIF.byte2String(mcuid);
        }
    }
}
