package com.autochips.ipodplayer.ipodaidl;

import android.graphics.Bitmap;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ITsIPodAidl extends IInterface {
    Bitmap getBitmap() throws RemoteException;

    int getCurrentTime() throws RemoteException;

    String getId3Album() throws RemoteException;

    String getId3Artist() throws RemoteException;

    String getId3Title() throws RemoteException;

    int getPlayStatu() throws RemoteException;

    int getRandomMode() throws RemoteException;

    int getRepeatMode() throws RemoteException;

    IBinder getSpecialBinder(String str) throws RemoteException;

    int getTotalTime() throws RemoteException;

    void ipodNext() throws RemoteException;

    void ipodPause() throws RemoteException;

    void ipodPlay() throws RemoteException;

    void ipodPlaypause() throws RemoteException;

    void ipodPrev() throws RemoteException;

    void ipodRandom() throws RemoteException;

    void ipodRepeat() throws RemoteException;

    void ipodSeek(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements ITsIPodAidl {
        private static final String DESCRIPTOR = "com.autochips.ipodplayer.ipodaidl.ITsIPodAidl";
        static final int TRANSACTION_getBitmap = 16;
        static final int TRANSACTION_getCurrentTime = 10;
        static final int TRANSACTION_getId3Album = 8;
        static final int TRANSACTION_getId3Artist = 7;
        static final int TRANSACTION_getId3Title = 9;
        static final int TRANSACTION_getPlayStatu = 15;
        static final int TRANSACTION_getRandomMode = 18;
        static final int TRANSACTION_getRepeatMode = 17;
        static final int TRANSACTION_getSpecialBinder = 1;
        static final int TRANSACTION_getTotalTime = 11;
        static final int TRANSACTION_ipodNext = 3;
        static final int TRANSACTION_ipodPause = 13;
        static final int TRANSACTION_ipodPlay = 12;
        static final int TRANSACTION_ipodPlaypause = 4;
        static final int TRANSACTION_ipodPrev = 2;
        static final int TRANSACTION_ipodRandom = 6;
        static final int TRANSACTION_ipodRepeat = 5;
        static final int TRANSACTION_ipodSeek = 14;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITsIPodAidl asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ITsIPodAidl)) {
                return new Proxy(obj);
            }
            return (ITsIPodAidl) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    IBinder _result = getSpecialBinder(data.readString());
                    reply.writeNoException();
                    reply.writeStrongBinder(_result);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    ipodPrev();
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    ipodNext();
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    ipodPlaypause();
                    reply.writeNoException();
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    ipodRepeat();
                    reply.writeNoException();
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    ipodRandom();
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    String _result2 = getId3Artist();
                    reply.writeNoException();
                    reply.writeString(_result2);
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    String _result3 = getId3Album();
                    reply.writeNoException();
                    reply.writeString(_result3);
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    String _result4 = getId3Title();
                    reply.writeNoException();
                    reply.writeString(_result4);
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    int _result5 = getCurrentTime();
                    reply.writeNoException();
                    reply.writeInt(_result5);
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    int _result6 = getTotalTime();
                    reply.writeNoException();
                    reply.writeInt(_result6);
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    ipodPlay();
                    reply.writeNoException();
                    return true;
                case 13:
                    data.enforceInterface(DESCRIPTOR);
                    ipodPause();
                    reply.writeNoException();
                    return true;
                case 14:
                    data.enforceInterface(DESCRIPTOR);
                    ipodSeek(data.readInt());
                    reply.writeNoException();
                    return true;
                case 15:
                    data.enforceInterface(DESCRIPTOR);
                    int _result7 = getPlayStatu();
                    reply.writeNoException();
                    reply.writeInt(_result7);
                    return true;
                case 16:
                    data.enforceInterface(DESCRIPTOR);
                    Bitmap _result8 = getBitmap();
                    reply.writeNoException();
                    if (_result8 != null) {
                        reply.writeInt(1);
                        _result8.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case 17:
                    data.enforceInterface(DESCRIPTOR);
                    int _result9 = getRepeatMode();
                    reply.writeNoException();
                    reply.writeInt(_result9);
                    return true;
                case 18:
                    data.enforceInterface(DESCRIPTOR);
                    int _result10 = getRandomMode();
                    reply.writeNoException();
                    reply.writeInt(_result10);
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements ITsIPodAidl {
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

            public IBinder getSpecialBinder(String name) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(name);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readStrongBinder();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void ipodPrev() throws RemoteException {
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

            public void ipodNext() throws RemoteException {
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

            public void ipodPlaypause() throws RemoteException {
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

            public void ipodRepeat() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void ipodRandom() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(6, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String getId3Artist() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(7, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String getId3Album() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(8, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String getId3Title() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(9, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int getCurrentTime() throws RemoteException {
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

            public int getTotalTime() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(11, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void ipodPlay() throws RemoteException {
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

            public void ipodPause() throws RemoteException {
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

            public void ipodSeek(int offset) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(offset);
                    this.mRemote.transact(14, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int getPlayStatu() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(15, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public Bitmap getBitmap() throws RemoteException {
                Bitmap _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(16, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (Bitmap) Bitmap.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int getRepeatMode() throws RemoteException {
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

            public int getRandomMode() throws RemoteException {
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
        }
    }
}
