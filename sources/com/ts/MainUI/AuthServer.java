package com.ts.MainUI;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.SystemProperties;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.autochips.storage.EnvironmentATC;
import com.lgb.canmodule.Can;
import com.yyw.ts70xhw.Mcu;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AuthServer {
    public static final String AUTO_R = "ffkjtsautoreg";
    public static final String BASIC_TIME_STRING = "2018-5-01 00:00:00";
    private static final String TAG = "AuthServer";
    private static AuthServer mAuthServer = null;
    byte[] DataFormat;
    EnvironmentATC EnvATC = null;
    String Liscense = "error";
    String LisencePath;
    private final LocationListener locationListener2 = new LocationListener() {
        public void onLocationChanged(Location location) {
            int longitude = (int) (location.getLongitude() * 1000000.0d);
            int latitude = (int) (location.getLatitude() * 1000000.0d);
        }

        public void onProviderDisabled(String arg0) {
        }

        public void onProviderEnabled(String arg0) {
        }

        public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
        }
    };
    public GetID m_GetID = GetID.getInstance();
    Context mcontext;
    int nGetIdOk = 0;
    int nIsRun = 0;
    int nSendXy = 0;
    String strcustom = "";

    public String[] GetMountedStorage() {
        Log.i(TAG, "EnvATC ==" + this.EnvATC);
        if (this.EnvATC == null) {
            this.EnvATC = new EnvironmentATC(this.mcontext);
        }
        return this.EnvATC.getStorageMountedPaths();
    }

    public static AuthServer GetInstance() {
        if (mAuthServer == null) {
            mAuthServer = new AuthServer();
        }
        return mAuthServer;
    }

    public boolean IsAuthOk() {
        return Mcu.GetXYZ(new byte[32]) == 1;
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
        String LicStr = null;
        String[] mCtouchPath = GetMountedStorage();
        for (int i = 0; i < mCtouchPath.length; i++) {
            if (TsFile.fileIsExists(String.valueOf(mCtouchPath[i]) + "/" + "License.dat")) {
                try {
                    LicStr = TsFile.readFileSdcardFile(String.valueOf(mCtouchPath[i]) + "/" + "License.dat");
                    this.LisencePath = mCtouchPath[i];
                } catch (IOException e) {
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

    /* access modifiers changed from: package-private */
    public int nFileWirte(String MyStr) {
        Log.i(TAG, " nFileWirte = " + MyStr);
        String[] mCtouchPath = GetMountedStorage();
        Log.i(TAG, " mCtouchPath.length = " + mCtouchPath.length);
        for (int i = 0; i < mCtouchPath.length; i++) {
            Log.i(TAG, " mCtouchPath  " + i + "  " + mCtouchPath[i] + "/" + "License.dat");
            if (TsFile.fileIsExists(String.valueOf(mCtouchPath[i]) + "/" + "License.dat")) {
                Log.i(TAG, " nFileWirte  " + i + "  " + mCtouchPath[i] + "/" + "License.dat");
                try {
                    TsFile.writeFileSdcardFile(String.valueOf(mCtouchPath[i]) + "/" + "License.dat", MyStr);
                } catch (IOException e) {
                }
            }
        }
        return 1;
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
        Mcu.SetXYZ((byte[]) null, Can.CAN_HYUNDAI_WC);
    }

    public void SetnID(int nData) {
        this.nGetIdOk = nData;
    }

    /* access modifiers changed from: package-private */
    public boolean IsWifiVer() {
        return SystemProperties.get("ro.product.device").equals("evb3561sv_t_63_m0");
    }

    public void Updateid() {
        if (this.nIsRun != 1) {
            new Thread() {
                public void run() {
                    if (AuthServer.this.Liscense == null || AuthServer.this.Liscense.length() <= 5) {
                        AuthServer.this.nGetIdOk = 3;
                    } else {
                        AuthServer.this.nIsRun = 1;
                        if (AuthServer.this.mcontext != null) {
                            AuthServer.this.nGetIdOk = AuthServer.this.m_GetID.getID(AuthServer.this.Liscense, ((TelephonyManager) AuthServer.this.mcontext.getSystemService("phone")).getDeviceId());
                        } else {
                            AuthServer.this.nGetIdOk = AuthServer.this.m_GetID.getID(AuthServer.this.Liscense, "");
                        }
                        AuthServer.this.nIsRun = 0;
                    }
                    if (AuthServer.this.nGetIdOk == 1 && AuthServer.this.m_GetID.mDevice != null) {
                        AuthServer.this.DataFormat = AuthServer.this.m_GetID.mDevice.getBytes();
                        if (AuthServer.this.DataFormat.length != 32) {
                            AuthServer.this.nGetIdOk = 4;
                        } else if (Mcu.SetXYZ(AuthServer.this.DataFormat, 0) != 1) {
                            AuthServer.this.nGetIdOk = 5;
                        }
                    }
                }
            }.start();
        }
    }

    public void UpdateMcu() {
        Mcu.SetXYZ((byte[]) null, 85);
    }

    /* access modifiers changed from: private */
    public static String getSN(byte[] data) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(data);
            byte[] sign = messageDigest.digest();
            StringBuilder stringBuilder = new StringBuilder("");
            for (byte b : sign) {
                String v = Integer.toHexString(b & 255);
                if (v.length() < 2) {
                    stringBuilder.append(0);
                }
                stringBuilder.append(v);
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void UpdateAutoId(String Custom) {
        if (Custom != null && Custom.length() <= 4) {
            try {
                if (System.currentTimeMillis() < new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(BASIC_TIME_STRING).getTime()) {
                    Log.i(TAG, "time error = ");
                    return;
                }
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            this.strcustom = Custom;
            new Thread() {
                public void run() {
                    String TimeStr = String.format("%x", new Object[]{Long.valueOf((System.currentTimeMillis() / 1000) ^ 916074021)});
                    String StrLicense = AuthServer.getSN((String.valueOf(TimeStr) + AuthServer.AUTO_R + AuthServer.this.strcustom).getBytes());
                    AuthServer.this.nGetIdOk = GetID.getInstance().getIDAuto(String.valueOf(StrLicense) + TimeStr + AuthServer.this.strcustom, "");
                    if (AuthServer.this.nGetIdOk == 1 && AuthServer.this.m_GetID.mDevice != null) {
                        AuthServer.this.DataFormat = AuthServer.this.m_GetID.mDevice.getBytes();
                        if (AuthServer.this.DataFormat.length != 32) {
                            AuthServer.this.nGetIdOk = 4;
                        } else if (Mcu.SetXYZ(AuthServer.this.DataFormat, 0) != 1) {
                            AuthServer.this.nGetIdOk = 5;
                        }
                    }
                }
            }.start();
        }
    }

    public void UpLoadTheLocation(Context ct) {
        this.nSendXy = 0;
        this.mcontext = ct;
    }
}
