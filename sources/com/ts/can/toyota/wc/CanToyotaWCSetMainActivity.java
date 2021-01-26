package com.ts.can.toyota.wc;

import android.os.Bundle;
import android.view.View;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.canview.CanItemIcoList;
import com.ts.canview.CanScrollList;

public class CanToyotaWCSetMainActivity extends CanToyotaWCBaseActivity implements View.OnClickListener {
    public static final int ITEM_AC = 2;
    public static final int ITEM_AMP = 4;
    public static final int ITEM_LANG = 6;
    public static final int ITEM_LIGHT = 3;
    public static final int ITEM_LOCK = 1;
    public static final int ITEM_OTHER = 5;
    protected static final String TAG = "CanToyotaSetMainActivity";
    protected CanItemIcoList mItemAc;
    protected CanItemIcoList mItemAmp;
    protected CanItemIcoList mItemLang;
    protected CanItemIcoList mItemLight;
    protected CanItemIcoList mItemLock;
    protected CanItemIcoList mItemOther;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_can_comm_list);
        this.mItemLock = new CanItemIcoList(this, R.drawable.can_icon_lock3, R.string.can_car_lock_set);
        this.mItemAc = new CanItemIcoList(this, R.drawable.can_icon_ac, R.string.can_ac_set);
        this.mItemLight = new CanItemIcoList(this, R.drawable.can_icon_light, R.string.can_light_set);
        this.mItemAmp = new CanItemIcoList(this, R.drawable.can_icon_setup, R.string.can_eq);
        this.mItemOther = new CanItemIcoList(this, R.drawable.can_icon_setup, R.string.can_other_set);
        this.mItemLang = new CanItemIcoList(this, R.drawable.can_icon_light2, R.string.can_car_lang);
        this.mItemLock.SetIdClickListener(this, 1);
        this.mItemAc.SetIdClickListener(this, 2);
        this.mItemLight.SetIdClickListener(this, 3);
        this.mItemAmp.SetIdClickListener(this, 4);
        this.mItemOther.SetIdClickListener(this, 5);
        this.mItemLang.SetIdClickListener(this, 6);
        CanScrollList sl = new CanScrollList(this);
        sl.AddView(this.mItemLock.GetView());
        sl.AddView(this.mItemAc.GetView());
        sl.AddView(this.mItemLight.GetView());
        sl.AddView(this.mItemAmp.GetView());
        sl.AddView(this.mItemOther.GetView());
        sl.AddView(this.mItemLang.GetView());
        if (CanJni.GetSubType() != 0) {
            this.mItemAmp.ShowGone(false);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        CarSet(5, 1, 98);
        CarSet(5, 1, 166);
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                enterSubWin(CanToyotaWCSetLockActivity.class);
                return;
            case 2:
                enterSubWin(CanToyotaWCSetAcActivity.class);
                return;
            case 3:
                enterSubWin(CanToyotaWCSetLightActivity.class);
                return;
            case 4:
                enterSubWin(CanToyotaWcAmpActivity.class);
                return;
            case 5:
                enterSubWin(CanToyotaWCSetOtherActivity.class);
                return;
            case 6:
                enterSubWin(CanToyotaWCSetLangActivity.class);
                return;
            default:
                return;
        }
    }
}
