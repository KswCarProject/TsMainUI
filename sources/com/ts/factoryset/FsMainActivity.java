package com.ts.factoryset;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.ts.MainUI.R;
import com.ts.main.common.MainSet;
import com.ts.main.common.WinShow;
import com.txznet.sdk.TXZResourceManager;

public class FsMainActivity extends FsBaseActivity {
    public static final String TAG = "FsMainActivity";
    public static boolean mbInit = false;
    AlertDialog m_dialgo;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent == null || !intent.getBooleanExtra("factory", false)) {
            setContentView(R.layout.activity_fs_main_shop);
        } else {
            setContentView(R.layout.activity_fs_main);
        }
        topBtnInit(R.string.str_fs_title);
    }

    public void onFsMainBtnClick(View v) {
        int id = v.getId();
        if (id == R.id.fsmain_btn_mcu_update) {
            WinShow.GotoWin(18, 0);
        } else if (id == R.id.fsmain_btn_touch_study) {
            WinShow.GotoWin(19, 0);
        } else if (id == R.id.fsmain_btn_device_id) {
            this.m_dialgo = new AlertDialog.Builder(this).setTitle(TXZResourceManager.STYLE_DEFAULT).setMessage(getResources().getString(R.string.str_fsmain_device_id)).setNeutralButton(getResources().getString(R.string.set_general_cancel), new DialogInterface.OnClickListener() {
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
        } else if (id == R.id.str_fsmain_backcar) {
            enterSubWin(FsBackCarSetActivity.class);
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
