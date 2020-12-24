package com.txznet.txz.util.recordcenter.T;

import android.os.SystemClock;
import java.io.IOException;

/* compiled from: Proguard */
public class Tn extends Ty {
    public Tn() {
        this(32000);
    }

    public Tn(int cacheSize) {
        super(cacheSize);
    }

    public int T(T writer, long clock) throws IOException {
        com.txznet.comm.Tr.Tr.Tn.T("TraceCacheBuffer_PcmMono16K readByClock, clock=" + clock);
        if (clock <= 0) {
            return 0;
        }
        return Tr(writer, (int) (SystemClock.elapsedRealtime() - clock));
    }

    public int Tr(T writer, int millSecond) throws IOException {
        com.txznet.comm.Tr.Tr.Tn.T("TraceCacheBuffer_PcmMono16K readByDurnation, millSecond=" + millSecond);
        if (millSecond <= 0) {
            return 0;
        }
        return super.T(writer, millSecond * 32);
    }
}
