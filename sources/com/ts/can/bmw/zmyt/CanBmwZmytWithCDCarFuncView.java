package com.ts.can.bmw.zmyt;

import android.app.Activity;
import android.view.View;
import com.ts.MainUI.R;
import com.ts.can.CanScrollCarInfoView;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Mcu;

public class CanBmwZmytWithCDCarFuncView extends CanScrollCarInfoView {
    private static final int ITEM_CAMERA = 0;
    private static final int ITEM_FRONT_DOOR = 6;
    private static final int ITEM_MAX = 7;
    private static final int ITEM_MIN = 0;
    private static final int ITEM_REAR_DOOR = 7;
    private static final int ITEM_RVS_DELAY = 5;
    private static final int ITEM_R_CAMERA = 1;
    private static final int ITEM_SPEECH_MODE = 2;
    private static final int ITEM_SPEED_DW = 4;
    private static final int ITEM_SW = 3;
    private static int m_Camerb = 0;
    private static int m_RCamerb = 0;
    private static int m_RvsDelayb = 0;
    private static int m_SpeechModeb = 0;
    private static int m_SpeedDwb = 0;
    private static int m_Swb = 0;

    public CanBmwZmytWithCDCarFuncView(Activity activity) {
        super(activity, 8);
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_camera_360, R.string.can_tigger7_start_avm, R.string.can_sw_speech_mode, R.string.can_swkey_pass, R.string.can_speed_dw, R.string.can_rvs_keep, R.string.can_front_door, R.string.can_rear_door};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP};
        this.mPopValueIds[0] = new int[]{R.string.can_rvs_cvbs, R.string.can_rvs_carmode};
        this.mPopValueIds[4] = new int[]{R.string.can_speed_kmh, R.string.can_speed_mph};
        this.mPopValueIds[5] = new int[]{R.string.can_0s, R.string.can_3s, R.string.can_5s, R.string.can_7s};
        this.mPopValueIds[6] = new int[]{R.string.str_fs_normal, R.string.str_fs_swap, R.string.str_fs_hide};
        this.mPopValueIds[7] = new int[]{R.string.str_fs_normal, R.string.str_fs_swap, R.string.str_fs_hide};
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                int temp = FtSet.Getlgb2() & 65520;
                if ((FtSet.Getlgb2() & 15) > 0) {
                    FtSet.Setlgb2(temp);
                    Mcu.SendXKey(40);
                    return;
                }
                FtSet.Setlgb2(temp | 1);
                Mcu.SendXKey(41);
                return;
            case 2:
                if (FtSet.Getlgb5() > 0) {
                    FtSet.Setlgb5(0);
                    return;
                } else {
                    FtSet.Setlgb5(1);
                    return;
                }
            case 3:
                int temp2 = FtSet.Getlgb4();
                if ((FtSet.Getlgb4() & 240) > 0) {
                    FtSet.Setlgb4(65295 & temp2);
                    return;
                } else {
                    FtSet.Setlgb4(temp2 | 16);
                    return;
                }
            default:
                return;
        }
    }

    public static int IsCameraMdoe() {
        return FtSet.Getlgb1();
    }

    public static int IsSwPass() {
        return (FtSet.Getlgb4() & 240) >> 4;
    }

    public static int BwmZmytSpeedDw() {
        return FtSet.Getlgb4() & 15;
    }

    public static int IsHaveRCamera() {
        return FtSet.Getlgb2() & 15;
    }

    public static int RvsDelay() {
        return (FtSet.Getlgb2() & 240) >> 4;
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                if (item == 0) {
                    FtSet.Setlgb1(0);
                    Mcu.SendXKey(34);
                    return;
                } else if (item == 1) {
                    FtSet.Setlgb1(1);
                    Mcu.SendXKey(33);
                    return;
                } else {
                    return;
                }
            case 4:
                int temp = FtSet.Getlgb4() & 65520;
                if (item == 0) {
                    FtSet.Setlgb4(temp);
                    return;
                } else if (item == 1) {
                    FtSet.Setlgb4(temp | 1);
                    return;
                } else {
                    return;
                }
            case 5:
                int temp2 = FtSet.Getlgb2() & 65295;
                if (item == 0) {
                    FtSet.Setlgb2(temp2);
                    Mcu.SendXKey(42);
                    return;
                } else if (item == 1) {
                    FtSet.Setlgb2(temp2 | 16);
                    Mcu.SendXKey(43);
                    return;
                } else if (item == 2) {
                    FtSet.Setlgb2(temp2 | 32);
                    Mcu.SendXKey(44);
                    return;
                } else if (item == 3) {
                    FtSet.Setlgb2(temp2 | 48);
                    Mcu.SendXKey(45);
                    return;
                } else {
                    return;
                }
            case 6:
                FtSet.SetFdoor(item);
                return;
            case 7:
                FtSet.SetBdoor(item);
                return;
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
    }

    public void ResetData(boolean check) {
        if (m_Camerb != FtSet.Getlgb1() || !check) {
            m_Camerb = FtSet.Getlgb1();
            updateItem(0, FtSet.Getlgb1());
        }
        if (m_RCamerb != (FtSet.Getlgb2() & 15) || !check) {
            m_RCamerb = FtSet.Getlgb2() & 15;
            updateItem(1, m_RCamerb);
        }
        if (m_SpeechModeb != FtSet.Getlgb5() || !check) {
            m_SpeechModeb = FtSet.Getlgb5();
            updateItem(2, FtSet.Getlgb5());
        }
        if (m_Swb != ((FtSet.Getlgb4() & 240) >> 4) || !check) {
            m_Swb = (FtSet.Getlgb4() & 240) >> 4;
            updateItem(3, m_Swb);
        }
        if (m_SpeedDwb != (FtSet.Getlgb4() & 15) || !check) {
            m_SpeedDwb = FtSet.Getlgb4() & 15;
            updateItem(4, m_SpeedDwb);
        }
        if (m_RvsDelayb != ((FtSet.Getlgb2() & 240) >> 4) || !check) {
            m_RvsDelayb = (FtSet.Getlgb2() & 240) >> 4;
            updateItem(5, m_RvsDelayb);
        }
        updateItem(6, FtSet.GetFdoor());
        updateItem(7, FtSet.GetBdoor());
    }

    public void QueryData() {
    }
}
