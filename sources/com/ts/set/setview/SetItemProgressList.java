package com.ts.set.setview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import com.ts.MainUI.R;

public class SetItemProgressList {
    private Bitmap mBmpProgress;
    private Button mBtnTitle;
    private SeekBar.OnSeekBarChangeListener mCallBack;
    private int mId;
    private int mMax;
    private TextView mVal;
    private View mView;
    private SeekBar m_SeekBar;
    private int nVal = -1;

    public interface onPosChange {
        void onChanged(int i, int i2);
    }

    public SetItemProgressList(Context context, int resId) {
        Create(context, resId);
    }

    public SetItemProgressList(Context context, String strVal) {
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

    public void SetIdCallBack(int id, SeekBar.OnSeekBarChangeListener callback) {
        this.mId = id;
        this.m_SeekBar.setTag(Integer.valueOf(this.mId));
        this.mCallBack = callback;
        this.m_SeekBar.setOnSeekBarChangeListener(this.mCallBack);
    }

    public void SetCurVal(int nPos) {
        this.m_SeekBar.setProgress(nPos);
        if (this.mVal == null) {
            this.nVal = nPos;
        } else {
            SetValText(new StringBuilder().append(nPos).toString());
        }
    }

    private void Init(Context context) {
        this.mView = LayoutInflater.from(context).inflate(R.layout.set_item_progress_list, (ViewGroup) null);
        this.mBtnTitle = (Button) this.mView.findViewById(R.id.btn);
        this.mBmpProgress = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.setup_general_seekbar_progress)).getBitmap();
        this.mVal = (TextView) this.mView.findViewById(R.id.val);
        this.m_SeekBar = (SeekBar) this.mView.findViewById(R.id.img_progress);
        SetCurVal(this.nVal);
    }

    public void SetMinMax(int min, int max) {
        this.mMax = max;
        this.m_SeekBar.setMax(this.mMax);
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
}
