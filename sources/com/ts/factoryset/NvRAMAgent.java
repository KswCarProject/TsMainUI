package com.ts.factoryset;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

public interface NvRAMAgent extends IInterface {
    byte[] readFile(int i) throws RemoteException;

    byte[] readFileByName(String str) throws RemoteException;

    int writeFile(int i, byte[] bArr) throws RemoteException;

    int writeFileByName(String str, byte[] bArr) throws RemoteException;

    public static abstract class Stub extends Binder implements NvRAMAgent {
        private static final String DESCRIPTOR = "NvRAMAgent";
        static final int TRANSACTION_READFILE = 1;
        static final int TRANSACTION_READFILEBYNAME = 3;
        static final int TRANSACTION_WRITEFILE = 2;
        static final int TRANSACTION_WRITEFILEBYNAME = 4;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static NvRAMAgent asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof NvRAMAgent)) {
                return new Proxy(obj);
            }
            return (NvRAMAgent) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _result = readFile(data.readInt());
                    reply.writeNoException();
                    reply.writeByteArray(_result);
                    return true;
                case 2:
                    Log.d("NvRAMAgent.java", "writefile");
                    data.enforceInterface(DESCRIPTOR);
                    int _result2 = writeFile(data.readInt(), data.createByteArray());
                    reply.writeNoException();
                    reply.writeInt(_result2);
                    Log.d("NvRAMAgent.java", "writefile result:" + _result2);
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements NvRAMAgent {
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

            public byte[] readFile(int file_lid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(file_lid);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                    return _reply.createByteArray();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int writeFile(int file_lid, byte[] buff) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(file_lid);
                    _data.writeByteArray(buff);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public byte[] readFileByName(String filename) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(filename);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                    return _reply.createByteArray();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int writeFileByName(String filename, byte[] buff) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(filename);
                    _data.writeByteArray(buff);
                    this.mRemote.transact(4, _data, _reply, 0);
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
