package com.ts.can.lexus.is250;

import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCommonActivity;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanScrollList;

public class CanLexusIs250CarInfosActivity extends CanCommonActivity implements CanItemPopupList.onPopItemClick {
    private static final int ITEM_RADIO_AREA = 1;
    private CanItemPopupList mItemRadioArea;
    private CanDataInfo.Is250_Radio_Area mRadioAreaData = new CanDataInfo.Is250_Radio_Area();
    private int[] mRadioAreas = {R.string.can_lexus_is250_area_0, R.string.can_lexus_is250_area_1, R.string.can_lexus_is250_area_2, R.string.can_lexus_is250_area_3, R.string.can_lexus_is250_area_4};

    public void onClick(View v) {
    }

    public void onItem(int id, int item) {
        CanJni.LexusIs250SetRadioInfo(item);
    }

    /* access modifiers changed from: protected */
    public int GetContentLayoutId() {
        return R.layout.activity_can_comm_list;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mItemRadioArea = new CanScrollList(this).addItemPopupList(R.string.can_lexus_is250_radio_area, this.mRadioAreas, 1, (CanItemPopupList.onPopItemClick) this);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.LexusIs250GetRadioInfo(this.mRadioAreaData);
        if (!i2b(this.mRadioAreaData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mRadioAreaData.Update)) {
            this.mRadioAreaData.Update = 0;
            this.mItemRadioArea.SetSel(this.mRadioAreaData.Area);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.LexusIs250Query(50, 0);
    }
}
