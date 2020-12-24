package com.hongfans.rearview.services.api;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface IOnPlayChangedListener extends IInterface {
    void OnPlayListChanged(List<ProgramDigtalModel> list) throws RemoteException;

    void OnPlayMusicChanged(ProgramDigtalModel programDigtalModel) throws RemoteException;

    void OnPlayStateChanged(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IOnPlayChangedListener {
        private static final String DESCRIPTOR = "com.hongfans.rearview.services.api.IOnPlayChangedListener";
        static final int TRANSACTION_OnPlayListChanged = 2;
        static final int TRANSACTION_OnPlayMusicChanged = 3;
        static final int TRANSACTION_OnPlayStateChanged = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IOnPlayChangedListener asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IOnPlayChangedListener)) {
                return new Proxy(obj);
            }
            return (IOnPlayChangedListener) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            ProgramDigtalModel _arg0;
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    OnPlayStateChanged(data.readInt());
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    OnPlayListChanged(data.createTypedArrayList(ProgramDigtalModel.CREATOR));
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        _arg0 = ProgramDigtalModel.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    OnPlayMusicChanged(_arg0);
                    reply.writeNoException();
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IOnPlayChangedListener {
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

            public void OnPlayStateChanged(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void OnPlayListChanged(List<ProgramDigtalModel> list) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeTypedList(list);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void OnPlayMusicChanged(ProgramDigtalModel model) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (model != null) {
                        _data.writeInt(1);
                        model.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
