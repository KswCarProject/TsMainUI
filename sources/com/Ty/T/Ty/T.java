package com.Ty.T.Ty;

import android.opengl.GLES10;
import com.Ty.T.Tr.T.T9;
import com.Ty.T.Tr.T.TE;

/* compiled from: Proguard */
public final class T {

    /* renamed from: T  reason: collision with root package name */
    private static T9 f346T;

    static {
        int[] maxTextureSize = new int[1];
        GLES10.glGetIntegerv(3379, maxTextureSize, 0);
        int maxBitmapDimension = Math.max(maxTextureSize[0], 2048);
        f346T = new T9(maxBitmapDimension, maxBitmapDimension);
    }

    public static T9 T(com.Ty.T.Tr.T9.T imageAware, T9 maxImageSize) {
        int width = imageAware.T();
        if (width <= 0) {
            width = maxImageSize.T();
        }
        int height = imageAware.Tr();
        if (height <= 0) {
            height = maxImageSize.Tr();
        }
        return new T9(width, height);
    }

    public static int T(T9 srcSize, T9 targetSize, TE viewScaleType, boolean powerOf2Scale) {
        int srcWidth = srcSize.T();
        int srcHeight = srcSize.Tr();
        int targetWidth = targetSize.T();
        int targetHeight = targetSize.Tr();
        int scale = 1;
        switch (viewScaleType) {
            case FIT_INSIDE:
                if (!powerOf2Scale) {
                    scale = Math.max(srcWidth / targetWidth, srcHeight / targetHeight);
                    break;
                } else {
                    int halfWidth = srcWidth / 2;
                    int halfHeight = srcHeight / 2;
                    while (true) {
                        if (halfWidth / scale <= targetWidth && halfHeight / scale <= targetHeight) {
                            break;
                        } else {
                            scale *= 2;
                        }
                    }
                }
                break;
            case CROP:
                if (!powerOf2Scale) {
                    scale = Math.min(srcWidth / targetWidth, srcHeight / targetHeight);
                    break;
                } else {
                    int halfWidth2 = srcWidth / 2;
                    int halfHeight2 = srcHeight / 2;
                    while (halfWidth2 / scale > targetWidth && halfHeight2 / scale > targetHeight) {
                        scale *= 2;
                    }
                }
                break;
        }
        if (scale < 1) {
            scale = 1;
        }
        return T(srcWidth, srcHeight, scale, powerOf2Scale);
    }

    private static int T(int srcWidth, int srcHeight, int scale, boolean powerOf2) {
        int maxWidth = f346T.T();
        int maxHeight = f346T.Tr();
        while (true) {
            if (srcWidth / scale <= maxWidth && srcHeight / scale <= maxHeight) {
                return scale;
            }
            if (powerOf2) {
                scale *= 2;
            } else {
                scale++;
            }
        }
    }

    public static int T(T9 srcSize) {
        int srcWidth = srcSize.T();
        int srcHeight = srcSize.Tr();
        return Math.max((int) Math.ceil((double) (((float) srcWidth) / ((float) f346T.T()))), (int) Math.ceil((double) (((float) srcHeight) / ((float) f346T.Tr()))));
    }

    public static float Tr(T9 srcSize, T9 targetSize, TE viewScaleType, boolean stretch) {
        int destWidth;
        int destHeight;
        int srcWidth = srcSize.T();
        int srcHeight = srcSize.Tr();
        int targetWidth = targetSize.T();
        int targetHeight = targetSize.Tr();
        float widthScale = ((float) srcWidth) / ((float) targetWidth);
        float heightScale = ((float) srcHeight) / ((float) targetHeight);
        if ((viewScaleType != TE.FIT_INSIDE || widthScale < heightScale) && (viewScaleType != TE.CROP || widthScale >= heightScale)) {
            destWidth = (int) (((float) srcWidth) / heightScale);
            destHeight = targetHeight;
        } else {
            destWidth = targetWidth;
            destHeight = (int) (((float) srcHeight) / widthScale);
        }
        if ((stretch || destWidth >= srcWidth || destHeight >= srcHeight) && (!stretch || destWidth == srcWidth || destHeight == srcHeight)) {
            return 1.0f;
        }
        return ((float) destWidth) / ((float) srcWidth);
    }
}
