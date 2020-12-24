package com.txznet.comm.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/* compiled from: Proguard */
class StackActivity extends FragmentActivity {
    StackActivity() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        T.T().Tr(this);
        T9();
    }

    /* access modifiers changed from: protected */
    public void T9() {
        try {
            setTheme(Class.forName("com.txznet.txz.comm.R$style").getDeclaredField("AppTransparentTheme").getInt((Object) null));
        } catch (Exception e) {
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        T.T().T((Activity) this);
        super.onDestroy();
        if (isFinishing() && !T.T().Tr()) {
            Tr.Ty("destroy");
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        T.T().Tn();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        T.T().T9();
    }
}
