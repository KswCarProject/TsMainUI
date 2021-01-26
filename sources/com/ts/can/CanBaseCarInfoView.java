package com.ts.can;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import com.ts.MainUI.R;
import com.ts.canview.CanScrollList;
import com.ts.other.RelativeLayoutManager;

public abstract class CanBaseCarInfoView implements View.OnClickListener {
    protected static final int FALSE = 0;
    protected static final int TRUE = 1;
    private Activity mActivity;
    private RelativeLayoutManager mRelativeManager;
    private CanScrollList mScrollManager;

    public enum Type {
        SCROLL,
        RELATIVE
    }

    /* access modifiers changed from: protected */
    public abstract void InitUI();

    public abstract void QueryData();

    public abstract void ResetData(boolean z);

    public abstract void doOnFinish();

    public abstract void doOnPause();

    public abstract void doOnResume();

    public abstract void doOnStart();

    /* access modifiers changed from: protected */
    public abstract View getView();

    public CanBaseCarInfoView(Activity activity, Type type) {
        this.mActivity = activity;
        if (type == Type.SCROLL) {
            activity.setContentView(R.layout.activity_can_comm_list);
            this.mScrollManager = new CanScrollList(this.mActivity);
            return;
        }
        activity.setContentView(R.layout.activity_can_comm_relative);
        this.mRelativeManager = new RelativeLayoutManager(this.mActivity, R.id.can_comm_layout);
    }

    /* access modifiers changed from: protected */
    public CanScrollList getScrollManager() {
        return this.mScrollManager;
    }

    /* access modifiers changed from: protected */
    public RelativeLayoutManager getRelativeManager() {
        return this.mRelativeManager;
    }

    /* access modifiers changed from: protected */
    public boolean i2b(int value) {
        return value > 0;
    }

    /* access modifiers changed from: protected */
    public void setViewShow(View v, int val) {
        if (i2b(val)) {
            v.setVisibility(0);
        } else {
            v.setVisibility(4);
        }
    }

    /* access modifiers changed from: protected */
    public void setViewShow(View v, boolean show) {
        if (show) {
            v.setVisibility(0);
        } else {
            v.setVisibility(4);
        }
    }

    /* access modifiers changed from: protected */
    public int Neg(int value) {
        if (value == 0) {
            return 1;
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public int GetSWVal(int cur, int on, int off) {
        return cur == 0 ? on : off;
    }

    /* access modifiers changed from: protected */
    public int GetDataAdj(int nCur, int nStep, int nMax, int nMin, int fgInc, int fgLoop) {
        if (fgInc > 0) {
            if (nCur + nStep <= nMax) {
                return nCur + nStep;
            }
            if (fgLoop > 0) {
                return nMin;
            }
            return nMax;
        } else if (nCur >= nStep + nMin) {
            return nCur - nStep;
        } else {
            if (fgLoop > 0) {
                return nMax;
            }
            return nMin;
        }
    }

    /* access modifiers changed from: protected */
    public void Sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public String getString(int resId) {
        return getActivity().getString(resId);
    }

    /* access modifiers changed from: protected */
    public String[] getStringArray(int arrayId) {
        return getActivity().getResources().getStringArray(arrayId);
    }

    /* access modifiers changed from: protected */
    public Activity getActivity() {
        return this.mActivity;
    }

    /* access modifiers changed from: protected */
    public void showToast(String text) {
        Toast.makeText(this.mActivity, text, 0).show();
    }

    /* access modifiers changed from: protected */
    public void showToast(int textId) {
        Toast.makeText(this.mActivity, textId, 0).show();
    }

    public void enterSubWin(Class<?> cls) {
        enterSubWin(cls, 0);
    }

    public void enterSubWin(Class<?> cls, int id) {
        Intent intent = new Intent();
        intent.setClass(this.mActivity, cls);
        intent.putExtra("ID", id);
        this.mActivity.startActivity(intent);
        this.mActivity.overridePendingTransition(0, 0);
    }
}
