package com.txznet.txz.util.recordcenter.T;

import com.txznet.comm.Tr.Tr.Tn;
import java.io.IOException;

/* compiled from: Proguard */
public class Tr {

    /* renamed from: T  reason: collision with root package name */
    private byte[] f909T;
    private int Tn;
    private int Tr;
    private int Ty;

    public Tr() {
        this(32000);
    }

    public Tr(int cacheSize) {
        this.f909T = null;
        this.Tr = 0;
        this.Ty = 0;
        this.Tn = 0;
        this.f909T = new byte[cacheSize];
    }

    public String T() {
        return toString();
    }

    public void T(T writer, Runnable runIdle) throws IOException {
        int r;
        while (true) {
            if (runIdle != null) {
                runIdle.run();
            }
            int fIndex = this.Tn;
            int writeIndex = this.Ty;
            int readIndex = this.Tr;
            if (writeIndex != readIndex) {
                if (writeIndex > readIndex) {
                    r = writer.T(this.f909T, readIndex, writeIndex - readIndex);
                } else {
                    r = writer.T(this.f909T, readIndex, this.f909T.length - readIndex);
                }
                int readIndex2 = (readIndex + r) % this.f909T.length;
                if (fIndex == this.Tn) {
                    this.Tr = readIndex2;
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    public int T(byte[] data, int offset, int len) {
        int writeIndex;
        int ret = -1;
        int fIndex = this.Tn;
        int readIndex = this.Tr;
        int writeIndex2 = this.Ty;
        if (readIndex > writeIndex2) {
            ret = len;
            if (len > (readIndex - writeIndex2) - 4) {
                ret = (readIndex - writeIndex2) - 4;
                Tn.Ty("discard record data size[" + (len - ret) + "], read[" + readIndex + "], write[" + writeIndex2 + "]: " + T());
            }
            System.arraycopy(data, offset, this.f909T, writeIndex2, ret);
            writeIndex = writeIndex2 + ret;
        } else if (len <= this.f909T.length - writeIndex2) {
            System.arraycopy(data, offset, this.f909T, writeIndex2, len);
            writeIndex = (writeIndex2 + len) % this.f909T.length;
        } else if (readIndex == 0) {
            ret = (this.f909T.length - writeIndex2) - 1;
            Tn.Ty("discard record data size: " + (len - ret) + "], read[" + readIndex + "], write[" + writeIndex2 + "]: " + T());
            System.arraycopy(data, offset, this.f909T, writeIndex2, ret);
            writeIndex = writeIndex2 + ret;
        } else {
            int ret2 = this.f909T.length - writeIndex2;
            System.arraycopy(data, offset, this.f909T, writeIndex2, ret2);
            if (fIndex != this.Tn) {
                return -999;
            }
            this.Ty = 0;
            int w = T(data, offset + ret2, len - ret2);
            if (w >= 0) {
                return w + ret2;
            }
            return w;
        }
        if (fIndex != this.Tn) {
            return -999;
        }
        this.Ty = writeIndex;
        return ret;
    }
}
