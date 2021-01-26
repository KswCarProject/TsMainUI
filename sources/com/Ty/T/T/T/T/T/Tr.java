package com.Ty.T.T.T.T.T;

import android.graphics.Bitmap;
import com.Ty.T.T.T.T;
import com.Ty.T.T.T.T.T.T;
import com.Ty.T.Ty.Tr;
import com.Ty.T.Ty.Ty;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: Proguard */
public class Tr implements T {

    /* renamed from: T  reason: collision with root package name */
    public static final Bitmap.CompressFormat f305T = Bitmap.CompressFormat.PNG;
    protected Bitmap.CompressFormat T9 = f305T;
    private File TZ;
    protected int Tk = 100;
    protected int Tn = 32768;
    protected T Tr;
    protected final com.Ty.T.T.T.Tr.T Ty;

    public Tr(File cacheDir, File reserveCacheDir, com.Ty.T.T.T.Tr.T fileNameGenerator, long cacheMaxSize, int cacheMaxFileCount) throws IOException {
        if (cacheDir == null) {
            throw new IllegalArgumentException("cacheDir argument must be not null");
        } else if (cacheMaxSize < 0) {
            throw new IllegalArgumentException("cacheMaxSize argument must be positive number");
        } else if (cacheMaxFileCount < 0) {
            throw new IllegalArgumentException("cacheMaxFileCount argument must be positive number");
        } else if (fileNameGenerator == null) {
            throw new IllegalArgumentException("fileNameGenerator argument must be not null");
        } else {
            cacheMaxSize = cacheMaxSize == 0 ? Long.MAX_VALUE : cacheMaxSize;
            cacheMaxFileCount = cacheMaxFileCount == 0 ? Integer.MAX_VALUE : cacheMaxFileCount;
            this.TZ = reserveCacheDir;
            this.Ty = fileNameGenerator;
            T(cacheDir, reserveCacheDir, cacheMaxSize, cacheMaxFileCount);
        }
    }

    private void T(File cacheDir, File reserveCacheDir, long cacheMaxSize, int cacheMaxFileCount) throws IOException {
        try {
            this.Tr = T.T(cacheDir, 1, 1, cacheMaxSize, cacheMaxFileCount);
        } catch (IOException e) {
            Ty.T((Throwable) e);
            if (reserveCacheDir != null) {
                T(reserveCacheDir, (File) null, cacheMaxSize, cacheMaxFileCount);
            }
            if (this.Tr == null) {
                throw e;
            }
        }
    }

    public File T(String imageUri) {
        File file = null;
        T.Ty snapshot = null;
        try {
            T.Ty snapshot2 = this.Tr.T(Tr(imageUri));
            if (snapshot2 != null) {
                file = snapshot2.T(0);
            }
            if (snapshot2 != null) {
                snapshot2.close();
            }
        } catch (IOException e) {
            Ty.T((Throwable) e);
            if (snapshot != null) {
                snapshot.close();
            }
        } catch (Throwable th) {
            if (snapshot != null) {
                snapshot.close();
            }
            throw th;
        }
        return file;
    }

    public boolean T(String imageUri, InputStream imageStream, Tr.T listener) throws IOException {
        boolean copied = false;
        T.C0009T editor = this.Tr.Tr(Tr(imageUri));
        if (editor != null) {
            OutputStream os = new BufferedOutputStream(editor.T(0), this.Tn);
            boolean copied2 = false;
            try {
                copied2 = com.Ty.T.Ty.Tr.T(imageStream, os, listener, this.Tn);
                if (!copied2) {
                    editor.Tr();
                }
            } finally {
                com.Ty.T.Ty.Tr.T((Closeable) os);
                if (copied2) {
                    editor.T();
                } else {
                    editor.Tr();
                }
            }
        }
        return copied;
    }

    public boolean T(String imageUri, Bitmap bitmap) throws IOException {
        boolean savedSuccessfully = false;
        T.C0009T editor = this.Tr.Tr(Tr(imageUri));
        if (editor != null) {
            OutputStream os = new BufferedOutputStream(editor.T(0), this.Tn);
            try {
                savedSuccessfully = bitmap.compress(this.T9, this.Tk, os);
                if (savedSuccessfully) {
                    editor.T();
                } else {
                    editor.Tr();
                }
            } finally {
                com.Ty.T.Ty.Tr.T((Closeable) os);
            }
        }
        return savedSuccessfully;
    }

    public void T() {
        try {
            this.Tr.Tn();
        } catch (IOException e) {
            Ty.T((Throwable) e);
        }
        try {
            T(this.Tr.T(), this.TZ, this.Tr.Tr(), this.Tr.Ty());
        } catch (IOException e2) {
            Ty.T((Throwable) e2);
        }
    }

    private String Tr(String imageUri) {
        return this.Ty.T(imageUri);
    }
}
