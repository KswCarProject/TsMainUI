package com.ts.can.psa;

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

public class CanPSAKeySetActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener, View.OnTouchListener {
    public static final int ITEM_DARK = 8;
    public static final int ITEM_DN = 3;
    public static final int ITEM_ESC = 9;
    public static final int ITEM_LT = 4;
    public static final int ITEM_MENU = 7;
    public static final int ITEM_MODE = 1;
    public static final int ITEM_OK = 6;
    public static final int ITEM_RT = 5;
    public static final int ITEM_UP = 2;
    public static final String TAG = "CanPSAKeySetActivity";
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

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        this.mKey = id;
        switch (id) {
            case 1:
                CanJni.PSAKeySet(2);
                return;
            case 2:
                CanJni.PSAKeySet(7);
                return;
            case 3:
                CanJni.PSAKeySet(8);
                return;
            case 4:
                CanJni.PSAKeySet(6);
                return;
            case 5:
                CanJni.PSAKeySet(5);
                return;
            case 6:
                CanJni.PSAKeySet(9);
                return;
            case 7:
                CanJni.PSAKeySet(4);
                return;
            case 8:
                CanJni.PSAKeySet(1);
                return;
            case 9:
                CanJni.PSAKeySet(3);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() != 0) {
            return false;
        }
        Log.d(TAG, "****ACTION_DOWN*****");
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.PSAKeySet(2);
                return false;
            case 2:
                CanJni.PSAKeySet(7);
                return false;
            case 3:
                CanJni.PSAKeySet(8);
                return false;
            case 4:
                CanJni.PSAKeySet(6);
                return false;
            case 5:
                CanJni.PSAKeySet(5);
                return false;
            case 6:
                CanJni.PSAKeySet(9);
                return false;
            case 7:
                CanJni.PSAKeySet(4);
                return false;
            case 8:
                CanJni.PSAKeySet(1);
                return false;
            case 9:
                CanJni.PSAKeySet(3);
                return false;
            default:
                return false;
        }
    }
}
