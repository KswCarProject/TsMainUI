package com.ts.can.bmw.zmyt;

import android.app.Activity;
import android.view.View;
import com.lgb.canmodule.Can;
import com.ts.MainUI.Evc;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanFunc;
import com.ts.can.CanScrollCarInfoView;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.Mcu;

public class CanBmwZmytWithCDCarFuncView extends CanScrollCarInfoView {
    private static final int ITEM_CAMERA = 0;
    private static final int ITEM_FRONT_DOOR = 6;
    private static final int ITEM_MAX = 10;
    private static final int ITEM_MIN = 0;
    private static final int ITEM_NAVI_PER = 10;
    private static final int ITEM_REAR_DOOR = 7;
    private static final int ITEM_RVS_DELAY = 5;
    private static final int ITEM_R_CAMERA = 1;
    private static final int ITEM_SPEECH_MODE = 2;
    private static final int ITEM_SPEED_DW = 4;
    private static final int ITEM_SW = 3;
    private static final int ITEM_XTCGFK = 8;
    private static final int ITEM_YCQPXS = 9;
    private static int m_Camerb = 0;
    private static int m_NaviPerb = 0;
    private static int m_RCamerb = 0;
    private static int m_RvsDelayb = 0;
    private static int m_SpeechModeb = 0;
    private static int m_SpeedDwb = 0;
    private static int m_Swb = 0;
    private static int m_Xtgfk = 0;
    private static int m_Ycqpxsb = 0;

    public CanBmwZmytWithCDCarFuncView(Activity activity) {
        super(activity, 11);
    }

    /* access modifiers changed from: protected */
    public void InitData() {
        this.mItemTitleIds = new int[]{R.string.can_camera_360, R.string.can_tigger7_start_avm, R.string.can_sw_speech_mode, R.string.can_swkey_pass, R.string.can_speed_dw, R.string.can_rvs_keep, R.string.can_front_door, R.string.can_rear_door, R.string.can_xtcgfk, R.string.can_ycqpxs, R.string.can_navi_pre};
        this.mItemTypes = new CanScrollCarInfoView.Item[]{CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.POP, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.SWITCH, CanScrollCarInfoView.Item.PROGRESS};
        if (CanFunc.getInstance().IsCore() != 1) {
            this.mPopValueIds[0] = new int[]{R.string.can_rvs_cvbs, R.string.can_rvs_carmode};
            this.mItemVisibles[9] = 0;
        } else {
            this.mPopValueIds[0] = new int[]{R.string.can_rvs_cvbs, R.string.can_rvs_carmode, R.string.can_rvs_avm, R.string.can_rvs_ahd};
            this.mItemVisibles[9] = 1;
        }
        this.mPopValueIds[4] = new int[]{R.string.can_speed_kmh, R.string.can_speed_mph};
        this.mPopValueIds[5] = new int[]{R.string.can_0s, R.string.can_3s, R.string.can_5s, R.string.can_7s};
        this.mPopValueIds[6] = new int[]{R.string.str_fs_normal, R.string.str_fs_swap, R.string.str_fs_hide};
        this.mPopValueIds[7] = new int[]{R.string.str_fs_normal, R.string.str_fs_swap, R.string.str_fs_hide};
        int[][] iArr = this.mProgressAttrs;
        int[] iArr2 = new int[4];
        iArr2[1] = 100;
        iArr2[2] = 1;
        iArr[10] = iArr2;
        if (CanFunc.getInstance().IsCore() == 1 && RvsMode() == 2) {
            this.mItemVisibles[1] = 0;
        }
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
                if ((FtSet.Getlgb4() & Can.CAN_VOLKS_XP) > 0) {
                    FtSet.Setlgb4(65295 & temp2);
                    return;
                } else {
                    FtSet.Setlgb4(temp2 | 16);
                    return;
                }
            case 8:
                int temp3 = FtSet.Getyw15() & -4;
                if ((FtSet.Getyw15() & 3) > 0) {
                    FtSet.Setyw15(temp3);
                    return;
                } else {
                    FtSet.Setyw15(temp3 | 1);
                    return;
                }
            case 9:
                int temp4 = FtSet.Getlgb2();
                if ((FtSet.Getlgb2() & 3840) > 0) {
                    FtSet.Setlgb2(61695 & temp4);
                    return;
                } else {
                    FtSet.Setlgb2(temp4 | 256);
                    return;
                }
            default:
                return;
        }
    }

    public static int RCamera() {
        return FtSet.Getlgb2() & 1;
    }

    public static int IsSwPass() {
        return (FtSet.Getlgb4() & Can.CAN_VOLKS_XP) >> 4;
    }

    public static int BwmZmytSpeedDw() {
        return FtSet.Getlgb4() & 15;
    }

    public static int RvsDelay() {
        return (FtSet.Getlgb2() & Can.CAN_VOLKS_XP) >> 4;
    }

    public static int RvsMode() {
        return FtSet.Getlgb1() & 3;
    }

    public static int Xtcgfk() {
        return FtSet.Getyw15() & 3;
    }

    public static int IsYcqpxs() {
        return (FtSet.Getlgb2() & 3840) >> 8;
    }

    public static int NaviPre() {
        return (FtSet.Getyw5() & 65280) >> 8;
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                int temp = FtSet.Getlgb1() & 65532;
                if (item == 0) {
                    FtSet.Setlgb1(temp);
                    Mcu.SendXKey(34);
                } else if (item == 1) {
                    FtSet.Setlgb1(temp | 1);
                    Mcu.SendXKey(33);
                } else if (item == 2) {
                    FtSet.Setlgb1(temp | 2);
                    Mcu.SendXKey(35);
                } else if (item == 3) {
                    FtSet.Setlgb1(temp | 3);
                    Mcu.SendXKey(36);
                    CanCameraUI.GetInstance().nLayoutReLoad = 1;
                }
                CanBmwZmytWithCDCarInitView.SetCamType(0, 0, 0);
                if (CanFunc.getInstance().IsCore() != 1) {
                    return;
                }
                if (RvsMode() == 2) {
                    showItem(1, 0);
                    Mcu.SendXKey(40);
                    return;
                }
                showItem(1, 1);
                if (RCamera() > 0) {
                    Mcu.SendXKey(41);
                    return;
                } else {
                    Mcu.SendXKey(40);
                    return;
                }
            case 4:
                int temp2 = FtSet.Getlgb4() & 65520;
                if (item == 0) {
                    FtSet.Setlgb4(temp2);
                    return;
                } else if (item == 1) {
                    FtSet.Setlgb4(temp2 | 1);
                    return;
                } else {
                    return;
                }
            case 5:
                int temp3 = FtSet.Getlgb2() & 65295;
                if (item == 0) {
                    FtSet.Setlgb2(temp3);
                    Mcu.SendXKey(42);
                    return;
                } else if (item == 1) {
                    FtSet.Setlgb2(temp3 | 16);
                    Mcu.SendXKey(43);
                    return;
                } else if (item == 2) {
                    FtSet.Setlgb2(temp3 | 32);
                    Mcu.SendXKey(44);
                    return;
                } else if (item == 3) {
                    FtSet.Setlgb2(temp3 | 48);
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
        switch (id) {
            case 10:
                FtSet.Setyw5((pos << 8) | (FtSet.Getyw5() & 255));
                if (Evc.GetInstance() != null && Iop.GetWorkMode() == 12) {
                    Evc.GetInstance().SetNaviVolDn(NaviPre());
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void ResetData(boolean check) {
        if (m_Camerb != RvsMode() || !check) {
            m_Camerb = RvsMode();
            updateItem(0, m_Camerb);
        }
        if (m_RCamerb != (FtSet.Getlgb2() & 15) || !check) {
            m_RCamerb = FtSet.Getlgb2() & 15;
            updateItem(1, m_RCamerb);
        }
        if (m_SpeechModeb != FtSet.Getlgb5() || !check) {
            m_SpeechModeb = FtSet.Getlgb5();
            updateItem(2, FtSet.Getlgb5());
        }
        if (m_Swb != ((FtSet.Getlgb4() & Can.CAN_VOLKS_XP) >> 4) || !check) {
            m_Swb = (FtSet.Getlgb4() & Can.CAN_VOLKS_XP) >> 4;
            updateItem(3, m_Swb);
        }
        if (m_SpeedDwb != (FtSet.Getlgb4() & 15) || !check) {
            m_SpeedDwb = FtSet.Getlgb4() & 15;
            updateItem(4, m_SpeedDwb);
        }
        if (m_RvsDelayb != ((FtSet.Getlgb2() & Can.CAN_VOLKS_XP) >> 4) || !check) {
            m_RvsDelayb = (FtSet.Getlgb2() & Can.CAN_VOLKS_XP) >> 4;
            updateItem(5, m_RvsDelayb);
        }
        updateItem(6, FtSet.GetFdoor());
        updateItem(7, FtSet.GetBdoor());
        if (m_Xtgfk != Xtcgfk() || !check) {
            m_Xtgfk = Xtcgfk();
            updateItem(8, m_Xtgfk == 0 ? 1 : 0);
        }
        if (m_Ycqpxsb != IsYcqpxs() || !check) {
            m_Ycqpxsb = IsYcqpxs();
            updateItem(9, m_Ycqpxsb);
        }
        if (m_NaviPerb != NaviPre() || !check) {
            m_NaviPerb = NaviPre();
            updateItem(10, m_NaviPerb, String.format("%d %%", new Object[]{Integer.valueOf(m_NaviPerb)}));
        }
    }

    public void QueryData() {
    }
}
