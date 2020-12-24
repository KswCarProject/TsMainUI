package com.ts.set.setview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ts.MainUI.R;

public class SetItemVerticalSeekBar {
    private SettingSeekBar mSb = ((SettingSeekBar) this.mView.findViewById(R.id.sb));
    private TextView mTitle = ((TextView) this.mView.findViewById(R.id.seek_name));
    private TextView mVal = ((TextView) this.mView.findViewById(R.id.seek_value));
    private View mView;

    public SetItemVerticalSeekBar(Context context, int Id) {
        this.mView = LayoutInflater.from(context).inflate(R.layout.set_vertical_seekbar, (ViewGroup) null);
        if (Id != 0) {
            this.mTitle.setText(Id);
        }
    }

    public void SetTitle(String title) {
        this.mTitle.setText(title);
    }

    public View GetView() {
        return this.mView;
    }

    public SettingSeekBar GetSeekBar() {
        return this.mSb;
    }

    public void SetVal(String text) {
        this.mVal.setText(text);
    }
}
