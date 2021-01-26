package com.ts.can.benc.withcd;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.view.View;
import com.lgb.canmodule.Can;
import com.lgb.canmodule.CanJni;
import com.ts.MainUI.Evc;
import com.ts.MainUI.MainTask;
import com.ts.MainUI.R;
import com.ts.can.CanCameraUI;
import com.ts.can.CanCommonActivity;
import com.ts.can.CanFunc;
import com.ts.canview.CanItemPopupList;
import com.ts.canview.CanItemProgressList;
import com.ts.canview.CanItemSwitchList;
import com.ts.canview.CanScrollList;
import com.yyw.ts70xhw.FtSet;
import com.yyw.ts70xhw.Iop;
import com.yyw.ts70xhw.Mcu;

public class CanBencWithCDCarFuncActivity extends CanCommonActivity implements CanItemPopupList.onPopItemClick, CanItemProgressList.onPosChange {
    private static final int ITEM_BENC_BOX_CAMERA = 7;
    private static final int ITEM_CAMERA = 0;
    private static final int ITEM_CLOCK_DISP = 12;
    private static final int ITEM_FRONT_DOOR = 9;
    private static final int ITEM_NAVI_PER = 8;
    private static final int ITEM_REAR_DOOR = 10;
    private static final int ITEM_RVS_DELAY = 5;
    private static final int ITEM_R_CAMERA = 1;
    private static final int ITEM_SPEECH_MODE = 2;
    private static final int ITEM_SPEED_DW = 3;
    private static final int ITEM_SSSB = 6;
    private static final int ITEM_TEMP_DW = 4;
    private static final int ITEM_XTCGFK = 11;
    private static final int ITEM_YCQPXS = 13;
    private static final int[] mClockStr = {R.string.can_time, R.string.can_compass, R.string.can_logo};
    private static final int[] mDoorStr1 = {R.string.str_fs_normal, R.string.str_fs_swap, R.string.str_fs_hide};
    private static int m_BenxBoxCamera = 0;
    private static int m_Camerb = 0;
    private static int m_Clockb = 0;
    private static int m_NaviPerb = 0;
    private static int m_RCamerb = 0;
    private static int m_RvsDelayb = 0;
    private static int m_SpeechModeb = 0;
    private static int m_SpeedDwb = 0;
    private static int m_Sssbb = 0;
    private static int m_TempDwb = 0;
    private static int m_Xtgfk = 0;
    private static int m_Ycqpxsb = 0;
    private int[] mBencBoxCameraArr = {R.string.can_auto_check, R.string.can_car_camera, R.string.can_tft_cvbs, R.string.can_tft_ahd, R.string.can_host_camera};
    private int[] mCamera8259Arr = {R.string.can_rvs_cvbs, R.string.can_rvs_carmode, R.string.can_rvs_avm, R.string.can_rvs_ahd};
    private int[] mCameraArr = {R.string.can_rvs_cvbs, R.string.can_rvs_carmode, R.string.can_rvs_vga};
    private CanItemPopupList mItemBencBoxCamera;
    private CanItemPopupList mItemCamera;
    private CanItemPopupList mItemClock;
    private CanItemPopupList mItemFrontDoor;
    private CanItemProgressList mItemNaviPer;
    private CanItemSwitchList mItemRCamera;
    private CanItemPopupList mItemRearDoor;
    private CanItemPopupList mItemRvsDelay;
    private CanItemSwitchList mItemSpeechMode;
    private CanItemPopupList mItemSpeedDw;
    private CanItemSwitchList mItemSssb;
    private CanItemPopupList mItemTempDw;
    private CanItemSwitchList mItemXtcgfk;
    private CanItemSwitchList mItemYcpqpxs;
    private CanScrollList mManager;
    private int[] mRvsDealyArr = {R.string.can_0s, R.string.can_3s, R.string.can_5s, R.string.can_7s};
    private int[] mSpeedDwArr = {R.string.can_speed_kmh, R.string.can_speed_mph};
    private String[] mTempDwArr = {"℃", "℉"};

    /* access modifiers changed from: protected */
    public int GetContentLayoutId() {
        return R.layout.activity_can_comm_list;
    }

    /* access modifiers changed from: protected */
    public void InitUI() {
        this.mManager = new CanScrollList(this);
        if (CanFunc.getInstance().IsCore() != 1) {
            this.mItemCamera = AddPopupItem(R.string.can_camera_360, this.mCameraArr, 0);
        } else {
            this.mItemCamera = AddPopupItem(R.string.can_camera_360, this.mCamera8259Arr, 0);
        }
        this.mItemRCamera = AddCheckItem(R.string.can_tigger7_start_avm, 1);
        this.mItemSpeechMode = AddCheckItem(R.string.can_sw_speech_mode, 2);
        this.mItemSpeedDw = AddPopupItem(R.string.can_speed_dw, this.mSpeedDwArr, 3);
        this.mItemTempDw = AddPopupItem(R.string.can_temp_dw, this.mTempDwArr, 4);
        this.mItemRvsDelay = AddPopupItem(R.string.can_rvs_keep, this.mRvsDealyArr, 5);
        this.mItemSssb = AddCheckItem(R.string.can_sssb, 6);
        this.mItemBencBoxCamera = AddPopupItem(R.string.can_benca_box_camera, this.mBencBoxCameraArr, 7);
        if (CanJni.GetSubType() != 1) {
            this.mItemBencBoxCamera.ShowGone(false);
        }
        this.mItemNaviPer = AddProgressItem(R.string.can_navi_pre, 8);
        this.mItemNaviPer.SetMinMax(0, 100);
        this.mItemNaviPer.SetUserValText();
        this.mItemFrontDoor = AddPopupItem(R.string.can_front_door, mDoorStr1, 9);
        this.mItemRearDoor = AddPopupItem(R.string.can_rear_door, mDoorStr1, 10);
        this.mItemXtcgfk = AddCheckItem(R.string.can_xtcgfk, 11);
        this.mItemClock = AddPopupItem(R.string.can_clocl_set, mClockStr, 12);
        this.mItemYcpqpxs = AddCheckItem(R.string.can_ycqpxs, 13);
        if (CanFunc.getInstance().IsCore() != 1) {
            this.mItemYcpqpxs.ShowGone(false);
        } else if (RvsMode() == 2) {
            this.mItemRCamera.ShowGone(false);
        }
    }

    /* access modifiers changed from: protected */
    public CanItemSwitchList AddCheckItem(int resId, int Id) {
        CanItemSwitchList item = new CanItemSwitchList(this, resId);
        item.SetIdClickListener(this, Id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(true);
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopupItem(int text, String[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, val, (CanItemPopupList.onPopItemClick) this);
        item.SetId(id);
        this.mManager.AddView(item.GetView());
        item.ShowGone(true);
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemPopupList AddPopupItem(int text, int[] val, int id) {
        CanItemPopupList item = new CanItemPopupList((Context) this, text, val, (CanItemPopupList.onPopItemClick) this);
        item.SetId(id);
        this.mManager.AddView(item.GetView());
        return item;
    }

    /* access modifiers changed from: protected */
    public CanItemProgressList AddProgressItem(int resId, int Id) {
        CanItemProgressList item = new CanItemProgressList((Context) this, resId);
        item.SetIdCallBack(Id, this);
        this.mManager.AddView(item.GetView());
        return item;
    }

    /* access modifiers changed from: protected */
    public void ResetData(boolean check) {
        boolean z = true;
        if (m_Camerb != (FtSet.Getlgb1() & 15) || !check) {
            m_Camerb = FtSet.Getlgb1() & 15;
            this.mItemCamera.SetSel(m_Camerb);
        }
        if (m_RCamerb != (FtSet.Getlgb2() & 15) || !check) {
            m_RCamerb = FtSet.Getlgb2() & 15;
            this.mItemRCamera.SetCheck(m_RCamerb);
        }
        if (m_SpeechModeb != (FtSet.Getlgb5() & 1) || !check) {
            m_SpeechModeb = FtSet.Getlgb5() & 1;
            this.mItemSpeechMode.SetCheck(m_SpeechModeb);
        }
        if (m_SpeedDwb != (FtSet.Getlgb2() & Can.CAN_VOLKS_XP) || !check) {
            m_SpeedDwb = (FtSet.Getlgb2() >> 4) & 15;
            this.mItemSpeedDw.SetSel(m_SpeedDwb);
        }
        if (m_TempDwb != (FtSet.Getlgb2() & 3840) || !check) {
            m_TempDwb = (FtSet.Getlgb2() >> 8) & 15;
            this.mItemTempDw.SetSel(m_TempDwb);
        }
        if (m_RvsDelayb != ((FtSet.Getlgb5() & 12) >> 2) || !check) {
            m_RvsDelayb = (FtSet.Getlgb5() >> 2) & 3;
            this.mItemRvsDelay.SetSel(m_RvsDelayb);
        }
        if (m_Sssbb != (FtSet.Getyw5() & 1) || !check) {
            m_Sssbb = FtSet.Getyw5() & 1;
            this.mItemSssb.SetCheck(m_Sssbb == 0);
        }
        if (CanJni.GetSubType() == 1 && (m_BenxBoxCamera != CanBencWithCDTouchDeal.nCameraMode || !check)) {
            m_BenxBoxCamera = CanBencWithCDTouchDeal.nCameraMode;
            this.mItemBencBoxCamera.SetSel(m_BenxBoxCamera);
        }
        if (m_NaviPerb != BencZmytNaviPre() || !check) {
            m_NaviPerb = BencZmytNaviPre();
            this.mItemNaviPer.SetCurVal(m_NaviPerb);
            this.mItemNaviPer.SetValText(String.valueOf(m_NaviPerb) + "%");
        }
        this.mItemFrontDoor.SetSel(FtSet.GetFdoor());
        this.mItemRearDoor.SetSel(FtSet.GetBdoor());
        if (m_Xtgfk != Xtcgfk() || !check) {
            m_Xtgfk = Xtcgfk();
            CanItemSwitchList canItemSwitchList = this.mItemXtcgfk;
            if (m_Xtgfk != 0) {
                z = false;
            }
            canItemSwitchList.SetCheck(z);
        }
        if (m_Clockb != ClockDisp() || !check) {
            m_Clockb = ClockDisp();
            this.mItemClock.SetSel(m_Clockb);
        }
        if (m_Ycqpxsb != IsYcqpxs() || !check) {
            m_Ycqpxsb = IsYcqpxs();
            this.mItemYcpqpxs.SetCheck(m_Ycqpxsb);
        }
    }

    public static int RCamera() {
        return FtSet.Getlgb2() & 15;
    }

    public static int RvsDelay() {
        return (FtSet.Getlgb5() & 12) >> 2;
    }

    public static int RvsMode() {
        return FtSet.Getlgb1() & 15;
    }

    public static int BencZmytSpeedDw() {
        return (FtSet.Getlgb2() & Can.CAN_VOLKS_XP) >> 4;
    }

    public static int BencZmytTempDw() {
        return FtSet.Getlgb2() & 3840;
    }

    public static int BencZmytNaviPre() {
        return (FtSet.Getyw5() & 65280) >> 8;
    }

    public static int AmbientLightSta() {
        return FtSet.Getyw12() & 255;
    }

    public static int AmbientLightMode() {
        return (FtSet.Getyw12() & 65280) >> 8;
    }

    public static int AmbientLightBri() {
        return (FtSet.Getyw12() & 16711680) >> 16;
    }

    public static int AmbientLightR() {
        return (FtSet.Getyw12() & ViewCompat.MEASURED_STATE_MASK) >> 24;
    }

    public static int AmbientLightG() {
        return FtSet.Getyw13() & 255;
    }

    public static int AmbientLightB() {
        return (FtSet.Getyw13() & 65280) >> 8;
    }

    public static int ClockDisp() {
        return (FtSet.Getlgb3() & 192) >> 6;
    }

    public static int IsYcqpxs() {
        return (FtSet.Getlgb3() & 3072) >> 10;
    }

    public static void AmbientLightSet(int Cmd, int Para, int Para2, int Para3) {
        int yw12 = FtSet.Getyw12();
        int Getyw13 = FtSet.Getyw13();
        switch (Cmd) {
            case 0:
                int yw122 = yw12 & -256;
                if (Para != 0) {
                    FtSet.Setyw12(yw122);
                    break;
                } else {
                    FtSet.Setyw12(yw122 | 1);
                    break;
                }
            case 1:
                if (Para <= 3) {
                    FtSet.Setyw12((Para << 8) | (yw12 & -65281));
                    break;
                }
                break;
            case 2:
                if (Para <= 100) {
                    FtSet.Setyw12((Para << 16) | (yw12 & -16711681));
                    break;
                }
                break;
            case 3:
                FtSet.Setyw12((Para << 24) | (yw12 & ViewCompat.MEASURED_SIZE_MASK));
                FtSet.Setyw13((FtSet.Getyw13() & -256) | Para2);
                FtSet.Setyw13((Para3 << 8) | (FtSet.Getyw13() & -65281));
                break;
        }
        CanJni.BencZmytAmbientLightCmd(2, AmbientLightSta(), AmbientLightMode(), AmbientLightBri(), AmbientLightR(), AmbientLightG(), AmbientLightB(), 0, 0, 0);
    }

    public static int Xtcgfk() {
        return FtSet.Getyw15() & 3;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        MainTask.GetInstance().SetUserCallBack(this);
        ResetData(false);
    }

    public void onClick(View v) {
        switch (((Integer) v.getTag()).intValue()) {
            case 1:
                int temp = FtSet.Getlgb2();
                if ((FtSet.Getlgb2() & 15) > 0) {
                    FtSet.Setlgb2(65520 & temp);
                    Mcu.SendXKey(40);
                    return;
                }
                FtSet.Setlgb2(temp | 1);
                Mcu.SendXKey(41);
                return;
            case 2:
                int temp2 = FtSet.Getlgb5();
                if ((temp2 & 1) > 0) {
                    FtSet.Setlgb5(temp2 & 65534);
                    return;
                } else {
                    FtSet.Setlgb5(temp2 | 1);
                    return;
                }
            case 6:
                int temp3 = FtSet.Getyw5();
                if ((temp3 & 1) > 0) {
                    FtSet.Setyw5(temp3 & 65534);
                    CanJni.BencZmytCommonCmd(1, 85, 1);
                    return;
                }
                FtSet.Setyw5(temp3 | 1);
                CanJni.BencZmytCommonCmd(1, 85, 0);
                return;
            case 11:
                int temp4 = FtSet.Getyw15() & -4;
                if ((FtSet.Getyw15() & 3) > 0) {
                    FtSet.Setyw15(temp4);
                    return;
                } else {
                    FtSet.Setyw15(temp4 | 1);
                    return;
                }
            case 13:
                int temp5 = FtSet.Getlgb3() & 62463;
                if ((FtSet.Getlgb3() & 3072) > 0) {
                    FtSet.Setlgb3(temp5);
                    return;
                } else {
                    FtSet.Setlgb3(temp5 | 1024);
                    return;
                }
            default:
                return;
        }
    }

    public void onItem(int id, int item) {
        switch (id) {
            case 0:
                int temp = FtSet.Getlgb1() & 65520;
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
                CanBencWithCDCarInitActivity.SetCamType(0, 0, 0);
                if (CanFunc.getInstance().IsCore() != 1) {
                    return;
                }
                if (RvsMode() == 2) {
                    this.mItemRCamera.ShowGone(false);
                    Mcu.SendXKey(40);
                    return;
                }
                this.mItemRCamera.ShowGone(true);
                if (RCamera() > 0) {
                    Mcu.SendXKey(41);
                    return;
                } else {
                    Mcu.SendXKey(40);
                    return;
                }
            case 3:
                int temp2 = FtSet.Getlgb2();
                if (item == 0) {
                    FtSet.Setlgb2(65295 & temp2);
                    return;
                } else if (item == 1) {
                    FtSet.Setlgb2(temp2 | 16);
                    return;
                } else {
                    return;
                }
            case 4:
                int temp3 = FtSet.Getlgb2();
                if (item == 0) {
                    FtSet.Setlgb2(61695 & temp3);
                    return;
                } else if (item == 1) {
                    FtSet.Setlgb2(temp3 | 256);
                    return;
                } else {
                    return;
                }
            case 5:
                int temp4 = FtSet.Getlgb5() & 65523;
                if (item == 0) {
                    FtSet.Setlgb5(temp4);
                    Mcu.SendXKey(42);
                    return;
                } else if (item == 1) {
                    FtSet.Setlgb5(temp4 | 4);
                    Mcu.SendXKey(43);
                    return;
                } else if (item == 2) {
                    FtSet.Setlgb5(temp4 | 8);
                    Mcu.SendXKey(44);
                    return;
                } else if (item == 3) {
                    FtSet.Setlgb5(temp4 | 12);
                    Mcu.SendXKey(45);
                    return;
                } else {
                    return;
                }
            case 7:
                if (item == 4) {
                    item = 16;
                }
                CanBencWithCDTouchDeal.Set(17, item);
                return;
            case 9:
                FtSet.SetFdoor(item);
                return;
            case 10:
                FtSet.SetBdoor(item);
                return;
            case 12:
                int temp5 = FtSet.Getlgb3() & 65343;
                if (item == 0) {
                    FtSet.Setlgb3(temp5);
                    return;
                } else if (item == 1) {
                    FtSet.Setlgb3(temp5 | 64);
                    return;
                } else if (item == 2) {
                    FtSet.Setlgb3(temp5 | 128);
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    public void onChanged(int id, int pos) {
        switch (id) {
            case 8:
                FtSet.Setyw5((pos << 8) | (FtSet.Getyw5() & 255));
                if (Evc.GetInstance() != null && Iop.GetWorkMode() == 12) {
                    Evc.GetInstance().SetNaviVolDn(BencZmytNaviPre());
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void QueryData() {
        if (CanJni.GetSubType() == 1) {
            CanBencWithCDTouchDeal.Query(16);
        }
    }
}
