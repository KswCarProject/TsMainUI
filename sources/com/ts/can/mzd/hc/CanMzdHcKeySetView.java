package com.ts.can.mzd.hc;

import android.app.Activity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.other.ParamButton;
import com.yyw.ts70xhw.KeyDef;

public class CanMzdHcKeySetView extends CanRelativeCarInfoView {
    public static final int ITEM_AMB = 4;
    public static final int ITEM_CLOCK = 2;
    public static final int ITEM_HOUR = 1;
    public static final int ITEM_MIN = 3;
    public static final String TAG = "CanMzdHcKeySetView";
    private int mKey = 0;

    public CanMzdHcKeySetView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        int id = ((Integer) v.getTag()).intValue();
        if (action != 0) {
            if (1 == action) {
                Log.d(TAG, "****ACTION_UP*****");
                switch (id) {
                    case 1:
                        CanJni.MzdHcKeyCmd(0);
                        break;
                    case 2:
                        CanJni.MzdHcKeyCmd(0);
                        break;
                }
            }
        } else {
            Log.d(TAG, "****ACTION_DOWN*****");
            switch (id) {
                case 1:
                    CanJni.MzdHcKeyCmd(2);
                    break;
                case 2:
                    CanJni.MzdHcKeyCmd(1);
                    break;
            }
        }
        return false;
    }

    public void onClick(View v) {
        this.mKey = ((Integer) v.getTag()).intValue();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        AddTextBtn(1, 144, 240, "Set");
        AddBtn(2, KeyDef.RKEY_RADIO_6S, 185, R.drawable.can_mzd_clock_up, R.drawable.can_mzd_clock_dn);
    }

    public void ResetData(boolean check) {
        if (this.mKey != 0) {
            this.mKey = 0;
        }
    }

    public void QueryData() {
    }

    /* access modifiers changed from: protected */
    public void AddBtn(int id, int x, int y, int up, int dn) {
        ParamButton btn = getRelativeManager().AddButton(x, y);
        btn.setOnTouchListener(this);
        btn.setStateUpDn(up, dn);
        btn.setTag(Integer.valueOf(id));
    }

    /* access modifiers changed from: protected */
    public void AddTextBtn(int id, int x, int y, String text) {
        ParamButton btn = getRelativeManager().AddButton(x, y);
        btn.setOnTouchListener(this);
        btn.setStateUpDn(R.drawable.can_mzd_rect_up, R.drawable.can_mzd_rect_dn);
        btn.setTag(Integer.valueOf(id));
        btn.setText(text);
        btn.setTextSize(0, 30.0f);
        btn.setTextColor(-1);
        btn.setPadding(0, 0, 0, 0);
    }
}
