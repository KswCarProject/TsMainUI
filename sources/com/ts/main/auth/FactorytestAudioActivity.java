package com.ts.main.auth;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.internal.view.SupportMenu;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.ts.MainUI.Evc;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.dvdplayer.definition.MediaDef;
import com.ts.main.auth.AudioRecoderUtils;
import com.ts.main.common.MainSet;
import java.io.IOException;
import java.util.List;

public class FactorytestAudioActivity extends Activity implements UserCallBack {
    private static final String LEFT_FILE = "/storage/ext/sdcard1/left.wav";
    private static final String RIGHT_FILE = "/storage/ext/sdcard1/right.wav";
    private static final String TAG = "scj";
    static int bWifiFix = 0;
    Button BtnLeft;
    Button BtnRecord;
    Button BtnRight;
    WifiAdmin MyWifi;
    boolean bRecod = false;
    AudioRecoderUtils mAudioRecoderUtils = new AudioRecoderUtils("/mnt/sdcard/");
    AlertDialog m_dialgo;
    private MediaPlayer mp = new MediaPlayer();
    int nBcheckGPSAndWifi = 0;
    int nGpsFix = 0;
    int nNum = 0;
    int nWifiFix = 0;
    TextView showTextView;
    TextView sstTextView;
    String strPathString;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factory_testaduio);
        this.BtnRecord = (Button) findViewById(R.id.btn_fatctory_record_audio);
        this.showTextView = (TextView) findViewById(R.id.btn_fatctory_record_text);
        this.sstTextView = (TextView) findViewById(R.id.btn_fatctory_audio_text);
        this.BtnLeft = (Button) findViewById(R.id.btn_fatctory_left_audio);
        this.BtnRight = (Button) findViewById(R.id.btn_fatctory_right_audio);
        this.sstTextView.setTextColor(-16711936);
        this.sstTextView.setText("按下按钮开始录音,说话大于80分贝认为麦克正常");
        this.BtnLeft.setVisibility(4);
        this.BtnRight.setVisibility(4);
        this.BtnLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                FactorytestAudioActivity.this.PlaySource(R.raw.left);
                FactorytestAudioActivity.this.BtnRight.setVisibility(0);
                FactorytestAudioActivity.this.BtnLeft.setVisibility(4);
                FactorytestAudioActivity.this.sstTextView.setText("按下右声道键测试右边声道");
            }
        });
        this.BtnRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                FactorytestAudioActivity.this.PlaySource(R.raw.right);
                factory_test.AddToSort("音频测试OK");
                factory_test.WriteReport();
                FactorytestAudioActivity.this.nBcheckGPSAndWifi = 1;
            }
        });
        this.mAudioRecoderUtils.setOnAudioStatusUpdateListener(new AudioRecoderUtils.OnAudioStatusUpdateListener() {
            public void onUpdate(double db, long time) {
                int fenbei = ((int) (3000.0d + ((6000.0d * db) / 100.0d))) / 100;
                FactorytestAudioActivity.this.showTextView.setText(String.valueOf(fenbei) + "分贝");
                if (fenbei >= 80) {
                    FactorytestAudioActivity.this.mAudioRecoderUtils.cancelRecord();
                    if (!MainSet.bKeyBroad) {
                        FactorytestAudioActivity.this.BtnRecord.setVisibility(4);
                        FactorytestAudioActivity.this.BtnLeft.setVisibility(0);
                        FactorytestAudioActivity.this.sstTextView.setText("按下左声道键测试左边声道");
                        return;
                    }
                    factory_test.AddToSort("音频测试OK");
                    factory_test.WriteReport();
                    FactorytestAudioActivity.this.nBcheckGPSAndWifi = 1;
                    FactorytestAudioActivity.this.BtnRecord.setVisibility(4);
                }
            }

            public void onStop(String filePath) {
                Toast.makeText(FactorytestAudioActivity.this, "录音保存在：" + filePath, 0).show();
            }
        });
        this.BtnRecord.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (FactorytestAudioActivity.this.nBcheckGPSAndWifi == 0) {
                    FactorytestAudioActivity.this.BtnRecord.setText("开始录音");
                    FactorytestAudioActivity.this.mAudioRecoderUtils.startRecord();
                }
            }
        });
    }

    private void ShowOneMessage(String str, final int nFinish) {
        this.m_dialgo = new AlertDialog.Builder(this).setTitle("系统提示").setMessage(str).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (nFinish != 1) {
                    FactorytestAudioActivity.this.m_dialgo.dismiss();
                    FactorytestAudioActivity.this.StopMp();
                    MainSet.GetInstance().Reboot();
                }
            }
        }).show();
    }

    /* access modifiers changed from: package-private */
    public void StopMp() {
        if (this.mp != null) {
            this.mp.stop();
            this.mp.release();
            this.mp = null;
        }
    }

    /* access modifiers changed from: package-private */
    public void PlaySource(int id) {
        StopMp();
        this.mp = MediaPlayer.create(this, id);
        try {
            this.mp.prepare();
        } catch (IOException | IllegalStateException e) {
            e.printStackTrace();
        }
        this.mp.start();
    }

    /* access modifiers changed from: package-private */
    public void PlayFile(String strPath) {
        if (this.mp != null) {
            this.mp.stop();
            this.mp.release();
            this.mp = null;
        }
        this.mp = new MediaPlayer();
        try {
            Log.i(TAG, "PlayFile==" + strPath);
            this.mp.setDataSource(strPath);
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
            this.mp.prepare();
        } catch (IOException | IllegalStateException e5) {
            e5.printStackTrace();
        }
        this.mp.start();
    }

    /* access modifiers changed from: package-private */
    public void enterSubWin(Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        startActivity(intent);
    }

    /* access modifiers changed from: package-private */
    public int nCacuWifiLev(int level) {
        return WifiManager.calculateSignalLevel(level, 5);
    }

    /* access modifiers changed from: package-private */
    public int GetAlltime() {
        return ((int) (factory_test.getTickCount() - factory_test.Starttime)) / MediaDef.PROGRESS_MAX;
    }

    public void UserAll() {
        if (bWifiFix == 0) {
            this.nNum++;
            if (this.nNum % 100 == 0) {
                this.MyWifi.updateWifiList();
                List<ScanResult> MyWifilist = this.MyWifi.getWifiList();
                if (MyWifilist.size() > 0) {
                    for (int i = 0; i < MyWifilist.size(); i++) {
                        Log.i(TAG, "name=" + MyWifilist.get(i).SSID + "level=" + nCacuWifiLev(MyWifilist.get(i).level));
                        if (nCacuWifiLev(MyWifilist.get(i).level) >= 4) {
                            if (bWifiFix == 0) {
                                Toast.makeText(this, "wifi名字=" + MyWifilist.get(i).SSID + "强度=" + nCacuWifiLev(MyWifilist.get(i).level), 0).show();
                            }
                            bWifiFix = 1;
                        }
                    }
                }
            }
        }
        if (this.nBcheckGPSAndWifi == 1) {
            this.showTextView.setVisibility(4);
            if (FactoryMainActivity.bGpsFix == 0 && bWifiFix == 0) {
                this.sstTextView.setText("GPS,wifi测试中 总用时=" + GetAlltime() + "秒");
            }
            if (FactoryMainActivity.bGpsFix != this.nGpsFix || bWifiFix != this.nWifiFix) {
                if (FactoryMainActivity.bGpsFix == 1 && bWifiFix == 0) {
                    this.sstTextView.setTextColor(SupportMenu.CATEGORY_MASK);
                    this.sstTextView.setText("GPS已经定位  wifi异常 总用时=" + GetAlltime() + "秒");
                } else if (FactoryMainActivity.bGpsFix == 0 && bWifiFix == 1) {
                    this.sstTextView.setTextColor(SupportMenu.CATEGORY_MASK);
                    this.sstTextView.setText("GPS未定位  wifi通过测试 总用时=" + GetAlltime() + "秒");
                } else if (FactoryMainActivity.bGpsFix == 1 && bWifiFix == 1) {
                    this.sstTextView.setTextColor(-16711936);
                    this.sstTextView.setText("GPS已经定位  wifi通过测试 总用时=" + GetAlltime() + "秒");
                    ShowOneMessage("测试通过 总用时=" + GetAlltime() + "秒", 0);
                    this.nGpsFix = FactoryMainActivity.bGpsFix;
                    this.nWifiFix = bWifiFix;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: package-private */
    public void CheckWifi() {
        this.MyWifi = new WifiAdmin(this);
        this.MyWifi.openWifi(this);
        this.MyWifi.startScan(this);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        MainTask.GetInstance().SetUserCallBack(this);
        if (MainSet.Testmode.bWifi == 1) {
            CheckWifi();
        } else {
            bWifiFix = 1;
        }
        if (MainSet.bKeyBroad) {
            Evc.GetInstance().evol_vol_set(12);
            PlaySource(R.raw.sin);
            Evc.GetInstance().evol_workmode_set(4);
        } else {
            Evc.GetInstance().evol_vol_set(26);
        }
        this.BtnRecord.setText("开始录音");
        this.mAudioRecoderUtils.startRecord();
        super.onResume();
    }
}
