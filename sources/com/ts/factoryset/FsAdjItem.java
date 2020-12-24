package com.ts.factoryset;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ts.MainUI.R;
import com.ts.other.ParamButton;

/* compiled from: FsOtherActivity */
class FsAdjItem {
    protected String[] mStrText;
    protected TextView mText;
    protected TextView mVal;
    protected View mView;

    public FsAdjItem(ViewGroup parent, int id, String strTitle, String[] strText, View.OnClickListener l) {
        Create(parent, id, strTitle, strText, l);
    }

    public FsAdjItem(ViewGroup parent, int id, int title, int text, View.OnClickListener l) {
        Resources res = parent.getResources();
        Create(parent, id, res.getString(title), text > 1 ? res.getStringArray(text) : null, l);
    }

    private void Create(ViewGroup parent, int id, String strTitle, String[] strText, View.OnClickListener l) {
        this.mStrText = strText;
        this.mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fs_item_data_adj, (ViewGroup) null);
        parent.addView(this.mView);
        ((TextView) this.mView.findViewById(R.id.fsadj_title)).setText(strTitle);
        this.mVal = (TextView) this.mView.findViewById(R.id.fsadj_val);
        this.mText = (TextView) this.mView.findViewById(R.id.fsadj_comment);
        ParamButton btnCut = (ParamButton) this.mView.findViewById(R.id.fsadj_cut);
        btnCut.setIntParam(id);
        btnCut.setIntParam2(0);
        btnCut.setStateDrawable(R.drawable.factory_reduce_up, R.drawable.factory_reduce_dn, R.drawable.factory_reduce_dn);
        btnCut.setOnClickListener(l);
        ParamButton btnAdd = (ParamButton) this.mView.findViewById(R.id.fsadj_add);
        btnAdd.setIntParam(id);
        btnAdd.setIntParam2(1);
        btnAdd.setStateDrawable(R.drawable.factory_add_up, R.drawable.factory_add_dn, R.drawable.factory_add_dn);
        btnAdd.setOnClickListener(l);
    }

    public int GetMaxVal() {
        return this.mStrText.length;
    }

    public void SetVal(int val) {
        this.mVal.setText(new StringBuilder(String.valueOf(val)).toString());
        if (this.mStrText == null) {
            return;
        }
        if (val < 0 || val >= this.mStrText.length) {
            this.mText.setText("");
        } else {
            this.mText.setText(this.mStrText[val]);
        }
    }
}
