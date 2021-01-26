package org.texustek.mirror.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface IMirrorAdapterBluetoothCallback extends IInterface {
    int onRequestBluetoothConnectState() throws RemoteException;

    void onRequestCloseRfcomm() throws RemoteException;

    void onRequestDisconnectBluetooth() throws RemoteException;

    byte[] onRequestLocalBluetoothMacAddress() throws RemoteException;

    int onRequestMpuBackCarStatus() throws RemoteException;

    void onRequestOpenRfcomm(byte[] bArr, String str) throws RemoteException;

    List<String> onRequestPairedBluetoothUuids() throws RemoteException;

    byte[] onRequestPairedPhoneBluetoothMacAddress() throws RemoteException;

    int onRequestRfcommConnectState() throws RemoteException;

    void onRequestWriteRfcomm(byte[] bArr) throws RemoteException;

    void onSpeedPlayConnectState(int i, String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IMirrorAdapterBluetoothCallback {
        private static final String DESCRIPTOR = "org.texustek.mirror.aidl.IMirrorAdapterBluetoothCallback";
        static final int TRANSACTION_onRequestBluetoothConnectState = 5;
        static final int TRANSACTION_onRequestCloseRfcomm = 3;
        static final int TRANSACTION_onRequestDisconnectBluetooth = 4;
        static final int TRANSACTION_onRequestLocalBluetoothMacAddress = 6;
        static final int TRANSACTION_onRequestMpuBackCarStatus = 10;
        static final int TRANSACTION_onRequestOpenRfcomm = 2;
        static final int TRANSACTION_onRequestPairedBluetoothUuids = 8;
        static final int TRANSACTION_onRequestPairedPhoneBluetoothMacAddress = 7;
        static final int TRANSACTION_onRequestRfcommConnectState = 9;
        static final int TRANSACTION_onRequestWriteRfcomm = 1;
        static final int TRANSACTION_onSpeedPlayConnectState = 11;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMirrorAdapterBluetoothCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IMirrorAdapterBluetoothCallback)) {
                return new Proxy(obj);
            }
            return (IMirrorAdapterBluetoothCallback) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    onRequestWriteRfcomm(data.createByteArray());
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    onRequestOpenRfcomm(data.createByteArray(), data.readString());
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    onRequestCloseRfcomm();
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    onRequestDisconnectBluetooth();
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    int _result = onRequestBluetoothConnectState();
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _result2 = onRequestLocalBluetoothMacAddress();
                    reply.writeNoException();
                    reply.writeByteArray(_result2);
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _result3 = onRequestPairedPhoneBluetoothMacAddress();
                    reply.writeNoException();
                    reply.writeByteArray(_result3);
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    List<String> _result4 = onRequestPairedBluetoothUuids();
                    reply.writeNoException();
                    reply.writeStringList(_result4);
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    int _result5 = onRequestRfcommConnectState();
                    reply.writeNoException();
                    reply.writeInt(_result5);
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    int _result6 = onRequestMpuBackCarStatus();
                    reply.writeNoException();
                    reply.writeInt(_result6);
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    onSpeedPlayConnectState(data.readInt(), data.readString());
                    reply.writeNoException();
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IMirrorAdapterBluetoothCallback {
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

            public void onRequestWriteRfcomm(byte[] data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(data);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onRequestOpenRfcomm(byte[] mac, String uuid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(mac);
                    _data.writeString(uuid);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onRequestCloseRfcomm() throws RemoteException {
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

            public void onRequestDisconnectBluetooth() throws RemoteException {
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

            public int onRequestBluetoothConnectState() throws RemoteException {
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

            public byte[] onRequestLocalBluetoothMacAddress() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(6, _data, _reply, 0);
                    _reply.readException();
                    return _reply.createByteArray();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public byte[] onRequestPairedPhoneBluetoothMacAddress() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(7, _data, _reply, 0);
                    _reply.readException();
                    return _reply.createByteArray();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public List<String> onRequestPairedBluetoothUuids() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(8, _data, _reply, 0);
                    _reply.readException();
                    return _reply.createStringArrayList();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int onRequestRfcommConnectState() throws RemoteException {
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

            public int onRequestMpuBackCarStatus() throws RemoteException {
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

            public void onSpeedPlayConnectState(int state, String phoneMode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    _data.writeString(phoneMode);
                    this.mRemote.transact(11, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
