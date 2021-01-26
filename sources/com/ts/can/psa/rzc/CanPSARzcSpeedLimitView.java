package com.ts.can.psa.rzc;

import android.app.Activity;
import android.support.v4.view.ViewCompat;
import android.view.MotionEvent;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.canview.CanNumInuptDlg;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;

public class CanPSARzcSpeedLimitView extends CanRelativeCarInfoView implements CanNumInuptDlg.onInputOK {
    public static final int ITEM_OK = 11;
    public static final int ITEM_RESET = 10;
    public static final int ITEM_VAL_BASE = 1;
    public static final String TAG = "CanPSASpeedLimitActivity";
    private ParamButton[] mBtnSpeed;
    private RelativeLayoutManager mManager;
    protected CanDataInfo.PeugMemTab mSpeedData = new CanDataInfo.PeugMemTab();
    protected boolean mbModify;

    public CanPSARzcSpeedLimitView(Activity activity) {
        super(activity);
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = getRelativeManager();
        this.mManager.AddImage(180, 105, R.drawable.can_psa_c4l_bg);
        this.mBtnSpeed = new ParamButton[6];
        for (int i = 0; i < 6; i++) {
            this.mBtnSpeed[i] = AddValBtn(((i % 3) * 195) + 270, ((i / 3) * 121) + 88 + 50, i + 1);
        }
        AddCtrlBtn(Can.CAN_MZD_TXB, 438, 10, R.string.can_setup_reset);
        AddCtrlBtn(639, 438, 11, R.string.can_confirm);
    }

    /* access modifiers changed from: protected */
    public ParamButton AddValBtn(int x, int y, int id) {
        ParamButton btn = this.mManager.AddButton(x, y);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setStateUpDn(R.drawable.can_psa_yuan_up, R.drawable.can_psa_yuan_dn);
        btn.setColorUpDn(ViewCompat.MEASURED_STATE_MASK, -1);
        btn.setTextSize(0, 45.0f);
        return btn;
    }

    /* access modifiers changed from: protected */
    public void AddCtrlBtn(int x, int y, int id, int text) {
        ParamButton btn = this.mManager.AddButton(x, y, 143, 74);
        btn.setTag(Integer.valueOf(id));
        btn.setOnClickListener(this);
        btn.setStateUpDn(R.drawable.can_psa_c4l_rect_up, R.drawable.can_psa_c4l_rect_dn);
        btn.setTextColor(-1);
        btn.setTextSize(0, 34.615387f);
        btn.setPadding(0, 5, 0, 0);
        btn.setText(text);
    }

    public void doOnResume() {
        super.doOnResume();
        ResetData(false);
        QueryData();
    }

    public void doOnPause() {
        super.doOnPause();
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        switch (id) {
            case 10:
                this.mbModify = false;
                CanJni.PSASpeedLimitSet(this.mSpeedData, 1);
                return;
            case 11:
                this.mbModify = false;
                CanJni.PSASpeedLimitSet(this.mSpeedData, 0);
                return;
            default:
                if (id >= 1 && id <= 6) {
                    new CanNumInuptDlg(getActivity(), this, 3, id);
                    this.mbModify = true;
                    return;
                }
                return;
        }
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void ResetData(boolean check) {
        if (!this.mbModify) {
            CanJni.PSAGetSpeedLimit(this.mSpeedData);
            if (!i2b(this.mSpeedData.UpdateOnce)) {
                return;
            }
            if (!check || i2b(this.mSpeedData.Update)) {
                this.mSpeedData.Update = 0;
                for (int i = 0; i < 6; i++) {
                    this.mBtnSpeed[i].setText(new StringBuilder(String.valueOf(this.mSpeedData.Data[i])).toString());
                }
            }
        }
    }

    public void QueryData() {
        CanJni.PSAQuery(61, 0);
    }

    public void onOK(String val, int num, int id) {
        if (num > 255) {
            num = 255;
        }
        this.mSpeedData.Data[id - 1] = num;
        this.mBtnSpeed[id - 1].setText(new StringBuilder().append(num).toString());
    }
}
