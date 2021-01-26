package com.Ty.T.Tr;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import com.Ty.T.Tr.T.Tn;
import com.Ty.T.Tr.Ty.T;

/* compiled from: Proguard */
public final class Ty {
    /* access modifiers changed from: private */

    /* renamed from: T  reason: collision with root package name */
    public final int f347T;
    /* access modifiers changed from: private */
    public final boolean T5;
    /* access modifiers changed from: private */
    public final int T6;
    /* access modifiers changed from: private */
    public final Drawable T9;
    /* access modifiers changed from: private */
    public final T TB;
    /* access modifiers changed from: private */
    public final boolean TE;
    /* access modifiers changed from: private */
    public final com.Ty.T.Tr.TZ.T TF;
    /* access modifiers changed from: private */
    public final Handler TK;
    /* access modifiers changed from: private */
    public final boolean TO;
    /* access modifiers changed from: private */
    public final boolean TZ;
    /* access modifiers changed from: private */
    public final boolean Te;
    /* access modifiers changed from: private */
    public final BitmapFactory.Options Th;
    /* access modifiers changed from: private */
    public final com.Ty.T.Tr.TZ.T Tj;
    /* access modifiers changed from: private */
    public final Drawable Tk;
    /* access modifiers changed from: private */
    public final Drawable Tn;
    /* access modifiers changed from: private */
    public final Object Tq;
    /* access modifiers changed from: private */
    public final int Tr;
    /* access modifiers changed from: private */
    public final Tn Tv;
    /* access modifiers changed from: private */
    public final int Ty;

    private Ty(T builder) {
        this.f347T = builder.f348T;
        this.Tr = builder.Tr;
        this.Ty = builder.Ty;
        this.Tn = builder.Tn;
        this.T9 = builder.T9;
        this.Tk = builder.Tk;
        this.TZ = builder.TZ;
        this.TE = builder.TE;
        this.T5 = builder.T5;
        this.Tv = builder.Tv;
        this.Th = builder.Th;
        this.T6 = builder.T6;
        this.Te = builder.Te;
        this.Tq = builder.Tq;
        this.TF = builder.TF;
        this.Tj = builder.Tj;
        this.TB = builder.TB;
        this.TK = builder.TK;
        this.TO = builder.TO;
    }

    public boolean T() {
        return (this.Tn == null && this.f347T == 0) ? false : true;
    }

    public boolean Tr() {
        return (this.T9 == null && this.Tr == 0) ? false : true;
    }

    public boolean Ty() {
        return (this.Tk == null && this.Ty == 0) ? false : true;
    }

    public boolean Tn() {
        return this.TF != null;
    }

    public boolean T9() {
        return this.Tj != null;
    }

    public boolean Tk() {
        return this.T6 > 0;
    }

    public Drawable T(Resources res) {
        return this.f347T != 0 ? res.getDrawable(this.f347T) : this.Tn;
    }

    public Drawable Tr(Resources res) {
        return this.Tr != 0 ? res.getDrawable(this.Tr) : this.T9;
    }

    public Drawable Ty(Resources res) {
        return this.Ty != 0 ? res.getDrawable(this.Ty) : this.Tk;
    }

    public boolean TZ() {
        return this.TZ;
    }

    public boolean TE() {
        return this.TE;
    }

    public boolean T5() {
        return this.T5;
    }

    public Tn Tv() {
        return this.Tv;
    }

    public BitmapFactory.Options Th() {
        return this.Th;
    }

    public int T6() {
        return this.T6;
    }

    public boolean Te() {
        return this.Te;
    }

    public Object Tq() {
        return this.Tq;
    }

    public com.Ty.T.Tr.TZ.T TF() {
        return this.TF;
    }

    public com.Ty.T.Tr.TZ.T Tj() {
        return this.Tj;
    }

    public T TB() {
        return this.TB;
    }

    public Handler TK() {
        return this.TK;
    }

    /* access modifiers changed from: package-private */
    public boolean TO() {
        return this.TO;
    }

    /* compiled from: Proguard */
    public static class T {
        /* access modifiers changed from: private */

        /* renamed from: T  reason: collision with root package name */
        public int f348T = 0;
        /* access modifiers changed from: private */
        public boolean T5 = false;
        /* access modifiers changed from: private */
        public int T6 = 0;
        /* access modifiers changed from: private */
        public Drawable T9 = null;
        /* access modifiers changed from: private */
        public com.Ty.T.Tr.Ty.T TB = T.Ty();
        /* access modifiers changed from: private */
        public boolean TE = false;
        /* access modifiers changed from: private */
        public com.Ty.T.Tr.TZ.T TF = null;
        /* access modifiers changed from: private */
        public Handler TK = null;
        /* access modifiers changed from: private */
        public boolean TO = false;
        /* access modifiers changed from: private */
        public boolean TZ = false;
        /* access modifiers changed from: private */
        public boolean Te = false;
        /* access modifiers changed from: private */
        public BitmapFactory.Options Th = new BitmapFactory.Options();
        /* access modifiers changed from: private */
        public com.Ty.T.Tr.TZ.T Tj = null;
        /* access modifiers changed from: private */
        public Drawable Tk = null;
        /* access modifiers changed from: private */
        public Drawable Tn = null;
        /* access modifiers changed from: private */
        public Object Tq = null;
        /* access modifiers changed from: private */
        public int Tr = 0;
        /* access modifiers changed from: private */
        public Tn Tv = Tn.IN_SAMPLE_POWER_OF_2;
        /* access modifiers changed from: private */
        public int Ty = 0;

        public T T(boolean resetViewBeforeLoading) {
            this.TZ = resetViewBeforeLoading;
            return this;
        }

        public T Tr(boolean cacheInMemory) {
            this.TE = cacheInMemory;
            return this;
        }

        public T Ty(boolean cacheOnDisk) {
            this.T5 = cacheOnDisk;
            return this;
        }

        public T T(Tn imageScaleType) {
            this.Tv = imageScaleType;
            return this;
        }

        public T T(Bitmap.Config bitmapConfig) {
            if (bitmapConfig == null) {
                throw new IllegalArgumentException("bitmapConfig can't be null");
            }
            this.Th.inPreferredConfig = bitmapConfig;
            return this;
        }

        public T T(Ty options) {
            this.f348T = options.f347T;
            this.Tr = options.Tr;
            this.Ty = options.Ty;
            this.Tn = options.Tn;
            this.T9 = options.T9;
            this.Tk = options.Tk;
            this.TZ = options.TZ;
            this.TE = options.TE;
            this.T5 = options.T5;
            this.Tv = options.Tv;
            this.Th = options.Th;
            this.T6 = options.T6;
            this.Te = options.Te;
            this.Tq = options.Tq;
            this.TF = options.TF;
            this.Tj = options.Tj;
            this.TB = options.TB;
            this.TK = options.TK;
            this.TO = options.TO;
            return this;
        }

        public Ty T() {
            return new Ty(this);
        }
    }

    public static Ty TN() {
        return new T().T();
    }
}
