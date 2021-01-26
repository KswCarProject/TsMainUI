package com.ts.set.dsp;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.ts.MainUI.R;
import com.ts.bt.BtExe;
import com.ts.can.CanCameraUI;
import com.ts.set.dsp.Switch;
import com.txznet.sdk.TXZPoiSearchManager;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.KeyDef;
import java.text.NumberFormat;
import java.util.Locale;

public class SetDspAdvancedActivity extends BaseActivity {
    public static final String TAG = "dsp";
    boolean isFrontHorn = false;
    boolean isUpgradeDsp = false;
    Button[] mBtnDspAdvancedDecs;
    Button[] mBtnDspAdvancedIncs;
    Button mBtnDspFilterBack;
    Button mBtnDspFilterFront;
    float mCursorWidth = 63.0f;
    View[] mDspAdvancedAdjustViews;
    int mHpfIndex = 0;
    int[] mHpfValues = {20, 31, 50, 63, 80, 125, 180, Can.CAN_NISSAN_XFY, KeyDef.RKEY_RADIO_3S, 410, 500};
    float[] mIndexValues = {0.0f, 32.0f, 63.0f, 95.0f, 126.0f, 158.0f, 189.0f, 221.0f, 252.0f, 284.0f, 318.0f};
    AdvancedImageView mIvDspAdvancedHpf;
    AdvancedImageView mIvDspAdvancedLpf;
    int mLpfIndex = 0;
    int[] mLpfValues = {1250, CanCameraUI.BTN_FENGSHEN_AX7_MODE_UP, 2000, 2500, 3150, 4000, BtExe.AUTO_ANSWER_CHK_TIME, 6300, 8000, TXZPoiSearchManager.DEFAULT_SEARCH_TIMEOUT, 20000};
    public int mOffset = 4;
    RelativeLayout mRvDspAdvancedLayout;
    Switch mSwitchDspAdvancedHpf;
    Switch mSwitchDspAdvancedLpf;
    TextView[] mTvDspAdvancedValues;
    float mWidth = 318.0f;
    NumberFormat nf = NumberFormat.getInstance(Locale.ENGLISH);

    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_dsp_filter_front) {
            this.isFrontHorn = true;
            initData();
        } else if (id == R.id.btn_dsp_filter_back) {
            this.isFrontHorn = false;
            initData();
        }
    }

    public boolean onLongClick(View v) {
        return false;
    }

    public int getLayout() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int ret = Iop.GetDspVer(new char[8]);
        Log.d(TAG, "ret = " + ret);
        if (ret == 2) {
            setContentView(R.layout.activity_dsp_filter);
            this.isUpgradeDsp = true;
        } else {
            setContentView(R.layout.activity_dsp_advanced);
            this.isUpgradeDsp = false;
        }
        initButton();
        initView();
        initData();
    }

    public void initView() {
        initIndexValues();
        if (this.isUpgradeDsp) {
            this.mBtnDspFilterFront = (Button) findViewById(R.id.btn_dsp_filter_front);
            this.mBtnDspFilterBack = (Button) findViewById(R.id.btn_dsp_filter_back);
            this.mBtnDspFilterFront.setOnClickListener(this);
            this.mBtnDspFilterBack.setOnClickListener(this);
        }
        this.mBtnDspAdvancedDecs = new Button[2];
        this.mBtnDspAdvancedIncs = new Button[2];
        this.mTvDspAdvancedValues = new TextView[2];
        this.mDspAdvancedAdjustViews = new View[2];
        this.mRvDspAdvancedLayout = (RelativeLayout) findViewById(R.id.rv_dsp_advanced_layout);
        this.mSwitchDspAdvancedHpf = (Switch) findViewById(R.id.switch_dsp_advanced_hpf);
        this.mSwitchDspAdvancedLpf = (Switch) findViewById(R.id.switch_dsp_advanced_lpf);
        this.mIvDspAdvancedHpf = (AdvancedImageView) findViewById(R.id.iv_dsp_advanced_hpf);
        this.mIvDspAdvancedLpf = (AdvancedImageView) findViewById(R.id.iv_dsp_advanced_lpf);
        this.mIvDspAdvancedHpf.setDefaultBitmap(R.drawable.dsp_gj_yling);
        this.mIvDspAdvancedLpf.setDefaultBitmap(R.drawable.dsp_yj_yling);
        this.mIvDspAdvancedHpf.setOnTouchListener(new View.OnTouchListener() {
            int mX;

            public boolean onTouch(View v, MotionEvent event) {
                if (!v.isEnabled()) {
                    return false;
                }
                int action = event.getAction();
                this.mX = (int) event.getX();
                this.mX = (int) (((float) this.mX) - SetDspAdvancedActivity.this.mCursorWidth);
                if (((float) this.mX) >= SetDspAdvancedActivity.this.mWidth) {
                    this.mX = (int) SetDspAdvancedActivity.this.mWidth;
                } else if (this.mX < 0) {
                    this.mX = 0;
                }
                SetDspAdvancedActivity.this.checkIndex(this.mX, 0);
                switch (action) {
                    case 0:
                        SetDspAdvancedActivity.this.updateHpfValue(false);
                        return true;
                    case 1:
                        SetDspAdvancedActivity.this.updateHpfValue(true);
                        return true;
                    case 2:
                        SetDspAdvancedActivity.this.updateHpfValue(false);
                        return true;
                    default:
                        return false;
                }
            }
        });
        this.mIvDspAdvancedLpf.setOnTouchListener(new View.OnTouchListener() {
            int mX;

            public boolean onTouch(View v, MotionEvent event) {
                if (!v.isEnabled()) {
                    return false;
                }
                int action = event.getAction();
                this.mX = (int) event.getX();
                this.mX = (int) (((float) this.mX) - SetDspAdvancedActivity.this.mCursorWidth);
                if (((float) this.mX) >= SetDspAdvancedActivity.this.mWidth) {
                    this.mX = (int) SetDspAdvancedActivity.this.mWidth;
                } else if (this.mX < 0) {
                    this.mX = 0;
                }
                SetDspAdvancedActivity.this.checkIndex(this.mX, 1);
                switch (action) {
                    case 0:
                        SetDspAdvancedActivity.this.updateLpfValue(false);
                        return true;
                    case 1:
                        SetDspAdvancedActivity.this.updateLpfValue(true);
                        return true;
                    case 2:
                        SetDspAdvancedActivity.this.updateLpfValue(false);
                        return true;
                    default:
                        return false;
                }
            }
        });
        this.mSwitchDspAdvancedHpf.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            public void onCheckedChanged(View v, boolean isChecked) {
                if (isChecked) {
                    SetDspAdvancedActivity.this.DspSetFilter(1, 1);
                } else {
                    SetDspAdvancedActivity.this.DspSetFilter(1, 0);
                }
                SetDspAdvancedActivity.this.checkHpf();
            }
        });
        this.mSwitchDspAdvancedLpf.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            public void onCheckedChanged(View v, boolean isChecked) {
                Log.d(SetDspAdvancedActivity.TAG, "onCheckedChanger: " + isChecked);
                if (isChecked) {
                    SetDspAdvancedActivity.this.DspSetFilter(0, 1);
                } else {
                    SetDspAdvancedActivity.this.DspSetFilter(0, 0);
                }
                SetDspAdvancedActivity.this.checkLpf();
            }
        });
        addAdjustLayout();
    }

    /* access modifiers changed from: package-private */
    public void checkSelected() {
        if (!this.isUpgradeDsp) {
            return;
        }
        if (this.isFrontHorn) {
            this.mBtnDspFilterFront.setSelected(true);
            this.mBtnDspFilterBack.setSelected(false);
            return;
        }
        this.mBtnDspFilterFront.setSelected(false);
        this.mBtnDspFilterBack.setSelected(true);
    }

    /* access modifiers changed from: package-private */
    public void switchIvBg() {
        if (!this.isUpgradeDsp) {
            this.mIvDspAdvancedHpf.setDefaultBitmap(R.drawable.dsp_gj_yling);
            this.mIvDspAdvancedLpf.setDefaultBitmap(R.drawable.dsp_yj_yling);
        } else if (this.isFrontHorn) {
            this.mIvDspAdvancedLpf.setBackgroundResource(R.drawable.dsp_gj_bg_g_01);
            this.mIvDspAdvancedHpf.setBackgroundResource(R.drawable.dsp_gj_bg_g_01);
        } else {
            this.mIvDspAdvancedLpf.setBackgroundResource(R.drawable.dsp_gj_bg_y_01);
            this.mIvDspAdvancedHpf.setBackgroundResource(R.drawable.dsp_gj_bg_y_01);
        }
    }

    /* access modifiers changed from: package-private */
    public void changeOffset() {
        if (!this.isUpgradeDsp) {
            this.mOffset = 0;
        } else if (this.isFrontHorn) {
            this.mOffset = 4;
        } else {
            this.mOffset = 8;
        }
    }

    /* access modifiers changed from: package-private */
    public void updateLpfValue() {
        Log.d(TAG, "updateLpfValue");
        checkLpf(DspGetFilter(2));
        int value = this.mLpfValues[this.mLpfIndex];
        float index = this.mIndexValues[this.mLpfIndex];
        this.mTvDspAdvancedValues[1].setText(String.valueOf(this.nf.format((double) (((float) value) * 0.001f))) + "khz");
        this.mIvDspAdvancedLpf.setOffset(index);
    }

    /* access modifiers changed from: package-private */
    public void updateLpfValue(boolean isSet) {
        int value = this.mLpfValues[this.mLpfIndex];
        this.mIvDspAdvancedLpf.setOffset(this.mIndexValues[this.mLpfIndex]);
        this.mTvDspAdvancedValues[1].setText(String.valueOf(this.nf.format((double) (((float) value) * 0.001f))) + "khz");
        if (isSet) {
            DspSetFilter(2, value);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateHpfValue(boolean isSet) {
        int value = this.mHpfValues[this.mHpfIndex];
        this.mIvDspAdvancedHpf.setOffset(this.mIndexValues[this.mHpfIndex]);
        this.mTvDspAdvancedValues[0].setText(String.valueOf(value) + "hz");
        if (isSet) {
            DspSetFilter(3, value);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateHpfValue() {
        Log.d(TAG, "updateRpfValue");
        checkHpf(DspGetFilter(3));
        int value = this.mHpfValues[this.mHpfIndex];
        float index = this.mIndexValues[this.mHpfIndex];
        this.mTvDspAdvancedValues[0].setText(String.valueOf(value) + "hz");
        this.mIvDspAdvancedHpf.setOffset(index);
    }

    /* access modifiers changed from: package-private */
    public void checkLpf() {
        if (DspGetFilter(0) == 1) {
            this.mSwitchDspAdvancedLpf.setChecked(true);
            this.mTvDspAdvancedValues[1].setTextColor(-1);
            this.mBtnDspAdvancedDecs[1].setClickable(true);
            this.mBtnDspAdvancedIncs[1].setClickable(true);
            this.mIvDspAdvancedLpf.setEnabled(true);
            updateLpfValue();
            return;
        }
        this.mSwitchDspAdvancedLpf.setChecked(false);
        this.mTvDspAdvancedValues[1].setTextColor(-7829368);
        this.mBtnDspAdvancedDecs[1].setClickable(false);
        this.mBtnDspAdvancedIncs[1].setClickable(false);
        this.mIvDspAdvancedLpf.setEnabled(false);
        resetLpf();
    }

    /* access modifiers changed from: package-private */
    public void checkHpf() {
        if (DspGetFilter(1) == 1) {
            this.mSwitchDspAdvancedHpf.setChecked(true);
            this.mTvDspAdvancedValues[0].setTextColor(-1);
            this.mBtnDspAdvancedDecs[0].setClickable(true);
            this.mBtnDspAdvancedIncs[0].setClickable(true);
            this.mIvDspAdvancedHpf.setEnabled(true);
            updateHpfValue();
            return;
        }
        this.mSwitchDspAdvancedHpf.setChecked(false);
        this.mTvDspAdvancedValues[0].setTextColor(-7829368);
        this.mBtnDspAdvancedDecs[0].setClickable(false);
        this.mBtnDspAdvancedIncs[0].setClickable(false);
        this.mIvDspAdvancedHpf.setEnabled(false);
        resetHpf();
    }

    /* access modifiers changed from: package-private */
    public void resetHpf() {
        DspSetFilter(3, 20);
        updateHpfValue();
    }

    /* access modifiers changed from: package-private */
    public void resetLpf() {
        DspSetFilter(2, 20000);
        updateLpfValue();
    }

    public void initData() {
        changeOffset();
        checkSelected();
        switchIvBg();
        checkLpf();
        checkHpf();
    }

    /* access modifiers changed from: package-private */
    public void addAdjustLayout() {
        int adjustLayoutWidth;
        int adjustLayoutHeight;
        int adjustLayoutLeftMargin_1;
        int adjustLayoutLeftMargin_2;
        int adjustLayoutTopMargin;
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        for (int i = 0; i < this.mBtnDspAdvancedDecs.length; i++) {
            this.mDspAdvancedAdjustViews[i] = layoutInflater.inflate(R.layout.dsp_advanced_adjust_layout, (ViewGroup) null);
            this.mBtnDspAdvancedDecs[i] = (Button) this.mDspAdvancedAdjustViews[i].findViewById(R.id.btn_dsp_advanced_dec);
            this.mBtnDspAdvancedIncs[i] = (Button) this.mDspAdvancedAdjustViews[i].findViewById(R.id.btn_dsp_advanced_inc);
            this.mTvDspAdvancedValues[i] = (TextView) this.mDspAdvancedAdjustViews[i].findViewById(R.id.tv_dsp_advanced_value);
            this.mBtnDspAdvancedDecs[i].setTag(Integer.valueOf(i));
            this.mBtnDspAdvancedIncs[i].setTag(Integer.valueOf(i));
            this.mBtnDspAdvancedDecs[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (((Integer) v.getTag()).intValue() == 0) {
                        SetDspAdvancedActivity setDspAdvancedActivity = SetDspAdvancedActivity.this;
                        setDspAdvancedActivity.mHpfIndex--;
                        if (SetDspAdvancedActivity.this.mHpfIndex < 0) {
                            SetDspAdvancedActivity.this.mHpfIndex = 0;
                        }
                        SetDspAdvancedActivity.this.DspSetFilter(3, SetDspAdvancedActivity.this.mHpfValues[SetDspAdvancedActivity.this.mHpfIndex]);
                        SetDspAdvancedActivity.this.updateHpfValue();
                        return;
                    }
                    SetDspAdvancedActivity setDspAdvancedActivity2 = SetDspAdvancedActivity.this;
                    setDspAdvancedActivity2.mLpfIndex--;
                    if (SetDspAdvancedActivity.this.mLpfIndex < 0) {
                        SetDspAdvancedActivity.this.mLpfIndex = 0;
                    }
                    SetDspAdvancedActivity.this.DspSetFilter(2, SetDspAdvancedActivity.this.mLpfValues[SetDspAdvancedActivity.this.mLpfIndex]);
                    SetDspAdvancedActivity.this.updateLpfValue();
                }
            });
            this.mBtnDspAdvancedDecs[i].setOnTouchListener(new View.OnTouchListener() {
                Handler handler = new Handler() {
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        AnonymousClass6.this.isLongClick = true;
                    }
                };
                boolean isLongClick = false;

                public boolean onTouch(View v, MotionEvent event) {
                    int action = event.getAction();
                    int id = ((Integer) v.getTag()).intValue();
                    switch (action) {
                        case 0:
                            this.isLongClick = false;
                            this.handler.sendEmptyMessageDelayed(id, 1000);
                            break;
                        case 1:
                            if (!this.isLongClick) {
                                this.handler.removeMessages(id);
                                break;
                            } else {
                                this.isLongClick = false;
                                if (id != 0) {
                                    SetDspAdvancedActivity.this.DspSetFilter(2, SetDspAdvancedActivity.this.mLpfValues[SetDspAdvancedActivity.this.mLpfIndex]);
                                    SetDspAdvancedActivity.this.updateLpfValue(false);
                                    break;
                                } else {
                                    SetDspAdvancedActivity.this.DspSetFilter(3, SetDspAdvancedActivity.this.mHpfValues[SetDspAdvancedActivity.this.mHpfIndex]);
                                    SetDspAdvancedActivity.this.updateHpfValue(false);
                                    break;
                                }
                            }
                        case 2:
                            if (this.isLongClick) {
                                if (id != 0) {
                                    SetDspAdvancedActivity setDspAdvancedActivity = SetDspAdvancedActivity.this;
                                    setDspAdvancedActivity.mLpfIndex--;
                                    if (SetDspAdvancedActivity.this.mLpfIndex < 0) {
                                        SetDspAdvancedActivity.this.mLpfIndex = 0;
                                    }
                                    SetDspAdvancedActivity.this.updateLpfValue(false);
                                    break;
                                } else {
                                    SetDspAdvancedActivity setDspAdvancedActivity2 = SetDspAdvancedActivity.this;
                                    setDspAdvancedActivity2.mHpfIndex--;
                                    if (SetDspAdvancedActivity.this.mHpfIndex < 0) {
                                        SetDspAdvancedActivity.this.mHpfIndex = 0;
                                    }
                                    SetDspAdvancedActivity.this.updateHpfValue(false);
                                    break;
                                }
                            }
                            break;
                    }
                    return false;
                }
            });
            this.mBtnDspAdvancedIncs[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (((Integer) v.getTag()).intValue() == 0) {
                        SetDspAdvancedActivity.this.mHpfIndex++;
                        int len = SetDspAdvancedActivity.this.mHpfValues.length;
                        if (SetDspAdvancedActivity.this.mHpfIndex >= len) {
                            SetDspAdvancedActivity.this.mHpfIndex = len - 1;
                        }
                        SetDspAdvancedActivity.this.DspSetFilter(3, SetDspAdvancedActivity.this.mHpfValues[SetDspAdvancedActivity.this.mHpfIndex]);
                        SetDspAdvancedActivity.this.updateHpfValue();
                        return;
                    }
                    SetDspAdvancedActivity.this.mLpfIndex++;
                    int len2 = SetDspAdvancedActivity.this.mLpfValues.length;
                    if (SetDspAdvancedActivity.this.mLpfIndex >= len2) {
                        SetDspAdvancedActivity.this.mLpfIndex = len2 - 1;
                    }
                    SetDspAdvancedActivity.this.DspSetFilter(2, SetDspAdvancedActivity.this.mLpfValues[SetDspAdvancedActivity.this.mLpfIndex]);
                    SetDspAdvancedActivity.this.updateLpfValue();
                }
            });
            this.mBtnDspAdvancedIncs[i].setOnTouchListener(new View.OnTouchListener() {
                Handler handler = new Handler() {
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        AnonymousClass8.this.isLongClick = true;
                    }
                };
                boolean isLongClick = false;

                public boolean onTouch(View v, MotionEvent event) {
                    int action = event.getAction();
                    int id = ((Integer) v.getTag()).intValue();
                    switch (action) {
                        case 0:
                            this.isLongClick = false;
                            this.handler.sendEmptyMessageDelayed(id, 1000);
                            break;
                        case 1:
                            if (!this.isLongClick) {
                                this.handler.removeMessages(id);
                                break;
                            } else {
                                this.isLongClick = false;
                                if (id != 0) {
                                    SetDspAdvancedActivity.this.DspSetFilter(2, SetDspAdvancedActivity.this.mLpfValues[SetDspAdvancedActivity.this.mLpfIndex]);
                                    SetDspAdvancedActivity.this.updateLpfValue(false);
                                    break;
                                } else {
                                    SetDspAdvancedActivity.this.DspSetFilter(3, SetDspAdvancedActivity.this.mHpfValues[SetDspAdvancedActivity.this.mHpfIndex]);
                                    SetDspAdvancedActivity.this.updateHpfValue(false);
                                    break;
                                }
                            }
                        case 2:
                            if (this.isLongClick) {
                                if (id != 0) {
                                    SetDspAdvancedActivity.this.mLpfIndex++;
                                    int len = SetDspAdvancedActivity.this.mLpfValues.length;
                                    if (SetDspAdvancedActivity.this.mLpfIndex >= len) {
                                        SetDspAdvancedActivity.this.mLpfIndex = len - 1;
                                    }
                                    SetDspAdvancedActivity.this.updateLpfValue(false);
                                    break;
                                } else {
                                    SetDspAdvancedActivity.this.mHpfIndex++;
                                    int len2 = SetDspAdvancedActivity.this.mHpfValues.length;
                                    if (SetDspAdvancedActivity.this.mHpfIndex >= len2) {
                                        SetDspAdvancedActivity.this.mHpfIndex = len2 - 1;
                                    }
                                    SetDspAdvancedActivity.this.updateHpfValue(false);
                                    break;
                                }
                            }
                            break;
                    }
                    return false;
                }
            });
            Resources res = getResources();
            if (this.isUpgradeDsp) {
                adjustLayoutWidth = res.getDimensionPixelSize(R.dimen.dsp_filter1_adjust_layout_width);
                adjustLayoutHeight = res.getDimensionPixelSize(R.dimen.dsp_filter1_adjust_layout_height);
                adjustLayoutLeftMargin_1 = res.getDimensionPixelSize(R.dimen.dsp_filter1_adjust_layout_left_margin1);
                adjustLayoutLeftMargin_2 = res.getDimensionPixelSize(R.dimen.dsp_filter1_adjust_layout_left_margin2);
                adjustLayoutTopMargin = res.getDimensionPixelSize(R.dimen.dsp_filter1_adjust_layout_top_margin);
            } else {
                adjustLayoutWidth = res.getDimensionPixelSize(R.dimen.dsp_filter_adjust_layout_width);
                adjustLayoutHeight = res.getDimensionPixelSize(R.dimen.dsp_filter_adjust_layout_height);
                adjustLayoutLeftMargin_1 = res.getDimensionPixelSize(R.dimen.dsp_filter_adjust_layout_left_margin1);
                adjustLayoutLeftMargin_2 = res.getDimensionPixelSize(R.dimen.dsp_filter_adjust_layout_left_margin2);
                adjustLayoutTopMargin = res.getDimensionPixelSize(R.dimen.dsp_filter_adjust_layout_top_margin);
            }
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(adjustLayoutWidth, adjustLayoutHeight);
            if (i % 2 == 0) {
                layoutParams.leftMargin = adjustLayoutLeftMargin_1;
            } else {
                layoutParams.leftMargin = adjustLayoutLeftMargin_2;
            }
            layoutParams.topMargin = adjustLayoutTopMargin;
            this.mRvDspAdvancedLayout.addView(this.mDspAdvancedAdjustViews[i], layoutParams);
        }
    }

    /* access modifiers changed from: package-private */
    public void checkHpf(int value) {
        int len = this.mHpfValues.length;
        if (value <= this.mHpfValues[0]) {
            this.mHpfIndex = 0;
        } else if (value >= this.mHpfValues[len - 1]) {
            this.mHpfIndex = len - 1;
        } else {
            int i = 0;
            while (i < len - 1) {
                if (value <= this.mHpfValues[i] || value > this.mHpfValues[i + 1]) {
                    i++;
                } else if (value - this.mHpfValues[i] > this.mHpfValues[i + 1] - value) {
                    this.mHpfIndex = i + 1;
                    return;
                } else {
                    this.mHpfIndex = i;
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void checkLpf(int value) {
        int len = this.mLpfValues.length;
        if (value <= this.mLpfValues[0]) {
            this.mLpfIndex = 0;
        } else if (value >= this.mLpfValues[len - 1]) {
            this.mLpfIndex = len - 1;
        } else {
            int i = 0;
            while (i < len - 1) {
                if (value <= this.mLpfValues[i] || value > this.mLpfValues[i + 1]) {
                    i++;
                } else if (value - this.mLpfValues[i] > this.mLpfValues[i + 1] - value) {
                    this.mLpfIndex = i + 1;
                    return;
                } else {
                    this.mLpfIndex = i;
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void checkIndex(int index, int flag) {
        int position = 0;
        float margin = this.mWidth / 20.0f;
        int i = 1;
        while (true) {
            if (i <= 21) {
                float right = margin * ((float) i);
                if (((float) index) >= margin * ((float) (i - 2)) && ((float) index) <= right) {
                    position = i / 2;
                    break;
                }
                i += 2;
            } else {
                break;
            }
        }
        if (flag == 0) {
            this.mHpfIndex = position;
        } else {
            this.mLpfIndex = position;
        }
    }

    /* access modifiers changed from: package-private */
    public void initIndexValues() {
        Resources res = getResources();
        this.mWidth = res.getDimension(R.dimen.dsp_advanceimageview_width);
        this.mCursorWidth = res.getDimension(R.dimen.dsp_advanceimageview_cursor_width);
        float index = this.mWidth / 10.0f;
        int len = this.mIndexValues.length;
        for (int i = 0; i < len; i++) {
            this.mIndexValues[i] = index * ((float) i);
        }
    }

    /* access modifiers changed from: package-private */
    public int DspGetFilter(int cmd) {
        Log.d(TAG, "cmd:" + cmd + ",offset:" + this.mOffset);
        return Iop.DspGetFilter(this.mOffset + cmd);
    }

    /* access modifiers changed from: package-private */
    public void DspSetFilter(int cmd, int value) {
        Log.d(TAG, "cmd:" + cmd + ",offset:" + this.mOffset);
        Iop.DspSetFilter(this.mOffset + cmd, value);
    }
}
