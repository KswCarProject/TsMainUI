package com.ts.can;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.ts.MainUI.R;
import com.ts.other.ParamButton;

public class CanBaseActivity extends Activity {
    public static final boolean DEBUG = true;
    public static final int FALSE = 0;
    public static final String TAG = "CanBaseActivity";
    public static final int TRUE = 1;

    protected static boolean int2Bool(int val) {
        if (val == 0) {
            return false;
        }
        return true;
    }

    protected static boolean i2b(int val) {
        if (val == 0) {
            return false;
        }
        return true;
    }

    public void enterSubWin(Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    /* access modifiers changed from: protected */
    public void showFlg(ImageView iv, int val, int onImg, int offImg) {
        if (int2Bool(val)) {
            iv.setImageDrawable(getResources().getDrawable(onImg));
        } else {
            iv.setImageDrawable(getResources().getDrawable(offImg));
        }
    }

    public void setIvStateDrawable(ImageView iv, int normal, int selected) {
        Drawable iSelected = null;
        Resources res = getResources();
        StateListDrawable sd = new StateListDrawable();
        Drawable iNormal = normal == -1 ? null : res.getDrawable(normal);
        if (selected != -1) {
            iSelected = res.getDrawable(selected);
        }
        sd.addState(new int[]{16842913}, iSelected);
        sd.addState(new int[]{16842910}, iNormal);
        sd.addState(new int[0], iNormal);
        iv.setBackground(sd);
    }

    public void setIvSel(ImageView iv, int val) {
        if (int2Bool(val)) {
            iv.setSelected(true);
        } else {
            iv.setSelected(false);
        }
    }

    public void setIvShow(ImageView iv, int val) {
        if (iv != null) {
            if (int2Bool(val)) {
                iv.setVisibility(0);
            } else {
                iv.setVisibility(4);
            }
        }
    }

    public void setViewShow(View v, int val) {
        if (int2Bool(val)) {
            v.setVisibility(0);
        } else {
            v.setVisibility(4);
        }
    }

    public void setViewShow(View v, boolean show) {
        if (show) {
            v.setVisibility(0);
        } else {
            v.setVisibility(4);
        }
    }

    public void setIvDrawable(ImageView iv, int resId) {
        iv.setImageDrawable(getResources().getDrawable(resId));
    }

    public static long GetTickCount() {
        return SystemClock.uptimeMillis();
    }

    /* access modifiers changed from: protected */
    public void setViewPos(RelativeLayout layout, View view, int x, int y, int w, int h) {
        RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(w, h);
        rl.leftMargin = x;
        rl.topMargin = y - 60;
        view.setLayoutParams(rl);
        layout.addView(view);
    }

    public void Sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int Neg(int val) {
        if (val == 0) {
            return 1;
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public void SetCommBtn(ParamButton btn, int text, int id, View.OnClickListener l) {
        SetCommBtn(btn, text);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(l);
    }

    /* access modifiers changed from: protected */
    public void SetCommBtn(ParamButton btn, int text) {
        btn.setStateUpDn(R.drawable.fuel_consumption_rect_up, R.drawable.fuel_consumption_rect_dn);
        if (text != 0) {
            btn.setText(text);
        }
        btn.setPadding(0, 3, 0, 0);
        btn.setTextColor(-1);
        btn.setGravity(17);
        btn.setTextSize(0, 32.0f);
    }

    public String getCurrentActivityName() {
        return ((ActivityManager) getSystemService("activity")).getRunningTasks(1).get(0).topActivity.getClassName();
    }
}
