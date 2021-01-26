package com.txznet.comm.ui.TE;

import android.os.Build;
import android.view.View;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: Proguard */
public class T9 {

    /* renamed from: T  reason: collision with root package name */
    private static final AtomicInteger f538T = new AtomicInteger(1);

    public static int T() {
        int result;
        int newValue;
        if (Build.VERSION.SDK_INT >= 17) {
            return View.generateViewId();
        }
        do {
            result = f538T.get();
            newValue = result + 1;
            if (newValue > 16777215) {
                newValue = 1;
            }
        } while (!f538T.compareAndSet(result, newValue));
        return result;
    }
}
