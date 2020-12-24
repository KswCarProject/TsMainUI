package com.ts.set.setview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.ts.MainUI.R;

public class SetMainItem {
    ImageView ImageTitle;
    ImageView ImageTitle2;
    private TextView mTextInfo;
    private TextView mTextTitle;
    private View mView;

    public SetMainItem(Context context, String strVal) {
        Create(context, strVal);
    }

    public View GetView() {
        return this.mView;
    }

    public void Create(Context context, String strVal) {
        Init(context);
        this.mTextTitle.setText(strVal);
    }

    public ImageView GetImageTile() {
        return this.ImageTitle;
    }

    public TextView Gettextinfo() {
        return this.mTextInfo;
    }

    public void SetUserCallback(int nid, View.OnClickListener listener) {
        this.mView.setOnClickListener(listener);
        this.mView.setTag(Integer.valueOf(nid));
    }

    private void Init(Context context) {
        this.mView = LayoutInflater.from(context).inflate(R.layout.del_main_item, (ViewGroup) null);
        this.mTextTitle = (TextView) this.mView.findViewById(R.id.set_text);
        this.ImageTitle = (ImageView) this.mView.findViewById(R.id.set_icon);
        this.ImageTitle2 = (ImageView) this.mView.findViewById(R.id.set_arrow);
        this.ImageTitle2.setBackgroundResource(R.drawable.del_arrow);
    }
}
