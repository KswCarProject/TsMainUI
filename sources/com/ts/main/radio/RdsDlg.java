package com.ts.main.radio;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.internal.view.SupportMenu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import com.ts.MainUI.R;
import com.ts.other.CustomDialog;
import com.ts.other.ParamButton;
import com.yyw.ts70xhw.Radio;

/* compiled from: RadioMainActivity */
class RdsDlg extends CustomDialog implements View.OnClickListener {
    private final int PTY_TOTAL = 30;
    private Button mBtnCancle;
    /* access modifiers changed from: private */
    public ParamButton[] mBtnList = new ParamButton[30];
    private Button mBtnOK;
    /* access modifiers changed from: private */
    public int mId = 0;
    private onInputOK mOK;
    private View.OnClickListener mPtyClick = new View.OnClickListener() {
        public void onClick(View v) {
            int id = ((ParamButton) v).getIntParam();
            if (id != RdsDlg.this.mId) {
                RdsDlg.this.mBtnList[RdsDlg.this.mId].setSelected(false);
                RdsDlg.this.mBtnList[id].setSelected(true);
                RdsDlg.this.mId = id;
            }
        }
    };

    /* compiled from: RadioMainActivity */
    public interface onInputOK {
        void onOK(int i);
    }

    public RdsDlg() {
    }

    public RdsDlg(Context context, onInputOK ok) {
        createDlg(context, ok);
    }

    public void createDlg(Context context, onInputOK ok) {
        this.mId = 0;
        super.create(R.layout.radio_rds_inputbox, context);
        this.mBtnOK = (Button) this.mWindow.findViewById(R.id.rad_rds_input_ok);
        this.mBtnCancle = (Button) this.mWindow.findViewById(R.id.rad_rds_input_cancle);
        this.mBtnOK.setOnClickListener(this);
        this.mBtnCancle.setOnClickListener(this);
        this.mOK = ok;
        LinearLayout ll = (LinearLayout) this.mWindow.findViewById(R.id.rad_rds_list);
        char[] szPty = new char[64];
        for (int i = 0; i < 30; i++) {
            this.mBtnList[i] = new ParamButton(context);
            this.mBtnList[i].setLayoutParams(new LinearLayout.LayoutParams(-1, 55));
            this.mBtnList[i].setText(new StringBuilder(String.valueOf(i)).toString());
            this.mBtnList[i].setIntParam(i);
            this.mBtnList[i].setBackground((Drawable) null);
            this.mBtnList[i].setPadding(0, 0, 0, 0);
            this.mBtnList[i].setStateColor(-16777216, SupportMenu.CATEGORY_MASK, SupportMenu.CATEGORY_MASK, 0);
            this.mBtnList[i].setTextSize(0, 30.0f);
            this.mBtnList[i].setOnClickListener(this.mPtyClick);
            if (i > 0) {
                Radio.GetPtyStr(szPty, i);
                this.mBtnList[i].setText(String.valueOf(szPty));
            } else {
                this.mBtnList[0].setSelected(true);
                this.mBtnList[0].setText("NO PTY");
            }
            ll.addView(this.mBtnList[i]);
        }
    }

    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.rad_rds_input_ok) {
            dismiss();
            Radio.RdsPty(this.mId);
        } else if (id == R.id.rad_rds_input_cancle) {
            dismiss();
        }
    }
}
