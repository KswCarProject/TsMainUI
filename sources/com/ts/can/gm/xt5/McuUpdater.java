package com.ts.can.gm.xt5;

import android.os.SystemClock;
import android.util.Log;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.main.common.MainSet;
import com.yyw.ts70xhw.Mcu;
import net.easyconn.platform.wrc.core.WrcManager;

/* compiled from: CanCadillacXt5UpdateActivity */
class McuUpdater {
    private static final String MCU_FILE = "wince_to_can.bin";
    public static final int UPDATE_FILE_MAX = 614400;
    public static final int UPDATE_FILE_MIN = 3072;
    private static final int UPDATE_SIZE_PER_TIME = 64;
    public static final int UPDATE_STA_FILE_ERR = 1;
    public static final int UPDATE_STA_NULL = 0;
    public static final int UPDATE_STA_UPDATE_OK = 3;
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
    private CanDataInfo.GmSb_IapReq m_IapReq = new CanDataInfo.GmSb_IapReq();

    /* compiled from: CanCadillacXt5UpdateActivity */
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
            this.mUpdateFile = "";
            if (getMcuUpdateData()) {
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
            CanJni.GmSbIapGetReq(this.m_IapReq);
            if (!(this.m_IapReq.UpdateOnce == 0 || this.m_IapReq.Update == 0)) {
                this.m_IapReq.Update = 0;
                sendFileData(this.m_IapReq.Index);
                this.mRevUpdateAck = true;
            }
            if (!this.mRevUpdateAck && this.mIsUpdating) {
                long curTick = SystemClock.uptimeMillis();
                if (curTick > this.mLastUpdateTick + 20000) {
                    this.mLastUpdateTick = curTick;
                    this.mIsUpdating = false;
                    startUpdate();
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
        byte chk = 0;
        this.mSendData[0] = 46;
        this.mSendData[1] = -80;
        this.mSendData[2] = 6;
        this.mSendData[3] = 32;
        this.mSendData[4] = 1;
        this.mSendData[5] = (byte) ((this.m_Fileinfo.len >> 24) & 255);
        this.mSendData[6] = (byte) ((this.m_Fileinfo.len >> 16) & 255);
        this.mSendData[7] = (byte) ((this.m_Fileinfo.len >> 8) & 255);
        this.mSendData[8] = (byte) (this.m_Fileinfo.len & 255);
        Log.d("SendData", "m_Fileinfo.len1=" + this.m_Fileinfo.len);
        for (int i = 1; i < 9; i++) {
            chk = (byte) (this.mSendData[i] + chk);
        }
        this.mSendData[9] = (byte) (chk ^ 255);
        Send(10);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sendReset() {
    }

    public void sendFileData(int offset) {
        byte chk1 = 0;
        byte chk2 = 0;
        int pos = offset * 64;
        if (pos + 64 <= this.m_Fileinfo.len) {
            this.mProgress = ((pos + 64) * 100) / this.m_Fileinfo.len;
            this.mSendData[0] = 46;
            this.mSendData[1] = -80;
            this.mSendData[2] = 69;
            this.mSendData[3] = WrcManager.WrcCallback.MINI_KEY_1;
            this.mSendData[4] = (byte) (offset >> 8);
            this.mSendData[5] = (byte) offset;
            this.mSendData[6] = 64;
            for (int i = 0; i < 64; i++) {
                this.mSendData[i + 7] = this.m_Fileinfo.data[pos + i];
                chk1 = (byte) (this.mSendData[i + 7] + chk1);
            }
            this.mSendData[71] = chk1;
            for (int i2 = 1; i2 < 72; i2++) {
                chk2 = (byte) (this.mSendData[i2] + chk2);
            }
            this.mSendData[72] = (byte) (chk2 ^ 255);
            Send(73);
            Log.d("SendData", "OffSet = " + offset);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            this.mSendData[0] = 46;
            this.mSendData[1] = -80;
            this.mSendData[2] = 3;
            this.mSendData[3] = WrcManager.WrcCallback.MINI_KEY_1;
            this.mSendData[4] = -1;
            this.mSendData[5] = -1;
            for (int i3 = 1; i3 < 6; i3++) {
                chk2 = (byte) (this.mSendData[i3] + chk2);
            }
            this.mSendData[6] = (byte) (chk2 ^ 255);
            Send(7);
        }
    }

    private void Send(int len) {
        Mcu.SendCanMsg(this.mSendData, len);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0044 A[SYNTHETIC, Splitter:B:20:0x0044] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0053 A[SYNTHETIC, Splitter:B:28:0x0053] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x005f A[SYNTHETIC, Splitter:B:34:0x005f] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:25:0x004e=Splitter:B:25:0x004e, B:17:0x003f=Splitter:B:17:0x003f} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.ts.can.gm.xt5.McuUpdater.FileInfo getFileData(java.lang.String r9) {
        /*
            r8 = this;
            com.ts.can.gm.xt5.McuUpdater$FileInfo r5 = new com.ts.can.gm.xt5.McuUpdater$FileInfo
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
        throw new UnsupportedOperationException("Method not decompiled: com.ts.can.gm.xt5.McuUpdater.getFileData(java.lang.String):com.ts.can.gm.xt5.McuUpdater$FileInfo");
    }

    public boolean getMcuFileData(String strFile) {
        String[] mMcuPath = MainSet.GetInstance().GetMountedStorage();
        int i = 0;
        while (i < mMcuPath.length) {
            this.m_Fileinfo = getFileData(String.valueOf(mMcuPath[i]) + "/" + strFile);
            if (this.m_Fileinfo.len < 3072 || this.m_Fileinfo.len > 614400) {
                i++;
            } else {
                Log.d("SendData", "m_Fileinfo.len=" + this.m_Fileinfo.len);
                this.mUpdateFile = strFile;
                return true;
            }
        }
        return false;
    }

    public boolean getMcuUpdateData() {
        if (getMcuFileData(MCU_FILE)) {
            return true;
        }
        return false;
    }
}
