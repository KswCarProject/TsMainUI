package com.ts.main.common;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.Map;

public interface ITsCommon extends IInterface {
    void BklTurn() throws RemoteException;

    void BtDail(String str) throws RemoteException;

    boolean BtIsConnect() throws RemoteException;

    void EnterActivity(int i) throws RemoteException;

    void EnterMode(int i) throws RemoteException;

    String GetBand() throws RemoteException;

    int GetBrakeState() throws RemoteException;

    float GetCog() throws RemoteException;

    int GetCurTime() throws RemoteException;

    String GetDeviceID() throws RemoteException;

    String GetFreq() throws RemoteException;

    String GetIMEI() throws RemoteException;

    String GetIMSI() throws RemoteException;

    String GetId3Album() throws RemoteException;

    String GetId3Artist() throws RemoteException;

    String GetId3Title() throws RemoteException;

    Map GetListBt() throws RemoteException;

    Map GetListMedia() throws RemoteException;

    int GetMcuPowerState() throws RemoteException;

    int GetPlayState() throws RemoteException;

    boolean GetRadioSTState() throws RemoteException;

    boolean GetRadioSTSwitch() throws RemoteException;

    int GetRadio_N_Step() throws RemoteException;

    int GetRadio_T_Step() throws RemoteException;

    int GetReverState() throws RemoteException;

    int GetSDCard() throws RemoteException;

    String GetSongName() throws RemoteException;

    float GetSpeed() throws RemoteException;

    String GetTemp() throws RemoteException;

    int GetTotalTime() throws RemoteException;

    void GotoEq() throws RemoteException;

    int IsHaveDisc() throws RemoteException;

    boolean IsLastMemory() throws RemoteException;

    boolean IsMute() throws RemoteException;

    boolean IsNightMode() throws RemoteException;

    boolean IsTconAdj() throws RemoteException;

    void Mute() throws RemoteException;

    void PlayByPath(String str) throws RemoteException;

    void PopMuteClear(int i) throws RemoteException;

    void PopMuteSet(int i) throws RemoteException;

    void SendMcuKey(int i) throws RemoteException;

    int StepToFreq(int i) throws RemoteException;

    void TconDvdSetShow() throws RemoteException;

    void TconSet(int i) throws RemoteException;

    void TconSetHide() throws RemoteException;

    void TconVideoSetShow() throws RemoteException;

    void TsVolumeShow() throws RemoteException;

    void VolDec() throws RemoteException;

    void VolInc() throws RemoteException;

    void VolSet(int i) throws RemoteException;

    int getRepeatMode() throws RemoteException;

    int getShuffleMode() throws RemoteException;

    IBinder getSpecialBinder(String str) throws RemoteException;

    int nGetKey() throws RemoteException;

    int nGetWorkMode() throws RemoteException;

    public static abstract class Stub extends Binder implements ITsCommon {
        private static final String DESCRIPTOR = "com.ts.main.common.ITsCommon";
        static final int TRANSACTION_BklTurn = 22;
        static final int TRANSACTION_BtDail = 40;
        static final int TRANSACTION_BtIsConnect = 12;
        static final int TRANSACTION_EnterActivity = 36;
        static final int TRANSACTION_EnterMode = 1;
        static final int TRANSACTION_GetBand = 33;
        static final int TRANSACTION_GetBrakeState = 9;
        static final int TRANSACTION_GetCog = 25;
        static final int TRANSACTION_GetCurTime = 26;
        static final int TRANSACTION_GetDeviceID = 53;
        static final int TRANSACTION_GetFreq = 34;
        static final int TRANSACTION_GetIMEI = 51;
        static final int TRANSACTION_GetIMSI = 52;
        static final int TRANSACTION_GetId3Album = 30;
        static final int TRANSACTION_GetId3Artist = 31;
        static final int TRANSACTION_GetId3Title = 32;
        static final int TRANSACTION_GetListBt = 37;
        static final int TRANSACTION_GetListMedia = 38;
        static final int TRANSACTION_GetMcuPowerState = 54;
        static final int TRANSACTION_GetPlayState = 28;
        static final int TRANSACTION_GetRadioSTState = 49;
        static final int TRANSACTION_GetRadioSTSwitch = 50;
        static final int TRANSACTION_GetRadio_N_Step = 43;
        static final int TRANSACTION_GetRadio_T_Step = 44;
        static final int TRANSACTION_GetReverState = 8;
        static final int TRANSACTION_GetSDCard = 35;
        static final int TRANSACTION_GetSongName = 29;
        static final int TRANSACTION_GetSpeed = 23;
        static final int TRANSACTION_GetTemp = 24;
        static final int TRANSACTION_GetTotalTime = 27;
        static final int TRANSACTION_GotoEq = 7;
        static final int TRANSACTION_IsHaveDisc = 11;
        static final int TRANSACTION_IsLastMemory = 16;
        static final int TRANSACTION_IsMute = 13;
        static final int TRANSACTION_IsNightMode = 14;
        static final int TRANSACTION_IsTconAdj = 15;
        static final int TRANSACTION_Mute = 2;
        static final int TRANSACTION_PlayByPath = 39;
        static final int TRANSACTION_PopMuteClear = 42;
        static final int TRANSACTION_PopMuteSet = 41;
        static final int TRANSACTION_SendMcuKey = 6;
        static final int TRANSACTION_StepToFreq = 45;
        static final int TRANSACTION_TconDvdSetShow = 17;
        static final int TRANSACTION_TconSet = 20;
        static final int TRANSACTION_TconSetHide = 19;
        static final int TRANSACTION_TconVideoSetShow = 18;
        static final int TRANSACTION_TsVolumeShow = 21;
        static final int TRANSACTION_VolDec = 4;
        static final int TRANSACTION_VolInc = 3;
        static final int TRANSACTION_VolSet = 5;
        static final int TRANSACTION_getRepeatMode = 47;
        static final int TRANSACTION_getShuffleMode = 48;
        static final int TRANSACTION_getSpecialBinder = 55;
        static final int TRANSACTION_nGetKey = 46;
        static final int TRANSACTION_nGetWorkMode = 10;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITsCommon asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ITsCommon)) {
                return new Proxy(obj);
            }
            return (ITsCommon) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            int i = 0;
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    EnterMode(data.readInt());
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    Mute();
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    VolInc();
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    VolDec();
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    VolSet(data.readInt());
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    SendMcuKey(data.readInt());
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    GotoEq();
                    reply.writeNoException();
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    int _result = GetReverState();
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    int _result2 = GetBrakeState();
                    reply.writeNoException();
                    reply.writeInt(_result2);
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    int _result3 = nGetWorkMode();
                    reply.writeNoException();
                    reply.writeInt(_result3);
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    int _result4 = IsHaveDisc();
                    reply.writeNoException();
                    reply.writeInt(_result4);
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result5 = BtIsConnect();
                    reply.writeNoException();
                    if (_result5) {
                        i = 1;
                    }
                    reply.writeInt(i);
                    return true;
                case 13:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result6 = IsMute();
                    reply.writeNoException();
                    if (_result6) {
                        i = 1;
                    }
                    reply.writeInt(i);
                    return true;
                case 14:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result7 = IsNightMode();
                    reply.writeNoException();
                    if (_result7) {
                        i = 1;
                    }
                    reply.writeInt(i);
                    return true;
                case 15:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result8 = IsTconAdj();
                    reply.writeNoException();
                    if (_result8) {
                        i = 1;
                    }
                    reply.writeInt(i);
                    return true;
                case 16:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result9 = IsLastMemory();
                    reply.writeNoException();
                    if (_result9) {
                        i = 1;
                    }
                    reply.writeInt(i);
                    return true;
                case 17:
                    data.enforceInterface(DESCRIPTOR);
                    TconDvdSetShow();
                    reply.writeNoException();
                    return true;
                case 18:
                    data.enforceInterface(DESCRIPTOR);
                    TconVideoSetShow();
                    reply.writeNoException();
                    return true;
                case 19:
                    data.enforceInterface(DESCRIPTOR);
                    TconSetHide();
                    reply.writeNoException();
                    return true;
                case 20:
                    data.enforceInterface(DESCRIPTOR);
                    TconSet(data.readInt());
                    reply.writeNoException();
                    return true;
                case 21:
                    data.enforceInterface(DESCRIPTOR);
                    TsVolumeShow();
                    reply.writeNoException();
                    return true;
                case 22:
                    data.enforceInterface(DESCRIPTOR);
                    BklTurn();
                    reply.writeNoException();
                    return true;
                case 23:
                    data.enforceInterface(DESCRIPTOR);
                    float _result10 = GetSpeed();
                    reply.writeNoException();
                    reply.writeFloat(_result10);
                    return true;
                case 24:
                    data.enforceInterface(DESCRIPTOR);
                    String _result11 = GetTemp();
                    reply.writeNoException();
                    reply.writeString(_result11);
                    return true;
                case 25:
                    data.enforceInterface(DESCRIPTOR);
                    float _result12 = GetCog();
                    reply.writeNoException();
                    reply.writeFloat(_result12);
                    return true;
                case 26:
                    data.enforceInterface(DESCRIPTOR);
                    int _result13 = GetCurTime();
                    reply.writeNoException();
                    reply.writeInt(_result13);
                    return true;
                case 27:
                    data.enforceInterface(DESCRIPTOR);
                    int _result14 = GetTotalTime();
                    reply.writeNoException();
                    reply.writeInt(_result14);
                    return true;
                case 28:
                    data.enforceInterface(DESCRIPTOR);
                    int _result15 = GetPlayState();
                    reply.writeNoException();
                    reply.writeInt(_result15);
                    return true;
                case 29:
                    data.enforceInterface(DESCRIPTOR);
                    String _result16 = GetSongName();
                    reply.writeNoException();
                    reply.writeString(_result16);
                    return true;
                case 30:
                    data.enforceInterface(DESCRIPTOR);
                    String _result17 = GetId3Album();
                    reply.writeNoException();
                    reply.writeString(_result17);
                    return true;
                case 31:
                    data.enforceInterface(DESCRIPTOR);
                    String _result18 = GetId3Artist();
                    reply.writeNoException();
                    reply.writeString(_result18);
                    return true;
                case 32:
                    data.enforceInterface(DESCRIPTOR);
                    String _result19 = GetId3Title();
                    reply.writeNoException();
                    reply.writeString(_result19);
                    return true;
                case 33:
                    data.enforceInterface(DESCRIPTOR);
                    String _result20 = GetBand();
                    reply.writeNoException();
                    reply.writeString(_result20);
                    return true;
                case 34:
                    data.enforceInterface(DESCRIPTOR);
                    String _result21 = GetFreq();
                    reply.writeNoException();
                    reply.writeString(_result21);
                    return true;
                case 35:
                    data.enforceInterface(DESCRIPTOR);
                    int _result22 = GetSDCard();
                    reply.writeNoException();
                    reply.writeInt(_result22);
                    return true;
                case 36:
                    data.enforceInterface(DESCRIPTOR);
                    EnterActivity(data.readInt());
                    reply.writeNoException();
                    return true;
                case 37:
                    data.enforceInterface(DESCRIPTOR);
                    Map _result23 = GetListBt();
                    reply.writeNoException();
                    reply.writeMap(_result23);
                    return true;
                case 38:
                    data.enforceInterface(DESCRIPTOR);
                    Map _result24 = GetListMedia();
                    reply.writeNoException();
                    reply.writeMap(_result24);
                    return true;
                case 39:
                    data.enforceInterface(DESCRIPTOR);
                    PlayByPath(data.readString());
                    reply.writeNoException();
                    return true;
                case 40:
                    data.enforceInterface(DESCRIPTOR);
                    BtDail(data.readString());
                    reply.writeNoException();
                    return true;
                case 41:
                    data.enforceInterface(DESCRIPTOR);
                    PopMuteSet(data.readInt());
                    reply.writeNoException();
                    return true;
                case 42:
                    data.enforceInterface(DESCRIPTOR);
                    PopMuteClear(data.readInt());
                    reply.writeNoException();
                    return true;
                case 43:
                    data.enforceInterface(DESCRIPTOR);
                    int _result25 = GetRadio_N_Step();
                    reply.writeNoException();
                    reply.writeInt(_result25);
                    return true;
                case 44:
                    data.enforceInterface(DESCRIPTOR);
                    int _result26 = GetRadio_T_Step();
                    reply.writeNoException();
                    reply.writeInt(_result26);
                    return true;
                case 45:
                    data.enforceInterface(DESCRIPTOR);
                    int _result27 = StepToFreq(data.readInt());
                    reply.writeNoException();
                    reply.writeInt(_result27);
                    return true;
                case 46:
                    data.enforceInterface(DESCRIPTOR);
                    int _result28 = nGetKey();
                    reply.writeNoException();
                    reply.writeInt(_result28);
                    return true;
                case 47:
                    data.enforceInterface(DESCRIPTOR);
                    int _result29 = getRepeatMode();
                    reply.writeNoException();
                    reply.writeInt(_result29);
                    return true;
                case 48:
                    data.enforceInterface(DESCRIPTOR);
                    int _result30 = getShuffleMode();
                    reply.writeNoException();
                    reply.writeInt(_result30);
                    return true;
                case 49:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result31 = GetRadioSTState();
                    reply.writeNoException();
                    if (_result31) {
                        i = 1;
                    }
                    reply.writeInt(i);
                    return true;
                case 50:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result32 = GetRadioSTSwitch();
                    reply.writeNoException();
                    if (_result32) {
                        i = 1;
                    }
                    reply.writeInt(i);
                    return true;
                case 51:
                    data.enforceInterface(DESCRIPTOR);
                    String _result33 = GetIMEI();
                    reply.writeNoException();
                    reply.writeString(_result33);
                    return true;
                case 52:
                    data.enforceInterface(DESCRIPTOR);
                    String _result34 = GetIMSI();
                    reply.writeNoException();
                    reply.writeString(_result34);
                    return true;
                case 53:
                    data.enforceInterface(DESCRIPTOR);
                    String _result35 = GetDeviceID();
                    reply.writeNoException();
                    reply.writeString(_result35);
                    return true;
                case 54:
                    data.enforceInterface(DESCRIPTOR);
                    int _result36 = GetMcuPowerState();
                    reply.writeNoException();
                    reply.writeInt(_result36);
                    return true;
                case 55:
                    data.enforceInterface(DESCRIPTOR);
                    IBinder _result37 = getSpecialBinder(data.readString());
                    reply.writeNoException();
                    reply.writeStrongBinder(_result37);
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements ITsCommon {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            public void EnterMode(int nMode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(nMode);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void Mute() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void VolInc() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void VolDec() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void VolSet(int nVol) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(nVol);
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void SendMcuKey(int nKey) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(nKey);
                    this.mRemote.transact(6, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void GotoEq() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(7, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int GetReverState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(8, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int GetBrakeState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(9, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int nGetWorkMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(10, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int IsHaveDisc() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(11, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean BtIsConnect() throws RemoteException {
                boolean _result = false;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(12, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean IsMute() throws RemoteException {
                boolean _result = false;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(13, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean IsNightMode() throws RemoteException {
                boolean _result = false;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(14, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean IsTconAdj() throws RemoteException {
                boolean _result = false;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(15, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean IsLastMemory() throws RemoteException {
                boolean _result = false;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(16, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void TconDvdSetShow() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(17, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void TconVideoSetShow() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(18, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void TconSetHide() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(19, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void TconSet(int nMode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(nMode);
                    this.mRemote.transact(20, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void TsVolumeShow() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(21, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void BklTurn() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(22, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public float GetSpeed() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(23, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readFloat();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String GetTemp() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(24, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public float GetCog() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(25, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readFloat();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int GetCurTime() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(26, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int GetTotalTime() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(27, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int GetPlayState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(28, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String GetSongName() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(29, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String GetId3Album() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(30, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String GetId3Artist() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(31, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String GetId3Title() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(32, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String GetBand() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(33, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String GetFreq() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(34, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int GetSDCard() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(35, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void EnterActivity(int nMode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(nMode);
                    this.mRemote.transact(36, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public Map GetListBt() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(37, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readHashMap(getClass().getClassLoader());
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public Map GetListMedia() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(38, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readHashMap(getClass().getClassLoader());
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void PlayByPath(String path) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(path);
                    this.mRemote.transact(39, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void BtDail(String Number) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(Number);
                    this.mRemote.transact(40, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void PopMuteSet(int nMode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(nMode);
                    this.mRemote.transact(41, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void PopMuteClear(int nMode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(nMode);
                    this.mRemote.transact(42, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int GetRadio_N_Step() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(43, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int GetRadio_T_Step() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(44, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int StepToFreq(int nStep) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(nStep);
                    this.mRemote.transact(45, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int nGetKey() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(46, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int getRepeatMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(47, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int getShuffleMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(48, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean GetRadioSTState() throws RemoteException {
                boolean _result = false;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(49, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean GetRadioSTSwitch() throws RemoteException {
                boolean _result = false;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(50, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String GetIMEI() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(51, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String GetIMSI() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(52, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String GetDeviceID() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(53, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int GetMcuPowerState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(54, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public IBinder getSpecialBinder(String name) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(name);
                    this.mRemote.transact(55, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readStrongBinder();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
