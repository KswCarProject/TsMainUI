package com.txznet.record.setting;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.txznet.txz.comm.R;

/* compiled from: Proguard */
public class Ty extends Dialog {

    /* renamed from: T  reason: collision with root package name */
    Context f687T;
    private TextView T9;
    private Button Tn;
    /* access modifiers changed from: private */
    public EditText Tr;
    private Button Ty;

    protected Ty(Context context) {
        super(context, R.style.TXZ_Dialog_Style);
        this.f687T = context;
        Ty();
    }

    private void Ty() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.layout_edit, (ViewGroup) null);
        this.T9 = (TextView) mView.findViewById(R.id.txt_dialogTitle);
        this.Tr = (EditText) mView.findViewById(R.id.et_command);
        this.Ty = (Button) mView.findViewById(R.id.commit_editCommand);
        this.Tn = (Button) mView.findViewById(R.id.cancel_editCommand);
        mView.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        getWindow().setLayout(mView.getMeasuredWidth(), mView.getMeasuredHeight());
        this.Tr.addTextChangedListener(new TextWatcher() {
            private int Tn;
            private CharSequence Tr;
            private int Ty;

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                this.Tr = s;
                if (this.Tr.length() == 8) {
                    Toast.makeText(Ty.this.f687T, "亲，您已输入" + this.Tr.length() + "个字符", 0).show();
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
                this.Ty = Ty.this.Tr.getSelectionStart();
                this.Tn = Ty.this.Tr.getSelectionEnd();
                if (this.Tr.length() > 8) {
                    s.delete(this.Ty - 1, this.Tn);
                    int tempSelection = this.Ty;
                    Ty.this.Tr.setText(s.toString());
                    Ty.this.Tr.setSelection(tempSelection);
                }
            }
        });
        super.setContentView(mView);
    }

    public View T() {
        return this.Tr;
    }

    public View Tr() {
        return this.T9;
    }

    public void setContentView(int layoutResID) {
    }

    public void setContentView(View view) {
    }

    public void setContentView(View view, ViewGroup.LayoutParams params) {
    }

    public void T(View.OnClickListener listener) {
        this.Ty.setOnClickListener(listener);
    }

    public void Tr(View.OnClickListener listener) {
        this.Tn.setOnClickListener(listener);
    }
}
