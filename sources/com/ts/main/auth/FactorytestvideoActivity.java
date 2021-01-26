package com.ts.main.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.ts.MainUI.Evc;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.backcar.AutoFitTextureView;
import com.ts.backcar.BackcarService;
import com.ts.main.common.MainSet;
import com.ts.main.common.MainUI;

public class FactorytestvideoActivity extends Activity implements UserCallBack {
    private static final String TAG = "[scj:]Test";
    public static final int VODEO_CHECK = 35;
    int bWrite = 0;
    TextView mytTextView;
    int nCheckTime = 0;
    int nStep = 0;
    Button nextWinButton;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factory_testvideo);
        this.nextWinButton = (Button) findViewById(R.id.btn_fatctory_videotnext);
        this.nextWinButton.setVisibility(4);
        this.mytTextView = (TextView) findViewById(R.id.signal_check);
        this.nextWinButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                FactorytestvideoActivity.this.enterSubWin(FactorytestAudioActivity.class);
                factory_test.AddToSort("视频测试OK");
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void enterSubWin(Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        startActivity(intent);
    }

    public void UserAll() {
        if (this.nCheckTime > 0) {
            this.nCheckTime--;
        } else if (this.nCheckTime == 0) {
            switch (this.nStep) {
                case 0:
                    if (!BackcarService.bSigShow) {
                        if (this.bWrite == 0) {
                            this.bWrite = 1;
                            factory_test.AddToSort("CVBS-倒车通道异常");
                        }
                        this.mytTextView.setText("CVBS-倒车通道异常");
                        return;
                    } else if (!MainSet.bKeyBroad) {
                        enterSubWin(FactorytestAudioActivity.class);
                        factory_test.AddToSort("视频测试OK");
                        return;
                    } else {
                        this.nCheckTime = 35;
                        this.nStep = 1;
                        this.mytTextView.setText("CVBS-倒车通道正常");
                        BackcarService.getInstance().StopCamera();
                        MainSet.GetInstance().SetVideoChannel(1);
                        BackcarService.getInstance().StartCamera((AutoFitTextureView) findViewById(R.id.testvideo), false);
                        return;
                    }
                case 1:
                    if (BackcarService.bSigShow) {
                        this.nCheckTime = 35;
                        this.nStep = 2;
                        this.mytTextView.setText("CVBS-数字电视通道正常");
                        BackcarService.getInstance().StopCamera();
                        MainSet.GetInstance().SetVideoChannel(2);
                        BackcarService.getInstance().StartCamera((AutoFitTextureView) findViewById(R.id.testvideo), false);
                        return;
                    }
                    if (this.bWrite == 0) {
                        this.bWrite = 1;
                        factory_test.AddToSort("CVBS-数字电视通道异常");
                    }
                    this.mytTextView.setText("CVBS-AVIN通道异常");
                    return;
                case 2:
                    if (BackcarService.bSigShow) {
                        this.mytTextView.setText("CVBS-AVIN通道正常");
                        this.nStep = 3;
                        BackcarService.getInstance().StopCamera();
                        if (MainSet.Testmode.Support_360View == 1) {
                            this.nCheckTime = 35;
                            return;
                        }
                        return;
                    }
                    if (this.bWrite == 0) {
                        this.bWrite = 1;
                        factory_test.AddToSort("CVBS-AVIN通道异常");
                    }
                    this.mytTextView.setText("CVBS-AVIN通道异常");
                    return;
                case 3:
                    if (MainSet.Testmode.Support_360View != 1) {
                        this.nStep = 4;
                        return;
                    } else if (!bIs360ok()) {
                        String Eoor = "360工作异常";
                        if (MainUI.AVMChaneleA != 2) {
                            Eoor = String.valueOf(Eoor) + "通道A异常  ";
                        }
                        if (MainUI.AVMChaneleB != 2) {
                            Eoor = String.valueOf(Eoor) + "通道B异常  ";
                        }
                        if (MainUI.AVMChaneleC != 2) {
                            Eoor = String.valueOf(Eoor) + "通道C异常  ";
                        }
                        if (MainUI.AVMChaneleD != 2) {
                            Eoor = String.valueOf(Eoor) + "通道D异常  ";
                        }
                        this.mytTextView.setText(Eoor);
                        return;
                    } else {
                        this.mytTextView.setText("360工作正常");
                        this.nStep = 4;
                        return;
                    }
                case 4:
                    if (MainSet.Testmode.Support_AHD == 1) {
                        this.nStep = 5;
                        this.nCheckTime = Can.CAN_BENC_ZMYT;
                        MainSet.GetInstance().SetVideoChannel(0);
                        BackcarService.getInstance().SetSource(1);
                        BackcarService.getInstance().StartCamera((AutoFitTextureView) findViewById(R.id.testvideo), false);
                        BackcarService.bSigShow = false;
                        return;
                    }
                    if (MainSet.bKeyBroad) {
                        Evc.GetInstance().evol_workmode_set(6);
                        enterSubWin(FactorytestAudioActivity.class);
                        factory_test.AddToSort("视频测试OK");
                    }
                    this.nStep = 5;
                    return;
                case 5:
                    if (BackcarService.bSigShow) {
                        this.mytTextView.setText("AHD通道正常");
                        this.nStep = 5;
                        BackcarService.getInstance().StopCamera();
                        if (MainSet.bKeyBroad) {
                            Evc.GetInstance().evol_workmode_set(6);
                            enterSubWin(FactorytestAudioActivity.class);
                            factory_test.AddToSort("视频测试OK");
                            return;
                        }
                        return;
                    }
                    if (this.bWrite == 0) {
                        this.bWrite = 1;
                        factory_test.AddToSort("AHD通道异常");
                    }
                    this.mytTextView.setText("AHD通道异常");
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean bIs360ok() {
        return MainUI.AVMChaneleA == 2 && MainUI.AVMChaneleB == 2 && MainUI.AVMChaneleC == 2 && MainUI.AVMChaneleA == 2;
    }

    /* access modifiers changed from: package-private */
    public boolean bIsAhdOK() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: package-private */
    public int GetCvbsOrAhd() {
        if (MainUI.IsCameraMode() == 1) {
            return 0;
        }
        return 1;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        MainTask.GetInstance().SetUserCallBack(this);
        BackcarService.getInstance().StopCamera();
        this.nStep = 0;
        if (MainSet.bKeyBroad) {
            Evc.GetInstance().evol_workmode_set(9);
        }
        MainSet.GetInstance().SetVideoChannel(0);
        BackcarService.getInstance().StartCamera((AutoFitTextureView) findViewById(R.id.testvideo), false);
        this.nCheckTime = 35;
        super.onResume();
    }
}
