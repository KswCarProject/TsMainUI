package com.ts.can.geely.boy;

import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCommonActivity;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanScrollList;

public class CanGeelyBoySetOtherActivity extends CanCommonActivity implements CanItemPopupList.onPopItemClick {
    private static final int ITEM_LANGUAGE = 0;
    private static final int ITEM_MODE = 1;
    private static int[] mLanguageArray = {R.string.can_lang_cn, R.string.can_lang_en};
    private static String[] mModeArray;
    private CanItemPopupList mItemLanguage;
    private CanItemPopupList mItemMode;
    private CanDataInfo.GeelyBoy_Settings mSetData = new CanDataInfo.GeelyBoy_Settings();

    public void onClick(View v) {
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.GeelyBoyCarSet(0, item);
                return;
            case 1:
                CanJni.GeelyBoyCarSet(5, item);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public int GetContentLayoutId() {
        return R.layout.activity_can_comm_list;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        mModeArray = getResources().getStringArray(R.array.can_geely_boy_mode);
        CanScrollList manager = new CanScrollList(this);
        this.mItemLanguage = manager.addItemPopupList(R.string.can_lang_set, mLanguageArray, 0, (CanItemPopupList.onPopItemClick) this);
        this.mItemMode = manager.addItemPopupList(R.string.can_geely_boy_mode, mModeArray, 1, (CanItemPopupList.onPopItemClick) this);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.GeelyBoyGetInfo(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            this.mItemLanguage.SetSel(this.mSetData.Lang);
            this.mItemMode.SetSel(this.mSetData.Dzzlmsxz);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.GeelyBoyCarQuery(64, 0);
    }
}
