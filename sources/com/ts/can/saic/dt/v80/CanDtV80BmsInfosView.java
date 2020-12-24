package com.ts.can.saic.dt.v80;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.canview.CanItemTextBtnList;
import com.ts.canview.CanScrollList;

public class CanDtV80BmsInfosView extends CanRelativeCarInfoView {
    private static String[] mWarn1 = {"温度差异报警", "电池高温报警", "车载储能装置类型过压报警", "车载储能装置类型欠压报警", "SOC低报警", "单体电池过压报警", "单体电池欠压报警", "SOC过高报警"};
    private static String[] mWarn2 = {"跳变报警", "可充电储能系统不匹配报警", "电池单体一致性差报警", "绝缘报警", "DC-DC温度报警", "制动系统报警", "DC-DC状态报警", "驱动电机控制器温度报警"};
    private static String[] mWarn3 = {"高压互锁状态报警", "驱动电机温度报警", "车载储能装置类型过充"};
    private CanDataInfo.DT_V80_BMS_ERR mErr;
    private CanScrollList mManager;

    public CanDtV80BmsInfosView(Activity activity) {
        super(activity);
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void onClick(View v) {
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        ScrollView scrollView = new ScrollView(getActivity());
        getRelativeManager().AddView(scrollView, 0, 0, -1, -1);
        LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(1);
        this.mManager = new CanScrollList(layout, 0);
        scrollView.addView(this.mManager.getLayout(), -1, -1);
        this.mErr = new CanDataInfo.DT_V80_BMS_ERR();
    }

    /* access modifiers changed from: protected */
    public void addItem(String text) {
        if (text != null) {
            CanItemTextBtnList item = new CanItemTextBtnList((Context) getActivity(), 0);
            item.ShowArrow(false);
            item.SetVal(text);
            this.mManager.AddView(item.GetView());
        }
    }

    public void ResetData(boolean check) {
        CanJni.SaicDtV80GetWarn(this.mErr);
        if (i2b(this.mErr.UpdateOnce) && (!check || i2b(this.mErr.Update))) {
            this.mErr.Update = 0;
            this.mManager.RemoveAllViews();
            int[] warn = this.mErr.Warn;
            addWarnItem(warn[0], mWarn1);
            addWarnItem(warn[1], mWarn2);
            addWarnItem(warn[2], mWarn3);
        }
        if (this.mManager.getLayout().getChildCount() == 0) {
            addItem("无故障");
        }
    }

    private void addWarnItem(int value, String[] array) {
        int[] bits = {1, 2, 4, 8, 16, 32, 64, 128};
        for (int i = 0; i < array.length; i++) {
            if ((bits[i] & value) > 0) {
                addItem(array[i]);
            }
        }
    }

    public void QueryData() {
    }
}
