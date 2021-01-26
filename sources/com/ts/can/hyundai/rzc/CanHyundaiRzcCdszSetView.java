package com.ts.can.hyundai.rzc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;

public class CanHyundaiRzcCdszSetView extends CanScrollCarInfoView {
    private static final int ITEM_DLSJXHL = 7;
    private static final int ITEM_DLSJXHL_CTXT = 8;
    private static final int ITEM_DLSJXHL_DCBY = 11;
    private static final int ITEM_DLSJXHL_DZSB = 10;
    private static final int ITEM_DLSJXHL_KTXT = 9;
    private static final int ITEM_SZCDL_KC = 1;
    private static final int ITEM_SZCDL_KC_DLBFB = 3;
    private static final int ITEM_SZCDL_KC_XHLC = 2;
    private static final int ITEM_SZCDL_ZC = 4;
    private static final int ITEM_SZCDL_ZC_DLBFB = 6;
    private static final int ITEM_SZCDL_ZC_XHLC = 5;
    private static final int ITEM_YJCDSXSJ_KC = 0;
    private String[] mDlbfbArr;
    private CanDataInfo.HyRzcXnySet4 mSetData;

    public CanHyundaiRzcCdszSetView(Activity activity) {
        super(activity, 12);
    }

    public void onPositiveItem(int id, int[] item) {
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 3:
                CanJni.HyundaiRzcXnySet(8, (item * 10) + 50, 0, 0);
                return;
            case 6:
                CanJni.HyundaiRzcXnySet(9, (item * 10) + 50, 0, 0);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_yjcdsxsj_kscd, R.string.can_szcdl_kc, R.string.can_range_xhlc, R.string.can_dlbfb, R.string.can_szcdl_zc, R.string.can_range_xhlc, R.string.can_dlbfb, R.string.can_dlsjxhl, R.string.can_ctxt, R.string.can_ktxt, R.string.can_dzsb, R.string.can_dcby};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE};
        this.mPopValueIds[3] = new int[]{R.string.app_name, R.string.app_name};
        this.mPopValueIds[6] = new int[]{R.string.app_name, R.string.app_name};
        this.mSetData = new CanDataInfo.HyRzcXnySet4();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
        this.mDlbfbArr = new String[]{"50%", "60%", "70%", "80%", "90%", "100%"};
        getScrollManager().RemoveView(3);
        this.mItemObjects[3] = getScrollManager().addItemPopupList(this.mItemTitleIds[3], this.mDlbfbArr, 3, this, 3);
        getScrollManager().RemoveView(6);
        this.mItemObjects[6] = getScrollManager().addItemPopupList(this.mItemTitleIds[6], this.mDlbfbArr, 6, this, 6);
    }

    public void ResetData(boolean check) {
        CanJni.HyundaiRzcGetXnySet4(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.Yjcdsxsj, String.format("%02d:%02d", new Object[]{Integer.valueOf(this.mSetData.Yjcdsxsj / 60), Integer.valueOf(this.mSetData.Yjcdsxsj % 60)}));
            updateItem(2, this.mSetData.Szcdl_Kc_Xhlc, String.format("%dKM", new Object[]{Integer.valueOf(this.mSetData.Szcdl_Kc_Xhlc)}));
            updateItem(3, getDlbfb(this.mSetData.Szcdl_Kc_Dlbfb));
            updateItem(5, this.mSetData.Szcdl_Xhlc, String.format("%dKM", new Object[]{Integer.valueOf(this.mSetData.Szcdl_Xhlc)}));
            updateItem(6, getDlbfb(this.mSetData.Szcdl_Dlbfb));
            updateItem(8, this.mSetData.CtxtSjxhl, String.format("%dKW", new Object[]{Integer.valueOf(this.mSetData.CtxtSjxhl)}));
            updateItem(9, this.mSetData.KtxtSjxhl, String.format("%.2fKW", new Object[]{Double.valueOf(((double) this.mSetData.KtxtSjxhl) * 0.01d)}));
            updateItem(10, this.mSetData.DzsbSjxhl, String.format("%.2fKW", new Object[]{Double.valueOf(((double) this.mSetData.DzsbSjxhl) * 0.01d)}));
            updateItem(11, this.mSetData.DcbySjxhl, String.format("%.2fKW", new Object[]{Double.valueOf(((double) this.mSetData.DcbySjxhl) * 0.01d)}));
        }
    }

    private int getDlbfb(int szcdl_Kc_Dlbfb) {
        switch (szcdl_Kc_Dlbfb) {
            case 25:
                return 0;
            case 30:
                return 1;
            case 35:
                return 2;
            case 40:
                return 3;
            case 45:
                return 4;
            case 50:
                return 5;
            default:
                return 0;
        }
    }

    public void QueryData() {
        CanJni.HyundaiRzcXnySet(255, 1, 0, 0);
        Sleep(10);
        CanJni.HyundaiRzcQuery(86, 0);
    }
}
