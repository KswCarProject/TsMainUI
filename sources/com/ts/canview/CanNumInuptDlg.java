package com.ts.canview;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.ts.MainUI.R;
import com.ts.main.common.MainSet;
import com.ts.other.CustomDialog;
import com.ts.other.ParamButton;
import com.txznet.sdk.TXZResourceManager;

public class CanNumInuptDlg extends CustomDialog implements View.OnClickListener {
    public static final String TAG = "CanNumInuptDlg";
    private int[] mBtnNumDn = {R.drawable.can_input_num0_dn, R.drawable.can_input_num1_dn, R.drawable.can_input_num2_dn, R.drawable.can_input_num3_dn, R.drawable.can_input_num4_dn, R.drawable.can_input_num5_dn, R.drawable.can_input_num6_dn, R.drawable.can_input_num7_dn, R.drawable.can_input_num8_dn, R.drawable.can_input_num9_dn, R.drawable.can_input_delete_dn, R.drawable.can_input_backspace_dn, R.drawable.can_input_ok_dn};
    private int[] mBtnNumId = {R.id.can_input_btn_num0, R.id.can_input_btn_num1, R.id.can_input_btn_num2, R.id.can_input_btn_num3, R.id.can_input_btn_num4, R.id.can_input_btn_num5, R.id.can_input_btn_num6, R.id.can_input_btn_num7, R.id.can_input_btn_num8, R.id.can_input_btn_num9, R.id.can_input_btn_delete, R.id.can_input_btn_backspace, R.id.can_input_btn_ok};
    private int[] mBtnNumUp = {R.drawable.can_input_num0_up, R.drawable.can_input_num1_up, R.drawable.can_input_num2_up, R.drawable.can_input_num3_up, R.drawable.can_input_num4_up, R.drawable.can_input_num5_up, R.drawable.can_input_num6_up, R.drawable.can_input_num7_up, R.drawable.can_input_num8_up, R.drawable.can_input_num9_up, R.drawable.can_input_delete_up, R.drawable.can_input_backspace_up, R.drawable.can_input_ok_up};
    private int mCurLen = 0;
    private int mId;
    private int mMaxInputLen = 4;
    private onInputOK mOK;
    private String mStrInputText = new String();
    private TextView mTvInput;

    public interface onInputOK {
        void onOK(String str, int i, int i2);
    }

    public CanNumInuptDlg() {
    }

    public CanNumInuptDlg(Context context, onInputOK ok, int inputLen, int id) {
        createDlg(context, ok, inputLen, id);
    }

    public void createDlg(Context context, onInputOK ok, int inputLen, int id) {
        this.mMaxInputLen = inputLen;
        this.mStrInputText = TXZResourceManager.STYLE_DEFAULT;
        super.create(R.layout.can_num_input_msgbox, context);
        this.mTvInput = (TextView) this.mWindow.findViewById(R.id.can_input_edx_num);
        for (int i = 0; i < this.mBtnNumId.length; i++) {
            ParamButton btn = (ParamButton) this.mWindow.findViewById(this.mBtnNumId[i]);
            btn.setOnClickListener(this);
            btn.setStateUpDn(this.mBtnNumUp[i], this.mBtnNumDn[i]);
        }
        this.mOK = ok;
        this.mId = id;
    }

    /* access modifiers changed from: protected */
    public void addStr(String str) {
        if (this.mCurLen < this.mMaxInputLen) {
            this.mStrInputText = String.valueOf(this.mStrInputText) + str;
            this.mCurLen++;
            this.mTvInput.setText(this.mStrInputText);
        }
    }

    /* access modifiers changed from: protected */
    public void decStr() {
        if (this.mStrInputText.length() <= 1) {
            this.mStrInputText = TXZResourceManager.STYLE_DEFAULT;
        } else {
            this.mStrInputText = this.mStrInputText.substring(0, this.mStrInputText.length() - 1);
        }
        this.mCurLen = this.mStrInputText.length();
        this.mTvInput.setText(this.mStrInputText);
    }

    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.can_input_btn_num0) {
            addStr("0");
        } else if (id == R.id.can_input_btn_num1) {
            addStr("1");
        } else if (id == R.id.can_input_btn_num2) {
            addStr("2");
        } else if (id == R.id.can_input_btn_num3) {
            addStr("3");
        } else if (id == R.id.can_input_btn_num4) {
            addStr(MainSet.SP_KS_QOROS);
        } else if (id == R.id.can_input_btn_num5) {
            addStr(MainSet.SP_TW_CJW);
        } else if (id == R.id.can_input_btn_num6) {
            addStr(MainSet.SP_XS_DZ);
        } else if (id == R.id.can_input_btn_num7) {
            addStr(MainSet.SP_PCBA_VOL);
        } else if (id == R.id.can_input_btn_num8) {
            addStr("8");
        } else if (id == R.id.can_input_btn_num9) {
            addStr("9");
        } else if (id == R.id.can_input_btn_backspace) {
            decStr();
        } else if (id == R.id.can_input_btn_delete) {
            dismiss();
        } else if (id == R.id.can_input_btn_ok) {
            if (this.mOK != null && this.mStrInputText.length() > 0) {
                int num = Integer.parseInt(this.mStrInputText, 10);
                Log.d(TAG, "input num = " + num);
                this.mOK.onOK(this.mStrInputText, num, this.mId);
            }
            dismiss();
        }
    }
}
