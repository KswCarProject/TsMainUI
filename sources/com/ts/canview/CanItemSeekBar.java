package com.ts.canview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.ts.MainUI.R;

public class CanItemSeekBar implements View.OnClickListener {
    private Button mBtnDecrease;
    private Button mBtnIncrease;
    private CallBack mCallBack;
    private int mId;
    private View mLayout;
    private int mMax;
    private int mMin;
    private ProgressBar mProgerssBar;
    private TextView mTvValue;

    public interface CallBack {
        void onDecrease(int i);

        void onIncrease(int i);
    }

    public void setOnProgressChangedListener(CallBack callBack) {
        this.mCallBack = callBack;
    }

    public CanItemSeekBar(Context context) {
        this.mLayout = View.inflate(context, R.layout.can_item_seek_bar, (ViewGroup) null);
        this.mTvValue = (TextView) this.mLayout.findViewById(R.id.value);
        this.mBtnDecrease = (Button) this.mLayout.findViewById(R.id.btn_decrease);
        this.mBtnIncrease = (Button) this.mLayout.findViewById(R.id.btn_increase);
        this.mProgerssBar = (ProgressBar) this.mLayout.findViewById(R.id.progress_bar);
        this.mTvValue.setText("");
        this.mBtnDecrease.setOnClickListener(this);
        this.mBtnIncrease.setOnClickListener(this);
    }

    public CanItemSeekBar(Context context, int min, int max, int id) {
        this(context);
        this.mId = id;
        setMinMax(min, max);
    }

    public View getView() {
        return this.mLayout;
    }

    public void setValue(int value) {
        this.mTvValue.setText(String.valueOf(value));
        this.mProgerssBar.setProgress(value - this.mMin);
    }

    public void setMinMax(int min, int max) {
        this.mMin = min;
        this.mMax = max;
        this.mProgerssBar.setMax(max - min);
    }

    public int getValue() {
        return this.mProgerssBar.getProgress() + this.mMin;
    }

    public void showGone(boolean show) {
        this.mLayout.setVisibility(show ? 0 : 8);
    }

    public void showGone(int visibility) {
        this.mLayout.setVisibility(visibility);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btn_decrease) {
            if (this.mCallBack != null) {
                this.mCallBack.onDecrease(this.mId);
            }
        } else if (v.getId() == R.id.btn_increase && this.mCallBack != null) {
            this.mCallBack.onIncrease(this.mId);
        }
    }
}
