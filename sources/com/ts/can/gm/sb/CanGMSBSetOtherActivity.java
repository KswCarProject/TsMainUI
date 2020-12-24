package com.ts.can.gm.sb;

import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCommonActivity;
import com.ts.canview.CanItemMsgBox;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;

public class CanGMSBSetOtherActivity extends CanCommonActivity implements CanItemPopupList.onPopItemClick, CanItemMsgBox.onMsgBoxClick {
    public static final int ITEM_FS_SET = 3;
    public static final int ITEM_LANGUAGE = 1;
    public static final int ITEM_RADAR_TOGGLE = 2;
    private static final int[] mLangArray = {R.string.can_lang_en, R.string.can_language_chinese};
    private CanItemSwitchList mItemRadar;
    private CanScrollList mManager;
    private CanDataInfo.GM_Radar mRadarData = new CanDataInfo.GM_Radar();

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 2:
                CanJni.GMRadarCtrl(Neg(this.mRadarData.fgOn));
                return;
            case 3:
                new CanItemMsgBox(3, this, R.string.can_sure_setup, this);
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        if (id == 1) {
            CanJni.GMCarCtrl(32, item);
        }
    }

    public void onOK(int param) {
        CanJni.GMCarCtrl(33, 1);
    }

    /* access modifiers changed from: protected */
    public int GetContentLayoutId() {
        return R.layout.activity_can_comm_list;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        this.mManager.addItemPopupList(R.string.can_lang_set, mLangArray, 1, (CanItemPopupList.onPopItemClick) this);
        this.mItemRadar = this.mManager.addItemCheckBox(R.string.can_r_radar_sw, 2, this);
        this.mManager.addItemFsSetList(R.string.can_factory_set, 3, this);
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.GMGetCarRadar(this.mRadarData);
        if (!i2b(this.mRadarData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mRadarData.Update)) {
            this.mRadarData.Update = 0;
            this.mItemRadar.SetCheck(this.mRadarData.fgOn);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
    }
}
