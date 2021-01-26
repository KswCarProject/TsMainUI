package com.ts.set.dsp;

import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ts.MainUI.R;
import com.yyw.ts70xhw.Iop;

public class SetDspEmperorActivity extends BaseActivity {
    Button[] mBtnDspHdwDecs;
    Button[] mBtnDspHdwIncs;
    Button[] mBtnDspHdwModes;
    int mCurMode = -1;
    View[] mDspHdwAdjustViews;
    ImageView[] mIvDspHdwSeats;
    RelativeLayout mRvDspHdwLayout;
    TextView[] mTvDspHdwFlags;
    TextView[] mTvDspHdwValues;

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        showDspHdwSeatView(id);
        if (id == 5) {
            Iop.DspSetPosMode(0);
            updateHdwValues();
            return;
        }
        Iop.DspSetPosMode(id + 1);
    }

    /* access modifiers changed from: package-private */
    public void showDspHdwSeatView(int id) {
        if (this.mCurMode != id) {
            this.mCurMode = id;
            for (ImageView showViewVisible : this.mIvDspHdwSeats) {
                showViewVisible(showViewVisible, false);
            }
            for (Button selected : this.mBtnDspHdwModes) {
                selected.setSelected(false);
            }
            for (int i = 0; i < this.mDspHdwAdjustViews.length; i++) {
                this.mDspHdwAdjustViews[i].setVisibility(8);
                this.mTvDspHdwFlags[i].setVisibility(8);
            }
            if (id < 5) {
                showViewVisible(this.mIvDspHdwSeats[id], true);
            } else {
                for (int i2 = 0; i2 < this.mDspHdwAdjustViews.length; i2++) {
                    this.mDspHdwAdjustViews[i2].setVisibility(0);
                    this.mTvDspHdwFlags[i2].setVisibility(0);
                }
            }
            this.mBtnDspHdwModes[id].setSelected(true);
        }
    }

    /* access modifiers changed from: package-private */
    public void showViewVisible(View view, boolean show) {
        if (show) {
            view.setVisibility(0);
        } else {
            view.setVisibility(8);
        }
    }

    public boolean onLongClick(View v) {
        return false;
    }

    public int getLayout() {
        return R.layout.activity_dsp_emperor;
    }

    public void initView() {
        this.mBtnDspHdwDecs = new Button[4];
        this.mBtnDspHdwIncs = new Button[4];
        this.mTvDspHdwValues = new TextView[4];
        this.mDspHdwAdjustViews = new View[4];
        this.mIvDspHdwSeats = new ImageView[5];
        this.mBtnDspHdwModes = new Button[6];
        this.mTvDspHdwFlags = new TextView[4];
        this.mRvDspHdwLayout = (RelativeLayout) findViewById(R.id.rv_dsp_hdw_layout);
        this.mIvDspHdwSeats[0] = (ImageView) findViewById(R.id.iv_dsp_hdw_seat_01);
        this.mIvDspHdwSeats[1] = (ImageView) findViewById(R.id.iv_dsp_hdw_seat_02);
        this.mIvDspHdwSeats[2] = (ImageView) findViewById(R.id.iv_dsp_hdw_seat_03);
        this.mIvDspHdwSeats[3] = (ImageView) findViewById(R.id.iv_dsp_hdw_seat_04);
        this.mIvDspHdwSeats[4] = (ImageView) findViewById(R.id.iv_dsp_hdw_seat_all);
        this.mBtnDspHdwModes[0] = (Button) findViewById(R.id.btn_dsp_hdw_01);
        this.mBtnDspHdwModes[1] = (Button) findViewById(R.id.btn_dsp_hdw_02);
        this.mBtnDspHdwModes[2] = (Button) findViewById(R.id.btn_dsp_hdw_03);
        this.mBtnDspHdwModes[3] = (Button) findViewById(R.id.btn_dsp_hdw_04);
        this.mBtnDspHdwModes[4] = (Button) findViewById(R.id.btn_dsp_hdw_all);
        this.mBtnDspHdwModes[5] = (Button) findViewById(R.id.btn_dsp_hdw_custom);
        this.mTvDspHdwFlags[0] = (TextView) findViewById(R.id.tv_dsp_hdw_flag_01);
        this.mTvDspHdwFlags[1] = (TextView) findViewById(R.id.tv_dsp_hdw_flag_02);
        this.mTvDspHdwFlags[2] = (TextView) findViewById(R.id.tv_dsp_hdw_flag_03);
        this.mTvDspHdwFlags[3] = (TextView) findViewById(R.id.tv_dsp_hdw_flag_04);
        for (int i = 0; i < this.mBtnDspHdwModes.length; i++) {
            this.mBtnDspHdwModes[i].setOnClickListener(this);
            this.mBtnDspHdwModes[i].setTag(Integer.valueOf(i));
        }
        addAdjustLayout();
        showDspHdwSeatView(0);
    }

    /* access modifiers changed from: package-private */
    public void addAdjustLayout() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        for (int i = 0; i < this.mBtnDspHdwDecs.length; i++) {
            this.mDspHdwAdjustViews[i] = layoutInflater.inflate(R.layout.dsp_hdw_adjust_layout, (ViewGroup) null);
            this.mBtnDspHdwDecs[i] = (Button) this.mDspHdwAdjustViews[i].findViewById(R.id.btn_dsp_hdw_dec);
            this.mBtnDspHdwIncs[i] = (Button) this.mDspHdwAdjustViews[i].findViewById(R.id.btn_dsp_hdw_inc);
            this.mTvDspHdwValues[i] = (TextView) this.mDspHdwAdjustViews[i].findViewById(R.id.tv_dsp_hdw_value);
            this.mBtnDspHdwDecs[i].setTag(Integer.valueOf(i));
            this.mBtnDspHdwIncs[i].setTag(Integer.valueOf(i));
            this.mBtnDspHdwDecs[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    int id = ((Integer) v.getTag()).intValue();
                    int value = Iop.DspGetPosDelay(id) - 1;
                    if (value >= 0) {
                        Iop.DspSetPosDelay(id, value);
                        if (value == 0) {
                            SetDspEmperorActivity.this.mTvDspHdwValues[id].setText(String.valueOf(value) + "ms");
                        } else {
                            SetDspEmperorActivity.this.mTvDspHdwValues[id].setText(String.valueOf(String.format("%.1f", new Object[]{Float.valueOf(((float) value) * 0.1f)})) + "ms");
                        }
                        SetDspEmperorActivity.this.mTvDspHdwFlags[id].setText(String.valueOf(Math.round(((double) value) * 3.4d)) + "cm");
                    }
                }
            });
            this.mBtnDspHdwDecs[i].setOnTouchListener(new View.OnTouchListener() {
                Handler handler = new Handler() {
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        AnonymousClass2.this.isLongClick = true;
                    }
                };
                boolean isLongClick = false;
                int mValue;

                public boolean onTouch(View v, MotionEvent event) {
                    int action = event.getAction();
                    int id = ((Integer) v.getTag()).intValue();
                    switch (action) {
                        case 0:
                            this.isLongClick = false;
                            this.mValue = Iop.DspGetPosDelay(id);
                            this.handler.sendEmptyMessageDelayed(id, 1000);
                            break;
                        case 1:
                            if (!this.isLongClick) {
                                this.handler.removeMessages(id);
                                break;
                            } else {
                                this.isLongClick = false;
                                SetDspEmperorActivity.this.updateHdwValues(true, id, this.mValue);
                                break;
                            }
                        case 2:
                            if (this.isLongClick) {
                                this.mValue--;
                                if (this.mValue < 0) {
                                    this.mValue = 0;
                                }
                                SetDspEmperorActivity.this.updateHdwValues(false, id, this.mValue);
                                break;
                            }
                            break;
                    }
                    return false;
                }
            });
            this.mBtnDspHdwIncs[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    int id = ((Integer) v.getTag()).intValue();
                    int value = Iop.DspGetPosDelay(id) + 1;
                    if (value <= 200) {
                        Iop.DspSetPosDelay(id, value);
                        SetDspEmperorActivity.this.mTvDspHdwValues[id].setText(String.valueOf(String.format("%.1f", new Object[]{Float.valueOf(((float) value) * 0.1f)})) + "ms");
                        SetDspEmperorActivity.this.mTvDspHdwFlags[id].setText(String.valueOf(Math.round(((double) value) * 3.4d)) + "cm");
                    }
                }
            });
            this.mBtnDspHdwIncs[i].setOnTouchListener(new View.OnTouchListener() {
                Handler handler = new Handler() {
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        AnonymousClass4.this.isLongClick = true;
                    }
                };
                boolean isLongClick = false;
                int mValue;

                public boolean onTouch(View v, MotionEvent event) {
                    int action = event.getAction();
                    int id = ((Integer) v.getTag()).intValue();
                    switch (action) {
                        case 0:
                            this.isLongClick = false;
                            this.mValue = Iop.DspGetPosDelay(id);
                            this.handler.sendEmptyMessageDelayed(id, 1000);
                            break;
                        case 1:
                            if (!this.isLongClick) {
                                this.handler.removeMessages(id);
                                break;
                            } else {
                                this.isLongClick = false;
                                SetDspEmperorActivity.this.updateHdwValues(true, id, this.mValue);
                                break;
                            }
                        case 2:
                            if (this.isLongClick) {
                                this.mValue++;
                                if (this.mValue > 200) {
                                    this.mValue = 200;
                                }
                                SetDspEmperorActivity.this.updateHdwValues(false, id, this.mValue);
                                break;
                            }
                            break;
                    }
                    return false;
                }
            });
            Resources res = getResources();
            int adjustLayoutWidth = res.getDimensionPixelSize(R.dimen.dsp_hdw_adjust_layout_width);
            int adjustLayoutHeight = res.getDimensionPixelSize(R.dimen.dsp_hdw_adjust_layout_height);
            int adjustLayoutLeftMargin_1 = res.getDimensionPixelSize(R.dimen.dsp_hdw_adjust_layout_left_margin1);
            int adjustLayoutLeftMargin_2 = res.getDimensionPixelSize(R.dimen.dsp_hdw_adjust_layout_left_margin2);
            int adjustLayoutTopMargin_1 = res.getDimensionPixelSize(R.dimen.dsp_hdw_adjust_layout_top_margin1);
            int adjustLayoutTopMargin_2 = res.getDimensionPixelSize(R.dimen.dsp_hdw_adjust_layout_top_margin2);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(adjustLayoutWidth, adjustLayoutHeight);
            if (i % 2 == 0) {
                layoutParams.leftMargin = adjustLayoutLeftMargin_1;
            } else {
                layoutParams.leftMargin = adjustLayoutLeftMargin_2;
            }
            if (i < 2) {
                layoutParams.topMargin = adjustLayoutTopMargin_1;
            } else {
                layoutParams.topMargin = adjustLayoutTopMargin_2;
            }
            this.mRvDspHdwLayout.addView(this.mDspHdwAdjustViews[i], layoutParams);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateHdwValues() {
        for (int i = 0; i < this.mTvDspHdwValues.length; i++) {
            int value = Iop.DspGetPosDelay(i);
            Log.d(SetDspAdvancedActivity.TAG, "updateHdwValues = " + value);
            if (value != 0) {
                this.mTvDspHdwValues[i].setText(String.valueOf(String.format("%.1f", new Object[]{Float.valueOf(((float) value) * 0.1f)})) + "ms");
            } else {
                this.mTvDspHdwValues[i].setText(String.valueOf(value) + "ms");
            }
            this.mTvDspHdwFlags[i].setText(String.valueOf(Math.round(((double) value) * 3.4d)) + "cm");
        }
    }

    /* access modifiers changed from: package-private */
    public void updateHdwValues(boolean isSet, int id, int value) {
        if (value != 0) {
            this.mTvDspHdwValues[id].setText(String.valueOf(String.format("%.1f", new Object[]{Float.valueOf(((float) value) * 0.1f)})) + "ms");
        } else {
            this.mTvDspHdwValues[id].setText(String.valueOf(value) + "ms");
        }
        this.mTvDspHdwFlags[id].setText(String.valueOf(Math.round(((double) value) * 3.4d)) + "cm");
        if (isSet) {
            Iop.DspSetPosDelay(id, value);
        }
    }

    public void initData() {
        int mode = Iop.DspGetPosMode();
        if (mode == 0) {
            showDspHdwSeatView(5);
            updateHdwValues();
            return;
        }
        showDspHdwSeatView(mode - 1);
    }
}
