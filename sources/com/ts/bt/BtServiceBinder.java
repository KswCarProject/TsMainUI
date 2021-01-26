package com.ts.bt;

import android.os.RemoteException;
import com.ts.bt.ITsBtService;
import java.util.List;

public class BtServiceBinder extends ITsBtService.Stub {
    private BtExe bt = BtExe.getBtInstance();

    public String getBtDevName() throws RemoteException {
        return this.bt.getDevName();
    }

    public String getBtPhoneName() throws RemoteException {
        return this.bt.getPhoneName();
    }

    public List<PbInfo> getPbInfo() throws RemoteException {
        return this.bt.getPbInfo();
    }

    public void connect() throws RemoteException {
        this.bt.connect();
    }

    public void disconnect() throws RemoteException {
        this.bt.disconnect();
    }

    public void dial(String callNumber) throws RemoteException {
        this.bt.dial(callNumber);
    }

    public void hangup() throws RemoteException {
        this.bt.hangup();
    }

    public void answer() throws RemoteException {
        this.bt.answer();
    }

    public void audioSwitch() throws RemoteException {
        this.bt.audioSwitch();
    }

    public void downLoad() throws RemoteException {
        this.bt.downLoad();
    }

    public void micMute() throws RemoteException {
        this.bt.micMute();
    }

    public void sendDTMFCode(byte code) throws RemoteException {
        this.bt.sendDTMFCode(code);
    }

    public int getSta() throws RemoteException {
        return this.bt.getSta();
    }

    public String getLastPhoneNo() throws RemoteException {
        return this.bt.getLastPhoneNo();
    }

    public boolean isConnected() throws RemoteException {
        return this.bt.isConnected();
    }

    public void updateLastPhoneNum() throws RemoteException {
        this.bt.updateLastPhoneNum();
    }

    public String getBtDevPin() throws RemoteException {
        return this.bt.getDevPin();
    }
}
