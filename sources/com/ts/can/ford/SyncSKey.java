package com.ts.can.ford;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.ts.MainUI.R;
import com.ts.other.CustomImgView;
import com.ts.other.ParamButton;
import com.txznet.sdk.TXZResourceManager;

/* compiled from: CanFordSyncUIActivity */
class SyncSKey {
    private ParamButton mBtn = ((ParamButton) this.mView.findViewById(R.id.sync_skey_btn));
    private CustomImgView mIco;
    private View mView;

    public SyncSKey(Context context, int id, View.OnClickListener l) {
        this.mView = LayoutInflater.from(context).inflate(R.layout.can_item_sync_softkey, (ViewGroup) null);
        this.mBtn.setDrawable(R.drawable.can_sync_txt_up, R.drawable.can_sync_txt_dn);
        this.mBtn.setOnClickListener(l);
        this.mIco = (CustomImgView) this.mView.findViewById(R.id.sync_skey_ico);
        this.mBtn.setTag(Integer.valueOf(id));
        this.mBtn.setPadding(0, 0, 0, 0);
        this.mBtn.setSingleLine();
    }

    public void SetSel(int sel) {
        this.mBtn.setSelected(sel != 0);
    }

    public void SetTextIco(String txt, int ico) {
        SetIco(ico);
        if (ico != 0) {
            SetText(TXZResourceManager.STYLE_DEFAULT);
        } else {
            SetText(txt);
        }
    }

    public void SetText(String txt) {
        this.mBtn.setText(txt);
    }

    public void SetIco(int ico) {
        this.mIco.setImageResource(ico);
    }

    public View GetView() {
        return this.mView;
    }

    public void SetEnable(boolean enable) {
        this.mBtn.setEnabled(enable);
    }

    public void Show(int val) {
        if (val != 0) {
            this.mView.setVisibility(0);
        } else {
            this.mView.setVisibility(4);
        }
    }

    public void Show(boolean show) {
        if (show) {
            this.mView.setVisibility(0);
        } else {
            this.mView.setVisibility(4);
        }
    }

    public void SetTextSize(int size) {
        this.mBtn.setTextSize(0, (float) size);
    }

    public void SetIconCenter() {
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) this.mIco.getLayoutParams();
        lp.addRule(13);
        this.mIco.setLayoutParams(lp);
    }
}
