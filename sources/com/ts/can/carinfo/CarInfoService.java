package com.ts.can.carinfo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.can.carinfo.ICarInfoService;
import com.ts.can.faw.t3.rjm.CanFawT3RjmDeal;
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
            date[0] = Can.mCarMsg.Avalid;
            date[1] = Can.mCarMsg.SpeedDw;
            date[2] = Can.mCarMsg.Speed;
            date[3] = Can.mCarMsg.Rpm;
            date[4] = Can.mCarMsg.WaterTemp;
            date[5] = Can.mCarMsg.TpmsDw;
            date[6] = Can.mCarMsg.Tpms[0];
            date[7] = Can.mCarMsg.Tpms[1];
            date[8] = Can.mCarMsg.Tpms[2];
            date[9] = Can.mCarMsg.Tpms[3];
            date[10] = Can.mCarMsg.OilDw;
            date[11] = Can.mCarMsg.AveOil;
            date[12] = Can.mCarMsg.InstantOil;
            date[13] = Can.mCarMsg.EndurOil;
            date[14] = Can.mCarMsg.Distance;
            date[15] = Can.mCarMsg.BatV;
            date[16] = Can.mCarMsg.DistanceDw;
            date[17] = Can.mCarMsg.TurnLeft;
            date[18] = Can.mCarMsg.TurnRight;
            date[19] = Can.mCarMsg.DriveSafe;
            date[20] = Can.mCarMsg.HighBeam;
            date[21] = Can.mCarMsg.LowBeam;
            date[22] = Can.mCarMsg.FootBrake;
            date[23] = Can.mCarMsg.HandBrake;
            date[24] = Can.mCarMsg.Stalls;
            date[25] = Can.mCarMsg.Ill;
            date[26] = Can.mCarMsg.WarnLed;
            date[27] = Can.mCarMsg.FrontFogLed;
            date[28] = Can.mCarMsg.RearFogLed;
            date[29] = Can.mCarMsg.Jqmwz;
            date[30] = Can.mCarMsg.Syyl;
            date[31] = Can.mCarMsg.Lqywd;
            date[32] = Can.mCarMsg.Jyyl;
            date[33] = Can.mCarMsg.Dqyl;
            date[34] = Can.mCarMsg.Syyw;
            date[35] = Can.mCarMsg.Zcbz;
            date[36] = Can.mCarMsg.PassengerSafe;
            date[37] = Can.mCarMsg.Bsqyw;
            date[38] = Can.mCarMsg.RearOpen;
            date[39] = Can.mCarMsg.AveSpeed;
            date[40] = Can.mCarMsg.DriveDis1;
            date[41] = Can.mCarMsg.DriveDis2;
            for (int i = 0; i < 17; i++) {
                date[i + 42] = Can.mCarMsg.Vin[i];
            }
            date[59] = Can.mCarMsg.WidthLight;
            date[60] = Can.mCarMsg.WiperSpeed;
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
            for (int i2 = 0; i2 < 6; i2++) {
                date[i2 + 61] = temp[i2];
            }
            date[67] = Can.mCarMsg.OutTemp;
            date[68] = Can.mCarMsg.OilTemp;
            return date;
        }

        public int[] requestT3FlDevInfo() throws RemoteException {
            return new int[]{CanFawT3RjmDeal.mT3FlDevInfo.GPS, CanFawT3RjmDeal.mT3FlDevInfo.ANT_4G1, CanFawT3RjmDeal.mT3FlDevInfo.ANT_4G2, CanFawT3RjmDeal.mT3FlDevInfo.KEY1_STA, CanFawT3RjmDeal.mT3FlDevInfo.KEY2_STA, CanFawT3RjmDeal.mT3FlDevInfo.KEY3_STA, CanFawT3RjmDeal.mT3FlDevInfo.KEY4_STA, CanFawT3RjmDeal.mT3FlDevInfo.BAT, CanFawT3RjmDeal.mT3FlDevInfo.CAN1, CanFawT3RjmDeal.mT3FlDevInfo.CAN2};
        }

        public int requestT3FlSta() throws RemoteException {
            return CanFawT3RjmDeal.mT3FlSta.Trailer;
        }

        public int[] requestT3FlCanData7f1() throws RemoteException {
            return CanFawT3RjmDeal.mT3FlCanDat_7f1.Data;
        }

        public int[] requestT3FlCanData7f2() throws RemoteException {
            CanFawT3RjmDeal.mT3FlCanDat_7f2.Data[2] = CanFawT3RjmDeal.mT3FlSpeed.Val[0];
            CanFawT3RjmDeal.mT3FlCanDat_7f2.Data[3] = CanFawT3RjmDeal.mT3FlSpeed.Val[1];
            return CanFawT3RjmDeal.mT3FlCanDat_7f2.Data;
        }

        public int[] requestT3FlCanData7f3() throws RemoteException {
            return CanFawT3RjmDeal.mT3FlCanDat_7f3.Data;
        }

        public int[] requestT3FlCanData7f4() throws RemoteException {
            return CanFawT3RjmDeal.mT3FlCanDat_7f4.Data;
        }

        public int[] requestT3FlCanData7e0() throws RemoteException {
            return CanFawT3RjmDeal.mT3FlCanDat_7e0.Data;
        }

        public int[] requestT3FlTexlData() throws RemoteException {
            int[] date = new int[60];
            date[0] = CanFawT3RjmDeal.mT3FlTexlData.WorkMode;
            date[1] = CanFawT3RjmDeal.mT3FlTexlData.CalcSta;
            date[2] = CanFawT3RjmDeal.mT3FlTexlData.Connect;
            date[3] = CanFawT3RjmDeal.mT3FlTexlData.DdxjxsRet;
            date[4] = CanFawT3RjmDeal.mT3FlTexlData.WycDyzlRet;
            for (int i = 0; i < 45; i++) {
                date[i + 5] = CanFawT3RjmDeal.mT3FlTexlData.Fbyyxx[i];
            }
            return date;
        }

        public int T3FlTexlCmd(int type, int[] cmd) throws RemoteException {
            switch (type) {
                case 1:
                    CanFawT3RjmDeal.mT3FlTexlCmd[0] = 1;
                    CanFawT3RjmDeal.mT3FlTexlCmd[1] = 1;
                    CanFawT3RjmDeal.mT3FlTexlCmd[2] = cmd[0];
                    break;
                case 2:
                    CanFawT3RjmDeal.mT3FlTexlCmd[10] = 1;
                    CanFawT3RjmDeal.mT3FlTexlCmd[11] = 2;
                    CanFawT3RjmDeal.mT3FlTexlCmd[12] = cmd[0];
                    break;
                case 4:
                    CanFawT3RjmDeal.mT3FlTexlCmd[20] = 1;
                    CanFawT3RjmDeal.mT3FlTexlCmd[21] = 4;
                    CanFawT3RjmDeal.mT3FlTexlCmd[22] = cmd[0];
                    break;
                case 5:
                    CanFawT3RjmDeal.mT3FlTexlCmd[30] = 1;
                    CanFawT3RjmDeal.mT3FlTexlCmd[31] = 5;
                    break;
                case 6:
                    CanFawT3RjmDeal.mT3FlTexlCmd[40] = 1;
                    CanFawT3RjmDeal.mT3FlTexlCmd[41] = 6;
                    CanFawT3RjmDeal.mT3FlTexlCmd[42] = cmd[0];
                    CanFawT3RjmDeal.mT3FlTexlCmd[43] = cmd[1];
                    CanFawT3RjmDeal.mT3FlTexlCmd[44] = cmd[2];
                    CanFawT3RjmDeal.mT3FlTexlCmd[45] = cmd[3];
                    break;
                case 7:
                    CanFawT3RjmDeal.mT3FlTexlCmd[50] = 1;
                    CanFawT3RjmDeal.mT3FlTexlCmd[51] = 7;
                    CanFawT3RjmDeal.mT3FlTexlCmd[52] = cmd[0];
                    break;
                case 8:
                    CanFawT3RjmDeal.mT3FlTexlCmd[60] = 1;
                    CanFawT3RjmDeal.mT3FlTexlCmd[61] = 8;
                    for (byte i = 0; i < 35; i = (byte) (i + 1)) {
                        CanFawT3RjmDeal.mT3FlTexlCmd[i + 62] = cmd[i];
                    }
                    break;
                case 9:
                    CanFawT3RjmDeal.mT3FlTexlCmd[110] = 1;
                    CanFawT3RjmDeal.mT3FlTexlCmd[111] = 9;
                    CanFawT3RjmDeal.mT3FlTexlCmd[112] = cmd[0];
                    break;
            }
            return 1;
        }

        public int[] requestT3FlTexlDisCur() throws RemoteException {
            int[] date = new int[30];
            if (CanFawT3RjmDeal.mT3FlTexlUpt[0] > 0) {
                date[0] = 1;
                CanFawT3RjmDeal.mT3FlTexlUpt[0] = 0;
            } else {
                date[0] = 0;
            }
            for (int i = 0; i < 25; i++) {
                date[i + 1] = CanFawT3RjmDeal.mT3FlTexlDisCur.DisMsg[i];
            }
            return date;
        }

        public int[] requestT3FlTexlDisOver() throws RemoteException {
            int[] date = new int[40];
            if (CanFawT3RjmDeal.mT3FlTexlUpt[1] > 0) {
                date[0] = 1;
                CanFawT3RjmDeal.mT3FlTexlUpt[1] = 0;
            } else {
                date[0] = 0;
            }
            for (int i = 0; i < 35; i++) {
                date[i + 1] = CanFawT3RjmDeal.mT3FlTexlDisOver.DisDetail[i];
            }
            return date;
        }

        public int[] requestT3FlTexlPjxx() throws RemoteException {
            int[] date = new int[10];
            if (CanFawT3RjmDeal.mT3FlTexlUpt[2] > 0) {
                date[0] = 1;
                CanFawT3RjmDeal.mT3FlTexlUpt[2] = 0;
            } else {
                date[0] = 0;
            }
            date[1] = CanFawT3RjmDeal.mT3FlTexlPjxx.PjxxRet;
            return date;
        }
    }

    public IBinder onBind(Intent arg0) {
        Log.i("yw", "CarInfo");
        return this.mISettings;
    }
}
