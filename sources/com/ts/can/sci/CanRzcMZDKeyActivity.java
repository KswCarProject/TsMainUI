package com.ts.can.sci;

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
import com.ts.can.CanCameraUI;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;

public class CanRzcMZDKeyActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener, View.OnTouchListener {
    public static final int ITEM_CLOCK = 2;
    public static final int ITEM_HOUR = 1;
    public static final int ITEM_MIN = 3;
    public static final int ITEM_SETTINGS = 4;
    private int mKey = 0;
    protected RelativeLayoutManager mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        if (CanJni.GetSubType() == 1) {
            AddTextBtn(4, 220, 240, "SETTING");
            AddBtn(2, 421, 185, R.drawable.can_mzd_clock_up, R.drawable.can_mzd_clock_dn);
        } else if (CanJni.GetSubType() == 2) {
            AddTextBtn(1, Can.CAN_NISSAN_RICH6_WC, 240, "Hour");
            AddBtn(2, 421, 185, R.drawable.can_mzd_clock_up, R.drawable.can_mzd_clock_dn);
            AddTextBtn(3, CanCameraUI.BTN_LANDWIND_2D_FRONT, 240, "Min");
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
        this.mKey = ((Integer) v.getTag()).intValue();
    }

    public void UserAll() {
        ResetData(true);
    }

    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        int id = ((Integer) v.getTag()).intValue();
        if (action != 0) {
            if (1 == action) {
                switch (id) {
                    case 1:
                    case 4:
                        CanJni.RzcSciKeySet(1, 0);
                        break;
                    case 2:
                        CanJni.RzcSciKeySet(2, 0);
                        break;
                    case 3:
                        CanJni.RzcSciKeySet(0, 0);
                        break;
                }
            }
        } else {
            Log.d(CanBaseActivity.TAG, "****ACTION_DOWN*****");
            switch (id) {
                case 1:
                case 4:
                    CanJni.RzcSciKeySet(1, 1);
                    break;
                case 2:
                    CanJni.RzcSciKeySet(2, 1);
                    break;
                case 3:
                    CanJni.RzcSciKeySet(0, 1);
                    break;
            }
        }
        return false;
    }
}
