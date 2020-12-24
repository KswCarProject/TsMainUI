package com.ts.canview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.ts.MainUI.R;

public class CanItemTitleValList {
    private Button mBtn;
    private ImageView mIcon;
    private TextView mVal;
    private View mView;

    public CanItemTitleValList() {
    }

    public CanItemTitleValList(Context context, int text) {
        Create(context, text);
    }

    public void Create(Context context, int text) {
        this.mView = LayoutInflater.from(context).inflate(R.layout.can_item_popup_list, (ViewGroup) null);
        this.mBtn = (Button) this.mView.findViewById(R.id.btn);
        this.mBtn.setText(text);
        this.mVal = (TextView) this.mView.findViewById(R.id.val);
        this.mIcon = (ImageView) this.mView.findViewById(R.id.delta);
        this.mIcon.setVisibility(4);
    }

    public void SetIdClickListener(View.OnClickListener listener, int id) {
        this.mBtn.setTag(Integer.valueOf(id));
        this.mBtn.setOnClickListener(listener);
    }

    public void SetValVisibility(int visible) {
        this.mVal.setVisibility(visible);
    }

    public void SetIconVisibility(int visible) {
        this.mIcon.setVisibility(visible);
    }

    public void SetVal(int resId) {
        this.mVal.setText(resId);
    }

    public void SetVal(String str) {
        this.mVal.setText(str);
    }

    public View GetView() {
        return this.mView;
    }

    public Button GetBtn() {
        return this.mBtn;
    }

    public void ShowGone(int show) {
        ShowGone(show != 0);
    }

    public void ShowGone(boolean show) {
        if (show) {
            this.mView.setVisibility(0);
        } else {
            this.mView.setVisibility(8);
        }
    }

    public void setEnabled(boolean isEnabled) {
        if (isEnabled) {
            this.mVal.setTextColor(-1);
            this.mBtn.setClickable(true);
            this.mBtn.setTextColor(-1);
            this.mIcon.setBackgroundResource(R.drawable.can_comm_rt_delta);
            return;
        }
        this.mVal.setTextColor(-7829368);
        this.mBtn.setClickable(false);
        this.mBtn.setTextColor(-7829368);
        this.mIcon.setBackgroundResource(R.drawable.can_comm_right_sjx_dn);
    }
}
