package com.ts.can.mzd.wc;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.canview.CanVerticalBar;
import com.ts.main.common.MainSet;
import com.yyw.ts70xhw.KeyDef;

public class CanMzdWcOilView extends CanRelativeCarInfoView {
    private CanDataInfo.MzdWCConsumpTrip mConsumpTrip = new CanDataInfo.MzdWCConsumpTrip();
    private CanVerticalBar[] mIvAveOils;
    private CanVerticalBar[] mIvMinOils;
    private CanDataInfo.MzdWCConsumpPerMin mPerMin = new CanDataInfo.MzdWCConsumpPerMin();
    private TextView[] mTvAveValue;
    private TextView mTvAvgOil;

    public CanMzdWcOilView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mIvMinOils = new CanVerticalBar[15];
        this.mTvAveValue = new TextView[4];
        this.mIvAveOils = new CanVerticalBar[6];
        if (MainSet.GetScreenType() == 5) {
            BaseUI_1280x480();
        } else {
            BaseUI();
        }
    }

    /* access modifiers changed from: protected */
    public void BaseUI() {
        setBackgroundResource(R.drawable.can_mzdcx4_bg);
        this.mTvAvgOil = AddText(866, 259, 30);
        for (int i = 0; i < this.mIvMinOils.length; i++) {
            if (i < 10) {
                this.mIvMinOils[i] = AddVerticalBar(710 - (i * 33), 57, R.drawable.can_mzdcx4_pillar_small_up);
            } else {
                this.mIvMinOils[i] = AddVerticalBar(350 - ((i - 10) * 63), 57, R.drawable.can_mzdcx4_pillar_big_up);
            }
        }
        for (int i2 = 0; i2 < this.mIvAveOils.length; i2++) {
            this.mIvAveOils[i2] = AddVerticalBar((i2 * 107) + 121, KeyDef.RKEY_POWER_OFF, R.drawable.can_mzdcx4_pillar_big_up);
        }
        for (int i3 = 0; i3 < this.mTvAveValue.length; i3++) {
            this.mTvAveValue[i3] = AddText((i3 * 107) + KeyDef.RKEY_DEL, 300, 15);
        }
    }

    /* access modifiers changed from: protected */
    public void BaseUI_1280x480() {
        setBackgroundResource(R.drawable.can_mzdcx4_hp_bg);
        this.mTvAvgOil = AddText(1064, 218, 30);
        for (int i = 0; i < this.mIvMinOils.length; i++) {
            if (i > 4) {
                this.mIvMinOils[i] = AddVerticalBar(((i - 5) * 33) + 492, 44, R.drawable.can_mzdcx4_hp_pillar_small_up);
            } else {
                this.mIvMinOils[i] = AddVerticalBar((i * 63) + 177, 44, R.drawable.can_mzdcx4_hp_pillar_big_up);
            }
        }
        for (int i2 = 0; i2 < this.mIvAveOils.length; i2++) {
            this.mIvAveOils[i2] = AddVerticalBar((i2 * 107) + 200, 258, R.drawable.can_mzdcx4_hp_pillar_big_up);
        }
        for (int i3 = 0; i3 < this.mTvAveValue.length; i3++) {
            this.mTvAveValue[i3] = AddText((i3 * 107) + 410, Can.CAN_FORD_SYNC3, 15);
        }
    }

    public void doOnResume() {
        super.doOnResume();
    }

    public void ResetData(boolean check) {
        CanJni.MzdWcGetConsumpTrip(this.mConsumpTrip);
        CanJni.MzdWcGetConsumpPerMin(this.mPerMin);
        if (i2b(this.mPerMin.UpdateOnce) && (!check || i2b(this.mPerMin.Update))) {
            this.mPerMin.Update = 0;
            showMinOilIcons(this.mPerMin.Min);
        }
        if (!i2b(this.mConsumpTrip.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mConsumpTrip.Update)) {
            this.mConsumpTrip.Update = 0;
            showAvgOilIcons(this.mConsumpTrip.Trip);
            showAvgOilValue(this.mConsumpTrip.Trip);
            this.mTvAvgOil.setText(String.format("%.1f", new Object[]{Float.valueOf(((float) this.mConsumpTrip.Trip[6]) * 0.1f)}));
        }
    }

    private void showAvgOilValue(int[] avgOils) {
        for (int i = 0; i < this.mTvAveValue.length; i++) {
            if (avgOils[i] > 0) {
                this.mTvAveValue[(this.mTvAveValue.length - 1) - i].setText(String.format("%.1f", new Object[]{Float.valueOf(((float) avgOils[i]) * 0.1f)}));
            } else {
                this.mTvAveValue[(this.mTvAveValue.length - 1) - i].setText("");
            }
        }
    }

    private void showAvgOilIcons(int[] avgOils) {
        for (int i = 0; i < this.mIvAveOils.length; i++) {
            if (((int) (((float) avgOils[i]) * 0.1f)) > 15) {
                this.mIvAveOils[(this.mIvAveOils.length - 1) - i].setCurPos(15);
            } else {
                this.mIvAveOils[(this.mIvAveOils.length - 1) - i].setCurPos((int) (((float) avgOils[i]) * 0.1f));
            }
        }
    }

    private void showMinOilIcons(int[] minOils) {
        for (int i = 0; i < this.mIvMinOils.length; i++) {
            if (((int) (((float) minOils[i]) * 0.1f)) > 15) {
                this.mIvMinOils[i].setCurPos(15);
            } else {
                this.mIvMinOils[i].setCurPos((int) (((float) minOils[i]) * 0.1f));
            }
        }
    }

    public void QueryData() {
        CanJni.MzdWcQuery(5, 1, 20);
        Sleep(1);
        CanJni.MzdWcQuery(5, 1, 21);
    }

    private TextView AddText(int x, int y, int textSize) {
        TextView view = getRelativeManager().AddText(x, y);
        view.setTextColor(-1);
        view.setSingleLine(true);
        view.setTextSize(0, ((float) textSize) * 1.5f);
        view.setGravity(3);
        return view;
    }

    private CanVerticalBar AddVerticalBar(int x, int y, int imgId) {
        CanVerticalBar bar = new CanVerticalBar((Context) getActivity(), imgId);
        bar.setMinMax(0.0f, 15.0f);
        bar.setCurPos(0);
        getRelativeManager().AddViewWrapContent(bar, x, y);
        return bar;
    }
}
