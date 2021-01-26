package com.ts.main.tpms;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;

public class TpmsPopup implements View.OnClickListener {
    private static final int[] mIconIds = {R.drawable.tpms_num0_up, R.drawable.tpms_num1_up, R.drawable.tpms_num2_up, R.drawable.tpms_num3_up, R.drawable.tpms_num4_up, R.drawable.tpms_num5_up, R.drawable.tpms_num6_up, R.drawable.tpms_num7_up, R.drawable.tpms_num8_up, R.drawable.tpms_num9_up};
    private static TpmsPopup mInstance = null;
    /* access modifiers changed from: private */
    public Callback mCallback;
    private View mContainerStudy;
    private View mContainerSwitch;
    private View mContentView;
    private Context mContext;
    /* access modifiers changed from: private */
    public int mCount;
    private byte[] mData;
    private int[] mIndex;
    private ImageView mIvFirstIcon;
    private ImageView mIvSecondIcon;
    private WindowManager.LayoutParams mLayoutParams;
    private Runnable mTimeTask = new Runnable() {
        public void run() {
            if (TpmsPopup.this.mCount >= 0) {
                TpmsPopup.this.updateTime();
            } else {
                TpmsPopup.this.cancel();
            }
        }
    };
    private TextView mTvLeftID;
    private TextView mTvRightID;
    private TextView mTvStudyNotice;
    private WindowManager mWindowManager;

    public interface Callback {
        void onCancel();

        void onLearningSuccess(int i);

        void onOk(int[] iArr, byte[] bArr);
    }

    public static TpmsPopup getInstance() {
        if (mInstance == null) {
            mInstance = new TpmsPopup();
        }
        return mInstance;
    }

    private TpmsPopup() {
    }

    public void init(Context context, Callback callback) {
        this.mContext = context.getApplicationContext();
        this.mCallback = callback;
        if (this.mWindowManager == null) {
            this.mWindowManager = (WindowManager) this.mContext.getSystemService("window");
            this.mLayoutParams = new WindowManager.LayoutParams();
            this.mLayoutParams.x = 0;
            this.mLayoutParams.y = 0;
            this.mLayoutParams.width = 467;
            this.mLayoutParams.height = CanCameraUI.BTN_TRUMPCHI_GS4_MODE2;
            this.mLayoutParams.gravity = 0;
            this.mLayoutParams.flags = 8;
            this.mLayoutParams.format = 1;
            this.mLayoutParams.type = 2037;
            initContentView();
        }
    }

    private void initContentView() {
        this.mContentView = View.inflate(this.mContext, R.layout.tpms_popup_content, (ViewGroup) null);
        this.mContainerSwitch = this.mContentView.findViewById(R.id.switch_container);
        this.mTvLeftID = (TextView) this.mContentView.findViewById(R.id.tv_left_id);
        this.mTvRightID = (TextView) this.mContentView.findViewById(R.id.tv_right_id);
        Button btnOk = (Button) this.mContentView.findViewById(R.id.btn_ok);
        Button btnCancel = (Button) this.mContentView.findViewById(R.id.btn_cancel);
        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnOk.setBackground(getStateDrawable(R.drawable.tpms_rect_up, R.drawable.tpms_rect_dn));
        btnCancel.setBackground(getStateDrawable(R.drawable.tpms_rect_up, R.drawable.tpms_rect_dn));
        this.mContainerStudy = this.mContentView.findViewById(R.id.study_container);
        this.mTvStudyNotice = (TextView) this.mContentView.findViewById(R.id.tv_study_notice);
        this.mIvFirstIcon = (ImageView) this.mContentView.findViewById(R.id.iv_first_icon);
        this.mIvSecondIcon = (ImageView) this.mContentView.findViewById(R.id.iv_second_icon);
    }

    public void switchTpmsID(CharSequence lefID, CharSequence rightID, int[] index, byte[] data) {
        if (this.mTvLeftID != null) {
            this.mTvLeftID.setText(lefID);
        }
        if (this.mTvRightID != null) {
            this.mTvRightID.setText(rightID);
        }
        this.mIndex = index;
        this.mData = data;
    }

    public void singleLearningSuccess(final int pos) {
        dismiss();
        this.mContentView.removeCallbacks(this.mTimeTask);
        this.mContentView.postDelayed(new Runnable() {
            public void run() {
                if (TpmsPopup.this.mCallback != null) {
                    TpmsPopup.this.mCallback.onLearningSuccess(pos);
                }
            }
        }, 2000);
    }

    public void allLearningSuccess() {
        this.mContentView.removeCallbacks(this.mTimeTask);
        this.mTvStudyNotice.setText(R.string.tpms_learning_success);
        this.mIvFirstIcon.setImageResource(R.drawable.tpms_num0_up);
        this.mIvSecondIcon.setImageResource(R.drawable.tpms_num0_up);
        this.mContentView.postDelayed(new Runnable() {
            public void run() {
                TpmsPopup.this.cancel();
            }
        }, 2000);
    }

    public void show(boolean showStudy) {
        if (this.mContentView.getParent() == null) {
            this.mWindowManager.addView(this.mContentView, this.mLayoutParams);
        }
        if (showStudy) {
            showStudyContainer(true);
            this.mCount = 30;
            this.mContentView.post(this.mTimeTask);
            return;
        }
        showStudyContainer(false);
    }

    public void dismiss() {
        if (isShowing()) {
            this.mWindowManager.removeView(this.mContentView);
        }
    }

    public boolean isShowing() {
        return (this.mContentView == null || this.mContentView.getParent() == null) ? false : true;
    }

    /* access modifiers changed from: private */
    public void updateTime() {
        if (this.mCount == 0) {
            this.mTvStudyNotice.setText(R.string.tpms_learning_failure);
            this.mIvFirstIcon.setImageResource(R.drawable.tpms_num0_up);
            this.mIvSecondIcon.setImageResource(R.drawable.tpms_num0_up);
            this.mContentView.postDelayed(this.mTimeTask, 2000);
        } else {
            this.mTvStudyNotice.setText(R.string.tpms_learning_start);
            this.mIvFirstIcon.setImageResource(mIconIds[this.mCount / 10]);
            this.mIvSecondIcon.setImageResource(mIconIds[this.mCount % 10]);
            this.mContentView.postDelayed(this.mTimeTask, 1000);
        }
        this.mCount--;
    }

    private void showStudyContainer(boolean show) {
        int i;
        int i2 = 0;
        View view = this.mContainerSwitch;
        if (show) {
            i = 8;
        } else {
            i = 0;
        }
        view.setVisibility(i);
        View view2 = this.mContainerStudy;
        if (!show) {
            i2 = 8;
        }
        view2.setVisibility(i2);
    }

    @SuppressLint({"NewApi"})
    private Drawable getStateDrawable(int up, int dn) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842913}, this.mContext.getDrawable(dn));
        stateListDrawable.addState(new int[]{16842919}, this.mContext.getDrawable(dn));
        stateListDrawable.addState(new int[0], this.mContext.getDrawable(up));
        return stateListDrawable;
    }

    /* access modifiers changed from: private */
    public void cancel() {
        if (this.mCallback != null) {
            this.mCallback.onCancel();
        }
        dismiss();
    }

    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_ok) {
            if (this.mCallback != null) {
                this.mCallback.onOk(this.mIndex, this.mData);
            }
            dismiss();
        } else if (id == R.id.btn_cancel) {
            cancel();
        }
    }
}
