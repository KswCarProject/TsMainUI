package com.ts.can.btobd;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.ts.MainUI.R;
import com.ts.main.common.MainSet;
import com.txznet.sdk.TXZResourceManager;

public class OBDPiniInputDialog extends CustomDialog implements View.OnClickListener {
    protected long hour;
    private Button mBtnCancle;
    private int[] mBtnNumId = {R.id.dvd_goto_btn_num0, R.id.dvd_goto_btn_num1, R.id.dvd_goto_btn_num2, R.id.dvd_goto_btn_num3, R.id.dvd_goto_btn_num4, R.id.dvd_goto_btn_num5, R.id.dvd_goto_btn_num6, R.id.dvd_goto_btn_num7, R.id.dvd_goto_btn_num8, R.id.dvd_goto_btn_num9, R.id.dvd_goto_btn_delete, R.id.dvd_goto_btn_close, R.id.dvd_goto_btn_ok};
    private Button mBtnOK;
    private Button mBtnSearch;
    private int mCurLen = 0;
    protected boolean mIsList = false;
    private ImageView mIvState;
    private int mMaxInputLen = 4;
    private onInputOK mOK;
    private String mStrInputText = new String();
    protected int mTotalCount;
    protected int mTotalTime;
    private TextView mTvInput;
    private TextView mTvTotal;
    protected long min;
    protected long second;
    protected long total;

    public interface onInputOK {
        void onOK(String str);
    }

    public boolean getState() {
        return this.mIsList;
    }

    public void updateState(int state, int totalCount, int totalTime) {
        this.mTotalCount = totalCount;
        this.mTotalTime = totalTime / 1000;
    }

    public OBDPiniInputDialog() {
    }

    public OBDPiniInputDialog(Context context, onInputOK ok, int inputLen) {
        createDlg(context, ok, inputLen);
    }

    public void createBootDlg(Context context, onInputOK ok, int inputLen) {
        createDlg(context, ok, inputLen);
    }

    public Button getSearch() {
        return this.mBtnSearch;
    }

    public void createDlg(Context context, onInputOK ok, int inputLen) {
        this.mMaxInputLen = inputLen;
        this.mStrInputText = TXZResourceManager.STYLE_DEFAULT;
        super.create(R.layout.obd_numinput, context);
        this.mTvInput = (TextView) this.mWindow.findViewById(R.id.dvd_goto_edx_num);
        for (int findViewById : this.mBtnNumId) {
            ((Button) this.mWindow.findViewById(findViewById)).setOnClickListener(this);
        }
        this.mOK = ok;
    }

    /* access modifiers changed from: package-private */
    public void addStr(String str) {
        if (this.mCurLen < this.mMaxInputLen) {
            this.mStrInputText = String.valueOf(this.mStrInputText) + str;
            this.mCurLen++;
            this.mTvInput.setText(this.mStrInputText);
        }
    }

    /* access modifiers changed from: package-private */
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
        if (id == R.id.dvd_goto_btn_num0) {
            addStr("0");
        } else if (id == R.id.dvd_goto_btn_num1) {
            addStr("1");
        } else if (id == R.id.dvd_goto_btn_num2) {
            addStr("2");
        } else if (id == R.id.dvd_goto_btn_num3) {
            addStr("3");
        } else if (id == R.id.dvd_goto_btn_num4) {
            addStr(MainSet.SP_KS_QOROS);
        } else if (id == R.id.dvd_goto_btn_num5) {
            addStr(MainSet.SP_TW_CJW);
        } else if (id == R.id.dvd_goto_btn_num6) {
            addStr(MainSet.SP_XS_DZ);
        } else if (id == R.id.dvd_goto_btn_num7) {
            addStr(MainSet.SP_PCBA_VOL);
        } else if (id == R.id.dvd_goto_btn_num8) {
            addStr("8");
        } else if (id == R.id.dvd_goto_btn_num9) {
            addStr("9");
        } else if (id == R.id.dvd_goto_btn_close) {
            dismiss();
        } else if (id == R.id.dvd_goto_btn_delete) {
            decStr();
        } else if (id == R.id.dvd_goto_btn_ok) {
            if (this.mOK != null && this.mStrInputText.length() > 0) {
                this.mOK.onOK(this.mStrInputText);
            }
            dismiss();
        }
    }
}
