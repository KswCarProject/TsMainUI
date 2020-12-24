package com.ts.can.mzd;

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
import com.ts.main.common.MainLight;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;

public class CanOpelKeyActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener, View.OnTouchListener {
    public static final int ITEM_BC = 3;
    public static final int ITEM_CDMP3 = 2;
    public static final int ITEM_LT = 4;
    public static final int ITEM_NUM1 = 8;
    public static final int ITEM_NUM9 = 16;
    public static final int ITEM_OK = 6;
    public static final int ITEM_RT = 5;
    public static final int ITEM_SETTING = 7;
    public static final int ITEM_TUNER = 1;
    public static final String TAG = "CanOpelKeyActivity";
    private int mKey = 0;
    protected RelativeLayoutManager mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        AddTextBtn(1, 59, 73, "TUNER");
        AddTextBtn(2, MainLight.VOL_BAR_SIZEX, 73, "CD/MP3");
        AddBtn(4, 59, 214, R.drawable.can_psa_left_up, R.drawable.can_psa_left_dn);
        AddBtn(6, Can.CAN_MZD_TXB, Can.CAN_FORD_WC, R.drawable.can_psa_ok_up, R.drawable.can_psa_ok_dn);
        AddBtn(5, MainLight.VOL_BAR_SIZEX, 214, R.drawable.can_psa_right_up, R.drawable.can_psa_right_dn);
        AddTextBtn(3, 59, 355, "BC");
        AddTextBtn(7, MainLight.VOL_BAR_SIZEX, 355, "SETTING");
        for (int i = 0; i < 9; i++) {
            AddNumBtn(i + 8, ((i % 3) * 105) + 677, ((i / 3) * Can.CAN_AUDI_ZMYT) + 70, new StringBuilder(String.valueOf(i + 1)).toString());
        }
    }

    /* access modifiers changed from: protected */
    public void AddBtn(int id, int x, int y, int up, int dn) {
        ParamButton btn = this.mManager.AddButton(x, y);
        btn.setOnTouchListener(this);
        btn.setStateUpDn(up, dn);
        btn.setTag(Integer.valueOf(id));
    }

    /* access modifiers changed from: protected */
    public void AddTextBtn(int id, int x, int y, String text) {
        ParamButton btn = this.mManager.AddButton(x, y);
        btn.setOnTouchListener(this);
        btn.setStateUpDn(R.drawable.can_mzd_rect_up, R.drawable.can_mzd_rect_dn);
        btn.setTag(Integer.valueOf(id));
        btn.setText(text);
        btn.setTextSize(0, 30.0f);
        btn.setTextColor(-1);
        btn.setPadding(0, 0, 0, 0);
    }

    /* access modifiers changed from: protected */
    public void AddNumBtn(int id, int x, int y, String text) {
        ParamButton btn = this.mManager.AddButton(x, y);
        btn.setOnTouchListener(this);
        btn.setStateUpDn(R.drawable.can_mzd_digital_up, R.drawable.can_mzd_digital_dn);
        btn.setTag(Integer.valueOf(id));
        btn.setText(text);
        btn.setTextSize(0, 30.0f);
        btn.setTextColor(-1);
        btn.setPadding(0, 0, 0, 0);
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
            this.mKey = 0;
        }
    }

    public void onClick(View v) {
    }

    public void UserAll() {
        ResetData(true);
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        if (action == 0) {
            Log.d(TAG, "****ACTION_DOWN*****");
            int id = ((Integer) v.getTag()).intValue();
            this.mKey = id;
            switch (id) {
                case 1:
                    CanJni.MZDSendKey(6);
                    break;
                case 2:
                    CanJni.MZDSendKey(7);
                    break;
                case 3:
                    CanJni.MZDSendKey(3);
                    break;
                case 4:
                    CanJni.MZDSendKey(4);
                    break;
                case 5:
                    CanJni.MZDSendKey(5);
                    break;
                case 6:
                    CanJni.MZDSendKey(1);
                    break;
                case 7:
                    CanJni.MZDSendKey(2);
                    break;
                default:
                    if (id >= 8 && id <= 16) {
                        CanJni.MZDSendKey((id + 8) - 8);
                        break;
                    }
            }
        } else if (1 == action) {
            CanJni.MZDSendKey(0);
        }
        return false;
    }
}
