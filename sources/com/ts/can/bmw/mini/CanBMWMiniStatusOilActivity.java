package com.ts.can.bmw.mini;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.internal.view.SupportMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.ts.MainUI.R;
import com.ts.other.CustomImgView;
import com.yyw.ts70xhw.KeyDef;

public class CanBMWMiniStatusOilActivity extends CanBMWMiniBaseActivity {
    /* access modifiers changed from: private */
    public int mBkX = 19;
    /* access modifiers changed from: private */
    public int mBkY = 15;
    /* access modifiers changed from: private */
    public int mCur;
    private RelativeLayout mManager;
    private TextView mMaxValue;
    private TextView mMinValue;
    private ImageView mOilIcon;
    private TextView mOilOver;
    private CustomImgView mOilProgressBar;
    private TextView mTvMeasureBtn;
    private TextView mTvStatus;

    /* access modifiers changed from: protected */
    public void SetLayoutContainer() {
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = (RelativeLayout) findViewById(R.id.can_comm_layout);
        this.mManager.setBackgroundResource(R.drawable.can_mini_bg);
    }

    /* access modifiers changed from: protected */
    public void AddItemView() {
        addTextView(30, 300, R.string.can_oil_measure);
        addTextView(30, 350, R.string.can_oil_measure_notice);
        this.mMinValue = addTextView(280, 190, R.string.can_oil_min_value);
        this.mMaxValue = addTextView(720, 190, R.string.can_oil_max_value);
        this.mOilIcon = addImageView(130, Can.CAN_DFFG_S560, R.drawable.can_mini_icon_oil);
        this.mOilProgressBar = addImageView(350, 170, KeyDef.RKEY_res3, 80);
        this.mOilOver = addTextView(490, 150, R.string.can_oil_over);
        this.mMinValue.setVisibility(4);
        this.mMaxValue.setVisibility(4);
        this.mOilIcon.setVisibility(4);
        this.mOilProgressBar.setVisibility(4);
        this.mOilOver.setVisibility(4);
        this.mTvStatus = addTextView(30, 60, 0);
        this.mTvMeasureBtn = addTextView(800, 450, R.string.can_oil_start_measure);
        this.mTvMeasureBtn.setTextColor(getColorStateList());
        this.mTvMeasureBtn.setOnClickListener(this);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        GetMiniEngineOil();
        if (!i2b(this.mEngineOil.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mEngineOil.Update)) {
            this.mEngineOil.Update = 0;
            setOilStatus(this.mEngineOil.Avalid, this.mEngineOil.Status);
            setMeasureStatus(this.mEngineOil.MeasureAvalid);
            setOilValue(this.mEngineOil.CurFuel);
        }
    }

    /* access modifiers changed from: protected */
    public void Query() {
        Query(74);
    }

    private void setOilStatus(int valid, int status) {
        if (!i2b(valid)) {
            this.mTvStatus.setText(R.string.can_oil_status_invalid);
        } else if (status == 0) {
            this.mTvStatus.setText(R.string.can_oil_status_none);
        } else if (status == 1) {
            this.mTvStatus.setText(R.string.can_oil_status_normal);
        } else {
            this.mTvStatus.setText(R.string.can_oil_status_measuring);
        }
    }

    private void setMeasureStatus(int measure) {
        if (i2b(measure)) {
            this.mTvMeasureBtn.setClickable(true);
            this.mTvMeasureBtn.setEnabled(true);
            return;
        }
        this.mTvMeasureBtn.setClickable(false);
        this.mTvMeasureBtn.setEnabled(false);
    }

    private void setOilValue(int val) {
        if (val < 0 || val > 7) {
            this.mOilIcon.setVisibility(4);
            this.mMinValue.setVisibility(4);
            this.mMaxValue.setVisibility(4);
            this.mOilProgressBar.setVisibility(4);
            this.mOilOver.setVisibility(4);
            return;
        }
        if (val == 7) {
            this.mCur = 6;
            this.mOilOver.setVisibility(0);
        } else {
            this.mCur = val;
            this.mOilOver.setVisibility(4);
        }
        this.mOilProgressBar.invalidate();
        this.mOilIcon.setVisibility(0);
        this.mMinValue.setVisibility(0);
        this.mMaxValue.setVisibility(0);
        this.mOilProgressBar.setVisibility(0);
    }

    private ColorStateList getColorStateList() {
        return new ColorStateList(new int[][]{new int[]{16842910, 16842919}, new int[]{16842910}, new int[0]}, new int[]{SupportMenu.CATEGORY_MASK, -1, -7829368});
    }

    private TextView addTextView(int x, int y, int resId) {
        TextView view = new TextView(this);
        if (resId != 0) {
            view.setText(resId);
        }
        view.setTextColor(-1);
        view.setTextSize(0, 30.0f);
        view.setPadding(0, 0, 30, 0);
        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(-2, -2);
        param.leftMargin = x;
        param.topMargin = y;
        this.mManager.addView(view, param);
        return view;
    }

    private ImageView addImageView(int x, int y, int resId) {
        ImageView view = new ImageView(this);
        view.setImageResource(resId);
        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(-2, -2);
        param.leftMargin = x;
        param.topMargin = y;
        this.mManager.addView(view, param);
        return view;
    }

    private CustomImgView addImageView(int x, int y, int w, int h) {
        final Bitmap bmp = ((BitmapDrawable) getResources().getDrawable(R.drawable.can_seekbar_progress)).getBitmap();
        CustomImgView view = new CustomImgView(this);
        view.setUserPaint(new CustomImgView.onPaint() {
            public boolean userPaint(CustomImgView view, Canvas canvas, Paint paint) {
                view.drawImage(R.drawable.can_seekbar_track, CanBMWMiniStatusOilActivity.this.mBkX, CanBMWMiniStatusOilActivity.this.mBkY);
                if (CanBMWMiniStatusOilActivity.this.mCur >= 0 && CanBMWMiniStatusOilActivity.this.mCur <= 6) {
                    Rect src = new Rect();
                    Rect dst = new Rect();
                    int drawW = ((CanBMWMiniStatusOilActivity.this.mCur + 0) * 302) / 6;
                    src.left = 0;
                    src.top = 0;
                    src.right = drawW;
                    src.bottom = bmp.getHeight();
                    dst.left = CanBMWMiniStatusOilActivity.this.mBkX;
                    dst.top = CanBMWMiniStatusOilActivity.this.mBkY;
                    dst.right = CanBMWMiniStatusOilActivity.this.mBkX + drawW;
                    dst.bottom = CanBMWMiniStatusOilActivity.this.mBkY + bmp.getHeight();
                    canvas.drawBitmap(bmp, src, dst, paint);
                }
                return false;
            }
        });
        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(w, h);
        param.leftMargin = x;
        param.topMargin = y;
        this.mManager.addView(view, param);
        return view;
    }

    public void onClick(View v) {
        CarSet(161, 1);
    }
}
