package com.ts.set;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.can.CanIF;
import com.ts.main.common.MainSet;
import com.ts.main.common.WinShow;
import com.ts.main.txz.TxzReg;
import com.ts.set.setview.SetMainGpsItem;
import com.ts.set.setview.UISetSroView;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.StSet;
import java.util.ArrayList;
import java.util.List;
import org.texustek.mirror.aidl.BinderName;

public class SettingGpsPathActivity extends Activity implements View.OnClickListener {
    public static final String ACTION_RECOGNIZE_CMD_B = "com.globalconstant.BROADCAST_CAR_SEND_CMD";
    ArrayList<PInfo> apps;
    AlertDialog m_dialgo;
    byte[] naviPacketName = new byte[128];

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UISetSroView.Inint(this);
        initGpsPath();
        UISetSroView.Show(this);
    }

    /* access modifiers changed from: package-private */
    public void initGpsPath() {
        listPackages();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        finish();
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: package-private */
    public int IsSysapk(String packName) {
        int appFlags = 0;
        try {
            appFlags = getPackageManager().getApplicationInfo(packName, 0).flags;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appFlags & 1;
    }

    class PInfo {
        /* access modifiers changed from: private */
        public String appname = TXZResourceManager.STYLE_DEFAULT;
        /* access modifiers changed from: private */
        public Drawable icon;
        /* access modifiers changed from: private */
        public String pname = TXZResourceManager.STYLE_DEFAULT;
        /* access modifiers changed from: private */
        public int versionCode = 0;
        /* access modifiers changed from: private */
        public String versionName = TXZResourceManager.STYLE_DEFAULT;

        PInfo() {
        }

        /* access modifiers changed from: private */
        public void prettyPrint() {
            Log.i("taskmanger", String.valueOf(this.appname) + " " + this.pname + " " + this.versionName + " " + this.versionCode + " " + this.icon);
        }
    }

    private void listPackages() {
        this.apps = getInstalledApps(false);
        int max = this.apps.size();
        int nHave = 0;
        for (int i = 0; i < max; i++) {
            if (MainSet.IsAvalidPackName(this.apps.get(i).pname) && MainSet.IsAvalidAPPName(this.apps.get(i).appname)) {
                this.apps.get(i).prettyPrint();
                SetMainGpsItem Myitem = new SetMainGpsItem(this, this.apps.get(i).appname);
                Myitem.SetUserCallback(i, this);
                Myitem.GetImageTile().setBackground(this.apps.get(i).icon);
                UISetSroView.AddView(Myitem.GetView());
                nHave++;
            }
        }
        if (nHave == 0) {
            Toast.makeText(this, "No NaviApp", 0).show();
        }
    }

    private ArrayList<PInfo> getInstalledApps(boolean getSysPackages) {
        ArrayList<PInfo> res = new ArrayList<>();
        List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);
            if (getSysPackages || p.versionName != null) {
                PInfo newInfo = new PInfo();
                newInfo.appname = p.applicationInfo.loadLabel(getPackageManager()).toString();
                newInfo.pname = p.packageName;
                newInfo.versionName = p.versionName;
                newInfo.versionCode = p.versionCode;
                newInfo.icon = p.applicationInfo.loadIcon(getPackageManager());
                res.add(newInfo);
            }
        }
        return res;
    }

    /* access modifiers changed from: package-private */
    public byte[] StrToByte128(String str) {
        byte[] naviname2 = new byte[128];
        byte[] naviname = str.getBytes();
        for (int i = 0; i < naviname.length; i++) {
            naviname2[i] = naviname[i];
        }
        return naviname2;
    }

    public void onClick(View v) {
        int n = ((Integer) v.getTag()).intValue();
        StSet.SetNaviName(StrToByte128(this.apps.get(n).appname));
        StSet.SetNaviPack(StrToByte128(this.apps.get(n).pname));
        Iop.tsxhwSleep();
        byte[] byteNavipath = new byte[128];
        StSet.GetNaviPack(byteNavipath);
        String NaviPath = CanIF.byte2String(byteNavipath);
        Intent Aintent = new Intent();
        Aintent.setAction(ACTION_RECOGNIZE_CMD_B);
        Aintent.putExtra("domain", BinderName.NAVI);
        Aintent.putExtra("action", "sendNaviAppPck");
        Aintent.putExtra("naviAppPck", NaviPath);
        sendBroadcast(Aintent);
        Evc.GetInstance().AddNaviWhileList(NaviPath);
        TxzReg.GetInstance().SetNaviType(NaviPath);
        Log.i("xxxx", "addToWhiteList4GIS true    " + NaviPath);
        this.m_dialgo = new AlertDialog.Builder(this).setTitle(TXZResourceManager.STYLE_DEFAULT).setMessage("                    " + this.apps.get(n).appname).setPositiveButton(getResources().getString(R.string.set_general_enter), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                WinShow.GotoWin(1, 0);
                SettingGpsPathActivity.this.finish();
            }
        }).show();
        Window dialogWindow = this.m_dialgo.getWindow();
        Display d = getWindowManager().getDefaultDisplay();
        WindowManager.LayoutParams p = dialogWindow.getAttributes();
        p.width = (int) (((double) d.getWidth()) * 0.5d);
        p.gravity = 17;
        dialogWindow.setAttributes(p);
    }
}
