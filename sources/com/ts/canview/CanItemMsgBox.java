package com.ts.canview;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.ts.MainUI.R;
import com.ts.other.CustomDialog;
import com.ts.other.ParamButton;

public class CanItemMsgBox extends CustomDialog implements View.OnClickListener {
    public static final int ITEM_CANCEL = 2;
    public static final int ITEM_OK = 1;
    private ParamButton mBtnCancel;
    private ParamButton mBtnOK;
    protected onMsgBoxClick mCallBack;
    protected onMsgBoxClick2 mCbCancel;
    private int mParam;
    private TextView mVal;

    public interface onMsgBoxClick {
        void onOK(int i);
    }

    public interface onMsgBoxClick2 {
        void onCancel(int i);
    }

    public CanItemMsgBox(int param, Context context, int text, onMsgBoxClick callBack) {
        Create(param, context, text, callBack);
    }

    public CanItemMsgBox(int param, Context context, int text, onMsgBoxClick callBack, onMsgBoxClick2 cbCancel) {
        Create(param, context, text, callBack);
        SetCancelCallBack(cbCancel);
    }

    public void Create(int param, Context context, int text, onMsgBoxClick callBack) {
        super.create(R.layout.can_msg_box, context);
        this.mCallBack = callBack;
        this.mParam = param;
        this.mBtnOK = (ParamButton) this.mWindow.findViewById(R.id.box_ok);
        this.mBtnOK.setTag(1);
        this.mBtnOK.setStateUpDn(R.drawable.can_box_btn_up, R.drawable.can_box_btn_dn);
        this.mBtnOK.setColorUpDn(-16724737, -1);
        this.mBtnOK.setText(R.string.can_confirm);
        this.mBtnOK.setOnClickListener(this);
        this.mBtnCancel = (ParamButton) this.mWindow.findViewById(R.id.box_cancel);
        this.mBtnCancel.setTag(2);
        this.mBtnCancel.setStateUpDn(R.drawable.can_box_btn_up, R.drawable.can_box_btn_dn);
        this.mBtnCancel.setColorUpDn(-16724737, -1);
        this.mBtnCancel.setText(R.string.can_cancel);
        this.mBtnCancel.setOnClickListener(this);
        this.mVal = (TextView) this.mWindow.findViewById(R.id.box_text);
        if (text != 0) {
            this.mVal.setText(text);
        }
    }

    public void SetCancelCallBack(onMsgBoxClick2 cancel) {
        this.mCbCancel = cancel;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                dismiss();
                if (this.mCallBack != null) {
                    this.mCallBack.onOK(this.mParam);
                    return;
                }
                return;
            case 2:
                dismiss();
                if (this.mCbCancel != null) {
                    this.mCbCancel.onCancel(this.mParam);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
