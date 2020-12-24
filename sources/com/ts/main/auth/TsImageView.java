package com.ts.main.auth;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

public class TsImageView extends ImageView {
    public UserPaint m_UserPaint = null;

    public interface UserPaint {
        void userPaint(Canvas canvas);
    }

    public TsImageView(Context context) {
        super(context);
    }

    public TsImageView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public TsImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.m_UserPaint != null) {
            this.m_UserPaint.userPaint(canvas);
        }
    }

    public void setUserPaint(UserPaint userPaint) {
        this.m_UserPaint = userPaint;
    }
}
