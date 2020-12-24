package com.ts.set.setview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import com.ts.MainUI.R;

public class SetMainItemSw {
    private TextView mTextTitle;
    private TextView mVal;
    private View mView;
    /* access modifiers changed from: private */
    public Switch switch_ctrl;

    public SetMainItemSw(Context context, String strVal) {
        Create(context, strVal);
    }

    public void Create(Context context, String strVal) {
        Init(context);
        this.mTextTitle.setText(strVal);
    }

    public void SetUserCallback(int nid, CompoundButton.OnCheckedChangeListener listener) {
        this.switch_ctrl.setOnCheckedChangeListener(listener);
        this.switch_ctrl.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() != 1) {
                    return false;
                }
                SetMainItemSw.this.switch_ctrl.setChecked(SetMainItemSw.this.switch_ctrl.isChecked());
                return false;
            }
        });
        this.switch_ctrl.setTag(Integer.valueOf(nid));
    }

    public View GetView() {
        return this.mView;
    }

    public void SetSwitch(int nSw) {
        if (nSw == 1) {
            this.mVal.setText(R.string.set_common_switch_on);
            this.switch_ctrl.setSelected(true);
            this.switch_ctrl.setChecked(true);
            this.switch_ctrl.setThumbResource(R.drawable.setup_switch_dn);
            this.switch_ctrl.setTrackResource(R.drawable.setup_switch_track_dn);
            return;
        }
        this.mVal.setText(R.string.set_common_switch_off);
        this.switch_ctrl.setSelected(false);
        this.switch_ctrl.setChecked(false);
        this.switch_ctrl.setThumbResource(R.drawable.setup_switch_up);
        this.switch_ctrl.setTrackResource(R.drawable.setup_switch_track_up);
    }

    private void Init(Context context) {
        this.mView = LayoutInflater.from(context).inflate(R.layout.set_main_item_sw, (ViewGroup) null);
        this.mTextTitle = (TextView) this.mView.findViewById(R.id.set_text);
        this.mVal = (TextView) this.mView.findViewById(R.id.switch_value);
        this.switch_ctrl = (Switch) this.mView.findViewById(R.id.switch_ctrl);
    }
}
