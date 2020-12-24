package com.ts.can.gm.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCarInfoSub1Activity;
import com.ts.can.CanScrollCarInfoView;

public class CanGMCarInfoView extends CanScrollCarInfoView {
    public static final int ITEM_AC = 2;
    public static final int ITEM_CAR_TYPE = 0;
    public static final int ITEM_CDS = 5;
    public static final int ITEM_CONV = 4;
    public static final int ITEM_HYBRID = 8;
    public static final int ITEM_LANGUAGE = 7;
    public static final int ITEM_LIGHT = 3;
    public static final int ITEM_LOCK = 1;
    private static final int ITEM_MAX = 8;
    private static final int ITEM_MIN = 0;
    public static final int ITEM_OTHER = 6;
    public static final String TAG = "CanGMCarInfoActivity";
    private CanDataInfo.GM_AdtAll mAdtAllData = new CanDataInfo.GM_AdtAll();
    private CanDataInfo.GM_Hybrid mAdtHybrid = new CanDataInfo.GM_Hybrid();
    private boolean mbLayout;

    public CanGMCarInfoView(Activity activity) {
        super(activity, 9);
    }

    public void onItem(int id, int item) {
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        enterSubWin(CanCarInfoSub1Activity.class, ((Integer) v.getTag()).intValue());
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_car_type_select, R.string.can_car_lock_set, R.string.can_ac_set, R.string.can_c4_l_light, R.string.can_sshbl, R.string.can_cds, R.string.can_other_set, R.string.can_lang_set, R.string.can_hybrid_image};
        this.mItemIcons = new int[]{R.drawable.can_icon_esc, R.drawable.can_icon_lock, R.drawable.can_icon_ac, R.drawable.can_icon_light, R.drawable.can_icon_service, R.drawable.can_icon_cds, R.drawable.can_icon_setup, R.drawable.can_icon_tyres, R.drawable.can_icon_hybrid};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON, CanScrollCarInfoView.Item.ICON};
    }

    public void ResetData(boolean check) {
    }

    public void QueryData() {
        CanJni.GMQuery(71);
    }

    /* access modifiers changed from: protected */
    public void LayoutUI() {
        for (int i = 0; i <= 8; i++) {
            ShowItem(i);
        }
    }

    /* access modifiers changed from: protected */
    public int IsHaveItem(int item) {
        switch (item) {
            case 0:
                return 1;
            case 1:
                return this.mAdtAllData.AutoLock + this.mAdtAllData.RmtLock;
            case 2:
                return this.mAdtAllData.AC;
            case 3:
                return this.mAdtAllData.Light;
            case 4:
                return this.mAdtAllData.Conv;
            case 5:
                return this.mAdtAllData.Pzjc;
            case 6:
                return 1;
            case 7:
                return 1;
            default:
                return 0;
        }
    }

    /* access modifiers changed from: protected */
    public void ShowItem(int item) {
        showItem(item, IsHaveItem(item));
    }
}
