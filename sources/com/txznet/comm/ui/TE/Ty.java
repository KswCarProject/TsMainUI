package com.txznet.comm.ui.TE;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;

/* compiled from: Proguard */
public class Ty {

    /* renamed from: T  reason: collision with root package name */
    private static Boolean f538T;

    public static LayoutAnimationController T() {
        if (f538T != null && !f538T.booleanValue()) {
            return null;
        }
        LayoutAnimationController controller = new LayoutAnimationController(Tr(), 0.08f);
        controller.setOrder(0);
        return controller;
    }

    public static AnimationSet Tr() {
        AnimationSet set = new AnimationSet(true);
        Animation animation = new AlphaAnimation(0.2f, 1.0f);
        animation.setDuration((long) 300);
        set.addAnimation(animation);
        Animation animation2 = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.9f, 1, 0.0f);
        animation2.setDuration((long) 300);
        set.addAnimation(animation2);
        return set;
    }
}
