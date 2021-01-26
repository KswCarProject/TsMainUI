package com.ts.can.gm.rzc;

import android.app.Activity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.main.common.MainLight;
import com.ts.other.ParamButton;

public class CanGMOpelKeyView extends CanRelativeCarInfoView {
    public static final int ITEM_BC = 3;
    public static final int ITEM_CDMP3 = 2;
    public static final int ITEM_LT = 4;
    public static final int ITEM_NUM1 = 8;
    public static final int ITEM_NUM9 = 16;
    public static final int ITEM_OK = 6;
    public static final int ITEM_RT = 5;
    public static final int ITEM_SETTING = 7;
    public static final int ITEM_TUNER = 1;
    public static final String TAG = "CanGMOpelKeyView";
    private int mKeyTime = 0;

    public CanGMOpelKeyView(Activity activity) {
        super(activity);
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        AddTextBtn(1, 59, 73, "TUNER");
        AddTextBtn(2, MainLight.VOL_BAR_SIZEX, 73, "CD/MP3");
        AddBtn(4, 59, 214, R.drawable.can_psa_left_up, R.drawable.can_psa_left_dn);
        AddBtn(6, Can.CAN_MZD_TXB, Can.CAN_FORD_WC, R.drawable.can_psa_ok_up, R.drawable.can_psa_ok_dn);
        AddBtn(5, MainLight.VOL_BAR_SIZEX, 214, R.drawable.can_psa_right_up, R.drawable.can_psa_right_dn);
        AddTextBtn(3, 59, 355, "BC");
        AddTextBtn(7, MainLight.VOL_BAR_SIZEX, 355, "SETTING");
        for (int i = 0; i < 9; i++) {
            AddNumBtn(i + 8, ((i % 3) * 105) + 677, ((i / 3) * 152) + 70, new StringBuilder(String.valueOf(i + 1)).toString());
        }
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

    /* access modifiers changed from: protected */
    public void AddNumBtn(int id, int x, int y, String text) {
        ParamButton btn = getRelativeManager().AddButton(x, y);
        btn.setOnTouchListener(this);
        btn.setStateUpDn(R.drawable.can_mzd_digital_up, R.drawable.can_mzd_digital_dn);
        btn.setTag(Integer.valueOf(id));
        btn.setText(text);
        btn.setTextSize(0, 30.0f);
        btn.setTextColor(-1);
        btn.setPadding(0, 0, 0, 0);
    }

    public void QueryData() {
        this.mKeyTime = 0;
    }

    public void onClick(View v) {
    }

    public void ResetData(boolean check) {
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        if (action == 0) {
            this.mKeyTime = 0;
            Log.d(TAG, "****ACTION_DOWN*****");
            int id = ((Integer) v.getTag()).intValue();
            switch (id) {
                case 1:
                    CanJni.GmRzcCmdKey(6);
                    break;
                case 2:
                    CanJni.GmRzcCmdKey(7);
                    break;
                case 3:
                    CanJni.GmRzcCmdKey(3);
                    break;
                case 4:
                    CanJni.GmRzcCmdKey(4);
                    break;
                case 5:
                    CanJni.GmRzcCmdKey(5);
                    break;
                case 6:
                    CanJni.GmRzcCmdKey(1);
                    break;
                case 7:
                    CanJni.GmRzcCmdKey(2);
                    break;
                default:
                    if (id >= 8 && id <= 16) {
                        CanJni.GmRzcCmdKey((id + 8) - 8);
                        break;
                    }
            }
        } else if (2 == action) {
            if (this.mKeyTime >= 10) {
                Log.d(TAG, "****ACTION_MOVE*****");
                this.mKeyTime = 0;
                int id2 = ((Integer) v.getTag()).intValue();
                switch (id2) {
                    case 1:
                        CanJni.GmRzcCmdKey(6);
                        break;
                    case 2:
                        CanJni.GmRzcCmdKey(7);
                        break;
                    case 3:
                        CanJni.GmRzcCmdKey(3);
                        break;
                    case 4:
                        CanJni.GmRzcCmdKey(4);
                        break;
                    case 5:
                        CanJni.GmRzcCmdKey(5);
                        break;
                    case 6:
                        CanJni.GmRzcCmdKey(1);
                        break;
                    case 7:
                        CanJni.GmRzcCmdKey(2);
                        break;
                    default:
                        if (id2 >= 8 && id2 <= 16) {
                            CanJni.GmRzcCmdKey((id2 + 8) - 8);
                            break;
                        }
                }
            } else {
                this.mKeyTime++;
            }
        } else if (1 == action) {
            this.mKeyTime = 0;
            CanJni.GmRzcCmdKey(0);
        }
        return false;
    }
}
