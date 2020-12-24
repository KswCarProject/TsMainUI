package com.ts.set.setview;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.ts.MainUI.R;
import com.ts.other.CustomDialog;

public class SetItemDialog extends CustomDialog {
    public static final int BTN_CANCEL = 17;
    public static final int BTN_ENTER = 16;
    private Button mBtnCancle;
    private Button mBtnOK = ((Button) this.mWindow.findViewById(R.id.setting_general_btn_ok));
    private TextView mTextTitle;

    public SetItemDialog(Context context, int texid, View.OnClickListener listense) {
        super.create(R.layout.setting_general01_input, context);
        this.mBtnOK.setText(R.string.set_general_enter);
        this.mBtnOK.setTag(16);
        this.mBtnCancle = (Button) this.mWindow.findViewById(R.id.setting_general_btn_cancel);
        this.mBtnCancle.setText(R.string.set_general_cancel);
        this.mBtnCancle.setTag(17);
        this.mTextTitle = (TextView) this.mWindow.findViewById(R.id.setting_general_showinfo);
        this.mTextTitle.setText(texid);
        this.mBtnOK.setOnClickListener(listense);
        this.mBtnCancle.setOnClickListener(listense);
    }

    public void Hide() {
        dismiss();
    }

    public TextView GetTitle() {
        return this.mTextTitle;
    }
}
