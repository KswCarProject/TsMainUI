package com.ts.main.auth;

import android.app.Application;
import android.content.Context;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.internal.view.SupportMenu;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.main.auth.AudioRecoderUtils;
import com.ts.main.common.MainSet;
import com.ts.main.common.MainVolume;
import com.ts.main.common.WinShow;
import com.yyw.ts70xhw.StSet;
import java.io.IOException;
import java.util.List;

public class FactoryTestWin {
    static FactoryTestWin MYFactoryTestWin = null;
    private static final String TAG = "TestWin";
    Button BtnStartT;
    Button BtnStartTest;
    /* access modifiers changed from: private */
    public Runnable CheckWifiState = new Runnable() {
        public void run() {
            FactoryTestWin.this.Task();
            FactoryTestWin.this.mHandler.postDelayed(FactoryTestWin.this.CheckWifiState, 30);
        }
    };
    TextView HmiVersion;
    ImageView ImeiImage;
    MyPhoneStateListener MyListener;
    WifiAdmin MyWifi;
    TextView deviceInfo;
    private GpsStatus.Listener gpsStatusListener = new GpsStatus.Listener() {
        public void onGpsStatusChanged(int event) {
            GpsStatus gpsStatus = FactoryTestWin.this.locationManager.getGpsStatus((GpsStatus) null);
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
                        FactoryTestWin.this.nVisNum = nNUM;
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
    LayoutInflater inflater;
    private LocationListener locationListener = new LocationListener() {
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.i(FactoryTestWin.TAG, "onStatusChanged ! provider =" + provider);
            Log.i(FactoryTestWin.TAG, "the number of satellites used to derive the fix is" + extras.getInt("satellites", 0));
        }

        public void onProviderEnabled(String provider) {
            Log.i(FactoryTestWin.TAG, "onProviderEnabled");
        }

        public void onProviderDisabled(String provider) {
            Log.i(FactoryTestWin.TAG, "onProviderDisabled");
        }

        public void onLocationChanged(Location location) {
        }
    };
    LocationManager locationManager;
    /* access modifiers changed from: private */
    public final Handler mHandler = new Handler();
    /* access modifiers changed from: private */
    public RelativeLayout mTestMainWin = null;
    Application m_Application;
    Context m_Context;
    int n4G = 0;
    int nNum = 0;
    int nState = 0;
    int nVisNum = 0;
    private List<GpsSatellite> satelliteList;
    private WindowManager wManager;
    TextView wifiGpsInfo;
    private WindowManager.LayoutParams wmParams;

    public static FactoryTestWin GetInstance() {
        if (MYFactoryTestWin == null) {
            MYFactoryTestWin = new FactoryTestWin();
        }
        return MYFactoryTestWin;
    }

    /* access modifiers changed from: package-private */
    public void GetFullParams() {
        this.wmParams = new WindowManager.LayoutParams();
        this.wmParams.type = 2003;
        this.wmParams.format = 1;
        this.wmParams.flags = 8;
        this.wmParams.gravity = 49;
        this.wmParams.width = -1;
        this.wmParams.height = -1;
        this.wmParams.flags |= 1024;
        this.wmParams.systemUiVisibility = 516;
    }

    public void Inint(Application MyApplication, Context MyContext) {
        if (this.m_Context == null) {
            this.m_Application = MyApplication;
            this.m_Context = MyContext;
            this.inflater = LayoutInflater.from(MyApplication);
            this.wManager = (WindowManager) MyContext.getSystemService("window");
        }
    }

    /* access modifiers changed from: package-private */
    public int nCacuWifiLev(int level) {
        return WifiManager.calculateSignalLevel(level, 5);
    }

    public void Task() {
        if (this.nState == 6) {
            this.nNum++;
            if (this.nNum % 30 == 0) {
                String showString = String.valueOf(String.valueOf("GPS=" + this.nVisNum + "颗" + "\r\n") + "4G强度=" + this.n4G + "\r\n") + "WIFI:\r\n";
                boolean WifibOK = false;
                this.MyWifi.updateWifiList();
                List<ScanResult> MyWifilist = this.MyWifi.getWifiList();
                if (MyWifilist.size() > 0) {
                    for (int i = 0; i < MyWifilist.size(); i++) {
                        showString = String.valueOf(showString) + " " + MyWifilist.get(i).SSID + "=" + nCacuWifiLev(MyWifilist.get(i).level) + "\r\n";
                        if (nCacuWifiLev(MyWifilist.get(i).level) >= 4) {
                            WifibOK = true;
                        }
                    }
                }
                this.wifiGpsInfo.setText(showString);
                if (WifibOK && this.nVisNum >= 3 && this.n4G >= 2) {
                    this.BtnStartT.setText("测试OK,按下关闭");
                    this.nState = 7;
                }
            }
        }
    }

    private class MyPhoneStateListener extends PhoneStateListener {
        private MyPhoneStateListener() {
        }

        /* synthetic */ MyPhoneStateListener(FactoryTestWin factoryTestWin, MyPhoneStateListener myPhoneStateListener) {
            this();
        }

        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);
            FactoryTestWin.this.n4G = signalStrength.getLevel();
        }
    }

    public void ShowMainWin() {
        HideMainWin();
        StSet.SetBLNight(6);
        StSet.SetBLDay(6);
        Evc.GetInstance().evol_vol_set(12);
        MainVolume.bVolNotShow = false;
        GetFullParams();
        this.mTestMainWin = (RelativeLayout) this.inflater.inflate(R.layout.activity_factory_testwin, (ViewGroup) null);
        this.HmiVersion = (TextView) this.mTestMainWin.findViewById(R.id.tex_testwin_hmiversion);
        this.deviceInfo = (TextView) this.mTestMainWin.findViewById(R.id.tex_fatctory_testwin);
        this.imeiInfo = (TextView) this.mTestMainWin.findViewById(R.id.tex_imei_device);
        this.ImeiImage = (ImageView) this.mTestMainWin.findViewById(R.id.image_imei_device);
        this.iccifInfo = (TextView) this.mTestMainWin.findViewById(R.id.tex_iccid_device);
        this.iccidImage = (ImageView) this.mTestMainWin.findViewById(R.id.image_iccid_device);
        this.wifiGpsInfo = (TextView) this.mTestMainWin.findViewById(R.id.showwifigpsinfo);
        this.HmiVersion.setText(String.valueOf(this.m_Context.getResources().getString(R.string.custom_num)) + MainSet.GetHMIVersion());
        this.deviceInfo.setText("  " + MainSet.getNumCores() + "核   内存:" + MainSet.GetInstance().GetMenSize() + "      内部空间:" + MainSet.GetInstance().GetEmmcSize());
        this.BtnStartT = (Button) this.mTestMainWin.findViewById(R.id.btn_fatctory_wel_start);
        this.BtnStartTest = (Button) this.mTestMainWin.findViewById(R.id.btn_fatctory_test_start);
        TelephonyManager tm = (TelephonyManager) this.m_Context.getSystemService("phone");
        this.MyListener = new MyPhoneStateListener(this, (MyPhoneStateListener) null);
        tm.listen(this.MyListener, 256);
        this.MyWifi = new WifiAdmin(this.m_Context);
        this.MyWifi.openWifi(this.m_Context);
        this.MyWifi.startScan(this.m_Context);
        this.mHandler.postDelayed(this.CheckWifiState, 30);
        regiseterListener();
        if (tm.getDeviceId() == null || tm.getDeviceId().length() <= 5) {
            this.imeiInfo.setText("IMEI 未烧录");
            this.imeiInfo.setTextColor(SupportMenu.CATEGORY_MASK);
        } else {
            this.imeiInfo.setText("IMEI:" + tm.getDeviceId());
            this.ImeiImage.setImageBitmap(factory_test.createBarCode(tm.getDeviceId(), 512, 76));
        }
        if (tm.getSimSerialNumber() != null) {
            this.iccifInfo.setText("ICCID:" + tm.getSimSerialNumber());
            this.iccidImage.setImageBitmap(factory_test.createBarCode(tm.getSimSerialNumber().toUpperCase(), 512, 76));
        } else {
            this.iccifInfo.setText("SIM卡 未识别到");
            this.iccifInfo.setTextColor(SupportMenu.CATEGORY_MASK);
        }
        this.nState = 1;
        this.BtnStartT.setText("按下自动录音3秒后回放");
        this.BtnStartTest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                WinShow.TsEnterMode(3);
                FactoryTestWin.this.HideMainWin();
            }
        });
        this.BtnStartT.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                RelativeLayout.LayoutParams parat = new RelativeLayout.LayoutParams(-2, -2);
                if (FactoryTestWin.this.nState == 7) {
                    FactoryTestWin.this.HideMainWin();
                    MainSet.GetInstance().StartMboilApp();
                    return;
                }
                switch (FactoryTestWin.this.nState) {
                    case 1:
                        FactoryTestWin.this.BtnStartTest.setVisibility(4);
                        FactoryTestWin.this.imeiInfo.setVisibility(4);
                        FactoryTestWin.this.ImeiImage.setVisibility(4);
                        FactoryTestWin.this.iccifInfo.setVisibility(4);
                        FactoryTestWin.this.iccidImage.setVisibility(4);
                        FactoryTestWin.this.StartRecord(3000);
                        return;
                    case 2:
                        FactoryTestWin.this.deviceInfo.setVisibility(4);
                        FactoryTestWin.this.HmiVersion.setVisibility(4);
                        parat.leftMargin = 90;
                        parat.topMargin = 50;
                        parat.addRule(9);
                        parat.addRule(10);
                        FactoryTestWin.this.BtnStartT.setLayoutParams(parat);
                        FactoryTestWin.this.mTestMainWin.setBackgroundColor(SupportMenu.CATEGORY_MASK);
                        FactoryTestWin.this.BtnStartT.setText("红");
                        FactoryTestWin.this.nState = 3;
                        return;
                    case 3:
                        parat.leftMargin = 90;
                        parat.bottomMargin = 50;
                        parat.addRule(9);
                        parat.addRule(12);
                        FactoryTestWin.this.BtnStartT.setLayoutParams(parat);
                        FactoryTestWin.this.mTestMainWin.setBackgroundColor(-16711936);
                        FactoryTestWin.this.BtnStartT.setText("绿");
                        FactoryTestWin.this.nState = 4;
                        return;
                    case 4:
                        parat.rightMargin = 90;
                        parat.bottomMargin = 50;
                        parat.addRule(11);
                        parat.addRule(12);
                        FactoryTestWin.this.BtnStartT.setLayoutParams(parat);
                        FactoryTestWin.this.mTestMainWin.setBackgroundColor(-16776961);
                        FactoryTestWin.this.BtnStartT.setText("蓝");
                        FactoryTestWin.this.nState = 5;
                        return;
                    case 5:
                        parat.bottomMargin = 70;
                        parat.addRule(14);
                        parat.addRule(12);
                        FactoryTestWin.this.BtnStartT.setLayoutParams(parat);
                        FactoryTestWin.this.BtnStartT.setText("测试无线信号中...");
                        FactoryTestWin.this.mTestMainWin.setBackgroundColor(-16777216);
                        FactoryTestWin.this.nState = 6;
                        FactoryTestWin.this.deviceInfo.setVisibility(0);
                        FactoryTestWin.this.deviceInfo.setText("");
                        return;
                    default:
                        return;
                }
            }
        });
        this.mTestMainWin.setBackgroundColor(-16777216);
        this.wManager.addView(this.mTestMainWin, this.wmParams);
    }

    public void HideMainWin() {
        if (this.mTestMainWin != null) {
            this.wManager.removeView(this.mTestMainWin);
            this.mTestMainWin = null;
        }
    }

    public void StartRecord(final long nTime) {
        final AudioRecoderUtils mMyRecord = new AudioRecoderUtils("/mnt/sdcard/");
        Evc.GetInstance().evol_workmode_set(0);
        mMyRecord.setOnAudioStatusUpdateListener(new AudioRecoderUtils.OnAudioStatusUpdateListener() {
            public void onUpdate(double db, long time) {
                Log.i(FactoryTestWin.TAG, "db==" + db);
                Log.i(FactoryTestWin.TAG, "time==" + time);
                int fenbei = ((int) (3000.0d + ((6000.0d * db) / 100.0d))) / 100;
                if (time > nTime) {
                    mMyRecord.stopRecord();
                }
                FactoryTestWin.this.deviceInfo.setText(String.valueOf(fenbei) + "DB");
            }

            public void onStop(String filePath) {
                Log.i(FactoryTestWin.TAG, "filePath==" + filePath);
                FactoryTestWin.this.PlayFile(filePath);
                FactoryTestWin.this.nState = 2;
                FactoryTestWin.this.BtnStartT.setText("开始测试显示");
            }
        });
        mMyRecord.startRecord();
    }

    /* access modifiers changed from: package-private */
    public void PlayFile(String strPath) {
        MediaPlayer mp = new MediaPlayer();
        try {
            Log.i(TAG, "PlayFile==" + strPath);
            mp.setDataSource(strPath);
        } catch (IllegalArgumentException e) {
            Log.i(TAG, "IllegalArgumentException 111");
            e.printStackTrace();
        } catch (SecurityException e2) {
            Log.i(TAG, "IllegalArgumentException 222");
            e2.printStackTrace();
        } catch (IllegalStateException e3) {
            Log.i(TAG, "IllegalArgumentException 333");
            e3.printStackTrace();
        } catch (IOException e4) {
            Log.i(TAG, "IllegalArgumentException 444");
            e4.printStackTrace();
        }
        try {
            mp.prepare();
        } catch (IOException | IllegalStateException e5) {
            e5.printStackTrace();
        }
        mp.start();
    }

    private void regiseterListener() {
        if (this.locationManager == null) {
            this.locationManager = (LocationManager) this.m_Context.getSystemService("location");
        }
        this.locationManager.requestLocationUpdates("gps", 1000, 0.0f, this.locationListener);
        this.locationManager.addGpsStatusListener(this.gpsStatusListener);
    }
}
