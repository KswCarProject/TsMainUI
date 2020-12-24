package com.ts.can.gac.trumpchi_wc;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanNumInuptDlg;
import java.util.ArrayList;

public class CanTrumpchiWcChargSetView extends CanScrollCarInfoView {
    public static final int ITEM_CHARG_JSSJ_H = 4;
    public static final int ITEM_CHARG_JSSJ_M = 5;
    public static final int ITEM_CHARG_KSSJ_H = 2;
    public static final int ITEM_CHARG_KSSJ_M = 3;
    public static final int ITEM_CHARG_MODE = 0;
    public static final int ITEM_CHARG_XHMS = 6;
    public static final int ITEM_CHARG_XHRQ = 7;
    public static final int ITEM_CHARG_YYSZ = 1;
    /* access modifiers changed from: private */
    public CanDataInfo.GCWcChargData mSetData;

    public CanTrumpchiWcChargSetView(Activity activity) {
        super(activity, 8);
    }

    public void onPositiveItem(int id, int[] item) {
        switch (id) {
            case 7:
                int itemNum = 0;
                for (int cycSta : item) {
                    itemNum += getCycSta(cycSta);
                }
                CanJni.TrumpchiWcChargSet(this.mSetData.Mode, this.mSetData.StartTimeH, this.mSetData.StartTimeM, this.mSetData.EndTimeH, this.mSetData.EndTimeM, itemNum + getMode(this.mSetData.CycMode));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    public int getMode(int cycMode) {
        if (i2b(cycMode)) {
            return 128;
        }
        return 0;
    }

    private int getCycSta(int i) {
        switch (i) {
            case 0:
                return 64;
            case 1:
                return 32;
            case 2:
                return 16;
            case 3:
                return 8;
            case 4:
                return 4;
            case 5:
                return 2;
            case 6:
                return 1;
            default:
                return 0;
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                CanJni.TrumpchiWcChargSet(item, this.mSetData.StartTimeH, this.mSetData.StartTimeM, this.mSetData.EndTimeH, this.mSetData.EndTimeM, getMode(this.mSetData.CycMode) + this.mSetData.Week);
                return;
            case 6:
                CanJni.TrumpchiWcChargSet(this.mSetData.Mode, this.mSetData.StartTimeH, this.mSetData.StartTimeM, this.mSetData.EndTimeH, this.mSetData.EndTimeM, getMode(item) + this.mSetData.Week);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void onClick(View v) {
        int id = ((Integer) v.getTag()).intValue();
        switch (id) {
            case 2:
                new CanNumInuptDlg(getActivity(), new CanNumInuptDlg.onInputOK() {
                    public void onOK(String val, int num, int id) {
                        if (num >= 0 && num <= 23) {
                            CanJni.TrumpchiWcChargSet(CanTrumpchiWcChargSetView.this.mSetData.Mode, num, CanTrumpchiWcChargSetView.this.mSetData.StartTimeM, CanTrumpchiWcChargSetView.this.mSetData.EndTimeH, CanTrumpchiWcChargSetView.this.mSetData.EndTimeM, CanTrumpchiWcChargSetView.this.mSetData.Week + CanTrumpchiWcChargSetView.this.getMode(CanTrumpchiWcChargSetView.this.mSetData.CycMode));
                        }
                    }
                }, 2, id);
                return;
            case 3:
                new CanNumInuptDlg(getActivity(), new CanNumInuptDlg.onInputOK() {
                    public void onOK(String val, int num, int id) {
                        if (num >= 0 && num <= 59) {
                            CanJni.TrumpchiWcChargSet(CanTrumpchiWcChargSetView.this.mSetData.Mode, CanTrumpchiWcChargSetView.this.mSetData.StartTimeH, num, CanTrumpchiWcChargSetView.this.mSetData.EndTimeH, CanTrumpchiWcChargSetView.this.mSetData.EndTimeM, CanTrumpchiWcChargSetView.this.mSetData.Week + CanTrumpchiWcChargSetView.this.getMode(CanTrumpchiWcChargSetView.this.mSetData.CycMode));
                        }
                    }
                }, 2, id);
                return;
            case 4:
                new CanNumInuptDlg(getActivity(), new CanNumInuptDlg.onInputOK() {
                    public void onOK(String val, int num, int id) {
                        if (num >= 0 && num <= 23) {
                            CanJni.TrumpchiWcChargSet(CanTrumpchiWcChargSetView.this.mSetData.Mode, CanTrumpchiWcChargSetView.this.mSetData.StartTimeH, CanTrumpchiWcChargSetView.this.mSetData.StartTimeM, num, CanTrumpchiWcChargSetView.this.mSetData.EndTimeM, CanTrumpchiWcChargSetView.this.mSetData.Week + CanTrumpchiWcChargSetView.this.getMode(CanTrumpchiWcChargSetView.this.mSetData.CycMode));
                        }
                    }
                }, 2, id);
                return;
            case 5:
                new CanNumInuptDlg(getActivity(), new CanNumInuptDlg.onInputOK() {
                    public void onOK(String val, int num, int id) {
                        if (num >= 0 && num <= 59) {
                            CanJni.TrumpchiWcChargSet(CanTrumpchiWcChargSetView.this.mSetData.Mode, CanTrumpchiWcChargSetView.this.mSetData.StartTimeH, CanTrumpchiWcChargSetView.this.mSetData.StartTimeM, CanTrumpchiWcChargSetView.this.mSetData.EndTimeH, num, CanTrumpchiWcChargSetView.this.mSetData.Week + CanTrumpchiWcChargSetView.this.getMode(CanTrumpchiWcChargSetView.this.mSetData.CycMode));
                        }
                    }
                }, 2, id);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_charg_mode, R.string.can_gc_yysz, R.string.can_gc_yytime_h, R.string.can_gc_yytime_m, R.string.can_gc_yyztime_h, R.string.can_gc_yyztime_m, R.string.can_gc_xhms, R.string.can_gc_xhrq};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP_CHECK};
        this.mPopValueIds[0] = new int[]{R.string.can_gc_ljcd, R.string.can_gc_yycd};
        this.mPopValueIds[6] = new int[]{R.string.can_gc_dc, R.string.can_gc_xh};
        this.mPopCheckValueIds[7] = new int[]{R.array.can_gc_week_arrays};
        this.mSetData = new CanDataInfo.GCWcChargData();
    }

    public void ResetData(boolean check) {
        CanJni.TrumpchiWcGetChargData(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(0, this.mSetData.Mode);
            if (!i2b(this.mSetData.Mode)) {
                showItem(1, 0);
                showItem(2, 0);
                showItem(3, 0);
                showItem(4, 0);
                showItem(5, 0);
                showItem(6, 0);
                showItem(7, 0);
                return;
            }
            showItem(1, 1);
            showItem(2, 1);
            showItem(3, 1);
            showItem(4, 1);
            showItem(5, 1);
            showItem(6, 1);
            updateItem(2, this.mSetData.StartTimeH, String.format("%02d", new Object[]{Integer.valueOf(this.mSetData.StartTimeH)}));
            updateItem(3, this.mSetData.StartTimeM, String.format("%02d", new Object[]{Integer.valueOf(this.mSetData.StartTimeM)}));
            updateItem(4, this.mSetData.EndTimeH, String.format("%02d", new Object[]{Integer.valueOf(this.mSetData.EndTimeH)}));
            updateItem(5, this.mSetData.EndTimeM, String.format("%02d", new Object[]{Integer.valueOf(this.mSetData.EndTimeM)}));
            updateItem(6, this.mSetData.CycMode);
            if (!i2b(this.mSetData.CycMode)) {
                showItem(7, 0);
                return;
            }
            showItem(7, 1);
            updateItem(7, setCycSta(this.mSetData.Week));
        }
    }

    private int[] setCycSta(int cycSta) {
        ArrayList<Integer> mList = new ArrayList<>();
        if ((cycSta & 64) > 0) {
            mList.add(0);
        }
        if ((cycSta & 32) > 0) {
            mList.add(1);
        }
        if ((cycSta & 16) > 0) {
            mList.add(2);
        }
        if ((cycSta & 8) > 0) {
            mList.add(3);
        }
        if ((cycSta & 4) > 0) {
            mList.add(4);
        }
        if ((cycSta & 2) > 0) {
            mList.add(5);
        }
        if ((cycSta & 1) > 0) {
            mList.add(6);
        }
        int[] cycStaInt = new int[mList.size()];
        for (int i = 0; i < mList.size(); i++) {
            cycStaInt[i] = mList.get(i).intValue();
        }
        return cycStaInt;
    }

    public void QueryData() {
    }
}
