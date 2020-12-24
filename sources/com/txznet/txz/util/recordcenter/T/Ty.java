package com.txznet.txz.util.recordcenter.T;

import com.txznet.comm.Tr.Tr.Tn;
import java.io.IOException;

/* compiled from: Proguard */
public class Ty {

    /* renamed from: T  reason: collision with root package name */
    private int f906T = 32000;
    private byte[] Tr = null;
    private int Ty = 0;

    public Ty(int cacheSize) {
        this.f906T = cacheSize;
    }

    public void T(byte[] data, int offset, int len) {
        if (this.Tr == null) {
            this.Tr = new byte[this.f906T];
        }
        if (len > this.Tr.length) {
            offset += len - this.Tr.length;
            len = this.Tr.length;
        }
        if (len <= this.Tr.length - this.Ty) {
            System.arraycopy(data, offset, this.Tr, this.Ty, len);
        } else {
            int remainLength = this.Tr.length - this.Ty;
            System.arraycopy(data, offset, this.Tr, this.Ty, remainLength);
            System.arraycopy(data, offset + remainLength, this.Tr, 0, len - remainLength);
        }
        this.Ty = (this.Ty + len) % this.Tr.length;
    }

    public int T(T writer, int dataLength) throws IOException {
        Tn.T("TraceCacheBuffer readBySize, dataLength=" + dataLength);
        if (dataLength <= 0) {
            return 0;
        }
        if (this.Tr == null) {
            Tn.Tn("TraceCacheBuffer readBySize null buffer");
            return 0;
        }
        Tn.T("TraceCacheBuffer readBySize buffer length=" + this.Tr.length + ", write=" + this.Ty);
        int dataTail = this.Ty;
        if (dataLength <= dataTail) {
            return writer.T(this.Tr, dataTail - dataLength, dataLength);
        }
        if (dataLength > this.Tr.length) {
            dataLength = this.Tr.length;
        }
        return writer.T(this.Tr, 0, dataTail) + writer.T(this.Tr, this.Tr.length - (dataLength - dataTail), dataLength - dataTail);
    }
}
