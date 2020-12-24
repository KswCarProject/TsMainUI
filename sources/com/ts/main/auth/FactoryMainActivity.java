package com.ts.main.auth;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.SystemProperties;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.main.common.MainSet;
import com.txznet.sdk.TXZPoiSearchManager;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Radio;
import java.util.List;

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
        this.deviceInfo.setText(String.valueOf(ReadCmdLine()) + "  " + MainSet.getNumCores() + "核   内存:" + MainSet.GetInstance().GetMenSize() + "      内部空间:" + MainSet.GetInstance().GetEmmcSize());
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
        return MainSet.Testmode.KeyType == 2;
    }

    /* access modifiers changed from: package-private */
    public String ReadCmdLine() {
        String str = SystemProperties.get("ro.forfan.modem");
        if (IsWifiVer()) {
            return "WIFI版";
        }
        if (str.equalsIgnoreCase("modem_ch")) {
            return "国内版";
        }
        if (str.equalsIgnoreCase("modem_fo")) {
            return "全球通";
        }
        return "modem错误";
    }

    /* access modifiers changed from: package-private */
    public void enterSubWin(Class<?> cls) {
        Intent intent2 = new Intent();
        intent2.setClass(this, cls);
        startActivity(intent2);
    }

    public void UserAll() {
        this.nNUM++;
        if (this.nNUM % 30 == 0) {
            MainSet.SendIntent("com.nwd.action.ACTION_NWD_PRODUCTION");
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: package-private */
    public boolean Checkdata() {
        boolean bRet = true;
        if (MainSet.Testmode.CoreType != 1) {
            ShowOneMessage("主芯片配置错误");
            bRet = false;
        }
        if (bRet) {
            if (MainSet.Testmode.KeyType == 0 && MainSet.getNumCores() != 4) {
                ShowOneMessage("核心板不是4核,配置3561,不匹配");
                bRet = false;
            }
            if (MainSet.Testmode.KeyType == 1 && MainSet.getNumCores() != 8) {
                ShowOneMessage("核心板不是8核,配置3562,不匹配");
                bRet = false;
            }
            if (MainSet.Testmode.KeyType == 2 && MainSet.getNumCores() != 4) {
                ShowOneMessage("核心板不是4核,配置3561,不匹配");
                bRet = false;
            }
        }
        if (bRet) {
            String MemSize = MainSet.GetInstance().GetMenSize();
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
            String EmmcSize = MainSet.GetInstance().GetEmmcSize();
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
        if (bRet && !IsWifiVer() && MainSet.Testmode.bIMEI == 1) {
            TelephonyManager tm = (TelephonyManager) getSystemService("phone");
            if (tm.getDeviceId() == null || tm.getDeviceId().length() <= 5) {
                this.imeiInfo.setText("IMEI:错误");
                ShowOneMessage("IMEI 异常");
                bRet = false;
            } else {
                this.imeiInfo.setText("IMEI:" + tm.getDeviceId());
                this.ImeiImage.setImageBitmap(factory_test.createBarCode(tm.getDeviceId(), 512, 76));
            }
            if (MainSet.Testmode.bSim == 1 && MainSet.Testmode.HardType != 1) {
                if (tm.getSimSerialNumber() != null) {
                    this.iccifInfo.setText("ICCID:" + tm.getSimSerialNumber());
                    this.iccidImage.setImageBitmap(factory_test.createBarCode(tm.getSimSerialNumber(), 512, 76));
                } else {
                    ShowOneMessage("ICCID 异常");
                }
            }
        }
        return bRet;
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
