package com.ts.can.psa.pg408;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;

public class CanPg408KeySetActivity extends CanBaseActivity implements UserCallBack, View.OnTouchListener {
    public static final int ITEM_DARK = 8;
    public static final int ITEM_DN = 3;
    public static final int ITEM_ESC = 9;
    public static final int ITEM_LT = 4;
    public static final int ITEM_MENU = 7;
    public static final int ITEM_MODE = 1;
    public static final int ITEM_OK = 6;
    public static final int ITEM_RT = 5;
    public static final int ITEM_UP = 2;
    public static final String TAG = "CanPg408KeySetActivity";
    private int mKey = 0;
    protected RelativeLayoutManager mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
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

    /* access modifiers changed from: protected */
    public void AddBtn(int id, int x, int y, int up, int dn) {
        ParamButton btn = this.mManager.AddButton(x, y);
        btn.setOnTouchListener(this);
        btn.setStateUpDn(up, dn);
        btn.setTag(Integer.valueOf(id));
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    private void ResetData(boolean check) {
        if (this.mKey != 0) {
            CanJni.PSAKeySet(0);
            this.mKey = 0;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        if (action == 0) {
            Log.d(TAG, "****ACTION_DOWN*****");
            switch (((Integer) v.getTag()).intValue()) {
                case 1:
                    CanJni.Pg408SetKeyDown(6);
                    break;
                case 2:
                    CanJni.Pg408SetKeyDown(2);
                    break;
                case 3:
                    CanJni.Pg408SetKeyDown(3);
                    break;
                case 4:
                    CanJni.Pg408SetKeyDown(4);
                    break;
                case 5:
                    CanJni.Pg408SetKeyDown(5);
                    break;
                case 6:
                    CanJni.Pg408SetKeyDown(8);
                    break;
                case 7:
                    CanJni.Pg408SetKeyDown(0);
                    break;
                case 8:
                    CanJni.Pg408SetKeyDown(7);
                    break;
                case 9:
                    CanJni.Pg408SetKeyDown(1);
                    break;
            }
        } else if (1 == action) {
            CanJni.Pg408SetKeyUp();
        }
        return false;
    }
}
