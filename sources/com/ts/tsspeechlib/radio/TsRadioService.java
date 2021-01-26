package com.ts.tsspeechlib.radio;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.ts.MainUI.Evc;
import com.ts.main.common.WinShow;
import com.ts.tsspeechlib.radio.ITsSpeechRadio;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.Radio;
import com.yyw.ts70xhw.StSet;

public class TsRadioService extends Service {
    public static final String BROADCAST_LANCHER_FUNC_CLOSEMEDIA = "forfan.intent.action.CLOSEMEDIA";
    private mTsSpeechRadio mBinder;

    public IBinder onBind(Intent arg0) {
        if (this.mBinder == null) {
            this.mBinder = new mTsSpeechRadio();
        }
        return this.mBinder;
    }

    public class mTsSpeechRadio extends ITsSpeechRadio.Stub {
        public mTsSpeechRadio() {
        }

        public void onPrevFreq() throws RemoteException {
            Radio.TuneMprev();
        }

        public void onNextFreq() throws RemoteException {
            Radio.TuneMnext();
        }

        public void onSelectedFreq(int number) throws RemoteException {
            Radio.TuneBandFq(0, number);
        }

        public void openRadio() throws RemoteException {
            WinShow.TsEnterMode(1);
        }

        public void closeRadio() throws RemoteException {
            Intent intent = new Intent("forfan.intent.action.CLOSEMEDIA");
            intent.putExtra("mode", 1);
            TsRadioService.this.sendBroadcast(intent);
        }

        public void onRadioFM() throws RemoteException {
            Radio.TuneBandFm();
        }

        public void onRadioAM() throws RemoteException {
            Radio.TuneBandAm();
        }

        public int getRadioState() throws RemoteException {
            if (Iop.GetWorkMode() == 1) {
                return 1;
            }
            return 0;
        }

        public void setMixVolumeSize(int size) throws RemoteException {
            Log.d("TsRadioService", "setMixVolumeSize state = " + size);
            StSet.Setmvwns(size);
        }

        public void setSoundCoexistence(int state) throws RemoteException {
            Log.d("TsRadioService", "setSoundCoexistence state = " + state);
            if (state == 1) {
                Evc.GetInstance().evol_navi_set(1, false);
            } else if (state == 0) {
                Evc.GetInstance().evol_navi_set(0, false);
            }
        }

        public int getRadioBand() throws RemoteException {
            int mCurBand = Radio.GetDisp(2);
            if (mCurBand < 4) {
            }
            return mCurBand;
        }

        public void SeekUp() throws RemoteException {
            Radio.TuneSearch(1);
        }

        public void SeekDn() throws RemoteException {
            Radio.TuneSearch(0);
        }

        public void OpenRadioCh() throws RemoteException {
            Evc.GetInstance().evol_mix_set(1);
            Evc.GetInstance().evol_workmode_set(1);
        }

        public void CloseRadioCh() throws RemoteException {
            Evc.GetInstance().evol_mix_set(0);
            Evc.GetInstance().evol_workmode_set(0);
        }

        public void TurnBandAndFq(int nBand, int fq) throws RemoteException {
            Radio.TuneBandFq(nBand, fq);
        }
    }
}
