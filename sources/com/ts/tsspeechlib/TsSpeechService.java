package com.ts.tsspeechlib;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.ts.bt.BtExe;
import com.ts.tsspeechlib.ITsSpeech;
import com.ts.tsspeechlib.bt.ITsSpeechBtListener;
import com.ts.tsspeechlib.bt.ITsSpeechBtPbInfo;
import com.ts.tsspeechlib.bt.TsBtCallback;
import java.util.List;

public class TsSpeechService extends Service {
    TsBtCallback btCallback = new TsBtCallback() {
        public void onBtStateChange(int state, String phoneNumber) {
            if (TsSpeechService.this.mBtListener != null) {
                try {
                    TsSpeechService.this.mBtListener.onBtStateChange(state, phoneNumber);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

        public void onBtConnectStateChange(int state) {
            if (TsSpeechService.this.mBtListener != null) {
                try {
                    TsSpeechService.this.mBtListener.onBtConnectStateChange(state);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

        public void onBtPbListChange(List<ITsSpeechBtPbInfo> btPbList) {
            if (TsSpeechService.this.mBtListener != null) {
                try {
                    TsSpeechService.this.mBtListener.onBtPbListChange(btPbList);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    ITsSpeechBtListener mBtListener;
    private mITsSpeech mIBinder;

    public IBinder onBind(Intent arg0) {
        BtExe.getBtInstance().setBtCallback(this.btCallback);
        if (this.mIBinder == null) {
            this.mIBinder = new mITsSpeech();
        }
        return this.mIBinder;
    }

    public class mITsSpeech extends ITsSpeech.Stub {
        public mITsSpeech() {
        }

        public void setBtPhone(IBinder binder) throws RemoteException {
        }

        public void setRadio(IBinder binder) throws RemoteException {
        }

        public void setCar(IBinder binder) throws RemoteException {
        }

        public void setFunction(IBinder binder) throws RemoteException {
        }

        public void setMusic(IBinder binder) throws RemoteException {
        }

        public void setBtListener(ITsSpeechBtListener listener) throws RemoteException {
            TsSpeechService.this.mBtListener = listener;
        }
    }
}
