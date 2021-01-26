package com.Ty.T.Tr.T;

import android.widget.ImageView;

/* compiled from: Proguard */
public enum TE {
    FIT_INSIDE,
    CROP;

    /* renamed from: com.Ty.T.Tr.T.TE$1  reason: invalid class name */
    /* compiled from: Proguard */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: T  reason: collision with root package name */
        static final /* synthetic */ int[] f317T = null;

        static {
            f317T = new int[ImageView.ScaleType.values().length];
            try {
                f317T[ImageView.ScaleType.FIT_CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f317T[ImageView.ScaleType.FIT_XY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f317T[ImageView.ScaleType.FIT_START.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f317T[ImageView.ScaleType.FIT_END.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f317T[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f317T[ImageView.ScaleType.MATRIX.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f317T[ImageView.ScaleType.CENTER.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f317T[ImageView.ScaleType.CENTER_CROP.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    public static TE T(ImageView imageView) {
        switch (AnonymousClass1.f317T[imageView.getScaleType().ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                return FIT_INSIDE;
            default:
                return CROP;
        }
    }
}
