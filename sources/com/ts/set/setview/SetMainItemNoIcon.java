package com.ts.set.setview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.ts.MainUI.R;

public class SetMainItemNoIcon {
    private TextView mTextTitle;
    private TextView mTextshowinfo;
    private View mView;

    public SetMainItemNoIcon(Context context, String strVal) {
        Create(context, strVal);
    }

    public View GetView() {
        return this.mView;
    }

    public void SetUserCallback(int nid, View.OnClickListener listener) {
        this.mView.setOnClickListener(listener);
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
        this.mTextshowinfo.setText("");
        ((ImageView) this.mView.findViewById(R.id.set_arrow)).setBackgroundResource(R.drawable.setup_arrow);
    }
}
