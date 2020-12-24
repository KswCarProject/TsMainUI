package com.ts.can.mzd.cx7;

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

public class CanMZDCX7KeyActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener, View.OnTouchListener {
    public static final int ITEM_CLEAR = 2;
    public static final int ITEM_HOUR = 1;
    public static final int ITEM_MIN = 3;
    public static final String TAG = "CanMZDCX7KeyActivity";
    private int mKey = 0;
    protected RelativeLayoutManager mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        AddTextBtn(1, Can.CAN_NISSAN_RICH6_WC, 240, "Hour");
        AddTextBtn(2, 439, 240, ": 00");
        AddTextBtn(3, CanCameraUI.BTN_LANDWIND_2D_FRONT, 240, "Min");
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
        if (action == 0) {
            Log.d(TAG, "****ACTION_DOWN*****");
            switch (((Integer) v.getTag()).intValue()) {
                case 1:
                    CanJni.MzdCx7SetTimeKey(64);
                    break;
                case 2:
                    CanJni.MzdCx7SetTimeKey(32);
                    break;
                case 3:
                    CanJni.MzdCx7SetTimeKey(128);
                    break;
            }
        } else if (1 == action) {
            CanJni.MzdCx7SetTimeKey(0);
        }
        return false;
    }
}
