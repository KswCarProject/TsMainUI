package com.ts.can.mzd.wc;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.other.ParamButton;

public class CanMzdWcClockSetView extends CanRelativeCarInfoView {
    public static final int ITEM_CLEAR = 5;
    public static final int ITEM_CLOCK = 3;
    public static final int ITEM_HOUR = 1;
    public static final int ITEM_MIN = 2;
    public static final int ITEM_SET = 4;

    public CanMzdWcClockSetView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        int id = ((Integer) v.getTag()).intValue();
        if (action == 0) {
            sendKey(id, 1);
        } else if (action == 1) {
            sendKey(id, 0);
        }
        return false;
    }

    private void sendKey(int id, int key) {
        int GetSubType = CanJni.GetSubType();
        switch (id) {
            case 1:
                CanJni.MzdWcClockSet(131, key);
                return;
            case 2:
                CanJni.MzdWcClockSet(132, key);
                return;
            case 3:
                CanJni.MzdWcClockSet(130, key);
                return;
            case 4:
                CanJni.MzdWcClockSet(129, key);
                return;
            case 5:
                CanJni.MzdWcClockSet(133, key);
                return;
            default:
                return;
        }
    }

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        int subType = CanJni.GetSubType();
        if (subType == 4 || subType == 5 || subType == 10) {
            AddBtn(3, 221, 185, R.drawable.can_mzd_clock_up, R.drawable.can_mzd_clock_dn);
            AddTextBtn(4, CanCameraUI.BTN_LANDWIND_2D_FRONT, 240, "Set");
        } else if (subType == 3) {
            AddTextBtn(1, Can.CAN_NISSAN_RICH6_WC, 240, "Hour");
            AddTextBtn(4, 441, 240, "Set");
            AddTextBtn(2, CanCameraUI.BTN_LANDWIND_2D_FRONT, 240, "Min");
        } else if (subType == 7 || subType == 13) {
            AddTextBtn(1, Can.CAN_NISSAN_RICH6_WC, 240, "Hour");
            AddTextBtn(5, 441, 240, ": 00");
            AddTextBtn(2, CanCameraUI.BTN_LANDWIND_2D_FRONT, 240, "Min");
        }
    }

    public void ResetData(boolean check) {
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
