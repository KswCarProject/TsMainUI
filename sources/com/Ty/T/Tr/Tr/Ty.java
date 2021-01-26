package com.Ty.T.Tr.Tr;

import android.annotation.TargetApi;
import android.graphics.BitmapFactory;
import android.os.Build;
import com.Ty.T.Tr.T.T9;
import com.Ty.T.Tr.T.TE;
import com.Ty.T.Tr.T.Tn;
import com.Ty.T.Tr.Tn.Tr;

/* compiled from: Proguard */
public class Ty {

    /* renamed from: T  reason: collision with root package name */
    private final String f346T;
    private final boolean T5;
    private final Tn T9;
    private final Object TE;
    private final Tr TZ;
    private final TE Tk;
    private final T9 Tn;
    private final String Tr;
    private final BitmapFactory.Options Tv = new BitmapFactory.Options();
    private final String Ty;

    public Ty(String imageKey, String imageUri, String originalImageUri, T9 targetSize, TE viewScaleType, Tr downloader, com.Ty.T.Tr.Ty displayOptions) {
        this.f346T = imageKey;
        this.Tr = imageUri;
        this.Ty = originalImageUri;
        this.Tn = targetSize;
        this.T9 = displayOptions.Tv();
        this.Tk = viewScaleType;
        this.TZ = downloader;
        this.TE = displayOptions.Tq();
        this.T5 = displayOptions.Te();
        T(displayOptions.Th(), this.Tv);
    }

    private void T(BitmapFactory.Options srcOptions, BitmapFactory.Options destOptions) {
        destOptions.inDensity = srcOptions.inDensity;
        destOptions.inDither = srcOptions.inDither;
        destOptions.inInputShareable = srcOptions.inInputShareable;
        destOptions.inJustDecodeBounds = srcOptions.inJustDecodeBounds;
        destOptions.inPreferredConfig = srcOptions.inPreferredConfig;
        destOptions.inPurgeable = srcOptions.inPurgeable;
        destOptions.inSampleSize = srcOptions.inSampleSize;
        destOptions.inScaled = srcOptions.inScaled;
        destOptions.inScreenDensity = srcOptions.inScreenDensity;
        destOptions.inTargetDensity = srcOptions.inTargetDensity;
        destOptions.inTempStorage = srcOptions.inTempStorage;
        if (Build.VERSION.SDK_INT >= 10) {
            Tr(srcOptions, destOptions);
        }
        if (Build.VERSION.SDK_INT >= 11) {
            Ty(srcOptions, destOptions);
        }
    }

    @TargetApi(10)
    private void Tr(BitmapFactory.Options srcOptions, BitmapFactory.Options destOptions) {
        destOptions.inPreferQualityOverSpeed = srcOptions.inPreferQualityOverSpeed;
    }

    @TargetApi(11)
    private void Ty(BitmapFactory.Options srcOptions, BitmapFactory.Options destOptions) {
        destOptions.inBitmap = srcOptions.inBitmap;
        destOptions.inMutable = srcOptions.inMutable;
    }

    public String T() {
        return this.f346T;
    }

    public String Tr() {
        return this.Tr;
    }

    public T9 Ty() {
        return this.Tn;
    }

    public Tn Tn() {
        return this.T9;
    }

    public TE T9() {
        return this.Tk;
    }

    public Tr Tk() {
        return this.TZ;
    }

    public Object TZ() {
        return this.TE;
    }

    public boolean TE() {
        return this.T5;
    }

    public BitmapFactory.Options T5() {
        return this.Tv;
    }
}
