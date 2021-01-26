package com.txznet.comm.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Observable;
import com.txznet.comm.Tr.Tr.Tn;

/* compiled from: Proguard */
public class Tr extends Observable<T> {

    /* renamed from: T  reason: collision with root package name */
    private Context f562T;
    private BroadcastReceiver Tr = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Tn.T("onReceive: action: " + action);
            if (action.equals("android.intent.action.CLOSE_SYSTEM_DIALOGS")) {
                String reason = intent.getStringExtra("reason");
                if ("homekey".equals(reason)) {
                    Tr.this.T();
                    return;
                }
                if ("recentapps".equals(reason) || "lock".equals(reason) || "assist".equals(reason)) {
                }
            }
        }
    };

    /* compiled from: Proguard */
    public interface T {
        void T();
    }

    Tr(Context context) {
        this.f562T = context;
        this.f562T.registerReceiver(this.Tr, new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
    }

    public void T() {
        synchronized (this.mObservers) {
            for (int i = this.mObservers.size() - 1; i >= 0; i--) {
                ((T) this.mObservers.get(i)).T();
            }
        }
    }
}
