package com.Ty.T.Tr;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import com.Ty.T.Tr.T.TZ;
import com.Ty.T.Tr.Tn.Tr;
import com.Ty.T.Ty.Tn;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executor;

/* compiled from: Proguard */
public final class T9 {

    /* renamed from: T  reason: collision with root package name */
    final Resources f324T;
    final boolean T5;
    final int T6;
    final int T9;
    final com.Ty.T.Tr.Tr.Tr TB;
    final Executor TE;
    final com.Ty.T.T.T.T TF;
    final Ty TK;
    final com.Ty.T.Tr.Tn.Tr TN;
    final com.Ty.T.Tr.Tn.Tr TO;
    final Executor TZ;
    final TZ Te;
    final int Th;
    final com.Ty.T.Tr.Tn.Tr Tj;
    final com.Ty.T.Tr.TZ.T Tk;
    final int Tn;
    final com.Ty.T.T.Tr.T Tq;
    final int Tr;
    final boolean Tv;
    final int Ty;

    private T9(T builder) {
        this.f324T = builder.Tr.getResources();
        this.Tr = builder.Ty;
        this.Ty = builder.Tn;
        this.Tn = builder.T9;
        this.T9 = builder.Tk;
        this.Tk = builder.TZ;
        this.TZ = builder.TE;
        this.TE = builder.T5;
        this.Th = builder.T6;
        this.T6 = builder.Te;
        this.Te = builder.TF;
        this.TF = builder.TN;
        this.Tq = builder.TO;
        this.TK = builder.Tt;
        this.Tj = builder.TG;
        this.TB = builder.Tu;
        this.T5 = builder.Tv;
        this.Tv = builder.Th;
        this.TO = new Tr(this.Tj);
        this.TN = new Ty(this.Tj);
        com.Ty.T.Ty.Ty.T(builder.TD);
    }

    /* access modifiers changed from: package-private */
    public com.Ty.T.Tr.T.T9 T() {
        DisplayMetrics displayMetrics = this.f324T.getDisplayMetrics();
        int width = this.Tr;
        if (width <= 0) {
            width = displayMetrics.widthPixels;
        }
        int height = this.Ty;
        if (height <= 0) {
            height = displayMetrics.heightPixels;
        }
        return new com.Ty.T.Tr.T.T9(width, height);
    }

    /* compiled from: Proguard */
    public static class T {

        /* renamed from: T  reason: collision with root package name */
        public static final TZ f326T = TZ.FIFO;
        /* access modifiers changed from: private */
        public Executor T5 = null;
        /* access modifiers changed from: private */
        public int T6 = 3;
        /* access modifiers changed from: private */
        public int T9 = 0;
        private long TB = 0;
        /* access modifiers changed from: private */
        public boolean TD = false;
        /* access modifiers changed from: private */
        public Executor TE = null;
        /* access modifiers changed from: private */
        public TZ TF = f326T;
        /* access modifiers changed from: private */
        public com.Ty.T.Tr.Tn.Tr TG = null;
        private int TK = 0;
        /* access modifiers changed from: private */
        public com.Ty.T.T.T.T TN = null;
        /* access modifiers changed from: private */
        public com.Ty.T.T.Tr.T TO = null;
        /* access modifiers changed from: private */
        public com.Ty.T.Tr.TZ.T TZ = null;
        /* access modifiers changed from: private */
        public int Te = 3;
        /* access modifiers changed from: private */
        public boolean Th = false;
        private int Tj = 0;
        /* access modifiers changed from: private */
        public int Tk = 0;
        /* access modifiers changed from: private */
        public int Tn = 0;
        private boolean Tq = false;
        /* access modifiers changed from: private */
        public Context Tr;
        private com.Ty.T.T.T.Tr.T Ts = null;
        /* access modifiers changed from: private */
        public Ty Tt = null;
        /* access modifiers changed from: private */
        public com.Ty.T.Tr.Tr.Tr Tu;
        /* access modifiers changed from: private */
        public boolean Tv = false;
        /* access modifiers changed from: private */
        public int Ty = 0;

        public T(Context context) {
            this.Tr = context.getApplicationContext();
        }

        public T T(int threadPoolSize) {
            if (!(this.TE == null && this.T5 == null)) {
                com.Ty.T.Ty.Ty.Ty("threadPoolSize(), threadPriority() and tasksProcessingOrder() calls can overlap taskExecutor() and taskExecutorForCachedImages() calls.", new Object[0]);
            }
            this.T6 = threadPoolSize;
            return this;
        }

        public T Tr(int threadPriority) {
            if (!(this.TE == null && this.T5 == null)) {
                com.Ty.T.Ty.Ty.Ty("threadPoolSize(), threadPriority() and tasksProcessingOrder() calls can overlap taskExecutor() and taskExecutorForCachedImages() calls.", new Object[0]);
            }
            if (threadPriority < 1) {
                this.Te = 1;
            } else if (threadPriority > 10) {
                this.Te = 10;
            } else {
                this.Te = threadPriority;
            }
            return this;
        }

        public T T(TZ tasksProcessingType) {
            if (!(this.TE == null && this.T5 == null)) {
                com.Ty.T.Ty.Ty.Ty("threadPoolSize(), threadPriority() and tasksProcessingOrder() calls can overlap taskExecutor() and taskExecutorForCachedImages() calls.", new Object[0]);
            }
            this.TF = tasksProcessingType;
            return this;
        }

        public T T(com.Ty.T.T.Tr.T memoryCache) {
            if (this.Tj != 0) {
                com.Ty.T.Ty.Ty.Ty("memoryCache() and memoryCacheSize() calls overlap each other", new Object[0]);
            }
            this.TO = memoryCache;
            return this;
        }

        public T T(com.Ty.T.Tr.Tn.Tr imageDownloader) {
            this.TG = imageDownloader;
            return this;
        }

        public T T(Ty defaultDisplayImageOptions) {
            this.Tt = defaultDisplayImageOptions;
            return this;
        }

        public T9 T() {
            Tr();
            return new T9(this);
        }

        private void Tr() {
            if (this.TE == null) {
                this.TE = T.T(this.T6, this.Te, this.TF);
            } else {
                this.Tv = true;
            }
            if (this.T5 == null) {
                this.T5 = T.T(this.T6, this.Te, this.TF);
            } else {
                this.Th = true;
            }
            if (this.TN == null) {
                if (this.Ts == null) {
                    this.Ts = T.Tr();
                }
                this.TN = T.T(this.Tr, this.Ts, this.TB, this.TK);
            }
            if (this.TO == null) {
                this.TO = T.T(this.Tr, this.Tj);
            }
            if (this.Tq) {
                this.TO = new com.Ty.T.T.Tr.T.T(this.TO, Tn.T());
            }
            if (this.TG == null) {
                this.TG = T.T(this.Tr);
            }
            if (this.Tu == null) {
                this.Tu = T.T(this.TD);
            }
            if (this.Tt == null) {
                this.Tt = Ty.TN();
            }
        }
    }

    /* compiled from: Proguard */
    private static class Tr implements com.Ty.T.Tr.Tn.Tr {

        /* renamed from: T  reason: collision with root package name */
        private final com.Ty.T.Tr.Tn.Tr f327T;

        public Tr(com.Ty.T.Tr.Tn.Tr wrappedDownloader) {
            this.f327T = wrappedDownloader;
        }

        public InputStream T(String imageUri, Object extra) throws IOException {
            switch (Tr.T.T(imageUri)) {
                case HTTP:
                case HTTPS:
                    throw new IllegalStateException();
                default:
                    return this.f327T.T(imageUri, extra);
            }
        }
    }

    /* compiled from: Proguard */
    private static class Ty implements com.Ty.T.Tr.Tn.Tr {

        /* renamed from: T  reason: collision with root package name */
        private final com.Ty.T.Tr.Tn.Tr f328T;

        public Ty(com.Ty.T.Tr.Tn.Tr wrappedDownloader) {
            this.f328T = wrappedDownloader;
        }

        public InputStream T(String imageUri, Object extra) throws IOException {
            InputStream imageStream = this.f328T.T(imageUri, extra);
            switch (Tr.T.T(imageUri)) {
                case HTTP:
                case HTTPS:
                    return new com.Ty.T.Tr.T.Ty(imageStream);
                default:
                    return imageStream;
            }
        }
    }
}
