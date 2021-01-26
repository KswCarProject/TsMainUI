package com.autochips.camera;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.ts.MainUI.R;
import com.ts.backcar.AutoFitTextureView;
import com.ts.backcar.TsDvrService;

public class MIPIDVRActivity extends Activity implements View.OnClickListener {
    private static final int MSG_CHECK_RECORD = 100;
    private static final int[] mBtnIds = {R.id.btn_start_record, R.id.btn_take_photo, R.id.btn_display_list, R.id.btn_switch_mode};
    private static final int[] mDnIds = {R.drawable.ic_btn_recording_dn, R.drawable.ic_btn_photograph_dn, R.drawable.ic_btn_list_dn, R.drawable.ic_btn_splitview_dn};
    private static final int[] mUpIds = {R.drawable.ic_btn_recording_up, R.drawable.ic_btn_photograph_up, R.drawable.ic_btn_list_up, R.drawable.ic_btn_splitview_up};
    private boolean isBackRecordStarted;
    private boolean isFrontRecordStarted;
    private View mBackContainer;
    private Runnable mBackTask = new Runnable() {
        public void run() {
            if (MIPIDVRActivity.this.mIvBackRecording.getVisibility() == 0) {
                MIPIDVRActivity.this.mIvBackRecording.setVisibility(8);
            } else {
                MIPIDVRActivity.this.mIvBackRecording.setVisibility(0);
            }
            MIPIDVRActivity.this.mHandler.postDelayed(this, 500);
        }
    };
    private AutoFitTextureView mBackTextureView;
    private ImageView[] mButtons = new ImageView[4];
    private View mCameraContainer;
    private View mFrontContainer;
    private Runnable mFrontTask = new Runnable() {
        public void run() {
            if (MIPIDVRActivity.this.mIvFrontRecording.getVisibility() == 0) {
                MIPIDVRActivity.this.mIvFrontRecording.setVisibility(8);
            } else {
                MIPIDVRActivity.this.mIvFrontRecording.setVisibility(0);
            }
            MIPIDVRActivity.this.mHandler.postDelayed(this, 500);
        }
    };
    private AutoFitTextureView mFrontTextureView;
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            MIPIDVRActivity.this.showRecordingIcon();
            MIPIDVRActivity.this.mHandler.sendEmptyMessageDelayed(100, 1000);
        }
    };
    /* access modifiers changed from: private */
    public ImageView mIvBackRecording;
    /* access modifiers changed from: private */
    public ImageView mIvFrontRecording;
    private View mLineView;

    /* access modifiers changed from: private */
    public void showRecordingIcon() {
        boolean isFrontStarted = TsDvrService.getInstance().IsRecordStarted("9");
        if (isFrontStarted != this.isFrontRecordStarted) {
            if (isFrontStarted) {
                this.mHandler.post(this.mFrontTask);
            } else {
                this.mHandler.removeCallbacks(this.mFrontTask);
                this.mIvFrontRecording.setVisibility(8);
            }
            this.isFrontRecordStarted = isFrontStarted;
        }
        boolean isBackStarted = TsDvrService.getInstance().IsRecordStarted("11");
        if (isBackStarted != this.isBackRecordStarted) {
            if (isBackStarted) {
                this.mHandler.post(this.mBackTask);
            } else {
                this.mHandler.removeCallbacks(this.mBackTask);
                this.mIvBackRecording.setVisibility(8);
            }
            this.isBackRecordStarted = isBackStarted;
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mipidvr);
        TsDvrService.getInstance().Init(this);
        initViews();
    }

    private void initViews() {
        for (int i = 0; i < mBtnIds.length; i++) {
            this.mButtons[i] = (ImageView) findViewById(mBtnIds[i]);
            this.mButtons[i].setImageDrawable(getStateDrawable(mUpIds[i], mDnIds[i]));
            this.mButtons[i].setOnClickListener(this);
        }
        this.mLineView = findViewById(R.id.line_view);
        this.mCameraContainer = findViewById(R.id.camera_container);
        this.mFrontContainer = findViewById(R.id.front_camera_container);
        this.mBackContainer = findViewById(R.id.back_camera_container);
        this.mIvFrontRecording = (ImageView) findViewById(R.id.iv_recording_front);
        this.mIvBackRecording = (ImageView) findViewById(R.id.iv_recording_back);
        this.mFrontTextureView = (AutoFitTextureView) findViewById(R.id.tv_front_camera);
        this.mBackTextureView = (AutoFitTextureView) findViewById(R.id.tv_back_camera);
        this.mFrontTextureView.setOnClickListener(this);
        this.mBackTextureView.setOnClickListener(this);
    }

    @SuppressLint({"NewApi"})
    private Drawable getStateDrawable(int up, int dn) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842919}, getDrawable(dn));
        stateListDrawable.addState(new int[0], getDrawable(up));
        return stateListDrawable;
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        this.mHandler.sendEmptyMessage(100);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        TsDvrService.getInstance().StartCam("9", this.mFrontTextureView);
        TsDvrService.getInstance().StartCam("11", this.mBackTextureView);
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        TsDvrService.getInstance().StopCam("9");
        TsDvrService.getInstance().StopCam("11");
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        this.mHandler.removeMessages(100);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.mHandler.removeCallbacksAndMessages((Object) null);
        this.mHandler = null;
    }

    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_start_record) {
            if (TsDvrService.getInstance().IsRecordStarted("9") || TsDvrService.getInstance().IsRecordStarted("11")) {
                TsDvrService.getInstance().StopRecord("9");
                TsDvrService.getInstance().StopRecord("11");
                return;
            }
            TsDvrService.getInstance().StartRecord("9", false);
            TsDvrService.getInstance().StartRecord("11", false);
        } else if (id == R.id.btn_take_photo) {
            TsDvrService.getInstance().SnapShot("9");
            TsDvrService.getInstance().SnapShot("11");
        } else if (id == R.id.btn_display_list) {
            startActivity(new Intent(this, MIPIDVRDisplayActivity.class));
        } else if (id == R.id.btn_switch_mode) {
            showDoubleCamera();
        } else if (id == R.id.tv_front_camera) {
            showFullCamera(true);
        } else if (id == R.id.tv_back_camera) {
            showFullCamera(false);
        }
    }

    private void showFullCamera(boolean isFrontCamera) {
        updateCameraContainerLayoutParams(true);
        this.mLineView.setVisibility(8);
        if (isFrontCamera) {
            this.mBackContainer.setVisibility(8);
            this.mButtons[this.mButtons.length - 1].setVisibility(0);
            return;
        }
        this.mFrontContainer.setVisibility(8);
        this.mButtons[this.mButtons.length - 1].setVisibility(0);
    }

    private void showDoubleCamera() {
        updateCameraContainerLayoutParams(false);
        this.mFrontContainer.setVisibility(0);
        this.mBackContainer.setVisibility(0);
        this.mLineView.setVisibility(0);
        this.mButtons[this.mButtons.length - 1].setVisibility(8);
        this.mButtons[this.mButtons.length - 1].setVisibility(8);
    }

    private void updateCameraContainerLayoutParams(boolean isFullCamera) {
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) this.mCameraContainer.getLayoutParams();
        if (isFullCamera) {
            lp.removeRule(2);
        } else {
            lp.addRule(2, R.id.btn_containers);
        }
        this.mCameraContainer.setLayoutParams(lp);
    }
}
