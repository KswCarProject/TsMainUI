package com.ts.set.setview;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.ts.MainUI.R;
import com.ts.main.common.MainSet;
import com.txznet.sdk.TXZResourceManager;

public class SetMainItemNoIcon implements View.OnClickListener {
    private Context mContext;
    private TextView mTextTitle;
    private TextView mTextshowinfo;
    private View mView;
    /* access modifiers changed from: private */
    public View.OnClickListener mViewClickListener;

    public SetMainItemNoIcon(Context context, String strVal) {
        this.mContext = context;
        Create(context, strVal);
    }

    public View GetView() {
        return this.mView;
    }

    public View.OnClickListener getViewClickListener() {
        return this.mViewClickListener;
    }

    public void SetUserCallback(int nid, View.OnClickListener listener) {
        this.mView.setOnClickListener(this);
        this.mViewClickListener = listener;
        this.mView.setTag(Integer.valueOf(nid));
    }

    public void Create(Context context, String strVal) {
        Init(context);
        this.mTextTitle.setText(strVal);
    }

    public void ShowEndInfo(int nid) {
        this.mTextshowinfo.setText(nid);
    }

    public void ShowEndInfo(String strVal) {
        this.mTextshowinfo.setText(strVal);
    }

    public void SetArrawShow(boolean bShow) {
    }

    private void Init(Context context) {
        this.mView = LayoutInflater.from(context).inflate(R.layout.set_main_item_noicon, (ViewGroup) null);
        this.mTextTitle = (TextView) this.mView.findViewById(R.id.set_text);
        this.mTextshowinfo = (TextView) this.mView.findViewById(R.id.show_info);
        this.mTextshowinfo.setText(TXZResourceManager.STYLE_DEFAULT);
        ((ImageView) this.mView.findViewById(R.id.set_arrow)).setBackgroundResource(R.drawable.setup_arrow);
    }

    public void onClick(final View v) {
        if (MainSet.GetInstance().isNeedPassword(((Integer) this.mView.getTag()).intValue(), this.mContext)) {
            MainSet.GetInstance().showPasswordDialog(this.mContext, ((Integer) v.getTag()).intValue(), new MainSet.DealPasswordResult() {
                public void OnOK() {
                    SetMainItemNoIcon.this.mViewClickListener.onClick(v);
                }
            });
        } else {
            this.mViewClickListener.onClick(v);
        }
    }

    public void setEnabled(boolean isEnabled) {
        if (isEnabled) {
            this.mTextTitle.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            this.mView.setEnabled(true);
            return;
        }
        this.mTextTitle.setTextColor(-7829368);
        this.mView.setEnabled(false);
    }
}
