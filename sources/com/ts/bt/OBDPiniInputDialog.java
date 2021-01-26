package com.ts.bt;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.ts.MainUI.R;
import com.ts.main.common.MainSet;
import com.txznet.sdk.TXZResourceManager;

public class OBDPiniInputDialog extends ObdCustomDialog implements View.OnClickListener {
    private static final String TAG = "OBDPiniInputDialog";
    protected long hour;
    private Button mBtnCancle;
    private int[] mBtnNumId = {R.id.dvd_goto_btn_num7, R.id.dvd_goto_btn_num8, R.id.dvd_goto_btn_num9, R.id.dvd_goto_btn_close, R.id.dvd_goto_btn_num4, R.id.dvd_goto_btn_num5, R.id.dvd_goto_btn_num6, R.id.dvd_goto_btn_ok, R.id.dvd_goto_btn_num1, R.id.dvd_goto_btn_num2, R.id.dvd_goto_btn_num3, R.id.dvd_goto_btn_num0, R.id.dvd_goto_btn_delete};
    private Button mBtnOK;
    private Button mBtnSearch;
    private Button[] mBtns;
    private int mCurLen = 0;
    private boolean mIsFocus = false;
    protected boolean mIsList = false;
    private ImageView mIvState;
    private int mMaxInputLen = 4;
    private onInputOK mOK;
    private View mOldFocusView;
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
        initView(context, ok, inputLen, false);
    }

    public void createDlg(Context context, onInputOK ok, int inputLen, boolean isFocus) {
        initView(context, ok, inputLen, isFocus);
    }

    /* access modifiers changed from: package-private */
    public void initView(Context context, onInputOK ok, int inputLen, boolean isFocus) {
        this.mIsFocus = isFocus;
        this.mMaxInputLen = inputLen;
        this.mStrInputText = TXZResourceManager.STYLE_DEFAULT;
        super.create(R.layout.obd_numinput, context);
        this.mTvInput = (TextView) this.mWindow.findViewById(R.id.dvd_goto_edx_num);
        int length = this.mBtnNumId.length;
        this.mBtns = new Button[length];
        for (int i = 0; i < length; i++) {
            this.mBtns[i] = (Button) this.mWindow.findViewById(this.mBtnNumId[i]);
            this.mBtns[i].setOnClickListener(this);
        }
        this.mOK = ok;
        if (isFocus) {
            updateFocus(this.mBtns[0]);
            getDlg().setOnKeyListener(new DialogInterface.OnKeyListener() {
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    int action = event.getAction();
                    int keycode = event.getKeyCode();
                    Log.d(OBDPiniInputDialog.TAG, "onKey: action = " + action);
                    Log.d(OBDPiniInputDialog.TAG, "onKey: keycode = " + keycode);
                    if (action == 1) {
                        if (keycode == 20) {
                            OBDPiniInputDialog.this.obdNext();
                        } else if (keycode == 19) {
                            OBDPiniInputDialog.this.obdPrev();
                        } else if (keycode == 23) {
                            OBDPiniInputDialog.this.obdCenter();
                        }
                    }
                    return true;
                }
            });
        }
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
        Log.d(TAG, "onClick id = " + id);
        if (this.mIsFocus) {
            updateFocus(v);
        }
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

    public void updateFocus(View v) {
        if (this.mOldFocusView != null) {
            this.mOldFocusView.setFocusableInTouchMode(false);
        }
        v.setFocusableInTouchMode(true);
        v.requestFocus();
        this.mOldFocusView = v;
    }

    /* access modifiers changed from: protected */
    public void obdPrev() {
        int index;
        int index2 = getObdFocusIndex();
        if (index2 > 0) {
            index = index2 - 1;
        } else {
            index = this.mBtns.length - 1;
        }
        Log.d(TAG, "obdPrev = " + index);
        updateFocus(this.mBtns[index]);
    }

    /* access modifiers changed from: protected */
    public void obdNext() {
        int index;
        int index2 = getObdFocusIndex();
        if (index2 < this.mBtns.length - 1) {
            index = index2 + 1;
        } else {
            index = 0;
        }
        Log.d(TAG, "obdNext = " + index);
        updateFocus(this.mBtns[index]);
    }

    /* access modifiers changed from: protected */
    public void obdCenter() {
        int index = getObdFocusIndex();
        Log.d(TAG, "obdCenter = " + index);
        this.mBtns[index].performClick();
    }

    /* access modifiers changed from: protected */
    public int getObdFocusIndex() {
        int ret = 0;
        if (this.mOldFocusView != null) {
            int j = 0;
            while (true) {
                if (j >= this.mBtns.length) {
                    break;
                } else if (this.mOldFocusView == this.mBtns[j]) {
                    ret = j;
                    break;
                } else {
                    j++;
                }
            }
        }
        Log.d(TAG, "getObdFocusIndex = " + ret);
        return ret;
    }
}
