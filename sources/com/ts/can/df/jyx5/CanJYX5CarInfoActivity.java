package com.ts.can.df.jyx5;

import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanCommonActivity;
import com.ts.canview.CanItemCarType;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class CanJYX5CarInfoActivity extends CanCommonActivity implements CanItemPopupList.onPopItemClick {
    private static final int ITEM_CAR_TYPE = 4;
    private static final int ITEM_CSSS = 2;
    private static final int ITEM_DDYS = 0;
    private static final int ITEM_JDSJ = 1;
    private static final int ITEM_WHSJZDZD = 7;
    private static final int ITEM_YKGC = 6;
    private static final int ITEM_YKSCFK = 5;
    private static final int ITEM_ZDCSS = 3;
    private static String[] mCarTypeArray;
    private static String[] mDdysArray;
    private static String[] mJdsjArray;
    private CanDataInfo.JyX5_CarInfo mCarInfo = new CanDataInfo.JyX5_CarInfo();
    private CanItemCarType mItemCarType;
    private CanItemSwitchList mItemCsss;
    private CanItemPopupList mItemDdys;
    private CanItemPopupList mItemJdsj;
    private CanItemSwitchList mItemWhsjzdzd;
    private CanItemPopupList mItemYkgc;
    private CanItemPopupList mItemYkscfk;
    private CanItemSwitchList mItemZdcss;
    protected int[] mYkgcArr = {R.string.can_shortkey, R.string.can_longkey};
    protected int[] mYkscfkArr = {R.string.can_only_light, R.string.can_dghlb};

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 2:
                CanJni.DfJyX5CarSet(2, Neg(this.mCarInfo.Csss));
                return;
            case 3:
                CanJni.DfJyX5CarSet(3, Neg(this.mCarInfo.Zdcss));
                return;
            case 7:
                CanJni.DfJyX5CarSet(6, Neg(this.mCarInfo.Whsjzdzd));
                return;
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.DfJyX5CarSet(0, item);
                return;
            case 1:
                CanJni.DfJyX5CarSet(1, item);
                return;
            case 4:
                FtSet.SetCanSubT(item);
                Mcu.SendXKey(20);
                return;
            case 5:
                CanJni.DfJyX5CarSet(4, item);
                return;
            case 6:
                CanJni.DfJyX5CarSet(5, item);
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
        mDdysArray = getResources().getStringArray(R.array.can_df_jyx5_ddys);
        mJdsjArray = getResources().getStringArray(R.array.can_df_jyx5_jdsj);
        mCarTypeArray = getResources().getStringArray(R.array.can_jyx5_car_type_array);
        CanScrollList manager = new CanScrollList(this);
        this.mItemCarType = manager.addItemCarType(R.string.can_car_type_select, mCarTypeArray, 4, (CanItemPopupList.onPopItemClick) this);
        this.mItemDdys = manager.addItemPopupList(R.string.can_df_jyx5_ddys, mDdysArray, 0, (CanItemPopupList.onPopItemClick) this);
        this.mItemJdsj = manager.addItemPopupList(R.string.can_df_jyx5_jdsj, mJdsjArray, 1, (CanItemPopupList.onPopItemClick) this);
        this.mItemYkscfk = manager.addItemPopupList(R.string.can_ykdglbfk, this.mYkscfkArr, 5, (CanItemPopupList.onPopItemClick) this);
        this.mItemYkgc = manager.addItemPopupList(R.string.can_wind_remote, this.mYkgcArr, 6, (CanItemPopupList.onPopItemClick) this);
        this.mItemCsss = manager.addItemCheckBox(R.string.can_df_jyx5_csss, 2, this);
        this.mItemZdcss = manager.addItemCheckBox(R.string.can_df_jyx5_zdcss, 3, this);
        this.mItemWhsjzdzd = manager.addItemCheckBox(R.string.can_zdhsjzd, 7, this);
        this.mItemCarType.GetPopItem().SetSel(CanJni.GetSubType());
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        CanJni.DfJyX5GetInfo(this.mCarInfo);
        if (!i2b(this.mCarInfo.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mCarInfo.Update)) {
            this.mCarInfo.Update = 0;
            this.mItemDdys.SetSel(this.mCarInfo.Ddys);
            this.mItemJdsj.SetSel(this.mCarInfo.Jdsj);
            this.mItemYkscfk.SetSel(this.mCarInfo.Ykscfk);
            this.mItemYkgc.SetSel(this.mCarInfo.Ykgc);
            this.mItemCsss.SetCheck(this.mCarInfo.Csss);
            this.mItemZdcss.SetCheck(this.mCarInfo.Zdcss);
            this.mItemWhsjzdzd.SetCheck(this.mCarInfo.Whsjzdzd);
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        CanJni.DfJyX5CarQuery(64);
    }
}
