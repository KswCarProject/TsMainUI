package com.Ty.T.Tr;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.Ty.T.Tr.T.T9;
import com.Ty.T.Tr.T.Tk;
import com.Ty.T.Tr.Tk.T;
import com.Ty.T.Tr.Tk.Tr;
import com.Ty.T.Tr.Tk.Ty;

/* compiled from: Proguard */
public class Tn {

    /* renamed from: T  reason: collision with root package name */
    public static final String f338T = Tn.class.getSimpleName();
    private static volatile Tn T9;
    private T Tn = new Ty();
    private T9 Tr;
    private Tk Ty;

    public static Tn T() {
        if (T9 == null) {
            synchronized (Tn.class) {
                if (T9 == null) {
                    T9 = new Tn();
                }
            }
        }
        return T9;
    }

    protected Tn() {
    }

    public synchronized void T(T9 configuration) {
        if (configuration == null) {
            throw new IllegalArgumentException("ImageLoader configuration can not be initialized with null");
        } else if (this.Tr == null) {
            com.Ty.T.Ty.Ty.T("Initialize ImageLoader with configuration", new Object[0]);
            this.Ty = new Tk(configuration);
            this.Tr = configuration;
        } else {
            com.Ty.T.Ty.Ty.Ty("Try to initialize ImageLoader which had already been initialized before. To re-init ImageLoader with new configuration call ImageLoader.destroy() at first.", new Object[0]);
        }
    }

    public boolean Tr() {
        return this.Tr != null;
    }

    public void T(String uri, com.Ty.T.Tr.T9.T imageAware) {
        T(uri, imageAware, (Ty) null, (T) null, (Tr) null);
    }

    public void T(String uri, com.Ty.T.Tr.T9.T imageAware, Ty options, T listener, Tr progressListener) {
        T(uri, imageAware, options, (T9) null, listener, progressListener);
    }

    public void T(String uri, com.Ty.T.Tr.T9.T imageAware, Ty options, T9 targetSize, T listener, Tr progressListener) {
        T9();
        if (imageAware == null) {
            throw new IllegalArgumentException("Wrong arguments were passed to displayImage() method (ImageView reference must not be null)");
        }
        if (listener == null) {
            listener = this.Tn;
        }
        if (options == null) {
            options = this.Tr.TK;
        }
        if (TextUtils.isEmpty(uri)) {
            this.Ty.Tr(imageAware);
            listener.T(uri, imageAware.Tn());
            if (options.Tr()) {
                imageAware.T(options.Tr(this.Tr.f324T));
            } else {
                imageAware.T((Drawable) null);
            }
            listener.T(uri, imageAware.Tn(), (Bitmap) null);
            return;
        }
        if (targetSize == null) {
            targetSize = com.Ty.T.Ty.T.T(imageAware, this.Tr.T());
        }
        String memoryCacheKey = com.Ty.T.Ty.Tn.T(uri, targetSize);
        this.Ty.T(imageAware, memoryCacheKey);
        listener.T(uri, imageAware.Tn());
        Bitmap bmp = this.Tr.Tq.T(memoryCacheKey);
        if (bmp == null || bmp.isRecycled()) {
            if (options.T()) {
                imageAware.T(options.T(this.Tr.f324T));
            } else if (options.TZ()) {
                imageAware.T((Drawable) null);
            }
            TE displayTask = new TE(this.Ty, new TZ(uri, imageAware, targetSize, memoryCacheKey, options, listener, progressListener, this.Ty.T(uri)), T(options));
            if (options.TO()) {
                displayTask.run();
            } else {
                this.Ty.T(displayTask);
            }
        } else {
            com.Ty.T.Ty.Ty.T("Load image from memory cache [%s]", memoryCacheKey);
            if (options.T9()) {
                T5 displayTask2 = new T5(this.Ty, bmp, new TZ(uri, imageAware, targetSize, memoryCacheKey, options, listener, progressListener, this.Ty.T(uri)), T(options));
                if (options.TO()) {
                    displayTask2.run();
                } else {
                    this.Ty.T(displayTask2);
                }
            } else {
                options.TB().T(bmp, imageAware, Tk.MEMORY_CACHE);
                listener.T(uri, imageAware.Tn(), bmp);
            }
        }
    }

    private void T9() {
        if (this.Tr == null) {
            throw new IllegalStateException("ImageLoader must be init with configuration before using");
        }
    }

    public void Ty() {
        T9();
        this.Tr.Tq.Tr();
    }

    public void Tn() {
        T9();
        this.Tr.TF.T();
    }

    private static Handler T(Ty options) {
        Handler handler = options.TK();
        if (options.TO()) {
            return null;
        }
        if (handler == null && Looper.myLooper() == Looper.getMainLooper()) {
            return new Handler();
        }
        return handler;
    }
}
