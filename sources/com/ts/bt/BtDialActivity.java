package com.ts.bt;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.main.common.MainSet;
import com.ts.main.common.MainVolume;

@SuppressLint({"NewApi"})
public class BtDialActivity extends BtBaseActivity implements View.OnClickListener, View.OnLongClickListener, UserCallBack {
    public static final int BT_ACTIVITY_ID = 2;
    public static final boolean DEBUG = true;
    private static final String TAG = "BTDialPadActivity";
    View.OnClickListener NumClickListen = new View.OnClickListener() {
        public void onClick(View v) {
            String strKey = "";
            int id = v.getId();
            if (id == R.id.bt_btn_dial_num1) {
                strKey = MainSet.SP_XPH5;
            } else if (id == R.id.bt_btn_dial_num2) {
                strKey = MainSet.SP_RLF_KORON;
            } else if (id == R.id.bt_btn_dial_num3) {
                strKey = MainSet.SP_XH_DMAX;
            } else if (id == R.id.bt_btn_dial_num4) {
                strKey = MainSet.SP_KS_QOROS;
            } else if (id == R.id.bt_btn_dial_num5) {
                strKey = MainSet.SP_LM_WR;
            } else if (id == R.id.bt_btn_dial_num6) {
                strKey = MainSet.SP_YSJ_QP;
            } else if (id == R.id.bt_btn_dial_num7) {
                strKey = MainSet.SP_TW_CJW;
            } else if (id == R.id.bt_btn_dial_num8) {
                strKey = MainSet.SP_FLKJ;
            } else if (id == R.id.bt_btn_dial_num9) {
                strKey = MainSet.SP_FXCARPLAY;
            } else if (id == R.id.bt_btn_dial_numx) {
                strKey = "*";
            } else if (id == R.id.bt_btn_dial_num0) {
                if (!BtDialActivity.this.mbLongTouch) {
                    strKey = "0";
                }
            } else if (id == R.id.bt_btn_dial_numj) {
                strKey = "#";
            }
            if (!strKey.isEmpty()) {
                BtDialActivity.this.addKey(strKey);
            }
            BtDialActivity.this.mbSubFocus = 2;
            BtDialActivity.this.updateFocus(v);
        }
    };
    private ImageButton btnCall;
    private ImageButton btnDel;
    private ImageButton btnExchange;
    private ImageButton btnHangup;
    private ImageButton btnMerge;
    private ImageButton btnMicMute;
    private ImageButton btnNum0;
    private ImageButton btnNum1;
    private ImageButton btnNum2;
    private ImageButton btnNum3;
    private ImageButton btnNum4;
    private ImageButton btnNum5;
    private ImageButton btnNum6;
    private ImageButton btnNum7;
    private ImageButton btnNum8;
    private ImageButton btnNum9;
    private ImageButton btnNumJ;
    private ImageButton btnNumX;
    private ImageButton btnSW;
    private ImageButton btnSound;
    private int mLength;
    protected int mOldSta;
    /* access modifiers changed from: private */
    public boolean mbLongTouch;
    private TextView tvInput;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bt_dial);
        this.mLength = getResources().getInteger(R.integer.bt_dial_input_length);
        initView();
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        SubItemsInit(this, 2);
        if (this.isShowActivity) {
            EnterSubFocus();
        }
        updateInputText();
        this.mOldSta = BtExe.mCallSta;
        MainTask.GetInstance().SetUserCallBack(this);
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public boolean onLongClick(View view) {
        if (view.getId() != R.id.bt_btn_dial_bkspace) {
            return false;
        }
        mBaseStrDialNo = "";
        updateInputText();
        this.mbSubFocus = 2;
        updateFocus(view);
        return true;
    }

    private void initView() {
        this.tvInput = (TextView) findViewById(R.id.bt_tv_dial_input);
        this.btnNum1 = (ImageButton) findViewById(R.id.bt_btn_dial_num1);
        this.btnNum2 = (ImageButton) findViewById(R.id.bt_btn_dial_num2);
        this.btnNum3 = (ImageButton) findViewById(R.id.bt_btn_dial_num3);
        this.btnNum4 = (ImageButton) findViewById(R.id.bt_btn_dial_num4);
        this.btnNum5 = (ImageButton) findViewById(R.id.bt_btn_dial_num5);
        this.btnNum6 = (ImageButton) findViewById(R.id.bt_btn_dial_num6);
        this.btnNum7 = (ImageButton) findViewById(R.id.bt_btn_dial_num7);
        this.btnNum8 = (ImageButton) findViewById(R.id.bt_btn_dial_num8);
        this.btnNum9 = (ImageButton) findViewById(R.id.bt_btn_dial_num9);
        this.btnNumX = (ImageButton) findViewById(R.id.bt_btn_dial_numx);
        this.btnNum0 = (ImageButton) findViewById(R.id.bt_btn_dial_num0);
        this.btnNumJ = (ImageButton) findViewById(R.id.bt_btn_dial_numj);
        this.btnNum1.setOnClickListener(this.NumClickListen);
        this.btnNum2.setOnClickListener(this.NumClickListen);
        this.btnNum3.setOnClickListener(this.NumClickListen);
        this.btnNum4.setOnClickListener(this.NumClickListen);
        this.btnNum5.setOnClickListener(this.NumClickListen);
        this.btnNum6.setOnClickListener(this.NumClickListen);
        this.btnNum7.setOnClickListener(this.NumClickListen);
        this.btnNum8.setOnClickListener(this.NumClickListen);
        this.btnNum9.setOnClickListener(this.NumClickListen);
        this.btnNumX.setOnClickListener(this.NumClickListen);
        this.btnNum0.setOnClickListener(this.NumClickListen);
        this.btnNumJ.setOnClickListener(this.NumClickListen);
        this.btnNum0.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                BtDialActivity.this.addKey("+");
                BtDialActivity.this.mbLongTouch = true;
                return false;
            }
        });
        this.btnNum0.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent ev) {
                switch (ev.getAction()) {
                    case 0:
                        BtDialActivity.this.mbLongTouch = false;
                        break;
                }
                return false;
            }
        });
        this.btnCall = (ImageButton) findViewById(R.id.bt_btn_dial_call);
        this.btnHangup = (ImageButton) findViewById(R.id.bt_btn_dial_end);
        this.btnSW = (ImageButton) findViewById(R.id.bt_btn_dial_sw);
        this.btnMicMute = (ImageButton) findViewById(R.id.bt_btn_dial_mute);
        this.btnDel = (ImageButton) findViewById(R.id.bt_btn_dial_bkspace);
        this.btnSound = (ImageButton) findViewById(R.id.bt_btn_dial_sound);
        this.btnMerge = (ImageButton) findViewById(R.id.bt_btn_dial_merge);
        this.btnExchange = (ImageButton) findViewById(R.id.bt_btn_dial_exchange);
        this.btnCall.setOnClickListener(this);
        this.btnDel.setOnClickListener(this);
        this.btnDel.setOnLongClickListener(this);
        this.btnHangup.setOnClickListener(this);
        this.btnMicMute.setOnClickListener(this);
        this.btnSound.setOnClickListener(this);
        this.btnSW.setOnClickListener(this);
        this.btnMerge.setOnClickListener(this);
        this.btnExchange.setOnClickListener(this);
        this.mFocusView = new View[20];
        this.mFocusView[0] = this.btnNum1;
        this.mFocusView[1] = this.btnNum2;
        this.mFocusView[2] = this.btnNum3;
        this.mFocusView[3] = this.btnDel;
        this.mFocusView[4] = this.btnNum4;
        this.mFocusView[5] = this.btnNum5;
        this.mFocusView[6] = this.btnNum6;
        this.mFocusView[7] = this.btnMicMute;
        this.mFocusView[8] = this.btnNum7;
        this.mFocusView[9] = this.btnNum8;
        this.mFocusView[10] = this.btnNum9;
        this.mFocusView[11] = this.btnSound;
        this.mFocusView[12] = this.btnNumJ;
        this.mFocusView[13] = this.btnNum0;
        this.mFocusView[14] = this.btnNumX;
        this.mFocusView[15] = this.btnSW;
        this.mFocusView[16] = this.btnMerge;
        this.mFocusView[17] = this.btnExchange;
        this.mFocusView[18] = this.btnCall;
        this.mFocusView[19] = this.btnHangup;
        if (this.mbSubFocus == 2) {
            updateFocus(this.mFocusView[0]);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateAudioFocus() {
        if (this.bt.getAudioState() == 2) {
            this.btnSW.setBackgroundResource(R.drawable.bt_audio_bt);
        } else {
            this.btnSW.setBackgroundResource(R.drawable.bt_audio_phone);
        }
    }

    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.bt_btn_dial_bkspace) {
            if (mBaseStrDialNo.length() <= 1) {
                mBaseStrDialNo = "";
            } else {
                mBaseStrDialNo = mBaseStrDialNo.substring(0, mBaseStrDialNo.length() - 1);
            }
            updateInputText();
        } else if (id == R.id.bt_btn_dial_mute) {
            this.bt.micMute();
        } else if (id == R.id.bt_btn_dial_sw) {
            if (this.bt.getCallValue() == 0) {
                this.bt.audioSwitch();
                updateAudioFocus();
            }
        } else if (id == R.id.bt_btn_dial_call) {
            if (BtExe.mCallSta > 1) {
                if (3 == BtExe.mCallSta) {
                    this.bt.answer();
                } else {
                    BtExe.isHideDialog = false;
                    if (BtExe.isAddCall) {
                        BtExe.isAddCall = false;
                        if (!TextUtils.isEmpty(mBaseStrDialNo)) {
                            this.bt.dial(mBaseStrDialNo);
                            mBaseStrDialNo = "";
                        } else {
                            BtCallMsgbox.GetInstance().Show(1);
                        }
                    } else {
                        BtCallMsgbox.GetInstance().Show(1);
                    }
                }
            } else if (mBaseStrDialNo != null && !mBaseStrDialNo.isEmpty()) {
                this.bt.dial(mBaseStrDialNo);
                mBaseStrDialNo = "";
            } else if (BtExe.mLastPhoneNo != null && !BtExe.mLastPhoneNo.isEmpty()) {
                BtExe.getBtInstance().updateLastPhoneNum();
                mBaseStrDialNo = BtExe.mLastPhoneNo;
                updateInputText();
            }
        } else if (id == R.id.bt_btn_dial_end) {
            clearInput();
            this.bt.hangup();
        } else if (id == R.id.bt_btn_dial_sound) {
            MainVolume.GetInstance().VolWinShow();
        } else if (id == R.id.bt_btn_dial_merge) {
            this.bt.answer(0);
        } else if (id == R.id.bt_btn_dial_exchange) {
            this.bt.answer(1);
        }
        this.mbSubFocus = 2;
        updateFocus(v);
    }

    /* access modifiers changed from: package-private */
    public void addKey(String strKey) {
        if (!BtExe.isAddCall && 4 == this.bt.getSta()) {
            this.bt.sendDTMFCode((byte) strKey.charAt(0));
        }
        inputAdd(strKey);
        updateInputText();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    /* access modifiers changed from: package-private */
    public void clearInput() {
        mBaseStrDialNo = "";
        updateInputText();
    }

    /* access modifiers changed from: package-private */
    public void inputAdd(String c) {
        mBaseStrDialNo = String.valueOf(mBaseStrDialNo) + c;
        updateInputText();
    }

    /* access modifiers changed from: package-private */
    public void updateInputText() {
        int len = mBaseStrDialNo.length();
        if (len < this.mLength) {
            this.tvInput.setText(mBaseStrDialNo);
        } else {
            this.tvInput.setText(mBaseStrDialNo.substring(len - this.mLength));
        }
    }

    public void UserAll() {
        if (this.mOldSta != BtExe.mCallSta) {
            this.mOldSta = BtExe.mCallSta;
            updateInputText();
        }
        if (this.bt.isHaveCall()) {
            updateAudioFocus();
        }
        this.bt.isConnected();
    }

    /* access modifiers changed from: protected */
    public int GetDialFocusIndex() {
        if (this.mOldFocusView == null) {
            return 0;
        }
        for (int j = 0; j < this.mFocusView.length; j++) {
            if (this.mOldFocusView == this.mFocusView[j]) {
                return j;
            }
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public void EnterSubFocus() {
        this.mbSubFocus = 2;
        updateFocus(this.mFocusView[0]);
    }

    /* access modifiers changed from: protected */
    public void DialPrev() {
        int index;
        int index2 = GetDialFocusIndex();
        if (index2 > 0) {
            index = index2 - 1;
        } else {
            index = this.mFocusView.length - 1;
        }
        Log.d(TAG, "GetDialFocusIndex LtPrev = " + index);
        updateFocus(this.mFocusView[index]);
    }

    /* access modifiers changed from: protected */
    public void DialNext() {
        int index;
        int index2 = GetDialFocusIndex();
        if (index2 < this.mFocusView.length - 1) {
            index = index2 + 1;
        } else {
            index = 0;
        }
        Log.d(TAG, "GetDialFocusIndex LtNext = " + index);
        updateFocus(this.mFocusView[index]);
    }

    /* access modifiers changed from: protected */
    public void DialCenter() {
        this.mFocusView[GetDialFocusIndex()].performClick();
    }

    /* access modifiers changed from: protected */
    public boolean DealSubKey(int key) {
        switch (key) {
            case 19:
                DialPrev();
                return true;
            case 20:
                DialNext();
                return true;
            case 21:
                if (this.mbSubFocus != 2) {
                    return true;
                }
                updateFocus(this.mFocusView[0]);
                return true;
            case 23:
                DialCenter();
                return true;
            default:
                return true;
        }
    }
}
