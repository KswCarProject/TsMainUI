package com.ts.can.volvo.lz;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemMsgBox;

public class CanVolvoLZSystemSetView extends CanScrollCarInfoView {
    private static final int HFXTXX = 3;
    private static final int JLYRLDW = 1;
    private static final int LANGUAGEE = 0;
    private static final int WDDW = 2;
    private CanDataInfo.VolvoXc60_CarSet mSetData;

    public CanVolvoLZSystemSetView(Activity activity) {
        super(activity, 4);
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.VolvoLzCx60CarSet(16, item);
                return;
            case 1:
                CanJni.VolvoLzCx60CarSet(17, item);
                return;
            case 2:
                CanJni.VolvoLzCx60CarSet(18, item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 3:
                new CanItemMsgBox(((Integer) v.getTag()).intValue(), getActivity(), R.string.can_sure_setup, new CanItemMsgBox.onMsgBoxClick() {
                    public void onOK(int param) {
                        CanJni.VolvoLzCx60Restor(1);
                    }
                });
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_lang_set, R.string.can_volvo_jlyrl_dw, R.string.can_temp_dw, R.string.can_volvo_hfxtxx};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TITLE};
        this.mPopValueIds[0] = new int[]{R.string.can_language_chinese, R.string.can_language_english};
        this.mPopValueIds[1] = new int[]{R.string.can_fuels_lkm, R.string.can_fuels_kml};
        this.mPopValueIds[2] = new int[]{R.string.can_temperature_c, R.string.can_temperature_f};
        this.mSetData = new CanDataInfo.VolvoXc60_CarSet();
    }

    public void ResetData(boolean check) {
        CanJni.VolvoLzCx60GetCarSet(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(new int[]{this.mSetData.Lang, this.mSetData.Jlyrldw, this.mSetData.Wddw});
        }
    }

    public void QueryData() {
        CanJni.VolvoLzCx60Query(124, 0);
    }
}
