package com.ts.can.chrysler;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.MainUI.UserCallBack;
import com.ts.can.CanFunc;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanChrOthSetLightActivity extends CanChrOthBaseActivity implements View.OnClickListener, UserCallBack, CanItemPopupList.onPopItemClick, CanItemProgressList.onPosChange {
    public static final int ITEM_CORNERINGLIGHTS = 7;
    public static final int ITEM_DDYCXM = 1;
    public static final int ITEM_GREETINGLIGHTS = 6;
    public static final int ITEM_HEADLIGHTSENS = 5;
    public static final int ITEM_HSJTGJ = 9;
    public static final int ITEM_INTERIOR_AMBIENT_LIGHT = 11;
    public static final int ITEM_KJDDLQ = 3;
    private static final int ITEM_MAX = 12;
    private static final int ITEM_MIN = 1;
    public static final int ITEM_QDYSSZDQDDD = 8;
    public static final int ITEM_RXD = 4;
    public static final int ITEM_SCSZXDSS = 2;
    public static final int ITEM_ZDFXYGD = 10;
    public static final int ITEM_ZSYYGD = 12;
    public static final String TAG = "CanChrOthSetLightActivity";
    private static final int[] mDdycxmArr = {R.string.can_0s, R.string.can_30s, R.string.can_60s, R.string.can_90s};
    private static final int[] mKheadlightsens = {R.string.can_headlightsens_1, R.string.can_headlightsens_2, R.string.can_headlightsens_3};
    private static final int[] mKjddlqArr = {R.string.can_0s, R.string.can_30s, R.string.can_60s, R.string.can_90s};
    protected CanItemSwitchList mItemCorneringlights;
    protected CanItemPopupList mItemDdycxm;
    protected CanItemSwitchList mItemGreetinglights;
    protected CanItemPopupList mItemHeadlightsens;
    protected CanItemSwitchList mItemHsjtgj;
    protected CanItemProgressList mItemInterAmbLight;
    protected CanItemPopupList mItemKjddlq;
    protected CanItemSwitchList mItemQdysszdqddd;
    protected CanItemSwitchList mItemRxd;
    protected CanItemSwitchList mItemScszxdss;
    protected CanItemSwitchList mItemZdfxygd;
    protected CanItemSwitchList mItemZsyygd;
    private CanScrollList mManager;
    private boolean mbLayout;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        InitUI();
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        GetSetData();
        if (i2b(this.mSetData.LightsUpdateOnce) && (!check || i2b(this.mSetData.LightsUpdate))) {
            this.mSetData.LightsUpdate = 0;
            this.mItemDdycxm.SetSel(this.mSetData.HeadlightDelay / 30);
            this.mItemScszxdss.SetCheck(this.mSetData.FlashLights);
            this.mItemKjddlq.SetSel(this.mSetData.Illnminated / 30);
            this.mItemRxd.SetCheck(this.mSetData.DaytimeLights);
            this.mItemHeadlightsens.SetSel(this.mSetData.HeadlightSens);
            this.mItemGreetinglights.SetCheck(this.mSetData.GreetingLights);
            this.mItemCorneringlights.SetCheck(this.mSetData.CornerLights);
            this.mItemQdysszdqddd.SetCheck(this.mSetData.Qdygzdqddd);
            this.mItemHsjtgj.SetCheck(this.mSetData.Hsjtgj);
            this.mItemZdfxygd.SetCheck(this.mSetData.Zdfxygd);
            this.mItemZsyygd.SetCheck(this.mSetData.Zsyygd);
        }
        if (!i2b(this.mSetData.Light2sUpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Light2sUpdate)) {
            this.mSetData.Light2sUpdate = 0;
            this.mItemInterAmbLight.SetCurVal(this.mSetData.InteriorAmbientLight);
            this.mItemInterAmbLight.SetValText(String.valueOf(this.mSetData.InteriorAmbientLight));
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        Query(64, 32);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        LayoutUI();
        ResetData(false);
        QueryData();
        Log.d("CanChrOthSetLightActivity", "-----onResume-----");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        MainTask.GetInstance().SetUserCallBack((UserCallBack) null);
        super.onPause();
        Log.d("CanChrOthSetLightActivity", "-----onPause-----");
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mItemScszxdss = AddCheckItem(R.string.can_scszxdss, 2);
        this.mItemRxd = AddCheckItem(R.string.can_rjxcd, 4);
        this.mItemDdycxm = AddPopupItem(R.string.can_car_ddycxm, mDdycxmArr, 1);
        this.mItemKjddlq = AddPopupItem(R.string.can_kjddlq, mKjddlqArr, 3);
        this.mItemHeadlightsens = AddPopupItem(R.string.can_headlightsens, mKheadlightsens, 5);
        this.mItemGreetinglights = AddCheckItem(R.string.can_greetingLights, 6);
        this.mItemCorneringlights = AddCheckItem(R.string.can_corneringLights, 7);
        this.mItemQdysszdqddd = AddCheckItem(R.string.can_jp_qdygszdqddd, 8);
        this.mItemHsjtgj = AddCheckItem(R.string.can_jp_hsjtgj, 9);
        this.mItemZdfxygd = AddCheckItem(R.string.can_jp_zdfxygd, 10);
        this.mItemInterAmbLight = this.mManager.addItemProgressList(R.string.can_car_inner_light, 11, (CanItemProgressList.onPosChange) this);
        this.mItemInterAmbLight.SetMinMax(0, 6);
        this.mItemInterAmbLight.SetStep(1);
        this.mItemInterAmbLight.SetUserValText();
        this.mItemZsyygd = AddCheckItem(R.string.can_zsyygd, 12);
        if (CanFunc.getInstance().IsCustomShow("Jeep")) {
            findViewById(R.id.can_comm_list_layout).setBackgroundResource(R.drawable.can_grand_cherokee_bg);
        }
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        GetAdtData();
        for (int i = 1; i <= 12; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean IsHaveItem(int item) {
        int ret = 0;
        switch (item) {
            case 1:
                ret = this.mAdtData.HeadlightDelay;
                break;
            case 2:
                ret = this.mAdtData.FlashLights;
                break;
            case 3:
                ret = this.mAdtData.Illnminated;
                break;
            case 4:
                ret = this.mAdtData.DaytimeLights;
                break;
            case 5:
                ret = this.mAdtData.HeadlightSens;
                break;
            case 6:
                ret = this.mAdtData.GreetingLights;
                break;
            case 7:
                ret = this.mAdtData.CornerLights;
                break;
            case 8:
                ret = this.mAdtData.Qdygzdqddd;
                break;
            case 9:
                ret = this.mAdtData.Hsjtgj;
                break;
            case 10:
                ret = this.mAdtData.Zdfxygd;
                break;
            case 11:
                ret = this.mAdtData.InteriorAmbientLight;
                break;
            case 12:
                ret = this.mAdtData.Zsyygd;
                break;
        }
        return i2b(ret);
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        boolean show = IsHaveItem(item);
        switch (item) {
            case 1:
                this.mItemDdycxm.ShowGone(show);
                return;
            case 2:
                this.mItemScszxdss.ShowGone(show);
                return;
            case 3:
                this.mItemKjddlq.ShowGone(show);
                return;
            case 4:
                this.mItemRxd.ShowGone(show);
                return;
            case 5:
                this.mItemHeadlightsens.ShowGone(show);
                return;
            case 6:
                this.mItemGreetinglights.ShowGone(show);
                return;
            case 7:
                this.mItemCorneringlights.ShowGone(show);
                return;
            case 8:
                this.mItemQdysszdqddd.ShowGone(show);
                return;
            case 9:
                this.mItemHsjtgj.ShowGone(show);
                return;
            case 10:
                this.mItemZdfxygd.ShowGone(show);
                return;
            case 11:
                this.mItemInterAmbLight.ShowGone(show);
                return;
            case 12:
                this.mItemZsyygd.ShowGone(show);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public CanItemSwitchList AddCheckItem(int resId, int Id) {
        CanItemSwitchList item = new CanItemSwitchList(this, resId);
        item.SetIdClickListener(this, Id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(false);
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopupItem(int text, int[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, val, (CanItemPopupList.onPopItemClick) this);
        item.SetId(id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(false);
        return item;
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 2:
                CarSWSet(35, this.mSetData.FlashLights);
                return;
            case 4:
                CarSWSet(36, this.mSetData.DaytimeLights);
                return;
            case 6:
                CarSWSet(42, this.mSetData.GreetingLights);
                return;
            case 7:
                CarSWSet(37, this.mSetData.CornerLights);
                return;
            case 8:
                CarSWSet(38, this.mSetData.Qdygzdqddd);
                return;
            case 9:
                CarSWSet(39, this.mSetData.Hsjtgj);
                return;
            case 10:
                CarSWSet(40, this.mSetData.Zdfxygd);
                return;
            case 12:
                CarSWSet(44, this.mSetData.Zsyygd);
                return;
            default:
                return;
        }
    }

    public void UserAll() {
        ResetData(true);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 1:
                CarSet(32, item * 30);
                return;
            case 3:
                CarSet(33, item * 30);
                return;
            case 5:
                CarSet(41, item + 1);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 11:
                CarSet(43, pos);
                return;
            default:
                return;
        }
    }
}
