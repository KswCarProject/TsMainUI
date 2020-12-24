package com.ts.tsspeechlib.bt;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface ITsSpeechBtListener extends IInterface {
    void onBtConnectStateChange(int i) throws RemoteException;

    void onBtPbListChange(List<ITsSpeechBtPbInfo> list) throws RemoteException;

    void onBtStateChange(int i, String str) throws RemoteException;

    public static abstract class Stub extends Binder implements ITsSpeechBtListener {
        private static final String DESCRIPTOR = "com.ts.tsspeechlib.bt.ITsSpeechBtListener";
        static final int TRANSACTION_onBtConnectStateChange = 2;
        static final int TRANSACTION_onBtPbListChange = 3;
        static final int TRANSACTION_onBtStateChange = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITsSpeechBtListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ITsSpeechBtListener)) {
                return new Proxy(obj);
            }
            return (ITsSpeechBtListener) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    onBtStateChange(data.readInt(), data.readString());
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    onBtConnectStateChange(data.readInt());
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    List<ITsSpeechBtPbInfo> _arg0 = data.createTypedArrayList(ITsSpeechBtPbInfo.CREATOR);
                    onBtPbListChange(_arg0);
                    reply.writeNoException();
                    reply.writeTypedList(_arg0);
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements ITsSpeechBtListener {
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

            public void onBtStateChange(int state, String phoneNumber) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    _data.writeString(phoneNumber);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onBtConnectStateChange(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onBtPbListChange(List<ITsSpeechBtPbInfo> btPbList) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeTypedList(btPbList);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                    _reply.readTypedList(btPbList, ITsSpeechBtPbInfo.CREATOR);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
