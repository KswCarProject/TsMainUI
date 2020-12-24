package com.ts.set.setview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.ts.MainUI.R;

public class SetMainItemTopName {
    public Button iv;
    private TextView mTextTitle;
    private View mView;

    public SetMainItemTopName(Context context, String strVal) {
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

    public void SetArrawShow(boolean bShow) {
    }

    private void Init(Context context) {
        this.mView = LayoutInflater.from(context).inflate(R.layout.set_main_item_top, (ViewGroup) null);
        this.mTextTitle = (TextView) this.mView.findViewById(R.id.set_top_text);
        this.iv = (Button) this.mView.findViewById(R.id.back);
        this.iv.setOnClickListener((View.OnClickListener) null);
    }
}
