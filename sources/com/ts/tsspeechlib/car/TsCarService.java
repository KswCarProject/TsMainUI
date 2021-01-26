package com.ts.tsspeechlib.car;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.lgb.canmodule.Can;
import com.ts.can.CanIF;
import com.ts.main.common.MainUI;
import com.ts.tsspeechlib.car.ITsSpeechCar;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Iop;

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

        public int GetTotalKilometers() throws RemoteException {
            return 0;
        }

        public int GetOilLeftover() throws RemoteException {
            return 0;
        }

        public int GetLeftTurnSignal() throws RemoteException {
            if (Iop.GetTurnLight() == 1 || CanIF.IsLeftTurn() > 0) {
                return 1;
            }
            return 0;
        }

        public int GetRightTurnSignal() throws RemoteException {
            if (Iop.GetTurnLight() == 2 || CanIF.IsRightTurn() > 0) {
                return 1;
            }
            return 0;
        }

        public int GetEmergency() throws RemoteException {
            if (Iop.GetTurnLight() == 3 || CanIF.IsWarningLight() > 0) {
                return 1;
            }
            return 0;
        }

        public int GetSpeed() throws RemoteException {
            return (int) MainUI.GpsSpeed;
        }

        public int getLineEps() throws RemoteException {
            int eps = Can.mEpsInfo.Eps;
            if (MainUI.IsCameraMode() == 0) {
                return 0;
            }
            return eps;
        }

        public int[] GetCarDoorInfo() throws RemoteException {
            Log.i("yw", "requestCarDoorInfo");
            int[] date = new int[6];
            int[] temp = new int[6];
            switch (FtSet.GetFdoor()) {
                case 1:
                    temp[0] = Can.mCarMsg.DoorSta[1];
                    temp[1] = Can.mCarMsg.DoorSta[0];
                    break;
                case 2:
                    temp[0] = 0;
                    temp[1] = 0;
                    break;
                default:
                    temp[0] = Can.mCarMsg.DoorSta[0];
                    temp[1] = Can.mCarMsg.DoorSta[1];
                    break;
            }
            switch (FtSet.GetBdoor()) {
                case 1:
                    temp[2] = Can.mCarMsg.DoorSta[3];
                    temp[3] = Can.mCarMsg.DoorSta[2];
                    break;
                case 2:
                    temp[2] = 0;
                    temp[3] = 0;
                    break;
                default:
                    temp[2] = Can.mCarMsg.DoorSta[2];
                    temp[3] = Can.mCarMsg.DoorSta[3];
                    break;
            }
            if (1 == FtSet.GetTrunk()) {
                temp[4] = 0;
            } else {
                temp[4] = Can.mCarMsg.DoorSta[4];
            }
            if (1 == FtSet.GetHood()) {
                temp[5] = 0;
            } else {
                temp[5] = Can.mCarMsg.DoorSta[5];
            }
            for (int i = 0; i < 6; i++) {
                date[i] = temp[i];
            }
            return date;
        }

        public int[] GetCarFrRadar() throws RemoteException {
            int[] date = {Can.mRadarForeInfo.nLeftDis, Can.mRadarForeInfo.nMidLtDis, Can.mRadarForeInfo.nMidRtDis, Can.mRadarForeInfo.nRightDis, Can.mRadarRearInfo.nLeftDis, Can.mRadarRearInfo.nMidLtDis, Can.mRadarRearInfo.nMidRtDis, Can.mRadarRearInfo.nRightDis};
            if (MainUI.IsCameraMode() == 0) {
                date[4] = 0;
                date[5] = 0;
                date[6] = 0;
                date[7] = 0;
            }
            return date;
        }

        public int[] GetCarLrRadar() throws RemoteException {
            return new int[]{Can.mRadarLeftInfo.nLeftDis, Can.mRadarLeftInfo.nMidLtDis, Can.mRadarLeftInfo.nMidRtDis, Can.mRadarLeftInfo.nRightDis, Can.mRadarRightInfo.nLeftDis, Can.mRadarRightInfo.nMidLtDis, Can.mRadarRightInfo.nMidRtDis, Can.mRadarRightInfo.nRightDis};
        }

        public int IsCameraMode() throws RemoteException {
            return MainUI.IsCameraMode();
        }
    }
}
