package com.txznet.comm.ui.dialog;

import android.graphics.drawable.AnimationDrawable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import com.txznet.txz.comm.R;

/* compiled from: Proguard */
public class WinProgress extends WinDialog {

    /* renamed from: T  reason: collision with root package name */
    Object f581T;
    private ImageView T9;
    private int Tn;
    private TextView Tr;
    private AnimationDrawable Ty;

    public WinProgress() {
        this("正在处理中...");
    }

    public WinProgress(String txt) {
        this.Tn = 0;
        setText(txt);
    }

    public WinProgress(String txt, int drawableid) {
        this.Tn = 0;
        setText(txt);
        setDrawableResouceId(drawableid);
    }

    public WinProgress(String txt, boolean isSystem) {
        super(isSystem);
        this.Tn = 0;
        setText(txt);
    }

    /* access modifiers changed from: protected */
    public WinProgress setMessageData(Object data) {
        this.f581T = data;
        return this;
    }

    public Object getMessageData() {
        return this.f581T;
    }

    public <T> T getMessageData(Class<T> cls) {
        return this.f581T;
    }

    /* access modifiers changed from: protected */
    public View T() {
        View context = LayoutInflater.from(getContext()).inflate(R.layout.comm_win_progress, (ViewGroup) null);
        this.Tr = (TextView) context.findViewById(R.id.prgProgress_Text);
        this.T9 = (ImageView) context.findViewById(R.id.imgProgress_Anim);
        if (this.Tn != 0) {
            this.T9.setImageResource(this.Tn);
            this.T9.startAnimation(new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f));
        } else if (this.T9.getDrawable() != null && (this.T9.getDrawable() instanceof AnimationDrawable)) {
            this.Ty = (AnimationDrawable) this.T9.getDrawable();
            this.Ty.start();
        }
        return context;
    }

    public WinProgress setDrawableID(int drawableId) {
        this.Tn = drawableId;
        return this;
    }

    public void setText(String txt) {
        this.Tr.setText(txt);
    }

    public void setDrawableResouceId(int drawableid) {
        this.Tn = drawableid;
        this.T9.setImageResource(this.Tn);
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode != 66) {
            return super.onKeyUp(keyCode, event);
        }
        cancel();
        return true;
    }
}
