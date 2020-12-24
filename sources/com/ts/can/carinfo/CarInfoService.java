package com.ts.can.carinfo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.can.CanFunc;
import com.ts.can.carinfo.ICarInfoService;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class CarInfoService extends Service {
    /* access modifiers changed from: private */
    public static CanDataInfo.CAN_ACInfo mACInfo = new CanDataInfo.CAN_ACInfo();
    /* access modifiers changed from: private */
    public static CanDataInfo.CAN_DoorInfo mDoorInfo = new CanDataInfo.CAN_DoorInfo();
    private ISettings mISettings = new ISettings();

    class ISettings extends ICarInfoService.Stub {
        ISettings() {
        }

        public int[] requestCarAirInfo() throws RemoteException {
            Log.i("yw", "requestCarAirInfo");
            int[] date = new int[19];
            if (Can.mACInfo == null || CanJni.GetCanFsTp() == 0 || CanJni.IsHaveAc() == 0) {
                for (int i = 0; i < 19; i++) {
                    date[i] = 0;
                }
            } else {
                CarInfoService.mACInfo = Can.mACInfo;
                date[0] = 1;
                date[1] = CarInfoService.mACInfo.fgAC;
                date[2] = CarInfoService.mACInfo.fgInnerLoop;
                date[3] = CarInfoService.mACInfo.nAutoLight;
                date[4] = CarInfoService.mACInfo.fgDual;
                date[5] = CarInfoService.mACInfo.fgMaxFornt;
                date[6] = CarInfoService.mACInfo.fgRearLight;
                date[7] = CarInfoService.mACInfo.fgACMax;
                date[8] = CarInfoService.mACInfo.fgDFBL;
                date[9] = CarInfoService.mACInfo.fgEco;
                date[10] = CarInfoService.mACInfo.fgForeWindMode;
                date[11] = CarInfoService.mACInfo.fgUpWind;
                date[12] = CarInfoService.mACInfo.fgParallelWind;
                date[13] = CarInfoService.mACInfo.fgDownWind;
                date[14] = CarInfoService.mACInfo.nWindValue;
                date[15] = CarInfoService.mACInfo.nLeftTemp;
                date[16] = CarInfoService.mACInfo.nRightTemp;
                date[17] = CarInfoService.mACInfo.nLtChairHot;
                date[18] = CarInfoService.mACInfo.nRtChairHot;
            }
            return date;
        }

        public String requestCarAirLtTemp() throws RemoteException {
            if (Can.mACInfo == null || CanJni.GetCanFsTp() == 0 || CanJni.IsHaveAc() == 0) {
                return null;
            }
            CarInfoService.mACInfo = Can.mACInfo;
            return CarInfoService.mACInfo.szLtTemp;
        }

        public String requestCarAirRtTemp() throws RemoteException {
            if (Can.mACInfo == null || CanJni.GetCanFsTp() == 0 || CanJni.IsHaveAc() == 0) {
                return null;
            }
            CarInfoService.mACInfo = Can.mACInfo;
            return CarInfoService.mACInfo.szRtTemp;
        }

        public int[] requestCarDoorInfo() throws RemoteException {
            Log.i("yw", "requestCarDoorInfo");
            int[] date = new int[18];
            if (Can.mDoorInfo == null || CanJni.GetCanFsTp() == 0) {
                for (int i = 0; i < 7; i++) {
                    date[i] = 0;
                }
            } else {
                CanDataInfo.CAN_DoorInfo curDoor = Can.mDoorInfo;
                CanJni.GetDoorInfo(curDoor);
                if (CanJni.GetCanType() == 0) {
                    CarInfoService.mDoorInfo.LFOpen = 0;
                    CarInfoService.mDoorInfo.RFOpen = 0;
                    CarInfoService.mDoorInfo.LROpen = 0;
                    CarInfoService.mDoorInfo.RROpen = 0;
                    CarInfoService.mDoorInfo.RearOpen = 0;
                    CarInfoService.mDoorInfo.HeadOpen = 0;
                } else {
                    switch (FtSet.GetFdoor()) {
                        case 1:
                            CarInfoService.mDoorInfo.LFOpen = curDoor.RFOpen;
                            CarInfoService.mDoorInfo.RFOpen = curDoor.LFOpen;
                            break;
                        case 2:
                            CarInfoService.mDoorInfo.LFOpen = 0;
                            CarInfoService.mDoorInfo.RFOpen = 0;
                            break;
                        default:
                            CarInfoService.mDoorInfo.LFOpen = curDoor.LFOpen;
                            CarInfoService.mDoorInfo.RFOpen = curDoor.RFOpen;
                            break;
                    }
                    switch (FtSet.GetBdoor()) {
                        case 1:
                            CarInfoService.mDoorInfo.LROpen = curDoor.RROpen;
                            CarInfoService.mDoorInfo.RROpen = curDoor.LROpen;
                            break;
                        case 2:
                            CarInfoService.mDoorInfo.LROpen = 0;
                            CarInfoService.mDoorInfo.RROpen = 0;
                            break;
                        default:
                            CarInfoService.mDoorInfo.LROpen = curDoor.LROpen;
                            CarInfoService.mDoorInfo.RROpen = curDoor.RROpen;
                            break;
                    }
                    if (1 == FtSet.GetTrunk()) {
                        CarInfoService.mDoorInfo.RearOpen = 0;
                    } else {
                        CarInfoService.mDoorInfo.RearOpen = curDoor.RearOpen;
                    }
                    if (1 == FtSet.GetHood()) {
                        CarInfoService.mDoorInfo.HeadOpen = 0;
                    } else {
                        CarInfoService.mDoorInfo.HeadOpen = curDoor.HeadOpen;
                    }
                }
                date[0] = 1;
                date[1] = CarInfoService.mDoorInfo.LFOpen;
                date[2] = CarInfoService.mDoorInfo.RFOpen;
                date[3] = CarInfoService.mDoorInfo.LROpen;
                date[4] = CarInfoService.mDoorInfo.RROpen;
                date[5] = CarInfoService.mDoorInfo.RearOpen;
                date[6] = CarInfoService.mDoorInfo.HeadOpen;
            }
            return date;
        }

        public boolean requestCarIllInfo() throws RemoteException {
            Log.i("yw", "requestCarIllInfo");
            if (Mcu.GetIll() == 1) {
                return true;
            }
            return false;
        }

        public int[] requestCarBaseInfo() throws RemoteException {
            Log.i("yw", "requestCarBaseInfo");
            int[] date = new int[69];
            date[0] = CanFunc.mCarInfo.Avalid;
            date[1] = CanFunc.mCarInfo.SpeedDw;
            date[2] = CanFunc.mCarInfo.Speed;
            date[3] = CanFunc.mCarInfo.Rpm;
            date[4] = CanFunc.mCarInfo.WaterTemp;
            date[5] = CanFunc.mCarInfo.TpmsDw;
            date[6] = CanFunc.mCarInfo.Tpms[0];
            date[7] = CanFunc.mCarInfo.Tpms[1];
            date[8] = CanFunc.mCarInfo.Tpms[2];
            date[9] = CanFunc.mCarInfo.Tpms[3];
            date[10] = CanFunc.mCarInfo.OilDw;
            date[11] = CanFunc.mCarInfo.AveOil;
            date[12] = CanFunc.mCarInfo.InstantOil;
            date[13] = CanFunc.mCarInfo.EndurOil;
            date[14] = CanFunc.mCarInfo.Distance;
            date[15] = CanFunc.mCarInfo.BatV;
            date[16] = CanFunc.mCarInfo.DistanceDw;
            date[17] = CanFunc.mCarInfo.TurnLeft;
            date[18] = CanFunc.mCarInfo.TurnRight;
            date[19] = CanFunc.mCarInfo.DriveSafe;
            date[20] = CanFunc.mCarInfo.HighBeam;
            date[21] = CanFunc.mCarInfo.LowBeam;
            date[22] = CanFunc.mCarInfo.FootBrake;
            date[23] = CanFunc.mCarInfo.HandBrake;
            date[24] = CanFunc.mCarInfo.Stalls;
            date[25] = CanFunc.mCarInfo.Ill;
            date[26] = CanFunc.mCarInfo.WarnLed;
            date[27] = CanFunc.mCarInfo.FrontFogLed;
            date[28] = CanFunc.mCarInfo.RearFogLed;
            date[29] = CanFunc.mCarInfo.Jqmwz;
            date[30] = CanFunc.mCarInfo.Syyl;
            date[31] = CanFunc.mCarInfo.Lqywd;
            date[32] = CanFunc.mCarInfo.Jyyl;
            date[33] = CanFunc.mCarInfo.Dqyl;
            date[34] = CanFunc.mCarInfo.Syyw;
            date[35] = CanFunc.mCarInfo.Zcbz;
            date[36] = CanFunc.mCarInfo.PassengerSafe;
            date[37] = CanFunc.mCarInfo.Bsqyw;
            date[38] = CanFunc.mCarInfo.RearOpen;
            date[39] = CanFunc.mCarInfo.AveSpeed;
            date[40] = CanFunc.mCarInfo.DriveDis1;
            date[41] = CanFunc.mCarInfo.DriveDis2;
            for (int i = 0; i < 17; i++) {
                date[i + 42] = CanFunc.mCarInfo.Vin[i];
            }
            date[59] = CanFunc.mCarInfo.WidthLight;
            date[60] = CanFunc.mCarInfo.WiperSpeed;
            int[] temp = new int[6];
            switch (FtSet.GetFdoor()) {
                case 1:
                    temp[0] = CanFunc.mCarInfo.DoorSta[1];
                    temp[1] = CanFunc.mCarInfo.DoorSta[0];
                    break;
                case 2:
                    temp[0] = 0;
                    temp[1] = 0;
                    break;
                default:
                    temp[0] = CanFunc.mCarInfo.DoorSta[0];
                    temp[1] = CanFunc.mCarInfo.DoorSta[1];
                    break;
            }
            switch (FtSet.GetBdoor()) {
                case 1:
                    temp[2] = CanFunc.mCarInfo.DoorSta[3];
                    temp[3] = CanFunc.mCarInfo.DoorSta[2];
                    break;
                case 2:
                    temp[2] = 0;
                    temp[3] = 0;
                    break;
                default:
                    temp[2] = CanFunc.mCarInfo.DoorSta[2];
                    temp[3] = CanFunc.mCarInfo.DoorSta[3];
                    break;
            }
            if (1 == FtSet.GetTrunk()) {
                temp[4] = 0;
            } else {
                temp[4] = CanFunc.mCarInfo.DoorSta[4];
            }
            if (1 == FtSet.GetHood()) {
                temp[5] = 0;
            } else {
                temp[5] = CanFunc.mCarInfo.DoorSta[5];
            }
            for (int i2 = 0; i2 < 6; i2++) {
                date[i2 + 61] = temp[i2];
            }
            date[67] = CanFunc.mCarInfo.OutTemp;
            date[68] = CanFunc.mCarInfo.OilTemp;
            return date;
        }
    }

    public IBinder onBind(Intent arg0) {
        Log.i("yw", "CarInfo");
        return this.mISettings;
    }
}
