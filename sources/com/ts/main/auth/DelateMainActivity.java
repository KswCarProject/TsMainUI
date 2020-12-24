package com.ts.main.auth;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.ts.MainUI.R;
import com.ts.set.setview.SetMainItem;
import com.ts.set.setview.SetSroView;
import java.util.ArrayList;
import java.util.List;

public class DelateMainActivity extends Activity implements View.OnClickListener {
    ArrayList<PInfo> apps;
    int nNum = 0;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SetSroView.Inint(this);
        listPackages();
        SetSroView.Show(this);
        if (this.nNum == 0) {
            new AlertDialog.Builder(this).setTitle("").setMessage(getResources().getString(R.string.del_common_no_apk)).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    DelateMainActivity.this.finish();
                }
            }).show();
        }
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

    class PInfo {
        /* access modifiers changed from: private */
        public String appname = "";
        /* access modifiers changed from: private */
        public Drawable icon;
        /* access modifiers changed from: private */
        public String pname = "";
        /* access modifiers changed from: private */
        public int versionCode = 0;
        /* access modifiers changed from: private */
        public String versionName = "";

        PInfo() {
        }

        /* access modifiers changed from: private */
        public void prettyPrint() {
            Log.i("taskmanger", String.valueOf(this.appname) + "\t" + this.pname + "\t" + this.versionName + "\t" + this.versionCode + "\t" + this.icon);
        }
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

    private void listPackages() {
        this.apps = getInstalledApps(false);
        int max = this.apps.size();
        for (int i = 0; i < max; i++) {
            if (IsSysapk(this.apps.get(i).pname) == 0) {
                this.nNum++;
                this.apps.get(i).prettyPrint();
                SetMainItem Myitem = new SetMainItem(this, this.apps.get(i).appname);
                Myitem.SetUserCallback(i, this);
                Myitem.GetImageTile().setBackground(this.apps.get(i).icon);
                SetSroView.AddView(Myitem.GetView());
            }
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

    public void onClick(View v) {
        startActivity(new Intent("android.intent.action.DELETE", Uri.parse("package:" + this.apps.get(((Integer) v.getTag()).intValue()).pname)));
        finish();
    }
}
