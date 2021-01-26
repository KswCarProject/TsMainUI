package com.ts.can.mzd.lz;

import android.app.Activity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.other.ParamButton;
import com.yyw.ts70xhw.KeyDef;

public class CanMzdLzKeySetView extends CanRelativeCarInfoView {
    public static final int ITEM_AMB = 4;
    public static final int ITEM_CLOCK = 2;
    public static final int ITEM_HOUR = 1;
    public static final int ITEM_MIN = 3;
    public static final String TAG = "CanMzdLzKeySetView";
    private int mKey = 0;

    public CanMzdLzKeySetView(Activity activity) {
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
                        CanJni.SciLzKeyCmd(1, 0);
                        break;
                    case 2:
                        CanJni.SciLzKeyCmd(2, 0);
                        break;
                    case 3:
                        CanJni.SciLzKeyCmd(0, 0);
                        break;
                    case 4:
                        CanJni.SciLzKeyCmd(3, 0);
                        break;
                }
            }
        } else {
            Log.d(TAG, "****ACTION_DOWN*****");
            switch (id) {
                case 1:
                    CanJni.SciLzKeyCmd(1, 1);
                    break;
                case 2:
                    CanJni.SciLzKeyCmd(2, 1);
                    break;
                case 3:
                    CanJni.SciLzKeyCmd(0, 1);
                    break;
                case 4:
                    CanJni.SciLzKeyCmd(3, 1);
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
        AddTextBtn(1, 144, Can.CAN_VOLKS_XP, "Hour");
        AddBtn(2, KeyDef.RKEY_RADIO_6S, 185, R.drawable.can_mzd_clock_up, R.drawable.can_mzd_clock_dn);
        AddTextBtn(3, CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST9, Can.CAN_VOLKS_XP, "Min");
        AddTextBtn(4, 732, Can.CAN_VOLKS_XP, "AMB");
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
