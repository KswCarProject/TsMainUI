package org.texustek.mirror.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
import org.texustek.mirror.aidl.IMirrorAdapterBluetoothCallback;

public interface IMirrorAdapterBluetoothServer extends IInterface {
    int getSpeedPlayConnectState() throws RemoteException;

    boolean isConnected(String str) throws RemoteException;

    void registerCallBack(IMirrorAdapterBluetoothCallback iMirrorAdapterBluetoothCallback) throws RemoteException;

    void reportBluetoothConnectState(int i) throws RemoteException;

    void reportLocalBluetoothMacAddress(byte[] bArr) throws RemoteException;

    void reportMpuBackCarStatus(int i) throws RemoteException;

    void reportPairedBluetoothUuids(List<String> list) throws RemoteException;

    void reportPhoneBluetoothMacAddress(byte[] bArr, int i) throws RemoteException;

    void reportRfcommConnectState(int i) throws RemoteException;

    void reportRfcommReadData(byte[] bArr) throws RemoteException;

    void sendKeyEventRequest(int i) throws RemoteException;

    void setNightMode(boolean z) throws RemoteException;

    void unregisterCallBack(IMirrorAdapterBluetoothCallback iMirrorAdapterBluetoothCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IMirrorAdapterBluetoothServer {
        private static final String DESCRIPTOR = "org.texustek.mirror.aidl.IMirrorAdapterBluetoothServer";
        static final int TRANSACTION_getSpeedPlayConnectState = 10;
        static final int TRANSACTION_isConnected = 11;
        static final int TRANSACTION_registerCallBack = 1;
        static final int TRANSACTION_reportBluetoothConnectState = 3;
        static final int TRANSACTION_reportLocalBluetoothMacAddress = 5;
        static final int TRANSACTION_reportMpuBackCarStatus = 9;
        static final int TRANSACTION_reportPairedBluetoothUuids = 7;
        static final int TRANSACTION_reportPhoneBluetoothMacAddress = 6;
        static final int TRANSACTION_reportRfcommConnectState = 8;
        static final int TRANSACTION_reportRfcommReadData = 4;
        static final int TRANSACTION_sendKeyEventRequest = 13;
        static final int TRANSACTION_setNightMode = 12;
        static final int TRANSACTION_unregisterCallBack = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMirrorAdapterBluetoothServer asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IMirrorAdapterBluetoothServer)) {
                return new Proxy(obj);
            }
            return (IMirrorAdapterBluetoothServer) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            boolean _arg0;
            int i = 0;
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    registerCallBack(IMirrorAdapterBluetoothCallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    unregisterCallBack(IMirrorAdapterBluetoothCallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    reportBluetoothConnectState(data.readInt());
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    reportRfcommReadData(data.createByteArray());
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    reportLocalBluetoothMacAddress(data.createByteArray());
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    reportPhoneBluetoothMacAddress(data.createByteArray(), data.readInt());
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    reportPairedBluetoothUuids(data.createStringArrayList());
                    reply.writeNoException();
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    reportRfcommConnectState(data.readInt());
                    reply.writeNoException();
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    reportMpuBackCarStatus(data.readInt());
                    reply.writeNoException();
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    int _result = getSpeedPlayConnectState();
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result2 = isConnected(data.readString());
                    reply.writeNoException();
                    if (_result2) {
                        i = 1;
                    }
                    reply.writeInt(i);
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = true;
                    } else {
                        _arg0 = false;
                    }
                    setNightMode(_arg0);
                    reply.writeNoException();
                    return true;
                case 13:
                    data.enforceInterface(DESCRIPTOR);
                    sendKeyEventRequest(data.readInt());
                    reply.writeNoException();
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IMirrorAdapterBluetoothServer {
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

            public void registerCallBack(IMirrorAdapterBluetoothCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void unregisterCallBack(IMirrorAdapterBluetoothCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void reportBluetoothConnectState(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void reportRfcommReadData(byte[] data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(data);
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void reportLocalBluetoothMacAddress(byte[] address) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(address);
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void reportPhoneBluetoothMacAddress(byte[] address, int firstTimePaired) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(address);
                    _data.writeInt(firstTimePaired);
                    this.mRemote.transact(6, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void reportPairedBluetoothUuids(List<String> uuids) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStringList(uuids);
                    this.mRemote.transact(7, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void reportRfcommConnectState(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    this.mRemote.transact(8, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void reportMpuBackCarStatus(int status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(status);
                    this.mRemote.transact(9, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int getSpeedPlayConnectState() throws RemoteException {
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

            public boolean isConnected(String phoneMode) throws RemoteException {
                boolean _result = false;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(phoneMode);
                    this.mRemote.transact(11, _data, _reply, 0);
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

            public void setNightMode(boolean isNightMode) throws RemoteException {
                int i = 0;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (isNightMode) {
                        i = 1;
                    }
                    _data.writeInt(i);
                    this.mRemote.transact(12, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void sendKeyEventRequest(int specFuncCode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(specFuncCode);
                    this.mRemote.transact(13, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
