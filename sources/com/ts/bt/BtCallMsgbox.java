package com.ts.bt;

import android.bluetooth.BluetoothHeadsetClientCall;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.ts.MainUI.R;
import com.ts.main.common.MainUI;
import com.yyw.ts70xhw.KeyDef;
import java.util.List;
import java.util.Map;

public class BtCallMsgbox implements DialogInterface.OnDismissListener, DialogInterface.OnCancelListener, View.OnClickListener {
    protected static final int BTN_CALL = 16;
    protected static final int BTN_HANG = 18;
    protected static final int BTN_KB = 17;
    private static final long CALL_HOLD_TIME = 4000;
    public static final String TAG = "BtCallMsgbox";
    protected static BtCallMsgbox mInstance;
    private Button answerButton;
    BtExe bt = new BtExe();
    private Button hangButton;
    private Button kbButton;
    Button mBtnAdd;
    Button mBtnAnswer;
    Button mBtnExchange;
    Button mBtnHang;
    Button mBtnKb;
    Button mBtnMerge;
    protected Context mContext;
    private RelativeLayout mFloatLayout = null;
    private long mHoldBegin;
    protected boolean mIsShow;
    private int mLastBtSta = 0;
    LinearLayout mLv;
    LinearLayout mLv1;
    LinearLayout mLv2;
    private int mOldSta;
    Resources mRes;
    RelativeLayout mRv;
    RelativeLayout mRv1;
    private String mStrOldCallName;
    private String mStrOldNo;
    public String mStrSta = "";
    private TextView mTvName;
    private TextView mTvName1;
    private TextView mTvNo;
    private TextView mTvState;
    private TextView mTvState1;
    private boolean mbHold;
    private RelativeLayout v;
    protected WindowManager wManager;

    public static BtCallMsgbox GetInstance() {
        if (mInstance == null) {
            mInstance = new BtCallMsgbox();
        }
        return mInstance;
    }

    public void Hide() {
        Show(0);
    }

    public void Init(Context context) {
        this.mContext = context;
        Destroy();
        this.mRes = this.mContext.getResources();
        if (BtExe.isCallback) {
            initViewSupportMeeting();
        } else {
            initView();
        }
        Hide();
    }

    /* access modifiers changed from: package-private */
    public void initView() {
        this.v = (RelativeLayout) ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R.layout.bt_msgbox, (ViewGroup) null);
        InitWinManager(this.mContext, this.v, KeyDef.RKEY_MEDIA_TITLE, 45, 667, 177);
        this.mTvName = (TextView) this.v.findViewById(R.id.bt_calling_name);
        this.mTvNo = (TextView) this.v.findViewById(R.id.bt_calling_no);
        this.mTvState = (TextView) this.v.findViewById(R.id.bt_calling_state);
        this.mTvName1 = (TextView) this.v.findViewById(R.id.bt_calling_name1);
        this.mTvState1 = (TextView) this.v.findViewById(R.id.bt_calling_state1);
        this.mLv = (LinearLayout) this.v.findViewById(R.id.bt_call_lv);
        this.mLv1 = (LinearLayout) this.v.findViewById(R.id.bt_call_lv1);
        this.mLv2 = (LinearLayout) this.v.findViewById(R.id.bt_call_lv2);
        this.hangButton = (Button) this.v.findViewById(R.id.btn_calling_hang);
        this.answerButton = (Button) this.v.findViewById(R.id.btn_calling_answer);
        this.kbButton = (Button) this.v.findViewById(R.id.btn_calling_kb);
        this.hangButton.setOnClickListener(this);
        this.answerButton.setOnClickListener(this);
        this.kbButton.setOnClickListener(this);
        UpdateLtIcon(0);
    }

    /* access modifiers changed from: package-private */
    public void initViewSupportMeeting() {
        this.v = (RelativeLayout) ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R.layout.bt_msgbox_s, (ViewGroup) null);
        InitWinManager(this.mContext, this.v, KeyDef.RKEY_MEDIA_SUBT, 45, 660, Can.CAN_TEANA_OLD_DJ);
        this.mTvName = (TextView) this.v.findViewById(R.id.bt_calling_name);
        this.mTvNo = (TextView) this.v.findViewById(R.id.bt_calling_no);
        this.mTvState = (TextView) this.v.findViewById(R.id.bt_calling_state);
        this.mTvName1 = (TextView) this.v.findViewById(R.id.bt_calling_name1);
        this.mTvState1 = (TextView) this.v.findViewById(R.id.bt_calling_state1);
        this.mLv = (LinearLayout) this.v.findViewById(R.id.bt_call_lv);
        this.mLv1 = (LinearLayout) this.v.findViewById(R.id.bt_call_lv1);
        this.mLv2 = (LinearLayout) this.v.findViewById(R.id.bt_call_lv2);
        this.hangButton = (Button) this.v.findViewById(R.id.btn_calling_hang);
        this.answerButton = (Button) this.v.findViewById(R.id.btn_calling_answer);
        this.kbButton = (Button) this.v.findViewById(R.id.btn_calling_kb);
        this.hangButton.setOnClickListener(this);
        this.answerButton.setOnClickListener(this);
        this.kbButton.setOnClickListener(this);
        this.mRv = (RelativeLayout) this.v.findViewById(R.id.bt_rv);
        this.mRv1 = (RelativeLayout) this.v.findViewById(R.id.bt_rv1);
        this.mBtnAnswer = (Button) this.v.findViewById(R.id.btn_calling_answer_s);
        this.mBtnHang = (Button) this.v.findViewById(R.id.btn_calling_hang_s);
        this.mBtnKb = (Button) this.v.findViewById(R.id.btn_calling_kb_s);
        this.mBtnExchange = (Button) this.v.findViewById(R.id.btn_calling_exchange);
        this.mBtnMerge = (Button) this.v.findViewById(R.id.btn_calling_merge);
        this.mBtnAdd = (Button) this.v.findViewById(R.id.btn_calling_add);
        this.mBtnAnswer.setOnClickListener(this);
        this.mBtnHang.setOnClickListener(this);
        this.mBtnKb.setOnClickListener(this);
        this.mBtnExchange.setOnClickListener(this);
        this.mBtnMerge.setOnClickListener(this);
        this.mBtnAdd.setOnClickListener(this);
        UpdateLtIcon(0);
    }

    public void Destroy() {
        if (this.mFloatLayout != null) {
            this.wManager.removeView(this.mFloatLayout);
            this.mFloatLayout = null;
        }
    }

    public void Show(int val) {
        if (this.mFloatLayout == null) {
            return;
        }
        if (val != 0) {
            this.mFloatLayout.setVisibility(0);
            SetShowFlg(true);
            this.mLastBtSta = BtExe.getBtInstance().getSta();
            return;
        }
        this.mFloatLayout.setVisibility(4);
        SetShowFlg(false);
    }

    public void onDismiss(DialogInterface dialog) {
    }

    public void onCancel(DialogInterface dialog) {
    }

    public void InitWinManager(Context context, RelativeLayout v2, int x, int y, int w, int h) {
        WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();
        this.wManager = (WindowManager) context.getSystemService("window");
        wmParams.type = 2010;
        wmParams.format = 1;
        wmParams.flags = 8;
        wmParams.gravity = 51;
        wmParams.x = x;
        wmParams.y = y;
        wmParams.width = w;
        wmParams.height = h;
        this.wManager.addView(v2, wmParams);
        this.mFloatLayout = v2;
    }

    public void create(Context context) {
        if (this.mFloatLayout == null && context != null) {
            Init(context);
        }
    }

    public void onClick(View v2) {
        int id = v2.getId();
        if (id == R.id.btn_calling_hang || id == R.id.btn_calling_hang_s) {
            this.bt.hangup();
        } else if (id == R.id.btn_calling_answer || id == R.id.btn_calling_answer_s) {
            this.bt.answer();
        } else if (id == R.id.btn_calling_exchange) {
            if (BtExe.getBtInstance().isMultiCall()) {
                this.bt.answer(1);
            }
        } else if (id == R.id.btn_calling_merge) {
            if (BtExe.getBtInstance().isMultiCall()) {
                this.bt.answer();
            }
        } else if (id == R.id.btn_calling_kb || id == R.id.btn_calling_kb_s) {
            if (MainUI.IsCameraMode() == 0) {
                BtExe.getBtInstance();
                Context context = BtExe.getContext();
                Intent intent = new Intent();
                intent.setClass(context, BtDialActivity.class);
                intent.addFlags(268500992);
                context.startActivity(intent);
                BtExe.isHideDialog = true;
                Hide();
                Log.d(TAG, "BTN_KB");
            }
        } else if (id == R.id.btn_calling_add && MainUI.IsCameraMode() == 0) {
            BtExe.getBtInstance();
            Context context2 = BtExe.getContext();
            Intent intent2 = new Intent();
            intent2.setClass(context2, BtDialActivity.class);
            intent2.addFlags(268500992);
            context2.startActivity(intent2);
            BtExe.isHideDialog = true;
            BtExe.isAddCall = true;
            Hide();
            Log.d(TAG, "BTN_KB");
        }
    }

    /* access modifiers changed from: package-private */
    public void showView(View view, boolean isShow) {
        if (view == null) {
            return;
        }
        if (isShow) {
            view.setVisibility(0);
        } else {
            view.setVisibility(8);
        }
    }

    /* access modifiers changed from: protected */
    public void UpdateLtIcon(int sta) {
        boolean z;
        boolean z2 = true;
        Button button = this.answerButton;
        if (sta == 3) {
            z = true;
        } else {
            z = false;
        }
        showView(button, z);
        Button button2 = this.kbButton;
        if (sta == 3) {
            z2 = false;
        }
        showView(button2, z2);
    }

    /* access modifiers changed from: protected */
    public void UpdateCallingUI() {
        int size = BtExe.getBtInstance().mCallMap.size();
        boolean isPhoneMeeting = BtExe.getBtInstance().isPhoneMeeting();
        int i = 0;
        for (Map.Entry<String, PhoneCall> element : BtExe.getBtInstance().mCallMap.entrySet()) {
            String strKey = element.getKey();
            PhoneCall strValue = element.getValue();
            String strName = strValue.getCallName();
            int state = strValue.getCallState();
            String mStrState = "";
            if (state == 0) {
                long second = strValue.getCallActiveSecond();
                mStrState = String.format("%02d:%02d:%02d", new Object[]{Long.valueOf(second / 3600), Long.valueOf((second / 60) % 60), Long.valueOf(second % 60)});
            } else if (state == 1) {
                mStrState = this.mRes.getString(R.string.str_bt_held);
            } else if (state == 2 || state == 3) {
                mStrState = this.mRes.getString(R.string.str_bt_call_out);
            } else if (state == 4 || state == 5) {
                mStrState = this.mRes.getString(R.string.str_bt_call_in);
            } else if (state == 5) {
                mStrState = this.mRes.getString(R.string.str_bt_waiting);
            } else if (state == 7) {
                mStrState = this.mRes.getString(R.string.str_bt_call_end);
            }
            if (state == 4 || state == 5) {
                showView(this.answerButton, true);
                showView(this.kbButton, false);
            } else {
                showView(this.answerButton, false);
                showView(this.kbButton, true);
            }
            if (BtExe.isCallback) {
                if (state == 4 || state == 5) {
                    showView(this.mBtnAnswer, true);
                    showView(this.mBtnKb, false);
                } else {
                    showView(this.mBtnAnswer, false);
                    showView(this.mBtnKb, true);
                }
            }
            if (isPhoneMeeting) {
                if (size > 2) {
                    if (i == 0) {
                        this.mTvName.setText(this.mRes.getString(R.string.str_bt_phone_meeting));
                        this.mTvState.setText(mStrState);
                    } else if (i == size - 1) {
                        if (!TextUtils.isEmpty(strName)) {
                            this.mTvName1.setText(strName);
                        } else {
                            this.mTvName1.setText(strKey);
                        }
                        this.mTvState1.setText(mStrState);
                    }
                } else if (i == 0) {
                    this.mTvName.setText(this.mRes.getString(R.string.str_bt_phone_meeting));
                    this.mTvState.setText(mStrState);
                }
            } else if (i == 0) {
                if (!TextUtils.isEmpty(strName)) {
                    this.mTvName.setText(strName);
                } else {
                    this.mTvName.setText(strKey);
                }
                this.mTvState.setText(mStrState);
            } else if (i == 1) {
                if (!TextUtils.isEmpty(strName)) {
                    this.mTvName1.setText(strName);
                } else {
                    this.mTvName1.setText(strKey);
                }
                this.mTvState1.setText(mStrState);
            }
            i++;
        }
        if (size <= 1) {
            this.mLv2.setVisibility(8);
        } else if (!isPhoneMeeting) {
            this.mLv2.setVisibility(0);
        } else if (size == 2) {
            this.mLv2.setVisibility(8);
        } else {
            this.mLv2.setVisibility(0);
        }
        if (BtExe.isCallback) {
            if (BtExe.getBtInstance().isMultiCall()) {
                this.mBtnExchange.setSelected(false);
                this.mBtnMerge.setSelected(false);
            } else {
                this.mBtnExchange.setSelected(true);
                this.mBtnMerge.setSelected(true);
            }
            if (BtExe.getBtInstance().isActive()) {
                showView(this.mRv, false);
                showView(this.mRv1, true);
            } else {
                showView(this.mRv, true);
                showView(this.mRv1, false);
            }
        }
        List<BluetoothHeadsetClientCall> callList = BtExe.getCurrentCalls();
        if (callList == null || callList.size() == 0) {
            Hide();
        } else if (callList != null && callList.size() > 0) {
            Show(1);
        }
    }

    private void SetShowFlg(boolean show) {
        if (this.mIsShow != show) {
            this.mIsShow = show;
            Log.d(TAG, "SetShowFlg = " + show);
            ResetSta();
        }
    }

    /* access modifiers changed from: protected */
    public void ResetSta() {
        this.mOldSta = 0;
        this.mStrOldNo = BtExe.UNKOWN_PHONE_NUMBER;
        this.mbHold = false;
        this.mStrOldCallName = "unkonw";
    }

    public void OnTimer(Context context) {
        if (this.mFloatLayout != null) {
            if (this.mIsShow) {
                UpdateCallingUI();
            }
            if (BtExe.isHideDialog) {
                return;
            }
            if (BtExe.isShowBox) {
                Show(1);
            } else {
                Show(0);
            }
        } else if (context != null) {
            Init(context);
        }
    }
}
