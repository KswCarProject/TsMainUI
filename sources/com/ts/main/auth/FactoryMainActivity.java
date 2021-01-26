package com.ts.main.auth;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.SdkConstants;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.main.common.MainSet;
import com.ts.main.common.tool;
import com.txznet.sdk.TXZPoiSearchManager;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Radio;
import java.io.File;
import java.io.FileFilter;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class FactoryMainActivity extends Activity implements UserCallBack {
    private static final String TAG = "[scj]Test";
    static int bGpsFix = 0;
    Button BtnStartT;
    ImageView ImeiImage;
    TextView deviceInfo;
    private GpsStatus.Listener gpsStatusListener = new GpsStatus.Listener() {
        public void onGpsStatusChanged(int event) {
            GpsStatus gpsStatus = FactoryMainActivity.this.locationManager.getGpsStatus((GpsStatus) null);
            switch (event) {
                case 3:
                    int timeToFirstFix = gpsStatus.getTimeToFirstFix();
                    return;
                case 4:
                    int nNUM = 0;
                    for (GpsSatellite satellite : gpsStatus.getSatellites()) {
                        if (satellite.getSnr() > 30.0f) {
                            nNUM++;
                        }
                    }
                    if (nNUM >= 3) {
                        FactoryMainActivity.bGpsFix = 1;
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };
    int iCCItest = 180;
    ImageView iccidImage;
    TextView iccifInfo;
    TextView imeiInfo;
    protected Intent intent;
    private LocationListener locationListener = new LocationListener() {
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.i(FactoryMainActivity.TAG, "onStatusChanged ! provider =" + provider);
            Log.i(FactoryMainActivity.TAG, "the number of satellites used to derive the fix is" + extras.getInt("satellites", 0));
        }

        public void onProviderEnabled(String provider) {
            Log.i(FactoryMainActivity.TAG, "onProviderEnabled");
        }

        public void onProviderDisabled(String provider) {
            Log.i(FactoryMainActivity.TAG, "onProviderDisabled");
        }

        public void onLocationChanged(Location location) {
        }
    };
    LocationManager locationManager;
    private ActivityManager mActivityManager = null;
    AlertDialog m_dialgo = null;
    int nNUM = 0;
    private List<GpsSatellite> satelliteList;
    TextView txeMyTextView;

    private void ShowOneMessage(String str) {
        this.m_dialgo = new AlertDialog.Builder(this).setTitle("系统提示").setMessage(str).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                FactoryMainActivity.this.m_dialgo.dismiss();
            }
        }).show();
        MainSet.GetInstance().RefreshDialog(this, this.m_dialgo);
    }

    /* access modifiers changed from: package-private */
    public String GetMenSize() {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        if (this.mActivityManager == null) {
            this.mActivityManager = (ActivityManager) getSystemService(SdkConstants.TAG_ACTIVITY);
        }
        this.mActivityManager.getMemoryInfo(memoryInfo);
        long nSize = (memoryInfo.totalMem / 1024) / 1024;
        if (nSize < 1024) {
            if (nSize < 512) {
                return "512MB";
            }
            if (nSize < 768) {
                return "768MB";
            }
            return "1G";
        } else if (nSize <= 1024 || nSize >= 2048) {
            if (nSize > 2048 && nSize < 3072) {
                return "3G";
            }
            if (nSize <= 3072 || nSize >= 4096) {
                return "8G";
            }
            return "4G";
        } else if (nSize < 1539) {
            return "1.5G";
        } else {
            return "2G";
        }
    }

    private int getNumCores() {
        try {
            File[] files = new File("/sys/devices/system/cpu/").listFiles(new FileFilter() {
                public boolean accept(File pathname) {
                    if (Pattern.matches("cpu[0-9]", pathname.getName())) {
                        return true;
                    }
                    return false;
                }
            });
            Log.d(TAG, "CPU Count: " + files.length);
            return files.length;
        } catch (Exception e) {
            Log.d(TAG, "CPU Count: Failed.");
            e.printStackTrace();
            return 1;
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factory_welcome);
        this.BtnStartT = (Button) findViewById(R.id.btn_fatctory_wel_start);
        this.txeMyTextView = (TextView) findViewById(R.id.btn_fatctory_wel_mes);
        this.deviceInfo = (TextView) findViewById(R.id.btn_fatctory_device);
        this.imeiInfo = (TextView) findViewById(R.id.tex_imei_device);
        this.ImeiImage = (ImageView) findViewById(R.id.image_imei_device);
        this.iccifInfo = (TextView) findViewById(R.id.tex_iccid_device);
        this.iccidImage = (ImageView) findViewById(R.id.image_iccid_device);
        this.deviceInfo.setText("  " + getNumCores() + "核   内存:" + GetMenSize() + "      内部空间:" + tool.GetInstance().GetEmmcSize());
        this.BtnStartT.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                factory_test.TotalTime = factory_test.getTickCount();
                factory_test.Clear();
                if (!MainSet.bKeyBroad) {
                    FactoryMainActivity.this.enterSubWin(FactorytestarmActivity.class);
                } else if (MainSet.GetInstance().GetMountedStorage().length < MainSet.Testmode.USB_PORT + 1) {
                    FactoryMainActivity.this.BtnStartT.setText("USB错误");
                } else {
                    FactoryMainActivity.this.enterSubWin(FactoryRadioTestActivity.class);
                    FtSet.SetBtId(TXZPoiSearchManager.DEFAULT_SEARCH_TIMEOUT);
                    FtSet.Save(0);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public boolean IsWifiVer() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public void enterSubWin(Class<?> cls) {
        Intent intent2 = new Intent();
        intent2.setClass(this, cls);
        startActivity(intent2);
    }

    public void UserAll() {
        if (this.iCCItest > 0) {
            this.iCCItest--;
            if (this.iCCItest % 30 == 0) {
                CheckICCID();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    public Bitmap createBarCode(CharSequence content, int BAR_WIDTH, int BAR_HEIGHT) {
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
                    i = ViewCompat.MEASURED_STATE_MASK;
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

    /* access modifiers changed from: package-private */
    public boolean Checkdata() {
        boolean bRet = true;
        if (MainSet.Testmode.CoreType != 2) {
            ShowOneMessage("主芯片配置错误");
            bRet = false;
        }
        if (bRet) {
            MainSet.Testmode.KeyType = 0;
        }
        if (bRet) {
            String MemSize = GetMenSize();
            switch (MainSet.Testmode.ram) {
                case 0:
                    if (!MemSize.equals("512MB")) {
                        ShowOneMessage("当前内存:" + MemSize + "配置为512M 不匹配");
                        bRet = false;
                        break;
                    }
                    break;
                case 1:
                    if (!MemSize.equals("768MB")) {
                        ShowOneMessage("当前内存:" + MemSize + "配置为768M 不匹配");
                        bRet = false;
                        break;
                    }
                    break;
                case 2:
                    if (!MemSize.equals("1G")) {
                        ShowOneMessage("当前内存:" + MemSize + "配置为1G 不匹配");
                        bRet = false;
                        break;
                    }
                    break;
                case 3:
                    if (!MemSize.equals("1.5G")) {
                        ShowOneMessage("当前内存:" + MemSize + "配置为1.5G 不匹配");
                        bRet = false;
                        break;
                    }
                    break;
                case 4:
                    if (!MemSize.equals("2G")) {
                        ShowOneMessage("当前内存:" + MemSize + "配置为2G 不匹配");
                        bRet = false;
                        break;
                    }
                    break;
                case 5:
                    if (!MemSize.equals("3G")) {
                        ShowOneMessage("当前内存:" + MemSize + "配置为3G 不匹配");
                        bRet = false;
                        break;
                    }
                    break;
                case 6:
                    if (!MemSize.equals("4G")) {
                        ShowOneMessage("当前内存:" + MemSize + "配置为4G 不匹配");
                        bRet = false;
                        break;
                    }
                    break;
                case 7:
                    if (!MemSize.equals("6G")) {
                        ShowOneMessage("当前内存:" + MemSize + "配置为6G 不匹配");
                        bRet = false;
                        break;
                    }
                    break;
                case 8:
                    if (!MemSize.equals("8G")) {
                        ShowOneMessage("当前内存:" + MemSize + "配置为8G 不匹配");
                        bRet = false;
                        break;
                    }
                    break;
            }
        }
        if (bRet) {
            String EmmcSize = tool.GetInstance().GetEmmcSize();
            switch (MainSet.Testmode.rom) {
                case 0:
                    if (!EmmcSize.equals("16GB")) {
                        ShowOneMessage("当前存储:" + EmmcSize + "配置为16G 不匹配");
                        bRet = false;
                        break;
                    }
                    break;
                case 1:
                    if (!EmmcSize.equals("32GB")) {
                        ShowOneMessage("当前存储:" + EmmcSize + "配置为32G 不匹配");
                        bRet = false;
                        break;
                    }
                    break;
                case 2:
                    if (!EmmcSize.equals("64GB")) {
                        ShowOneMessage("当前存储:" + EmmcSize + "配置为64G 不匹配");
                        bRet = false;
                        break;
                    }
                    break;
                case 3:
                    if (!EmmcSize.equals("128GB")) {
                        ShowOneMessage("当前存储:" + EmmcSize + "配置为128G 不匹配");
                        bRet = false;
                        break;
                    }
                    break;
            }
        }
        if (bRet) {
            TelephonyManager tm = (TelephonyManager) getSystemService("phone");
            if (MainSet.Testmode.bIMEI == 1) {
                if (tm.getDeviceId() == null || tm.getDeviceId().length() <= 5) {
                    this.imeiInfo.setText("IMEI:错误");
                    ShowOneMessage("IMEI 异常");
                    bRet = false;
                } else {
                    this.imeiInfo.setText("IMEI:" + tm.getDeviceId());
                    this.ImeiImage.setImageBitmap(createBarCode(tm.getDeviceId(), 512, 76));
                }
            }
        }
        this.iCCItest = 180;
        return bRet;
    }

    /* access modifiers changed from: package-private */
    public void CheckICCID() {
        if (!IsWifiVer()) {
            TelephonyManager tm = (TelephonyManager) getSystemService("phone");
            if (MainSet.Testmode.bSim != 1) {
                return;
            }
            if (tm.getSimSerialNumber() != null) {
                this.iccifInfo.setText("ICCID:" + tm.getSimSerialNumber());
                this.iccidImage.setImageBitmap(createBarCode(tm.getSimSerialNumber(), 512, 76));
            } else if (this.iCCItest < 30 && tm.getSimSerialNumber() == null) {
                ShowOneMessage("ICCID 异常");
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        MainTask.GetInstance().SetUserCallBack(this);
        factory_test.Starttime = factory_test.getTickCount();
        factory_test.ReadReport();
        if (factory_test.Err.equals(" ")) {
            this.txeMyTextView.setText("全部正常");
        } else {
            this.txeMyTextView.setText(factory_test.Err);
        }
        if (!MainSet.GetInstance().IsTestMode()) {
            this.BtnStartT.setVisibility(4);
        } else if (!Checkdata()) {
            this.BtnStartT.setVisibility(4);
        } else {
            Radio.TuneBandFq(0, 8750);
            if (MainSet.Testmode.bGps == 1) {
                regiseterListener();
            } else {
                bGpsFix = 1;
            }
        }
        super.onResume();
    }

    private void regiseterListener() {
        if (this.locationManager == null) {
            this.locationManager = (LocationManager) getSystemService("location");
        }
        this.locationManager.requestLocationUpdates("gps", 1000, 0.0f, this.locationListener);
        this.locationManager.addGpsStatusListener(this.gpsStatusListener);
    }
}
