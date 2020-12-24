package com.ts.can.psa.rzc.scr;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.other.ParamButton;

public class CanPSAScrRzcKeySetView extends CanRelativeCarInfoView {
    public static final int ITEM_DARK = 8;
    public static final int ITEM_DN = 3;
    public static final int ITEM_ESC = 9;
    public static final int ITEM_LT = 4;
    public static final int ITEM_MENU = 7;
    public static final int ITEM_MODE = 1;
    public static final int ITEM_OK = 6;
    public static final int ITEM_RT = 5;
    public static final int ITEM_UP = 2;

    public CanPSAScrRzcKeySetView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View v, MotionEvent event) {
        int id = ((Integer) v.getTag()).intValue();
        int action = event.getAction();
        if (action == 0) {
            switch (id) {
                case 1:
                    CanJni.PsaSrcRzcKeyCmd(0, 64);
                    break;
                case 2:
                    CanJni.PsaSrcRzcKeyCmd(0, 4);
                    break;
                case 3:
                    CanJni.PsaSrcRzcKeyCmd(0, 8);
                    break;
                case 4:
                    CanJni.PsaSrcRzcKeyCmd(0, 16);
                    break;
                case 5:
                    CanJni.PsaSrcRzcKeyCmd(0, 32);
                    break;
                case 6:
                    CanJni.PsaSrcRzcKeyCmd(32, 0);
                    break;
                case 7:
                    CanJni.PsaSrcRzcKeyCmd(0, 1);
                    break;
                case 8:
                    CanJni.PsaSrcRzcKeyCmd(0, 128);
                    break;
                case 9:
                    CanJni.PsaSrcRzcKeyCmd(0, 2);
                    break;
            }
        } else if (action == 1) {
            CanJni.PsaSrcRzcKeyCmd(0, 0);
        }
        return false;
    }

    public void onClick(View arg0) {
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        AddBtn(1, 188, 51, R.drawable.can_psa_mode_up, R.drawable.can_psa_mode_dn);
        AddBtn(2, 439, 51, R.drawable.can_psa_on_up, R.drawable.can_psa_on_dn);
        AddBtn(7, 691, 51, R.drawable.can_psa_menu_up, R.drawable.can_psa_menu_dn);
        AddBtn(4, 188, Can.CAN_FLAT_WC, R.drawable.can_psa_left_up, R.drawable.can_psa_left_dn);
        AddBtn(6, 421, 175, R.drawable.can_psa_ok_up, R.drawable.can_psa_ok_dn);
        AddBtn(5, 691, Can.CAN_FLAT_WC, R.drawable.can_psa_right_up, R.drawable.can_psa_right_dn);
        AddBtn(8, 188, 423, R.drawable.can_psa_dark_up, R.drawable.can_psa_dark_dn);
        AddBtn(3, 439, 423, R.drawable.can_psa_down_up, R.drawable.can_psa_down_dn);
        AddBtn(9, 691, 423, R.drawable.can_psa_esc_up, R.drawable.can_psa_esc_dn);
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
    }

    /* access modifiers changed from: protected */
    public void AddBtn(int id, int x, int y, int up, int dn) {
        ParamButton btn = addButton(x, y);
        btn.setOnTouchListener(this);
        btn.setStateUpDn(up, dn);
        btn.setTag(Integer.valueOf(id));
    }
}
