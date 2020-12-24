package com.ts.dvdplayer;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.Map;

public interface ITsPlayerService extends IInterface {
    void Rotate() throws RemoteException;

    void ZoomIn() throws RemoteException;

    void ZoomOut() throws RemoteException;

    void changeMediaType(int i) throws RemoteException;

    void clickDVDMenuItem(int i, int i2) throws RemoteException;

    void dealBTCall(int i) throws RemoteException;

    void enterMedia(int i) throws RemoteException;

    int getChannelCount() throws RemoteException;

    int getChannelId() throws RemoteException;

    int getChapterCount() throws RemoteException;

    int getChapterId() throws RemoteException;

    long getCurrentTime() throws RemoteException;

    int getDiscType() throws RemoteException;

    String getDvdUpdateInfo(String str) throws RemoteException;

    String getDvdVersion() throws RemoteException;

    int getFilterType() throws RemoteException;

    String getId3AlbumName() throws RemoteException;

    String getId3Artist() throws RemoteException;

    String getId3Title() throws RemoteException;

    int getMediaCnt() throws RemoteException;

    int getMediaDevice() throws RemoteException;

    Map getMediaList() throws RemoteException;

    int getMediaPlayerId() throws RemoteException;

    int getMediaType() throws RemoteException;

    String getMediaUpdateInfo(String str) throws RemoteException;

    String getMediaVersion() throws RemoteException;

    int getPlayId() throws RemoteException;

    String getPlayName() throws RemoteException;

    int getPlayStatus() throws RemoteException;

    int getRepeatMode() throws RemoteException;

    int getShuffleMode() throws RemoteException;

    int getSubtitleCount() throws RemoteException;

    int getSubtitleId() throws RemoteException;

    int getTitleCount() throws RemoteException;

    int getTitleId() throws RemoteException;

    long getTotoalTime() throws RemoteException;

    void gotoMenu() throws RemoteException;

    boolean isDvdLoadOK() throws RemoteException;

    int mediaTask(int i) throws RemoteException;

    int nDealMediaKey(int i) throws RemoteException;

    void next() throws RemoteException;

    void pause() throws RemoteException;

    void play() throws RemoteException;

    void playByPath(String str) throws RemoteException;

    void playByPos(int i) throws RemoteException;

    void playPause() throws RemoteException;

    void prev() throws RemoteException;

    void seek(long j) throws RemoteException;

    void setChapter(int i) throws RemoteException;

    void setFilterType(int i) throws RemoteException;

    void setTitle(int i) throws RemoteException;

    void toggleAudioChannel() throws RemoteException;

    void toggleRepeatMode() throws RemoteException;

    void toggleScrRate() throws RemoteException;

    void toggleShuffleMode() throws RemoteException;

    void toggleSubtitle() throws RemoteException;

    public static abstract class Stub extends Binder implements ITsPlayerService {
        private static final String DESCRIPTOR = "com.ts.dvdplayer.ITsPlayerService";
        static final int TRANSACTION_Rotate = 19;
        static final int TRANSACTION_ZoomIn = 18;
        static final int TRANSACTION_ZoomOut = 17;
        static final int TRANSACTION_changeMediaType = 2;
        static final int TRANSACTION_clickDVDMenuItem = 25;
        static final int TRANSACTION_dealBTCall = 56;
        static final int TRANSACTION_enterMedia = 1;
        static final int TRANSACTION_getChannelCount = 44;
        static final int TRANSACTION_getChannelId = 43;
        static final int TRANSACTION_getChapterCount = 40;
        static final int TRANSACTION_getChapterId = 39;
        static final int TRANSACTION_getCurrentTime = 30;
        static final int TRANSACTION_getDiscType = 24;
        static final int TRANSACTION_getDvdUpdateInfo = 49;
        static final int TRANSACTION_getDvdVersion = 47;
        static final int TRANSACTION_getFilterType = 5;
        static final int TRANSACTION_getId3AlbumName = 34;
        static final int TRANSACTION_getId3Artist = 35;
        static final int TRANSACTION_getId3Title = 36;
        static final int TRANSACTION_getMediaCnt = 33;
        static final int TRANSACTION_getMediaDevice = 3;
        static final int TRANSACTION_getMediaList = 53;
        static final int TRANSACTION_getMediaPlayerId = 45;
        static final int TRANSACTION_getMediaType = 4;
        static final int TRANSACTION_getMediaUpdateInfo = 50;
        static final int TRANSACTION_getMediaVersion = 48;
        static final int TRANSACTION_getPlayId = 32;
        static final int TRANSACTION_getPlayName = 26;
        static final int TRANSACTION_getPlayStatus = 27;
        static final int TRANSACTION_getRepeatMode = 28;
        static final int TRANSACTION_getShuffleMode = 29;
        static final int TRANSACTION_getSubtitleCount = 42;
        static final int TRANSACTION_getSubtitleId = 41;
        static final int TRANSACTION_getTitleCount = 38;
        static final int TRANSACTION_getTitleId = 37;
        static final int TRANSACTION_getTotoalTime = 31;
        static final int TRANSACTION_gotoMenu = 16;
        static final int TRANSACTION_isDvdLoadOK = 52;
        static final int TRANSACTION_mediaTask = 51;
        static final int TRANSACTION_nDealMediaKey = 46;
        static final int TRANSACTION_next = 7;
        static final int TRANSACTION_pause = 55;
        static final int TRANSACTION_play = 54;
        static final int TRANSACTION_playByPath = 14;
        static final int TRANSACTION_playByPos = 15;
        static final int TRANSACTION_playPause = 8;
        static final int TRANSACTION_prev = 9;
        static final int TRANSACTION_seek = 12;
        static final int TRANSACTION_setChapter = 21;
        static final int TRANSACTION_setFilterType = 6;
        static final int TRANSACTION_setTitle = 20;
        static final int TRANSACTION_toggleAudioChannel = 23;
        static final int TRANSACTION_toggleRepeatMode = 10;
        static final int TRANSACTION_toggleScrRate = 13;
        static final int TRANSACTION_toggleShuffleMode = 11;
        static final int TRANSACTION_toggleSubtitle = 22;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITsPlayerService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ITsPlayerService)) {
                return new Proxy(obj);
            }
            return (ITsPlayerService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    enterMedia(data.readInt());
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    changeMediaType(data.readInt());
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface(DESCRIPTOR);
                    int _result = getMediaDevice();
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                case 4:
                    data.enforceInterface(DESCRIPTOR);
                    int _result2 = getMediaType();
                    reply.writeNoException();
                    reply.writeInt(_result2);
                    return true;
                case 5:
                    data.enforceInterface(DESCRIPTOR);
                    int _result3 = getFilterType();
                    reply.writeNoException();
                    reply.writeInt(_result3);
                    return true;
                case 6:
                    data.enforceInterface(DESCRIPTOR);
                    setFilterType(data.readInt());
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface(DESCRIPTOR);
                    next();
                    reply.writeNoException();
                    return true;
                case 8:
                    data.enforceInterface(DESCRIPTOR);
                    playPause();
                    reply.writeNoException();
                    return true;
                case 9:
                    data.enforceInterface(DESCRIPTOR);
                    prev();
                    reply.writeNoException();
                    return true;
                case 10:
                    data.enforceInterface(DESCRIPTOR);
                    toggleRepeatMode();
                    reply.writeNoException();
                    return true;
                case 11:
                    data.enforceInterface(DESCRIPTOR);
                    toggleShuffleMode();
                    reply.writeNoException();
                    return true;
                case 12:
                    data.enforceInterface(DESCRIPTOR);
                    seek(data.readLong());
                    reply.writeNoException();
                    return true;
                case 13:
                    data.enforceInterface(DESCRIPTOR);
                    toggleScrRate();
                    reply.writeNoException();
                    return true;
                case 14:
                    data.enforceInterface(DESCRIPTOR);
                    playByPath(data.readString());
                    reply.writeNoException();
                    return true;
                case 15:
                    data.enforceInterface(DESCRIPTOR);
                    playByPos(data.readInt());
                    reply.writeNoException();
                    return true;
                case 16:
                    data.enforceInterface(DESCRIPTOR);
                    gotoMenu();
                    reply.writeNoException();
                    return true;
                case 17:
                    data.enforceInterface(DESCRIPTOR);
                    ZoomOut();
                    reply.writeNoException();
                    return true;
                case 18:
                    data.enforceInterface(DESCRIPTOR);
                    ZoomIn();
                    reply.writeNoException();
                    return true;
                case 19:
                    data.enforceInterface(DESCRIPTOR);
                    Rotate();
                    reply.writeNoException();
                    return true;
                case 20:
                    data.enforceInterface(DESCRIPTOR);
                    setTitle(data.readInt());
                    reply.writeNoException();
                    return true;
                case 21:
                    data.enforceInterface(DESCRIPTOR);
                    setChapter(data.readInt());
                    reply.writeNoException();
                    return true;
                case 22:
                    data.enforceInterface(DESCRIPTOR);
                    toggleSubtitle();
                    reply.writeNoException();
                    return true;
                case 23:
                    data.enforceInterface(DESCRIPTOR);
                    toggleAudioChannel();
                    reply.writeNoException();
                    return true;
                case 24:
                    data.enforceInterface(DESCRIPTOR);
                    int _result4 = getDiscType();
                    reply.writeNoException();
                    reply.writeInt(_result4);
                    return true;
                case 25:
                    data.enforceInterface(DESCRIPTOR);
                    clickDVDMenuItem(data.readInt(), data.readInt());
                    reply.writeNoException();
                    return true;
                case 26:
                    data.enforceInterface(DESCRIPTOR);
                    String _result5 = getPlayName();
                    reply.writeNoException();
                    reply.writeString(_result5);
                    return true;
                case 27:
                    data.enforceInterface(DESCRIPTOR);
                    int _result6 = getPlayStatus();
                    reply.writeNoException();
                    reply.writeInt(_result6);
                    return true;
                case 28:
                    data.enforceInterface(DESCRIPTOR);
                    int _result7 = getRepeatMode();
                    reply.writeNoException();
                    reply.writeInt(_result7);
                    return true;
                case 29:
                    data.enforceInterface(DESCRIPTOR);
                    int _result8 = getShuffleMode();
                    reply.writeNoException();
                    reply.writeInt(_result8);
                    return true;
                case 30:
                    data.enforceInterface(DESCRIPTOR);
                    long _result9 = getCurrentTime();
                    reply.writeNoException();
                    reply.writeLong(_result9);
                    return true;
                case 31:
                    data.enforceInterface(DESCRIPTOR);
                    long _result10 = getTotoalTime();
                    reply.writeNoException();
                    reply.writeLong(_result10);
                    return true;
                case 32:
                    data.enforceInterface(DESCRIPTOR);
                    int _result11 = getPlayId();
                    reply.writeNoException();
                    reply.writeInt(_result11);
                    return true;
                case 33:
                    data.enforceInterface(DESCRIPTOR);
                    int _result12 = getMediaCnt();
                    reply.writeNoException();
                    reply.writeInt(_result12);
                    return true;
                case 34:
                    data.enforceInterface(DESCRIPTOR);
                    String _result13 = getId3AlbumName();
                    reply.writeNoException();
                    reply.writeString(_result13);
                    return true;
                case 35:
                    data.enforceInterface(DESCRIPTOR);
                    String _result14 = getId3Artist();
                    reply.writeNoException();
                    reply.writeString(_result14);
                    return true;
                case 36:
                    data.enforceInterface(DESCRIPTOR);
                    String _result15 = getId3Title();
                    reply.writeNoException();
                    reply.writeString(_result15);
                    return true;
                case 37:
                    data.enforceInterface(DESCRIPTOR);
                    int _result16 = getTitleId();
                    reply.writeNoException();
                    reply.writeInt(_result16);
                    return true;
                case 38:
                    data.enforceInterface(DESCRIPTOR);
                    int _result17 = getTitleCount();
                    reply.writeNoException();
                    reply.writeInt(_result17);
                    return true;
                case 39:
                    data.enforceInterface(DESCRIPTOR);
                    int _result18 = getChapterId();
                    reply.writeNoException();
                    reply.writeInt(_result18);
                    return true;
                case 40:
                    data.enforceInterface(DESCRIPTOR);
                    int _result19 = getChapterCount();
                    reply.writeNoException();
                    reply.writeInt(_result19);
                    return true;
                case 41:
                    data.enforceInterface(DESCRIPTOR);
                    int _result20 = getSubtitleId();
                    reply.writeNoException();
                    reply.writeInt(_result20);
                    return true;
                case 42:
                    data.enforceInterface(DESCRIPTOR);
                    int _result21 = getSubtitleCount();
                    reply.writeNoException();
                    reply.writeInt(_result21);
                    return true;
                case 43:
                    data.enforceInterface(DESCRIPTOR);
                    int _result22 = getChannelId();
                    reply.writeNoException();
                    reply.writeInt(_result22);
                    return true;
                case 44:
                    data.enforceInterface(DESCRIPTOR);
                    int _result23 = getChannelCount();
                    reply.writeNoException();
                    reply.writeInt(_result23);
                    return true;
                case 45:
                    data.enforceInterface(DESCRIPTOR);
                    int _result24 = getMediaPlayerId();
                    reply.writeNoException();
                    reply.writeInt(_result24);
                    return true;
                case 46:
                    data.enforceInterface(DESCRIPTOR);
                    int _result25 = nDealMediaKey(data.readInt());
                    reply.writeNoException();
                    reply.writeInt(_result25);
                    return true;
                case 47:
                    data.enforceInterface(DESCRIPTOR);
                    String _result26 = getDvdVersion();
                    reply.writeNoException();
                    reply.writeString(_result26);
                    return true;
                case 48:
                    data.enforceInterface(DESCRIPTOR);
                    String _result27 = getMediaVersion();
                    reply.writeNoException();
                    reply.writeString(_result27);
                    return true;
                case 49:
                    data.enforceInterface(DESCRIPTOR);
                    String _result28 = getDvdUpdateInfo(data.readString());
                    reply.writeNoException();
                    reply.writeString(_result28);
                    return true;
                case 50:
                    data.enforceInterface(DESCRIPTOR);
                    String _result29 = getMediaUpdateInfo(data.readString());
                    reply.writeNoException();
                    reply.writeString(_result29);
                    return true;
                case 51:
                    data.enforceInterface(DESCRIPTOR);
                    int _result30 = mediaTask(data.readInt());
                    reply.writeNoException();
                    reply.writeInt(_result30);
                    return true;
                case 52:
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result31 = isDvdLoadOK();
                    reply.writeNoException();
                    reply.writeInt(_result31 ? 1 : 0);
                    return true;
                case 53:
                    data.enforceInterface(DESCRIPTOR);
                    Map _result32 = getMediaList();
                    reply.writeNoException();
                    reply.writeMap(_result32);
                    return true;
                case 54:
                    data.enforceInterface(DESCRIPTOR);
                    play();
                    reply.writeNoException();
                    return true;
                case 55:
                    data.enforceInterface(DESCRIPTOR);
                    pause();
                    reply.writeNoException();
                    return true;
                case 56:
                    data.enforceInterface(DESCRIPTOR);
                    dealBTCall(data.readInt());
                    reply.writeNoException();
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements ITsPlayerService {
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

            public void enterMedia(int device) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(device);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void changeMediaType(int nMediaType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(nMediaType);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int getMediaDevice() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int getMediaType() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int getFilterType() throws RemoteException {
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

            public void setFilterType(int nFilterType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(nFilterType);
                    this.mRemote.transact(6, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void next() throws RemoteException {
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

            public void playPause() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(8, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void prev() throws RemoteException {
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

            public void toggleRepeatMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(10, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void toggleShuffleMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(11, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void seek(long lmsec) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(lmsec);
                    this.mRemote.transact(12, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void toggleScrRate() throws RemoteException {
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

            public void playByPath(String filePath) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(filePath);
                    this.mRemote.transact(14, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void playByPos(int position) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(position);
                    this.mRemote.transact(15, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void gotoMenu() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(16, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void ZoomOut() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(17, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void ZoomIn() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(18, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void Rotate() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(19, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void setTitle(int titleId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(titleId);
                    this.mRemote.transact(20, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void setChapter(int chapterId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(chapterId);
                    this.mRemote.transact(21, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void toggleSubtitle() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(22, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void toggleAudioChannel() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(23, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int getDiscType() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(24, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void clickDVDMenuItem(int X, int Y) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(X);
                    _data.writeInt(Y);
                    this.mRemote.transact(25, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String getPlayName() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(26, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int getPlayStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(27, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
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
                    this.mRemote.transact(28, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int getShuffleMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(29, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public long getCurrentTime() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(30, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readLong();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public long getTotoalTime() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(31, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readLong();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int getPlayId() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(32, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int getMediaCnt() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(33, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String getId3AlbumName() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(34, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readString();
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
                    this.mRemote.transact(35, _data, _reply, 0);
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
                    this.mRemote.transact(36, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int getTitleId() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(37, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int getTitleCount() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(38, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int getChapterId() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(39, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int getChapterCount() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(40, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int getSubtitleId() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(41, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int getSubtitleCount() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(42, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int getChannelId() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(43, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int getChannelCount() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(44, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int getMediaPlayerId() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(45, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int nDealMediaKey(int nCode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(nCode);
                    this.mRemote.transact(46, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String getDvdVersion() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(47, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String getMediaVersion() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(48, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String getDvdUpdateInfo(String version) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(version);
                    this.mRemote.transact(49, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public String getMediaUpdateInfo(String version) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(version);
                    this.mRemote.transact(50, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public int mediaTask(int mcuState) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(mcuState);
                    this.mRemote.transact(51, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public boolean isDvdLoadOK() throws RemoteException {
                boolean _result = false;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(52, _data, _reply, 0);
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

            public Map getMediaList() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(53, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readHashMap(getClass().getClassLoader());
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void play() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(54, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void pause() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(55, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void dealBTCall(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    this.mRemote.transact(56, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
