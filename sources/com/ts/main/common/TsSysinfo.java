package com.ts.main.common;

import android.util.Log;
import com.ts.MainUI.TsFile;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TsSysinfo {
    static TsSysinfo Mysysinfo = null;
    private static final String TAG = "syssetinfo";
    private static final String systemDyChangeDpi = "support_dychange_dpi";
    private static final String systemlosstransient = "support_loss_transient";
    private static final String systemremotecontrol = "support_remote_control";
    private static final String systemsetinfo_path = "/system/etc/tssystem.ini";
    private static final String systemsetinfo_sdpath = "/sdcard/tssystem.ini";
    public boolean bDyChangeDpi = false;
    public boolean bLossTranclient = false;
    public boolean bRemoteControl = false;

    public static TsSysinfo GetInstance() {
        if (Mysysinfo == null) {
            Mysysinfo = new TsSysinfo();
        }
        return Mysysinfo;
    }

    public void GetInfo() {
        Log.i(TAG, "GetInfo a==");
        String path = null;
        if (TsFile.fileIsExists(systemsetinfo_sdpath)) {
            path = systemsetinfo_sdpath;
        } else if (TsFile.fileIsExists(systemsetinfo_path)) {
            path = systemsetinfo_path;
        }
        if (path != null) {
            Log.i(TAG, "GetInfo ==");
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
                try {
                    String[] strArr = new String[2];
                    while (true) {
                        try {
                            String s = bufferedReader.readLine();
                            if (s == null) {
                                break;
                            }
                            String[] p = s.split("=");
                            Log.i(TAG, "p[0] ==" + p[0]);
                            if (p[0].equals(systemremotecontrol)) {
                                Log.i(TAG, "systemremotecontrol  ==" + p[1]);
                                if (p[1].equals("1")) {
                                    this.bRemoteControl = true;
                                    Log.i(TAG, "bRemoteControl ==" + this.bRemoteControl);
                                } else {
                                    this.bRemoteControl = false;
                                    Log.i(TAG, "bRemoteControl ==" + this.bRemoteControl);
                                }
                            } else if (p[0].equals(systemlosstransient)) {
                                Log.i(TAG, "systemlosstransient  ==" + p[1]);
                                if (p[1].equals("1")) {
                                    this.bLossTranclient = true;
                                    Log.i(TAG, "bLossTranclient ==" + this.bLossTranclient);
                                } else {
                                    this.bLossTranclient = false;
                                    Log.i(TAG, "bLossTranclient ==" + this.bLossTranclient);
                                }
                            } else if (p[0].equals(systemDyChangeDpi)) {
                                if (p[1].equals("1")) {
                                    this.bDyChangeDpi = true;
                                    Log.i(TAG, "bDyChangeDpi ==" + this.bDyChangeDpi);
                                } else {
                                    this.bDyChangeDpi = false;
                                    Log.i(TAG, "bDyChangeDpi ==" + this.bDyChangeDpi);
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                } catch (FileNotFoundException e3) {
                    e = e3;
                    BufferedReader bufferedReader2 = bufferedReader;
                }
            } catch (FileNotFoundException e4) {
                e = e4;
                e.printStackTrace();
            }
        }
    }
}
