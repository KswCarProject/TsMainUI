package com.ts.tsspeechlib.car;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ITsSpeechCar extends IInterface {
    int[] GetCarDoorInfo() throws RemoteException;

    int[] GetCarFrRadar() throws RemoteException;

    int[] GetCarLrRadar() throws RemoteException;

    int GetEmergency() throws RemoteException;

    int GetLeftTurnSignal() throws RemoteException;

    int GetOilLeftover() throws RemoteException;

    int GetRightTurnSignal() throws RemoteException;

    int GetSpeed() throws RemoteException;

    int GetTotalKilometers() throws RemoteException;

    int IsCameraMode() throws RemoteException;

    int getLineEps() throws RemoteException;

    void onCloseAllCarwindow() throws RemoteException;

    void onCloseCarwindow(String str) throws RemoteException;

    void onCloseSkylight() throws RemoteException;

    void onDownWindSpeed() throws RemoteException;

    void onHeatRearwindow() throws RemoteException;

    void onHighAir() throws RemoteException;

    void onLowAir() throws RemoteException;

    void onOpenAllCarwindow() throws RemoteException;

    void onOpenCarwindow(String str) throws RemoteException;

    void onOpenSkylight() throws RemoteException;

    void onSetAir(String str) throws RemoteException;

    void onSetLoop(String str) throws RemoteException;

    void onTurnDownAir() throws RemoteException;

    void onTurnOnAir() throws RemoteException;

    void onUpWindSpeed() throws RemoteException;

    void onWindMode(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements ITsSpeechCar {
        private static final String DESCRIPTOR = "com.ts.tsspeechlib.car.ITsSpeechCar";
        static final int TRANSACTION_GetCarDoorInfo = 24;
        static final int TRANSACTION_GetCarFrRadar = 25;
        static final int TRANSACTION_GetCarLrRadar = 26;
        static final int TRANSACTION_GetEmergency = 21;
        static final int TRANSACTION_GetLeftTurnSignal = 19;
        static final int TRANSACTION_GetOilLeftover = 18;
        static final int TRANSACTION_GetRightTurnSignal = 20;
        static final int TRANSACTION_GetSpeed = 22;
        static final int TRANSACTION_GetTotalKilometers = 17;
        static final int TRANSACTION_IsCameraMode = 27;
        static final int TRANSACTION_getLineEps = 23;
        static final int TRANSACTION_onCloseAllCarwindow = 13;
        static final int TRANSACTION_onCloseCarwindow = 15;
        static final int TRANSACTION_onCloseSkylight = 11;
        static final int TRANSACTION_onDownWindSpeed = 8;
        static final int TRANSACTION_onHeatRearwindow = 16;
        static final int TRANSACTION_onHighAir = 3;
        static final int TRANSACTION_onLowAir = 4;
        static final int TRANSACTION_onOpenAllCarwindow = 12;
        static final int TRANSACTION_onOpenCarwindow = 14;
        static final int TRANSACTION_onOpenSkylight = 10;
        static final int TRANSACTION_onSetAir = 6;
        static final int TRANSACTION_onSetLoop = 5;
        static final int TRANSACTION_onTurnDownAir = 2;
        static final int TRANSACTION_onTurnOnAir = 1;
        static final int TRANSACTION_onUpWindSpeed = 7;
        static final int TRANSACTION_onWindMode = 9;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITsSpeechCar asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ITsSpeechCar)) {
                return new Proxy(obj);
            }
            return (ITsSpeechCar) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    onTurnOnAir();
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    onTurnDownAir();
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    onHighAir();
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    onLowAir();
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    onSetLoop(data.readString());
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    onSetAir(data.readString());
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    onUpWindSpeed();
                    reply.writeNoException();
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    onDownWindSpeed();
                    reply.writeNoException();
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    onWindMode(data.readString());
                    reply.writeNoException();
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    onOpenSkylight();
                    reply.writeNoException();
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    onCloseSkylight();
                    reply.writeNoException();
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    onOpenAllCarwindow();
                    reply.writeNoException();
                    return true;
                case 13:
                    data.enforceInterface(DESCRIPTOR);
                    onCloseAllCarwindow();
                    reply.writeNoException();
                    return true;
                case 14:
                    data.enforceInterface(DESCRIPTOR);
                    onOpenCarwindow(data.readString());
                    reply.writeNoException();
                    return true;
                case 15:
                    data.enforceInterface(DESCRIPTOR);
                    onCloseCarwindow(data.readString());
                    reply.writeNoException();
                    return true;
                case 16:
                    data.enforceInterface(DESCRIPTOR);
                    onHeatRearwindow();
                    reply.writeNoException();
                    return true;
                case 17:
                    data.enforceInterface(DESCRIPTOR);
                    int _result = GetTotalKilometers();
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                case 18:
                    data.enforceInterface(DESCRIPTOR);
                    int _result2 = GetOilLeftover();
                    reply.writeNoException();
                    reply.writeInt(_result2);
                    return true;
                case 19:
                    data.enforceInterface(DESCRIPTOR);
                    int _result3 = GetLeftTurnSignal();
                    reply.writeNoException();
                    reply.writeInt(_result3);
                    return true;
                case 20:
                    data.enforceInterface(DESCRIPTOR);
                    int _result4 = GetRightTurnSignal();
                    reply.writeNoException();
                    reply.writeInt(_result4);
                    return true;
                case 21:
                    data.enforceInterface(DESCRIPTOR);
                    int _result5 = GetEmergency();
                    reply.writeNoException();
                    reply.writeInt(_result5);
                    return true;
                case 22:
                    data.enforceInterface(DESCRIPTOR);
                    int _result6 = GetSpeed();
                    reply.writeNoException();
                    reply.writeInt(_result6);
                    return true;
                case 23:
                    data.enforceInterface(DESCRIPTOR);
                    int _result7 = getLineEps();
                    reply.writeNoException();
                    reply.writeInt(_result7);
                    return true;
                case 24:
                    data.enforceInterface(DESCRIPTOR);
                    int[] _result8 = GetCarDoorInfo();
                    reply.writeNoException();
                    reply.writeIntArray(_result8);
                    return true;
                case 25:
                    data.enforceInterface(DESCRIPTOR);
                    int[] _result9 = GetCarFrRadar();
                    reply.writeNoException();
                    reply.writeIntArray(_result9);
                    return true;
                case 26:
                    data.enforceInterface(DESCRIPTOR);
                    int[] _result10 = GetCarLrRadar();
                    reply.writeNoException();
                    reply.writeIntArray(_result10);
                    return true;
                case 27:
                    data.enforceInterface(DESCRIPTOR);
                    int _result11 = IsCameraMode();
                    reply.writeNoException();
                    reply.writeInt(_result11);
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements ITsSpeechCar {
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

            public void onTurnOnAir() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onTurnDownAir() throws RemoteException {
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

            public void onHighAir() throws RemoteException {
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

            public void onLowAir() throws RemoteException {
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

            public void onSetLoop(String number) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(number);
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onSetAir(String number) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(number);
                    this.mRemote.transact(6, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onUpWindSpeed() throws RemoteException {
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

            public void onDownWindSpeed() throws RemoteException {
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

            public void onWindMode(String number) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(number);
                    this.mRemote.transact(9, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onOpenSkylight() throws RemoteException {
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

            public void onCloseSkylight() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(11, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onOpenAllCarwindow() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(12, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onCloseAllCarwindow() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(13, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onOpenCarwindow(String number) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(number);
                    this.mRemote.transact(14, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onCloseCarwindow(String number) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(number);
                    this.mRemote.transact(15, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onHeatRearwindow() throws RemoteException {
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

            public int GetTotalKilometers() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(17, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int GetOilLeftover() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(18, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int GetLeftTurnSignal() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(19, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int GetRightTurnSignal() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(20, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int GetEmergency() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(21, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int GetSpeed() throws RemoteException {
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

            public int getLineEps() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(23, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int[] GetCarDoorInfo() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(24, _data, _reply, 0);
                    _reply.readException();
                    return _reply.createIntArray();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int[] GetCarFrRadar() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(25, _data, _reply, 0);
                    _reply.readException();
                    return _reply.createIntArray();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int[] GetCarLrRadar() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(26, _data, _reply, 0);
                    _reply.readException();
                    return _reply.createIntArray();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int IsCameraMode() throws RemoteException {
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
        }
    }
}
