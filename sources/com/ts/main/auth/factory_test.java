package com.ts.main.auth;

import android.os.SystemClock;
import com.ts.MainUI.TsFile;
import java.io.IOException;

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
}
