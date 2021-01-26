package com.txznet.comm.base;

import android.app.Activity;
import java.util.NoSuchElementException;
import java.util.Stack;

/* compiled from: Proguard */
public class T {
    private static T Tr;
    private static int Ty = 0;

    /* renamed from: T  reason: collision with root package name */
    private Stack<Activity> f419T = new Stack<>();

    private T() {
    }

    public static T T() {
        if (Tr == null) {
            synchronized (T.class) {
                if (Tr == null) {
                    Tr = new T();
                }
            }
        }
        return Tr;
    }

    public void T(Activity activity) {
        if (activity != null) {
            this.f419T.remove(activity);
        }
    }

    public void Tr(Activity activity) {
        this.f419T.add(activity);
    }

    public boolean Tr() {
        return this.f419T.size() > 0;
    }

    public Activity Ty() {
        try {
            return (Activity) this.f419T.lastElement();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public void Tn() {
        Ty++;
        if (1 == Ty) {
            T(true);
        }
    }

    public void T9() {
        Ty--;
        if (Ty == 0) {
            T(false);
        }
    }

    private void T(boolean visible) {
        Tr.T("onVisibilityChanged", Boolean.TYPE, Boolean.valueOf(visible));
    }
}
