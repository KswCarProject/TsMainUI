package com.txznet.txz.util;

import android.graphics.Bitmap;
import com.Tr.Tr.T;
import com.Tr.Tr.T9;
import com.Tr.Tr.TE;
import com.Tr.Tr.Tr.Tr;
import com.Tr.Tr.Ty;
import java.util.Hashtable;

/* compiled from: Proguard */
public class Tk {
    public static Bitmap T(String str, int widthAndHeight) throws TE {
        return T(str, widthAndHeight, 0);
    }

    private static Bitmap T(String str, int widthAndHeight, int margin) throws TE {
        Hashtable<Ty, Object> hints = new Hashtable<>();
        hints.put(Ty.CHARACTER_SET, "utf-8");
        hints.put(Ty.MARGIN, Integer.valueOf(margin));
        Tr matrix = new T9().T(str, T.QR_CODE, widthAndHeight, widthAndHeight, hints);
        int width = matrix.Tr();
        int height = matrix.Ty();
        int[] pixels = new int[(width * height)];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (matrix.T(x, y)) {
                    pixels[(y * width) + x] = -16777216;
                } else {
                    pixels[(y * width) + x] = -1;
                }
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }
}
