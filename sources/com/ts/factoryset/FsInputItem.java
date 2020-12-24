package com.ts.factoryset;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ts.MainUI.R;
import com.ts.other.ParamButton;

/* compiled from: FsOtherActivity */
class FsInputItem {
    protected ParamButton mBtnVal;
    protected View mView;

    public FsInputItem(ViewGroup parent, int id, String strTitle, View.OnClickListener l) {
        Create(parent, id, strTitle, l);
    }

    public FsInputItem(ViewGroup parent, int id, int title, View.OnClickListener l) {
        Create(parent, id, parent.getResources().getString(title), l);
    }

    private void Create(ViewGroup parent, int id, String strTitle, View.OnClickListener l) {
        this.mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fs_btname_item, (ViewGroup) null);
        parent.addView(this.mView);
        ((TextView) this.mView.findViewById(R.id.fsbt_title)).setText(strTitle);
        this.mBtnVal = (ParamButton) this.mView.findViewById(R.id.fsbt_name);
        this.mBtnVal.setStateDrawable(R.drawable.factory_radio_up, R.drawable.factory_radio_dn, R.drawable.factory_radio_dn);
        this.mBtnVal.setOnClickListener(l);
        this.mBtnVal.setIntParam(id);
        this.mBtnVal.setColorUpDn(-16777216, -16777216);
    }

    public void SetVal(String val) {
        this.mBtnVal.setText(val);
    }
}
