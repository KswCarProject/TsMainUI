package com.ts.main.auth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.backcar.AutoFitTextureView;
import com.ts.backcar.BackcarService;
import com.ts.main.common.MainSet;
import com.yyw.ts70xhw.FtSet;

public class FactorytestvideoActivity extends Activity implements UserCallBack {
    private static final String TAG = "[scj:]Test";
    Button[] BtnVideo = new Button[3];
    boolean bBt1 = false;
    boolean bBt2 = false;
    boolean bBt3 = false;
    Button nextWinButton;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factory_testvideo);
        this.BtnVideo[0] = (Button) findViewById(R.id.btn_fatctory_videot1);
        this.BtnVideo[1] = (Button) findViewById(R.id.btn_fatctory_videot2);
        this.BtnVideo[2] = (Button) findViewById(R.id.btn_fatctory_videot3);
        this.nextWinButton = (Button) findViewById(R.id.btn_fatctory_videotnext);
        this.BtnVideo[1].setVisibility(4);
        this.BtnVideo[2].setVisibility(4);
        this.nextWinButton.setVisibility(4);
        this.BtnVideo[0].setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                FactorytestvideoActivity.this.bBt1 = true;
                MainSet.GetInstance().SetVideoChannel(0);
                FactorytestvideoActivity.this.BtnVideo[1].setVisibility(0);
                FactorytestvideoActivity.this.BtnVideo[0].setVisibility(4);
            }
        });
        this.BtnVideo[1].setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                FactorytestvideoActivity.this.bBt2 = true;
                MainSet.GetInstance().SetVideoChannel(2);
                FactorytestvideoActivity.this.BtnVideo[2].setVisibility(0);
                FactorytestvideoActivity.this.BtnVideo[1].setVisibility(4);
            }
        });
        this.BtnVideo[2].setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                FactorytestvideoActivity.this.bBt3 = true;
                MainSet.GetInstance().SetVideoChannel(1);
                FactorytestvideoActivity.this.nextWinButton.setVisibility(0);
                FactorytestvideoActivity.this.BtnVideo[2].setVisibility(4);
            }
        });
        this.nextWinButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                FactorytestvideoActivity.this.enterSubWin(FactorytestAudioActivity.class);
                FactorytestvideoActivity.this.finish();
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
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        BackcarService.getInstance().StopCamera();
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        MainTask.GetInstance().SetUserCallBack(this);
        if (FtSet.GetCam8824() == 1) {
            if (MainSet.bKeyBroad) {
                MainSet.GetInstance().SetVideoChannel(0);
            }
            BackcarService.getInstance().StartCamera((AutoFitTextureView) findViewById(R.id.testvideo), true);
            if (MainSet.bKeyBroad) {
                this.BtnVideo[1].setVisibility(0);
                this.BtnVideo[0].setVisibility(4);
            } else {
                this.BtnVideo[1].setVisibility(4);
                this.BtnVideo[0].setVisibility(0);
            }
        } else {
            MainSet.GetInstance().SetVideoChannel(0);
            BackcarService.getInstance().StartCamera((AutoFitTextureView) findViewById(R.id.testvideo), true);
            this.BtnVideo[1].setVisibility(0);
            this.BtnVideo[0].setVisibility(4);
        }
        super.onResume();
    }
}
