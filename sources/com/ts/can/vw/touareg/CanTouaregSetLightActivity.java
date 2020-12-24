package com.ts.can.vw.touareg;

import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanBaseActivity;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanTouaregSetLightActivity extends CanBaseActivity implements View.OnClickListener, UserCallBack, CanItemProgressList.onPosChange, CanItemPopupList.onPopItemClick {
    public static final int ITEM_CNZM = 7;
    public static final int ITEM_CWZM = 3;
    public static final int ITEM_HJZM = 5;
    public static final int ITEM_JBKJZM = 4;
    public static final int ITEM_KZXS = 1;
    public static final int ITEM_LJZM = 6;
    public static final int ITEM_ZNDD = 2;
    private String[] mCommingArray = new String[3];
    private CanItemProgressList mItemCarInsideLight;
    private CanItemPopupList mItemCommingFunc;
    private CanItemSwitchList mItemCwzm;
    private CanItemProgressList mItemFootLight;
    private CanItemSwitchList mItemKzxs;
    private CanItemPopupList mItemLeavingFunc;
    private CanItemSwitchList mItemZndd;
    private String[] mLeavingArray = new String[4];
    private CanDataInfo.TouaregWcLight mLightData = new CanDataInfo.TouaregWcLight();
    private CanScrollList mManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        this.mLeavingArray = getResources().getStringArray(R.array.can_df_jyx5_ddys);
        for (int i = 0; i < this.mCommingArray.length; i++) {
            this.mCommingArray[i] = this.mLeavingArray[i + 1];
        }
        InitUI();
    }

    private void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemKzxs = this.mManager.addItemCheckBox(R.string.can_drive_left, 1, this);
        this.mItemZndd = this.mManager.addItemCheckBox(R.string.can_zndd, 2, this);
        this.mItemCwzm = this.mManager.addItemCheckBox(R.string.can_cwzm_rjxcd, 3, this);
        this.mItemCommingFunc = this.mManager.addItemPopupList(R.string.can_coming_func, this.mCommingArray, 5, (CanItemPopupList.onPopItemClick) this);
        this.mItemLeavingFunc = this.mManager.addItemPopupList(R.string.can_leaving_func, this.mLeavingArray, 6, (CanItemPopupList.onPopItemClick) this);
        this.mItemFootLight = this.mManager.addItemProgressList(R.string.can_foot_light, 4, (CanItemProgressList.onPosChange) this);
        this.mItemFootLight.SetStep(10);
        this.mItemFootLight.SetMinMax(0, 100);
        this.mItemFootLight.SetUserValText();
        this.mItemCarInsideLight = this.mManager.addItemProgressList(R.string.can_interior_light, 7, (CanItemProgressList.onPosChange) this);
        this.mItemCarInsideLight.SetStep(10);
        this.mItemCarInsideLight.SetMinMax(0, 100);
        this.mItemCarInsideLight.SetUserValText();
        this.mItemKzxs.ShowGone(false);
        this.mItemZndd.ShowGone(false);
        this.mItemCwzm.ShowGone(false);
        this.mItemCommingFunc.ShowGone(false);
        this.mItemLeavingFunc.ShowGone(false);
        this.mItemFootLight.ShowGone(false);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
        CanJni.TouaregQuery(5, 1, 104);
    }

    private void ResetData(boolean check) {
        CanJni.TouaregGetLightData(this.mLightData);
        if (!i2b(this.mLightData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mLightData.Update)) {
            this.mLightData.Update = 0;
            this.mItemKzxs.ShowGone(this.mLightData.KzxsppAvalid);
            this.mItemZndd.ShowGone(this.mLightData.ZnddAvalid);
            this.mItemCwzm.ShowGone(this.mLightData.CwzmAvalid);
            this.mItemFootLight.ShowGone(this.mLightData.JbkjzmAvalid);
            this.mItemCommingFunc.ShowGone(this.mLightData.HjzmAvalid);
            this.mItemLeavingFunc.ShowGone(this.mLightData.LjzmAvalid);
            this.mItemKzxs.SetCheck(this.mLightData.Kzxspp);
            this.mItemZndd.SetCheck(this.mLightData.Zndd);
            this.mItemCwzm.SetCheck(this.mLightData.Cwzm);
            this.mItemCommingFunc.SetSel(this.mLightData.Hjzm - 1);
            this.mItemLeavingFunc.SetSel(this.mLightData.Ljzm);
            this.mItemFootLight.SetCurVal(this.mLightData.Jbkjzm);
            this.mItemFootLight.SetValText(String.valueOf(this.mLightData.Jbkjzm) + "%");
            this.mItemCarInsideLight.SetCurVal(this.mLightData.Cnzmdld);
            this.mItemCarInsideLight.SetValText(String.valueOf(this.mLightData.Cnzmdld) + "%");
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                CanJni.TouaregLightSet(19, Neg(this.mLightData.Kzxspp));
                return;
            case 2:
                CanJni.TouaregLightSet(18, Neg(this.mLightData.Zndd));
                return;
            case 3:
                CanJni.TouaregLightSet(16, Neg(this.mLightData.Cwzm));
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 4:
                CanJni.TouaregLightSet(15, pos);
                return;
            case 7:
                CanJni.TouaregLightSet(17, pos);
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 5:
                CanJni.TouaregLightSet(13, item + 1);
                return;
            case 6:
                CanJni.TouaregLightSet(14, item);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }
}
