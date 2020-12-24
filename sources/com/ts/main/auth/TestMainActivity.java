package com.ts.main.auth;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StatFs;
import android.os.SystemClock;
import android.support.v4.internal.view.SupportMenu;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.autochips.storage.EnvironmentATC;
import com.lgb.canmodule.Can;
import com.ts.MainUI.Evc;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.TsFile;
import com.ts.MainUI.UserCallBack;
import com.ts.bt.BtExe;
import com.ts.can.CanCameraUI;
import com.ts.main.auth.TsImageView;
import com.ts.main.common.MainUI;
import com.ts.main.common.MainVerSion;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;
import com.yyw.ts70xhw.Iop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestMainActivity extends Activity implements UserCallBack {
    private static final int BT_TEST = 3;
    private static final int CHECK_OK = 0;
    private static final int DVD_TEST = 4;
    private static final String DataFile = "/sdcard/scj_test/test.bin";
    private static final String DataFilePath = "/sdcard/scj_test/";
    static EnvironmentATC EnvATC = null;
    private static final int GPS_TEST = 2;
    private static final String SDDataFile = "/storage/ext_sdcard1/test.bin";
    private static final String SDDataFile2 = "/storage/ext_sdcard2/test.bin";
    private static final String SDDataFilePath = "/storage/ext_sdcard1";
    private static final String SDDataFilePath2 = "/storage/ext_sdcard2";
    private static final int SD_TEST = 5;
    private static final String TAG = "[scj:]Test";
    private static final int TEST_NUM = 5;
    private static final int TEST_TOTAL_TIME = 90;
    private static final String USBDataFile = "/storage/udisk2/test.bin";
    private static final String USBDataFile2 = "/storage/udisk1/test.bin";
    private static final String USBDataFilePath = "/storage/udisk2";
    private static final String USBDataFilePath2 = "/storage/udisk1";
    private static final int USB_TEST = 1;
    static int nNum = 0;
    TextView BtVersion;
    TextView DvpVersion;
    TextView IcVersion;
    String MemSize;
    ParamButton ShowInfoButton;
    private String[] Showinfo = {"USB测试", "GPS测试", "蓝牙测试", "DVD测试", "SD测试"};
    ParamButton StartButton;
    long Starttime = 0;
    RelativeLayoutManager TestManage;
    TextView[] TestResultInfo = new TextView[5];
    int Time = 0;
    private GpsStatus.Listener gpsStatusListener = new GpsStatus.Listener() {
        public void onGpsStatusChanged(int event) {
            GpsStatus gpsStatus = TestMainActivity.this.locationManager.getGpsStatus((GpsStatus) null);
            switch (event) {
                case 3:
                    int timeToFirstFix = gpsStatus.getTimeToFirstFix();
                    return;
                case 4:
                    Iterable<GpsSatellite> satellites = gpsStatus.getSatellites();
                    TestMainActivity.this.satelliteList = new ArrayList();
                    for (GpsSatellite satellite : satellites) {
                        TestMainActivity.this.satelliteList.add(satellite);
                    }
                    if (TestMainActivity.this.mtsivSatelliteSignal != null) {
                        TestMainActivity.this.mtsivSatelliteSignal.postInvalidate();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };
    private LocationListener locationListener = new LocationListener() {
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.i(TestMainActivity.TAG, "onStatusChanged ! provider =" + provider);
            Log.i(TestMainActivity.TAG, "the number of satellites used to derive the fix is" + extras.getInt("satellites", 0));
        }

        public void onProviderEnabled(String provider) {
            Log.i(TestMainActivity.TAG, "onProviderEnabled");
        }

        public void onProviderDisabled(String provider) {
            Log.i(TestMainActivity.TAG, "onProviderDisabled");
        }

        public void onLocationChanged(Location location) {
            Bundle bundle = location.getExtras();
            if (bundle != null) {
                int fixedsatellite = bundle.getInt("satellites", 0);
                new StringBuffer();
                if (fixedsatellite > 3 && TestMainActivity.this.nTestMode == 1 && TestMainActivity.this.nData[2] == 0) {
                    TestMainActivity.this.nData[2] = TestMainActivity.this.Time;
                }
            }
        }
    };
    LocationManager locationManager;
    BtExe mBtExe = BtExe.getBtInstance();
    /* access modifiers changed from: private */
    public TsImageView mtsivSatelliteSignal;
    int[] nData = new int[6];
    int nStep = 0;
    int nTestMode = 0;
    /* access modifiers changed from: private */
    public List<GpsSatellite> satelliteList;
    private TsImageView.UserPaint satellitesSignalPaint = new TsImageView.UserPaint() {
        private Paint paint = new Paint();

        public void userPaint(Canvas canvas) {
            if (TestMainActivity.this.satelliteList != null) {
                int j = 0;
                for (int i = 0; i < TestMainActivity.this.satelliteList.size(); i++) {
                    GpsSatellite satellite = (GpsSatellite) TestMainActivity.this.satelliteList.get(i);
                    if (satellite.getSnr() > 0.0f) {
                        drawSatellite(canvas, satellite, j * 55, Can.CAN_JAC_REFINE_OD);
                        j++;
                    }
                }
            }
        }

        private void drawSatellite(Canvas canvas, GpsSatellite gpsSatellite, int cx, int cy) {
            float snr = gpsSatellite.getSnr();
            float prn = (float) gpsSatellite.getPrn();
            this.paint.setColor(TestMainActivity.this.getSNRColor(snr));
            this.paint.setTextSize(28.0f);
            this.paint.setTextAlign(Paint.Align.CENTER);
            Rect rect = new Rect(cx, cy - ((int) ((((float) cy) * snr) / 100.0f)), cx + 40, cy);
            canvas.drawText(new StringBuilder().append((int) snr).toString(), (float) rect.centerX(), (float) ((cy - ((int) ((((float) cy) * snr) / 100.0f))) - 20), this.paint);
            canvas.drawRect(rect, this.paint);
            canvas.drawText(new StringBuilder().append((int) prn).toString(), (float) rect.centerX(), (float) (cy + 30), this.paint);
        }
    };

    /* access modifiers changed from: package-private */
    public int ReadData() {
        if (!TsFile.fileIsExists(DataFile)) {
            TsFile.NewDir(DataFilePath);
            for (int i = 0; i < 5; i++) {
                this.nData[i] = 0;
            }
            TsFile.writer(DataFile, this.nData);
            return 0;
        }
        TsFile.reader(DataFile, this.nData);
        if (this.nData[0] == 1) {
            return 1;
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public void WriteData() {
        TsFile.writer(DataFile, this.nData);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_main);
        this.TestManage = new RelativeLayoutManager(this, R.id.activity_test_mainid);
        this.mtsivSatelliteSignal = (TsImageView) findViewById(R.id.satellite_signal);
        this.mtsivSatelliteSignal.setUserPaint(this.satellitesSignalPaint);
        this.IcVersion = this.TestManage.AddText(15, 0, CanCameraUI.BTN_GOLF_WC_MODE1, 40);
        this.IcVersion.setTextColor(-1);
        for (int i = 0; i < 5; i++) {
            this.TestResultInfo[i] = this.TestManage.AddText(CanCameraUI.BTN_CC_WC_DIRECTION1, (i * 30) + 0, 300, 30);
            this.TestResultInfo[i].setTextColor(-1);
        }
        this.StartButton = this.TestManage.AddButton(30, Can.CAN_JAC_REFINE_OD, Can.CAN_CC_HF_DJ, 100);
        this.StartButton.setText("开始测试");
        this.StartButton.setTextSize(15.0f);
        this.StartButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                TestMainActivity.this.nTestMode = 1;
                TestMainActivity.this.Starttime = SystemClock.uptimeMillis();
                for (int i = 0; i < 6; i++) {
                    TestMainActivity.this.nData[i] = 0;
                }
                if (TestMainActivity.this.IsHaveDvd()) {
                    MainUI.GetInstance().EnterMyDvdMode(4);
                    Evc.GetInstance().evol_workmode_set(2);
                } else {
                    TestMainActivity.this.nData[4] = 1;
                }
                TestMainActivity.this.ShowInfoButton.setVisibility(4);
            }
        });
        this.ShowInfoButton = this.TestManage.AddButton(260, Can.CAN_JAC_REFINE_OD, Can.CAN_CHANA_CS75_WC, 100);
        this.ShowInfoButton.setText("查看报告");
        this.ShowInfoButton.setTextSize(15.0f);
        this.ShowInfoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                TestMainActivity.this.nTestMode = 2;
            }
        });
        if (IsHaveDvd()) {
            this.DvpVersion = this.TestManage.AddText(15, 40, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 40);
            this.DvpVersion.setTextColor(-1);
        }
        this.BtVersion = this.TestManage.AddText(15, 80, CanCameraUI.BTN_TRUMPCHI_GS4_MODE1, 40);
        this.BtVersion.setTextColor(-1);
        if (ReadData() == 1) {
            this.ShowInfoButton.setVisibility(0);
        } else {
            this.ShowInfoButton.setVisibility(4);
        }
        UpdateInfo();
        regiseterListener();
    }

    /* access modifiers changed from: package-private */
    public boolean IsHaveDvd() {
        if (Iop.GetChipVer() == 0 || Iop.GetChipVer() == 2) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public int getSNRColor(float snr) {
        if (snr <= 0.0f) {
            return -7829368;
        }
        if (snr > 0.0f && snr < 10.0f) {
            return SupportMenu.CATEGORY_MASK;
        }
        if (snr >= 10.0f && snr < 20.0f) {
            return Color.rgb(255, 102, 0);
        }
        if (snr >= 20.0f && snr < 30.0f) {
            return Color.rgb(255, 255, 0);
        }
        if (snr < 30.0f || snr >= 40.0f) {
            return -16711936;
        }
        return Color.rgb(204, 255, 0);
    }

    private void regiseterListener() {
        if (this.locationManager == null) {
            this.locationManager = (LocationManager) getSystemService("location");
        }
        this.locationManager.requestLocationUpdates("gps", 1000, 0.0f, this.locationListener);
        this.locationManager.addGpsStatusListener(this.gpsStatusListener);
    }

    /* access modifiers changed from: package-private */
    public boolean AllIsOK() {
        int nNum2 = 0;
        for (int i = 0; i < 5; i++) {
            if (this.nData[i + 1] > 0) {
                nNum2++;
            }
        }
        if (nNum2 == 5) {
            return true;
        }
        return false;
    }

    public static long CheckStorageAvailableMem(String Mypath) {
        if (Mypath == null || Mypath.length() <= 0) {
            return 0;
        }
        StatFs stat = new StatFs(new File(Mypath).getPath());
        long availableSize1 = ((long) stat.getBlockSize()) * ((long) stat.getAvailableBlocks());
        Log.i(TAG, "CheckStorageAvailableMem:  " + Mypath + ":" + ((availableSize1 / 1024) / 1024) + "MB");
        return availableSize1;
    }

    /* access modifiers changed from: package-private */
    public String GetMemSize() {
        int nNawHave = (int) ((getFolderSize(new File("/sdcard/")) / 1024) / 1024);
        Log.i(TAG, "nNawHave ==" + nNawHave);
        int navaile = (int) ((CheckStorageAvailableMem("/sdcard/") / 1024) / 1024);
        Log.i(TAG, "navaile ==" + navaile);
        if (nNawHave + navaile > 8192) {
            return "16G";
        }
        return "8G";
    }

    /* access modifiers changed from: package-private */
    public void UpdateInfo() {
        switch (Iop.GetChipVer()) {
            case 0:
                this.IcVersion.setText("主芯片版本: 8317  双核有碟 " + this.MemSize);
                break;
            case 1:
                this.IcVersion.setText("主芯片版本: 8217  双核无碟 " + this.MemSize);
                break;
            case 2:
                this.IcVersion.setText("主芯片版本: 8327  四核有碟 " + this.MemSize);
                break;
            case 3:
                this.IcVersion.setText("主芯片版本: 8227  四核无碟 " + this.MemSize);
                break;
        }
        if (this.DvpVersion != null) {
            this.DvpVersion.setText("DVD版本:" + MainVerSion.ROM_VERSION);
        }
        if (this.BtVersion != null) {
            this.BtVersion.setText("蓝牙名称:" + BtExe.getBtInstance().getDevName());
        }
        if (this.nTestMode == 2) {
            for (int i = 0; i < 5; i++) {
                if (this.nData[i + 1] > 0) {
                    this.TestResultInfo[i].setTextColor(-16711936);
                    if (i + 1 == 2) {
                        this.TestResultInfo[i].setText(String.valueOf(this.Showinfo[i]) + ":" + "成功" + "(" + this.nData[i + 1] + "秒)");
                    } else {
                        this.TestResultInfo[i].setText(String.valueOf(this.Showinfo[i]) + ":" + "成功");
                    }
                } else {
                    this.TestResultInfo[i].setTextColor(SupportMenu.CATEGORY_MASK);
                    this.TestResultInfo[i].setText(String.valueOf(this.Showinfo[i]) + ":" + "失败");
                }
            }
        } else if (this.nTestMode == 1) {
            this.Time = (int) ((SystemClock.uptimeMillis() - this.Starttime) / 1000);
            this.StartButton.setText("测试中(" + this.Time + "/" + 90 + ")");
            if (this.Time >= 90 || AllIsOK()) {
                this.nTestMode = 2;
                this.nData[0] = 1;
                if (AllIsOK()) {
                    new AlertDialog.Builder(this).setTitle("系统提示").setMessage("测试成功").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            TestMainActivity.this.finish();
                        }
                    }).show();
                } else {
                    new AlertDialog.Builder(this).setTitle("系统提示").setMessage("测试失败").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    }).show();
                }
                WriteData();
            }
            for (int i2 = 0; i2 < 5; i2++) {
                if (this.nData[i2 + 1] > 0) {
                    this.TestResultInfo[i2].setTextColor(-16711936);
                    if (i2 + 1 == 2) {
                        this.TestResultInfo[i2].setText(String.valueOf(this.Showinfo[i2]) + ":" + "成功" + "(" + this.nData[i2 + 1] + "秒)");
                    } else {
                        this.TestResultInfo[i2].setText(String.valueOf(this.Showinfo[i2]) + ":" + "成功");
                    }
                } else {
                    this.TestResultInfo[i2].setTextColor(-1);
                    if (i2 + 1 == 2) {
                        if (this.nData[2] == 0) {
                            this.TestResultInfo[i2].setText(String.valueOf(this.Showinfo[i2]) + ":" + "测试中" + "(" + this.Time + "秒)");
                        }
                    } else if (i2 + 1 == 1) {
                        if (!StorageMounted(USBDataFilePath)) {
                            this.TestResultInfo[i2].setText(String.valueOf(this.Showinfo[i2]) + ":" + "未连接");
                        }
                    } else if (i2 + 1 != 5) {
                        this.TestResultInfo[i2].setText(String.valueOf(this.Showinfo[i2]) + ":" + "测试中");
                    } else if (!StorageMounted(SDDataFilePath)) {
                        this.TestResultInfo[i2].setText(String.valueOf(this.Showinfo[i2]) + ":" + "未连接");
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        MainTask.GetInstance().SetUserCallBack(this);
        this.MemSize = GetMemSize();
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    public void UserAll() {
        int i = nNum + 1;
        nNum = i;
        if (i % 30 == 0) {
            UpdateInfo();
            CheckUsbSD();
        }
        CheckBt();
        CheckDvd();
    }

    /* access modifiers changed from: package-private */
    public boolean CheckMemory(String Path, String File) {
        if (StorageMounted(Path)) {
            if (!TsFile.fileIsExists(File)) {
                try {
                    TsFile.writeFileSdcardFile(File, TAG);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            String Read = new String();
            try {
                Read = TsFile.readFileSdcardFile(File);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            if (Read == null || !Read.equals(TAG)) {
                return false;
            }
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void CheckUsbSD() {
        if (this.nData[1] == 0) {
            if (CheckMemory(USBDataFilePath, USBDataFile)) {
                this.nData[1] = 1;
            }
            if (CheckMemory(USBDataFilePath2, USBDataFile2)) {
                this.nData[1] = 1;
            }
        }
        if (this.nData[5] == 0) {
            if (CheckMemory(SDDataFilePath, SDDataFile)) {
                this.nData[5] = 1;
            }
            if (CheckMemory(SDDataFilePath2, SDDataFile2)) {
                this.nData[5] = 1;
            }
        }
    }

    public static long getFolderSize(File file) {
        long length;
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                if (fileList[i].isDirectory()) {
                    length = getFolderSize(fileList[i]);
                } else {
                    length = fileList[i].length();
                }
                size += length;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i(TAG, "getFolderSize= = " + size);
        return size;
    }

    public boolean StorageMounted(String Path) {
        if (EnvATC == null) {
            EnvATC = new EnvironmentATC(this);
        }
        String[] strSDMountedPath = EnvATC.getStorageMountedPaths();
        for (String equals : strSDMountedPath) {
            if (Path.equals(equals)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void CheckDvd() {
        if (this.nData[4] == 0 && MainUI.GetInstance().DvdLoadOK()) {
            this.nData[4] = 1;
        }
    }

    /* access modifiers changed from: package-private */
    public void CheckBt() {
        if (this.nData[3] == 0 && this.mBtExe.getDevName() != null && this.mBtExe.getDevName().contains("BT") && this.mBtExe.getDevName().length() <= 6) {
            this.nData[3] = 1;
        }
    }
}
