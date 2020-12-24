package com.txznet.txz.util.recordcenter.T;

import java.io.IOException;
import java.io.OutputStream;

/* compiled from: Proguard */
public interface T {
    int T(byte[] bArr, int i, int i2) throws IOException;

    /* renamed from: com.txznet.txz.util.recordcenter.T.T$T  reason: collision with other inner class name */
    /* compiled from: Proguard */
    public static class C0023T implements T {

        /* renamed from: T  reason: collision with root package name */
        private OutputStream f904T;

        public C0023T(OutputStream out) {
            this.f904T = out;
        }

        public int T(byte[] data, int offset, int len) throws IOException {
            this.f904T.write(data, offset, len);
            return len;
        }
    }
}
