package com.ts.can;

import android.os.SystemClock;
import android.util.Log;
import com.ts.main.common.MainSet;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.Mcu;

/* compiled from: CanMcuUpdateActivity */
class McuUpdater {
    private static final String MCU_FILE = "ts24x.bin";
    private static final String MCU_FILE2 = "ts70.bin";
    private static final String MCU_M8259_FILE = "ts59.bin";
    private static final String MCU_M8259_FILE2 = "ts59-b.bin";
    public static final int UPDATE_FILE_MAX = 153600;
    public static final int UPDATE_FILE_MIN = 32768;
    private static final int UPDATE_SIZE_PER_TIME = 64;
    public static final int UPDATE_STA_FILE_ERR = 1;
    public static final int UPDATE_STA_FLUSH_ERR = 4;
    public static final int UPDATE_STA_FLUSH_OK = 5;
    public static final int UPDATE_STA_MCU_ENC = 3;
    public static final int UPDATE_STA_NULL = 0;
    public static final int UPDATE_STA_UPDATE_OK = 6;
    public static final int UPDATE_STA_UPDATING = 2;
    private static McuUpdater mInstance;
    private boolean mIsUpdating = false;
    private long mLastUpdateTick = 0;
    private boolean mNewSendFmt = false;
    private int mProgress = 0;
    private byte[] mReadBuf = new byte[16];
    private int mReadCnt = 0;
    private boolean mRevUpdateAck = false;
    private byte[] mRxBuf = new byte[16];
    private byte[] mSendData = new byte[80];
    private int mSendLen = 0;
    private int mSta = 0;
    private String mUpdateFile;
    private FileInfo m_Fileinfo = null;

    /* compiled from: CanMcuUpdateActivity */
    public class FileInfo {
        public byte[] data;
        public int len;

        public FileInfo() {
        }
    }

    public boolean IsUpdating() {
        return this.mIsUpdating;
    }

    public static McuUpdater getInstance() {
        if (mInstance == null) {
            mInstance = new McuUpdater();
        }
        return mInstance;
    }

    private McuUpdater() {
    }

    public int startUpdate() {
        if (!this.mIsUpdating) {
            setSta(1);
            this.mRevUpdateAck = false;
            this.mLastUpdateTick = SystemClock.uptimeMillis();
            this.mUpdateFile = TXZResourceManager.STYLE_DEFAULT;
            if (getMcuUpdateData()) {
                Mcu.McuUpdateStart();
                sendStart();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sendStart();
                setSta(2);
                this.mIsUpdating = true;
                this.mProgress = 0;
                this.mNewSendFmt = false;
            }
        }
        return 0;
    }

    public int main() {
        if (this.mIsUpdating) {
            int len = Mcu.McuUpdateRead(this.mRxBuf, 15);
            if (len > 0) {
                for (int i = 0; i < len; i++) {
                    Analysis(this.mRxBuf[i]);
                }
            }
            if (!this.mRevUpdateAck) {
                long curTick = SystemClock.uptimeMillis();
                if (curTick > this.mLastUpdateTick + 4000) {
                    this.mLastUpdateTick = curTick;
                }
            }
        }
        return 0;
    }

    public int getProgress() {
        return this.mProgress;
    }

    public int getSta() {
        return this.mSta;
    }

    private void setSta(int sta) {
        this.mSta = sta;
    }

    private void sendStart() {
        this.mSendData[0] = 35;
        this.mSendData[1] = 85;
        this.mSendData[2] = 2;
        this.mSendData[3] = 83;
        if (CanFunc.getInstance().IsCore() == 1) {
            if (this.mUpdateFile.equals(MCU_M8259_FILE2)) {
                this.mSendData[4] = 74;
                this.mSendData[5] = 78;
            } else if (this.mUpdateFile.equals(MCU_M8259_FILE)) {
                this.mSendData[4] = 66;
                this.mSendData[5] = 70;
            }
        } else if (this.mUpdateFile.equals(MCU_FILE)) {
            this.mSendData[4] = 74;
            this.mSendData[5] = 78;
        } else if (this.mUpdateFile.equals(MCU_FILE2)) {
            this.mSendData[4] = 66;
            this.mSendData[5] = 70;
        }
        Send(6);
    }

    public void sendReset() {
        this.mSendData[0] = 35;
        this.mSendData[1] = 85;
        this.mSendData[2] = 82;
        Send(71);
    }

    private void sendFileData(int offset) {
        int pos = offset * 64;
        if (pos + 64 <= this.m_Fileinfo.len) {
            this.mProgress = ((pos + 64) * 100) / this.m_Fileinfo.len;
            this.mSendData[0] = 35;
            this.mSendData[1] = 85;
            this.mSendData[2] = 68;
            byte chk1 = 0;
            byte chk2 = 0;
            for (int i = 0; i < 64; i++) {
                this.mSendData[i + 3] = this.m_Fileinfo.data[pos + i];
                chk1 = (byte) (this.mSendData[i + 3] + chk1);
                chk2 = (byte) (this.mSendData[i + 3] ^ chk2);
            }
            this.mSendData[67] = (byte) (offset >> 8);
            byte chk12 = (byte) (this.mSendData[67] + chk1);
            byte chk22 = (byte) (this.mSendData[67] ^ chk2);
            this.mSendData[68] = (byte) (offset & 255);
            byte chk13 = (byte) (this.mSendData[68] + chk12);
            byte chk23 = (byte) (this.mSendData[68] ^ chk22);
            this.mSendData[69] = chk13;
            this.mSendData[70] = chk23;
            Send(71);
            Log.d("SendData", "OffSet = " + offset);
        }
    }

    private void Send(int len) {
        Mcu.McuUpdateSend(this.mSendData, len);
    }

    private void Analysis(byte data) {
        if (this.mReadCnt < 15) {
            this.mReadBuf[this.mReadCnt] = data;
        }
        switch (this.mReadCnt) {
            case 0:
                if (35 == data) {
                    this.mReadCnt++;
                    return;
                }
                return;
            case 1:
                if (85 == data) {
                    this.mReadCnt++;
                    return;
                } else {
                    this.mReadCnt = 0;
                    return;
                }
            case 2:
            case 3:
                this.mReadCnt++;
                return;
            case 4:
                if (75 == this.mReadBuf[2] && 78 == this.mReadBuf[3] && 71 == this.mReadBuf[4]) {
                    this.mReadCnt = 0;
                    this.mIsUpdating = false;
                    setSta(4);
                    this.mRevUpdateAck = true;
                    return;
                } else if (69 == this.mReadBuf[2] && 78 == this.mReadBuf[3] && 71 == this.mReadBuf[4]) {
                    this.mReadCnt = 0;
                    this.mIsUpdating = false;
                    setSta(3);
                    this.mRevUpdateAck = true;
                    return;
                } else if (75 == this.mReadBuf[2] && 79 == this.mReadBuf[3] && 75 == this.mReadBuf[4]) {
                    this.mReadCnt = 0;
                    setSta(5);
                    this.mRevUpdateAck = true;
                    return;
                } else {
                    this.mReadCnt++;
                    return;
                }
            case 5:
                this.mReadCnt++;
                return;
            case 6:
                this.mReadCnt = 0;
                if (this.mReadBuf[3] + this.mReadBuf[5] == -1 && this.mReadBuf[4] + this.mReadBuf[6] == -1) {
                    this.mRevUpdateAck = true;
                    if (-1 == this.mReadBuf[3] && -1 == this.mReadBuf[4]) {
                        this.mIsUpdating = false;
                        setSta(6);
                        this.mProgress = 100;
                        return;
                    } else if (-1 == this.mReadBuf[3] && -2 == this.mReadBuf[4]) {
                        this.mNewSendFmt = true;
                        return;
                    } else {
                        sendFileData((this.mReadBuf[4] & 255) | ((this.mReadBuf[3] & 255) << 8));
                        return;
                    }
                } else {
                    return;
                }
            default:
                this.mReadCnt = 0;
                String strData = TXZResourceManager.STYLE_DEFAULT;
                for (int i = 0; i <= this.mReadCnt; i++) {
                    strData = String.valueOf(strData) + String.format("%02X ", new Object[]{Byte.valueOf(this.mReadBuf[i])});
                }
                Log.e("McuCmdErr", strData);
                return;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0044 A[SYNTHETIC, Splitter:B:20:0x0044] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0053 A[SYNTHETIC, Splitter:B:28:0x0053] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x005f A[SYNTHETIC, Splitter:B:34:0x005f] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:25:0x004e=Splitter:B:25:0x004e, B:17:0x003f=Splitter:B:17:0x003f} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.ts.can.McuUpdater.FileInfo getFileData(java.lang.String r9) {
        /*
            r8 = this;
            com.ts.can.McuUpdater$FileInfo r5 = new com.ts.can.McuUpdater$FileInfo
            r5.<init>()
            r7 = 0
            r5.len = r7
            java.io.File r6 = new java.io.File
            r6.<init>(r9)
            boolean r7 = r6.exists()
            if (r7 == 0) goto L_0x0019
            boolean r7 = r6.isDirectory()
            if (r7 == 0) goto L_0x001a
        L_0x0019:
            return r5
        L_0x001a:
            r3 = 0
            r0 = 0
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x003e, IOException -> 0x004d }
            r4.<init>(r6)     // Catch:{ FileNotFoundException -> 0x003e, IOException -> 0x004d }
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x0076, all -> 0x006f }
            r1.<init>(r4)     // Catch:{ FileNotFoundException -> 0x007d, IOException -> 0x0076, all -> 0x006f }
            int r7 = r4.available()     // Catch:{ FileNotFoundException -> 0x0080, IOException -> 0x0079, all -> 0x0072 }
            byte[] r7 = new byte[r7]     // Catch:{ FileNotFoundException -> 0x0080, IOException -> 0x0079, all -> 0x0072 }
            r5.data = r7     // Catch:{ FileNotFoundException -> 0x0080, IOException -> 0x0079, all -> 0x0072 }
            byte[] r7 = r5.data     // Catch:{ FileNotFoundException -> 0x0080, IOException -> 0x0079, all -> 0x0072 }
            int r7 = r1.read(r7)     // Catch:{ FileNotFoundException -> 0x0080, IOException -> 0x0079, all -> 0x0072 }
            r5.len = r7     // Catch:{ FileNotFoundException -> 0x0080, IOException -> 0x0079, all -> 0x0072 }
            if (r1 == 0) goto L_0x006c
            r1.close()     // Catch:{ IOException -> 0x0068 }
            r0 = r1
            r3 = r4
            goto L_0x0019
        L_0x003e:
            r2 = move-exception
        L_0x003f:
            r2.printStackTrace()     // Catch:{ all -> 0x005c }
            if (r0 == 0) goto L_0x0019
            r0.close()     // Catch:{ IOException -> 0x0048 }
            goto L_0x0019
        L_0x0048:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0019
        L_0x004d:
            r2 = move-exception
        L_0x004e:
            r2.printStackTrace()     // Catch:{ all -> 0x005c }
            if (r0 == 0) goto L_0x0019
            r0.close()     // Catch:{ IOException -> 0x0057 }
            goto L_0x0019
        L_0x0057:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0019
        L_0x005c:
            r7 = move-exception
        L_0x005d:
            if (r0 == 0) goto L_0x0062
            r0.close()     // Catch:{ IOException -> 0x0063 }
        L_0x0062:
            throw r7
        L_0x0063:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0062
        L_0x0068:
            r2 = move-exception
            r2.printStackTrace()
        L_0x006c:
            r0 = r1
            r3 = r4
            goto L_0x0019
        L_0x006f:
            r7 = move-exception
            r3 = r4
            goto L_0x005d
        L_0x0072:
            r7 = move-exception
            r0 = r1
            r3 = r4
            goto L_0x005d
        L_0x0076:
            r2 = move-exception
            r3 = r4
            goto L_0x004e
        L_0x0079:
            r2 = move-exception
            r0 = r1
            r3 = r4
            goto L_0x004e
        L_0x007d:
            r2 = move-exception
            r3 = r4
            goto L_0x003f
        L_0x0080:
            r2 = move-exception
            r0 = r1
            r3 = r4
            goto L_0x003f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.can.McuUpdater.getFileData(java.lang.String):com.ts.can.McuUpdater$FileInfo");
    }

    public boolean getMcuFileData(String strFile) {
        int z = 0;
        int f = 0;
        String[] mMcuPath = MainSet.GetInstance().GetMountedStorage();
        int i = 0;
        while (i < mMcuPath.length) {
            this.m_Fileinfo = getFileData(String.valueOf(mMcuPath[i]) + "/" + strFile);
            if (this.m_Fileinfo.len < 32768 || this.m_Fileinfo.len > 153600) {
                i++;
            } else {
                for (int i2 = 0; i2 < 64; i2++) {
                    if (this.m_Fileinfo.data[i2] == 0) {
                        z++;
                    }
                    if (this.m_Fileinfo.data[i2] == -1) {
                        f++;
                    }
                }
                Log.d("SendData", "FileData z= " + z);
                Log.d("SendData", "FileData f= " + f);
                if (z > 45 || f > 45) {
                    return false;
                }
                this.mUpdateFile = strFile;
                return true;
            }
        }
        return false;
    }

    public boolean getMcuUpdateData() {
        if (CanFunc.getInstance().IsCore() == 1) {
            if (getMcuFileData(MCU_M8259_FILE2)) {
                return true;
            }
            return getMcuFileData(MCU_M8259_FILE);
        } else if (!getMcuFileData(MCU_FILE)) {
            return getMcuFileData(MCU_FILE2);
        } else {
            return true;
        }
    }
}
