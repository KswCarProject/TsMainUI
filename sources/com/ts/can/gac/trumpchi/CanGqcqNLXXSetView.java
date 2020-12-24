package com.ts.can.gac.trumpchi;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.CanDataInfo;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanNumInuptDlg;
import java.util.ArrayList;

public class CanGqcqNLXXSetView extends CanScrollCarInfoView {
    /* access modifiers changed from: private */
    public CanDataInfo.GCMixData mSetData;

    public CanGqcqNLXXSetView(Activity activity) {
        super(activity, 15);
    }

    public void onPositiveItem(int id, int[] item) {
        switch (id) {
            case 8:
                int itemNum = 0;
                for (int cycSta : item) {
                    itemNum += getCycSta(cycSta);
                }
                int itemNum2 = itemNum + (this.mSetData.CycSta & 1);
                int[] iArr = new int[2];
                int[] iArr2 = new int[2];
                int[] mYYData = setTime(this.mSetData.YyTime_h, this.mSetData.YyTime_m);
                int[] mYYZData = setTime(this.mSetData.Yy2Time_h, this.mSetData.Yy2Time_m);
                CanJni.TrumpchiRzcMixSet(2, mYYData[0], mYYData[1], mYYZData[0], mYYZData[1], itemNum2);
                return;
            default:
                return;
        }
    }

    private int getCycSta(int i) {
        switch (i) {
            case 0:
                return 2;
            case 1:
                return 4;
            case 2:
                return 8;
            case 3:
                return 16;
            case 4:
                return 32;
            case 5:
                return 64;
            case 6:
                return 128;
            default:
                return 0;
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 13:
                CanJni.TrumpchiRzcMixSet(3, item, 0, 0, 0, 0);
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
            case 1:
                CanJni.TrumpchiRzcMixSet(1, 1, 0, 0, 0, 0);
                return;
            case 2:
                new CanNumInuptDlg(getActivity(), new CanNumInuptDlg.onInputOK() {
                    public void onOK(String val, int num, int id) {
                        if (num >= 0 && num <= 23) {
                            int[] iArr = new int[2];
                            int[] iArr2 = new int[2];
                            int[] mYYData = CanGqcqNLXXSetView.this.setTime(num, CanGqcqNLXXSetView.this.mSetData.YyTime_m);
                            int[] mYYZData = CanGqcqNLXXSetView.this.setTime(CanGqcqNLXXSetView.this.mSetData.Yy2Time_h, CanGqcqNLXXSetView.this.mSetData.Yy2Time_m);
                            CanJni.TrumpchiRzcMixSet(2, mYYData[0], mYYData[1], mYYZData[0], mYYZData[1], CanGqcqNLXXSetView.this.mSetData.CycSta);
                        }
                    }
                }, 2, id);
                return;
            case 3:
                new CanNumInuptDlg(getActivity(), new CanNumInuptDlg.onInputOK() {
                    public void onOK(String val, int num, int id) {
                        if (num >= 0 && num <= 59) {
                            int[] iArr = new int[2];
                            int[] iArr2 = new int[2];
                            int[] mYYData = CanGqcqNLXXSetView.this.setTime(CanGqcqNLXXSetView.this.mSetData.YyTime_h, num);
                            int[] mYYZData = CanGqcqNLXXSetView.this.setTime(CanGqcqNLXXSetView.this.mSetData.Yy2Time_h, CanGqcqNLXXSetView.this.mSetData.Yy2Time_m);
                            CanJni.TrumpchiRzcMixSet(2, mYYData[0], mYYData[1], mYYZData[0], mYYZData[1], CanGqcqNLXXSetView.this.mSetData.CycSta);
                        }
                    }
                }, 2, id);
                return;
            case 4:
                new CanNumInuptDlg(getActivity(), new CanNumInuptDlg.onInputOK() {
                    public void onOK(String val, int num, int id) {
                        if (num >= 0 && num <= 23) {
                            int[] iArr = new int[2];
                            int[] iArr2 = new int[2];
                            int[] mYYData = CanGqcqNLXXSetView.this.setTime(CanGqcqNLXXSetView.this.mSetData.YyTime_h, CanGqcqNLXXSetView.this.mSetData.YyTime_m);
                            int[] mYYZData = CanGqcqNLXXSetView.this.setTime(num, CanGqcqNLXXSetView.this.mSetData.Yy2Time_m);
                            CanJni.TrumpchiRzcMixSet(2, mYYData[0], mYYData[1], mYYZData[0], mYYZData[1], CanGqcqNLXXSetView.this.mSetData.CycSta);
                        }
                    }
                }, 2, id);
                return;
            case 5:
                new CanNumInuptDlg(getActivity(), new CanNumInuptDlg.onInputOK() {
                    public void onOK(String val, int num, int id) {
                        if (num >= 0 && num <= 59) {
                            int[] iArr = new int[2];
                            int[] iArr2 = new int[2];
                            int[] mYYData = CanGqcqNLXXSetView.this.setTime(CanGqcqNLXXSetView.this.mSetData.YyTime_h, CanGqcqNLXXSetView.this.mSetData.YyTime_m);
                            int[] mYYZData = CanGqcqNLXXSetView.this.setTime(CanGqcqNLXXSetView.this.mSetData.Yy2Time_h, num);
                            CanJni.TrumpchiRzcMixSet(2, mYYData[0], mYYData[1], mYYZData[0], mYYZData[1], CanGqcqNLXXSetView.this.mSetData.CycSta);
                        }
                    }
                }, 2, id);
                return;
            case 7:
                int[] iArr = new int[2];
                int[] iArr2 = new int[2];
                int[] mYYData = setTime(this.mSetData.YyTime_h, this.mSetData.YyTime_m);
                int[] mYYZData = setTime(this.mSetData.Yy2Time_h, this.mSetData.Yy2Time_m);
                if ((this.mSetData.CycSta & 1) > 0) {
                    CanJni.TrumpchiRzcMixSet(2, mYYData[0], mYYData[1], mYYZData[0], mYYZData[1], this.mSetData.CycSta - 1);
                    return;
                }
                CanJni.TrumpchiRzcMixSet(2, mYYData[0], mYYData[1], mYYZData[0], mYYZData[1], this.mSetData.CycSta + 1);
                return;
            case 14:
                CanJni.TrumpchiRzcMixSet(4, this.mSetData.Ipedal + 1, 0, 0, 0, 0);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_gc_yytime, R.string.can_gc_ljcd, R.string.can_gc_yytime_h, R.string.can_gc_yytime_m, R.string.can_gc_yyztime_h, R.string.can_gc_yyztime_m, R.string.can_gc_yysz, R.string.can_gc_xhms, R.string.can_gc_xhrq, R.string.can_gc_jlcdkssj, R.string.can_gc_nlsz, R.string.can_gc_dldj, R.string.can_gc_nllzt, R.string.can_gc_nlhsdj, R.string.app_name};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP_CHECK, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.TEXT, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.VALUE, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH};
        this.mPopCheckValueIds[8] = new int[]{R.array.can_gc_week_arrays};
        this.mPopValueIds[13] = new int[]{R.string.can_Scsfctsy_3, R.string.can_ac_low, R.string.can_ac_high};
        this.mSetData = new CanDataInfo.GCMixData();
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        super.InitUI();
        ((CanItemSwitchList) this.mItemObjects[14]).GetBtn().setText("I-pedal");
    }

    public void ResetData(boolean check) {
        CanJni.TrumpchiRzcGetMixInfo(this.mSetData);
        if (!i2b(this.mSetData.UpdateOnce)) {
            return;
        }
        if (!check || i2b(this.mSetData.Update)) {
            this.mSetData.Update = 0;
            updateItem(2, this.mSetData.YyTime_h, String.format("%02d", new Object[]{Integer.valueOf(this.mSetData.YyTime_h)}));
            updateItem(3, this.mSetData.YyTime_m, String.format("%02d", new Object[]{Integer.valueOf(this.mSetData.YyTime_m)}));
            updateItem(4, this.mSetData.Yy2Time_h, String.format("%02d", new Object[]{Integer.valueOf(this.mSetData.Yy2Time_h)}));
            updateItem(5, this.mSetData.Yy2Time_m, String.format("%02d", new Object[]{Integer.valueOf(this.mSetData.Yy2Time_m)}));
            updateItem(7, this.mSetData.CycSta & 1);
            if ((this.mSetData.CycSta & 1) == 0) {
                showItem(8, 0);
            } else {
                showItem(8, 1);
                updateItem(8, setCycSta(this.mSetData.CycSta));
            }
            updateItem(9, this.mSetData.JlcdTime_day, setCDKSSJ());
            updateItem(11, this.mSetData.Dldj, String.format("%d", new Object[]{Integer.valueOf(this.mSetData.Dldj)}));
            updateItem(12, this.mSetData.Nllzt, String.format("%d", new Object[]{Integer.valueOf(this.mSetData.Nllzt)}));
            updateItem(13, this.mSetData.Nlhsdj);
            updateItem(14, this.mSetData.Ipedal);
        }
    }

    private String setCDKSSJ() {
        return String.valueOf(String.format("%d", new Object[]{Integer.valueOf(this.mSetData.JlcdTime_day)})) + getActivity().getResources().getString(R.string.can_days) + String.format("%02d", new Object[]{Integer.valueOf(this.mSetData.JlcdTime_h)}) + getActivity().getResources().getString(R.string.can_hour) + String.format("%02d", new Object[]{Integer.valueOf(this.mSetData.JlcdTime_m)}) + getActivity().getResources().getString(R.string.can_min);
    }

    /* access modifiers changed from: private */
    public int[] setTime(int hour, int minute) {
        return new int[]{(hour >> 2) & 7, ((hour << 6) & 192) + (minute & 63)};
    }

    private int[] setCycSta(int cycSta) {
        ArrayList<Integer> mList = new ArrayList<>();
        if ((cycSta & 2) > 0) {
            mList.add(0);
        }
        if ((cycSta & 4) > 0) {
            mList.add(1);
        }
        if ((cycSta & 8) > 0) {
            mList.add(2);
        }
        if ((cycSta & 16) > 0) {
            mList.add(3);
        }
        if ((cycSta & 32) > 0) {
            mList.add(4);
        }
        if ((cycSta & 64) > 0) {
            mList.add(5);
        }
        if ((cycSta & 128) > 0) {
            mList.add(6);
        }
        int[] cycStaInt = new int[mList.size()];
        for (int i = 0; i < mList.size(); i++) {
            cycStaInt[i] = mList.get(i).intValue();
        }
        return cycStaInt;
    }

    public void QueryData() {
        CanJni.TrumpchiRzcQuery(83, 0);
    }
}
