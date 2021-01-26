package com.Ty.T.Tr;

import android.graphics.Bitmap;
import android.os.Handler;
import com.Ty.T.Tr.T.T9;
import com.Ty.T.Tr.T.Tk;
import com.Ty.T.Tr.T.Tn;
import com.Ty.T.Tr.T.Tr;
import com.Ty.T.Tr.Tn.Tr;
import com.Ty.T.Tr.Ty;
import com.Ty.T.Ty.Tr;
import com.Ty.T.Ty.Ty;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: Proguard */
final class TE implements Tr.T, Runnable {

    /* renamed from: T  reason: collision with root package name */
    final String f330T;
    /* access modifiers changed from: private */
    public final T9 T5;
    private final com.Ty.T.Tr.Tn.Tr T6;
    final com.Ty.T.Tr.Tk.Tr T9;
    private Tk TB = Tk.NETWORK;
    private final Handler TE;
    private final T9 TF;
    private final TZ TZ;
    private final com.Ty.T.Tr.Tr.Tr Te;
    private final com.Ty.T.Tr.Tn.Tr Th;
    private final boolean Tj;
    private final Tk Tk;
    final com.Ty.T.Tr.Tk.T Tn;
    private final String Tq;
    final com.Ty.T.Tr.T9.T Tr;
    private final com.Ty.T.Tr.Tn.Tr Tv;
    final Ty Ty;

    public TE(Tk engine, TZ imageLoadingInfo, Handler handler) {
        this.Tk = engine;
        this.TZ = imageLoadingInfo;
        this.TE = handler;
        this.T5 = engine.f336T;
        this.Tv = this.T5.Tj;
        this.Th = this.T5.TO;
        this.T6 = this.T5.TN;
        this.Te = this.T5.TB;
        this.f330T = imageLoadingInfo.f335T;
        this.Tq = imageLoadingInfo.Tr;
        this.Tr = imageLoadingInfo.Ty;
        this.TF = imageLoadingInfo.Tn;
        this.Ty = imageLoadingInfo.T9;
        this.Tn = imageLoadingInfo.Tk;
        this.T9 = imageLoadingInfo.TZ;
        this.Tj = this.Ty.TO();
    }

    public void run() {
        if (!Tr() && !Ty()) {
            ReentrantLock loadFromUriLock = this.TZ.TE;
            Ty.T("Start display image task [%s]", this.Tq);
            if (loadFromUriLock.isLocked()) {
                Ty.T("Image already is loading. Waiting... [%s]", this.Tq);
            }
            loadFromUriLock.lock();
            try {
                T5();
                Bitmap bmp = this.T5.Tq.T(this.Tq);
                if (bmp == null || bmp.isRecycled()) {
                    bmp = Tn();
                    if (bmp != null) {
                        T5();
                        TF();
                        if (this.Ty.Tn()) {
                            Ty.T("PreProcess image before caching in memory [%s]", this.Tq);
                            bmp = this.Ty.TF().T(bmp);
                            if (bmp == null) {
                                Ty.Tn("Pre-processor returned null [%s]", this.Tq);
                            }
                        }
                        if (bmp != null && this.Ty.TE()) {
                            Ty.T("Cache image in memory [%s]", this.Tq);
                            this.T5.Tq.T(this.Tq, bmp);
                        }
                    } else {
                        return;
                    }
                } else {
                    this.TB = Tk.MEMORY_CACHE;
                    Ty.T("...Get cached bitmap from memory after waiting. [%s]", this.Tq);
                }
                if (bmp != null && this.Ty.T9()) {
                    Ty.T("PostProcess image before displaying [%s]", this.Tq);
                    bmp = this.Ty.Tj().T(bmp);
                    if (bmp == null) {
                        Ty.Tn("Post-processor returned null [%s]", this.Tq);
                    }
                }
                T5();
                TF();
                loadFromUriLock.unlock();
                T(new Tr(bmp, this.TZ, this.Tk, this.TB), this.Tj, this.TE, this.Tk);
            } catch (T e) {
                TZ();
            } finally {
                loadFromUriLock.unlock();
            }
        }
    }

    private boolean Tr() {
        AtomicBoolean pause = this.Tk.T();
        if (pause.get()) {
            synchronized (this.Tk.Tr()) {
                if (pause.get()) {
                    Ty.T("ImageLoader is paused. Waiting...  [%s]", this.Tq);
                    try {
                        this.Tk.Tr().wait();
                        Ty.T(".. Resume loading [%s]", this.Tq);
                    } catch (InterruptedException e) {
                        Ty.Tn("Task was interrupted [%s]", this.Tq);
                        return true;
                    }
                }
            }
        }
        return Tv();
    }

    private boolean Ty() {
        if (!this.Ty.Tk()) {
            return false;
        }
        Ty.T("Delay %d ms before loading...  [%s]", Integer.valueOf(this.Ty.T6()), this.Tq);
        try {
            Thread.sleep((long) this.Ty.T6());
            return Tv();
        } catch (InterruptedException e) {
            Ty.Tn("Task was interrupted [%s]", this.Tq);
            return true;
        }
    }

    private Bitmap Tn() throws T {
        File imageFile;
        Bitmap bitmap = null;
        try {
            File imageFile2 = this.T5.TF.T(this.f330T);
            if (imageFile2 != null && imageFile2.exists() && imageFile2.length() > 0) {
                Ty.T("Load image from disk cache [%s]", this.Tq);
                this.TB = Tk.DISC_CACHE;
                T5();
                bitmap = T(Tr.T.FILE.Tr(imageFile2.getAbsolutePath()));
            }
            if (bitmap == null || bitmap.getWidth() <= 0 || bitmap.getHeight() <= 0) {
                Ty.T("Load image from network [%s]", this.Tq);
                this.TB = Tk.NETWORK;
                String imageUriForDecoding = this.f330T;
                if (this.Ty.T5() && T9() && (imageFile = this.T5.TF.T(this.f330T)) != null) {
                    imageUriForDecoding = Tr.T.FILE.Tr(imageFile.getAbsolutePath());
                }
                T5();
                bitmap = T(imageUriForDecoding);
                if (bitmap == null || bitmap.getWidth() <= 0 || bitmap.getHeight() <= 0) {
                    T(Tr.T.DECODING_ERROR, (Throwable) null);
                }
            }
        } catch (IllegalStateException e) {
            T(Tr.T.NETWORK_DENIED, (Throwable) null);
        } catch (T e2) {
            throw e2;
        } catch (IOException e3) {
            Ty.T((Throwable) e3);
            T(Tr.T.IO_ERROR, (Throwable) e3);
        } catch (OutOfMemoryError e4) {
            Ty.T((Throwable) e4);
            T(Tr.T.OUT_OF_MEMORY, (Throwable) e4);
        } catch (Throwable e5) {
            Ty.T(e5);
            T(Tr.T.UNKNOWN, e5);
        }
        return bitmap;
    }

    private Bitmap T(String imageUri) throws IOException {
        String str = imageUri;
        return this.Te.T(new com.Ty.T.Tr.Tr.Ty(this.Tq, str, this.f330T, this.TF, this.Tr.Ty(), TE(), this.Ty));
    }

    private boolean T9() throws T {
        Ty.T("Cache image on disk [%s]", this.Tq);
        try {
            boolean loaded = Tk();
            if (!loaded) {
                return loaded;
            }
            int width = this.T5.Tn;
            int height = this.T5.T9;
            if (width <= 0 && height <= 0) {
                return loaded;
            }
            Ty.T("Resize image in disk cache [%s]", this.Tq);
            Tr(width, height);
            return loaded;
        } catch (IOException e) {
            Ty.T((Throwable) e);
            return false;
        }
    }

    private boolean Tk() throws IOException {
        boolean z = false;
        InputStream is = TE().T(this.f330T, this.Ty.Tq());
        if (is == null) {
            Ty.Tn("No stream for image [%s]", this.Tq);
        } else {
            try {
                z = this.T5.TF.T(this.f330T, is, this);
            } finally {
                com.Ty.T.Ty.Tr.T((Closeable) is);
            }
        }
        return z;
    }

    private boolean Tr(int maxWidth, int maxHeight) throws IOException {
        File targetFile = this.T5.TF.T(this.f330T);
        if (targetFile == null || !targetFile.exists()) {
            return false;
        }
        Bitmap bmp = this.Te.T(new com.Ty.T.Tr.Tr.Ty(this.Tq, Tr.T.FILE.Tr(targetFile.getAbsolutePath()), this.f330T, new T9(maxWidth, maxHeight), com.Ty.T.Tr.T.TE.FIT_INSIDE, TE(), new Ty.T().T(this.Ty).T(Tn.IN_SAMPLE_INT).T()));
        if (!(bmp == null || this.T5.Tk == null)) {
            com.Ty.T.Ty.Ty.T("Process image before cache on disk [%s]", this.Tq);
            bmp = this.T5.Tk.T(bmp);
            if (bmp == null) {
                com.Ty.T.Ty.Ty.Tn("Bitmap processor for disk cache returned null [%s]", this.Tq);
            }
        }
        if (bmp == null) {
            return false;
        }
        boolean saved = this.T5.TF.T(this.f330T, bmp);
        bmp.recycle();
        return saved;
    }

    public boolean T(int current, int total) {
        return this.Tj || Ty(current, total);
    }

    private boolean Ty(final int current, final int total) {
        if (Tj() || Tv()) {
            return false;
        }
        if (this.T9 != null) {
            T(new Runnable() {
                public void run() {
                    TE.this.T9.T(TE.this.f330T, TE.this.Tr.Tn(), current, total);
                }
            }, false, this.TE, this.Tk);
        }
        return true;
    }

    private void T(final Tr.T failType, final Throwable failCause) {
        if (!this.Tj && !Tj() && !Tv()) {
            T(new Runnable() {
                public void run() {
                    if (TE.this.Ty.Ty()) {
                        TE.this.Tr.T(TE.this.Ty.Ty(TE.this.T5.f324T));
                    }
                    TE.this.Tn.T(TE.this.f330T, TE.this.Tr.Tn(), new com.Ty.T.Tr.T.Tr(failType, failCause));
                }
            }, false, this.TE, this.Tk);
        }
    }

    private void TZ() {
        if (!this.Tj && !Tj()) {
            T(new Runnable() {
                public void run() {
                    TE.this.Tn.Tr(TE.this.f330T, TE.this.Tr.Tn());
                }
            }, false, this.TE, this.Tk);
        }
    }

    private com.Ty.T.Tr.Tn.Tr TE() {
        if (this.Tk.Ty()) {
            return this.Th;
        }
        if (this.Tk.Tn()) {
            return this.T6;
        }
        return this.Tv;
    }

    private void T5() throws T {
        Th();
        Te();
    }

    private boolean Tv() {
        return T6() || Tq();
    }

    private void Th() throws T {
        if (T6()) {
            throw new T();
        }
    }

    private boolean T6() {
        if (!this.Tr.T9()) {
            return false;
        }
        com.Ty.T.Ty.Ty.T("ImageAware was collected by GC. Task is cancelled. [%s]", this.Tq);
        return true;
    }

    private void Te() throws T {
        if (Tq()) {
            throw new T();
        }
    }

    private boolean Tq() {
        boolean imageAwareWasReused;
        if (!this.Tq.equals(this.Tk.T(this.Tr))) {
            imageAwareWasReused = true;
        } else {
            imageAwareWasReused = false;
        }
        if (!imageAwareWasReused) {
            return false;
        }
        com.Ty.T.Ty.Ty.T("ImageAware is reused for another image. Task is cancelled. [%s]", this.Tq);
        return true;
    }

    private void TF() throws T {
        if (Tj()) {
            throw new T();
        }
    }

    private boolean Tj() {
        if (!Thread.interrupted()) {
            return false;
        }
        com.Ty.T.Ty.Ty.T("Task was interrupted [%s]", this.Tq);
        return true;
    }

    /* access modifiers changed from: package-private */
    public String T() {
        return this.f330T;
    }

    static void T(Runnable r, boolean sync, Handler handler, Tk engine) {
        if (sync) {
            r.run();
        } else if (handler == null) {
            engine.T(r);
        } else {
            handler.post(r);
        }
    }

    /* compiled from: Proguard */
    class T extends Exception {
        T() {
        }
    }
}
