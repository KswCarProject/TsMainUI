package com.txznet.txz.util.recordcenter;

import android.os.Environment;
import android.text.TextUtils;
import com.Tn.Tr.TZ.T;
import com.Tr.T.T.T9;
import com.txznet.comm.Tr.Tr.Tn;
import com.txznet.sdk.TXZResourceManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.zip.CRC32;

/* compiled from: Proguard */
public class Ty {

    /* renamed from: T  reason: collision with root package name */
    public static String f915T;
    public static boolean Tr = new File(T(), "test_definit_voice_name.debug").exists();
    private static String Ty = TXZResourceManager.STYLE_DEFAULT;
    private int T5 = 0;
    private RandomAccessFile T9;
    private T.C0004T TE;
    private long TZ;
    private int Tk;
    private File Tn;
    private int Tv = 0;

    public static File T() {
        try {
            return new File(Environment.getExternalStorageDirectory(), "txz");
        } catch (Exception e) {
            return new File(".");
        }
    }

    public static Ty T(File f, T.C0004T mRecordData) {
        if (f == null || mRecordData == null) {
            return null;
        }
        if (TextUtils.isEmpty(Ty)) {
            Tn.Tn("RecordFile:key is null");
            return null;
        }
        Ty mRecordFile = new Ty();
        byte[] mRecordDataBuffer = T.C0004T.T((T9) mRecordData);
        mRecordFile.Tk = 1;
        mRecordFile.TZ = 0;
        mRecordFile.TE = mRecordData;
        mRecordFile.T5 = mRecordDataBuffer.length;
        mRecordFile.Tn = f;
        try {
            mRecordFile.T9 = new RandomAccessFile(f, "rw");
            mRecordFile.T9.writeByte(82);
            mRecordFile.T9.writeByte(70);
            mRecordFile.T9.writeInt(mRecordFile.Tk);
            mRecordFile.T9.writeLong(mRecordFile.TZ);
            mRecordFile.T9.writeBytes("00000000");
            mRecordFile.T9.writeInt(mRecordFile.T5);
            mRecordFile.T9.writeInt(mRecordFile.Tv);
            mRecordFile.T9.write(mRecordDataBuffer);
            if (mRecordFile.T9 == null) {
                return mRecordFile;
            }
            try {
                mRecordFile.T9.close();
                return mRecordFile;
            } catch (IOException e) {
                return mRecordFile;
            }
        } catch (IOException e2) {
            if (mRecordFile.T9 != null) {
                try {
                    mRecordFile.T9.close();
                } catch (IOException e3) {
                }
            }
            return null;
        } catch (Throwable th) {
            if (mRecordFile.T9 != null) {
                try {
                    mRecordFile.T9.close();
                } catch (IOException e4) {
                }
            }
            throw th;
        }
    }

    public void T(File file) {
        try {
            this.T9 = new RandomAccessFile(this.Tn, "rw");
            InputStream fis = new FileInputStream(file);
            int voiceSize = (int) file.length();
            byte[] mBuffer = new byte[((int) file.length())];
            for (int offset = 0; fis.read(mBuffer, offset, voiceSize + 0) > 0 && voiceSize > 0; offset = 0 + 0) {
            }
            fis.close();
            CRC32 mCrc32 = new CRC32();
            mCrc32.update(mBuffer);
            this.TZ = mCrc32.getValue();
            byte[] mEncryptedBuffer = com.txznet.txz.util.T.T(com.txznet.txz.util.Tn.T(this.TE.TE + TXZResourceManager.STYLE_DEFAULT + this.TZ + Ty), mBuffer);
            this.T9.seek(6);
            this.T9.writeLong(this.TZ);
            this.T9.seek((long) (this.T5 + 30));
            this.T9.write(mEncryptedBuffer);
            this.Tv = (int) this.T9.length();
            this.T9.seek(26);
            this.T9.writeInt(this.Tv);
            Tn.T("RecordFile completeRecordFile name = " + this.Tn.getName() + " ,CRC = " + this.TZ + " ,TotalSize = " + this.Tv);
            if (this.T9 != null) {
                try {
                    this.T9.close();
                } catch (IOException e) {
                }
            }
        } catch (IOException e2) {
            if (this.T9 != null) {
                try {
                    this.T9.close();
                } catch (IOException e3) {
                }
            }
        } catch (Throwable th) {
            if (this.T9 != null) {
                try {
                    this.T9.close();
                } catch (IOException e4) {
                }
            }
            throw th;
        }
    }
}
