package com.ts.can.mzd.rzc;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.canview.CanVerticalBar;
import com.ts.main.common.MainSet;
import com.txznet.sdk.TXZResourceManager;
import com.yyw.ts70xhw.KeyDef;

public class CanMzdRzcCarOilView extends CanRelativeCarInfoView {
    private CanDataInfo.Cx4_Ave_Oil_His mAveOilHis;
    private CanVerticalBar[] mIvAveOils;
    private CanVerticalBar[] mIvMinOils;
    private CanDataInfo.Cx4_Min_Oil_Info mMinOilInfo;
    private TextView[] mTvAveValue;
    private TextView mTvAvgOil;

    public CanMzdRzcCarOilView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View arg0) {
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mTvAveValue = new TextView[4];
        this.mIvMinOils = new CanVerticalBar[15];
        this.mIvAveOils = new CanVerticalBar[6];
        this.mAveOilHis = new CanDataInfo.Cx4_Ave_Oil_His();
        this.mMinOilInfo = new CanDataInfo.Cx4_Min_Oil_Info();
        if (MainSet.GetScreenType() == 5) {
            BaseUI_1280x480();
        } else {
            BaseUI();
        }
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        if (mDisplayMetrics.heightPixels == 400) {
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) getRelativeManager().GetLayout().getLayoutParams();
            lp.width = 1024;
            lp.height = CanCameraUI.BTN_NISSAN_XTRAL_RVS_ASSIST6;
            lp.gravity = 17;
            getRelativeManager().GetLayout().setLayoutParams(lp);
            BaseUI();
            getRelativeManager().GetLayout().setScaleY(0.66f);
        }
    }

    /* access modifiers changed from: protected */
    public void BaseUI() {
        getRelativeManager().GetLayout().setBackgroundResource(R.drawable.can_mzdcx4_bg);
        this.mTvAvgOil = AddText(866, 269, 30);
        for (int i = 0; i < this.mIvMinOils.length; i++) {
            if (i > 4) {
                this.mIvMinOils[i] = AddVerticalBar(((i - 5) * 33) + 413, 55, R.drawable.can_mzdcx4_pillar_small_up);
            } else {
                this.mIvMinOils[i] = AddVerticalBar((i * 63) + 98, 55, R.drawable.can_mzdcx4_pillar_big_up);
            }
        }
        for (int i2 = 0; i2 < this.mIvAveOils.length; i2++) {
            this.mIvAveOils[i2] = AddVerticalBar((i2 * 107) + 121, KeyDef.RKEY_EJECT_L, R.drawable.can_mzdcx4_pillar_big_up);
        }
        for (int i3 = 0; i3 < this.mTvAveValue.length; i3++) {
            this.mTvAveValue[i3] = AddText((i3 * 107) + KeyDef.RKEY_DEL, 300, 15);
        }
    }

    /* access modifiers changed from: protected */
    public void BaseUI_1280x480() {
        getRelativeManager().GetLayout().setBackgroundResource(R.drawable.can_mzdcx4_hp_bg);
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

    public void ResetData(boolean check) {
        CanJni.MzdCx4GetAveOilHis(this.mAveOilHis);
        CanJni.MzdCx4GetMineOilInfo(this.mMinOilInfo);
        if (i2b(this.mMinOilInfo.UpdateOnce) && (!check || i2b(this.mMinOilInfo.Update))) {
            this.mMinOilInfo.Update = 0;
            showMinOilIcons(this.mMinOilInfo.pjyh);
        }
        if (!i2b(this.mAveOilHis.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mAveOilHis.Update)) {
            this.mAveOilHis.Update = 0;
            showAvgOilIcons(this.mAveOilHis.pjryls);
            showAvgOilValue(this.mAveOilHis.pjryls);
            this.mTvAvgOil.setText(String.format("%.1f", new Object[]{Float.valueOf(((float) this.mAveOilHis.bcjspjyh) * 0.1f)}));
        }
    }

    private void showAvgOilValue(int[] avgOils) {
        for (int i = 0; i < this.mTvAveValue.length; i++) {
            if (avgOils[i] > 0) {
                this.mTvAveValue[(this.mTvAveValue.length - 1) - i].setText(String.format("%.1f", new Object[]{Float.valueOf(((float) avgOils[i]) * 0.1f)}));
            } else {
                this.mTvAveValue[(this.mTvAveValue.length - 1) - i].setText(TXZResourceManager.STYLE_DEFAULT);
            }
        }
    }

    private void showAvgOilIcons(int[] avgOils) {
        for (int i = 0; i < this.mIvAveOils.length; i++) {
            if (avgOils[i] > 150) {
                this.mIvAveOils[(this.mIvAveOils.length - 1) - i].setCurPos(150);
            } else {
                this.mIvAveOils[(this.mIvAveOils.length - 1) - i].setCurPos(avgOils[i]);
            }
        }
    }

    private void showMinOilIcons(int[] minOils) {
        for (int i = 0; i < this.mIvMinOils.length; i++) {
            if (minOils[i] > 150) {
                this.mIvMinOils[i].setCurPos(150);
            } else {
                this.mIvMinOils[i].setCurPos(minOils[i]);
            }
        }
    }

    public void QueryData() {
        CanJni.MzdCx4Query(80, 0);
        Sleep(1);
        CanJni.MzdCx4Query(81, 0);
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
        bar.setMinMax(0.0f, 150.0f);
        bar.setCurPos(0);
        getRelativeManager().AddViewWrapContent(bar, x, y);
        return bar;
    }
}
