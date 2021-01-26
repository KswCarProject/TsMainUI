package org.texustek.mirror.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import org.texustek.mirror.aidl.IMirrorSdkMediaFocusCallback;

public interface IMirrorSdkMediaFocusServer extends IInterface {
    int abandonAudioFocus(int i, int i2) throws RemoteException;

    void registerCallBack(IMirrorSdkMediaFocusCallback iMirrorSdkMediaFocusCallback) throws RemoteException;

    int requestAudioFocus(int i, int i2) throws RemoteException;

    void unregisterCallBack(IMirrorSdkMediaFocusCallback iMirrorSdkMediaFocusCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IMirrorSdkMediaFocusServer {
        private static final String DESCRIPTOR = "org.texustek.mirror.aidl.IMirrorSdkMediaFocusServer";
        static final int TRANSACTION_abandonAudioFocus = 4;
        static final int TRANSACTION_registerCallBack = 1;
        static final int TRANSACTION_requestAudioFocus = 3;
        static final int TRANSACTION_unregisterCallBack = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMirrorSdkMediaFocusServer asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IMirrorSdkMediaFocusServer)) {
                return new Proxy(obj);
            }
            return (IMirrorSdkMediaFocusServer) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    registerCallBack(IMirrorSdkMediaFocusCallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    unregisterCallBack(IMirrorSdkMediaFocusCallback.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    int _result = requestAudioFocus(data.readInt(), data.readInt());
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    int _result2 = abandonAudioFocus(data.readInt(), data.readInt());
                    reply.writeNoException();
                    reply.writeInt(_result2);
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IMirrorSdkMediaFocusServer {
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

            public void registerCallBack(IMirrorSdkMediaFocusCallback callback) throws RemoteException {
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

            public void unregisterCallBack(IMirrorSdkMediaFocusCallback callback) throws RemoteException {
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

            public int requestAudioFocus(int streamType, int durationHint) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(streamType);
                    _data.writeInt(durationHint);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int abandonAudioFocus(int streamType, int durationHint) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(streamType);
                    _data.writeInt(durationHint);
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
