package com.ts.factoryset;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import com.ts.MainUI.R;
import com.ts.main.common.MainSet;
import com.ts.main.common.MainUI;
import com.ts.main.common.WinShow;

public class FsMainActivity extends FsBaseActivity {
    public static final String TAG = "FsMainActivity";
    public static boolean mbInit = false;
    private Handler handler = null;
    private Button mBtnAtv;
    private Button mBtnCan;
    private Button mBtnDisplay;
    private Button mBtnIcon;
    private Button mBtnKey;
    private Button mBtnLang;
    private Button mBtnMpMode;
    private Button mBtnOther;
    private Button mBtnRad;
    private Button mBtnVol;
    private int mCurSyncSta = 0;
    private int mLastSyncSta = 0;
    private int mTaskCount = 0;
    AlertDialog m_dialgo;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fs_main);
        topBtnInit(R.string.str_fs_title);
        this.mBtnRad = (Button) findViewById(R.id.fsmain_btn_rad);
        this.mBtnCan = (Button) findViewById(R.id.fsmain_btn_can);
        this.mBtnVol = (Button) findViewById(R.id.fsmain_btn_vol);
        this.mBtnIcon = (Button) findViewById(R.id.fsmain_btn_ico);
        this.mBtnDisplay = (Button) findViewById(R.id.fsmain_btn_display);
        this.mBtnAtv = (Button) findViewById(R.id.fsmain_btn_atv);
        this.mBtnKey = (Button) findViewById(R.id.fsmain_btn_key);
        this.mBtnLang = (Button) findViewById(R.id.fsmain_btn_lang);
        this.mBtnOther = (Button) findViewById(R.id.fsmain_btn_other);
        this.mBtnMpMode = (Button) findViewById(R.id.fsmain_btn_mpmode);
        this.mBtnRad.setOnClickListener(this);
        this.mBtnCan.setOnClickListener(this);
        this.mBtnVol.setOnClickListener(this);
        this.mBtnIcon.setOnClickListener(this);
        this.mBtnDisplay.setOnClickListener(this);
        this.mBtnAtv.setOnClickListener(this);
        this.mBtnKey.setOnClickListener(this);
        this.mBtnLang.setOnClickListener(this);
        this.mBtnOther.setOnClickListener(this);
        this.mBtnMpMode.setOnClickListener(this);
    }

    public void onFsMainBtnClick(View v) {
        int id = v.getId();
        if (id == R.id.fsmain_btn_mcu_update) {
            WinShow.GotoWin(18, 0);
        } else if (id == R.id.fsmain_btn_touch_study) {
            WinShow.GotoWin(19, 0);
        } else if (id == R.id.fsmain_btn_device_id) {
            this.m_dialgo = new AlertDialog.Builder(this).setTitle("").setMessage(getResources().getString(R.string.str_fsmain_device_id)).setNeutralButton(getResources().getString(R.string.set_general_cancel), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    FsMainActivity.this.m_dialgo.dismiss();
                }
            }).setPositiveButton(getResources().getString(R.string.set_general_enter), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    MainSet.GetInstance().SystemClear();
                }
            }).show();
            Window dialogWindow = this.m_dialgo.getWindow();
            Display d = getWindowManager().getDefaultDisplay();
            WindowManager.LayoutParams p = dialogWindow.getAttributes();
            p.width = (int) (((double) d.getWidth()) * 0.5d);
            p.gravity = 17;
            dialogWindow.setAttributes(p);
        } else if (id == R.id.fsmain_btn_boot_logo) {
            WinShow.GotoWin(17, 0);
        } else if (id != R.id.fsmain_btn_screen_shot) {
        } else {
            if (MainUI.bIsScreenMode) {
                Toast.makeText(this, R.string.screen_shot_off, 0).show();
                MainUI.bIsScreenMode = false;
                return;
            }
            Toast.makeText(this, R.string.screen_shot_on, 0).show();
            MainUI.bIsScreenMode = true;
        }
    }

    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.fsmain_btn_rad) {
            enterSubWin(FsRadioActivity.class);
        } else if (id == R.id.fsmain_btn_can) {
            enterSubWin(FsCanActivity.class);
        } else if (id == R.id.fsmain_btn_vol) {
            enterSubWin(FsVolActivity.class);
        } else if (id == R.id.fsmain_btn_ico) {
            enterSubWin(FsIconActivity.class);
        } else if (id == R.id.fsmain_btn_display) {
            enterSubWin(FsDisplayActivity.class);
        } else if (id == R.id.fsmain_btn_atv) {
            enterSubWin(FsTVActivity.class);
        } else if (id == R.id.fsmain_btn_key) {
            enterSubWin(FsKeyActivity.class);
        } else if (id == R.id.fsmain_btn_lang) {
            enterSubWin(FsLangActivity.class);
        } else if (id == R.id.fsmain_btn_other) {
            enterSubWin(FsOtherActivity.class);
        } else if (id == R.id.fsmain_btn_mpmode) {
            enterSubWin(FsMPModeActivity.class);
        }
    }

    /* access modifiers changed from: package-private */
    public void enterSubWin(Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        startActivity(intent);
    }
}
