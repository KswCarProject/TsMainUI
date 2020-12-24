package com.ts.can;

import android.os.Bundle;
import android.view.View;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.UserCallBack;

public abstract class CanCommonActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener {
    /* access modifiers changed from: protected */
    public abstract int GetContentLayoutId();

    /* access modifiers changed from: protected */
    public abstract void InitUI();

    /* access modifiers changed from: protected */
    public abstract void QueryData();

    /* access modifiers changed from: protected */
    public abstract void ResetData(boolean z);

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(GetContentLayoutId());
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        QueryData();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
    }

    public void UserAll() {
        ResetData(true);
    }

    public void showHideView(View view, int show) {
        showHideView(view, i2b(show));
    }

    public void showGoneView(View view, int show) {
        showGoneView(view, i2b(show));
    }

    public void showHideView(View view, boolean show) {
        view.setVisibility(show ? 0 : 4);
    }

    public void showGoneView(View view, boolean show) {
        view.setVisibility(show ? 0 : 8);
    }

    public void setViewSelected(View view, boolean selected) {
        view.setSelected(selected);
    }

    public void setViewSelected(View view, int selected) {
        view.setSelected(i2b(selected));
    }
}
