package com.ts.MainUI;

import android.util.Log;

public class TsBuf {
    static final String TAG = "TsBuf";
    byte[] CmdBuf;
    int nDataSize = 0;
    int nRead = 0;
    int nWrite = 0;

    public TsBuf(int nNum) {
        this.nDataSize = nNum;
        this.CmdBuf = new byte[nNum];
    }

    public int Inint(int nNum) {
        this.nDataSize = nNum;
        this.CmdBuf = new byte[nNum];
        return 1;
    }

    public int Push(byte ubData) {
        if (this.nWrite < this.nDataSize) {
            byte[] bArr = this.CmdBuf;
            int i = this.nWrite;
            this.nWrite = i + 1;
            bArr[i] = ubData;
            return 1;
        } else if (this.nRead > 0) {
            int j = 0;
            int i2 = this.nRead;
            while (i2 < this.nWrite) {
                this.CmdBuf[j] = this.CmdBuf[i2];
                i2++;
                j++;
            }
            this.nRead = 0;
            this.nWrite = j;
            if (this.nWrite >= this.nDataSize) {
                return 1;
            }
            byte[] bArr2 = this.CmdBuf;
            int i3 = this.nWrite;
            this.nWrite = i3 + 1;
            bArr2[i3] = ubData;
            return 1;
        } else {
            Log.i(TAG, "LineBuf Push ERROR ...");
            return 1;
        }
    }

    public int Pop(byte[] ubData) {
        if (this.nRead >= this.nWrite) {
            return 0;
        }
        byte[] bArr = this.CmdBuf;
        int i = this.nRead;
        this.nRead = i + 1;
        ubData[0] = bArr[i];
        return 1;
    }

    public int GetData(int nIndex, byte[] ubData, int nWei) {
        if (nIndex >= GetLen()) {
            return 0;
        }
        if (this.nRead + nIndex < this.nDataSize) {
            ubData[nWei] = this.CmdBuf[this.nRead + nIndex];
            return 1;
        }
        Log.i(TAG, "LineBuf GetData ERROR ...");
        return 0;
    }

    public int GetLen() {
        return this.nWrite - this.nRead;
    }
}
