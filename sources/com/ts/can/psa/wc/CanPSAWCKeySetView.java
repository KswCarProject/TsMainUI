package com.ts.can.psa.wc;

import android.app.Activity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;

public class CanPSAWCKeySetView extends CanRelativeCarInfoView {
    public static final int ITEM_DARK = 8;
    public static final int ITEM_DN = 3;
    public static final int ITEM_ESC = 9;
    public static final int ITEM_LT = 4;
    public static final int ITEM_MENU = 7;
    public static final int ITEM_MODE = 1;
    public static final int ITEM_OK = 6;
    public static final int ITEM_RT = 5;
    public static final int ITEM_UP = 2;
    private int mKey = 0;
    protected RelativeLayoutManager mManager;

    public CanPSAWCKeySetView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View v, MotionEvent event) {
        int param;
        int action = event.getAction();
        if (action == 0) {
            Log.d("__lh__", "****ACTION_DOWN*****");
            param = 1;
        } else {
            if (1 == action) {
                param = 0;
            }
            return false;
        }
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.PsaWcPankey(38, param);
                break;
            case 2:
                CanJni.PsaWcPankey(23, param);
                break;
            case 3:
                CanJni.PsaWcPankey(24, param);
                break;
            case 4:
                CanJni.PsaWcPankey(25, param);
                break;
            case 5:
                CanJni.PsaWcPankey(26, param);
                break;
            case 6:
                CanJni.PsaWcPankey(36, param);
                break;
            case 7:
                CanJni.PsaWcPankey(22, param);
                break;
            case 8:
                CanJni.PsaWcPankey(39, param);
                break;
            case 9:
                CanJni.PsaWcPankey(37, param);
                break;
        }
        return false;
    }

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public void AddBtn(int id, int x, int y, int up, int dn) {
        ParamButton btn = this.mManager.AddButton(x, y);
        btn.setOnTouchListener(this);
        btn.setStateUpDn(up, dn);
        btn.setTag(Integer.valueOf(id));
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = getRelativeManager();
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
}
