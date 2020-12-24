package com.txznet.comm.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import com.txznet.comm.ui.IKeepClass;

/* compiled from: Proguard */
public class RippleView extends RelativeLayout implements IKeepClass {

    /* renamed from: T  reason: collision with root package name */
    private int f653T;
    private int T5 = 0;
    private float T6 = -1.0f;
    private int T9 = 90;
    private Boolean TB;
    private T TD;
    private int TE = 0;
    private ScaleAnimation TF;
    private int TG;
    private Integer TK;
    private Bitmap TN;
    private Paint TO;
    private boolean TZ = false;
    private int Te;
    private float Th = -1.0f;
    private Boolean Tj;
    private float Tk = 0.0f;
    private int Tn = 300;
    private float Tq;
    private int Tr;
    private int Ts;
    private final Runnable Tt = new Runnable() {
        public void run() {
            RippleView.this.invalidate();
        }
    };
    /* access modifiers changed from: private */
    public GestureDetector Tu;
    private int Tv = -1;
    private int Ty = 10;

    /* compiled from: Proguard */
    public interface T {
        void T(RippleView rippleView);
    }

    public RippleView(Context context) {
        super(context);
        T(context, (AttributeSet) null);
    }

    public RippleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        T(context, attrs);
    }

    public RippleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        T(context, attrs);
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        this.TZ = false;
    }

    private void T(final Context context, AttributeSet attrs) {
        if (!isInEditMode()) {
            this.Ts = Color.parseColor("#FFFFFF");
            this.TK = 0;
            this.Tj = false;
            this.TB = false;
            this.TG = 0;
            this.Tq = 1.03f;
            this.Te = 200;
            this.TO = new Paint();
            this.TO.setAntiAlias(true);
            this.TO.setStyle(Paint.Style.FILL);
            this.TO.setColor(this.Ts);
            this.TO.setAlpha(this.T9);
            setWillNotDraw(false);
            post(new Runnable() {
                public void run() {
                    GestureDetector unused = RippleView.this.Tu = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                        public void onLongPress(MotionEvent event) {
                            super.onLongPress(event);
                            RippleView.this.animateRipple(event);
                            RippleView.this.T((Boolean) true);
                        }

                        public boolean onSingleTapConfirmed(MotionEvent e) {
                            return true;
                        }

                        public boolean onSingleTapUp(MotionEvent e) {
                            return true;
                        }
                    });
                }
            });
            setDrawingCacheEnabled(true);
            setClickable(true);
        }
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.TZ) {
            canvas.save();
            if (this.Tn <= this.TE * this.Ty) {
                this.TZ = false;
                this.TE = 0;
                this.Tv = -1;
                this.T5 = 0;
                if (Build.VERSION.SDK_INT != 23) {
                    canvas.restore();
                }
                invalidate();
                if (this.TD != null) {
                    this.TD.T(this);
                    return;
                }
                return;
            }
            postDelayed(this.Tt, (long) this.Ty);
            if (this.TE == 0) {
                canvas.save();
            }
            canvas.drawCircle(this.Th, this.T6, this.Tk * ((((float) this.TE) * ((float) this.Ty)) / ((float) this.Tn)), this.TO);
            this.TO.setColor(Color.parseColor("#ffff4444"));
            if (this.TK.intValue() == 1 && this.TN != null && (((float) this.TE) * ((float) this.Ty)) / ((float) this.Tn) > 0.4f) {
                if (this.Tv == -1) {
                    this.Tv = this.Tn - (this.TE * this.Ty);
                }
                this.T5++;
                Bitmap tmpBitmap = T((int) (this.Tk * ((((float) this.T5) * ((float) this.Ty)) / ((float) this.Tv))));
                canvas.drawBitmap(tmpBitmap, 0.0f, 0.0f, this.TO);
                tmpBitmap.recycle();
            }
            this.TO.setColor(this.Ts);
            if (this.TK.intValue() != 1) {
                this.TO.setAlpha((int) (((float) this.T9) - (((float) this.T9) * ((((float) this.TE) * ((float) this.Ty)) / ((float) this.Tn)))));
            } else if ((((float) this.TE) * ((float) this.Ty)) / ((float) this.Tn) > 0.6f) {
                this.TO.setAlpha((int) (((float) this.T9) - (((float) this.T9) * ((((float) this.T5) * ((float) this.Ty)) / ((float) this.Tv)))));
            } else {
                this.TO.setAlpha(this.T9);
            }
            this.TE++;
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.f653T = w;
        this.Tr = h;
        this.TF = new ScaleAnimation(1.0f, this.Tq, 1.0f, this.Tq, (float) (w / 2), (float) (h / 2));
        this.TF.setDuration((long) this.Te);
        this.TF.setRepeatMode(2);
        this.TF.setRepeatCount(1);
    }

    public void animateRipple(MotionEvent event) {
        T(event.getX(), event.getY());
    }

    public void animateRipple(float x, float y) {
        T(x, y);
    }

    private void T(float x, float y) {
        if (isEnabled() && !this.TZ) {
            if (this.Tj.booleanValue()) {
                startAnimation(this.TF);
            }
            this.Tk = (float) Math.max(this.f653T, this.Tr);
            if (this.TK.intValue() != 2) {
                this.Tk /= 2.0f;
            }
            this.Tk -= (float) this.TG;
            if (this.TB.booleanValue() || this.TK.intValue() == 1) {
                this.Th = (float) (getMeasuredWidth() / 2);
                this.T6 = (float) (getMeasuredHeight() / 2);
            } else {
                this.Th = x;
                this.T6 = y;
            }
            this.TZ = true;
            if (this.TK.intValue() == 1 && this.TN == null) {
                this.TN = getDrawingCache(true);
            }
            invalidate();
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (this.Tu != null && this.Tu.onTouchEvent(event)) {
            animateRipple(event);
            T((Boolean) false);
        }
        return super.onTouchEvent(event);
    }

    public boolean onInterceptTouchEvent(MotionEvent event) {
        animateRipple(event);
        return super.onInterceptTouchEvent(event);
    }

    /* access modifiers changed from: private */
    public void T(Boolean isLongClick) {
        if (getParent() instanceof ExpandableListView) {
            ExpandableListView ad = (ExpandableListView) getParent();
            int position = ad.getPositionForView(this);
            ad.performItemClick(this, position, ad.getItemIdAtPosition(position));
        } else if (getParent() instanceof AdapterView) {
            AdapterView adapterView = (AdapterView) getParent();
            int position2 = adapterView.getPositionForView(this);
            long id = adapterView.getItemIdAtPosition(position2);
            if (isLongClick.booleanValue()) {
                if (adapterView.getOnItemLongClickListener() != null) {
                    adapterView.getOnItemLongClickListener().onItemLongClick(adapterView, this, position2, id);
                }
            } else if (adapterView.getOnItemClickListener() != null) {
                adapterView.getOnItemClickListener().onItemClick(adapterView, this, position2, id);
            }
        }
    }

    private Bitmap T(int radius) {
        Bitmap output = Bitmap.createBitmap(this.TN.getWidth(), this.TN.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        Rect rect = new Rect((int) (this.Th - ((float) radius)), (int) (this.T6 - ((float) radius)), (int) (this.Th + ((float) radius)), (int) (this.T6 + ((float) radius)));
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(this.Th, this.T6, (float) radius, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(this.TN, rect, rect, paint);
        return output;
    }

    @ColorRes
    public void setRippleColor(int rippleColor) {
        this.Ts = getResources().getColor(rippleColor);
    }

    public int getRippleColor() {
        return this.Ts;
    }

    public Tr getRippleType() {
        return Tr.values()[this.TK.intValue()];
    }

    public void setRippleType(Tr rippleType) {
        this.TK = Integer.valueOf(rippleType.ordinal());
    }

    public Boolean isCentered() {
        return this.TB;
    }

    public void setCentered(Boolean isCentered) {
        this.TB = isCentered;
    }

    public int getRipplePadding() {
        return this.TG;
    }

    public void setRipplePadding(int ripplePadding) {
        this.TG = ripplePadding;
    }

    public Boolean isZooming() {
        return this.Tj;
    }

    public void setZooming(Boolean hasToZoom) {
        this.Tj = hasToZoom;
    }

    public float getZoomScale() {
        return this.Tq;
    }

    public void setZoomScale(float zoomScale) {
        this.Tq = zoomScale;
    }

    public int getZoomDuration() {
        return this.Te;
    }

    public void setZoomDuration(int zoomDuration) {
        this.Te = zoomDuration;
    }

    public int getRippleDuration() {
        return this.Tn;
    }

    public void setRippleDuration(int rippleDuration) {
        this.Tn = rippleDuration;
    }

    public int getFrameRate() {
        return this.Ty;
    }

    public void setFrameRate(int frameRate) {
        this.Ty = frameRate;
    }

    public int getRippleAlpha() {
        return this.T9;
    }

    public void setRippleAlpha(int rippleAlpha) {
        this.T9 = rippleAlpha;
    }

    public void setOnRippleCompleteListener(T listener) {
        this.TD = listener;
    }

    /* compiled from: Proguard */
    public enum Tr {
        SIMPLE(0),
        DOUBLE(1),
        RECTANGLE(2);
        
        int Tn;

        private Tr(int type) {
            this.Tn = type;
        }
    }
}
