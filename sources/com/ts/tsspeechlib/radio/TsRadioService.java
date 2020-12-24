package com.ts.tsspeechlib.radio;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.ts.main.common.WinShow;
import com.ts.tsspeechlib.radio.ITsSpeechRadio;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.Mcu;
import com.yyw.ts70xhw.Radio;

public class TsRadioService extends Service {
    private static final String ACTION_CLOSE_RADIO = "com.ts.speech.action.closeradio";
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
            Mcu.SetCkey(45);
        }

        public void onNextFreq() throws RemoteException {
            Mcu.SetCkey(44);
        }

        public void onSelectedFreq(int number) throws RemoteException {
            Radio.TuneFsset(number);
        }

        public void openRadio() throws RemoteException {
            WinShow.TsEnterMode(1);
        }

        public void closeRadio() throws RemoteException {
            WinShow.TsEnterMode(0);
            TsRadioService.this.sendBroadcast(new Intent(TsRadioService.ACTION_CLOSE_RADIO));
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
    }
}
