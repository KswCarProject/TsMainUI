package com.txznet.comm.base;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import com.android.SdkConstants;
import com.txznet.T.T;
import com.txznet.comm.Ty.Tn;
import com.txznet.sdk.TXZResourceManager;
import java.lang.reflect.Field;

/* compiled from: Proguard */
public class BaseActivity extends StackActivity {

    /* renamed from: T  reason: collision with root package name */
    private boolean f417T = false;
    private TextView Tr;

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context newBase) {
        Configuration con = newBase.getResources().getConfiguration();
        con.screenWidthDp = Tn.T();
        con.screenHeightDp = Tn.Tr();
        newBase.getResources().updateConfiguration(con, newBase.getResources().getDisplayMetrics());
        super.attachBaseContext(newBase);
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        Ty.T().Tr();
        return super.dispatchTouchEvent(ev);
    }

    public void T() {
    }

    public void Tr() {
    }

    public void onWindowFocusChanged(boolean newFocus) {
        com.txznet.comm.Tr.Tr.Tn.T(toString() + " onWindowFocusChanged: from " + this.f417T + " to " + newFocus);
        if (this.f417T != newFocus) {
            this.f417T = newFocus;
            if (this.f417T) {
                T();
            } else {
                Tr();
            }
        }
        super.onWindowFocusChanged(newFocus);
    }

    public Resources getResources() {
        Application app = T.T();
        if (app != null) {
            try {
                Field f = app.getClass().getDeclaredField("mResources");
                f.setAccessible(true);
                Resources r = (Resources) f.get((Object) null);
                if (r != null) {
                    return r;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return super.getResources();
    }

    public static boolean Ty() {
        if (SdkConstants.CODENAME_RELEASE.equals(SdkConstants.CODENAME_RELEASE)) {
            return false;
        }
        return true;
    }

    public boolean Tn() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (Ty() && Tn()) {
            Tk();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        if (this.Tr != null) {
            getWindowManager().removeView(this.Tr);
            this.Tr = null;
        }
        super.onPause();
    }

    private void Tk() {
        try {
            if (this.Tr == null) {
                this.Tr = new TextView(this);
                PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 0);
                String verInfo = "未知版本";
                if ("DEV".equals(SdkConstants.CODENAME_RELEASE)) {
                    verInfo = "开发版本";
                } else if ("NEW".equals(SdkConstants.CODENAME_RELEASE)) {
                    verInfo = "演示版本";
                }
                this.Tr.setText("此版本为" + verInfo + (info == null ? TXZResourceManager.STYLE_DEFAULT : info.versionName));
                this.Tr.setTextSize(16.0f);
                this.Tr.setTextColor(Color.parseColor("#ccffffff"));
                WindowManager.LayoutParams mLp = new WindowManager.LayoutParams();
                this.Tr.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
                mLp.width = this.Tr.getMeasuredWidth();
                mLp.height = this.Tr.getMeasuredHeight();
                mLp.flags = 40;
                mLp.format = 1;
                mLp.gravity = 51;
                mLp.x = 10;
                mLp.y = 10;
                getWindowManager().addView(this.Tr, mLp);
            }
        } catch (Exception e) {
            this.Tr = null;
        }
    }
}
