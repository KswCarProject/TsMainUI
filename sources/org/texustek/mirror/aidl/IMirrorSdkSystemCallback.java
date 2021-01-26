package org.texustek.mirror.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IMirrorSdkSystemCallback extends IInterface {
    void onMirrorConnectStatusChange(int i) throws RemoteException;

    void onMirrorCoreAudioPlay(byte[] bArr) throws RemoteException;

    void onMirrorCoreAudioStart(int i, int i2, int i3, int i4) throws RemoteException;

    void onMirrorCoreAudioStop(int i) throws RemoteException;

    void onMirrorCoreMicrophoneStart(int i, int i2, int i3, int i4) throws RemoteException;

    void onMirrorCoreMicrophoneStop() throws RemoteException;

    int onMirrorCoreMicrophoneStopCapture(byte[] bArr, int i) throws RemoteException;

    void onMirrorCoreVideoPlay(byte[] bArr, int i) throws RemoteException;

    void onMirrorCoreVideoStart(int i, int i2, int i3, int i4) throws RemoteException;

    void onMirrorCoreVideoStop() throws RemoteException;

    public static abstract class Stub extends Binder implements IMirrorSdkSystemCallback {
        private static final String DESCRIPTOR = "org.texustek.mirror.aidl.IMirrorSdkSystemCallback";
        static final int TRANSACTION_onMirrorConnectStatusChange = 1;
        static final int TRANSACTION_onMirrorCoreAudioPlay = 3;
        static final int TRANSACTION_onMirrorCoreAudioStart = 2;
        static final int TRANSACTION_onMirrorCoreAudioStop = 4;
        static final int TRANSACTION_onMirrorCoreMicrophoneStart = 8;
        static final int TRANSACTION_onMirrorCoreMicrophoneStop = 9;
        static final int TRANSACTION_onMirrorCoreMicrophoneStopCapture = 10;
        static final int TRANSACTION_onMirrorCoreVideoPlay = 6;
        static final int TRANSACTION_onMirrorCoreVideoStart = 5;
        static final int TRANSACTION_onMirrorCoreVideoStop = 7;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMirrorSdkSystemCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IMirrorSdkSystemCallback)) {
                return new Proxy(obj);
            }
            return (IMirrorSdkSystemCallback) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    onMirrorConnectStatusChange(data.readInt());
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    onMirrorCoreAudioStart(data.readInt(), data.readInt(), data.readInt(), data.readInt());
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    onMirrorCoreAudioPlay(data.createByteArray());
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    onMirrorCoreAudioStop(data.readInt());
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    onMirrorCoreVideoStart(data.readInt(), data.readInt(), data.readInt(), data.readInt());
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    onMirrorCoreVideoPlay(data.createByteArray(), data.readInt());
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    onMirrorCoreVideoStop();
                    reply.writeNoException();
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    onMirrorCoreMicrophoneStart(data.readInt(), data.readInt(), data.readInt(), data.readInt());
                    reply.writeNoException();
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    onMirrorCoreMicrophoneStop();
                    reply.writeNoException();
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    int _result = onMirrorCoreMicrophoneStopCapture(data.createByteArray(), data.readInt());
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements IMirrorSdkSystemCallback {
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

            public void onMirrorConnectStatusChange(int status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(status);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onMirrorCoreAudioStart(int audioStreamType, int rate, int bits, int channels) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(audioStreamType);
                    _data.writeInt(rate);
                    _data.writeInt(bits);
                    _data.writeInt(channels);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onMirrorCoreAudioPlay(byte[] data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(data);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onMirrorCoreAudioStop(int audioStreamType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(audioStreamType);
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onMirrorCoreVideoStart(int w, int h, int x, int y) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(w);
                    _data.writeInt(h);
                    _data.writeInt(x);
                    _data.writeInt(y);
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onMirrorCoreVideoPlay(byte[] data, int len) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(data);
                    _data.writeInt(len);
                    this.mRemote.transact(6, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onMirrorCoreVideoStop() throws RemoteException {
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

            public void onMirrorCoreMicrophoneStart(int sampleRate, int channel, int bits, int mirrorMode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(sampleRate);
                    _data.writeInt(channel);
                    _data.writeInt(bits);
                    _data.writeInt(mirrorMode);
                    this.mRemote.transact(8, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void onMirrorCoreMicrophoneStop() throws RemoteException {
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

            public int onMirrorCoreMicrophoneStopCapture(byte[] buf, int len) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeByteArray(buf);
                    _data.writeInt(len);
                    this.mRemote.transact(10, _data, _reply, 0);
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
