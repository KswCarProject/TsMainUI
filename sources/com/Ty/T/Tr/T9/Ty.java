package com.Ty.T.Tr.T9;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import com.Ty.T.Tr.T.TE;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/* compiled from: Proguard */
public abstract class Ty implements T {

    /* renamed from: T  reason: collision with root package name */
    protected Reference<View> f329T;
    protected boolean Tr;

    /* access modifiers changed from: protected */
    public abstract void T(Bitmap bitmap, View view);

    /* access modifiers changed from: protected */
    public abstract void T(Drawable drawable, View view);

    public Ty(View view) {
        this(view, true);
    }

    public Ty(View view, boolean checkActualViewSize) {
        if (view == null) {
            throw new IllegalArgumentException("view must not be null");
        }
        this.f329T = new WeakReference(view);
        this.Tr = checkActualViewSize;
    }

    public int T() {
        View view = this.f329T.get();
        if (view == null) {
            return 0;
        }
        ViewGroup.LayoutParams params = view.getLayoutParams();
        int width = 0;
        if (!(!this.Tr || params == null || params.width == -2)) {
            width = view.getWidth();
        }
        if (width > 0 || params == null) {
            return width;
        }
        return params.width;
    }

    public int Tr() {
        View view = this.f329T.get();
        if (view == null) {
            return 0;
        }
        ViewGroup.LayoutParams params = view.getLayoutParams();
        int height = 0;
        if (!(!this.Tr || params == null || params.height == -2)) {
            height = view.getHeight();
        }
        if (height > 0 || params == null) {
            return height;
        }
        return params.height;
    }

    public TE Ty() {
        return TE.CROP;
    }

    public View Tn() {
        return this.f329T.get();
    }

    public boolean T9() {
        return this.f329T.get() == null;
    }

    public int Tk() {
        View view = this.f329T.get();
        return view == null ? super.hashCode() : view.hashCode();
    }

    public boolean T(Drawable drawable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            View view = this.f329T.get();
            if (view == null) {
                return false;
            }
            T(drawable, view);
            return true;
        }
        com.Ty.T.Ty.Ty.Ty("Can't set a drawable into view. You should call ImageLoader on UI thread for it.", new Object[0]);
        return false;
    }

    public boolean T(Bitmap bitmap) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            View view = this.f329T.get();
            if (view == null) {
                return false;
            }
            T(bitmap, view);
            return true;
        }
        com.Ty.T.Ty.Ty.Ty("Can't set a bitmap into view. You should call ImageLoader on UI thread for it.", new Object[0]);
        return false;
    }
}
