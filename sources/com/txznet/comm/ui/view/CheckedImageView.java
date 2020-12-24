package com.txznet.comm.ui.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.txznet.comm.ui.IKeepClass;
import com.txznet.txz.comm.R;

/* compiled from: Proguard */
public class CheckedImageView extends ImageView implements IKeepClass {

    /* renamed from: T  reason: collision with root package name */
    protected boolean f650T;
    private Drawable Tr;
    private Drawable Ty;

    public CheckedImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        T(context);
    }

    public CheckedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        T(context);
    }

    public CheckedImageView(Context context) {
        super(context);
        T(context);
    }

    private void T(Context context) {
        this.Tr = getResources().getDrawable(R.drawable.asr_switch_on);
        this.Ty = getResources().getDrawable(R.drawable.asr_switch_off);
    }

    public void setChecked(boolean checked) {
        this.f650T = checked;
        setImageDrawable(checked ? this.Tr : this.Ty);
    }

    public boolean isChecked() {
        return this.f650T;
    }
}
