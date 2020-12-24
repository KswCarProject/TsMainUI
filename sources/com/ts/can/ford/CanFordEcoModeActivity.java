package com.ts.can.ford;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemTextBtnList;
import com.ts.main.common.MainSet;
import com.ts.other.CustomImgView;
import com.ts.other.RelativeLayoutManager;

public class CanFordEcoModeActivity extends CanBaseActivity implements UserCallBack, View.OnClickListener, CanItemProgressList.onPosChange {
    public static final int ITEM_HOT = 2;
    public static final int ITEM_PLAN = 0;
    public static final int ITEM_SPEED = 1;
    public static final String TAG = "CanMbRelativeActivity";
    private static final int[] mEcoModeArr = {R.drawable.can_jnh_icon_00, R.drawable.can_jnh_icon_01, R.drawable.can_jnh_icon_02, R.drawable.can_jnh_icon_03, R.drawable.can_jnh_icon_04, R.drawable.can_jnh_icon_05, R.drawable.can_jnh_icon_02, R.drawable.can_jnh_icon_02};
    protected CustomImgView mImgHot;
    protected CustomImgView mImgPlan;
    protected CustomImgView mImgSpeed;
    protected CanItemTextBtnList mItemEngHot;
    protected CanItemTextBtnList mItemPlan;
    protected CanItemTextBtnList mItemSpeed;
    protected RelativeLayoutManager mManager;
    protected CanDataInfo.FordSet mSetData = new CanDataInfo.FordSet();
    protected String mStrHot;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_comm_relative);
        this.mManager = new RelativeLayoutManager(this, R.id.can_comm_layout);
        this.mItemPlan = AddItem(0, R.string.can_plan_df, 0);
        this.mItemSpeed = AddItem(1, R.string.can_speed_df, 1);
        this.mItemEngHot = AddItem(2, R.string.can_lc_yqwr, 2);
        if (MainSet.GetScreenType() == 3) {
            initScreen_768x1024();
        } else {
            initCommonScreen();
        }
        this.mStrHot = getResources().getString(R.string.can_lc_yqwr);
    }

    private void initScreen_768x1024() {
        this.mImgPlan = this.mManager.AddImage(605, 19, R.drawable.can_jnh_icon_00);
        this.mImgSpeed = this.mManager.AddImage(605, 104, R.drawable.can_jnh_icon_00);
        this.mImgHot = this.mManager.AddImage(605, 189, R.drawable.can_jnh_oil);
    }

    private void initCommonScreen() {
        this.mImgPlan = this.mManager.AddImage(905, 19, R.drawable.can_jnh_icon_00);
        this.mImgSpeed = this.mManager.AddImage(905, 104, R.drawable.can_jnh_icon_00);
        this.mImgHot = this.mManager.AddImage(905, 189, R.drawable.can_jnh_oil);
    }

    /* access modifiers changed from: protected */
    public CanItemTextBtnList AddItem(int line, int text, int id) {
        CanItemTextBtnList item = new CanItemTextBtnList((Context) this, text);
        item.ShowArrow(false);
        item.SetIdClickListener(this, id);
        this.mManager.AddView(item.GetView(), 0, (line * 85) + 10, -2, 85);
        return item;
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
        CanJni.FordGetSetup(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mImgPlan.setBackgroundResource(mEcoModeArr[this.mSetData.Plane & 7]);
            this.mImgSpeed.setBackgroundResource(mEcoModeArr[this.mSetData.Speed & 7]);
            this.mItemEngHot.ShowGone(this.mSetData.EHotShow);
            this.mImgHot.Show(this.mSetData.EHotShow);
            if (this.mSetData.EHotShow != 0) {
                this.mItemEngHot.SetVal(String.valueOf(String.format("%d", new Object[]{Integer.valueOf(this.mSetData.EHotVal)})) + "%" + this.mStrHot);
            }
        }
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onChanged(int id, int pos) {
    }
}
