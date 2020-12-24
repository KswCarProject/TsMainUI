package com.Ty.T.Tr.Tr;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import com.Ty.T.Tr.T.T9;
import com.Ty.T.Tr.T.Tn;
import com.Ty.T.Tr.Tn.Tr;
import com.Ty.T.Ty.Ty;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: Proguard */
public class T implements Tr {

    /* renamed from: T  reason: collision with root package name */
    protected final boolean f340T;

    public T(boolean loggingEnabled) {
        this.f340T = loggingEnabled;
    }

    public Bitmap T(Ty decodingInfo) throws IOException {
        InputStream imageStream = Tr(decodingInfo);
        if (imageStream == null) {
            Ty.Tn("No stream for image [%s]", decodingInfo.T());
            return null;
        }
        try {
            Tr imageInfo = T(imageStream, decodingInfo);
            imageStream = Tr(imageStream, decodingInfo);
            Bitmap decodedBitmap = BitmapFactory.decodeStream(imageStream, (Rect) null, T(imageInfo.f342T, decodingInfo));
            if (decodedBitmap != null) {
                return T(decodedBitmap, decodingInfo, imageInfo.Tr.f341T, imageInfo.Tr.Tr);
            }
            Ty.Tn("Image can't be decoded [%s]", decodingInfo.T());
            return decodedBitmap;
        } finally {
            com.Ty.T.Ty.Tr.T((Closeable) imageStream);
        }
    }

    /* access modifiers changed from: protected */
    public InputStream Tr(Ty decodingInfo) throws IOException {
        return decodingInfo.Tk().T(decodingInfo.Tr(), decodingInfo.TZ());
    }

    /* access modifiers changed from: protected */
    public Tr T(InputStream imageStream, Ty decodingInfo) throws IOException {
        C0012T exif;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(imageStream, (Rect) null, options);
        String imageUri = decodingInfo.Tr();
        if (!decodingInfo.TE() || !T(imageUri, options.outMimeType)) {
            exif = new C0012T();
        } else {
            exif = T(imageUri);
        }
        return new Tr(new T9(options.outWidth, options.outHeight, exif.f341T), exif);
    }

    private boolean T(String imageUri, String mimeType) {
        return "image/jpeg".equalsIgnoreCase(mimeType) && Tr.T.T(imageUri) == Tr.T.FILE;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0026, code lost:
        r4 = 180;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002a, code lost:
        r4 = 270;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001f, code lost:
        r4 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0022, code lost:
        r4 = 90;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.Ty.T.Tr.Tr.T.C0012T T(java.lang.String r9) {
        /*
            r8 = this;
            r7 = 1
            r4 = 0
            r3 = 0
            android.media.ExifInterface r1 = new android.media.ExifInterface     // Catch:{ IOException -> 0x002d }
            com.Ty.T.Tr.Tn.Tr$T r5 = com.Ty.T.Tr.Tn.Tr.T.FILE     // Catch:{ IOException -> 0x002d }
            java.lang.String r5 = r5.Ty(r9)     // Catch:{ IOException -> 0x002d }
            r1.<init>(r5)     // Catch:{ IOException -> 0x002d }
            java.lang.String r5 = "Orientation"
            r6 = 1
            int r2 = r1.getAttributeInt(r5, r6)     // Catch:{ IOException -> 0x002d }
            switch(r2) {
                case 1: goto L_0x001f;
                case 2: goto L_0x001e;
                case 3: goto L_0x0026;
                case 4: goto L_0x0025;
                case 5: goto L_0x0029;
                case 6: goto L_0x0022;
                case 7: goto L_0x0021;
                case 8: goto L_0x002a;
                default: goto L_0x0018;
            }
        L_0x0018:
            com.Ty.T.Tr.Tr.T$T r5 = new com.Ty.T.Tr.Tr.T$T
            r5.<init>(r4, r3)
            return r5
        L_0x001e:
            r3 = 1
        L_0x001f:
            r4 = 0
            goto L_0x0018
        L_0x0021:
            r3 = 1
        L_0x0022:
            r4 = 90
            goto L_0x0018
        L_0x0025:
            r3 = 1
        L_0x0026:
            r4 = 180(0xb4, float:2.52E-43)
            goto L_0x0018
        L_0x0029:
            r3 = 1
        L_0x002a:
            r4 = 270(0x10e, float:3.78E-43)
            goto L_0x0018
        L_0x002d:
            r0 = move-exception
            java.lang.String r5 = "Can't read EXIF tags from file [%s]"
            java.lang.Object[] r6 = new java.lang.Object[r7]
            r7 = 0
            r6[r7] = r9
            com.Ty.T.Ty.Ty.Ty(r5, r6)
            goto L_0x0018
        */
        throw new UnsupportedOperationException("Method not decompiled: com.Ty.T.Tr.Tr.T.T(java.lang.String):com.Ty.T.Tr.Tr.T$T");
    }

    /* access modifiers changed from: protected */
    public BitmapFactory.Options T(T9 imageSize, Ty decodingInfo) {
        boolean powerOf2;
        int scale;
        Tn scaleType = decodingInfo.Tn();
        if (scaleType == Tn.NONE) {
            scale = 1;
        } else if (scaleType == Tn.NONE_SAFE) {
            scale = com.Ty.T.Ty.T.T(imageSize);
        } else {
            T9 targetSize = decodingInfo.Ty();
            if (scaleType == Tn.IN_SAMPLE_POWER_OF_2) {
                powerOf2 = true;
            } else {
                powerOf2 = false;
            }
            scale = com.Ty.T.Ty.T.T(imageSize, targetSize, decodingInfo.T9(), powerOf2);
        }
        if (scale > 1 && this.f340T) {
            Ty.T("Subsample original image (%1$s) to %2$s (scale = %3$d) [%4$s]", imageSize, imageSize.T(scale), Integer.valueOf(scale), decodingInfo.T());
        }
        BitmapFactory.Options decodingOptions = decodingInfo.T5();
        decodingOptions.inSampleSize = scale;
        return decodingOptions;
    }

    /* access modifiers changed from: protected */
    public InputStream Tr(InputStream imageStream, Ty decodingInfo) throws IOException {
        if (imageStream.markSupported()) {
            try {
                imageStream.reset();
                return imageStream;
            } catch (IOException e) {
            }
        }
        com.Ty.T.Ty.Tr.T((Closeable) imageStream);
        return Tr(decodingInfo);
    }

    /* access modifiers changed from: protected */
    public Bitmap T(Bitmap subsampledBitmap, Ty decodingInfo, int rotation, boolean flipHorizontal) {
        Matrix m = new Matrix();
        Tn scaleType = decodingInfo.Tn();
        if (scaleType == Tn.EXACTLY || scaleType == Tn.EXACTLY_STRETCHED) {
            T9 srcSize = new T9(subsampledBitmap.getWidth(), subsampledBitmap.getHeight(), rotation);
            float scale = com.Ty.T.Ty.T.Tr(srcSize, decodingInfo.Ty(), decodingInfo.T9(), scaleType == Tn.EXACTLY_STRETCHED);
            if (Float.compare(scale, 1.0f) != 0) {
                m.setScale(scale, scale);
                if (this.f340T) {
                    Ty.T("Scale subsampled image (%1$s) to %2$s (scale = %3$.5f) [%4$s]", srcSize, srcSize.T(scale), Float.valueOf(scale), decodingInfo.T());
                }
            }
        }
        if (flipHorizontal) {
            m.postScale(-1.0f, 1.0f);
            if (this.f340T) {
                Ty.T("Flip image horizontally [%s]", decodingInfo.T());
            }
        }
        if (rotation != 0) {
            m.postRotate((float) rotation);
            if (this.f340T) {
                Ty.T("Rotate image on %1$d° [%2$s]", Integer.valueOf(rotation), decodingInfo.T());
            }
        }
        Bitmap finalBitmap = Bitmap.createBitmap(subsampledBitmap, 0, 0, subsampledBitmap.getWidth(), subsampledBitmap.getHeight(), m, true);
        if (finalBitmap != subsampledBitmap) {
            subsampledBitmap.recycle();
        }
        return finalBitmap;
    }

    /* renamed from: com.Ty.T.Tr.Tr.T$T  reason: collision with other inner class name */
    /* compiled from: Proguard */
    protected static class C0012T {

        /* renamed from: T  reason: collision with root package name */
        public final int f341T;
        public final boolean Tr;

        protected C0012T() {
            this.f341T = 0;
            this.Tr = false;
        }

        protected C0012T(int rotation, boolean flipHorizontal) {
            this.f341T = rotation;
            this.Tr = flipHorizontal;
        }
    }

    /* compiled from: Proguard */
    protected static class Tr {

        /* renamed from: T  reason: collision with root package name */
        public final T9 f342T;
        public final C0012T Tr;

        protected Tr(T9 imageSize, C0012T exif) {
            this.f342T = imageSize;
            this.Tr = exif;
        }
    }
}
