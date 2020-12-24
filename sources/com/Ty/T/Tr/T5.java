package com.Ty.T.Tr;

import android.graphics.Bitmap;
import android.os.Handler;
import com.Ty.T.Tr.T.Tk;
import com.Ty.T.Ty.Ty;

/* compiled from: Proguard */
final class T5 implements Runnable {

    /* renamed from: T  reason: collision with root package name */
    private final Tk f320T;
    private final Handler Tn;
    private final Bitmap Tr;
    private final TZ Ty;

    public T5(Tk engine, Bitmap bitmap, TZ imageLoadingInfo, Handler handler) {
        this.f320T = engine;
        this.Tr = bitmap;
        this.Ty = imageLoadingInfo;
        this.Tn = handler;
    }

    public void run() {
        Ty.T("PostProcess image before displaying [%s]", this.Ty.Tr);
        TE.T(new Tr(this.Ty.T9.Tj().T(this.Tr), this.Ty, this.f320T, Tk.MEMORY_CACHE), this.Ty.T9.TO(), this.Tn, this.f320T);
    }
}
