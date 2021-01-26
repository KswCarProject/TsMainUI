package com.ts.other;

import android.app.Activity;
import android.os.Handler;
import java.lang.ref.WeakReference;

public class TsHandler extends Handler {
    public WeakReference<Activity> mwrActivity;

    public TsHandler(Activity activity) {
        this.mwrActivity = new WeakReference<>(activity);
    }
}
