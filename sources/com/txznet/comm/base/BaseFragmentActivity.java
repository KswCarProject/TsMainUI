package com.txznet.comm.base;

import android.app.Activity;
import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import com.android.SdkConstants;
import com.txznet.T.T;
import com.txznet.comm.Tr.Tr.Tn;
import com.txznet.sdk.TXZResourceManager;
import java.lang.reflect.Field;

/* compiled from: Proguard */
public class BaseFragmentActivity extends FragmentActivity {

    /* renamed from: T  reason: collision with root package name */
    private boolean f418T = false;
    private TextView Tr;

    /* access modifiers changed from: protected */
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        T.T().Tr(this);
        try {
            setTheme(Class.forName("com.txznet.txz.comm.R$style").getDeclaredField("AppTransparentTheme").getInt((Object) null));
        } catch (Exception e) {
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        T.T().T((Activity) this);
        if (isFinishing() && !T.T().Tr()) {
            Tr.Ty("destroy");
        }
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
        Tn.T(toString() + " onWindowFocusChanged: from " + this.f418T + " to " + newFocus);
        if (this.f418T != newFocus) {
            this.f418T = newFocus;
            if (this.f418T) {
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

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (Ty()) {
            Tn();
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

    private void Tn() {
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
        }
    }
}
