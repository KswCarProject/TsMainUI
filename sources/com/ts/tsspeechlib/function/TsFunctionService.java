package com.ts.tsspeechlib.function;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.ts.MainUI.Evc;
import com.ts.main.common.KeyTouch;
import com.ts.tsspeechlib.function.ITsSpeechFunction;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.Mcu;
import com.yyw.ts70xhw.StSet;

public class TsFunctionService extends Service {
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
            Intent intent = new Intent();
            intent.addFlags(337641472);
            intent.setComponent(new ComponentName("com.ts.MainUI", "com.ts.set.SetMainActivity"));
            if (intent != null) {
                TsFunctionService.this.startActivity(intent);
            }
        }

        public void openAvin() throws RemoteException {
            Intent intent = new Intent();
            intent.addFlags(337641472);
            intent.setComponent(new ComponentName("com.ts.MainUI", "com.ts.main.avin.AvinMainActivity"));
            if (intent != null) {
                TsFunctionService.this.startActivity(intent);
            }
        }

        public void openCarInfo() throws RemoteException {
            Intent intent = new Intent();
            intent.addFlags(337641472);
            intent.setComponent(new ComponentName("com.ts.MainUI", "com.ts.can.CanMainActivity"));
            if (intent != null) {
                TsFunctionService.this.startActivity(intent);
            }
        }

        public void speechStartTTS() throws RemoteException {
            Evc.GetInstance().evol_mix_set(1, false);
        }

        public void speechStopTTS() throws RemoteException {
            Evc.GetInstance().evol_mix_set(0, false);
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
    }
}
