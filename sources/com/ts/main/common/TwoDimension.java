package com.ts.main.common;

import android.graphics.Bitmap;
import android.telecom.Log;
import com.android.SdkConstants;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.txznet.sdk.TXZResourceManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;

public class TwoDimension {
    static TwoDimension m_TwoDimension = null;

    public static TwoDimension GetInstance() {
        if (m_TwoDimension == null) {
            m_TwoDimension = new TwoDimension();
        }
        return m_TwoDimension;
    }

    public Bitmap createQRImage(String url, int width, int height) {
        if (url != null) {
            try {
                if (!TXZResourceManager.STYLE_DEFAULT.equals(url) && url.length() >= 1) {
                    Hashtable<EncodeHintType, String> hints = new Hashtable<>();
                    hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
                    BitMatrix bitMatrix = new QRCodeWriter().encode(url, BarcodeFormat.QR_CODE, width, height, hints);
                    int[] pixels = new int[(width * height)];
                    for (int y = 0; y < height; y++) {
                        for (int x = 0; x < width; x++) {
                            if (bitMatrix.get(x, y)) {
                                pixels[(y * width) + x] = -16777216;
                            } else {
                                pixels[(y * width) + x] = -1;
                            }
                        }
                    }
                    Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                    bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
                    return bitmap;
                }
            } catch (WriterException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public void CreateTheAdas2D(String url, int width, int height, String fileName, String Path) {
        Bitmap bmp1 = createQRImage(url, width, height);
        if (bmp1 != null) {
            try {
                saveBitmap(bmp1, fileName, Path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Log.i("TwoDimension", "createQRImage error", new Object[0]);
        }
    }

    private void saveBitmap(Bitmap bitmap, String fileName, String Path) throws IOException {
        File file = new File(Path, String.valueOf(fileName) + SdkConstants.DOT_PNG);
        Log.i("TwoDimension", "file ==" + file, new Object[0]);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            if (bitmap.compress(Bitmap.CompressFormat.PNG, 90, out)) {
                out.flush();
                out.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }
}
