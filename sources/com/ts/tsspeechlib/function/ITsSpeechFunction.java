package com.ts.tsspeechlib.function;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ts.tsspeechlib.function.ITsFunctionCallback;

public interface ITsSpeechFunction extends IInterface {
    void CloseMainUIApp(int i) throws RemoteException;

    int GetCameraType() throws RemoteException;

    int GetCanType() throws RemoteException;

    String GetDeviceID() throws RemoteException;

    int GetRadioIC() throws RemoteException;

    void KillProcess(String str) throws RemoteException;

    void OpenMainUIApp(int i, int i2) throws RemoteException;

    void SendKey(int i) throws RemoteException;

    String getBranch() throws RemoteException;

    int getCurrentBrightness() throws RemoteException;

    int getCurrentVolume() throws RemoteException;

    String getDeviceType() throws RemoteException;

    String getHMI() throws RemoteException;

    int getMaxBrightnes() throws RemoteException;

    int getMaxVolume() throws RemoteException;

    int getMinBrightnes() throws RemoteException;

    int getMinVolume() throws RemoteException;

    int getWorkMode() throws RemoteException;

    int isBackCar() throws RemoteException;

    int isIllOn() throws RemoteException;

    boolean isScreenOpen() throws RemoteException;

    boolean isVolumeMute() throws RemoteException;

    void onBrightenScreen() throws RemoteException;

    void onCloseScreen() throws RemoteException;

    void onDimmingScreen() throws RemoteException;

    void onOpenScreen() throws RemoteException;

    void onScreenBrightest() throws RemoteException;

    void onScreenDarkest() throws RemoteException;

    void onVolumeDown() throws RemoteException;

    void onVolumeMax() throws RemoteException;

    void onVolumeMin() throws RemoteException;

    void onVolumeMute() throws RemoteException;

    void onVolumeUnmute() throws RemoteException;

    void onVolumeUp() throws RemoteException;

    void openAvin() throws RemoteException;

    void openCarInfo() throws RemoteException;

    void openSetting() throws RemoteException;

    void setFunctionCallback(ITsFunctionCallback iTsFunctionCallback) throws RemoteException;

    void setScreenBrightness(int i) throws RemoteException;

    void setVolume(int i) throws RemoteException;

    void showLauncher() throws RemoteException;

    void speechStartRecognition() throws RemoteException;

    void speechStartTTS() throws RemoteException;

    void speechStopRecognition() throws RemoteException;

    void speechStopTTS() throws RemoteException;

    void supportFastCharging(int i) throws RemoteException;

    int withBrakes() throws RemoteException;

    public static abstract class Stub extends Binder implements ITsSpeechFunction {
        private static final String DESCRIPTOR = "com.ts.tsspeechlib.function.ITsSpeechFunction";
        static final int TRANSACTION_CloseMainUIApp = 46;
        static final int TRANSACTION_GetCameraType = 42;
        static final int TRANSACTION_GetCanType = 41;
        static final int TRANSACTION_GetDeviceID = 47;
        static final int TRANSACTION_GetRadioIC = 40;
        static final int TRANSACTION_KillProcess = 44;
        static final int TRANSACTION_OpenMainUIApp = 45;
        static final int TRANSACTION_SendKey = 43;
        static final int TRANSACTION_getBranch = 39;
        static final int TRANSACTION_getCurrentBrightness = 4;
        static final int TRANSACTION_getCurrentVolume = 11;
        static final int TRANSACTION_getDeviceType = 37;
        static final int TRANSACTION_getHMI = 38;
        static final int TRANSACTION_getMaxBrightnes = 6;
        static final int TRANSACTION_getMaxVolume = 13;
        static final int TRANSACTION_getMinBrightnes = 5;
        static final int TRANSACTION_getMinVolume = 12;
        static final int TRANSACTION_getWorkMode = 22;
        static final int TRANSACTION_isBackCar = 35;
        static final int TRANSACTION_isIllOn = 34;
        static final int TRANSACTION_isScreenOpen = 1;
        static final int TRANSACTION_isVolumeMute = 18;
        static final int TRANSACTION_onBrightenScreen = 7;
        static final int TRANSACTION_onCloseScreen = 3;
        static final int TRANSACTION_onDimmingScreen = 8;
        static final int TRANSACTION_onOpenScreen = 2;
        static final int TRANSACTION_onScreenBrightest = 9;
        static final int TRANSACTION_onScreenDarkest = 10;
        static final int TRANSACTION_onVolumeDown = 15;
        static final int TRANSACTION_onVolumeMax = 16;
        static final int TRANSACTION_onVolumeMin = 17;
        static final int TRANSACTION_onVolumeMute = 19;
        static final int TRANSACTION_onVolumeUnmute = 20;
        static final int TRANSACTION_onVolumeUp = 14;
        static final int TRANSACTION_openAvin = 24;
        static final int TRANSACTION_openCarInfo = 25;
        static final int TRANSACTION_openSetting = 23;
        static final int TRANSACTION_setFunctionCallback = 33;
        static final int TRANSACTION_setScreenBrightness = 31;
        static final int TRANSACTION_setVolume = 30;
        static final int TRANSACTION_showLauncher = 21;
        static final int TRANSACTION_speechStartRecognition = 28;
        static final int TRANSACTION_speechStartTTS = 26;
        static final int TRANSACTION_speechStopRecognition = 29;
        static final int TRANSACTION_speechStopTTS = 27;
        static final int TRANSACTION_supportFastCharging = 32;
        static final int TRANSACTION_withBrakes = 36;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITsSpeechFunction asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ITsSpeechFunction)) {
                return new Proxy(obj);
            }
            return (ITsSpeechFunction) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            int i = 0;
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result = isScreenOpen();
                    reply.writeNoException();
                    if (_result) {
                        i = 1;
                    }
                    reply.writeInt(i);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    onOpenScreen();
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    onCloseScreen();
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    int _result2 = getCurrentBrightness();
                    reply.writeNoException();
                    reply.writeInt(_result2);
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    int _result3 = getMinBrightnes();
                    reply.writeNoException();
                    reply.writeInt(_result3);
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    int _result4 = getMaxBrightnes();
                    reply.writeNoException();
                    reply.writeInt(_result4);
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    onBrightenScreen();
                    reply.writeNoException();
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    onDimmingScreen();
                    reply.writeNoException();
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    onScreenBrightest();
                    reply.writeNoException();
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    onScreenDarkest();
                    reply.writeNoException();
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    int _result5 = getCurrentVolume();
                    reply.writeNoException();
                    reply.writeInt(_result5);
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    int _result6 = getMinVolume();
                    reply.writeNoException();
                    reply.writeInt(_result6);
                    return true;
                case 13:
                    data.enforceInterface(DESCRIPTOR);
                    int _result7 = getMaxVolume();
                    reply.writeNoException();
                    reply.writeInt(_result7);
                    return true;
                case 14:
                    data.enforceInterface(DESCRIPTOR);
                    onVolumeUp();
                    reply.writeNoException();
                    return true;
                case 15:
                    data.enforceInterface(DESCRIPTOR);
                    onVolumeDown();
                    reply.writeNoException();
                    return true;
                case 16:
                    data.enforceInterface(DESCRIPTOR);
                    onVolumeMax();
                    reply.writeNoException();
                    return true;
                case 17:
                    data.enforceInterface(DESCRIPTOR);
                    onVolumeMin();
                    reply.writeNoException();
                    return true;
                case 18:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result8 = isVolumeMute();
                    reply.writeNoException();
                    if (_result8) {
                        i = 1;
                    }
                    reply.writeInt(i);
                    return true;
                case 19:
                    data.enforceInterface(DESCRIPTOR);
                    onVolumeMute();
                    reply.writeNoException();
                    return true;
                case 20:
                    data.enforceInterface(DESCRIPTOR);
                    onVolumeUnmute();
                    reply.writeNoException();
                    return true;
                case 21:
                    data.enforceInterface(DESCRIPTOR);
                    showLauncher();
                    reply.writeNoException();
                    return true;
                case 22:
                    data.enforceInterface(DESCRIPTOR);
                    int _result9 = getWorkMode();
                    reply.writeNoException();
                    reply.writeInt(_result9);
                    return true;
                case 23:
                    data.enforceInterface(DESCRIPTOR);
                    openSetting();
                    reply.writeNoException();
                    return true;
                case 24:
                    data.enforceInterface(DESCRIPTOR);
                    openAvin();
                    reply.writeNoException();
                    return true;
                case 25:
                    data.enforceInterface(DESCRIPTOR);
                    openCarInfo();
                    reply.writeNoException();
                    return true;
                case 26:
                    data.enforceInterface(DESCRIPTOR);
                    speechStartTTS();
                    reply.writeNoException();
                    return true;
                case 27:
                    data.enforceInterface(DESCRIPTOR);
                    speechStopTTS();
                    reply.writeNoException();
                    return true;
                case 28:
                    data.enforceInterface(DESCRIPTOR);
                    speechStartRecognition();
                    reply.writeNoException();
                    return true;
                case 29:
                    data.enforceInterface(DESCRIPTOR);
                    speechStopRecognition();
                    reply.writeNoException();
                    return true;
                case 30:
                    data.enforceInterface(DESCRIPTOR);
                    setVolume(data.readInt());
                    reply.writeNoException();
                    return true;
                case 31:
                    data.enforceInterface(DESCRIPTOR);
                    setScreenBrightness(data.readInt());
                    reply.writeNoException();
                    return true;
                case 32:
                    data.enforceInterface(DESCRIPTOR);
                    supportFastCharging(data.readInt());
                    reply.writeNoException();
                    return true;
                case 33:
                    data.enforceInterface(DESCRIPTOR);
                    setFunctionCallback(ITsFunctionCallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 34:
                    data.enforceInterface(DESCRIPTOR);
                    int _result10 = isIllOn();
                    reply.writeNoException();
                    reply.writeInt(_result10);
                    return true;
                case 35:
                    data.enforceInterface(DESCRIPTOR);
                    int _result11 = isBackCar();
                    reply.writeNoException();
                    reply.writeInt(_result11);
                    return true;
                case 36:
                    data.enforceInterface(DESCRIPTOR);
                    int _result12 = withBrakes();
                    reply.writeNoException();
                    reply.writeInt(_result12);
                    return true;
                case 37:
                    data.enforceInterface(DESCRIPTOR);
                    String _result13 = getDeviceType();
                    reply.writeNoException();
                    reply.writeString(_result13);
                    return true;
                case 38:
                    data.enforceInterface(DESCRIPTOR);
                    String _result14 = getHMI();
                    reply.writeNoException();
                    reply.writeString(_result14);
                    return true;
                case 39:
                    data.enforceInterface(DESCRIPTOR);
                    String _result15 = getBranch();
                    reply.writeNoException();
                    reply.writeString(_result15);
                    return true;
                case 40:
                    data.enforceInterface(DESCRIPTOR);
                    int _result16 = GetRadioIC();
                    reply.writeNoException();
                    reply.writeInt(_result16);
                    return true;
                case 41:
                    data.enforceInterface(DESCRIPTOR);
                    int _result17 = GetCanType();
                    reply.writeNoException();
                    reply.writeInt(_result17);
                    return true;
                case 42:
                    data.enforceInterface(DESCRIPTOR);
                    int _result18 = GetCameraType();
                    reply.writeNoException();
                    reply.writeInt(_result18);
                    return true;
                case 43:
                    data.enforceInterface(DESCRIPTOR);
                    SendKey(data.readInt());
                    reply.writeNoException();
                    return true;
                case 44:
                    data.enforceInterface(DESCRIPTOR);
                    KillProcess(data.readString());
                    reply.writeNoException();
                    return true;
                case 45:
                    data.enforceInterface(DESCRIPTOR);
                    OpenMainUIApp(data.readInt(), data.readInt());
                    reply.writeNoException();
                    return true;
                case 46:
                    data.enforceInterface(DESCRIPTOR);
                    CloseMainUIApp(data.readInt());
                    reply.writeNoException();
                    return true;
                case 47:
                    data.enforceInterface(DESCRIPTOR);
                    String _result19 = GetDeviceID();
                    reply.writeNoException();
                    reply.writeString(_result19);
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements ITsSpeechFunction {
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

            public boolean isScreenOpen() throws RemoteException {
                boolean _result = true;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onOpenScreen() throws RemoteException {
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

            public void onCloseScreen() throws RemoteException {
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

            public int getCurrentBrightness() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int getMinBrightnes() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int getMaxBrightnes() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(6, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onBrightenScreen() throws RemoteException {
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

            public void onDimmingScreen() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(8, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onScreenBrightest() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(9, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onScreenDarkest() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(10, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int getCurrentVolume() throws RemoteException {
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

            public int getMinVolume() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(12, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int getMaxVolume() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(13, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onVolumeUp() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(14, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onVolumeDown() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(15, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onVolumeMax() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(16, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onVolumeMin() throws RemoteException {
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

            public boolean isVolumeMute() throws RemoteException {
                boolean _result = false;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(18, _data, _reply, 0);
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

            public void onVolumeMute() throws RemoteException {
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

            public void onVolumeUnmute() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(20, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void showLauncher() throws RemoteException {
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

            public int getWorkMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(22, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void openSetting() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(23, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void openAvin() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(24, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void openCarInfo() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(25, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void speechStartTTS() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(26, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void speechStopTTS() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(27, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void speechStartRecognition() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(28, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void speechStopRecognition() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(29, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void setVolume(int number) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(number);
                    this.mRemote.transact(30, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void setScreenBrightness(int number) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(number);
                    this.mRemote.transact(31, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void supportFastCharging(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    this.mRemote.transact(32, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void setFunctionCallback(ITsFunctionCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(33, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int isIllOn() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(34, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int isBackCar() throws RemoteException {
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

            public int withBrakes() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(36, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String getDeviceType() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(37, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String getHMI() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(38, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String getBranch() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(39, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int GetRadioIC() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(40, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int GetCanType() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(41, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int GetCameraType() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(42, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void SendKey(int KeyCode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(KeyCode);
                    this.mRemote.transact(43, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void KillProcess(String pname) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pname);
                    this.mRemote.transact(44, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void OpenMainUIApp(int nWin, int parat) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(nWin);
                    _data.writeInt(parat);
                    this.mRemote.transact(45, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void CloseMainUIApp(int nWin) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(nWin);
                    this.mRemote.transact(46, _data, _reply, 0);
                    _reply.readException();
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
                    this.mRemote.transact(47, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
