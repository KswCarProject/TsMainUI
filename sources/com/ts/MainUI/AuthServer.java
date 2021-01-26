package com.ts.MainUI;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;
import com.yyw.ts70xhw.Mcu;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthServer {
    private static final String TAG = "AuthServer";
    private static AuthServer mAuthServer = null;
    byte[] DataFormat;
    private String Liscense = "error";
    String LisencePath;
    Context mcontext;
    int nGetIdOk = 0;

    public String GetLicense() {
        return this.Liscense;
    }

    public String[] GetMountedStorage() {
        File[] files;
        List<String> pathList = new ArrayList<>();
        File mountRoot = new File("/storage");
        if (mountRoot.exists() && mountRoot.isDirectory() && (files = mountRoot.listFiles()) != null) {
            for (File file : files) {
                if (!file.isHidden() && file.isDirectory() && file.canRead()) {
                    pathList.add(file.getAbsolutePath());
                }
            }
        }
        String[] arrys = new String[pathList.size()];
        pathList.toArray(arrys);
        return arrys;
    }

    public static AuthServer GetInstance() {
        if (mAuthServer == null) {
            mAuthServer = new AuthServer();
        }
        return mAuthServer;
    }

    public int GetIDType() {
        return Mcu.GetXYZ(new byte[32]);
    }

    public boolean IsAuthOk() {
        return Mcu.GetXYZ(new byte[32]) != 0;
    }

    public boolean IsNetOk(Context ct) {
        ConnectivityManager con = (ConnectivityManager) ct.getSystemService("connectivity");
        boolean wifi = con.getNetworkInfo(1).isConnectedOrConnecting();
        boolean internet = con.getNetworkInfo(0).isConnectedOrConnecting();
        if (wifi || internet) {
            Log.i(TAG, " wifi = " + wifi);
            Log.i(TAG, " internet = " + internet);
            return true;
        }
        Log.i(TAG, " IsNetOk = false");
        return false;
    }

    public boolean GettheLicense() {
        File[] lfileFile;
        String LicStr = null;
        String[] mCtouchPath = GetMountedStorage();
        Log.i(TAG, " mCtouchPath[] = " + mCtouchPath.length);
        for (int i = 0; i < mCtouchPath.length; i++) {
            if (!mCtouchPath[i].contains("/storage/sdcard0") && !mCtouchPath[i].contains("cdfs")) {
                File myfile = new File(mCtouchPath[i]);
                Log.i(TAG, " mCtouchPath[] = " + mCtouchPath[i]);
                if (myfile != null && myfile.isDirectory() && (lfileFile = myfile.listFiles()) != null) {
                    int j = 0;
                    while (true) {
                        if (j >= lfileFile.length) {
                            break;
                        }
                        if (lfileFile[j].getName().equalsIgnoreCase("License.dat")) {
                            Log.i(TAG, " find License file = " + lfileFile[j].getPath());
                            if (TsFile.fileIsExists(lfileFile[j].getPath())) {
                                try {
                                    LicStr = TsFile.readFileSdcardFile(lfileFile[j].getPath());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                Log.i(TAG, " read ok" + lfileFile[j].getName());
                                this.LisencePath = mCtouchPath[i];
                                break;
                            }
                        }
                        j++;
                    }
                }
            }
        }
        Log.i(TAG, " GettheLicense = " + LicStr);
        if (LicStr == null) {
            return false;
        }
        this.Liscense = LicStr;
        return true;
    }

    public int GetnID() {
        if (this.nGetIdOk <= 0) {
            return 0;
        }
        int nTEMP = this.nGetIdOk;
        this.nGetIdOk = 0;
        return nTEMP;
    }

    public void DestroyID() {
        byte[] DataFormat2 = new byte[32];
        for (int i = 0; i < 32; i++) {
            DataFormat2[i] = -1;
        }
        Mcu.SetXYZ(DataFormat2, 0);
        Mcu.SetXYZ((byte[]) null, 153);
    }

    public void SetnID(int nData) {
        this.nGetIdOk = nData;
    }

    public boolean bCheckAuthID(String ID) {
        this.DataFormat = ID.getBytes();
        if (this.DataFormat.length == 32 && Mcu.SetXYZ(this.DataFormat, 0) == 1) {
            return true;
        }
        return false;
    }

    public void UpdateMcu() {
        Mcu.SetXYZ((byte[]) null, 85);
    }

    public void UpLoadTheLocation(Context ct) {
        this.mcontext = ct;
    }
}
