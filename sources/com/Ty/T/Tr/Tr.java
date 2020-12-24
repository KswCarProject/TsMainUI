package com.Ty.T.Tr;

import android.graphics.Bitmap;
import com.Ty.T.Tr.T.Tk;
import com.Ty.T.Tr.Ty.T;
import com.Ty.T.Ty.Ty;

/* compiled from: Proguard */
final class Tr implements Runnable {

    /* renamed from: T  reason: collision with root package name */
    private final Bitmap f339T;
    private final T T9;
    private final Tk TE;
    private final Tk TZ;
    private final com.Ty.T.Tr.Tk.T Tk;
    private final String Tn;
    private final String Tr;
    private final com.Ty.T.Tr.T9.T Ty;

    public Tr(Bitmap bitmap, TZ imageLoadingInfo, Tk engine, Tk loadedFrom) {
        this.f339T = bitmap;
        this.Tr = imageLoadingInfo.f332T;
        this.Ty = imageLoadingInfo.Ty;
        this.Tn = imageLoadingInfo.Tr;
        this.T9 = imageLoadingInfo.T9.TB();
        this.Tk = imageLoadingInfo.Tk;
        this.TZ = engine;
        this.TE = loadedFrom;
    }

    public void run() {
        if (this.Ty.T9()) {
            Ty.T("ImageAware was collected by GC. Task is cancelled. [%s]", this.Tn);
            this.Tk.Tr(this.Tr, this.Ty.Tn());
        } else if (T()) {
            Ty.T("ImageAware is reused for another image. Task is cancelled. [%s]", this.Tn);
            this.Tk.Tr(this.Tr, this.Ty.Tn());
        } else {
            Ty.T("Display image in ImageAware (loaded from %1$s) [%2$s]", this.TE, this.Tn);
            this.T9.T(this.f339T, this.Ty, this.TE);
            this.TZ.Tr(this.Ty);
            this.Tk.T(this.Tr, this.Ty.Tn(), this.f339T);
        }
    }

    private boolean T() {
        return !this.Tn.equals(this.TZ.T(this.Ty));
    }
}
