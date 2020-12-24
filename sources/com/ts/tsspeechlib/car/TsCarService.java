package com.ts.tsspeechlib.car;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.ts.tsspeechlib.car.ITsSpeechCar;

public class TsCarService extends Service {
    private mTsSpeechCar mBinder;

    public IBinder onBind(Intent arg0) {
        if (this.mBinder == null) {
            this.mBinder = new mTsSpeechCar();
        }
        return this.mBinder;
    }

    public class mTsSpeechCar extends ITsSpeechCar.Stub {
        public mTsSpeechCar() {
        }

        public void onTurnOnAir() throws RemoteException {
        }

        public void onTurnDownAir() throws RemoteException {
        }

        public void onHighAir() throws RemoteException {
        }

        public void onLowAir() throws RemoteException {
        }

        public void onSetLoop(String number) throws RemoteException {
        }

        public void onSetAir(String number) throws RemoteException {
        }

        public void onUpWindSpeed() throws RemoteException {
        }

        public void onDownWindSpeed() throws RemoteException {
        }

        public void onWindMode(String number) throws RemoteException {
        }

        public void onOpenSkylight() throws RemoteException {
        }

        public void onCloseSkylight() throws RemoteException {
        }

        public void onOpenAllCarwindow() throws RemoteException {
        }

        public void onCloseAllCarwindow() throws RemoteException {
        }

        public void onOpenCarwindow(String number) throws RemoteException {
        }

        public void onCloseCarwindow(String number) throws RemoteException {
        }

        public void onHeatRearwindow() throws RemoteException {
        }
    }
}
