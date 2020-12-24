package com.ts.main.auth;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.lgb.canmodule.Can;
import com.ts.MainUI.AuthServer;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.main.common.KeyTouch;
import com.ts.main.common.MainSet;

public class AuthActivity extends Activity implements UserCallBack {
    private static final String TAG = "AuthActivity";
    AuthServer m_AuthServer = AuthServer.GetInstance();
    AlertDialog m_dialgo;
    int nDelayTime = 0;
    int nDellayTime = 0;
    int nStep = 0;
    int nUpdateOnce = 1;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authactivity_main);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        this.nStep = 0;
        MainTask.GetInstance().SetUserCallBack(this);
        super.onResume();
    }

    private void ShowOneMessage(String str, final int nFinish) {
        this.m_dialgo = new AlertDialog.Builder(this).setTitle("系统提示").setMessage(str).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (nFinish == 1) {
                    AuthActivity.this.finish();
                } else if (AuthActivity.this.nStep == 0) {
                    AuthActivity.this.m_dialgo.dismiss();
                    AuthActivity.this.nStep = 0;
                    AuthActivity.this.nUpdateOnce = 1;
                }
            }
        }).show();
    }

    private void ShowNetIsNotOK() {
        if (this.m_dialgo != null) {
            this.m_dialgo.dismiss();
        }
        this.m_dialgo = new AlertDialog.Builder(this).setTitle("系统提示").setMessage("设备未激活,请联网").setPositiveButton("Wifi", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                AuthActivity.this.startActivity(new Intent("android.settings.WIFI_SETTINGS"));
                AuthActivity.this.finish();
            }
        }).setNegativeButton("3G/4G", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                AuthActivity.this.startActivity(new Intent("android.settings.WIRELESS_SETTINGS"));
                AuthActivity.this.finish();
            }
        }).show();
    }

    public void UserAll() {
        switch (this.nStep) {
            case 0:
                if (!this.m_AuthServer.IsAuthOk()) {
                    this.nStep = 1;
                    break;
                } else {
                    ShowOneMessage("设备已激活", 1);
                    this.nStep = Can.CAN_HYUNDAI_WC;
                    break;
                }
            case 1:
                if (this.m_AuthServer.IsNetOk(this)) {
                    if (!MainSet.GetInstance().bAutoAuth()) {
                        this.nStep = 3;
                        break;
                    } else {
                        this.nStep = 2;
                        break;
                    }
                } else {
                    ShowNetIsNotOK();
                    this.nStep = Can.CAN_HYUNDAI_WC;
                    break;
                }
            case 2:
                if (!MainSet.GetInstance().bIsTimeOK()) {
                    if (this.nDelayTime != 0) {
                        this.nDelayTime--;
                        Log.i(TAG, "*******nDelayTime=" + this.nDelayTime);
                        break;
                    } else {
                        MainSet.getNetworkTime();
                        this.nDelayTime = 100;
                        break;
                    }
                } else {
                    this.nStep = 3;
                    break;
                }
            case 3:
                if (!MainSet.GetInstance().bAutoAuth() && !this.m_AuthServer.GettheLicense()) {
                    this.m_dialgo = new AlertDialog.Builder(this).setTitle("系统提示").setMessage("未发现授权文件").setPositiveButton("重试", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            AuthActivity.this.m_dialgo.dismiss();
                            AuthActivity.this.nStep = 0;
                        }
                    }).setNegativeButton("返回", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            KeyTouch.GetInstance().sendKeyClick(3);
                        }
                    }).show();
                    this.nStep = Can.CAN_HYUNDAI_WC;
                    break;
                } else {
                    if (this.nUpdateOnce == 1) {
                        Toast.makeText(this, "开始激活", 0).show();
                        if (MainSet.GetInstance().bAutoAuth()) {
                            this.m_AuthServer.UpdateAutoId(MainSet.GetInstance().GetCustom());
                        } else {
                            this.m_AuthServer.Updateid();
                        }
                        this.nUpdateOnce = 0;
                    }
                    this.nStep = Can.CAN_HYUNDAI_WC;
                    break;
                }
                break;
        }
        if (this.nDellayTime > 0) {
            this.nDellayTime--;
            if (this.nDellayTime == 0) {
                this.m_AuthServer.UpdateMcu();
            }
        }
        if (this.nDellayTime == 0) {
            switch (this.m_AuthServer.GetnID()) {
                case 1:
                    ShowOneMessage("[" + this.m_AuthServer.m_GetID.mNum + "]激活中...", 2);
                    this.nDellayTime = 200;
                    return;
                case 2:
                    ShowOneMessage("网络异常,请重试", 0);
                    return;
                case 3:
                    ShowOneMessage("文件无效", 0);
                    return;
                case 4:
                    ShowOneMessage("激活失败(1)", 0);
                    return;
                case 5:
                    ShowOneMessage("激活失败(2)", 0);
                    return;
                default:
                    return;
            }
        }
    }
}
