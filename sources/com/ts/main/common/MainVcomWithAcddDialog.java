package com.ts.main.common;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.other.CustomDialog;
import com.yyw.ts70xhw.FtSet;

public class MainVcomWithAcddDialog extends CustomDialog implements CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener, View.OnClickListener {
    Switch mAvddSwitch;
    TextView mAvddTxt;
    Button mBtnVcomDn;
    Button mBtnVcomOk;
    Button mBtnVcomUp;
    private int mHeight = 442;
    LinearLayout mLayout;
    private int mProgress;
    Button mShowBtn;
    SeekBar mVcomSeekBar;
    private int mWidth = CanCameraUI.BTN_CCH9_MODE10;

    public MainVcomWithAcddDialog() {
    }

    public MainVcomWithAcddDialog(Context context, int vcom, int avdd) {
        createDlg(context, vcom, avdd);
    }

    public void createDlg(Context context, int vcom, int avdd) {
        super.create(R.layout.vcom_with_avdd_layout, context);
        this.mWindow.setBackgroundDrawableResource(17170445);
        this.mWindow.setLayout(this.mWidth, this.mHeight);
        this.mWindow.setGravity(17);
        this.mLayout = (LinearLayout) findViewById(R.id.layout);
        this.mShowBtn = (Button) findViewById(R.id.vcom_show_btn);
        this.mShowBtn.setText(new StringBuilder(String.valueOf(vcom)).toString());
        setShowBtn(vcom);
        this.mVcomSeekBar = (SeekBar) findViewById(R.id.vcom_seekbar);
        this.mVcomSeekBar.setProgress(vcom);
        this.mVcomSeekBar.setOnSeekBarChangeListener(this);
        this.mAvddSwitch = (Switch) findViewById(R.id.avdd_switch_ctrl);
        this.mBtnVcomUp = (Button) findViewById(R.id.btn_vcom_up);
        this.mBtnVcomDn = (Button) findViewById(R.id.btn_vcom_dn);
        this.mBtnVcomOk = (Button) findViewById(R.id.btn_vcom_ok);
        this.mBtnVcomUp.setOnClickListener(this);
        this.mBtnVcomDn.setOnClickListener(this);
        this.mBtnVcomOk.setOnClickListener(this);
        if (avdd == 0) {
            this.mAvddSwitch.setChecked(false);
        } else {
            this.mAvddSwitch.setChecked(true);
        }
        this.mAvddSwitch.setOnCheckedChangeListener(this);
        this.mAvddTxt = (TextView) this.mWindow.findViewById(R.id.avdd_txt);
        this.mAvddTxt.setText(new StringBuilder(String.valueOf(avdd)).toString());
    }

    /* access modifiers changed from: package-private */
    public void setShowBtn(int progress) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(72, 50);
        params.leftMargin = (int) (164.0f + (331.0f * (Float.valueOf((float) progress).floatValue() / 255.0f)));
        params.topMargin = 28;
        this.mShowBtn.setLayoutParams(params);
        this.mLayout.invalidate();
    }

    public void onCheckedChanged(CompoundButton view, boolean checked) {
        if (checked) {
            FtSet.SetLCDavdd(1);
            this.mAvddTxt.setText(MainSet.SP_XPH5);
            return;
        }
        FtSet.SetLCDavdd(0);
        this.mAvddTxt.setText("0");
    }

    public void onProgressChanged(SeekBar seekBar, int progress, boolean user) {
        Log.d("lh", "progress = " + progress);
        this.mShowBtn.setText(new StringBuilder(String.valueOf(progress)).toString());
        setShowBtn(progress);
        this.mProgress = progress;
        if (!user) {
            FtSet.SetLCDvcom(progress);
        }
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        this.mShowBtn.setText(new StringBuilder(String.valueOf(this.mProgress)).toString());
        setShowBtn(this.mProgress);
        FtSet.SetLCDvcom(this.mProgress);
    }

    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_vcom_up) {
            subProgress();
        } else if (id == R.id.btn_vcom_dn) {
            addProgress();
        } else if (id == R.id.btn_vcom_ok) {
            FtSet.Save(0);
            dismiss();
        }
    }

    /* access modifiers changed from: package-private */
    public void subProgress() {
        int progress = this.mVcomSeekBar.getProgress() - 1;
        if (progress >= 0) {
            this.mVcomSeekBar.setProgress(progress);
        }
    }

    /* access modifiers changed from: package-private */
    public void addProgress() {
        int progress = this.mVcomSeekBar.getProgress() + 1;
        if (progress <= this.mVcomSeekBar.getMax()) {
            this.mVcomSeekBar.setProgress(progress);
        }
    }
}
