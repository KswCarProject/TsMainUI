package com.ts.can.bmw.zmyt;

import android.app.Activity;
import android.view.View;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.ts.factoryset.FsCanActivity;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class CanBmwZmytWithCDCarInitView extends CanScrollCarInfoView {
    private static final int ITEM_AUX_CONFIG = 0;
    private static final int ITEM_AUX_PARA1 = 1;
    private static final int ITEM_AUX_PARA2 = 2;
    private static final int ITEM_AUX_PARA3 = 3;
    private static final int ITEM_AUX_PARA4 = 4;
    private static final int ITEM_HOST_RES = 5;
    private static final int ITEM_MAX = 5;
    private static final int ITEM_MIN = 0;
    private static int m_AuxConfigb = 0;
    private static int m_AuxPara1b = 0;
    private static int m_AuxPara2b = 0;
    private static int m_AuxPara3b = 0;
    private static int m_AuxPara4b = 0;
    private static int m_HostResb = 0;

    public CanBmwZmytWithCDCarInitView(Activity activity) {
        super(activity, 6);
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_bmw_entauxmode, R.string.can_bmw_entauxmode_para, R.string.can_bmw_entauxmode_para2, R.string.can_bmw_entauxmode_para3, R.string.can_bmw_entauxmode_para4, R.string.can_host_res};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.array.can_ent_aux_arrays};
        this.mPopValueIds[1] = new int[]{R.array.can_ent_aux_para_arrays};
        this.mPopValueIds[2] = new int[]{R.array.can_ent_aux_para_arrays};
        this.mPopValueIds[3] = new int[]{R.array.can_ent_aux_para_arrays};
        this.mPopValueIds[4] = new int[]{R.array.can_ent_aux_para_arrays};
        this.mPopValueIds[5] = new int[]{R.array.can_digital_arr};
    }

    public void ResetData(boolean check) {
        if (m_AuxConfigb != (FtSet.Getlgb3() & 15) || !check) {
            m_AuxConfigb = FtSet.Getlgb3() & 15;
            updateItem(0, m_AuxConfigb);
            if (m_AuxConfigb == 1) {
                showItem(1, 0);
                showItem(2, 1);
                showItem(3, 1);
                showItem(4, 0);
            } else if (m_AuxConfigb == 2) {
                showItem(1, 0);
                showItem(2, 0);
                showItem(3, 0);
                showItem(4, 1);
            } else {
                showItem(1, 1);
                showItem(2, 0);
                showItem(3, 0);
                showItem(4, 0);
            }
        }
        if (m_AuxPara1b != ((FtSet.Getlgb3() & 240) >> 4) || !check) {
            m_AuxPara1b = (FtSet.Getlgb3() & 240) >> 4;
            updateItem(1, m_AuxPara1b);
        }
        if (m_AuxPara2b != ((FtSet.Getlgb3() & 3840) >> 8) || !check) {
            m_AuxPara2b = (FtSet.Getlgb3() & 3840) >> 8;
            updateItem(2, m_AuxPara2b);
        }
        if (m_AuxPara3b != ((FtSet.Getlgb3() & 61440) >> 12) || !check) {
            m_AuxPara3b = (FtSet.Getlgb3() & 61440) >> 12;
            updateItem(3, m_AuxPara3b);
        }
        if (m_AuxPara4b != ((FtSet.Getlgb4() & 61440) >> 12) || !check) {
            m_AuxPara4b = (FtSet.Getlgb4() & 61440) >> 12;
            updateItem(4, m_AuxPara4b);
        }
        if (m_HostResb != ((FtSet.Getlgb4() & 3840) >> 8) || !check) {
            m_HostResb = (FtSet.Getlgb4() & 3840) >> 8;
            updateItem(5, m_HostResb);
        }
    }

    public void onClick(View v) {
        int intValue = ((Integer) v.getTag()).intValue();
    }

    public static int IsAuxConfig() {
        if ((FtSet.Getlgb3() & 15) >= 3) {
            return 1;
        }
        return (FtSet.Getlgb3() & 15) + 1;
    }

    public static int IsAuxPara1() {
        if (((FtSet.Getlgb3() & 240) >> 4) == 0 || ((FtSet.Getlgb3() & 240) >> 4) >= 8) {
            return 1;
        }
        return ((FtSet.Getlgb3() & 240) >> 4) + 1;
    }

    public static int IsAuxPara2() {
        if (((FtSet.Getlgb3() & 3840) >> 8) == 0 || ((FtSet.Getlgb3() & 3840) >> 8) >= 8) {
            return 1;
        }
        return ((FtSet.Getlgb3() & 3840) >> 8) + 1;
    }

    public static int IsAuxPara3() {
        if (((FtSet.Getlgb3() & 61440) >> 12) == 0 || ((FtSet.Getlgb3() & 61440) >> 12) >= 8) {
            return 1;
        }
        return ((FtSet.Getlgb3() & 61440) >> 12) + 1;
    }

    public static int IsAuxPara4() {
        if (((FtSet.Getlgb4() & 61440) >> 12) == 0 || ((FtSet.Getlgb4() & 61440) >> 12) >= 8) {
            return 1;
        }
        return ((FtSet.Getlgb4() & 61440) >> 12) + 1;
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                FtSet.Setlgb3((FtSet.Getlgb3() & 65520) | item);
                return;
            case 1:
                FtSet.Setlgb3((item << 4) | (FtSet.Getlgb3() & 65295));
                return;
            case 2:
                FtSet.Setlgb3((item << 8) | (FtSet.Getlgb3() & 61695));
                return;
            case 3:
                FtSet.Setlgb3((item << 12) | (FtSet.Getlgb3() & FsCanActivity.DOOR_UPDATE_ALL));
                return;
            case 4:
                FtSet.Setlgb4((item << 12) | (FtSet.Getlgb4() & FsCanActivity.DOOR_UPDATE_ALL));
                return;
            case 5:
                FtSet.Setlgb4((item << 8) | (FtSet.Getlgb4() & 61695));
                Mcu.SendXKey(((FtSet.Getlgb4() & 3840) >> 8) + 50);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void QueryData() {
    }
}
