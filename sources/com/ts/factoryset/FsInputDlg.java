package com.ts.factoryset;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.ts.MainUI.R;
import com.ts.other.CustomDialog;

public class FsInputDlg extends CustomDialog implements View.OnClickListener {
    private Button mBtnCancle;
    private Button mBtnOK;
    private EditText mEditVal;
    private onInputOK mOK;

    public interface onInputOK {
        void onOK(String str);
    }

    public FsInputDlg() {
    }

    public FsInputDlg(Context context, onInputOK ok, boolean isnumber) {
        createDlg(context, ok, isnumber);
    }

    public void createDlg(Context context, onInputOK ok, boolean isnumber) {
        super.create(R.layout.fs_mp_inputbox, context);
        this.mWindow.setSoftInputMode(5);
        this.mEditVal = (EditText) this.mWindow.findViewById(R.id.fsmp_input_val);
        if (!isnumber) {
            this.mEditVal.setInputType(1);
        } else {
            this.mEditVal.setInputType(2);
        }
        this.mBtnOK = (Button) this.mWindow.findViewById(R.id.fsmp_ok);
        this.mBtnCancle = (Button) this.mWindow.findViewById(R.id.fsmp_cancel);
        this.mBtnOK.setOnClickListener(this);
        this.mBtnCancle.setOnClickListener(this);
        this.mOK = ok;
    }

    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.fsmp_ok) {
            dismiss();
            if (this.mOK != null) {
                this.mOK.onOK(this.mEditVal.getText().toString());
            }
        } else if (id == R.id.fsmp_cancel) {
            dismiss();
        }
    }
}
