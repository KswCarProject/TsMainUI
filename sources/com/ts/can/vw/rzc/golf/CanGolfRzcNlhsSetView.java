package com.ts.can.vw.rzc.golf;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanRelativeCarInfoView;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanVerticalBar;
import com.ts.other.ParamButton;
import com.ts.other.RelativeLayoutManager;

public class CanGolfRzcNlhsSetView extends CanRelativeCarInfoView implements CanItemPopupList.onPopItemClick {
    private static final int ID_CLEAR = 1281;
    private static final int ID_HISTORY = 1280;
    public static final String TAG = "CanGolfRzcNlhsSetView";
    private CanVerticalBar[] m30Min;
    private CanVerticalBar mCurrent;
    private TextView mDW;
    private int mLeftMarign;
    protected RelativeLayoutManager mManager;
    private CanDataInfo.GolfNlhsData mNlhsData;
    private TextView mPastDW;
    private TextView[] mProgText;
    private int mTopMarign;

    public CanGolfRzcNlhsSetView(Activity activity) {
        super(activity);
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.m30Min = new CanVerticalBar[30];
        this.mLeftMarign = 113;
        this.mTopMarign = 63;
        initBaseUI();
        this.mNlhsData = new CanDataInfo.GolfNlhsData();
    }

    private void initBaseUI() {
        this.mManager = getRelativeManager();
        this.mDW = this.mManager.AddText(860 - this.mLeftMarign, this.mTopMarign + 20, 130, 30);
        SetProgText(this.mDW);
        this.mDW.setText("kWh");
        this.mPastDW = this.mManager.AddText(610 - this.mLeftMarign, this.mTopMarign + 20, 130, 30);
        SetProgText(this.mPastDW);
        this.mPastDW.setText("kWh");
        this.mProgText = new TextView[4];
        TextView[] tvBot = new TextView[4];
        for (int i = 0; i < this.mProgText.length; i++) {
            tvBot[i] = this.mManager.AddText(((i * 176) + 177) - this.mLeftMarign, this.mTopMarign + 388, 100, 30);
            SetProgText(tvBot[i]);
            tvBot[i].setText(new StringBuilder(String.valueOf((3 - i) * 10)).toString());
            this.mProgText[i] = this.mManager.AddText(760 - this.mLeftMarign, (357 - (i * 104)) + this.mTopMarign, 100, 30);
            SetProgText(this.mProgText[i]);
            this.mProgText[i].setText(new StringBuilder(String.valueOf(i * 10)).toString());
        }
        tvBot[0].setText(R.string.can_30min);
        TextView tv = this.mManager.AddText(860 - this.mLeftMarign, this.mTopMarign + 388, 130, 30);
        SetProgText(tv);
        tv.setText(R.string.can_current);
        this.mManager.AddImage(225 - this.mLeftMarign, this.mTopMarign + 61, R.drawable.fuel_consumption_line01);
        this.mCurrent = new CanVerticalBar((Context) getActivity(), R.drawable.fuel_consumption_pillars02);
        this.mManager.AddViewWrapContent(this.mCurrent, 879 - this.mLeftMarign, this.mTopMarign + 65);
        this.mCurrent.setMinMax(0.0f, 100.0f);
        for (int i2 = 0; i2 < this.m30Min.length; i2++) {
            this.m30Min[i2] = new CanVerticalBar((Context) getActivity(), R.drawable.fuel_consumption_pillars04);
            this.m30Min[i2].setMinMax(0.0f, 100.0f);
            this.mManager.AddView(this.m30Min[i2], ((((i2 % 10) * 16) + Can.CAN_LIEBAO_WC) + ((i2 / 10) * 177)) - this.mLeftMarign, this.mTopMarign + 65, 15, 308);
        }
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
        switch (((Integer) v.getTag()).intValue()) {
        }
    }

    public boolean onTouch(View arg0, MotionEvent arg1) {
        return false;
    }

    public void ResetData(boolean check) {
        int i;
        int i2;
        int i3;
        CanJni.GolfGetNlhs(this.mNlhsData);
        if (!i2b(this.mNlhsData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mNlhsData.Update)) {
            this.mNlhsData.Update = 0;
            for (int i4 = 1; i4 < this.mProgText.length; i4++) {
                this.mProgText[i4].setText(new StringBuilder(String.valueOf(CanCameraUI.BTN_TRUMPCHI_GS4_MODE1 * i4)).toString());
            }
            this.mCurrent.setMinMax(0.0f, (float) CanCameraUI.BTN_CHANA_WC_MODE);
            CanVerticalBar canVerticalBar = this.mCurrent;
            if (this.mNlhsData.Nlhsz / 10 > 1200) {
                i = 1200;
            } else {
                i = this.mNlhsData.Nlhsz / 10;
            }
            canVerticalBar.setCurPos(i);
            for (int i5 = 0; i5 < 15; i5++) {
                this.m30Min[29 - i5].setMinMax(0.0f, (float) CanCameraUI.BTN_CHANA_WC_MODE);
                CanVerticalBar canVerticalBar2 = this.m30Min[29 - i5];
                if (this.mNlhsData.Nlhsz15min[i5] / 10 > 1200) {
                    i3 = 1200;
                } else {
                    i3 = this.mNlhsData.Nlhsz15min[i5] / 10;
                }
                canVerticalBar2.setCurPos(i3);
            }
            for (int i6 = 0; i6 < 15; i6++) {
                this.m30Min[14 - i6].setMinMax(0.0f, (float) CanCameraUI.BTN_CHANA_WC_MODE);
                CanVerticalBar canVerticalBar3 = this.m30Min[14 - i6];
                if (this.mNlhsData.Nlhsz30min[i6] / 10 > 1200) {
                    i2 = 1200;
                } else {
                    i2 = this.mNlhsData.Nlhsz30min[i6] / 10;
                }
                canVerticalBar3.setCurPos(i2);
            }
        }
    }

    public void QueryData() {
        CanJni.GolfQuery(83, 144);
    }

    public void onItem(int id, int item) {
    }

    private void SetLeftText(TextView tv) {
        tv.setTextSize(0, 27.0f);
        tv.setTextColor(-1);
        tv.setGravity(3);
    }

    private void SetProgText(TextView tv) {
        tv.setTextSize(0, 25.0f);
        tv.setTextColor(-1);
        tv.setGravity(17);
    }

    /* access modifiers changed from: protected */
    public void SetCommBtn(ParamButton btn, int text) {
        btn.setStateUpDn(R.drawable.fuel_consumption_rect_up, R.drawable.fuel_consumption_rect_dn);
        if (text != 0) {
            btn.setText(text);
        }
        btn.setPadding(0, 3, 0, 0);
        btn.setTextColor(-1);
        btn.setGravity(17);
        btn.setTextSize(0, 32.0f);
    }
}
