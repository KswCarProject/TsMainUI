package com.ts.main.auth;

import android.graphics.Bitmap;
import android.os.SystemClock;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.ts.MainUI.TsFile;
import java.io.IOException;
import java.util.Map;

public class factory_test {
    static String Err = null;
    static long Starttime = 0;
    static long TotaArmlTime = 0;
    static long TotalTime = 0;
    private static final String filepath = "/mnt/sdcard/factoryreport.ini";
    static int nNUM = 0;

    static void Clear() {
        nNUM = 0;
        Err = " ";
    }

    static void AddToSort(String Str) {
        nNUM++;
        Err = String.valueOf(Err) + " " + nNUM + ".";
        Err = String.valueOf(Err) + Str;
        Err = String.valueOf(Err) + "\r\n";
    }

    static void ReadReport() {
        try {
            Err = TsFile.readFileSdcardFile(filepath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void WriteReport() {
        try {
            TsFile.writeFileSdcardFile(filepath, Err);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static long getTickCount() {
        return SystemClock.uptimeMillis();
    }

    public static Bitmap createBarCode(CharSequence content, int BAR_WIDTH, int BAR_HEIGHT) {
        int i;
        BarcodeFormat barcodeFormat = BarcodeFormat.CODE_128;
        BitMatrix result = null;
        try {
            result = new MultiFormatWriter().encode(new StringBuilder().append(content).toString(), barcodeFormat, BAR_WIDTH, BAR_HEIGHT, (Map<EncodeHintType, ?>) null);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        int width = result.getWidth();
        int height = result.getHeight();
        int[] pixels = new int[(width * height)];
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                int i2 = offset + x;
                if (result.get(x, y)) {
                    i = -16777216;
                } else {
                    i = -1;
                }
                pixels[i2] = i;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }
}
