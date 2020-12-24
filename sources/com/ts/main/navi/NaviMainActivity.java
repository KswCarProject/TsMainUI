package com.ts.main.navi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.ts.MainUI.TsFile;
import com.ts.can.CanIF;
import com.ts.main.common.KeyTouch;
import com.ts.main.common.MainSet;
import com.ts.main.common.WinShow;
import com.yyw.ts70xhw.StSet;
import java.io.File;

public class NaviMainActivity extends Activity {
    static final String NAVI_PATH_STRING = "/mnt/sdcard/amapauto8";
    boolean bCheck = true;
    byte[] byteNavipath = new byte[128];
    AlertDialog m_dialgo;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StSet.GetNaviPack(this.byteNavipath);
        String NaviPath = CanIF.byte2String(this.byteNavipath);
        Intent it = getPackageManager().getLaunchIntentForPackage(NaviPath);
        Log.i("NaviMainActivity  PATH =", NaviPath);
        if (MainSet.bCheckNave) {
            MainSet.bCheckNave = false;
            if (!MainSet.isZh() && MainSet.GetInstance().IsHaveApk("com.android.vending") && TsFile.fileIsExists(NAVI_PATH_STRING)) {
                ShowOneMessage("发现大容量国内地图数据,是否删除", 0);
                return;
            }
        }
        if (it != null) {
            startActivity(it);
            overridePendingTransition(0, 0);
        } else {
            WinShow.GotoWin(11, 99);
        }
        finish();
        overridePendingTransition(0, 0);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    private void ShowOneMessage(String str, int nFinish) {
        new AlertDialog.Builder(this).setTitle("系统提示").setMessage(str).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                TsFile.deleteFile(new File(NaviMainActivity.NAVI_PATH_STRING));
                new File(NaviMainActivity.NAVI_PATH_STRING).delete();
                NaviMainActivity.this.finish();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                KeyTouch.GetInstance().sendKeyClick(3);
            }
        }).show();
    }
}
