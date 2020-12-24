package com.ts.set.setview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.ts.MainUI.R;

public class SetMainMtemBtn {
    private Button mButton;
    private TextView mTextTitle;
    private View mView;

    public SetMainMtemBtn(Context context, String strVal) {
        Create(context, strVal);
    }

    public View GetView() {
        return this.mView;
    }

    public void Create(Context context, String strVal) {
        Init(context);
        this.mTextTitle.setText(strVal);
    }

    public void SetBtnFormat(int background) {
        this.mButton.setBackgroundResource(background);
    }

    public void SetBtnUserCallback(int nid, View.OnClickListener listener) {
        this.mButton.setOnClickListener(listener);
        this.mButton.setTag(Integer.valueOf(nid));
        this.mButton.setVisibility(0);
    }

    public void SetTileText(String strVal) {
        this.mTextTitle.setText(strVal);
    }

    private void Init(Context context) {
        this.mView = LayoutInflater.from(context).inflate(R.layout.set_main_item_btn, (ViewGroup) null);
        this.mTextTitle = (TextView) this.mView.findViewById(R.id.set_text);
        this.mButton = (Button) this.mView.findViewById(R.id.set_button);
        this.mButton.setVisibility(4);
    }
}
