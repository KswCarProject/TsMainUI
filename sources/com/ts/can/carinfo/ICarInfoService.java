package com.ts.can.carinfo;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ICarInfoService extends IInterface {
    int T3FlTexlCmd(int i, int[] iArr) throws RemoteException;

    int[] requestCarAirInfo() throws RemoteException;

    String requestCarAirLtTemp() throws RemoteException;

    String requestCarAirRtTemp() throws RemoteException;

    int[] requestCarBaseInfo() throws RemoteException;

    int[] requestCarDoorInfo() throws RemoteException;

    boolean requestCarIllInfo() throws RemoteException;

    int[] requestT3FlCanData7e0() throws RemoteException;

    int[] requestT3FlCanData7f1() throws RemoteException;

    int[] requestT3FlCanData7f2() throws RemoteException;

    int[] requestT3FlCanData7f3() throws RemoteException;

    int[] requestT3FlCanData7f4() throws RemoteException;

    int[] requestT3FlDevInfo() throws RemoteException;

    int requestT3FlSta() throws RemoteException;

    int[] requestT3FlTexlData() throws RemoteException;

    int[] requestT3FlTexlDisCur() throws RemoteException;

    int[] requestT3FlTexlDisOver() throws RemoteException;

    int[] requestT3FlTexlPjxx() throws RemoteException;

    public static abstract class Stub extends Binder implements ICarInfoService {
        private static final String DESCRIPTOR = "com.ts.can.carinfo.ICarInfoService";
        static final int TRANSACTION_T3FlTexlCmd = 15;
        static final int TRANSACTION_requestCarAirInfo = 1;
        static final int TRANSACTION_requestCarAirLtTemp = 2;
        static final int TRANSACTION_requestCarAirRtTemp = 3;
        static final int TRANSACTION_requestCarBaseInfo = 6;
        static final int TRANSACTION_requestCarDoorInfo = 4;
        static final int TRANSACTION_requestCarIllInfo = 5;
        static final int TRANSACTION_requestT3FlCanData7e0 = 13;
        static final int TRANSACTION_requestT3FlCanData7f1 = 9;
        static final int TRANSACTION_requestT3FlCanData7f2 = 10;
        static final int TRANSACTION_requestT3FlCanData7f3 = 11;
        static final int TRANSACTION_requestT3FlCanData7f4 = 12;
        static final int TRANSACTION_requestT3FlDevInfo = 7;
        static final int TRANSACTION_requestT3FlSta = 8;
        static final int TRANSACTION_requestT3FlTexlData = 14;
        static final int TRANSACTION_requestT3FlTexlDisCur = 16;
        static final int TRANSACTION_requestT3FlTexlDisOver = 17;
        static final int TRANSACTION_requestT3FlTexlPjxx = 18;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarInfoService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ICarInfoService)) {
                return new Proxy(obj);
            }
            return (ICarInfoService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    int[] _result = requestCarAirInfo();
                    reply.writeNoException();
                    reply.writeIntArray(_result);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    String _result2 = requestCarAirLtTemp();
                    reply.writeNoException();
                    reply.writeString(_result2);
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    String _result3 = requestCarAirRtTemp();
                    reply.writeNoException();
                    reply.writeString(_result3);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    int[] _result4 = requestCarDoorInfo();
                    reply.writeNoException();
                    reply.writeIntArray(_result4);
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result5 = requestCarIllInfo();
                    reply.writeNoException();
                    reply.writeInt(_result5 ? 1 : 0);
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    int[] _result6 = requestCarBaseInfo();
                    reply.writeNoException();
                    reply.writeIntArray(_result6);
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    int[] _result7 = requestT3FlDevInfo();
                    reply.writeNoException();
                    reply.writeIntArray(_result7);
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    int _result8 = requestT3FlSta();
                    reply.writeNoException();
                    reply.writeInt(_result8);
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    int[] _result9 = requestT3FlCanData7f1();
                    reply.writeNoException();
                    reply.writeIntArray(_result9);
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    int[] _result10 = requestT3FlCanData7f2();
                    reply.writeNoException();
                    reply.writeIntArray(_result10);
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    int[] _result11 = requestT3FlCanData7f3();
                    reply.writeNoException();
                    reply.writeIntArray(_result11);
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    int[] _result12 = requestT3FlCanData7f4();
                    reply.writeNoException();
                    reply.writeIntArray(_result12);
                    return true;
                case 13:
                    data.enforceInterface(DESCRIPTOR);
                    int[] _result13 = requestT3FlCanData7e0();
                    reply.writeNoException();
                    reply.writeIntArray(_result13);
                    return true;
                case 14:
                    data.enforceInterface(DESCRIPTOR);
                    int[] _result14 = requestT3FlTexlData();
                    reply.writeNoException();
                    reply.writeIntArray(_result14);
                    return true;
                case 15:
                    data.enforceInterface(DESCRIPTOR);
                    int _result15 = T3FlTexlCmd(data.readInt(), data.createIntArray());
                    reply.writeNoException();
                    reply.writeInt(_result15);
                    return true;
                case 16:
                    data.enforceInterface(DESCRIPTOR);
                    int[] _result16 = requestT3FlTexlDisCur();
                    reply.writeNoException();
                    reply.writeIntArray(_result16);
                    return true;
                case 17:
                    data.enforceInterface(DESCRIPTOR);
                    int[] _result17 = requestT3FlTexlDisOver();
                    reply.writeNoException();
                    reply.writeIntArray(_result17);
                    return true;
                case 18:
                    data.enforceInterface(DESCRIPTOR);
                    int[] _result18 = requestT3FlTexlPjxx();
                    reply.writeNoException();
                    reply.writeIntArray(_result18);
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements ICarInfoService {
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

            public int[] requestCarAirInfo() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                    return _reply.createIntArray();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String requestCarAirLtTemp() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String requestCarAirRtTemp() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int[] requestCarDoorInfo() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                    return _reply.createIntArray();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean requestCarIllInfo() throws RemoteException {
                boolean _result = false;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(5, _data, _reply, 0);
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

            public int[] requestCarBaseInfo() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(6, _data, _reply, 0);
                    _reply.readException();
                    return _reply.createIntArray();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int[] requestT3FlDevInfo() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(7, _data, _reply, 0);
                    _reply.readException();
                    return _reply.createIntArray();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int requestT3FlSta() throws RemoteException {
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

            public int[] requestT3FlCanData7f1() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(9, _data, _reply, 0);
                    _reply.readException();
                    return _reply.createIntArray();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int[] requestT3FlCanData7f2() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(10, _data, _reply, 0);
                    _reply.readException();
                    return _reply.createIntArray();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int[] requestT3FlCanData7f3() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(11, _data, _reply, 0);
                    _reply.readException();
                    return _reply.createIntArray();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int[] requestT3FlCanData7f4() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(12, _data, _reply, 0);
                    _reply.readException();
                    return _reply.createIntArray();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int[] requestT3FlCanData7e0() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(13, _data, _reply, 0);
                    _reply.readException();
                    return _reply.createIntArray();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int[] requestT3FlTexlData() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(14, _data, _reply, 0);
                    _reply.readException();
                    return _reply.createIntArray();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int T3FlTexlCmd(int type, int[] cmd) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(type);
                    _data.writeIntArray(cmd);
                    this.mRemote.transact(15, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int[] requestT3FlTexlDisCur() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(16, _data, _reply, 0);
                    _reply.readException();
                    return _reply.createIntArray();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int[] requestT3FlTexlDisOver() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(17, _data, _reply, 0);
                    _reply.readException();
                    return _reply.createIntArray();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int[] requestT3FlTexlPjxx() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(18, _data, _reply, 0);
                    _reply.readException();
                    return _reply.createIntArray();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
