package com.Ty.T.Tr.T9;

import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import com.Ty.T.Tr.T.TE;
import com.Ty.T.Ty.Ty;
import java.lang.reflect.Field;

/* compiled from: Proguard */
public class Tr extends Ty {
    public Tr(ImageView imageView) {
        super(imageView);
    }

    public int T() {
        ImageView imageView;
        int width = super.T();
        if (width > 0 || (imageView = (ImageView) this.f326T.get()) == null) {
            return width;
        }
        return T((Object) imageView, "mMaxWidth");
    }

    public int Tr() {
        ImageView imageView;
        int height = super.Tr();
        if (height > 0 || (imageView = (ImageView) this.f326T.get()) == null) {
            return height;
        }
        return T((Object) imageView, "mMaxHeight");
    }

    public TE Ty() {
        ImageView imageView = (ImageView) this.f326T.get();
        if (imageView != null) {
            return TE.T(imageView);
        }
        return super.Ty();
    }

    /* renamed from: TZ */
    public ImageView Tn() {
        return (ImageView) super.Tn();
    }

    /* access modifiers changed from: protected */
    public void T(Drawable drawable, View view) {
        ((ImageView) view).setImageDrawable(drawable);
        if (drawable instanceof AnimationDrawable) {
            ((AnimationDrawable) drawable).start();
        }
    }

    /* access modifiers changed from: protected */
    public void T(Bitmap bitmap, View view) {
        ((ImageView) view).setImageBitmap(bitmap);
    }

    private static int T(Object object, String fieldName) {
        try {
            Field field = ImageView.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            int fieldValue = ((Integer) field.get(object)).intValue();
            if (fieldValue <= 0 || fieldValue >= Integer.MAX_VALUE) {
                return 0;
            }
            return fieldValue;
        } catch (Exception e) {
            Ty.T((Throwable) e);
            return 0;
        }
    }
}
