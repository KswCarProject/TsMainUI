package com.txznet.txz.Tr;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: Proguard */
public interface T extends IInterface {
    byte[] T(String str, String str2, byte[] bArr) throws RemoteException;

    /* renamed from: com.txznet.txz.Tr.T$T  reason: collision with other inner class name */
    /* compiled from: Proguard */
    public static abstract class C0021T extends Binder implements T {
        public C0021T() {
            attachInterface(this, "com.txznet.txz.service.IService");
        }

        public static T T(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface("com.txznet.txz.service.IService");
            if (iin == null || !(iin instanceof T)) {
                return new C0022T(obj);
            }
            return (T) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1:
                    data.enforceInterface("com.txznet.txz.service.IService");
                    byte[] _result = T(data.readString(), data.readString(), data.createByteArray());
                    reply.writeNoException();
                    reply.writeByteArray(_result);
                    return true;
                case 1598968902:
                    reply.writeString("com.txznet.txz.service.IService");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* renamed from: com.txznet.txz.Tr.T$T$T  reason: collision with other inner class name */
        /* compiled from: Proguard */
        private static class C0022T implements T {

            /* renamed from: T  reason: collision with root package name */
            private IBinder f893T;

            C0022T(IBinder remote) {
                this.f893T = remote;
            }

            public IBinder asBinder() {
                return this.f893T;
            }

            public byte[] T(String packageName, String command, byte[] data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.txznet.txz.service.IService");
                    _data.writeString(packageName);
                    _data.writeString(command);
                    _data.writeByteArray(data);
                    this.f893T.transact(1, _data, _reply, 0);
                    _reply.readException();
                    return _reply.createByteArray();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
