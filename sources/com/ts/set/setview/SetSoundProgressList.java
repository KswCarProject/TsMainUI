package com.ts.set.setview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.ts.MainUI.R;
import com.ts.other.CustomImgView;
import com.ts.other.ParamButton;

public class SetSoundProgressList implements View.OnClickListener, View.OnTouchListener {
    private static final int ITEM_ADD = 2;
    private static final int ITEM_SUB = 1;
    /* access modifiers changed from: private */
    public int mBkX = 0;
    /* access modifiers changed from: private */
    public int mBkY = 0;
    /* access modifiers changed from: private */
    public int mBmpH;
    /* access modifiers changed from: private */
    public Bitmap mBmpProgress;
    private int mBmpW;
    private ParamButton mBtnAdd;
    private ParamButton mBtnSub;
    private Button mBtnTitle;
    private onPosChange mCallBack;
    /* access modifiers changed from: private */
    public int mCur;
    private int mCurRead;
    private int mId;
    private CustomImgView mImgProgress;
    /* access modifiers changed from: private */
    public int mMax;
    /* access modifiers changed from: private */
    public int mMin;
    private int mStep;
    private TextView mVal;
    private View mView;
    private boolean mbUserVal;

    public interface onPosChange {
        void onChanged(int i, int i2);
    }

    public SetSoundProgressList(Context context, int resId) {
        Create(context, resId);
    }

    public SetSoundProgressList(Context context, String strVal) {
        Create(context, strVal);
    }

    public void Create(Context context, String strVal) {
        Init(context);
        this.mBtnTitle.setText(strVal);
    }

    public void Create(Context context, int resId) {
        Init(context);
        if (resId != 0) {
            this.mBtnTitle.setText(resId);
        }
    }

    public void SetIdCallBack(int id, onPosChange callback) {
        this.mId = id;
        this.mCallBack = callback;
    }

    private void Init(Context context) {
        this.mView = LayoutInflater.from(context).inflate(R.layout.set_sound_progress_list, (ViewGroup) null);
        this.mBtnTitle = (Button) this.mView.findViewById(R.id.btn_sound);
        this.mBtnAdd = (ParamButton) this.mView.findViewById(R.id.btn_sound_add);
        this.mBtnAdd.setStateUpDn(R.drawable.setup_bal_dn_up, R.drawable.setup_bal_dn_dn);
        this.mBtnAdd.setTag(2);
        this.mBtnAdd.setOnClickListener(this);
        this.mBtnSub = (ParamButton) this.mView.findViewById(R.id.btn_sound_sub);
        this.mBtnSub.setStateUpDn(R.drawable.setup_bal_up_up, R.drawable.setup_bal_up_dn);
        this.mBtnSub.setTag(1);
        this.mBtnSub.setOnClickListener(this);
        this.mBmpProgress = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.set_eq_sound_orange)).getBitmap();
        this.mBmpW = this.mBmpProgress.getWidth();
        this.mBmpH = this.mBmpProgress.getHeight();
        this.mVal = (TextView) this.mView.findViewById(R.id.val_sound);
        this.mImgProgress = (CustomImgView) this.mView.findViewById(R.id.img_sound_progress);
        this.mImgProgress.setOnTouchListener(this);
        this.mImgProgress.setUserPaint(new CustomImgView.onPaint() {
            public boolean userPaint(CustomImgView view, Canvas canvas, Paint paint) {
                view.drawImage(R.drawable.set_eq_sound_pillar, SetSoundProgressList.this.mBkX, SetSoundProgressList.this.mBkY);
                if (SetSoundProgressList.this.mCur >= SetSoundProgressList.this.mMin && SetSoundProgressList.this.mCur <= SetSoundProgressList.this.mMax) {
                    Rect src = new Rect();
                    Rect dst = new Rect();
                    int drawW = ((SetSoundProgressList.this.mCur - SetSoundProgressList.this.mMin) * Can.CAN_MZD_TXB) / (SetSoundProgressList.this.mMax - SetSoundProgressList.this.mMin);
                    src.left = 0;
                    src.top = 0;
                    src.right = drawW;
                    src.bottom = SetSoundProgressList.this.mBmpH;
                    dst.left = SetSoundProgressList.this.mBkX;
                    dst.top = SetSoundProgressList.this.mBkY;
                    dst.right = SetSoundProgressList.this.mBkX + drawW;
                    dst.bottom = SetSoundProgressList.this.mBkY + SetSoundProgressList.this.mBmpH;
                    canvas.drawBitmap(SetSoundProgressList.this.mBmpProgress, src, dst, paint);
                }
                return false;
            }
        });
        this.mStep = 1;
        this.mMin = 0;
        this.mMax = 100;
    }

    public void SetStep(int step) {
        this.mStep = step;
    }

    public void SetMinMax(int min, int max) {
        this.mMin = min;
        this.mMax = max;
    }

    public void SetCurVal(int val) {
        this.mCur = val;
        this.mImgProgress.invalidate();
        if (!this.mbUserVal) {
            this.mVal.setText(new StringBuilder(String.valueOf(val - 9)).toString());
        }
    }

    public void SetUserValText() {
        this.mbUserVal = true;
    }

    public View GetView() {
        return this.mView;
    }

    public void SetValText(String val) {
        this.mVal.setText(val);
    }

    public void SetValText(int resId) {
        this.mVal.setText(resId);
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                Adjust(((this.mCur / this.mStep) * this.mStep) - this.mStep);
                return;
            case 2:
                Adjust(((this.mCur / this.mStep) * this.mStep) + this.mStep);
                return;
            default:
                return;
        }
    }

    public int Adjust(int val) {
        int pos = val;
        if (val < this.mMin) {
            pos = this.mMin;
        }
        if (val > this.mMax) {
            pos = this.mMax;
        }
        if (pos != this.mCur) {
            if (this.mCallBack != null) {
                this.mCallBack.onChanged(this.mId, pos);
            }
            Log.d("Adjust", "pos = " + pos);
        }
        return pos;
    }

    private void dealTouch(int x, int y) {
        int i = this.mCur;
        Adjust(((((((x - this.mBkX) + (this.mBmpW / ((this.mMax - this.mMin) * 2))) * (this.mMax - this.mMin)) / this.mBmpW) / this.mStep) * this.mStep) + this.mMin);
    }

    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case 0:
                dealTouch((int) event.getX(), (int) event.getY());
                return false;
            default:
                return false;
        }
    }

    public void ShowGone(int show) {
        ShowGone(show != 0);
    }

    public void ShowGone(boolean show) {
        if (show) {
            this.mView.setVisibility(0);
        } else {
            this.mView.setVisibility(8);
        }
    }
}
