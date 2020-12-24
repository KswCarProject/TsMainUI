package com.ts.can.ford.wc;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanRelativeCarInfoView;
import com.yyw.ts70xhw.KeyDef;

public class CanFordWcActiveParkView extends CanRelativeCarInfoView {
    private CanDataInfo.FordWcCarSetData mCarSet = new CanDataInfo.FordWcCarSetData();
    private AlertDialog mDialog;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            CanFordWcActiveParkView.this.updateProgress(CanFordWcActiveParkView.this.mProgressBar.getProgress() + 1);
        }
    };
    private ImageView mIvLeftArcLine;
    private ImageView mIvLeftArea;
    private ImageView mIvLeftArrow;
    private ImageView mIvLeftCar;
    private ImageView mIvLeftCarXie;
    private ImageView mIvLeftGou;
    private ImageView mIvLeftHalfCar;
    private ImageView mIvLeftLine;
    private ImageView mIvLeftScan;
    private ImageView mIvLeftWarnLine;
    private ImageView mIvRightArcLine;
    private ImageView mIvRightArea;
    private ImageView mIvRightArrow;
    private ImageView mIvRightCar;
    private ImageView mIvRightCarXie;
    private ImageView mIvRightGou;
    private ImageView mIvRightHalfCar;
    private ImageView mIvRightLine;
    private ImageView mIvRightScan;
    private ImageView mIvRightWarnLine;
    private ImageView mIvWarnning;
    private RelativeLayout mLeftParkLayout;
    /* access modifiers changed from: private */
    public ProgressBar mProgressBar;
    private RelativeLayout mRightParkLayout;
    private RelativeLayout mRoot;
    private String[] mStatusValues;
    private TextView mTvDialogStatus;
    private TextView mTvLeftStatus;
    private TextView mTvRightStatus;
    private TextView mTvTakeCare;
    private TextView mTvTime;

    public CanFordWcActiveParkView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View arg0) {
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mRoot = getRelativeManager().GetLayout();
        initLeftParkLayout();
        initRightParkLayout();
        this.mStatusValues = getActivity().getResources().getStringArray(R.array.can_ford_active_park);
    }

    private void initLeftParkLayout() {
        this.mLeftParkLayout = (RelativeLayout) View.inflate(getActivity(), R.layout.left_park_status_layout, (ViewGroup) null);
        this.mIvLeftArea = (ImageView) this.mLeftParkLayout.findViewById(R.id.iv_left_area_icon);
        this.mIvLeftScan = (ImageView) this.mLeftParkLayout.findViewById(R.id.iv_left_scan_icon);
        this.mIvLeftCar = (ImageView) this.mLeftParkLayout.findViewById(R.id.iv_left_car);
        this.mIvLeftCarXie = (ImageView) this.mLeftParkLayout.findViewById(R.id.iv_left_car_xie);
        this.mIvLeftHalfCar = (ImageView) this.mLeftParkLayout.findViewById(R.id.iv_left_half_car);
        this.mIvLeftGou = (ImageView) this.mLeftParkLayout.findViewById(R.id.iv_left_gou_icon);
        this.mIvLeftArrow = (ImageView) this.mLeftParkLayout.findViewById(R.id.iv_left_arrow_icon);
        this.mIvLeftArcLine = (ImageView) this.mLeftParkLayout.findViewById(R.id.iv_left_arc_line);
        this.mIvLeftLine = (ImageView) this.mLeftParkLayout.findViewById(R.id.iv_left_line);
        this.mIvLeftWarnLine = (ImageView) this.mLeftParkLayout.findViewById(R.id.iv_left_warnning_icon);
        this.mTvLeftStatus = (TextView) this.mLeftParkLayout.findViewById(R.id.tv_left_park_status);
    }

    private void initRightParkLayout() {
        this.mRightParkLayout = (RelativeLayout) View.inflate(getActivity(), R.layout.right_park_status_layout, (ViewGroup) null);
        this.mIvRightArea = (ImageView) this.mRightParkLayout.findViewById(R.id.iv_right_area_icon);
        this.mIvRightScan = (ImageView) this.mRightParkLayout.findViewById(R.id.iv_right_scan_icon);
        this.mIvRightCar = (ImageView) this.mRightParkLayout.findViewById(R.id.iv_right_car);
        this.mIvRightCarXie = (ImageView) this.mRightParkLayout.findViewById(R.id.iv_right_car_xie);
        this.mIvRightHalfCar = (ImageView) this.mRightParkLayout.findViewById(R.id.iv_right_half_car);
        this.mIvRightGou = (ImageView) this.mRightParkLayout.findViewById(R.id.iv_right_gou_icon);
        this.mIvRightArrow = (ImageView) this.mRightParkLayout.findViewById(R.id.iv_right_arrow_icon);
        this.mIvRightArcLine = (ImageView) this.mRightParkLayout.findViewById(R.id.iv_right_arc_line);
        this.mIvRightLine = (ImageView) this.mRightParkLayout.findViewById(R.id.iv_right_line);
        this.mIvRightWarnLine = (ImageView) this.mRightParkLayout.findViewById(R.id.iv_right_warnning_icon);
        this.mTvRightStatus = (TextView) this.mRightParkLayout.findViewById(R.id.tv_right_park_status);
    }

    public void ResetData(boolean check) {
        CanJni.FordWcGetCarSet(this.mCarSet);
        if (!i2b(this.mCarSet.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCarSet.Update)) {
            this.mCarSet.Update = 0;
            updateStatus(this.mCarSet.AutoPark);
        }
    }

    public void QueryData() {
    }

    private void updateStatus(int status) {
        this.mHandler.removeMessages(0);
        switch (status) {
            case 0:
            case 1:
                getActivity().finish();
                return;
            case 2:
                showDialog(this.mStatusValues[1], true);
                return;
            case 4:
                addLayout(false, 2);
                this.mIvRightArea.setImageResource(R.drawable.can_focus_park_rway);
                this.mIvRightCar.setVisibility(0);
                this.mIvRightScan.setVisibility(0);
                return;
            case 5:
                addLayout(true, 3);
                this.mIvLeftArea.setImageResource(R.drawable.can_focus_park_lway);
                this.mIvLeftCar.setVisibility(0);
                this.mIvLeftScan.setVisibility(0);
                return;
            case 6:
                showDialog(this.mStatusValues[4], false);
                return;
            case 7:
                addLayout(true, getString(R.string.can_left_found_spot));
                updateParkIcon(true, R.drawable.can_focus_park_sarrow, 300);
                this.mIvLeftArea.setImageResource(R.drawable.can_focus_park_lparking);
                this.mIvLeftCar.setVisibility(0);
                this.mIvLeftGou.setVisibility(0);
                this.mIvLeftArrow.setVisibility(4);
                return;
            case 8:
                addLayout(false, getString(R.string.can_right_found_spot));
                updateParkIcon(false, R.drawable.can_focus_park_sarrow, 300);
                this.mIvRightArea.setImageResource(R.drawable.can_focus_park_rparking);
                this.mIvRightCar.setVisibility(0);
                this.mIvRightGou.setVisibility(0);
                this.mIvRightArrow.setVisibility(4);
                return;
            case 9:
                addLayout(true, 5);
                updateParkIcon(true, R.drawable.can_focus_park_sarrow, 300);
                this.mIvLeftArea.setImageResource(R.drawable.can_focus_park_lparking);
                this.mIvLeftCar.setVisibility(0);
                this.mIvLeftGou.setVisibility(0);
                return;
            case 10:
                addLayout(false, 6);
                updateParkIcon(false, R.drawable.can_focus_park_sarrow, 300);
                this.mIvRightArea.setImageResource(R.drawable.can_focus_park_rparking);
                this.mIvRightCar.setVisibility(0);
                this.mIvRightGou.setVisibility(0);
                return;
            case 11:
            case 13:
                addLayout(true, status - 4);
                updateParkIcon(true, R.drawable.can_focus_park_stop, 300);
                this.mIvLeftArea.setImageResource(R.drawable.can_focus_park_lparking);
                this.mIvLeftHalfCar.setVisibility(0);
                return;
            case 12:
            case 14:
                addLayout(false, status - 4);
                updateParkIcon(false, R.drawable.can_focus_park_stop, 300);
                this.mIvRightArea.setImageResource(R.drawable.can_focus_park_rparking);
                this.mIvRightHalfCar.setVisibility(0);
                return;
            case 15:
            case 17:
                addLayout(true, 11);
                this.mIvLeftHalfCar.setVisibility(0);
                this.mIvLeftArcLine.setVisibility(0);
                this.mIvLeftArea.setImageResource(R.drawable.can_focus_park_lparking);
                return;
            case 16:
            case 18:
                addLayout(false, 12);
                this.mIvRightHalfCar.setVisibility(0);
                this.mIvRightArcLine.setVisibility(0);
                this.mIvRightArea.setImageResource(R.drawable.can_focus_park_rparking);
                return;
            case 19:
                addLayout(true, 13);
                updateCarXieParams(true, 40, 80);
                updateParkIcon(true, R.drawable.can_focus_park_stop, 240);
                updateWarnLineParams(true, 2, KeyDef.RKEY_AVIN);
                this.mIvLeftArea.setImageResource(R.drawable.can_focus_park_lparking);
                return;
            case 20:
                addLayout(false, 13);
                updateCarXieParams(false, 40, 80);
                updateParkIcon(false, R.drawable.can_focus_park_stop, 240);
                updateWarnLineParams(false, 2, KeyDef.RKEY_AVIN);
                this.mIvRightArea.setImageResource(R.drawable.can_focus_park_rparking);
                return;
            case 21:
            case 35:
                addLayout(true, 14);
                updateCarXieParams(true, 20, 100);
                updateLineParams(true, 60, 50);
                updateParkIcon(true, R.drawable.can_focus_park_sarrow, 240);
                updateWarnLineParams(true, 2, KeyDef.RKEY_AVIN);
                this.mIvLeftArea.setImageResource(R.drawable.can_focus_park_lparking);
                return;
            case 22:
            case 36:
                addLayout(false, 14);
                updateCarXieParams(false, 20, 100);
                updateLineParams(false, 60, 50);
                updateParkIcon(false, R.drawable.can_focus_park_sarrow, 240);
                updateWarnLineParams(false, 2, KeyDef.RKEY_AVIN);
                this.mIvRightArea.setImageResource(R.drawable.can_focus_park_rparking);
                return;
            case 23:
                addLayout(true, 15);
                updateCarXieParams(true, 40, 80);
                updateParkIcon(true, R.drawable.can_focus_park_stop, 240);
                updateWarnLineParams(true, 2, 50);
                this.mIvLeftArea.setImageResource(R.drawable.can_focus_park_lparking);
                return;
            case 24:
                addLayout(false, 15);
                updateCarXieParams(false, 40, 80);
                updateParkIcon(false, R.drawable.can_focus_park_stop, 240);
                updateWarnLineParams(false, 2, 50);
                this.mIvRightArea.setImageResource(R.drawable.can_focus_park_rparking);
                return;
            case 25:
            case 37:
                addLayout(true, 16);
                updateCarXieParams(true, 20, 80);
                updateLineParams(true, 60, 290);
                updateParkIcon(true, R.drawable.can_focus_park_xarrow, 240);
                this.mIvLeftArea.setImageResource(R.drawable.can_focus_park_lparking);
                return;
            case 26:
            case 38:
                addLayout(false, 16);
                updateCarXieParams(false, 20, 80);
                updateLineParams(false, 60, 290);
                updateParkIcon(false, R.drawable.can_focus_park_xarrow, 240);
                this.mIvRightArea.setImageResource(R.drawable.can_focus_park_rparking);
                return;
            case 27:
                showDialog(this.mStatusValues[17], true);
                return;
            case 28:
                showDialog(this.mStatusValues[18], true);
                updateProgress(0);
                return;
            case 29:
            case 39:
                showDialog(this.mStatusValues[19], false);
                return;
            case 30:
                showDialog(this.mStatusValues[20], false);
                return;
            case 31:
                showDialog(this.mStatusValues[21], false);
                return;
            case 32:
                showDialog(this.mStatusValues[22], false);
                return;
            case 33:
                showDialog(this.mStatusValues[23], false);
                return;
            case 34:
                showDialog(this.mStatusValues[24], false);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    public void updateProgress(int progress) {
        if (this.mProgressBar.getVisibility() != 0) {
            this.mIvWarnning.setImageResource(R.drawable.can_focus_park_auto);
            this.mProgressBar.setVisibility(0);
            this.mTvTime.setVisibility(0);
        }
        if (progress <= 10) {
            this.mTvTime.setText(String.valueOf(progress) + " s");
            this.mProgressBar.setProgress(progress);
            this.mHandler.removeMessages(0);
            this.mHandler.sendEmptyMessageDelayed(0, 1000);
            return;
        }
        this.mHandler.removeMessages(0);
    }

    private void updateWarnLineParams(boolean isLeft, int x, int y) {
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(-2, -2);
        if (isLeft) {
            lp.leftMargin = x;
            lp.topMargin = y;
            this.mIvLeftWarnLine.setLayoutParams(lp);
            this.mIvLeftWarnLine.setVisibility(0);
            return;
        }
        lp.rightMargin = x;
        lp.topMargin = y;
        lp.addRule(11);
        this.mIvRightWarnLine.setLayoutParams(lp);
        this.mIvRightWarnLine.setVisibility(0);
    }

    private void updateCarXieParams(boolean isLeft, int x, int y) {
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(-2, -2);
        if (isLeft) {
            lp.leftMargin = x;
            lp.topMargin = y;
            this.mIvLeftCarXie.setLayoutParams(lp);
            this.mIvLeftCarXie.setVisibility(0);
            return;
        }
        lp.rightMargin = x;
        lp.topMargin = y;
        lp.addRule(11);
        this.mIvRightCarXie.setLayoutParams(lp);
        this.mIvRightCarXie.setVisibility(0);
    }

    private void updateLineParams(boolean isLeft, int x, int y) {
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(-2, -2);
        if (isLeft) {
            lp.leftMargin = x;
            lp.topMargin = y;
            this.mIvLeftLine.setLayoutParams(lp);
            this.mIvLeftLine.setVisibility(0);
            return;
        }
        lp.rightMargin = x;
        lp.topMargin = y;
        lp.addRule(11);
        this.mIvRightLine.setLayoutParams(lp);
        this.mIvRightLine.setVisibility(0);
    }

    private void updateParkIcon(boolean isLeft, int resId, int x) {
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(-2, -2);
        if (isLeft) {
            lp.leftMargin = x;
            lp.addRule(15);
            this.mIvLeftArrow.setLayoutParams(lp);
            this.mIvLeftArrow.setVisibility(0);
            this.mIvLeftArrow.setImageResource(resId);
            return;
        }
        lp.rightMargin = x;
        lp.addRule(15);
        lp.addRule(11);
        this.mIvRightArrow.setLayoutParams(lp);
        this.mIvRightArrow.setVisibility(0);
        this.mIvRightArrow.setImageResource(resId);
    }

    private void showDialog(String text) {
        if (this.mDialog == null) {
            this.mDialog = new AlertDialog.Builder(getActivity()).show();
            this.mDialog.setContentView(R.layout.park_dialog_content);
            this.mDialog.setCanceledOnTouchOutside(false);
            this.mIvWarnning = (ImageView) this.mDialog.findViewById(R.id.iv_warnning);
            this.mTvTakeCare = (TextView) this.mDialog.findViewById(R.id.tv_take_care);
            this.mTvDialogStatus = (TextView) this.mDialog.findViewById(R.id.tv_status);
            this.mProgressBar = (ProgressBar) this.mDialog.findViewById(R.id.progress_bar);
            this.mTvTime = (TextView) this.mDialog.findViewById(R.id.tv_time);
        }
        if (!this.mDialog.isShowing()) {
            this.mDialog.show();
        }
        this.mIvWarnning.setImageResource(R.drawable.can_focus_park_warnning);
        this.mTvDialogStatus.setText(text);
        this.mProgressBar.setVisibility(4);
        this.mTvTime.setVisibility(4);
    }

    private void showDialog(String text, boolean hideTakeCare) {
        showDialog(text);
        this.mTvTakeCare.setVisibility(hideTakeCare ? 4 : 0);
    }

    private void addLayout(boolean isLeft, int status) {
        if (this.mDialog != null) {
            this.mDialog.dismiss();
        }
        this.mRoot.removeAllViews();
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(KeyDef.SKEY_CHUP_3, 374);
        lp.addRule(13);
        if (isLeft) {
            this.mRoot.addView(this.mLeftParkLayout, lp);
            this.mTvLeftStatus.setText(this.mStatusValues[status]);
        } else {
            this.mRoot.addView(this.mRightParkLayout, lp);
            this.mTvRightStatus.setText(this.mStatusValues[status]);
        }
        hideChildrens(isLeft);
    }

    private void addLayout(boolean isLeft, String text) {
        if (this.mDialog != null) {
            this.mDialog.dismiss();
        }
        this.mRoot.removeAllViews();
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(KeyDef.SKEY_CHUP_3, 374);
        lp.addRule(13);
        if (isLeft) {
            this.mRoot.addView(this.mLeftParkLayout, lp);
            this.mTvLeftStatus.setText(text);
        } else {
            this.mRoot.addView(this.mRightParkLayout, lp);
            this.mTvRightStatus.setText(text);
        }
        hideChildrens(isLeft);
    }

    private void hideChildrens(boolean isLeft) {
        if (isLeft) {
            this.mIvLeftHalfCar.setVisibility(8);
            this.mIvLeftCar.setVisibility(8);
            this.mIvLeftCarXie.setVisibility(8);
            this.mIvLeftScan.setVisibility(8);
            this.mIvLeftGou.setVisibility(8);
            this.mIvLeftArrow.setVisibility(8);
            this.mIvLeftArcLine.setVisibility(8);
            this.mIvLeftLine.setVisibility(8);
            this.mIvLeftWarnLine.setVisibility(8);
            return;
        }
        this.mIvRightHalfCar.setVisibility(8);
        this.mIvRightCar.setVisibility(8);
        this.mIvRightCarXie.setVisibility(8);
        this.mIvRightScan.setVisibility(8);
        this.mIvRightGou.setVisibility(8);
        this.mIvRightArrow.setVisibility(8);
        this.mIvRightArcLine.setVisibility(8);
        this.mIvRightLine.setVisibility(8);
        this.mIvRightWarnLine.setVisibility(8);
    }
}
