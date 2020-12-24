package com.ts.main.radio;

import android.os.RemoteException;
import com.ts.MainUI.Evc;
import com.ts.main.common.ITsRadioCommon;
import com.ts.main.common.MainSet;
import com.ts.main.common.MainVolume;
import com.ts.main.common.WinShow;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Radio;
import com.yyw.ts70xhw.StSet;
import java.util.ArrayList;
import java.util.List;

public class RadioServiceBinder extends ITsRadioCommon.Stub {
    private char[] mcMemList = new char[16];

    public String GetBand() throws RemoteException {
        if (Radio.GetDisp(2) >= 4) {
            return "AM";
        }
        return "FM";
    }

    public String GetFreq() throws RemoteException {
        int curFreq = Radio.GetDisp(1);
        if (Radio.GetDisp(2) >= 4) {
            return String.format("%d", new Object[]{Integer.valueOf(curFreq)});
        }
        return String.format("%.2f", new Object[]{Float.valueOf(((float) curFreq) / 100.0f)});
    }

    public int getBand() throws RemoteException {
        return Radio.GetDisp(2);
    }

    public int getSelectedMem() throws RemoteException {
        return Radio.GetDisp(3);
    }

    public boolean isNeedUpdate() throws RemoteException {
        if (Radio.GetDispUpdate() == 0) {
            return false;
        }
        return true;
    }

    public int GetRadio_N_Step() throws RemoteException {
        return Radio.GetDisp(5);
    }

    public int GetRadio_T_Step() throws RemoteException {
        return Radio.GetDisp(4);
    }

    public int StepToFreq(int nStep) throws RemoteException {
        return Radio.StepToFreq(nStep);
    }

    public List<String> getMemList() throws RemoteException {
        int mCurBand = Radio.GetDisp(2);
        ArrayList<String> memList = new ArrayList<>();
        if (mCurBand >= 4) {
            for (int i = 25; i < 37; i++) {
                int result = Radio.GetMemList(i, this.mcMemList);
                if (-1 != result) {
                    memList.add(new StringBuilder(String.valueOf(result)).toString());
                }
            }
        } else if (mCurBand == 3) {
            for (int i2 = 19; i2 < 25; i2++) {
                int result2 = Radio.GetMemList(i2, this.mcMemList);
                if (-1 != result2) {
                    memList.add(new StringBuilder(String.valueOf(result2)).toString());
                }
            }
        } else {
            for (int i3 = 1; i3 < 19; i3++) {
                int result3 = Radio.GetMemList(i3, this.mcMemList);
                if (-1 == result3) {
                    memList.add(new String(this.mcMemList, 0, 8));
                } else {
                    memList.add(MainSet.GetInstance().tranalateIntoString(result3));
                }
            }
        }
        return memList;
    }

    public void Raido_TuneMsetEx(int id) throws RemoteException {
        Radio.TuneMsetEx(id);
    }

    public void Raido_TuneMsaveEx(int id) throws RemoteException {
        Radio.TuneMsaveEx(id);
    }

    public void Radio_VolWinShow() throws RemoteException {
        MainVolume.GetInstance().VolWinShow();
    }

    public void Radio_TuneStset() throws RemoteException {
        Radio.TuneStset();
    }

    public void Radio_TuneBandFm() throws RemoteException {
        Radio.TuneBandFm();
    }

    public void Radio_TuneBandAm() throws RemoteException {
        Radio.TuneBandAm();
    }

    public void Radio_TuneInt() throws RemoteException {
        Radio.TuneInt();
    }

    public void Radio_TuneAms() throws RemoteException {
        Radio.TuneAms();
    }

    public void Radio_TurnToEq() throws RemoteException {
        WinShow.TurnToEq();
    }

    public void Radio_TuneBand() throws RemoteException {
        Radio.TuneBand(1);
    }

    public void Radio_TuneSearch(int arg0) throws RemoteException {
        Radio.TuneSearch(arg0);
    }

    public void Radio_TuneStep(int arg0) throws RemoteException {
        Radio.TuneStep(0);
    }

    public void Radio_RdsAf() throws RemoteException {
        Radio.RdsAf();
    }

    public void Radio_RdsTa() throws RemoteException {
        Radio.RdsTa();
    }

    public int Radio_GetDisp(int arg0) throws RemoteException {
        return Radio.GetDisp(arg0);
    }

    public int Radio_GetDispUpdate() throws RemoteException {
        return Radio.GetDispUpdate();
    }

    public int Radio_GetDispFlag() throws RemoteException {
        return Radio.GetDispFlag();
    }

    public void Radio_TuneMsave(int id) throws RemoteException {
        Radio.TuneMsave(id);
    }

    public void Radio_TuneMset(int id) throws RemoteException {
        Radio.TuneMset(id);
    }

    public void Evc_evol_workmode_set(int newmode) throws RemoteException {
        Evc.GetInstance().evol_workmode_set(newmode);
    }

    public String Radio_GetPsName() throws RemoteException {
        char[] mcPs = new char[16];
        Radio.GetPsName(mcPs);
        return new String(mcPs, 0, 15);
    }

    public String Radio_GetMemList(int arg0) throws RemoteException {
        char[] mcMemList2 = new char[16];
        int GetMemList = Radio.GetMemList(arg0, mcMemList2);
        return new String(mcMemList2, 0, 15);
    }

    public int Radio_GetMemListToResult(int arg0) throws RemoteException {
        return Radio.GetMemList(arg0, new char[16]);
    }

    public void Radio_RdsPty(int arg0) throws RemoteException {
        Radio.RdsPty(arg0);
    }

    public void Radio_TuneFset(int arg0) throws RemoteException {
        Radio.TuneFset(arg0);
    }

    public String Radio_GetPtyStr(int arg0) throws RemoteException {
        char[] mcPty = new char[64];
        Radio.GetPtyStr(mcPty, arg0);
        return String.valueOf(mcPty);
    }

    public int FtSet_Init() throws RemoteException {
        return FtSet.Init();
    }

    public int FtSet_GetRDSen() throws RemoteException {
        return FtSet.GetRDSen();
    }

    public int StSet_SetInit() throws RemoteException {
        return StSet.SetInit();
    }

    public String Radio_GetMemPsName(int arg0) throws RemoteException {
        char[] mcFreq = new char[16];
        Radio.GetMemPsName(arg0, mcFreq);
        return new String(mcFreq, 0, 15);
    }
}
