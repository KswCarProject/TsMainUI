package com.Ty.T.T.T.T;

import android.graphics.Bitmap;
import com.Ty.T.Ty.Tr;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: Proguard */
public abstract class T implements com.Ty.T.T.T.T {

    /* renamed from: T  reason: collision with root package name */
    public static final Bitmap.CompressFormat f297T = Bitmap.CompressFormat.PNG;
    protected int T9 = 32768;
    protected int TZ = 100;
    protected Bitmap.CompressFormat Tk = f297T;
    protected final com.Ty.T.T.T.Tr.T Tn;
    protected final File Tr;
    protected final File Ty;

    public T(File cacheDir, File reserveCacheDir, com.Ty.T.T.T.Tr.T fileNameGenerator) {
        if (cacheDir == null) {
            throw new IllegalArgumentException("cacheDir argument must be not null");
        } else if (fileNameGenerator == null) {
            throw new IllegalArgumentException("fileNameGenerator argument must be not null");
        } else {
            this.Tr = cacheDir;
            this.Ty = reserveCacheDir;
            this.Tn = fileNameGenerator;
        }
    }

    public File T(String imageUri) {
        return Tr(imageUri);
    }

    public boolean T(String imageUri, InputStream imageStream, Tr.T listener) throws IOException {
        OutputStream os;
        File imageFile = Tr(imageUri);
        File tmpFile = new File(imageFile.getAbsolutePath() + ".tmp");
        boolean loaded = false;
        try {
            os = new BufferedOutputStream(new FileOutputStream(tmpFile), this.T9);
            boolean loaded2 = Tr.T(imageStream, os, listener, this.T9);
            Tr.T((Closeable) os);
            if (loaded2 && !tmpFile.renameTo(imageFile)) {
                loaded2 = false;
            }
            if (!loaded2) {
                tmpFile.delete();
            }
            return loaded2;
        } catch (Throwable th) {
            if (0 != 0 && !tmpFile.renameTo(imageFile)) {
                loaded = false;
            }
            if (!loaded) {
                tmpFile.delete();
            }
            throw th;
        }
    }

    public boolean T(String imageUri, Bitmap bitmap) throws IOException {
        File imageFile = Tr(imageUri);
        File tmpFile = new File(imageFile.getAbsolutePath() + ".tmp");
        OutputStream os = new BufferedOutputStream(new FileOutputStream(tmpFile), this.T9);
        boolean savedSuccessfully = false;
        try {
            savedSuccessfully = bitmap.compress(this.Tk, this.TZ, os);
            bitmap.recycle();
            return savedSuccessfully;
        } finally {
            Tr.T((Closeable) os);
            if (savedSuccessfully && !tmpFile.renameTo(imageFile)) {
                savedSuccessfully = false;
            }
            if (!savedSuccessfully) {
                tmpFile.delete();
            }
        }
    }

    public void T() {
        File[] files = this.Tr.listFiles();
        if (files != null) {
            for (File f : files) {
                f.delete();
            }
        }
    }

    /* access modifiers changed from: protected */
    public File Tr(String imageUri) {
        String fileName = this.Tn.T(imageUri);
        File dir = this.Tr;
        if (!this.Tr.exists() && !this.Tr.mkdirs() && this.Ty != null && (this.Ty.exists() || this.Ty.mkdirs())) {
            dir = this.Ty;
        }
        return new File(dir, fileName);
    }
}
